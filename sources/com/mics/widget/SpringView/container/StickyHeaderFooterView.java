package com.mics.widget.SpringView.container;

import android.support.annotation.Px;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.mics.R;

public class StickyHeaderFooterView extends BaseHeader {

    /* renamed from: a  reason: collision with root package name */
    public static final int f7819a = 1;
    public static final int b = 2;
    private OnReachLimitListener c;
    private int d;
    private int e;

    public void a(View view, int i) {
    }

    public void a(View view, boolean z) {
    }

    public void b() {
    }

    public int c(View view) {
        return 1;
    }

    public void d(View view) {
    }

    public StickyHeaderFooterView(int i) {
        this.e = i;
    }

    public View a(LayoutInflater layoutInflater, ViewGroup viewGroup) {
        View view;
        if (this.e == 1) {
            view = layoutInflater.inflate(R.layout.mics_sticky_paging_header, viewGroup, true);
        } else if (this.e == 2) {
            view = layoutInflater.inflate(R.layout.mics_sticky_paging_footer, viewGroup, true);
        } else {
            view = layoutInflater.inflate(this.e, viewGroup, true);
        }
        this.d = (int) TypedValue.applyDimension(1, 14.0f, view.getResources().getDisplayMetrics());
        return view;
    }

    public void a(OnReachLimitListener onReachLimitListener) {
        this.c = onReachLimitListener;
    }

    public void a(@Px int i) {
        this.d = i;
    }

    public void a() {
        if (this.c != null) {
            this.c.a(this);
        }
    }

    public int a(View view) {
        return this.d;
    }
}
