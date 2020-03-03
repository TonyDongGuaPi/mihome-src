package com.xiaomi.smarthome.library.common.widget;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Point;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.Region;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.util.AttributeSet;
import android.util.Log;
import android.util.Property;
import android.util.TypedValue;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.LinearInterpolator;
import android.widget.AdapterView;
import android.widget.FrameLayout;
import com.xiaomi.smarthome.R;

public class MaterialRippleLayout extends FrameLayout {

    /* renamed from: a  reason: collision with root package name */
    private static final int f18882a = 350;
    private static final int b = 75;
    private static final float c = 35.0f;
    private static final float d = 0.2f;
    private static final int e = -16777216;
    private static final int f = 0;
    private static final boolean g = true;
    private static final boolean h = true;
    private static final boolean i = false;
    private static final boolean j = false;
    private static final boolean k = false;
    private static final int l = 0;
    private static final int m = 50;
    private static final long n = 2500;
    /* access modifiers changed from: private */
    public boolean A;
    private float B;
    private float C;
    private AdapterView D;
    /* access modifiers changed from: private */
    public View E;
    private AnimatorSet F;
    private ObjectAnimator G;
    private Point H;
    private Point I;
    private int J;
    private boolean K;
    /* access modifiers changed from: private */
    public boolean L;
    private int M;
    private GestureDetector N;
    private PerformClickEvent O;
    private PressedEvent P;
    /* access modifiers changed from: private */
    public boolean Q;
    private GestureDetector.SimpleOnGestureListener R;
    private int S;
    private boolean T;
    private Property<MaterialRippleLayout, Float> U;
    private Property<MaterialRippleLayout, Integer> V;
    private boolean W;
    private boolean aa;
    Path mPath;
    private final Paint o;
    private final Rect p;
    private int q;
    private boolean r;
    /* access modifiers changed from: private */
    public boolean s;
    private int t;
    private int u;
    /* access modifiers changed from: private */
    public int v;
    /* access modifiers changed from: private */
    public boolean w;
    private int x;
    /* access modifiers changed from: private */
    public boolean y;
    private Drawable z;

    public boolean isInEditMode() {
        return true;
    }

    public static RippleBuilder on(View view) {
        return new RippleBuilder(view);
    }

    public MaterialRippleLayout(Context context) {
        this(context, (AttributeSet) null, 0);
    }

    public MaterialRippleLayout(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public MaterialRippleLayout(Context context, AttributeSet attributeSet, int i2) {
        super(context, attributeSet, i2);
        this.o = new Paint(1);
        this.p = new Rect();
        this.H = new Point();
        this.I = new Point();
        this.R = new GestureDetector.SimpleOnGestureListener() {
            public void onLongPress(MotionEvent motionEvent) {
                boolean unused = MaterialRippleLayout.this.Q = MaterialRippleLayout.this.E != null && MaterialRippleLayout.this.E.performLongClick();
                if (MaterialRippleLayout.this.Q) {
                    if (MaterialRippleLayout.this.s) {
                        MaterialRippleLayout.this.a((Runnable) null);
                    }
                    MaterialRippleLayout.this.a();
                }
            }

            public boolean onDown(MotionEvent motionEvent) {
                boolean unused = MaterialRippleLayout.this.Q = false;
                return super.onDown(motionEvent);
            }
        };
        this.T = false;
        this.mPath = null;
        this.U = new Property<MaterialRippleLayout, Float>(Float.class, "radius") {
            /* renamed from: a */
            public Float get(MaterialRippleLayout materialRippleLayout) {
                return Float.valueOf(materialRippleLayout.getRadius());
            }

            /* renamed from: a */
            public void set(MaterialRippleLayout materialRippleLayout, Float f) {
                materialRippleLayout.setRadius(f.floatValue());
            }
        };
        this.V = new Property<MaterialRippleLayout, Integer>(Integer.class, "rippleAlpha") {
            /* renamed from: a */
            public Integer get(MaterialRippleLayout materialRippleLayout) {
                return Integer.valueOf(materialRippleLayout.getRippleAlpha());
            }

            /* renamed from: a */
            public void set(MaterialRippleLayout materialRippleLayout, Integer num) {
                materialRippleLayout.setRippleAlpha(num);
            }
        };
        this.W = false;
        this.aa = true;
        setWillNotDraw(false);
        this.N = new GestureDetector(context, this.R);
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R.styleable.MaterialRippleLayout);
        this.q = obtainStyledAttributes.getColor(2, -16777216);
        this.t = obtainStyledAttributes.getDimensionPixelSize(4, (int) dpToPx(getResources(), 35.0f));
        this.r = obtainStyledAttributes.getBoolean(9, false);
        this.s = obtainStyledAttributes.getBoolean(7, true);
        this.u = obtainStyledAttributes.getInt(5, f18882a);
        this.v = (int) (obtainStyledAttributes.getFloat(0, 0.2f) * 255.0f);
        this.w = obtainStyledAttributes.getBoolean(3, true);
        this.x = obtainStyledAttributes.getInteger(6, 75);
        this.z = new ColorDrawable(obtainStyledAttributes.getColor(1, 0));
        this.y = obtainStyledAttributes.getBoolean(10, false);
        this.A = obtainStyledAttributes.getBoolean(8, false);
        this.B = (float) obtainStyledAttributes.getDimensionPixelSize(11, 0);
        obtainStyledAttributes.recycle();
        this.o.setColor(this.q);
        this.o.setAlpha(this.v);
        h();
    }

