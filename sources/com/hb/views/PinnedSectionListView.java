package com.hb.views;

import android.content.Context;
import android.database.DataSetObserver;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.PointF;
import android.graphics.Rect;
import android.graphics.drawable.GradientDrawable;
import android.os.Parcelable;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewConfiguration;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.HeaderViewListAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SectionIndexer;

public class PinnedSectionListView extends ListView {

    /* renamed from: a  reason: collision with root package name */
    private final Rect f5792a = new Rect();
    private final PointF b = new PointF();
    private int c;
    private View d;
    private MotionEvent e;
    private GradientDrawable f;
    private int g;
    private int h;
    /* access modifiers changed from: private */
    public boolean i = true;
    private final AbsListView.OnScrollListener j = new AbsListView.OnScrollListener() {
        public void onScrollStateChanged(AbsListView absListView, int i) {
            if (PinnedSectionListView.this.mDelegateOnScrollListener != null) {
                PinnedSectionListView.this.mDelegateOnScrollListener.onScrollStateChanged(absListView, i);
            }
        }

        public void onScroll(AbsListView absListView, int i, int i2, int i3) {
            if (PinnedSectionListView.this.mDelegateOnScrollListener != null) {
                PinnedSectionListView.this.mDelegateOnScrollListener.onScroll(absListView, i, i2, i3);
            }
            ListAdapter adapter = PinnedSectionListView.this.getAdapter();
            if (adapter != null && i2 != 0) {
                if (i != 0) {
                    PinnedSectionListView.this.i = false;
                } else if (PinnedSectionListView.this.getChildAt(0).getTop() == PinnedSectionListView.this.getPaddingTop()) {
                    PinnedSectionListView.this.i = true;
                } else {
                    PinnedSectionListView.this.i = false;
                }
                if (!PinnedSectionListView.isItemViewTypePinned(adapter, adapter.getItemViewType(i))) {
                    int findCurrentSectionPosition = PinnedSectionListView.this.findCurrentSectionPosition(i);
                    if (findCurrentSectionPosition > -1) {
                        PinnedSectionListView.this.ensureShadowForPosition(findCurrentSectionPosition, i, i2);
                    } else {
                        PinnedSectionListView.this.destroyPinnedShadow();
                    }
                } else if (PinnedSectionListView.this.getChildAt(0).getTop() == PinnedSectionListView.this.getPaddingTop()) {
                    PinnedSectionListView.this.destroyPinnedShadow();
                } else {
                    PinnedSectionListView.this.ensureShadowForPosition(i, i, i2);
                }
            }
        }
    };
    private final DataSetObserver k = new DataSetObserver() {
        public void onChanged() {
            PinnedSectionListView.this.recreatePinnedShadow();
        }

        public void onInvalidated() {
            PinnedSectionListView.this.recreatePinnedShadow();
        }
    };
    AbsListView.OnScrollListener mDelegateOnScrollListener;
    PinnedSection mPinnedSection;
    PinnedSection mRecycleSection;
    int mTranslateY;

    public interface PinnedSectionListAdapter extends ListAdapter {
        boolean a(int i);
    }

    static class PinnedSection {

        /* renamed from: a  reason: collision with root package name */
        public View f5796a;
        public int b;
        public long c;

        PinnedSection() {
        }
    }

