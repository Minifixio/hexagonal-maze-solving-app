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

    public void drawPolygonGrid(int gridSize, int hexagonSize, Graphics g) {
        hexagons = new ArrayList<Hexagon>();
        double[] pos = new double[]{2*hexagonSize,2*hexagonSize};

        int k = 0;

        for(int i=0;i<gridSize;i++) {
            pos[1] = i*2*2*gridSize + gridSize;
            for(int j=0;j<gridSize;j++) {
                if (i%2 == 0) {
                    pos[0] = j*Math.sqrt(3)*hexagonSize+0.5*Math.sqrt(3)*hexagonSize;
                } else {
                    pos[0] = j*Math.sqrt(3)*hexagonSize;
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
    public void addObserver(ChangeListener listener) {
        listeners.add(listener);
    }

    public void stateChanges() {
        ChangeEvent evt = new ChangeEvent(this);
        for (ChangeListener listener : listeners) {
            listener.stateChanged(evt);
        }
    }
}
