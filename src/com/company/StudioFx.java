package com.company;

import java.awt.*;
import java.io.IOException;
import java.util.Vector;

public class StudioFx extends PianoCartesiano{
    //** ATTRIBUTI **//
    //* GRAFICA *//
    private static final long serialVersionUID = 1L;
    Graphics2D g1;
    private double minY = Double.MAX_VALUE;
    private double maxY = Double.MIN_VALUE;

    //* PARAMETRI ANALISI FUNZIONE *//
    private double Piniziale;
    private double Pfinale;
    private double Intervallo;

    //* PARAMETRI FUNZIONE *//
    private double a;
    private double b;
    private double c;
    private double d;
    private Grado gradoFx;
    private double exp = 1;
    private int nCoords;

    //* MATRICI DATI FINALI *//
    private double datiXY[][];
    private double root[][];
    private double minMax[][];
    private double flex[][];


    //* COTRUTTORE *//
    public StudioFx(double[] value, double[] parametri) throws Exception{
        if (parametri.length == 3){
            this.Piniziale = parametri[0];
            this.Pfinale = parametri[1];
            this.Intervallo = parametri[2];
        }else { throw new IllegalArgumentException("Formato parametri non corretto"); }

        switch (value.length){
            case 2 -> {
                this.a = value[0];
                this.b = value[1];
                this.gradoFx = Grado.PRIMO;
            }
            case 3 -> {
                this.a = value[0];
                this.b = value[1];
                this.c = value[2];
                this.gradoFx = Grado.SECONDO;
            }
            case 4 -> {
                this.a = value[0];
                this.b = value[1];
                this.c = value[2];
                this.d = value[3];
                this.gradoFx = Grado.TERZO;
            }
            default -> { throw new Exception(); }
        }
    }

    //* FUNZIONI MATEMATICHE *//
    private double f(double x){
        switch (gradoFx){
            case PRIMO -> { return a * Math.pow(x, exp) + b; }
            case SECONDO -> { return a * Math.pow(x, exp+1) + b * x + c; }
            case TERZO -> { return  a * Math.pow(x, exp+2) + b * Math.pow(x, exp+1) + c * x + d; }
            default -> throw new ArithmeticException();
        }
    }
    private double F(double x){
        switch (gradoFx){
            case PRIMO -> { return 1 * a * Math.pow(x, 1);}
            case SECONDO -> { return 2 * a * Math.pow(x, 2-1) + b; }
            case TERZO -> { return 3 * a * Math.pow(x, 3-1) + 2 * b * Math.pow(x, 2-1) + c;}
            default -> throw new ArithmeticException();
        }
    }
    private double F2(double x){
        switch (gradoFx){
            case PRIMO -> { return a;}
            case SECONDO -> { return 2 * a * x; }
            case TERZO -> { return 3 * a * x + b;}
            default -> throw new ArithmeticException();
        }
    }

    //* COSTRUTTORE FUNZIONE *//
    public void scanFx() throws Exception {
        nCoords = (int)Math.floor(Math.abs(Piniziale - Pfinale)/ Intervallo)+1;
        datiXY = new double[nCoords][2];
        double x= Piniziale;
        double y = 0;

        int i=0;

        //NullPointerException
        try{
            while (x <= Pfinale){
                y = f(x);

                datiXY[i][0]=x;
                datiXY[i][1]=y;

                if (y > maxY) { maxY = y; }
                if (y < minY) { minY = y; }

                x+= Intervallo;
                i++;
            }
        }catch (NullPointerException ex){
            System.err.println(ex.getMessage());
        }

        if (Piniziale >0)
            setxOrigine(0);
        else
            setxOrigine(Piniziale);
            setyOrigine(Math.abs((maxY-minY)) / 2.0);
        if ((Pfinale - Piniziale) > (maxY-minY))
            setMaxY(Pfinale - Piniziale);
        else
            setMaxY(maxY - minY);

        GetRoot();
        GetMinMax();
        GetFlex();
    }

    //* SEGNO FUNZIONE *//
    private static int sign(double x) {
        if (x < 0.0){
            return -1;
        }else if (x > 0.0){
            return 1;
        }else{ return 0;}
    }

