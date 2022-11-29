package maze;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;

public class DepartureMazeBox extends MazeBox {
    public DepartureMazeBox(Maze maze, int x, int y) {
        super(maze, x, y, 'D');
        this.color = Color.YELLOW;
        this.setTexturePaint("path1.png");
    }
}
