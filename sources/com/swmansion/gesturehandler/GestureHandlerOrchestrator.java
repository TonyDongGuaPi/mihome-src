package com.swmansion.gesturehandler;

import android.graphics.Matrix;
import android.graphics.PointF;
import android.support.annotation.Nullable;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;

public class GestureHandlerOrchestrator {

    /* renamed from: a  reason: collision with root package name */
    private static final int f8877a = 20;
    private static final float b = 0.0f;
    private static final PointF c = new PointF();
    private static final float[] d = new float[2];
    private static final Matrix e = new Matrix();
    private static final float[] f = new float[2];
    private static final Comparator<GestureHandler> g = new Comparator<GestureHandler>() {
        /* renamed from: a */
        public int compare(GestureHandler gestureHandler, GestureHandler gestureHandler2) {
            if ((gestureHandler.m && gestureHandler2.m) || (gestureHandler.n && gestureHandler2.n)) {
                return Integer.signum(gestureHandler2.l - gestureHandler.l);
            }
            if (gestureHandler.m) {
                return -1;
            }
            if (gestureHandler2.m) {
                return 1;
            }
            if (gestureHandler.n) {
                return -1;
            }
            if (gestureHandler2.n) {
                return 1;
            }
            return 0;
        }
    };
    private final ViewGroup h;
    private final GestureHandlerRegistry i;
    private final ViewConfigurationHelper j;
    private final GestureHandler[] k = new GestureHandler[20];
    private final GestureHandler[] l = new GestureHandler[20];
    private final GestureHandler[] m = new GestureHandler[20];
    private final GestureHandler[] n = new GestureHandler[20];
    private int o = 0;
    private int p = 0;
    private boolean q = false;
    private int r = 0;
    private boolean s = false;
    private int t = 0;
    private float u = 0.0f;

    private static boolean a(int i2) {
        return i2 == 3 || i2 == 1 || i2 == 5;
    }

    public GestureHandlerOrchestrator(ViewGroup viewGroup, GestureHandlerRegistry gestureHandlerRegistry, ViewConfigurationHelper viewConfigurationHelper) {
        this.h = viewGroup;
        this.i = gestureHandlerRegistry;
        this.j = viewConfigurationHelper;
    }

    public void a(float f2) {
        this.u = f2;
    }

    public boolean a(MotionEvent motionEvent) {
        this.q = true;
        int actionMasked = motionEvent.getActionMasked();
        if (actionMasked == 0 || actionMasked == 5) {
            c(motionEvent);
        } else if (actionMasked == 3) {
            d();
        }
        b(motionEvent);
        this.q = false;
        if (this.s && this.r == 0) {
            b();
        }
        return true;
    }

    private void a() {
        if (this.q || this.r != 0) {
            this.s = true;
        } else {
            b();
        }
    }

    private void b() {
        boolean z = false;
        for (int i2 = this.o - 1; i2 >= 0; i2--) {
            GestureHandler gestureHandler = this.k[i2];
            if (a(gestureHandler.k()) && !gestureHandler.n) {
                this.k[i2] = null;
                gestureHandler.q();
                gestureHandler.m = false;
                gestureHandler.n = false;
                gestureHandler.l = Integer.MAX_VALUE;
                z = true;
            }
        }
        if (z) {
            int i3 = 0;
            for (int i4 = 0; i4 < this.o; i4++) {
                if (this.k[i4] != null) {
                    this.k[i3] = this.k[i4];
                    i3++;
                }
            }
            this.o = i3;
        }
        this.s = false;
    }

    private boolean a(GestureHandler gestureHandler) {
        for (int i2 = 0; i2 < this.o; i2++) {
            GestureHandler gestureHandler2 = this.k[i2];
            if (!a(gestureHandler2.k()) && a(gestureHandler, gestureHandler2)) {
                return true;
            }
        }
        return false;
    }

    private void b(GestureHandler gestureHandler) {
        if (a(gestureHandler)) {
            d(gestureHandler);
            return;
        }
        c(gestureHandler);
        gestureHandler.n = false;
    }

    private void c() {
        int i2 = 0;
        for (int i3 = 0; i3 < this.p; i3++) {
            if (this.l[i3].n) {
                this.l[i2] = this.l[i3];
                i2++;
            }
        }
        this.p = i2;
    }

    /* access modifiers changed from: package-private */
    public void a(GestureHandler gestureHandler, int i2, int i3) {
        this.r++;
        if (a(i2)) {
            for (int i4 = 0; i4 < this.p; i4++) {
                GestureHandler gestureHandler2 = this.l[i4];
                if (a(gestureHandler2, gestureHandler)) {
                    if (i2 == 5) {
                        gestureHandler2.l();
                        gestureHandler2.n = false;
                    } else {
                        b(gestureHandler2);
                    }
                }
            }
            c();
        }
        if (i2 == 4) {
            b(gestureHandler);
        } else if (i3 != 4 && i3 != 5) {
            gestureHandler.a(i2, i3);
        } else if (gestureHandler.m) {
            gestureHandler.a(i2, i3);
        }
        this.r--;
        a();
    }

