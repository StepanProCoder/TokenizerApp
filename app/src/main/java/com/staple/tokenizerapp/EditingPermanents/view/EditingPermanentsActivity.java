package com.staple.tokenizerapp.EditingPermanents.view;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.staple.tokenizerapp.EditingPermanents.presenter.EditingPermanentsPresenter;
import com.staple.tokenizerapp.PickingDecks.entity.Card;
import com.staple.tokenizerapp.PickingDecks.entity.Deck;
import com.staple.tokenizerapp.PickingPermanent.entity.Emblem;
import com.staple.tokenizerapp.PickingPermanent.entity.Permcounter;
import com.staple.tokenizerapp.PickingPermanent.entity.Playercounter;
import com.staple.tokenizerapp.PickingPermanent.entity.Token;
import com.staple.tokenizerapp.R;

public class EditingPermanentsActivity extends AppCompatActivity {

    private Card card;
    private Token token;
    private Emblem emblem;
    private Permcounter permcounter;
    private Playercounter playercounter;
    public EditText permNameEditText;
    public EditText permPicUrlEditText;
    private Button bondButton;
    private Button submitButton;
    private EditingPermanentsPresenter presenter;
    public int mode = 1;
    public Long permId;

    public ActivityResultLauncher<Intent> cardsResultLauncher = registerForActivityResult(
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
                            presenter.onBondCard(chosenCard);
                        }
                    }
                }
            });

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Intent intent = getIntent();

        String jsonCard = intent.getStringExtra("card");
        String jsonToken = intent.getStringExtra("token");
        String jsonEmblem = intent.getStringExtra("emblem");
        String jsonPermcounter = intent.getStringExtra("permcounter");
        String jsonPlayercounter = intent.getStringExtra("playercounter");

        GsonBuilder builder = new GsonBuilder();
        Gson gson = builder.create();

        setContentView(R.layout.activity_editing_permanents);

        presenter = new EditingPermanentsPresenter(this);

        permNameEditText = findViewById(R.id.editTextPermName);
        permPicUrlEditText = findViewById(R.id.editTextPicUrl);
        bondButton = findViewById(R.id.buttonBond);
        submitButton = findViewById(R.id.buttonSubmit);

        if (jsonCard != null)
        {
            card = gson.fromJson(jsonCard, Card.class);
            bondButton.setEnabled(false);
            if(card.getName() != null)
            {
                permNameEditText.setHint(card.getName());
                permPicUrlEditText.setHint(card.getPicUrl());
            }
            permId = card.getId();
            mode = 1;
        }
        else if (jsonToken != null)
        {
            token = gson.fromJson(jsonToken, Token.class);
            if(token.getName() != null) {
                permNameEditText.setHint(token.getName());
                permPicUrlEditText.setHint(token.getPicUrl());
            }
            permId = token.getId();
            mode = 2;
        }
        else if (jsonEmblem != null)
        {
            emblem = gson.fromJson(jsonEmblem, Emblem.class);
            if(emblem.getName() != null) {
                permNameEditText.setHint(emblem.getName());
                permPicUrlEditText.setHint(emblem.getPicUrl());
            }
            permId = emblem.getId();
            mode = 3;
        }
        else if (jsonPermcounter != null)
        {
            permcounter = gson.fromJson(jsonPermcounter, Permcounter.class);
            if(permcounter.getName() != null) {
                permNameEditText.setHint(permcounter.getName());
                permPicUrlEditText.setHint(permcounter.getPicUrl());
            }
            permId = permcounter.getId();
            mode = 4;
        }
        else if (jsonPlayercounter != null)
        {
            playercounter = gson.fromJson(jsonPlayercounter, Playercounter.class);
            if(playercounter.getName() != null) {
                permNameEditText.setHint(playercounter.getName());
                permPicUrlEditText.setHint(playercounter.getPicUrl());
            }
            permId = playercounter.getId();
            mode = 5;
        }

        bondButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.onBondButtonClick();
            }
        });

        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.onSubmitButtonClick();
            }
        });

    }
}