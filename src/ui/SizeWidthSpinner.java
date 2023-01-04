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
        super(new SpinnerNumberModel(mazeApp.getMazeAppModel().getMazeDefaultWidth(), mazeApp.getMazeAppModel().getMazeMinSize(), mazeApp.getMazeAppModel().getMazeMaxWidth(), 1));
        setOpaque(false);
        this.mazeApp = mazeApp;
        this.mazeApp.getMazeAppModel().setWidthSpinnerValue((int) this.getValue());
        addChangeListener(this);
    }

    @Override
    public void stateChanged(ChangeEvent e) {
        mazeApp.getMazeAppModel().setWidthSpinnerValue((int) this.getValue());
    }

    public void refresh() {
        this.setModel(new SpinnerNumberModel(mazeApp.getMazeAppModel().getMazeDefaultWidth(), mazeApp.getMazeAppModel().getMazeMinSize(), mazeApp.getMazeAppModel().getMazeMaxWidth(), 1));
    }
}
