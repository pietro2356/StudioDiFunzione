package com.company;

import javax.swing.*;

public class Main {

    public static void main(String[] args) throws Exception {
        double[] PRIMO = new double[]{1, 1};
        double[] SECONDO = new double[]{4, -2, -2};
        double[] TERZO = new double[]{-1, 6, -11, 6};
        double[] TERZO2 = new double[]{6, 7, -7, -6};
        double[] PARAMETRI = new double[]{-2, 1.1, 0.001};

        JFrame frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        StudioFx s = new StudioFx(TERZO2, PARAMETRI);
        s.scanFx();

        frame.add(s);
        frame.setSize(1200,800);
        frame.setLocation(200,200);
        frame.setVisible(true);
        frame.setVisible(true);
    }
}
