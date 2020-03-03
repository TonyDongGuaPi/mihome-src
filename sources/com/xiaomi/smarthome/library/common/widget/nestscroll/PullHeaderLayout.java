package com.xiaomi.smarthome.library.common.widget.nestscroll;

import android.animation.Animator;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.support.annotation.Nullable;
import android.support.v4.view.MotionEventCompat;
import android.support.v4.view.NestedScrollingChild;
import android.support.v4.view.NestedScrollingChildHelper;
import android.support.v4.view.NestedScrollingParent;
import android.support.v4.view.NestedScrollingParentHelper;
import android.support.v4.view.VelocityTrackerCompat;
import android.support.v4.view.ViewCompat;
import android.support.v4.widget.ScrollerCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.VelocityTracker;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.view.animation.DecelerateInterpolator;
import android.widget.AbsListView;
import android.widget.ImageView;
import com.xiaomi.smarthome.R;
import com.xiaomi.smarthome.framework.log.LogUtil;
import com.xiaomi.smarthome.library.common.widget.nestscroll.internal.SimpleAnimatorListener;
import com.xiaomi.youpin.login.api.LoginErrorCode;
import java.util.ArrayList;
import java.util.List;
import org.jacoco.agent.rt.internal_8ff85ea.asm.Opcodes;

public class PullHeaderLayout extends ViewGroup implements NestedScrollingChild, NestedScrollingParent {
    static final int ACTION_BUTTON_CENTER = UIUtils.a(40);
    static final int ACTION_ICON_SIZE = UIUtils.a(32);
    protected static final boolean D = true;
    public static final int DEFAULT_EXPAND = UIUtils.a(260);
    public static final int DEFAULT_HEIGHT = UIUtils.a((int) Opcodes.dg);
    public static final int DEFAULT_SHRINK = UIUtils.a(63);
    public static final int DIRECTION_DOWN = 2;
    public static final int DIRECTION_UP = 1;
    protected static final int INVALID_POINTER = -1;
    public static final int STATE_BOUNCE = 3;
    public static final int STATE_DRAG = 1;
    public static final int STATE_FLING = 2;
    public static final int STATE_IDLE = 0;
    protected static final String TAG = "PullHeaderLayout";
    private static final float b = 0.97f;
    private static final int f = 800;
    private static final int g = 250;
    private static final long h = 10;
    private static final float u = 0.5f;
    /* access modifiers changed from: private */

    /* renamed from: a  reason: collision with root package name */
    public float f19026a;
    /* access modifiers changed from: private */
    public boolean c;
    private Runnable d;
    boolean dragOverHeight;
    private boolean e;
    /* access modifiers changed from: private */
    public View i;
    /* access modifiers changed from: private */
    public View j;
    private int k;
    private int l;
    private int m;
    protected Drawable mActionDrawable;
    protected int mActivePointerId;
    protected ValueAnimator mBounceAnim;
    protected final NestedScrollingChildHelper mChildHelper;
    protected View mContent;
    protected int mContentId;
    protected int mDirection;
    protected ImageView mFlyView;
    boolean mFromNestedScroll;
    protected HeaderController mHeaderController;
    protected int mHeaderId;
    protected View mHeaderView;
    protected boolean mIsBeingDragged;
    protected int mLastMotionY;
    protected int mMaximumVelocity;
    protected int mMinimumVelocity;
    protected int mNestedYOffset;
    boolean mOnNotifiedHeaderScrollEnd;
    protected final NestedScrollingParentHelper mParentHelper;
    protected IPullHeader mPullHeaderView;
    protected OnPullListener mPullListener;
    protected int mPullState;
    protected final int[] mScrollConsumed;
    protected final int[] mScrollOffset;
    protected ScrollerCompat mScroller;
    protected int mTouchSlop;
    protected VelocityTracker mVelocityTracker;
    private boolean n;
    private float o;
    private float p;
    private boolean q;
    private State r;
    private OnRefreshListener s;
    /* access modifiers changed from: private */
    public boolean t;
    private Runnable v;
    private Runnable w;
    private List<RefreshHeader> x;
    private boolean y;

    public interface OnPullListener {
        void a(PullHeaderLayout pullHeaderLayout, int i, float f);
    }

    public interface OnRefreshListener {
        void a();
    }

    public enum State {
        RESET,
        PULL,
        LOADING,
        State,
        COMPLETE
    }

    private void b() {
    }

    /* access modifiers changed from: protected */
    public void onMoveHeader(int i2, float f2, float f3) {
    }

    public void startRefresh() {
    }

    public PullHeaderLayout(Context context) {
        this(context, (AttributeSet) null);
    }

