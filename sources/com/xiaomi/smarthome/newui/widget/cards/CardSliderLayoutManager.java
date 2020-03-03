package com.xiaomi.smarthome.newui.widget.cards;

import android.app.Activity;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.PointF;
import android.os.Parcel;
import android.os.Parcelable;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewCompat;
import android.support.v7.widget.LinearSmoothScroller;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.util.Log;
import android.util.SparseArray;
import android.util.SparseIntArray;
import android.view.View;
import com.xiaomi.smarthome.R;
import com.xiaomi.smarthome.newui.widget.cards.ViewUpdater;
import com.xiaomi.smarthome.shop.utils.DisplayUtils;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.Iterator;
import java.util.LinkedList;

public class CardSliderLayoutManager extends RecyclerView.LayoutManager implements RecyclerView.SmoothScroller.ScrollVectorProvider, UpdateInterceptor {
    private static final int DEFAULT_ACTIVE_CARD_LEFT_OFFSET = 50;
    private static final int DEFAULT_CARDS_GAP = 90;
    private static final int DEFAULT_CARDS_GAP_1TO2 = 84;
    private static final int DEFAULT_CARDS_GAP_2TO3 = 78;
    private static final int DEFAULT_CARD_HEIGHT = 160;
    private static final int DEFAULT_CARD_WIDTH = 148;
    private static final boolean DEFAULT_CENTER_HORIZONTAL = false;
    private static final int LEFT_CARD_COUNT = 2;
    public static final int MODE_HORIZONTAL = 0;
    public static final int MODE_VERTICL = 1;
    private static final int TOP_CARD_COUNT = 2;
    /* access modifiers changed from: private */
    public static int mScrollMode;
    /* access modifiers changed from: private */
    public int activeCardBottom;
    private int activeCardCenter;
    /* access modifiers changed from: private */
    public int activeCardLeft;
    private int activeCardLeftOffset;
    /* access modifiers changed from: private */
    public int activeCardRight;
    /* access modifiers changed from: private */
    public int activeCardTop;
    /* access modifiers changed from: private */
    public int cardHeight;
    /* access modifiers changed from: private */
    public int cardWidth;
    private float cardsGap;
    private float cardsGap1to2;
    private float cardsGap2to3;
    private final SparseIntArray cardsXCoords;
    private int curY0;
    private boolean isCenterHorizontal;
    private int lastWidth;
    private ViewUpdater.OnActiveCardChangeListener mActCardListener;
    private RecyclerView.Adapter mAdapter;
    private Context mContext;
    private UpdateInterceptor mInterceptor;
    private RecyclerView mMyRecyclerView;
    private int scrollRequestedPosition;
    private final SparseArray<View> viewCache;
    private ViewUpdater viewUpdater;

    public boolean supportsPredictiveItemAnimations() {
        return true;
    }

    public CardSliderLayoutManager(@NonNull Context context) {
        this(context, (AttributeSet) null, 0, 0);
    }

    /* JADX INFO: finally extract failed */
    public CardSliderLayoutManager(@NonNull Context context, AttributeSet attributeSet, int i, int i2) {
        Context context2 = context;
        AttributeSet attributeSet2 = attributeSet;
        this.viewCache = new SparseArray<>();
        this.cardsXCoords = new SparseIntArray();
        this.activeCardLeftOffset = 0;
        this.scrollRequestedPosition = 0;
        this.lastWidth = 0;
        this.curY0 = 0;
        this.mContext = context2;
        float f = context.getResources().getDisplayMetrics().density;
        int i3 = (int) (148.0f * f);
        int i4 = (int) (50.0f * f);
        float f2 = 90.0f * f;
        int i5 = (int) (160.0f * f);
        int height = getHeight() > 0 ? getHeight() : getPhoneHeight(context);
        int i6 = (height - i5) / 2;
        float f3 = f * 84.0f;
        float f4 = f * 78.0f;
        if (attributeSet2 != null) {
            TypedArray obtainStyledAttributes = context.getTheme().obtainStyledAttributes(attributeSet2, R.styleable.CardSlider, 0, 0);
            try {
                int dimensionPixelSize = obtainStyledAttributes.getDimensionPixelSize(2, i3);
                int dimensionPixelSize2 = obtainStyledAttributes.getDimensionPixelSize(1, i5);
                int dimensionPixelSize3 = obtainStyledAttributes.getDimensionPixelSize(0, i4);
                int i7 = (height - dimensionPixelSize2) / 2;
                float dimension = obtainStyledAttributes.getDimension(3, f2);
                float dimension2 = obtainStyledAttributes.getDimension(4, 84.0f);
                float dimension3 = obtainStyledAttributes.getDimension(5, 78.0f);
                String string = obtainStyledAttributes.getString(8);
                mScrollMode = obtainStyledAttributes.getInt(7, 0);
                boolean z = obtainStyledAttributes.getBoolean(6, false);
                obtainStyledAttributes.recycle();
                ViewUpdater loadViewUpdater = loadViewUpdater(context2, string, attributeSet2);
                if (mScrollMode == 1) {
                    initializeVertical(i7, dimensionPixelSize2, dimensionPixelSize, dimension, loadViewUpdater, z, dimension2, dimension3);
                } else {
                    initialize(dimensionPixelSize3, dimensionPixelSize, dimension, loadViewUpdater);
                }
            } catch (Throwable th) {
                obtainStyledAttributes.recycle();
                throw th;
            }
        } else if (mScrollMode == 1) {
            initializeVertical(i6, i5, i3, f2, (ViewUpdater) null, false, f3, f4);
        } else {
            initialize(i4, i3, f2, (ViewUpdater) null);
        }
    }

