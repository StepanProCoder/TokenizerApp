package com.staple.tokenizerapp.PickingDecks.presenter;

import com.staple.tokenizerapp.PickingDecks.entity.Deck;
import com.staple.tokenizerapp.PickingDecks.interactor.PickingDecksInteractor;
import com.staple.tokenizerapp.PickingDecks.router.PickingDecksRouter;
import com.staple.tokenizerapp.PickingDecks.view.PickingDecksActivity;

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
        router.routeToEditingDecks(deck);
    }
}
