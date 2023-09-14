package com.pandey.models;

import java.util.Random;

public class SingleDie implements Die {
    @Override
    public Integer roll() {
        Random random = new Random();
        return random.nextInt(6)+1;
    }
}
