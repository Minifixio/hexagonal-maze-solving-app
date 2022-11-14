package dijkstra;
import java.util.HashSet;

public class ProcessedVertexesImpl implements ProcessedVertexes {

    private HashSet<Vertex> processedVertexes = new HashSet<>();
    public boolean containsEndVertex() {
        return false;
    }

    public void addVertex(Vertex vertex) {

    }

    public void setDistance(Integer distance) {

    }
}
