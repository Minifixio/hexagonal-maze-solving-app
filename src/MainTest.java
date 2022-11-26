import ui.MazeApp;
import maze.Maze;
import maze.MazeDistance;
import maze.MazeReadingException;

public class MainTest {

    private static Maze maze;
    private static MazeDistance mazeDistance;

    public static void main(String[] args) throws MazeReadingException {
        new MazeApp(1000,1000,30, 5, 10, 10);
    }


}
