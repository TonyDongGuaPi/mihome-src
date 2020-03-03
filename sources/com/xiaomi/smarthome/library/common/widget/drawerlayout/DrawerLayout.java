package com.xiaomi.smarthome.library.common.widget.drawerlayout;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.SystemClock;
import android.support.annotation.ColorInt;
import android.support.annotation.DrawableRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.support.v4.graphics.drawable.DrawableCompat;
import android.support.v4.view.AccessibilityDelegateCompat;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewCompat;
import android.support.v4.view.ViewGroupCompat;
import android.support.v4.view.accessibility.AccessibilityNodeInfoCompat;
import android.util.AttributeSet;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.view.accessibility.AccessibilityEvent;
import com.xiaomi.smarthome.library.common.widget.drawerlayout.ViewDragHelper;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.ArrayList;
import java.util.List;

public class DrawerLayout extends ViewGroup implements DrawerLayoutImpl {
    static final DrawerLayoutCompatImpl IMPL;
    public static final int LOCK_MODE_LOCKED_CLOSED = 1;
    public static final int LOCK_MODE_LOCKED_OPEN = 2;
    public static final int LOCK_MODE_UNDEFINED = 3;
    public static final int LOCK_MODE_UNLOCKED = 0;
    public static final int STATE_DRAGGING = 1;
    public static final int STATE_IDLE = 0;
    public static final int STATE_SETTLING = 2;

    /* renamed from: a  reason: collision with root package name */
    private static final String f19012a = "DrawerLayout";
    private static final int b = 64;
    private static final int c = 10;
    private static final int d = -1728053248;
    private static final int e = 160;
    private static final int f = 400;
    private static final boolean g = false;
    private static final boolean h = true;
    private static final float i = 1.0f;
    /* access modifiers changed from: private */
    public static final int[] j = {16842931};
    /* access modifiers changed from: private */
    public static final boolean k = (Build.VERSION.SDK_INT >= 19);
    private static final boolean l;
    private int A;
    private int B;
    private int C;
    private boolean D;
    private boolean E;
    @Nullable
    @Deprecated
    private DrawerListener F;
    private List<DrawerListener> G;
    private float H;
    private float I;
    private Drawable J;
    private Drawable K;
    private Drawable L;
    private CharSequence M;
    private CharSequence N;
    private Object O;
    private boolean P;
    private Drawable Q;
    private Drawable R;
    private Drawable S;
    private Drawable T;
    private final ArrayList<View> U;
    private final ChildAccessibilityDelegate m;
    private float n;
    private int o;
    private int p;
    private float q;
    private Paint r;
    private final ViewDragHelper s;
    private final ViewDragHelper t;
    private final ViewDragCallback u;
    private final ViewDragCallback v;
    private int w;
    private boolean x;
    private boolean y;
    private int z;

    @Retention(RetentionPolicy.SOURCE)
    private @interface EdgeGravity {
    }

    @Retention(RetentionPolicy.SOURCE)
    private @interface LockMode {
    }

    @Retention(RetentionPolicy.SOURCE)
    private @interface State {
    }

    interface DrawerLayoutCompatImpl {
        int a(Object obj);

        Drawable a(Context context);

        void a(View view);

        void a(View view, Object obj, int i);

        void a(ViewGroup.MarginLayoutParams marginLayoutParams, Object obj, int i);
    }

    public interface DrawerListener {
        void a(int i);

        void a(View view);

        void a(View view, float f);

        void b(View view);
    }

    public static abstract class SimpleDrawerListener implements DrawerListener {
        public void a(int i) {
        }

        public void a(View view) {
        }

        public void a(View view, float f) {
        }

        public void b(View view) {
        }
    }

    static {
        boolean z2 = true;
        if (Build.VERSION.SDK_INT < 21) {
            z2 = false;
        }
        l = z2;
        if (Build.VERSION.SDK_INT >= 21) {
            IMPL = new DrawerLayoutCompatImplApi21();
        } else {
            IMPL = new DrawerLayoutCompatImplBase();
        }
    }

    private Paint getScrimPaint() {
        if (this.r == null) {
            this.r = new Paint();
        }
        return this.r;
    }

    static class DrawerLayoutCompatImplBase implements DrawerLayoutCompatImpl {
        public int a(Object obj) {
            return 0;
        }

        public Drawable a(Context context) {
            return null;
        }

        public void a(View view) {
        }

        public void a(View view, Object obj, int i) {
        }

        public void a(ViewGroup.MarginLayoutParams marginLayoutParams, Object obj, int i) {
        }

        DrawerLayoutCompatImplBase() {
        }
    }

    static class DrawerLayoutCompatImplApi21 implements DrawerLayoutCompatImpl {
        DrawerLayoutCompatImplApi21() {
        }

        public void a(View view) {
            DrawerLayoutCompatApi21.a(view);
        }

        public void a(View view, Object obj, int i) {
            DrawerLayoutCompatApi21.a(view, obj, i);
        }

        public void a(ViewGroup.MarginLayoutParams marginLayoutParams, Object obj, int i) {
            DrawerLayoutCompatApi21.a(marginLayoutParams, obj, i);
        }

        public int a(Object obj) {
            return DrawerLayoutCompatApi21.a(obj);
        }

        public Drawable a(Context context) {
            return DrawerLayoutCompatApi21.a(context);
        }
    }

    public DrawerLayout(Context context) {
        this(context, (AttributeSet) null);
    }

