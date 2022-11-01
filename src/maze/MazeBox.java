package maze;

import dijkstra.Vertex;

import java.util.List;

public class MazeBox implements Vertex {

    public int x;
    public int y;

    private Maze maze;

    public MazeBox(Maze maze, int x, int y) {
        this.x = x;
        this.y = y;
        this.maze = maze;
    }

    public void setDistance(int distance) {

    }

    public void setPredecessor(Vertex predecessor) {

    }

    public boolean isProcessed() {
        return false;
    }

    public List<Vertex> getSuccessors() {
        return null;
    }
}
