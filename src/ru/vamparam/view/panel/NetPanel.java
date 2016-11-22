package ru.vamparam.view.panel;

import ru.vamparam.model.game_objects.static_object.Net;

import javax.swing.*;
import java.awt.*;

public class NetPanel extends JPanel {

    private static final long serialVersionUID = 1L;

    public NetPanel(Net net) {
        super();
        setBackground(Color.gray);
        setSize(net.getSize());
        setLocation(net.getLocation().x,
                net.getLocation().y);
    }
}
