package ui;

import javax.swing.*;

/**
 * Boutton qui permet d'effacer la construction actuelle du labyrinthe (sélection de la nature des cases) et d'en générer un nouveau, vierge
 */
public class ResetButton extends JButton {

    private final MazeApp mazeApp;

    public ResetButton(MazeApp mazeApp) {
        super("Réinitialiser le labyrinthe");
        this.mazeApp = mazeApp;
    }
}
