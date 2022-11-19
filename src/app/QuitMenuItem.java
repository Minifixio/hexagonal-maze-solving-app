package app;

import javax.swing.*;

/**
 * Contient le boutton qui permet de fermer la fenêtre
 */
public class QuitMenuItem extends JMenuItem {
    private final MazeApp mazeApp;

    public QuitMenuItem(MazeApp mazeApp) {
        super("Quit");
        this.mazeApp = mazeApp;
    }
}