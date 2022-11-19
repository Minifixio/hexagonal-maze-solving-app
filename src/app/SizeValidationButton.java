package app;

import javax.swing.*;

/**
 * Boutton permettant de valider la taille de labyrinthe et de le regénérer avec la taille choisie
 */
public class SizeValidationButton extends JButton {
    private final MazeApp mazeApp;

    public SizeValidationButton(MazeApp mazeApp) {
        super("Valider la taille");
        this.mazeApp = mazeApp;
    }
}