package com.xiaomi.smarthome.library.common.widget.slidinglayer;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import android.support.v4.view.MotionEventCompat;
import android.support.v4.view.ViewCompat;
import android.support.v4.view.ViewConfigurationCompat;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.VelocityTracker;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.ViewGroup;
import android.view.animation.Interpolator;
import android.widget.FrameLayout;
import android.widget.RelativeLayout;
import android.widget.Scroller;
import com.xiaomi.smarthome.R;
import java.util.Random;

public class SlidingLayer extends FrameLayout {
    private static final int D = 0;
    private static final int E = 1;
    private static final int F = 2;
    public static final int STICK_TO_BOTTOM = -4;
    public static final int STICK_TO_LEFT = -2;
    public static final int STICK_TO_RIGHT = -1;
    public static final int STICK_TO_TOP = -3;

    /* renamed from: a  reason: collision with root package name */
    private static final String f19071a = "state";
    private static final int b = 0;
    private static final int c = 1;
    private static final int d = 9000;
    private static final int e = 600;
    private static final int f = 10;
    private static final Interpolator g = new Interpolator() {
        public float getInterpolation(float f) {
            return ((float) Math.pow((double) (f - 1.0f), 5.0d)) + 1.0f;
        }
    };
    private static final int h = -1;
    private float A;
    private float B;
    private float C;
    private int G;
    private boolean H;
    private OnInteractListener I;
    private OnScrollListener J;
    private int K;
    private int L;
    private LayerTransformer M;
    private Random i;
    private Scroller j;
    private int k;
    private Drawable l;
    private boolean m;
    protected int mActivePointerId;
    protected int mMaximumVelocity;
    protected Bundle mState;
    protected VelocityTracker mVelocityTracker;
    private int n;
    private boolean o;
    private int p;
    private boolean q;
    private int r;
    private boolean s;
    private boolean t;
    private boolean u;
    private boolean v;
    private int w;
    private float x;
    private float y;
    private float z;

    public interface OnInteractListener {
        void a();

        void b();

        void c();

        void d();

        void e();

        void f();
    }

    public interface OnScrollListener {
        void a(int i);
    }

    public SlidingLayer(Context context) {
        this(context, (AttributeSet) null);
    }

    public SlidingLayer(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public SlidingLayer(Context context, AttributeSet attributeSet, int i2) {
        super(context, attributeSet, i2);
        this.mActivePointerId = -1;
        this.q = true;
        this.r = -1;
        this.s = true;
        this.t = true;
        this.x = -1.0f;
        this.y = -1.0f;
        this.z = -1.0f;
        this.A = -1.0f;
        this.B = -1.0f;
        this.C = -1.0f;
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R.styleable.SlidingLayer);
        setStickTo(obtainStyledAttributes.getInt(5, -1));
        int resourceId = obtainStyledAttributes.getResourceId(3, -1);
        if (resourceId != -1) {
            setShadowDrawable(resourceId);
        }
        this.k = (int) obtainStyledAttributes.getDimension(4, 0.0f);
        this.q = obtainStyledAttributes.getBoolean(0, true);
        this.n = obtainStyledAttributes.getDimensionPixelOffset(1, 0);
        this.r = obtainStyledAttributes.getDimensionPixelOffset(2, -1);
        b();
        obtainStyledAttributes.recycle();
        a();
    }

    private void a() {
        if (Build.VERSION.SDK_INT >= 11) {
            setLayerType(2, (Paint) null);
        }
        setWillNotDraw(false);
        setDescendantFocusability(262144);
        setFocusable(true);
        Context context = getContext();
        this.j = new Scroller(context, g);
        ViewConfiguration viewConfiguration = ViewConfiguration.get(context);
        this.w = ViewConfigurationCompat.getScaledPagingTouchSlop(viewConfiguration);
        this.K = viewConfiguration.getScaledMinimumFlingVelocity();
        this.mMaximumVelocity = viewConfiguration.getScaledMaximumFlingVelocity();
        this.L = (int) (context.getResources().getDisplayMetrics().density * 10.0f);
        this.i = new Random();
    }

    private int getCurrentState() {
        return this.G;
    }

    public boolean isOpened() {
        return this.G == 2;
    }

    public boolean isInPreviewMode() {
        return this.G == 1;
    }

    public boolean isClosed() {
        return this.G == 0;
    }

    public void openLayer(boolean z2) {
        a(2, z2);
    }

    public void openPreview(boolean z2) {
        if (this.r != -1) {
            a(1, z2);
            return;
        }
        throw new IllegalStateException("A value offset for the preview has to be specified in order to open the layer in preview mode. Use setPreviewOffsetDistance or set its associated XML property ");
    }

