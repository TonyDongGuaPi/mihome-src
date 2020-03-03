package com.flipboard.bottomsheet;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ObjectAnimator;
import android.animation.TimeInterpolator;
import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.Rect;
import android.support.annotation.NonNull;
import android.util.AttributeSet;
import android.util.Property;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.VelocityTracker;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.view.WindowManager;
import android.view.animation.DecelerateInterpolator;
import android.widget.FrameLayout;
import flipboard.bottomsheet.R;
import java.util.Iterator;
import java.util.concurrent.CopyOnWriteArraySet;

public class BottomSheetLayout extends FrameLayout {

    /* renamed from: a  reason: collision with root package name */
    private static final Property<BottomSheetLayout, Float> f5297a = new Property<BottomSheetLayout, Float>(Float.class, "sheetTranslation") {
        /* renamed from: a */
        public Float get(BottomSheetLayout bottomSheetLayout) {
            return Float.valueOf(bottomSheetLayout.i);
        }

        /* renamed from: a */
        public void set(BottomSheetLayout bottomSheetLayout, Float f) {
            bottomSheetLayout.setSheetTranslation(f.floatValue());
        }
    };
    private static final long c = 300;
    private int A;
    private final boolean B;
    private final int C;
    private int D;
    private int E;
    private float F;
    private float G;
    private float H;
    private State I;
    /* access modifiers changed from: private */
    public Runnable b;
    public boolean bottomSheetOwnsTouch;
    private Rect d;
    /* access modifiers changed from: private */
    public State e;
    private boolean f;
    private TimeInterpolator g;
    private boolean h;
    /* access modifiers changed from: private */
    public float i;
    private VelocityTracker j;
    private float k;
    private float l;
    private ViewTransformer m;
    /* access modifiers changed from: private */
    public ViewTransformer n;
    private boolean o;
    private boolean p;
    /* access modifiers changed from: private */
    public Animator q;
    /* access modifiers changed from: private */
    public CopyOnWriteArraySet<OnSheetDismissedListener> r;
    private CopyOnWriteArraySet<OnSheetStateChangeListener> s;
    private View.OnLayoutChangeListener t;
    private View u;
    private boolean v;
    /* access modifiers changed from: private */
    public int w;
    private boolean x;
    private float y;
    private float z;

    public interface OnSheetStateChangeListener {
        void a(State state);
    }

    public enum State {
        HIDDEN,
        PREPARING,
        PEEKED,
        EXPANDED
    }

    private static class CancelDetectionAnimationListener extends AnimatorListenerAdapter {
        protected boolean c;

        private CancelDetectionAnimationListener() {
        }

        public void onAnimationCancel(Animator animator) {
            this.c = true;
        }
    }

    private static class IdentityViewTransformer extends BaseViewTransformer {
        public void b(float f, float f2, float f3, BottomSheetLayout bottomSheetLayout, View view) {
        }

        private IdentityViewTransformer() {
        }
    }

    public BottomSheetLayout(Context context) {
        super(context);
        this.d = new Rect();
        this.e = State.HIDDEN;
        this.f = false;
        this.g = new DecelerateInterpolator(1.6f);
        this.m = new IdentityViewTransformer();
        this.o = true;
        this.p = true;
        this.r = new CopyOnWriteArraySet<>();
        this.s = new CopyOnWriteArraySet<>();
        this.v = true;
        this.A = 0;
        this.B = getResources().getBoolean(R.bool.bottomsheet_is_tablet);
        this.C = getResources().getDimensionPixelSize(R.dimen.bottomsheet_default_sheet_width);
        this.D = 0;
        this.E = 0;
        a();
    }

