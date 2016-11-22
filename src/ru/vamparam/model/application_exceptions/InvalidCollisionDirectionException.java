package ru.vamparam.model.application_exceptions;

/**
 * Исключение, возникающее при неизвестном типе столкновения
 * Created by Vamparam
 */
public class InvalidCollisionDirectionException extends Exception {

    public InvalidCollisionDirectionException() {
    }

    public InvalidCollisionDirectionException(String message) {

        super(message);
    }

    public InvalidCollisionDirectionException(String message, Throwable exception) {

        super(message, exception);
    }

    public InvalidCollisionDirectionException(Throwable exception) {

        super(exception);
    }
}
