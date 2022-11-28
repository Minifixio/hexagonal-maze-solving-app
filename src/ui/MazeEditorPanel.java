package ui;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
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
        setBackground(Color.GRAY);

        MazeEditorPanelMouseListener mazeEditorPanelMouseListener = new MazeEditorPanelMouseListener(mazeApp);
        addMouseListener(mazeEditorPanelMouseListener);
        addMouseMotionListener(mazeEditorPanelMouseListener);

        File root = null;
        try {
            root = new File(Thread.currentThread().getContextClassLoader().getResource("").toURI());
        } catch (URISyntaxException e) {
            throw new RuntimeException(e);
        }
        try {
            this.background = ImageIO.read(new File(root, "assets/background1.png"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        setPreferredSize(new Dimension(mazeApp.getMazeAppModel().getAppWidth(),mazeApp.getMazeAppModel().getAppHeight()));
    }

    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(this.background, 0, 0, null);
        mazeApp.getMazeAppModel().refreshHexagonGrid(g);
    }

    public void notifyForUpdates() {
        this.revalidate();
        this.repaint();
    }

}
