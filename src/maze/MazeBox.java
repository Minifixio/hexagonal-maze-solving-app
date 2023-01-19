package maze;

import dijkstra.Vertex;
import model.Hexagon;
import utils.ImageLoader;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;

public class MazeBox implements Vertex {
    public int x;
    public int y;
    private final Maze maze;

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
    private final char type;
    private TexturePaint texturePaint;
    private TexturePaint defaultTexturePaint;

    public MazeBox(Maze maze, int x, int y, char type) {
        this.x = x;
        this.y = y;
        this.maze = maze;
        this.type = type;
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
        this.hexagon.setTexturePaint(this.texturePaint);
    }

    public Hexagon getHexagon() {
        return this.hexagon;
    }

    public void setTexturePaint(String imgName) {
        BufferedImage textureImage = ImageLoader.loadImageFromName(imgName);
        this.texturePaint = new TexturePaint(textureImage, new Rectangle(this.x, this.y, 50, 50));
    }

    public void setHexagonTexturePaint(String imgName) {
        this.setTexturePaint(imgName);
        this.hexagon.setTexturePaint(this.texturePaint);
    }

    public void setHexagonDefaultTexturePaint() {
        this.hexagon.setTexturePaint(this.defaultTexturePaint);
    }

    public char getType() {
        return this.type;
    }

    public void setDefaultTexturePaint(String imgName) {
        BufferedImage defaultTexturePaintImage = ImageLoader.loadImageFromName(imgName);
        this.defaultTexturePaint = new TexturePaint(defaultTexturePaintImage, new Rectangle(this.x, this.y, 50, 50));
        this.texturePaint = this.defaultTexturePaint;
    }
}
