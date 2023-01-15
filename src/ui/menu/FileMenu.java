package ui.menu;

import ui.MazeApp;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import static javax.swing.JOptionPane.showMessageDialog;

public class FileMenu extends JMenu implements ChangeListener {


    public FileMenu(MazeApp mazeApp) {
        super("Fichier");
        add(new LoadFileMenuItem(mazeApp));
        add(new SaveFileMenuItem(mazeApp));
        mazeApp.getMazeAppModel().setFileMenuListener(this);
    }

    @Override
    public void stateChanged(ChangeEvent e) {
        showMessageDialog(null, "Erreur : veuillez vérifier que l'emplacement/format du fichier est correct");
    }
}