package com.staple.tokenizerapp.EditingDecks.router;

import android.content.Intent;

import androidx.activity.result.ActivityResultLauncher;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.staple.tokenizerapp.EditingDecks.view.EditingDecksActivity;
import com.staple.tokenizerapp.PickingCards.view.PickingCardsActivity;

public class EditingDecksRouter
{
    private EditingDecksActivity view;
    private ActivityResultLauncher<Intent> cardsResultLauncher;
    public EditingDecksRouter(EditingDecksActivity view, ActivityResultLauncher<Intent> cardsResultLauncher)
    {
        this.view = view;
        this.cardsResultLauncher = cardsResultLauncher;
    }

    public void routeToPickingCards()
    {
        Intent intent = new Intent(view, PickingCardsActivity.class);
        cardsResultLauncher.launch(intent);
    }

}
