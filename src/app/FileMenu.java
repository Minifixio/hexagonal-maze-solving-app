package app ;
import javax.swing.*;

public class FileMenu extends JMenu {
    private final QuitMenuItem quitMenuItem;

    public FileMenu(MazeApp mazeApp) {
        super("Fichier");
        add(quitMenuItem = new QuitMenuItem(mazeApp));
    }

}