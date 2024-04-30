package com.staple.tokenizerapp.EditingDecks.interactor;

import com.staple.tokenizerapp.EditingDecks.entity.DeckChanges;
import com.staple.tokenizerapp.PickingDecks.entity.Card;
import com.staple.tokenizerapp.PickingDecks.entity.Deck;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface CardApi
{
    @POST("decks/")
    Call<List<Card>> postChosenDeck(@Body Deck deck);

    @GET("allcards/")
    Call<List<Card>> getAllCards();

    @POST("deckchange/")
    Call<Boolean> postDeckChanges(@Body DeckChanges deckChanges);

    @POST("allperm/")
    Call<List<String>> postPlacingCard(@Body Card card);
}
