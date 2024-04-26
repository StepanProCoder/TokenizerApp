package com.staple.tokenizerapp.PickingCards.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.staple.tokenizerapp.EditingDecks.presenter.EditingDecksPresenter;
import com.staple.tokenizerapp.EditingDecks.view.CardAdapter;
import com.staple.tokenizerapp.PickingCards.presenter.PickingCardsPresenter;
import com.staple.tokenizerapp.PickingDecks.entity.Card;
import com.staple.tokenizerapp.PickingDecks.entity.Deck;
import com.staple.tokenizerapp.PickingDecks.presenter.PickingDecksPresenter;
import com.staple.tokenizerapp.R;

public class PickingCardsActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private TextView textView;
    private PickingCardsAdapter adapter;
    private PickingCardsPresenter presenter;
    private Deck currentDeck;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Intent intent = getIntent();
        String json = intent.getStringExtra("deck");
        GsonBuilder builder = new GsonBuilder();
        Gson gson = builder.create();
        Deck currentDeck = gson.fromJson(json, Deck.class);

        setContentView(R.layout.activity_picking_cards);
        recyclerView = findViewById(R.id.pickingCardsRecyclerView);
        textView = findViewById(R.id.deckNameTextView);
        textView.setText(currentDeck == null ? "Choose a card" : currentDeck.getName());

        adapter = new PickingCardsAdapter((Card card) -> { presenter.onCardClick(card); });
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);
        presenter = new PickingCardsPresenter(this, currentDeck);
    }

    public PickingCardsAdapter getAdapter()
    {
        return adapter;
    }
}