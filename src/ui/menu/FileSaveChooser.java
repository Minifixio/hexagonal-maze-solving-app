package ui.menu;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.io.File;

public class FileSaveChooser extends JFileChooser {

    public FileSaveChooser() {
        super();
        this.setCurrentDirectory(new File(System.getProperty("user.dir")));
        this.setAcceptAllFileFilterUsed(false);
        FileNameExtensionFilter filter = new FileNameExtensionFilter("Fichier txt", "txt");
        this.addChoosableFileFilter(filter);
    }

    public String openFileSave(String message) {
        this.setDialogTitle(message);
        int userSelection = this.showSaveDialog(null);
        if (userSelection == JFileChooser.APPROVE_OPTION) {
            File fileToSave = this.getSelectedFile();
            return fileToSave.getAbsolutePath() + ".txt";
        } else {
            return null;
        }
    }

}
