package ru.vamparam.view.panel;

import ru.vamparam.model.game_objects.static_object.Border;

import javax.swing.*;
import java.awt.*;

public class BoundaryPanel extends JPanel {

    private static final long serialVersionUID = 1L;


    public BoundaryPanel(Border border) {
        super();
        setBackground(Color.black);

        setSize(border.getSize());
        setLocation(border.getLocation().x, border.getLocation().y);

    }

}
