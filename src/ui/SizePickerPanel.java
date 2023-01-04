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
        this.mazeApp = mazeApp;

        setLayout(new GridLayout(2,1));
        setOpaque(false);
        setPreferredSize(new Dimension(50,100));
        setLayout(new GridLayout(2,0));

        JPanel sizeButtonsPanel = new JPanel();
        sizeButtonsPanel.setOpaque(false);
        sizeButtonsPanel.setLayout(new GridLayout(0,2));
        sizeButtonsPanel.add(sizeWidthSpinner = new SizeWidthSpinner(mazeApp));
        sizeButtonsPanel.add(sizeHeightSpinner = new SizeHeightSpinner(mazeApp));
        add(sizeButtonsPanel);
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