    private void c(GestureHandler gestureHandler) {
        int k2 = gestureHandler.k();
        gestureHandler.n = false;
        gestureHandler.m = true;
        int i2 = this.t;
        this.t = i2 + 1;
        gestureHandler.l = i2;
        int i3 = 0;
        for (int i4 = 0; i4 < this.o; i4++) {
            GestureHandler gestureHandler2 = this.k[i4];
            if (c(gestureHandler2, gestureHandler)) {
                this.n[i3] = gestureHandler2;
                i3++;
            }
        }
        for (int i5 = i3 - 1; i5 >= 0; i5--) {
            this.n[i5].l();
        }
        for (int i6 = this.p - 1; i6 >= 0; i6--) {
            GestureHandler gestureHandler3 = this.l[i6];
            if (c(gestureHandler3, gestureHandler)) {
                gestureHandler3.l();
                gestureHandler3.n = false;
            }
        }
        c();
        gestureHandler.a(4, 2);
        if (k2 != 4) {
            gestureHandler.a(5, 4);
            if (k2 != 5) {
                gestureHandler.a(0, 5);
            }
        }
    }

    public void b(MotionEvent motionEvent) {
        int i2 = this.o;
        System.arraycopy(this.k, 0, this.m, 0, i2);
        Arrays.sort(this.m, 0, i2, g);
        for (int i3 = 0; i3 < i2; i3++) {
            a(this.m[i3], motionEvent);
        }
    }

    private void d() {
        for (int i2 = this.p - 1; i2 >= 0; i2--) {
            this.l[i2].l();
        }
        int i3 = this.o;
        for (int i4 = 0; i4 < i3; i4++) {
            this.m[i4] = this.k[i4];
        }
        for (int i5 = i3 - 1; i5 >= 0; i5--) {
            this.m[i5].l();
        }
    }

    private void a(GestureHandler gestureHandler, MotionEvent motionEvent) {
        if (!a(gestureHandler.e())) {
            gestureHandler.l();
        } else if (gestureHandler.j()) {
            int actionMasked = motionEvent.getActionMasked();
            if (!gestureHandler.n || actionMasked != 2) {
                float[] fArr = f;
                a(gestureHandler.e(), motionEvent, fArr);
                float x = motionEvent.getX();
                float y = motionEvent.getY();
                motionEvent.setLocation(fArr[0], fArr[1]);
                gestureHandler.c(motionEvent);
                if (gestureHandler.m) {
                    gestureHandler.b(motionEvent);
                }
                motionEvent.setLocation(x, y);
                if (actionMasked == 1 || actionMasked == 6) {
                    gestureHandler.e(motionEvent.getPointerId(motionEvent.getActionIndex()));
                }
            }
        }
    }

    private boolean a(@Nullable View view) {
        if (view == null) {
            return false;
        }
        if (view == this.h) {
            return true;
        }
        ViewParent parent = view.getParent();
        while (parent != null && parent != this.h) {
            parent = parent.getParent();
        }
        if (parent == this.h) {
            return true;
        }
        return false;
    }

    private void a(View view, MotionEvent motionEvent, float[] fArr) {
        if (view == this.h) {
            fArr[0] = motionEvent.getX();
            fArr[1] = motionEvent.getY();
        } else if (view == null || !(view.getParent() instanceof ViewGroup)) {
            throw new IllegalArgumentException("Parent is null? View is no longer in the tree");
        } else {
            ViewGroup viewGroup = (ViewGroup) view.getParent();
            a((View) viewGroup, motionEvent, fArr);
            PointF pointF = c;
            a(fArr[0], fArr[1], viewGroup, view, pointF);
            fArr[0] = pointF.x;
            fArr[1] = pointF.y;
        }
    }

    private void d(GestureHandler gestureHandler) {
        int i2 = 0;
        while (i2 < this.p) {
            if (this.l[i2] != gestureHandler) {
                i2++;
            } else {
                return;
            }
        }
        if (this.p < this.l.length) {
            GestureHandler[] gestureHandlerArr = this.l;
            int i3 = this.p;
            this.p = i3 + 1;
            gestureHandlerArr[i3] = gestureHandler;
            gestureHandler.n = true;
            int i4 = this.t;
            this.t = i4 + 1;
            gestureHandler.l = i4;
            return;
        }
        throw new IllegalStateException("Too many recognizers");
    }

    private void a(GestureHandler gestureHandler, View view) {
        int i2 = 0;
        while (i2 < this.o) {
            if (this.k[i2] != gestureHandler) {
                i2++;
            } else {
                return;
            }
        }
        if (this.o < this.k.length) {
            GestureHandler[] gestureHandlerArr = this.k;
            int i3 = this.o;
            this.o = i3 + 1;
            gestureHandlerArr[i3] = gestureHandler;
            gestureHandler.m = false;
            gestureHandler.n = false;
            gestureHandler.l = Integer.MAX_VALUE;
            gestureHandler.a(view, this);
            return;
        }
        throw new IllegalStateException("Too many recognizers");
    }

