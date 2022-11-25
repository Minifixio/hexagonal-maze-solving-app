package maze;

import dijkstra.Graph;
import dijkstra.ShortestPaths;
import dijkstra.Vertex;
import org.jetbrains.annotations.NotNull;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Maze implements Graph {

    public MazeBox[][] boxes;
    public int width;
    public int height;
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

    /**
     * Permet d'afficher le labyrinthe dans la console avec la charte graphique suivante :
     * EmptyMazeBox -> 🬀
     * WallMazeBox -> █
     * ArrivalMazeBox -> X
     * DepartureMazeBox -> O
     */
    public void printMaze() {
        for (int i=0; i<this.height; i++) {
            String line = "";
            for (int j=0; j<this.width; j++) {
                switch (this.getBoxByCoords(j, i).getType()) {
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

    /**
     * @param fileLocation path vers le fichier
     * @return le labyrinthe initialisé par lecture du fichier
     * @throws MazeReadingException
     */
    public Maze initFromTextFile(String fileLocation) throws MazeReadingException {
        try {
            BufferedReader br = new BufferedReader(new FileReader(fileLocation));

            try {
                // Possible IOException si la lecture de la première ligne pose problème
                this.width = br.readLine().length();
                this.height = 1;
                while (br.readLine() != null) this.height++;

                System.out.println("Création d'un labyrinthe de longueur : " + this.height + " et de largeur " + this.width);
                this.boxes = new MazeBox[this.width][this.height];

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

    /**
     * @param width largeur du labyrinthe
     * @param height hauteur du labyrinthe
     * @return le labyrinthe de taille (width x height) initialisé avec seulement des EmptyMazeBox
     */
    public Maze initEmptyMaze(int width, int height) {
        this.width = width;
        this.height = height;
        this.boxes = new MazeBox[this.width][this.height];

        // Pas d'arrivée ou de départ fixé pour le moment
        this.setStartVertex(null);
        this.setEndVertex(null);

        for (int i=0; i<width; i++) {
            for (int j=0;j<height; j++) {
                this.setBoxByCoords(i,j, new EmptyMazeBox(this, i, j));
            }
        }
        return this;
    }

    /**
     * @param x
     * @param y
     * @return la MazeBox à la position (x,y)
     */
    public MazeBox getBoxByCoords(int x, int y) {
        return this.boxes[x][y];
    }

    /**
     * @param x
     * @param y
     * @param box la MazeBox à attribuer en (x,y)
     */
    public void setBoxByCoords(int x, int y, MazeBox box) {
        this.boxes[x][y] = null;
        this.boxes[x][y] = box;
    }

    /**
     * @return Toutes les cases du Labyrinthe
     */
    public List<Vertex> getAllVertexes() {
        List<Vertex> res = new ArrayList<>();
        for (int x=0; x<this.width; x++) {
            for (int y=0; y<this.height; y++) {
                res.add(getBoxByCoords(x,y));
            }
        }
        return res;
    }

    /**
     * Permet de supprimer les tags "isInPath" des cases du chemin optimal pour pouvoir en tracer un nouveau
     */
    public void resetBoxesInPath() {
        for (int i=0; i<this.width; i++) {
            for(int j=0; j<this.height; j++) {
                this.getBoxByCoords(i,j).isInPath = false;
                this.getBoxByCoords(i,j).setDefaultColor();
            }
        }
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


    /**
     * Fonction de debug pour voir les cases visitées pour chacunes des cases du Labyrinthe
     * @param vertex le sommet pour lequel on veut afficher le chemin optimal depuis l'origine
     * @param shortestPaths l'arborescence des plus courts chemins
     */
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

    /**
     * Marque les chemins appartenant au chemin optimal de l'origine au sommet de fin
     * @param shortestPaths l'arborescence des plus courts chemins
     */
    public void setBoxesInPath(@NotNull ShortestPaths shortestPaths) {
        // On remonte succesivement les predecesseurs depuis arrivalMazeBox
        MazeBox predecessor = (MazeBox) shortestPaths.getPredecessor(this.getEndVertex());

        // On s'arrête quand on tombe sur la case de départ
        while (predecessor != departureMazeBox) {
            predecessor.isInPath = true;
            MazeBox predecessorTemp = (MazeBox) shortestPaths.getPredecessor(predecessor);
            predecessor = predecessorTemp;
        }
    }

    /**
     * Permet de tracer le plus court chemins sur le Labyrinthe affiché dans la console avec la cherte graphique :
     * EmptyMazeBox -> 🬀
     * WallMazeBox -> █
     * ArrivalMazeBox -> X
     * DepartureMazeBox -> O
     * Case du chemin optimal -> •
     * @param shortestPaths l'arborescence des plus courts chemins
     */
    public void printPathInMaze(ShortestPaths shortestPaths) {
        setBoxesInPath(shortestPaths);
        for (int i=0; i<this.height; i++) {
            String line = "";
            for (int j=0; j<this.width; j++) {
                if (this.getBoxByCoords(j,i).isInPath){
                    line += "•";
                } else {
                    switch(this.getBoxByCoords(j,i).getType()) {
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
