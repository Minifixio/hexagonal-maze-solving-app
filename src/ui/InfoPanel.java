package ui;

import javax.swing.*;
import java.awt.*;

public class InfoPanel extends JPanel {
    private MazeApp mazeApp;
    private final InfoMazeBoxesLabel infoMazeBoxesLabel;
    private final InfoFileLabel infoFileLabel;
    private final ButtonsPanel buttonsPanel;


    public InfoPanel(MazeApp mazeApp) {
        this.mazeApp = mazeApp;
        setLayout(new GridLayout(3,1));
        setOpaque(false);
        setPreferredSize(new Dimension(250, mazeApp.getMazeAppModel().getAppHeight()));
        add(infoFileLabel = new InfoFileLabel(mazeApp));
        add(infoMazeBoxesLabel = new InfoMazeBoxesLabel(mazeApp));
        add(buttonsPanel = new ButtonsPanel(mazeApp));
    }
}
