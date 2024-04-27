package com.staple.tokenizerapp.PickingCards.presenter;

import android.app.Activity;
import android.content.Intent;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
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
        Intent resultIntent = new Intent();
        GsonBuilder builder = new GsonBuilder();
        Gson gson = builder.create();
        resultIntent.putExtra("card", gson.toJson(card));
        view.setResult(Activity.RESULT_OK, resultIntent);
        view.finish();
    }

    public void onQueryTextChange(String text)
    {
        interactor.filterList(text);
    }

}

