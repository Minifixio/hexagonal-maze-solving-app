package graph;

import java.util.List;

public interface Graph {

    public List<Vertex> getAllVertexes();

    public List<Vertex> getAllVertexesSorted();


    public void setStartVertex(Vertex startVertex);

    public void setEndVertex(Vertex endVertex);

    public Vertex[] getUnprocessedVertexes();
}
