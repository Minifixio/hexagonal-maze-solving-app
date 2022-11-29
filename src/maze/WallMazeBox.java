package maze;

import java.awt.*;

public class WallMazeBox extends MazeBox {
    public WallMazeBox(Maze maze, int x, int y) {
        super(maze, x, y, 'W');
        this.color = Color.DARK_GRAY;
        this.setTexturePaint("wall1.png");
    }
}
