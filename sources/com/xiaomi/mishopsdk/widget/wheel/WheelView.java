package com.xiaomi.mishopsdk.widget.wheel;

import android.content.Context;
import android.database.DataSetObserver;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Interpolator;
import android.widget.LinearLayout;
import com.xiaomi.mishopsdk.widget.wheel.WheelScroller;
import com.xiaomi.mishopsdk.widget.wheel.adapters.WheelViewAdapter;
import java.util.LinkedList;
import java.util.List;

public class WheelView extends View {
    private static final int DEF_VISIBLE_ITEMS = 5;
    private static final int ITEM_OFFSET_PERCENT = 10;
    private static final int PADDING = 10;
    private static final int[] SHADOWS_COLORS = {-15658735, 11184810, 11184810};
    private GradientDrawable bottomShadow;
    private Drawable centerDrawable;
    private List<OnWheelChangedListener> changingListeners = new LinkedList();
    private List<OnWheelClickedListener> clickingListeners = new LinkedList();
    private int currentItem = 0;
    private DataSetObserver dataObserver = new DataSetObserver() {
        public void onChanged() {
            WheelView.this.invalidateWheel(false);
        }

        public void onInvalidated() {
            WheelView.this.invalidateWheel(true);
        }
    };
    private int firstItem;
    boolean isCyclic = false;
    /* access modifiers changed from: private */
    public boolean isScrollingPerformed;
    private int itemHeight = 0;
    private LinearLayout itemsLayout;
    private WheelRecycle recycle = new WheelRecycle(this);
    /* access modifiers changed from: private */
    public WheelScroller scroller;
    WheelScroller.ScrollingListener scrollingListener = new WheelScroller.ScrollingListener() {
        public void onStarted() {
            boolean unused = WheelView.this.isScrollingPerformed = true;
            WheelView.this.notifyScrollingListenersAboutStart();
        }

        public void onScroll(int i) {
            WheelView.this.doScroll(i);
            int height = WheelView.this.getHeight();
            if (WheelView.this.scrollingOffset > height) {
                int unused = WheelView.this.scrollingOffset = height;
                WheelView.this.scroller.stopScrolling();
                return;
            }
            int i2 = -height;
            if (WheelView.this.scrollingOffset < i2) {
                int unused2 = WheelView.this.scrollingOffset = i2;
                WheelView.this.scroller.stopScrolling();
            }
        }

        public void onFinished() {
            if (WheelView.this.isScrollingPerformed) {
                WheelView.this.notifyScrollingListenersAboutEnd();
                boolean unused = WheelView.this.isScrollingPerformed = false;
            }
            int unused2 = WheelView.this.scrollingOffset = 0;
            WheelView.this.invalidate();
        }

        public void onJustify() {
            if (Math.abs(WheelView.this.scrollingOffset) > 1) {
                WheelView.this.scroller.scroll(WheelView.this.scrollingOffset, 0);
            }
        }
    };
    private List<OnWheelScrollListener> scrollingListeners = new LinkedList();
    /* access modifiers changed from: private */
    public int scrollingOffset;
    private GradientDrawable topShadow;
    private WheelViewAdapter viewAdapter;
    private int visibleItems = 5;

    private void drawShadows(Canvas canvas) {
    }

    private void initResourcesIfNecessary() {
    }

