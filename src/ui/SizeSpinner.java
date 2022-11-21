package ui;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Spinner permettent de choisir la valeur entière de la taille du labyrinthe
 */
public class SizeSpinner extends JSpinner implements ChangeListener {
    private final MazeApp mazeApp;

    public SizeSpinner(MazeApp mazeApp) {
        super(new SpinnerNumberModel(mazeApp.mazeDefaultSize, mazeApp.mazeMinSize, mazeApp.mazeMaxSize, 1));
        this.mazeApp = mazeApp;
        addChangeListener(this);
    }

    @Override
    public void stateChanged(ChangeEvent e) {
        mazeApp.getMazeAppModel().setGridSize((int) this.getValue());
    }
}
