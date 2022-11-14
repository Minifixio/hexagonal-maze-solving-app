package dijkstra;

public interface ProcessedVertexes {

    /**
     * @return true si le sommet de fin est present, false sinon
     */
    public boolean containsEndVertex();

    /**
     * @param vertex le sommet à ajouter
     */
    public void addVertex(Vertex vertex);

    public static Vertex[] processedVertexes = new Vertex[0];
}
