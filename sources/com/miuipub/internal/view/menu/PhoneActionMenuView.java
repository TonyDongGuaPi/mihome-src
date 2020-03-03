package com.miuipub.internal.view.menu;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewParent;
import com.miuipub.internal.view.menu.ActionMenuView;
import com.miuipub.internal.widget.ActionBarOverlayLayout;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Iterator;
import miuipub.graphics.BitmapFactory;
import miuipub.util.AttributeResolver;
import miuipub.v6.R;

public class PhoneActionMenuView extends ActionMenuView {

    /* renamed from: a  reason: collision with root package name */
    private static final int f8311a = 2;
    private static final int[] b = {R.attr.v6_expandBackground, R.attr.v6_splitActionBarOverlayHeight};
    /* access modifiers changed from: private */
    public View c;
    private OverflowMenuViewAnimator d;
    /* access modifiers changed from: private */
    public OverflowMenuState e;
    private Drawable f;
    private Drawable g;
    private WeakReference<Bitmap> h;
    private Drawable i;
    private int j;
    private int k;
    private int l;
    private Rect m;
    private int n;
    private boolean o;

    private enum OverflowMenuState {
        Collapsed,
        Expanding,
        Expanded,
        Collapsing
    }

    public PhoneActionMenuView(Context context) {
        this(context, (AttributeSet) null);
    }