    public void closeLayer(boolean z2) {
        a(0, z2);
    }

    private void a(int i2, boolean z2) {
        a(i2, z2, false);
    }

    private void a(int i2, boolean z2, boolean z3) {
        a(i2, z2, z3, 0, 0);
    }

    private void a(int i2, boolean z2, boolean z3, int i3, int i4) {
        if (z3 || this.G != i2) {
            if (this.I != null) {
                b(i2);
            }
            int[] a2 = a(i2);
            if (z2) {
                if (h() != 0) {
                    i3 = i4;
                }
                smoothScrollTo(a2[0], a2[1], i3);
            } else {
                g();
                a(a2[0], a2[1]);
            }
            this.G = i2;
            return;
        }
        setDrawingCacheEnabled(false);
    }

    public void setOnInteractListener(OnInteractListener onInteractListener) {
        this.I = onInteractListener;
    }

    public void setOnScrollListener(OnScrollListener onScrollListener) {
        this.J = onScrollListener;
    }

    public void setLayerTransformer(LayerTransformer layerTransformer) {
        this.M = layerTransformer;
    }

    public void setShadowSize(int i2) {
        this.k = i2;
        invalidate(getLeft(), getTop(), getRight(), getBottom());
    }

    public void setShadowSizeRes(int i2) {
        setShadowSize((int) getResources().getDimension(i2));
    }

    public int getShadowSize() {
        return this.k;
    }

    public void setShadowDrawable(Drawable drawable) {
        this.l = drawable;
        refreshDrawableState();
        setWillNotDraw(false);
        invalidate(getLeft(), getTop(), getRight(), getBottom());
    }

    public void setShadowDrawable(int i2) {
        setShadowDrawable(getContext().getResources().getDrawable(i2));
    }

    public void setOffsetDistanceRes(int i2) {
        setOffsetDistance((int) getResources().getDimension(i2));
    }

    public void setOffsetDistance(int i2) {
        this.n = i2;
        b();
        invalidate(getLeft(), getTop(), getRight(), getBottom());
    }

    public int getOffsetDistance() {
        return this.n;
    }

    public void setPreviewOffsetDistanceRes(int i2) {
        setPreviewOffsetDistance((int) getResources().getDimension(i2));
    }

    public void setPreviewOffsetDistance(int i2) {
        this.r = i2;
        b();
        invalidate(getLeft(), getTop(), getRight(), getBottom());
        if (this.G == 1) {
            e();
        }
    }

    private void b() {
        if (c() && this.n > this.r) {
            throw new IllegalStateException("The showing offset of the layer can never be greater than the offset dimension of the preview mode");
        }
    }

    private boolean c() {
        return this.r != -1;
    }

    /* access modifiers changed from: protected */
    public boolean verifyDrawable(Drawable drawable) {
        return super.verifyDrawable(drawable) || drawable == this.l;
    }

    /* access modifiers changed from: protected */
    public void drawableStateChanged() {
        super.drawableStateChanged();
        Drawable drawable = this.l;
        if (drawable != null && drawable.isStateful()) {
            drawable.setState(getDrawableState());
        }
    }

    public boolean isSlidingEnabled() {
        return this.s;
    }

    public void setSlidingEnabled(boolean z2) {
        this.s = z2;
    }

    public boolean isSlidingFromShadowEnabled() {
        return this.t;
    }

    public void setSlidingFromShadowEnabled(boolean z2) {
        this.t = z2;
    }

    /* access modifiers changed from: protected */
    public Parcelable onSaveInstanceState() {
        SavedState savedState = new SavedState(super.onSaveInstanceState());
        if (this.mState == null) {
            this.mState = new Bundle();
        }
        this.mState.putInt("state", this.G);
        savedState.f19072a = this.mState;
        return savedState;
    }

    /* access modifiers changed from: protected */
    public void onRestoreInstanceState(Parcelable parcelable) {
        SavedState savedState = (SavedState) parcelable;
        super.onRestoreInstanceState(savedState.getSuperState());
        restoreState(savedState.f19072a);
    }

    public void restoreState(Parcelable parcelable) {
        this.mState = (Bundle) parcelable;
        a(this.mState.getInt("state"), true);
    }

    private float a(MotionEvent motionEvent) {
        return motionEvent.getRawX();
    }

    private float b(MotionEvent motionEvent) {
        return motionEvent.getRawY();
    }

