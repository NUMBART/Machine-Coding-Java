package com.pandey.constants;

import lombok.Getter;

@Getter
public enum Color {
    BLACK("B"),
    WHITE("W");

    private final String notation;
    Color(String notation) {
        this.notation = notation;
    }
}
