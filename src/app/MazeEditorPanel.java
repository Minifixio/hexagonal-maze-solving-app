package app;

import javax.swing.*;
import java.awt.*;

public class MazeEditorPanel extends JPanel {

    private final MazeApp mazeApp;

    public MazeEditorPanel(MazeApp mazeApp) {
        this.mazeApp = mazeApp;
        setBackground(Color.GRAY);
        setPreferredSize(new Dimension(300,300));
    }
}
