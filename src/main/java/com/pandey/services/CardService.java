package com.pandey.services;

import com.pandey.models.Card;
import com.pandey.repositories.CardRepository;

public class CardService {
    CardRepository cardRepository;
    public CardService() {
        cardRepository = new CardRepository();
    }
    public boolean isValidPin(Card card, String pin) {
        return pin.equals(cardRepository.fetchPin(card));
    }
}
