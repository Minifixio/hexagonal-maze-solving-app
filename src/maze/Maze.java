package maze;

import dijkstra.Graph;
import dijkstra.Vertex;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;

public class Maze implements Graph {

    public MazeBox[][] boxes;
    public int width;
    public int length;

    public Maze() {

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
                        line += "🬀";
                        break;
                    case ("WallMazeBox"):
                        line += '█';
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
