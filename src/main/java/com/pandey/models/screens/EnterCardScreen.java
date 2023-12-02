package com.pandey.models.screens;

import com.pandey.models.Card;
import com.pandey.models.AtmMachine;
import lombok.AllArgsConstructor;

import java.util.Scanner;

@AllArgsConstructor
public class EnterCardScreen implements Screen {
    AtmMachine atmMachine;
    @Override
    public void enterCard(boolean isValidCard) {
        Card card = new Card(isValidCard);
        atmMachine.setCard(card);
        if(card.isValid())
            atmMachine.setScreen(new EnterPinScreen(this.atmMachine));
        else
            atmMachine.setScreen(new CollectCardScreen(this.atmMachine));
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
        return 0;
    }

    @Override
    public void prompt() {
        System.out.println("Please enter your mock card - vaild card(v), invalid card(i): ");
        Scanner sc = new Scanner(System.in);
        char input = sc.next().charAt(0);
        atmMachine.enterCard(input == 'v' ? true : false);
    }
}
