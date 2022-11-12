package dijkstra;

import java.util.List;

public interface Vertex {

    /**
     * @param distance distance a l'origine
     */
    public void setDistance(Integer distance);

    /**
     * @param predecessor le prédecesseur du sommet dans le chemin optimal
     */
    public void setPredecessor(Vertex predecessor);

    /**
     * @return true si l'arrète a ete marquee, false sinon
     */
    public boolean isProcessed();

    public List<Vertex> getSuccessors();
}
