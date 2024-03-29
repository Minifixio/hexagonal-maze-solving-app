package ui.menu;

import ui.MazeApp;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SaveFileMenuItem extends JMenuItem implements ActionListener {
    private final MazeApp mazeApp;
    private final FileSaveChooser fileSaveChooser;

    public SaveFileMenuItem(MazeApp mazeApp) {
        super("Enregistrer dans un fichier");
        this.mazeApp = mazeApp;
        this.fileSaveChooser = new FileSaveChooser();
        addActionListener(this);
    }

    public void actionPerformed(ActionEvent e) {
        String filePath = this.fileSaveChooser.openFileSave("Choisir le fichier destination");
        if(filePath != null) {
            this.mazeApp.getMazeAppModel().saveToTextFile(filePath);
        }
    }
}
