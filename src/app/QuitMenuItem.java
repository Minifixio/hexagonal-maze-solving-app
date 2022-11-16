package app;

import javax.swing.*;

public class QuitMenuItem extends JMenuItem {
    private final MazeApp mazeApp;

    public QuitMenuItem(MazeApp mazeApp) {
        super("Quit");
        this.mazeApp = mazeApp;
    }
}