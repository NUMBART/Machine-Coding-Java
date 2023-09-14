package com.pandey.models;

import com.pandey.exceptions.InvalidSnakeException;

public class Snake extends Pipe {
    public Snake(int start, int end) throws InvalidSnakeException {
        super(start, end);
        if(start <= end)
            throw new InvalidSnakeException();
    }
}
