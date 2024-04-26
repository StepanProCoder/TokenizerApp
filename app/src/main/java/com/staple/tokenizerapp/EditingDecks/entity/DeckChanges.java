package com.staple.tokenizerapp.EditingDecks.entity;

import com.staple.tokenizerapp.PickingDecks.entity.Card;

import java.util.List;

public class DeckChanges {
    private String deckId;
    private List<Card> deletedCards;
    private List<Card> addedCards;
    private String newName;

    public String getDeckId() {
        return deckId;
    }

    public List<Card> getDeletedCards() {
        return deletedCards;
    }

    public List<Card> getAddedCards() {
        return addedCards;
    }

    public String getNewName() {
        return newName;
    }

    public void setDeckId(String deckId) {
        this.deckId = deckId;
    }

    public void setDeletedCards(List<Card> deletedCards) {
        this.deletedCards = deletedCards;
    }

    public void setAddedCards(List<Card> addedCards) {
        this.addedCards = addedCards;
    }

    public void setNewName(String newName) {
        this.newName = newName;
    }
}

