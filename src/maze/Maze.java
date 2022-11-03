package maze;

import dijkstra.Graph;
import dijkstra.Vertex;

import java.util.List;

public class Maze implements Graph {

    public MazeBox[][] boxes;
    public int width;
    public int length;

    public Maze(int width, int length) {
        this.width = width;
        this.length = length;
        this.boxes = new MazeBox[width][length];
    }

    /**
     * @param x coord x de la case
     * @param y coord y de la case
     * @param type le type de case selon la lettre correspondante ('E', 'W', 'A' ou 'D')
     * @return true si la case est valide (i.e type est du type 'E', 'W', 'A' ou 'D'), false sinon
     */
    public boolean addBoxByCoords(int x, int y, char type) {
        switch (type) {
            case 'W':
                this.boxes[x][y] = new WallMazeBox(this, x, y);
                return true;
            case 'E':
                this.boxes[x][y] = new EmptyMazeBox(this, x, y);
                return true;
            case 'A':
                this.boxes[x][y] = new ArrivalMazeBox(this, x, y);
                return true;
            case 'D':
                this.boxes[x][y] = new DepartureMazeBox(this, x, y);
                return true;
            default:
                return false;
        }
    }

    public void printMaze() {
        for (int i=0; i<this.length; i++) {
            String line = "";
            for (int j=0; j<this.width; j++) {
                switch(this.getBoxByCoords(j,i).getClass().getSimpleName()) {
                    case ("EmptyMazeBox"):
                        line += "+";
                        break;
                    case ("WallMazeBox"):
                        line += "#";
                        break;
                    case ("ArrivalMazeBox"):
                        line += "X";
                        break;
                    case ("DepartureMazeBox"):
                        line += "O";
                        break;
                }
            }
            System.out.println(line);
        }
    }
    public Vertex getBoxByCoords(int x, int y) {
        return this.boxes[x][y];
    }

    public List<Vertex> getAllVertexes() {
        return null;
    }

    public List<Vertex> getAllVertexesSorted() {
        return null;
    }

    public List<Vertex> getSuccessors(Vertex vertex) {
        return null;
    }

    public List<Vertex> getSuccessors() {
        return null;
    }

    public int getWeight(Vertex src, Vertex dst) {
        return 0;
    }

    public void setStartVertex(Vertex startVertex) {

    }

    public void setEndVertex(Vertex endVertex) {

    }

    public Vertex[] getUnprocessedVertexes() {
        return new Vertex[0];
    }
}
