package dijkstra;

public interface ProcessedVertexes extends Vertex {

    /**
     * @return true si le sommet de fin est present, false sinon
     */
    public boolean containsEndVertex();

    /**
     * @param vertex
     */
    public void addVertex(Vertex vertex);

    public static Vertex[] processedVertexes = new Vertex[0];
}
