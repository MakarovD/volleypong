package ru.vamparam.model.game_objects.static_object;

import ru.vamparam.model.game_objects.GameObject;

import java.awt.*;


public abstract class StaticObject extends GameObject {

    public StaticObject(Point location, Dimension size) {
        super(location, size);
    }

}
