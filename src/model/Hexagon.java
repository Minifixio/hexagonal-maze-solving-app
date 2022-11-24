package model;

import java.awt.*;
import java.awt.geom.Path2D;

public class Hexagon extends Path2D.Float {

    private Color color;
    private double xCenter;
    private double yCenter;

    public Hexagon(double xCenter, double yCenter, double size) {
        super();
        this.xCenter = xCenter;
        this.yCenter = yCenter;

        // On trace un "path" on faisant bouger le pinceau de tracé aux 6 sommets de l'hexagone
        // A la fin on clot le "path" et on obtient une figure hexagonale
        // Voir https://www.redblobgames.com/grids/hexagons/ pour l'explication des calculs des positions des sommets
        double height = 2*size;
        double width = Math.sqrt(3)*size;
        this.moveTo(xCenter, yCenter-0.5*height);
        this.lineTo(xCenter+0.5*width, yCenter-0.25*height);
        this.lineTo(xCenter+0.5*width, yCenter+0.25*height);
        this.lineTo(xCenter, yCenter+0.5*height);
        this.lineTo(xCenter-0.5*width, yCenter+0.25*height);
        this.lineTo(xCenter-0.5*width, yCenter-0.25*height);
        this.lineTo(xCenter, yCenter-0.5*height);
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

    public double getyCenter() {
        return yCenter;
    }

    public double getxCenter() {
        return xCenter;
    }
}
