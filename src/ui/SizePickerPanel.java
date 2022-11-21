package ui;

import javax.swing.*;
import java.awt.*;

/**
 * Panel contenant les outils de redimension du labyrinthe
 */
public class SizePickerPanel extends JPanel {
    private final MazeApp mazeApp;
    private final SizeValidationButton sizeValidationButton;
    private final SizeWidthSpinner sizeWidthSpinner;
    private final SizeHeightSpinner sizeHeightSpinner;
    //private final SizeLabel sizeLabel;

    public SizePickerPanel(MazeApp mazeApp) {
        setLayout(new GridLayout(2,1));

        this.mazeApp = mazeApp;
        setPreferredSize(new Dimension(50,50));

        setLayout(new GridLayout(0,3));

        //add(sizeLabel = new SizeLabel(mazeApp));
        add(sizeWidthSpinner = new SizeWidthSpinner(mazeApp));
        add(sizeHeightSpinner = new SizeHeightSpinner(mazeApp));
        add(sizeValidationButton = new SizeValidationButton(mazeApp));
        setAlignmentX(Component.CENTER_ALIGNMENT);

    }

}
