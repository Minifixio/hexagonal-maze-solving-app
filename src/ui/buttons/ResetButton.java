package ui.buttons;

import ui.MazeApp;
import utils.ImageLoader;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.net.URISyntaxException;
import java.util.Objects;

/**
 * Bouton qui permet d'effacer la construction actuelle du labyrinthe (sélection de la nature des cases) et d'en générer un nouveau, vierge
 */
public class ResetButton extends JButton implements ActionListener {

    private final MazeApp mazeApp;

    public ResetButton(MazeApp mazeApp) {
        super("Réinitialiser");

        this.mazeApp = mazeApp;

        BufferedImage buttonImage = ImageLoader.loadImageFromName("button_default.png");
        ImageIcon buttonIcon = new ImageIcon(buttonImage);
        setIcon(buttonIcon);

        setOpaque(false);
        setContentAreaFilled(false);
        setBorderPainted(false);
        setFocusPainted(false);
        setHorizontalTextPosition(SwingConstants.CENTER);
        setFont(new Font("Palatino", Font.BOLD, 18));

        addActionListener(this);
    }

    public void actionPerformed(ActionEvent e) {
        this.mazeApp.getMazeAppModel().redrawHexagonGrid();
    }
}
