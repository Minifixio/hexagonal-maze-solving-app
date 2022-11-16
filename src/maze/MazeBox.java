package maze;

import dijkstra.Vertex;

import java.util.ArrayList;
import java.util.List;

public class MazeBox implements Vertex {
    // Les coordonnées (x,y) de la case
    public int x;
    public int y;
    private Maze maze;
    public boolean isInPath = false;

    // Le caractère type représente le type de case du Labyrinthe avec les correspondances suivantes :
    // 'W' -> WallMazeBox
    // 'E' -> EmptyMazeBox
    // 'D' -> DepartureMazeBox
    // 'A' -> ArrivalMazeBox
    public char type;

    public MazeBox(Maze maze, int x, int y, char type) {
        this.x = x;
        this.y = y;
        this.maze = maze;
        this.type = type;
    }

    public List<Vertex> getSuccessors() {
        List<Vertex> successors = new ArrayList<>();
        for (int i=-1; i<=1; i=i+2) {
            if (
                    this.x+i > 0
                    && this.x+i < this.maze.width
                    && !(this.maze.getBoxByCoords(this.x+i, this.y) instanceof WallMazeBox)
            ) {
                successors.add(this.maze.getBoxByCoords(this.x+i, this.y));
            }
            if (
                    this.y+i > 0
                    && this.y+i < this.maze.length
                    && !(this.maze.getBoxByCoords(this.x, this.y+i) instanceof WallMazeBox)
            ) {
                successors.add(this.maze.getBoxByCoords(this.x, this.y+i));
            }
        }
        return successors;
    }
}
