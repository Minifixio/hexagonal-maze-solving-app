package model;

import utils.ImageLoader;

import java.awt.*;
import java.awt.image.BufferedImage;

public class DepartureTraveller {

    private final double x;
    private final double y;
    private final int size;
    private final BufferedImage icon;

    public DepartureTraveller(int x, int y, int size) {
        this.x = x;
        this.y = y;
        this.size = size;
        this.icon = ImageLoader.loadImageFromName("traveller1.png");
    }

    public void paint(Graphics g) {
        g.drawImage(this.icon, (int) this.x, (int) this.y, this.size, this.size*2, null);
    }
}
