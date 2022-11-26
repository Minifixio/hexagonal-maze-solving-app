package ui;

import javax.swing.*;
import java.awt.*;

/**
 * Fenêtre principale affichant les différents panels
 */
public class WindowPanel extends JPanel {

    private final MazeEditorPanel mazeEditorPanel;
    private final ButtonsPanel buttonsPanel;
    private final InfoPanel infoPanel;

    public WindowPanel(MazeApp mazeApp) {
        setLayout(new BorderLayout());
        add(mazeEditorPanel = new MazeEditorPanel(mazeApp), BorderLayout.CENTER);
        add(buttonsPanel = new ButtonsPanel(mazeApp), BorderLayout.SOUTH);
        add(infoPanel = new InfoPanel(mazeApp), BorderLayout.EAST);
    }

    public void notifyForUpdates() {
        mazeEditorPanel.notifyForUpdates();
    }
}
