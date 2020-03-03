package com.mi.global.bbs.view.cardpager;

import android.support.v7.widget.CardView;

public interface CardAdapter {
    float getBaseElevation();

    CardView getCardView(int i);

    int getCount();

    float getFloatElevation();
}
