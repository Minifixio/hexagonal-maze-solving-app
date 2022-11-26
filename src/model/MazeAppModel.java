package model;

import dijkstra.Dijkstra;
import dijkstra.ShortestPaths;
import maze.*;

import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.util.ArrayList;

import static java.lang.Math.min;

public class MazeAppModel {
    private ChangeListener mazeAppListener;
    private ChangeListener fileMenuListener;

    private ChangeListener sizePanelListener;
    private int gridWidth;
    private int gridHeight;
    private int appHeight;
    private int appWidth;
    private int mazeMinSize;
    private int mazeMaxWidth;
    private int mazeMaxHeight;
    private int mazeDefaultHeight;
    private int mazeDefaultWidth;
    private int widthSpinnerValue;
    private int heightSpinnerValue;
    private int hexagonSize;
    private Maze maze = new Maze();

    public MazeAppModel(int gridWidth, int gridHeight, int hexagonSize) {
        this.gridWidth = gridWidth;
        this.gridHeight = gridHeight;
        this.hexagonSize = hexagonSize;
        this.maze.initEmptyMaze(gridWidth, gridHeight);
        this.resetHexagonGrid();
    }

    /**
     * Ré-initialise la grille en générant une grille de cases vides de la taille adéquate
     */
    public void resetHexagonGrid() {
        System.out.println("Reset grid with size : width "  + this.gridWidth + " height " + this.gridHeight);

        this.maze.initEmptyMaze(gridWidth, gridHeight);

        // On initialise de manière à placer les hexagones dans le cadre du panel
        double[] pos = new double[]{hexagonSize,hexagonSize};

        for(int i=0;i<gridHeight;i++) {
            pos[1] = 2*hexagonSize*0.75*i + hexagonSize;
            for(int j=0;j<gridWidth;j++) {

                // Selon la parité de i (coordonnée x), on décale ou pas la ligne d'hexagones (de 1/2*width) par rapport à la précédente
                // Voir https://www.redblobgames.com/grids/hexagons/ pour l'explication géométrique
                if (i%2 == 0) {
                    pos[0] = j*Math.sqrt(3)*hexagonSize+0.5*Math.sqrt(3)*hexagonSize + hexagonSize;
                } else {
                    pos[0] = j*Math.sqrt(3)*hexagonSize + hexagonSize;
                }

                // On associe à chaque case son hexagone à la bonne position
                Hexagon hexagon = new Hexagon(pos[0], pos[1], hexagonSize);
                EmptyMazeBox emptyMazeBox = new EmptyMazeBox(maze,j,i);
                emptyMazeBox.setHexagon(hexagon);

                maze.setBoxByCoords(j, i, emptyMazeBox);
            }
        }
    }

    /**
     * Appelée depuis l'ui pour rafraichir la grille en redessinant chacun des hexagones
     * @param g
     */
    public void refreshHexagonGrid(Graphics g) {
        for(int i=0;i<gridWidth;i++) {
            for(int j=0;j<gridHeight;j++) {
                Hexagon h = maze.getBoxByCoords(i,j).getHexagon();
                h.paint(g);
            }
        }
    }

    public void solveMaze() {
        // On supprime le chemin optimal courant (si il existe)
        this.maze.resetBoxesInPath();

        MazeDistance mazeDistance = new MazeDistance();
        ShortestPaths shortestPaths = Dijkstra.dijkstra(maze, maze.getStartVertex(), maze.getEndVertex(), mazeDistance);

        this.maze.printPathInMaze(shortestPaths);
        this.drawCorrectPath();
        this.mazeSateChanged();
    }

    private void drawCorrectPath() {
        for(int i=0;i<gridWidth;i++) {
            for(int j=0;j<gridHeight;j++) {
                MazeBox box = maze.getBoxByCoords(i,j);
                // On colore les cases du chemin optimal différement
                if (box.isInPath) {
                    box.setHexagonColor(Color.MAGENTA);
                }
            }
        }
    }

