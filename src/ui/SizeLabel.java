package ui;

import javax.swing.*;

public class SizeLabel extends JLabel {
    private MazeApp mazeApp;

    public SizeLabel(MazeApp mazeApp) {
        super("Taille : min " + mazeApp.mazeMinSize + " - max " + mazeApp.mazeMaxWidth + "x" + mazeApp.mazeMaxHeight, JLabel.CENTER);
        this.mazeApp = mazeApp;
    }
}
