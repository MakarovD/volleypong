package ru.vamparam.model.game_objects;

import java.awt.*;

/**
 * Абстрактный класс GameObject. Базовый класс для всех объектов игры. <br />
 * Определяет размер и положение на экране <br />
 * <p>
 * Created by Vamparam
 */
public abstract class GameObject {

    /**
     * Положение на экране
     */
    private Point location;
    /**
     * Размер объекта
     */
    private Dimension size;

    /**
     * Конструктор класса <br>
     * Задает положение на экране и размер <br>
     *
     * @param location - положение на экране
     * @param size     - размер
     */
    public GameObject(Point location, Dimension size) {
        this.location = location;
        this.setSize(size);

    }

    /**
     * Конструктор класса <br>
     * {@link #GameObject(Point, Dimension)
     *
     * @param x      - Положение по X
     * @param y      - Положение по Y
     * @param width  - ширина
     * @param height - высота
     */
    public GameObject(int x, int y, int width, int height) {
        this(new Point(x, y), new Dimension(width, height));
    }

    /**
     * Получение положения объекта на экране
     *
     * @return {@link #location}
     */
    public Point getLocation() {
        return location;
    }

    /**
     * Установка положения объекта на экране
     *
     * @param location
     */
    public void setLocation(Point location) {
        this.location = location;
    }

    /**
     * Получение размера объекта
     *
     * @return {@link #size}
     */
    public Dimension getSize() {
        return size;
    }

    /**
     * Установка размера объекта
     *
     * @param size
     */
    public void setSize(Dimension size) {
        this.size = size;
    }

}
