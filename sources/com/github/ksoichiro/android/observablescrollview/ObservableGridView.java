package com.github.ksoichiro.android.observablescrollview;

import android.content.Context;
import android.database.DataSetObservable;
import android.database.DataSetObserver;
import android.os.Build;
import android.os.Parcel;
import android.os.Parcelable;
import android.util.AttributeSet;
import android.util.SparseIntArray;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.FrameLayout;
import android.widget.GridView;
import android.widget.ListAdapter;
import android.widget.WrapperListAdapter;
import java.util.ArrayList;
import java.util.Iterator;

public class ObservableGridView extends GridView implements Scrollable {

    /* renamed from: a  reason: collision with root package name */
    private int f5313a;
    private int b = -1;
    private int c;
    private int d;
    private int e;
    private SparseIntArray f;
    private ObservableScrollViewCallbacks g;
    private ScrollState h;
    private boolean i;
    private boolean j;
    private boolean k;
    private MotionEvent l;
    private ViewGroup m;
    private ArrayList<FixedViewInfo> n;
    private ArrayList<FixedViewInfo> o;
    /* access modifiers changed from: private */
    public AbsListView.OnScrollListener p;
    private AbsListView.OnScrollListener q = new AbsListView.OnScrollListener() {
        public void onScrollStateChanged(AbsListView absListView, int i) {
            if (ObservableGridView.this.p != null) {
                ObservableGridView.this.p.onScrollStateChanged(absListView, i);
            }
        }

        public void onScroll(AbsListView absListView, int i, int i2, int i3) {
            if (ObservableGridView.this.p != null) {
                ObservableGridView.this.p.onScroll(absListView, i, i2, i3);
            }
            ObservableGridView.this.b();
        }
    };

    public static class FixedViewInfo {

        /* renamed from: a  reason: collision with root package name */
        public View f5316a;
        public ViewGroup b;
        public Object c;
        public boolean d;
    }

    public void setClipChildren(boolean z) {
    }

    public ObservableGridView(Context context) {
        super(context);
        a();
    }

