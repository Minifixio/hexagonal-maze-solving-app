package ui.panels;

import ui.MazeApp;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.Objects;

/**
 * Fenêtre principale affichant les différents panels
 */
public class WindowPanel extends JPanel implements ChangeListener {

    private final MazeEditorPanel mazeEditorPanel;
    private final BufferedImage background;

    public WindowPanel(MazeApp mazeApp) {
        setLayout(new BorderLayout());

        File root;
        try {
            root = new File(Objects.requireNonNull(Thread.currentThread().getContextClassLoader().getResource("")).toURI());
        } catch (URISyntaxException e) {
            throw new RuntimeException(e);
        }
        try {
            this.background = ImageIO.read(new File(root, "ressources/background1.png"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        mazeApp.getMazeAppModel().setPathFoundListener(this);
        add(mazeEditorPanel = new MazeEditorPanel(mazeApp), BorderLayout.CENTER);
        add(new InfoPanel(mazeApp), BorderLayout.EAST);
    }

    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(this.background, 0, 0, null);
    }

    public void notifyForUpdates() {
        mazeEditorPanel.notifyForUpdates();
    }
    @Override
    public void stateChanged(ChangeEvent e) {
        JOptionPane.showMessageDialog(this, "Pas de chemin trouvé");
    }
}
