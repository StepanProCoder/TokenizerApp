package com.staple.tokenizerapp.EditingDecks.interactor;

import com.staple.tokenizerapp.PickingDecks.entity.Card;
import com.staple.tokenizerapp.PickingDecks.entity.Deck;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface CardApi
{
    @POST("decks/")
    Call<List<Card>> postChosenDeck(@Body Deck deck);
}
