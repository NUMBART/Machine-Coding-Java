package com.pandey.models;

import com.pandey.models.screens.EnterCardScreen;
import com.pandey.models.screens.Screen;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AtmMachine {
    Card card;
    Screen screen;
    Integer amountRequested;
    TransactionHandler transactionHandler;
    public AtmMachine() {
        this.screen = new EnterCardScreen(this);
        this.transactionHandler = new TransactionHandler();
        card = null;
    }

    public void start() {
        while(transactionHandler.availableCash > 0) {
            screen.prompt();
        }
        System.out.println("No available cash. Goodbye!");
    }
    public void enterCard(boolean validCard) {
        screen.enterCard(validCard);
    }
    public void enterPin(String pin) {
        screen.enterPin(pin);
    }
    public void enterAmount(Integer amount) {
        screen.enterAmount(amount);
    }
    public Card collectCard() {
        return screen.collectCard();
    }
    public Integer collectCash() {
        return screen.collectCash();
    }
}
