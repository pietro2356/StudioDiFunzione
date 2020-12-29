package com.company;

import java.util.Vector;

public class Main {

    public static void main(String[] args) {
	    System.out.println("Calcolo equazione!");
	    double a = 1;
	    double b = 2;
        double c = -1;
        //double d = 6;

        double Piniziale = -3;
        double Pfinale = 1;
        double Intervallo = 0.001;

        StudioFx st = new StudioFx(a, b, c);
        st.SetParametri(Piniziale, Pfinale, Intervallo);

        //Vector<double[]> root = st.GetRootPoint();

        st.GetRoot();
/*
        System.out.println("Item -> " + root.size());
        for (double[] item: root) {
            System.out.println("-> (" + item[0] + "; " + item[1] + ")");
            //System.out.println("X -> " + item);
        }*/

    }
}
