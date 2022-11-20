package ui;

import javax.swing.*;
import java.awt.*;

/**
 * Panel contenant les outils de redimension du labyrinthe
 */
public class SizePickerPanel extends JPanel {
    private final MazeApp mazeApp;
    private final SizeValidationButton sizeValidationButton;
    private final SizeSpinner sizeSpinner;

    public SizePickerPanel(MazeApp mazeApp) {
        setLayout(new GridLayout(2,1));

        this.mazeApp = mazeApp;
        setPreferredSize(new Dimension(100,100));
        add(sizeSpinner = new SizeSpinner(mazeApp));
        add(sizeValidationButton = new SizeValidationButton(mazeApp));

    }

}
