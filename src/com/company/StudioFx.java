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

    public double f(double x){
        switch (gradoFx){
            case PRIMO -> { return a * Math.pow(x, exp) + b;}
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

    private static int sign(double x) {
        return (x < 0.0) ? -1 : (x > 0.0) ? 1 : 0;
    }

    ///Ritorna un vettore contenente gli zeri della funzione: [X1, X2, X3, ...]
    public double[] GetRoot(){
        double[] root = new double[n][2];
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
                System.out.println("X -> " + String.format("%.2f", cx));

                ///TODO: Finire di convertire la matrice in vettore come seire di X: [X1, X2, X3, ...].
                root[count][0] = Double.parseDouble(String.format("%.2f", cx).replace(",","."));
                count++;
            }
            ox = x; oy = y; os = s;
        }

        return root;
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
}
