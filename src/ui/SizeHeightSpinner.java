package ui;

import ui.MazeApp;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

/**
 * Spinner permettent de choisir la valeur entière de la hauteur du labyrinthe
 */
public class SizeHeightSpinner extends JSpinner implements ChangeListener {
    private final MazeApp mazeApp;

    public SizeHeightSpinner(MazeApp mazeApp) {
        super(new SpinnerNumberModel(mazeApp.mazeDefaultHeight, mazeApp.mazeMinSize, mazeApp.mazeMaxHeight, 1));
        this.mazeApp = mazeApp;
        addChangeListener(this);
    }

    @Override
    public void stateChanged(ChangeEvent e) {
        mazeApp.getMazeAppModel().setGridHeight((int) this.getValue());
    }
}
