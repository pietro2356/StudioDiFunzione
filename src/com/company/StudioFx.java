package com.company;

import java.awt.*;
import java.util.Vector;

public class StudioFx extends PianoCartesiano{
    /*
        - Parametrizzare a, b, h [Piniziale, Pfinale, Intervallo]
        - Metodi studio funzione:
            - Radici di f(x) - tan -> []
            - Min e Max.
            - Punti di flesso.
            - Grafico
        - Calcolo tutti i punti tra a e b con intervallo h.
     */

    /*ATRIBUTI*/
    private static final long serialVersionUID = 1L;
    Graphics2D g1;

    protected double Piniziale;
    protected double Pfinale;
    protected double Intervallo;
    private double a;
    private double b;
    private double c;
    private double d;
    private Grado gradoFx;

    protected double[][] datiXY;
    protected double[][] root;
    protected double[][] flex;


    protected double maxY = Double.MAX_VALUE;
    protected double minY = Double.MIN_VALUE;
    protected double Xorigin;
    protected double Yorigin;

    private double exp = 1;

    int nCoords;

    /*COTRUTTORI*/
    public StudioFx(){}
    public StudioFx(double[] value, double[] parametri) throws Exception {
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

    private void GetValueOfF() throws Exception {
        throw new Exception("Not implemented yet!");
    }

    ///TODO: Da analizzare.
    private static int sign(double x) {
        return (x < 0.0) ? -1 : (x > 0.0) ? 1 : 0;
    }

    ///Ritorna una matrice con all'interno gli zeri: [Zero reale, Zero Approssimato]
    public void GetRoot(){
        Vector<double[]> rootP = new Vector();
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
                // [Numero completo, Numero approssimato]
                rootP.addElement(new double[]{ox, Double.parseDouble(String.format("%.2f", cx).replace(",","."))});
            }
            ox = x; oy = y; os = s;
        }

        root =  rootP.toArray(new double[rootP.size()][2]);
    }

    ///Ritorna una matrice con all'interno i vari punti: [x, y]
    public void GetPoint(){
        nCoords = (int)Math.floor(Math.abs(Piniziale-Pfinale)/Intervallo) + 1;
        datiXY = new double[nCoords][2];
        double x = Piniziale;
        double y = 0;
        int i = 0;

        while (x < Pfinale){
            y = f(x);

            datiXY[i][0] = x;
            datiXY[i][1] = y;

            if (y > maxY) { maxY = y; }
            if (y < minY) { minY = y; }

            i++;
            x += Intervallo;
        }

        if (Piniziale > 0) { setxOrigine(0); }
        else {
            setxOrigine(Piniziale);
            setyOrigine(Math.abs((maxY - minY)) / 2.0);
        }
        if ((Pfinale - Piniziale) > (maxY - minY)) { setMaxY(Pfinale - Piniziale); }
        else { setMaxY(maxY-minY); }
    }

    ///Ritorna una matrice con all'interno i punti di minimo e massimo [X reale, X approssimato]
    /// !!!Da verificare se il punto è di minimo o massimo!!!
    public double[][] GetMinMax() throws Exception {
        Vector<double[]> minMax = new Vector();
        int count = 0;

        double x = Piniziale, ox = x;
        double y = F(x), oy = y;
        int s = sign(y), os = s;

        for (; x <= Pfinale ; x += Intervallo) {
            s = sign(y = F(x));
            if (s == 0) {
                System.out.println(x);
            } else if (s != os) {
                double dx = x - ox;
                double dy = y - oy;
                double cx = x - dx * (y / dy);
                // [Numero completo, Numero approssimato]
                minMax.addElement(new double[]{ox, Double.parseDouble(String.format("%.2f", cx).replace(",","."))});
            }
            ox = x; oy = y; os = s;
        }

        return minMax.toArray(new double[minMax.size()][2]);
    }
    public double[][] GetFlex() throws Exception { throw new Exception("Not Implemnted yet!"); }

    public void assi(){ super.assi(g1); }

    protected void paintComponent(Graphics g){
        super.paintComponent(g);
        g1 = (Graphics2D)g;
        super.assi(g1);
        super.plotFx(datiXY, g1);
    }

    public void plotFx(){
        super.assi(g1);
        super.plotFx(datiXY, g1);
    }
}
