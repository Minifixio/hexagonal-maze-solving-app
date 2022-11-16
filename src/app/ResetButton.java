package app;

import javax.swing.*;

public class ResetButton extends JButton {

    private final MazeApp mazeApp;

    public ResetButton(MazeApp mazeApp) {
        super("Réinitialiser le labyrinthe");
        this.mazeApp = mazeApp;
    }
}