    /**
     * Utilisée pour changer le type d'une case (x,y) suite à un clic sur cette dernière
     * On a la rotation suivante :
     * EmptyWallMazeBox ->(clic)-> WallMazeBox
     * WallMazeBox ->(clic)-> DepartureMazeBox
     * DepartureMazeBox ->(clic)-> ArrivalMazeBox
     * ArrivalMazeBox ->(clic)-> EmptyWallMazeBox
     * Si il y a déjà une case d'arrivée (de départ), et que l'on clique sur un mur (sur une case de départ),
     * on efface la case d'arrivée courante (de départ) en la remplacant par une case vide
     *
     * @param x
     * @param y
     */
    private void changeMazeBoxType(int x, int y) {
        MazeBox box = maze.getBoxByCoords(x, y);
        MazeBox newBox = null;

        switch(box.getType()) {
            case 'E':
                newBox = new WallMazeBox(maze, x, y);
                break;
            case 'W':
                System.out.println('W');
                newBox = new DepartureMazeBox(maze, x, y);

                // Si il existe déjà une case départ...
                if (this.maze.getStartVertex() != null) {
                    EmptyMazeBox e = new EmptyMazeBox(maze, this.maze.getStartVertex().x, this.maze.getStartVertex().y);
                    Hexagon h = new Hexagon(this.maze.getStartVertex().getHexagon().getxCenter(), this.maze.getStartVertex().getHexagon().getyCenter(), hexagonSize);
                    e.setHexagon(h);
                    this.maze.setBoxByCoords(this.maze.getStartVertex().x, this.maze.getStartVertex().y, e);
                }
                // .. on la remplace par une case vide
                this.maze.setStartVertex(newBox);
                break;
            case 'D':
                System.out.println('D');
                newBox = new ArrivalMazeBox(maze, x, y);

                // Si il existe déjà une case arrivée...
                if (this.maze.getEndVertex() != null) {
                    EmptyMazeBox e = new EmptyMazeBox(maze, this.maze.getEndVertex().x, this.maze.getEndVertex().y);
                    Hexagon h = new Hexagon(this.maze.getEndVertex().getHexagon().getxCenter(), this.maze.getEndVertex().getHexagon().getyCenter(), hexagonSize);
                    e.setHexagon(h);
                    this.maze.setBoxByCoords(this.maze.getEndVertex().x, this.maze.getEndVertex().y, e);
                }
                // ...on la remplace par une case vide
                this.maze.setStartVertex(null);
                this.maze.setEndVertex(newBox);
                break;
            case 'A':
                System.out.println('A');
                this.maze.setEndVertex(null);
                newBox = new EmptyMazeBox(maze, x, y);
                break;
        }
        Hexagon h = new Hexagon(box.getHexagon().getxCenter(), box.getHexagon().getyCenter(), hexagonSize);
        newBox.setHexagon(h);
        maze.setBoxByCoords(x,y, newBox);
        maze.printMaze();
    }

    /**
     * Traite un clic sur la fenètre pour trouver sur quel hexagone il a eu lieu
     * @param x
     * @param y
     */
    public void changeMazeBoxFromClick(double x, double y) {
        for(int i=0;i<gridWidth;i++) {
            for(int j=0;j<gridHeight;j++) {
                if (isInsideHexagon(x, y, maze.getBoxByCoords(i,j).getHexagon().getxCenter(), maze.getBoxByCoords(i,j).getHexagon().getyCenter(), this.hexagonSize)) {
                    System.out.println("Is in : " + i + " " + j);
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
     * @param x
     * @param y
     * @param xCenter
     * @param yCenter
     * @param hexagonSize
     * @return true ou false selon si le point est dans l'exagone de centre (xCenter, yCenter)
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

    public void redrawHexagonGrid() {
        this.resetHexagonGrid();
        this.mazeSateChanged();
    }

    public void saveToTextFile(String filePath) throws MazeWritingException {
        try {
            this.maze.saveToTextFile(filePath);
        } catch (MazeWritingException e) {

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

            // On initialise de manière à placer les hexagones dans le cadre du panel
            double[] pos = new double[]{hexagonSize,0.5 * Math.sqrt(3) * hexagonSize};

            for(int i=0;i<gridHeight;i++) {
                pos[1] = 2*hexagonSize*0.75*i + hexagonSize;
                for(int j=0;j<gridWidth;j++) {

                    // Selon la parité de i (coordonnée x), on décale ou pas la ligne d'hexagones (de 1/2*width) par rapport à la précédente
                    // Voir https://www.redblobgames.com/grids/hexagons/ pour l'explication géométrique
                    if (i%2 == 0) {
                        pos[0] = j*Math.sqrt(3)*hexagonSize+0.5*Math.sqrt(3)*hexagonSize + 0.5 * Math.sqrt(3) *hexagonSize;
                    } else {
                        pos[0] = j*Math.sqrt(3)*hexagonSize + 0.5 * Math.sqrt(3) *hexagonSize;
                    }

                    // On associe à chaque case son hexagone à la bonne position
                    MazeBox mazeBox = this.maze.getBoxByCoords(j,i);
                    Hexagon hexagon = new Hexagon(pos[0], pos[1], hexagonSize);
                    hexagon.setColor(mazeBox.getColor());
                    mazeBox.setHexagon(hexagon);
                }
            }
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

    public void setAppHeight(int appHeight) {
        this.appHeight = appHeight;
    }

    public void setAppWidth(int appWidth) {
        this.appWidth = appWidth;
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
}