    public <T extends View> T getChildView() {
        return this.E;
    }

    public final void addView(View view, int i2, ViewGroup.LayoutParams layoutParams) {
        if (getChildCount() <= 0) {
            this.E = view;
            super.addView(view, i2, layoutParams);
            return;
        }
        throw new IllegalStateException("MaterialRippleLayout can host only one child");
    }

    public void setOnClickListener(View.OnClickListener onClickListener) {
        if (this.E != null) {
            this.E.setOnClickListener(onClickListener);
            return;
        }
        throw new IllegalStateException("MaterialRippleLayout must have a child view to handle clicks");
    }

    public void setOnLongClickListener(View.OnLongClickListener onLongClickListener) {
        if (this.E != null) {
            this.E.setOnLongClickListener(onLongClickListener);
            return;
        }
        throw new IllegalStateException("MaterialRippleLayout must have a child view to handle clicks");
    }

    public boolean onInterceptTouchEvent(MotionEvent motionEvent) {
        return !a(this.E, (int) motionEvent.getX(), (int) motionEvent.getY());
    }

    public boolean onTouchEvent(MotionEvent motionEvent) {
        boolean onTouchEvent = super.onTouchEvent(motionEvent);
        if (!isEnabled() || (this.E != null && !this.E.isEnabled())) {
            return onTouchEvent;
        }
        boolean contains = this.p.contains((int) motionEvent.getX(), (int) motionEvent.getY());
        if (contains) {
            this.I.set(this.H.x, this.H.y);
            this.H.set((int) motionEvent.getX(), (int) motionEvent.getY());
        }
        if (this.N.onTouchEvent(motionEvent) || this.Q) {
            return true;
        }
        switch (motionEvent.getActionMasked()) {
            case 0:
                f();
                this.K = false;
                this.P = new PressedEvent(motionEvent);
                if (!d()) {
                    this.P.run();
                    break;
                } else {
                    a();
                    this.L = true;
                    postDelayed(this.P, (long) ViewConfiguration.getTapTimeout());
                    break;
                }
            case 1:
                this.O = new PerformClickEvent();
                if (this.L) {
                    if (this.E != null) {
                        this.E.setPressed(true);
                    }
                    postDelayed(new Runnable() {
                        public void run() {
                            if (MaterialRippleLayout.this.E != null) {
                                MaterialRippleLayout.this.E.setPressed(false);
                            }
                        }
                    }, (long) ViewConfiguration.getPressedStateDuration());
                }
                if (contains) {
                    a(this.O);
                } else if (!this.s) {
                    setRadius(0.0f);
                }
                if (!this.w && contains) {
                    this.O.run();
                }
                a();
                break;
            case 2:
                if (this.s) {
                    if (contains && !this.K) {
                        invalidate();
                    } else if (!contains) {
                        a((Runnable) null);
                    }
                }
                if (!contains) {
                    a();
                    if (this.G != null) {
                        this.G.cancel();
                    }
                    if (this.E != null) {
                        this.E.onTouchEvent(motionEvent);
                    }
                    this.K = true;
                    break;
                }
                break;
            case 3:
                if (this.A) {
                    this.H.set(this.I.x, this.I.y);
                    this.I = new Point();
                }
                if (this.E != null) {
                    this.E.onTouchEvent(motionEvent);
                }
                if (this.s) {
                    if (!this.L) {
                        a((Runnable) null);
                    }
                } else if (this.E != null) {
                    this.E.setPressed(false);
                }
                a();
                break;
        }
        return true;
    }

