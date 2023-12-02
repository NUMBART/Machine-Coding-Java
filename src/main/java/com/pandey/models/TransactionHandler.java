package com.pandey.models;

public class TransactionHandler {
    int availableCash = 10000;
    public boolean isValidAmount(Integer amount) {
        return amount <= availableCash; // not checking for available balance in card
    }

    public Integer dispense(Integer requestedAmount) {
        if(isValidAmount(requestedAmount)) {
            availableCash -= requestedAmount;
            return requestedAmount;
        }
        return 0;
    }
}
