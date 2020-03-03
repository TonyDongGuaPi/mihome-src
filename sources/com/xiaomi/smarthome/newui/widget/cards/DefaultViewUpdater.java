package com.xiaomi.smarthome.newui.widget.cards;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewCompat;
import android.view.View;
import com.xiaomi.smarthome.newui.widget.cards.ViewUpdater;

public class DefaultViewUpdater extends ViewUpdater {
    private static final float SCALE_CENTER = 0.95f;
    private static final float SCALE_CENTER_TO_LEFT = 0.3f;
    private static final float SCALE_CENTER_TO_RIGHT = 0.14999998f;
    private static final float SCALE_LEFT = 0.65f;
    private static final float SCALE_RIGHT = 0.8f;
    private static final int Z_CENTER_1 = 12;
    private static final int Z_CENTER_2 = 16;
    private static final int Z_RIGHT = 8;
    private int activeCardCenter;
    private int activeCardLeft;
    private int activeCardRight;
    private int cardWidth;
    private float cardsGap;
    private int transitionDistance;
    private int transitionEnd;
    private float transitionRight2Center;

    public void setOnActiveCardChangeListener(ViewUpdater.OnActiveCardChangeListener onActiveCardChangeListener) {
    }

    public DefaultViewUpdater(CardSliderLayoutManager cardSliderLayoutManager) {
        super(cardSliderLayoutManager);
    }

    public void onLayoutManagerInitialized() {
        this.cardWidth = this.lm.getCardWidth();
        this.activeCardLeft = this.lm.getActiveCardLeft();
        this.activeCardRight = this.lm.getActiveCardRight();
        this.activeCardCenter = this.lm.getActiveCardCenter();
        this.cardsGap = this.lm.getCardsGap();
        this.transitionEnd = this.activeCardCenter;
        this.transitionDistance = this.activeCardRight - this.transitionEnd;
        this.transitionRight2Center = ((((float) this.activeCardRight) + ((((float) this.cardWidth) - (((float) this.cardWidth) * 0.95f)) / 2.0f)) - (((float) this.activeCardRight) - ((((float) this.cardWidth) - (((float) this.cardWidth) * 0.8f)) / 2.0f))) - this.cardsGap;
    }

    public int getActiveCardPosition() {
        int childCount = this.lm.getChildCount();
        View view = null;
        float f = 0.0f;
        for (int i = 0; i < childCount; i++) {
            View childAt = this.lm.getChildAt(i);
            int decoratedLeft = this.lm.getDecoratedLeft(childAt);
            if (decoratedLeft < this.activeCardRight) {
                float scaleX = ViewCompat.getScaleX(childAt);
                if (f < scaleX && decoratedLeft < this.activeCardCenter) {
                    view = childAt;
                    f = scaleX;
                }
            }
        }
        if (view != null) {
            return this.lm.getPosition(view);
        }
        return -1;
    }

    @Nullable
    public View getTopView() {
        View view = null;
        if (this.lm.getChildCount() == 0) {
            return null;
        }
        float f = (float) this.cardWidth;
        int childCount = this.lm.getChildCount();
        for (int i = 0; i < childCount; i++) {
            View childAt = this.lm.getChildAt(i);
            if (this.lm.getDecoratedLeft(childAt) < this.activeCardRight) {
                float decoratedLeft = (float) (this.activeCardRight - this.lm.getDecoratedLeft(childAt));
                if (decoratedLeft < f) {
                    view = childAt;
                    f = decoratedLeft;
                }
            }
        }
        return view;
    }

    public void updateView() {
        int i;
        int childCount = this.lm.getChildCount();
        View view = null;
        int i2 = 0;
        while (i2 < childCount) {
            View childAt = this.lm.getChildAt(i2);
            int decoratedLeft = this.lm.getDecoratedLeft(childAt);
            this.lm.getPosition(childAt);
            float f = 0.8f;
            float f2 = 12.0f;
            float f3 = 1.0f;
            float f4 = 0.95f;
            float f5 = 0.0f;
            if (decoratedLeft < this.activeCardLeft) {
                float f6 = ((float) decoratedLeft) / ((float) this.activeCardLeft);
                f = (SCALE_CENTER_TO_LEFT * f6) + 0.65f;
                f3 = 0.1f;
                f2 = 12.0f * f6;
            } else if (decoratedLeft < this.activeCardCenter) {
                f = 0.95f;
            } else if (decoratedLeft < this.activeCardRight) {
                f = 0.95f - ((((float) (decoratedLeft - this.activeCardCenter)) / ((float) (this.activeCardRight - this.activeCardCenter))) * SCALE_CENTER_TO_RIGHT);
                f2 = 16.0f;
                float f7 = (this.transitionRight2Center * ((float) (decoratedLeft - this.transitionEnd))) / ((float) this.transitionDistance);
                if (Math.abs(this.transitionRight2Center) < Math.abs(f7)) {
                    f7 = this.transitionRight2Center;
                }
                f5 = -f7;
            } else {
                f2 = 8.0f;
                if (view != null) {
                    if (this.lm.getDecoratedRight(view) <= this.activeCardRight) {
                        i = this.activeCardRight;
                    } else {
                        f4 = ViewCompat.getScaleX(view);
                        int decoratedRight = this.lm.getDecoratedRight(view);
                        f5 = ViewCompat.getTranslationX(view);
                        i = decoratedRight;
                    }
                    f5 = -(((((float) decoratedLeft) + ((((float) this.cardWidth) - (((float) this.cardWidth) * 0.8f)) / 2.0f)) - ((((float) i) - ((((float) this.cardWidth) - (((float) this.cardWidth) * f4)) / 2.0f)) + f5)) - this.cardsGap);
                }
            }
            onUpdateViewScale(childAt, f);
            onUpdateViewTransitionX(childAt, f5);
            onUpdateViewZ(childAt, f2);
            onUpdateViewAlpha(childAt, f3);
            i2++;
            view = childAt;
        }
    }

    /* access modifiers changed from: protected */
    public void onUpdateViewAlpha(@NonNull View view, float f) {
        if (ViewCompat.getAlpha(view) != f) {
            ViewCompat.setAlpha(view, f);
        }
    }

    /* access modifiers changed from: protected */
    public void onUpdateViewScale(@NonNull View view, float f) {
        if (ViewCompat.getScaleX(view) != f) {
            ViewCompat.setScaleX(view, f);
            ViewCompat.setScaleY(view, f);
        }
    }

    /* access modifiers changed from: protected */
    public void onUpdateViewZ(@NonNull View view, float f) {
        if (ViewCompat.getZ(view) != f) {
            ViewCompat.setZ(view, f);
        }
    }

    /* access modifiers changed from: protected */
    public void onUpdateViewTransitionX(@NonNull View view, float f) {
        if (ViewCompat.getTranslationX(view) != f) {
            ViewCompat.setTranslationX(view, f);
        }
    }
}
