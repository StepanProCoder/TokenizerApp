package com.staple.tokenizerapp.PickingCards.interactor;

import android.util.Log;

import com.staple.tokenizerapp.EditingDecks.interactor.CardApi;
import com.staple.tokenizerapp.EditingDecks.view.CardAdapter;
import com.staple.tokenizerapp.PickingCards.view.PickingCardsAdapter;
import com.staple.tokenizerapp.PickingDecks.entity.Card;
import com.staple.tokenizerapp.PickingDecks.entity.Deck;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class PickingCardsInteractor
{
    private List<Card> cardList;
    private PickingCardsAdapter adapter;
    private CardApi cardApi;
    private Deck currentDeck;

    public PickingCardsInteractor(PickingCardsAdapter adapter, Deck currentDeck)
    {
        this.currentDeck = currentDeck;
        this.cardList = new ArrayList<>();
        this.adapter = adapter;
        this.adapter.setCardList(cardList);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://10.0.2.2:8080/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        cardApi = retrofit.create(CardApi.class);

        if (currentDeck == null)
        {
            requestAllCards();
        }
        else
        {
            requestChosenDeck(currentDeck);
        }
    }

    private void requestAllCards()
    {
        Log.d("REQUEST", "ALL CARDS");
        //TODO request all cards
    }

    public void requestChosenDeck(Deck deck)
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

