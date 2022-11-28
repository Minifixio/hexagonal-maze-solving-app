package ui;
import model.MazeAppModel;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class MazeApp extends JFrame implements ChangeListener {
    private final MenuBar MenuBar;
    private final WindowPanel windowPanel;
    private MazeAppModel mazeAppModel;

    public MazeApp(int appWidth, int appHeight, int hexagonSize, int mazeMinSize, int mazeDefaultWidth, int mazeDefaultHeight) {
        super("Labyrinth");

        // On calcule les dimension maximales pour que le layrinthe reste dans la fenêtre
        int mazeMaxWidth = (int) (appWidth/(Math.sqrt(3)*hexagonSize)) - 1;
        int mazeMaxHeight = (appHeight/(2*hexagonSize)) - 1;

        this.mazeAppModel = new MazeAppModel(mazeDefaultWidth, mazeDefaultHeight, hexagonSize, appWidth, appHeight);
        this.mazeAppModel.setMazeMaxWidth(mazeMaxWidth);
        this.mazeAppModel.setMazeMaxHeight(mazeMaxHeight);
        this.mazeAppModel.setMazeMinSize(mazeMinSize);
        this.mazeAppModel.setMazeDefaultHeight(mazeDefaultHeight);
        this.mazeAppModel.setMazeDefaultWidth(mazeDefaultWidth);

        setJMenuBar(MenuBar = new MenuBar(this));
        setContentPane(windowPanel = new WindowPanel(this));

        mazeAppModel.setMazeAppListener(this);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        pack();
        setVisible(true);
    }

    public MazeAppModel getMazeAppModel() {
        return mazeAppModel;
    }

    @Override
    public void stateChanged(ChangeEvent e) {
        windowPanel.notifyForUpdates();
    }

}