    private boolean a(View view, float[] fArr, int i2) {
        ArrayList<GestureHandler> a2 = this.i.a(view);
        if (a2 == null) {
            return false;
        }
        int size = a2.size();
        boolean z = false;
        for (int i3 = 0; i3 < size; i3++) {
            GestureHandler gestureHandler = a2.get(i3);
            if (gestureHandler.c() && gestureHandler.a(view, fArr[0], fArr[1])) {
                a(gestureHandler, view);
                gestureHandler.d(i2);
                z = true;
            }
        }
        return z;
    }

    private void c(MotionEvent motionEvent) {
        int actionIndex = motionEvent.getActionIndex();
        int pointerId = motionEvent.getPointerId(actionIndex);
        f[0] = motionEvent.getX(actionIndex);
        f[1] = motionEvent.getY(actionIndex);
        b(this.h, f, pointerId);
        a(this.h, f, pointerId);
    }

    private boolean a(ViewGroup viewGroup, float[] fArr, int i2) {
        boolean z;
        for (int childCount = viewGroup.getChildCount() - 1; childCount >= 0; childCount--) {
            View a2 = this.j.a(viewGroup, childCount);
            if (b(a2)) {
                PointF pointF = c;
                a(fArr[0], fArr[1], viewGroup, a2, pointF);
                float f2 = fArr[0];
                float f3 = fArr[1];
                fArr[0] = pointF.x;
                fArr[1] = pointF.y;
                if (!c(a2) || a(fArr[0], fArr[1], a2)) {
                    z = b(a2, fArr, i2);
                } else {
                    z = false;
                }
                fArr[0] = f2;
                fArr[1] = f3;
                if (z) {
                    return true;
                }
            }
        }
        return false;
    }

    private static boolean a(View view, float[] fArr) {
        if (!(!(view instanceof ViewGroup) || view.getBackground() != null) || !a(fArr[0], fArr[1], view)) {
            return false;
        }
        return true;
    }

    private boolean b(View view, float[] fArr, int i2) {
        PointerEventsConfig a2 = this.j.a(view);
        if (a2 == PointerEventsConfig.NONE) {
            return false;
        }
        if (a2 == PointerEventsConfig.BOX_ONLY) {
            if (a(view, fArr, i2) || a(view, fArr)) {
                return true;
            }
            return false;
        } else if (a2 == PointerEventsConfig.BOX_NONE) {
            if (view instanceof ViewGroup) {
                return a((ViewGroup) view, fArr, i2);
            }
            return false;
        } else if (a2 == PointerEventsConfig.AUTO) {
            boolean a3 = view instanceof ViewGroup ? a((ViewGroup) view, fArr, i2) : false;
            if (a(view, fArr, i2) || a3 || a(view, fArr)) {
                return true;
            }
            return false;
        } else {
            throw new IllegalArgumentException("Unknown pointer event type: " + a2.toString());
        }
    }

    private boolean b(View view) {
        return view.getVisibility() == 0 && view.getAlpha() >= this.u;
    }

    private static void a(float f2, float f3, ViewGroup viewGroup, View view, PointF pointF) {
        float scrollX = (f2 + ((float) viewGroup.getScrollX())) - ((float) view.getLeft());
        float scrollY = (f3 + ((float) viewGroup.getScrollY())) - ((float) view.getTop());
        Matrix matrix = view.getMatrix();
        if (!matrix.isIdentity()) {
            float[] fArr = d;
            fArr[0] = scrollX;
            fArr[1] = scrollY;
            Matrix matrix2 = e;
            matrix.invert(matrix2);
            matrix2.mapPoints(fArr);
            float f4 = fArr[0];
            scrollY = fArr[1];
            scrollX = f4;
        }
        pointF.set(scrollX, scrollY);
    }

    private boolean c(View view) {
        return !(view instanceof ViewGroup) || this.j.a((ViewGroup) view);
    }

    private static boolean a(float f2, float f3, View view) {
        return f2 >= 0.0f && f2 <= ((float) view.getWidth()) && f3 >= 0.0f && f3 < ((float) view.getHeight());
    }

    private static boolean a(GestureHandler gestureHandler, GestureHandler gestureHandler2) {
        return gestureHandler != gestureHandler2 && (gestureHandler.c(gestureHandler2) || gestureHandler2.b(gestureHandler));
    }

    private static boolean b(GestureHandler gestureHandler, GestureHandler gestureHandler2) {
        return gestureHandler == gestureHandler2 || gestureHandler.d(gestureHandler2) || gestureHandler2.d(gestureHandler);
    }

    private static boolean c(GestureHandler gestureHandler, GestureHandler gestureHandler2) {
        if (!gestureHandler.a(gestureHandler2) || b(gestureHandler, gestureHandler2)) {
            return false;
        }
        if (gestureHandler == gestureHandler2) {
            return true;
        }
        if (gestureHandler.n || gestureHandler.k() == 4) {
            return gestureHandler.e(gestureHandler2);
        }
        return true;
    }
}
