package com.pandey.models.moveValidators;

import com.pandey.exceptions.InvalidMoveException;
import com.pandey.models.Board;
import com.pandey.models.Move;

public class QueenMoveValidator implements MoveValidator {
    @Override
    public Boolean validate(Move move, Board board) throws InvalidMoveException {
        RookMoveValidator rookMoveValidator = new RookMoveValidator();
        BishopMoveValidator bishopMoveValidator = new BishopMoveValidator();
        try {
            if(rookMoveValidator.validate(move, board)) return true;
        } catch (InvalidMoveException ex ) {}
        try {
            if(bishopMoveValidator.validate(move, board)) return true;
        } catch(InvalidMoveException ex) {}
        throw new InvalidMoveException();
    }
}