    public PullHeaderLayout(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public PullHeaderLayout(Context context, AttributeSet attributeSet, int i2) {
        super(context, attributeSet, i2);
        this.mHeaderId = 0;
        this.mContentId = 0;
        this.mPullState = 0;
        this.mDirection = 0;
        this.mActivePointerId = -1;
        this.mIsBeingDragged = false;
        this.mLastMotionY = 0;
        this.mScrollOffset = new int[2];
        this.mScrollConsumed = new int[2];
        this.f19026a = 0.0f;
        this.c = true;
        this.d = new Runnable() {
            public void run() {
                boolean unused = PullHeaderLayout.this.c = false;
                PullHeaderLayout.this.moveBy((float) PullHeaderLayout.this.mHeaderController.e());
            }
        };
        this.mOnNotifiedHeaderScrollEnd = false;
        this.e = false;
        this.mFromNestedScroll = false;
        this.r = State.RESET;
        this.v = new Runnable() {
            public void run() {
                PullHeaderLayout.this.a(State.RESET);
            }
        };
        this.w = new Runnable() {
            public void run() {
                LogUtil.a(PullHeaderLayout.TAG, "auto refresh");
                boolean unused = PullHeaderLayout.this.t = true;
                if (!(PullHeaderLayout.this.i == null || PullHeaderLayout.this.i.getVisibility() == 0)) {
                    PullHeaderLayout.this.i.setVisibility(0);
                }
                PullHeaderLayout.this.a(State.LOADING);
            }
        };
        this.dragOverHeight = false;
        this.x = new ArrayList();
        this.y = false;
        int i3 = DEFAULT_HEIGHT;
        int i4 = DEFAULT_EXPAND;
        int i5 = DEFAULT_SHRINK;
        if (attributeSet != null) {
            TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R.styleable.PullHeaderLayout);
            i3 = obtainStyledAttributes.getDimensionPixelOffset(4, DEFAULT_HEIGHT);
            i4 = obtainStyledAttributes.getDimensionPixelOffset(3, DEFAULT_EXPAND);
            i5 = obtainStyledAttributes.getDimensionPixelOffset(5, DEFAULT_SHRINK);
            this.mHeaderId = obtainStyledAttributes.getResourceId(2, this.mHeaderId);
            this.mContentId = obtainStyledAttributes.getResourceId(1, this.mContentId);
            this.mActionDrawable = obtainStyledAttributes.getDrawable(0);
            obtainStyledAttributes.recycle();
        }
        this.mHeaderController = new HeaderController(i3, i4, i5);
        ViewConfiguration.get(getContext());
        this.mParentHelper = new NestedScrollingParentHelper(this);
        this.mChildHelper = new NestedScrollingChildHelper(this);
        setNestedScrollingEnabled(true);
        init();
    }

    /* access modifiers changed from: protected */
    public void init() {
        this.mScroller = ScrollerCompat.create(getContext(), new DecelerateInterpolator(3.0f));
        ViewConfiguration viewConfiguration = ViewConfiguration.get(getContext());
        this.mTouchSlop = viewConfiguration.getScaledTouchSlop();
        this.mMinimumVelocity = viewConfiguration.getScaledMinimumFlingVelocity();
        this.mMaximumVelocity = viewConfiguration.getScaledMaximumFlingVelocity();
        this.l = ViewConfiguration.get(getContext()).getScaledTouchSlop();
        b();
    }

    public void setNestedScrollingEnabled(boolean z) {
        this.mChildHelper.setNestedScrollingEnabled(z);
    }

    public boolean isNestedScrollingEnabled() {
        return this.mChildHelper.isNestedScrollingEnabled();
    }

    public boolean startNestedScroll(int i2) {
        LogUtil.a(TAG, "startNestedScroll in");
        return this.mChildHelper.startNestedScroll(i2);
    }

    public void stopNestedScroll() {
        this.mChildHelper.stopNestedScroll();
    }

    public boolean hasNestedScrollingParent() {
        LogUtil.a(TAG, "hasNestedScrollingParent in");
        return this.mChildHelper.hasNestedScrollingParent();
    }

    public boolean dispatchNestedScroll(int i2, int i3, int i4, int i5, int[] iArr) {
        LogUtil.a(TAG, "dispatchNestedScroll in");
        return this.mChildHelper.dispatchNestedScroll(i2, i3, i4, i5, iArr);
    }

    public boolean dispatchNestedPreScroll(int i2, int i3, int[] iArr, int[] iArr2) {
        LogUtil.a(TAG, "dispatchNestedPreScroll in");
        return this.mChildHelper.dispatchNestedPreScroll(i2, i3, iArr, iArr2);
    }

    public boolean dispatchNestedFling(float f2, float f3, boolean z) {
        boolean dispatchNestedFling = this.mChildHelper.dispatchNestedFling(f2, f3, z);
        String str = TAG;
        LogUtil.a(str, "dispatchNestedFling bool=" + dispatchNestedFling);
        return true;
    }

    public boolean dispatchNestedPreFling(float f2, float f3) {
        LogUtil.a(TAG, "dispatchNestedPreFling in");
        return this.mChildHelper.dispatchNestedPreFling(f2, f3);
    }

    public boolean onStartNestedScroll(View view, View view2, int i2) {
        String str = TAG;
        LogUtil.a(str, "onStartNestedScroll in nestedScrollAxes=" + i2);
        return (i2 & 2) != 0;
    }

    public void onNestedScrollAccepted(View view, View view2, int i2) {
        LogUtil.a(TAG, "onNestedScrollAccepted in");
        this.mParentHelper.onNestedScrollAccepted(view, view2, i2);
        startNestedScroll(2);
    }

    public void onStopNestedScroll(View view) {
        LogUtil.a(TAG, "onStopNestedScroll in");
        stopNestedScroll();
    }

    public void onNestedScroll(View view, int i2, int i3, int i4, int i5) {
        LogUtil.a(TAG, "onNestedScroll in");
        this.mFromNestedScroll = true;
        int moveBy = moveBy((float) i5);
        dispatchNestedScroll(0, moveBy, 0, i5 - moveBy, (int[]) null);
        this.mFromNestedScroll = false;
    }

