package ui.buttons;

import ui.*;
import ui.panels.SizePickerPanel;

import java.awt.*;
import javax.swing.*;

public class ButtonsPanel extends JPanel {
    private final SizePickerPanel sizePickerPanel;
    private final ResetButton resetButton;
    private final FindPathButton findPathButton;
    private final RandomizeButton randomizeButton;


    public ButtonsPanel(MazeApp mazeApp) {
        setLayout(new GridLayout(4,1));
        setOpaque(false);

        add(sizePickerPanel = new SizePickerPanel(mazeApp));
        add(resetButton = new ResetButton(mazeApp));
        add(randomizeButton = new RandomizeButton(mazeApp));
        add(findPathButton = new FindPathButton(mazeApp));

    }
}