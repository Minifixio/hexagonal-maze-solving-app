package dijkstra;

import java.util.HashMap;

public class MinDistanceImpl implements MinDistance {

    /**
     * Une HashMap qui attribue à chaque sommet (Vertex) sa distance à la racine (Integer)
     * Sa distance peut être infinie d'ou l'utilisatoin d'Integer
     */
    private final HashMap<Vertex, Integer> minDistance = new HashMap<>();

    public Integer getMinDistance(Vertex vertex) {
        return minDistance.get(vertex);
    }

    public void setMinDistance(Vertex vertex, Integer distance) {
        minDistance.put(vertex, distance);
    }
}
