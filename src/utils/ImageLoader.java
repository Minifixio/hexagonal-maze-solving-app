package utils;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;

public class ImageLoader {
    public static BufferedImage loadImageFromName(String imgName) {
        BufferedImage image = null;
        InputStream stream = ImageLoader.class.getResourceAsStream("/ressources/" + imgName);
        try {
            image = ImageIO.read(stream);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                stream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return image;
    }
}
