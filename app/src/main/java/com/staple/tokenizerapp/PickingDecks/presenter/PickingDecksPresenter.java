package com.staple.tokenizerapp.PickingDecks.presenter;

import com.staple.tokenizerapp.PickingDecks.entity.Deck;
import com.staple.tokenizerapp.PickingDecks.interactor.PickingDecksInteractor;
import com.staple.tokenizerapp.PickingDecks.router.PickingDecksRouter;
import com.staple.tokenizerapp.PickingDecks.view.PickingDecksActivity;
import com.staple.tokenizerapp.PickingDecks.view.DeckActionsFragment;

public class PickingDecksPresenter
{
    private PickingDecksActivity view;
    private PickingDecksInteractor interactor;
    private PickingDecksRouter router;
    public PickingDecksPresenter(PickingDecksActivity view)
    {
        this.view = view;
        this.interactor = new PickingDecksInteractor(view.getAdapter());
        this.router = new PickingDecksRouter(view);
    }

    public void onDeckClick(Deck deck)
    {
        if (deck.getName() == null)
        {
            router.routeToEditingDecks(deck);
            return;
        }
        DeckActionsFragment deckActionsFragment = new DeckActionsFragment(() -> { router.routeToEditingDecks(deck); });
        deckActionsFragment.show(view.getSupportFragmentManager(), "deck_actions_dialog");
    }
}
