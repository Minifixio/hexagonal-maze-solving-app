package maze;

public class ArrivalMazeBox extends MazeBox {
    public ArrivalMazeBox(Maze maze, int x, int y) {
        super(maze, x, y, 'A');
        this.setDefaultTexturePaint("path.png");
    }
}
