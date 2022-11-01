package maze;

import dijkstra.Graph;
import dijkstra.Vertex;

import java.util.List;

public class Maze implements Graph {

    public MazeBox[][] maze;

    public void Maze(int size) {
        this.maze = new MazeBox[size][size];
    }

    public List<Vertex> getAllVertexes() {
        return null;
    }

    public List<Vertex> getAllVertexesSorted() {
        return null;
    }

    public List<Vertex> getSuccessors() {
        return null;
    }

    public int getWeight(Vertex src, Vertex dst) {
        return 0;
    }

    public void setStartVertex(Vertex startVertex) {

    }

    public void setEndVertex(Vertex endVertex) {

    }

    public Vertex[] getUnprocessedVertexes() {
        return new Vertex[0];
    }
}
