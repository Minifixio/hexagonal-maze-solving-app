package maze;

public class MazeBox {

    public int x;
    public int y;

    private Maze maze;

    public MazeBox(Maze maze, int x, int y) {
        this.x = x;
        this.y = y;
        this.maze = maze;
    }
}
