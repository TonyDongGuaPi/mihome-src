package com.chad.library.adapter.base.util;

import android.support.annotation.LayoutRes;
import android.util.SparseIntArray;
import com.chad.library.adapter.base.BaseMultiItemQuickAdapter;
import java.util.List;

public abstract class MultiTypeDelegate<T> {

    /* renamed from: a  reason: collision with root package name */
    private static final int f5144a = -255;
    private SparseIntArray b;
    private boolean c;
    private boolean d;

    /* access modifiers changed from: protected */
    public abstract int a(T t);

    public MultiTypeDelegate(SparseIntArray sparseIntArray) {
        this.b = sparseIntArray;
    }

    public MultiTypeDelegate() {
    }

    public final int a(List<T> list, int i) {
        T t = list.get(i);
        return t != null ? a(t) : f5144a;
    }

    public final int a(int i) {
        return this.b.get(i, BaseMultiItemQuickAdapter.f5118a);
    }

    private void b(int i, @LayoutRes int i2) {
        if (this.b == null) {
            this.b = new SparseIntArray();
        }
        this.b.put(i, i2);
    }

    public MultiTypeDelegate a(@LayoutRes int... iArr) {
        this.c = true;
        a(this.d);
        for (int i = 0; i < iArr.length; i++) {
            b(i, iArr[i]);
        }
        return this;
    }

    public MultiTypeDelegate a(int i, @LayoutRes int i2) {
        this.d = true;
        a(this.c);
        b(i, i2);
        return this;
    }

    private void a(boolean z) {
        if (z) {
            throw new RuntimeException("Don't mess two register mode");
        }
    }
}
