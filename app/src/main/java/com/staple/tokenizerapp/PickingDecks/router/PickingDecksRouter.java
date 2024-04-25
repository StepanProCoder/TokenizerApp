package com.staple.tokenizerapp.PickingDecks.router;

import android.content.Intent;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.staple.tokenizerapp.EditingDecks.view.EditingDecksActivity;
import com.staple.tokenizerapp.PickingDecks.entity.Deck;
import com.staple.tokenizerapp.PickingDecks.view.PickingDecksActivity;

public class PickingDecksRouter
{
    private PickingDecksActivity view;
    public PickingDecksRouter(PickingDecksActivity view)
    {
        this.view = view;
    }

    public void routeToEditingDecks(Deck deck)
    {
        Intent intent = new Intent(view, EditingDecksActivity.class);
        GsonBuilder builder = new GsonBuilder();
        Gson gson = builder.create();
        intent.putExtra("deck", gson.toJson(deck));
        view.startActivity(intent);
    }
}