    public BottomSheetLayout(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public BottomSheetLayout(Context context, AttributeSet attributeSet, int i2) {
        super(context, attributeSet, i2);
        this.d = new Rect();
        this.e = State.HIDDEN;
        this.f = false;
        this.g = new DecelerateInterpolator(1.6f);
        this.m = new IdentityViewTransformer();
        this.o = true;
        this.p = true;
        this.r = new CopyOnWriteArraySet<>();
        this.s = new CopyOnWriteArraySet<>();
        this.v = true;
        this.A = 0;
        this.B = getResources().getBoolean(R.bool.bottomsheet_is_tablet);
        this.C = getResources().getDimensionPixelSize(R.dimen.bottomsheet_default_sheet_width);
        this.D = 0;
        this.E = 0;
        a();
    }

    @TargetApi(21)
    public BottomSheetLayout(Context context, AttributeSet attributeSet, int i2, int i3) {
        super(context, attributeSet, i2, i3);
        this.d = new Rect();
        this.e = State.HIDDEN;
        this.f = false;
        this.g = new DecelerateInterpolator(1.6f);
        this.m = new IdentityViewTransformer();
        this.o = true;
        this.p = true;
        this.r = new CopyOnWriteArraySet<>();
        this.s = new CopyOnWriteArraySet<>();
        this.v = true;
        this.A = 0;
        this.B = getResources().getBoolean(R.bool.bottomsheet_is_tablet);
        this.C = getResources().getDimensionPixelSize(R.dimen.bottomsheet_default_sheet_width);
        this.D = 0;
        this.E = 0;
        a();
    }

    private void a() {
        ViewConfiguration viewConfiguration = ViewConfiguration.get(getContext());
        this.k = (float) viewConfiguration.getScaledMinimumFlingVelocity();
        this.l = (float) viewConfiguration.getScaledTouchSlop();
        this.u = new View(getContext());
        this.u.setBackgroundColor(-16777216);
        this.u.setAlpha(0.0f);
        this.u.setVisibility(4);
        setFocusableInTouchMode(true);
        Point point = new Point();
        ((WindowManager) getContext().getSystemService("window")).getDefaultDisplay().getSize(point);
        this.A = point.x;
        this.E = this.A;
        this.z = 0.0f;
        this.y = ((float) point.y) - (((float) this.A) / 1.7777778f);
    }

    public void addView(@NonNull View view) {
        if (getChildCount() <= 0) {
            setContentView(view);
            return;
        }
        throw new IllegalArgumentException("You may not declare more then one child of bottom sheet. The sheet view must be added dynamically with showWithSheetView()");
    }

    public void addView(@NonNull View view, int i2) {
        addView(view);
    }

    public void addView(@NonNull View view, int i2, @NonNull ViewGroup.LayoutParams layoutParams) {
        addView(view);
    }

    public void addView(@NonNull View view, @NonNull ViewGroup.LayoutParams layoutParams) {
        addView(view);
    }

    public void addView(@NonNull View view, int i2, int i3) {
        addView(view);
    }

    /* access modifiers changed from: protected */
    public void onAttachedToWindow() {
        super.onAttachedToWindow();
        this.j = VelocityTracker.obtain();
    }

    /* access modifiers changed from: protected */
    public void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        this.j.clear();
        c();
    }

    /* access modifiers changed from: protected */
    public void onLayout(boolean z2, int i2, int i3, int i4, int i5) {
        super.onLayout(z2, i2, i3, i4, i5);
        double height = (double) getHeight();
        double ceil = Math.ceil((double) this.i);
        Double.isNaN(height);
        this.d.set(0, 0, getWidth(), (int) (height - ceil));
    }

    public boolean onKeyPreIme(int i2, @NonNull KeyEvent keyEvent) {
        if (i2 == 4 && isSheetShowing()) {
            if (keyEvent.getAction() == 0 && keyEvent.getRepeatCount() == 0) {
                KeyEvent.DispatcherState keyDispatcherState = getKeyDispatcherState();
                if (keyDispatcherState != null) {
                    keyDispatcherState.startTracking(keyEvent, this);
                }
                return true;
            } else if (keyEvent.getAction() == 1) {
                KeyEvent.DispatcherState keyDispatcherState2 = getKeyDispatcherState();
                if (keyDispatcherState2 != null) {
                    keyDispatcherState2.handleUpEvent(keyEvent);
                }
                if (isSheetShowing() && keyEvent.isTracking() && !keyEvent.isCanceled()) {
                    if (this.e != State.EXPANDED || !this.f) {
                        dismissSheet();
                    } else {
                        peekSheet();
                    }
                    return true;
                }
            }
        }
        return super.onKeyPreIme(i2, keyEvent);
    }

