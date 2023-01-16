package maze;

import java.awt.*;

public class DepartureMazeBox extends MazeBox {
    public DepartureMazeBox(Maze maze, int x, int y) {
        super(maze, x, y, 'D');
        this.setDefaultTexturePaint("path.png");
    }
}
