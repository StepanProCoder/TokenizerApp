package com.staple.tokenizerapp.PickingCards.view;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;
import com.staple.tokenizerapp.PickingDecks.entity.Card;
import com.staple.tokenizerapp.R;

import java.util.List;

public class PickingCardsAdapter extends RecyclerView.Adapter<PickingCardsAdapter.PickingCardsViewHolder> {

    public interface OnCardClickListener
    {
        void onCardClick(Card card);
    }


    private List<Card> cardList;
    private PickingCardsAdapter.OnCardClickListener onCardClickListener;

    public PickingCardsAdapter(PickingCardsAdapter.OnCardClickListener onCardClickListener)
    {
        this.onCardClickListener = onCardClickListener;
    }

    public void setCardList(List<Card> cardList) {
        this.cardList = cardList;
    }

    @NonNull
    @Override
    public PickingCardsAdapter.PickingCardsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_item_layout, parent, false);
        return new PickingCardsAdapter.PickingCardsViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PickingCardsAdapter.PickingCardsViewHolder holder, int position) {
        Card card = cardList.get(position);
        holder.cardNameTextView.setText(card.getName());
        Picasso.get().load(card.getPicUrl()).into(holder.cardImageView);
        holder.itemView.setOnClickListener(view -> {
            onCardClickListener.onCardClick(card);
        });
    }

    @Override
    public int getItemCount() {
        return cardList.size();
    }

    public static class PickingCardsViewHolder extends RecyclerView.ViewHolder {

        TextView cardNameTextView;
        ImageView cardImageView;

        public PickingCardsViewHolder(@NonNull View itemView) {
            super(itemView);
            cardNameTextView = itemView.findViewById(R.id.card_name_text_view);
            cardImageView = itemView.findViewById(R.id.card_image_view);
        }
    }
}

