package com.xiaomi.smarthome.library.common.widget;

import android.content.Context;
import android.database.DataSetObservable;
import android.database.DataSetObserver;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.FrameLayout;
import android.widget.GridView;
import android.widget.ListAdapter;
import android.widget.TextView;
import android.widget.WrapperListAdapter;
import java.util.ArrayList;
import java.util.Iterator;

public class HeaderGridView extends GridView {

    /* renamed from: a  reason: collision with root package name */
    private static final String f18849a = "HeaderGridView";
    private ArrayList<FixedViewInfo> b = new ArrayList<>();

    public void setClipChildren(boolean z) {
    }

    private static class FixedViewInfo {

        /* renamed from: a  reason: collision with root package name */
        public View f18850a;
        public ViewGroup b;
        public Object c;
        public boolean d;

        private FixedViewInfo() {
        }
    }

    private void a() {
        super.setClipChildren(false);
    }

    public HeaderGridView(Context context) {
        super(context);
        a();
    }

    public HeaderGridView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        a();
    }

    public HeaderGridView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        a();
    }

    /* access modifiers changed from: protected */
    public void onMeasure(int i, int i2) {
        super.onMeasure(i, i2);
        ListAdapter adapter = getAdapter();
        if (adapter != null && (adapter instanceof HeaderViewGridAdapter)) {
            ((HeaderViewGridAdapter) adapter).a(getNumColumns());
        }
    }

    public void addHeaderView(View view, Object obj, boolean z) {
        ListAdapter adapter = getAdapter();
        if (adapter == null || (adapter instanceof HeaderViewGridAdapter)) {
            FixedViewInfo fixedViewInfo = new FixedViewInfo();
            FullWidthFixedViewLayout fullWidthFixedViewLayout = new FullWidthFixedViewLayout(getContext());
            fullWidthFixedViewLayout.addView(view);
            fixedViewInfo.f18850a = view;
            fixedViewInfo.b = fullWidthFixedViewLayout;
            fixedViewInfo.c = obj;
            fixedViewInfo.d = z;
            this.b.add(fixedViewInfo);
            if (adapter != null) {
                ((HeaderViewGridAdapter) adapter).b();
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
        return this.b.size();
    }

    public boolean removeHeaderView(View view) {
        boolean z = false;
        if (this.b.size() <= 0) {
            return false;
        }
        ListAdapter adapter = getAdapter();
        if (adapter != null && ((HeaderViewGridAdapter) adapter).a(view)) {
            z = true;
        }
        a(view, this.b);
        return z;
    }

    private void a(View view, ArrayList<FixedViewInfo> arrayList) {
        int size = arrayList.size();
        for (int i = 0; i < size; i++) {
            if (arrayList.get(i).f18850a == view) {
                arrayList.remove(i);
                return;
            }
        }
    }

    public void setAdapter(ListAdapter listAdapter) {
        if (this.b.size() > 0) {
            HeaderViewGridAdapter headerViewGridAdapter = new HeaderViewGridAdapter(this.b, listAdapter);
            int numColumns = getNumColumns();
            if (numColumns > 1) {
                headerViewGridAdapter.a(numColumns);
            }
            super.setAdapter(headerViewGridAdapter);
            return;
        }
        super.setAdapter(listAdapter);
    }

    private class FullWidthFixedViewLayout extends FrameLayout {
        public FullWidthFixedViewLayout(Context context) {
            super(context);
        }

        /* access modifiers changed from: protected */
        public void onMeasure(int i, int i2) {
            super.onMeasure(View.MeasureSpec.makeMeasureSpec((HeaderGridView.this.getMeasuredWidth() - HeaderGridView.this.getPaddingLeft()) - HeaderGridView.this.getPaddingRight(), View.MeasureSpec.getMode(i)), i2);
        }
    }

    private static class HeaderViewGridAdapter implements Filterable, WrapperListAdapter {

        /* renamed from: a  reason: collision with root package name */
        ArrayList<FixedViewInfo> f18852a;
        boolean b;
        private final DataSetObservable c = new DataSetObservable();
        private final ListAdapter d;
        private int e = 1;
        private final boolean f;

        public HeaderViewGridAdapter(ArrayList<FixedViewInfo> arrayList, ListAdapter listAdapter) {
            this.d = listAdapter;
            this.f = listAdapter instanceof Filterable;
            if (arrayList != null) {
                this.f18852a = arrayList;
                this.b = a(this.f18852a);
                return;
            }
            throw new IllegalArgumentException("headerViewInfos cannot be null");
        }

        public int a() {
            return this.f18852a.size();
        }

        public boolean isEmpty() {
            return (this.d == null || this.d.isEmpty()) && a() == 0;
        }

        public void a(int i) {
            if (i < 1) {
                throw new IllegalArgumentException("Number of columns must be 1 or more");
            } else if (this.e != i) {
                this.e = i;
                b();
            }
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
            for (int i = 0; i < this.f18852a.size(); i++) {
                if (this.f18852a.get(i).f18850a == view) {
                    this.f18852a.remove(i);
                    this.b = a(this.f18852a);
                    this.c.notifyChanged();
                    return true;
                }
            }
            return false;
        }

        public int getCount() {
            if (this.d != null) {
                return (a() * this.e) + this.d.getCount();
            }
            return a() * this.e;
        }

        public boolean areAllItemsEnabled() {
            if (this.d == null) {
                return true;
            }
            if (!this.b || !this.d.areAllItemsEnabled()) {
                return false;
            }
            return true;
        }

        public boolean isEnabled(int i) {
            int a2 = a() * this.e;
            if (i >= a2) {
                int i2 = i - a2;
                if (this.d == null || i2 >= this.d.getCount()) {
                    return false;
                }
                return this.d.isEnabled(i2);
            } else if (i % this.e != 0 || !this.f18852a.get(i / this.e).d) {
                return false;
            } else {
                return true;
            }
        }

        public Object getItem(int i) {
            int a2 = a() * this.e;
            if (i >= a2) {
                int i2 = i - a2;
                if (this.d != null && i2 < this.d.getCount()) {
                    return this.d.getItem(i2);
                }
                throw new ArrayIndexOutOfBoundsException(i);
            } else if (i % this.e == 0) {
                return this.f18852a.get(i / this.e).c;
            } else {
                return null;
            }
        }

        public long getItemId(int i) {
            int i2;
            int a2 = a() * this.e;
            if (this.d == null || i < a2 || (i2 = i - a2) >= this.d.getCount()) {
                return -1;
            }
            return this.d.getItemId(i2);
        }

        public boolean hasStableIds() {
            if (this.d != null) {
                return this.d.hasStableIds();
            }
            return false;
        }

        public View getView(int i, View view, ViewGroup viewGroup) {
            int a2 = a() * this.e;
            if (i < a2) {
                ViewGroup viewGroup2 = this.f18852a.get(i / this.e).b;
                if (i % this.e == 0) {
                    return viewGroup2;
                }
                if (view == null) {
                    view = new View(viewGroup.getContext());
                }
                view.setVisibility(4);
                view.setMinimumHeight(viewGroup2.getHeight());
                return view;
            }
            int i2 = i - a2;
            if (this.d == null || i2 >= this.d.getCount()) {
                return new TextView(viewGroup.getContext());
            }
            return this.d.getView(i2, view, viewGroup);
        }

        public int getItemViewType(int i) {
            int i2;
            int a2 = a() * this.e;
            if (i >= a2 || i % this.e == 0) {
                if (this.d == null || i < a2 || (i2 = i - a2) >= this.d.getCount()) {
                    return -2;
                }
                return this.d.getItemViewType(i2);
            } else if (this.d != null) {
                return this.d.getViewTypeCount();
            } else {
                return 1;
            }
        }

        public int getViewTypeCount() {
            if (this.d != null) {
                return this.d.getViewTypeCount() + 1;
            }
            return 2;
        }

        public void registerDataSetObserver(DataSetObserver dataSetObserver) {
            this.c.registerObserver(dataSetObserver);
            if (this.d != null) {
                this.d.registerDataSetObserver(dataSetObserver);
            }
        }

        public void unregisterDataSetObserver(DataSetObserver dataSetObserver) {
            this.c.unregisterObserver(dataSetObserver);
            if (this.d != null) {
                this.d.unregisterDataSetObserver(dataSetObserver);
            }
        }

        public Filter getFilter() {
            if (this.f) {
                return ((Filterable) this.d).getFilter();
            }
            return null;
        }

        public ListAdapter getWrappedAdapter() {
            return this.d;
        }

        public void b() {
            this.c.notifyChanged();
        }
    }
}
