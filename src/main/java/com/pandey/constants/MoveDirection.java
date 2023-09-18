package com.pandey.constants;

import com.pandey.exceptions.InvalidMoveCodeException;
import lombok.Getter;

@Getter
public enum MoveDirection {
    LEFT(0),
    RIGHT(1),
    TOP(2),
    BOTTOM(3);

    private final Integer code;

    MoveDirection(Integer code) {
        this.code = code;
    }

    public static MoveDirection getByCode(Integer code) throws InvalidMoveCodeException {
        for(MoveDirection direction: MoveDirection.values()) {
            if(direction.getCode() == code) return direction;
        }
        throw new InvalidMoveCodeException();
    }
}