    public WheelView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        initData(context);
    }

    public WheelView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        initData(context);
    }

    public WheelView(Context context) {
        super(context);
        initData(context);
    }

    private void initData(Context context) {
        this.scroller = new WheelScroller(getContext(), this.scrollingListener);
    }

    public void setInterpolator(Interpolator interpolator) {
        this.scroller.setInterpolator(interpolator);
    }

    public int getVisibleItems() {
        return this.visibleItems;
    }

    public void setVisibleItems(int i) {
        this.visibleItems = i;
    }

    public WheelViewAdapter getViewAdapter() {
        return this.viewAdapter;
    }

    public void setViewAdapter(WheelViewAdapter wheelViewAdapter) {
        if (this.viewAdapter != null) {
            this.viewAdapter.unregisterDataSetObserver(this.dataObserver);
        }
        this.viewAdapter = wheelViewAdapter;
        if (this.viewAdapter != null) {
            this.viewAdapter.registerDataSetObserver(this.dataObserver);
        }
        invalidateWheel(true);
    }

    public void addChangingListener(OnWheelChangedListener onWheelChangedListener) {
        this.changingListeners.add(onWheelChangedListener);
    }

    public void removeChangingListener(OnWheelChangedListener onWheelChangedListener) {
        this.changingListeners.remove(onWheelChangedListener);
    }

    /* access modifiers changed from: protected */
    public void notifyChangingListeners(int i, int i2) {
        for (OnWheelChangedListener onChanged : this.changingListeners) {
            onChanged.onChanged(this, i, i2);
        }
    }

    public void addScrollingListener(OnWheelScrollListener onWheelScrollListener) {
        this.scrollingListeners.add(onWheelScrollListener);
    }

    public void removeScrollingListener(OnWheelScrollListener onWheelScrollListener) {
        this.scrollingListeners.remove(onWheelScrollListener);
    }

    /* access modifiers changed from: protected */
    public void notifyScrollingListenersAboutStart() {
        for (OnWheelScrollListener onScrollingStarted : this.scrollingListeners) {
            onScrollingStarted.onScrollingStarted(this);
        }
    }

    /* access modifiers changed from: protected */
    public void notifyScrollingListenersAboutEnd() {
        for (OnWheelScrollListener onScrollingFinished : this.scrollingListeners) {
            onScrollingFinished.onScrollingFinished(this);
        }
    }

    public void addClickingListener(OnWheelClickedListener onWheelClickedListener) {
        this.clickingListeners.add(onWheelClickedListener);
    }

    public void removeClickingListener(OnWheelClickedListener onWheelClickedListener) {
        this.clickingListeners.remove(onWheelClickedListener);
    }

    /* access modifiers changed from: protected */
    public void notifyClickListenersAboutClick(int i) {
        for (OnWheelClickedListener onItemClicked : this.clickingListeners) {
            onItemClicked.onItemClicked(this, i);
        }
    }

    public int getCurrentItem() {
        return this.currentItem;
    }

    public void setCurrentItem(int i, boolean z) {
        int min;
        if (this.viewAdapter != null && this.viewAdapter.getItemsCount() != 0) {
            int itemsCount = this.viewAdapter.getItemsCount();
            if (i < 0 || i >= itemsCount) {
                if (this.isCyclic) {
                    while (i < 0) {
                        i += itemsCount;
                    }
                    i %= itemsCount;
                } else {
                    return;
                }
            }
            if (i == this.currentItem) {
                return;
            }
            if (z) {
                int i2 = i - this.currentItem;
                if (this.isCyclic && (min = (itemsCount + Math.min(i, this.currentItem)) - Math.max(i, this.currentItem)) < Math.abs(i2)) {
                    i2 = i2 < 0 ? min : -min;
                }
                scroll(i2, 0);
                return;
            }
            this.scrollingOffset = 0;
            int i3 = this.currentItem;
            this.currentItem = i;
            notifyChangingListeners(i3, this.currentItem);
            invalidate();
        }
    }

    public void setCurrentItem(int i) {
        setCurrentItem(i, false);
    }

    public boolean isCyclic() {
        return this.isCyclic;
    }

    public void setCyclic(boolean z) {
        this.isCyclic = z;
        invalidateWheel(false);
    }

    public void invalidateWheel(boolean z) {
        if (z) {
            this.recycle.clearAll();
            if (this.itemsLayout != null) {
                this.itemsLayout.removeAllViews();
            }
            this.scrollingOffset = 0;
        } else if (this.itemsLayout != null) {
            this.recycle.recycleItems(this.itemsLayout, this.firstItem, new ItemsRange());
        }
        invalidate();
    }

    private int getDesiredHeight(LinearLayout linearLayout) {
        if (!(linearLayout == null || linearLayout.getChildAt(0) == null)) {
            this.itemHeight = linearLayout.getChildAt(0).getMeasuredHeight();
        }
        return Math.max((this.itemHeight * this.visibleItems) - ((this.itemHeight * 10) / 50), getSuggestedMinimumHeight());
    }

    private int getItemHeight() {
        if (this.itemHeight != 0) {
            return this.itemHeight;
        }
        if (this.itemsLayout == null || this.itemsLayout.getChildAt(0) == null) {
            return getHeight() / this.visibleItems;
        }
        this.itemHeight = this.itemsLayout.getChildAt(0).getHeight();
        return this.itemHeight;
    }

    private int calculateLayoutWidth(int i, int i2) {
        initResourcesIfNecessary();
        this.itemsLayout.setLayoutParams(new ViewGroup.LayoutParams(-2, -2));
        this.itemsLayout.measure(View.MeasureSpec.makeMeasureSpec(i, 0), View.MeasureSpec.makeMeasureSpec(0, 0));
        int measuredWidth = this.itemsLayout.getMeasuredWidth();
        if (i2 != 1073741824) {
            int max = Math.max(measuredWidth + 20, getSuggestedMinimumWidth());
            if (i2 != Integer.MIN_VALUE || i >= max) {
                i = max;
            }
        }
        this.itemsLayout.measure(View.MeasureSpec.makeMeasureSpec(i - 20, 1073741824), View.MeasureSpec.makeMeasureSpec(0, 0));
        return i;
    }

    /* access modifiers changed from: protected */
    public void onMeasure(int i, int i2) {
        int mode = View.MeasureSpec.getMode(i);
        int mode2 = View.MeasureSpec.getMode(i2);
        int size = View.MeasureSpec.getSize(i);
        int size2 = View.MeasureSpec.getSize(i2);
        buildViewForMeasuring();
        int calculateLayoutWidth = calculateLayoutWidth(size, mode);
        if (mode2 != 1073741824) {
            int desiredHeight = getDesiredHeight(this.itemsLayout);
            size2 = mode2 == Integer.MIN_VALUE ? Math.min(desiredHeight, size2) : desiredHeight;
        }
        setMeasuredDimension(calculateLayoutWidth, size2);
    }

    /* access modifiers changed from: protected */
    public void onLayout(boolean z, int i, int i2, int i3, int i4) {
        layout(i3 - i, i4 - i2);
    }

    private void layout(int i, int i2) {
        this.itemsLayout.layout(0, 0, i - 20, i2);
    }

    /* access modifiers changed from: protected */
    public void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if (this.viewAdapter != null && this.viewAdapter.getItemsCount() > 0) {
            updateView();
            drawItems(canvas);
            drawCenterRect(canvas);
        }
        drawShadows(canvas);
    }

    private void drawItems(Canvas canvas) {
        canvas.save();
        canvas.translate(10.0f, (float) ((-(((this.currentItem - this.firstItem) * getItemHeight()) + ((getItemHeight() - getHeight()) / 2))) + this.scrollingOffset));
        this.itemsLayout.draw(canvas);
        canvas.restore();
    }

    private void drawCenterRect(Canvas canvas) {
        int height = getHeight() / 2;
        double itemHeight2 = (double) (getItemHeight() / 2);
        Double.isNaN(itemHeight2);
        int i = (int) (itemHeight2 * 1.2d);
        if (this.centerDrawable != null) {
            this.centerDrawable.setBounds(0, height - i, getWidth(), height + i);
            this.centerDrawable.draw(canvas);
        }
    }

    public boolean onTouchEvent(MotionEvent motionEvent) {
        int i;
        if (!isEnabled() || getViewAdapter() == null) {
            return true;
        }
        switch (motionEvent.getAction()) {
            case 1:
                if (!this.isScrollingPerformed) {
                    int y = ((int) motionEvent.getY()) - (getHeight() / 2);
                    if (y > 0) {
                        i = y + (getItemHeight() / 2);
                    } else {
                        i = y - (getItemHeight() / 2);
                    }
                    int itemHeight2 = i / getItemHeight();
                    if (itemHeight2 != 0 && isValidItemIndex(this.currentItem + itemHeight2)) {
                        notifyClickListenersAboutClick(this.currentItem + itemHeight2);
                        break;
                    }
                }
                break;
            case 2:
                if (getParent() != null) {
                    getParent().requestDisallowInterceptTouchEvent(true);
                    break;
                }
                break;
        }
        return this.scroller.onTouchEvent(motionEvent);
    }

    /* access modifiers changed from: private */
    public void doScroll(int i) {
        this.scrollingOffset += i;
        int itemHeight2 = getItemHeight();
        int i2 = this.scrollingOffset / itemHeight2;
        int i3 = this.currentItem - i2;
        int itemsCount = this.viewAdapter.getItemsCount();
        int i4 = this.scrollingOffset % itemHeight2;
        if (Math.abs(i4) <= itemHeight2 / 2) {
            i4 = 0;
        }
        if (this.isCyclic && itemsCount > 0) {
            if (i4 > 0) {
                i3--;
                i2++;
            } else if (i4 < 0) {
                i3++;
                i2--;
            }
            while (i3 < 0) {
                i3 += itemsCount;
            }
            i3 %= itemsCount;
        } else if (i3 < 0) {
            i2 = this.currentItem;
            i3 = 0;
        } else if (i3 >= itemsCount) {
            i2 = (this.currentItem - itemsCount) + 1;
            i3 = itemsCount - 1;
        } else if (i3 > 0 && i4 > 0) {
            i3--;
            i2++;
        } else if (i3 < itemsCount - 1 && i4 < 0) {
            i3++;
            i2--;
        }
        int i5 = this.scrollingOffset;
        if (i3 != this.currentItem) {
            setCurrentItem(i3, false);
        } else {
            invalidate();
        }
        this.scrollingOffset = i5 - (i2 * itemHeight2);
        if (this.scrollingOffset > getHeight()) {
            this.scrollingOffset = (this.scrollingOffset % getHeight()) + getHeight();
        }
    }

    public void scroll(int i, int i2) {
        this.scroller.scroll((i * getItemHeight()) - this.scrollingOffset, i2);
    }

    private ItemsRange getItemsRange() {
        if (getItemHeight() == 0) {
            return null;
        }
        int i = this.currentItem;
        int i2 = 1;
        while (getItemHeight() * i2 < getHeight()) {
            i--;
            i2 += 2;
        }
        if (this.scrollingOffset != 0) {
            if (this.scrollingOffset > 0) {
                i--;
            }
            int itemHeight2 = this.scrollingOffset / getItemHeight();
            i -= itemHeight2;
            double d = (double) (i2 + 1);
            double asin = Math.asin((double) itemHeight2);
            Double.isNaN(d);
            i2 = (int) (d + asin);
        }
        return new ItemsRange(i, i2);
    }

    private boolean rebuildItems() {
        boolean z;
        ItemsRange itemsRange = getItemsRange();
        if (this.itemsLayout != null) {
            int recycleItems = this.recycle.recycleItems(this.itemsLayout, this.firstItem, itemsRange);
            z = this.firstItem != recycleItems;
            this.firstItem = recycleItems;
        } else {
            createItemsLayout();
            z = true;
        }
        if (!z) {
            z = (this.firstItem == itemsRange.getFirst() && this.itemsLayout.getChildCount() == itemsRange.getCount()) ? false : true;
        }
        if (this.firstItem <= itemsRange.getFirst() || this.firstItem > itemsRange.getLast()) {
            this.firstItem = itemsRange.getFirst();
        } else {
            int i = this.firstItem - 1;
            while (i >= itemsRange.getFirst() && addViewItem(i, true)) {
                this.firstItem = i;
                i--;
            }
        }
        int i2 = this.firstItem;
        for (int childCount = this.itemsLayout.getChildCount(); childCount < itemsRange.getCount(); childCount++) {
            if (!addViewItem(this.firstItem + childCount, false) && this.itemsLayout.getChildCount() == 0) {
                i2++;
            }
        }
        this.firstItem = i2;
        return z;
    }

    private void updateView() {
        if (rebuildItems()) {
            calculateLayoutWidth(getWidth(), 1073741824);
            layout(getWidth(), getHeight());
        }
    }

    private void createItemsLayout() {
        if (this.itemsLayout == null) {
            this.itemsLayout = new LinearLayout(getContext());
            this.itemsLayout.setOrientation(1);
        }
    }

    private void buildViewForMeasuring() {
        if (this.itemsLayout != null) {
            this.recycle.recycleItems(this.itemsLayout, this.firstItem, new ItemsRange());
        } else {
            createItemsLayout();
        }
        int i = this.visibleItems / 2;
        for (int i2 = this.currentItem + i; i2 >= this.currentItem - i; i2--) {
            if (addViewItem(i2, true)) {
                this.firstItem = i2;
            }
        }
    }

    private boolean addViewItem(int i, boolean z) {
        View itemView = getItemView(i);
        if (itemView == null) {
            return false;
        }
        if (z) {
            this.itemsLayout.addView(itemView, 0);
            return true;
        }
        this.itemsLayout.addView(itemView);
        return true;
    }

    private boolean isValidItemIndex(int i) {
        return this.viewAdapter != null && this.viewAdapter.getItemsCount() > 0 && (this.isCyclic || (i >= 0 && i < this.viewAdapter.getItemsCount()));
    }

    private View getItemView(int i) {
        if (this.viewAdapter == null || this.viewAdapter.getItemsCount() == 0) {
            return null;
        }
        int itemsCount = this.viewAdapter.getItemsCount();
        if (!isValidItemIndex(i)) {
            return this.viewAdapter.getEmptyItem(this.recycle.getEmptyItem(), this.itemsLayout);
        }
        while (i < 0) {
            i += itemsCount;
        }
        return this.viewAdapter.getItem(i % itemsCount, this.recycle.getItem(), this.itemsLayout);
    }

    public void stopScrolling() {
        this.scroller.stopScrolling();
    }

    public boolean dispatchTouchEvent(MotionEvent motionEvent) {
        getParent().requestDisallowInterceptTouchEvent(true);
        return super.dispatchTouchEvent(motionEvent);
    }
}
