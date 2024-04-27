package com.staple.tokenizerapp.PickingDecks.interactor;

import android.util.Log;

import com.staple.tokenizerapp.PickingDecks.entity.Card;
import com.staple.tokenizerapp.PickingDecks.entity.Deck;
import com.staple.tokenizerapp.PickingDecks.view.DeckAdapter;

import java.util.ArrayList;
import java.util.Iterator;
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
                .baseUrl("http://10.0.2.2:8080/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        deckApi = retrofit.create(DeckApi.class);
        fetchDecks();
    }

    public void fetchDecks()
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
                deckList.clear();
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

    public void filterList(String text)
    {
        List<Deck> filteredList = new ArrayList<Deck>();
        for (Deck item: deckList)
        {
            if(item.getName().toLowerCase().contains(text.toLowerCase()))
            {
                filteredList.add(item);
            }
        }

        adapter.setDeckList(filteredList);
        adapter.notifyDataSetChanged();
    }

    public void deleteDeck(Deck deck)
    {
        Iterator<Deck> iterator = deckList.iterator();
        while (iterator.hasNext())
        {
            Deck item = iterator.next();
            if (item.getId() == deck.getId())
            {
                iterator.remove();
            }
        }
        adapter.notifyDataSetChanged();

        Call<Boolean> call = deckApi.deleteDeck(deck);
        call.enqueue(new Callback<Boolean>() {
            @Override
            public void onResponse(Call<Boolean> call, Response<Boolean> response) {
                Log.d("DELETE DECK", "ALL SET");
            }

            @Override
            public void onFailure(Call<Boolean> call, Throwable t) {
                Log.d("DELETE DECK", "ERR");
            }
        });
    }
}
