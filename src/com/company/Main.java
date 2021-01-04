package com.company;

import javax.swing.*;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws Exception {
        JFrame frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        Scanner in = new Scanner (System.in);
        double a;
        double b;
         double c;
        double d;
        double Pi = 0;
        double Pf = 0;
        double I = 0;
        double[] f = new double[0];
        boolean fine = false;

        double[] TERZO = new double[]{-1, 6, -11, 6};
        double[] PARAMETRI = new double[]{0, 4, 0.001};

        System.out.println("##########################################");
        System.out.println("#           STUDIO DI FUNZIONE           #");
        System.out.println("##########################################");
        System.out.println("");

        try{
            System.out.print("Di che grado è l'equazione da analizzare? [1, 3] -> ");
            switch (in.nextInt()) {
                case 1 -> {
                    System.out.println("\nL'equazione si presenterà nella forma -> ax + b = 0");
                    System.out.print("Inserisci il parametro a -> ");
                    a = in.nextDouble();
                    System.out.print("Inserisci il parametro b -> ");
                    b = in.nextDouble();
                    f = new double[]{a, b};
                }
                case 2 -> {
                    System.out.println("\nL'equazione si presenterà nella forma -> ax^2 + bx + c = 0");
                    System.out.print("Inserisci il parametro a -> ");
                    a = in.nextDouble();
                    System.out.print("Inserisci il parametro b -> ");
                    b = in.nextDouble();
                    System.out.print("Inserisci il parametro c -> ");
                    c = in.nextDouble();

                    f = new double[]{a, b, c};
                }
                case 3 -> {
                    System.out.println("\nL'equazione si presenterà nella forma -> ax^3 + bx^2 + cx + d = 0");
                    System.out.print("Inserisci il parametro a -> ");
                    a = in.nextDouble();
                    System.out.print("Inserisci il parametro b -> ");
                    b = in.nextDouble();
                    System.out.print("Inserisci il parametro c -> ");
                    c = in.nextDouble();
                    System.out.print("Inserisci il parametro d -> ");
                    d = in.nextDouble();

                    f = new double[]{a, b, c, d};
                }
                default -> {
                    throw new Exception("Grado dell'equazioen non corretto!");
                }
            }
            System.out.print("Inserisci il valore iniziale per l'analisi -> ");
            Pi = in.nextDouble();

            System.out.print("Inserisci il valore finale per l'analisi -> ");
            Pf = in.nextDouble();

            System.out.print("Inserisci l'intervallo per l'analisi -> [per i decimali usare la virgola -> 2,4]");
            I = in.nextDouble();
        }catch (Exception ex){
            System.err.println(ex.getMessage());
        }


        StudioFx s = new StudioFx(f, new double[]{Pi, Pf, I});
        s.scanFx();

        frame.add(s);
        frame.setSize(1200,800);
        frame.setLocation(200,200);
        frame.setVisible(true);
        frame.setVisible(true);


        System.out.println("\n------------------------------------------");
        System.out.println("ZERI DELLA FUNZIONE:");
        for (double[] item : s.getRoot()) {
            //System.out.println("---");
            System.out.println("-> (" + item[0] + ", " + item[1] + ")");
        }

        System.out.println("\n------------------------------------------");
        System.out.println("MINIMI E MASSIMI DELLA FUNZIONE:");
        for (double[] item : s.getMinMax()) {
            //System.out.println("---");
            System.out.println("-> (" + item[0] + ", " + item[1] + ")");
        }

        System.out.println("\n------------------------------------------");
        System.out.println("FLESSI DELLA FUNZIONE:");
        for (double[] item : s.getFlex()) {
            //System.out.println("---");
            System.out.println("-> (" + item[0] + ", " + item[1] + ")");
        }

    }

    public static void DataInput() throws Exception {
        try{

        }catch (Exception ex){
            throw new Exception(ex.getMessage());
        }
    }
}
