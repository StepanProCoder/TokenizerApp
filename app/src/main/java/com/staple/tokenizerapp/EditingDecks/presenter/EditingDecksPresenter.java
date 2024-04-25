package com.staple.tokenizerapp.EditingDecks.presenter;

import com.staple.tokenizerapp.EditingDecks.interactor.EditingDecksInteractor;
import com.staple.tokenizerapp.EditingDecks.view.EditingDecksActivity;
import com.staple.tokenizerapp.PickingDecks.entity.Card;
import com.staple.tokenizerapp.PickingDecks.entity.Deck;

public class EditingDecksPresenter
{
    private EditingDecksActivity view;
    private EditingDecksInteractor interactor;
    public EditingDecksPresenter(EditingDecksActivity view, Deck currentDeck)
    {
        this.view = view;
        this.interactor = new EditingDecksInteractor(view.getAdapter(), currentDeck);
    }

    public void onCardClick(Card card)
    {
        //TODO on card click
    }
}