    public DrawerLayout(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public DrawerLayout(Context context, AttributeSet attributeSet, int i2) {
        super(context, attributeSet, i2);
        this.m = new ChildAccessibilityDelegate();
        this.p = -1728053248;
        this.y = true;
        this.z = 3;
        this.A = 3;
        this.B = 3;
        this.C = 3;
        this.Q = null;
        this.R = null;
        this.S = null;
        this.T = null;
        setDescendantFocusability(262144);
        float f2 = getResources().getDisplayMetrics().density;
        this.o = (int) ((64.0f * f2) + 0.5f);
        float f3 = 400.0f * f2;
        this.u = new ViewDragCallback(3);
        this.v = new ViewDragCallback(5);
        this.s = ViewDragHelper.a((ViewGroup) this, 1.0f, (ViewDragHelper.Callback) this.u);
        this.s.a(1);
        this.s.a(f3);
        this.u.a(this.s);
        this.t = ViewDragHelper.a((ViewGroup) this, 1.0f, (ViewDragHelper.Callback) this.v);
        this.t.a(2);
        this.t.a(f3);
        this.v.a(this.t);
        setFocusableInTouchMode(true);
        ViewCompat.setImportantForAccessibility(this, 1);
        ViewCompat.setAccessibilityDelegate(this, new AccessibilityDelegate());
        ViewGroupCompat.setMotionEventSplittingEnabled(this, false);
        if (ViewCompat.getFitsSystemWindows(this)) {
            IMPL.a((View) this);
            this.J = IMPL.a(context);
        }
        this.n = f2 * 10.0f;
        this.U = new ArrayList<>();
    }

    public void setDrawerElevation(float f2) {
        this.n = f2;
        for (int i2 = 0; i2 < getChildCount(); i2++) {
            View childAt = getChildAt(i2);
            if (isDrawerView(childAt)) {
                ViewCompat.setElevation(childAt, this.n);
            }
        }
    }

    public float getDrawerElevation() {
        if (l) {
            return this.n;
        }
        return 0.0f;
    }

    public void setChildInsets(Object obj, boolean z2) {
        this.O = obj;
        this.P = z2;
        setWillNotDraw(!z2 && getBackground() == null);
        requestLayout();
    }

    public void setDrawerShadow(Drawable drawable, int i2) {
        if (!l) {
            if ((i2 & GravityCompat.START) == 8388611) {
                this.Q = drawable;
            } else if ((i2 & GravityCompat.END) == 8388613) {
                this.R = drawable;
            } else if ((i2 & 3) == 3) {
                this.S = drawable;
            } else if ((i2 & 5) == 5) {
                this.T = drawable;
            } else {
                return;
            }
            a();
            invalidate();
        }
    }

    public void setDrawerShadow(@DrawableRes int i2, int i3) {
        setDrawerShadow(getResources().getDrawable(i2), i3);
    }

    public void setScrimColor(@ColorInt int i2) {
        this.p = i2;
        invalidate();
    }

    @Deprecated
    public void setDrawerListener(DrawerListener drawerListener) {
        if (this.F != null) {
            removeDrawerListener(this.F);
        }
        if (drawerListener != null) {
            addDrawerListener(drawerListener);
        }
        this.F = drawerListener;
    }

    public void addDrawerListener(@NonNull DrawerListener drawerListener) {
        if (drawerListener != null) {
            if (this.G == null) {
                this.G = new ArrayList();
            }
            this.G.add(drawerListener);
        }
    }

    public void removeDrawerListener(@NonNull DrawerListener drawerListener) {
        if (drawerListener != null && this.G != null) {
            this.G.remove(drawerListener);
        }
    }

    public void setDrawerLockMode(int i2) {
        setDrawerLockMode(i2, 3);
        setDrawerLockMode(i2, 5);
    }

    public void setDrawerLockMode(int i2, int i3) {
        int absoluteGravity = GravityCompat.getAbsoluteGravity(i3, ViewCompat.getLayoutDirection(this));
        if (i3 == 3) {
            this.z = i2;
        } else if (i3 == 5) {
            this.A = i2;
        } else if (i3 == 8388611) {
            this.B = i2;
        } else if (i3 == 8388613) {
            this.C = i2;
        }
        if (i2 != 0) {
            (absoluteGravity == 3 ? this.s : this.t).g();
        }
        switch (i2) {
            case 1:
                View findDrawerWithGravity = findDrawerWithGravity(absoluteGravity);
                if (findDrawerWithGravity != null) {
                    closeDrawer(findDrawerWithGravity);
                    return;
                }
                return;
            case 2:
                View findDrawerWithGravity2 = findDrawerWithGravity(absoluteGravity);
                if (findDrawerWithGravity2 != null) {
                    openDrawer(findDrawerWithGravity2);
                    return;
                }
                return;
            default:
                return;
        }
    }

    public void setDrawerLockMode(int i2, View view) {
        if (isDrawerView(view)) {
            setDrawerLockMode(i2, ((LayoutParams) view.getLayoutParams()).f19015a);
            return;
        }
        throw new IllegalArgumentException("View " + view + " is not a drawer with appropriate layout_gravity");
    }

    public int getDrawerLockMode(int i2) {
        int i3;
        int i4;
        int i5;
        int layoutDirection = ViewCompat.getLayoutDirection(this);
        if (i2 != 3) {
            if (i2 != 5) {
                if (i2 != 8388611) {
                    if (i2 != 8388613) {
                        return 0;
                    }
                    if (this.C != 3) {
                        return this.C;
                    }
                    if (layoutDirection == 0) {
                        i5 = this.A;
                    } else {
                        i5 = this.z;
                    }
                    if (i5 != 3) {
                        return i5;
                    }
                    return 0;
                } else if (this.B != 3) {
                    return this.B;
                } else {
                    if (layoutDirection == 0) {
                        i4 = this.z;
                    } else {
                        i4 = this.A;
                    }
                    if (i4 != 3) {
                        return i4;
                    }
                    return 0;
                }
            } else if (this.A != 3) {
                return this.A;
            } else {
                if (layoutDirection == 0) {
                    i3 = this.C;
                } else {
                    i3 = this.B;
                }
                if (i3 != 3) {
                    return i3;
                }
                return 0;
            }
        } else if (this.z != 3) {
            return this.z;
        } else {
            int i6 = layoutDirection == 0 ? this.B : this.C;
            if (i6 != 3) {
                return i6;
            }
            return 0;
        }
    }

    public int getDrawerLockMode(View view) {
        if (isDrawerView(view)) {
            return getDrawerLockMode(((LayoutParams) view.getLayoutParams()).f19015a);
        }
        throw new IllegalArgumentException("View " + view + " is not a drawer");
    }

    public void setDrawerTitle(int i2, CharSequence charSequence) {
        int absoluteGravity = GravityCompat.getAbsoluteGravity(i2, ViewCompat.getLayoutDirection(this));
        if (absoluteGravity == 3) {
            this.M = charSequence;
        } else if (absoluteGravity == 5) {
            this.N = charSequence;
        }
    }

    @Nullable
    public CharSequence getDrawerTitle(int i2) {
        int absoluteGravity = GravityCompat.getAbsoluteGravity(i2, ViewCompat.getLayoutDirection(this));
        if (absoluteGravity == 3) {
            return this.M;
        }
        if (absoluteGravity == 5) {
            return this.N;
        }
        return null;
    }

    /* access modifiers changed from: package-private */
    public void updateDrawerState(int i2, int i3, View view) {
        int b2 = this.s.b();
        int b3 = this.t.b();
        int i4 = 2;
        if (b2 == 1 || b3 == 1) {
            i4 = 1;
        } else if (!(b2 == 2 || b3 == 2)) {
            i4 = 0;
        }
        if (view != null && i3 == 0) {
            LayoutParams layoutParams = (LayoutParams) view.getLayoutParams();
            if (layoutParams.e == 0.0f) {
                dispatchOnDrawerClosed(view);
            } else if (layoutParams.e == 1.0f) {
                dispatchOnDrawerOpened(view);
            }
        }
        if (i4 != this.w) {
            this.w = i4;
            if (this.G != null) {
                for (int size = this.G.size() - 1; size >= 0; size--) {
                    this.G.get(size).a(i4);
                }
            }
        }
    }

    /* access modifiers changed from: package-private */
    public void dispatchOnDrawerClosed(View view) {
        View rootView;
        LayoutParams layoutParams = (LayoutParams) view.getLayoutParams();
        if ((layoutParams.g & 1) == 1) {
            int unused = layoutParams.g = 0;
            if (this.G != null) {
                for (int size = this.G.size() - 1; size >= 0; size--) {
                    this.G.get(size).b(view);
                }
            }
            a(view, false);
            if (hasWindowFocus() && (rootView = getRootView()) != null) {
                rootView.sendAccessibilityEvent(32);
            }
        }
    }

    /* access modifiers changed from: package-private */
    public void dispatchOnDrawerOpened(View view) {
        LayoutParams layoutParams = (LayoutParams) view.getLayoutParams();
        if ((layoutParams.g & 1) == 0) {
            int unused = layoutParams.g = 1;
            if (this.G != null) {
                for (int size = this.G.size() - 1; size >= 0; size--) {
                    this.G.get(size).a(view);
                }
            }
            a(view, true);
            if (hasWindowFocus()) {
                sendAccessibilityEvent(32);
            }
            view.requestFocus();
        }
    }

    private void a(View view, boolean z2) {
        int childCount = getChildCount();
        for (int i2 = 0; i2 < childCount; i2++) {
            View childAt = getChildAt(i2);
            if ((z2 || isDrawerView(childAt)) && (!z2 || childAt != view)) {
                ViewCompat.setImportantForAccessibility(childAt, 4);
            } else {
                ViewCompat.setImportantForAccessibility(childAt, 1);
            }
        }
    }

    /* access modifiers changed from: package-private */
    public void dispatchOnDrawerSlide(View view, float f2) {
        if (this.G != null) {
            for (int size = this.G.size() - 1; size >= 0; size--) {
                this.G.get(size).a(view, f2);
            }
        }
    }

    /* access modifiers changed from: package-private */
    public void setDrawerViewOffset(View view, float f2) {
        LayoutParams layoutParams = (LayoutParams) view.getLayoutParams();
        if (f2 != layoutParams.e) {
            float unused = layoutParams.e = f2;
            dispatchOnDrawerSlide(view, f2);
        }
    }

    /* access modifiers changed from: package-private */
    public float getDrawerViewOffset(View view) {
        return ((LayoutParams) view.getLayoutParams()).e;
    }

    /* access modifiers changed from: package-private */
    public int getDrawerViewAbsoluteGravity(View view) {
        return GravityCompat.getAbsoluteGravity(((LayoutParams) view.getLayoutParams()).f19015a, ViewCompat.getLayoutDirection(this));
    }

    /* access modifiers changed from: package-private */
    public boolean checkDrawerViewAbsoluteGravity(View view, int i2) {
        return (getDrawerViewAbsoluteGravity(view) & i2) == i2;
    }

    /* access modifiers changed from: package-private */
    public View findOpenDrawer() {
        int childCount = getChildCount();
        for (int i2 = 0; i2 < childCount; i2++) {
            View childAt = getChildAt(i2);
            if ((((LayoutParams) childAt.getLayoutParams()).g & 1) == 1) {
                return childAt;
            }
        }
        return null;
    }

    /* access modifiers changed from: package-private */
    public void moveDrawerToOffset(View view, float f2) {
        float drawerViewOffset = getDrawerViewOffset(view);
        float width = (float) view.getWidth();
        int i2 = ((int) (width * f2)) - ((int) (drawerViewOffset * width));
        if (!checkDrawerViewAbsoluteGravity(view, 3)) {
            i2 = -i2;
        }
        view.offsetLeftAndRight(i2);
        setDrawerViewOffset(view, f2);
    }

    /* access modifiers changed from: package-private */
    public View findDrawerWithGravity(int i2) {
        int absoluteGravity = GravityCompat.getAbsoluteGravity(i2, ViewCompat.getLayoutDirection(this)) & 7;
        int childCount = getChildCount();
        for (int i3 = 0; i3 < childCount; i3++) {
            View childAt = getChildAt(i3);
            if ((getDrawerViewAbsoluteGravity(childAt) & 7) == absoluteGravity) {
                return childAt;
            }
        }
        return null;
    }

    static String gravityToString(int i2) {
        if ((i2 & 3) == 3) {
            return "LEFT";
        }
        return (i2 & 5) == 5 ? "RIGHT" : Integer.toHexString(i2);
    }

    /* access modifiers changed from: protected */
    public void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        this.y = true;
    }

