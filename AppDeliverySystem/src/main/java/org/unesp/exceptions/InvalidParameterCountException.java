package org.unesp.exceptions;

public class InvalidParameterCountException extends RuntimeException {
    public InvalidParameterCountException(String message) {
        super(message);
    }
}