    /* access modifiers changed from: private */
    public void a() {
        if (this.P != null) {
            removeCallbacks(this.P);
            this.L = false;
        }
    }

    /* access modifiers changed from: private */
    public void b() {
        if (!this.K) {
            if (this.G != null) {
                this.G.cancel();
            }
            this.G = ObjectAnimator.ofFloat(this, this.U, new float[]{(float) this.t, (float) (Math.sqrt(Math.pow((double) getWidth(), 2.0d) + Math.pow((double) getHeight(), 2.0d)) * 1.2000000476837158d)}).setDuration(n);
            this.G.setInterpolator(new LinearInterpolator());
            this.G.start();
        }
    }

    public void setExpandedColor(int i2) {
        this.S = i2;
        setBackgroundColor(i2);
    }

    /* access modifiers changed from: private */
    public void a(final Runnable runnable) {
        if (!this.K) {
            float endRadius = getEndRadius();
            c();
            setBackgroundColor(0);
            this.F = new AnimatorSet();
            this.F.addListener(new AnimatorListenerAdapter() {
                public void onAnimationEnd(Animator animator) {
                    if (!MaterialRippleLayout.this.y) {
                        MaterialRippleLayout.this.setRadius(0.0f);
                        MaterialRippleLayout.this.setRippleAlpha(Integer.valueOf(MaterialRippleLayout.this.v));
                    }
                    if (runnable != null && MaterialRippleLayout.this.w) {
                        runnable.run();
                    }
                    if (MaterialRippleLayout.this.E != null) {
                        MaterialRippleLayout.this.E.setPressed(false);
                    }
                }
            });
            Property<MaterialRippleLayout, Float> property = this.U;
            float[] fArr = new float[2];
            fArr[0] = this.T ? this.C : endRadius;
            fArr[1] = this.T ? endRadius : 0.0f;
            ObjectAnimator ofFloat = ObjectAnimator.ofFloat(this, property, fArr);
            ofFloat.setDuration((long) this.u);
            ofFloat.setInterpolator(new DecelerateInterpolator());
            ObjectAnimator ofInt = ObjectAnimator.ofInt(this, this.V, new int[]{this.v, 0});
            ofInt.setDuration((long) this.x);
            ofInt.setInterpolator(new AccelerateInterpolator());
            ofInt.setStartDelay((long) ((this.u - this.x) - 50));
            if (this.y) {
                this.F.play(ofFloat);
            } else if (getRadius() > endRadius) {
                ofInt.setStartDelay(0);
                this.F.play(ofInt);
            } else {
                this.F.playTogether(new Animator[]{ofFloat, ofInt});
            }
            this.F.start();
        }
    }

    private void a(Runnable runnable, boolean z2) {
        this.T = z2;
        a(runnable);
    }

