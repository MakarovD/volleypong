package ru.vamparam.view;

import resources.Resource;
import ru.vamparam.controller.Controller;
import ru.vamparam.model.Model;
import ru.vamparam.model.Scoreboard;
import ru.vamparam.model.game_objects.GameObject;
import ru.vamparam.model.game_objects.static_object.Border;
import ru.vamparam.model.game_objects.static_object.Court;
import ru.vamparam.model.game_objects.static_object.Net;
import ru.vamparam.model.listeners.WinnerListener;
import ru.vamparam.view.panel.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class View extends JFrame implements WinnerListener {

    private static final long serialVersionUID = 1L;
    private static final String ARCADE_VOLLEYBALL = Resource
            .getString("view.arcade_volleyball");
    private static final int CORRECTION_VALUE = Resource
            .getInt("view.correction_value");
    private Model model;
    private Controller controller;
    private JPanel mainPanel;
    private BoundaryPanel boundaryPanel;
    private BallPanel ballPanel;
    private CourtPanel courtPanel;
    private NetPanel netPanel;
    private PlayerPanel playerLeftPanel;
    private PlayerPanel playerRightPanel;
    private ScorePanel scorePanel;
    private InfoPanel infoPanel;

    public View(Model model, Controller controller) {
        this.setModel(model);
        this.setController(controller);
        initializeMainPanel();
        initializeMainFrame();
        setListener();
        addFormListener();

    }

    private void initializeMainPanel() {
        this.mainPanel = new JPanel();

        scorePanel = new ScorePanel(model.getScoreBoard());
        this.mainPanel.add(scorePanel);
        infoPanel = new InfoPanel();
        this.mainPanel.add(infoPanel);
        mainPanel.setBackground(Color.blue.darker());
        for (GameObject object : Model.getGameObject()) {
            if (object instanceof Net) {
                netPanel = new NetPanel((Net) object);
                mainPanel.add(netPanel);
            }
            if (object instanceof Court) {
                courtPanel = new CourtPanel((Court) object);
                mainPanel.add(courtPanel);
            }
            if (object instanceof Border) {
                boundaryPanel = new BoundaryPanel((Border) object);
                mainPanel.add(boundaryPanel);
            }

        }

        playerLeftPanel = new PlayerPanel(model.getPlayerLeft());
        this.mainPanel.add(playerLeftPanel);
        playerRightPanel = new PlayerPanel(model.getPlayerRight());
        this.mainPanel.add(playerRightPanel);
        ballPanel = new BallPanel(model.getBall());
        this.mainPanel.add(ballPanel);

        this.mainPanel.setLayout(new BorderLayout());
    }

    public void setVisibleView() {
        setVisible(true);
        controller.run();
    }

    public void initializeMainFrame() {
        setTitle(ARCADE_VOLLEYBALL);
        setResizable(false);
        super.setBounds(0, 0, model.getGameField().getSize().width, model
                .getGameField().getSize().height + CORRECTION_VALUE);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setContentPane(mainPanel);
    }

    private void setModel(Model model) {
        this.model = model;
    }

    public Controller getController() {
        return controller;
    }

    public void setController(Controller controller) {
        this.controller = controller;
    }

    private void setListener() {
        model.getBall().setMoveListener(ballPanel);

        model.getPlayerLeft().addListener(playerLeftPanel);
        model.getPlayerRight().addListener(playerRightPanel);
        model.getScoreBoard().addListener(scorePanel);
        model.getScoreBoard().addWinnerListener(scorePanel);
        model.getScoreBoard().addWinnerListener(this);

    }

    private void addFormListener() {

        addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent event) {
                int keyCode = event.getKeyCode();
                String keyText = KeyEvent.getKeyText(keyCode);
                controller.impact(keyText);
            }

            ;
        });
    }

    @Override
    public void showWinner(Scoreboard scoreboard) {
        controller.stop();
        JOptionPane.showMessageDialog(this, model.gameOver());
        scorePanel.updateScore(scoreboard);
        Model.restartGame();
        setVisible(false);

    }
}
