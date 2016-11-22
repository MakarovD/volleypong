package ru.vamparam.view;

import resources.Resource;
import ru.vamparam.view.panel.StartPanel;

import javax.swing.*;

public class StartFrame extends JFrame {

    private static final long serialVersionUID = 468688912355779790L;
    private static final int DEFAULT_WIDHT = Resource.getInt("startframe.default_widht");
    private static final int DEFAULT_HEIGHT = Resource.getInt("startframe.default_height");
    private static final String ARCADE_VOLLEYBALL = Resource.getString("view.arcade_volleyball");
    private static View view;

    public StartFrame(View view) {
        StartFrame.view = view;
        initialFrame();
    }


    private void initialFrame() {
        setSize(DEFAULT_WIDHT, DEFAULT_HEIGHT);
        setTitle(ARCADE_VOLLEYBALL);
        setVisible(true);
        setResizable(false);
        add(new StartPanel(getWidth(), getHeight()));
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }

    public static View getView() {
        return view;
    }

}
