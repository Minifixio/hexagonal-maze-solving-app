package ui;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import static javax.swing.JOptionPane.showMessageDialog;

public class FileMenu extends JMenu implements ChangeListener {
    private final QuitMenuItem quitMenuItem;
    private final LoadFileMenuItem loadFileMenuItem;
    private MazeApp mazeApp;


    public FileMenu(MazeApp mazeApp) {
        super("Fichier");
        add(quitMenuItem = new QuitMenuItem(mazeApp));
        add(loadFileMenuItem = new LoadFileMenuItem(mazeApp));
        this.mazeApp = mazeApp;
        this.mazeApp.getMazeAppModel().setFileMenuListener(this);
    }

    @Override
    public void stateChanged(ChangeEvent e) {
        showMessageDialog(null, "Erreur");
    }
}