    public void onNestedPreScroll(View view, int i2, int i3, int[] iArr) {
        LogUtil.a(TAG, "onNestedPreScroll in");
        if (i3 > 0 && this.mHeaderController.j()) {
            int moveBy = moveBy((float) i3);
            iArr[0] = 0;
            iArr[1] = moveBy;
        }
    }

    public boolean onNestedFling(View view, float f2, float f3, boolean z) {
        String str = TAG;
        LogUtil.a(str, "onNestedFling consumed=" + z + ",velocityY=" + f3);
        this.f19026a = f3;
        if (!z) {
            return false;
        }
        flingWithNestedDispatch((int) f3);
        return true;
    }

    public boolean onNestedPreFling(View view, float f2, float f3) {
        LogUtil.a(TAG, "onNestedPreFling in");
        return false;
    }

    /* access modifiers changed from: protected */
    public boolean flingWithNestedDispatch(int i2) {
        int d2 = this.mHeaderController.d();
        int e2 = this.mHeaderController.e();
        float f2 = (float) i2;
        boolean shouldFlingWithNestedDispatch = shouldFlingWithNestedDispatch(this.j, 0.0f, f2);
        String str = TAG;
        LogUtil.a(str, "flingWithNestedDispatch in canFling=" + shouldFlingWithNestedDispatch + ",scroll=" + d2 + ",maxScroll=" + e2 + ",var top=" + this.mHeaderView.findViewById(R.id.variable_layout).getTop());
        if (shouldFlingWithNestedDispatch && !this.mHeaderController.k()) {
            fling(i2);
        } else if (!dispatchNestedPreFling(0.0f, f2)) {
            dispatchNestedFling(0.0f, f2, shouldFlingWithNestedDispatch);
        }
        return shouldFlingWithNestedDispatch;
    }

    /* access modifiers changed from: protected */
    public boolean shouldFlingWithNestedDispatch(View view, float f2, float f3) {
        if ((!this.mHeaderController.j() || f3 <= 0.0f) && this.mHeaderController.i()) {
            int i2 = (f3 > 0.0f ? 1 : (f3 == 0.0f ? 0 : -1));
        }
        String str = TAG;
        LogUtil.a(str, "shouldFlingWithNestedDispatch velocityY=" + f3);
        if (f3 > 0.0f && !this.mHeaderController.j()) {
            return false;
        }
        if (f3 >= 0.0f || !canChildScrollDown(this.mContent)) {
            return true;
        }
        return false;
    }

    public void scrollToBottom() {
        a(-this.mHeaderController.d());
    }

    public boolean canChildScrollDown(View view) {
        if (Build.VERSION.SDK_INT < 14) {
            if (view instanceof AbsListView) {
                AbsListView absListView = (AbsListView) view;
                if (absListView.getChildCount() <= 0) {
                    return false;
                }
                if (absListView.getFirstVisiblePosition() > 0 || absListView.getChildAt(0).getTop() < absListView.getPaddingTop()) {
                    return true;
                }
                return false;
            } else if (ViewCompat.canScrollVertically(view, 1) || view.getScrollY() < 0) {
                return true;
            } else {
                return false;
            }
        } else if (!(view instanceof RecyclerView)) {
            return ViewCompat.canScrollVertically(view, 1);
        } else {
            RecyclerView.LayoutManager layoutManager = ((RecyclerView) view).getLayoutManager();
            if (!(layoutManager instanceof LinearLayoutManager)) {
                return view.canScrollVertically(-1);
            }
            if (((LinearLayoutManager) layoutManager).findFirstCompletelyVisibleItemPosition() <= 1) {
                return false;
            }
            return view.canScrollVertically(-1);
        }
    }

    public boolean canChildScrollUp(View view) {
        if (Build.VERSION.SDK_INT < 14) {
            if (view instanceof AbsListView) {
                AbsListView absListView = (AbsListView) view;
                if (absListView.getChildCount() <= 0 || (absListView.getFirstVisiblePosition() <= 0 && absListView.getChildAt(0).getTop() >= absListView.getPaddingTop())) {
                    return false;
                }
                return true;
            } else if (ViewCompat.canScrollVertically(view, 1) || view.getScrollY() < 0) {
                return true;
            } else {
                return false;
            }
        } else if (view instanceof RecyclerView) {
            return view.canScrollVertically(-1);
        } else {
            return ViewCompat.canScrollVertically(view, 1);
        }
    }

    public int getNestedScrollAxes() {
        LogUtil.a(TAG, "getNestedScrollAxes in");
        return this.mParentHelper.getNestedScrollAxes();
    }

    public void setHeaderSize(int i2, int i3, int i4) {
        this.mHeaderController.a(i2, i3, i4);
        if (isLayoutRequested()) {
            requestLayout();
        }
    }

    public void setOnPullListener(OnPullListener onPullListener) {
        this.mPullListener = onPullListener;
    }

    public void setActionDrawable(Drawable drawable) {
        this.mActionDrawable = drawable;
    }

    @Nullable
    public View getIconView() {
        return this.mFlyView;
    }

    public void setHeaderView(View view, LayoutParams layoutParams) {
        if (this.mHeaderView != null) {
            removeView(this.mHeaderView);
            this.mPullHeaderView = null;
        }
        addView(view, 0, layoutParams);
        this.mHeaderView = view;
        if (this.mHeaderView instanceof IPullHeader) {
            this.mPullHeaderView = (IPullHeader) this.mHeaderView;
        }
    }

