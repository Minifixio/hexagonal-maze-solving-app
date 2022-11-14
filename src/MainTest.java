import dijkstra.*;
import maze.DepartureMazeBox;
import maze.Maze;
import app.*;
import maze.MazeDistance;
import maze.MazeReadingException;

public class MainTest {

    private static Maze maze;
    private static MazeDistance mazeDistance;

    public static void main(String[] args) throws MazeReadingException {

        maze = new Maze();
        mazeDistance = new MazeDistance();

        try {
            maze.initFromTextFile(args[0]);
        } catch (MazeReadingException e) {
            throw e;
        }

        maze.printMaze();

        ShortestPaths shortestPaths = Dijkstra.dijkstra(maze, maze.getStartVertex(), maze.getEndVertex(), mazeDistance);


    }


}