    public PhoneActionMenuView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.e = OverflowMenuState.Collapsed;
        this.i = getBackground();
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, b);
        this.f = obtainStyledAttributes.getDrawable(0);
        this.n = obtainStyledAttributes.getDimensionPixelSize(1, 0);
        obtainStyledAttributes.recycle();
        this.o = AttributeResolver.a(context, R.attr.v6_actionMenuBlurEnabled, false);
        c();
    }

    public void setBackgroundDrawable(Drawable drawable) {
        super.setBackgroundDrawable(drawable);
        if (this.i != drawable) {
            this.i = drawable;
            c();
        }
    }

    public boolean filterLeftoverView(int i2) {
        boolean z;
        ActionMenuView.LayoutParams layoutParams;
        View childAt = getChildAt(i2);
        if (childAt != this.c && ((layoutParams = (ActionMenuView.LayoutParams) childAt.getLayoutParams()) == null || !layoutParams.f8297a)) {
            z = true;
        } else {
            z = false;
        }
        if (!z || !super.filterLeftoverView(i2)) {
            return false;
        }
        return true;
    }

    public void setOverflowMenuView(View view) {
        if (this.c != view) {
            if (this.c != null) {
                if (this.c.getAnimation() != null) {
                    this.c.clearAnimation();
                }
                removeView(this.c);
            }
            if (view != null) {
                addView(view);
            }
            this.c = view;
            c();
        }
    }

    public boolean showOverflowMenu() {
        OverflowMenuState overflowMenuState = this.e;
        if (overflowMenuState == OverflowMenuState.Expanding || overflowMenuState == OverflowMenuState.Expanded || this.c == null) {
            return false;
        }
        OverflowMenuViewAnimator overflowMenuViewAnimator = getOverflowMenuViewAnimator();
        if (overflowMenuState == OverflowMenuState.Collapsed) {
            a();
            this.e = OverflowMenuState.Expanding;
            overflowMenuViewAnimator.b();
        } else if (overflowMenuState == OverflowMenuState.Collapsing) {
            a();
            overflowMenuViewAnimator.e();
        }
        postInvalidateOnAnimation();
        return true;
    }

    private void a() {
        if (this.o) {
            Drawable b2 = AttributeResolver.b(getContext(), R.attr.v6_immersionBlurMask);
            View rootView = getRootView();
            if (rootView != null) {
                Bitmap bitmap = null;
                try {
                    if (b() != -1) {
                        if (this.h != null) {
                            bitmap = (Bitmap) this.h.get();
                        }
                        if (!(bitmap != null && bitmap.getWidth() == getWidth() && bitmap.getHeight() == getHeight())) {
                            if (bitmap != null) {
                                bitmap.recycle();
                            }
                            bitmap = Bitmap.createBitmap(getWidth(), getHeight(), Bitmap.Config.ARGB_8888);
                            this.h = new WeakReference<>(bitmap);
                        }
                        Canvas canvas = new Canvas(bitmap);
                        canvas.translate(0.0f, (float) (-b()));
                        rootView.draw(canvas);
                        int width = bitmap.getWidth();
                        int height = bitmap.getHeight();
                        Bitmap createScaledBitmap = Bitmap.createScaledBitmap(BitmapFactory.a(Bitmap.createScaledBitmap(bitmap, width / 2, height / 2, false), getContext().getResources().getDimensionPixelSize(R.dimen.v6_screenshot_blur_radius)), width, height, false);
                        Canvas canvas2 = new Canvas(createScaledBitmap);
                        b2.setBounds(0, 0, createScaledBitmap.getWidth(), createScaledBitmap.getHeight());
                        b2.draw(canvas2);
                        this.g = new BitmapDrawable(getContext().getResources(), createScaledBitmap);
                    }
                } catch (OutOfMemoryError unused) {
                }
            }
        }
    }

    private int b() {
        int top = getTop();
        View rootView = getRootView();
        if (rootView == null) {
            return -1;
        }
        ViewParent parent = getParent();
        while (parent != null && (parent instanceof View) && parent != rootView) {
            View view = (View) parent;
            top += view.getTop();
            parent = view.getParent();
        }
        return top;
    }

    public boolean isOverflowMenuShowing() {
        return this.e == OverflowMenuState.Expanding || this.e == OverflowMenuState.Expanded;
    }

    public boolean hideOverflowMenu() {
        OverflowMenuState overflowMenuState = this.e;
        if (overflowMenuState == OverflowMenuState.Collapsing || overflowMenuState == OverflowMenuState.Collapsed) {
            return false;
        }
        OverflowMenuViewAnimator overflowMenuViewAnimator = getOverflowMenuViewAnimator();
        if (overflowMenuState == OverflowMenuState.Expanded) {
            this.e = OverflowMenuState.Collapsing;
            overflowMenuViewAnimator.c();
            return true;
        } else if (overflowMenuState != OverflowMenuState.Expanding) {
            return true;
        } else {
            overflowMenuViewAnimator.e();
            return true;
        }
    }

    private OverflowMenuViewAnimator getOverflowMenuViewAnimator() {
        if (this.d == null) {
            this.d = new OverflowMenuViewAnimator();
        }
        return this.d;
    }

    public int getCollapsedHeight() {
        if (this.k == 0) {
            return 0;
        }
        return Math.max(this.k, this.l) - this.n;
    }

    /* access modifiers changed from: protected */
    public void onMeasure(int i2, int i3) {
        int childCount = getChildCount();
        if (childCount == 0) {
            this.k = 0;
            setMeasuredDimension(0, 0);
            return;
        }
        int i4 = 0;
        int i5 = 0;
        for (int i6 = 0; i6 < childCount; i6++) {
            View childAt = getChildAt(i6);
            if (childAt != this.c) {
                measureChildWithMargins(childAt, i2, 0, i3, 0);
                i4 += childAt.getMeasuredWidth();
                i5 = Math.max(i5, childAt.getMeasuredHeight());
            }
        }
        this.j = i4;
        this.k = i5;
        float f2 = 0.0f;
        if (this.c != null) {
            measureChildWithMargins(this.c, i2, 0, i3, 0);
            i4 = Math.max(i4, this.c.getMeasuredWidth());
            i5 += this.c.getMeasuredHeight();
            if (this.e != OverflowMenuState.Collapsing) {
                if (this.e != OverflowMenuState.Expanded) {
                    f2 = (float) this.c.getMeasuredHeight();
                }
                setTranslationY(f2);
            }
        } else {
            setTranslationY(0.0f);
        }
        if (this.l > this.k) {
            i5 += this.l - this.k;
        }
        setMeasuredDimension(Math.max(i4, View.MeasureSpec.getSize(i2)), i5);
    }

    /* access modifiers changed from: protected */
    public void onLayout(boolean z, int i2, int i3, int i4, int i5) {
        int i6;
        int i7;
        int i8 = i4 - i2;
        int i9 = i5 - i3;
        int i10 = (i8 - this.j) >> 1;
        int i11 = this.k;
        if (this.m != null) {
            i7 = this.m.top;
            i6 = Math.min(this.k + i7, Math.max(this.l, this.k));
        } else {
            i6 = i11;
            i7 = 0;
        }
        int childCount = getChildCount();
        int i12 = i10;
        for (int i13 = 0; i13 < childCount; i13++) {
            View childAt = getChildAt(i13);
            if (childAt != this.c) {
                childAt.layout(i12, i7, childAt.getMeasuredWidth() + i12, i6);
                i12 += childAt.getMeasuredWidth();
            }
        }
        if (this.c != null) {
            this.c.layout(0, Math.max(i6, this.l), i8, i9);
        }
    }

    /* access modifiers changed from: protected */
    public void onDraw(Canvas canvas) {
        boolean z = this.e == OverflowMenuState.Expanded || this.e == OverflowMenuState.Expanding || this.e == OverflowMenuState.Collapsing;
        if (z && this.g != null) {
            this.g.setBounds(0, 0, getWidth(), getHeight());
            this.g.draw(canvas);
        }
        Drawable drawable = z ? this.f : this.i;
        if (drawable != null) {
            drawable.setBounds(0, 0, getWidth(), Math.max(this.k, this.l));
            drawable.draw(canvas);
        }
    }

    public boolean onTouchEvent(MotionEvent motionEvent) {
        return motionEvent.getY() > getTranslationY() || super.onTouchEvent(motionEvent);
    }

    public void onPageScrolled(int i2, float f2, boolean z, boolean z2) {
        setAlpha(computeAlpha(f2, z, z2));
        float computeTranslationY = computeTranslationY(f2, z, z2);
        for (int i3 = 0; i3 < getChildCount(); i3++) {
            View childAt = getChildAt(i3);
            if (childAt != this.c) {
                childAt.setTranslationY(computeTranslationY);
            }
        }
    }

    private void c() {
        Drawable drawable = this.c == null ? this.i : this.f;
        if (drawable == null) {
            this.m = null;
            this.l = 0;
            return;
        }
        if (this.m == null) {
            this.m = new Rect();
        }
        drawable.getPadding(this.m);
        this.l = drawable.getIntrinsicHeight();
        if (this.l == -1) {
            this.l = 0;
        }
    }

    public void setValue(float f2) {
        if (this.c == null) {
            setTranslationY(0.0f);
        } else {
            setTranslationY(f2 * ((float) this.c.getHeight()));
        }
    }

    private class OverflowMenuViewAnimator implements Animator.AnimatorListener, View.OnClickListener {
        private AnimatorSet b;
        private AnimatorSet c;

        public void onAnimationRepeat(Animator animator) {
        }

        public void onAnimationStart(Animator animator) {
        }

        private OverflowMenuViewAnimator() {
        }

        public void a() {
            if (this.b == null) {
                ActionBarOverlayLayout actionBarOverlayLayout = ActionBarOverlayLayout.getActionBarOverlayLayout(PhoneActionMenuView.this);
                AnimatorSet animatorSet = new AnimatorSet();
                animatorSet.playTogether(new Animator[]{ObjectAnimator.ofFloat(PhoneActionMenuView.this, "Value", new float[]{1.0f, 0.0f}), actionBarOverlayLayout.getContentMaskAnimator(this).a()});
                animatorSet.setDuration((long) PhoneActionMenuView.this.getResources().getInteger(17694720));
                animatorSet.addListener(this);
                this.b = animatorSet;
                AnimatorSet animatorSet2 = new AnimatorSet();
                animatorSet2.playTogether(new Animator[]{ObjectAnimator.ofFloat(PhoneActionMenuView.this, "Value", new float[]{0.0f, 1.0f}), actionBarOverlayLayout.getContentMaskAnimator((View.OnClickListener) null).b()});
                animatorSet2.setDuration((long) PhoneActionMenuView.this.getResources().getInteger(17694720));
                animatorSet2.addListener(this);
                this.c = animatorSet2;
            }
        }

        public void b() {
            a();
            this.c.cancel();
            this.b.cancel();
            this.b.start();
        }

        public void c() {
            a();
            this.c.cancel();
            this.b.cancel();
            this.c.start();
        }

        public void d() {
            if (this.c != null && this.c.isRunning()) {
                this.c.cancel();
            }
            if (this.b != null && this.b.isRunning()) {
                this.b.cancel();
            }
        }

        public void e() {
            ArrayList<Animator> childAnimations = this.b.isRunning() ? this.b.getChildAnimations() : null;
            if (this.c.isRunning()) {
                childAnimations = this.c.getChildAnimations();
            }
            if (childAnimations != null) {
                Iterator<Animator> it = childAnimations.iterator();
                while (it.hasNext()) {
                    ((ValueAnimator) it.next()).reverse();
                }
            }
        }

        public void onAnimationEnd(Animator animator) {
            if (PhoneActionMenuView.this.getTranslationY() == 0.0f) {
                OverflowMenuState unused = PhoneActionMenuView.this.e = OverflowMenuState.Expanded;
            } else if (PhoneActionMenuView.this.c != null && PhoneActionMenuView.this.getTranslationY() == ((float) PhoneActionMenuView.this.c.getHeight())) {
                OverflowMenuState unused2 = PhoneActionMenuView.this.e = OverflowMenuState.Collapsed;
            }
            PhoneActionMenuView.this.postInvalidateOnAnimation();
        }

        public void onAnimationCancel(Animator animator) {
            if (PhoneActionMenuView.this.e == OverflowMenuState.Expanding || PhoneActionMenuView.this.e == OverflowMenuState.Expanded) {
                PhoneActionMenuView.this.setValue(0.0f);
            } else if (PhoneActionMenuView.this.e == OverflowMenuState.Collapsing || PhoneActionMenuView.this.e == OverflowMenuState.Collapsed) {
                PhoneActionMenuView.this.setValue(1.0f);
            }
            PhoneActionMenuView.this.postInvalidateOnAnimation();
        }

        public void onClick(View view) {
            if (PhoneActionMenuView.this.e == OverflowMenuState.Expanded) {
                PhoneActionMenuView.this.getPresenter().e(true);
            }
        }
    }
}