    /* access modifiers changed from: protected */
    public void onAttachedToWindow() {
        super.onAttachedToWindow();
        this.y = true;
    }

    /* access modifiers changed from: protected */
    public void onMeasure(int i2, int i3) {
        int mode = View.MeasureSpec.getMode(i2);
        int mode2 = View.MeasureSpec.getMode(i3);
        int size = View.MeasureSpec.getSize(i2);
        int size2 = View.MeasureSpec.getSize(i3);
        if (!(mode == 1073741824 && mode2 == 1073741824)) {
            if (isInEditMode()) {
                if (mode != Integer.MIN_VALUE && mode == 0) {
                    size = 300;
                }
                if (mode2 != Integer.MIN_VALUE && mode2 == 0) {
                    size2 = 300;
                }
            } else {
                throw new IllegalArgumentException("DrawerLayout must be measured with MeasureSpec.EXACTLY.");
            }
        }
        setMeasuredDimension(size, size2);
        boolean z2 = this.O != null && ViewCompat.getFitsSystemWindows(this);
        int layoutDirection = ViewCompat.getLayoutDirection(this);
        int childCount = getChildCount();
        boolean z3 = false;
        boolean z4 = false;
        for (int i4 = 0; i4 < childCount; i4++) {
            View childAt = getChildAt(i4);
            if (childAt.getVisibility() != 8) {
                LayoutParams layoutParams = (LayoutParams) childAt.getLayoutParams();
                if (z2) {
                    int absoluteGravity = GravityCompat.getAbsoluteGravity(layoutParams.f19015a, layoutDirection);
                    if (ViewCompat.getFitsSystemWindows(childAt)) {
                        IMPL.a(childAt, this.O, absoluteGravity);
                    } else {
                        IMPL.a((ViewGroup.MarginLayoutParams) layoutParams, this.O, absoluteGravity);
                    }
                }
                if (isContentView(childAt)) {
                    childAt.measure(View.MeasureSpec.makeMeasureSpec((size - layoutParams.leftMargin) - layoutParams.rightMargin, 1073741824), View.MeasureSpec.makeMeasureSpec((size2 - layoutParams.topMargin) - layoutParams.bottomMargin, 1073741824));
                } else if (isDrawerView(childAt)) {
                    if (l && ViewCompat.getElevation(childAt) != this.n) {
                        ViewCompat.setElevation(childAt, this.n);
                    }
                    int drawerViewAbsoluteGravity = getDrawerViewAbsoluteGravity(childAt) & 7;
                    boolean z5 = drawerViewAbsoluteGravity == 3;
                    if ((!z5 || !z3) && (z5 || !z4)) {
                        if (z5) {
                            z3 = true;
                        } else {
                            z4 = true;
                        }
                        childAt.measure(getChildMeasureSpec(i2, this.o + layoutParams.leftMargin + layoutParams.rightMargin, layoutParams.width), getChildMeasureSpec(i3, layoutParams.topMargin + layoutParams.bottomMargin, layoutParams.height));
                    } else {
                        throw new IllegalStateException("Child drawer has absolute gravity " + gravityToString(drawerViewAbsoluteGravity) + " but this " + f19012a + " already has a drawer view along that edge");
                    }
                } else {
                    throw new IllegalStateException("Child " + childAt + " at index " + i4 + " does not have a valid layout_gravity - must be Gravity.LEFT, Gravity.RIGHT or Gravity.NO_GRAVITY");
                }
            }
            int i5 = i2;
            int i6 = i3;
        }
    }

    private void a() {
        if (!l) {
            this.K = b();
            this.L = c();
        }
    }

