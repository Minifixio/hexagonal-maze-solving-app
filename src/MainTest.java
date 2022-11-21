import ui.MazeApp;
import maze.Maze;
import maze.MazeDistance;
import maze.MazeReadingException;

public class MainTest {

    private static Maze maze;
    private static MazeDistance mazeDistance;

    public static void main(String[] args) throws MazeReadingException {

        /**
        maze = new Maze();
        mazeDistance = new MazeDistance();

        try {
            maze.initFromTextFile(args[0]);
        } catch (MazeReadingException e) {
            throw e;
        }

        maze.printMaze();

        ShortestPaths shortestPaths = Dijkstra.dijkstra(maze, maze.getStartVertex(), maze.getEndVertex(), mazeDistance);

        maze.printShortestPath(maze.getEndVertex(), shortestPaths);
        maze.printPathInMaze(shortestPaths);
         **/
        new MazeApp(1000,1000,20, 5, 10);
    }


}
