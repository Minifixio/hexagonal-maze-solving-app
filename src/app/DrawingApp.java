package app;
import javax.swing.*;

public class DrawingApp extends JFrame {
    private final DrawingMenuBar drawingMenuBar ;

    public DrawingApp() {
        super("Labyrinth");
        setJMenuBar(drawingMenuBar = new DrawingMenuBar(this)) ;
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        pack();
        setVisible(true);
    }
}
