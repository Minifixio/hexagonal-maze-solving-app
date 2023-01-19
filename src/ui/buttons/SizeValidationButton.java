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
 * Boutton permettant de valider la taille de labyrinthe et de le regénérer avec la taille choisie
 */
public class SizeValidationButton extends JButton implements ActionListener {
    private final MazeApp mazeApp;

    public SizeValidationButton(MazeApp mazeApp) {
        super("Valider la taille");

        this.mazeApp = mazeApp;

        BufferedImage buttonImage = ImageLoader.loadImageFromName("button_size.png");
        ImageIcon buttonIcon = new ImageIcon(buttonImage);
        setIcon(buttonIcon);

        setOpaque(false);
        setContentAreaFilled(false);
        setBorderPainted(false);
        setFocusPainted(false);
        setHorizontalTextPosition(SwingConstants.CENTER);
        setFont(new Font("Palatino", Font.BOLD, 16));

        addActionListener(this);
    }
    public void actionPerformed(ActionEvent e) {
        this.mazeApp.getMazeAppModel().changeMazeSize();
    }
}