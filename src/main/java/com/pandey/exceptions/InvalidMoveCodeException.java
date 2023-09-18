package com.pandey.exceptions;

public class InvalidMoveCodeException extends Exception {
    public InvalidMoveCodeException() {
        super("Move code must be between 0-3.");
    }
}
