package dijkstra;

import java.util.List;

public class Dijkstra {

    Integer inf = Integer.MAX_VALUE;

    public ShortestPaths dijkstra(
            Graph graph,
            Vertex startVertex,
            Vertex endVertex,
            ProcessedVertexes processedVertexes,
            MinDistance minDistance,
            Distance distance) {

        processedVertexes.addVertex(startVertex);
        Vertex pivot = startVertex;
        minDistance.setMinDistance(startVertex, 0);

        for (Vertex vertex : graph.getAllVertexes()) {
            minDistance.setMinDistance(vertex, inf);
        }

        while (processedVertexes.containsEndVertex() == false) {
            List<Vertex> successors = pivot.getSuccessors();
            for (Vertex successor : successors) {
                if (!successor.isProcessed()) {
                    if (minDistance.getMinDistance(pivot) + distance.distance(pivot, successor) < minDistance.getMinDistance(successor)) {
                        minDistance.setMinDistance(successor, minDistance.getMinDistance(pivot) + distance.distance(pivot, successor));
                        successor.setPredecessor(pivot);
                    }
                }
            }

            for (Vertex vertex : graph.getAllVertexesSorted()) {
                if (!vertex.isProcessed()) {
                    pivot = vertex;
                }
            }

            processedVertexes.addVertex(pivot);
        }
        return null;
    }
}
