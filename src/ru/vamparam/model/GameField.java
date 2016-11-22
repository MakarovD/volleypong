package ru.vamparam.model;

import ru.vamparam.model.application_exceptions.InvalidBordersTypeException;
import ru.vamparam.model.game_objects.GameObject;
import ru.vamparam.model.game_objects.enumerations.BordersType;
import ru.vamparam.model.game_objects.static_object.Border;
import ru.vamparam.model.game_objects.static_object.Court;
import ru.vamparam.model.game_objects.static_object.Net;
import ru.vamparam.model.game_objects.dynamic_objects.Ball;
import ru.vamparam.model.game_objects.dynamic_objects.Player;

import java.awt.*;


public class GameField extends GameObject {

    private static final int CORRECTION_VALUE_LOCATION_Y_PLAYERS = 64;
    private static final int CORRECTION_VALUE__X_LEFT_PLAYER = 4;

    private static final int CORRECTION_VALUE_LOCATION_BALL = 4;
    private static final int CORRECTION_VALUE_LOCATION_PLAYERS = 2;
    private static final int NULL_POINT = 0;
    private static final int CORRECTION_VALUE_HEIGHT = 10;
    private static final int UP_BOUNDARY_HEIGHT = 60;
    private static final int CORRECTION_VALUE_COURT_HEIGHT = 4;
    private static final int COURT_HEIGHT = 6;
    private static final int CORRECTION_VALUE_WIDTH = 5;
    private static final int CORRECTION_VALUE_NET = 2;
    private static final int CORRECTION_VALUE_NET_Y = 20;
    private static final int CORRECTION_VALUE_NET_WIDTH = 15;

    private Ball ball;

    private Player firstPlayer;

    private Player secondPlayer;

    private Point initialLocationFirstPlayer;

    private Point initialLocationSecondPlayer;

    private Point locationBallOnStart;

    private Point locationBallAtFirstPlayer;

    private Point locationBallAtSecondPlayer;


    public GameField(Point location, Dimension size) {
        super(location, size);
        initialization();
    }


    private void initialization() {
        initialLocationFirstPlayer = new Point(getSize().width
                / CORRECTION_VALUE__X_LEFT_PLAYER, getSize().height
                - CORRECTION_VALUE_LOCATION_Y_PLAYERS);

        initialLocationSecondPlayer = new Point(
                (int) (getSize().width * 3 / 4), getSize().height
                - CORRECTION_VALUE_LOCATION_Y_PLAYERS);

        locationBallOnStart = new Point(this.getSize().width
                / CORRECTION_VALUE_LOCATION_BALL, this.getSize().height
                / CORRECTION_VALUE_LOCATION_BALL);

        setLocationBallAtFirstPlayer(new Point(this.getSize().width
                / CORRECTION_VALUE__X_LEFT_PLAYER, this.getSize().height
                / CORRECTION_VALUE_LOCATION_PLAYERS));

        setLocationBallAtSecondPlayer(new Point(
                (int) (this.getSize().width * 3 / 4), this.getSize().height
                / CORRECTION_VALUE_LOCATION_PLAYERS));
    }


    public Border addBorders(BordersType bordersType)
            throws InvalidBordersTypeException {
        switch (bordersType) {
            case LEFT_BOUNDARY:
                return new Border(new Point(NULL_POINT, NULL_POINT),
                        new Dimension(NULL_POINT, this.getSize().height
                                + CORRECTION_VALUE_HEIGHT));
            case RIGHT_BOUNDARY:
                return new Border(new Point(this.getSize().width, NULL_POINT),
                        new Dimension(NULL_POINT, this.getSize().height
                                + CORRECTION_VALUE_HEIGHT));

            case UP_BOUNDARY:
                return new Border(new Point(NULL_POINT, NULL_POINT), new Dimension(
                        this.getSize().width + CORRECTION_VALUE_WIDTH,
                        UP_BOUNDARY_HEIGHT));

            case COURT:
                return new Court(new Point(NULL_POINT, this.getSize().height
                        + CORRECTION_VALUE_COURT_HEIGHT),
                        new Dimension(
                                this.getSize().width + CORRECTION_VALUE_WIDTH,
                                COURT_HEIGHT));

            default:
                throw new InvalidBordersTypeException(bordersType.toString());
        }

    }


    public Ball addBall() {
        Ball ball = Ball.getInstanse();
        ball.setLocation(getLocationBallOnStart());
        return ball;
    }

    public Player addPlayerLeft() {
        return firstPlayer = new Player(getInitialLocationFirstPlayer());
    }

    public Player addPlayerRight() {
        return secondPlayer = new Player(getInitialLocationSecondPlayer());
    }

    public Net addNet() {
        return new Net(new Point(this.getSize().width / CORRECTION_VALUE_NET,
                this.getSize().height / CORRECTION_VALUE_NET
                        + CORRECTION_VALUE_NET_Y), new Dimension(
                CORRECTION_VALUE_NET_WIDTH, this.getSize().height
                / CORRECTION_VALUE_NET));
    }

    public Ball getBall() {
        return ball;
    }

    public Player getFirstPlayer() {
        return firstPlayer;
    }

    public void setFirstPlayer(Player firstPlayer) {
        this.firstPlayer = firstPlayer;
    }

    public Player getSecondPlayer() {
        return secondPlayer;
    }

    public void setSecondPlayer(Player secondPlayer) {
        this.secondPlayer = secondPlayer;
    }

    public Point getInitialLocationFirstPlayer() {
        return initialLocationFirstPlayer;
    }

    public void setInitialLocationFirstPlayer(Point initialLocationFirstPlayer) {
        this.initialLocationFirstPlayer = initialLocationFirstPlayer;
    }

    public Point getInitialLocationSecondPlayer() {
        return initialLocationSecondPlayer;
    }

    public void setInitialLocationSecondPlayer(Point initialLocationSecondPlayer) {
        this.initialLocationSecondPlayer = initialLocationSecondPlayer;
    }

    public Point getLocationBallOnStart() {
        return locationBallOnStart;
    }

    public void setLocationBallOnStart(Point locationBallOnStart) {
        this.locationBallOnStart = locationBallOnStart;
    }

    public Point getLocationBallAtFirstPlayer() {
        return locationBallAtFirstPlayer;
    }

    public void setLocationBallAtFirstPlayer(Point locationBallAtFirstPlayer) {
        this.locationBallAtFirstPlayer = locationBallAtFirstPlayer;
    }

    public Point getLocationBallAtSecondPlayer() {
        return locationBallAtSecondPlayer;
    }

    public void setLocationBallAtSecondPlayer(Point locationBallAtSecondPlayer) {
        this.locationBallAtSecondPlayer = locationBallAtSecondPlayer;
    }

}
