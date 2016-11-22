package ru.vamparam.voleypong;

import ru.vamparam.controller.Controller;
import ru.vamparam.model.Model;
import ru.vamparam.view.StartFrame;
import ru.vamparam.view.View;

import java.util.Locale;

public class Volleypong {
    public static void main(String[] args) {
        Locale.setDefault(Locale.US);
        Model model = new Model();
        Controller controller = new Controller(model);
        View view = new View(model, controller);
        StartFrame start = new StartFrame(view);
    }

}
