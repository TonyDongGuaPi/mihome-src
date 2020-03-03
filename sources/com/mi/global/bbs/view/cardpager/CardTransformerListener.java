package com.mi.global.bbs.view.cardpager;

import android.support.v4.view.ViewPager;
import android.support.v7.widget.CardView;

public class CardTransformerListener implements ViewPager.OnPageChangeListener {
    private CardAdapter mAdapter;
    private float mLastOffset;
    private ViewPager.OnPageChangeListener mOnPageChangeListener;
    private boolean mScalingEnabled;

    public CardTransformerListener(CardAdapter cardAdapter, boolean z) {
        this.mAdapter = cardAdapter;
        this.mScalingEnabled = z;
    }

    public void onPageScrolled(int i, float f, int i2) {
        float f2;
        int i3;
        int i4;
        int i5 = i;
        float f3 = f;
        float baseElevation = this.mAdapter.getBaseElevation();
        float floatElevation = this.mAdapter.getFloatElevation();
        if (this.mLastOffset > f3) {
            f2 = 1.0f - f3;
            i3 = i5 + 1;
            i4 = i5;
        } else {
            i4 = i5 + 1;
            i3 = i5;
            f2 = f3;
        }
        if (i4 <= this.mAdapter.getCount() - 1 && i3 <= this.mAdapter.getCount() - 1) {
            CardView cardView = this.mAdapter.getCardView(i3);
            if (cardView != null) {
                if (this.mScalingEnabled) {
                    double d = (double) (1.0f - f2);
                    Double.isNaN(d);
                    float f4 = (float) ((d * 0.1d) + 1.0d);
                    cardView.setScaleX(f4);
                    cardView.setScaleY(f4);
                }
                cardView.setCardElevation(((floatElevation - baseElevation) * (1.0f - f2)) + baseElevation);
            }
            CardView cardView2 = this.mAdapter.getCardView(i4);
            if (cardView2 != null) {
                if (this.mScalingEnabled) {
                    double d2 = (double) f2;
                    Double.isNaN(d2);
                    float f5 = (float) ((d2 * 0.1d) + 1.0d);
                    cardView2.setScaleX(f5);
                    cardView2.setScaleY(f5);
                }
                cardView2.setCardElevation(baseElevation + ((floatElevation - baseElevation) * f2));
            }
            this.mLastOffset = f3;
            if (this.mOnPageChangeListener != null) {
                this.mOnPageChangeListener.onPageScrolled(i5, f3, i2);
            }
        }
    }

    public void onPageSelected(int i) {
        if (this.mOnPageChangeListener != null) {
            this.mOnPageChangeListener.onPageSelected(i);
        }
    }

    public void onPageScrollStateChanged(int i) {
        if (this.mOnPageChangeListener != null) {
            this.mOnPageChangeListener.onPageScrollStateChanged(i);
        }
    }

    public void setOnPageChangeListener(ViewPager.OnPageChangeListener onPageChangeListener) {
        this.mOnPageChangeListener = onPageChangeListener;
    }
}
