package ui.panels;

import ui.MazeApp;
import utils.MazeEditorPanelMouseListener;

import javax.swing.*;
import java.awt.*;

/**
 * Fenêtre d'édition du labyrinthe contenant les hexagones
 */
public class MazeEditorPanel extends JPanel {

    private final MazeApp mazeApp;

    public MazeEditorPanel(MazeApp mazeApp) {
        this.mazeApp = mazeApp;
        setOpaque(false);

        MazeEditorPanelMouseListener mazeEditorPanelMouseListener = new MazeEditorPanelMouseListener(mazeApp);
        addMouseListener(mazeEditorPanelMouseListener);
        addMouseMotionListener(mazeEditorPanelMouseListener);

        setPreferredSize(new Dimension(mazeApp.getMazeAppModel().getAppWidth(),mazeApp.getMazeAppModel().getAppHeight()));
    }


    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        mazeApp.getMazeAppModel().refreshHexagonGrid(g);
    }

    public void notifyForUpdates() {
        this.revalidate();
        this.repaint();
    }

}
