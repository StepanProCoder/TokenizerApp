package com.staple.tokenizerapp.VirtualTableTop.interactor;

import com.staple.tokenizerapp.EditingDecks.interactor.CardApi;
import com.staple.tokenizerapp.PickingDecks.entity.Card;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class VirtualTableTopInteractor {

    public interface AllCardPermanents
    {
        void onResult(List<String> result);
    }

    private CardApi cardApi;

    public VirtualTableTopInteractor()
    {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://10.0.2.2:8080/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        cardApi = retrofit.create(CardApi.class);
    }
    public void getAllCardPermanents(Card card, AllCardPermanents handler)
    {
        Call<List<String>> call = cardApi.postPlacingCard(card);
        call.enqueue(new Callback<List<String>>() {
            @Override
            public void onResponse(Call<List<String>> call, Response<List<String>> response) {
                if (response.isSuccessful())
                {
                    handler.onResult(response.body());
                }
            }

            @Override
            public void onFailure(Call<List<String>> call, Throwable t) {

            }
        });
    }
}
