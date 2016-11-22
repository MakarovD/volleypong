package ru.vamparam.view.panel;

import ru.vamparam.model.game_objects.dynamic_objects.Ball;
import ru.vamparam.model.listeners.MoveBallListener;

import javax.swing.*;
import java.awt.*;

public class BallPanel extends JPanel implements MoveBallListener {

    private static final long serialVersionUID = 1L;
    private Ball ball;

    public BallPanel(Ball ball) {
        this.ball = ball;
        setSize(ball.getSize());
        setOpaque(false);
    }

    @Override
    public void paintComponent(Graphics g) {
        g.setColor(Color.magenta);
        int width = this.getSize().width;
        int height = this.getSize().height;
        g.fillOval(0, 0, width - 2, height - 2);
        g.drawOval(0, 0, width - 2, height - 2);

    }

    @Override
    public void update(Graphics g) {
        paintComponent(g);
    }

    @Override
    public void moveBall(Ball ball) {
        setLocation(ball.getLocation());

    }

}
