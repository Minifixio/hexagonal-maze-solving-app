package ui;

import javax.swing.*;

public class SizeLabel extends JLabel {
    private final MazeApp mazeApp;

    public SizeLabel(MazeApp mazeApp) {
        super("Taille : min " + mazeApp.getMazeAppModel().getMazeMinSize() + " - max " + mazeApp.getMazeAppModel().getMazeMaxWidth() + "x" + mazeApp.getMazeAppModel().getMazeMaxHeight(), JLabel.CENTER);
        this.mazeApp = mazeApp;
    }
}
