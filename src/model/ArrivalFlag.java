package model;

import utils.ImageLoader;

import java.awt.*;
import java.awt.image.BufferedImage;

public class ArrivalFlag {
    private final double x;
    private final double y;
    private final int size;
    private final BufferedImage icon;

    public ArrivalFlag(int x, int y, int size) {
        this.x = x;
        this.y = y;
        this.size = size;
        this.icon = ImageLoader.loadImageFromName("flag.png");
    }

    public void paint(Graphics g) {
        g.drawImage(this.icon, (int) this.x, (int) this.y, this.size, this.size, null);
    }
}
