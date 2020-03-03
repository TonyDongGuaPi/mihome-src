package com.xiaomi.smarthome.library.common.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.database.DataSetObserver;
import android.util.AttributeSet;
import android.view.View;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TableLayout;
import android.widget.TableRow;
import com.xiaomi.smarthome.R;

public class GridTableLayout extends TableLayout {

    /* renamed from: a  reason: collision with root package name */
    private BaseAdapter f18843a;
    private int b;
    private int c;
    private int d;
    /* access modifiers changed from: private */
    public boolean e = false;
    private boolean f = false;
    private int g = -1;
    DataSetObserver mDataSetObserver;

    public GridTableLayout(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R.styleable.GridTableLayout);
        this.b = obtainStyledAttributes.getInt(1, 1);
        this.c = (int) obtainStyledAttributes.getDimension(2, 0.0f);
        this.d = (int) obtainStyledAttributes.getDimension(0, 0.0f);
        this.f = obtainStyledAttributes.getBoolean(3, false);
        obtainStyledAttributes.recycle();
        if (context.getResources().getDisplayMetrics().density < 1.8f) {
            this.f = true;
        }
        this.mDataSetObserver = new DataSetObserver() {
            public void onChanged() {
                super.onChanged();
                boolean unused = GridTableLayout.this.e = true;
                GridTableLayout.this.a();
            }
        };
    }

    public int getNumColumn() {
        return this.b;
    }

    public void setNumColumn(int i) {
        this.b = i;
    }

    public float getVerticalSpacing() {
        return (float) this.c;
    }

    public void setVerticalSpacing(int i) {
        this.c = i;
    }

    public float getHorizontalSpacing() {
        return (float) this.d;
    }

    public void setHorizontalSpacing(int i) {
        this.d = i;
    }

    public GridTableLayout(Context context) {
        super(context);
    }

    public void setAdapter(BaseAdapter baseAdapter) {
        baseAdapter.registerDataSetObserver(this.mDataSetObserver);
        this.f18843a = baseAdapter;
        a();
    }

    /* access modifiers changed from: private */
    public void a() {
        int count = this.f18843a.getCount();
        if (count < this.g) {
            removeAllViews();
        }
        this.g = count;
        Context context = getContext();
        for (int i = 0; i < count; i++) {
            int i2 = i % this.b;
            int i3 = i / this.b;
            int childCount = getChildCount();
            if ((i2 == 0 && !this.e) || (i2 == 0 && this.e && i3 >= childCount)) {
                TableRow tableRow = new TableRow(context);
                for (int i4 = 0; i4 < this.b; i4++) {
                    LinearLayout linearLayout = new LinearLayout(context);
                    if (this.f) {
                        TableRow.LayoutParams layoutParams = new TableRow.LayoutParams(0, -2);
                        layoutParams.weight = 1.0f;
                        linearLayout.setGravity(1);
                        linearLayout.setLayoutParams(layoutParams);
                    } else {
                        TableRow.LayoutParams layoutParams2 = new TableRow.LayoutParams(-2, -2);
                        if (i2 != this.b - 1) {
                            layoutParams2.setMargins(0, 0, this.d, 0);
                        }
                        linearLayout.setGravity(1);
                        linearLayout.setLayoutParams(layoutParams2);
                    }
                    tableRow.addView(linearLayout);
                }
                tableRow.setGravity(16);
                TableLayout.LayoutParams layoutParams3 = new TableLayout.LayoutParams(-1, -2);
                layoutParams3.setMargins(0, 0, 0, this.c);
                tableRow.setLayoutParams(layoutParams3);
                addView(tableRow);
            }
            LinearLayout linearLayout2 = (LinearLayout) ((TableRow) getChildAt(i3)).getChildAt(i2);
            View childAt = linearLayout2.getChildAt(0);
            View view = this.f18843a.getView(i, childAt, linearLayout2);
            if (view != null && (!this.e || (this.e && childAt == null))) {
                linearLayout2.addView(view);
            }
        }
        this.e = false;
        invalidate();
    }

    /* access modifiers changed from: protected */
    public void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        if (this.f18843a != null) {
            this.f18843a.unregisterDataSetObserver(this.mDataSetObserver);
            this.mDataSetObserver = null;
        }
    }
}
