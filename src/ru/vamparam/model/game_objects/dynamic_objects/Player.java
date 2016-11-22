package ru.vamparam.model.game_objects.dynamic_objects;

import ru.vamparam.model.game_objects.GameObject;
import ru.vamparam.model.game_objects.enumerations.CollisionDirection;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

import resources.Resource;
import ru.vamparam.model.Model;
import ru.vamparam.model.application_exceptions.InvalidCollisionDirectionException;
import ru.vamparam.model.listeners.MovePlayerListener;

public class Player extends DynamicObject implements Runnable {
    private static final int DELAY = Resource.getInt("dinamic_object.delay");
    private static final byte INITIAL_SCORE = Resource
            .getByte("player.initial_score");
    private static final int DEFAULT_WIDTH = Resource
            .getInt("player.default_width");
    private static final int DEFAULT_HEIGHT = Resource
            .getInt("player.default_height");

    private static final int POSITIVELY_DIRECTION = Resource
            .getInt("dinamic_object.positively_direction");
    private static final int NEGATIVELY_DIRECTION = Resource
            .getInt("dinamic_object.negatively_direction");

    private static final int DEFAULT_ACCELERATION_X = Resource
            .getInt("player.default_acceleration_x");
    private static final int DEFAULT_ACCELERATION_Y = Resource
            .getInt("player.default_acceleration_y");
    private static final int DEFAULT_VELOCITY_X = Resource
            .getInt("player.default_velocity_x");

    private static final int DEFAULT_VELOCITY_Y = Resource
            .getInt("player.default_velocity_y");

    private static final Point DEFAULT_ACCELERATION = new Point(
            DEFAULT_ACCELERATION_X, DEFAULT_ACCELERATION_Y);
    private static final Point DEFAULT_DIRECTION = new Point(
            POSITIVELY_DIRECTION, NEGATIVELY_DIRECTION);
    private static final Point DEFAULT_VELOCITY = new Point(DEFAULT_VELOCITY_X,
            DEFAULT_VELOCITY_Y);

    private List<MovePlayerListener> moveListener;
    private Point direction;
    private boolean isMoving = true;
    private byte score;

    public Player(Point location) {
        super(location, new Dimension(DEFAULT_WIDTH, DEFAULT_HEIGHT));
        this.setVelocity(DEFAULT_VELOCITY);
        this.direction = new Point(DEFAULT_DIRECTION);
        this.setAcceleration(DEFAULT_ACCELERATION);
        this.setGameObjects(Model.getGameObject());
        this.setScore(INITIAL_SCORE);
        this.moveListener = new ArrayList<>();
    }


    @Override
    public void move() {
        int currentLocationX = this.getLocation().x;
        int currentLocationY = this.getLocation().y;

        int velocityX = getVelocity().x * direction.x
                + this.getAcceleration().x * direction.x;
        int velocityY = (getVelocity().y + this.getAcceleration().y)
                * direction.y;

        int nextLocationX = currentLocationX + velocityX;

        int nextLocationY = currentLocationY + velocityY;

        this.setLocation(new Point(nextLocationX, nextLocationY));
    }

    public void moveLeft() {
        this.direction.x = NEGATIVELY_DIRECTION;
    }


    public void moveRight() {
        this.direction.x = POSITIVELY_DIRECTION;
    }

    private void step() {
        checkCollision();
        move();
        notifyMoveListener();
    }

    @Override
    protected void processUp(GameObject interacted) {

    }

    @Override
    public synchronized void run() {

        while (isMoving) {
            step();
            try {
                Thread.sleep(DELAY);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }


    private void notifyMoveListener() {
        for (MovePlayerListener listener : moveListener) {
            listener.movePlayer(this);
        }
    }

    public void addListener(MovePlayerListener playerListener) {
        this.moveListener.add(playerListener);
    }


    public void setMoveListener(List<MovePlayerListener> moveListener) {
        if (this.moveListener != null) {
            return;
        }
        this.moveListener = moveListener;
    }

    protected void checkLeftCollisions(GameObject gameObject) {
        if (Math.abs(gameObject.getLocation().x + gameObject.getSize().width
                - this.getLocation().x) <= Math.abs(this.getVelocity().x)
                && this.getLocation().y + this.getSize().height > gameObject
                .getLocation().y
                && this.getLocation().y < gameObject.getLocation().y
                + gameObject.getSize().height) {
            this.processInteraction(gameObject, CollisionDirection.LEFT);
        }

    }

    @Override
    protected void checkRightCollisions(GameObject gameObject) {
        if (Math.abs(this.getLocation().x + this.getSize().width
                - gameObject.getLocation().x) <= Math.abs(this.getVelocity().x)
                && this.getLocation().y + this.getSize().height > gameObject
                .getLocation().y
                && this.getLocation().y < gameObject.getLocation().y
                + gameObject.getSize().height) {
            this.processInteraction(gameObject, CollisionDirection.RIGHT);
        }

    }

    @Override
    protected void checkDownCollisions(GameObject gameObject) {
        if (Math.abs(this.getLocation().y + this.getSize().height
                - gameObject.getLocation().y) <= Math.abs(this.getVelocity().y)
                && this.getLocation().x + this.getSize().width > gameObject
                .getLocation().x
                && this.getLocation().x < gameObject.getLocation().x
                + gameObject.getSize().width) {
            this.processInteraction(gameObject, CollisionDirection.DOWN);
        }

    }

    @Override
    protected void checkUpCollisions(GameObject gameObject) {

    }

    @Override
    protected void processInteraction(GameObject interacted,
                                      CollisionDirection collisionDirection) {
        switch (collisionDirection) {
            case LEFT:
                processLeft(interacted);
                break;
            case RIGHT:
                processRight(interacted);
                break;
            case DOWN:
                processDown(interacted);
                break;
            default:
                try {
                    throw new InvalidCollisionDirectionException(
                            collisionDirection.toString());
                } catch (InvalidCollisionDirectionException e) {
                    e.getMessage();
                }
        }

    }

    ;

    @Override
    protected void processRight(GameObject interacted) {
        if (this.getLocation().x + this.getSize().width < interacted
                .getLocation().x) {
            this.direction.x = POSITIVELY_DIRECTION;
        }
        this.direction.x = NEGATIVELY_DIRECTION;

    }

    @Override
    protected void processLeft(GameObject interacted) {
        if (this.getLocation().x > interacted.getLocation().x
                + interacted.getSize().width) {
            this.direction.x = NEGATIVELY_DIRECTION;
        }
        this.direction.x = POSITIVELY_DIRECTION;
    }

    @Override
    protected void processDown(GameObject interacted) {
        if (this.getLocation().y + this.getSize().height < interacted
                .getLocation().y) {
            this.direction.y = POSITIVELY_DIRECTION;
        }
        this.setLocation(new Point(getLocation().x, interacted.getLocation().y
                - getSize().height));
        this.setVelocity(new Point(getVelocity().x, 0));

    }


    public void increaseScore() {
        this.score++;
    }


    public void reset() {
        this.score = 0;
    }


    public boolean isMoving() {
        return isMoving;
    }


    public void setMoving(boolean isMoving) {
        this.isMoving = isMoving;
    }


    public byte getScore() {
        return score;
    }


    public void setScore(byte score) {
        this.score = score;
    }
}
