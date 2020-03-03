package com.xiaomi.smarthome.newui.widget.cards;

import android.graphics.PointF;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearSmoothScroller;
import android.support.v7.widget.LinearSnapHelper;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.animation.LinearInterpolator;
import java.security.InvalidParameterException;

public class VerticalCardSanpHelper extends LinearSnapHelper {
    private static final String TAG = "VerticalCardSanpHelper";
    private RecyclerView recyclerView;

    public void attachToRecyclerView(@Nullable RecyclerView recyclerView2) throws IllegalStateException {
        super.attachToRecyclerView(recyclerView2);
        if (recyclerView2 == null || (recyclerView2.getLayoutManager() instanceof CardSliderLayoutManager)) {
            this.recyclerView = recyclerView2;
            return;
        }
        throw new InvalidParameterException("LayoutManager must be instance of CardSliderLayoutManager");
    }

    public int findTargetSnapPosition(RecyclerView.LayoutManager layoutManager, int i, int i2) {
        PointF computeScrollVectorForPosition;
        int i3;
        int activeCardPosition;
        int i4;
        CardSliderLayoutManager cardSliderLayoutManager = (CardSliderLayoutManager) layoutManager;
        int itemCount = cardSliderLayoutManager.getItemCount();
        if (itemCount == 0 || (computeScrollVectorForPosition = ((RecyclerView.SmoothScroller.ScrollVectorProvider) layoutManager).computeScrollVectorForPosition(itemCount - 1)) == null) {
            return -1;
        }
        int i5 = calculateScrollDistance(i, i2)[0];
        if (i5 > 0) {
            i3 = (int) Math.floor((double) (i5 / cardSliderLayoutManager.getCardHeight()));
        } else {
            i3 = (int) Math.ceil((double) (i5 / cardSliderLayoutManager.getCardHeight()));
        }
        int signum = Integer.signum(i3) * Math.min(3, Math.abs(i3));
        if (computeScrollVectorForPosition.y < 0.0f) {
            signum = -signum;
        }
        if (signum != 0 && (activeCardPosition = cardSliderLayoutManager.getActiveCardPosition()) != -1 && (i4 = activeCardPosition + signum) >= 0 && i4 < itemCount) {
            return i4;
        }
        return -1;
    }

    public View findSnapView(RecyclerView.LayoutManager layoutManager) {
        return ((CardSliderLayoutManager) layoutManager).getTopView();
    }

    public int[] calculateDistanceToFinalSnap(@NonNull RecyclerView.LayoutManager layoutManager, @NonNull View view) {
        CardSliderLayoutManager cardSliderLayoutManager = (CardSliderLayoutManager) layoutManager;
        int decoratedTop = cardSliderLayoutManager.getDecoratedTop(view);
        int activeCardTop = cardSliderLayoutManager.getActiveCardTop();
        int activeCardTop2 = cardSliderLayoutManager.getActiveCardTop() + (cardSliderLayoutManager.getCardHeight() / 2);
        int activeCardTop3 = cardSliderLayoutManager.getActiveCardTop() + cardSliderLayoutManager.getCardHeight();
        int[] iArr = {0, 0};
        int[] iArr2 = {0, 0};
        if (decoratedTop < activeCardTop2) {
            int position = cardSliderLayoutManager.getPosition(view);
            int activeCardPosition = cardSliderLayoutManager.getActiveCardPosition();
            if (position != activeCardPosition) {
                iArr[1] = (-(activeCardPosition - position)) * cardSliderLayoutManager.getCardHeight();
            } else {
                iArr[1] = decoratedTop - activeCardTop;
            }
        } else {
            iArr[1] = decoratedTop - activeCardTop3;
        }
        if (iArr[1] != 0) {
            this.recyclerView.smoothScrollBy(0, iArr[1], new LinearInterpolator());
        }
        return iArr2;
    }

    /* access modifiers changed from: protected */
    @Nullable
    public LinearSmoothScroller createSnapScroller(RecyclerView.LayoutManager layoutManager) {
        return ((CardSliderLayoutManager) layoutManager).getSmoothScroller(this.recyclerView);
    }
}
