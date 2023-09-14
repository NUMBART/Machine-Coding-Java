package com.pandey.models.moveValidators;

import com.pandey.exceptions.InvalidMoveException;
import com.pandey.models.Board;
import com.pandey.models.Move;

public interface MoveValidator {
    Boolean validate(Move move, Board board) throws InvalidMoveException;
}
