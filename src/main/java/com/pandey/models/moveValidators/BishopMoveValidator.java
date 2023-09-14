package com.pandey.models.moveValidators;

import com.pandey.exceptions.InvalidMoveException;
import com.pandey.models.Board;
import com.pandey.models.Move;
import com.pandey.models.Piece;

import static java.lang.Math.abs;

public class BishopMoveValidator implements MoveValidator {
    @Override
    public Boolean validate(Move move, Board board) throws InvalidMoveException {
        int sx = move.getStartX(), sy = move.getStartY(), ex = move.getEndX(), ey = move.getEndY();
        if((ex-sx != ey-sy) && (ex+ey != sx+sy))
            throw new InvalidMoveException();
        int dx = ex == sx ? 0 : (ex-sx)/abs(ex-sx), dy = ey == sy ? 0 : (ey-sy)/abs(ey-sy);
        for(int curx = sx, cury = sy; curx <= ex && cury <= ey; curx += dx, cury += dy) {
            if((curx == sx && cury == sy) || (curx == ex && cury == ey))
                continue;
            Piece curPiece = board.getBoard().get(curx).get(cury);
            if(curPiece != null)
                throw new InvalidMoveException();
        }
        return true;
    }
}
