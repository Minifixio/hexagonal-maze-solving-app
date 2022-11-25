package ui;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.io.File;

public class FileChooser extends JFileChooser {
    private MazeApp mazeApp;

    public FileChooser(MazeApp mazeApp) {
        super();
        this.setCurrentDirectory(new File(System.getProperty("user.dir")));
        this.setDialogTitle("Choisissez un fichier txt à ouvrir");
        this.setAcceptAllFileFilterUsed(false);
        FileNameExtensionFilter filter = new FileNameExtensionFilter("Fichier txt", "txt");
        this.addChoosableFileFilter(filter);
        this.mazeApp = mazeApp;
    }

    public void openFileChooser() {
        int res = this.showOpenDialog(null);
        if (res == JFileChooser.APPROVE_OPTION) {
            System.out.println(this.getSelectedFile().getPath());
            this.mazeApp.getMazeAppModel().initMazeFromFile(this.getSelectedFile().getPath());
        }
    }
}
