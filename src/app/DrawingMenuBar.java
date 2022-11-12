package app;
import javax.swing.* ;

public class DrawingMenuBar extends JMenuBar {

    private final FileMenu fileMenu ;

    public DrawingMenuBar(DrawingApp drawingApp)
    {
        super();
        add(fileMenu = new FileMenu(drawingApp));
    }
}