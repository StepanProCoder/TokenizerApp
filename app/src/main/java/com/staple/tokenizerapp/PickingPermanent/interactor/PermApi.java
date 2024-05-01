package com.staple.tokenizerapp.PickingPermanent.interactor;

import com.staple.tokenizerapp.EditingPermanents.entity.PermanentChanges;
import com.staple.tokenizerapp.PickingDecks.entity.Card;
import com.staple.tokenizerapp.PickingPermanent.entity.Emblem;
import com.staple.tokenizerapp.PickingPermanent.entity.Permcounter;
import com.staple.tokenizerapp.PickingPermanent.entity.Playercounter;
import com.staple.tokenizerapp.PickingPermanent.entity.Token;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface PermApi
{
    @GET("permeditcards/")
    Call<List<Card>> permGetCards();

    @GET("permedittokens/")
    Call<List<Token>> permGetTokens();

    @GET("permeditemblems/")
    Call<List<Emblem>> permGetEmblems();

    @GET("permeditpermcounters/")
    Call<List<Permcounter>> permGetPermcounters();

    @GET("permeditplayercounters/")
    Call<List<Playercounter>> permGetPlayercounters();

    @POST("permeditcards/")
    Call<Boolean> deleteCard(@Body Card card);

    @POST("permedittokens/")
    Call<Boolean> deleteToken(@Body Token token);

    @POST("permeditemblem/")
    Call<Boolean> deleteEmblem(@Body Emblem emblem);

    @POST("permeditpermcounters/")
    Call<Boolean> deletePermcounter(@Body Permcounter permcounter);

    @POST("permeditplayercounters/")
    Call<Boolean> deletePlayercounter(@Body Playercounter playercounter);

    @POST("permchanges/")
    Call<Boolean> submitPermChanges(@Body PermanentChanges permanentChanges);

}
