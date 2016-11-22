package ru.vamparam.controller;

import resources.Resource;
import ru.vamparam.model.Model;
import ru.vamparam.model.game_objects.enumerations.TypeMovement;

public class Controller {

    private static final String FIRST_PLAYER_LEFT_KEY = Resource.getString("controller.first_player_left_key");
    private static final String FIRST_PLAYER_RIGHT_KEY = Resource.getString("controller.first_player_right_key");
    private static final String SECOND_PLAYER_LEFT_KEY = Resource.getString("controller.second_player_left_key");
    private static final String SECOND_PLAYER_RIGHT_KEY = Resource.getString("controller.second_player_right_key");
    private static final String PAUSE = Resource.getString("controller.pause");
    private static final String RESUME = Resource.getString("controller.resume");

    Model model;

    public Controller(Model model) {
        this.model = model;

    }

    public void impact(String keyText) {
        if (keyText.equalsIgnoreCase(FIRST_PLAYER_LEFT_KEY)) {
            this.model.moveFirstPlayer(TypeMovement.LEFT);
        }
        if (keyText.equalsIgnoreCase(FIRST_PLAYER_RIGHT_KEY)) {
            this.model.moveFirstPlayer(TypeMovement.RIGHT);
        }
        if (keyText.equalsIgnoreCase(SECOND_PLAYER_LEFT_KEY)) {
            this.model.moveSecondPlayer(TypeMovement.LEFT);
        }
        if (keyText.equalsIgnoreCase(SECOND_PLAYER_RIGHT_KEY)) {
            this.model.moveSecondPlayer(TypeMovement.RIGHT);
        }
        if (keyText.equalsIgnoreCase(PAUSE)) {
            model.stop();
        }
        if (keyText.equalsIgnoreCase(RESUME)) {
            model.resume();
        }

    }

    public void run() {
        model.run();
    }

    public void stop() {
        model.stop();
    }
}
