package com.company;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Vector;

public class StudioFx{
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
    private double Piniziale;
    private double Pfinale;
    private double Intervallo;
    private double a;
    private double b;
    private double c;
    private double d;
    private Grado gradoFx;
    private double datiXY[][];


    /*COTRUTTORI*/
    public StudioFx(){}
    public StudioFx(double a, double b){
        this.a = a;
        this.b = b;
        this.gradoFx = Grado.PRIMO;
    }
    public StudioFx(double a, double b, double c){
        this.a = a;
        this.b = b;
        this.c = c;
        this.gradoFx = Grado.SECONDO;
    }
    public StudioFx(double a, double b, double c, double d){
        this.a = a;
        this.b = b;
        this.c = c;
        this.d = d;
        this.gradoFx = Grado.TERZO;
    }

    /*METODI*/
    public void SetParametri(double Piniziale, double Pfinale, double Intervallo){
        this.Piniziale = Piniziale;
        this.Pfinale = Pfinale;
        this.Intervallo = Intervallo;
    }

    public double[] CalcRootoot() throws Exception {


        throw new Exception();
    }

    public double f(double x){
        switch (gradoFx){
            case PRIMO -> {return a * Math.pow(x, 2);}
            case SECONDO -> { return a * Math.pow(x, 2) + b * x + c; }
            case TERZO -> { return  a * Math.pow(x, 3) + b * Math.pow(x, 2) + c * x + d; }
            default -> throw new ArithmeticException();
        }
    }

    public Vector CalcolaValori(){
        //int valueAnalizzati=(int)Math.floor(Math.abs(Piniziale-Pfinale)/Intervallo)+1;
        Vector<double[]> datiXY = new Vector();
        double x = Piniziale;
        double y = 0;
        int i = 0;

        while (x < Pfinale){
            y = f(x);

            if (y == 0){
                datiXY.addElement(new double[]{x, y});
            }

            x += Intervallo;    
            i++;
        }
        return datiXY;
    }

    public Vector CalcValue(){
        Vector<double[]> point = new Vector();
        double x = Piniziale;
        double y = 0;

        while (x < Pfinale){
            y = f(x);
            point.addElement(new double[]{x, y});

            x += Intervallo;
        }
        return point;
    }

    public Vector GetRootPoint(){
        Vector<double[]> point = CalcValue();
        Vector<double[]> root = new Vector();

        for (double[] item: point) {
            if (item[1] == 0){
                root.addElement(item);
            }
        }

        return root;
    }


    private static int sign(double x) {
        return (x < 0.0) ? -1 : (x > 0.0) ? 1 : 0;
    }

    public void GetRoot(){
        int n = (int)Math.floor(Math.abs(Piniziale-Pfinale)/Intervallo)+1;
        double[][] dati = new double[n][2];

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
                System.out.println("~" + cx);
            }
            ox = x; oy = y; os = s;
        }
    }

}
