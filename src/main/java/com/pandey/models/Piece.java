package com.pandey.models;

import com.pandey.constants.Color;
import com.pandey.constants.PieceType;
import com.pandey.models.moveValidators.MoveValidator;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class Piece {
    Color color;
    PieceType pieceType;
}
