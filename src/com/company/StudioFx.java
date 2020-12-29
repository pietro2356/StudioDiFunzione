package com.company;

import javax.swing.*;
import java.lang.reflect.Array;
import java.nio.FloatBuffer;
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

    private double exp = 1;


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
    public double[][] GetRoot(){
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
                // [Numero completo, Numero approssimato]
                root.addElement(new double[]{ox, Double.parseDouble(String.format("%.2f", cx).replace(",","."))});
            }
            ox = x; oy = y; os = s;
        }

        return root.toArray(new double[root.size()][2]);
    }

    ///Ritorna una matrice con all'interno i vari punti: [x, y]
    public double[][] GetPoint(){
        int n = (int)Math.floor(Math.abs(Piniziale-Pfinale)/Intervallo)+1;
        double[][] point = new double[n][2];
        double x = Piniziale;
        double y = 0;
        int i = 0;

        while (x < Pfinale){
            y = f(x);

            point[i][0] = x;
            point[i][1] = y;

            i++;
            x += Intervallo;
        }

        return point;
    }

    ///TODO: Da implementare!!!!
    public double[][] GetMin() throws Exception { throw new Exception("Not Implemnted yet!"); }
    public double[][] GetMax() throws Exception { throw new Exception("Not Implemnted yet!"); }
    public double[][] GetFlex() throws Exception { throw new Exception("Not Implemnted yet!"); }
}
