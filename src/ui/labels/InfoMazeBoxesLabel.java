package ui.labels;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class InfoMazeBoxesLabel extends JLabel {
    public InfoMazeBoxesLabel() {
        super("<html>" +
                    "<div style='text-align: center;'>" +
                    "<b>A propos de l'éditeur de labyrinthe</b>" +
                    "<p>" +
                        "Cliquez sur une case pour changer son type. " +
                        "La case voyageur est le départ, le drapeau est l'arrivée. Une fois une de ces cases placée, pour la changer, cliquer dessus pour la retirer puis la placer autre part." +
                    "</p> " +
                    "</div>" +
                    "</html>"
        );
        setForeground(Color.WHITE);
        setFont(new Font("Palatino", Font.PLAIN, 18));
        setBorder(new EmptyBorder(0,0,0,0));
    }
}
