package com.mi.global.shop.widget.pulltorefresh;

import android.content.Context;
import android.content.res.TypedArray;
import android.os.Build;
import android.support.v4.view.ViewCompat;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.Transformation;
import android.widget.AbsListView;
import com.mi.global.shop.util.SkinUtil;
import com.mi.util.Coder;

public class SimplePullToRefreshLayout extends ViewGroup implements IPullToRefresh<ViewGroup> {

    /* renamed from: a  reason: collision with root package name */
    private static final String f7273a = "SimplePullToRefreshLayout";
    private static final State b = State.RESET;
    private static final float c = 2.0f;
    private static final float d = 2.2222223f;
    private static final int e = 0;
    private static final boolean f = false;
    private final Runnable A = new Runnable() {
        public void run() {
            boolean unused = SimplePullToRefreshLayout.this.o = true;
            SimplePullToRefreshLayout.this.b(SimplePullToRefreshLayout.this.n + SimplePullToRefreshLayout.this.getPaddingTop(), SimplePullToRefreshLayout.this.F);
        }
    };
    private final Runnable B = new Runnable() {
        public void run() {
            boolean unused = SimplePullToRefreshLayout.this.o = true;
            SimplePullToRefreshLayout.this.a(SimplePullToRefreshLayout.this.n + SimplePullToRefreshLayout.this.getPaddingTop(), SimplePullToRefreshLayout.this.E);
        }
    };
    private final Animation C = new Animation() {
        public void applyTransformation(float f, Transformation transformation) {
            int paddingTop = SimplePullToRefreshLayout.this.getPaddingTop();
            if (SimplePullToRefreshLayout.this.k != SimplePullToRefreshLayout.this.m) {
                paddingTop = SimplePullToRefreshLayout.this.k + ((int) (((float) (SimplePullToRefreshLayout.this.m - SimplePullToRefreshLayout.this.k)) * f));
            }
            int top = paddingTop - SimplePullToRefreshLayout.this.h.getTop();
            int top2 = SimplePullToRefreshLayout.this.h.getTop();
            if (top + top2 < 0) {
                top = 0 - top2;
            }
            SimplePullToRefreshLayout.this.setTargetOffsetTopAndBottom(top);
        }
    };
    private final Animation D = new Animation() {
        public void applyTransformation(float f, Transformation transformation) {
            int paddingTop = SimplePullToRefreshLayout.this.getPaddingTop();
            if (SimplePullToRefreshLayout.this.k != SimplePullToRefreshLayout.this.m || SimplePullToRefreshLayout.this.getState() == State.MANUAL_REFRESHING) {
                paddingTop = SimplePullToRefreshLayout.this.k + ((int) (((float) ((SimplePullToRefreshLayout.this.m - SimplePullToRefreshLayout.this.k) + SimplePullToRefreshLayout.this.l)) * f));
            }
            int top = paddingTop - SimplePullToRefreshLayout.this.h.getTop();
            int top2 = SimplePullToRefreshLayout.this.h.getTop();
            if (top + top2 < 0) {
                top = 0 - top2;
            }
            SimplePullToRefreshLayout.this.setTargetOffsetTopAndBottom(top);
        }
    };
    /* access modifiers changed from: private */
    public final Animation.AnimationListener E = new BaseAnimationListener() {
        public void onAnimationEnd(Animation animation) {
            int unused = SimplePullToRefreshLayout.this.n = 0;
        }
    };
    /* access modifiers changed from: private */
    public final Animation.AnimationListener F = new BaseAnimationListener() {
        public void onAnimationEnd(Animation animation) {
            int unused = SimplePullToRefreshLayout.this.n = SimplePullToRefreshLayout.this.l;
            if (SimplePullToRefreshLayout.this.p != null) {
                SimplePullToRefreshLayout.this.p.onRefresh();
            }
        }
    };
    private State g = b;
    /* access modifiers changed from: private */
    public View h;
    private float i;
    private long j;
    /* access modifiers changed from: private */
    public int k;
    /* access modifiers changed from: private */
    public int l;
    /* access modifiers changed from: private */
    public int m;
    protected LoadingLayout mHeaderLoadingLayout;
    /* access modifiers changed from: private */
    public int n;
    /* access modifiers changed from: private */
    public boolean o;
    /* access modifiers changed from: private */
    public OnRefreshListener p;
    private OnStateChangeListener q;
    private OnRefreshEndListener r;
    private DecelerateInterpolator s;
    private DecelerateInterpolator t;
    private OnContentOffsetListener u;
    private float v;
    private float w;
    private int x;
    private final int y = 100;
    private boolean z = false;

