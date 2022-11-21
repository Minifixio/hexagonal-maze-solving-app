package model;

import maze.Maze;

import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.util.ArrayList;

public class MazeAppModel {
    private Maze maze;
    private ArrayList<ChangeListener> listeners = new ArrayList<ChangeListener>();
    private ArrayList<Hexagon> hexagons = new ArrayList<Hexagon>();
    private int gridWidth;
    private int gridHeight;
    private int hexagonSize;

    public MazeAppModel(int gridWidth, int gridHeight, int hexagonSize) {
        this.gridWidth = gridWidth;
        this.gridHeight = gridHeight;
        this.hexagonSize = hexagonSize;
    }
    public void drawPolygonGrid(Graphics g) {
        System.out.println("Drawing polygon grid with size : width "  + this.gridWidth + " height " + this.gridHeight);

        hexagons = new ArrayList<Hexagon>();
        // On initialise de manière à placer les hexagones dans le cadre du panel
        double[] pos = new double[]{hexagonSize,hexagonSize};

        int k = 0;

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

                Color color = Color.BLACK;
                if (k%3 == 0) {
                    color = Color.BLUE;
                } else if (k%3 == 1) {
                    color = Color.MAGENTA;
                } else {
                    color = Color.YELLOW;
                }
                k = k+1;

                Hexagon h = new Hexagon(pos[0], pos[1], hexagonSize, color);
                hexagons.add(h);
                h.paint(g);
            }
        }
    }

    public void setGridWidth(int gridWidth) {
        this.gridWidth = gridWidth;
    }
    public void setGridHeight(int gridHeight) {
        this.gridHeight = gridHeight;
    }

    public void redrawHexagonGrid() {
        this.stateChanges();
    }

    public void addObserver(ChangeListener listener) {
        listeners.add(listener);
    }

    public void stateChanges() {
        System.out.println("State changes");
        ChangeEvent evt = new ChangeEvent(this);
        for (ChangeListener listener : listeners) {
            listener.stateChanged(evt);
        }
    }
}
