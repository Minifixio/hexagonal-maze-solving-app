package ui;
import model.MazeAppModel;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class MazeApp extends JFrame implements ChangeListener {
    private final MenuBar MenuBar;
    private final WindowPanel windowPanel;

    private MazeAppModel mazeAppModel;
    public int mazeMinSize;
    public int mazeMaxWidth;

    public int mazeMaxHeight;

    public int mazeDefaultWidth;
    public int mazeDefaultHeight;
    public int appWidth;
    public int appHeight;

    public MazeApp(int appWidth, int appHeight, int hexagonSize, int mazeMinSize, int mazeDefaultWidth, int mazeDefaultHeight) {
        super("Labyrinth");

        this.appWidth = appWidth;
        this.appHeight = appHeight;
        this.mazeMinSize = mazeMinSize;
        this.mazeMaxWidth = (int) (appWidth/(Math.sqrt(3)*hexagonSize)) - 1;
        this.mazeMaxHeight = (int) (appHeight/(2*hexagonSize)) - 1;
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

    public void setMazeAppModel(MazeAppModel mazeAppModel) {
        this.mazeAppModel = mazeAppModel;
    }

    @Override
    public void stateChanged(ChangeEvent e) {
        windowPanel.notifyForUpdates();
    }
}
