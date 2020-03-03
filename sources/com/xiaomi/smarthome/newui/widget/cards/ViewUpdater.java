package com.xiaomi.smarthome.newui.widget.cards;

import android.support.annotation.Nullable;
import android.view.View;

public abstract class ViewUpdater {
    protected final CardSliderLayoutManager lm;

    public interface OnActiveCardChangeListener {
        void onActiveCardChange(int i, int i2);
    }

    public abstract int getActiveCardPosition();

    @Nullable
    public abstract View getTopView();

    public void onLayoutManagerInitialized() {
    }

    public abstract void setOnActiveCardChangeListener(OnActiveCardChangeListener onActiveCardChangeListener);

    public abstract void updateView();

    public ViewUpdater(CardSliderLayoutManager cardSliderLayoutManager) {
        this.lm = cardSliderLayoutManager;
    }
}