    public CardSliderLayoutManager(int i, int i2, float f) {
        this.viewCache = new SparseArray<>();
        this.cardsXCoords = new SparseIntArray();
        this.activeCardLeftOffset = 0;
        this.scrollRequestedPosition = 0;
        this.lastWidth = 0;
        this.curY0 = 0;
        initialize(i, i2, f, (ViewUpdater) null);
    }

    public void setViewUpdateInterceptor(UpdateInterceptor updateInterceptor) {
        this.mInterceptor = updateInterceptor;
    }

    public static int getMode() {
        return mScrollMode;
    }

    public float getCardGap1to2() {
        return this.cardsGap1to2;
    }

    public float getCardGap2to3() {
        return this.cardsGap2to3;
    }

    private void initialize(int i, int i2, float f, @Nullable ViewUpdater viewUpdater2) {
        this.cardWidth = i2;
        this.activeCardLeft = i;
        this.activeCardRight = this.activeCardLeft + this.cardWidth;
        this.activeCardCenter = this.activeCardLeft + ((this.activeCardRight - this.activeCardLeft) / 2);
        this.cardsGap = f;
        this.viewUpdater = viewUpdater2;
        if (this.viewUpdater == null) {
            if (mScrollMode == 1) {
                this.viewUpdater = new VerticalViewUpdater(this);
                ((VerticalViewUpdater) this.viewUpdater).setUpdateInterceptor(this);
            } else {
                this.viewUpdater = new DefaultViewUpdater(this);
            }
        }
        this.viewUpdater.onLayoutManagerInitialized();
    }

    private void initializeVertical(int i, int i2, int i3, float f, @Nullable ViewUpdater viewUpdater2, boolean z, float f2, float f3) {
        this.cardHeight = i2;
        this.cardWidth = i3;
        this.activeCardTop = i;
        this.activeCardBottom = this.activeCardTop + this.cardHeight;
        this.activeCardCenter = this.activeCardTop + ((this.activeCardBottom - this.activeCardTop) / 2);
        this.cardsGap = (float) (getPhoneHeight(this.mContext) - this.activeCardBottom);
        this.cardsGap1to2 = f2;
        this.cardsGap2to3 = f3;
        this.viewUpdater = viewUpdater2;
        this.curY0 = this.activeCardTop - DisplayUtils.b(this.mContext, 145.0f);
        Log.i("LAYOUT_MANAGER", "curY0:" + this.curY0);
        if (this.viewUpdater == null) {
            this.viewUpdater = new VerticalViewUpdater(this);
        }
        ((VerticalViewUpdater) this.viewUpdater).setUpdateInterceptor(this);
        this.isCenterHorizontal = z;
        if (z) {
            this.activeCardLeftOffset = (getPhoneWidth(this.mContext) - this.cardWidth) / 2;
        }
        this.viewUpdater.onLayoutManagerInitialized();
    }

    private int getPhoneWidth(Context context) {
        return DisplayUtils.a((Activity) context).x;
    }

    private int getPhoneHeight(Context context) {
        return DisplayUtils.a((Activity) context).y;
    }

    public RecyclerView.LayoutParams generateDefaultLayoutParams() {
        return new RecyclerView.LayoutParams(-2, -2);
    }

    public void onLayoutChildren(RecyclerView.Recycler recycler, RecyclerView.State state) {
        if (getItemCount() == 0) {
            removeAndRecycleAllViews(recycler);
        } else if (getChildCount() != 0 || !state.isPreLayout()) {
            int activeCardPosition = getActiveCardPosition();
            if (state.isPreLayout()) {
                LinkedList linkedList = new LinkedList();
                int childCount = getChildCount();
                for (int i = 0; i < childCount; i++) {
                    View childAt = getChildAt(i);
                    if (((RecyclerView.LayoutParams) childAt.getLayoutParams()).isItemRemoved()) {
                        linkedList.add(Integer.valueOf(getPosition(childAt)));
                    }
                }
                if (linkedList.contains(Integer.valueOf(activeCardPosition))) {
                    int intValue = ((Integer) linkedList.getLast()).intValue();
                    int intValue2 = ((Integer) linkedList.getFirst()).intValue();
                    int min = Math.min(intValue, getItemCount() - 1);
                    activeCardPosition = Math.max(intValue != intValue2 ? Math.max(intValue2, 0) : min, min);
                }
            }
            detachAndScrapAttachedViews(recycler);
            if (mScrollMode == 1) {
                fill(activeCardPosition, recycler, state);
                return;
            }
            fill(activeCardPosition, recycler, state);
            if (this.cardsXCoords.size() != 0) {
                layoutByCoords();
            }
        }
    }

    public void setAdapter(RecyclerView.Adapter adapter) {
        this.mAdapter = adapter;
    }

    public int getViewPositionInAdapter(View view) {
        int childAdapterPosition = this.mMyRecyclerView.getChildAdapterPosition(view);
        Log.i("layoutManager", "---getViewPositionInAdapter---pos:" + childAdapterPosition);
        return childAdapterPosition;
    }

    public void onAdapterChanged(RecyclerView.Adapter adapter, RecyclerView.Adapter adapter2) {
        removeAllViews();
    }

    public boolean canScrollHorizontally() {
        return getChildCount() != 0 && mScrollMode == 0;
    }

    public boolean canScrollVertically() {
        return getChildCount() != 0 && mScrollMode == 1;
    }

    public void scrollToPosition(int i) {
        if (i >= 0 && i < getItemCount()) {
            this.scrollRequestedPosition = i;
            requestLayout();
        }
    }

    public int scrollVerticallyBy(int i, RecyclerView.Recycler recycler, RecyclerView.State state) {
        int i2;
        if (mScrollMode != 1) {
            return super.scrollVerticallyBy(i, recycler, state);
        }
        this.scrollRequestedPosition = -1;
        if (i < 0) {
            i2 = scrollBottom(Math.max(i, -this.cardHeight));
        } else {
            i2 = scrollTop(i);
        }
        fill(getActiveCardPosition(), recycler, state);
        return i2;
    }