    /* access modifiers changed from: private */
    public void setSheetTranslation(float f2) {
        this.i = Math.min(f2, getMaxSheetTranslation());
        double height = (double) getHeight();
        double ceil = Math.ceil((double) this.i);
        Double.isNaN(height);
        int i2 = (int) (height - ceil);
        int i3 = 0;
        this.d.set(0, 0, getWidth(), i2);
        getSheetView().setTranslationY(((float) getHeight()) - this.i);
        a(this.i);
        if (this.o) {
            float b2 = b(this.i);
            this.u.setAlpha(b2);
            View view = this.u;
            if (b2 <= 0.0f) {
                i3 = 4;
            }
            view.setVisibility(i3);
        }
    }

    private void a(float f2) {
        if (this.n != null) {
            this.n.b(f2, getMaxSheetTranslation(), getPeekSheetTranslation(), this, getContentView());
        } else if (this.m != null) {
            this.m.b(f2, getMaxSheetTranslation(), getPeekSheetTranslation(), this, getContentView());
        }
    }

    private float b(float f2) {
        if (this.n != null) {
            return this.n.a(f2, getMaxSheetTranslation(), getPeekSheetTranslation(), this, getContentView());
        } else if (this.m == null) {
            return 0.0f;
        } else {
            return this.m.a(f2, getMaxSheetTranslation(), getPeekSheetTranslation(), this, getContentView());
        }
    }

    public boolean onInterceptTouchEvent(@NonNull MotionEvent motionEvent) {
        boolean z2 = true;
        boolean z3 = motionEvent.getActionMasked() == 0;
        if (z3) {
            this.x = false;
        }
        if (this.v || (motionEvent.getY() > ((float) getHeight()) - this.i && c(motionEvent.getX()))) {
            if (!z3 || !isSheetShowing()) {
                z2 = false;
            }
            this.x = z2;
        } else {
            this.x = false;
        }
        return this.x;
    }

    public boolean onTouchEvent(@NonNull MotionEvent motionEvent) {
        boolean z2 = false;
        if (!isSheetShowing() || b()) {
            return false;
        }
        if (!this.x) {
            return onInterceptTouchEvent(motionEvent);
        }
        if (motionEvent.getAction() == 0) {
            this.bottomSheetOwnsTouch = false;
            this.h = false;
            this.F = motionEvent.getY();
            this.G = motionEvent.getX();
            this.H = this.i;
            this.I = this.e;
            this.j.clear();
        }
        this.j.addMovement(motionEvent);
        float maxSheetTranslation = getMaxSheetTranslation();
        float peekSheetTranslation = getPeekSheetTranslation();
        float y2 = this.F - motionEvent.getY();
        float x2 = this.G - motionEvent.getX();
        float f2 = 0.0f;
        if (!this.bottomSheetOwnsTouch && !this.h) {
            this.bottomSheetOwnsTouch = Math.abs(y2) > this.l;
            this.h = Math.abs(x2) > this.l;
            if (this.bottomSheetOwnsTouch) {
                if (this.e == State.PEEKED) {
                    MotionEvent obtain = MotionEvent.obtain(motionEvent);
                    obtain.offsetLocation(0.0f, this.i - ((float) getHeight()));
                    obtain.setAction(3);
                    getSheetView().dispatchTouchEvent(obtain);
                    obtain.recycle();
                }
                this.h = false;
                this.F = motionEvent.getY();
                this.G = motionEvent.getX();
                y2 = 0.0f;
            }
        }
        float f3 = this.H + y2;
        if (this.bottomSheetOwnsTouch) {
            boolean z3 = y2 < 0.0f;
            boolean a2 = a(getSheetView(), motionEvent.getX(), motionEvent.getY() + (this.i - ((float) getHeight())));
            if (this.e == State.EXPANDED && z3 && !a2) {
                this.F = motionEvent.getY();
                this.H = this.i;
                this.j.clear();
                setState(State.PEEKED);
                setSheetLayerTypeIfEnabled(2);
                f3 = this.i;
                MotionEvent obtain2 = MotionEvent.obtain(motionEvent);
                obtain2.setAction(3);
                getSheetView().dispatchTouchEvent(obtain2);
                obtain2.recycle();
            }
            if (this.e == State.PEEKED && f3 > maxSheetTranslation) {
                setSheetTranslation(maxSheetTranslation);
                f3 = Math.min(maxSheetTranslation, f3);
                MotionEvent obtain3 = MotionEvent.obtain(motionEvent);
                obtain3.setAction(0);
                getSheetView().dispatchTouchEvent(obtain3);
                obtain3.recycle();
                setState(State.EXPANDED);
                setSheetLayerTypeIfEnabled(0);
            }
            if (this.e == State.EXPANDED) {
                motionEvent.offsetLocation(0.0f, this.i - ((float) getHeight()));
                getSheetView().dispatchTouchEvent(motionEvent);
            } else {
                if (f3 < peekSheetTranslation) {
                    f3 = peekSheetTranslation - ((peekSheetTranslation - f3) / 4.0f);
                }
                setSheetTranslation(f3);
                if (motionEvent.getAction() == 3) {
                    if (this.I == State.EXPANDED) {
                        expandSheet();
                    } else {
                        peekSheet();
                    }
                }
                if (motionEvent.getAction() == 1) {
                    if (f3 < peekSheetTranslation) {
                        dismissSheet();
                    } else {
                        this.j.computeCurrentVelocity(1000);
                        float yVelocity = this.j.getYVelocity();
                        if (Math.abs(yVelocity) < this.k) {
                            if (this.i > ((float) (getHeight() / 2))) {
                                expandSheet();
                            } else {
                                peekSheet();
                            }
                        } else if (yVelocity < 0.0f) {
                            expandSheet();
                        } else {
                            peekSheet();
                        }
                    }
                }
            }
        } else {
            if (motionEvent.getY() < ((float) getHeight()) - this.i || !c(motionEvent.getX())) {
                z2 = true;
            }
            if (motionEvent.getAction() != 1 || !z2 || !this.v) {
                if (this.B) {
                    f2 = getX() - ((float) this.D);
                }
                motionEvent.offsetLocation(f2, this.i - ((float) getHeight()));
                getSheetView().dispatchTouchEvent(motionEvent);
            } else {
                dismissSheet();
                return true;
            }
        }
        return true;
    }

