package com.company;

import java.awt.*;

public class Graph extends PianoCartesiano {
    private static final long serialVersionUID = 1L;
    Graphics2D g1;
    double[][] point;
    double a;
    double b;
    double h;

    public Graph(double[][] point){
        this.point = point;
    }

    public Graph(double[][] point, double Xorigin, double Yorigin,double maxY, double a, double b, double h){
        this.point = point;
        setxOrigine(Xorigin);
        setyOrigine(Yorigin);
        setMaxY(maxY);
        plotFx();
        this.a = a;
        this.b = b;
        this.h = h;
    }

    public void assi(){ super.assi(g1); }

    public void paintComponent(Graphics g){
        super.paintComponent(g);
        g1 = (Graphics2D)g;
        super.assi(g1);
        super.plotFx(point, g1);
    }

    public double f(double x){
        return new StudioFx().f(x);
    }

    public void plotFx(){
        super.assi(g1);
        super.plotFx(point, g1);
    }
}
