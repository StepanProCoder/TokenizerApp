package com.staple.tokenizerapp.PickingDecks.interactor;

import com.staple.tokenizerapp.PickingDecks.entity.Card;
import com.staple.tokenizerapp.PickingDecks.entity.Deck;

import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface DeckApi
{
    @GET("decks/")
    Call<List<Deck>> getDecks();

    @POST("deletedeck/")
    Call<Boolean> deleteDeck(@Body Deck deck);
}

