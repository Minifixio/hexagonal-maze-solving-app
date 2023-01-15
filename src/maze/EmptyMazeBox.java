package maze;

import java.awt.*;

public class EmptyMazeBox extends MazeBox {
    public EmptyMazeBox(Maze maze, int x, int y) {
        super(maze, x, y, 'E');
        this.setDefaultTexturePaint("dirt1.png");
    }
}
