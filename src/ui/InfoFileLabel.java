package ui;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class InfoFileLabel extends JLabel {
    private MazeApp mazeApp;
    public InfoFileLabel(MazeApp mazeApp) {
        super("<html><h3>Lire/sauver ses propres labyrinthes</h3><p>Vous pouvez charger vos labyrinthe depuis <b>Fichier>Charger un fichier</b>. Chacunes des lignes doivent avoir la même longueur et doivent être uniquement composée de cractères parmis 'W', 'E', 'A' ou 'D'</html></p>");
        setBackground(Color.WHITE);
        setBorder(new EmptyBorder(0,10,0,10));
        this.mazeApp = mazeApp;
    }
}
