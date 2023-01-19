package ui.labels;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class InfoFileLabel extends JLabel {
    public InfoFileLabel() {
        super("<html>" +
                    "<div style='text-align: center;'>" +
                    "<b>Lire/sauver ses propres labyrinthes</b>" +
                    "<p>" +
                        "Vous pouvez charger vos labyrinthe depuis Fichier>Charger un fichier. " +
                        "Chacunes des lignes doivent avoir la même longueur et doivent être uniquement composée de cractères parmis 'W', 'E', 'A' ou 'D'" +
                    "</p>" +
                    "</div>" +
                    "</html>"
        );
        setForeground(Color.WHITE);
        setFont(new Font("Palatino", Font.PLAIN, 18));
        setBorder(new EmptyBorder(20,0,0,0));
    }
}
