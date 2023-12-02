package com.pandey.models.screens;

import com.pandey.models.Card;
import com.pandey.models.AtmMachine;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class CollectCardScreen implements Screen {
    AtmMachine atmMachine;

    @Override
    public void enterCard(boolean validCard) {

    }

    @Override
    public void enterPin(String pin) {

    }

    @Override
    public void enterAmount(Integer amount) {

    }

    @Override
    public Card collectCard() {
        Card card = this.atmMachine.getCard();
        this.atmMachine.setCard(null);
        this.atmMachine.setScreen(new EnterCardScreen(this.atmMachine));
        return card;
    }

    @Override
    public Integer collectCash() {
        return null;
    }

    @Override
    public void prompt() {
        System.out.println("Please collect your card");
        atmMachine.collectCard();
    }
}