    private Drawable b() {
        int layoutDirection = ViewCompat.getLayoutDirection(this);
        if (layoutDirection == 0) {
            if (this.Q != null) {
                a(this.Q, layoutDirection);
                return this.Q;
            }
        } else if (this.R != null) {
            a(this.R, layoutDirection);
            return this.R;
        }
        return this.S;
    }

    private Drawable c() {
        int layoutDirection = ViewCompat.getLayoutDirection(this);
        if (layoutDirection == 0) {
            if (this.R != null) {
                a(this.R, layoutDirection);
                return this.R;
            }
        } else if (this.Q != null) {
            a(this.Q, layoutDirection);
            return this.Q;
        }
        return this.T;
    }

    private boolean a(Drawable drawable, int i2) {
        if (drawable == null || !DrawableCompat.isAutoMirrored(drawable)) {
            return false;
        }
        DrawableCompat.setLayoutDirection(drawable, i2);
        return true;
    }

    /* access modifiers changed from: protected */
    public void onLayout(boolean z2, int i2, int i3, int i4, int i5) {
        float f2;
        int i6;
        this.x = true;
        int i7 = i4 - i2;
        int childCount = getChildCount();
        for (int i8 = 0; i8 < childCount; i8++) {
            View childAt = getChildAt(i8);
            if (childAt.getVisibility() != 8) {
                LayoutParams layoutParams = (LayoutParams) childAt.getLayoutParams();
                if (isContentView(childAt)) {
                    childAt.layout(layoutParams.leftMargin, layoutParams.topMargin, layoutParams.leftMargin + childAt.getMeasuredWidth(), layoutParams.topMargin + childAt.getMeasuredHeight());
                } else {
                    int measuredWidth = childAt.getMeasuredWidth();
                    int measuredHeight = childAt.getMeasuredHeight();
                    if (checkDrawerViewAbsoluteGravity(childAt, 3)) {
                        float f3 = (float) measuredWidth;
                        i6 = (-measuredWidth) + ((int) (layoutParams.e * f3));
                        f2 = ((float) (measuredWidth + i6)) / f3;
                    } else {
                        float f4 = (float) measuredWidth;
                        int a2 = i7 - ((int) (layoutParams.e * f4));
                        f2 = ((float) (i7 - a2)) / f4;
                        i6 = a2;
                    }
                    boolean z3 = f2 != layoutParams.e;
                    int i9 = layoutParams.f19015a & 112;
                    if (i9 == 16) {
                        int i10 = i5 - i3;
                        int i11 = (i10 - measuredHeight) / 2;
                        if (i11 < layoutParams.topMargin) {
                            i11 = layoutParams.topMargin;
                        } else if (i11 + measuredHeight > i10 - layoutParams.bottomMargin) {
                            i11 = (i10 - layoutParams.bottomMargin) - measuredHeight;
                        }
                        childAt.layout(i6, i11, measuredWidth + i6, measuredHeight + i11);
                    } else if (i9 != 80) {
                        childAt.layout(i6, layoutParams.topMargin, measuredWidth + i6, layoutParams.topMargin + measuredHeight);
                    } else {
                        int i12 = i5 - i3;
                        childAt.layout(i6, (i12 - layoutParams.bottomMargin) - childAt.getMeasuredHeight(), measuredWidth + i6, i12 - layoutParams.bottomMargin);
                    }
                    if (z3) {
                        setDrawerViewOffset(childAt, f2);
                    }
                    int i13 = layoutParams.e > 0.0f ? 0 : 4;
                    if (childAt.getVisibility() != i13) {
                        childAt.setVisibility(i13);
                    }
                }
            }
        }
        this.x = false;
        this.y = false;
    }

    public void requestLayout() {
        if (!this.x) {
            super.requestLayout();
        }
    }

    public void computeScroll() {
        int childCount = getChildCount();
        float f2 = 0.0f;
        for (int i2 = 0; i2 < childCount; i2++) {
            f2 = Math.max(f2, ((LayoutParams) getChildAt(i2).getLayoutParams()).e);
        }
        this.q = f2;
        if (this.s.a(true) || this.t.a(true)) {
            ViewCompat.postInvalidateOnAnimation(this);
        }
    }

    private static boolean a(View view) {
        Drawable background = view.getBackground();
        if (background == null || background.getOpacity() != -1) {
            return false;
        }
        return true;
    }

    public void setStatusBarBackground(Drawable drawable) {
        this.J = drawable;
        invalidate();
    }

    public Drawable getStatusBarBackgroundDrawable() {
        return this.J;
    }

    public void setStatusBarBackground(int i2) {
        this.J = i2 != 0 ? ContextCompat.getDrawable(getContext(), i2) : null;
        invalidate();
    }

    public void setStatusBarBackgroundColor(@ColorInt int i2) {
        this.J = new ColorDrawable(i2);
        invalidate();
    }

    public void onRtlPropertiesChanged(int i2) {
        a();
    }

    public void onDraw(Canvas canvas) {
        int a2;
        super.onDraw(canvas);
        if (this.P && this.J != null && (a2 = IMPL.a(this.O)) > 0) {
            this.J.setBounds(0, 0, getWidth(), a2);
            this.J.draw(canvas);
        }
    }

    /* access modifiers changed from: protected */
    public boolean drawChild(Canvas canvas, View view, long j2) {
        int i2;
        Canvas canvas2 = canvas;
        View view2 = view;
        int height = getHeight();
        boolean isContentView = isContentView(view2);
        int width = getWidth();
        int save = canvas.save();
        int i3 = 0;
        if (isContentView) {
            int childCount = getChildCount();
            i2 = width;
            int i4 = 0;
            for (int i5 = 0; i5 < childCount; i5++) {
                View childAt = getChildAt(i5);
                if (childAt != view2 && childAt.getVisibility() == 0 && a(childAt) && isDrawerView(childAt) && childAt.getHeight() >= height) {
                    if (checkDrawerViewAbsoluteGravity(childAt, 3)) {
                        int right = childAt.getRight();
                        if (right > i4) {
                            i4 = right;
                        }
                    } else {
                        int left = childAt.getLeft();
                        if (left < i2) {
                            i2 = left;
                        }
                    }
                }
            }
            canvas.clipRect(i4, 0, i2, getHeight());
            i3 = i4;
        } else {
            i2 = width;
        }
        boolean drawChild = super.drawChild(canvas, view, j2);
        canvas.restoreToCount(save);
        if (this.q > 0.0f && isContentView) {
            getScrimPaint().setColor((((int) (((float) ((this.p & -16777216) >>> 24)) * this.q)) << 24) | (this.p & 16777215));
            canvas.drawRect((float) i3, 0.0f, (float) i2, (float) getHeight(), getScrimPaint());
        } else if (this.K != null && checkDrawerViewAbsoluteGravity(view2, 3)) {
            int intrinsicWidth = this.K.getIntrinsicWidth();
            int right2 = view.getRight();
            float max = Math.max(0.0f, Math.min(((float) right2) / ((float) this.s.c()), 1.0f));
            this.K.setBounds(right2, view.getTop(), intrinsicWidth + right2, view.getBottom());
            this.K.setAlpha((int) (max * 255.0f));
            this.K.draw(canvas);
        } else if (this.L != null && checkDrawerViewAbsoluteGravity(view2, 5)) {
            int intrinsicWidth2 = this.L.getIntrinsicWidth();
            int left2 = view.getLeft();
            float max2 = Math.max(0.0f, Math.min(((float) (getWidth() - left2)) / ((float) this.t.c()), 1.0f));
            this.L.setBounds(left2 - intrinsicWidth2, view.getTop(), left2, view.getBottom());
            this.L.setAlpha((int) (max2 * 255.0f));
            this.L.draw(canvas);
        }
        return drawChild;
    }

