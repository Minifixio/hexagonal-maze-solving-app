package dijkstra;

public interface ShortestPaths {

    /**
     * @param vertex le sommet pour lequel on définit le prédecesseur
     * @param predecessor le sommet precedent dans l'arboresence depuis la racine
     */
    public void setPredecessor(Vertex vertex, Vertex predecessor);

    /**
     * @param vertex le sommet pour lequel on recherche le sommet précédent correspondant au chemin le plus court depuis la racine
     * @return
     */
    public Vertex getPredecessor(Vertex vertex);
}