    public int scrollHorizontallyBy(int i, RecyclerView.Recycler recycler, RecyclerView.State state) {
        int i2;
        if (mScrollMode == 1) {
            return super.scrollHorizontallyBy(i, recycler, state);
        }
        this.scrollRequestedPosition = -1;
        if (i < 0) {
            i2 = scrollRight(Math.max(i, -this.cardWidth));
        } else {
            i2 = scrollLeft(i);
        }
        fill(getActiveCardPosition(), recycler, state);
        this.cardsXCoords.clear();
        int childCount = getChildCount();
        for (int i3 = 0; i3 < childCount; i3++) {
            View childAt = getChildAt(i3);
            this.cardsXCoords.put(getPosition(childAt), getDecoratedLeft(childAt));
        }
        return i2;
    }

    public PointF computeScrollVectorForPosition(int i) {
        if (mScrollMode == 1) {
            return new PointF(0.0f, (float) (i - getActiveCardPosition()));
        }
        return new PointF((float) (i - getActiveCardPosition()), 0.0f);
    }

    public void smoothScrollToPosition(RecyclerView recyclerView, RecyclerView.State state, int i) {
        if (i >= 0 && i < getItemCount()) {
            LinearSmoothScroller smoothScroller = getSmoothScroller(recyclerView);
            smoothScroller.setTargetPosition(i);
            startSmoothScroll(smoothScroller);
        }
    }

    public void onItemsRemoved(RecyclerView recyclerView, int i, int i2) {
        int activeCardPosition = getActiveCardPosition();
        if (i + i2 <= activeCardPosition) {
            this.scrollRequestedPosition = activeCardPosition - 1;
        }
    }

    public Parcelable onSaveInstanceState() {
        SavedState savedState = new SavedState();
        savedState.anchorPos = getActiveCardPosition();
        return savedState;
    }

    public void onRestoreInstanceState(Parcelable parcelable) {
        if (parcelable instanceof SavedState) {
            this.scrollRequestedPosition = ((SavedState) parcelable).anchorPos;
            requestLayout();
        }
    }

    public int getActiveCardPosition() {
        if (this.scrollRequestedPosition != -1) {
            return this.scrollRequestedPosition;
        }
        return this.viewUpdater.getActiveCardPosition();
    }

    @Nullable
    public View getTopView() {
        return this.viewUpdater.getTopView();
    }

    public int getActiveCardLeft() {
        return this.activeCardLeft;
    }

    public int getActiveCardTop() {
        return this.activeCardTop;
    }

    public int getActiveCardRight() {
        return this.activeCardRight;
    }

    public int getActiveCardBottom() {
        return this.activeCardBottom;
    }

    public int getActiveCardCenter() {
        return this.activeCardCenter;
    }

    public int getCardWidth() {
        return this.cardWidth;
    }

    public int getCardHeight() {
        return this.cardHeight;
    }

    public float getCardsGap() {
        return this.cardsGap;
    }

    public int getCurY0() {
        return this.curY0;
    }

