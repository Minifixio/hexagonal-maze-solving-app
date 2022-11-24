package ui;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

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
