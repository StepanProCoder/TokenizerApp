package com.staple.tokenizerapp.PickingDecks.interactor;

import android.util.Log;

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

public class PickingDecksInteractor
{
    private List<Deck> deckList;
    private DeckAdapter adapter;
    private DeckApi deckApi;

    public PickingDecksInteractor(DeckAdapter adapter)
    {
        this.deckList = new ArrayList<>();;
        this.adapter = adapter;
        this.adapter.setDeckList(deckList);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://192.168.43.156:8080/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        deckApi = retrofit.create(DeckApi.class);
        fetchDecks();
    }

    private void fetchDecks()
    {
        Call<List<Deck>> call = deckApi.getDecks();
        call.enqueue(new Callback<List<Deck>>() {
            @Override
            public void onResponse(Call<List<Deck>> call, Response<List<Deck>> response) {
                Log.d("RETROFIT","HERE");
                if (!response.isSuccessful()) {
                    // Обработка ошибки
                    return;
                }
                deckList.addAll(response.body());
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onFailure(Call<List<Deck>> call, Throwable t) {
                Log.d("RETROFIT",t.getMessage());
                // Обработка ошибки
            }
        });
    }
}
