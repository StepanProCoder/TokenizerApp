package com.staple.tokenizerapp.PickingPermanent.interactor;

import android.util.Log;

import com.staple.tokenizerapp.PickingDecks.entity.Card;
import com.staple.tokenizerapp.PickingDecks.entity.Deck;
import com.staple.tokenizerapp.PickingPermanent.entity.Emblem;
import com.staple.tokenizerapp.PickingPermanent.entity.Permcounter;
import com.staple.tokenizerapp.PickingPermanent.entity.Playercounter;
import com.staple.tokenizerapp.PickingPermanent.entity.Token;
import com.staple.tokenizerapp.PickingPermanent.view.PermanentAdapter;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class PickingPermanentInteractor
{
    private List<Card> cardList;
    private List<Token> tokenList;
    private List<Emblem> emblemList;
    private List<Permcounter> permcounterList;
    private List<Playercounter> playercounterList;
    private PermanentAdapter adapter;
    private PermApi permApi;
    private int mode;


    public PickingPermanentInteractor(PermanentAdapter adapter, int mode)
    {
        this.mode = mode;

        this.cardList = new ArrayList<>();
        this.tokenList = new ArrayList<>();
        this.emblemList = new ArrayList<>();
        this.permcounterList = new ArrayList<>();
        this.playercounterList = new ArrayList<>();

        this.adapter = adapter;
        this.adapter.setCardList(cardList);
        this.adapter.setTokenList(tokenList);
        this.adapter.setEmblemList(emblemList);
        this.adapter.setPermcounterList(permcounterList);
        this.adapter.setPlayercounterList(playercounterList);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://10.0.2.2:8080/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        permApi = retrofit.create(PermApi.class);
        requestPermanents();
    }

    public void requestPermanents()
    {
        if(mode == 1) {
            Call<List<Card>> call = permApi.permGetCards();
            call.enqueue(new Callback<List<Card>>() {
                @Override
                public void onResponse(Call<List<Card>> call, Response<List<Card>> response) {
                    cardList.clear();
                    cardList.addAll(response.body());
                    adapter.notifyDataSetChanged();
                }

                @Override
                public void onFailure(Call<List<Card>> call, Throwable t) {
                    Log.d("RETROFIT", t.getMessage());
                    // Обработка ошибки
                }
            });
        }
        else if(mode == 2) {
            Call<List<Token>> call = permApi.permGetTokens();
            call.enqueue(new Callback<List<Token>>() {
                @Override
                public void onResponse(Call<List<Token>> call, Response<List<Token>> response) {
                    tokenList.clear();
                    tokenList.addAll(response.body());
                    adapter.notifyDataSetChanged();
                }

                @Override
                public void onFailure(Call<List<Token>> call, Throwable t) {
                    Log.d("RETROFIT", t.getMessage());
                    // Обработка ошибки
                }
            });
        }
        else if(mode == 3) {
            Call<List<Emblem>> call = permApi.permGetEmblems();
            call.enqueue(new Callback<List<Emblem>>() {
                @Override
                public void onResponse(Call<List<Emblem>> call, Response<List<Emblem>> response) {
                    emblemList.clear();
                    emblemList.addAll(response.body());
                    adapter.notifyDataSetChanged();
                }

                @Override
                public void onFailure(Call<List<Emblem>> call, Throwable t) {
                    Log.d("RETROFIT", t.getMessage());
                    // Обработка ошибки
                }
            });
        }
        else if(mode == 4) {
            Call<List<Permcounter>> call = permApi.permGetPermcounters();
            call.enqueue(new Callback<List<Permcounter>>() {
                @Override
                public void onResponse(Call<List<Permcounter>> call, Response<List<Permcounter>> response) {
                    permcounterList.clear();
                    permcounterList.addAll(response.body());
                    adapter.notifyDataSetChanged();
                }

                @Override
                public void onFailure(Call<List<Permcounter>> call, Throwable t) {
                    Log.d("RETROFIT", t.getMessage());
                    // Обработка ошибки
                }
            });
        }
        else if(mode == 5) {
            Call<List<Playercounter>> call = permApi.permGetPlayercounters();
            call.enqueue(new Callback<List<Playercounter>>() {
                @Override
                public void onResponse(Call<List<Playercounter>> call, Response<List<Playercounter>> response) {
                    playercounterList.clear();
                    playercounterList.addAll(response.body());
                    adapter.notifyDataSetChanged();
                }

                @Override
                public void onFailure(Call<List<Playercounter>> call, Throwable t) {
                    Log.d("RETROFIT", t.getMessage());
                    // Обработка ошибки
                }
            });
        }
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

    public void deleteCard(Card card) {

        Iterator<Card> iterator = cardList.iterator();
        while (iterator.hasNext())
        {
            Card item = iterator.next();
            if (item.getId() == card.getId())
            {
                iterator.remove();
            }
        }
        adapter.notifyDataSetChanged();

        Call<Boolean> call = permApi.deleteCard(card);
        call.enqueue(new Callback<Boolean>() {
            @Override
            public void onResponse(Call<Boolean> call, Response<Boolean> response) {

            }

            @Override
            public void onFailure(Call<Boolean> call, Throwable t) {

            }
        });
    }

    public void deleteToken(Token token) {
        Iterator<Token> iterator = tokenList.iterator();
        while (iterator.hasNext())
        {
            Token item = iterator.next();
            if (item.getId() == token.getId())
            {
                iterator.remove();
            }
        }
        adapter.notifyDataSetChanged();

        Call<Boolean> call = permApi.deleteToken(token);
        call.enqueue(new Callback<Boolean>() {
            @Override
            public void onResponse(Call<Boolean> call, Response<Boolean> response) {

            }

            @Override
            public void onFailure(Call<Boolean> call, Throwable t) {

            }
        });
    }

    public void deleteEmblem(Emblem emblem) {
        Iterator<Emblem> iterator = emblemList.iterator();
        while (iterator.hasNext())
        {
            Emblem item = iterator.next();
            if (item.getId() == emblem.getId())
            {
                iterator.remove();
            }
        }
        adapter.notifyDataSetChanged();

        Call<Boolean> call = permApi.deleteEmblem(emblem);
        call.enqueue(new Callback<Boolean>() {
            @Override
            public void onResponse(Call<Boolean> call, Response<Boolean> response) {

            }

            @Override
            public void onFailure(Call<Boolean> call, Throwable t) {

            }
        });
    }

    public void deletePermcounter(Permcounter permcounter) {
        Iterator<Permcounter> iterator = permcounterList.iterator();
        while (iterator.hasNext())
        {
            Permcounter item = iterator.next();
            if (item.getId() == permcounter.getId())
            {
                iterator.remove();
            }
        }
        adapter.notifyDataSetChanged();

        Call<Boolean> call = permApi.deletePermcounter(permcounter);
        call.enqueue(new Callback<Boolean>() {
            @Override
            public void onResponse(Call<Boolean> call, Response<Boolean> response) {

            }

            @Override
            public void onFailure(Call<Boolean> call, Throwable t) {

            }
        });
    }

    public void deletePlayercounter(Playercounter playercounter) {
        Iterator<Playercounter> iterator = playercounterList.iterator();
        while (iterator.hasNext())
        {
            Playercounter item = iterator.next();
            if (item.getId() == playercounter.getId())
            {
                iterator.remove();
            }
        }
        adapter.notifyDataSetChanged();

        Call<Boolean> call = permApi.deletePlayercounter(playercounter);
        call.enqueue(new Callback<Boolean>() {
            @Override
            public void onResponse(Call<Boolean> call, Response<Boolean> response) {

            }

            @Override
            public void onFailure(Call<Boolean> call, Throwable t) {

            }
        });
    }
}