    //* ZERI DELLA FUNZIONE *//
    private void GetRoot(){
        Vector<double[]> root = new Vector();

        double x = Piniziale;
        double ox = x;
        double y = f(x);
        double oy = y;
        int s = sign(y);
        int os = s;

        try{
            for (; x <= Pfinale ; x += Intervallo) {
                s = sign(y = f(x));
                if (s == 0) {
                    System.out.println(x);
                } else if (s != os) {
                    double dx = x - ox;
                    double dy = y - oy;
                    double cx = x - dx * (y / dy);
                    // [x, y]
                    root.addElement(new double[]{
                            Double.parseDouble(String.format("%.2f", cx).replace(",",".")),
                            Double.parseDouble(String.format("%.2f", f(cx)).replace(",","."))
                    });
                }
                ox = x; oy = y; os = s;
            }
        }catch (NullPointerException ex){
            System.err.println(ex.getMessage());
        }

        this.root = root.toArray(new double[root.size()][2]);
    }

    //* MINIMI E MASIMI DELLA FUNZIONE *//
    private void GetMinMax() throws NullPointerException {
        Vector<double[]> minMax = new Vector();

        double x = Piniziale;
        double ox = x;
        double y = f(x);
        double oy = y;
        int s = sign(y);
        int os = s;

        try{

            for (; x <= Pfinale ; x += Intervallo) {
                s = sign(y = F(x));
                if (s == 0) {
                    System.out.println(x);
                } else if (s != os) {
                    double dx = x - ox;
                    double dy = y - oy;
                    double cx = x - dx * (y / dy);
                    // [x, y]
                    minMax.addElement(new double[]{
                            Double.parseDouble(String.format("%.2f", cx).replace(",",".")),
                            Double.parseDouble(String.format("%.2f", f(cx)).replace(",","."))
                    });
                }
                ox = x; oy = y; os = s;
            }
        }catch (NullPointerException ex){
            System.err.println(ex.getMessage());
        }

        this.minMax = minMax.toArray(new double[minMax.size()][2]);
    }

    //* FLESSI DELLA FUNZIONE *//
    private void GetFlex() throws NullPointerException {
        Vector<double[]> flex = new Vector();

        double x = Piniziale;
        double ox = x;
        double y = f(x);
        double oy = y;
        int s = sign(y);
        int os = s;

        try{
            for (; x <= Pfinale ; x += Intervallo) {
                s = sign(y = F2(x));
                if (s == 0) {
                    System.out.println(x);
                } else if (s != os) {
                    double dx = x - ox;
                    double dy = y - oy;
                    double cx = x - dx * (y / dy);
                    // [x, y]
                    flex.addElement(new double[]{
                            Double.parseDouble(String.format("%.2f", cx).replace(",",".")),
                            Double.parseDouble(String.format("%.2f", f(cx)).replace(",","."))
                    });
                }
                ox = x; oy = y; os = s;
            }
        }catch (NullPointerException ex){
            System.err.println(ex.getMessage());
        }

        this.flex = flex.toArray(new double[flex.size()][2]);
    }

    //** PARTE DI GRAFICA **//
    private void assi(){
        super.assi(g1);
    }
    protected void paintComponent(Graphics g){
        super.paintComponent(g);
        g1=(Graphics2D)g;
        super.assi(g1);
        super.plotFx(datiXY,g1, Color.BLUE);

        try{
            super.plotPoint(root, Color.RED, "root");
            super.plotPoint(minMax, Color.ORANGE, "mM");
            super.plotPoint(flex, Color.MAGENTA, "flex");
        }catch (NullPointerException ex){
            System.out.println("Alcune matrici sono vuote -> " + ex.getMessage());
        }
    }
    private void plotFx(){
        super.assi(g1);
        super.plotFx(datiXY,g1, Color.BLUE);

        try{
            super.plotPoint(root, Color.RED, "root");
            super.plotPoint(minMax, Color.ORANGE, "mM");
            super.plotPoint(flex, Color.PINK, "flex");
        }catch (NullPointerException ex){
            System.out.println("Alcune matrici sono vuote -> " + ex.getMessage());
        }
    }
}