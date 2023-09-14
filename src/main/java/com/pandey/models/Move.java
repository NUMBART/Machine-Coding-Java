package com.pandey.models;

import com.pandey.exceptions.InvalidMoveException;
import lombok.Getter;

@Getter
public class Move {
    int startX;
    int startY;
    int endX;
    int endY;

    private void checkCellString(String cell) throws InvalidMoveException {
        if(cell.length() != 2 || cell.charAt(0) < 'a' || cell.charAt(0) > 'h' || cell.charAt(1) < '1' || cell.charAt(1) > '8')
            throw new InvalidMoveException();
    }
    public Move(String startCell, String endCell) throws InvalidMoveException {
        checkCellString(startCell);
        checkCellString(endCell);
        startY = startCell.charAt(0) - 'a';
        startX = startCell.charAt(1) - '1';
        endY = endCell.charAt(0) - 'a';
        endX = endCell.charAt(1) - '1';
    }
}
