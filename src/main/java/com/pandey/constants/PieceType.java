package com.pandey.constants;

import lombok.Getter;

@Getter
public enum PieceType {
    KING("K"),
    QUEEN("Q"),
    KNIGHT("N"),
    ROOK("R"),
    BISHOP("B"),
    PAWN("P");

    private final String notation;
    PieceType(String notation) {
        this.notation = notation;
    }
}
