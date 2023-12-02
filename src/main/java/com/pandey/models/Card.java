package com.pandey.models;

import lombok.AllArgsConstructor;

import java.util.Random;

public class Card {
    int cardNumber;
    boolean isValid;
    public Card(boolean isValid) {
        this.cardNumber = new Random().nextInt(90000) + 10000;
        this.isValid = isValid;
    }
    public boolean isValid() {
        return isValid;
    }
}
