package ui;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class FindPathButton extends JButton implements ActionListener {
    private final MazeApp mazeApp;

    public FindPathButton(MazeApp mazeApp) {
        super("Trouver le plus court chemin");
        this.mazeApp = mazeApp;
        addActionListener(this);
    }

    public void actionPerformed(ActionEvent e) {
        this.mazeApp.getMazeAppModel().solveMaze();
    }
}