    /* access modifiers changed from: package-private */
    public boolean isContentView(View view) {
        return ((LayoutParams) view.getLayoutParams()).f19015a == 0;
    }

    /* access modifiers changed from: package-private */
    public boolean isDrawerView(View view) {
        int absoluteGravity = GravityCompat.getAbsoluteGravity(((LayoutParams) view.getLayoutParams()).f19015a, ViewCompat.getLayoutDirection(view));
        return ((absoluteGravity & 3) == 0 && (absoluteGravity & 5) == 0) ? false : true;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:8:0x0046, code lost:
        r7 = r6.s.e((int) r0, (int) r7);
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public boolean onInterceptTouchEvent(android.view.MotionEvent r7) {
        /*
            r6 = this;
            int r0 = android.support.v4.view.MotionEventCompat.getActionMasked(r7)
            com.xiaomi.smarthome.library.common.widget.drawerlayout.ViewDragHelper r1 = r6.s
            boolean r1 = r1.a((android.view.MotionEvent) r7)
            com.xiaomi.smarthome.library.common.widget.drawerlayout.ViewDragHelper r2 = r6.t
            boolean r2 = r2.a((android.view.MotionEvent) r7)
            r1 = r1 | r2
            r2 = 1
            r3 = 0
            switch(r0) {
                case 0: goto L_0x0033;
                case 1: goto L_0x002b;
                case 2: goto L_0x0017;
                case 3: goto L_0x002b;
                default: goto L_0x0016;
            }
        L_0x0016:
            goto L_0x005e
        L_0x0017:
            com.xiaomi.smarthome.library.common.widget.drawerlayout.ViewDragHelper r7 = r6.s
            r0 = 3
            boolean r7 = r7.d(r0)
            if (r7 == 0) goto L_0x005e
            com.xiaomi.smarthome.library.common.widget.drawerlayout.DrawerLayout$ViewDragCallback r7 = r6.u
            r7.a()
            com.xiaomi.smarthome.library.common.widget.drawerlayout.DrawerLayout$ViewDragCallback r7 = r6.v
            r7.a()
            goto L_0x005e
        L_0x002b:
            r6.closeDrawers(r2)
            r6.D = r3
            r6.E = r3
            goto L_0x005e
        L_0x0033:
            float r0 = r7.getX()
            float r7 = r7.getY()
            r6.H = r0
            r6.I = r7
            float r4 = r6.q
            r5 = 0
            int r4 = (r4 > r5 ? 1 : (r4 == r5 ? 0 : -1))
            if (r4 <= 0) goto L_0x0058
            com.xiaomi.smarthome.library.common.widget.drawerlayout.ViewDragHelper r4 = r6.s
            int r0 = (int) r0
            int r7 = (int) r7
            android.view.View r7 = r4.e(r0, r7)
            if (r7 == 0) goto L_0x0058
            boolean r7 = r6.isContentView(r7)
            if (r7 == 0) goto L_0x0058
            r7 = 1
            goto L_0x0059
        L_0x0058:
            r7 = 0
        L_0x0059:
            r6.D = r3
            r6.E = r3
            goto L_0x005f
        L_0x005e:
            r7 = 0
        L_0x005f:
            if (r1 != 0) goto L_0x006f
            if (r7 != 0) goto L_0x006f
            boolean r7 = r6.d()
            if (r7 != 0) goto L_0x006f
            boolean r7 = r6.E
            if (r7 == 0) goto L_0x006e
            goto L_0x006f
        L_0x006e:
            r2 = 0
        L_0x006f:
            return r2
        */
        throw new UnsupportedOperationException("Method not decompiled: com.xiaomi.smarthome.library.common.widget.drawerlayout.DrawerLayout.onInterceptTouchEvent(android.view.MotionEvent):boolean");
    }

    public boolean onTouchEvent(MotionEvent motionEvent) {
        boolean z2;
        View findOpenDrawer;
        this.s.b(motionEvent);
        this.t.b(motionEvent);
        int action = motionEvent.getAction() & 255;
        if (action != 3) {
            switch (action) {
                case 0:
                    float x2 = motionEvent.getX();
                    float y2 = motionEvent.getY();
                    this.H = x2;
                    this.I = y2;
                    this.D = false;
                    this.E = false;
                    break;
                case 1:
                    float x3 = motionEvent.getX();
                    float y3 = motionEvent.getY();
                    View e2 = this.s.e((int) x3, (int) y3);
                    if (e2 != null && isContentView(e2)) {
                        float f2 = x3 - this.H;
                        float f3 = y3 - this.I;
                        int f4 = this.s.f();
                        if (!((f2 * f2) + (f3 * f3) >= ((float) (f4 * f4)) || (findOpenDrawer = findOpenDrawer()) == null || getDrawerLockMode(findOpenDrawer) == 2)) {
                            z2 = false;
                            closeDrawers(z2);
                            this.D = false;
                            break;
                        }
                    }
                    z2 = true;
                    closeDrawers(z2);
                    this.D = false;
            }
        } else {
            closeDrawers(true);
            this.D = false;
            this.E = false;
        }
        return true;
    }

    public void requestDisallowInterceptTouchEvent(boolean z2) {
        super.requestDisallowInterceptTouchEvent(z2);
        this.D = z2;
        if (z2) {
            closeDrawers(true);
        }
    }

    public void closeDrawers() {
        closeDrawers(false);
    }

    /* access modifiers changed from: package-private */
    public void closeDrawers(boolean z2) {
        int childCount = getChildCount();
        boolean z3 = false;
        for (int i2 = 0; i2 < childCount; i2++) {
            View childAt = getChildAt(i2);
            LayoutParams layoutParams = (LayoutParams) childAt.getLayoutParams();
            if (isDrawerView(childAt) && (!z2 || layoutParams.f)) {
                int width = childAt.getWidth();
                if (checkDrawerViewAbsoluteGravity(childAt, 3)) {
                    z3 |= this.s.a(childAt, -width, childAt.getTop());
                } else {
                    z3 |= this.t.a(childAt, getWidth(), childAt.getTop());
                }
                boolean unused = layoutParams.f = false;
            }
        }
        this.u.a();
        this.v.a();
        if (z3) {
            invalidate();
        }
    }

    public void openDrawer(View view) {
        if (isDrawerView(view)) {
            LayoutParams layoutParams = (LayoutParams) view.getLayoutParams();
            if (this.y) {
                float unused = layoutParams.e = 1.0f;
                int unused2 = layoutParams.g = 1;
                a(view, true);
            } else {
                int unused3 = layoutParams.g = layoutParams.g | 2;
                if (checkDrawerViewAbsoluteGravity(view, 3)) {
                    this.s.a(view, 0, view.getTop());
                } else {
                    this.t.a(view, getWidth() - view.getWidth(), view.getTop());
                }
            }
            invalidate();
            return;
        }
        throw new IllegalArgumentException("View " + view + " is not a sliding drawer");
    }

    public void openDrawer(int i2) {
        View findDrawerWithGravity = findDrawerWithGravity(i2);
        if (findDrawerWithGravity != null) {
            openDrawer(findDrawerWithGravity);
            return;
        }
        throw new IllegalArgumentException("No drawer view found with gravity " + gravityToString(i2));
    }

    public void closeDrawer(View view) {
        if (isDrawerView(view)) {
            LayoutParams layoutParams = (LayoutParams) view.getLayoutParams();
            if (this.y) {
                float unused = layoutParams.e = 0.0f;
                int unused2 = layoutParams.g = 0;
            } else {
                int unused3 = layoutParams.g = layoutParams.g | 4;
                if (checkDrawerViewAbsoluteGravity(view, 3)) {
                    this.s.a(view, -view.getWidth(), view.getTop());
                } else {
                    this.t.a(view, getWidth(), view.getTop());
                }
            }
            invalidate();
            return;
        }
        throw new IllegalArgumentException("View " + view + " is not a sliding drawer");
    }

    public void closeDrawerFast(View view) {
        if (isDrawerView(view)) {
            LayoutParams layoutParams = (LayoutParams) view.getLayoutParams();
            if (this.y) {
                float unused = layoutParams.e = 0.0f;
                int unused2 = layoutParams.g = 0;
            } else {
                int unused3 = layoutParams.g = layoutParams.g | 4;
                if (checkDrawerViewAbsoluteGravity(view, 3)) {
                    this.s.b(view, -view.getWidth(), view.getTop());
                } else {
                    this.t.a(view, getWidth(), view.getTop());
                }
            }
            invalidate();
            return;
        }
        throw new IllegalArgumentException("View " + view + " is not a sliding drawer");
    }

    public void closeDrawer(int i2) {
        View findDrawerWithGravity = findDrawerWithGravity(i2);
        if (findDrawerWithGravity != null) {
            closeDrawer(findDrawerWithGravity);
            return;
        }
        throw new IllegalArgumentException("No drawer view found with gravity " + gravityToString(i2));
    }

    public void closeDrawerFast(int i2) {
        View findDrawerWithGravity = findDrawerWithGravity(i2);
        if (findDrawerWithGravity != null) {
            closeDrawerFast(findDrawerWithGravity);
            return;
        }
        throw new IllegalArgumentException("No drawer view found with gravity " + gravityToString(i2));
    }

    public boolean isDrawerOpen(View view) {
        if (isDrawerView(view)) {
            return (((LayoutParams) view.getLayoutParams()).g & 1) == 1;
        }
        throw new IllegalArgumentException("View " + view + " is not a drawer");
    }

    public boolean isDrawerOpen(int i2) {
        View findDrawerWithGravity = findDrawerWithGravity(i2);
        if (findDrawerWithGravity != null) {
            return isDrawerOpen(findDrawerWithGravity);
        }
        return false;
    }

    public boolean isDrawerVisible(View view) {
        if (isDrawerView(view)) {
            return ((LayoutParams) view.getLayoutParams()).e > 0.0f;
        }
        throw new IllegalArgumentException("View " + view + " is not a drawer");
    }

    public boolean isDrawerVisible(int i2) {
        View findDrawerWithGravity = findDrawerWithGravity(i2);
        if (findDrawerWithGravity != null) {
            return isDrawerVisible(findDrawerWithGravity);
        }
        return false;
    }

    private boolean d() {
        int childCount = getChildCount();
        for (int i2 = 0; i2 < childCount; i2++) {
            if (((LayoutParams) getChildAt(i2).getLayoutParams()).f) {
                return true;
            }
        }
        return false;
    }

    /* access modifiers changed from: protected */
    public ViewGroup.LayoutParams generateDefaultLayoutParams() {
        return new LayoutParams(-1, -1);
    }

    /* access modifiers changed from: protected */
    public ViewGroup.LayoutParams generateLayoutParams(ViewGroup.LayoutParams layoutParams) {
        if (layoutParams instanceof LayoutParams) {
            return new LayoutParams((LayoutParams) layoutParams);
        }
        return layoutParams instanceof ViewGroup.MarginLayoutParams ? new LayoutParams((ViewGroup.MarginLayoutParams) layoutParams) : new LayoutParams(layoutParams);
    }

    /* access modifiers changed from: protected */
    public boolean checkLayoutParams(ViewGroup.LayoutParams layoutParams) {
        return (layoutParams instanceof LayoutParams) && super.checkLayoutParams(layoutParams);
    }

    public ViewGroup.LayoutParams generateLayoutParams(AttributeSet attributeSet) {
        return new LayoutParams(getContext(), attributeSet);
    }

    public void addFocusables(ArrayList<View> arrayList, int i2, int i3) {
        if (getDescendantFocusability() != 393216) {
            int childCount = getChildCount();
            boolean z2 = false;
            for (int i4 = 0; i4 < childCount; i4++) {
                View childAt = getChildAt(i4);
                if (!isDrawerView(childAt)) {
                    this.U.add(childAt);
                } else if (isDrawerOpen(childAt)) {
                    childAt.addFocusables(arrayList, i2, i3);
                    z2 = true;
                }
            }
            if (!z2) {
                int size = this.U.size();
                for (int i5 = 0; i5 < size; i5++) {
                    View view = this.U.get(i5);
                    if (view.getVisibility() == 0) {
                        view.addFocusables(arrayList, i2, i3);
                    }
                }
            }
            this.U.clear();
        }
    }

    private boolean e() {
        return f() != null;
    }

    /* access modifiers changed from: private */
    public View f() {
        int childCount = getChildCount();
        for (int i2 = 0; i2 < childCount; i2++) {
            View childAt = getChildAt(i2);
            if (isDrawerView(childAt) && isDrawerVisible(childAt)) {
                return childAt;
            }
        }
        return null;
    }

    /* access modifiers changed from: package-private */
    public void cancelChildViewTouch() {
        if (!this.E) {
            long uptimeMillis = SystemClock.uptimeMillis();
            MotionEvent obtain = MotionEvent.obtain(uptimeMillis, uptimeMillis, 3, 0.0f, 0.0f, 0);
            int childCount = getChildCount();
            for (int i2 = 0; i2 < childCount; i2++) {
                getChildAt(i2).dispatchTouchEvent(obtain);
            }
            obtain.recycle();
            this.E = true;
        }
    }

    public boolean onKeyDown(int i2, KeyEvent keyEvent) {
        if (i2 != 4 || !e()) {
            return super.onKeyDown(i2, keyEvent);
        }
        keyEvent.startTracking();
        return true;
    }

    public boolean onKeyUp(int i2, KeyEvent keyEvent) {
        if (i2 != 4) {
            return super.onKeyUp(i2, keyEvent);
        }
        View f2 = f();
        if (f2 != null && getDrawerLockMode(f2) == 0) {
            closeDrawers();
        }
        return f2 != null;
    }

    /* access modifiers changed from: protected */
    public void onRestoreInstanceState(Parcelable parcelable) {
        View findDrawerWithGravity;
        if (!(parcelable instanceof SavedState)) {
            super.onRestoreInstanceState(parcelable);
            return;
        }
        SavedState savedState = (SavedState) parcelable;
        super.onRestoreInstanceState(savedState.getSuperState());
        if (!(savedState.f19016a == 0 || (findDrawerWithGravity = findDrawerWithGravity(savedState.f19016a)) == null)) {
            openDrawer(findDrawerWithGravity);
        }
        if (savedState.b != 3) {
            setDrawerLockMode(savedState.b, 3);
        }
        if (savedState.c != 3) {
            setDrawerLockMode(savedState.c, 5);
        }
        if (savedState.d != 3) {
            setDrawerLockMode(savedState.d, (int) GravityCompat.START);
        }
        if (savedState.e != 3) {
            setDrawerLockMode(savedState.e, (int) GravityCompat.END);
        }
    }

    /* access modifiers changed from: protected */
    public Parcelable onSaveInstanceState() {
        SavedState savedState = new SavedState(super.onSaveInstanceState());
        int childCount = getChildCount();
        int i2 = 0;
        while (true) {
            if (i2 >= childCount) {
                break;
            }
            LayoutParams layoutParams = (LayoutParams) getChildAt(i2).getLayoutParams();
            boolean z2 = true;
            boolean z3 = layoutParams.g == 1;
            if (layoutParams.g != 2) {
                z2 = false;
            }
            if (z3 || z2) {
                savedState.f19016a = layoutParams.f19015a;
            } else {
                i2++;
            }
        }
        savedState.b = this.z;
        savedState.c = this.A;
        savedState.d = this.B;
        savedState.e = this.C;
        return savedState;
    }

    public void addView(View view, int i2, ViewGroup.LayoutParams layoutParams) {
        super.addView(view, i2, layoutParams);
        if (findOpenDrawer() != null || isDrawerView(view)) {
            ViewCompat.setImportantForAccessibility(view, 4);
        } else {
            ViewCompat.setImportantForAccessibility(view, 1);
        }
        if (!k) {
            ViewCompat.setAccessibilityDelegate(view, this.m);
        }
    }

    /* access modifiers changed from: private */
    public static boolean b(View view) {
        return (ViewCompat.getImportantForAccessibility(view) == 4 || ViewCompat.getImportantForAccessibility(view) == 2) ? false : true;
    }

    protected static class SavedState extends View.BaseSavedState {
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
        int f19016a = 0;
        int b;
        int c;
        int d;
        int e;

        public SavedState(Parcel parcel) {
            super(parcel);
            this.f19016a = parcel.readInt();
            this.b = parcel.readInt();
            this.c = parcel.readInt();
            this.d = parcel.readInt();
            this.e = parcel.readInt();
        }

        public SavedState(Parcelable parcelable) {
            super(parcelable);
        }

        public void writeToParcel(Parcel parcel, int i) {
            super.writeToParcel(parcel, i);
            parcel.writeInt(this.f19016a);
            parcel.writeInt(this.b);
            parcel.writeInt(this.c);
            parcel.writeInt(this.d);
            parcel.writeInt(this.e);
        }
    }

    private class ViewDragCallback extends ViewDragHelper.Callback {
        private final int b;
        private ViewDragHelper c;
        private final Runnable d = new Runnable() {
            public void run() {
                ViewDragCallback.this.c();
            }
        };

        public boolean b(int i) {
            return false;
        }

        public ViewDragCallback(int i) {
            this.b = i;
        }

        public void a(ViewDragHelper viewDragHelper) {
            this.c = viewDragHelper;
        }

        public void a() {
            DrawerLayout.this.removeCallbacks(this.d);
        }

        public boolean a(View view, int i) {
            return DrawerLayout.this.isDrawerView(view) && DrawerLayout.this.checkDrawerViewAbsoluteGravity(view, this.b) && DrawerLayout.this.getDrawerLockMode(view) == 0;
        }

        public void a(int i) {
            DrawerLayout.this.updateDrawerState(this.b, i, this.c.d());
        }

        public void a(View view, int i, int i2, int i3, int i4) {
            float f;
            int width = view.getWidth();
            if (DrawerLayout.this.checkDrawerViewAbsoluteGravity(view, 3)) {
                f = ((float) (i + width)) / ((float) width);
            } else {
                f = ((float) (DrawerLayout.this.getWidth() - i)) / ((float) width);
            }
            DrawerLayout.this.setDrawerViewOffset(view, f);
            view.setVisibility(f == 0.0f ? 4 : 0);
            DrawerLayout.this.invalidate();
        }

        public void b(View view, int i) {
            boolean unused = ((LayoutParams) view.getLayoutParams()).f = false;
            b();
        }

        private void b() {
            int i = 3;
            if (this.b == 3) {
                i = 5;
            }
            View findDrawerWithGravity = DrawerLayout.this.findDrawerWithGravity(i);
            if (findDrawerWithGravity != null) {
                DrawerLayout.this.closeDrawer(findDrawerWithGravity);
            }
        }

        public void a(View view, float f, float f2) {
            int i;
            float drawerViewOffset = DrawerLayout.this.getDrawerViewOffset(view);
            int width = view.getWidth();
            if (DrawerLayout.this.checkDrawerViewAbsoluteGravity(view, 3)) {
                i = (f > 0.0f || (f == 0.0f && drawerViewOffset > 0.5f)) ? 0 : -width;
            } else {
                int width2 = DrawerLayout.this.getWidth();
                if (f < 0.0f || (f == 0.0f && drawerViewOffset > 0.5f)) {
                    width2 -= width;
                }
                i = width2;
            }
            this.c.a(i, view.getTop());
            DrawerLayout.this.invalidate();
        }

        public void a(int i, int i2) {
            DrawerLayout.this.postDelayed(this.d, 160);
        }

        /* access modifiers changed from: private */
        public void c() {
            View view;
            int i;
            int c2 = this.c.c();
            int i2 = 0;
            boolean z = this.b == 3;
            if (z) {
                view = DrawerLayout.this.findDrawerWithGravity(3);
                if (view != null) {
                    i2 = -view.getWidth();
                }
                i = i2 + c2;
            } else {
                view = DrawerLayout.this.findDrawerWithGravity(5);
                i = DrawerLayout.this.getWidth() - c2;
            }
            if (view == null) {
                return;
            }
            if (((z && view.getLeft() < i) || (!z && view.getLeft() > i)) && DrawerLayout.this.getDrawerLockMode(view) == 0) {
                this.c.a(view, i, view.getTop());
                boolean unused = ((LayoutParams) view.getLayoutParams()).f = true;
                DrawerLayout.this.invalidate();
                b();
                DrawerLayout.this.cancelChildViewTouch();
            }
        }

        public void b(int i, int i2) {
            View view;
            if ((i & 1) == 1) {
                view = DrawerLayout.this.findDrawerWithGravity(3);
            } else {
                view = DrawerLayout.this.findDrawerWithGravity(5);
            }
            if (view != null && DrawerLayout.this.getDrawerLockMode(view) == 0) {
                this.c.a(view, i2);
            }
        }

        public int a(View view) {
            if (DrawerLayout.this.isDrawerView(view)) {
                return view.getWidth();
            }
            return 0;
        }

        public int a(View view, int i, int i2) {
            if (DrawerLayout.this.checkDrawerViewAbsoluteGravity(view, 3)) {
                return Math.max(-view.getWidth(), Math.min(i, 0));
            }
            int width = DrawerLayout.this.getWidth();
            return Math.max(width - view.getWidth(), Math.min(i, width));
        }

        public int b(View view, int i, int i2) {
            return view.getTop();
        }
    }

    public static class LayoutParams extends ViewGroup.MarginLayoutParams {
        private static final int b = 1;
        private static final int c = 2;
        private static final int d = 4;

        /* renamed from: a  reason: collision with root package name */
        public int f19015a;
        /* access modifiers changed from: private */
        public float e;
        /* access modifiers changed from: private */
        public boolean f;
        /* access modifiers changed from: private */
        public int g;

        public LayoutParams(Context context, AttributeSet attributeSet) {
            super(context, attributeSet);
            this.f19015a = 0;
            TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, DrawerLayout.j);
            this.f19015a = obtainStyledAttributes.getInt(0, 0);
            obtainStyledAttributes.recycle();
        }

        public LayoutParams(int i, int i2) {
            super(i, i2);
            this.f19015a = 0;
        }

        public LayoutParams(int i, int i2, int i3) {
            this(i, i2);
            this.f19015a = i3;
        }

        public LayoutParams(LayoutParams layoutParams) {
            super(layoutParams);
            this.f19015a = 0;
            this.f19015a = layoutParams.f19015a;
        }

        public LayoutParams(ViewGroup.LayoutParams layoutParams) {
            super(layoutParams);
            this.f19015a = 0;
        }

        public LayoutParams(ViewGroup.MarginLayoutParams marginLayoutParams) {
            super(marginLayoutParams);
            this.f19015a = 0;
        }
    }

