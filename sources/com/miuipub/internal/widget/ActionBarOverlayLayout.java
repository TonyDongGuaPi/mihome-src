package com.miuipub.internal.widget;

import android.animation.Animator;
import android.animation.ObjectAnimator;
import android.app.Activity;
import android.content.Context;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.os.IBinder;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import android.view.ActionMode;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.FrameLayout;
import com.miuipub.internal.view.menu.ContextMenuBuilder;
import com.miuipub.internal.view.menu.MenuBuilder;
import com.miuipub.internal.view.menu.MenuDialogHelper;
import com.miuipub.internal.view.menu.MenuPresenter;
import miuipub.app.ActionBar;
import miuipub.app.OnStatusBarChangeListener;
import miuipub.util.Versions;
import miuipub.v6.R;
import miuipub.view.SearchActionMode;

public class ActionBarOverlayLayout extends FrameLayout {
    private Rect A;
    private Rect B;
    private ContextMenuBuilder C;
    private MenuDialogHelper D;
    private ContextMenuCallback E;
    private OnStatusBarChangeListener F;

    /* renamed from: a  reason: collision with root package name */
    private ActionBar f8326a;
    /* access modifiers changed from: private */
    public ActionBarContainer b;
    private ActionBarContextView c;
    /* access modifiers changed from: private */
    public View d;
    /* access modifiers changed from: private */
    public ActionMode e;
    private Window.Callback f;
    private boolean g;
    private boolean h;
    private boolean i;
    private boolean j;
    private Drawable k;
    private int l;
    private Paint m;
    protected ActionBarContainer mActionBarTop;
    protected ActionBarView mActionBarView;
    protected View mContentView;
    private Bitmap n;
    private Bitmap o;
    private Bitmap p;
    private Bitmap q;
    private TypedValue r;
    private TypedValue s;
    private TypedValue t;
    private TypedValue u;
    private boolean v;
    private Rect w;
    private Rect x;
    private Rect y;
    private Rect z;

    public ActionBarOverlayLayout(Context context) {
        this(context, (AttributeSet) null);
    }

    public ActionBarOverlayLayout(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public ActionBarOverlayLayout(Context context, AttributeSet attributeSet, int i2) {
        super(context, attributeSet, i2);
        this.w = new Rect();
        this.x = new Rect();
        this.y = new Rect();
        this.z = new Rect();
        this.A = new Rect();
        this.B = new Rect();
        this.E = new ContextMenuCallback();
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R.styleable.V6_Window, i2, 0);
        if (obtainStyledAttributes.hasValue(R.styleable.V6_Window_v6_windowFixedWidthMajor)) {
            this.r = new TypedValue();
            obtainStyledAttributes.getValue(R.styleable.V6_Window_v6_windowFixedWidthMajor, this.r);
        }
        if (obtainStyledAttributes.hasValue(R.styleable.V6_Window_v6_windowFixedWidthMinor)) {
            this.s = new TypedValue();
            obtainStyledAttributes.getValue(R.styleable.V6_Window_v6_windowFixedWidthMinor, this.s);
        }
        if (obtainStyledAttributes.hasValue(R.styleable.V6_Window_v6_windowFixedHeightMajor)) {
            this.t = new TypedValue();
            obtainStyledAttributes.getValue(R.styleable.V6_Window_v6_windowFixedHeightMajor, this.t);
        }
        if (obtainStyledAttributes.hasValue(R.styleable.V6_Window_v6_windowFixedHeightMinor)) {
            this.u = new TypedValue();
            obtainStyledAttributes.getValue(R.styleable.V6_Window_v6_windowFixedHeightMinor, this.u);
        }
        this.j = obtainStyledAttributes.getBoolean(R.styleable.V6_Window_v6_contentAutoFitSystemWindow, false);
        if (this.j) {
            this.k = obtainStyledAttributes.getDrawable(R.styleable.V6_Window_v6_contentHeaderBackground);
        }
        obtainStyledAttributes.recycle();
    }

