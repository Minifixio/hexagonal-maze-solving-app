package app;
import javax.swing.*;

public class MenuBar extends JMenuBar {

    private final FileMenu fileMenu;

    public MenuBar(MazeApp mazeApp) {
        super();
        add(fileMenu = new FileMenu(mazeApp));
    }
}