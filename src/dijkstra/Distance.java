package dijkstra;

public interface Distance {

    /**
     * @param vertex1
     * @param vertex2
     * @return la distance entre vertex1 et vertex2 connectés
     */
    public int distance(Vertex vertex1, Vertex vertex2);
}
