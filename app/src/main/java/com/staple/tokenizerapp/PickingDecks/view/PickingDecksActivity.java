package com.staple.tokenizerapp.PickingDecks.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;

import com.staple.tokenizerapp.PickingDecks.entity.Deck;
import com.staple.tokenizerapp.PickingDecks.interactor.DeckApi;
import com.staple.tokenizerapp.PickingDecks.presenter.PickingDecksPresenter;
import com.staple.tokenizerapp.R;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class PickingDecksActivity extends AppCompatActivity
{
    private PickingDecksPresenter presenter;
    private RecyclerView recyclerView;
    private SearchView searchView;
    private DeckAdapter adapter;

    private SearchView.OnQueryTextListener onQueryTextListener = new SearchView.OnQueryTextListener() {
        @Override
        public boolean onQueryTextSubmit(String query) {
            return false;
        }

        @Override
        public boolean onQueryTextChange(String newText) {
            presenter.onQueryTextChange(newText);
            return true;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_picking_decks);
        recyclerView = findViewById(R.id.pickingDecksRecyclerView);
        searchView = findViewById(R.id.pickingDecksSearchView);
        searchView.clearFocus();
        searchView.setOnQueryTextListener(onQueryTextListener);
        adapter = new DeckAdapter((Deck deck) -> { presenter.onDeckClick(deck); });
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);
        presenter = new PickingDecksPresenter(this);
    }

    @Override
    protected void onResume()
    {
        super.onResume();
        presenter.onResume();
    }

    public DeckAdapter getAdapter()
    {
        return adapter;
    }

}