package ui.menu;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.io.File;

public class FileLoadChooser extends JFileChooser {

    public FileLoadChooser() {
        super();
        this.setCurrentDirectory(new File(System.getProperty("user.dir")));
        this.setAcceptAllFileFilterUsed(false);
        FileNameExtensionFilter filter = new FileNameExtensionFilter("Fichier txt", "txt");
        this.addChoosableFileFilter(filter);
    }

    public String openFileChooser(String message) {
        this.setDialogTitle(message);
        int res = this.showOpenDialog(null);
        if (res == JFileChooser.APPROVE_OPTION) {
            System.out.println(this.getSelectedFile().getPath());
            return this.getSelectedFile().getPath();
        } else {
            return null;
        }
    }

}
