package com.pandey.exceptions;

public class InvalidSnakeException extends Exception {
    public InvalidSnakeException() {
        super("Snake's head must be above its tail!");
    }
}
