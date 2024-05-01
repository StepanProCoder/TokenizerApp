package com.staple.tokenizerapp.EditingPermanents.router;

import android.content.Intent;

import androidx.activity.result.ActivityResultLauncher;

import com.staple.tokenizerapp.EditingPermanents.view.EditingPermanentsActivity;
import com.staple.tokenizerapp.PickingCards.view.PickingCardsActivity;

public class EditingPermanentsRouter {
    private EditingPermanentsActivity view;
    private ActivityResultLauncher<Intent> cardsResultLauncher;

    public EditingPermanentsRouter(EditingPermanentsActivity view, ActivityResultLauncher<Intent> cardsResultLauncher) {
        this.view = view;
        this.cardsResultLauncher = cardsResultLauncher;
    }

    public void routeToPickingCards()
    {
        Intent intent = new Intent(view, PickingCardsActivity.class);
        cardsResultLauncher.launch(intent);
    }
}
