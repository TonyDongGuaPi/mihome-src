package com.xiaomi.mishopsdk.widget.special;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.AbsListView;
import android.widget.ListView;
import com.xiaomi.mishopsdk.R;
import com.xiaomi.mishopsdk.ShopApp;

public class ListViewAsGridView extends ListView implements AbsListView.OnScrollListener, ScrollObservable {
    private static int COL_NUM = 1;
    private final int GRID_PADDING = ((int) ShopApp.instance.getResources().getDimension(R.dimen.mishopsdk_padding4));
    private int mHeaderHeight;
    private ScrollObserver mOb;
    private AbsListView.OnScrollListener mOnScrollListener;

    public ListViewAsGridView(Context context) {
        super(context);
        super.setOnScrollListener(this);
    }

    public ListViewAsGridView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        super.setOnScrollListener(this);
    }

    public ListViewAsGridView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        super.setOnScrollListener(this);
    }

    public void setHeaderHeight(int i) {
        this.mHeaderHeight = i;
    }

    public void setScrollObserver(ScrollObserver scrollObserver) {
        this.mOb = scrollObserver;
    }

    public void onScroll(AbsListView absListView, int i, int i2, int i3) {
        View childAt;
        if (this.mOnScrollListener != null) {
            this.mOnScrollListener.onScroll(absListView, i, i2, i3);
        }
        if (this.mOb != null && (childAt = absListView.getChildAt(0)) != null) {
            int i4 = -childAt.getTop();
            if (i >= COL_NUM) {
                i4 += this.mHeaderHeight + this.GRID_PADDING + (((i / COL_NUM) - 1) * (childAt.getHeight() + this.GRID_PADDING));
            }
            this.mOb.onScrollTo(i4);
        }
    }

    public void onScrollStateChanged(AbsListView absListView, int i) {
        if (this.mOnScrollListener != null) {
            this.mOnScrollListener.onScrollStateChanged(absListView, i);
        }
        if (i == 0 && this.mOb != null) {
            this.mOb.onScollStop();
        }
    }

    public void setOnScrollListener(AbsListView.OnScrollListener onScrollListener) {
        this.mOnScrollListener = onScrollListener;
    }
}
