package app;

import javax.swing.*;

public class FindPathButton extends JButton {
    private final MazeApp mazeApp;

    public FindPathButton(MazeApp mazeApp) {
        super("Trouver le plus court chemin");
        this.mazeApp = mazeApp;
    }
}
