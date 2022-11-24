package ui;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Boutton permettant de valider la taille de labyrinthe et de le regénérer avec la taille choisie
 */
public class SizeValidationButton extends JButton implements ActionListener {
    private final MazeApp mazeApp;

    public SizeValidationButton(MazeApp mazeApp) {
        super("Valider la taille");
        this.mazeApp = mazeApp;
        addActionListener(this);
    }

    public void actionPerformed(ActionEvent e) {
        this.mazeApp.getMazeAppModel().changeMazeSize();
    }
}