package app;

import java.awt.*;
import javax.swing.*;

public class ButtonsPanel extends JPanel {
    private final SizePickerPanel sizePickerPanel;
    private final ResetButton resetButton;
    private final FindPathButton findPathButton;

    public ButtonsPanel(MazeApp mazeApp) {
        setLayout(new GridLayout(1,3));

        add(sizePickerPanel = new SizePickerPanel(mazeApp));
        add(resetButton = new ResetButton(mazeApp));
        add(findPathButton = new FindPathButton(mazeApp));
    }
}