    private boolean c(float f2) {
        return !this.B || (f2 >= ((float) this.D) && f2 <= ((float) this.E));
    }

    private boolean b() {
        return this.q != null;
    }

    private void c() {
        if (this.q != null) {
            this.q.cancel();
        }
    }

    private boolean a(View view, float f2, float f3) {
        if (view instanceof ViewGroup) {
            ViewGroup viewGroup = (ViewGroup) view;
            for (int i2 = 0; i2 < viewGroup.getChildCount(); i2++) {
                View childAt = viewGroup.getChildAt(i2);
                int left = childAt.getLeft() - view.getScrollX();
                int top = childAt.getTop() - view.getScrollY();
                float f4 = (float) left;
                if ((f2 > f4 && f2 < ((float) (childAt.getRight() - view.getScrollX())) && f3 > ((float) top) && f3 < ((float) (childAt.getBottom() - view.getScrollY()))) && a(childAt, f2 - f4, f3 - ((float) top))) {
                    return true;
                }
            }
        }
        return view.canScrollVertically(-1);
    }

    /* access modifiers changed from: private */
    public void setSheetLayerTypeIfEnabled(int i2) {
        if (this.p) {
            getSheetView().setLayerType(i2, (Paint) null);
        }
    }

    /* access modifiers changed from: private */
    public void setState(State state) {
        if (state != this.e) {
            this.e = state;
            Iterator<OnSheetStateChangeListener> it = this.s.iterator();
            while (it.hasNext()) {
                it.next().a(state);
            }
        }
    }

    private boolean d() {
        return getSheetView() == null || ((float) getSheetView().getHeight()) > this.y;
    }

    private boolean e() {
        return getSheetView() == null || getSheetView().getHeight() == getHeight();
    }

    private void f() {
        this.i = 0.0f;
        this.d.set(0, 0, getWidth(), getHeight());
        getSheetView().setTranslationY((float) getHeight());
        this.u.setAlpha(0.0f);
        this.u.setVisibility(4);
    }

