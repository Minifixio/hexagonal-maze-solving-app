package ui;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.io.File;

public class FileSaveChooser extends JFileChooser {
    private MazeApp mazeApp;

    public FileSaveChooser(MazeApp mazeApp) {
        super();
        this.setCurrentDirectory(new File(System.getProperty("user.dir")));
        this.setAcceptAllFileFilterUsed(false);
        FileNameExtensionFilter filter = new FileNameExtensionFilter("Fichier txt", "txt");
        this.addChoosableFileFilter(filter);
        this.mazeApp = mazeApp;
    }

    public String openFileSave(String message) {
        this.setDialogTitle(message);
        int userSelection = this.showSaveDialog(null);
        if (userSelection == JFileChooser.APPROVE_OPTION) {
            File fileToSave = this.getSelectedFile();
            String path = fileToSave.getAbsolutePath() + ".txt";
            return path;
        } else {
            return null;
        }
    }

}
