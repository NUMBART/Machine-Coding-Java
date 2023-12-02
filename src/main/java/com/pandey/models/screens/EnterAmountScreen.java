package com.pandey.models.screens;

import com.pandey.models.Card;
import com.pandey.models.AtmMachine;

import java.util.Scanner;

public class EnterAmountScreen implements Screen {
    AtmMachine atmMachine;
    public EnterAmountScreen(AtmMachine atmMachine) {
        this.atmMachine = atmMachine;
    }

    @Override
    public void enterCard(boolean validCard) {

    }

    @Override
    public void enterPin(String pin) {

    }

    @Override
    public void enterAmount(Integer amount) {
        this.atmMachine.setAmountRequested(amount);
        if(this.atmMachine.getTransactionHandler().isValidAmount(amount))
            atmMachine.setScreen(new CollectCashScreen(this.atmMachine));
        else
            atmMachine.setScreen(new CollectCardScreen(this.atmMachine));
    }

    @Override
    public Card collectCard() {
        return null;
    }

    @Override
    public Integer collectCash() {
        return null;
    }

    @Override
    public void prompt() {
        System.out.println("Please enter your withdrawal amount: ");
        Scanner sc = new Scanner(System.in);
        Integer input = sc.nextInt();
        atmMachine.enterAmount(input);
    }
}
