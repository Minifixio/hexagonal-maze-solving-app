package model;

import java.awt.*;
import java.awt.geom.Path2D;

public class Hexagon extends Path2D.Float {

    private Color color;

    public Hexagon(double xcenter, double ycenter, double size, Color color){
        super();
        double height = 2*size;
        double width = Math.sqrt(3)*size;
        this.moveTo(xcenter, ycenter-0.5*height);
        this.lineTo(xcenter+0.5*width, ycenter-0.25*height);
        this.lineTo(xcenter+0.5*width, ycenter+0.25*height);
        this.lineTo(xcenter, ycenter+0.5*height);
        this.lineTo(xcenter-0.5*width, ycenter+0.25*height);
        this.lineTo(xcenter-0.5*width, ycenter-0.25*height);
        this.lineTo(xcenter, ycenter-0.5*height);
        this.closePath();

        this.color = color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public final void paint(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;
        g2.setPaint(this.color);
        g2.fill(this);
        g2.draw(this);
    }
}