    public void expandSheet() {
        c();
        setSheetLayerTypeIfEnabled(0);
        ObjectAnimator ofFloat = ObjectAnimator.ofFloat(this, f5297a, new float[]{getMaxSheetTranslation()});
        ofFloat.setDuration(c);
        ofFloat.setInterpolator(this.g);
        ofFloat.addListener(new CancelDetectionAnimationListener() {
            public void onAnimationEnd(@NonNull Animator animator) {
                if (!this.c) {
                    Animator unused = BottomSheetLayout.this.q = null;
                }
            }
        });
        ofFloat.start();
        this.q = ofFloat;
        setState(State.EXPANDED);
    }

    public void peekSheet() {
        c();
        setSheetLayerTypeIfEnabled(2);
        ObjectAnimator ofFloat = ObjectAnimator.ofFloat(this, f5297a, new float[]{getPeekSheetTranslation()});
        ofFloat.setDuration(c);
        ofFloat.setInterpolator(this.g);
        ofFloat.addListener(new CancelDetectionAnimationListener() {
            public void onAnimationEnd(@NonNull Animator animator) {
                if (!this.c) {
                    Animator unused = BottomSheetLayout.this.q = null;
                }
            }
        });
        ofFloat.start();
        this.q = ofFloat;
        setState(State.PEEKED);
    }

    public float getPeekSheetTranslation() {
        return this.z == 0.0f ? getDefaultPeekTranslation() : this.z;
    }

    private float getDefaultPeekTranslation() {
        return d() ? this.y : (float) getSheetView().getHeight();
    }

    public void setPeekSheetTranslation(float f2) {
        this.z = f2;
    }

    public float getMaxSheetTranslation() {
        return (float) (e() ? getHeight() - getPaddingTop() : getSheetView().getHeight());
    }

    public View getContentView() {
        if (getChildCount() > 0) {
            return getChildAt(0);
        }
        return null;
    }

    public View getSheetView() {
        if (getChildCount() > 2) {
            return getChildAt(2);
        }
        return null;
    }

    public void setContentView(View view) {
        super.addView(view, -1, generateDefaultLayoutParams());
        super.addView(this.u, -1, generateDefaultLayoutParams());
    }

    public void showWithSheetView(View view) {
        showWithSheetView(view, (ViewTransformer) null);
    }

    public void showWithSheetView(final View view, final ViewTransformer viewTransformer) {
        if (this.e != State.HIDDEN) {
            a((Runnable) new Runnable() {
                public void run() {
                    BottomSheetLayout.this.showWithSheetView(view, viewTransformer);
                }
            });
            return;
        }
        setState(State.PREPARING);
        FrameLayout.LayoutParams layoutParams = (FrameLayout.LayoutParams) view.getLayoutParams();
        if (layoutParams == null) {
            layoutParams = new FrameLayout.LayoutParams(this.B ? -2 : -1, -2, 1);
        }
        if (this.B && layoutParams.width == -2) {
            if (layoutParams.gravity == -1) {
                layoutParams.gravity = 1;
            }
            layoutParams.width = this.C;
            this.D = (this.A - this.C) / 2;
            this.E = this.A - this.D;
        }
        super.addView(view, -1, layoutParams);
        f();
        this.n = viewTransformer;
        getViewTreeObserver().addOnPreDrawListener(new ViewTreeObserver.OnPreDrawListener() {
            public boolean onPreDraw() {
                BottomSheetLayout.this.getViewTreeObserver().removeOnPreDrawListener(this);
                BottomSheetLayout.this.post(new Runnable() {
                    public void run() {
                        if (BottomSheetLayout.this.getSheetView() != null) {
                            BottomSheetLayout.this.peekSheet();
                        }
                    }
                });
                return true;
            }
        });
        this.w = view.getMeasuredHeight();
        this.t = new View.OnLayoutChangeListener() {
            public void onLayoutChange(View view, int i, int i2, int i3, int i4, int i5, int i6, int i7, int i8) {
                int measuredHeight = view.getMeasuredHeight();
                if (BottomSheetLayout.this.e != State.HIDDEN && measuredHeight < BottomSheetLayout.this.w) {
                    if (BottomSheetLayout.this.e == State.EXPANDED) {
                        BottomSheetLayout.this.setState(State.PEEKED);
                    }
                    BottomSheetLayout.this.setSheetTranslation((float) measuredHeight);
                }
                int unused = BottomSheetLayout.this.w = measuredHeight;
            }
        };
        view.addOnLayoutChangeListener(this.t);
    }

