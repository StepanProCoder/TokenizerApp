package com.staple.tokenizerapp.PickingPermanent.presenter;

import com.staple.tokenizerapp.PickingDecks.entity.Card;
import com.staple.tokenizerapp.PickingPermanent.entity.Emblem;
import com.staple.tokenizerapp.PickingPermanent.entity.Permcounter;
import com.staple.tokenizerapp.PickingPermanent.entity.Playercounter;
import com.staple.tokenizerapp.PickingPermanent.entity.Token;
import com.staple.tokenizerapp.PickingPermanent.interactor.PickingPermanentInteractor;
import com.staple.tokenizerapp.PickingPermanent.router.PickingPermanentRouter;
import com.staple.tokenizerapp.PickingPermanent.view.PermanentActionsFragment;
import com.staple.tokenizerapp.PickingPermanent.view.PickingPermanentActivity;

public class PickingPermanentPresenter
{
    private PickingPermanentActivity view;
    private PickingPermanentInteractor interactor;
    private PickingPermanentRouter router;
    public PickingPermanentPresenter(PickingPermanentActivity view, int mode)
    {
        this.view = view;
        this.interactor = new PickingPermanentInteractor(view.getAdapter(), mode);
        this.router = new PickingPermanentRouter(view);
    }

    public void onPermClick(Card card, Token token, Emblem emblem, Permcounter permcounter, Playercounter playercounter)
    {
        if (card != null) {
            if (card.getName() == null) {
                router.routeToEditingPerms(card, null, null, null, null); //add a card
            } else {
                PermanentActionsFragment permanentActionsFragment = new PermanentActionsFragment(() -> { router.routeToEditingPerms(card, null, null, null, null); }, () -> {
                    interactor.deleteCard(card);
                });
                permanentActionsFragment.show(view.getSupportFragmentManager(), "card_actions_dialog");
            }
        }
        else if (token != null)
        {
            if (token.getName() == null) {
                router.routeToEditingPerms(null, token, null, null, null); //add a card
            } else {
                PermanentActionsFragment permanentActionsFragment = new PermanentActionsFragment(() -> { router.routeToEditingPerms(null, token, null, null, null); }, () -> {
                    interactor.deleteToken(token);
                });
                permanentActionsFragment.show(view.getSupportFragmentManager(), "card_actions_dialog");
            }
        }
        else if (emblem != null)
        {
            if (emblem.getName() == null) {
                router.routeToEditingPerms(null, null, emblem, null, null); //add a card
            } else {
                PermanentActionsFragment permanentActionsFragment = new PermanentActionsFragment(() -> { router.routeToEditingPerms(null, null, emblem, null, null); }, () -> {
                    interactor.deleteEmblem(emblem);
                });
                permanentActionsFragment.show(view.getSupportFragmentManager(), "card_actions_dialog");
            }
        }
        else if (permcounter != null)
        {
            if (permcounter.getName() == null) {
                router.routeToEditingPerms(null, null, null, permcounter, null); //add a card
            } else {
                PermanentActionsFragment permanentActionsFragment = new PermanentActionsFragment(() -> { router.routeToEditingPerms(null, null, null, permcounter, null); }, () -> {
                    interactor.deletePermcounter(permcounter);
                });
                permanentActionsFragment.show(view.getSupportFragmentManager(), "card_actions_dialog");
            }
        }
        else if (playercounter != null)
        {
            if (playercounter.getName() == null) {
                router.routeToEditingPerms(null, null, null, null, playercounter); //add a card
            } else {
                PermanentActionsFragment permanentActionsFragment = new PermanentActionsFragment(() -> { router.routeToEditingPerms(null, null, null, null, playercounter); }, () -> {
                    interactor.deletePlayercounter(playercounter);
                });
                permanentActionsFragment.show(view.getSupportFragmentManager(), "card_actions_dialog");
            }
        }
    }

    public void onQueryTextChange(String text)
    {
        interactor.filterList(text);
    }

    public void onResume()
    {
        try {
            Thread.sleep(250);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        interactor.requestPermanents();
    }
}

