package ui;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

/**
 * Spinner permettent de choisir la valeur entière de la largeur du labyrinthe
 */
public class SizeWidthSpinner extends JSpinner implements ChangeListener {
    private final MazeApp mazeApp;

    public SizeWidthSpinner(MazeApp mazeApp) {
        super(new SpinnerNumberModel(mazeApp.mazeDefaultWidth, mazeApp.mazeMinSize, mazeApp.mazeMaxWidth, 1));
        this.mazeApp = mazeApp;
        addChangeListener(this);
    }

    @Override
    public void stateChanged(ChangeEvent e) {
        mazeApp.getMazeAppModel().setGridWidth((int) this.getValue());
    }
}
