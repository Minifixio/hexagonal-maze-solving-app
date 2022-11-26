package ui;

import javax.swing.*;
import java.awt.*;

public class InfoPanel extends JPanel {
    private MazeApp mazeApp;
    private final InfoMazeBoxesLabel infoMazeBoxesLabel;
    private final InfoFileLabel infoFileLabel;

    public InfoPanel(MazeApp mazeApp) {
        this.mazeApp = mazeApp;
        setLayout(new GridLayout(2,1));
        setBackground(Color.WHITE);
        setPreferredSize(new Dimension(200, mazeApp.getMazeAppModel().getAppHeight()));
        add(infoFileLabel = new InfoFileLabel(mazeApp));
        add(infoMazeBoxesLabel = new InfoMazeBoxesLabel(mazeApp));
    }
}
