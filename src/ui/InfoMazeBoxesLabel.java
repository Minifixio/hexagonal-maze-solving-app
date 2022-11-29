package ui;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class InfoMazeBoxesLabel extends JLabel {
    private MazeApp mazeApp;
    public InfoMazeBoxesLabel(MazeApp mazeApp) {
        super("<html><h3>A propos de l'éditeur de labyrinthe</h3><p>Cliquez sur une case pour changer son type. Il ne peut y avoir qu'une seule case d'arrivée et une seule de départ.</p> <p>On a les correspondances : <font color=\"black\"><b>Briques</b></font>=Mur <br> <font color=\"brown\"><b>Terre</b></font>=Vide <br> <font color=\"red\"><b>Rouge</b></font>=Arrivée <br> <font color=\"yellow\"><b>Jaune</b></font>=Départ </p></html>");
        setBackground(Color.WHITE);
        setBorder(new EmptyBorder(0,10,0,10));
        this.mazeApp = mazeApp;
    }
}
