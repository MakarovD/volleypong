package ru.vamparam.view.panel;

import resources.Resource;

import javax.swing.*;
import java.awt.*;

public class InfoPanel extends JPanel {
    private static final long serialVersionUID = -5688890380252589777L;

    private static final String INSTRUCTION = Resource
            .getString("infopanel.instruction");
    private static final int WIDTH = Resource.getInt("infopanel.width");
    private static final int HEIGHT = Resource.getInt("infopanel.height");
    private static final int PANEL_LOCTION = Resource
            .getInt("infopanel.panel_loction");
    private static final int INFOLABEL_LOCATION_X = Resource
            .getInt("infopanel.infolabel_location_x");
    private static final int INFOLABEL_LOCATION_Y = Resource
            .getInt("infopanel.infolabel_location_y");

    private JLabel infoLabel = new JLabel(INSTRUCTION);


    public InfoPanel() {
        Font info = new Font(INSTRUCTION, Font.ROMAN_BASELINE, 9);
        setSize(WIDTH, HEIGHT);
        setLocation(PANEL_LOCTION, PANEL_LOCTION);
        setVisible(true);
        infoLabel.setLocation(INFOLABEL_LOCATION_X, INFOLABEL_LOCATION_Y);
        infoLabel.setHorizontalAlignment(JLabel.LEFT);
        infoLabel.setFont(info);
        infoLabel.setBackground(Color.white.darker());
        this.add(infoLabel);
    }
}
