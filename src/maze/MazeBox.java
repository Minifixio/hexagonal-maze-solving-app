package maze;

import dijkstra.Vertex;

import java.util.ArrayList;
import java.util.List;

public class MazeBox implements Vertex {
    public int x;
    public int y;
    private Maze maze;
    private boolean processed;

    public MazeBox(Maze maze, int x, int y) {
        this.x = x;
        this.y = y;
        this.maze = maze;
        this.processed = false;
    }

    public void setDistance(int distance) {

    }

    public void setPredecessor(Vertex predecessor) {

    }

    public boolean isProcessed() {
        return this.processed;
    }

    public List<Vertex> getSuccessors() {
        List<Vertex> successors = new ArrayList<>();
        for (int i=-1; i<=1; i=i+2) {
            if (
                    this.x+i > 0
                    && this.x+i < this.maze.width
                    && !(this.maze.getBoxByCoords(this.x+i, this.y) instanceof WallMazeBox)
                    && !(this.maze.getBoxByCoords(this.x+i, this.y).isProcessed())
            ) {
                successors.add(this.maze.getBoxByCoords(this.x+i, this.y));
            }
            if (
                    this.y+i > 0
                    && this.y+i < this.maze.length
                    && !(this.maze.getBoxByCoords(this.x, this.y+i) instanceof WallMazeBox)
                    && !(this.maze.getBoxByCoords(this.x, this.y+i).isProcessed())
            ) {
                successors.add(this.maze.getBoxByCoords(this.x, this.y+i));
            }
        }
        return successors;
    }
}
