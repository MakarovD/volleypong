package ru.vamparam.view.panel;

import ru.vamparam.model.Scoreboard;
import ru.vamparam.model.listeners.ScoreListener;
import ru.vamparam.model.listeners.WinnerListener;

import javax.swing.*;
import java.awt.*;

public class ScorePanel extends JPanel implements ScoreListener, WinnerListener {

    private static final long serialVersionUID = 1L;
    private static final String TEST_STRING = "0 - 0";
    private JLabel jlabel = new JLabel(TEST_STRING);
    ;
    private String scoreText = "";
    private Scoreboard scoreboard;

    public ScorePanel(Scoreboard scoreboard) {
        super();
        this.setScoreboard(scoreboard);
        setSize(scoreboard.getSize());
        setLocation(scoreboard.getLocation());
        setVisible(true);

        Font score = new Font(TEST_STRING, Font.ROMAN_BASELINE, 30);
        jlabel.setFont(score);
        setBackground(Color.white);
        this.add(jlabel);
    }

    @Override
    public void updateScore(Scoreboard scoreboard) {

        String out = scoreboard.getGoalsFirstPlayer() + "  -  "
                + scoreboard.getGoalsSecondPlayer();

        jlabel.setText(out);

    }

    public String getScoreText() {
        return scoreText;
    }


    public void setScoreText(String scoreText) {
        this.scoreText = scoreText;
    }

    @Override
    public void showWinner(Scoreboard scoreboard) {
        updateScore(scoreboard);

    }

    public Scoreboard getScoreboard() {
        return scoreboard;
    }

    public void setScoreboard(Scoreboard scoreboard) {
        this.scoreboard = scoreboard;
    }

}
