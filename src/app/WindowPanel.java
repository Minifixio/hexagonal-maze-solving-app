package app;

import javax.swing.*;
import java.awt.*;

/**
 * Fenêtre principale affichant les différents panels
 */
public class WindowPanel extends JPanel {

    private final MazeEditorPanel mazeEditorPanel;
    private final ButtonsPanel buttonsPanel;

    public WindowPanel(MazeApp mazeApp) {
        setLayout(new BorderLayout());
        add(mazeEditorPanel = new MazeEditorPanel(mazeApp), BorderLayout.CENTER);
        add(buttonsPanel = new ButtonsPanel(mazeApp), BorderLayout.SOUTH);
    }
}
