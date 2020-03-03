package com.mi.global.shop.widget.gallery;

import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.view.MotionEvent;
import android.view.VelocityTracker;
import android.view.ViewConfiguration;

public class GestureDetector {

    /* renamed from: a  reason: collision with root package name */
    public static final String f7232a = "GestureDetector";
    private static final int g = ViewConfiguration.getLongPressTimeout();
    private static final int h = ViewConfiguration.getTapTimeout();
    private static final int i = ViewConfiguration.getDoubleTapTimeout();
    private static final int j = 1;
    private static final int k = 2;
    private static final int l = 3;
    private VelocityTracker A;
    private int b;
    private int c;
    private int d;
    private int e;
    private int f;
    private final Handler m;
    /* access modifiers changed from: private */
    public final OnGestureListener n;
    /* access modifiers changed from: private */
    public OnDoubleTapListener o;
    /* access modifiers changed from: private */
    public boolean p;
    private boolean q;
    private boolean r;
    private boolean s;
    /* access modifiers changed from: private */
    public MotionEvent t;
    private MotionEvent u;
    private boolean v;
    private float w;
    private float x;
    private boolean y;
    private boolean z;

    public interface OnDoubleTapListener {
        boolean a(MotionEvent motionEvent);

        boolean b(MotionEvent motionEvent);

        boolean c(MotionEvent motionEvent);
    }

    public interface OnGestureListener {
        boolean a(MotionEvent motionEvent, MotionEvent motionEvent2, float f, float f2);

        boolean b(MotionEvent motionEvent, MotionEvent motionEvent2, float f, float f2);

        boolean d(MotionEvent motionEvent);

        boolean e(MotionEvent motionEvent);

        void f(MotionEvent motionEvent);

        boolean g(MotionEvent motionEvent);

        void h(MotionEvent motionEvent);
    }

    public static class SimpleOnGestureListener implements OnDoubleTapListener, OnGestureListener {
        public boolean a(MotionEvent motionEvent) {
            return false;
        }

        public boolean a(MotionEvent motionEvent, MotionEvent motionEvent2, float f, float f2) {
            return false;
        }

        public boolean b(MotionEvent motionEvent) {
            return false;
        }

        public boolean b(MotionEvent motionEvent, MotionEvent motionEvent2, float f, float f2) {
            return false;
        }

        public boolean c(MotionEvent motionEvent) {
            return false;
        }

        public boolean d(MotionEvent motionEvent) {
            return false;
        }

        public boolean e(MotionEvent motionEvent) {
            return false;
        }

        public void f(MotionEvent motionEvent) {
        }

        public boolean g(MotionEvent motionEvent) {
            return false;
        }

        public void h(MotionEvent motionEvent) {
        }
    }

    private class GestureHandler extends Handler {
        GestureHandler() {
        }

        GestureHandler(Handler handler) {
            super(handler.getLooper());
        }

        public void handleMessage(Message message) {
            switch (message.what) {
                case 1:
                    GestureDetector.this.n.f(GestureDetector.this.t);
                    return;
                case 2:
                    GestureDetector.this.c();
                    return;
                case 3:
                    if (GestureDetector.this.o != null && !GestureDetector.this.p) {
                        GestureDetector.this.o.a(GestureDetector.this.t);
                        return;
                    }
                    return;
                default:
                    throw new RuntimeException("Unknown message " + message);
            }
        }
    }

    @Deprecated
    public GestureDetector(OnGestureListener onGestureListener, Handler handler) {
        this((Context) null, onGestureListener, handler);
    }

    @Deprecated
    public GestureDetector(OnGestureListener onGestureListener) {
        this((Context) null, onGestureListener, (Handler) null);
    }

    public GestureDetector(Context context, OnGestureListener onGestureListener) {
        this(context, onGestureListener, (Handler) null);
    }

    public GestureDetector(Context context, OnGestureListener onGestureListener, Handler handler) {
        this(context, onGestureListener, handler, true);
    }

    public GestureDetector(Context context, OnGestureListener onGestureListener, Handler handler, boolean z2) {
        if (handler != null) {
            this.m = new GestureHandler(handler);
        } else {
            this.m = new GestureHandler();
        }
        this.n = onGestureListener;
        if (onGestureListener instanceof OnDoubleTapListener) {
            a((OnDoubleTapListener) onGestureListener);
        }
        a(context, z2);
    }

