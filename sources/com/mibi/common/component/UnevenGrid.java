package com.mibi.common.component;

import android.content.Context;
import android.content.res.Configuration;
import android.content.res.TypedArray;
import android.database.DataSetObserver;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import com.mibi.common.R;
import com.mibi.common.data.ArrayAdapter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.TreeSet;

public class UnevenGrid extends ViewGroup {

    /* renamed from: a  reason: collision with root package name */
    private int f7492a;
    private int b;
    private int c;
    private int d;
    private int e;
    private int f;
    private TreeSet<Integer> g;
    private int h;
    private int i;
    private List<? extends GridItemData> j;
    private RecycleBin k = new RecycleBin();
    private ArrayAdapter<? extends GridItemData> l;
    private AdapterDataSetObserver m;
    private HashMap<View, Integer> n;

    public UnevenGrid(Context context) {
        super(context);
        a();
    }

    public UnevenGrid(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        a(context, attributeSet);
        a();
    }

    public UnevenGrid(Context context, AttributeSet attributeSet, int i2) {
        super(context, attributeSet, i2);
        a(context, attributeSet);
        a();
    }

    private void a(Context context, AttributeSet attributeSet) {
        TypedArray obtainStyledAttributes = context.getTheme().obtainStyledAttributes(attributeSet, R.styleable.Mibi_UnevenGrid, 0, 0);
        try {
            this.f7492a = 0;
            this.b = obtainStyledAttributes.getInt(R.styleable.Mibi_UnevenGrid_android_numColumns, 2);
            this.c = (int) obtainStyledAttributes.getDimension(R.styleable.Mibi_UnevenGrid_android_horizontalSpacing, 0.0f);
            this.d = (int) obtainStyledAttributes.getDimension(R.styleable.Mibi_UnevenGrid_android_verticalSpacing, 0.0f);
        } finally {
            obtainStyledAttributes.recycle();
        }
    }

    private void a() {
        this.i = getContext().getResources().getConfiguration().orientation;
        this.n = new HashMap<>();
        this.g = new TreeSet<>();
        this.h = 0;
        this.e = 0;
        this.f = 0;
    }

    public void setHorizontalSpacing(int i2) {
        this.c = i2;
    }

    public void setGridItemGap(int i2) {
        if (i2 >= 0) {
            this.c = i2;
            this.d = i2;
        }
    }

    public void setNumRowsAndColumns(int i2, int i3) {
        if (i2 > 0) {
            this.f7492a = i2;
        }
        if (i3 > 0) {
            this.b = i3;
        }
        requestLayout();
    }

    public void setNumColumns(int i2) {
        if (i2 > 0) {
            this.b = i2;
        }
        requestLayout();
    }

    public void setNumRows(int i2) {
        if (i2 > 0) {
            this.f7492a = i2;
        }
        requestLayout();
    }

    public void setGridWidth(int i2) {
        if (this.e != i2) {
            this.e = i2;
            requestLayout();
        }
    }

    public void setGridHeight(int i2) {
        if (this.f != i2) {
            this.f = i2;
            requestLayout();
        }
    }

    public void setAdapter(ArrayAdapter<? extends GridItemData> arrayAdapter) {
        if (!(this.l == null || this.m == null)) {
            this.l.unregisterDataSetObserver(this.m);
        }
        this.k.a();
        this.l = arrayAdapter;
        if (this.l != null) {
            this.m = new AdapterDataSetObserver();
            this.l.registerDataSetObserver(this.m);
            this.k.a(this.l.getViewTypeCount());
        }
        b();
    }

    /* access modifiers changed from: private */
    public void b() {
        for (int i2 = 0; i2 < getChildCount(); i2++) {
            this.k.a(getChildAt(i2));
        }
        detachAllViewsFromParent();
        this.n.clear();
        this.g.clear();
        this.h = 0;
        int count = this.l.getCount();
        for (int i3 = 0; i3 < count; i3++) {
            GridItemData gridItemData = (GridItemData) this.l.getItem(i3);
            View view = this.l.getView(i3, this.k.b(this.l.getItemViewType(i3)), this);
            if (view != null && !a(view, gridItemData.f7494a, gridItemData.b, this.l.getItemViewType(i3))) {
                break;
            }
        }
        this.k.a();
    }

