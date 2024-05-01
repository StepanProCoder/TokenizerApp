package com.staple.tokenizerapp.MainMenu;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import com.staple.tokenizerapp.PickingDecks.view.PickingDecksActivity;
import com.staple.tokenizerapp.PickingPermanent.view.PickingPermanentActivity;
import com.staple.tokenizerapp.R;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;

public class MainMenuActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menu);

        // Находим все кнопки по их идентификаторам
        Button cardsButton = findViewById(R.id.cards_button);
        Button tokensButton = findViewById(R.id.tokens_button);
        Button emblemsButton = findViewById(R.id.emblems_button);
        Button permanentCountersButton = findViewById(R.id.permanent_counters_button);
        Button playerCountersButton = findViewById(R.id.player_counters_button);
        Button decksButton = findViewById(R.id.decks_button);
        Button secretButton = findViewById(R.id.secret_button);

        // Устанавливаем обработчик клика для каждой кнопки
        cardsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainMenuActivity.this, PickingPermanentActivity.class);
                intent.putExtra("mode", 1);
                MainMenuActivity.this.startActivity(intent);
            }
        });

        tokensButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainMenuActivity.this, PickingPermanentActivity.class);
                intent.putExtra("mode", 2);
                MainMenuActivity.this.startActivity(intent);
            }
        });

        emblemsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainMenuActivity.this, PickingPermanentActivity.class);
                intent.putExtra("mode", 3);
                MainMenuActivity.this.startActivity(intent);
            }
        });

        permanentCountersButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainMenuActivity.this, PickingPermanentActivity.class);
                intent.putExtra("mode", 4);
                MainMenuActivity.this.startActivity(intent);
            }
        });

        playerCountersButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainMenuActivity.this, PickingPermanentActivity.class);
                intent.putExtra("mode", 5);
                MainMenuActivity.this.startActivity(intent);
            }
        });

        decksButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainMenuActivity.this, PickingDecksActivity.class);
                MainMenuActivity.this.startActivity(intent);
            }
        });

        secretButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Ваш код обработки клика для секретной кнопки
            }
        });
    }
}
