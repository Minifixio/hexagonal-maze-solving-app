package maze;

public class EmptyMazeBox extends MazeBox {
    public EmptyMazeBox(Maze maze, int x, int y) {
        super(maze, x, y, 'E');
        this.setDefaultTexturePaint("hexground.png");
    }
}
