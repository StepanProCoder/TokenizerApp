package com.staple.tokenizerapp.PickingDecks.view;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;

import androidx.fragment.app.DialogFragment;

import com.staple.tokenizerapp.R;

public class DeckActionsFragment extends DialogFragment {

    private Runnable routeToEditingLambda;
    public DeckActionsFragment(Runnable routeToEditingLambda)
    {
        this.routeToEditingLambda = routeToEditingLambda;
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle("Выберите действие")
                .setItems(R.array.deck_actions_array, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        // Обработка выбранного действия
                        switch (which) {
                            case 0:
                                routeToEditingLambda.run();
                                break;
                            case 1:
                                break;
                            case 2:
                                break;
                        }
                    }
                });
        return builder.create();
    }
}