    public void dismissSheet() {
        a((Runnable) null);
    }

    private void a(Runnable runnable) {
        if (this.e == State.HIDDEN) {
            this.b = null;
            return;
        }
        this.b = runnable;
        final View sheetView = getSheetView();
        sheetView.removeOnLayoutChangeListener(this.t);
        c();
        ObjectAnimator ofFloat = ObjectAnimator.ofFloat(this, f5297a, new float[]{0.0f});
        ofFloat.setDuration(c);
        ofFloat.setInterpolator(this.g);
        ofFloat.addListener(new CancelDetectionAnimationListener() {
            public void onAnimationEnd(Animator animator) {
                if (!this.c) {
                    Animator unused = BottomSheetLayout.this.q = null;
                    BottomSheetLayout.this.setState(State.HIDDEN);
                    BottomSheetLayout.this.setSheetLayerTypeIfEnabled(0);
                    BottomSheetLayout.this.removeView(sheetView);
                    Iterator it = BottomSheetLayout.this.r.iterator();
                    while (it.hasNext()) {
                        ((OnSheetDismissedListener) it.next()).onDismissed(BottomSheetLayout.this);
                    }
                    ViewTransformer unused2 = BottomSheetLayout.this.n = null;
                    if (BottomSheetLayout.this.b != null) {
                        BottomSheetLayout.this.b.run();
                        Runnable unused3 = BottomSheetLayout.this.b = null;
                    }
                }
            }
        });
        ofFloat.start();
        this.q = ofFloat;
        this.D = 0;
        this.E = this.A;
    }

    public void setPeekOnDismiss(boolean z2) {
        this.f = z2;
    }

    public boolean getPeekOnDismiss() {
        return this.f;
    }

    public void setInterceptContentTouch(boolean z2) {
        this.v = z2;
    }

    public boolean getInterceptContentTouch() {
        return this.v;
    }

    public State getState() {
        return this.e;
    }

    public boolean isSheetShowing() {
        return this.e != State.HIDDEN;
    }

    public void setDefaultViewTransformer(ViewTransformer viewTransformer) {
        this.m = viewTransformer;
    }

    public void setShouldDimContentView(boolean z2) {
        this.o = z2;
    }

    public boolean shouldDimContentView() {
        return this.o;
    }

    public void setUseHardwareLayerWhileAnimating(boolean z2) {
        this.p = z2;
    }

    public void addOnSheetStateChangeListener(@NonNull OnSheetStateChangeListener onSheetStateChangeListener) {
        a(onSheetStateChangeListener, "onSheetStateChangeListener == null");
        this.s.add(onSheetStateChangeListener);
    }

    public void addOnSheetDismissedListener(@NonNull OnSheetDismissedListener onSheetDismissedListener) {
        a(onSheetDismissedListener, "onSheetDismissedListener == null");
        this.r.add(onSheetDismissedListener);
    }

    public void removeOnSheetStateChangeListener(@NonNull OnSheetStateChangeListener onSheetStateChangeListener) {
        a(onSheetStateChangeListener, "onSheetStateChangeListener == null");
        this.s.remove(onSheetStateChangeListener);
    }

    public void removeOnSheetDismissedListener(@NonNull OnSheetDismissedListener onSheetDismissedListener) {
        a(onSheetDismissedListener, "onSheetDismissedListener == null");
        this.r.remove(onSheetDismissedListener);
    }

    public static boolean isTablet(Context context) {
        return context.getResources().getBoolean(R.bool.bottomsheet_is_tablet);
    }

    public static int predictedDefaultWidth(Context context) {
        if (isTablet(context)) {
            return context.getResources().getDimensionPixelSize(R.dimen.bottomsheet_default_sheet_width);
        }
        return context.getResources().getDisplayMetrics().widthPixels;
    }

    private static <T> T a(T t2, String str) {
        if (t2 != null) {
            return t2;
        }
        throw new NullPointerException(str);
    }
}