    /* access modifiers changed from: protected */
    public void onFinishInflate() {
        int childCount = getChildCount();
        if (childCount <= 2) {
            if (childCount == 2) {
                if (this.mHeaderId != 0 && this.mHeaderView == null) {
                    this.mHeaderView = findViewById(this.mHeaderId);
                }
                if (this.mContentId != 0 && this.mContent == null) {
                    this.mContent = findViewById(this.mContentId);
                }
                if (this.mContent == null || this.mHeaderView == null) {
                    View childAt = getChildAt(0);
                    View childAt2 = getChildAt(1);
                    if (childAt instanceof IPullHeader) {
                        this.mHeaderView = childAt;
                        this.mContent = childAt2;
                        this.mPullHeaderView = (IPullHeader) this.mHeaderView;
                    } else if (childAt2 instanceof IPullHeader) {
                        this.mHeaderView = childAt2;
                        this.mContent = childAt;
                        this.mPullHeaderView = (IPullHeader) this.mHeaderView;
                    } else if (this.mContent == null && this.mHeaderView == null) {
                        this.mHeaderView = childAt;
                        this.mContent = childAt2;
                    } else if (this.mHeaderView == null) {
                        if (this.mContent == childAt) {
                            childAt = childAt2;
                        }
                        this.mHeaderView = childAt;
                    } else {
                        if (this.mHeaderView == childAt) {
                            childAt = childAt2;
                        }
                        this.mContent = childAt;
                    }
                }
            } else if (childCount == 1) {
                this.mContent = getChildAt(0);
            }
            this.j = this.mContent;
            this.i = this.mHeaderView.findViewById(R.id.ll_loading);
            setActionDrawable(this.mActionDrawable);
            super.onFinishInflate();
            return;
        }
        throw new IllegalStateException("FlyRefreshLayout only can host 2 elements");
    }

    private void a() {
        if (this.j instanceof RecyclerView) {
            ((RecyclerView) this.j).addOnScrollListener(new RecyclerView.OnScrollListener() {
                public void onScrollStateChanged(RecyclerView recyclerView, int i) {
                    super.onScrollStateChanged(recyclerView, i);
                    if (i == 0) {
                        RecyclerView.LayoutManager layoutManager = ((RecyclerView) PullHeaderLayout.this.j).getLayoutManager();
                        if ((layoutManager instanceof LinearLayoutManager) && ((LinearLayoutManager) layoutManager).findFirstCompletelyVisibleItemPosition() <= 1 && PullHeaderLayout.this.f19026a < -100.0f) {
                            PullHeaderLayout.this.fling((int) PullHeaderLayout.this.f19026a);
                        }
                    }
                }

                public void onScrolled(RecyclerView recyclerView, int i, int i2) {
                    super.onScrolled(recyclerView, i, i2);
                }
            });
        }
    }

    /* access modifiers changed from: protected */
    public void onMeasure(int i2, int i3) {
        super.onMeasure(i2, i3);
        if (this.mHeaderView != null) {
            measureChildWithMargins(this.mHeaderView, i2, 0, i3, 0);
        }
        if (this.mContent != null) {
            measureChildWithMargins(this.mContent, i2, 0, i3, this.mHeaderController.b());
        }
    }

    /* access modifiers changed from: protected */
    public void onLayout(boolean z, int i2, int i3, int i4, int i5) {
        layoutChildren();
        if (this.c) {
            moveBy(((float) this.mHeaderController.e()) * b);
            this.c = false;
        }
    }

    /* access modifiers changed from: protected */
    public void layoutChildren() {
        int g2 = this.mHeaderController.g();
        int paddingLeft = getPaddingLeft();
        int paddingTop = getPaddingTop();
        if (this.mHeaderView != null) {
            ViewGroup.MarginLayoutParams marginLayoutParams = (ViewGroup.MarginLayoutParams) this.mHeaderView.getLayoutParams();
            int i2 = marginLayoutParams.leftMargin + paddingLeft;
            int i3 = marginLayoutParams.topMargin + paddingTop;
            this.mHeaderView.layout(i2, i3, this.mHeaderView.getMeasuredWidth() + i2, this.mHeaderView.getMeasuredHeight() + i3);
        }
        if (this.mContent != null) {
            ViewGroup.MarginLayoutParams marginLayoutParams2 = (ViewGroup.MarginLayoutParams) this.mContent.getLayoutParams();
            int i4 = paddingLeft + marginLayoutParams2.leftMargin;
            int i5 = paddingTop + marginLayoutParams2.topMargin + g2;
            this.mContent.layout(i4, i5, this.mContent.getMeasuredWidth() + i4, this.mContent.getMeasuredHeight() + i5);
        }
    }

    /* access modifiers changed from: protected */
    public void obtainVelocityTracker(MotionEvent motionEvent) {
        if (this.mVelocityTracker == null) {
            this.mVelocityTracker = VelocityTracker.obtain();
        }
        this.mVelocityTracker.addMovement(motionEvent);
    }

    /* access modifiers changed from: protected */
    public void initOrResetVelocityTracker() {
        if (this.mVelocityTracker == null) {
            this.mVelocityTracker = VelocityTracker.obtain();
        } else {
            this.mVelocityTracker.clear();
        }
    }

    /* access modifiers changed from: protected */
    public void releaseVelocityTracker() {
        if (this.mVelocityTracker != null) {
            this.mVelocityTracker.recycle();
            this.mVelocityTracker = null;
        }
    }

