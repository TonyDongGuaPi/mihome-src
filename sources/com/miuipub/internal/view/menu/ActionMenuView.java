package com.miuipub.internal.view.menu;

import android.animation.Animator;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.content.res.Configuration;
import android.os.Build;
import android.util.AttributeSet;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewDebug;
import android.view.ViewGroup;
import android.view.accessibility.AccessibilityEvent;
import android.widget.LinearLayout;
import com.miuipub.internal.view.menu.MenuBuilder;

public abstract class ActionMenuView extends LinearLayout implements MenuBuilder.ItemInvoker, MenuView {

    /* renamed from: a  reason: collision with root package name */
    private MenuBuilder f8296a;
    private boolean b;
    private ActionMenuPresenter c;
    private OpenCloseAnimator d;

    public interface ActionMenuChildView {
        boolean needsDividerAfter();

        boolean needsDividerBefore();
    }

    /* access modifiers changed from: protected */
    public float computeAlpha(float f, boolean z, boolean z2) {
        if (z && z2) {
            return 1.0f;
        }
        if (z) {
            return ((float) ((int) ((1.0f - f) * 10.0f))) / 10.0f;
        }
        if (z2) {
            return ((float) ((int) (f * 10.0f))) / 10.0f;
        }
        return 1.0f;
    }

    public boolean dispatchPopulateAccessibilityEvent(AccessibilityEvent accessibilityEvent) {
        return false;
    }

    public abstract int getCollapsedHeight();

    public int getWindowAnimations() {
        return 0;
    }

    public ActionMenuView(Context context) {
        this(context, (AttributeSet) null);
    }

