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
    private final SizeLabel sizeLabel;

    public SizePickerPanel(MazeApp mazeApp) {
        setLayout(new GridLayout(2,1));

        this.mazeApp = mazeApp;
        setPreferredSize(new Dimension(50,50));

        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridwidth = GridBagConstraints.REMAINDER;
        gbc.anchor = GridBagConstraints.CENTER;
        gbc.fill = GridBagConstraints.HORIZONTAL;

        add(sizeLabel = new SizeLabel(mazeApp));
        add(sizeSpinner = new SizeSpinner(mazeApp));
        add(sizeValidationButton = new SizeValidationButton(mazeApp), gbc);
        setAlignmentX(Component.CENTER_ALIGNMENT);

    }

}
