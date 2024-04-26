package com.staple.tokenizerapp.PickingDecks.view;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.staple.tokenizerapp.PickingDecks.entity.Deck;
import com.staple.tokenizerapp.R;

import java.util.List;

public class DeckAdapter extends RecyclerView.Adapter<DeckAdapter.DeckViewHolder> {

    public interface OnDeckClickListener
    {
        void onDeckClick(Deck deck);
    }


    private List<Deck> deckList;
    private OnDeckClickListener onDeckClickListener;

    public DeckAdapter(OnDeckClickListener onDeckClickListener)
    {
        this.onDeckClickListener = onDeckClickListener;
    }

    public void setDeckList(List<Deck> deckList) {
        this.deckList = deckList;
    }

    @NonNull
    @Override
    public DeckViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.deck_item_layout, parent, false);
        return new DeckViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull DeckViewHolder holder, int position) {
        if (position == 0)
        {
            holder.deckNameTextView.setText("Add new deck");
            holder.itemView.setOnClickListener(view -> {
                onDeckClickListener.onDeckClick(new Deck());
            });
        }
        else
        {
            Deck deck = deckList.get(position - 1);
            holder.deckNameTextView.setText(deck.getName());
            holder.itemView.setOnClickListener(view -> {
                onDeckClickListener.onDeckClick(deck);
            });
        }
    }

    @Override
    public int getItemCount() {
        return deckList.size() + 1;
    }

    public static class DeckViewHolder extends RecyclerView.ViewHolder {

        TextView deckNameTextView;

        public DeckViewHolder(@NonNull View itemView) {
            super(itemView);
            deckNameTextView = itemView.findViewById(R.id.deck_name_text_view);
        }
    }
}

