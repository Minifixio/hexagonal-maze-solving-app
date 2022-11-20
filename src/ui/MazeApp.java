package ui;
import model.MazeAppModel;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class MazeApp extends JFrame implements ChangeListener {
    private final MenuBar MenuBar;
    private final WindowPanel windowPanel;

    private MazeAppModel mazeAppModel = new MazeAppModel();
    public int mazeMinSize;
    public int mazeMaxSize;
    public int mazeDefaultSize;

    public MazeApp(int mazeMinSize, int mazeMaxSize, int mazeDefaultSize) {
        super("Labyrinth");

        this.mazeMinSize = mazeMinSize;
        this.mazeMaxSize = mazeMaxSize;
        this.mazeDefaultSize = mazeDefaultSize;

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

    }
}
