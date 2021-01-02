package com.company;

import java.awt.*;

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
    }

    public void assi(){
        super.assi(g1);
    }
    protected void paintComponent(Graphics g){
        super.paintComponent(g);
        g1=(Graphics2D)g;
        super.assi(g1);
        super.plotFx(datiXY,g1) ;
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
    }
}