    public boolean onInterceptTouchEvent(MotionEvent motionEvent) {
        MotionEvent motionEvent2 = motionEvent;
        boolean z2 = false;
        if (!this.s) {
            return false;
        }
        int action = motionEvent.getAction() & 255;
        if (action == 3 || action == 1) {
            this.u = false;
            this.v = false;
            this.mActivePointerId = -1;
            if (this.mVelocityTracker != null) {
                this.mVelocityTracker.recycle();
                this.mVelocityTracker = null;
            }
            return false;
        }
        if (action != 0) {
            if (this.u) {
                return true;
            }
            if (this.v) {
                return false;
            }
        }
        if (action == 0) {
            float a2 = a(motionEvent);
            this.B = a2;
            this.x = a2;
            float b2 = b(motionEvent);
            this.C = b2;
            this.y = b2;
            this.z = motionEvent2.getX(0);
            this.A = motionEvent2.getY(0);
            this.mActivePointerId = MotionEventCompat.getPointerId(motionEvent2, 0);
            if (a(motionEvent.getX(), motionEvent.getY())) {
                this.u = false;
                this.v = false;
                return super.onInterceptTouchEvent(motionEvent);
            }
            g();
            this.u = false;
            this.v = true;
        } else if (action == 2) {
            int i2 = this.mActivePointerId;
            if (i2 != -1) {
                int findPointerIndex = MotionEventCompat.findPointerIndex(motionEvent2, i2);
                float a3 = a(motionEvent);
                float f2 = a3 - this.x;
                float abs = Math.abs(f2);
                float b3 = b(motionEvent);
                float f3 = b3 - this.y;
                float abs2 = Math.abs(f3);
                if (!(f2 == 0.0f && f3 == 0.0f)) {
                    if (canScroll(this, false, (int) f2, (int) f3, (int) a3, (int) b3)) {
                        this.B = a3;
                        this.x = a3;
                        this.C = b3;
                        this.y = b3;
                        this.z = motionEvent2.getX(findPointerIndex);
                        this.A = motionEvent2.getY(findPointerIndex);
                        return false;
                    }
                }
                boolean z3 = abs > ((float) this.w) && abs > abs2;
                if (abs2 > ((float) this.w) && abs2 > abs) {
                    z2 = true;
                }
                if (z3) {
                    this.x = a3;
                } else if (z2) {
                    this.y = b3;
                }
                if (z3 || z2) {
                    this.u = true;
                    setDrawingCacheEnabled(true);
                }
            }
        } else if (action == 6) {
            c(motionEvent);
        }
        if (!this.u) {
            if (this.mVelocityTracker == null) {
                this.mVelocityTracker = VelocityTracker.obtain();
            }
            this.mVelocityTracker.addMovement(motionEvent2);
        }
        return this.u;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:44:0x00ed, code lost:
        r1 = 0.0f;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:45:0x00ee, code lost:
        r4 = 0.0f;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:46:0x00ef, code lost:
        r5 = 0.0f;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:52:0x0112, code lost:
        if (r14 <= r2) goto L_0x0116;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:53:0x0114, code lost:
        r14 = r2;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:55:0x0118, code lost:
        if (r14 >= r1) goto L_0x011b;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:56:0x011a, code lost:
        r14 = r1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:58:0x011d, code lost:
        if (r0 <= r4) goto L_0x0121;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:59:0x011f, code lost:
        r0 = r4;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:61:0x0123, code lost:
        if (r0 >= r5) goto L_0x0126;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:62:0x0125, code lost:
        r0 = r5;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:63:0x0126, code lost:
        r2 = (int) r14;
        r13.x += r14 - ((float) r2);
        r1 = (int) r0;
        r13.y += r0 - ((float) r1);
        a(r2, r1);
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public boolean onTouchEvent(android.view.MotionEvent r14) {
        /*
            r13 = this;
            int r0 = r14.getAction()
            r1 = 0
            if (r0 != 0) goto L_0x000e
            int r0 = r14.getEdgeFlags()
            if (r0 == 0) goto L_0x000e
            return r1
        L_0x000e:
            boolean r0 = r13.s
            if (r0 == 0) goto L_0x01ba
            boolean r0 = r13.u
            if (r0 != 0) goto L_0x0022
            float r0 = r13.z
            float r2 = r13.A
            boolean r0 = r13.a((float) r0, (float) r2)
            if (r0 != 0) goto L_0x0022
            goto L_0x01ba
        L_0x0022:
            android.view.VelocityTracker r0 = r13.mVelocityTracker
            if (r0 != 0) goto L_0x002c
            android.view.VelocityTracker r0 = android.view.VelocityTracker.obtain()
            r13.mVelocityTracker = r0
        L_0x002c:
            android.view.VelocityTracker r0 = r13.mVelocityTracker
            r0.addMovement(r14)
            int r0 = r14.getAction()
            r0 = r0 & 255(0xff, float:3.57E-43)
            r2 = -1
            r3 = 1
            switch(r0) {
                case 0: goto L_0x0194;
                case 1: goto L_0x013b;
                case 2: goto L_0x007c;
                case 3: goto L_0x006c;
                case 4: goto L_0x003c;
                case 5: goto L_0x0054;
                case 6: goto L_0x003e;
                default: goto L_0x003c;
            }
        L_0x003c:
            goto L_0x01b9
        L_0x003e:
            r13.c(r14)
            int r0 = r13.mActivePointerId
            android.support.v4.view.MotionEventCompat.findPointerIndex(r14, r0)
            float r0 = r13.a((android.view.MotionEvent) r14)
            r13.x = r0
            float r14 = r13.b((android.view.MotionEvent) r14)
            r13.y = r14
            goto L_0x01b9
        L_0x0054:
            int r0 = android.support.v4.view.MotionEventCompat.getActionIndex(r14)
            int r0 = r14.getPointerId(r0)
            r13.mActivePointerId = r0
            float r0 = r13.a((android.view.MotionEvent) r14)
            r13.x = r0
            float r14 = r13.b((android.view.MotionEvent) r14)
            r13.y = r14
            goto L_0x01b9
        L_0x006c:
            boolean r14 = r13.u
            if (r14 == 0) goto L_0x01b9
            int r14 = r13.G
            r13.a((int) r14, (boolean) r3, (boolean) r3)
            r13.mActivePointerId = r2
            r13.f()
            goto L_0x01b9
        L_0x007c:
            int r0 = r13.mActivePointerId
            android.support.v4.view.MotionEventCompat.findPointerIndex(r14, r0)
            float r0 = r14.getX()
            float r2 = r14.getY()
            boolean r0 = r13.a((float) r0, (float) r2, (boolean) r1)
            if (r0 != 0) goto L_0x0090
            return r1
        L_0x0090:
            float r0 = r13.a((android.view.MotionEvent) r14)
            float r14 = r13.b((android.view.MotionEvent) r14)
            float r2 = r13.x
            float r2 = r2 - r0
            float r4 = r13.y
            float r4 = r4 - r14
            r13.x = r0
            r13.y = r14
            boolean r5 = r13.u
            if (r5 != 0) goto L_0x00d7
            float r5 = r13.B
            float r0 = r0 - r5
            float r0 = java.lang.Math.abs(r0)
            float r5 = r13.C
            float r14 = r14 - r5
            float r14 = java.lang.Math.abs(r14)
            int r5 = r13.w
            float r5 = (float) r5
            int r5 = (r0 > r5 ? 1 : (r0 == r5 ? 0 : -1))
            if (r5 <= 0) goto L_0x00c1
            int r5 = (r0 > r14 ? 1 : (r0 == r14 ? 0 : -1))
            if (r5 <= 0) goto L_0x00c1
            r5 = 1
            goto L_0x00c2
        L_0x00c1:
            r5 = 0
        L_0x00c2:
            int r6 = r13.w
            float r6 = (float) r6
            int r6 = (r14 > r6 ? 1 : (r14 == r6 ? 0 : -1))
            if (r6 <= 0) goto L_0x00ce
            int r14 = (r14 > r0 ? 1 : (r14 == r0 ? 0 : -1))
            if (r14 <= 0) goto L_0x00ce
            r1 = 1
        L_0x00ce:
            if (r5 != 0) goto L_0x00d2
            if (r1 == 0) goto L_0x00d7
        L_0x00d2:
            r13.u = r3
            r13.setDrawingCacheEnabled(r3)
        L_0x00d7:
            boolean r14 = r13.u
            if (r14 == 0) goto L_0x01b9
            int r14 = r13.getScrollX()
            float r14 = (float) r14
            int r0 = r13.getScrollY()
            float r0 = (float) r0
            float r14 = r14 + r2
            float r0 = r0 + r4
            int r1 = r13.p
            r2 = 0
            switch(r1) {
                case -4: goto L_0x0107;
                case -3: goto L_0x00ff;
                case -2: goto L_0x00f8;
                case -1: goto L_0x00f1;
                default: goto L_0x00ed;
            }
        L_0x00ed:
            r1 = 0
        L_0x00ee:
            r4 = 0
        L_0x00ef:
            r5 = 0
            goto L_0x0110
        L_0x00f1:
            int r1 = r13.getWidth()
            int r1 = -r1
            float r1 = (float) r1
            goto L_0x00ee
        L_0x00f8:
            int r1 = r13.getWidth()
            float r1 = (float) r1
            r2 = r1
            goto L_0x00ed
        L_0x00ff:
            int r1 = r13.getHeight()
            float r1 = (float) r1
            r4 = r1
            r1 = 0
            goto L_0x00ef
        L_0x0107:
            int r1 = r13.getHeight()
            int r1 = -r1
            float r1 = (float) r1
            r5 = r1
            r1 = 0
            r4 = 0
        L_0x0110:
            int r6 = (r14 > r2 ? 1 : (r14 == r2 ? 0 : -1))
            if (r6 <= 0) goto L_0x0116
            r14 = r2
            goto L_0x011b
        L_0x0116:
            int r2 = (r14 > r1 ? 1 : (r14 == r1 ? 0 : -1))
            if (r2 >= 0) goto L_0x011b
            r14 = r1
        L_0x011b:
            int r1 = (r0 > r4 ? 1 : (r0 == r4 ? 0 : -1))
            if (r1 <= 0) goto L_0x0121
            r0 = r4
            goto L_0x0126
        L_0x0121:
            int r1 = (r0 > r5 ? 1 : (r0 == r5 ? 0 : -1))
            if (r1 >= 0) goto L_0x0126
            r0 = r5
        L_0x0126:
            float r1 = r13.x
            int r2 = (int) r14
            float r4 = (float) r2
            float r14 = r14 - r4
            float r1 = r1 + r14
            r13.x = r1
            float r14 = r13.y
            int r1 = (int) r0
            float r4 = (float) r1
            float r0 = r0 - r4
            float r14 = r14 + r0
            r13.y = r14
            r13.a((int) r2, (int) r1)
            goto L_0x01b9
        L_0x013b:
            boolean r0 = r13.u
            if (r0 == 0) goto L_0x0188
            android.view.VelocityTracker r0 = r13.mVelocityTracker
            r1 = 1000(0x3e8, float:1.401E-42)
            int r4 = r13.mMaximumVelocity
            float r4 = (float) r4
            r0.computeCurrentVelocity(r1, r4)
            int r1 = r13.mActivePointerId
            float r1 = android.support.v4.view.VelocityTrackerCompat.getXVelocity(r0, r1)
            int r1 = (int) r1
            int r4 = r13.mActivePointerId
            float r0 = android.support.v4.view.VelocityTrackerCompat.getYVelocity(r0, r4)
            int r0 = (int) r0
            int r5 = r13.getScrollX()
            int r6 = r13.getScrollY()
            int r4 = r13.mActivePointerId
            android.support.v4.view.MotionEventCompat.findPointerIndex(r14, r4)
            float r4 = r13.a((android.view.MotionEvent) r14)
            float r14 = r13.b((android.view.MotionEvent) r14)
            float r7 = r13.B
            int r9 = (int) r7
            float r7 = r13.C
            int r10 = (int) r7
            int r11 = (int) r4
            int r12 = (int) r14
            r4 = r13
            r7 = r1
            r8 = r0
            int r5 = r4.a(r5, r6, r7, r8, r9, r10, r11, r12)
            r6 = 1
            r7 = 1
            r8 = r1
            r9 = r0
            r4.a(r5, r6, r7, r8, r9)
            r13.mActivePointerId = r2
            r13.f()
            goto L_0x01b9
        L_0x0188:
            boolean r14 = r13.q
            if (r14 == 0) goto L_0x01b9
            int r14 = r13.d()
            r13.a((int) r14, (boolean) r3, (boolean) r3)
            goto L_0x01b9
        L_0x0194:
            r13.g()
            float r0 = r13.a((android.view.MotionEvent) r14)
            r13.B = r0
            r13.x = r0
            float r0 = r13.b((android.view.MotionEvent) r14)
            r13.C = r0
            r13.y = r0
            float r0 = r14.getX()
            r13.z = r0
            float r0 = r14.getY()
            r13.A = r0
            int r14 = r14.getPointerId(r1)
            r13.mActivePointerId = r14
        L_0x01b9:
            return r3
        L_0x01ba:
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.xiaomi.smarthome.library.common.widget.slidinglayer.SlidingLayer.onTouchEvent(android.view.MotionEvent):boolean");
    }

    private boolean a(float f2, float f3) {
        return a(f2, f3, true);
    }

    private boolean a(float f2, float f3, boolean z2) {
        int i2;
        if (h() == 0) {
            i2 = z2 ? getScrollX() : 0;
        } else {
            float f4 = f3;
            i2 = z2 ? getScrollY() : 0;
            f2 = f4;
        }
        switch (this.p) {
            case -4:
            case -1:
                if (f2 >= ((float) (-i2))) {
                    return true;
                }
                return false;
            case -3:
                if (f2 <= ((float) (getHeight() - i2))) {
                    return true;
                }
                return false;
            case -2:
                if (f2 <= ((float) (getWidth() - i2))) {
                    return true;
                }
                return false;
            default:
                throw new IllegalStateException("The layer has to be stuck to one of the sides of the screen. Current value is: " + this.p);
        }
    }

    /* access modifiers changed from: protected */
    public boolean canScroll(View view, boolean z2, int i2, int i3, int i4, int i5) {
        int i6;
        View view2 = view;
        if (view2 instanceof ViewGroup) {
            ViewGroup viewGroup = (ViewGroup) view2;
            int scrollX = view.getScrollX();
            int scrollY = view.getScrollY();
            for (int childCount = viewGroup.getChildCount() - 1; childCount >= 0; childCount--) {
                View childAt = viewGroup.getChildAt(childCount);
                int i7 = i4 + scrollX;
                if (i7 >= childAt.getLeft() && i7 < childAt.getRight() && (i6 = i5 + scrollY) >= childAt.getTop() && i6 < childAt.getBottom()) {
                    if (canScroll(childAt, true, i2, i3, i7 - childAt.getLeft(), i6 - childAt.getTop())) {
                        return true;
                    }
                }
            }
        }
        if (z2) {
            if (h() == 0 && ViewCompat.canScrollHorizontally(view, -i2)) {
                return true;
            }
            if (h() != 1 || !ViewCompat.canScrollVertically(view, -i3)) {
                return false;
            }
            return true;
        }
        return false;
    }

    private int a(int i2, int i3, int i4, int i5, int i6, int i7, int i8, int i9) {
        int i10;
        int i11;
        int i12;
        int i13;
        int i14 = -1;
        if (h() == 0) {
            int width = getWidth();
            int abs = Math.abs(width - Math.abs(i2));
            i10 = Math.abs(i8 - i6);
            if (this.p == -2) {
                i14 = 1;
            }
            i11 = i4 * i14;
            int i15 = width;
            i12 = abs;
            i13 = i15;
        } else {
            i13 = getHeight();
            i12 = Math.abs(i13 - Math.abs(i3));
            int abs2 = Math.abs(i9 - i7);
            if (this.p == -3) {
                i14 = 1;
            }
            int i16 = i5 * i14;
            i10 = abs2;
            i11 = i16;
        }
        int abs3 = Math.abs(i11);
        if (!(i10 > this.L && abs3 > this.K)) {
            if (i12 > (i13 + (c() ? this.r : 0)) / 2) {
                return 2;
            }
            if (!c() || i12 <= this.r / 2) {
                return 0;
            }
            return 1;
        } else if (i11 > 0) {
            return 2;
        } else {
            if (c() && i12 > this.r && abs3 < 9000) {
                return 1;
            }
            return 0;
        }
    }

    private int d() {
        switch (this.G) {
            case 0:
                if (c()) {
                    return 1;
                }
                return 2;
            case 1:
                return 2;
            case 2:
                return c() ? 1 : 0;
            default:
                return 0;
        }
    }

    /* access modifiers changed from: package-private */
    public void smoothScrollTo(int i2, int i3) {
        smoothScrollTo(i2, i3, 0);
    }

    /* access modifiers changed from: package-private */
    public void smoothScrollTo(int i2, int i3, int i4) {
        if (getChildCount() == 0) {
            setDrawingCacheEnabled(false);
            return;
        }
        int scrollX = getScrollX();
        int scrollY = getScrollY();
        int i5 = i2 - scrollX;
        int i6 = i3 - scrollY;
        if (i5 == 0 && i6 == 0) {
            g();
            if (this.I != null) {
                j();
                return;
            }
            return;
        }
        setDrawingCacheEnabled(true);
        this.H = true;
        int width = getWidth();
        float f2 = (float) (width / 2);
        float distanceInfluenceForSnapDuration = f2 + (distanceInfluenceForSnapDuration(Math.min(1.0f, (((float) Math.abs(i5)) * 1.0f) / ((float) width))) * f2);
        int abs = Math.abs(i4);
        this.j.startScroll(scrollX, scrollY, i5, i6, Math.min(abs > 0 ? Math.round(Math.abs(distanceInfluenceForSnapDuration / ((float) abs)) * 1000.0f) * 4 : 600, 600));
        ViewCompat.postInvalidateOnAnimation(this);
    }

    private void e() {
        int[] a2 = a(this.G);
        smoothScrollTo(a2[0], a2[1]);
    }

    /* access modifiers changed from: package-private */
    public float distanceInfluenceForSnapDuration(float f2) {
        double d2 = (double) (f2 - 0.5f);
        Double.isNaN(d2);
        return (float) Math.sin((double) ((float) (d2 * 0.4712389167638204d)));
    }

    private void f() {
        this.u = false;
        this.v = false;
        if (this.mVelocityTracker != null) {
            this.mVelocityTracker.recycle();
            this.mVelocityTracker = null;
        }
    }

    public void setDrawingCacheEnabled(boolean z2) {
        if (this.o != z2) {
            super.setDrawingCacheEnabled(z2);
            this.o = z2;
            int childCount = getChildCount();
            for (int i2 = 0; i2 < childCount; i2++) {
                View childAt = getChildAt(i2);
                if (childAt.getVisibility() != 8) {
                    childAt.setDrawingCacheEnabled(z2);
                }
            }
        }
    }

    private void c(MotionEvent motionEvent) {
        int actionIndex = motionEvent.getActionIndex();
        if (motionEvent.getPointerId(actionIndex) == this.mActivePointerId) {
            int i2 = actionIndex == 0 ? 1 : 0;
            this.x = motionEvent.getX(i2);
            this.mActivePointerId = motionEvent.getPointerId(i2);
            if (this.mVelocityTracker != null) {
                this.mVelocityTracker.clear();
            }
        }
    }

    private void g() {
        if (this.H) {
            setDrawingCacheEnabled(false);
            this.j.abortAnimation();
            int scrollX = getScrollX();
            int scrollY = getScrollY();
            int currX = this.j.getCurrX();
            int currY = this.j.getCurrY();
            if (!(scrollX == currX && scrollY == currY)) {
                a(currX, currY);
            }
            if (this.I != null) {
                j();
            }
        }
        this.H = false;
    }

    private void a(int i2, int i3) {
        int i4;
        scrollTo(i2, i3);
        if (this.J != null || this.M != null) {
            if (h() == 1) {
                i4 = getHeight() - Math.abs(i3);
            } else {
                i4 = getWidth() - Math.abs(i2);
            }
            if (this.J != null) {
                this.J.a(Math.abs(i4));
            }
            if (this.M != null) {
                float abs = (float) Math.abs(i4);
                this.M.a(this, this.r > 0 ? Math.min(1.0f, abs / ((float) this.r)) : 0.0f, abs / ((float) (h() == 0 ? getMeasuredWidth() : getMeasuredHeight())), this.p);
            }
        }
    }

    public void setStickTo(int i2) {
        this.m = true;
        this.p = i2;
        a(0, false, true);
    }

    private int h() {
        if (this.p == -3 || this.p == -4) {
            return 1;
        }
        if (this.p == -2 || this.p == -1) {
            return 0;
        }
        throw new IllegalStateException("The screen side of the layer is illegal");
    }

    public void setChangeStateOnTap(boolean z2) {
        this.q = z2;
    }

    /* access modifiers changed from: protected */
    public void onMeasure(int i2, int i3) {
        int defaultSize = getDefaultSize(0, i2);
        int defaultSize2 = getDefaultSize(0, i3);
        setMeasuredDimension(defaultSize, defaultSize2);
        if (this.M != null) {
            this.M.a(this, this.p);
        }
        super.onMeasure(getChildMeasureSpec(i2, 0, defaultSize), getChildMeasureSpec(i3, 0, defaultSize2));
    }

    /* access modifiers changed from: protected */
    public void onSizeChanged(int i2, int i3, int i4, int i5) {
        super.onSizeChanged(i2, i3, i4, i5);
        if (h() != 1 ? i2 != i4 : i3 != i5) {
            g();
            int[] a2 = a(this.G);
            scrollTo(a2[0], a2[1]);
        }
    }

    /* access modifiers changed from: protected */
    public void onLayout(boolean z2, int i2, int i3, int i4, int i5) {
        if (this.m) {
            this.m = false;
            i();
            if (this.p == -1) {
                setPadding(getPaddingLeft() + this.k, getPaddingTop(), getPaddingRight(), getPaddingBottom());
            } else if (this.p == -4) {
                setPadding(getPaddingLeft(), getPaddingTop() + this.k, getPaddingRight(), getPaddingBottom());
            } else if (this.p == -2) {
                setPadding(getPaddingLeft(), getPaddingTop(), getPaddingRight() + this.k, getPaddingBottom());
            } else if (this.p == -3) {
                setPadding(getPaddingLeft(), getPaddingTop(), getPaddingRight(), getPaddingBottom() + this.k);
            }
        }
        super.onLayout(z2, i2, i3, i4, i5);
    }

    /* access modifiers changed from: protected */
    public void onDraw(Canvas canvas) {
        super.onDraw(canvas);
    }

    private void i() {
        ViewGroup.LayoutParams layoutParams = getLayoutParams();
        if (layoutParams instanceof FrameLayout.LayoutParams) {
            FrameLayout.LayoutParams layoutParams2 = (FrameLayout.LayoutParams) layoutParams;
            switch (this.p) {
                case -4:
                    layoutParams2.gravity = 80;
                    break;
                case -3:
                    layoutParams2.gravity = 48;
                    break;
                case -2:
                    layoutParams2.gravity = 3;
                    break;
                case -1:
                    layoutParams2.gravity = 5;
                    break;
            }
            setLayoutParams(layoutParams);
        } else if (layoutParams instanceof RelativeLayout.LayoutParams) {
            RelativeLayout.LayoutParams layoutParams3 = (RelativeLayout.LayoutParams) layoutParams;
            switch (this.p) {
                case -4:
                    layoutParams3.addRule(12);
                    return;
                case -3:
                    layoutParams3.addRule(10);
                    return;
                case -2:
                    layoutParams3.addRule(9);
                    return;
                case -1:
                    layoutParams3.addRule(11);
                    return;
                default:
                    return;
            }
        }
    }

    private int[] a(int i2) {
        int[] iArr = new int[2];
        if (i2 == 2) {
            return iArr;
        }
        int i3 = i2 == 0 ? this.n : this.r;
        switch (this.p) {
            case -4:
                iArr[1] = (-getHeight()) + i3;
                break;
            case -3:
                iArr[1] = getHeight() - i3;
                break;
            case -2:
                iArr[0] = getWidth() - i3;
                break;
            case -1:
                iArr[0] = (-getWidth()) + i3;
                break;
        }
        return iArr;
    }

    public int getContentLeft() {
        return getLeft() + getPaddingLeft();
    }

    /* access modifiers changed from: protected */
    public void dispatchDraw(Canvas canvas) {
        super.dispatchDraw(canvas);
        if (this.k > 0 && this.l != null) {
            if (this.p == -1) {
                this.l.setBounds(0, 0, this.k, getHeight());
            }
            if (this.p == -3) {
                this.l.setBounds(0, getHeight() - this.k, getWidth(), getHeight());
            }
            if (this.p == -2) {
                this.l.setBounds(getWidth() - this.k, 0, getWidth(), getHeight());
            }
            if (this.p == -4) {
                this.l.setBounds(0, 0, getWidth(), this.k);
            }
            this.l.draw(canvas);
        }
    }

    public void computeScroll() {
        if (this.j.isFinished() || !this.j.computeScrollOffset()) {
            g();
            return;
        }
        int scrollX = getScrollX();
        int scrollY = getScrollY();
        int currX = this.j.getCurrX();
        int currY = this.j.getCurrY();
        if (!(scrollX == currX && scrollY == currY)) {
            a(currX, currY);
        }
        ViewCompat.postInvalidateOnAnimation(this);
    }

    private void b(int i2) {
        switch (i2) {
            case 0:
                this.I.c();
                return;
            case 1:
                this.I.b();
                return;
            case 2:
                this.I.a();
                return;
            default:
                return;
        }
    }

    private void j() {
        switch (this.G) {
            case 0:
                this.I.f();
                return;
            case 1:
                this.I.e();
                return;
            case 2:
                this.I.d();
                return;
            default:
                return;
        }
    }

    static class SavedState extends View.BaseSavedState {
        public static final Parcelable.Creator<SavedState> CREATOR = new Parcelable.Creator<SavedState>() {
            /* renamed from: a */
            public SavedState createFromParcel(Parcel parcel) {
                return new SavedState(parcel);
            }

            /* renamed from: a */
            public SavedState[] newArray(int i) {
                return new SavedState[i];
            }
        };

        /* renamed from: a  reason: collision with root package name */
        Bundle f19072a;

        public SavedState(Parcelable parcelable) {
            super(parcelable);
        }

        public SavedState(Parcel parcel) {
            super(parcel);
            this.f19072a = parcel.readBundle();
        }

        public void writeToParcel(Parcel parcel, int i) {
            super.writeToParcel(parcel, i);
            parcel.writeBundle(this.f19072a);
        }
    }
}
