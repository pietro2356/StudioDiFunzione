package com.company;

import java.awt.*;

public class StudioFunzione  extends PianoCartesiano{
    private static final long serialVersionUID = 1L;
    Graphics2D g1;
    /**
     * parametrizzare a,b,h
     */
    double a=-Math.PI,b=Math.PI,h=0.001;
    //double a=2,b=3,h=0.001;
    int nCoords=(int)Math.floor(Math.abs(a-b)/h)+1;
    double datiXY[][];
    double minY=Double.MAX_VALUE,maxY=Double.MIN_VALUE;
    public StudioFunzione(){}
    /**
     * Calcolo i tutti i punti tra a e b con passo h
     */
    public void scansioneFx(){
        datiXY = new double[nCoords][2];
        double x=a,y=0;
        int i=0;
        while (x<b){
            y=f(x);
            /*
            implementare lo studio di funzioni con i metodi
            che abbiamo stabilito

            */
            datiXY[i][0]=x;
            datiXY[i][1]=y;
            if (y>maxY) 
                maxY=y;
            if (y<minY)
                minY=y;
            x+=h;
            i++;
        }
        if (a>0)
            setxOrigine(0);
        else    
            setxOrigine(a);
            setyOrigine(Math.abs((maxY-minY))/2.0);
        if ((b-a)>(maxY-minY))
            setMaxY(b-a);
        else
            setMaxY(maxY-minY);    
    }
    public void assi(){
        super.assi(g1);
    }
    protected void paintComponent(Graphics g){
        super.paintComponent(g);
        g1=(Graphics2D)g;
        super.assi(g1);
        //super.plotFx(datiXY,g1) ;
    }
    private double f(double x){
        return Math.pow(x, 2);
        //return Math.pow(Math.sin(x),2)+Math.pow(Math.cos(x), 3);
        //return -Math.pow(x, 3) + 6*Math.pow(x, 2) - 11 * x+6;
    }
    public void plotFx(){
        super.assi(g1);
        //super.plotFx(datiXY,g1) ;
    }
}