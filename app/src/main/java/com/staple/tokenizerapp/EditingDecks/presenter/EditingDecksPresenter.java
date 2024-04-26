package com.staple.tokenizerapp.EditingDecks.presenter;

import com.staple.tokenizerapp.EditingDecks.entity.DeckChanges;
import com.staple.tokenizerapp.EditingDecks.interactor.EditingDecksInteractor;
import com.staple.tokenizerapp.EditingDecks.router.EditingDecksRouter;
import com.staple.tokenizerapp.EditingDecks.view.EditingDecksActivity;
import com.staple.tokenizerapp.PickingDecks.entity.Card;
import com.staple.tokenizerapp.PickingDecks.entity.Deck;

public class EditingDecksPresenter
{
    private EditingDecksActivity view;
    private EditingDecksInteractor interactor;
    private EditingDecksRouter router;
    public EditingDecksPresenter(EditingDecksActivity view, Deck currentDeck)
    {
        this.view = view;
        this.interactor = new EditingDecksInteractor(view.getAdapter(), currentDeck);
        this.router = new EditingDecksRouter(view);
    }

    public void onCardClick(Card card)
    {
        if (card.getName() == null)
        {
            router.routeToPickingCards(); //add a card
        }
        else
        {
            //TODO on card click
        }
    }

    public void sendChanges()
    {
        //TODO send changes
    }
}