    public LinearSmoothScroller getSmoothScroller(RecyclerView recyclerView) {
        return new LinearSmoothScroller(recyclerView.getContext()) {
            /* access modifiers changed from: protected */
            public float calculateSpeedPerPixel(DisplayMetrics displayMetrics) {
                return 0.5f;
            }

            /* JADX WARNING: Code restructure failed: missing block: B:12:0x0037, code lost:
                r5 = r4.this$0.getDecoratedLeft(r5);
             */
            /* Code decompiled incorrectly, please refer to instructions dump. */
            public int calculateDxToMakeVisible(android.view.View r5, int r6) {
                /*
                    r4 = this;
                    int r0 = com.xiaomi.smarthome.newui.widget.cards.CardSliderLayoutManager.mScrollMode
                    r1 = 1
                    if (r0 != r1) goto L_0x000c
                    int r5 = super.calculateDxToMakeVisible(r5, r6)
                    return r5
                L_0x000c:
                    com.xiaomi.smarthome.newui.widget.cards.CardSliderLayoutManager r6 = com.xiaomi.smarthome.newui.widget.cards.CardSliderLayoutManager.this
                    int r5 = r6.getDecoratedLeft(r5)
                    com.xiaomi.smarthome.newui.widget.cards.CardSliderLayoutManager r6 = com.xiaomi.smarthome.newui.widget.cards.CardSliderLayoutManager.this
                    int r6 = r6.activeCardLeft
                    if (r5 <= r6) goto L_0x0022
                    com.xiaomi.smarthome.newui.widget.cards.CardSliderLayoutManager r6 = com.xiaomi.smarthome.newui.widget.cards.CardSliderLayoutManager.this
                    int r6 = r6.activeCardLeft
                    int r6 = r6 - r5
                    return r6
                L_0x0022:
                    com.xiaomi.smarthome.newui.widget.cards.CardSliderLayoutManager r5 = com.xiaomi.smarthome.newui.widget.cards.CardSliderLayoutManager.this
                    android.view.View r5 = r5.getTopView()
                    r6 = 0
                    if (r5 == 0) goto L_0x0058
                    com.xiaomi.smarthome.newui.widget.cards.CardSliderLayoutManager r0 = com.xiaomi.smarthome.newui.widget.cards.CardSliderLayoutManager.this
                    int r0 = r0.getPosition(r5)
                    int r2 = r4.getTargetPosition()
                    if (r0 == r2) goto L_0x0056
                    com.xiaomi.smarthome.newui.widget.cards.CardSliderLayoutManager r2 = com.xiaomi.smarthome.newui.widget.cards.CardSliderLayoutManager.this
                    int r5 = r2.getDecoratedLeft(r5)
                    com.xiaomi.smarthome.newui.widget.cards.CardSliderLayoutManager r2 = com.xiaomi.smarthome.newui.widget.cards.CardSliderLayoutManager.this
                    int r2 = r2.activeCardLeft
                    if (r5 < r2) goto L_0x0056
                    com.xiaomi.smarthome.newui.widget.cards.CardSliderLayoutManager r2 = com.xiaomi.smarthome.newui.widget.cards.CardSliderLayoutManager.this
                    int r2 = r2.activeCardRight
                    if (r5 >= r2) goto L_0x0056
                    com.xiaomi.smarthome.newui.widget.cards.CardSliderLayoutManager r2 = com.xiaomi.smarthome.newui.widget.cards.CardSliderLayoutManager.this
                    int r2 = r2.activeCardRight
                    int r5 = r2 - r5
                    goto L_0x005a
                L_0x0056:
                    r5 = 0
                    goto L_0x005a
                L_0x0058:
                    r5 = 0
                    r0 = 0
                L_0x005a:
                    com.xiaomi.smarthome.newui.widget.cards.CardSliderLayoutManager r2 = com.xiaomi.smarthome.newui.widget.cards.CardSliderLayoutManager.this
                    int r2 = r2.cardWidth
                    int r3 = r4.getTargetPosition()
                    int r0 = r0 - r3
                    int r0 = r0 - r1
                    int r6 = java.lang.Math.max(r6, r0)
                    int r2 = r2 * r6
                    int r5 = r5 + r2
                    return r5
                */
                throw new UnsupportedOperationException("Method not decompiled: com.xiaomi.smarthome.newui.widget.cards.CardSliderLayoutManager.AnonymousClass1.calculateDxToMakeVisible(android.view.View, int):int");
            }

            /* JADX WARNING: Code restructure failed: missing block: B:12:0x0037, code lost:
                r5 = r4.this$0.getDecoratedTop(r5);
             */
            /* Code decompiled incorrectly, please refer to instructions dump. */
            public int calculateDyToMakeVisible(android.view.View r5, int r6) {
                /*
                    r4 = this;
                    int r0 = com.xiaomi.smarthome.newui.widget.cards.CardSliderLayoutManager.mScrollMode
                    r1 = 1
                    if (r0 == r1) goto L_0x000c
                    int r5 = super.calculateDyToMakeVisible(r5, r6)
                    return r5
                L_0x000c:
                    com.xiaomi.smarthome.newui.widget.cards.CardSliderLayoutManager r6 = com.xiaomi.smarthome.newui.widget.cards.CardSliderLayoutManager.this
                    int r5 = r6.getDecoratedTop(r5)
                    com.xiaomi.smarthome.newui.widget.cards.CardSliderLayoutManager r6 = com.xiaomi.smarthome.newui.widget.cards.CardSliderLayoutManager.this
                    int r6 = r6.activeCardTop
                    if (r5 <= r6) goto L_0x0022
                    com.xiaomi.smarthome.newui.widget.cards.CardSliderLayoutManager r6 = com.xiaomi.smarthome.newui.widget.cards.CardSliderLayoutManager.this
                    int r6 = r6.activeCardTop
                    int r6 = r6 - r5
                    return r6
                L_0x0022:
                    com.xiaomi.smarthome.newui.widget.cards.CardSliderLayoutManager r5 = com.xiaomi.smarthome.newui.widget.cards.CardSliderLayoutManager.this
                    android.view.View r5 = r5.getTopView()
                    r6 = 0
                    if (r5 == 0) goto L_0x0058
                    com.xiaomi.smarthome.newui.widget.cards.CardSliderLayoutManager r0 = com.xiaomi.smarthome.newui.widget.cards.CardSliderLayoutManager.this
                    int r0 = r0.getPosition(r5)
                    int r2 = r4.getTargetPosition()
                    if (r0 == r2) goto L_0x0056
                    com.xiaomi.smarthome.newui.widget.cards.CardSliderLayoutManager r2 = com.xiaomi.smarthome.newui.widget.cards.CardSliderLayoutManager.this
                    int r5 = r2.getDecoratedTop(r5)
                    com.xiaomi.smarthome.newui.widget.cards.CardSliderLayoutManager r2 = com.xiaomi.smarthome.newui.widget.cards.CardSliderLayoutManager.this
                    int r2 = r2.activeCardTop
                    if (r5 < r2) goto L_0x0056
                    com.xiaomi.smarthome.newui.widget.cards.CardSliderLayoutManager r2 = com.xiaomi.smarthome.newui.widget.cards.CardSliderLayoutManager.this
                    int r2 = r2.activeCardBottom
                    if (r5 >= r2) goto L_0x0056
                    com.xiaomi.smarthome.newui.widget.cards.CardSliderLayoutManager r2 = com.xiaomi.smarthome.newui.widget.cards.CardSliderLayoutManager.this
                    int r2 = r2.activeCardBottom
                    int r5 = r2 - r5
                    goto L_0x005a
                L_0x0056:
                    r5 = 0
                    goto L_0x005a
                L_0x0058:
                    r5 = 0
                    r0 = 0
                L_0x005a:
                    com.xiaomi.smarthome.newui.widget.cards.CardSliderLayoutManager r2 = com.xiaomi.smarthome.newui.widget.cards.CardSliderLayoutManager.this
                    int r2 = r2.cardHeight
                    int r3 = r4.getTargetPosition()
                    int r0 = r0 - r3
                    int r0 = r0 - r1
                    int r6 = java.lang.Math.max(r6, r0)
                    int r2 = r2 * r6
                    int r5 = r5 + r2
                    return r5
                */
                throw new UnsupportedOperationException("Method not decompiled: com.xiaomi.smarthome.newui.widget.cards.CardSliderLayoutManager.AnonymousClass1.calculateDyToMakeVisible(android.view.View, int):int");
            }
        };
    }

