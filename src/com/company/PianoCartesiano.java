package com.company;

import java.awt.*;
import javax.swing.*;
import javax.swing.event.MouseInputAdapter;
import javax.swing.event.MouseInputListener;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;
import java.awt.geom.*;
import java.awt.GridLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.MouseListener;
import java.awt.event.MouseEvent;

public class PianoCartesiano extends JPanel  {

    double xOrigine = 0, yOrigine = 0;
    double scale = 1, maxY = 0;

    public PianoCartesiano() {
        addMouseListener(new MouseListen());
        addMouseWheelListener(new MouseListen());
    }

    public PianoCartesiano(double xOrigine, double yOrigine) { // origine assi
        this.xOrigine = xOrigine;
        this.yOrigine = yOrigine;
    }

    public void setxOrigine(double xOrigine) {
        this.xOrigine = xOrigine;
    }

    public void setyOrigine(double yOrigine) {
        this.yOrigine = yOrigine;
    }

    public void setScale(double scale) {
        this.scale=scale;
    }

    public void setMaxY(double maxY) {
        this.maxY = maxY;
    }

    private static final long serialVersionUID = 1L;
    int width, height;
    int mar = 50;
    Graphics2D g1;

    public void assi(Graphics2D g1) {
        if (g1 == null) return;
        g1.draw(new Line2D.Double(mar - (xOrigine * scale) - 2, mar - (yOrigine * scale), mar - (xOrigine * scale) - 2,(height - mar)));
        g1.draw(new Line2D.Double(0, (height - mar) - (yOrigine * scale) - 2, (width ),(height - mar) - (yOrigine * scale) - 2));
        g1.setPaint(Color.BLUE);
    }

    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g1 = (Graphics2D) g;
        g1.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        width = getWidth();
        height = getHeight();
        scale = (double) (height - 2.0 * mar) / getMax();
        g1.drawString(Double.toString(scale), 75, 75);
        g1.setPaint(Color.BLACK);
    }


    public void plotFx(double datiXY[][], Graphics2D g1) {
        if (g1 == null) return;
        for (int i = 0; i < datiXY.length - 1; i++) {
            double x1 = mar - (xOrigine * scale) + datiXY[i][0] * scale;
            double y1 = (height - mar) - (yOrigine * scale) - scale * datiXY[i][1];
            double x2 = mar - (xOrigine * scale) + datiXY[i + 1][0] * scale;
            double y2 = (height - mar) - (yOrigine * scale) - scale * datiXY[i + 1][1];
            // g1.fill(new Ellipse2D.Double(x1-2,y1-2,1,1));
            g1.draw(new Line2D.Double(x1, y1, x2, y2));
        }
    }

    private double getMax() {
        return ((this.maxY));
    }

    class MouseListen extends MouseInputAdapter {
        Point p1,p2;
        double distance;
        public void mouseReleased(MouseEvent e) {
            p2= new Point(e.getPoint());
            distance=Math.sqrt(Math.pow(p2.x-p1.x,2 ) +Math.pow(p2.y-p1.y,2 ));
            xOrigine+=(p1.x-p2.x)/scale;
            yOrigine+=(p1.y-p2.y)/scale;
            g1.drawString(Double.toString(distance), 220, 220);
            repaint();
        }

        
        public void mousePressed(MouseEvent e) {
            p1= new Point(e.getPoint());
            repaint();
        }
        

        public void mouseWheelMoved(MouseWheelEvent e){
            
            if (e.getWheelRotation()>0)
            {    if (maxY<=0.01)
                    return;
                 else   
                    maxY-=0.01;
            }
            else
                maxY+=0.1;    
            repaint();
        }
    }
}
