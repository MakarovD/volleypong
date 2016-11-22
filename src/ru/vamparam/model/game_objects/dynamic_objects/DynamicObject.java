package ru.vamparam.model.game_objects.dynamic_objects;

import ru.vamparam.model.game_objects.GameObject;
import ru.vamparam.model.application_exceptions.InvalidCollisionDirectionException;
import ru.vamparam.model.game_objects.enumerations.CollisionDirection;

import java.awt.*;
import java.util.ArrayList;

/**
 * Created by Vamparam on 21.11.2016.
 */
public abstract class DynamicObject extends GameObject {
    /**
     * Скорость
     */
    private Point velocity;
    /**
     * Ускорение
     */
    private Point acceleration;
    /**
     * Список объектов игры
     */
    private java.util.List<GameObject> gameObjects = new ArrayList<>();

    /**
     * Конструктор класса
     *
     * @param location - положение на экране
     * @param size     - размер
     */
    public DynamicObject(Point location, Dimension size) {
        super(location, size);
    }

    protected void checkCollision() {
        for (GameObject gameObject : gameObjects) {
            checkDownCollisions(gameObject);
            checkUpCollisions(gameObject);
            checkLeftCollisions(gameObject);
            checkRightCollisions(gameObject);
        }
    }

    protected abstract void checkLeftCollisions(GameObject gameObject);

    protected abstract void checkRightCollisions(GameObject gameObject);

    protected abstract void checkDownCollisions(GameObject gameObject);

    protected abstract void checkUpCollisions(GameObject gameObject);

    protected void processInteraction(GameObject interacted,
                                      CollisionDirection collisionDirection) {
        switch (collisionDirection) {
            case UP:
                processUp(interacted);
                break;
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

    protected abstract void processRight(GameObject interacted);

    protected abstract void processLeft(GameObject interacted);

    protected abstract void processUp(GameObject interacted);

    protected abstract void processDown(GameObject interacted);

    public abstract void move();


    public Point getVelocity() {
        return velocity;
    }


    public void setVelocity(Point velocity) {
        this.velocity = velocity;
    }


    public Point getAcceleration() {
        return acceleration;
    }


    public void setAcceleration(Point acceleration) {
        this.acceleration = acceleration;
    }

    public java.util.List<GameObject> getGameObjects() {
        return gameObjects;
    }

    public void setGameObjects(java.util.List<GameObject> gameObjects) {
        this.gameObjects = gameObjects;
    }

}
