package com.company;

import java.awt.*;
import java.io.IOException;
import java.util.Vector;

public class StudioFx extends PianoCartesiano{
    private static final long serialVersionUID = 1L;
    Graphics2D g1;

    private double Piniziale = 0;
    private double Pfinale = 4;
    private double Intervallo = 0.001;

    private double a;
    private double b;
    private double c;
    private double d;
    private Grado gradoFx;
    private double exp = 1;

    private int nCoords;
    private double datiXY[][];
    private double root[][];
    private double minMax[][];
    private double flex[][];

    private double minY = Double.MAX_VALUE;
    private double maxY = Double.MIN_VALUE;

    public StudioFx(double[] value, double[] parametri)throws Exception{
        ///TODO: Implementare i controlli sui valori!
        if (parametri.length == 3){
            this.Piniziale = parametri[0];
            this.Pfinale = parametri[1];
            this.Intervallo = parametri[2];
        }else { throw new Exception("Formato parametri non corretto"); }

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

    public void scansioneFx(){
        nCoords = (int)Math.floor(Math.abs(Piniziale - Pfinale)/ Intervallo)+1;
        datiXY = new double[nCoords][2];
        double x= Piniziale,y=0;
        int i=0;
        while (x <= Pfinale){
            y = f(x);

            datiXY[i][0]=x;
            datiXY[i][1]=y;

            if (y > maxY) { maxY = y; }
            if (y < minY) { minY = y; }

            x+= Intervallo;
            i++;
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
    }

    private static int sign(double x) {
        return (x < 0.0) ? -1 : (x > 0.0) ? 1 : 0; ///TODO: MODIFICARE!
    }
    public void GetRoot(){
        Vector<double[]> root = new Vector();
        int count = 0;

        double x = Piniziale, ox = x;
        double y = f(x), oy = y;
        int s = sign(y), os = s;

        for (; x <= Pfinale ; x += Intervallo) {
            s = sign(y = f(x));
            if (s == 0) {
                System.out.println(x);
            } else if (s != os) {
                double dx = x - ox;
                double dy = y - oy;
                double cx = x - dx * (y / dy);
                // [x, y]
                root.addElement(new double[]{Double.parseDouble(String.format("%.2f", cx).replace(",",".")), 0});
            }
            ox = x; oy = y; os = s;
        }
        this.root = root.toArray(new double[root.size()][2]);
    }

    public void assi(){
        super.assi(g1);
    }

    protected void paintComponent(Graphics g){
        super.paintComponent(g);
        g1=(Graphics2D)g;
        super.assi(g1);
        super.plotFx(datiXY,g1);

        try{
            super.plotPoint(root, Color.RED, "root");
            super.plotPoint(minMax, Color.GREEN, "minmax");
            super.plotPoint(flex, Color.MAGENTA, "flex");
        }catch (NullPointerException ex){
            System.out.println("Alcune matrici sono vuote -> " + ex.getMessage());
        }
    }

    public double f(double x){
        switch (gradoFx){
            case PRIMO -> { return a * Math.pow(x, exp) + b; }
            case SECONDO -> { return a * Math.pow(x, exp+1) + b * x + c; }
            case TERZO -> { return  a * Math.pow(x, exp+2) + b * Math.pow(x, exp+1) + c * x + d; }
            default -> throw new ArithmeticException();
        }
    }
    public double F(double x){
        switch (gradoFx){
            case PRIMO -> { return 1 * a * Math.pow(x, 1);}
            case SECONDO -> { return 2 * a * Math.pow(x, 2-1) + b; }
            case TERZO -> { return 3 * a * Math.pow(x, 3-1) + 2 * b * Math.pow(x, 2-1) + c;}
            default -> throw new ArithmeticException();
        }
    }
    public void plotFx(){
        super.assi(g1);
        super.plotFx(datiXY,g1) ;

        try{
            super.plotPoint(root, Color.RED, "root");
            super.plotPoint(minMax, Color.GREEN, "minmax");
            super.plotPoint(flex, Color.MAGENTA, "flex");
        }catch (NullPointerException ex){
            System.out.println("Alcune matrici sono vuote -> " + ex.getMessage());
        }
    }
}