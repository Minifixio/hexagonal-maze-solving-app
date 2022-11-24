package ui;

import javax.swing.*;

public class SizeLabel extends JLabel {
    private final MazeApp mazeApp;

    public SizeLabel(MazeApp mazeApp) {
        super("Taille : min " + mazeApp.getMazeMinSize() + " - max " + mazeApp.getMazeMaxWidth() + "x" + mazeApp.getMazeMaxHeight(), JLabel.CENTER);
        this.mazeApp = mazeApp;
    }
}
