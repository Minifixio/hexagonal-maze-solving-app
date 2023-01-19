package ui.panels;

import ui.buttons.ButtonsPanel;
import ui.labels.InfoFileLabel;
import ui.labels.InfoMazeBoxesLabel;
import ui.MazeApp;

import javax.swing.*;
import java.awt.*;

public class InfoPanel extends JPanel {


    public InfoPanel(MazeApp mazeApp) {
        setLayout(new GridLayout(3,1));
        setOpaque(false);
        setPreferredSize(new Dimension(250, mazeApp.getMazeAppModel().getAppHeight()));
        add(new InfoFileLabel());
        add(new InfoMazeBoxesLabel());
        add(new ButtonsPanel(mazeApp));
    }
}
