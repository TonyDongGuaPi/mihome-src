package com.miuipub.internal.widget;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.ActionMode;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import com.miuipub.internal.view.menu.ActionMenuView;
import miuipub.app.ActionBar;
import miuipub.os.Build;
import miuipub.v6.R;

public class ActionBarContainer extends FrameLayout implements ActionBar.FragmentViewPagerChangeListener {
    private static final int r = 0;
    private static final int s = 1;
    private static final int t = 2;
    private static final int u = 3;

    /* renamed from: a  reason: collision with root package name */
    private boolean f8318a;
    private View b;
    private ActionBarView c;
    private ActionBarContextView d;
    /* access modifiers changed from: private */
    public Animator e;
    private Drawable f;
    private Drawable[] g;
    private Drawable h;
    private Drawable i;
    private boolean j;
    private boolean k;
    private boolean l;
    private boolean m;
    private boolean n;
    private int o;
    private Rect p;
    private int q;
    private boolean v;
    private AnimatorListenerAdapter w;
    private AnimatorListenerAdapter x;

    public boolean onHoverEvent(MotionEvent motionEvent) {
        return true;
    }

    public void onPageScrollStateChanged(int i2) {
    }

    public void onPageSelected(int i2) {
    }

    public ActionMode startActionModeForChild(View view, ActionMode.Callback callback) {
        return null;
    }

    public ActionBarContainer(Context context) {
        this(context, (AttributeSet) null);
    }