    class AccessibilityDelegate extends AccessibilityDelegateCompat {
        private final Rect b = new Rect();

        AccessibilityDelegate() {
        }

        public void onInitializeAccessibilityNodeInfo(View view, AccessibilityNodeInfoCompat accessibilityNodeInfoCompat) {
            if (DrawerLayout.k) {
                super.onInitializeAccessibilityNodeInfo(view, accessibilityNodeInfoCompat);
            } else {
                AccessibilityNodeInfoCompat obtain = AccessibilityNodeInfoCompat.obtain(accessibilityNodeInfoCompat);
                super.onInitializeAccessibilityNodeInfo(view, obtain);
                accessibilityNodeInfoCompat.setSource(view);
                ViewParent parentForAccessibility = ViewCompat.getParentForAccessibility(view);
                if (parentForAccessibility instanceof View) {
                    accessibilityNodeInfoCompat.setParent((View) parentForAccessibility);
                }
                a(accessibilityNodeInfoCompat, obtain);
                obtain.recycle();
                a(accessibilityNodeInfoCompat, (ViewGroup) view);
            }
            accessibilityNodeInfoCompat.setClassName(DrawerLayout.class.getName());
            accessibilityNodeInfoCompat.setFocusable(false);
            accessibilityNodeInfoCompat.setFocused(false);
            accessibilityNodeInfoCompat.removeAction(AccessibilityNodeInfoCompat.AccessibilityActionCompat.ACTION_FOCUS);
            accessibilityNodeInfoCompat.removeAction(AccessibilityNodeInfoCompat.AccessibilityActionCompat.ACTION_CLEAR_FOCUS);
        }

