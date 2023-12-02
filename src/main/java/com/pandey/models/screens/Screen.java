package com.pandey.models.screens;

import com.pandey.models.Card;

public interface Screen {
    void enterCard(boolean validCard); // void enterCard(Card card);
    void enterPin(String pin);
    void enterAmount(Integer amount);
    Card collectCard();
    Integer collectCash();
    void prompt();
}
