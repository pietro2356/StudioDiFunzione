package com.company;

import javax.swing.*;

public class Main {

    public static void main(String[] args) throws Exception {
        double[] PRIMO = new double[]{1, 1};
        double[] SECONDO = new double[]{4, -2, -2};
        double[] TERZO = new double[]{-1, 6, -11, 6};
        double[] PARAMETRI = new double[]{-1, 2, 0.001};

        JFrame frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        /*
        StudioFx sf = new StudioFx(TERZO, PARAMETRI);
        sf.GetPoint();

        StudioFunzione st = new StudioFunzione();
        st.scansioneFx();
*/
        StudioFx s = new StudioFx(PRIMO, PARAMETRI);
        s.scansioneFx();

        frame.add(s);
        frame.setSize(800,800);
        frame.setLocation(200,200);
        frame.setVisible(true);
        frame.setVisible(true);




        /*
        System.out.println("Calcolo equazione!");
	    double[] PRIMO = new double[]{0.50, 1};
        double[] SECONDO = new double[]{1, 5, 4};
        double[] TERZO = new double[]{-1, 6, -11, 6};

        double[] PARAMETRI = new double[]{0, 4, 0.001};

        StudioFx st = new StudioFx(TERZO, PARAMETRI);

        double[][] dati =  st.GetPoint();
        double[][] root = st.GetRoot();
        double[][] minMax = st.GetMinMax();

        System.out.println("f(x) --> x -> 2; y -> " + st.f(2));
        System.out.println("f'(x) --> x -> 2; y -> " + st.F(2));

        for (double[] item: dati) {
            System.out.println("-> (" + item[0] + "; " + item[1] + ")");
        }
        for (double[] item: root) {
            System.out.println("Xnorm -> " + item[0]);
            System.out.println("Xapp -> " + item[1]);
        }
        for (double[] item: minMax) {
            System.out.println("P -> " + item[1]);
        }*/


    }
}
