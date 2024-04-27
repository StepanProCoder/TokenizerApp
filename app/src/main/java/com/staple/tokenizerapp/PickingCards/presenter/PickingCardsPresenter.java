package com.staple.tokenizerapp.PickingCards.presenter;

import com.staple.tokenizerapp.EditingDecks.interactor.EditingDecksInteractor;
import com.staple.tokenizerapp.EditingDecks.view.EditingDecksActivity;
import com.staple.tokenizerapp.PickingCards.interactor.PickingCardsInteractor;
import com.staple.tokenizerapp.PickingCards.view.PickingCardsActivity;
import com.staple.tokenizerapp.PickingDecks.entity.Card;
import com.staple.tokenizerapp.PickingDecks.entity.Deck;

public class PickingCardsPresenter
{
    private PickingCardsActivity view;
    private PickingCardsInteractor interactor;
    public PickingCardsPresenter(PickingCardsActivity view, Deck currentDeck)
    {
        this.view = view;
        this.interactor = new PickingCardsInteractor(view.getAdapter(), currentDeck);
    }

    public void onCardClick(Card card)
    {
        //TODO on card click
    }

    public void onQueryTextChange(String text)
    {
        interactor.filterList(text);
    }

}

