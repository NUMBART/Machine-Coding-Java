package com.pandey.models;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Player {
    String name;
    int curPos = 0;

    public Player(String name) {
        this.name = name;
    }
}