        public void onInitializeAccessibilityEvent(View view, AccessibilityEvent accessibilityEvent) {
            super.onInitializeAccessibilityEvent(view, accessibilityEvent);
            accessibilityEvent.setClassName(DrawerLayout.class.getName());
        }

        public boolean dispatchPopulateAccessibilityEvent(View view, AccessibilityEvent accessibilityEvent) {
            CharSequence drawerTitle;
            if (accessibilityEvent.getEventType() != 32) {
                return super.dispatchPopulateAccessibilityEvent(view, accessibilityEvent);
            }
            List text = accessibilityEvent.getText();
            View access$600 = DrawerLayout.this.f();
            if (access$600 == null || (drawerTitle = DrawerLayout.this.getDrawerTitle(DrawerLayout.this.getDrawerViewAbsoluteGravity(access$600))) == null) {
                return true;
            }
            text.add(drawerTitle);
            return true;
        }

        public boolean onRequestSendAccessibilityEvent(ViewGroup viewGroup, View view, AccessibilityEvent accessibilityEvent) {
            if (DrawerLayout.k || DrawerLayout.b(view)) {
                return super.onRequestSendAccessibilityEvent(viewGroup, view, accessibilityEvent);
            }
            return false;
        }

        private void a(AccessibilityNodeInfoCompat accessibilityNodeInfoCompat, ViewGroup viewGroup) {
            int childCount = viewGroup.getChildCount();
            for (int i = 0; i < childCount; i++) {
                View childAt = viewGroup.getChildAt(i);
                if (DrawerLayout.b(childAt)) {
                    accessibilityNodeInfoCompat.addChild(childAt);
                }
            }
        }

