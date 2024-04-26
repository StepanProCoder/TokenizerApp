package com.staple.tokenizerapp.EditingDecks.router;

import android.content.Intent;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.staple.tokenizerapp.EditingDecks.view.EditingDecksActivity;
import com.staple.tokenizerapp.PickingCards.view.PickingCardsActivity;

public class EditingDecksRouter
{
    private EditingDecksActivity view;
    public EditingDecksRouter(EditingDecksActivity view)
    {
        this.view = view;
    }

    public void routeToPickingCards()
    {
        Intent intent = new Intent(view, PickingCardsActivity.class);
        view.startActivity(intent);
    }

}
