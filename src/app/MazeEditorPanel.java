package app;

import javax.swing.*;
import java.awt.*;

/**
 * Fenêtre d'édition du labyrinthe contenant les hexagones
 */
public class MazeEditorPanel extends JPanel {

    private final MazeApp mazeApp;

    public MazeEditorPanel(MazeApp mazeApp) {
        this.mazeApp = mazeApp;
        setBackground(Color.GRAY);
        add(new Hexagon(mazeApp));
        setPreferredSize(new Dimension(300,300));
    }
}