    private boolean a(View view, int i2, int i3, int i4) {
        if (i2 <= 0) {
            i2 = 1;
        }
        if (i3 <= 0) {
            i3 = 1;
        }
        if (i2 >= this.b) {
            i2 = this.b;
        }
        int a2 = a(i2, i3);
        if (a2 < 0) {
            return false;
        }
        this.n.put(view, Integer.valueOf(a2));
        view.setLayoutParams(LayoutParams.a(view.getLayoutParams(), i2, i3, i4));
        addView(view);
        return true;
    }

    private int a(int i2, int i3) {
        Iterator<Integer> it = this.g.iterator();
        while (it.hasNext()) {
            Integer next = it.next();
            if (a(next.intValue(), i2, i3)) {
                b(next.intValue(), i2, i3);
                return next.intValue();
            }
        }
        int i4 = this.h * this.b;
        if (!b(i4, i3)) {
            return -1;
        }
        b(i4, i2, i3);
        return i4;
    }

    private boolean b(int i2, int i3) {
        if (this.f7492a > 0 && this.f7492a - (i2 / this.b) < i3) {
            return false;
        }
        return true;
    }

    private boolean a(int i2, int i3, int i4) {
        if (this.b - (i2 % this.b) < i3) {
            return false;
        }
        for (int i5 = 0; i5 < i4; i5++) {
            for (int i6 = 0; i6 < i3; i6++) {
                int i7 = (this.b * i5) + i2 + i6;
                if (i7 / this.b >= this.h) {
                    return true;
                }
                if (!this.g.contains(Integer.valueOf(i7))) {
                    return false;
                }
            }
        }
        return true;
    }

    private void b(int i2, int i3, int i4) {
        int i5 = ((i2 / this.b) + i4) - 1;
        int i6 = this.h;
        while (true) {
            if (i6 > i5) {
                break;
            }
            for (int i7 = 0; i7 < this.b; i7++) {
                this.g.add(Integer.valueOf((this.b * i6) + i7));
            }
            i6++;
        }
        if (this.h <= i5) {
            this.h = i5 + 1;
        }
        for (int i8 = 0; i8 < i4; i8++) {
            for (int i9 = 0; i9 < i3; i9++) {
                this.g.remove(Integer.valueOf((this.b * i8) + i2 + i9));
            }
        }
    }

    private int a(int i2) {
        return (i2 % this.b) * (this.e + this.c);
    }

    private int b(int i2) {
        return (i2 / this.b) * (this.f + this.d);
    }

    private int c(int i2, int i3) {
        return a(i2) + i3;
    }

    private int d(int i2, int i3) {
        return b(i2) + i3;
    }

    /* access modifiers changed from: protected */
    public void onMeasure(int i2, int i3) {
        int childCount = getChildCount();
        if (childCount == 0) {
            super.onMeasure(i2, i3);
            return;
        }
        super.onMeasure(i2, i3);
        int size = View.MeasureSpec.getSize(i2);
        if (View.MeasureSpec.getMode(i2) != 1073741824) {
            size = getPaddingRight() + getPaddingLeft();
            if (this.e > 0) {
                size += (this.e * this.b) + ((this.b - 1) * this.c);
            }
        } else if (size > 0) {
            this.e = (((size - getPaddingLeft()) - getPaddingRight()) - ((this.b - 1) * this.c)) / this.b;
        } else {
            this.e = 0;
        }
        if ((this.e == 0 || this.f == 0) && childCount > 0) {
            View childAt = getChildAt(0);
            ViewGroup.LayoutParams layoutParams = childAt.getLayoutParams();
            if (layoutParams == null) {
                layoutParams = new ViewGroup.LayoutParams(-1, -2);
                childAt.setLayoutParams(layoutParams);
            }
            childAt.measure(getChildMeasureSpec(View.MeasureSpec.makeMeasureSpec(this.e, 1073741824), 0, layoutParams.width), getChildMeasureSpec(View.MeasureSpec.makeMeasureSpec(0, 0), 0, layoutParams.height));
            this.e = childAt.getMeasuredWidth();
            this.f = childAt.getMeasuredHeight();
        }
        int i4 = 0;
        for (int i5 = 0; i5 < childCount; i5++) {
            View childAt2 = getChildAt(i5);
            LayoutParams layoutParams2 = (LayoutParams) childAt2.getLayoutParams();
            int i6 = layoutParams2.f7495a;
            int i7 = layoutParams2.b;
            int i8 = (this.e * i6) + ((i6 - 1) * this.c);
            int i9 = (this.f * i7) + ((i7 - 1) * this.d);
            int intValue = this.n.get(childAt2).intValue();
            childAt2.measure(View.MeasureSpec.makeMeasureSpec(i8, 1073741824), View.MeasureSpec.makeMeasureSpec(i9, 1073741824));
            i4 = Math.max(i4, d(intValue, i9));
        }
        setMeasuredDimension(size, i4 + getPaddingTop() + getPaddingBottom());
    }

