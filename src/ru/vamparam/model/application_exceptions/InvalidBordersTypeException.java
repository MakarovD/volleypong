package ru.vamparam.model.application_exceptions;

/**
 * Исключение, возникающее при задании неверного типа границы поля
 * Created by Vamparam
 */
public class InvalidBordersTypeException extends Exception {
    public InvalidBordersTypeException() {
    }

    public InvalidBordersTypeException(String message) {

        super(message);
    }

    public InvalidBordersTypeException(String message, Throwable exception) {

        super(message, exception);
    }

    public InvalidBordersTypeException(Throwable exception) {

        super(exception);
    }
}
