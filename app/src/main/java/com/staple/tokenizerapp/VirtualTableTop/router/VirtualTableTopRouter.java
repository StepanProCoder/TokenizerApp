package com.staple.tokenizerapp.VirtualTableTop.router;

import android.content.Intent;

import androidx.activity.result.ActivityResultLauncher;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.staple.tokenizerapp.PickingCards.view.PickingCardsActivity;
import com.staple.tokenizerapp.PickingDecks.entity.Deck;
import com.staple.tokenizerapp.VirtualTableTop.view.VirtualTableTopActivity;

public class VirtualTableTopRouter {

    private VirtualTableTopActivity view;
    private ActivityResultLauncher<Intent> cardsResultLauncher;

    public VirtualTableTopRouter(VirtualTableTopActivity view, ActivityResultLauncher<Intent> cardsResultLauncher)
    {
        this.view = view;
        this.cardsResultLauncher = cardsResultLauncher;
    }
    public void routeToPickingCards(Deck currentDeck)
    {
        Intent intent = new Intent(view, PickingCardsActivity.class);
        GsonBuilder builder = new GsonBuilder();
        Gson gson = builder.create();
        intent.putExtra("deck", gson.toJson(currentDeck));
        cardsResultLauncher.launch(intent);
    }
}
