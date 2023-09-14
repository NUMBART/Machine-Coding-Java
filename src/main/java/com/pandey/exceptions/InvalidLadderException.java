package com.pandey.exceptions;

public class InvalidLadderException extends Exception {
    public InvalidLadderException() {
        super("Ladder's start should be below its end!");
    }
}
