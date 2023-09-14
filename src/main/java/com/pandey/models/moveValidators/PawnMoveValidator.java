package com.pandey.models.moveValidators;

import com.pandey.constants.Color;
import com.pandey.exceptions.InvalidMoveException;
import com.pandey.models.Board;
import com.pandey.models.Move;
import com.pandey.models.Piece;

import static java.lang.Math.abs;

public class PawnMoveValidator implements MoveValidator {
    @Override
    public Boolean validate(Move move, Board board) throws InvalidMoveException {
        int sx = move.getStartX(), sy = move.getStartY(), ex = move.getEndX(), ey = move.getEndY();
        Piece startPiece = board.getBoard().get(sx).get(sy);
        Piece endPiece = board.getBoard().get(ex).get(ey);
        if(startPiece.getColor() == Color.WHITE && sx >= ex)
            throw new InvalidMoveException();
        if(startPiece.getColor() == Color.BLACK && sx <= ex)
            throw new InvalidMoveException();
        if(abs(ex-sx) == 1 && ey == sy)
            return true;
        if(abs(ex-sx) == 1 && abs(ey-sy) == 1 && endPiece != null && endPiece.getColor() != startPiece.getColor())
            return true;
        if(startPiece.getColor() == Color.WHITE && ex == 3 && sx == 1 && ey == sy)
            if(board.getBoard().get(2).get(sy) == null && board.getBoard().get(3).get(ey) == null)
                return true;
        if(startPiece.getColor() == Color.BLACK && ex == 4 && sx == 6 && ey == sy)
            if(board.getBoard().get(5).get(sy) == null && board.getBoard().get(4).get(ey) == null)
                return true;
        throw new InvalidMoveException();
    }
}
