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
    public final void mouseClicked(MouseEvent e) {
        System.out.println("Mouse Clicked");
        this.mazeApp.getMazeAppModel().findMazeBoxFromClick(e.getX(), e.getY());
    }
}
