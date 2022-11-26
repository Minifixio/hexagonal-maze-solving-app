package ui;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LoadFileMenuItem extends JMenuItem implements ActionListener {
    private final MazeApp mazeApp;
    private FileLoadChooser fileLoadChooser;

    public LoadFileMenuItem(MazeApp mazeApp) {
        super("Charger un fichier");
        this.mazeApp = mazeApp;
        this.fileLoadChooser = new FileLoadChooser(mazeApp);
        addActionListener(this);
    }

    public void actionPerformed(ActionEvent e) {
        String filePath = this.fileLoadChooser.openFileChooser("Choisissez un fichier txt à ouvrir");
        if(filePath != null) {
            this.mazeApp.getMazeAppModel().initMazeFromFile(filePath);
        }
    }
}