    public static ActionBarOverlayLayout getActionBarOverlayLayout(View view) {
        while (view != null) {
            if (view instanceof ActionBarOverlayLayout) {
                return (ActionBarOverlayLayout) view;
            }
            view = view.getParent() instanceof View ? (View) view.getParent() : null;
        }
        return null;
    }

    /* access modifiers changed from: protected */
    public boolean fitSystemWindows(Rect rect) {
        if (this.F != null) {
            this.F.a(rect.top);
        }
        this.z.set(rect);
        a(this.z, this.w);
        boolean z2 = false;
        if (this.mActionBarTop != null) {
            if (isTranslucentStatus()) {
                this.mActionBarTop.setPendingInsets(rect);
            }
            z2 = a(this.mActionBarTop, this.z, true, isRootSubDecor() && !isTranslucentStatus(), false, true);
        }
        boolean a2 = this.b != null ? a(this.b, this.z, true, false, true, true) | z2 : z2;
        if (!this.x.equals(this.w)) {
            this.x.set(this.w);
            a2 = true;
        }
        if (a2) {
            requestLayout();
        }
        return isRootSubDecor();
    }

    private void a(Rect rect, Rect rect2) {
        boolean isRootSubDecor = isRootSubDecor();
        boolean isTranslucentStatus = isTranslucentStatus();
        if (!isRootSubDecor) {
            rect.bottom = 0;
        }
        rect2.set(rect);
        if ((!isRootSubDecor || isTranslucentStatus) && !this.j) {
            rect2.top = 0;
        }
    }

    private boolean a(View view, Rect rect, boolean z2, boolean z3, boolean z4, boolean z5) {
        boolean z6;
        FrameLayout.LayoutParams layoutParams = (FrameLayout.LayoutParams) view.getLayoutParams();
        if (!z2 || layoutParams.leftMargin == rect.left) {
            z6 = false;
        } else {
            layoutParams.leftMargin = rect.left;
            z6 = true;
        }
        if (z3 && layoutParams.topMargin != rect.top) {
            layoutParams.topMargin = rect.top;
            z6 = true;
        }
        if (z5 && layoutParams.rightMargin != rect.right) {
            layoutParams.rightMargin = rect.right;
            z6 = true;
        }
        if (!z4 || layoutParams.bottomMargin == rect.bottom) {
            return z6;
        }
        layoutParams.bottomMargin = rect.bottom;
        return true;
    }

    /* access modifiers changed from: protected */
    public void onAttachedToWindow() {
        super.onAttachedToWindow();
        if (Versions.g()) {
            requestFitSystemWindows();
        }
    }

    public void requestFitSystemWindows() {
        super.requestFitSystemWindows();
        this.i = true;
    }

    /* access modifiers changed from: protected */
    public void onFinishInflate() {
        super.onFinishInflate();
        b();
    }