        private void a(AccessibilityNodeInfoCompat accessibilityNodeInfoCompat, AccessibilityNodeInfoCompat accessibilityNodeInfoCompat2) {
            Rect rect = this.b;
            accessibilityNodeInfoCompat2.getBoundsInParent(rect);
            accessibilityNodeInfoCompat.setBoundsInParent(rect);
            accessibilityNodeInfoCompat2.getBoundsInScreen(rect);
            accessibilityNodeInfoCompat.setBoundsInScreen(rect);
            accessibilityNodeInfoCompat.setVisibleToUser(accessibilityNodeInfoCompat2.isVisibleToUser());
            accessibilityNodeInfoCompat.setPackageName(accessibilityNodeInfoCompat2.getPackageName());
            accessibilityNodeInfoCompat.setClassName(accessibilityNodeInfoCompat2.getClassName());
            accessibilityNodeInfoCompat.setContentDescription(accessibilityNodeInfoCompat2.getContentDescription());
            accessibilityNodeInfoCompat.setEnabled(accessibilityNodeInfoCompat2.isEnabled());
            accessibilityNodeInfoCompat.setClickable(accessibilityNodeInfoCompat2.isClickable());
            accessibilityNodeInfoCompat.setFocusable(accessibilityNodeInfoCompat2.isFocusable());
            accessibilityNodeInfoCompat.setFocused(accessibilityNodeInfoCompat2.isFocused());
            accessibilityNodeInfoCompat.setAccessibilityFocused(accessibilityNodeInfoCompat2.isAccessibilityFocused());
            accessibilityNodeInfoCompat.setSelected(accessibilityNodeInfoCompat2.isSelected());
            accessibilityNodeInfoCompat.setLongClickable(accessibilityNodeInfoCompat2.isLongClickable());
            accessibilityNodeInfoCompat.addAction(accessibilityNodeInfoCompat2.getActions());
        }
    }

    final class ChildAccessibilityDelegate extends AccessibilityDelegateCompat {
        ChildAccessibilityDelegate() {
        }

        public void onInitializeAccessibilityNodeInfo(View view, AccessibilityNodeInfoCompat accessibilityNodeInfoCompat) {
            super.onInitializeAccessibilityNodeInfo(view, accessibilityNodeInfoCompat);
            if (!DrawerLayout.b(view)) {
                accessibilityNodeInfoCompat.setParent((View) null);
            }
        }
    }
}