    private void c() {
        if (this.F != null) {
            this.F.cancel();
            this.F.removeAllListeners();
        }
        if (this.G != null) {
            this.G.cancel();
        }
    }

    private float getEndRadius() {
        int width = getWidth();
        int height = getHeight();
        return ((float) Math.sqrt(Math.pow((double) ((float) (width / 2 > this.H.x ? width - this.H.x : this.H.x)), 2.0d) + Math.pow((double) ((float) (height / 2 > this.H.y ? height - this.H.y : this.H.y)), 2.0d))) * 1.2f;
    }

    private boolean d() {
        ViewParent parent = getParent();
        while (parent != null && (parent instanceof ViewGroup)) {
            if (((ViewGroup) parent).shouldDelayChildPressedState()) {
                return true;
            }
            parent = parent.getParent();
        }
        return false;
    }

    /* access modifiers changed from: private */
    public AdapterView e() {
        if (this.D != null) {
            return this.D;
        }
        ViewParent parent = getParent();
        while (!(parent instanceof AdapterView)) {
            try {
                parent = parent.getParent();
            } catch (NullPointerException unused) {
                throw new RuntimeException("Could not find a parent AdapterView");
            }
        }
        this.D = (AdapterView) parent;
        return this.D;
    }

    private void f() {
        if (this.A) {
            this.M = e().getPositionForView(this);
        }
    }

    private boolean g() {
        if (!this.A) {
            return false;
        }
        int positionForView = e().getPositionForView(this);
        boolean z2 = positionForView != this.M;
        this.M = positionForView;
        if (z2) {
            a();
            c();
            if (this.E != null) {
                this.E.setPressed(false);
            }
            setRadius(0.0f);
        }
        return z2;
    }

    private boolean a(View view, int i2, int i3) {
        if (view instanceof ViewGroup) {
            ViewGroup viewGroup = (ViewGroup) view;
            for (int i4 = 0; i4 < viewGroup.getChildCount(); i4++) {
                View childAt = viewGroup.getChildAt(i4);
                Rect rect = new Rect();
                childAt.getHitRect(rect);
                if (rect.contains(i2, i3)) {
                    return a(childAt, i2 - rect.left, i3 - rect.top);
                }
            }
        } else if (view != this.E) {
            if (!view.isEnabled() || (!view.isClickable() && !view.isLongClickable() && !view.isFocusableInTouchMode())) {
                return false;
            }
            return true;
        }
        if (view == null || !view.isFocusableInTouchMode()) {
            return false;
        }
        return true;
    }

    /* access modifiers changed from: protected */
    public void onSizeChanged(int i2, int i3, int i4, int i5) {
        super.onSizeChanged(i2, i3, i4, i5);
        this.p.set(0, 0, i2, i3);
        this.z.setBounds(this.p);
    }

    public void draw(Canvas canvas) {
        if (!this.aa) {
            super.draw(canvas);
            return;
        }
        if (this.W && this.mPath == null) {
            this.mPath = new Path();
            this.mPath.addCircle((float) (getWidth() / 2), (float) (getWidth() / 2), (float) (getWidth() / 2), Path.Direction.CW);
        }
        if (this.W) {
            canvas.clipPath(this.mPath, Region.Op.INTERSECT);
        } else {
            Log.d("hzd1", "");
        }
        boolean g2 = g();
        if (this.r) {
            if (!g2) {
                this.z.draw(canvas);
            }
            super.draw(canvas);
            if (!g2) {
                if (this.B != 0.0f) {
                    Path path = new Path();
                    path.addRoundRect(new RectF(0.0f, 0.0f, (float) canvas.getWidth(), (float) canvas.getHeight()), this.B, this.B, Path.Direction.CW);
                    canvas.clipPath(path);
                }
                canvas.drawCircle((float) this.H.x, (float) this.H.y, this.C, this.o);
            }
        } else {
            if (!g2) {
                this.z.draw(canvas);
                canvas.drawCircle((float) this.H.x, (float) this.H.y, this.C, this.o);
            }
            super.draw(canvas);
        }
        if (this.W) {
            Log.d("hzd1", "round clip radius=" + this.C + ",rippleOverlay=" + this.r);
            return;
        }
        Log.d("hzd1", "normal radius=" + this.C + ",rippleOverlay=" + this.r);
    }

