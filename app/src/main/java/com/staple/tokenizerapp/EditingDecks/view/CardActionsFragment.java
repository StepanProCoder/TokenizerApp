package com.staple.tokenizerapp.EditingDecks.view;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;

import androidx.fragment.app.DialogFragment;

import com.staple.tokenizerapp.R;

public class CardActionsFragment extends DialogFragment {

    private Runnable deleteLambda;
    public CardActionsFragment(Runnable deleteLambda)
    {
        this.deleteLambda = deleteLambda;
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle("Choose an action")
                .setItems(R.array.card_actions_array, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        switch (which) {
                            case 0:
                                deleteLambda.run();
                                break;
                        }
                    }
                });
        return builder.create();
    }
}