    /* access modifiers changed from: protected */
    public void onSecondaryPointerUp(MotionEvent motionEvent) {
        int action = (motionEvent.getAction() & 65280) >> 8;
        if (MotionEventCompat.getPointerId(motionEvent, action) == this.mActivePointerId) {
            int i2 = action == 0 ? 1 : 0;
            this.mLastMotionY = (int) MotionEventCompat.getY(motionEvent, i2);
            this.mActivePointerId = MotionEventCompat.getPointerId(motionEvent, i2);
            if (this.mVelocityTracker != null) {
                this.mVelocityTracker.clear();
            }
        }
    }

    /* access modifiers changed from: protected */
    public void endDrag() {
        this.mIsBeingDragged = false;
        releaseVelocityTracker();
    }

    public boolean onInterceptTouchEvent(MotionEvent motionEvent) {
        int action = motionEvent.getAction();
        if (action == 2 && this.mIsBeingDragged) {
            return true;
        }
        if (!isEnabled()) {
            return false;
        }
        int i2 = action & 255;
        if (i2 != 6) {
            switch (i2) {
                case 0:
                    this.mLastMotionY = (int) motionEvent.getY();
                    this.mActivePointerId = MotionEventCompat.getPointerId(motionEvent, 0);
                    initOrResetVelocityTracker();
                    this.mVelocityTracker.addMovement(motionEvent);
                    this.mIsBeingDragged = !this.mScroller.isFinished();
                    startNestedScroll(2);
                    break;
                case 1:
                case 3:
                    this.mIsBeingDragged = false;
                    this.mActivePointerId = -1;
                    endDrag();
                    stopNestedScroll();
                    break;
                case 2:
                    int i3 = this.mActivePointerId;
                    if (i3 != -1) {
                        int findPointerIndex = MotionEventCompat.findPointerIndex(motionEvent, i3);
                        if (findPointerIndex != -1) {
                            int y2 = (int) MotionEventCompat.getY(motionEvent, findPointerIndex);
                            if (Math.abs(y2 - this.mLastMotionY) > this.mTouchSlop && (2 & getNestedScrollAxes()) == 0) {
                                this.mIsBeingDragged = true;
                                this.mLastMotionY = y2;
                                obtainVelocityTracker(motionEvent);
                                this.mNestedYOffset = 0;
                                ViewParent parent = getParent();
                                if (parent != null) {
                                    parent.requestDisallowInterceptTouchEvent(true);
                                    break;
                                }
                            }
                        } else {
                            String str = TAG;
                            Log.e(str, "Invalid pointerId=" + i3 + " in onInterceptTouchEvent");
                            break;
                        }
                    }
                    break;
            }
        } else {
            onSecondaryPointerUp(motionEvent);
        }
        return this.mIsBeingDragged;
    }

    public boolean dispatchTouchEvent(MotionEvent motionEvent) {
        int actionMasked = MotionEventCompat.getActionMasked(motionEvent);
        if (actionMasked == 0 && motionEvent.getX() <= getResources().getDisplayMetrics().density * 20.0f) {
            return false;
        }
        if (actionMasked == 1 || actionMasked == 3) {
            tryBounceBack();
        }
        boolean dispatchTouchEvent = super.dispatchTouchEvent(motionEvent);
        dispatchTouchEventForPullToRefresh(motionEvent);
        return dispatchTouchEvent;
    }

