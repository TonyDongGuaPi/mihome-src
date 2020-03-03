package com.scwang.smartrefresh.layout.impl;

import android.graphics.PointF;
import android.view.View;
import com.scwang.smartrefresh.layout.api.ScrollBoundaryDecider;
import com.scwang.smartrefresh.layout.util.ScrollBoundaryUtil;

public class ScrollBoundaryDeciderAdapter implements ScrollBoundaryDecider {

    /* renamed from: a  reason: collision with root package name */
    public PointF f8797a;
    public ScrollBoundaryDecider b;
    public boolean c = true;

    public boolean a(View view) {
        if (this.b != null) {
            return this.b.a(view);
        }
        return ScrollBoundaryUtil.a(view, this.f8797a);
    }

    public boolean b(View view) {
        if (this.b != null) {
            return this.b.b(view);
        }
        return ScrollBoundaryUtil.a(view, this.f8797a, this.c);
    }
}
