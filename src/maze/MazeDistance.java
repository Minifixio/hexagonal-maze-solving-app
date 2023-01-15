package maze;

import dijkstra.Distance;
import dijkstra.Vertex;

public class MazeDistance implements Distance {

    /**
     * On suppose que les deux sommets passés en paramètres sont adjacents
     * @return +inf si l'un des deux sommets est un mur, 1 sinon
     */
    public Integer distance(Vertex vertex1, Vertex vertex2) {
        if (vertex1 instanceof WallMazeBox || vertex2 instanceof WallMazeBox) {
            return Integer.MAX_VALUE;
        } else {
            return 1;
        }
    }
}
