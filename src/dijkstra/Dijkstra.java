package dijkstra;

import maze.ArrivalMazeBox;
import maze.DepartureMazeBox;
import maze.Maze;
import maze.MazeDistance;

import java.util.List;

/**
 * Classe implémentant l'algorithme de Dijkstra, permettant de calculer des plus courts chemins dans un graphe
 */
public class Dijkstra {

    // Définition de l'infini, utilisé pour l'initialisation de la distance des sommets par rapport à l'origine
    static Integer inf = Integer.MAX_VALUE;

    /**
     * Méthode publique d'appel de Dijkstra où l'on passe en paramètre les classes ProcessedVertexesImpl, MinDistanceImpl et ShortestPathsImpl
     * @param graph Le graph avec ses sommets à traiter
     * @param startVertex Le sommet de départ (origine)
     * @param endVertex Le sommet pour lequel on cherche la distance minimale par rapport à l'origine
     * @param distance Une fonction indiquant le type de distance à utiliser dans le graphe
     * @return L'arborescence des plus courts chemins par rapport à l'origine
     */
    public static ShortestPaths dijkstra(Graph graph, Vertex startVertex, Vertex endVertex, Distance distance) {
        return dijkstra(graph, startVertex, endVertex, new ProcessedVertexesImpl(), new MinDistanceImpl(), distance, new ShortestPathsImpl());
    }
    private static final ShortestPaths dijkstra(
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

            // TODO : Revoir l'initialisation à null
            Vertex nextVertex = null;
            Integer minDistanceNextVertex = inf;
            for (Vertex vertex : graph.getAllVertexes()) {
                if (!processedVertexes.contains(vertex)) {
                    Integer d = minDistance.getMinDistance(vertex);
                    if (d <= minDistanceNextVertex) {
                        nextVertex = vertex;
                        minDistanceNextVertex = d;
                    }
                }
            }

            // Si nextVertex==null, on a pas trouvé d'arrête adjacente à distance finie donc on rompt la boucle
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
