package com.pandey.models.screens;

import com.pandey.models.Card;
import com.pandey.models.AtmMachine;
import com.pandey.services.CardService;

import java.util.Scanner;

public class EnterPinScreen implements Screen {
    AtmMachine atmMachine;
    CardService cardService;

    public EnterPinScreen(AtmMachine atmMachine) {
        cardService = new CardService();
        this.atmMachine = atmMachine;
    }

    @Override
    public void enterCard(boolean validCard) {

    }

    @Override
    public void enterPin(String pin) {
        if(cardService.isValidPin(this.atmMachine.getCard(), pin))
            atmMachine.setScreen(new EnterAmountScreen(this.atmMachine));
        else
            atmMachine.setScreen(new CollectCardScreen(this.atmMachine));
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
        return null;
    }

    @Override
    public void prompt() {
        System.out.println("Please enter your pin: ");
        Scanner sc = new Scanner(System.in);
        String input = sc.nextLine();
        atmMachine.enterPin(input);
    }
}
