package com.xiaomi.smarthome.newui.widget.topnavi.indicator;

import android.util.SparseArray;
import android.util.SparseBooleanArray;

public class NavigatorHelper {

    /* renamed from: a  reason: collision with root package name */
    private SparseBooleanArray f20924a = new SparseBooleanArray();
    private SparseArray<Float> b = new SparseArray<>();
    private int c;
    private int d;
    private int e;
    private float f;
    private int g;
    private boolean h;
    private OnNavigatorScrollListener i;

    public interface OnNavigatorScrollListener {
        void onDeselected(int i, int i2);

        void onEnter(int i, int i2, float f, boolean z);

        void onLeave(int i, int i2, float f, boolean z);

        void onSelected(int i, int i2);
    }

    public void a(int i2, float f2, int i3) {
        boolean z;
        float f3 = ((float) i2) + f2;
        boolean z2 = this.f <= f3;
        if (this.g == 0) {
            for (int i4 = 0; i4 < this.c; i4++) {
                if (i4 != this.d) {
                    if (!this.f20924a.get(i4)) {
                        e(i4);
                    }
                    if (this.b.get(i4, Float.valueOf(0.0f)).floatValue() != 1.0f) {
                        b(i4, 1.0f, false, true);
                    }
                }
            }
            a(this.d, 1.0f, false, true);
            d(this.d);
        } else if (f3 != this.f) {
            int i5 = i2 + 1;
            if (f2 != 0.0f || !z2) {
                z = true;
            } else {
                i5 = i2 - 1;
                z = false;
            }
            for (int i6 = 0; i6 < this.c; i6++) {
                if (!(i6 == i2 || i6 == i5 || this.b.get(i6, Float.valueOf(0.0f)).floatValue() == 1.0f)) {
                    b(i6, 1.0f, z2, true);
                }
            }
            if (!z) {
                float f4 = 1.0f - f2;
                b(i5, f4, true, false);
                a(i2, f4, true, false);
            } else if (z2) {
                b(i2, f2, true, false);
                a(i5, f2, true, false);
            } else {
                float f5 = 1.0f - f2;
                b(i5, f5, false, false);
                a(i2, f5, false, false);
            }
        } else {
            return;
        }
        this.f = f3;
    }

    private void a(int i2, float f2, boolean z, boolean z2) {
        if (this.h || i2 == this.d || this.g == 1 || z2) {
            if (this.i != null) {
                this.i.onEnter(i2, this.c, f2, z);
            }
            this.b.put(i2, Float.valueOf(1.0f - f2));
        }
    }

    private void b(int i2, float f2, boolean z, boolean z2) {
        if (this.h || i2 == this.e || this.g == 1 || (((i2 == this.d - 1 || i2 == this.d + 1) && this.b.get(i2, Float.valueOf(0.0f)).floatValue() != 1.0f) || z2)) {
            if (this.i != null) {
                this.i.onLeave(i2, this.c, f2, z);
            }
            this.b.put(i2, Float.valueOf(f2));
        }
    }

    private void d(int i2) {
        if (this.i != null) {
            this.i.onSelected(i2, this.c);
        }
        this.f20924a.put(i2, false);
    }

    private void e(int i2) {
        if (this.i != null) {
            this.i.onDeselected(i2, this.c);
        }
        this.f20924a.put(i2, true);
    }

    public void a(int i2) {
        this.e = this.d;
        this.d = i2;
        d(this.d);
        for (int i3 = 0; i3 < this.c; i3++) {
            if (i3 != this.d && !this.f20924a.get(i3)) {
                e(i3);
            }
        }
    }

    public void b(int i2) {
        this.g = i2;
    }

    public void a(OnNavigatorScrollListener onNavigatorScrollListener) {
        this.i = onNavigatorScrollListener;
    }

    public void a(boolean z) {
        this.h = z;
    }

    public int a() {
        return this.c;
    }

    public void c(int i2) {
        this.c = i2;
        this.f20924a.clear();
        this.b.clear();
    }

    public int b() {
        return this.d;
    }

    public int c() {
        return this.g;
    }
}