    private void a(Context context, boolean z2) {
        int i2;
        int i3;
        int i4;
        if (this.n != null) {
            this.y = true;
            this.z = z2;
            if (context == null) {
                i3 = ViewConfiguration.getTouchSlop();
                i2 = i3 + 2;
                i4 = 100;
                this.e = ViewConfiguration.getMinimumFlingVelocity();
                this.f = ViewConfiguration.getMaximumFlingVelocity();
            } else {
                ViewConfiguration viewConfiguration = ViewConfiguration.get(context);
                int scaledTouchSlop = viewConfiguration.getScaledTouchSlop();
                int scaledDoubleTapSlop = viewConfiguration.getScaledDoubleTapSlop();
                this.e = viewConfiguration.getScaledMinimumFlingVelocity();
                this.f = viewConfiguration.getScaledMaximumFlingVelocity();
                i3 = scaledTouchSlop;
                i4 = scaledDoubleTapSlop;
                i2 = 18;
            }
            this.b = i3 * i3;
            this.c = i2 * i2;
            this.d = i4 * i4;
            return;
        }
        throw new NullPointerException("OnGestureListener must not be null");
    }

    public void a(OnDoubleTapListener onDoubleTapListener) {
        this.o = onDoubleTapListener;
    }

    public void a(boolean z2) {
        this.y = z2;
    }

    public boolean a() {
        return this.y;
    }

