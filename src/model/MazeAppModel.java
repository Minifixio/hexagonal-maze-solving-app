package model;

import dijkstra.Dijkstra;
import dijkstra.ShortestPaths;
import maze.*;

import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;

import static java.lang.Math.min;

public class MazeAppModel {
    /**
     * Gère les changement sur le labyrinthe
     */
    private ChangeListener mazeAppListener;
    /**
     * Gère les actions sur le menu déroulant
     */
    private ChangeListener fileMenuListener;
    /**
     * Change les actions de changement de taille
     */
    private ChangeListener sizePanelListener;
    /**
     * Gère les actions de recherche du chemin optimal
     */
    private ChangeListener pathFoundListener;
    private int gridWidth;
    private int gridHeight;
    private final int appHeight;
    private final int appWidth;
    private int mazeMinSize;
    private int mazeMaxWidth;
    private int mazeMaxHeight;
    private int mazeDefaultHeight;
    private int mazeDefaultWidth;
    private int widthSpinnerValue;
    private int heightSpinnerValue;
    private int hexagonSize;
    private final Maze maze = new Maze();

    public MazeAppModel(int gridWidth, int gridHeight, int hexagonSize, int appWidth, int appHeight) {
        this.gridWidth = gridWidth;
        this.gridHeight = gridHeight;
        this.hexagonSize = hexagonSize;
        this.appWidth = appWidth;
        this.appHeight = appHeight;
        this.maze.initEmptyMaze(gridWidth, gridHeight);
        this.resetHexagonGrid();
    }

    /**
     * Assigne aux différentes cases un hexagone à la bonne place sur la grille
     */
    public void assignHexagons() {
        // On initialise de manière à placer les hexagones dans le cadre du panel
        double startXPos = (this.appWidth - Math.sqrt(3)*hexagonSize*this.gridWidth)/2;
        double startYPos = (this.appHeight - 2*hexagonSize*this.gridHeight)/2;
        double[] pos = new double[]{startXPos,startYPos};

        for(int i=0;i<gridHeight;i++) {
            pos[1] = 2*hexagonSize*0.75*i + startYPos;
            for(int j=0;j<gridWidth;j++) {

                // Selon la parité de i (coordonnée x), on décale ou pas la ligne d'hexagones (de 1/2*width) par rapport à la précédente
                // Voir https://www.redblobgames.com/grids/hexagons/ pour l'explication géométrique
                if (i%2 == 0) {
                    pos[0] = j*Math.sqrt(3)*hexagonSize+0.5*Math.sqrt(3)*hexagonSize + startXPos;
                } else {
                    pos[0] = j*Math.sqrt(3)*hexagonSize + startXPos;
                }

                // On associe à chaque case son hexagone à la bonne position
                Hexagon hexagon = new Hexagon(pos[0], pos[1], hexagonSize);
                MazeBox mazeBox = this.maze.getBoxByCoords(j,i);
                mazeBox.setHexagon(hexagon);
            }
        }
    }

    /**
     * Ré-initialise la grille en générant une grille de cases vides de la taille adéquate
     */
    public void resetHexagonGrid() {
        this.maze.initEmptyMaze(gridWidth, gridHeight);
        this.assignHexagons();
    }

    /**
     * Appelée depuis l'ui pour rafraichir la grille en redessinant chacun des hexagones
     */
    public void refreshHexagonGrid(Graphics g) {
        for(int i=0;i<gridWidth;i++) {
            for(int j=0;j<gridHeight;j++) {
                Hexagon h = maze.getBoxByCoords(i,j).getHexagon();
                h.paint(g);
            }
        }

        this.paintArrivalFlag(g);
        this.paintDepartureTraveller(g);
    }

    public void paintArrivalFlag(Graphics g) {
        if (this.maze.getEndVertex() != null) {
            ArrivalFlag arrivalFlag = new ArrivalFlag((int) this.maze.getEndVertex().getHexagon().getxCenter() - this.hexagonSize, (int) this.maze.getEndVertex().getHexagon().getyCenter()-2*this.hexagonSize, this.hexagonSize*3);
            arrivalFlag.paint(g);
        }
    }

    public void paintDepartureTraveller(Graphics g) {
        if (this.maze.getStartVertex() != null) {
            DepartureTraveller departureTraveller = new DepartureTraveller((int) this.maze.getStartVertex().getHexagon().getxCenter() - this.hexagonSize, (int) this.maze.getStartVertex().getHexagon().getyCenter()-3*this.hexagonSize,  2*this.hexagonSize);
            departureTraveller.paint(g);
        }
    }