    /* access modifiers changed from: protected */
    public void onMeasure(int i2, int i3) {
        int a2 = a(i2);
        int b2 = b(i3);
        View view = this.mContentView;
        View view2 = this.d;
        int i4 = 0;
        int i5 = 0;
        int i6 = 0;
        for (int i7 = 0; i7 < getChildCount(); i7++) {
            View childAt = getChildAt(i7);
            if (!(childAt == view || childAt == view2 || childAt.getVisibility() == 8)) {
                View view3 = childAt;
                measureChildWithMargins(childAt, a2, 0, b2, 0);
                FrameLayout.LayoutParams layoutParams = (FrameLayout.LayoutParams) view3.getLayoutParams();
                int max = Math.max(i4, view3.getMeasuredWidth() + layoutParams.leftMargin + layoutParams.rightMargin);
                i5 = Math.max(i5, view3.getMeasuredHeight() + layoutParams.topMargin + layoutParams.bottomMargin);
                i4 = max;
                i6 = combineMeasuredStates(i6, view3.getMeasuredState());
            }
        }
        int measuredHeight = (this.mActionBarTop == null || this.mActionBarTop.getVisibility() != 0) ? 0 : this.mActionBarTop.getMeasuredHeight();
        int bottomInset = (this.mActionBarView == null || !this.mActionBarView.isSplitActionBar() || this.b == null) ? 0 : getBottomInset();
        if (isTranslucentStatus() && this.j) {
            if (this.k != null) {
                this.k.setBounds(0, 0, getRight() - getLeft(), this.w.top);
            } else {
                ViewGroup viewGroup = (ViewGroup) findViewById(16908290);
                if (viewGroup != null && viewGroup.getChildCount() == 1) {
                    View childAt2 = viewGroup.getChildAt(0);
                    if (measuredHeight <= 0) {
                        childAt2.setPadding(childAt2.getPaddingLeft(), this.z.top, childAt2.getPaddingRight(), childAt2.getPaddingBottom());
                    } else {
                        childAt2.setPadding(childAt2.getPaddingLeft(), 0, childAt2.getPaddingRight(), childAt2.getPaddingBottom());
                    }
                }
            }
        }
        this.B.set(this.z);
        this.y.set(this.w);
        if (isTranslucentStatus() && measuredHeight > 0) {
            this.y.top = 0;
        }
        if (!this.g) {
            this.y.top += measuredHeight;
            this.y.bottom += bottomInset;
        } else {
            if (!isTranslucentStatus()) {
                this.B.top += measuredHeight;
            } else if (measuredHeight > 0) {
                this.B.top = measuredHeight;
            }
            this.B.bottom += bottomInset;
        }
        a(view, this.y, true, true, true, true);
        if (!this.A.equals(this.B) || this.i) {
            this.A.set(this.B);
            super.fitSystemWindows(this.B);
            this.i = false;
        }
        measureChildWithMargins(view, a2, 0, b2, 0);
        FrameLayout.LayoutParams layoutParams2 = (FrameLayout.LayoutParams) view.getLayoutParams();
        int max2 = Math.max(i4, view.getMeasuredWidth() + layoutParams2.leftMargin + layoutParams2.rightMargin);
        int max3 = Math.max(i5, view.getMeasuredHeight() + layoutParams2.topMargin + layoutParams2.bottomMargin);
        int combineMeasuredStates = combineMeasuredStates(i6, view.getMeasuredState());
        if (view2 != null && view2.getVisibility() == 0) {
            measureChildWithMargins(view2, a2, 0, b2, 0);
        }
        setMeasuredDimension(resolveSizeAndState(Math.max(max2 + getPaddingLeft() + getPaddingRight(), getSuggestedMinimumWidth()), a2, combineMeasuredStates), resolveSizeAndState(Math.max(max3 + getPaddingTop() + getPaddingBottom(), getSuggestedMinimumHeight()), b2, combineMeasuredStates << 16));
        this.v = a();
        if (this.v && this.m == null) {
            this.m = new Paint();
            this.m.setAntiAlias(true);
            this.m.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.DST_OUT));
            Resources resources = getResources();
            this.n = BitmapFactory.decodeResource(resources, R.drawable.v6_popup_mask_1);
            this.o = BitmapFactory.decodeResource(resources, R.drawable.v6_popup_mask_2);
            this.p = BitmapFactory.decodeResource(resources, R.drawable.v6_popup_mask_3);
            this.q = BitmapFactory.decodeResource(resources, R.drawable.v6_popup_mask_4);
        }
    }

    private int a(int i2) {
        int mode = View.MeasureSpec.getMode(i2);
        int size = View.MeasureSpec.getSize(i2);
        if (mode != Integer.MIN_VALUE) {
            return i2;
        }
        DisplayMetrics displayMetrics = getContext().getResources().getDisplayMetrics();
        int i3 = 0;
        TypedValue typedValue = displayMetrics.widthPixels < displayMetrics.heightPixels ? this.s : this.r;
        if (typedValue == null || typedValue.type == 0) {
            return i2;
        }
        if (typedValue.type == 5) {
            i3 = (int) typedValue.getDimension(displayMetrics);
        } else if (typedValue.type == 6) {
            i3 = (int) typedValue.getFraction((float) displayMetrics.widthPixels, (float) displayMetrics.widthPixels);
        }
        return i3 > 0 ? View.MeasureSpec.makeMeasureSpec(Math.min(i3, size), 1073741824) : i2;
    }

    private int b(int i2) {
        int mode = View.MeasureSpec.getMode(i2);
        int size = View.MeasureSpec.getSize(i2);
        if (mode != Integer.MIN_VALUE) {
            return i2;
        }
        DisplayMetrics displayMetrics = getContext().getResources().getDisplayMetrics();
        int i3 = 0;
        TypedValue typedValue = displayMetrics.widthPixels < displayMetrics.heightPixels ? this.t : this.u;
        if (typedValue == null || typedValue.type == 0) {
            return i2;
        }
        if (typedValue.type == 5) {
            i3 = (int) typedValue.getDimension(displayMetrics);
        } else if (typedValue.type == 6) {
            i3 = (int) typedValue.getFraction((float) displayMetrics.heightPixels, (float) displayMetrics.heightPixels);
        }
        return i3 > 0 ? View.MeasureSpec.makeMeasureSpec(Math.min(i3, size), 1073741824) : i2;
    }

    /* access modifiers changed from: protected */
    public void dispatchDraw(Canvas canvas) {
        if (this.j && this.k != null) {
            this.k.setBounds(0, 0, getRight() - getLeft(), this.w.top);
            this.k.draw(canvas);
        }
        if (this.v) {
            canvas.saveLayer(0.0f, 0.0f, (float) getWidth(), (float) getHeight(), (Paint) null, 31);
            super.dispatchDraw(canvas);
            canvas.drawBitmap(this.n, 0.0f, 0.0f, this.m);
            canvas.drawBitmap(this.o, (float) (getWidth() - this.o.getWidth()), 0.0f, this.m);
            canvas.drawBitmap(this.p, 0.0f, (float) (getHeight() - this.p.getHeight()), this.m);
            canvas.drawBitmap(this.q, (float) (getWidth() - this.q.getWidth()), (float) (getHeight() - this.q.getHeight()), this.m);
            canvas.restore();
            return;
        }
        super.dispatchDraw(canvas);
    }

    private boolean a() {
        int[] iArr = new int[2];
        getLocationOnScreen(iArr);
        DisplayMetrics displayMetrics = getResources().getDisplayMetrics();
        if (iArr[0] == 0 || iArr[1] == 0 || iArr[0] + getMeasuredWidth() == displayMetrics.widthPixels || iArr[1] + getMeasuredHeight() == displayMetrics.heightPixels) {
            return false;
        }
        return true;
    }

    /* access modifiers changed from: protected */
    public int getBottomInset() {
        if (this.b != null) {
            return this.b.getCollapsedHeight();
        }
        return 0;
    }

    public ActionBar getActionBar() {
        return this.f8326a;
    }

    public void setActionBar(ActionBar actionBar) {
        this.f8326a = actionBar;
    }

    public void setOverlayMode(boolean z2) {
        this.g = z2;
    }

    public View getContentView() {
        return this.mContentView;
    }

    public ContentMaskAnimator getContentMaskAnimator(View.OnClickListener onClickListener) {
        return new ContentMaskAnimator(onClickListener);
    }

    public ActionBarView getActionBarView() {
        return this.mActionBarView;
    }

    public Window.Callback getCallback() {
        return this.f;
    }

    public void setCallback(Window.Callback callback) {
        this.f = callback;
    }

    public void setTranslucentStatus(int i2) {
        if (this.l != i2) {
            this.l = i2;
            requestFitSystemWindows();
        }
    }

    private void b() {
        if (this.mContentView == null) {
            this.mContentView = findViewById(16908290);
            this.d = findViewById(R.id.content_mask);
            this.mActionBarTop = (ActionBarContainer) findViewById(R.id.action_bar_container);
            if (this.mActionBarTop != null) {
                this.c = (ActionBarContextView) this.mActionBarTop.findViewById(R.id.action_context_bar);
                this.mActionBarView = (ActionBarView) this.mActionBarTop.findViewById(R.id.action_bar);
            }
            this.b = (ActionBarContainer) findViewById(R.id.split_action_bar);
        }
    }

    public boolean isRootSubDecor() {
        return this.h;
    }

    public boolean isTranslucentStatus() {
        return this.l != 0;
    }

    public void setRootSubDecor(boolean z2) {
        this.h = z2;
    }

    public boolean dispatchKeyEvent(KeyEvent keyEvent) {
        if (super.dispatchKeyEvent(keyEvent)) {
            return true;
        }
        if (a(keyEvent)) {
            if (this.e != null) {
                if (this.c != null && this.c.hideOverflowMenu()) {
                    return true;
                }
                this.e.finish();
                this.e = null;
                return true;
            } else if (this.mActionBarView == null || !this.mActionBarView.hideOverflowMenu()) {
                return false;
            } else {
                return true;
            }
        }
        return false;
    }

    private boolean a(KeyEvent keyEvent) {
        return keyEvent.getKeyCode() == 4 && keyEvent.getAction() == 1;
    }

    public ActionMode startActionModeForChild(View view, ActionMode.Callback callback) {
        return startActionMode(view, callback);
    }

    public ActionMode startActionMode(ActionMode.Callback callback) {
        if (this.e != null) {
            this.e.finish();
        }
        ActionMode actionMode = null;
        this.e = null;
        if (getCallback() != null) {
            actionMode = getCallback().onWindowStartingActionMode(a(callback));
        }
        if (actionMode != null) {
            this.e = actionMode;
        }
        if (!(this.e == null || getCallback() == null)) {
            getCallback().onActionModeStarted(this.e);
        }
        return this.e;
    }

    public ActionMode startActionMode(View view, ActionMode.Callback callback) {
        if (!(view instanceof ActionBarOverlayLayout)) {
            return startActionMode(callback);
        }
        if (this.e != null) {
            this.e.finish();
        }
        this.e = view.startActionMode(a(callback));
        return this.e;
    }

    private ActionModeCallbackWrapper a(ActionMode.Callback callback) {
        if (callback instanceof SearchActionMode.Callback) {
            return new SearchActionModeCallbackWrapper(callback);
        }
        return new ActionModeCallbackWrapper(callback);
    }

    private class ActionModeCallbackWrapper implements ActionMode.Callback {
        private ActionMode.Callback b;

        public ActionModeCallbackWrapper(ActionMode.Callback callback) {
            this.b = callback;
        }

        public boolean onCreateActionMode(ActionMode actionMode, Menu menu) {
            return this.b.onCreateActionMode(actionMode, menu);
        }

        public boolean onPrepareActionMode(ActionMode actionMode, Menu menu) {
            return this.b.onPrepareActionMode(actionMode, menu);
        }

        public boolean onActionItemClicked(ActionMode actionMode, MenuItem menuItem) {
            return this.b.onActionItemClicked(actionMode, menuItem);
        }

        public void onDestroyActionMode(ActionMode actionMode) {
            this.b.onDestroyActionMode(actionMode);
            if (ActionBarOverlayLayout.this.getCallback() != null) {
                ActionBarOverlayLayout.this.getCallback().onActionModeFinished(actionMode);
            }
            ActionMode unused = ActionBarOverlayLayout.this.e = null;
        }
    }

    private class SearchActionModeCallbackWrapper extends ActionModeCallbackWrapper implements SearchActionMode.Callback {
        public SearchActionModeCallbackWrapper(ActionMode.Callback callback) {
            super(callback);
        }
    }

    public class ContentMaskAnimator implements Animator.AnimatorListener {
        private ObjectAnimator b;
        private ObjectAnimator c;
        private View.OnClickListener d;

        public void onAnimationRepeat(Animator animator) {
        }

        private ContentMaskAnimator(View.OnClickListener onClickListener) {
            this.d = onClickListener;
            this.b = ObjectAnimator.ofFloat(ActionBarOverlayLayout.this.d, "alpha", new float[]{0.0f, 1.0f});
            this.b.addListener(this);
            this.c = ObjectAnimator.ofFloat(ActionBarOverlayLayout.this.d, "alpha", new float[]{1.0f, 0.0f});
            this.c.addListener(this);
        }

        public Animator a() {
            return this.b;
        }

        public Animator b() {
            return this.c;
        }

        public void onAnimationStart(Animator animator) {
            if (animator == this.b) {
                ActionBarOverlayLayout.this.d.setVisibility(0);
                ActionBarOverlayLayout.this.d.bringToFront();
                ActionBarOverlayLayout.this.b.bringToFront();
                ActionBarOverlayLayout.this.d.setOnClickListener(this.d);
            }
        }

        public void onAnimationEnd(Animator animator) {
            if (ActionBarOverlayLayout.this.d.getAlpha() == 0.0f) {
                ActionBarOverlayLayout.this.b.bringToFront();
                ActionBarOverlayLayout.this.d.setOnClickListener((View.OnClickListener) null);
                ActionBarOverlayLayout.this.d.setVisibility(8);
            }
        }

        public void onAnimationCancel(Animator animator) {
            if (animator == this.c) {
                ActionBarOverlayLayout.this.b.bringToFront();
                ActionBarOverlayLayout.this.d.setOnClickListener((View.OnClickListener) null);
            }
        }
    }

    private class ContextMenuCallback implements MenuBuilder.Callback, MenuPresenter.Callback {
        private MenuDialogHelper b;

        public void c(MenuBuilder menuBuilder) {
        }

        private ContextMenuCallback() {
        }

        public void a(MenuBuilder menuBuilder) {
            if (a() != null) {
                a().onPanelClosed(6, menuBuilder.q());
            }
        }

        /* access modifiers changed from: package-private */
        public Activity a() {
            Context context = ActionBarOverlayLayout.this.getRootView().getContext();
            if (context instanceof Activity) {
                return (Activity) context;
            }
            return null;
        }

        public void b(MenuBuilder menuBuilder, boolean z) {
            if (menuBuilder.q() != menuBuilder) {
                a(menuBuilder);
            }
            if (z) {
                if (a() != null) {
                    a().onPanelClosed(6, menuBuilder);
                }
                ActionBarOverlayLayout.this.c();
                if (this.b != null) {
                    this.b.a();
                    this.b = null;
                }
            }
        }

        public boolean b(MenuBuilder menuBuilder) {
            if (menuBuilder == null) {
                return false;
            }
            menuBuilder.a((MenuBuilder.Callback) this);
            this.b = new MenuDialogHelper(menuBuilder);
            this.b.a((IBinder) null);
            return true;
        }

        public boolean a(MenuBuilder menuBuilder, MenuItem menuItem) {
            if (a() != null) {
                return a().onMenuItemSelected(6, menuItem);
            }
            return false;
        }
    }

    /* access modifiers changed from: private */
    public void c() {
        if (this.D != null) {
            this.D.a();
            this.C = null;
        }
    }

    public void setOnStatusBarChangeListener(OnStatusBarChangeListener onStatusBarChangeListener) {
        this.F = onStatusBarChangeListener;
    }

    public void onFinishAdjustHierarchy() {
        this.mContentView = findViewById(16908290);
    }
}
