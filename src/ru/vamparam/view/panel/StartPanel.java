package ru.vamparam.view.panel;

import resources.Resource;
import ru.vamparam.view.StartFrame;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

public class StartPanel extends JPanel {

    private static final long serialVersionUID = -3981937205064011357L;
    private static final String IMAGE_PATH = Resource
            .getString("back_panel.image_path");
    private static final String START = Resource.getString("back_panel.start");
    private static final String EXIT = Resource.getString("back_panel.exit");
    private static final String INSTRUCTION = Resource
            .getString("back_panel.instruction");
    private static final String ARCADE_VOLLEYBALL = Resource
            .getString("view.arcade_volleyball");
    private int width;
    private int height;
    private JButton startButton;
    private JButton exitButton;
    private JLabel labelInfo = new JLabel(INSTRUCTION);
    private JLabel arcadeVolleyballLabel = new JLabel(ARCADE_VOLLEYBALL);
    private Font score = new Font("START", Font.ROMAN_BASELINE, 14);
    private Rectangle labelInfoRectagle = new Rectangle(280, 110, 200, 90);
    private Rectangle arcadeVolleyballLabelRectagle = new Rectangle(250, 50,
            250, 80);

    public StartPanel(int width, int height) {
        this.width = width;
        this.height = height;
        setLayout(null);
        addButton();
        addLabelInfo();
        addArcadeVolleyballLabel();
    }

    private void addLabelInfo() {
        labelInfo.setBounds(labelInfoRectagle);
        add(labelInfo);
    }

    private void addArcadeVolleyballLabel() {
        arcadeVolleyballLabel.setBounds(arcadeVolleyballLabelRectagle);
        arcadeVolleyballLabel.setFont(new Font(ARCADE_VOLLEYBALL, Font.ITALIC,
                30));
        arcadeVolleyballLabel.setForeground(Color.RED);
        add(arcadeVolleyballLabel);
    }

    private void addButton() {
        startButton = new JButton(START);
        exitButton = new JButton(EXIT);

        startButton.setBounds(75, 120, 100, 30);
        exitButton.setBounds(75, 160, 100, 30);

        startButton.setFont(score);
        exitButton.setFont(score);

        startButton.setForeground(Color.RED);
        exitButton.setForeground(Color.RED);

        startButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                StartFrame.getView().setVisibleView();

            }
        });
        exitButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });

        add(startButton);
        add(exitButton);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Image im = null;
        try {
            im = ImageIO.read(new File(IMAGE_PATH));
        } catch (IOException e) {
            e.getMessage();
        }

        g.drawImage(im, 0, 0, width, height, null);

    }
}
