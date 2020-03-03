package com.xiaomi.smarthome.newui.widget.micards;

import android.os.Build;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.view.View;
import com.xiaomi.smarthome.newui.widget.cards.CardSliderLayoutManager;
import com.xiaomi.smarthome.newui.widget.cards.VerticalViewUpdater;

public class CardsVerticalUpdater extends VerticalViewUpdater {
    public CardsVerticalUpdater(CardSliderLayoutManager cardSliderLayoutManager) {
        super(cardSliderLayoutManager);
    }

    public void onLayoutManagerInitialized() {
        super.onLayoutManagerInitialized();
    }

    /* access modifiers changed from: protected */
    public void onUpdateViewZ(@NonNull View view, int i, float f) {
        if (Build.VERSION.SDK_INT >= 21) {
            super.onUpdateViewZ(view, i, f);
        } else if (view instanceof CardView) {
            ((CardView) view).setCardElevation(Math.max(0.0f, f));
        }
    }
}