    private ViewUpdater loadViewUpdater(Context context, String str, AttributeSet attributeSet) {
        String str2;
        if (str == null || str.trim().length() == 0) {
            return null;
        }
        if (str.charAt(0) == '.') {
            str2 = context.getPackageName() + str;
        } else if (str.contains(".")) {
            str2 = str;
        } else {
            str2 = CardSliderLayoutManager.class.getPackage().getName() + '.' + str;
        }
        try {
            Constructor<? extends U> constructor = context.getClassLoader().loadClass(str2).asSubclass(ViewUpdater.class).getConstructor(new Class[]{CardSliderLayoutManager.class});
            constructor.setAccessible(true);
            return (ViewUpdater) constructor.newInstance(new Object[]{this});
        } catch (NoSuchMethodException e) {
            throw new IllegalStateException(attributeSet.getPositionDescription() + ": Error creating LayoutManager " + str, e);
        } catch (ClassNotFoundException e2) {
            throw new IllegalStateException(attributeSet.getPositionDescription() + ": Unable to find ViewUpdater" + str, e2);
        } catch (InstantiationException | InvocationTargetException e3) {
            throw new IllegalStateException(attributeSet.getPositionDescription() + ": Could not instantiate the ViewUpdater: " + str, e3);
        } catch (IllegalAccessException e4) {
            throw new IllegalStateException(attributeSet.getPositionDescription() + ": Cannot access non-public constructor " + str, e4);
        } catch (ClassCastException e5) {
            throw new IllegalStateException(attributeSet.getPositionDescription() + ": Class is not a ViewUpdater " + str, e5);
        }
    }

    private int scrollBottom2(int i) {
        int i2;
        int i3;
        int childCount = getChildCount();
        int i4 = 0;
        if (childCount == 0) {
            return 0;
        }
        int i5 = childCount - 1;
        View childAt = getChildAt(i5);
        int allowedBottomDelta = getAllowedBottomDelta(childAt, i, this.activeCardTop + (getPosition(childAt) * this.cardHeight));
        LinkedList linkedList = new LinkedList();
        LinkedList linkedList2 = new LinkedList();
        while (i5 >= 0) {
            View childAt2 = getChildAt(i5);
            if (getDecoratedTop(childAt2) >= this.activeCardBottom) {
                linkedList.add(childAt2);
            } else {
                linkedList2.add(childAt2);
            }
            i5--;
        }
        Iterator it = linkedList.iterator();
        while (it.hasNext()) {
            View view = (View) it.next();
            view.offsetTopAndBottom(-getAllowedBottomDelta(view, i, getHeight()));
        }
        View view2 = null;
        int activeCardPosition = getActiveCardPosition();
        int size = linkedList2.size();
        while (i4 < size) {
            View view3 = (View) linkedList2.get(i4);
            if (view2 == null || getDecoratedTop(view2) >= this.activeCardBottom) {
                view3.offsetTopAndBottom(-getAllowedBottomDelta(view3, i, this.activeCardTop + (getPosition(view3) * this.cardHeight)));
            } else {
                if (activeCardPosition - getPosition(view3) == 2) {
                    i3 = (int) Math.floor((double) (((((float) allowedBottomDelta) * 1.0f) * this.cardsGap1to2) / ((float) this.cardHeight)));
                    i2 = (int) ((((float) this.activeCardTop) - this.cardsGap1to2) - this.cardsGap2to3);
                } else {
                    i3 = (int) Math.floor((double) (((((float) allowedBottomDelta) * 1.0f) * this.cardsGap1to2) / ((float) this.cardHeight)));
                    i2 = (int) (((float) this.activeCardTop) - this.cardsGap2to3);
                }
                view3.offsetTopAndBottom(-getAllowedBottomDelta(view3, i3, i2));
            }
            i4++;
            view2 = view3;
        }
        return allowedBottomDelta;
    }

    private int scrollBottom(int i) {
        boolean z;
        int i2;
        int i3;
        int i4 = i;
        int childCount = getChildCount();
        if (childCount == 0) {
            return 0;
        }
        int i5 = childCount - 1;
        View childAt = getChildAt(i5);
        int allowedBottomDelta = getAllowedBottomDelta(childAt, i4, this.activeCardTop + (getPosition(childAt) * this.cardHeight));
        LinkedList linkedList = new LinkedList();
        LinkedList linkedList2 = new LinkedList();
        while (i5 >= 0) {
            View childAt2 = getChildAt(i5);
            if (getDecoratedTop(childAt2) >= this.activeCardBottom) {
                linkedList.add(childAt2);
            } else {
                linkedList2.add(childAt2);
            }
            i5--;
        }
        Iterator it = linkedList.iterator();
        while (it.hasNext()) {
            View view = (View) it.next();
            view.offsetTopAndBottom(-getAllowedBottomDelta(view, i4, this.activeCardTop + (getPosition(view) * this.cardHeight)));
        }
        float f = ((float) allowedBottomDelta) * 1.0f;
        int floor = (int) Math.floor((double) ((this.cardsGap2to3 * f) / ((float) this.cardHeight)));
        int i6 = this.activeCardTop;
        int size = linkedList2.size();
        int i7 = i6;
        int i8 = 0;
        int i9 = floor;
        View view2 = null;
        int i10 = 0;
        while (true) {
            if (i10 >= size) {
                z = false;
                break;
            }
            View view3 = (View) linkedList2.get(i10);
            if (view2 == null || getDecoratedTop(view2) > this.activeCardBottom) {
                view3.offsetTopAndBottom(-getAllowedBottomDelta(view3, i4, this.activeCardTop + (getPosition(view3) * this.cardHeight)));
                if (view2 == null && getDecoratedTop(view3) == this.activeCardBottom) {
                    z = true;
                    break;
                }
            } else {
                view3.offsetTopAndBottom(-getAllowedBottomDelta(view3, i9, i7));
                if (i8 == 0) {
                    i3 = (int) (((float) i7) - this.cardsGap2to3);
                    i9 = (int) Math.floor((double) ((this.cardsGap1to2 * f) / ((float) this.cardHeight)));
                } else {
                    i3 = (int) (((float) i7) - this.cardsGap1to2);
                }
                i8++;
                i7 = i3;
            }
            i10++;
            view2 = view3;
        }
        if (z) {
            int size2 = linkedList2.size();
            for (int i11 = 1; i11 < size2; i11++) {
                View view4 = (View) linkedList2.get(i11);
                if (i11 == 1) {
                    i2 = this.activeCardTop - getDecoratedTop(view4);
                } else if (i11 == 2) {
                    i2 = (int) Math.floor((double) ((((float) this.activeCardTop) - this.cardsGap2to3) - ((float) getDecoratedTop(view4))));
                } else {
                    i2 = (int) Math.floor((double) (((((float) this.activeCardTop) - this.cardsGap2to3) - (this.cardsGap1to2 * ((float) (i11 - 2)))) - ((float) getDecoratedTop(view4))));
                }
                view4.offsetTopAndBottom(i2);
            }
        }
        return allowedBottomDelta;
    }

