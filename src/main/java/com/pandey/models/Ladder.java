package com.pandey.models;

import com.pandey.exceptions.InvalidLadderException;
import com.pandey.exceptions.InvalidSnakeException;

public class Ladder extends Pipe {
    public Ladder(int start, int end) throws InvalidLadderException{
        super(start, end);
        if(start <= end)
            throw new InvalidLadderException();
    }
}