    public ActionBarContainer(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        boolean z = false;
        this.v = false;
        this.w = new AnimatorListenerAdapter() {
            public void onAnimationEnd(Animator animator) {
                ActionBarContainer.this.setVisibility(8);
                Animator unused = ActionBarContainer.this.e = null;
            }
        };
        this.x = new AnimatorListenerAdapter() {
            public void onAnimationEnd(Animator animator) {
                Animator unused = ActionBarContainer.this.e = null;
            }
        };
        setBackgroundDrawable((Drawable) null);
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R.styleable.V6_ActionBar);
        this.f = obtainStyledAttributes.getDrawable(R.styleable.V6_ActionBar_android_background);
        this.g = new Drawable[]{this.f, obtainStyledAttributes.getDrawable(R.styleable.V6_ActionBar_v6_actionBarEmbededTabsBackground), obtainStyledAttributes.getDrawable(R.styleable.V6_ActionBar_v6_actionBarStackedBackground)};
        this.h = obtainStyledAttributes.getDrawable(R.styleable.V6_ActionBar_android_backgroundStacked);
        this.n = obtainStyledAttributes.getBoolean(R.styleable.V6_ActionBar_v6_customViewAutoFitSystemWindow, false);
        if (getId() == R.id.split_action_bar) {
            this.j = true;
            this.i = obtainStyledAttributes.getDrawable(R.styleable.V6_ActionBar_android_backgroundSplit);
            this.o = obtainStyledAttributes.getDimensionPixelSize(R.styleable.V6_ActionBar_android_height, 0);
        }
        obtainStyledAttributes.recycle();
        if (!this.j ? this.f == null && this.h == null : this.i == null) {
            z = true;
        }
        setWillNotDraw(z);
    }

    public void onFinishInflate() {
        super.onFinishInflate();
        this.c = (ActionBarView) findViewById(R.id.action_bar);
        this.d = (ActionBarContextView) findViewById(R.id.action_context_bar);
    }

    public void setActionBarContextView(ActionBarContextView actionBarContextView) {
        this.d = actionBarContextView;
    }

    public void setPendingInsets(Rect rect) {
        if (!this.j) {
            if (this.p == null) {
                this.p = new Rect();
            }
            this.p.set(rect);
        }
    }

    public Rect getPendingInsets() {
        return this.p;
    }

    public void setFragmentViewPagerMode(boolean z) {
        this.m = z;
    }

    /* access modifiers changed from: package-private */
    public int getCollapsedHeight() {
        if (!this.j) {
            return 0;
        }
        int i2 = 0;
        int i3 = 0;
        for (int i4 = 0; i4 < getChildCount(); i4++) {
            if (getChildAt(i4) instanceof ActionMenuView) {
                ActionMenuView actionMenuView = (ActionMenuView) getChildAt(i4);
                if (actionMenuView.getVisibility() == 0 && actionMenuView.getCollapsedHeight() > 0) {
                    i3++;
                    i2 = Math.max(i2, actionMenuView.getCollapsedHeight());
                }
            }
        }
        int max = Build.W ? Math.max(i2, getMeasuredHeight()) : i2;
        if (!this.m || (i3 == 1 && this.d.getAnimatedVisibility() == 0)) {
            return max;
        }
        return 0;
    }

    public void setPrimaryBackground(Drawable drawable) {
        Rect rect;
        if (this.f != null) {
            rect = this.f.getBounds();
            this.f.setCallback((Drawable.Callback) null);
            unscheduleDrawable(this.f);
        } else {
            rect = null;
        }
        this.f = drawable;
        boolean z = true;
        if (drawable != null) {
            drawable.setCallback(this);
            if (rect == null) {
                requestLayout();
            } else {
                this.f.setBounds(rect);
            }
            this.v = true;
        } else {
            this.v = false;
        }
        if (!this.j ? !(this.f == null && this.h == null) : this.i != null) {
            z = false;
        }
        setWillNotDraw(z);
        invalidate();
    }

    public void setStackedBackground(Drawable drawable) {
        if (this.h != null) {
            this.h.setCallback((Drawable.Callback) null);
            unscheduleDrawable(this.h);
        }
        this.h = drawable;
        if (drawable != null) {
            drawable.setCallback(this);
        }
        boolean z = false;
        if (!this.j ? this.f == null && this.h == null : this.i == null) {
            z = true;
        }
        setWillNotDraw(z);
        if (this.b != null) {
            this.b.setBackgroundDrawable(this.h);
        }
    }

    public void setSplitBackground(Drawable drawable) {
        if (this.i != null) {
            this.i.setCallback((Drawable.Callback) null);
            unscheduleDrawable(this.i);
        }
        this.i = drawable;
        if (drawable != null) {
            drawable.setCallback(this);
        }
        boolean z = false;
        if (!this.j ? this.f == null && this.h == null : this.i == null) {
            z = true;
        }
        setWillNotDraw(z);
        invalidate();
    }

    public int getContentHeight() {
        return this.o;
    }

    public void setVisibility(int i2) {
        super.setVisibility(i2);
        boolean z = i2 == 0;
        if (this.f != null) {
            this.f.setVisible(z, false);
        }
        if (this.h != null) {
            this.h.setVisible(z, false);
        }
        if (this.i != null) {
            this.i.setVisible(z, false);
        }
    }

    /* access modifiers changed from: protected */
    public boolean verifyDrawable(Drawable drawable) {
        return (drawable == this.f && !this.j) || (drawable == this.h && this.k) || ((drawable == this.i && this.j) || super.verifyDrawable(drawable));
    }

    /* access modifiers changed from: protected */
    public void drawableStateChanged() {
        super.drawableStateChanged();
        if (this.f != null && this.f.isStateful()) {
            this.f.setState(getDrawableState());
        }
        if (this.h != null && this.h.isStateful()) {
            this.h.setState(getDrawableState());
        }
        if (this.i != null && this.i.isStateful()) {
            this.i.setState(getDrawableState());
        }
    }

    public void setTransitioning(boolean z) {
        this.f8318a = z;
        setDescendantFocusability(z ? 393216 : 262144);
    }

    public boolean onInterceptTouchEvent(MotionEvent motionEvent) {
        return this.f8318a || super.onInterceptTouchEvent(motionEvent);
    }

    public boolean onTouchEvent(MotionEvent motionEvent) {
        return !this.j && super.onTouchEvent(motionEvent);
    }

    public View getTabContainer() {
        return this.b;
    }

    public void setTabContainer(ScrollingTabContainerView scrollingTabContainerView) {
        if (this.b != null) {
            removeView(this.b);
        }
        if (scrollingTabContainerView != null) {
            scrollingTabContainerView.setBackgroundDrawable(this.h);
            addView(scrollingTabContainerView);
            ViewGroup.LayoutParams layoutParams = scrollingTabContainerView.getLayoutParams();
            layoutParams.width = -1;
            layoutParams.height = -2;
            scrollingTabContainerView.setAllowCollapse(false);
            this.q = scrollingTabContainerView.getPaddingTop();
        } else if (this.b != null) {
            this.b.setBackgroundDrawable((Drawable) null);
        }
        this.b = scrollingTabContainerView;
    }

    public void onDraw(Canvas canvas) {
        if (getWidth() != 0 && getHeight() != 0) {
            if (this.j) {
                if (Build.W && this.i != null) {
                    this.i.draw(canvas);
                }
            } else if (this.f != null) {
                this.f.draw(canvas);
            }
        }
    }

    private void a(View view) {
        if (view != null && view.getVisibility() == 0) {
            if (view != this.c || !this.n) {
                ((ViewGroup.MarginLayoutParams) view.getLayoutParams()).topMargin = this.p != null ? this.p.top : 0;
            }
        }
    }

    public void onMeasure(int i2, int i3) {
        int i4;
        if (this.j) {
            a(i2, i3);
            return;
        }
        if (this.b != null) {
            this.b.setPadding(this.b.getPaddingLeft(), this.q, this.b.getPaddingRight(), this.b.getPaddingBottom());
        }
        a(this.c);
        ActionBarContextView actionBarContextView = this.d;
        if (actionBarContextView != null) {
            actionBarContextView.setContentInset(this.p != null ? this.p.top : 0);
        }
        super.onMeasure(i2, i3);
        boolean z = (this.c == null || this.c.getVisibility() == 8 || this.c.getMeasuredHeight() <= 0) ? false : true;
        if (z) {
            FrameLayout.LayoutParams layoutParams = (FrameLayout.LayoutParams) this.c.getLayoutParams();
            i4 = this.c.isCollapsed() ? layoutParams.topMargin : this.c.getMeasuredHeight() + layoutParams.topMargin + layoutParams.bottomMargin;
        } else {
            i4 = 0;
        }
        if (!(this.b == null || this.b.getVisibility() == 8 || View.MeasureSpec.getMode(i3) != Integer.MIN_VALUE)) {
            setMeasuredDimension(getMeasuredWidth(), Math.min(i4 + this.b.getMeasuredHeight(), View.MeasureSpec.getSize(i3)) + ((z || this.p == null) ? 0 : this.p.top));
        }
        int i5 = 0;
        for (int i6 = 0; i6 < getChildCount(); i6++) {
            View childAt = getChildAt(i6);
            if (childAt.getVisibility() == 0 && childAt.getMeasuredHeight() > 0 && childAt.getMeasuredWidth() > 0) {
                i5++;
            }
        }
        if (i5 == 0) {
            setMeasuredDimension(0, 0);
        }
    }

    private void a(int i2, int i3) {
        if (View.MeasureSpec.getMode(i2) == Integer.MIN_VALUE) {
            i2 = View.MeasureSpec.makeMeasureSpec(View.MeasureSpec.getSize(i2), 1073741824);
        }
        if (Build.W && this.o > 0) {
            i3 = View.MeasureSpec.makeMeasureSpec(this.o, 1073741824);
        }
        super.onMeasure(i2, i3);
        int childCount = getChildCount();
        int i4 = 0;
        for (int i5 = 0; i5 < childCount; i5++) {
            i4 = Math.max(i4, getChildAt(i5).getMeasuredHeight());
        }
        if (i4 == 0) {
            setMeasuredDimension(0, 0);
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:32:0x00ae  */
    /* JADX WARNING: Removed duplicated region for block: B:34:? A[RETURN, SYNTHETIC] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void onLayout(boolean r6, int r7, int r8, int r9, int r10) {
        /*
            r5 = this;
            super.onLayout(r6, r7, r8, r9, r10)
            int r6 = r5.getMeasuredHeight()
            android.view.View r8 = r5.b
            r10 = 0
            if (r8 == 0) goto L_0x0084
            android.view.View r8 = r5.b
            int r8 = r8.getVisibility()
            r0 = 8
            if (r8 == r0) goto L_0x0084
            android.view.View r8 = r5.b
            int r8 = r8.getMeasuredHeight()
            com.miuipub.internal.widget.ActionBarView r0 = r5.c
            if (r0 == 0) goto L_0x004b
            com.miuipub.internal.widget.ActionBarView r0 = r5.c
            int r0 = r0.getVisibility()
            if (r0 != 0) goto L_0x004b
            com.miuipub.internal.widget.ActionBarView r0 = r5.c
            int r0 = r0.getMeasuredHeight()
            if (r0 <= 0) goto L_0x004b
            android.view.View r0 = r5.b
            android.view.View r1 = r5.b
            int r1 = r1.getPaddingLeft()
            int r2 = r5.q
            android.view.View r3 = r5.b
            int r3 = r3.getPaddingRight()
            android.view.View r4 = r5.b
            int r4 = r4.getPaddingBottom()
            r0.setPadding(r1, r2, r3, r4)
            r0 = r8
            goto L_0x007c
        L_0x004b:
            android.graphics.Rect r0 = r5.p
            if (r0 == 0) goto L_0x0054
            android.graphics.Rect r0 = r5.p
            int r0 = r0.top
            goto L_0x0055
        L_0x0054:
            r0 = 0
        L_0x0055:
            int r8 = r8 + r0
            android.view.View r0 = r5.b
            android.view.View r1 = r5.b
            int r1 = r1.getPaddingLeft()
            android.graphics.Rect r2 = r5.p
            if (r2 == 0) goto L_0x006a
            android.graphics.Rect r2 = r5.p
            int r2 = r2.top
            int r3 = r5.q
            int r2 = r2 + r3
            goto L_0x006c
        L_0x006a:
            int r2 = r5.q
        L_0x006c:
            android.view.View r3 = r5.b
            int r3 = r3.getPaddingRight()
            android.view.View r4 = r5.b
            int r4 = r4.getPaddingBottom()
            r0.setPadding(r1, r2, r3, r4)
            r0 = 0
        L_0x007c:
            android.view.View r1 = r5.b
            int r8 = r6 - r8
            r1.layout(r7, r8, r9, r6)
            goto L_0x0085
        L_0x0084:
            r0 = 0
        L_0x0085:
            boolean r8 = r5.j
            r1 = 1
            if (r8 == 0) goto L_0x009c
            android.graphics.drawable.Drawable r6 = r5.i
            if (r6 == 0) goto L_0x00ab
            android.graphics.drawable.Drawable r6 = r5.i
            int r7 = r5.getMeasuredWidth()
            int r8 = r5.getMeasuredHeight()
            r6.setBounds(r10, r10, r7, r8)
            goto L_0x00ac
        L_0x009c:
            r5.a()
            android.graphics.drawable.Drawable r8 = r5.f
            if (r8 == 0) goto L_0x00ab
            android.graphics.drawable.Drawable r8 = r5.f
            int r9 = r9 - r7
            int r6 = r6 - r0
            r8.setBounds(r10, r10, r9, r6)
            goto L_0x00ac
        L_0x00ab:
            r1 = 0
        L_0x00ac:
            if (r1 == 0) goto L_0x00b1
            r5.invalidate()
        L_0x00b1:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.miuipub.internal.widget.ActionBarContainer.onLayout(boolean, int, int, int, int):void");
    }

    public void onWindowShow() {
        if (this.c.getMenuView() != null) {
            this.c.getMenuView().startLayoutAnimation();
        }
    }

    public void onWindowHide() {
        if (this.c.getMenuView() != null) {
            this.c.getMenuView().startLayoutAnimation();
        }
    }

    public void hide(boolean z) {
        if (this.e != null) {
            this.e.cancel();
        }
        if (!z || !this.j) {
            setVisibility(8);
            return;
        }
        this.e = ObjectAnimator.ofFloat(this, "TranslationY", new float[]{0.0f, (float) getHeight()});
        this.e.setDuration((long) getContext().getResources().getInteger(17694720));
        this.e.addListener(this.w);
        this.e.start();
    }

    public void show(boolean z) {
        if (this.e != null) {
            this.e.cancel();
        }
        setVisibility(0);
        if (!z) {
            setTranslationY(0.0f);
        } else if (this.j) {
            this.e = ObjectAnimator.ofFloat(this, "TranslationY", new float[]{(float) getHeight(), 0.0f});
            this.e.setDuration((long) getContext().getResources().getInteger(17694720));
            this.e.addListener(this.x);
            this.e.start();
            ((ActionMenuView) getChildAt(0)).startLayoutAnimation();
        }
    }

    public void onPageScrolled(int i2, float f2, boolean z, boolean z2) {
        ActionMenuView actionMenuView;
        if (this.j && (actionMenuView = (ActionMenuView) getChildAt(0)) != null) {
            actionMenuView.onPageScrolled(i2, f2, z, z2);
        }
    }

    public void draw(Canvas canvas) {
        super.draw(canvas);
        if (this.l) {
            post(new Runnable() {
                public void run() {
                    ActionBarContainer.this.show(true);
                }
            });
            this.l = false;
        }
    }

    private void a() {
        if (!this.v && !this.j && this.c != null && this.f != null && this.g != null && this.g.length >= 3) {
            char c2 = 0;
            if (this.c.hasEmbeddedTabs()) {
                c2 = 1;
                int displayOptions = this.c.getDisplayOptions();
                if (!((displayOptions & 2) == 0 && (displayOptions & 4) == 0 && (displayOptions & 16) == 0)) {
                    c2 = 2;
                }
            }
            if (this.g[c2] != null) {
                this.f = this.g[c2];
            }
        }
    }
}