    /* access modifiers changed from: private */
    public float getRadius() {
        return this.C;
    }

    public void setRadius(float f2) {
        this.C = f2;
        invalidate();
    }

    public int getRippleAlpha() {
        return this.o.getAlpha();
    }

    public void setRippleAlpha(Integer num) {
        this.o.setAlpha(num.intValue());
        invalidate();
    }

    public void setRippleColor(int i2) {
        this.q = i2;
        this.o.setColor(i2);
        this.o.setAlpha(this.v);
        invalidate();
    }

    public void setRippleOverlay(boolean z2) {
        this.r = z2;
    }

    public void setRippleDiameter(int i2) {
        this.t = i2;
    }

    public void setRippleDuration(int i2) {
        this.u = i2;
    }

    public void setRippleBackground(int i2) {
        this.z = new ColorDrawable(i2);
        this.z.setBounds(this.p);
        invalidate();
    }

    public void setRippleHover(boolean z2) {
        this.s = z2;
    }

    public void setRippleDelayClick(boolean z2) {
        this.w = z2;
    }

    public void setRippleFadeDuration(int i2) {
        this.x = i2;
    }

    public void setRipplePersistent(boolean z2) {
        this.y = z2;
    }

    public void setRippleInAdapter(boolean z2) {
        this.A = z2;
    }

    public void setRippleRoundedCorners(int i2) {
        this.B = (float) i2;
        h();
    }

    public void setDefaultRippleAlpha(float f2) {
        this.v = (int) (f2 * 255.0f);
        this.o.setAlpha(this.v);
        invalidate();
    }

    public void performRipple(boolean z2) {
        this.H = new Point(getWidth() / 2, getHeight() / 2);
        a((Runnable) null, z2);
    }

    public void performRipple(Point point, boolean z2) {
        this.H = new Point(point.x, point.y);
        a((Runnable) null, z2);
    }

    private void h() {
        if (Build.VERSION.SDK_INT > 17) {
            return;
        }
        if (this.B != 0.0f) {
            this.J = getLayerType();
            setLayerType(1, (Paint) null);
            return;
        }
        setLayerType(this.J, (Paint) null);
    }

    private class PerformClickEvent implements Runnable {
        private PerformClickEvent() {
        }

        public void run() {
            if (!MaterialRippleLayout.this.Q) {
                if (MaterialRippleLayout.this.getParent() instanceof AdapterView) {
                    if (MaterialRippleLayout.this.E != null && !MaterialRippleLayout.this.E.performClick()) {
                        a((AdapterView) MaterialRippleLayout.this.getParent());
                    }
                } else if (MaterialRippleLayout.this.A) {
                    a(MaterialRippleLayout.this.e());
                } else if (MaterialRippleLayout.this.E != null) {
                    MaterialRippleLayout.this.E.performClick();
                }
            }
        }

        private void a(AdapterView adapterView) {
            int positionForView = adapterView.getPositionForView(MaterialRippleLayout.this);
            long itemId = adapterView.getAdapter() != null ? adapterView.getAdapter().getItemId(positionForView) : 0;
            if (positionForView != -1) {
                adapterView.performItemClick(MaterialRippleLayout.this, positionForView, itemId);
            }
        }
    }

    private final class PressedEvent implements Runnable {
        private final MotionEvent b;

        public PressedEvent(MotionEvent motionEvent) {
            this.b = motionEvent;
        }

        public void run() {
            boolean unused = MaterialRippleLayout.this.L = false;
            if (MaterialRippleLayout.this.E != null) {
                MaterialRippleLayout.this.E.setLongClickable(false);
                MaterialRippleLayout.this.E.onTouchEvent(this.b);
                MaterialRippleLayout.this.E.setPressed(true);
            }
            if (MaterialRippleLayout.this.s) {
                MaterialRippleLayout.this.b();
            }
        }
    }

