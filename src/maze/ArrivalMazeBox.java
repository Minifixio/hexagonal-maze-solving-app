package maze;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;

public class ArrivalMazeBox extends MazeBox {
    public ArrivalMazeBox(Maze maze, int x, int y) {
        super(maze, x, y, 'A');
        this.color = Color.RED;
        this.texturePaint = this.initTexturePaint("arrival1.png");
    }
}
