package com.pandey.models.moveValidators;

import com.pandey.constants.PieceType;

public class MoveValidatorFactory {
    public static MoveValidator createValidator(PieceType pieceType) {
        switch (pieceType) {
            case KING:
                return new KingMoveValidator();
            case QUEEN:
                return new QueenMoveValidator();
            case KNIGHT:
                return new KnightMoveValidator();
            case ROOK:
                return new RookMoveValidator();
            case BISHOP:
                return new BishopMoveValidator();
            case PAWN:
                return new PawnMoveValidator();
            default:
                return null;
        }
    }
}
