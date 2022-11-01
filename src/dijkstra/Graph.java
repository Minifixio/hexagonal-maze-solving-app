package dijkstra;

import java.util.List;

public interface Graph {

    public List<Vertex> getAllVertexes();

    public List<Vertex> getAllVertexesSorted();

    public List<Vertex> getSuccessors();

    public int getWeight(Vertex src, Vertex dst);
    public void setStartVertex(Vertex startVertex);

    public void setEndVertex(Vertex endVertex);

    public Vertex[] getUnprocessedVertexes();
}
