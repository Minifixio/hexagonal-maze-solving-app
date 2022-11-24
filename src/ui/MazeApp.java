package ui;
import model.MazeAppModel;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class MazeApp extends JFrame implements ChangeListener {
    private final MenuBar MenuBar;
    private final WindowPanel windowPanel;
    private MazeAppModel mazeAppModel;
    private int mazeMinSize;
    private int mazeMaxWidth;
    private int mazeMaxHeight;
    private int mazeDefaultWidth;
    private int mazeDefaultHeight;
    private int appWidth;
    private int appHeight;

    public MazeApp(int appWidth, int appHeight, int hexagonSize, int mazeMinSize, int mazeDefaultWidth, int mazeDefaultHeight) {
        super("Labyrinth");

        this.appWidth = appWidth;
        this.appHeight = appHeight;
        this.mazeMinSize = mazeMinSize;

        // On calcule les dimension maximales pour que le layrinthe reste dans la fenêtre
        this.mazeMaxWidth = (int) (appWidth/(Math.sqrt(3)*hexagonSize)) - 1;
        this.mazeMaxHeight = (appHeight/(2*hexagonSize)) - 1;
        this.mazeDefaultWidth = mazeDefaultWidth;
        this.mazeDefaultHeight = mazeDefaultHeight;

        this.mazeAppModel = new MazeAppModel(mazeDefaultWidth, mazeDefaultHeight, hexagonSize);

        setJMenuBar(MenuBar = new MenuBar(this));
        setContentPane(windowPanel = new WindowPanel(this));

        mazeAppModel.addObserver(this);
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

    public int getMazeMinSize() {
        return mazeMinSize;
    }

    public int getMazeMaxWidth() {
        return mazeMaxWidth;
    }

    public int getMazeMaxHeight() {
        return mazeMaxHeight;
    }

    public int getMazeDefaultWidth() {
        return mazeDefaultWidth;
    }

    public int getMazeDefaultHeight() {
        return mazeDefaultHeight;
    }

    public int getAppWidth() {
        return appWidth;
    }

    public int getAppHeight() {
        return appHeight;
    }

}
