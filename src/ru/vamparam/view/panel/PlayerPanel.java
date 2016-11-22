package ru.vamparam.view.panel;

import ru.vamparam.model.game_objects.dynamic_objects.Player;
import ru.vamparam.model.listeners.MovePlayerListener;

import javax.swing.*;
import java.awt.*;


public class PlayerPanel extends JPanel implements MovePlayerListener {
    private static final long serialVersionUID = 1471894252661469219L;
    private Player player;

    public PlayerPanel(Player player) {
        this.player = player;
        setSize(player.getSize());
        setOpaque(true);
    }

    @Override
    public void paintComponent(Graphics g) {
        g.setColor(Color.green);
        int width = this.getSize().width;
        int height = this.getSize().height;
        g.fillRect(0, 0, width, height);
        g.drawRect(0, 0, width, height);

    }

    @Override
    public void movePlayer(Player player) {
        setLocation(player.getLocation());
    }

}
