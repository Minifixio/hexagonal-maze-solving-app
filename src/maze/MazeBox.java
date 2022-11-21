package maze;

import dijkstra.Vertex;
import model.Hexagon;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class MazeBox implements Vertex {
    // Les coordonnées (x,y) de la case
    public int x;
    public int y;
    private Maze maze;
    public boolean isInPath = false;

    // L'hexagone associé sur la représentation visuelle
    private Hexagon hexagon;

    // Le caractère type représente le type de case du Labyrinthe avec les correspondances suivantes :
    // 'W' -> WallMazeBox
    // 'E' -> EmptyMazeBox
    // 'D' -> DepartureMazeBox
    // 'A' -> ArrivalMazeBox
    private char type;

    public MazeBox(Maze maze, int x, int y, char type) {
        this.x = x;
        this.y = y;
        this.maze = maze;
        this.type = type;
    }

    public List<Vertex> getSuccessors() {
        List<Vertex> successors = new ArrayList<>();
        for (int i=-1; i<=1; i=i+2) {
            if (
                    this.x+i > 0
                    && this.x+i < this.maze.width
                    && !(this.maze.getBoxByCoords(this.x+i, this.y) instanceof WallMazeBox)
            ) {
                successors.add(this.maze.getBoxByCoords(this.x+i, this.y));
            }
            if (
                    this.y+i > 0
                    && this.y+i < this.maze.height
                    && !(this.maze.getBoxByCoords(this.x, this.y+i) instanceof WallMazeBox)
            ) {
                successors.add(this.maze.getBoxByCoords(this.x, this.y+i));
            }
        }
        return successors;
    }

    public void setHexagon(Hexagon hexagon) {
        this.hexagon = hexagon;
        this.hexagon.setColor(this.getColor());
    }

    public Hexagon getHexagon() {
        return this.hexagon;
    }

    // Renvoi la couleur associée au type de case :
    // 'W' -> WallMazeBox -> DARK_GRAY
    // 'E' -> EmptyMazeBox -> LIGHT_GRAY
    // 'D' -> DepartureMazeBox -> YELLOW
    // 'A' -> ArrivalMazeBox -> RED
    public Color getColor() {
        switch (this.type) {
            case 'W':
                return Color.DARK_GRAY;
            case 'E':
                return Color.LIGHT_GRAY;
            case 'D':
                return Color.YELLOW;
            case 'A':
                return Color.RED;
            default:
                return Color.DARK_GRAY;
        }
    }

    public char getType() {
        return this.type;
    }
}
