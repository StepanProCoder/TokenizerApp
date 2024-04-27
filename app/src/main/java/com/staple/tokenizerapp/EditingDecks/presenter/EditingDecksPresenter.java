package com.staple.tokenizerapp.EditingDecks.presenter;

import android.content.Intent;

import androidx.activity.result.ActivityResultLauncher;

import com.staple.tokenizerapp.EditingDecks.entity.DeckChanges;
import com.staple.tokenizerapp.EditingDecks.interactor.EditingDecksInteractor;
import com.staple.tokenizerapp.EditingDecks.router.EditingDecksRouter;
import com.staple.tokenizerapp.EditingDecks.view.CardActionsFragment;
import com.staple.tokenizerapp.EditingDecks.view.EditingDecksActivity;
import com.staple.tokenizerapp.PickingDecks.entity.Card;
import com.staple.tokenizerapp.PickingDecks.entity.Deck;

public class EditingDecksPresenter
{
    private EditingDecksActivity view;
    private EditingDecksInteractor interactor;
    private EditingDecksRouter router;
    private Deck currentDeck;
    private DeckChanges deckChanges;
    public EditingDecksPresenter(EditingDecksActivity view, Deck currentDeck, ActivityResultLauncher<Intent> cardsResultLauncher)
    {
        this.deckChanges = new DeckChanges();
        this.currentDeck = currentDeck;
        this.view = view;
        this.interactor = new EditingDecksInteractor(view.getAdapter(), currentDeck, deckChanges);
        this.router = new EditingDecksRouter(view, cardsResultLauncher);
    }

    public void onCardClick(Card card)
    {
        if (card.getName() == null)
        {
            router.routeToPickingCards(); //add a card
        }
        else
        {
            CardActionsFragment cardActionsFragment = new CardActionsFragment(() -> { interactor.deleteCardFromDeck(card); });
            cardActionsFragment.show(view.getSupportFragmentManager(), "card_actions_dialog");
        }
    }

    public void onSendingChanges()
    {
        String newName = view.getChangedDeckName();
        if (!newName.isEmpty())
        {
            deckChanges.setNewName(newName);
        }

        if(currentDeck.getName() != null)
        {
            deckChanges.setDeckId(currentDeck.getId());
        }

        interactor.sendChanges();
    }

    public void onQueryTextChange(String text)
    {
        interactor.filterList(text);
    }

    public void onAddCard(Card chosenCard)
    {
        interactor.addCardToDeck(chosenCard);
    }
}

