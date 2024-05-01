package com.staple.tokenizerapp.PickingPermanent.view;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.staple.tokenizerapp.PickingDecks.entity.Card;
import com.staple.tokenizerapp.PickingPermanent.entity.Emblem;
import com.staple.tokenizerapp.PickingPermanent.entity.Permcounter;
import com.staple.tokenizerapp.PickingPermanent.entity.Playercounter;
import com.staple.tokenizerapp.PickingPermanent.entity.Token;
import com.staple.tokenizerapp.PickingPermanent.presenter.PickingPermanentPresenter;
import com.staple.tokenizerapp.R;

public class PickingPermanentActivity extends AppCompatActivity
{
    private PickingPermanentPresenter presenter;
    private RecyclerView recyclerView;
    private PermanentAdapter adapter;
    private SearchView searchView;

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

        Intent intent = getIntent();
        int mode = intent.getIntExtra("mode", 1);

        setContentView(R.layout.activity_picking_permanents);
        recyclerView = findViewById(R.id.pickingPermanentsRecyclerView);
        searchView = findViewById(R.id.pickingPermanentsSearchView);
        searchView.clearFocus();
        searchView.setOnQueryTextListener(onQueryTextListener);

        adapter = new PermanentAdapter((Card card, Token token, Emblem emblem, Permcounter permcounter, Playercounter playercounter) -> { presenter.onPermClick(card ,token, emblem, permcounter, playercounter); }, mode);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);
        presenter = new PickingPermanentPresenter(this, mode);
    }

    @Override
    protected void onResume() {
        super.onResume();
        presenter.onResume();
    }

    public PermanentAdapter getAdapter()
    {
        return adapter;
    }

}