    /**
     * Trouve le chemin optimal et le trace sur l'interface
     */
    public void solveMaze() {
        // On supprime le chemin optimal courant (si il existe)
        this.maze.resetBoxesInPath();

        MazeDistance mazeDistance = new MazeDistance();
        ShortestPaths shortestPaths = Dijkstra.dijkstra(maze, maze.getStartVertex(), maze.getEndVertex(), mazeDistance);

        boolean pathFound = this.maze.setBoxesInPath(shortestPaths);
        if (pathFound) {
            this.drawCorrectPath();
            this.mazeSateChanged();
        } else {
            this.pathFoundListener.stateChanged(new ChangeEvent(this));
        }

    }

    /**
     * Colorie les cases du chemin optimal
     */
    private void drawCorrectPath() {
        for(int i=0;i<gridWidth;i++) {
            for(int j=0;j<gridHeight;j++) {
                MazeBox box = maze.getBoxByCoords(i,j);
                // On colore les cases du chemin optimal différement
                if (box.isInPath) {
                    box.setHexagonTexturePaint("path.png");
                }
            }
        }
    }

    /**
     * Utilisée pour changer le type d'une case (x,y) suite à un clic sur cette dernière
     * On a la rotation suivante :
     * EmptyWallMazeBox ->(clic)-> WallMazeBox
     *
     * WallMazeBox ->(clic)->
     *                      si le départ est fixé :
     *                          si l'arrivée est fixée : EmptyMazeBox
     *                          sinon : ArrivalMazeBox
     *
     * DepartureMazeBox ->(clic)->
     *                      si l'arrivée est fixée : EmptyMazeBox
     *                      sinon : ArrivalMazeBox
     *
     * ArrivalMazeBox ->(clic)-> EmptyWallMazeBox
     */
    private void changeMazeBoxType(int x, int y) {
        MazeBox box = maze.getBoxByCoords(x, y);
        MazeBox newBox = null;

        switch (box.getType()) {
            case 'E' -> newBox = new WallMazeBox(maze, x, y);
            case 'W' -> {
                if (this.maze.getStartVertex() != null) {
                    if(this.maze.getEndVertex() != null) {
                        newBox = new EmptyMazeBox(maze, x, y);
                    } else {
                        newBox = new ArrivalMazeBox(maze, x, y);
                        this.maze.setEndVertex(newBox);
                    }
                } else {
                    newBox = new DepartureMazeBox(maze, x, y);
                    this.maze.setStartVertex(newBox);
                }
            }
            case 'D' -> {
                this.maze.setStartVertex(null);
                if (this.maze.getEndVertex() != null) {
                    newBox = new EmptyMazeBox(maze, x, y);
                } else {
                    newBox = new ArrivalMazeBox(maze, x, y);
                    this.maze.setEndVertex(newBox);
                }
            }
            case 'A' -> {
                this.maze.setEndVertex(null);
                newBox = new EmptyMazeBox(maze, x, y);
            }
        }
        Hexagon h = new Hexagon(box.getHexagon().getxCenter(), box.getHexagon().getyCenter(), hexagonSize);
        assert newBox != null;
        newBox.setHexagon(h);
        maze.setBoxByCoords(x,y, newBox);
        maze.printMaze();
    }

    /**
     * Traite un clic sur la fenètre pour trouver sur quel hexagone il a eu lieu
     */
    public void changeMazeBoxFromClick(double x, double y) {
        for(int i=0;i<gridWidth;i++) {
            for(int j=0;j<gridHeight;j++) {
                if (isInsideHexagon(x, y, maze.getBoxByCoords(i,j).getHexagon().getxCenter(), maze.getBoxByCoords(i,j).getHexagon().getyCenter(), this.hexagonSize)) {
                    this.changeMazeBoxType(i,j);
                    this.mazeSateChanged();
                    return;
                }
            }
        }
    }

    /**
     * On teste si un point (x,y) est situé dans l'intérieur de l'hexagone de centre (xCenter, yCenter)
     * Explications : http://www.playchilla.com/how-to-check-if-a-point-is-inside-a-hexagon
     * @return true ou false selon si le point est dans l'hexagone de centre (xCenter, yCenter)
     */
    private boolean isInsideHexagon(double x, double y, double xCenter, double yCenter, int hexagonSize) {
        double q2x = Math.abs(x - xCenter);
        double q2y = Math.abs(y - yCenter);
        if (q2x > Math.sqrt(3)*hexagonSize*0.5 || q2y > hexagonSize) {
            return false;
        } else {
            return hexagonSize*Math.sqrt(3)*hexagonSize*0.5 - 0.5*hexagonSize*q2x - Math.sqrt(3)*hexagonSize*0.5*q2y >= 0;
        }
    }