    public interface OnContentOffsetListener {
        void a(int i);
    }

    public interface OnRefreshEndListener {
        void a();
    }

    public interface OnRefreshListener {
        void onRefresh();
    }

    public interface OnStateChangeListener {
        void a(String str);
    }

    public boolean isPullToRefreshEnabled() {
        return true;
    }

    public SimplePullToRefreshLayout(Context context) {
        super(context);
        a(context, (AttributeSet) null, 0, false);
    }

    public SimplePullToRefreshLayout(Context context, boolean z2) {
        super(context);
        a(context, (AttributeSet) null, 0, z2);
    }

    public SimplePullToRefreshLayout(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        a(context, attributeSet, 0, false);
    }

    public SimplePullToRefreshLayout(Context context, AttributeSet attributeSet, int i2) {
        super(context, attributeSet, i2);
        a(context, attributeSet, i2, false);
    }

    private void a(Context context, AttributeSet attributeSet, int i2, boolean z2) {
        float f2 = context.getResources().getDisplayMetrics().density;
        if (SkinUtil.al && SkinUtil.c(SkinUtil.I) != null) {
            this.z = true;
        }
        this.i = (float) ViewConfiguration.get(context).getScaledTouchSlop();
        this.mHeaderLoadingLayout = a(context, (TypedArray) null, z2);
        this.s = new DecelerateInterpolator(c);
        this.t = new DecelerateInterpolator(4.0f);
        this.j = 600;
        this.x = (int) (f2 * 0.0f);
    }

    /* access modifiers changed from: protected */
    public void onFinishInflate() {
        super.onFinishInflate();
        updateLayout();
    }

    public void updateLayout() {
        b();
        removeAllViews();
        addView(this.mHeaderLoadingLayout);
        addView(this.h);
    }

    /* access modifiers changed from: protected */
    public void onMeasure(int i2, int i3) {
        super.onMeasure(i2, i3);
        if (getChildCount() > 2 && !isInEditMode()) {
            throw new IllegalStateException("SimplePullToRefreshLayout can host only one direct child");
        } else if (getChildCount() == 2) {
            getChildAt(0).measure(View.MeasureSpec.makeMeasureSpec((getMeasuredWidth() - getPaddingLeft()) - getPaddingRight(), 1073741824), View.MeasureSpec.makeMeasureSpec((getMeasuredHeight() - getPaddingTop()) - getPaddingBottom(), Integer.MIN_VALUE));
            getChildAt(1).measure(View.MeasureSpec.makeMeasureSpec((getMeasuredWidth() - getPaddingLeft()) - getPaddingRight(), 1073741824), View.MeasureSpec.makeMeasureSpec((getMeasuredHeight() - getPaddingTop()) - getPaddingBottom(), 1073741824));
        }
    }

    /* access modifiers changed from: protected */
    public void onLayout(boolean z2, int i2, int i3, int i4, int i5) {
        int measuredWidth = getMeasuredWidth();
        int measuredHeight = getMeasuredHeight();
        if (getChildCount() != 0) {
            int paddingLeft = getPaddingLeft();
            int paddingTop = this.n + getPaddingTop();
            int paddingLeft2 = (measuredWidth - getPaddingLeft()) - getPaddingRight();
            int paddingTop2 = (measuredHeight - getPaddingTop()) - getPaddingBottom();
            int measuredHeight2 = this.mHeaderLoadingLayout.getMeasuredHeight();
            if (!this.z || this.mHeaderLoadingLayout.getGif_bg() == null) {
                this.l = measuredHeight2;
            } else {
                this.l = Coder.a(100.0f);
            }
            if (getState() == State.RESET) {
                this.mHeaderLoadingLayout.layout(paddingLeft, (-measuredHeight2) + paddingTop, paddingLeft2, paddingTop);
            } else {
                this.mHeaderLoadingLayout.layout(paddingLeft, (-measuredHeight2) + paddingTop + this.x, paddingLeft2, this.x + paddingTop);
            }
            if (getChildCount() >= 2) {
                getChildAt(1).layout(paddingLeft, paddingTop, paddingLeft2 + paddingLeft, paddingTop2 + paddingTop);
            }
        }
    }