    private int scrollRight(int i) {
        int childCount = getChildCount();
        int i2 = 0;
        if (childCount == 0) {
            return 0;
        }
        int i3 = childCount - 1;
        View childAt = getChildAt(i3);
        int allowedRightDelta = getAllowedRightDelta(childAt, i, this.activeCardLeft + (getPosition(childAt) * this.cardWidth));
        LinkedList linkedList = new LinkedList();
        LinkedList linkedList2 = new LinkedList();
        while (i3 >= 0) {
            View childAt2 = getChildAt(i3);
            if (getDecoratedLeft(childAt2) >= this.activeCardRight) {
                linkedList.add(childAt2);
            } else {
                linkedList2.add(childAt2);
            }
            i3--;
        }
        Iterator it = linkedList.iterator();
        while (it.hasNext()) {
            View view = (View) it.next();
            view.offsetLeftAndRight(-getAllowedRightDelta(view, i, this.activeCardLeft + (getPosition(view) * this.cardWidth)));
        }
        int i4 = this.activeCardLeft / 2;
        int floor = (int) Math.floor((double) (((((float) allowedRightDelta) * 1.0f) * ((float) i4)) / ((float) this.cardWidth)));
        View view2 = null;
        int size = linkedList2.size();
        int i5 = 0;
        while (i2 < size) {
            View view3 = (View) linkedList2.get(i2);
            if (view2 == null || getDecoratedLeft(view2) >= this.activeCardRight) {
                view3.offsetLeftAndRight(-getAllowedRightDelta(view3, i, this.activeCardLeft + (getPosition(view3) * this.cardWidth)));
            } else {
                view3.offsetLeftAndRight(-getAllowedRightDelta(view3, floor, this.activeCardLeft - (i4 * i5)));
                i5++;
            }
            i2++;
            view2 = view3;
        }
        return allowedRightDelta;
    }

    private int scrollLeft(int i) {
        int childCount = getChildCount();
        boolean z = false;
        if (childCount == 0) {
            return 0;
        }
        int i2 = childCount - 1;
        View childAt = getChildAt(i2);
        if (getPosition(childAt) == getItemCount() - 1) {
            z = true;
        }
        if (z) {
            i = Math.min(i, getDecoratedRight(childAt) - this.activeCardRight);
        }
        int i3 = this.activeCardLeft / 2;
        int ceil = (int) Math.ceil((double) (((((float) i) * 1.0f) * ((float) i3)) / ((float) this.cardWidth)));
        while (true) {
            if (i2 < 0) {
                break;
            }
            View childAt2 = getChildAt(i2);
            if (getDecoratedLeft(childAt2) > this.activeCardLeft) {
                childAt2.offsetLeftAndRight(getAllowedLeftDelta(childAt2, i, this.activeCardLeft));
                i2--;
            } else {
                int i4 = this.activeCardLeft - i3;
                while (i2 >= 0) {
                    View childAt3 = getChildAt(i2);
                    childAt3.offsetLeftAndRight(getAllowedLeftDelta(childAt3, ceil, i4));
                    i4 -= i3;
                    i2--;
                }
            }
        }
        return i;
    }

    private int scrollTop(int i) {
        int i2;
        boolean z;
        int childCount = getChildCount();
        int i3 = 0;
        if (childCount == 0) {
            return 0;
        }
        int i4 = childCount - 1;
        View childAt = getChildAt(i4);
        if (getPosition(childAt) == getItemCount() - 1) {
            i = Math.min(i, getDecoratedTop(childAt) - this.activeCardTop);
        }
        while (true) {
            if (i4 < 0) {
                break;
            }
            View childAt2 = getChildAt(i4);
            if (getDecoratedTop(childAt2) > this.activeCardTop) {
                childAt2.offsetTopAndBottom(getAllowedTopDelta(childAt2, i, this.activeCardTop));
                if (getDecoratedTop(childAt2) == this.activeCardTop) {
                    i2 = i4;
                    z = true;
                    break;
                }
                i4--;
            } else {
                float f = ((float) i) * 1.0f;
                int ceil = (int) Math.ceil((double) ((this.cardsGap2to3 * f) / ((float) this.cardHeight)));
                int i5 = (int) (((float) this.activeCardTop) - this.cardsGap2to3);
                int i6 = 1;
                while (i4 >= 0) {
                    View childAt3 = getChildAt(i4);
                    if (i6 > 3) {
                        Log.e("layoutManager", "---scrollTop---顶部卡片数目异常：" + i6 + ",cardPos:" + getPosition(childAt3) + " ,top:" + getDecoratedTop(childAt3));
                    }
                    childAt3.offsetTopAndBottom(getAllowedTopDelta(childAt3, ceil, i5));
                    i5 = (int) (((float) i5) - this.cardsGap1to2);
                    ceil = (int) Math.ceil((double) ((this.cardsGap1to2 * f) / ((float) this.cardHeight)));
                    i4--;
                    i6++;
                }
            }
        }
        z = false;
        i2 = -1;
        if (z) {
            for (int i7 = i2 - 1; i7 >= 0; i7--) {
                View childAt4 = getChildAt(i7);
                childAt4.offsetTopAndBottom((int) Math.floor((double) (((((float) this.activeCardTop) - this.cardsGap2to3) - (this.cardsGap1to2 * ((float) i3))) - ((float) getDecoratedTop(childAt4)))));
                i3++;
            }
        }
        return i;
    }

