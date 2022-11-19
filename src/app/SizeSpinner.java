package app;

import javax.swing.*;

/**
 * Spinner permettent de choisir la valeur entière de la taille du labyrinthe
 */
public class SizeSpinner extends JSpinner {
    private final MazeApp mazeApp;

    public SizeSpinner(MazeApp mazeApp) {
        super(new SpinnerNumberModel(mazeApp.mazeDefaultSize, mazeApp.mazeMinSize, mazeApp.mazeMaxSize, 1));
        this.mazeApp = mazeApp;
    }
}
