package com.xiaomi.smarthome.library.common.widget;

import android.os.Parcelable;
import android.view.View;

public abstract class PagerAdapter {
    public static final int b = -1;
    public static final int c = -2;

    /* renamed from: a  reason: collision with root package name */
    private DataSetObserver f18911a;

    public interface DataSetObserver {
        void a();
    }

    public abstract int a();

    public int a(Object obj) {
        return -1;
    }

    public abstract Object a(View view, int i);

    public abstract void a(Parcelable parcelable, ClassLoader classLoader);

    public abstract void a(View view);

    public abstract void a(View view, int i, Object obj);

    public abstract boolean a(View view, Object obj);

    public abstract Parcelable b();

    public CharSequence b(int i) {
        return null;
    }

    public abstract void b(View view);

    public void b(View view, int i, Object obj) {
    }

    public void d() {
        if (this.f18911a != null) {
            this.f18911a.a();
        }
    }

    public void a(DataSetObserver dataSetObserver) {
        this.f18911a = dataSetObserver;
    }
}
