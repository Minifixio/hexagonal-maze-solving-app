package ui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Button permettant de générer un labyrinthe aléatoire
 */
public class RandomizeButton extends JButton implements ActionListener {
    private final MazeApp mazeApp;

    public RandomizeButton(MazeApp mazeApp) {
        super("Labyrinthe aléatoire");
        this.mazeApp = mazeApp;
        addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        this.mazeApp.getMazeAppModel().drawRandomHexagon();
    }
}
