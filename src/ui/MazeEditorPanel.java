package ui;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;

/**
 * Fenêtre d'édition du labyrinthe contenant les hexagones
 */
public class MazeEditorPanel extends JPanel {

    private final MazeApp mazeApp;
    private BufferedImage background;

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
