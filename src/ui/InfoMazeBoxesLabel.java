package ui;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class InfoMazeBoxesLabel extends JLabel {
    private MazeApp mazeApp;
    public InfoMazeBoxesLabel(MazeApp mazeApp) {
        super("<html>" +
                    "<div style='text-align: center;'>" +
                    "<b>A propos de l'éditeur de labyrinthe</b>" +
                    "<p>" +
                        "Cliquez sur une case pour changer son type. " +
                        "Il ne peut y avoir qu'une seule case d'arrivée et une seule de départ." +
                    "</p> " +
                    "</div>" +
                    "</html>"
        );
        setForeground(Color.WHITE);
        setFont(new Font("Palatino", Font.PLAIN, 18));
        setBorder(new EmptyBorder(0,0,0,0));
        this.mazeApp = mazeApp;
    }
}
