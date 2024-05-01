package com.staple.tokenizerapp.EditingPermanents.presenter;

import com.staple.tokenizerapp.EditingDecks.router.EditingDecksRouter;
import com.staple.tokenizerapp.EditingPermanents.interactor.EditingPermanentsInteractor;
import com.staple.tokenizerapp.EditingPermanents.router.EditingPermanentsRouter;
import com.staple.tokenizerapp.EditingPermanents.view.EditingPermanentsActivity;
import com.staple.tokenizerapp.PickingDecks.entity.Card;

public class EditingPermanentsPresenter
{
    private EditingPermanentsActivity view;
    private EditingPermanentsInteractor interactor;
    private EditingPermanentsRouter router;
    public EditingPermanentsPresenter(EditingPermanentsActivity view)
    {
        this.view = view;
        this.interactor = new EditingPermanentsInteractor();
        this.router = new EditingPermanentsRouter(view, view.cardsResultLauncher);
    }

    public void onBondCard(Card chosenCard) {
        interactor.bondCard(chosenCard);
    }

    public void onBondButtonClick() {
        router.routeToPickingCards();
    }

    public void onSubmitButtonClick() {
        String newName = view.permNameEditText.getText().toString().isEmpty() ? (view.permNameEditText.getHint().toString().equals("Permanent Name") ? "" : view.permNameEditText.getHint().toString()) : view.permNameEditText.getText().toString();
        String newPicUrl = view.permPicUrlEditText.getText().toString().isEmpty() ? (view.permPicUrlEditText.getHint().toString().equals("Picture URL") ? "" : view.permPicUrlEditText.getHint().toString()) : view.permPicUrlEditText.getText().toString();
        interactor.submit(view.permId, newName, newPicUrl, view.mode, () -> { view.finish(); });
    }
}
