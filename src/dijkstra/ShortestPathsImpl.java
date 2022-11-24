package dijkstra;

import java.util.HashMap;

public class ShortestPathsImpl implements ShortestPaths {

    /**
     * Une HashMap qui associe à un sommet son prédecesseur dans un chemin optimal depuis la racine
     */
    private HashMap<Vertex, Vertex> shortestPaths = new HashMap();
    public void setPredecessor(Vertex vertex, Vertex predecessor) {
        shortestPaths.put(vertex, predecessor);
    }

    public Vertex getPredecessor(Vertex vertex) {
        return shortestPaths.get(vertex);
    }
}
