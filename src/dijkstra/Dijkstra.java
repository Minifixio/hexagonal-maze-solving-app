package dijkstra;

import maze.ArrivalMazeBox;
import maze.DepartureMazeBox;
import maze.Maze;
import maze.MazeDistance;

import java.util.List;

public class Dijkstra {

    static Integer inf = Integer.MAX_VALUE;

    public static ShortestPaths dijkstra(Graph graph, Vertex startVertex, Vertex endVertex, Distance distance) {
        return dijkstra(graph, startVertex, endVertex, new ProcessedVertexesImpl(), new MinDistanceImpl(), distance, new ShortestPathsImpl());
    }
    public static final ShortestPaths dijkstra(
            Graph graph,
            Vertex startVertex,
            Vertex endVertex,
            ProcessedVertexes processedVertexes,
            MinDistance minDistance,
            Distance distance,
            ShortestPaths shortestPaths) {

        processedVertexes.addVertex(startVertex);
        Vertex pivot = startVertex;
        minDistance.setMinDistance(startVertex, 0);

        for (Vertex vertex : graph.getAllVertexes()) {
            minDistance.setMinDistance(vertex, inf);
        }

        while (processedVertexes.contains(endVertex) == false) {
            List<Vertex> successors = pivot.getSuccessors();
            for (Vertex successor : successors) {
                if (!processedVertexes.contains(successor)) {
                    if (minDistance.getMinDistance(pivot) + distance.distance(pivot, successor) < minDistance.getMinDistance(successor)) {
                        minDistance.setMinDistance(successor, minDistance.getMinDistance(pivot) + distance.distance(pivot, successor));
                        shortestPaths.setPredecessor(successor, pivot);
                    }
                }
            }

            // Revoir l'initialisation à null
            Vertex nextVertex = null;
            Integer minDistanceNextVertex = 100000;
            for (Vertex vertex : graph.getAllVertexes()) {
                if (!processedVertexes.contains(vertex)) {
                    Integer d = minDistance.getMinDistance(vertex);
                    if (d < minDistanceNextVertex) {
                        nextVertex = vertex;
                        minDistanceNextVertex = d;
                    }
                }
            }

            if (nextVertex == null) {
                break;
            } else {
                pivot = nextVertex;
                processedVertexes.addVertex(pivot);
            }

        }
        return shortestPaths;
    }
}