    private int scrollTop2(int i) {
        double d;
        int childCount = getChildCount();
        boolean z = false;
        if (childCount == 0) {
            return 0;
        }
        int i2 = childCount - 1;
        View childAt = getChildAt(i2);
        if (getPosition(childAt) == getItemCount() - 1) {
            z = true;
        }
        if (z) {
            i = Math.min(i, getDecoratedBottom(childAt) - this.activeCardBottom);
        }
        int activeCardPosition = getActiveCardPosition();
        int i3 = this.activeCardTop / 2;
        while (true) {
            if (i2 < 0) {
                break;
            }
            View childAt2 = getChildAt(i2);
            if (getDecoratedTop(childAt2) > this.activeCardTop) {
                childAt2.offsetTopAndBottom(getAllowedTopDelta(childAt2, i, this.activeCardTop));
                i2--;
            } else {
                int i4 = this.activeCardTop - i3;
                while (i2 >= 0) {
                    View childAt3 = getChildAt(i2);
                    if (activeCardPosition - getPosition(childAt2) == 2) {
                        d = Math.ceil((double) (((((float) i) * 1.0f) * this.cardsGap1to2) / ((float) this.cardHeight)));
                    } else {
                        d = Math.ceil((double) (((((float) i) * 1.0f) * this.cardsGap2to3) / ((float) this.cardHeight)));
                    }
                    childAt3.offsetTopAndBottom(getAllowedTopDelta(childAt3, (int) d, i4));
                    i4 -= i3;
                    i2--;
                }
            }
        }
        return i;
    }

    private int getAllowedLeftDelta(@NonNull View view, int i, int i2) {
        int decoratedLeft = getDecoratedLeft(view);
        return decoratedLeft - i > i2 ? -i : i2 - decoratedLeft;
    }

    private int getAllowedTopDelta(@NonNull View view, int i, int i2) {
        int decoratedTop = getDecoratedTop(view);
        return decoratedTop - i > i2 ? -i : i2 - decoratedTop;
    }

    private int getAllowedRightDelta(@NonNull View view, int i, int i2) {
        int decoratedLeft = getDecoratedLeft(view);
        return Math.abs(i) + decoratedLeft < i2 ? i : decoratedLeft - i2;
    }

    private int getAllowedBottomDelta(@NonNull View view, int i, int i2) {
        int decoratedTop = getDecoratedTop(view);
        return Math.abs(i) + decoratedTop < i2 ? i : decoratedTop - i2;
    }

    private void layoutByCoords() {
        int min = Math.min(getChildCount(), this.cardsXCoords.size());
        for (int i = 0; i < min; i++) {
            View childAt = getChildAt(i);
            int i2 = this.cardsXCoords.get(getPosition(childAt));
            layoutDecorated(childAt, i2, 0, i2 + this.cardWidth, getDecoratedBottom(childAt));
        }
        updateViewScale();
        this.cardsXCoords.clear();
    }

    private void fill(int i, RecyclerView.Recycler recycler, RecyclerView.State state) {
        int height;
        this.viewCache.clear();
        int childCount = getChildCount();
        for (int i2 = 0; i2 < childCount; i2++) {
            View childAt = getChildAt(i2);
            this.viewCache.put(getPosition(childAt), childAt);
        }
        int size = this.viewCache.size();
        for (int i3 = 0; i3 < size; i3++) {
            detachView(this.viewCache.valueAt(i3));
        }
        if (!state.isPreLayout()) {
            if (mScrollMode == 1) {
                if (getHeight() > 0 && (height = (getHeight() - this.cardHeight) / 2) != this.activeCardTop) {
                    this.activeCardTop = height;
                    this.activeCardBottom = this.activeCardTop + this.cardHeight;
                    this.activeCardCenter = this.activeCardTop + ((this.activeCardBottom - this.activeCardTop) / 2);
                    this.cardsGap = (float) (getHeight() - this.activeCardBottom);
                    this.curY0 = this.activeCardTop - DisplayUtils.b(this.mContext, 145.0f);
                    Log.i("LAYOUT_MANAGER", "curY0:" + this.curY0);
                    this.viewUpdater.onLayoutManagerInitialized();
                }
                fillTop(i, recycler);
                fillBottom(i, recycler);
            } else {
                fillLeft(i, recycler);
                fillRight(i, recycler);
            }
        }
        int size2 = this.viewCache.size();
        for (int i4 = 0; i4 < size2; i4++) {
            recycler.recycleView(this.viewCache.valueAt(i4));
        }
        updateViewScale();
    }

