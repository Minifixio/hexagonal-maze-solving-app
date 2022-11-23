package ui;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Boutton qui permet d'effacer la construction actuelle du labyrinthe (sélection de la nature des cases) et d'en générer un nouveau, vierge
 */
public class ResetButton extends JButton implements ActionListener {

    private final MazeApp mazeApp;

    public ResetButton(MazeApp mazeApp) {
        super("Réinitialiser le labyrinthe");
        this.mazeApp = mazeApp;
        addActionListener(this);
    }

    public void actionPerformed(ActionEvent e) {
        this.mazeApp.getMazeAppModel().redrawHexagonGrid();
    }
}
