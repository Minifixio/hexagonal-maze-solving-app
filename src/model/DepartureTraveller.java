package model;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.Objects;

public class DepartureTraveller {

    private double x;
    private double y;
    private int size;
    private BufferedImage icon;

    public DepartureTraveller(int x, int y, int size) {
        this.x = x;
        this.y = y;
        this.size = size;
        File root;
        try {
            root = new File(Objects.requireNonNull(Thread.currentThread().getContextClassLoader().getResource("")).toURI());
        } catch (URISyntaxException e) {
            throw new RuntimeException(e);
        }
        BufferedImage myImage;
        try {
            myImage = ImageIO.read(new File(root, "assets/traveller1.png"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        this.icon = myImage;
    }

    public void paint(Graphics g) {
        g.drawImage(this.icon, (int) this.x, (int) this.y, this.size, this.size*2, null);
    }
}
