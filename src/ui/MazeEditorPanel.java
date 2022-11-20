package ui;

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

        MazeEditorPanelMouseListener mazeEditorPanelMouseListener = new MazeEditorPanelMouseListener(mazeApp);
        addMouseListener(mazeEditorPanelMouseListener);
        addMouseMotionListener(mazeEditorPanelMouseListener);

        setPreferredSize(new Dimension(300,300));
    }

    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        mazeApp.getMazeAppModel().drawPolygonGrid(8, 20, g);
    }

}
