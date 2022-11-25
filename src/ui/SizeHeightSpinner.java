package ui;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

/**
 * Spinner permettent de choisir la valeur entière de la hauteur du labyrinthe
 */
public class SizeHeightSpinner extends JSpinner implements ChangeListener {
    private final MazeApp mazeApp;

    public SizeHeightSpinner(MazeApp mazeApp) {
        super(new SpinnerNumberModel(mazeApp.getMazeAppModel().getMazeDefaultHeight(), mazeApp.getMazeAppModel().getMazeMinSize(), mazeApp.getMazeAppModel().getMazeMaxHeight(), 1));
        this.mazeApp = mazeApp;
        this.mazeApp.getMazeAppModel().setHeightSpinnerValue((int) this.getValue());
        addChangeListener(this);
    }

    @Override
    public void stateChanged(ChangeEvent e) {
        mazeApp.getMazeAppModel().setHeightSpinnerValue((int) this.getValue());
    }

    public void refresh() {
        this.setModel(new SpinnerNumberModel(mazeApp.getMazeAppModel().getMazeDefaultHeight(), mazeApp.getMazeAppModel().getMazeMinSize(), mazeApp.getMazeAppModel().getMazeMaxHeight(), 1));
    }
}
