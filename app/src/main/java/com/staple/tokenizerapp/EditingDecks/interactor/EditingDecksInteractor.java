package com.staple.tokenizerapp.EditingDecks.interactor;

import android.util.Log;

import com.staple.tokenizerapp.EditingDecks.entity.DeckChanges;
import com.staple.tokenizerapp.EditingDecks.view.CardAdapter;
import com.staple.tokenizerapp.PickingDecks.entity.Card;
import com.staple.tokenizerapp.PickingDecks.entity.Deck;

import java.util.ArrayList;
import java.util.Iterator;
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
    private DeckChanges deckChanges;

    public EditingDecksInteractor(CardAdapter adapter, Deck currentDeck, DeckChanges deckChanges)
    {
        this.deckChanges = deckChanges;
        this.currentDeck = currentDeck;
        this.cardList = new ArrayList<>();
        this.adapter = adapter;
        this.adapter.setCardList(cardList);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://10.0.2.2:8080/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        cardApi = retrofit.create(CardApi.class);
        requestChosenDeck(currentDeck);
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

    public void filterList(String text)
    {
        List<Card> filteredList = new ArrayList<Card>();
        for (Card item: cardList)
        {
            if(item.getName().toLowerCase().contains(text.toLowerCase()))
            {
                filteredList.add(item);
            }
        }

        adapter.setCardList(filteredList);
        adapter.notifyDataSetChanged();

    }

    public void deleteCardFromDeck(Card card)
    {
        Iterator<Card> iterator = cardList.iterator();
        while (iterator.hasNext())
        {
            Card item = iterator.next();
            if (item.getId() == card.getId())
            {
                deckChanges.getDeletedCards().add(item);
                iterator.remove();
            }
        }
        adapter.notifyDataSetChanged();
    }

    public void addCardToDeck(Card chosenCard)
    {
        Iterator<Card> iterator = cardList.iterator();
        while (iterator.hasNext())
        {
            Card item = iterator.next();
            if (item.getId() == chosenCard.getId())
            {
                return;
            }
        }
        deckChanges.getAddedCards().add(chosenCard);
        cardList.add(chosenCard);
        adapter.notifyDataSetChanged();
    }

    public void sendChanges()
    {
        Call<Boolean> call = cardApi.postDeckChanges(deckChanges);
        call.enqueue(new Callback<Boolean>() {
            @Override
            public void onResponse(Call<Boolean> call, Response<Boolean> response) {
                Log.d("DECK CHANGE", "ALL SET");
            }

            @Override
            public void onFailure(Call<Boolean> call, Throwable t) {
                Log.d("DECK CHANGE", "ERR");
            }
        });
    }
}
