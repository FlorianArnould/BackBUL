package fr.bul.backend.activities;

/**
 * @author toinou
 */

public class ActivityException extends Exception {
    ActivityException(String message, Exception e) {
        super(message, e);
    }

    ActivityException(String message) {
        super(message);
    }
}
