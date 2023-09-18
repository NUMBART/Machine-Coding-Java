package com.pandey.constants;

import lombok.Getter;

@Getter
public enum GameStatus {
    INPROGRESS("Enter number to move, left(0), right(1), top(2), bottom(3): "),
    WON("Congratulations!"),
    LOST("Game Over!");

    public final String message;

    GameStatus(String message) {
        this.message = message;
    }
}
