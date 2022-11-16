package app;

import javax.swing.*;

public class SizeValidationButton extends JButton {
    private final MazeApp mazeApp;

    public SizeValidationButton(MazeApp mazeApp) {
        super("Valider la taille");
        this.mazeApp = mazeApp;
    }
}