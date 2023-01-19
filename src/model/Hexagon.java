package model;
import java.awt.*;
import java.awt.geom.Path2D;

public class Hexagon extends Path2D.Float {

    private TexturePaint texturePaint;
    private final double xCenter;
    private final double yCenter;
    private final double size;

    public Hexagon(double xCenter, double yCenter, double size) {
        super();
        this.xCenter = xCenter;
        this.yCenter = yCenter;
        this.size = size;

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
    }


    public final void paint(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;

        int height = (int) (2*size);
        int width = (int) (Math.sqrt(3)*size);
        int x = (int) (this.xCenter-width/2);
        int y = (int) (this.yCenter-height/2);

        //g2.setPaint(this.texturePaint);
        g.drawImage(this.texturePaint.getImage(), x, y, width, height, null);

        //g2.fill(this);
        g2.draw(this);
    }

    public double getyCenter() {
        return yCenter;
    }

    public double getxCenter() {
        return xCenter;
    }

    public void setTexturePaint(TexturePaint texturePaint) {
        this.texturePaint = texturePaint;
    }

}
