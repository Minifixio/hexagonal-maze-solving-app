package ui.buttons;

import ui.*;
import ui.panels.SizePickerPanel;

import java.awt.*;
import javax.swing.*;

public class ButtonsPanel extends JPanel {


    public ButtonsPanel(MazeApp mazeApp) {
        setLayout(new GridLayout(4,1));
        setOpaque(false);

        add(new SizePickerPanel(mazeApp));
        add(new ResetButton(mazeApp));
        add(new RandomizeButton(mazeApp));
        add(new FindPathButton(mazeApp));
    }
}