    public ObservableGridView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        a();
    }

    public ObservableGridView(Context context, AttributeSet attributeSet, int i2) {
        super(context, attributeSet, i2);
        a();
    }

    public void onRestoreInstanceState(Parcelable parcelable) {
        SavedState savedState = (SavedState) parcelable;
        this.f5313a = savedState.f5319a;
        this.b = savedState.b;
        this.c = savedState.c;
        this.d = savedState.d;
        this.e = savedState.e;
        this.f = savedState.f;
        super.onRestoreInstanceState(savedState.getSuperState());
    }

    public Parcelable onSaveInstanceState() {
        SavedState savedState = new SavedState(super.onSaveInstanceState());
        savedState.f5319a = this.f5313a;
        savedState.b = this.b;
        savedState.c = this.c;
        savedState.d = this.d;
        savedState.e = this.e;
        savedState.f = this.f;
        return savedState;
    }

    public boolean onInterceptTouchEvent(MotionEvent motionEvent) {
        if (this.g != null && motionEvent.getActionMasked() == 0) {
            this.j = true;
            this.i = true;
            this.g.onDownMotionEvent();
        }
        return super.onInterceptTouchEvent(motionEvent);
    }

    public boolean onTouchEvent(MotionEvent motionEvent) {
        final ViewGroup viewGroup;
        if (this.g != null) {
            switch (motionEvent.getActionMasked()) {
                case 1:
                case 3:
                    this.k = false;
                    this.j = false;
                    this.g.onUpOrCancelMotionEvent(this.h);
                    break;
                case 2:
                    if (this.l == null) {
                        this.l = motionEvent;
                    }
                    float y = motionEvent.getY() - this.l.getY();
                    this.l = MotionEvent.obtainNoHistory(motionEvent);
                    if (((float) getCurrentScrollY()) - y <= 0.0f) {
                        if (this.k) {
                            return false;
                        }
                        if (this.m == null) {
                            viewGroup = (ViewGroup) getParent();
                        } else {
                            viewGroup = this.m;
                        }
                        float f2 = 0.0f;
                        float f3 = 0.0f;
                        View view = this;
                        while (view != null && view != viewGroup) {
                            f2 += (float) (view.getLeft() - view.getScrollX());
                            f3 += (float) (view.getTop() - view.getScrollY());
                            view = (View) view.getParent();
                        }
                        final MotionEvent obtainNoHistory = MotionEvent.obtainNoHistory(motionEvent);
                        obtainNoHistory.offsetLocation(f2, f3);
                        if (!viewGroup.onInterceptTouchEvent(obtainNoHistory)) {
                            return super.onTouchEvent(motionEvent);
                        }
                        this.k = true;
                        obtainNoHistory.setAction(0);
                        post(new Runnable() {
                            public void run() {
                                viewGroup.dispatchTouchEvent(obtainNoHistory);
                            }
                        });
                        return false;
                    }
                    break;
            }
        }
        return super.onTouchEvent(motionEvent);
    }

    public void addFooterView(View view) {
        addFooterView(view, (Object) null, true);
    }

    public void addFooterView(View view, Object obj, boolean z) {
        ListAdapter adapter = getAdapter();
        if (adapter == null || (adapter instanceof HeaderViewGridAdapter)) {
            ViewGroup.LayoutParams layoutParams = view.getLayoutParams();
            FixedViewInfo fixedViewInfo = new FixedViewInfo();
            FullWidthFixedViewLayout fullWidthFixedViewLayout = new FullWidthFixedViewLayout(getContext());
            if (layoutParams != null) {
                view.setLayoutParams(new FrameLayout.LayoutParams(layoutParams.width, layoutParams.height));
                fullWidthFixedViewLayout.setLayoutParams(new AbsListView.LayoutParams(layoutParams.width, layoutParams.height));
            }
            fullWidthFixedViewLayout.addView(view);
            fixedViewInfo.f5316a = view;
            fixedViewInfo.b = fullWidthFixedViewLayout;
            fixedViewInfo.c = obj;
            fixedViewInfo.d = z;
            this.o.add(fixedViewInfo);
            if (adapter != null) {
                ((HeaderViewGridAdapter) adapter).c();
                return;
            }
            return;
        }
        throw new IllegalStateException("Cannot add header view to grid -- setAdapter has already been called.");
    }

    public boolean removeFooterView(View view) {
        boolean z = false;
        if (this.o.size() <= 0) {
            return false;
        }
        ListAdapter adapter = getAdapter();
        if (adapter != null && ((HeaderViewGridAdapter) adapter).b(view)) {
            z = true;
        }
        a(view, this.o);
        return z;
    }

    public void setOnScrollListener(AbsListView.OnScrollListener onScrollListener) {
        this.p = onScrollListener;
    }

    public void setScrollViewCallbacks(ObservableScrollViewCallbacks observableScrollViewCallbacks) {
        this.g = observableScrollViewCallbacks;
    }

    public void setTouchInterceptionViewGroup(ViewGroup viewGroup) {
        this.m = viewGroup;
    }

    public void scrollVerticallyTo(int i2) {
        scrollTo(0, i2);
    }

    public int getCurrentScrollY() {
        return this.e;
    }

    public void setAdapter(ListAdapter listAdapter) {
        if (this.n.size() > 0) {
            HeaderViewGridAdapter headerViewGridAdapter = new HeaderViewGridAdapter(this.n, this.o, listAdapter);
            int numColumnsCompat = getNumColumnsCompat();
            if (1 < numColumnsCompat) {
                headerViewGridAdapter.a(numColumnsCompat);
            }
            super.setAdapter(headerViewGridAdapter);
            return;
        }
        super.setAdapter(listAdapter);
    }

    public void addHeaderView(View view, Object obj, boolean z) {
        ListAdapter adapter = getAdapter();
        if (adapter == null || (adapter instanceof HeaderViewGridAdapter)) {
            FixedViewInfo fixedViewInfo = new FixedViewInfo();
            FullWidthFixedViewLayout fullWidthFixedViewLayout = new FullWidthFixedViewLayout(getContext());
            fullWidthFixedViewLayout.addView(view);
            fixedViewInfo.f5316a = view;
            fixedViewInfo.b = fullWidthFixedViewLayout;
            fixedViewInfo.c = obj;
            fixedViewInfo.d = z;
            this.n.add(fixedViewInfo);
            if (adapter != null) {
                ((HeaderViewGridAdapter) adapter).c();
                return;
            }
            return;
        }
        throw new IllegalStateException("Cannot add header view to grid -- setAdapter has already been called.");
    }

    public void addHeaderView(View view) {
        addHeaderView(view, (Object) null, true);
    }

    public int getHeaderViewCount() {
        return this.n.size();
    }

    public boolean removeHeaderView(View view) {
        boolean z = false;
        if (this.n.size() <= 0) {
            return false;
        }
        ListAdapter adapter = getAdapter();
        if (adapter != null && (adapter instanceof HeaderViewGridAdapter) && ((HeaderViewGridAdapter) adapter).a(view)) {
            z = true;
        }
        a(view, this.n);
        return z;
    }

    /* access modifiers changed from: protected */
    public void onMeasure(int i2, int i3) {
        super.onMeasure(i2, i3);
        ListAdapter adapter = getAdapter();
        if (adapter != null && (adapter instanceof HeaderViewGridAdapter)) {
            ((HeaderViewGridAdapter) adapter).a(getNumColumnsCompat());
        }
    }

    private void a() {
        this.f = new SparseIntArray();
        this.n = new ArrayList<>();
        this.o = new ArrayList<>();
        super.setClipChildren(false);
        super.setOnScrollListener(this.q);
    }

    private int getNumColumnsCompat() {
        int measuredWidth;
        if (Build.VERSION.SDK_INT >= 11) {
            return getNumColumns();
        }
        int i2 = 0;
        if (getChildCount() > 0 && (measuredWidth = getChildAt(0).getMeasuredWidth()) > 0) {
            i2 = getWidth() / measuredWidth;
        }
        if (i2 > 0) {
            return i2;
        }
        return -1;
    }

    /* access modifiers changed from: private */
    public void b() {
        int i2;
        int i3;
        if (this.g != null && getChildCount() > 0) {
            int firstVisiblePosition = getFirstVisiblePosition();
            int firstVisiblePosition2 = getFirstVisiblePosition();
            int i4 = 0;
            while (firstVisiblePosition2 <= getLastVisiblePosition()) {
                if ((this.f.indexOfKey(firstVisiblePosition2) < 0 || getChildAt(i4).getHeight() != this.f.get(firstVisiblePosition2)) && firstVisiblePosition2 % getNumColumnsCompat() == 0) {
                    this.f.put(firstVisiblePosition2, getChildAt(i4).getHeight());
                }
                firstVisiblePosition2++;
                i4++;
            }
            View childAt = getChildAt(0);
            if (childAt != null) {
                if (this.f5313a < firstVisiblePosition) {
                    if (firstVisiblePosition - this.f5313a != 1) {
                        i3 = 0;
                        for (int i5 = firstVisiblePosition - 1; i5 > this.f5313a; i5--) {
                            if (this.f.indexOfKey(i5) > 0) {
                                i3 += this.f.get(i5);
                            }
                        }
                    } else {
                        i3 = 0;
                    }
                    this.c += this.b + i3;
                    this.b = childAt.getHeight();
                } else if (firstVisiblePosition < this.f5313a) {
                    if (this.f5313a - firstVisiblePosition != 1) {
                        i2 = 0;
                        for (int i6 = this.f5313a - 1; i6 > firstVisiblePosition; i6--) {
                            if (this.f.indexOfKey(i6) > 0) {
                                i2 += this.f.get(i6);
                            }
                        }
                    } else {
                        i2 = 0;
                    }
                    this.c -= childAt.getHeight() + i2;
                    this.b = childAt.getHeight();
                } else if (firstVisiblePosition == 0) {
                    this.b = childAt.getHeight();
                }
                if (this.b < 0) {
                    this.b = 0;
                }
                this.e = this.c - childAt.getTop();
                this.f5313a = firstVisiblePosition;
                this.g.onScrollChanged(this.e, this.i, this.j);
                if (this.i) {
                    this.i = false;
                }
                if (this.d < this.e) {
                    this.h = ScrollState.UP;
                } else if (this.e < this.d) {
                    this.h = ScrollState.DOWN;
                } else {
                    this.h = ScrollState.STOP;
                }
                this.d = this.e;
            }
        }
    }

    private void a(View view, ArrayList<FixedViewInfo> arrayList) {
        int size = arrayList.size();
        for (int i2 = 0; i2 < size; i2++) {
            if (arrayList.get(i2).f5316a == view) {
                arrayList.remove(i2);
                return;
            }
        }
    }

    private class FullWidthFixedViewLayout extends FrameLayout {
        public FullWidthFixedViewLayout(Context context) {
            super(context);
        }

        /* access modifiers changed from: protected */
        public void onMeasure(int i, int i2) {
            super.onMeasure(View.MeasureSpec.makeMeasureSpec((ObservableGridView.this.getMeasuredWidth() - ObservableGridView.this.getPaddingLeft()) - ObservableGridView.this.getPaddingRight(), View.MeasureSpec.getMode(i)), i2);
        }
    }

    static class SavedState extends View.BaseSavedState {
        public static final Parcelable.Creator<SavedState> CREATOR = new Parcelable.Creator<SavedState>() {
            /* renamed from: a */
            public SavedState createFromParcel(Parcel parcel) {
                return new SavedState(parcel);
            }

            /* renamed from: a */
            public SavedState[] newArray(int i) {
                return new SavedState[i];
            }
        };

        /* renamed from: a  reason: collision with root package name */
        int f5319a;
        int b;
        int c;
        int d;
        int e;
        SparseIntArray f;

        SavedState(Parcelable parcelable) {
            super(parcelable);
            this.b = -1;
        }

        private SavedState(Parcel parcel) {
            super(parcel);
            this.b = -1;
            this.f5319a = parcel.readInt();
            this.b = parcel.readInt();
            this.c = parcel.readInt();
            this.d = parcel.readInt();
            this.e = parcel.readInt();
            this.f = new SparseIntArray();
            int readInt = parcel.readInt();
            if (readInt > 0) {
                for (int i = 0; i < readInt; i++) {
                    this.f.put(parcel.readInt(), parcel.readInt());
                }
            }
        }

        public void writeToParcel(Parcel parcel, int i) {
            super.writeToParcel(parcel, i);
            parcel.writeInt(this.f5319a);
            parcel.writeInt(this.b);
            parcel.writeInt(this.c);
            parcel.writeInt(this.d);
            parcel.writeInt(this.e);
            int size = this.f == null ? 0 : this.f.size();
            parcel.writeInt(size);
            if (size > 0) {
                for (int i2 = 0; i2 < size; i2++) {
                    parcel.writeInt(this.f.keyAt(i2));
                    parcel.writeInt(this.f.valueAt(i2));
                }
            }
        }
    }

    public static class HeaderViewGridAdapter implements Filterable, WrapperListAdapter {

        /* renamed from: a  reason: collision with root package name */
        static final ArrayList<FixedViewInfo> f5318a = new ArrayList<>();
        ArrayList<FixedViewInfo> b;
        ArrayList<FixedViewInfo> c;
        boolean d;
        private final DataSetObservable e = new DataSetObservable();
        private final ListAdapter f;
        private int g;
        private int h;
        private final boolean i;
        private boolean j;
        private boolean k;

        public HeaderViewGridAdapter(ArrayList<FixedViewInfo> arrayList, ArrayList<FixedViewInfo> arrayList2, ListAdapter listAdapter) {
            boolean z = true;
            this.g = 1;
            this.h = -1;
            this.j = true;
            this.k = false;
            this.f = listAdapter;
            this.i = listAdapter instanceof Filterable;
            if (arrayList == null) {
                this.b = f5318a;
            } else {
                this.b = arrayList;
            }
            if (arrayList2 == null) {
                this.c = f5318a;
            } else {
                this.c = arrayList2;
            }
            this.d = (!a(this.b) || !a(this.c)) ? false : z;
        }

        public void a(int i2) {
            if (i2 >= 1 && this.g != i2) {
                this.g = i2;
                c();
            }
        }

        public void b(int i2) {
            this.h = i2;
        }

        public int a() {
            return this.b.size();
        }

        public int b() {
            return this.c.size();
        }

        public boolean isEmpty() {
            return this.f == null || this.f.isEmpty();
        }

        private boolean a(ArrayList<FixedViewInfo> arrayList) {
            if (arrayList == null) {
                return true;
            }
            Iterator<FixedViewInfo> it = arrayList.iterator();
            while (it.hasNext()) {
                if (!it.next().d) {
                    return false;
                }
            }
            return true;
        }

        public boolean a(View view) {
            boolean z = false;
            for (int i2 = 0; i2 < this.b.size(); i2++) {
                if (this.b.get(i2).f5316a == view) {
                    this.b.remove(i2);
                    if (a(this.b) && a(this.c)) {
                        z = true;
                    }
                    this.d = z;
                    this.e.notifyChanged();
                    return true;
                }
            }
            return false;
        }

        public boolean b(View view) {
            boolean z = false;
            for (int i2 = 0; i2 < this.c.size(); i2++) {
                if (this.c.get(i2).f5316a == view) {
                    this.c.remove(i2);
                    if (a(this.b) && a(this.c)) {
                        z = true;
                    }
                    this.d = z;
                    this.e.notifyChanged();
                    return true;
                }
            }
            return false;
        }

        public int getCount() {
            if (this.f != null) {
                return ((b() + a()) * this.g) + d();
            }
            return (b() + a()) * this.g;
        }

        public boolean areAllItemsEnabled() {
            return this.f == null || (this.d && this.f.areAllItemsEnabled());
        }

        private int d() {
            double ceil = Math.ceil((double) ((((float) this.f.getCount()) * 1.0f) / ((float) this.g)));
            double d2 = (double) this.g;
            Double.isNaN(d2);
            return (int) (ceil * d2);
        }

        public boolean isEnabled(int i2) {
            int i3;
            int a2 = a() * this.g;
            if (i2 >= a2) {
                int i4 = i2 - a2;
                if (this.f != null) {
                    i3 = d();
                    if (i4 < i3) {
                        if (i4 >= this.f.getCount() || !this.f.isEnabled(i4)) {
                            return false;
                        }
                        return true;
                    }
                } else {
                    i3 = 0;
                }
                int i5 = i4 - i3;
                if (i5 % this.g != 0 || !this.c.get(i5 / this.g).d) {
                    return false;
                }
                return true;
            } else if (i2 % this.g != 0 || !this.b.get(i2 / this.g).d) {
                return false;
            } else {
                return true;
            }
        }

        public Object getItem(int i2) {
            int a2 = a() * this.g;
            if (i2 >= a2) {
                int i3 = i2 - a2;
                int i4 = 0;
                if (this.f == null || i3 >= (i4 = d())) {
                    int i5 = i3 - i4;
                    if (i5 % this.g == 0) {
                        return this.c.get(i5).c;
                    }
                    return null;
                } else if (i3 < this.f.getCount()) {
                    return this.f.getItem(i3);
                } else {
                    return null;
                }
            } else if (i2 % this.g == 0) {
                return this.b.get(i2 / this.g).c;
            } else {
                return null;
            }
        }

        public long getItemId(int i2) {
            int i3;
            int a2 = a() * this.g;
            if (this.f == null || i2 < a2 || (i3 = i2 - a2) >= this.f.getCount()) {
                return -1;
            }
            return this.f.getItemId(i3);
        }

        public boolean hasStableIds() {
            return this.f != null && this.f.hasStableIds();
        }

        public View getView(int i2, View view, ViewGroup viewGroup) {
            int a2 = a() * this.g;
            if (i2 < a2) {
                ViewGroup viewGroup2 = this.b.get(i2 / this.g).b;
                if (i2 % this.g == 0) {
                    return viewGroup2;
                }
                if (view == null) {
                    view = new View(viewGroup.getContext());
                }
                view.setVisibility(4);
                view.setMinimumHeight(viewGroup2.getHeight());
                return view;
            }
            int i3 = i2 - a2;
            int i4 = 0;
            if (this.f == null || i3 >= (i4 = d())) {
                int i5 = i3 - i4;
                if (i5 < getCount()) {
                    ViewGroup viewGroup3 = this.c.get(i5 / this.g).b;
                    if (i2 % this.g == 0) {
                        return viewGroup3;
                    }
                    if (view == null) {
                        view = new View(viewGroup.getContext());
                    }
                    view.setVisibility(4);
                    view.setMinimumHeight(viewGroup3.getHeight());
                    return view;
                }
                throw new ArrayIndexOutOfBoundsException(i2);
            } else if (i3 < this.f.getCount()) {
                return this.f.getView(i3, view, viewGroup);
            } else {
                if (view == null) {
                    view = new View(viewGroup.getContext());
                }
                view.setVisibility(4);
                view.setMinimumHeight(this.h);
                return view;
            }
        }

        public int getItemViewType(int i2) {
            int i3;
            int a2 = a() * this.g;
            int i4 = 0;
            int viewTypeCount = this.f == null ? 0 : this.f.getViewTypeCount() - 1;
            int i5 = -2;
            if (this.j && i2 < a2) {
                if (i2 == 0 && this.k) {
                    i5 = this.b.size() + viewTypeCount + this.c.size() + 1 + 1;
                }
                if (i2 % this.g != 0) {
                    i5 = (i2 / this.g) + 1 + viewTypeCount;
                }
            }
            int i6 = i2 - a2;
            if (this.f != null) {
                i4 = d();
                if (i6 >= 0 && i6 < i4) {
                    if (i6 < this.f.getCount()) {
                        i5 = this.f.getItemViewType(i6);
                    } else if (this.j) {
                        i5 = this.b.size() + viewTypeCount + 1;
                    }
                }
            }
            return (!this.j || (i3 = i6 - i4) < 0 || i3 >= getCount() || i3 % this.g == 0) ? i5 : viewTypeCount + this.b.size() + 1 + (i3 / this.g) + 1;
        }

        public int getViewTypeCount() {
            int viewTypeCount = this.f == null ? 1 : this.f.getViewTypeCount();
            if (!this.j) {
                return viewTypeCount;
            }
            int size = this.b.size() + 1 + this.c.size();
            if (this.k) {
                size++;
            }
            return viewTypeCount + size;
        }

        public void registerDataSetObserver(DataSetObserver dataSetObserver) {
            this.e.registerObserver(dataSetObserver);
            if (this.f != null) {
                this.f.registerDataSetObserver(dataSetObserver);
            }
        }

        public void unregisterDataSetObserver(DataSetObserver dataSetObserver) {
            this.e.unregisterObserver(dataSetObserver);
            if (this.f != null) {
                this.f.unregisterDataSetObserver(dataSetObserver);
            }
        }

        public Filter getFilter() {
            if (this.i) {
                return ((Filterable) this.f).getFilter();
            }
            return null;
        }

        public ListAdapter getWrappedAdapter() {
            return this.f;
        }

        public void c() {
            this.e.notifyChanged();
        }
    }
}
