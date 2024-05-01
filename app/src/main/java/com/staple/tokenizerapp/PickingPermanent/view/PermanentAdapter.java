package com.staple.tokenizerapp.PickingPermanent.view;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;
import com.staple.tokenizerapp.PickingDecks.entity.Card;
import com.staple.tokenizerapp.PickingPermanent.entity.Emblem;
import com.staple.tokenizerapp.PickingPermanent.entity.Permcounter;
import com.staple.tokenizerapp.PickingPermanent.entity.Playercounter;
import com.staple.tokenizerapp.PickingPermanent.entity.Token;
import com.staple.tokenizerapp.R;

import java.util.List;

public class PermanentAdapter extends RecyclerView.Adapter<PermanentAdapter.PermViewHolder> {

    public interface OnPermClickListener
    {
        void onPermClick(Card card, Token token, Emblem emblem, Permcounter permcounter, Playercounter playercounter);
    }


    private List<Card> cardList;
    private List<Token> tokenList;
    private List<Emblem> emblemList;
    private List<Permcounter> permcounterList;
    private List<Playercounter> playercounterList;
    private OnPermClickListener onPermClickListener;
    private int mode;


    public PermanentAdapter(OnPermClickListener onPermClickListener, int mode)
    {
        this.onPermClickListener = onPermClickListener;
        this.mode = mode;
    }

    public void setCardList(List<Card> cardList) {
        this.cardList = cardList;
    }

    public void setTokenList(List<Token> tokenList) {
        this.tokenList = tokenList;
    }

    public void setEmblemList(List<Emblem> emblemList) {
        this.emblemList = emblemList;
    }

    public void setPermcounterList(List<Permcounter> permcounterList) {
        this.permcounterList = permcounterList;
    }

    public void setPlayercounterList(List<Playercounter> playercounterList) {
        this.playercounterList = playercounterList;
    }

    @NonNull
    @Override
    public PermViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_item_layout, parent, false);
        return new PermViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PermViewHolder holder, int position) {
        if (position == 0)
        {
            holder.permNameTextView.setText("Add a permanent");
            holder.permImageView.setImageDrawable(null);
            holder.itemView.setOnClickListener(view -> {
                onPermClickListener.onPermClick(mode == 1? new Card() : null, mode == 2? new Token() : null, mode == 3? new Emblem() : null, mode == 4? new Permcounter() : null, mode == 5? new Playercounter() : null);
            });
        }
        else
        {
            String pic = "";
            String text = "";

            if(mode == 1) {
                Card card = cardList.get(position - 1);
                pic = card.getPicUrl();
                text = card.getName();
                holder.itemView.setOnClickListener(view -> {
                    onPermClickListener.onPermClick(card, null, null, null, null);
                });
            }
            else if(mode == 2) {
                Token token = tokenList.get(position - 1);
                pic = token.getPicUrl();
                text = token.getName();
                holder.itemView.setOnClickListener(view -> {
                    onPermClickListener.onPermClick(null, token, null, null, null);
                });
            }
            else if(mode == 3) {
                Emblem emblem = emblemList.get(position - 1);
                pic = emblem.getPicUrl();
                text = emblem.getName();
                holder.itemView.setOnClickListener(view -> {
                    onPermClickListener.onPermClick(null, null, emblem, null, null);
                });
            }
            else if(mode == 4) {
                Permcounter permcounter = permcounterList.get(position - 1);
                pic = permcounter.getPicUrl();
                text = permcounter.getName();
                holder.itemView.setOnClickListener(view -> {
                    onPermClickListener.onPermClick(null, null, null, permcounter, null);
                });
            }
            else if(mode == 5) {
                Playercounter playercounter = playercounterList.get(position - 1);
                pic = playercounter.getPicUrl();
                text = playercounter.getName();
                holder.itemView.setOnClickListener(view -> {
                    onPermClickListener.onPermClick(null, null, null, null, playercounter);
                });
            }

            holder.permNameTextView.setText(text);
            Picasso.get().load(pic).into(holder.permImageView);
        }
    }

    @Override
    public int getItemCount() {
        switch (mode)
        {
            case 1:
                return cardList.size() + 1;
            case 2:
                return tokenList.size() + 1;
            case 3:
                return emblemList.size() + 1;
            case 4:
                return permcounterList.size() + 1;
            case 5:
                return playercounterList.size() + 1;
        }
        return 1;
    }

    public static class PermViewHolder extends RecyclerView.ViewHolder {

        TextView permNameTextView;
        ImageView permImageView;

        public PermViewHolder(@NonNull View itemView) {
            super(itemView);
            permNameTextView = itemView.findViewById(R.id.card_name_text_view);
            permImageView = itemView.findViewById(R.id.card_image_view);
        }
    }
}
