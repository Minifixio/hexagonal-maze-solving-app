import maze.Maze;
import app.*;
import maze.MazeReadingException;

public class MainTest {

    private static Maze maze;

    public static void main(String[] args) throws MazeReadingException {

        maze = new Maze();

        try {
            maze.initFromTextFile(args[0]);
        } catch (MazeReadingException e) {
            throw e;
        }

        maze.printMaze();
    }


}
