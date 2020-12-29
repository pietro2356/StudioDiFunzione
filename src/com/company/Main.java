package com.company;

import java.util.Vector;

public class Main {

    public static void main(String[] args) throws Exception {
	    System.out.println("Calcolo equazione!");
	    double[] PRIMO = new double[]{0.50, 1};
        double[] SECONDO = new double[]{1, 5, 4};
        double[] TERZO = new double[]{-1, 6, -11, 6};

        double[] PARAMETRI = new double[]{0, 4, 0.001};

        StudioFx st = new StudioFx(TERZO, PARAMETRI);

        double[][] dati =  st.GetPoint();
        double[][] root = st.GetRoot();

        for (double[] item: dati) {
            System.out.println("-> (" + item[0] + "; " + item[1] + ")");
        }
        for (double[] item: root) {
            System.out.println("Xnorm -> " + item[0]);
            System.out.println("Xapp -> " + item[1]);
        }


    }
}