    public boolean onTouchEvent(MotionEvent motionEvent) {
        ViewParent parent;
        int i2;
        MotionEvent obtain = MotionEvent.obtain(motionEvent);
        int actionMasked = MotionEventCompat.getActionMasked(motionEvent);
        if (actionMasked == 0) {
            this.mNestedYOffset = 0;
        }
        obtain.offsetLocation(0.0f, (float) this.mNestedYOffset);
        switch (actionMasked) {
            case 0:
                boolean z = !this.mScroller.isFinished();
                this.mIsBeingDragged = z;
                if (z && (parent = getParent()) != null) {
                    parent.requestDisallowInterceptTouchEvent(true);
                }
                if (!this.mScroller.isFinished()) {
                    this.mScroller.abortAnimation();
                }
                this.mLastMotionY = (int) motionEvent.getY();
                this.mActivePointerId = MotionEventCompat.getPointerId(motionEvent, 0);
                startNestedScroll(2);
                this.e = true;
                break;
            case 1:
                if (this.mIsBeingDragged) {
                    VelocityTracker velocityTracker = this.mVelocityTracker;
                    if (velocityTracker != null) {
                        velocityTracker.computeCurrentVelocity(1000, (float) this.mMaximumVelocity);
                        i2 = (int) VelocityTrackerCompat.getYVelocity(velocityTracker, this.mActivePointerId);
                    } else {
                        i2 = 0;
                    }
                    if (Math.abs(i2) > this.mMinimumVelocity) {
                        flingWithNestedDispatch(-i2);
                    }
                    this.mActivePointerId = -1;
                    endDrag();
                    this.e = false;
                    break;
                }
                break;
            case 2:
                int findPointerIndex = MotionEventCompat.findPointerIndex(motionEvent, this.mActivePointerId);
                if (findPointerIndex != -1) {
                    int y2 = (int) MotionEventCompat.getY(motionEvent, findPointerIndex);
                    int i3 = this.mLastMotionY - y2;
                    if (dispatchNestedPreScroll(0, i3, this.mScrollConsumed, this.mScrollOffset)) {
                        i3 -= this.mScrollConsumed[1];
                        obtain.offsetLocation(0.0f, (float) this.mScrollOffset[1]);
                        this.mNestedYOffset += this.mScrollOffset[1];
                    }
                    if (!this.mIsBeingDragged && Math.abs(i3) > this.mTouchSlop) {
                        ViewParent parent2 = getParent();
                        if (parent2 != null) {
                            parent2.requestDisallowInterceptTouchEvent(true);
                        }
                        this.mIsBeingDragged = true;
                        if (i3 > 0) {
                            i3 -= this.mTouchSlop;
                        } else {
                            i3 += this.mTouchSlop;
                        }
                    }
                    if (this.mIsBeingDragged) {
                        this.mLastMotionY = y2 - this.mScrollOffset[1];
                        int moveBy = moveBy((float) i3);
                        if (dispatchNestedScroll(0, moveBy, 0, i3 - moveBy, this.mScrollOffset)) {
                            this.mLastMotionY -= this.mScrollOffset[1];
                            obtain.offsetLocation(0.0f, (float) this.mScrollOffset[1]);
                            this.mNestedYOffset += this.mScrollOffset[1];
                            break;
                        }
                    }
                } else {
                    Log.e(TAG, "Invalid pointerId=" + this.mActivePointerId + " in onTouchEvent");
                    break;
                }
                break;
            case 3:
                if (this.mIsBeingDragged && getChildCount() > 0) {
                    this.mActivePointerId = -1;
                    endDrag();
                }
                this.e = false;
                break;
            case 5:
                int actionIndex = MotionEventCompat.getActionIndex(motionEvent);
                this.mLastMotionY = (int) MotionEventCompat.getY(motionEvent, actionIndex);
                this.mActivePointerId = MotionEventCompat.getPointerId(motionEvent, actionIndex);
                break;
            case 6:
                onSecondaryPointerUp(motionEvent);
                int findPointerIndex2 = MotionEventCompat.findPointerIndex(motionEvent, this.mActivePointerId);
                if (findPointerIndex2 == -1) {
                    this.mLastMotionY = (int) motionEvent.getY();
                    break;
                } else {
                    this.mLastMotionY = (int) MotionEventCompat.getY(motionEvent, findPointerIndex2);
                    break;
                }
        }
        if (this.mVelocityTracker != null) {
            this.mVelocityTracker.addMovement(obtain);
        }
        obtain.recycle();
        return true;
    }

    public void fling(int i2) {
        this.mPullState = 2;
        LogUtil.a(TAG, "fling start1");
        this.mScroller.abortAnimation();
        this.mScroller.fling(0, this.mHeaderController.d(), 0, i2 < 0 ? LoginErrorCode.y : 3000, 0, 0, 0, this.mHeaderController.e(), 0, 0);
        ViewCompat.postInvalidateOnAnimation(this);
    }

    public void flingToHalfOpenState(int i2) {
        this.mPullState = 2;
        LogUtil.a(TAG, "fling start2");
        this.mScroller.abortAnimation();
        this.mScroller.fling(0, this.mHeaderController.d(), 0, i2 < 0 ? LoginErrorCode.y : 3000, 0, 0, 0, (int) (((float) this.mHeaderController.e()) * b), 0, 0);
        ViewCompat.postInvalidateOnAnimation(this);
    }

    public void fling(boolean z, int i2) {
        this.mPullState = 2;
        LogUtil.a(TAG, "fling start3");
        this.mScroller.abortAnimation();
        this.mScroller.fling(0, this.mHeaderController.d(), 0, z ? LoginErrorCode.y : 3000, 0, 0, 0, i2 + this.mHeaderController.d(), 0, 0);
        ViewCompat.postInvalidateOnAnimation(this);
    }

    /* access modifiers changed from: protected */
    public int moveBy(float f2) {
        String str = TAG;
        LogUtil.a(str, "moveBy " + f2);
        int d2 = this.mHeaderController.d();
        int a2 = this.mHeaderController.a(f2);
        int d3 = this.mHeaderController.d() - d2;
        if (d3 == 0) {
            if (!this.mOnNotifiedHeaderScrollEnd && this.mPullState == 1 && (this.e || this.mFromNestedScroll)) {
                this.mOnNotifiedHeaderScrollEnd = true;
                this.mPullHeaderView.onPullEnd(this, this.mPullState, this.mDirection);
            }
            return 0;
        }
        if (this.mPullState != 1) {
            this.mPullState = 1;
            this.mOnNotifiedHeaderScrollEnd = false;
        }
        if (this.mPullState == 1) {
            if (f2 < 0.0f) {
                this.mDirection = 2;
            } else if (f2 > 0.0f) {
                this.mDirection = 1;
            }
        }
        if (this.mContent != null) {
            this.mContent.offsetTopAndBottom(-d3);
        }
        if (this.mFlyView != null) {
            this.mFlyView.offsetTopAndBottom(-d3);
        }
        float m2 = this.mHeaderController.m();
        onMoveHeader(this.mPullState, m2, f2);
        String str2 = TAG;
        StringBuilder sb = new StringBuilder();
        sb.append("mHeaderView.offsetTopAndBottom ");
        int i2 = -d3;
        sb.append(i2);
        LogUtil.a(str2, sb.toString());
        this.mHeaderView.offsetTopAndBottom(i2);
        if (this.mPullHeaderView != null) {
            this.mPullHeaderView.onPullProgress(this, this.mPullState, this.mHeaderController.n(), d3, this.mHeaderController.k());
        }
        if (this.mPullListener != null) {
            this.mPullListener.a(this, this.mPullState, m2);
        }
        return a2;
    }

