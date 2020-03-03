package com.chad.library.adapter.base.loadmore;

import android.support.annotation.IdRes;
import android.support.annotation.LayoutRes;
import com.chad.library.adapter.base.BaseViewHolder;

public abstract class LoadMoreView {

    /* renamed from: a  reason: collision with root package name */
    public static final int f5142a = 1;
    public static final int b = 2;
    public static final int c = 3;
    public static final int d = 4;
    private int e = 1;
    private boolean f = false;

    @LayoutRes
    public abstract int d();

    /* access modifiers changed from: protected */
    @IdRes
    public abstract int e();

    /* access modifiers changed from: protected */
    @IdRes
    public abstract int f();

    /* access modifiers changed from: protected */
    @IdRes
    public abstract int g();

    public void a(int i) {
        this.e = i;
    }

    public int a() {
        return this.e;
    }

    public void a(BaseViewHolder baseViewHolder) {
        switch (this.e) {
            case 1:
                a(baseViewHolder, false);
                b(baseViewHolder, false);
                c(baseViewHolder, false);
                return;
            case 2:
                a(baseViewHolder, true);
                b(baseViewHolder, false);
                c(baseViewHolder, false);
                return;
            case 3:
                a(baseViewHolder, false);
                b(baseViewHolder, true);
                c(baseViewHolder, false);
                return;
            case 4:
                a(baseViewHolder, false);
                b(baseViewHolder, false);
                c(baseViewHolder, true);
                return;
            default:
                return;
        }
    }

    private void a(BaseViewHolder baseViewHolder, boolean z) {
        baseViewHolder.a(e(), z);
    }

    private void b(BaseViewHolder baseViewHolder, boolean z) {
        baseViewHolder.a(f(), z);
    }

    private void c(BaseViewHolder baseViewHolder, boolean z) {
        int g = g();
        if (g != 0) {
            baseViewHolder.a(g, z);
        }
    }

    public final void a(boolean z) {
        this.f = z;
    }

    public final boolean b() {
        if (g() == 0) {
            return true;
        }
        return this.f;
    }

    @Deprecated
    public boolean c() {
        return this.f;
    }
}
