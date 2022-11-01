package graph;

import java.util.List;

public interface Vertex {

    /**
     * @param distance distance a l'origine
     */
    public void setDistance(int distance);

    /**
     * @param predecessor le predecesseur du sommet dans le chemin optimal
     */
    public void setPredecessor(Vertex predecessor);

    /**
     * @return true si l'arrete a ete marquee, false sinon
     */
    public boolean isProcessed();

    public List<Vertex> getSuccessors();
}
