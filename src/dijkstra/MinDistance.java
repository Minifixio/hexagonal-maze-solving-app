package dijkstra;

public interface MinDistance {

    /**
     * @param vertex le sommet pour lequel on calcul la distance min
     * @return distance minimale a la racine
     */
    public Integer getMinDistance(Vertex vertex);

    /**
     * @param vertex le sommet pour lequel on ajoute la distance à la racine
     * @param distance la distance par rapport à la racine pour ce sommet
     */
    public void setMinDistance(Vertex vertex, Integer distance);

}
