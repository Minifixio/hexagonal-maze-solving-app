package dijkstra;

import java.util.List;

public interface Vertex {

    /**
     * @return les successeurs du sommet en question
     */
    public List<Vertex> getSuccessors();
}
