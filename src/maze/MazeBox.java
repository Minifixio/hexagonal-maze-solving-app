package maze;

import dijkstra.Vertex;
import model.Hexagon;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

public class MazeBox implements Vertex {
    public int x;
    public int y;
    private Maze maze;

    /**
     * Indique si la case fait partie du chemin optimal de l'origine à la case de fin
     */
    public boolean isInPath = false;

    /**
     * L'hexagone associé sur la représentation visuelle
     */
    private Hexagon hexagon;


    /**
     * Le caractère type représente le type de case du Labyrinthe avec les correspondances suivantes :
     * 'W' -> WallMazeBox
     * 'E' -> EmptyMazeBox
     * 'D' -> DepartureMazeBox
     * 'A' -> ArrivalMazeBox
     */
    private char type;

    public Color color;
    public TexturePaint texturePaint;

    public MazeBox(Maze maze, int x, int y, char type) {
        this.x = x;
        this.y = y;
        this.maze = maze;
        this.type = type;
    }


    /**
     * Fonction utilisée pour résoudre des Labyrinthe rectangulaire
     * @return les sommets voisins dans une grille avec des carrés
     */
    public List<Vertex> getSuccessorsSquareGrid() {
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

    /**
     * @return les sommets voisins dans une grille hexagonale
     */
    public List<Vertex> getSuccessors() {
        List<Vertex> successors = new ArrayList<>();

        if (this.y % 2 == 0) {
            for (int i=0; i<2; i++) {
                for (int j=-1; j<2; j++) {
                    if (
                            this.x+i >= 0
                            && this.x+i < this.maze.width
                            && this.y+j >= 0
                            && this.y+j < this.maze.height
                            && !(i==0 && j==0)
                            && !(this.maze.getBoxByCoords(this.x+i, this.y+j).getType() == 'W')
                    ) {
                        successors.add(this.maze.getBoxByCoords(this.x+i, this.y+j));
                    }
                }
            }
            if (
                    this.x-1 >= 0
                    && this.x-1 < this.maze.width
                    && this.y >= 0
                    && this.y < this.maze.height
                    && !(this.maze.getBoxByCoords(this.x-1, this.y).getType() == 'W')
            ) {
                successors.add(this.maze.getBoxByCoords(this.x-1, this.y));
            }
        } else {
            for (int i=-1; i<1; i++) {
                for (int j=-1; j<2; j++) {
                    if (
                            this.x+i >= 0
                            && this.x+i < this.maze.width
                            && this.y+j >= 0
                            && this.y+j < this.maze.height
                            && !(i==0 && j==0)
                            && !(this.maze.getBoxByCoords(this.x+i, this.y+j).getType() == 'W')
                    ) {
                        successors.add(this.maze.getBoxByCoords(this.x+i, this.y+j));
                    }
                }
            }
            if (
                    this.x+1 >= 0
                    && this.x+1 < this.maze.width
                    && this.y >= 0
                    && this.y < this.maze.height
                    && !(this.maze.getBoxByCoords(this.x+1, this.y).getType() == 'W')
            ) {
                successors.add(this.maze.getBoxByCoords(this.x+1, this.y));
            }
        }


        return successors;
    }

    public void setHexagon(Hexagon hexagon) {
        this.hexagon = hexagon;
        this.hexagon.setColor(this.getColor());
        this.hexagon.setTexturePaint(this.texturePaint);
    }

    public void setHexagonColor(Color color) {
        this.hexagon.setColor(color);
    }

    public Hexagon getHexagon() {
        return this.hexagon;
    }


    /**
     * @return Renvoi la couleur associée au type de case
     * 'W' -> WallMazeBox -> DARK_GRAY
     * 'E' -> EmptyMazeBox -> LIGHT_GRAY
     * 'D' -> DepartureMazeBox -> YELLOW
     * 'A' -> ArrivalMazeBox -> RED
     */
    public Color getColor() {
        return this.color;
    }

    public TexturePaint getPaint() {
        return this.texturePaint;
    }

    public TexturePaint initTexturePaint(String imgName) {
        File root = null;
        try {
            root = new File(Thread.currentThread().getContextClassLoader().getResource("").toURI());
        } catch (URISyntaxException e) {
            throw new RuntimeException(e);
        }
        BufferedImage myImage = null;
        try {
            myImage = ImageIO.read(new File(root, "assets/" + imgName));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return new TexturePaint(myImage, new Rectangle(this.x, this.y, 50, 50));
    }

    public void setDefaultColor() {
        this.hexagon.setColor(this.getColor());
    }

    public char getType() {
        return this.type;
    }
}