    static float dpToPx(Resources resources, float f2) {
        return TypedValue.applyDimension(1, f2, resources.getDisplayMetrics());
    }

    public static class RippleBuilder {

        /* renamed from: a  reason: collision with root package name */
        private final Context f18890a;
        private final View b;
        private int c = -16777216;
        private boolean d = false;
        private boolean e = true;
        private float f = 35.0f;
        private int g = MaterialRippleLayout.f18882a;
        private float h = 0.2f;
        private boolean i = true;
        private int j = 75;
        private boolean k = false;
        private int l = 0;
        private boolean m = false;
        private float n = 0.0f;

        public RippleBuilder(View view) {
            this.b = view;
            this.f18890a = view.getContext();
        }

        public RippleBuilder a(int i2) {
            this.c = i2;
            return this;
        }

        public RippleBuilder a(boolean z) {
            this.d = z;
            return this;
        }

        public RippleBuilder b(boolean z) {
            this.e = z;
            return this;
        }

        public RippleBuilder b(int i2) {
            this.f = (float) i2;
            return this;
        }

        public RippleBuilder c(int i2) {
            this.g = i2;
            return this;
        }

        public RippleBuilder a(float f2) {
            this.h = f2;
            return this;
        }

        public RippleBuilder c(boolean z) {
            this.i = z;
            return this;
        }

        public RippleBuilder d(int i2) {
            this.j = i2;
            return this;
        }

        public RippleBuilder d(boolean z) {
            this.k = z;
            return this;
        }

        public RippleBuilder e(int i2) {
            this.l = i2;
            return this;
        }

        public RippleBuilder e(boolean z) {
            this.m = z;
            return this;
        }

        public RippleBuilder f(int i2) {
            this.n = (float) i2;
            return this;
        }

        public MaterialRippleLayout a() {
            int i2;
            MaterialRippleLayout materialRippleLayout = new MaterialRippleLayout(this.f18890a);
            materialRippleLayout.setRippleColor(this.c);
            materialRippleLayout.setDefaultRippleAlpha(this.h);
            materialRippleLayout.setRippleDelayClick(this.i);
            materialRippleLayout.setRippleDiameter((int) MaterialRippleLayout.dpToPx(this.f18890a.getResources(), this.f));
            materialRippleLayout.setRippleDuration(this.g);
            materialRippleLayout.setRippleFadeDuration(this.j);
            materialRippleLayout.setRippleHover(this.e);
            materialRippleLayout.setRipplePersistent(this.k);
            materialRippleLayout.setRippleOverlay(this.d);
            materialRippleLayout.setRippleBackground(this.l);
            materialRippleLayout.setRippleInAdapter(this.m);
            materialRippleLayout.setRippleRoundedCorners((int) MaterialRippleLayout.dpToPx(this.f18890a.getResources(), this.n));
            ViewGroup.LayoutParams layoutParams = this.b.getLayoutParams();
            ViewGroup viewGroup = (ViewGroup) this.b.getParent();
            if (viewGroup == null || !(viewGroup instanceof MaterialRippleLayout)) {
                if (viewGroup != null) {
                    i2 = viewGroup.indexOfChild(this.b);
                    viewGroup.removeView(this.b);
                } else {
                    i2 = 0;
                }
                materialRippleLayout.addView(this.b, new ViewGroup.LayoutParams(-1, -1));
                if (viewGroup != null) {
                    viewGroup.addView(materialRippleLayout, i2, layoutParams);
                }
                return materialRippleLayout;
            }
            throw new IllegalStateException("MaterialRippleLayout could not be created: parent of the view already is a MaterialRippleLayout");
        }
    }

    public void setRoundClipEnabled(boolean z2) {
        this.W = z2;
    }

    public void setRippleAnim(boolean z2) {
        this.aa = z2;
    }
}
