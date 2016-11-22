package ru.vamparam.model.game_objects.dynamic_objects;

import resources.Resource;
import ru.vamparam.model.Model;
import ru.vamparam.model.game_objects.GameObject;
import ru.vamparam.model.game_objects.static_object.Court;
import ru.vamparam.model.game_objects.enumerations.CollisionDirection;
import ru.vamparam.model.listeners.BallTouchListener;
import ru.vamparam.model.listeners.MoveBallListener;

import java.awt.*;

public class Ball extends DynamicObject implements Runnable {

    private static final int DEFAULT_WIDTH = Resource
            .getInt("ball.default_width");
    private static final int DEFAULT_HEIGHT = Resource
            .getInt("ball.default_height");
    private static final byte POSITIVELY_DIRECTION = Resource
            .getByte("dinamic_object.positively_direction");
    private static final byte NEGATIVELY_DIRECTION = Resource
            .getByte("dinamic_object.negatively_direction");
    private static final int DEFAULT_ACCELERATION_X = Resource
            .getInt("ball.default_acceleration_x");
    private static final int DEFAULT_ACCELERATION_Y = Resource
            .getInt("ball.default_acceleration_y");
    private static final Point DEFAULT_ACCELERATION = new Point(
            DEFAULT_ACCELERATION_X, DEFAULT_ACCELERATION_Y);
    private static final int DELAY = Resource.getInt("dinamic_object.delay");
    private MoveBallListener moveListener = null;
    private BallTouchListener touchListener = null;

    private boolean isMoving;

    private static Ball instance = new Ball();

    private Point direction;

    private Ball() {
        super(new Point(0, 0), new Dimension(DEFAULT_WIDTH, DEFAULT_HEIGHT));
        this.setVelocity(new Point(6, 6));
        this.setAcceleration(DEFAULT_ACCELERATION);
        direction = new Point(POSITIVELY_DIRECTION, POSITIVELY_DIRECTION);
        this.setGameObjects(Model.getGameObject());
        this.setMoving(true);
    }

    public static Ball getInstanse() {
        if (instance == null) {
            instance = new Ball();
        }
        return instance;

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

    private void step() {
        checkCollision();
        move();
        notifyMoveListener();
    }

    @Override
    public void move() {
        int currentLocationX = this.getLocation().x;
        int currentLocationY = this.getLocation().y;
        int velocityX = (getVelocity().x + getAcceleration().x) * direction.x;
        int velocityY = (getVelocity().y + getAcceleration().y) * direction.y;

        int nextLocationX = currentLocationX + velocityX;

        int nextLocationY = currentLocationY + velocityY;

        this.setLocation(new Point(nextLocationX, nextLocationY));
    }

    private void notifyMoveListener() {
        if (this.moveListener == null) {
            return;
        }
        this.moveListener.moveBall(this);
    }

    @Override
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
        if (Math.abs(gameObject.getLocation().y + gameObject.getSize().height
                - this.getLocation().y) <= Math.abs(this.getVelocity().y)
                && this.getLocation().x + this.getSize().width > gameObject
                .getLocation().x
                && this.getLocation().x < gameObject.getLocation().x
                + gameObject.getSize().width) {
            this.processInteraction(gameObject, CollisionDirection.UP);
        }
    }

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
    protected void processUp(GameObject interacted) {
        if (this.getLocation().y > interacted.getLocation().y
                + interacted.getSize().height) {
            this.direction.y = NEGATIVELY_DIRECTION;
        }
        this.direction.y = POSITIVELY_DIRECTION;
    }

    @Override
    protected void processDown(GameObject interacted) {
        if (interacted instanceof Court) {
            notifyTouchListener();
        }
        if (this.getLocation().y + this.getSize().height < interacted
                .getLocation().y) {

            this.direction.y = POSITIVELY_DIRECTION;
        }
        this.direction.y = NEGATIVELY_DIRECTION;
    }

    private void notifyTouchListener() {
        if (this.touchListener == null) {
            return;
        }
        this.touchListener.touchCourt(this);
    }

    public void setMoveListener(MoveBallListener moveListener) {
        if (this.moveListener != null) {
            return;
        }
        this.moveListener = moveListener;
    }

    public void setTouchListener(BallTouchListener touchListener) {
        if (this.touchListener != null) {
            return;
        }
        this.touchListener = touchListener;
    }

    public boolean isMoving() {
        return isMoving;
    }

    public void setMoving(boolean isMoving) {
        this.isMoving = isMoving;
    }
}
