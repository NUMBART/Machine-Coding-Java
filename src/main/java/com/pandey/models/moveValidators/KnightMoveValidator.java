package com.pandey.models.moveValidators;

import com.pandey.exceptions.InvalidMoveException;
import com.pandey.models.Board;
import com.pandey.models.Move;
import com.pandey.models.Piece;

import static java.lang.Math.abs;

public class KnightMoveValidator implements MoveValidator {
    @Override
    public Boolean validate(Move move, Board board) throws InvalidMoveException {
        int sx = move.getStartX(), sy = move.getStartY(), ex = move.getEndX(), ey = move.getEndY();
        if(sx == ex || sy == ey)
            throw new InvalidMoveException();
        if(abs(ex-sx) + abs(ey-sy) == 3)
            return true;
        throw new InvalidMoveException();
    }
}