    public boolean onTouchEvent(MotionEvent motionEvent) {
        if (!isPullToRefreshEnabled()) {
            return false;
        }
        if (isRefreshing()) {
            return super.onInterceptTouchEvent(motionEvent);
        }
        switch (motionEvent.getAction()) {
            case 0:
                float y2 = motionEvent.getY();
                this.w = y2;
                this.v = y2;
                return false;
            case 1:
            case 3:
                if (this.g == State.RELEASE_TO_REFRESH && this.p != null) {
                    setState(State.REFRESHING);
                    return true;
                } else if (isRefreshing()) {
                    return true;
                } else {
                    setState(State.RESET);
                    return false;
                }
            case 2:
                if (this.o) {
                    return false;
                }
                float y3 = motionEvent.getY() - this.w;
                if (y3 <= this.i) {
                    return false;
                }
                a(Math.round(y3 / d));
                this.v = motionEvent.getY();
                a();
                return true;
            default:
                return false;
        }
    }

    private void a() {
        int i2;
        int round = Math.round(Math.min(this.w - this.v, 0.0f) / d);
        if (!this.z || this.mHeaderLoadingLayout.getGif_bg() == null) {
            i2 = this.mHeaderLoadingLayout.getMeasuredHeight();
        } else {
            i2 = Coder.a(100.0f);
        }
        if (this.g != State.PULL_TO_REFRESH && i2 >= Math.abs(round)) {
            setState(State.PULL_TO_REFRESH);
        } else if (this.g == State.PULL_TO_REFRESH && i2 < Math.abs(round)) {
            setState(State.RELEASE_TO_REFRESH);
        }
    }

    public boolean onInterceptTouchEvent(MotionEvent motionEvent) {
        int action = motionEvent.getAction();
        boolean z2 = false;
        if (this.o && action == 0) {
            float y2 = motionEvent.getY();
            this.w = y2;
            this.v = y2;
            this.o = false;
        }
        if (isEnabled() && !this.o && !canChildScrollUp()) {
            z2 = onTouchEvent(motionEvent);
        }
        return !z2 ? super.onInterceptTouchEvent(motionEvent) : z2;
    }

    public boolean canChildScrollUp() {
        if (Build.VERSION.SDK_INT < 14) {
            if (this.h instanceof AbsListView) {
                AbsListView absListView = (AbsListView) this.h;
                if (absListView.getChildCount() <= 0) {
                    return false;
                }
                if (absListView.getFirstVisiblePosition() > 0 || absListView.getChildAt(0).getTop() < absListView.getPaddingTop()) {
                    return true;
                }
                return false;
            } else if (this.h.getScrollY() > 0) {
                return true;
            } else {
                return false;
            }
        } else if (this.h == null) {
            return false;
        } else {
            return ViewCompat.canScrollVertically(this.h, -1);
        }
    }

    private void b() {
        if (this.h != null) {
            return;
        }
        if (getChildCount() <= 1 || isInEditMode()) {
            this.h = getChildAt(0);
            this.m = this.h.getTop() + getPaddingTop();
            return;
        }
        throw new IllegalStateException("SimplePullToRefreshLayout can host only one direct child");
    }

    public enum State {
        RESET(0),
        PULL_TO_REFRESH(1),
        RELEASE_TO_REFRESH(2),
        REFRESHING(8),
        MANUAL_REFRESHING(9);
        
        private int mIntValue;

        static State mapIntToValue(int i) {
            for (State state : values()) {
                if (i == state.getIntValue()) {
                    return state;
                }
            }
            return RESET;
        }

        private State(int i) {
            this.mIntValue = i;
        }

        /* access modifiers changed from: package-private */
        public int getIntValue() {
            return this.mIntValue;
        }
    }

