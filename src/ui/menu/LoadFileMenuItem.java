package ui.menu;

import ui.MazeApp;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LoadFileMenuItem extends JMenuItem implements ActionListener {
    private final MazeApp mazeApp;
    private final FileLoadChooser fileLoadChooser;

    public LoadFileMenuItem(MazeApp mazeApp) {
        super("Charger un fichier");
        this.mazeApp = mazeApp;
        this.fileLoadChooser = new FileLoadChooser();
        addActionListener(this);
    }

    public void actionPerformed(ActionEvent e) {
        String filePath = this.fileLoadChooser.openFileChooser("Choisissez un fichier txt à ouvrir");
        if(filePath != null) {
            this.mazeApp.getMazeAppModel().initMazeFromFile(filePath);
        }
    }
}
