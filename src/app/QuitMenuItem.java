package app;

import javax.swing.*;

public class QuitMenuItem extends JMenuItem {
    private final DrawingApp drawingApp;

    public QuitMenuItem(DrawingApp drawingApp) {
        super("Quit");
        this.drawingApp = drawingApp;
    }
}