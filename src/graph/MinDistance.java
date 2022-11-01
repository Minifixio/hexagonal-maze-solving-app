package graph;

public interface MinDistance {

    /**
     * @param vertex le sommet pour lequel on calcul la distance min
     * @return distance minimale a la racine
     */
    public int getMinDistance(Vertex vertex);

    public void setMinDistance(Vertex vertex, int distance);

}
