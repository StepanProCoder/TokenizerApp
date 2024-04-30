package com.staple.tokenizerapp.VirtualTableTop.presenter;

import android.content.Intent;
import android.util.Log;

import androidx.activity.result.ActivityResultLauncher;

import com.staple.tokenizerapp.PickingDecks.entity.Card;
import com.staple.tokenizerapp.PickingDecks.entity.Deck;
import com.staple.tokenizerapp.VirtualTableTop.interactor.VirtualTableTopInteractor;
import com.staple.tokenizerapp.VirtualTableTop.router.VirtualTableTopRouter;
import com.staple.tokenizerapp.VirtualTableTop.view.VirtualTableTopActivity;

import java.util.List;

public class VirtualTableTopPresenter
{
    private VirtualTableTopActivity view;
    private VirtualTableTopInteractor interactor;
    private VirtualTableTopRouter router;
    private Deck currentDeck;

    public VirtualTableTopPresenter(VirtualTableTopActivity view, Deck currentDeck, ActivityResultLauncher<Intent> cardsResultLauncher)
    {
        this.view = view;
        this.interactor = new VirtualTableTopInteractor();
        this.router = new VirtualTableTopRouter(view, cardsResultLauncher);
        this.currentDeck = currentDeck;
    }

    public void onPlacingNewCards()
    {
        router.routeToPickingCards(currentDeck);
    }

    public void onAddCard(Card chosenCard)
    {
        interactor.getAllCardPermanents(chosenCard, new VirtualTableTopInteractor.AllCardPermanents() {
            @Override
            public void onResult(List<String> result) {
                for (String item: result)
                {
                    if (item != null)
                    {
                        view.addCardToLayout(item);
                    }
                }
            }
        });
    }
}
