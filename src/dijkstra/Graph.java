package dijkstra;

import java.util.List;

public interface Graph {

    public List<Vertex> getAllVertexes();

    public List<Vertex> getAllVertexesSorted();

    /**
     * @param vertex Le sommet pour lequel on cherche les successeurs
     * @return les sucesseurs de vertex
     */
    public List<Vertex> getSuccessors(Vertex vertex);

    /**
     * @param src le sommet source
     * @param dst le sommet destination
     * @return la distance entre src et dst
     */
    public int getWeight(Vertex src, Vertex dst);

    /**
     * @param startVertex le sommet désigné comme sommet de départ
     */
    public void setStartVertex(Vertex startVertex);

    /**
     * @param endVertex le sommet désigné comme sommet d'arrivée
     */
    public void setEndVertex(Vertex endVertex);

    /**
     * @return les sommets pas encore marqués
     */
    public Vertex[] getUnprocessedVertexes();
}
