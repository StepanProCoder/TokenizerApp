package com.staple.tokenizerapp.EditingPermanents.interactor;

import com.staple.tokenizerapp.EditingPermanents.entity.PermanentChanges;
import com.staple.tokenizerapp.PickingDecks.entity.Card;
import com.staple.tokenizerapp.PickingPermanent.interactor.PermApi;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class EditingPermanentsInteractor {

    private List<Card> bonds = new ArrayList<>();
    private PermApi permApi;

    public EditingPermanentsInteractor()
    {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://10.0.2.2:8080/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        permApi = retrofit.create(PermApi.class);
    }
    public void bondCard(Card chosenCard)
    {
        bonds.add(chosenCard);
    }

    public void submit(Long permId, String newName, String newPicUrl, int mode, Runnable lambda)
    {
        PermanentChanges permanentChanges = new PermanentChanges();

        permanentChanges.setPermId(permId);
        permanentChanges.setMode(mode);

        if (!newName.isEmpty())
        {
            permanentChanges.setNewName(newName);
        }

        if(!newPicUrl.isEmpty())
        {
            permanentChanges.setNewPicUrl(newPicUrl);
        }

        permanentChanges.setBonds(bonds);

        Call<Boolean> call = permApi.submitPermChanges(permanentChanges);
        call.enqueue(new Callback<Boolean>() {
            @Override
            public void onResponse(Call<Boolean> call, Response<Boolean> response) {
                lambda.run();
            }

            @Override
            public void onFailure(Call<Boolean> call, Throwable t) {
                lambda.run();
            }
        });

    }
}
