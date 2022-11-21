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
    public int mazeMaxSize;
    public int mazeDefaultSize;
    public int appWidth;
    public int appHeight;

    public MazeApp(int appWidth, int appHeight, int hexagonSize, int mazeMinSize, int mazeDefaultSize) {
        super("Labyrinth");

        this.appWidth = appWidth;
        this.appHeight = appHeight;
        this.mazeMinSize = mazeMinSize;
        this.mazeMaxSize = (int) (appWidth/(Math.sqrt(3)*hexagonSize));
        this.mazeDefaultSize = mazeDefaultSize;

        this.mazeAppModel = new MazeAppModel(mazeDefaultSize, hexagonSize);

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
