package utils;

import ui.MazeApp;

import java.awt.event.*;

public class MazeEditorPanelMouseListener extends MouseAdapter implements MouseListener, MouseMotionListener {

    private final MazeApp mazeApp;

    public MazeEditorPanelMouseListener(MazeApp mazeApp) {
       this.mazeApp = mazeApp;
    }

    @Override
    public final void mousePressed(MouseEvent e) {
        this.mazeApp.getMazeAppModel().changeMazeBoxFromClick(e.getX(), e.getY());
    }
    
}
