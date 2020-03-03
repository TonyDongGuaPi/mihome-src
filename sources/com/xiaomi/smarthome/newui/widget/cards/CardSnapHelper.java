package com.xiaomi.smarthome.newui.widget.cards;

import android.graphics.PointF;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearSmoothScroller;
import android.support.v7.widget.LinearSnapHelper;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.animation.AccelerateInterpolator;
import java.security.InvalidParameterException;

public class CardSnapHelper extends LinearSnapHelper {
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
            i3 = (int) Math.floor((double) (i5 / cardSliderLayoutManager.getCardWidth()));
        } else {
            i3 = (int) Math.ceil((double) (i5 / cardSliderLayoutManager.getCardWidth()));
        }
        int signum = Integer.signum(i3) * Math.min(3, Math.abs(i3));
        if (computeScrollVectorForPosition.x < 0.0f) {
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
        int decoratedLeft = cardSliderLayoutManager.getDecoratedLeft(view);
        int activeCardLeft = cardSliderLayoutManager.getActiveCardLeft();
        int activeCardLeft2 = cardSliderLayoutManager.getActiveCardLeft() + (cardSliderLayoutManager.getCardWidth() / 2);
        int activeCardLeft3 = cardSliderLayoutManager.getActiveCardLeft() + cardSliderLayoutManager.getCardWidth();
        int[] iArr = {0, 0};
        if (decoratedLeft < activeCardLeft2) {
            int position = cardSliderLayoutManager.getPosition(view);
            int activeCardPosition = cardSliderLayoutManager.getActiveCardPosition();
            if (position != activeCardPosition) {
                iArr[0] = (-(activeCardPosition - position)) * cardSliderLayoutManager.getCardWidth();
            } else {
                iArr[0] = decoratedLeft - activeCardLeft;
            }
        } else {
            iArr[0] = (decoratedLeft - activeCardLeft3) + 1;
        }
        if (iArr[0] != 0) {
            this.recyclerView.smoothScrollBy(iArr[0], 0, new AccelerateInterpolator());
        }
        return new int[]{0, 0};
    }

    /* access modifiers changed from: protected */
    @Nullable
    public LinearSmoothScroller createSnapScroller(RecyclerView.LayoutManager layoutManager) {
        return ((CardSliderLayoutManager) layoutManager).getSmoothScroller(this.recyclerView);
    }
}
