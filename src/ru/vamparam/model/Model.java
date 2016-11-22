package ru.vamparam.model;

import resources.Resource;
import ru.vamparam.model.game_objects.GameObject;
import ru.vamparam.model.game_objects.enumerations.BordersType;
import ru.vamparam.model.game_objects.enumerations.TypeMovement;
import ru.vamparam.model.game_objects.static_object.Net;
import ru.vamparam.model.listeners.BallTouchListener;
import ru.vamparam.model.application_exceptions.InvalidBordersTypeException;
import ru.vamparam.model.game_objects.dynamic_objects.Ball;
import ru.vamparam.model.game_objects.dynamic_objects.Player;

import java.awt.*;
import java.util.ArrayList;

public class Model implements BallTouchListener {

    private static final int MAX_GOALS = Resource.getInt("model.max_goals");
    private static final int WIDTH_GAME_FIELD = Resource
            .getInt("model.width_game_field");

    private static final int HEIGHT_GAME_FIELD = Resource
            .getInt("model.height_game_field");

    private static final int WIDTH_SCOREBOARD = Resource
            .getInt("model.width_scoreboard");

    private static final int HEIGHT_SCOREBOARD = Resource
            .getInt("model.height_scoreboard");

    private static final int POINTS_X_Y_GAME_FIELD = Resource
            .getInt("model.points_x_y_game_field");

    private static final int LOCATION_X_SCOREBOARD = Resource
            .getInt("model.location_x_scoreboard");

    private static final int LOCATION_Y_SCOREBOARD = Resource
            .getInt("model.location_y_scoreboard");

    private static final String LEFT_PLAYER_WIN = Resource
            .getString("model.left_player_win");
    private static final String RIGHT_PLAYER_WIN = Resource
            .getString("model.right_player_win");

    private GameField gameField = new GameField(new Point(
            POINTS_X_Y_GAME_FIELD, POINTS_X_Y_GAME_FIELD), new Dimension(
            WIDTH_GAME_FIELD, HEIGHT_GAME_FIELD));

    private Scoreboard scoreBoard = new Scoreboard(new Point(
            LOCATION_X_SCOREBOARD, LOCATION_Y_SCOREBOARD), new Dimension(
            WIDTH_SCOREBOARD, HEIGHT_SCOREBOARD), MAX_GOALS);

    private static ArrayList<GameObject> gameObject = new ArrayList<GameObject>();
    private Thread threadForBall;
    private Thread threadForPlayerLeft;
    private Thread threadForSecondPlayer;
    private Net net;

    private Ball ball;

    private static Player firstPlayer;

    private static Player secondPlayer;

    public Model() {

        setStaticObject();
        setDinamicObject();
        ball = gameField.addBall();
        ball.setTouchListener(this);

    }

    public void run() {
        this.threadForBall = new Thread(this.ball);
        this.threadForBall.start();
        this.threadForPlayerLeft = new Thread(firstPlayer);
        this.threadForPlayerLeft.start();
        this.threadForSecondPlayer = new Thread(secondPlayer);
        this.threadForSecondPlayer.start();
    }

    public void stop() {
        this.ball.setMoving(false);
        firstPlayer.setMoving(false);
        secondPlayer.setMoving(false);
    }

    public void resume() {
        this.ball.setMoving(true);
        firstPlayer.setMoving(true);
        secondPlayer.setMoving(true);
        run();
    }

    public void reset() {
        firstPlayer.setLocation(gameField.getInitialLocationFirstPlayer());
        secondPlayer.setLocation(gameField.getInitialLocationSecondPlayer());
        stop();
    }


    private void setStaticObject() {
        net = gameField.addNet();
        try {
            gameObject.add(gameField.addBorders(BordersType.LEFT_BOUNDARY));
            gameObject.add(gameField.addBorders(BordersType.RIGHT_BOUNDARY));
            gameObject.add(gameField.addBorders(BordersType.UP_BOUNDARY));
            gameObject.add(gameField.addBorders(BordersType.COURT));

            gameObject.add(net);
        } catch (InvalidBordersTypeException e) {
            e.printStackTrace();
        }

    }

    private void setDinamicObject() {
        firstPlayer = gameField.addPlayerLeft();
        secondPlayer = gameField.addPlayerRight();
        gameObject.add(firstPlayer);
        gameObject.add(secondPlayer);
    }


    public void moveFirstPlayer(TypeMovement typeMovement) {
        switch (typeMovement) {
            case LEFT:
                firstPlayer.moveLeft();
                break;
            case RIGHT:
                firstPlayer.moveRight();
                break;
            default:
                break;
        }
    }

    public void moveSecondPlayer(TypeMovement typeMovement) {
        switch (typeMovement) {
            case LEFT:
                secondPlayer.moveLeft();
                break;
            case RIGHT:
                secondPlayer.moveRight();
                break;
            default:
                break;
        }
    }

    @Override
    public void touchCourt(Ball ball) {
        if (isLeftCort(ball)) {
            secondPlayer.increaseScore();
            giveBallRightPlayer();
        } else {
            firstPlayer.increaseScore();
            giveBallLeftPlayer();
        }

        determineTheWinner();
        scoreBoard.setScore(firstPlayer.getScore(), secondPlayer.getScore());
        reset();
    }

    public boolean isLeftCort(Ball ball) {
        return (ball.getLocation().x + ball.getSize().width) < (this.net
                .getLocation().x + this.net.getSize().width);
    }

    public void giveBallLeftPlayer() {
        this.ball.setLocation(gameField.getLocationBallAtFirstPlayer());

    }


    public void giveBallRightPlayer() {
        this.ball.setLocation(gameField.getLocationBallAtSecondPlayer());
    }

    private void determineTheWinner() {
        if (scoreBoard.isGameOver()) {
            gameOver();

        }
    }

    public static void restartGame() {
        secondPlayer.reset();
        firstPlayer.reset();
        Scoreboard.resetGoals();
    }

    public String gameOver() {

        StringBuilder finalScore = new StringBuilder();
        if (isFirstPlayerWin()) {
            finalScore.append(LEFT_PLAYER_WIN);
        }
        if (isSecondPlayerWin()) {
            finalScore.append(RIGHT_PLAYER_WIN);
        }
        finalScore.append("\nScore: " + scoreBoard.getGoalsFirstPlayer()
                + "  -  " + scoreBoard.getGoalsSecondPlayer());
        return finalScore.toString();
    }

    private boolean isFirstPlayerWin() {
        return (firstPlayer.getScore() == MAX_GOALS);
    }

    private boolean isSecondPlayerWin() {
        return (secondPlayer.getScore() == MAX_GOALS);
    }


    public Scoreboard getScoreBoard() {
        return scoreBoard;
    }

    public void setScoreBoard(Scoreboard scoreBoard) {
        this.scoreBoard = scoreBoard;
    }

    public static ArrayList<GameObject> getGameObject() {
        return gameObject;
    }

    public GameField getGameField() {
        return gameField;
    }

    public Player getPlayerLeft() {
        return firstPlayer;
    }

    public void setPlayerLeft(Player player) {
        firstPlayer = player;
    }

    public Player getPlayerRight() {
        return secondPlayer;
    }

    public void setPlayerRight(Player player) {
        secondPlayer = player;
    }

    public Ball getBall() {
        return ball;
    }


}
