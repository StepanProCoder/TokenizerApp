package com.staple.tokenizerapp.EditingDecks.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.staple.tokenizerapp.EditingDecks.presenter.EditingDecksPresenter;
import com.staple.tokenizerapp.PickingDecks.entity.Card;
import com.staple.tokenizerapp.PickingDecks.entity.Deck;
import com.staple.tokenizerapp.PickingDecks.presenter.PickingDecksPresenter;
import com.staple.tokenizerapp.PickingDecks.view.DeckAdapter;
import com.staple.tokenizerapp.R;

public class EditingDecksActivity extends AppCompatActivity
{
    private EditingDecksPresenter presenter;
    private RecyclerView recyclerView;
    private CardAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Intent intent = getIntent();
        String json = intent.getStringExtra("deck");
        GsonBuilder builder = new GsonBuilder();
        Gson gson = builder.create();
        Deck currentDeck = gson.fromJson(json, Deck.class);

        setContentView(R.layout.activity_picking_decks);
        recyclerView = findViewById(R.id.recyclerView);
        adapter = new CardAdapter((Card card) -> { presenter.onCardClick(card); });
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);
        presenter = new EditingDecksPresenter(this, currentDeck);
    }

    public CardAdapter getAdapter()
    {
        return adapter;
    }

}