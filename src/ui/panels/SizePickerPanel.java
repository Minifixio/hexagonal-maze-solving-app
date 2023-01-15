package ui.panels;

import ui.MazeApp;
import ui.buttons.SizeValidationButton;
import ui.buttons.SizeWidthSpinner;
import ui.buttons.SizeHeightSpinner;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;

/**
 * Panel contenant les outils de redimension du labyrinthe
 */
public class SizePickerPanel extends JPanel implements ChangeListener {
    private final SizeWidthSpinner sizeWidthSpinner;
    private final SizeHeightSpinner sizeHeightSpinner;

    public SizePickerPanel(MazeApp mazeApp) {

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
        add(new SizeValidationButton(mazeApp));

        setAlignmentX(Component.CENTER_ALIGNMENT);

        mazeApp.getMazeAppModel().setSizePanelListener(this);

    }

    @Override
    public void stateChanged(ChangeEvent e) {
        System.out.println("Redrawing SizePanel");
        this.sizeWidthSpinner.refresh();
        this.sizeHeightSpinner.refresh();
    }
}