    public PinnedSectionListView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        a();
    }

    public PinnedSectionListView(Context context, AttributeSet attributeSet, int i2) {
        super(context, attributeSet, i2);
        a();
    }

    private void a() {
        setOnScrollListener(this.j);
        this.c = ViewConfiguration.get(getContext()).getScaledTouchSlop();
        initShadow(true);
    }

    public void setShadowVisible(boolean z) {
        initShadow(z);
        if (this.mPinnedSection != null) {
            View view = this.mPinnedSection.f5796a;
            invalidate(view.getLeft(), view.getTop(), view.getRight(), view.getBottom() + this.h);
        }
    }

    public void initShadow(boolean z) {
        if (z) {
            if (this.f == null) {
                this.f = new GradientDrawable(GradientDrawable.Orientation.TOP_BOTTOM, new int[]{Color.parseColor("#ffa0a0a0"), Color.parseColor("#50a0a0a0"), Color.parseColor("#00a0a0a0")});
                this.h = (int) (getResources().getDisplayMetrics().density * 8.0f);
            }
        } else if (this.f != null) {
            this.f = null;
            this.h = 0;
        }
    }

    /* access modifiers changed from: package-private */
    public void createPinnedShadow(int i2) {
        PinnedSection pinnedSection = this.mRecycleSection;
        this.mRecycleSection = null;
        if (pinnedSection == null) {
            pinnedSection = new PinnedSection();
        }
        View view = getAdapter().getView(i2, pinnedSection.f5796a, this);
        AbsListView.LayoutParams layoutParams = (AbsListView.LayoutParams) view.getLayoutParams();
        if (layoutParams == null) {
            layoutParams = new AbsListView.LayoutParams(-1, -2);
        }
        int mode = View.MeasureSpec.getMode(layoutParams.height);
        int size = View.MeasureSpec.getSize(layoutParams.height);
        if (mode == 0) {
            mode = 1073741824;
        }
        int height = (getHeight() - getListPaddingTop()) - getListPaddingBottom();
        if (size > height) {
            size = height;
        }
        view.measure(View.MeasureSpec.makeMeasureSpec((getWidth() - getListPaddingLeft()) - getListPaddingRight(), 1073741824), View.MeasureSpec.makeMeasureSpec(size, mode));
        view.layout(0, 0, view.getMeasuredWidth(), view.getMeasuredHeight());
        this.mTranslateY = 0;
        pinnedSection.f5796a = view;
        pinnedSection.b = i2;
        pinnedSection.c = getAdapter().getItemId(i2);
        this.mPinnedSection = pinnedSection;
    }

    /* access modifiers changed from: package-private */
    public void destroyPinnedShadow() {
        if (this.mPinnedSection != null) {
            this.mRecycleSection = this.mPinnedSection;
            this.mPinnedSection = null;
        }
    }

    public boolean isTouchTop() {
        return this.i;
    }

    /* access modifiers changed from: package-private */
    public void ensureShadowForPosition(int i2, int i3, int i4) {
        if (i4 < 2) {
            destroyPinnedShadow();
            return;
        }
        if (!(this.mPinnedSection == null || this.mPinnedSection.b == i2)) {
            destroyPinnedShadow();
        }
        if (this.mPinnedSection == null) {
            createPinnedShadow(i2);
        }
        int i5 = i2 + 1;
        if (i5 < getCount()) {
            int findFirstVisibleSectionPosition = findFirstVisibleSectionPosition(i5, i4 - (i5 - i3));
            if (findFirstVisibleSectionPosition > -1) {
                this.g = getChildAt(findFirstVisibleSectionPosition - i3).getTop() - (this.mPinnedSection.f5796a.getBottom() + getPaddingTop());
                if (this.g < 0) {
                    this.mTranslateY = this.g;
                } else {
                    this.mTranslateY = 0;
                }
            } else {
                this.mTranslateY = 0;
                this.g = Integer.MAX_VALUE;
            }
        }
    }

    /* access modifiers changed from: package-private */
    public int findFirstVisibleSectionPosition(int i2, int i3) {
        ListAdapter adapter = getAdapter();
        for (int i4 = 0; i4 < i3; i4++) {
            int i5 = i2 + i4;
            if (isItemViewTypePinned(adapter, adapter.getItemViewType(i5))) {
                return i5;
            }
        }
        return -1;
    }

    /* access modifiers changed from: package-private */
    public int findCurrentSectionPosition(int i2) {
        ListAdapter adapter = getAdapter();
        if (adapter instanceof SectionIndexer) {
            SectionIndexer sectionIndexer = (SectionIndexer) adapter;
            int positionForSection = sectionIndexer.getPositionForSection(sectionIndexer.getSectionForPosition(i2));
            if (isItemViewTypePinned(adapter, adapter.getItemViewType(positionForSection))) {
                return positionForSection;
            }
        }
        while (i2 >= 0) {
            if (isItemViewTypePinned(adapter, adapter.getItemViewType(i2))) {
                return i2;
            }
            i2--;
        }
        return -1;
    }

    /* access modifiers changed from: package-private */
    /* JADX WARNING: Code restructure failed: missing block: B:4:0x000f, code lost:
        r0 = getFirstVisiblePosition();
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void recreatePinnedShadow() {
        /*
            r3 = this;
            r3.destroyPinnedShadow()
            android.widget.ListAdapter r0 = r3.getAdapter()
            if (r0 == 0) goto L_0x0023
            int r0 = r0.getCount()
            if (r0 <= 0) goto L_0x0023
            int r0 = r3.getFirstVisiblePosition()
            int r1 = r3.findCurrentSectionPosition(r0)
            r2 = -1
            if (r1 != r2) goto L_0x001b
            return
        L_0x001b:
            int r2 = r3.getLastVisiblePosition()
            int r2 = r2 - r0
            r3.ensureShadowForPosition(r1, r0, r2)
        L_0x0023:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.hb.views.PinnedSectionListView.recreatePinnedShadow():void");
    }

    public void setOnScrollListener(AbsListView.OnScrollListener onScrollListener) {
        if (onScrollListener == this.j) {
            super.setOnScrollListener(onScrollListener);
        } else {
            this.mDelegateOnScrollListener = onScrollListener;
        }
    }

    public void onRestoreInstanceState(Parcelable parcelable) {
        super.onRestoreInstanceState(parcelable);
        post(new Runnable() {
            public void run() {
                PinnedSectionListView.this.recreatePinnedShadow();
            }
        });
    }

    public void setAdapter(ListAdapter listAdapter) {
        if (listAdapter != null) {
            if (!(listAdapter instanceof PinnedSectionListAdapter)) {
                throw new IllegalArgumentException("Does your adapter implement PinnedSectionListAdapter?");
            } else if (listAdapter.getViewTypeCount() < 2) {
                throw new IllegalArgumentException("Does your adapter handle at least two types of views in getViewTypeCount() method: items and sections?");
            }
        }
        ListAdapter adapter = getAdapter();
        if (adapter != null) {
            adapter.unregisterDataSetObserver(this.k);
        }
        if (listAdapter != null) {
            listAdapter.registerDataSetObserver(this.k);
        }
        if (adapter != listAdapter) {
            destroyPinnedShadow();
        }
        super.setAdapter(listAdapter);
    }

    /* access modifiers changed from: protected */
    public void onLayout(boolean z, int i2, int i3, int i4, int i5) {
        super.onLayout(z, i2, i3, i4, i5);
        if (this.mPinnedSection != null && ((i4 - i2) - getPaddingLeft()) - getPaddingRight() != this.mPinnedSection.f5796a.getWidth()) {
            recreatePinnedShadow();
        }
    }

    /* access modifiers changed from: protected */
    public void dispatchDraw(Canvas canvas) {
        super.dispatchDraw(canvas);
        if (this.mPinnedSection != null) {
            int listPaddingLeft = getListPaddingLeft();
            int listPaddingTop = getListPaddingTop();
            View view = this.mPinnedSection.f5796a;
            canvas.save();
            canvas.clipRect(listPaddingLeft, listPaddingTop, view.getWidth() + listPaddingLeft, view.getHeight() + (this.f == null ? 0 : Math.min(this.h, this.g)) + listPaddingTop);
            canvas.translate((float) listPaddingLeft, (float) (listPaddingTop + this.mTranslateY));
            drawChild(canvas, this.mPinnedSection.f5796a, getDrawingTime());
            if (this.f != null && this.g > 0) {
                this.f.setBounds(this.mPinnedSection.f5796a.getLeft(), this.mPinnedSection.f5796a.getBottom(), this.mPinnedSection.f5796a.getRight(), this.mPinnedSection.f5796a.getBottom() + this.h);
                this.f.draw(canvas);
            }
            canvas.restore();
        }
    }

    public boolean dispatchTouchEvent(MotionEvent motionEvent) {
        float x = motionEvent.getX();
        float y = motionEvent.getY();
        int action = motionEvent.getAction();
        if (action == 0 && this.d == null && this.mPinnedSection != null && a(this.mPinnedSection.f5796a, x, y)) {
            this.d = this.mPinnedSection.f5796a;
            this.b.x = x;
            this.b.y = y;
            this.e = MotionEvent.obtain(motionEvent);
        }
        if (this.d == null) {
            return super.dispatchTouchEvent(motionEvent);
        }
        if (a(this.d, x, y)) {
            this.d.dispatchTouchEvent(motionEvent);
        }
        if (action == 1) {
            super.dispatchTouchEvent(motionEvent);
            c();
            b();
        } else if (action == 3) {
            b();
            return super.dispatchTouchEvent(motionEvent);
        } else if (action == 2 && Math.abs(y - this.b.y) > ((float) this.c)) {
            MotionEvent obtain = MotionEvent.obtain(motionEvent);
            obtain.setAction(3);
            this.d.dispatchTouchEvent(obtain);
            obtain.recycle();
            super.dispatchTouchEvent(this.e);
            super.dispatchTouchEvent(motionEvent);
            b();
        }
        return true;
    }

    private boolean a(View view, float f2, float f3) {
        view.getHitRect(this.f5792a);
        this.f5792a.top += this.mTranslateY;
        this.f5792a.bottom += this.mTranslateY + getPaddingTop();
        this.f5792a.left += getPaddingLeft();
        this.f5792a.right -= getPaddingRight();
        return this.f5792a.contains((int) f2, (int) f3);
    }

    private void b() {
        this.d = null;
        if (this.e != null) {
            this.e.recycle();
            this.e = null;
        }
    }

    private boolean c() {
        AdapterView.OnItemClickListener onItemClickListener;
        if (this.mPinnedSection == null || (onItemClickListener = getOnItemClickListener()) == null) {
            return false;
        }
        View view = this.mPinnedSection.f5796a;
        playSoundEffect(0);
        if (view != null) {
            view.sendAccessibilityEvent(1);
        }
        onItemClickListener.onItemClick(this, view, this.mPinnedSection.b, this.mPinnedSection.c);
        return true;
    }

    public static boolean isItemViewTypePinned(ListAdapter listAdapter, int i2) {
        if (listAdapter instanceof HeaderViewListAdapter) {
            listAdapter = ((HeaderViewListAdapter) listAdapter).getWrappedAdapter();
        }
        return ((PinnedSectionListAdapter) listAdapter).a(i2);
    }
}
