package com.pandey.models.screens;

import com.pandey.models.AtmMachine;
import com.pandey.models.Card;
import lombok.AllArgsConstructor;

import java.util.Scanner;

@AllArgsConstructor
public class CollectCashScreen implements Screen {
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
        return null;
    }

    @Override
    public Integer collectCash() {
        int requestedAmount = atmMachine.getAmountRequested();
        atmMachine.getTransactionHandler().dispense(requestedAmount);
        atmMachine.setScreen(new CollectCardScreen(atmMachine));
        return requestedAmount;
    }

    @Override
    public void prompt() {
        System.out.println("Please collect your cash");
        atmMachine.collectCash();
    }
}
