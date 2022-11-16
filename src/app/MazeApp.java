package app;
import javax.swing.*;

public class MazeApp extends JFrame {
    private final MenuBar MenuBar;
    private final WindowPanel windowPanel;
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
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        pack();
        setVisible(true);
    }
}
