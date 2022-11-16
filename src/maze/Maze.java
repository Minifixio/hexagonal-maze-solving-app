package maze;

import dijkstra.Graph;
import dijkstra.ShortestPaths;
import dijkstra.Vertex;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Maze implements Graph {

    public MazeBox[][] boxes;
    public int width;
    public int length;
    private DepartureMazeBox departureMazeBox;
    private ArrivalMazeBox arrivalMazeBox;

    // TODO : A retirer à terme si rien n'est passé en paramètre
    public Maze() {}

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
                ArrivalMazeBox arrival = new ArrivalMazeBox(this, x, y);
                this.boxes[x][y] = new ArrivalMazeBox(this, x, y);
                this.arrivalMazeBox = (ArrivalMazeBox) this.boxes[x][y];
                return true;
            case 'D':
                DepartureMazeBox departure = new DepartureMazeBox(this, x, y);
                this.boxes[x][y] = departure;
                this.departureMazeBox = (DepartureMazeBox) this.boxes[x][y];
                return true;
            default:
                return false;
        }
    }

    public void printMaze() {
        for (int i=0; i<this.length; i++) {
            String line = "";
            for (int j=0; j<this.width; j++) {
                switch (this.getBoxByCoords(j, i).type) {
                    case ('E'):
                        line += "🬀";
                        break;
                    case ('W'):
                        line += '█';
                        break;
                    case ('A'):
                        line += "X";
                        break;
                    case ('D'):
                        line += "O";
                        break;
                }
            }
            System.out.println(line);
        }
    }
    public Maze initFromTextFile(String fileName) throws MazeReadingException {
        String fileLocation = "data/" + fileName + ".txt";
        try {
            BufferedReader br = new BufferedReader(new FileReader(fileLocation));

            try {
                // Possible IOException si la lecture de la première ligne pose problème
                this.width = br.readLine().length();
                this.length = 1;
                while (br.readLine() != null) this.length++;

                System.out.println("Création d'un labyrinthe de longueur : " + this.length + " et de largeur " + this.width);
                this.boxes = new MazeBox[this.width][this.length];

                // On reset le Buffer car on a fait descendre le curseur jusqu'en bas pour avoir mazeLength
                br = new BufferedReader(new FileReader(fileLocation));

                String line = null;
                int lineCount = 0;
                while ((line = br.readLine()) != null) {

                    // Si l'une des lignes n'est pas de la même taille que la première (avec la convention qu'un labyrinthe est rectangulaire)
                    if (line.length() != this.width) {
                        throw new MazeReadingException(fileLocation, lineCount, "Ligne de mauvaise taille");
                    }

                    for (int i = 0; i < line.length(); i++) {
                        boolean boxValid = this.addBoxByCoords(i, lineCount, line.charAt(i));

                        // Si le caractère n'est pas 'E', 'W', 'A' ou 'D'
                        if (!boxValid) {
                            throw new MazeReadingException(fileLocation, lineCount, "Mauvais caractère (" + line.charAt(i) + ") à la position " + i);
                        }
                    }
                    lineCount += 1;
                }
                br.close();
                return this;

            } catch (IOException e) {
                throw new MazeReadingException(fileLocation, 0, "Initialisation de la taille du labyrinthe");
            }

        } catch (FileNotFoundException e) {
            throw new MazeReadingException(fileLocation, null, "Ouverture du fichier");
        }
    }
    public MazeBox getBoxByCoords(int x, int y) {
        return this.boxes[x][y];
    }

    public List<Vertex> getAllVertexes() {
        List<Vertex> res = new ArrayList<>();
        for (int x=0; x<this.width; x++) {
            for (int y=0; y<this.length; y++) {
                res.add(getBoxByCoords(x,y));
            }
        }
        return res;
    }

    public void setEndVertex(Vertex endVertex) {
        this.arrivalMazeBox = (ArrivalMazeBox) endVertex;
    }

    public void setStartVertex(Vertex startVertex) {
        this.departureMazeBox = (DepartureMazeBox) startVertex;
    }

    public ArrivalMazeBox getEndVertex() {
        return this.arrivalMazeBox;
    }

    public DepartureMazeBox getStartVertex() {
        return this.departureMazeBox;
    }


    public void printShortestPath(Vertex vertex, ShortestPaths shortestPaths) {
        MazeBox predecessor = (MazeBox) shortestPaths.getPredecessor(vertex);
        String pathToDeparture = "";
        while (predecessor != departureMazeBox) {
            pathToDeparture += (" -> Box(" + Integer.toString(predecessor.x) + "," + Integer.toString(predecessor.y) + ")");
            MazeBox predecessorTemp = (MazeBox) shortestPaths.getPredecessor(predecessor);
            predecessor = predecessorTemp;
        }
        System.out.println(pathToDeparture);
    }

    public void printPathInMaze(ShortestPaths shortestPaths) {
        MazeBox predecessor = (MazeBox) shortestPaths.getPredecessor(this.getEndVertex());
        while (predecessor != departureMazeBox) {
            predecessor.isInPath = true;
            MazeBox predecessorTemp = (MazeBox) shortestPaths.getPredecessor(predecessor);
            predecessor = predecessorTemp;
        }
        for (int i=0; i<this.length; i++) {
            String line = "";
            for (int j=0; j<this.width; j++) {
                if (this.getBoxByCoords(j,i).isInPath){
                    line += "•";
                } else {
                    switch(this.getBoxByCoords(j,i).type) {
                        case ('E'):
                            line += "🬀";
                            break;
                        case ('W'):
                            line += '█';
                            break;
                        case ('A'):
                            line += "X";
                            break;
                        case ('D'):
                            line += "O";
                            break;
                    }
                }

            }
            System.out.println(line);
        }
    }

}