    public void computeScroll() {
        LogUtil.a(TAG, "computeScroll");
        if (this.mScroller.computeScrollOffset()) {
            int d2 = this.mHeaderController.d();
            int currY = this.mScroller.getCurrY();
            if (d2 != currY) {
                moveBy((float) (currY - d2));
            }
            ViewCompat.postInvalidateOnAnimation(this);
        }
    }

    /* access modifiers changed from: protected */
    public void tryBounceBack() {
        if (this.mHeaderController.k()) {
            LogUtil.a(TAG, "tryBounceBack isOverHeight");
            this.mBounceAnim = ObjectAnimator.ofFloat(new float[]{(float) this.mHeaderController.d(), 0.0f});
            this.mBounceAnim.setInterpolator(new DecelerateInterpolator());
            this.mBounceAnim.setDuration(200);
            this.mBounceAnim.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                public void onAnimationUpdate(ValueAnimator valueAnimator) {
                    PullHeaderLayout.this.moveBy(((Float) valueAnimator.getAnimatedValue()).floatValue() - ((float) PullHeaderLayout.this.mHeaderController.d()));
                }
            });
            this.mBounceAnim.addListener(new SimpleAnimatorListener() {
                public void onAnimationEnd(Animator animator) {
                    PullHeaderLayout.this.mPullState = 0;
                    PullHeaderLayout.this.mPullHeaderView.onBounceEnd();
                }
            });
            this.mBounceAnim.start();
            this.mPullState = 3;
            if (this.mHeaderController.o()) {
                this.dragOverHeight = true;
                startRefresh();
            }
        } else if (Math.abs(this.f19026a) <= 0.0f) {
            int d2 = this.mHeaderController.d();
            int e2 = this.mHeaderController.e();
            float f2 = (float) d2;
            float f3 = (float) e2;
            float f4 = 0.3f * f3;
            if (f2 <= f4) {
                a(-d2);
                return;
            }
            if (f2 > f4) {
                float f5 = f3 * b;
                if (f2 < f5) {
                    a((int) (f5 - f2));
                    return;
                }
            }
            float f6 = f3 * b;
            if (f2 > f6 && d2 < e2) {
                a(-((int) (f6 - f2)));
            }
        }
    }

    private void a(int i2) {
        this.mPullState = 2;
        LogUtil.a(TAG, "scroll start");
        this.mScroller.abortAnimation();
        this.mScroller.startScroll(0, this.mHeaderController.d(), 0, i2, 300);
        ViewCompat.postInvalidateOnAnimation(this);
    }

    /* access modifiers changed from: protected */
    public boolean checkLayoutParams(ViewGroup.LayoutParams layoutParams) {
        return layoutParams instanceof LayoutParams;
    }

    /* access modifiers changed from: protected */
    public ViewGroup.LayoutParams generateDefaultLayoutParams() {
        return new LayoutParams(-1, -1);
    }

    /* access modifiers changed from: protected */
    public ViewGroup.LayoutParams generateLayoutParams(ViewGroup.LayoutParams layoutParams) {
        return new LayoutParams(layoutParams);
    }

    public ViewGroup.LayoutParams generateLayoutParams(AttributeSet attributeSet) {
        return new LayoutParams(getContext(), attributeSet);
    }

    public static class LayoutParams extends ViewGroup.MarginLayoutParams {
        public LayoutParams(Context context, AttributeSet attributeSet) {
            super(context, attributeSet);
        }

        public LayoutParams(int i, int i2) {
            super(i, i2);
        }

        public LayoutParams(ViewGroup.MarginLayoutParams marginLayoutParams) {
            super(marginLayoutParams);
        }

        public LayoutParams(ViewGroup.LayoutParams layoutParams) {
            super(layoutParams);
        }
    }

    private void a(boolean z) {
        if (this.t && !z) {
            this.t = false;
            a(State.LOADING);
            e();
        }
    }

    public void setRefreshHeader(View view) {
        if (view != null && view != this.i) {
            removeView(this.i);
            if (view.getLayoutParams() == null) {
                view.setLayoutParams(new ViewGroup.LayoutParams(-1, -2));
            }
            this.i = view;
            addView(this.i);
        }
    }

    public void setRefreshListener(OnRefreshListener onRefreshListener) {
        this.s = onRefreshListener;
    }

    public void refreshComplete() {
        a(State.COMPLETE);
        if (!this.n) {
            postDelayed(this.v, h);
        }
    }

    public void autoRefresh() {
        autoRefresh(500);
    }

    public void autoRefresh(long j2) {
        if (this.r == State.RESET) {
            postDelayed(this.w, j2);
        }
    }

    public boolean dispatchTouchEventForPullToRefresh(MotionEvent motionEvent) {
        if (!isEnabled() || this.j == null) {
            return true;
        }
        switch (motionEvent.getActionMasked()) {
            case 0:
                LogUtil.a(TAG, "ACTION_DOWN");
                if (motionEvent.getPointerCount() > 0) {
                    this.m = motionEvent.getPointerId(0);
                    float y2 = motionEvent.getY(0);
                    this.o = y2;
                    this.p = y2;
                } else {
                    float y3 = motionEvent.getY();
                    this.o = y3;
                    this.p = y3;
                }
                this.t = false;
                this.n = true;
                this.q = false;
                this.k = this.j.getTop();
                removeCallbacks(this.v);
                removeCallbacks(this.w);
                this.dragOverHeight = false;
                this.f19026a = 0.0f;
                return true;
            case 1:
            case 3:
                LogUtil.a(TAG, "ACTION_UP ACTION_CANCEL");
                this.n = false;
                d();
                this.m = -1;
                break;
            case 2:
                LogUtil.a(TAG, "ACTION_MOVE");
                if (this.m == -1) {
                    Log.e(TAG, "Got ACTION_MOVE event but don't have an active pointer id.");
                    return true;
                } else if (!this.mHeaderController.k()) {
                    c();
                    return true;
                } else {
                    float y4 = motionEvent.getY();
                    int findPointerIndex = MotionEventCompat.findPointerIndex(motionEvent, this.m);
                    if (findPointerIndex != -1) {
                        y4 = motionEvent.getY(findPointerIndex);
                    }
                    float f2 = (y4 - this.o) * 0.5f;
                    this.o = y4;
                    if (!this.q && Math.abs(y4 - this.p) > ((float) this.l)) {
                        this.q = true;
                    }
                    if (this.q) {
                        a(f2);
                        return true;
                    }
                }
                break;
            case 5:
                LogUtil.a(TAG, "ACTION_POINTER_DOWN");
                int actionIndex = MotionEventCompat.getActionIndex(motionEvent);
                if (actionIndex >= 0) {
                    this.o = motionEvent.getY(actionIndex);
                    this.m = MotionEventCompat.getPointerId(motionEvent, actionIndex);
                    break;
                } else {
                    Log.e(TAG, "Got ACTION_POINTER_DOWN event but have an invalid action index.");
                    return true;
                }
            case 6:
                LogUtil.a(TAG, "ACTION_POINTER_UP");
                onSecondaryPointerUp(motionEvent);
                int findPointerIndex2 = motionEvent.findPointerIndex(this.m);
                if (findPointerIndex2 != -1) {
                    this.o = motionEvent.getY(findPointerIndex2);
                    break;
                }
                break;
        }
        return true;
    }

    private void c() {
        a(State.RESET);
    }

    private void a(float f2) {
        if (Math.round(f2) != 0) {
            if (!(this.i == null || this.i.getVisibility() == 0)) {
                this.i.setVisibility(0);
            }
            if (!this.dragOverHeight && this.mHeaderController.k() && ((float) Math.abs(this.mHeaderController.d())) >= this.mHeaderController.l()) {
                this.dragOverHeight = true;
            }
            if (this.r == State.RESET && this.mHeaderController.k()) {
                a(State.PULL);
            }
        }
    }

    private void d() {
        if (this.r != State.PULL || this.n || !this.dragOverHeight) {
            e();
        } else {
            a(State.LOADING);
        }
    }

    /* access modifiers changed from: private */
    public void a(State state) {
        this.r = state;
        RefreshHeader refreshHeader = this.i instanceof RefreshHeader ? (RefreshHeader) this.i : null;
        switch (state) {
            case RESET:
                if (refreshHeader != null) {
                    refreshHeader.reset();
                }
                f();
                return;
            case PULL:
                if (refreshHeader != null) {
                    refreshHeader.pull();
                }
                g();
                return;
            case LOADING:
                if (this.s != null) {
                    this.s.a();
                }
                if (!this.t) {
                    if (refreshHeader != null) {
                        refreshHeader.refreshing();
                    }
                    h();
                    return;
                }
                return;
            case COMPLETE:
                if (refreshHeader != null) {
                    refreshHeader.complete();
                }
                i();
                return;
            default:
                return;
        }
    }

    private void e() {
        if (this.r != State.LOADING) {
            c();
        }
    }

    public void addRefreshHeader(RefreshHeader refreshHeader) {
        if (refreshHeader != null && !this.x.contains(refreshHeader)) {
            this.x.add(refreshHeader);
        }
    }

    public boolean removeRefreshHeader(RefreshHeader refreshHeader) {
        return this.x.remove(refreshHeader);
    }

    private void f() {
        if (!this.y) {
            for (int i2 = 0; i2 < this.x.size(); i2++) {
                RefreshHeader refreshHeader = this.x.get(i2);
                if (refreshHeader != null) {
                    refreshHeader.reset();
                }
            }
        }
    }

    private void g() {
        if (!this.y) {
            for (int i2 = 0; i2 < this.x.size(); i2++) {
                RefreshHeader refreshHeader = this.x.get(i2);
                if (refreshHeader != null) {
                    refreshHeader.pull();
                }
            }
        }
    }

    private void h() {
        if (!this.y) {
            for (int i2 = 0; i2 < this.x.size(); i2++) {
                RefreshHeader refreshHeader = this.x.get(i2);
                if (refreshHeader != null) {
                    refreshHeader.refreshing();
                }
            }
        }
    }

    private void i() {
        if (!this.y) {
            for (int i2 = 0; i2 < this.x.size(); i2++) {
                RefreshHeader refreshHeader = this.x.get(i2);
                if (refreshHeader != null) {
                    refreshHeader.complete();
                }
            }
        }
    }

    /* access modifiers changed from: protected */
    public void onDetachedFromWindow() {
        this.y = true;
        super.onDetachedFromWindow();
        this.x.clear();
    }

    /* access modifiers changed from: protected */
    public void onAttachedToWindow() {
        super.onAttachedToWindow();
        this.y = false;
    }
}
