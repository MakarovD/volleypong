package ru.vamparam.view.panel;

import ru.vamparam.model.game_objects.static_object.Court;

import javax.swing.*;
import java.awt.*;

public class CourtPanel extends JPanel {

    private static final long serialVersionUID = 1L;

    public CourtPanel(Court court) {
        super();
        setBackground(Color.yellow.darker());

        setSize(court.getSize());
        setLocation(court.getLocation().x, court.getLocation().y);

    }
}
