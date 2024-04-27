package com.staple.tokenizerapp.EditingDecks.view;

import androidx.activity.OnBackPressedCallback;
import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.EditText;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.staple.tokenizerapp.EditingDecks.presenter.EditingDecksPresenter;
import com.staple.tokenizerapp.PickingDecks.entity.Card;
import com.staple.tokenizerapp.PickingDecks.entity.Deck;
import com.staple.tokenizerapp.R;

public class EditingDecksActivity extends AppCompatActivity
{
    private EditingDecksPresenter presenter;
    private RecyclerView recyclerView;
    private CardAdapter adapter;
    private EditText deckNameEditText;
    private SearchView searchView;

    private OnBackPressedCallback onBackPressedCallback = new OnBackPressedCallback(true) {
        @Override
        public void handleOnBackPressed()
        {
            presenter.onSendingChanges();
            finish();
        }
    };

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

    ActivityResultLauncher<Intent> cardsResultLauncher = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(),
            new ActivityResultCallback<ActivityResult>() {
                @Override
                public void onActivityResult(ActivityResult result) {
                    if (result.getResultCode() == Activity.RESULT_OK) {
                        Intent data = result.getData();
                        if (data != null && data.hasExtra("card"))
                        {
                            String json = data.getStringExtra("card");
                            GsonBuilder builder = new GsonBuilder();
                            Gson gson = builder.create();
                            Card chosenCard = gson.fromJson(json, Card.class);
                            presenter.onAddCard(chosenCard);
                        }
                    }
                }
            });


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Intent intent = getIntent();
        String json = intent.getStringExtra("deck");
        GsonBuilder builder = new GsonBuilder();
        Gson gson = builder.create();
        Deck currentDeck = gson.fromJson(json, Deck.class);

        getOnBackPressedDispatcher().addCallback(onBackPressedCallback);

        setContentView(R.layout.activity_editing_decks);
        recyclerView = findViewById(R.id.editingDecksRecyclerView);
        deckNameEditText = findViewById(R.id.deckNameEditText);
        searchView = findViewById(R.id.editingDecksSearchView);
        searchView.clearFocus();
        searchView.setOnQueryTextListener(onQueryTextListener);
        String hint = currentDeck.getName() == null ? "Deck Name" : currentDeck.getName();
        deckNameEditText.setHint(hint);

        adapter = new CardAdapter((Card card) -> { presenter.onCardClick(card); });
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);
        presenter = new EditingDecksPresenter(this, currentDeck, cardsResultLauncher);
    }

    public String getChangedDeckName()
    {
        return deckNameEditText.getText().toString();
    }
    public CardAdapter getAdapter()
    {
        return adapter;
    }

}