package dijkstra;

import java.util.List;

public interface Graph {

    public List<Vertex> getAllVertexes();

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
     * @return le sommet désigné comme sommet de départ
     */
    public Vertex getStartVertex();

    /**
     * @return le sommet désigné comme sommet d'arrivée
     */
    public Vertex getEndVertex();

}
