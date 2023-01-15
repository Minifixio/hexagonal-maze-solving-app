package maze;

import java.awt.*;

public class WallMazeBox extends MazeBox {
    public WallMazeBox(Maze maze, int x, int y) {
        super(maze, x, y, 'W');
        this.setDefaultTexturePaint("hexwall.png");
    }
}
