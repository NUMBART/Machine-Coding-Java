package com.pandey.exceptions;

public class MultiplePipeStartException extends Exception {
    public MultiplePipeStartException() {
        super("Only one pipe can start in a board cell");
    }
}