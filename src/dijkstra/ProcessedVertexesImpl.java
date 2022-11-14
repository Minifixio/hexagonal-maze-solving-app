package dijkstra;
import java.util.HashSet;

public class ProcessedVertexesImpl implements ProcessedVertexes {

    private HashSet<Vertex> processedVertexes = new HashSet<>();
    public boolean contains(Vertex vertex) {
        return processedVertexes.contains(vertex);
    }

    public void addVertex(Vertex vertex) {
        processedVertexes.add(vertex);
    }

}