    private void fillLeft(int i, RecyclerView.Recycler recycler) {
        if (i != -1) {
            int i2 = this.activeCardLeft / 2;
            int max = Math.max(0, (i - 2) - 1);
            int max2 = Math.max(-1, 2 - (i - max)) * i2;
            while (max < i) {
                View view = this.viewCache.get(max);
                if (view != null) {
                    attachView(view);
                    this.viewCache.remove(max);
                } else {
                    View viewForPosition = recycler.getViewForPosition(max);
                    addView(viewForPosition);
                    measureChildWithMargins(viewForPosition, 0, 0);
                    int decoratedMeasuredHeight = getDecoratedMeasuredHeight(viewForPosition);
                    layoutDecorated(viewForPosition, max2, 0, max2 + this.cardWidth, decoratedMeasuredHeight);
                }
                max2 += i2;
                max++;
            }
        }
    }

    private void fillTop(int i, RecyclerView.Recycler recycler) {
        int i2;
        if (i != -1) {
            for (int max = Math.max(0, i - 2); max < i; max++) {
                View view = this.viewCache.get(max);
                if (view == null) {
                    View viewForPosition = recycler.getViewForPosition(max);
                    addView(viewForPosition);
                    measureChildWithMargins(viewForPosition, 0, 0);
                    int decoratedMeasuredWidth = getDecoratedMeasuredWidth(viewForPosition);
                    int i3 = i - max;
                    if (i3 == 2) {
                        i2 = (int) ((((float) this.activeCardTop) - this.cardsGap2to3) - this.cardsGap1to2);
                    } else if (i3 == 1) {
                        i2 = (int) (((float) this.activeCardTop) - this.cardsGap2to3);
                    } else {
                        i2 = this.activeCardTop;
                    }
                    int i4 = i2;
                    layoutDecorated(viewForPosition, this.activeCardLeftOffset, i4, decoratedMeasuredWidth + this.activeCardLeftOffset, i4 + this.cardHeight);
                } else if (((float) getDecoratedTop(view)) > ((((float) this.activeCardTop) - this.cardsGap2to3) - this.cardsGap1to2) - this.cardsGap1to2) {
                    attachView(view);
                    this.viewCache.remove(max);
                }
            }
        }
    }

    private void fillRight(int i, RecyclerView.Recycler recycler) {
        if (i != -1) {
            int width = getWidth();
            int itemCount = getItemCount();
            int i2 = this.activeCardLeft;
            int i3 = i;
            boolean z = true;
            while (z && i3 < itemCount) {
                View view = this.viewCache.get(i3);
                if (view != null) {
                    attachView(view);
                    this.viewCache.remove(i3);
                } else {
                    view = recycler.getViewForPosition(i3);
                    addView(view);
                    measureChildWithMargins(view, 0, 0);
                    layoutDecorated(view, i2, 0, i2 + this.cardWidth, getDecoratedMeasuredHeight(view));
                }
                i2 = getDecoratedRight(view);
                z = i2 < this.cardWidth + width;
                i3++;
            }
        }
    }

    private void fillBottom(int i, RecyclerView.Recycler recycler) {
        if (i != -1) {
            int height = getHeight();
            int itemCount = getItemCount();
            int i2 = this.activeCardTop;
            int i3 = i;
            boolean z = true;
            while (z && i3 < itemCount) {
                View view = this.viewCache.get(i3);
                if (view != null) {
                    attachView(view);
                    this.viewCache.remove(i3);
                } else {
                    view = recycler.getViewForPosition(i3);
                    addView(view);
                    measureChildWithMargins(view, 0, 0);
                    layoutDecorated(view, this.activeCardLeftOffset, i2, getDecoratedMeasuredWidth(view) + this.activeCardLeftOffset, i2 + this.cardHeight);
                }
                i2 = getDecoratedBottom(view);
                z = i2 < this.cardHeight + height;
                i3++;
            }
        }
    }

    public void updateViewScale() {
        this.viewUpdater.updateView();
    }

    public void setMyRecyclerView(RecyclerView recyclerView) {
        this.mMyRecyclerView = recyclerView;
    }

    public void onUpdateViewAlpha(@NonNull View view, int i, float f) {
        if ((this.viewUpdater instanceof VerticalViewUpdater) && this.mInterceptor != null) {
            this.mInterceptor.onUpdateViewAlpha(view, i, f);
        }
    }

    public void onUpdateViewScale(@NonNull View view, int i, float f) {
        if (ViewCompat.getScaleY(view) != f) {
            ViewCompat.setScaleX(view, f);
            ViewCompat.setScaleY(view, f);
        }
    }

    public void onUpdateViewZ(@NonNull View view, int i, float f) {
        if (ViewCompat.getZ(view) != f) {
            ViewCompat.setZ(view, f);
        }
    }

    public void onUpdateViewTransitionY(@NonNull View view, int i, float f) {
        if (ViewCompat.getTranslationY(view) != f) {
            ViewCompat.setTranslationY(view, f);
        }
    }

    public void onUpdateViewBackgroud(@NonNull View view, int i, int i2) {
        view.setBackgroundResource(i2);
    }

    private static class SavedState implements Parcelable {
        public static final Parcelable.Creator<SavedState> CREATOR = new Parcelable.Creator<SavedState>() {
            public SavedState createFromParcel(Parcel parcel) {
                return new SavedState(parcel);
            }

            public SavedState[] newArray(int i) {
                return new SavedState[i];
            }
        };
        int anchorPos;

        public int describeContents() {
            return 0;
        }

        SavedState() {
        }

        SavedState(Parcel parcel) {
            this.anchorPos = parcel.readInt();
        }

        public SavedState(SavedState savedState) {
            this.anchorPos = savedState.anchorPos;
        }

        public void writeToParcel(Parcel parcel, int i) {
            parcel.writeInt(this.anchorPos);
        }
    }

    public void setOnActiveCardChangeListener(ViewUpdater.OnActiveCardChangeListener onActiveCardChangeListener) {
        this.mActCardListener = onActiveCardChangeListener;
        this.viewUpdater.setOnActiveCardChangeListener(this.mActCardListener);
    }
}
