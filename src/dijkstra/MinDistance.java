package dijkstra;

public interface MinDistance {

    /**
     * @param vertex le sommet pour lequel on calcul la distance min
     * @return distance minimale a la racine
     */
    public Integer getMinDistance(Vertex vertex);

    public void setMinDistance(Vertex vertex, Integer distance);

}
