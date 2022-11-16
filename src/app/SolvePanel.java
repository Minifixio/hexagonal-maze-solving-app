package app;

import javax.swing.* ;
import java.awt.*;

public class SolvePanel extends JPanel {
    private final MazeApp mazeApp ;

    public SolvePanel(MazeApp mazeApp) {
        this.mazeApp = mazeApp ;
        setPreferredSize(new Dimension(100,100)) ;
    }
}