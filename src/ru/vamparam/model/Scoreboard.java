package ru.vamparam.model;

import ru.vamparam.model.game_objects.GameObject;
import ru.vamparam.model.listeners.ScoreListener;
import ru.vamparam.model.listeners.WinnerListener;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;


public class Scoreboard extends GameObject {

    private static int goalsFirstPlayer;

    private static int goalsSecondPlayer;

    private boolean isGameOver;

    private int maxGoals;

    private List<ScoreListener> scoreListenerList = new ArrayList<ScoreListener>();
    private List<WinnerListener> winnerListenerList = new ArrayList<WinnerListener>();


    public Scoreboard(Point location, Dimension size, int maxGoals) {
        super(location, size);
        this.maxGoals = maxGoals;

    }

    public static void resetGoals() {
        goalsFirstPlayer = 0;
        goalsSecondPlayer = 0;
    }


    private void notifyWinnerListener() {
        for (WinnerListener listener : winnerListenerList) {
            listener.showWinner(this);
        }
    }

    public void addWinnerListener(WinnerListener listener) {
        this.winnerListenerList.add(listener);
    }

    public void setWinnerListener(List<WinnerListener> winnerListenerList) {
        this.winnerListenerList = winnerListenerList;
    }

    private void notifyScoreListener() {
        for (ScoreListener listener : scoreListenerList) {
            listener.updateScore(this);
        }
    }

    public void addListener(ScoreListener listener) {
        this.scoreListenerList.add(listener);
    }

    public void setScoreListener(List<ScoreListener> scoreListener) {
        if (this.scoreListenerList != null) {
            return;
        }
        this.scoreListenerList = scoreListener;
    }


    public void setScore(int goalsFirstPlayer, int goalsSecondPlayer) {
        Scoreboard.goalsFirstPlayer = goalsFirstPlayer;
        Scoreboard.goalsSecondPlayer = goalsSecondPlayer;
        checkWinner();
        notifyScoreListener();
    }


    public void checkWinner() {
        if ((goalsFirstPlayer == maxGoals) || (goalsSecondPlayer == maxGoals)) {
            setGameOver(true);
            notifyWinnerListener();
        }
    }

    public int getGoalsFirstPlayer() {
        return goalsFirstPlayer;
    }


    public void setGoalsFirstPlayer(int goalsFirstPlayer) {
        Scoreboard.goalsFirstPlayer = goalsFirstPlayer;
    }

    public int getGoalsSecondPlayer() {
        return goalsSecondPlayer;
    }

    public void setGoalsSecondPlayer(int goalsSecondPlayer) {
        Scoreboard.goalsSecondPlayer = goalsSecondPlayer;
    }

    public boolean isGameOver() {
        return isGameOver;
    }

    public void setGameOver(boolean isGameOver) {
        this.isGameOver = isGameOver;
    }

}
