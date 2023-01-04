package ui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.net.URISyntaxException;

public class FindPathButton extends JButton implements ActionListener {
    private final MazeApp mazeApp;

    public FindPathButton(MazeApp mazeApp) {
        super("Résoudre");
        File root = null;
        try {
            root = new File(Thread.currentThread().getContextClassLoader().getResource("").toURI());
        } catch (URISyntaxException e) {
            throw new RuntimeException(e);
        }
        File background = new File(root, "assets/button_default.png");
        ImageIcon icon = new ImageIcon(background.toURI().getPath());
        setIcon(icon);

        setOpaque(false);
        setContentAreaFilled(false);
        setBorderPainted(false);
        setFocusPainted(false);
        setHorizontalTextPosition(SwingConstants.CENTER);
        setFont(new Font("Palatino", Font.BOLD, 18));

        this.mazeApp = mazeApp;
        addActionListener(this);
    }

    public void actionPerformed(ActionEvent e) {
        this.mazeApp.getMazeAppModel().solveMaze();
    }
}