    public ActionMenuView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        setBaselineAligned(false);
        this.d = new OpenCloseAnimator();
    }

    public void setPresenter(ActionMenuPresenter actionMenuPresenter) {
        this.c = actionMenuPresenter;
    }

    public ActionMenuPresenter getPresenter() {
        return this.c;
    }

    public void onConfigurationChanged(Configuration configuration) {
        if (Build.VERSION.SDK_INT >= 8) {
            super.onConfigurationChanged(configuration);
        }
        this.c.d(false);
        if (this.c != null && this.c.c()) {
            this.c.e(false);
            this.c.a();
        }
    }

    /* access modifiers changed from: protected */
    public void onMeasure(int i, int i2) {
        if (getChildCount() == 0) {
            setMeasuredDimension(0, 0);
        } else {
            super.onMeasure(i, i2);
        }
    }

    public void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        this.c.f(false);
    }

    public boolean isOverflowReserved() {
        return this.b;
    }

    public void setOverflowReserved(boolean z) {
        this.b = z;
    }

    public boolean filterLeftoverView(int i) {
        removeViewAt(i);
        return true;
    }

    /* access modifiers changed from: protected */
    public LayoutParams generateDefaultLayoutParams() {
        return new LayoutParams(-2, -2);
    }

    public LayoutParams generateLayoutParams(AttributeSet attributeSet) {
        return new LayoutParams(getContext(), attributeSet);
    }

    /* access modifiers changed from: protected */
    public LayoutParams generateLayoutParams(ViewGroup.LayoutParams layoutParams) {
        if (layoutParams instanceof LayoutParams) {
            return new LayoutParams((LayoutParams) layoutParams);
        }
        return generateDefaultLayoutParams();
    }

    /* access modifiers changed from: protected */
    public boolean checkLayoutParams(ViewGroup.LayoutParams layoutParams) {
        return layoutParams != null && (layoutParams instanceof LayoutParams);
    }

    public LayoutParams generateOverflowButtonLayoutParams() {
        LayoutParams generateDefaultLayoutParams = generateDefaultLayoutParams();
        generateDefaultLayoutParams.f8297a = true;
        return generateDefaultLayoutParams;
    }

    public boolean invokeItem(MenuItemImpl menuItemImpl) {
        return this.f8296a.a((MenuItem) menuItemImpl, 0);
    }

    public void initialize(MenuBuilder menuBuilder) {
        this.f8296a = menuBuilder;
    }

    /* access modifiers changed from: protected */
    public boolean hasDividerBeforeChildAt(int i) {
        View childAt = getChildAt(i - 1);
        View childAt2 = getChildAt(i);
        boolean z = false;
        if (i < getChildCount() && (childAt instanceof ActionMenuChildView)) {
            z = false | ((ActionMenuChildView) childAt).needsDividerAfter();
        }
        return (i <= 0 || !(childAt2 instanceof ActionMenuChildView)) ? z : z | ((ActionMenuChildView) childAt2).needsDividerBefore();
    }

    public static class LayoutParams extends LinearLayout.LayoutParams {
        @ViewDebug.ExportedProperty

        /* renamed from: a  reason: collision with root package name */
        public boolean f8297a;
        @ViewDebug.ExportedProperty
        public int b;
        @ViewDebug.ExportedProperty
        public int c;
        @ViewDebug.ExportedProperty
        public boolean d;
        @ViewDebug.ExportedProperty
        public boolean e;
        public boolean f;

        public LayoutParams(Context context, AttributeSet attributeSet) {
            super(context, attributeSet);
        }

        public LayoutParams(LayoutParams layoutParams) {
            super(layoutParams);
            this.f8297a = layoutParams.f8297a;
        }

        public LayoutParams(int i, int i2) {
            super(i, i2);
            this.f8297a = false;
        }

        public LayoutParams(int i, int i2, boolean z) {
            super(i, i2);
            this.f8297a = z;
        }
    }

    public void playOpenAnimator() {
        this.d.b();
    }

    public void playCloseAnimator() {
        this.d.c();
    }

    /* access modifiers changed from: protected */
    public float computeTranslationY(float f, boolean z, boolean z2) {
        float collapsedHeight = (float) getCollapsedHeight();
        if (z && z2) {
            f = ((double) f) < 0.5d ? f * 2.0f : (1.0f - f) * 2.0f;
        } else if (z2) {
            f = 1.0f - f;
        }
        return f * collapsedHeight;
    }

    public void onPageScrolled(int i, float f, boolean z, boolean z2) {
        setAlpha(computeAlpha(f, z, z2));
        float computeTranslationY = computeTranslationY(f, z, z2);
        if (!z || !z2 || getTranslationY() != 0.0f) {
            setTranslationY(computeTranslationY);
        }
        this.d.a(computeTranslationY);
    }

    class OpenCloseAnimator implements Animator.AnimatorListener {

        /* renamed from: a  reason: collision with root package name */
        boolean f8298a;
        Animator b;
        Animator c;

        public void onAnimationCancel(Animator animator) {
        }

        public void onAnimationRepeat(Animator animator) {
        }

        public void a(float f) {
            for (int i = 0; i < ActionMenuView.this.getChildCount(); i++) {
                ActionMenuView.this.getChildAt(i).setTranslationY(f);
            }
        }

        public OpenCloseAnimator() {
        }

        /* access modifiers changed from: package-private */
        public void a() {
            if (this.c == null) {
                this.c = ObjectAnimator.ofFloat(this, "TranslationY", new float[]{(float) ActionMenuView.this.getHeight()});
                this.c.setDuration((long) ActionMenuView.this.getResources().getInteger(17694720));
                this.c.addListener(this);
            }
        }

        /* access modifiers changed from: package-private */
        public void b() {
            d();
            this.f8298a = true;
            a(0.0f);
            ActionMenuView.this.startLayoutAnimation();
        }

        /* access modifiers changed from: package-private */
        public void c() {
            d();
            this.f8298a = false;
            this.c.start();
        }

        /* access modifiers changed from: package-private */
        public void d() {
            a();
            if (this.b != null) {
                this.b.cancel();
                this.b = null;
            }
        }

        public void onAnimationStart(Animator animator) {
            this.b = animator;
        }

        public void onAnimationEnd(Animator animator) {
            this.b = null;
        }
    }
}
