package ui;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LoadFileMenuItem extends JMenuItem implements ActionListener {
    private final MazeApp mazeApp;
    private FileChooser fileChooser;

    public LoadFileMenuItem(MazeApp mazeApp) {
        super("Charger un fichier");
        this.mazeApp = mazeApp;
        this.fileChooser = new FileChooser(mazeApp);
        addActionListener(this);
    }

    public void actionPerformed(ActionEvent e) {
        this.fileChooser.openFileChooser();
    }
}
