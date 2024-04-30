package com.staple.tokenizerapp.VirtualTableTop.view;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.widget.NestedScrollView;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.HorizontalScrollView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import com.google.android.material.card.MaterialCardView;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.squareup.picasso.Picasso;
import com.staple.tokenizerapp.PickingDecks.entity.Card;
import com.staple.tokenizerapp.PickingDecks.entity.Deck;
import com.staple.tokenizerapp.R;
import com.staple.tokenizerapp.VirtualTableTop.presenter.VirtualTableTopPresenter;

public class VirtualTableTopActivity extends AppCompatActivity {

    private ScrollView scrollView;
    private LinearLayout layout;
    private Button placeNewCardsBtn;

    private VirtualTableTopPresenter presenter;

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

        setContentView(R.layout.activity_virtual_table_top);
        layout = findViewById(R.id.virtualTableLayout);
        scrollView = findViewById(R.id.virtualTableScrollView);
        placeNewCardsBtn = findViewById(R.id.placeNewCardsBtn);

        presenter = new VirtualTableTopPresenter(this, currentDeck, cardsResultLauncher);

        placeNewCardsBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.onPlacingNewCards();
            }
        });

        //addCardToLayout("https://draftsim.com/wp-content/uploads/mtg-card-DB/cards/0/c/0ca14c17-dc72-4f68-92f2-14a6c4019f4e.jpg");
    }

    public void addCardToLayout(String picUrl) {
        // Надуваем макет карточки из XML
        View cardView = LayoutInflater.from(this).inflate(R.layout.mtg_card_item, null);

        // Устанавливаем параметры ширины и высоты для карточки
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(
                600, // Замените на желаемую ширину карточки в пикселях
                800  // Замените на желаемую высоту карточки в пикселях
        );
        layoutParams.gravity = Gravity.TOP | Gravity.LEFT;
        cardView.setLayoutParams(layoutParams);

        // Находим Views в карточке
        ImageView imageView = cardView.findViewById(R.id.card_image_view);

        // Устанавливаем данные
        Picasso.get().load(picUrl).into(imageView);

        cardView.setTag(R.id.tag_dx, 0f); // Устанавливаем начальное значение dx
        cardView.setTag(R.id.tag_dy, 0f); // Устанавливаем начальное значение dy

        // Находим кнопки в карточке
        Button deleteButton = cardView.findViewById(R.id.delete_button);
        Button rotateButton = cardView.findViewById(R.id.rotate_button);
        Button copyButton = cardView.findViewById(R.id.copy_button);

        // Устанавливаем обработчики событий для кнопок
        deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Удалить карточку из макета
                layout.removeView(cardView);
            }
        });

        rotateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Повернуть карточку на 90 градусов
                cardView.setRotation(cardView.getRotation() > 0 ? 0 : 90);
            }
        });

        copyButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Создать копию карточки
                addCardToLayout(picUrl);
            }
        });

        cardView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                float dX = (float) v.getTag(R.id.tag_dx);
                float dY = (float) v.getTag(R.id.tag_dy);

                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        scrollView.requestDisallowInterceptTouchEvent(true);
                        dX = v.getX() - event.getRawX();
                        dY = v.getY() - event.getRawY();
                        v.setTag(R.id.tag_dx, dX); // Сохраняем dx в тэг
                        v.setTag(R.id.tag_dy, dY); // Сохраняем dy в тэг
                        break;
                    case MotionEvent.ACTION_MOVE:
                        scrollView.requestDisallowInterceptTouchEvent(true);
                        v.setX(event.getRawX() + dX);
                        v.setY(event.getRawY() + dY);
                        break;
                }

                return true;
            }
        });


        layout.addView(cardView);
    }

}

