package ui;

import javax.swing.* ;
import java.awt.*;

/**
 * Panel contenant le bouton de résolution
 */
public class SolvePanel extends JPanel {
    private final MazeApp mazeApp ;

    public SolvePanel(MazeApp mazeApp) {
        this.mazeApp = mazeApp ;
        setPreferredSize(new Dimension(100,100)) ;
    }
}