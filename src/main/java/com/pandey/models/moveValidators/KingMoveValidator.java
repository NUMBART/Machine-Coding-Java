package com.pandey.models.moveValidators;

import com.pandey.exceptions.InvalidMoveException;
import com.pandey.models.Board;
import com.pandey.models.Move;

import static java.lang.Math.abs;

public class KingMoveValidator implements MoveValidator {
    @Override
    public Boolean validate(Move move, Board board) throws InvalidMoveException {
        int sx = move.getStartX(), sy = move.getStartY(), ex = move.getEndX(), ey = move.getEndY();
        if(abs(ex-sx) == 1 && abs(ey-sy) == 1)
            return true;
        throw new InvalidMoveException();
    }
}
