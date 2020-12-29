package com.company;

import java.util.Vector;

public class Main {

    public static void main(String[] args) {
	    System.out.println("Calcolo equazione!");
	    double a = -1;
	    double b = 6;
        double c = -11;
        double d = 6;

        double Piniziale = 0;
        double Pfinale = 4;
        double Intervallo = 0.001;

        StudioFx st = new StudioFx(a, b, c, d);
        st.SetParametri(Piniziale, Pfinale, Intervallo);

        double[][] dati =  st.GetPoint();

        for (double[] item: dati) {
            System.out.println("-> (" + item[0] + "; " + item[1] + ")");
        }


    }
}
