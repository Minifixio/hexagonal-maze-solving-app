package maze;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;

public class EmptyMazeBox extends MazeBox {
    public EmptyMazeBox(Maze maze, int x, int y) {
        super(maze, x, y, 'E');
        this.color = Color.LIGHT_GRAY;
        this.setDefaultTexturePaint("dirt1.png");
    }
}
