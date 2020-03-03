package com.xiaomi.yp_ui.widget.zoomable;

import android.view.MotionEvent;
import com.xiaomi.yp_ui.widget.zoomable.MultiPointerGestureDetector;

public class TransformGestureDetector implements MultiPointerGestureDetector.Listener {

    /* renamed from: a  reason: collision with root package name */
    private final MultiPointerGestureDetector f1618a;
    private Listener b = null;

    public interface Listener {
        void a(TransformGestureDetector transformGestureDetector);

        void b(TransformGestureDetector transformGestureDetector);

        void c(TransformGestureDetector transformGestureDetector);
    }

    public TransformGestureDetector(MultiPointerGestureDetector multiPointerGestureDetector) {
        this.f1618a = multiPointerGestureDetector;
        this.f1618a.a((MultiPointerGestureDetector.Listener) this);
    }

    public static TransformGestureDetector a() {
        return new TransformGestureDetector(MultiPointerGestureDetector.a());
    }

    public void a(Listener listener) {
        this.b = listener;
    }

    public void b() {
        this.f1618a.b();
    }

    public boolean a(MotionEvent motionEvent) {
        return this.f1618a.a(motionEvent);
    }

    public void a(MultiPointerGestureDetector multiPointerGestureDetector) {
        if (this.b != null) {
            this.b.a(this);
        }
    }

    public void b(MultiPointerGestureDetector multiPointerGestureDetector) {
        if (this.b != null) {
            this.b.b(this);
        }
    }

    public void c(MultiPointerGestureDetector multiPointerGestureDetector) {
        if (this.b != null) {
            this.b.c(this);
        }
    }

    private float a(float[] fArr, int i) {
        float f = 0.0f;
        for (int i2 = 0; i2 < i; i2++) {
            f += fArr[i2];
        }
        if (i > 0) {
            return f / ((float) i);
        }
        return 0.0f;
    }

    public void c() {
        this.f1618a.d();
    }

    public boolean d() {
        return this.f1618a.e();
    }

    public int e() {
        return this.f1618a.f();
    }

    public int f() {
        return this.f1618a.g();
    }

    public float g() {
        return a(this.f1618a.h(), this.f1618a.g());
    }

    public float h() {
        return a(this.f1618a.i(), this.f1618a.g());
    }

    public float i() {
        return a(this.f1618a.j(), this.f1618a.g()) - a(this.f1618a.h(), this.f1618a.g());
    }

    public float j() {
        return a(this.f1618a.k(), this.f1618a.g()) - a(this.f1618a.i(), this.f1618a.g());
    }

    public float k() {
        if (this.f1618a.g() < 2) {
            return 1.0f;
        }
        float f = this.f1618a.h()[1] - this.f1618a.h()[0];
        float f2 = this.f1618a.i()[1] - this.f1618a.i()[0];
        float f3 = this.f1618a.j()[1] - this.f1618a.j()[0];
        return ((float) Math.hypot((double) f3, (double) (this.f1618a.k()[1] - this.f1618a.k()[0]))) / ((float) Math.hypot((double) f, (double) f2));
    }

    public float l() {
        if (this.f1618a.g() < 2) {
            return 0.0f;
        }
        float f = this.f1618a.h()[1] - this.f1618a.h()[0];
        float f2 = this.f1618a.i()[1] - this.f1618a.i()[0];
        return ((float) Math.atan2((double) (this.f1618a.k()[1] - this.f1618a.k()[0]), (double) (this.f1618a.j()[1] - this.f1618a.j()[0]))) - ((float) Math.atan2((double) f2, (double) f));
    }
}