    /**
     * Redessine la grille sur le Panel
     */
    public void redrawHexagonGrid() {
        this.resetHexagonGrid();
        this.mazeSateChanged();
    }

    /**
     * Dessine un labyrinthe aléatoire
     */
    public void drawRandomHexagon() {
        this.maze.initRandomMaze();
        this.assignHexagons();
        this.mazeSateChanged();
    }

    /**
     * @param filePath le chemin vers le fichier .txt sur lequel on sauvegarde le Labyrinthe
     */
    public void saveToTextFile(String filePath) {
        try {
            this.maze.saveToTextFile(filePath);
        } catch (MazeWritingException e) {
            this.resetHexagonGrid();
            this.fileMenuStateChanged();
        }
    }

    /**
     * @param filePath le chemin vers le fichier .txt à ouvrir
     */
    public void initMazeFromFile(String filePath) {
        try {
            this.maze.initFromTextFile(filePath);
            this.gridHeight = this.maze.height;
            this.gridWidth = this.maze.width;
            this.hexagonSize = (int) min(0.45*(this.appHeight / this.gridHeight), (1.0/Math.sqrt(3))*(this.appWidth / this.gridWidth));
            this.mazeMinSize = 2;
            this.mazeMaxWidth = this.maze.width;
            this.mazeMaxHeight = this.maze.height;
            this.mazeDefaultHeight = this.maze.height;
            this.mazeDefaultWidth = this.maze.width;

            // Pour enlever le drapeau et traveller
            this.mazeSateChanged();

            this.assignHexagons();
            this.sizeConstraintsChanged();
            this.mazeSateChanged();

        } catch (MazeReadingException e) {
            this.resetHexagonGrid();
            this.fileMenuStateChanged();
        }
    }

    public void mazeSateChanged() {
        this.mazeAppListener.stateChanged(new ChangeEvent(this));
    }

    public void fileMenuStateChanged() {
        this.fileMenuListener.stateChanged(new ChangeEvent(this));
    }

    public void sizeConstraintsChanged() {
        this.sizePanelListener.stateChanged(new ChangeEvent(this));
    }

    public int getWidthSpinnerValue() {
        return widthSpinnerValue;
    }

    public void setWidthSpinnerValue(int widthSpinnerValue) {
        this.widthSpinnerValue = widthSpinnerValue;
    }

    public int getHeightSpinnerValue() {
        return heightSpinnerValue;
    }

    public void setHeightSpinnerValue(int heightSpinnerValue) {
        this.heightSpinnerValue = heightSpinnerValue;
    }

    public void changeMazeSize() {
        this.gridWidth = this.getWidthSpinnerValue();
        this.gridHeight = this.getHeightSpinnerValue();
        this.redrawHexagonGrid();
    }

    public int getAppHeight() {
        return this.appHeight;
    }

    public int getAppWidth() {
        return this.appWidth;
    }

    public void setMazeMinSize(int mazeMinSize) {
        this.mazeMinSize = mazeMinSize;
    }

    public void setMazeMaxWidth(int mazeMaxWidth) {
        this.mazeMaxWidth = mazeMaxWidth;
    }

    public void setMazeMaxHeight(int mazeMaxHeight) {
        this.mazeMaxHeight = mazeMaxHeight;
    }

    public void setMazeAppListener(ChangeListener mazeAppListener) {
        this.mazeAppListener = mazeAppListener;
    }

    public void setFileMenuListener(ChangeListener fileMenuListener) {
        this.fileMenuListener = fileMenuListener;
    }

    public void setSizePanelListener(ChangeListener sizePanelListener) {
        this.sizePanelListener = sizePanelListener;
    }

    public int getMazeMinSize() {
        return mazeMinSize;
    }

    public int getMazeMaxWidth() {
        return mazeMaxWidth;
    }

    public int getMazeMaxHeight() {
        return mazeMaxHeight;
    }

    public int getMazeDefaultHeight() {
        return mazeDefaultHeight;
    }

    public void setMazeDefaultHeight(int mazeDefaultHeight) {
        this.mazeDefaultHeight = mazeDefaultHeight;
    }

    public int getMazeDefaultWidth() {
        return mazeDefaultWidth;
    }

    public void setMazeDefaultWidth(int mazeDefaultWidth) {
        this.mazeDefaultWidth = mazeDefaultWidth;
    }

    public void setPathFoundListener(ChangeListener pathFoundListener) {
        this.pathFoundListener = pathFoundListener;
    }
}
