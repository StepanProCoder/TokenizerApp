package com.staple.tokenizerapp.PickingPermanent.view;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;

import androidx.fragment.app.DialogFragment;

import com.staple.tokenizerapp.R;

public class PermanentActionsFragment extends DialogFragment {

    private Runnable deleteLambda;
    private Runnable redactLambda;
    public PermanentActionsFragment(Runnable redactLambda, Runnable deleteLambda)
    {
        this.deleteLambda = deleteLambda;
        this.redactLambda = redactLambda;
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle("Choose an action")
                .setItems(R.array.perm_actions_array, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        switch (which) {
                            case 0:
                                redactLambda.run();
                                break;
                            case 1:
                                deleteLambda.run();
                                break;
                        }
                    }
                });
        return builder.create();
    }
}
