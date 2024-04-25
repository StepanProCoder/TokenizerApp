package com.staple.tokenizerapp.EditingDecks.interactor;

import android.util.Log;

import com.staple.tokenizerapp.EditingDecks.view.CardAdapter;
import com.staple.tokenizerapp.PickingDecks.entity.Card;
import com.staple.tokenizerapp.PickingDecks.entity.Deck;
import com.staple.tokenizerapp.PickingDecks.view.DeckAdapter;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class EditingDecksInteractor
{
    private List<Card> cardList;
    private CardAdapter adapter;
    private CardApi cardApi;
    private Deck currentDeck;

    public EditingDecksInteractor(CardAdapter adapter, Deck currentDeck)
    {
        this.currentDeck = currentDeck;
        this.cardList = new ArrayList<>();
        this.adapter = adapter;
        this.adapter.setCardList(cardList);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://192.168.43.156:8080/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        cardApi = retrofit.create(CardApi.class);
        requestClickedDeck(currentDeck);
    }

    public void requestClickedDeck(Deck deck)
    {
        Call<List<Card>> call = cardApi.postChosenDeck(deck);
        call.enqueue(new Callback<List<Card>>() {
            @Override
            public void onResponse(Call<List<Card>> call, Response<List<Card>> response) {
                cardList.addAll(response.body());
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onFailure(Call<List<Card>> call, Throwable t) {
                Log.d("RETROFIT",t.getMessage());
                // Обработка ошибки
            }
        });
    }
}
