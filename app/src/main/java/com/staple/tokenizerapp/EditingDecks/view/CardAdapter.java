package com.staple.tokenizerapp.EditingDecks.view;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;
import com.staple.tokenizerapp.PickingDecks.entity.Card;
import com.staple.tokenizerapp.PickingDecks.entity.Deck;
import com.staple.tokenizerapp.R;

import java.util.List;

public class CardAdapter extends RecyclerView.Adapter<CardAdapter.CardViewHolder> {

    public interface OnCardClickListener
    {
        void onCardClick(Card card);
    }


    private List<Card> cardList;
    private OnCardClickListener onCardClickListener;

    public CardAdapter(OnCardClickListener onCardClickListener)
    {
        this.onCardClickListener = onCardClickListener;
    }

    public void setCardList(List<Card> cardList) {
        this.cardList = cardList;
    }

    @NonNull
    @Override
    public CardViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_item_layout, parent, false);
        return new CardViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CardViewHolder holder, int position) {
        if (position == 0)
        {
            holder.cardNameTextView.setText("Add a card");
            holder.cardImageView.setImageDrawable(null);
            holder.itemView.setOnClickListener(view -> {
                onCardClickListener.onCardClick(new Card());
            });
        }
        else
        {
            Card card = cardList.get(position - 1);
            holder.cardNameTextView.setText(card.getName());
            Picasso.get().load(card.getPicUrl()).into(holder.cardImageView);
            holder.itemView.setOnClickListener(view -> {
                onCardClickListener.onCardClick(card);
            });
        }
    }

    @Override
    public int getItemCount() {
        return cardList.size() + 1;
    }

    public static class CardViewHolder extends RecyclerView.ViewHolder {

        TextView cardNameTextView;
        ImageView cardImageView;

        public CardViewHolder(@NonNull View itemView) {
            super(itemView);
            cardNameTextView = itemView.findViewById(R.id.card_name_text_view);
            cardImageView = itemView.findViewById(R.id.card_image_view);
        }
    }
}
