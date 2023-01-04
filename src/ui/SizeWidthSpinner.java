package ui;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.plaf.basic.BasicSpinnerUI;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;

/**
 * Spinner permettent de choisir la valeur entière de la largeur du labyrinthe
 */
public class SizeWidthSpinner extends JSpinner implements ChangeListener {
    private final MazeApp mazeApp;

    public SizeWidthSpinner(MazeApp mazeApp) {
        super(new SpinnerNumberModel(mazeApp.getMazeAppModel().getMazeDefaultWidth(), mazeApp.getMazeAppModel().getMazeMinSize(), mazeApp.getMazeAppModel().getMazeMaxWidth(), 1));
        this.mazeApp = mazeApp;
        this.mazeApp.getMazeAppModel().setWidthSpinnerValue((int) this.getValue());
        addChangeListener(this);

        getEditor().getComponent(0).setBackground(Color.GRAY);
        setFont(new Font("Palatino", Font.BOLD, 14));
    }
    @Override
    public void stateChanged(ChangeEvent e) {
        mazeApp.getMazeAppModel().setWidthSpinnerValue((int) this.getValue());
    }

    public void refresh() {
        this.setModel(new SpinnerNumberModel(mazeApp.getMazeAppModel().getMazeDefaultWidth(), mazeApp.getMazeAppModel().getMazeMinSize(), mazeApp.getMazeAppModel().getMazeMaxWidth(), 1));
    }
}


