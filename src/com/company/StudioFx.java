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
        return  a * Math.pow(x, 3) + b * Math.pow(x, 2) + c * x + d;
    }

    public Vector CalcolaValori(){
        int numero_di_valori_da_analizzare=(int)Math.floor(Math.abs(Piniziale-Pfinale)/Intervallo)+1;
        datiXY = new double[numero_di_valori_da_analizzare][2];
        Vector rootXY = new Vector();
        double x = Piniziale;
        double y = 0;
        int i = 0;

        while (x < Pfinale){
            y = f(x);

            datiXY[i][0] = x;
            datiXY[i][1] = y;
            
            x += Intervallo;    
            i++;
        }

        for (double item[]: datiXY) {
            if (item[1] == 0){
                rootXY.addElement(item);
            }
        }
        
        return rootXY;
    }




}