    private LoadingLayout a(Context context, TypedArray typedArray, boolean z2) {
        if (this.z) {
            return new FestivalLoadingLayout(context, typedArray, 0);
        }
        return new FrameLoadingLayout(context, typedArray, z2);
    }

    private void a(int i2) {
        int top = this.h.getTop();
        if (i2 < 0) {
            i2 = 0;
        }
        setTargetOffsetTopAndBottom((i2 - top) + getPaddingTop());
    }

    /* access modifiers changed from: private */
    public void setTargetOffsetTopAndBottom(int i2) {
        this.h.offsetTopAndBottom(i2);
        this.n = this.h.getTop() - getPaddingTop();
        this.mHeaderLoadingLayout.offsetTopAndBottom(i2);
        if (Build.VERSION.SDK_INT < 11) {
            invalidate();
        }
        if (this.u != null) {
            this.u.a(this.h.getTop());
        }
        Log.d("OffsetTopAndBottom", "===========" + this.h.getTop());
    }

    public void setOnContentOffsetListener(OnContentOffsetListener onContentOffsetListener) {
        this.u = onContentOffsetListener;
    }

    /* access modifiers changed from: package-private */
    public final void setState(State state) {
        if (!(this.q == null || this.g == state)) {
            this.q.a(state.toString());
        }
        this.g = state;
        switch (this.g) {
            case RESET:
                this.mHeaderLoadingLayout.reset();
                post(this.B);
                return;
            case PULL_TO_REFRESH:
                this.mHeaderLoadingLayout.pullToRefresh();
                return;
            case RELEASE_TO_REFRESH:
                this.mHeaderLoadingLayout.releaseToRefresh();
                return;
            case REFRESHING:
            case MANUAL_REFRESHING:
                this.mHeaderLoadingLayout.refreshing();
                post(this.A);
                return;
            default:
                return;
        }
    }

    public State getState() {
        return this.g;
    }

    public boolean isRefreshing() {
        return this.g == State.REFRESHING || this.g == State.MANUAL_REFRESHING;
    }

    public void onRefreshComplete() {
        if (isRefreshing()) {
            setState(State.RESET);
            if (this.r != null) {
                this.r.a();
            }
        }
    }

    public void setOnRefreshListener(OnRefreshListener onRefreshListener) {
        this.p = onRefreshListener;
    }

    public void setRefreshing() {
        if (this.p == null) {
            throw new IllegalStateException("mRefreshListener can not be null");
        } else if (!isRefreshing()) {
            setState(State.MANUAL_REFRESHING);
        }
    }

    /* access modifiers changed from: private */
    public void a(int i2, Animation.AnimationListener animationListener) {
        this.k = i2;
        this.C.reset();
        this.C.setDuration(this.j);
        this.C.setAnimationListener(animationListener);
        this.C.setInterpolator(this.s);
        this.h.startAnimation(this.C);
    }

    /* access modifiers changed from: private */
    public void b(int i2, Animation.AnimationListener animationListener) {
        this.k = i2;
        this.D.reset();
        this.D.setAnimationListener(animationListener);
        if (this.z) {
            if (SkinUtil.am != 0) {
                this.D.setDuration((long) SkinUtil.am);
            } else {
                this.D.setDuration(this.j * 3);
            }
            this.D.setInterpolator(this.t);
        } else {
            this.D.setDuration(this.j);
            this.D.setInterpolator(this.s);
        }
        this.h.startAnimation(this.D);
    }

    private class BaseAnimationListener implements Animation.AnimationListener {
        public void onAnimationEnd(Animation animation) {
        }

        public void onAnimationRepeat(Animation animation) {
        }

        public void onAnimationStart(Animation animation) {
        }

        private BaseAnimationListener() {
        }
    }

    public void setTransparentViewHeight(int i2) {
        this.x = (int) (((float) i2) * getContext().getResources().getDisplayMetrics().density);
    }

    public void setOnRefreshEndListener(OnRefreshEndListener onRefreshEndListener) {
        this.r = onRefreshEndListener;
    }

    public boolean isUsingFestivalStyle() {
        return this.z;
    }

    public void setUsingFestivalStyle(boolean z2) {
        this.z = z2;
    }

    public void setOnStateChangeListener(OnStateChangeListener onStateChangeListener) {
        this.q = onStateChangeListener;
    }
}
