package com.mi.global.bbs.view.cardpager;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.annotation.Px;
import android.support.v4.app.Fragment;
import android.support.v7.widget.CardView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import com.mi.global.bbs.R;

public class CardFragment<T> extends Fragment {
    private int cardMargin;
    private View contentView;
    private CardView mCardView;

    public static CardFragment newInstance() {
        Bundle bundle = new Bundle();
        CardFragment cardFragment = new CardFragment();
        cardFragment.setArguments(bundle);
        return cardFragment;
    }

    @Nullable
    public View onCreateView(LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, @Nullable Bundle bundle) {
        View inflate = layoutInflater.inflate(R.layout.bbs_view_card, viewGroup, false);
        this.mCardView = (CardView) inflate.findViewById(R.id.card_view);
        FrameLayout.LayoutParams layoutParams = (FrameLayout.LayoutParams) this.mCardView.getLayoutParams();
        int i = (int) (getResources().getDisplayMetrics().density * 4.0f);
        Log.d("banner margin", this.cardMargin + "  " + i);
        layoutParams.setMargins(this.cardMargin, i, this.cardMargin, i);
        this.mCardView.setLayoutParams(layoutParams);
        ViewGroup.LayoutParams layoutParams2 = new ViewGroup.LayoutParams(-1, -1);
        if (this.contentView != null) {
            if (this.contentView.getParent() != null) {
                ((ViewGroup) this.contentView.getParent()).removeView(this.contentView);
            }
            this.mCardView.addView(this.contentView, layoutParams2);
        }
        return inflate;
    }

    public void setCardContentView(ViewHolder viewHolder, T t) {
        this.contentView = viewHolder.getView(getContext(), t);
    }

    public void setCardMargin(@Px int i) {
        this.cardMargin = i;
    }

    public CardView getCardView() {
        return this.mCardView;
    }
}
