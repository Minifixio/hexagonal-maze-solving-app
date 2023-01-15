package ui.menu;

import ui.MazeApp;

import javax.swing.*;

public class MenuBar extends JMenuBar {

    public MenuBar(MazeApp mazeApp) {
        super();
        add(new FileMenu(mazeApp));
    }
}