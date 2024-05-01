package com.staple.tokenizerapp.PickingPermanent.router;

import android.content.Intent;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.staple.tokenizerapp.PickingCards.view.PickingCardsActivity;
import com.staple.tokenizerapp.PickingDecks.entity.Card;
import com.staple.tokenizerapp.PickingPermanent.entity.Emblem;
import com.staple.tokenizerapp.PickingPermanent.entity.Permcounter;
import com.staple.tokenizerapp.PickingPermanent.entity.Playercounter;
import com.staple.tokenizerapp.PickingPermanent.entity.Token;
import com.staple.tokenizerapp.PickingPermanent.view.PickingPermanentActivity;
import com.staple.tokenizerapp.VirtualTableTop.view.VirtualTableTopActivity;

public class PickingPermanentRouter
{
    private PickingPermanentActivity view;
    public PickingPermanentRouter(PickingPermanentActivity view)
    {
        this.view = view;
    }

    public void routeToEditingPerms(Card card, Token token, Emblem emblem, Permcounter permcounter, Playercounter playercounter)
    {
        Intent intent = new Intent(view, VirtualTableTopActivity.class); //HERE
        GsonBuilder builder = new GsonBuilder();
        Gson gson = builder.create();
        if (card != null) {
            intent.putExtra("card", gson.toJson(card));
        }
        else if (token != null) {
            intent.putExtra("token", gson.toJson(token));
        }
        else if (emblem != null) {
            intent.putExtra("emblem", gson.toJson(emblem));
        }
        else if (permcounter != null) {
            intent.putExtra("permcounter", gson.toJson(permcounter));
        }
        else if (playercounter != null) {
            intent.putExtra("playercounter", gson.toJson(playercounter));
        }
        view.startActivity(intent);
    }

}
