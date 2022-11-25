package ui;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;

/**
 * Panel contenant les outils de redimension du labyrinthe
 */
public class SizePickerPanel extends JPanel implements ChangeListener {
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

        this.mazeApp.getMazeAppModel().setSizePanelListener(this);

    }

    @Override
    public void stateChanged(ChangeEvent e) {
        System.out.println("Redrawing SizePanel");
        this.sizeWidthSpinner.refresh();
        this.sizeHeightSpinner.refresh();
    }
}
