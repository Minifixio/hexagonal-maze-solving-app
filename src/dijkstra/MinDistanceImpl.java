package dijkstra;

import java.util.HashMap;

public class MinDistanceImpl implements MinDistance {

    private HashMap<Vertex, Integer> minDistance = new HashMap<>();
    public Integer getMinDistance(Vertex vertex) {
        return minDistance.get(vertex);
    }
    public void setMinDistance(Vertex vertex, Integer distance) {
        minDistance.put(vertex, distance);
    }
}