    /* access modifiers changed from: protected */
    public void onLayout(boolean z, int i2, int i3, int i4, int i5) {
        for (int i6 = 0; i6 < getChildCount(); i6++) {
            View childAt = getChildAt(i6);
            int measuredWidth = childAt.getMeasuredWidth();
            int measuredHeight = childAt.getMeasuredHeight();
            int intValue = this.n.get(childAt).intValue();
            childAt.layout(getPaddingLeft() + a(intValue), getPaddingTop() + b(intValue), getPaddingLeft() + c(intValue, measuredWidth), getPaddingTop() + d(intValue, measuredHeight));
        }
    }

    public static class LayoutParams extends ViewGroup.MarginLayoutParams {

        /* renamed from: a  reason: collision with root package name */
        int f7495a;
        int b;
        int c;

        public LayoutParams() {
            super(-1, -1);
        }

        public LayoutParams(ViewGroup.LayoutParams layoutParams) {
            super(layoutParams);
        }

        public LayoutParams(Context context, AttributeSet attributeSet) {
            super(context, attributeSet);
        }

        public static LayoutParams a(ViewGroup.LayoutParams layoutParams, int i, int i2) {
            return a(layoutParams, i, i2, 0);
        }

        public static LayoutParams a(ViewGroup.LayoutParams layoutParams, int i, int i2, int i3) {
            LayoutParams layoutParams2 = new LayoutParams(layoutParams);
            layoutParams2.f7495a = i;
            layoutParams2.b = i2;
            layoutParams2.c = i3;
            return layoutParams2;
        }
    }

    public static class GridItemData {

        /* renamed from: a  reason: collision with root package name */
        public int f7494a;
        public int b;

        public boolean equals(Object obj) {
            if (obj == null || !(obj instanceof GridItemData)) {
                return false;
            }
            GridItemData gridItemData = (GridItemData) obj;
            if (this.f7494a == gridItemData.f7494a && this.b == gridItemData.b) {
                return true;
            }
            return false;
        }
    }

    class AdapterDataSetObserver extends DataSetObserver {
        AdapterDataSetObserver() {
        }

        public void onChanged() {
            super.onChanged();
            UnevenGrid.this.b();
        }

        public void onInvalidated() {
            super.onInvalidated();
            UnevenGrid.this.b();
        }
    }

    private static class RecycleBin {

        /* renamed from: a  reason: collision with root package name */
        private int f7496a;
        private ArrayList<View>[] b;

        private RecycleBin() {
            this.b = null;
        }

        public void a(int i) {
            if (i < 1) {
                i = 1;
            }
            if (this.f7496a != i) {
                this.f7496a = i;
                ArrayList<View>[] arrayListArr = new ArrayList[i];
                for (int i2 = 0; i2 < i; i2++) {
                    arrayListArr[i2] = new ArrayList<>();
                }
                this.b = arrayListArr;
            }
        }

        public void a(View view) {
            this.b[((LayoutParams) view.getLayoutParams()).c].add(view);
        }

        public View b(int i) {
            int size;
            if (i < this.f7496a && (size = this.b[i].size()) > 0) {
                return this.b[i].remove(size - 1);
            }
            return null;
        }

        public void a() {
            for (int i = 0; i < this.f7496a; i++) {
                this.b[i].clear();
            }
        }
    }

    public void onConfigurationChanged(Configuration configuration) {
        super.onConfigurationChanged(configuration);
        if (configuration.orientation != this.i) {
            b();
        }
        this.i = configuration.orientation;
    }
}