    /* JADX WARNING: Removed duplicated region for block: B:62:0x0178  */
    /* JADX WARNING: Removed duplicated region for block: B:65:0x018f  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public boolean a(android.view.MotionEvent r12) {
        /*
            r11 = this;
            int r0 = r12.getAction()
            float r1 = r12.getY()
            float r2 = r12.getX()
            android.view.VelocityTracker r3 = r11.A
            if (r3 != 0) goto L_0x0016
            android.view.VelocityTracker r3 = android.view.VelocityTracker.obtain()
            r11.A = r3
        L_0x0016:
            android.view.VelocityTracker r3 = r11.A
            r3.addMovement(r12)
            r0 = r0 & 255(0xff, float:3.57E-43)
            r3 = 2
            r4 = 3
            r5 = 1
            r6 = 0
            switch(r0) {
                case 0: goto L_0x012f;
                case 1: goto L_0x00aa;
                case 2: goto L_0x002b;
                case 3: goto L_0x0026;
                default: goto L_0x0024;
            }
        L_0x0024:
            goto L_0x01be
        L_0x0026:
            r11.b()
            goto L_0x01be
        L_0x002b:
            boolean r0 = r11.q
            if (r0 == 0) goto L_0x0031
            goto L_0x01be
        L_0x0031:
            float r0 = r11.x
            float r0 = r0 - r2
            float r7 = r11.w
            float r7 = r7 - r1
            boolean r8 = r11.v
            if (r8 == 0) goto L_0x0044
            com.mi.global.shop.widget.gallery.GestureDetector$OnDoubleTapListener r0 = r11.o
            boolean r12 = r0.c(r12)
            r6 = r6 | r12
            goto L_0x01be
        L_0x0044:
            boolean r8 = r11.r
            if (r8 == 0) goto L_0x008a
            android.view.MotionEvent r8 = r11.t
            float r8 = r8.getX()
            float r8 = r2 - r8
            int r8 = (int) r8
            android.view.MotionEvent r9 = r11.t
            float r9 = r9.getY()
            float r9 = r1 - r9
            int r9 = (int) r9
            int r8 = r8 * r8
            int r9 = r9 * r9
            int r8 = r8 + r9
            int r9 = r11.b
            if (r8 <= r9) goto L_0x0081
            com.mi.global.shop.widget.gallery.GestureDetector$OnGestureListener r9 = r11.n
            android.view.MotionEvent r10 = r11.t
            boolean r12 = r9.a(r10, r12, r0, r7)
            r11.x = r2
            r11.w = r1
            r11.r = r6
            android.os.Handler r0 = r11.m
            r0.removeMessages(r4)
            android.os.Handler r0 = r11.m
            r0.removeMessages(r5)
            android.os.Handler r0 = r11.m
            r0.removeMessages(r3)
            goto L_0x0082
        L_0x0081:
            r12 = 0
        L_0x0082:
            int r0 = r11.c
            if (r8 <= r0) goto L_0x012c
            r11.s = r6
            goto L_0x012c
        L_0x008a:
            float r3 = java.lang.Math.abs(r0)
            r4 = 1065353216(0x3f800000, float:1.0)
            int r3 = (r3 > r4 ? 1 : (r3 == r4 ? 0 : -1))
            if (r3 >= 0) goto L_0x009c
            float r3 = java.lang.Math.abs(r7)
            int r3 = (r3 > r4 ? 1 : (r3 == r4 ? 0 : -1))
            if (r3 < 0) goto L_0x01be
        L_0x009c:
            com.mi.global.shop.widget.gallery.GestureDetector$OnGestureListener r3 = r11.n
            android.view.MotionEvent r4 = r11.t
            boolean r6 = r3.a(r4, r12, r0, r7)
            r11.x = r2
            r11.w = r1
            goto L_0x01be
        L_0x00aa:
            r11.p = r6
            android.view.MotionEvent r0 = android.view.MotionEvent.obtain(r12)
            boolean r1 = r11.v
            if (r1 == 0) goto L_0x00bc
            com.mi.global.shop.widget.gallery.GestureDetector$OnDoubleTapListener r1 = r11.o
            boolean r12 = r1.c(r12)
            r12 = r12 | r6
            goto L_0x0106
        L_0x00bc:
            boolean r1 = r11.q
            if (r1 == 0) goto L_0x00c8
            android.os.Handler r12 = r11.m
            r12.removeMessages(r4)
            r11.q = r6
            goto L_0x00fc
        L_0x00c8:
            boolean r1 = r11.r
            if (r1 == 0) goto L_0x00d3
            com.mi.global.shop.widget.gallery.GestureDetector$OnGestureListener r1 = r11.n
            boolean r12 = r1.g(r12)
            goto L_0x0106
        L_0x00d3:
            android.view.VelocityTracker r1 = r11.A
            r2 = 1000(0x3e8, float:1.401E-42)
            int r4 = r11.f
            float r4 = (float) r4
            r1.computeCurrentVelocity(r2, r4)
            float r2 = r1.getYVelocity()
            float r1 = r1.getXVelocity()
            float r4 = java.lang.Math.abs(r2)
            int r7 = r11.e
            float r7 = (float) r7
            int r4 = (r4 > r7 ? 1 : (r4 == r7 ? 0 : -1))
            if (r4 > 0) goto L_0x00fe
            float r4 = java.lang.Math.abs(r1)
            int r7 = r11.e
            float r7 = (float) r7
            int r4 = (r4 > r7 ? 1 : (r4 == r7 ? 0 : -1))
            if (r4 <= 0) goto L_0x00fc
            goto L_0x00fe
        L_0x00fc:
            r12 = 0
            goto L_0x0106
        L_0x00fe:
            com.mi.global.shop.widget.gallery.GestureDetector$OnGestureListener r4 = r11.n
            android.view.MotionEvent r7 = r11.t
            boolean r12 = r4.b(r7, r12, r1, r2)
        L_0x0106:
            com.mi.global.shop.widget.gallery.GestureDetector$OnGestureListener r1 = r11.n
            android.view.MotionEvent r2 = r11.t
            r1.e(r2)
            android.view.MotionEvent r1 = r11.u
            if (r1 == 0) goto L_0x0116
            android.view.MotionEvent r1 = r11.u
            r1.recycle()
        L_0x0116:
            r11.u = r0
            android.view.VelocityTracker r0 = r11.A
            r0.recycle()
            r0 = 0
            r11.A = r0
            r11.v = r6
            android.os.Handler r0 = r11.m
            r0.removeMessages(r5)
            android.os.Handler r0 = r11.m
            r0.removeMessages(r3)
        L_0x012c:
            r6 = r12
            goto L_0x01be
        L_0x012f:
            com.mi.global.shop.widget.gallery.GestureDetector$OnDoubleTapListener r0 = r11.o
            if (r0 == 0) goto L_0x016f
            android.os.Handler r0 = r11.m
            boolean r0 = r0.hasMessages(r4)
            if (r0 == 0) goto L_0x0140
            android.os.Handler r7 = r11.m
            r7.removeMessages(r4)
        L_0x0140:
            android.view.MotionEvent r7 = r11.t
            if (r7 == 0) goto L_0x0167
            android.view.MotionEvent r7 = r11.u
            if (r7 == 0) goto L_0x0167
            if (r0 == 0) goto L_0x0167
            android.view.MotionEvent r0 = r11.t
            android.view.MotionEvent r7 = r11.u
            boolean r0 = r11.a(r0, r7, r12)
            if (r0 == 0) goto L_0x0167
            r11.v = r5
            com.mi.global.shop.widget.gallery.GestureDetector$OnDoubleTapListener r0 = r11.o
            android.view.MotionEvent r4 = r11.t
            boolean r0 = r0.b(r4)
            r0 = r0 | r6
            com.mi.global.shop.widget.gallery.GestureDetector$OnDoubleTapListener r4 = r11.o
            boolean r4 = r4.c(r12)
            r0 = r0 | r4
            goto L_0x0170
        L_0x0167:
            android.os.Handler r0 = r11.m
            int r7 = i
            long r7 = (long) r7
            r0.sendEmptyMessageDelayed(r4, r7)
        L_0x016f:
            r0 = 0
        L_0x0170:
            r11.x = r2
            r11.w = r1
            android.view.MotionEvent r1 = r11.t
            if (r1 == 0) goto L_0x017d
            android.view.MotionEvent r1 = r11.t
            r1.recycle()
        L_0x017d:
            android.view.MotionEvent r1 = android.view.MotionEvent.obtain(r12)
            r11.t = r1
            r11.r = r5
            r11.s = r5
            r11.p = r5
            r11.q = r6
            boolean r1 = r11.y
            if (r1 == 0) goto L_0x01a7
            android.os.Handler r1 = r11.m
            r1.removeMessages(r3)
            android.os.Handler r1 = r11.m
            android.view.MotionEvent r2 = r11.t
            long r6 = r2.getDownTime()
            int r2 = h
            long r8 = (long) r2
            long r6 = r6 + r8
            int r2 = g
            long r8 = (long) r2
            long r6 = r6 + r8
            r1.sendEmptyMessageAtTime(r3, r6)
        L_0x01a7:
            android.os.Handler r1 = r11.m
            android.view.MotionEvent r2 = r11.t
            long r2 = r2.getDownTime()
            int r4 = h
            long r6 = (long) r4
            long r2 = r2 + r6
            r1.sendEmptyMessageAtTime(r5, r2)
            com.mi.global.shop.widget.gallery.GestureDetector$OnGestureListener r1 = r11.n
            boolean r12 = r1.d(r12)
            r6 = r0 | r12
        L_0x01be:
            return r6
        */
        throw new UnsupportedOperationException("Method not decompiled: com.mi.global.shop.widget.gallery.GestureDetector.a(android.view.MotionEvent):boolean");
    }

    private void b() {
        this.m.removeMessages(1);
        this.m.removeMessages(2);
        this.m.removeMessages(3);
        this.A.recycle();
        this.A = null;
        this.v = false;
        this.p = false;
        if (this.q) {
            this.q = false;
        }
    }

    private boolean a(MotionEvent motionEvent, MotionEvent motionEvent2, MotionEvent motionEvent3) {
        if (!this.s || motionEvent3.getEventTime() - motionEvent2.getEventTime() > ((long) i)) {
            return false;
        }
        int x2 = ((int) motionEvent.getX()) - ((int) motionEvent3.getX());
        int y2 = ((int) motionEvent.getY()) - ((int) motionEvent3.getY());
        if ((x2 * x2) + (y2 * y2) < this.d) {
            return true;
        }
        return false;
    }

    /* access modifiers changed from: private */
    public void c() {
        this.m.removeMessages(3);
        this.q = true;
        this.n.h(this.t);
    }
}
