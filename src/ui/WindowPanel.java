package ui;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;

/**
 * Fenêtre principale affichant les différents panels
 */
public class WindowPanel extends JPanel implements ChangeListener {

    private final MazeEditorPanel mazeEditorPanel;
    private final ButtonsPanel buttonsPanel;
    private final InfoPanel infoPanel;

    public WindowPanel(MazeApp mazeApp) {
        setLayout(new BorderLayout());
        mazeApp.getMazeAppModel().setPathFoundListener(this);
        add(mazeEditorPanel = new MazeEditorPanel(mazeApp), BorderLayout.CENTER);
        add(buttonsPanel = new ButtonsPanel(mazeApp), BorderLayout.SOUTH);
        add(infoPanel = new InfoPanel(mazeApp), BorderLayout.EAST);
    }

    public void notifyForUpdates() {
        mazeEditorPanel.notifyForUpdates();
    }
    @Override
    public void stateChanged(ChangeEvent e) {
        JOptionPane.showMessageDialog(this, "Pas de chemin trouvé");
    }
}
