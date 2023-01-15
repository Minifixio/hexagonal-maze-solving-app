package ui.buttons;

import ui.MazeApp;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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
        File root;
        try {
            root = new File(Objects.requireNonNull(Thread.currentThread().getContextClassLoader().getResource("")).toURI());
        } catch (URISyntaxException e) {
            throw new RuntimeException(e);
        }
        File background = new File(root, "assets/button_size.png");
        ImageIcon icon = new ImageIcon(background.toURI().getPath());
        setIcon(icon);

        setOpaque(false);
        setContentAreaFilled(false);
        setBorderPainted(false);
        setFocusPainted(false);
        setHorizontalTextPosition(SwingConstants.CENTER);
        setFont(new Font("Palatino", Font.BOLD, 16));

        this.mazeApp = mazeApp;
        addActionListener(this);
    }
    public void actionPerformed(ActionEvent e) {
        this.mazeApp.getMazeAppModel().changeMazeSize();
    }
}