package app;

import javax.swing.*;
import java.awt.*;

public class Hexagon extends JPanel {

    private final MazeApp mazeApp;

    public Hexagon(MazeApp mazeApp) {
        this.mazeApp = mazeApp;
        setPreferredSize(new Dimension(500,500));
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        int[] xPoints = {200,300,350,300,200,150};
        int[] yPoints = {350,350,250,150,150,250};
        g.fillPolygon(xPoints, yPoints, xPoints.length);
    }
}
