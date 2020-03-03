package com.taobao.weex.ui.view.refresh.core;

import android.animation.Animator;
import android.animation.ValueAnimator;
import android.annotation.TargetApi;
import android.content.Context;
import android.os.Build;
import android.support.annotation.Nullable;
import android.support.v4.view.NestedScrollingChild;
import android.support.v4.view.NestedScrollingChildHelper;
import android.support.v4.view.NestedScrollingParent;
import android.support.v4.view.NestedScrollingParentHelper;
import android.support.v4.view.ViewCompat;
import android.support.v4.view.ViewParentCompat;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.widget.AbsListView;
import android.widget.FrameLayout;
import android.widget.ListAdapter;
import java.util.LinkedList;
import java.util.List;
import org.jacoco.agent.rt.internal_8ff85ea.Offline;

public class WXSwipeLayout extends FrameLayout implements NestedScrollingChild, NestedScrollingParent {
    private static transient /* synthetic */ boolean[] $jacocoData = null;
    private static final float DAMPING = 0.4f;
    private static final int INVALID = -1;
    private static final int LOAD_MORE = 1;
    private static final int PULL_REFRESH = 0;
    private static final float overFlow = 1.0f;
    private WXRefreshView footerView;
    private WXRefreshView headerView;
    private boolean isConfirm;
    private volatile float loadingViewFlowHeight;
    private volatile float loadingViewHeight;
    private int mCurrentAction;
    private ViewParent mNestedScrollAcceptedParent;
    private boolean mNestedScrollInProgress;
    private NestedScrollingChildHelper mNestedScrollingChildHelper;
    private NestedScrollingParentHelper mNestedScrollingParentHelper;
    private final int[] mParentOffsetInWindow = new int[2];
    private final int[] mParentScrollConsumed = new int[2];
    private int mProgressBgColor;
    private int mProgressColor;
    private boolean mPullLoadEnable;
    private boolean mPullRefreshEnable;
    private final List<OnRefreshOffsetChangedListener> mRefreshOffsetChangedListeners;
    private int mRefreshViewBgColor;
    private volatile boolean mRefreshing;
    private View mTargetView;
    private WXOnLoadingListener onLoadingListener;
    private WXOnRefreshListener onRefreshListener;
    private volatile float refreshViewFlowHeight;
    private volatile float refreshViewHeight;

    public interface OnRefreshOffsetChangedListener {
        void onOffsetChanged(int i);
    }

    public interface WXOnLoadingListener {
        void onLoading();

        void onPullingUp(float f, int i, float f2);
    }

    public interface WXOnRefreshListener {
        void onPullingDown(float f, int i, float f2);

        void onRefresh();
    }

    private static /* synthetic */ boolean[] $jacocoInit() {
        boolean[] zArr = $jacocoData;
        if (zArr != null) {
            return zArr;
        }
        boolean[] a2 = Offline.a(229143306931371837L, "com/taobao/weex/ui/view/refresh/core/WXSwipeLayout", 323);
        $jacocoData = a2;
        return a2;
    }

    static /* synthetic */ WXRefreshView access$000(WXSwipeLayout wXSwipeLayout) {
        boolean[] $jacocoInit = $jacocoInit();
        WXRefreshView wXRefreshView = wXSwipeLayout.headerView;
        $jacocoInit[315] = true;
        return wXRefreshView;
    }

    static /* synthetic */ void access$100(WXSwipeLayout wXSwipeLayout, int i) {
        boolean[] $jacocoInit = $jacocoInit();
        wXSwipeLayout.notifyOnRefreshOffsetChangedListener(i);
        $jacocoInit[316] = true;
    }

    static /* synthetic */ void access$200(WXSwipeLayout wXSwipeLayout, float f) {
        boolean[] $jacocoInit = $jacocoInit();
        wXSwipeLayout.moveTargetView(f);
        $jacocoInit[317] = true;
    }

    static /* synthetic */ WXOnRefreshListener access$300(WXSwipeLayout wXSwipeLayout) {
        boolean[] $jacocoInit = $jacocoInit();
        WXOnRefreshListener wXOnRefreshListener = wXSwipeLayout.onRefreshListener;
        $jacocoInit[318] = true;
        return wXOnRefreshListener;
    }

    static /* synthetic */ void access$400(WXSwipeLayout wXSwipeLayout) {
        boolean[] $jacocoInit = $jacocoInit();
        wXSwipeLayout.resetRefreshState();
        $jacocoInit[319] = true;
    }

    static /* synthetic */ WXRefreshView access$500(WXSwipeLayout wXSwipeLayout) {
        boolean[] $jacocoInit = $jacocoInit();
        WXRefreshView wXRefreshView = wXSwipeLayout.footerView;
        $jacocoInit[320] = true;
        return wXRefreshView;
    }

    static /* synthetic */ WXOnLoadingListener access$600(WXSwipeLayout wXSwipeLayout) {
        boolean[] $jacocoInit = $jacocoInit();
        WXOnLoadingListener wXOnLoadingListener = wXSwipeLayout.onLoadingListener;
        $jacocoInit[321] = true;
        return wXOnLoadingListener;
    }

    static /* synthetic */ void access$700(WXSwipeLayout wXSwipeLayout) {
        boolean[] $jacocoInit = $jacocoInit();
        wXSwipeLayout.resetLoadmoreState();
        $jacocoInit[322] = true;
    }

    static class WXRefreshAnimatorListener implements Animator.AnimatorListener {
        private static transient /* synthetic */ boolean[] $jacocoData;

        private static /* synthetic */ boolean[] $jacocoInit() {
            boolean[] zArr = $jacocoData;
            if (zArr != null) {
                return zArr;
            }
            boolean[] a2 = Offline.a(-8035634741216009808L, "com/taobao/weex/ui/view/refresh/core/WXSwipeLayout$WXRefreshAnimatorListener", 5);
            $jacocoData = a2;
            return a2;
        }

        WXRefreshAnimatorListener() {
            $jacocoInit()[0] = true;
        }

        public void onAnimationStart(Animator animator) {
            $jacocoInit()[1] = true;
        }

        public void onAnimationEnd(Animator animator) {
            $jacocoInit()[2] = true;
        }

        public void onAnimationCancel(Animator animator) {
            $jacocoInit()[3] = true;
        }

        public void onAnimationRepeat(Animator animator) {
            $jacocoInit()[4] = true;
        }
    }

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public WXSwipeLayout(Context context) {
        super(context);
        boolean[] $jacocoInit = $jacocoInit();
        $jacocoInit[0] = true;
        this.mRefreshOffsetChangedListeners = new LinkedList();
        this.mPullRefreshEnable = false;
        this.mPullLoadEnable = false;
        this.mRefreshing = false;
        this.refreshViewHeight = 0.0f;
        this.loadingViewHeight = 0.0f;
        this.refreshViewFlowHeight = 0.0f;
        this.loadingViewFlowHeight = 0.0f;
        this.mCurrentAction = -1;
        this.isConfirm = false;
        $jacocoInit[1] = true;
        initAttrs(context, (AttributeSet) null);
        $jacocoInit[2] = true;
    }

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public WXSwipeLayout(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        boolean[] $jacocoInit = $jacocoInit();
        $jacocoInit[3] = true;
        this.mRefreshOffsetChangedListeners = new LinkedList();
        this.mPullRefreshEnable = false;
        this.mPullLoadEnable = false;
        this.mRefreshing = false;
        this.refreshViewHeight = 0.0f;
        this.loadingViewHeight = 0.0f;
        this.refreshViewFlowHeight = 0.0f;
        this.loadingViewFlowHeight = 0.0f;
        this.mCurrentAction = -1;
        this.isConfirm = false;
        $jacocoInit[4] = true;
        initAttrs(context, attributeSet);
        $jacocoInit[5] = true;
    }

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public WXSwipeLayout(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        boolean[] $jacocoInit = $jacocoInit();
        $jacocoInit[6] = true;
        this.mRefreshOffsetChangedListeners = new LinkedList();
        this.mPullRefreshEnable = false;
        this.mPullLoadEnable = false;
        this.mRefreshing = false;
        this.refreshViewHeight = 0.0f;
        this.loadingViewHeight = 0.0f;
        this.refreshViewFlowHeight = 0.0f;
        this.loadingViewFlowHeight = 0.0f;
        this.mCurrentAction = -1;
        this.isConfirm = false;
        $jacocoInit[7] = true;
        initAttrs(context, attributeSet);
        $jacocoInit[8] = true;
    }

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    @TargetApi(21)
    public WXSwipeLayout(Context context, AttributeSet attributeSet, int i, int i2) {
        super(context, attributeSet, i, i2);
        boolean[] $jacocoInit = $jacocoInit();
        $jacocoInit[9] = true;
        this.mRefreshOffsetChangedListeners = new LinkedList();
        this.mPullRefreshEnable = false;
        this.mPullLoadEnable = false;
        this.mRefreshing = false;
        this.refreshViewHeight = 0.0f;
        this.loadingViewHeight = 0.0f;
        this.refreshViewFlowHeight = 0.0f;
        this.loadingViewFlowHeight = 0.0f;
        this.mCurrentAction = -1;
        this.isConfirm = false;
        $jacocoInit[10] = true;
        initAttrs(context, attributeSet);
        $jacocoInit[11] = true;
    }

    private void initAttrs(Context context, AttributeSet attributeSet) {
        boolean[] $jacocoInit = $jacocoInit();
        if (getChildCount() <= 1) {
            this.mNestedScrollingParentHelper = new NestedScrollingParentHelper(this);
            $jacocoInit[14] = true;
            this.mNestedScrollingChildHelper = new NestedScrollingChildHelper(this);
            $jacocoInit[15] = true;
            setNestedScrollingEnabled(false);
            $jacocoInit[16] = true;
            if (!isInEditMode()) {
                $jacocoInit[17] = true;
            } else if (attributeSet != null) {
                $jacocoInit[18] = true;
            } else {
                $jacocoInit[19] = true;
                return;
            }
            this.mRefreshViewBgColor = 0;
            this.mProgressBgColor = 0;
            this.mProgressColor = -65536;
            $jacocoInit[20] = true;
            return;
        }
        $jacocoInit[12] = true;
        RuntimeException runtimeException = new RuntimeException("WXSwipeLayout should not have more than one child");
        $jacocoInit[13] = true;
        throw runtimeException;
    }

    /* access modifiers changed from: protected */
    public void onAttachedToWindow() {
        boolean[] $jacocoInit = $jacocoInit();
        super.onAttachedToWindow();
        $jacocoInit[21] = true;
        if (this.mTargetView != null) {
            $jacocoInit[22] = true;
        } else if (getChildCount() <= 0) {
            $jacocoInit[23] = true;
        } else {
            $jacocoInit[24] = true;
            this.mTargetView = getChildAt(0);
            $jacocoInit[25] = true;
        }
        if (this.mTargetView == null) {
            $jacocoInit[26] = true;
        } else {
            if (this.headerView == null) {
                $jacocoInit[27] = true;
            } else if (this.footerView != null) {
                $jacocoInit[28] = true;
            } else {
                $jacocoInit[29] = true;
            }
            setRefreshView();
            $jacocoInit[30] = true;
        }
        $jacocoInit[31] = true;
    }

    public void addTargetView(View view) {
        boolean[] $jacocoInit = $jacocoInit();
        addView(view, new FrameLayout.LayoutParams(-1, -1));
        $jacocoInit[32] = true;
        setRefreshView();
        $jacocoInit[33] = true;
    }

    private void setRefreshView() {
        boolean[] $jacocoInit = $jacocoInit();
        FrameLayout.LayoutParams layoutParams = new FrameLayout.LayoutParams(-1, 0);
        $jacocoInit[34] = true;
        this.headerView = new WXRefreshView(getContext());
        $jacocoInit[35] = true;
        this.headerView.setStartEndTrim(0.0f, 0.75f);
        $jacocoInit[36] = true;
        this.headerView.setBackgroundColor(this.mRefreshViewBgColor);
        $jacocoInit[37] = true;
        this.headerView.setProgressBgColor(this.mProgressBgColor);
        $jacocoInit[38] = true;
        this.headerView.setProgressColor(this.mProgressColor);
        $jacocoInit[39] = true;
        this.headerView.setContentGravity(80);
        $jacocoInit[40] = true;
        addView(this.headerView, layoutParams);
        $jacocoInit[41] = true;
        FrameLayout.LayoutParams layoutParams2 = new FrameLayout.LayoutParams(-1, 0);
        layoutParams2.gravity = 80;
        $jacocoInit[42] = true;
        this.footerView = new WXRefreshView(getContext());
        $jacocoInit[43] = true;
        this.footerView.setStartEndTrim(0.5f, 1.25f);
        $jacocoInit[44] = true;
        this.footerView.setBackgroundColor(this.mRefreshViewBgColor);
        $jacocoInit[45] = true;
        this.footerView.setProgressBgColor(this.mProgressBgColor);
        $jacocoInit[46] = true;
        this.footerView.setProgressColor(this.mProgressColor);
        $jacocoInit[47] = true;
        this.footerView.setContentGravity(48);
        $jacocoInit[48] = true;
        addView(this.footerView, layoutParams2);
        $jacocoInit[49] = true;
    }

    public boolean onInterceptTouchEvent(MotionEvent motionEvent) {
        boolean[] $jacocoInit = $jacocoInit();
        if (this.mPullRefreshEnable) {
            $jacocoInit[50] = true;
        } else if (this.mPullLoadEnable) {
            $jacocoInit[51] = true;
        } else {
            $jacocoInit[52] = true;
            return false;
        }
        if (!isEnabled()) {
            $jacocoInit[53] = true;
        } else if (canChildScrollUp()) {
            $jacocoInit[54] = true;
        } else if (this.mRefreshing) {
            $jacocoInit[55] = true;
        } else if (this.mNestedScrollInProgress) {
            $jacocoInit[56] = true;
        } else {
            boolean onInterceptTouchEvent = super.onInterceptTouchEvent(motionEvent);
            $jacocoInit[58] = true;
            return onInterceptTouchEvent;
        }
        $jacocoInit[57] = true;
        return false;
    }

    public void setNestedScrollingEnabled(boolean z) {
        boolean[] $jacocoInit = $jacocoInit();
        this.mNestedScrollingChildHelper.setNestedScrollingEnabled(z);
        $jacocoInit[59] = true;
    }

    public boolean isNestedScrollingEnabled() {
        boolean[] $jacocoInit = $jacocoInit();
        boolean isNestedScrollingEnabled = this.mNestedScrollingChildHelper.isNestedScrollingEnabled();
        $jacocoInit[60] = true;
        return isNestedScrollingEnabled;
    }

    public boolean startNestedScroll(int i) {
        boolean[] $jacocoInit = $jacocoInit();
        boolean startNestedScroll = this.mNestedScrollingChildHelper.startNestedScroll(i);
        if (!startNestedScroll) {
            $jacocoInit[61] = true;
        } else if (this.mNestedScrollAcceptedParent != null) {
            $jacocoInit[62] = true;
        } else {
            $jacocoInit[63] = true;
            ViewParent parent = getParent();
            $jacocoInit[64] = true;
            View view = this;
            while (true) {
                if (parent == null) {
                    $jacocoInit[65] = true;
                    break;
                }
                $jacocoInit[66] = true;
                if (ViewParentCompat.onStartNestedScroll(parent, view, this, i)) {
                    this.mNestedScrollAcceptedParent = parent;
                    $jacocoInit[67] = true;
                    break;
                }
                if (!(parent instanceof View)) {
                    $jacocoInit[68] = true;
                } else {
                    view = (View) parent;
                    $jacocoInit[69] = true;
                }
                parent = parent.getParent();
                $jacocoInit[70] = true;
            }
        }
        $jacocoInit[71] = true;
        return startNestedScroll;
    }

    public void stopNestedScroll() {
        boolean[] $jacocoInit = $jacocoInit();
        this.mNestedScrollingChildHelper.stopNestedScroll();
        if (this.mNestedScrollAcceptedParent == null) {
            $jacocoInit[72] = true;
        } else {
            this.mNestedScrollAcceptedParent = null;
            $jacocoInit[73] = true;
        }
        $jacocoInit[74] = true;
    }

    public boolean hasNestedScrollingParent() {
        boolean[] $jacocoInit = $jacocoInit();
        boolean hasNestedScrollingParent = this.mNestedScrollingChildHelper.hasNestedScrollingParent();
        $jacocoInit[75] = true;
        return hasNestedScrollingParent;
    }

    public boolean dispatchNestedScroll(int i, int i2, int i3, int i4, int[] iArr) {
        boolean[] $jacocoInit = $jacocoInit();
        boolean dispatchNestedScroll = this.mNestedScrollingChildHelper.dispatchNestedScroll(i, i2, i3, i4, iArr);
        $jacocoInit[76] = true;
        return dispatchNestedScroll;
    }

    public boolean dispatchNestedPreScroll(int i, int i2, int[] iArr, int[] iArr2) {
        boolean[] $jacocoInit = $jacocoInit();
        boolean dispatchNestedPreScroll = this.mNestedScrollingChildHelper.dispatchNestedPreScroll(i, i2, iArr, iArr2);
        $jacocoInit[77] = true;
        return dispatchNestedPreScroll;
    }

    public boolean dispatchNestedFling(float f, float f2, boolean z) {
        boolean[] $jacocoInit = $jacocoInit();
        boolean dispatchNestedFling = this.mNestedScrollingChildHelper.dispatchNestedFling(f, f2, z);
        $jacocoInit[78] = true;
        return dispatchNestedFling;
    }

    public boolean dispatchNestedPreFling(float f, float f2) {
        boolean[] $jacocoInit = $jacocoInit();
        boolean dispatchNestedPreFling = this.mNestedScrollingChildHelper.dispatchNestedPreFling(f, f2);
        $jacocoInit[79] = true;
        return dispatchNestedPreFling;
    }

    public boolean onNestedPreFling(View view, float f, float f2) {
        boolean[] $jacocoInit = $jacocoInit();
        if (isNestedScrollingEnabled()) {
            $jacocoInit[80] = true;
            boolean dispatchNestedPreFling = dispatchNestedPreFling(f, f2);
            $jacocoInit[81] = true;
            return dispatchNestedPreFling;
        }
        $jacocoInit[82] = true;
        return false;
    }

    public boolean onNestedFling(View view, float f, float f2, boolean z) {
        boolean[] $jacocoInit = $jacocoInit();
        if (isNestedScrollingEnabled()) {
            $jacocoInit[83] = true;
            boolean dispatchNestedFling = dispatchNestedFling(f, f2, z);
            $jacocoInit[84] = true;
            return dispatchNestedFling;
        }
        $jacocoInit[85] = true;
        return false;
    }

    public boolean onStartNestedScroll(View view, View view2, int i) {
        boolean z;
        boolean[] $jacocoInit = $jacocoInit();
        if (!isEnabled()) {
            $jacocoInit[86] = true;
        } else if (this.mRefreshing) {
            $jacocoInit[87] = true;
        } else if ((i & 2) == 0) {
            $jacocoInit[88] = true;
        } else {
            $jacocoInit[89] = true;
            z = true;
            $jacocoInit[91] = true;
            return z;
        }
        z = false;
        $jacocoInit[90] = true;
        $jacocoInit[91] = true;
        return z;
    }

    public void onNestedScrollAccepted(View view, View view2, int i) {
        boolean[] $jacocoInit = $jacocoInit();
        this.mNestedScrollingParentHelper.onNestedScrollAccepted(view, view2, i);
        $jacocoInit[92] = true;
        if (!isNestedScrollingEnabled()) {
            $jacocoInit[93] = true;
        } else {
            $jacocoInit[94] = true;
            startNestedScroll(i & 2);
            this.mNestedScrollInProgress = true;
            $jacocoInit[95] = true;
        }
        $jacocoInit[96] = true;
    }

    public void onNestedPreScroll(View view, int i, int i2, int[] iArr) {
        boolean[] $jacocoInit = $jacocoInit();
        int[] iArr2 = this.mParentScrollConsumed;
        $jacocoInit[97] = true;
        if (!isNestedScrollingEnabled()) {
            $jacocoInit[98] = true;
        } else {
            $jacocoInit[99] = true;
            if (!dispatchNestedPreScroll(i - iArr[0], i2 - iArr[1], iArr2, (int[]) null)) {
                $jacocoInit[100] = true;
            } else {
                iArr[0] = iArr[0] + iArr2[0];
                iArr[1] = iArr[1] + iArr2[1];
                $jacocoInit[101] = true;
                return;
            }
        }
        if (this.mPullRefreshEnable) {
            $jacocoInit[102] = true;
        } else if (this.mPullLoadEnable) {
            $jacocoInit[103] = true;
        } else {
            $jacocoInit[104] = true;
            return;
        }
        if (canChildScrollUp()) {
            $jacocoInit[105] = true;
        } else if (!isNestedScrollingEnabled()) {
            $jacocoInit[106] = true;
        } else if (this.mNestedScrollAcceptedParent == null) {
            $jacocoInit[107] = true;
        } else if (this.mNestedScrollAcceptedParent == this.mTargetView) {
            $jacocoInit[108] = true;
        } else {
            ViewGroup viewGroup = (ViewGroup) this.mNestedScrollAcceptedParent;
            $jacocoInit[109] = true;
            if (viewGroup.getChildCount() <= 0) {
                $jacocoInit[110] = true;
            } else {
                $jacocoInit[111] = true;
                int childCount = viewGroup.getChildCount();
                $jacocoInit[112] = true;
                int i3 = 0;
                while (true) {
                    if (i3 >= childCount) {
                        $jacocoInit[113] = true;
                        break;
                    }
                    $jacocoInit[114] = true;
                    View childAt = viewGroup.getChildAt(i3);
                    $jacocoInit[115] = true;
                    if (childAt.getVisibility() == 8) {
                        $jacocoInit[116] = true;
                    } else if (childAt.getMeasuredHeight() <= 0) {
                        $jacocoInit[117] = true;
                    } else {
                        $jacocoInit[118] = true;
                        if (childAt.getTop() >= 0) {
                            $jacocoInit[119] = true;
                        } else {
                            $jacocoInit[120] = true;
                            return;
                        }
                    }
                    i3++;
                    $jacocoInit[121] = true;
                }
            }
        }
        int calculateDistanceY = (int) calculateDistanceY(view, i2);
        this.mRefreshing = false;
        if (this.isConfirm) {
            $jacocoInit[122] = true;
        } else {
            $jacocoInit[123] = true;
            if (calculateDistanceY >= 0) {
                $jacocoInit[124] = true;
            } else if (canChildScrollUp()) {
                $jacocoInit[125] = true;
            } else {
                this.mCurrentAction = 0;
                this.isConfirm = true;
                $jacocoInit[126] = true;
            }
            if (calculateDistanceY <= 0) {
                $jacocoInit[127] = true;
            } else if (canChildScrollDown()) {
                $jacocoInit[128] = true;
            } else if (this.mRefreshing) {
                $jacocoInit[129] = true;
            } else {
                this.mCurrentAction = 1;
                this.isConfirm = true;
                $jacocoInit[130] = true;
            }
        }
        if (!moveSpinner((float) (-calculateDistanceY))) {
            $jacocoInit[131] = true;
        } else {
            $jacocoInit[132] = true;
            if (canChildScrollUp()) {
                $jacocoInit[133] = true;
            } else if (!this.mPullRefreshEnable) {
                $jacocoInit[134] = true;
            } else {
                View view2 = this.mTargetView;
                $jacocoInit[135] = true;
                if (view2.getTranslationY() <= 0.0f) {
                    $jacocoInit[136] = true;
                } else if (i2 <= 0) {
                    $jacocoInit[137] = true;
                } else {
                    iArr[1] = iArr[1] + i2;
                    $jacocoInit[138] = true;
                }
            }
            if (canChildScrollDown()) {
                $jacocoInit[139] = true;
            } else if (!this.mPullLoadEnable) {
                $jacocoInit[140] = true;
            } else {
                View view3 = this.mTargetView;
                $jacocoInit[141] = true;
                if (view3.getTranslationY() >= 0.0f) {
                    $jacocoInit[142] = true;
                } else if (i2 >= 0) {
                    $jacocoInit[143] = true;
                } else {
                    iArr[1] = iArr[1] + i2;
                    $jacocoInit[144] = true;
                }
            }
            iArr[1] = iArr[1] + calculateDistanceY;
            $jacocoInit[145] = true;
        }
        $jacocoInit[146] = true;
    }

    public int getNestedScrollAxes() {
        boolean[] $jacocoInit = $jacocoInit();
        int nestedScrollAxes = this.mNestedScrollingParentHelper.getNestedScrollAxes();
        $jacocoInit[147] = true;
        return nestedScrollAxes;
    }

    public void onStopNestedScroll(View view) {
        boolean[] $jacocoInit = $jacocoInit();
        this.mNestedScrollingParentHelper.onStopNestedScroll(view);
        $jacocoInit[148] = true;
        handlerAction();
        $jacocoInit[149] = true;
        if (!isNestedScrollingEnabled()) {
            $jacocoInit[150] = true;
        } else {
            this.mNestedScrollInProgress = true;
            $jacocoInit[151] = true;
            stopNestedScroll();
            $jacocoInit[152] = true;
        }
        $jacocoInit[153] = true;
    }

    public void onNestedScroll(View view, int i, int i2, int i3, int i4) {
        boolean[] $jacocoInit = $jacocoInit();
        if (!isNestedScrollingEnabled()) {
            $jacocoInit[154] = true;
        } else {
            $jacocoInit[155] = true;
            dispatchNestedScroll(i, i2, i3, i4, this.mParentOffsetInWindow);
            $jacocoInit[156] = true;
        }
        $jacocoInit[157] = true;
    }

    private double calculateDistanceY(View view, int i) {
        boolean[] $jacocoInit = $jacocoInit();
        int measuredHeight = view.getMeasuredHeight();
        $jacocoInit[158] = true;
        double abs = (double) (((float) measuredHeight) - Math.abs(view.getY()));
        Double.isNaN(abs);
        double d = (double) measuredHeight;
        Double.isNaN(d);
        double d2 = ((abs / 1.0d) / d) * 0.4000000059604645d;
        if (d2 > 0.01d) {
            $jacocoInit[159] = true;
        } else {
            $jacocoInit[160] = true;
            d2 = 0.01d;
        }
        double d3 = (double) i;
        Double.isNaN(d3);
        double d4 = d2 * d3;
        $jacocoInit[161] = true;
        return d4;
    }

    private boolean moveSpinner(float f) {
        boolean[] $jacocoInit = $jacocoInit();
        if (this.mRefreshing) {
            $jacocoInit[162] = true;
            return false;
        }
        if (canChildScrollUp()) {
            $jacocoInit[163] = true;
        } else if (!this.mPullRefreshEnable) {
            $jacocoInit[164] = true;
        } else if (this.mCurrentAction != 0) {
            $jacocoInit[165] = true;
        } else {
            $jacocoInit[166] = true;
            FrameLayout.LayoutParams layoutParams = (FrameLayout.LayoutParams) this.headerView.getLayoutParams();
            layoutParams.height = (int) (((float) layoutParams.height) + f);
            if (layoutParams.height >= 0) {
                $jacocoInit[167] = true;
            } else {
                layoutParams.height = 0;
                $jacocoInit[168] = true;
            }
            if (layoutParams.height != 0) {
                $jacocoInit[169] = true;
            } else {
                this.isConfirm = false;
                this.mCurrentAction = -1;
                $jacocoInit[170] = true;
            }
            this.headerView.setLayoutParams(layoutParams);
            $jacocoInit[171] = true;
            this.onRefreshListener.onPullingDown(f, layoutParams.height, this.refreshViewFlowHeight);
            $jacocoInit[172] = true;
            notifyOnRefreshOffsetChangedListener(layoutParams.height);
            $jacocoInit[173] = true;
            this.headerView.setProgressRotation(((float) layoutParams.height) / this.refreshViewFlowHeight);
            $jacocoInit[174] = true;
            moveTargetView((float) layoutParams.height);
            $jacocoInit[175] = true;
            return true;
        }
        if (canChildScrollDown()) {
            $jacocoInit[176] = true;
        } else if (!this.mPullLoadEnable) {
            $jacocoInit[177] = true;
        } else if (this.mCurrentAction != 1) {
            $jacocoInit[178] = true;
        } else {
            $jacocoInit[179] = true;
            FrameLayout.LayoutParams layoutParams2 = (FrameLayout.LayoutParams) this.footerView.getLayoutParams();
            layoutParams2.height = (int) (((float) layoutParams2.height) - f);
            if (layoutParams2.height >= 0) {
                $jacocoInit[180] = true;
            } else {
                layoutParams2.height = 0;
                $jacocoInit[181] = true;
            }
            if (layoutParams2.height != 0) {
                $jacocoInit[182] = true;
            } else {
                this.isConfirm = false;
                this.mCurrentAction = -1;
                $jacocoInit[183] = true;
            }
            this.footerView.setLayoutParams(layoutParams2);
            $jacocoInit[184] = true;
            this.onLoadingListener.onPullingUp(f, layoutParams2.height, this.loadingViewFlowHeight);
            $jacocoInit[185] = true;
            this.footerView.setProgressRotation(((float) layoutParams2.height) / this.loadingViewFlowHeight);
            $jacocoInit[186] = true;
            moveTargetView((float) (-layoutParams2.height));
            $jacocoInit[187] = true;
            return true;
        }
        $jacocoInit[188] = true;
        return false;
    }

    private void moveTargetView(float f) {
        boolean[] $jacocoInit = $jacocoInit();
        this.mTargetView.setTranslationY(f);
        $jacocoInit[189] = true;
    }

    private void handlerAction() {
        boolean[] $jacocoInit = $jacocoInit();
        if (isRefreshing()) {
            $jacocoInit[190] = true;
            return;
        }
        this.isConfirm = false;
        if (!this.mPullRefreshEnable) {
            $jacocoInit[191] = true;
        } else if (this.mCurrentAction != 0) {
            $jacocoInit[192] = true;
        } else {
            $jacocoInit[193] = true;
            FrameLayout.LayoutParams layoutParams = (FrameLayout.LayoutParams) this.headerView.getLayoutParams();
            if (((float) layoutParams.height) >= this.refreshViewHeight) {
                $jacocoInit[194] = true;
                startRefresh(layoutParams.height);
                $jacocoInit[195] = true;
            } else if (layoutParams.height > 0) {
                $jacocoInit[196] = true;
                resetHeaderView(layoutParams.height);
                $jacocoInit[197] = true;
            } else {
                resetRefreshState();
                $jacocoInit[198] = true;
            }
        }
        if (!this.mPullLoadEnable) {
            $jacocoInit[199] = true;
        } else if (this.mCurrentAction != 1) {
            $jacocoInit[200] = true;
        } else {
            $jacocoInit[201] = true;
            FrameLayout.LayoutParams layoutParams2 = (FrameLayout.LayoutParams) this.footerView.getLayoutParams();
            if (((float) layoutParams2.height) >= this.loadingViewHeight) {
                $jacocoInit[202] = true;
                startLoadmore(layoutParams2.height);
                $jacocoInit[203] = true;
            } else if (layoutParams2.height > 0) {
                $jacocoInit[204] = true;
                resetFootView(layoutParams2.height);
                $jacocoInit[205] = true;
            } else {
                resetLoadmoreState();
                $jacocoInit[206] = true;
            }
        }
        $jacocoInit[207] = true;
    }

    private void startRefresh(int i) {
        boolean[] $jacocoInit = $jacocoInit();
        this.mRefreshing = true;
        $jacocoInit[208] = true;
        ValueAnimator ofFloat = ValueAnimator.ofFloat(new float[]{(float) i, this.refreshViewHeight});
        $jacocoInit[209] = true;
        ofFloat.addUpdateListener(new ValueAnimator.AnimatorUpdateListener(this) {
            private static transient /* synthetic */ boolean[] $jacocoData;
            final /* synthetic */ WXSwipeLayout this$0;

            private static /* synthetic */ boolean[] $jacocoInit() {
                boolean[] zArr = $jacocoData;
                if (zArr != null) {
                    return zArr;
                }
                boolean[] a2 = Offline.a(181355842346884414L, "com/taobao/weex/ui/view/refresh/core/WXSwipeLayout$1", 6);
                $jacocoData = a2;
                return a2;
            }

            {
                boolean[] $jacocoInit = $jacocoInit();
                this.this$0 = r3;
                $jacocoInit[0] = true;
            }

            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                boolean[] $jacocoInit = $jacocoInit();
                FrameLayout.LayoutParams layoutParams = (FrameLayout.LayoutParams) WXSwipeLayout.access$000(this.this$0).getLayoutParams();
                $jacocoInit[1] = true;
                layoutParams.height = (int) ((Float) valueAnimator.getAnimatedValue()).floatValue();
                $jacocoInit[2] = true;
                WXSwipeLayout.access$100(this.this$0, layoutParams.height);
                $jacocoInit[3] = true;
                WXSwipeLayout.access$000(this.this$0).setLayoutParams(layoutParams);
                $jacocoInit[4] = true;
                WXSwipeLayout.access$200(this.this$0, (float) layoutParams.height);
                $jacocoInit[5] = true;
            }
        });
        $jacocoInit[210] = true;
        ofFloat.addListener(new WXRefreshAnimatorListener(this) {
            private static transient /* synthetic */ boolean[] $jacocoData;
            final /* synthetic */ WXSwipeLayout this$0;

            private static /* synthetic */ boolean[] $jacocoInit() {
                boolean[] zArr = $jacocoData;
                if (zArr != null) {
                    return zArr;
                }
                boolean[] a2 = Offline.a(-1331016115189954611L, "com/taobao/weex/ui/view/refresh/core/WXSwipeLayout$2", 6);
                $jacocoData = a2;
                return a2;
            }

            {
                boolean[] $jacocoInit = $jacocoInit();
                this.this$0 = r3;
                $jacocoInit[0] = true;
            }

            public void onAnimationEnd(Animator animator) {
                boolean[] $jacocoInit = $jacocoInit();
                WXSwipeLayout.access$000(this.this$0).startAnimation();
                $jacocoInit[1] = true;
                if (WXSwipeLayout.access$300(this.this$0) == null) {
                    $jacocoInit[2] = true;
                } else {
                    $jacocoInit[3] = true;
                    WXSwipeLayout.access$300(this.this$0).onRefresh();
                    $jacocoInit[4] = true;
                }
                $jacocoInit[5] = true;
            }
        });
        $jacocoInit[211] = true;
        ofFloat.setDuration(300);
        $jacocoInit[212] = true;
        ofFloat.start();
        $jacocoInit[213] = true;
    }

    private void resetHeaderView(int i) {
        boolean[] $jacocoInit = $jacocoInit();
        this.headerView.stopAnimation();
        $jacocoInit[214] = true;
        this.headerView.setStartEndTrim(0.0f, 0.75f);
        $jacocoInit[215] = true;
        ValueAnimator ofFloat = ValueAnimator.ofFloat(new float[]{(float) i, 0.0f});
        $jacocoInit[216] = true;
        ofFloat.addUpdateListener(new ValueAnimator.AnimatorUpdateListener(this) {
            private static transient /* synthetic */ boolean[] $jacocoData;
            final /* synthetic */ WXSwipeLayout this$0;

            private static /* synthetic */ boolean[] $jacocoInit() {
                boolean[] zArr = $jacocoData;
                if (zArr != null) {
                    return zArr;
                }
                boolean[] a2 = Offline.a(7805195622079707035L, "com/taobao/weex/ui/view/refresh/core/WXSwipeLayout$3", 6);
                $jacocoData = a2;
                return a2;
            }

            {
                boolean[] $jacocoInit = $jacocoInit();
                this.this$0 = r3;
                $jacocoInit[0] = true;
            }

            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                boolean[] $jacocoInit = $jacocoInit();
                FrameLayout.LayoutParams layoutParams = (FrameLayout.LayoutParams) WXSwipeLayout.access$000(this.this$0).getLayoutParams();
                $jacocoInit[1] = true;
                layoutParams.height = (int) ((Float) valueAnimator.getAnimatedValue()).floatValue();
                $jacocoInit[2] = true;
                WXSwipeLayout.access$100(this.this$0, layoutParams.height);
                $jacocoInit[3] = true;
                WXSwipeLayout.access$000(this.this$0).setLayoutParams(layoutParams);
                $jacocoInit[4] = true;
                WXSwipeLayout.access$200(this.this$0, (float) layoutParams.height);
                $jacocoInit[5] = true;
            }
        });
        $jacocoInit[217] = true;
        ofFloat.addListener(new WXRefreshAnimatorListener(this) {
            private static transient /* synthetic */ boolean[] $jacocoData;
            final /* synthetic */ WXSwipeLayout this$0;

            private static /* synthetic */ boolean[] $jacocoInit() {
                boolean[] zArr = $jacocoData;
                if (zArr != null) {
                    return zArr;
                }
                boolean[] a2 = Offline.a(-2211152490931162107L, "com/taobao/weex/ui/view/refresh/core/WXSwipeLayout$4", 2);
                $jacocoData = a2;
                return a2;
            }

            {
                boolean[] $jacocoInit = $jacocoInit();
                this.this$0 = r3;
                $jacocoInit[0] = true;
            }

            public void onAnimationEnd(Animator animator) {
                boolean[] $jacocoInit = $jacocoInit();
                WXSwipeLayout.access$400(this.this$0);
                $jacocoInit[1] = true;
            }
        });
        $jacocoInit[218] = true;
        ofFloat.setDuration(300);
        $jacocoInit[219] = true;
        ofFloat.start();
        $jacocoInit[220] = true;
    }

    private void resetRefreshState() {
        boolean[] $jacocoInit = $jacocoInit();
        this.mRefreshing = false;
        this.isConfirm = false;
        this.mCurrentAction = -1;
        $jacocoInit[221] = true;
    }

    private void startLoadmore(int i) {
        boolean[] $jacocoInit = $jacocoInit();
        this.mRefreshing = true;
        $jacocoInit[222] = true;
        ValueAnimator ofFloat = ValueAnimator.ofFloat(new float[]{(float) i, this.loadingViewHeight});
        $jacocoInit[223] = true;
        ofFloat.addUpdateListener(new ValueAnimator.AnimatorUpdateListener(this) {
            private static transient /* synthetic */ boolean[] $jacocoData;
            final /* synthetic */ WXSwipeLayout this$0;

            private static /* synthetic */ boolean[] $jacocoInit() {
                boolean[] zArr = $jacocoData;
                if (zArr != null) {
                    return zArr;
                }
                boolean[] a2 = Offline.a(7568913479955351449L, "com/taobao/weex/ui/view/refresh/core/WXSwipeLayout$5", 5);
                $jacocoData = a2;
                return a2;
            }

            {
                boolean[] $jacocoInit = $jacocoInit();
                this.this$0 = r3;
                $jacocoInit[0] = true;
            }

            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                boolean[] $jacocoInit = $jacocoInit();
                FrameLayout.LayoutParams layoutParams = (FrameLayout.LayoutParams) WXSwipeLayout.access$500(this.this$0).getLayoutParams();
                $jacocoInit[1] = true;
                layoutParams.height = (int) ((Float) valueAnimator.getAnimatedValue()).floatValue();
                $jacocoInit[2] = true;
                WXSwipeLayout.access$500(this.this$0).setLayoutParams(layoutParams);
                $jacocoInit[3] = true;
                WXSwipeLayout.access$200(this.this$0, (float) (-layoutParams.height));
                $jacocoInit[4] = true;
            }
        });
        $jacocoInit[224] = true;
        ofFloat.addListener(new WXRefreshAnimatorListener(this) {
            private static transient /* synthetic */ boolean[] $jacocoData;
            final /* synthetic */ WXSwipeLayout this$0;

            private static /* synthetic */ boolean[] $jacocoInit() {
                boolean[] zArr = $jacocoData;
                if (zArr != null) {
                    return zArr;
                }
                boolean[] a2 = Offline.a(3322016925138002090L, "com/taobao/weex/ui/view/refresh/core/WXSwipeLayout$6", 6);
                $jacocoData = a2;
                return a2;
            }

            {
                boolean[] $jacocoInit = $jacocoInit();
                this.this$0 = r3;
                $jacocoInit[0] = true;
            }

            public void onAnimationEnd(Animator animator) {
                boolean[] $jacocoInit = $jacocoInit();
                WXSwipeLayout.access$500(this.this$0).startAnimation();
                $jacocoInit[1] = true;
                if (WXSwipeLayout.access$600(this.this$0) == null) {
                    $jacocoInit[2] = true;
                } else {
                    $jacocoInit[3] = true;
                    WXSwipeLayout.access$600(this.this$0).onLoading();
                    $jacocoInit[4] = true;
                }
                $jacocoInit[5] = true;
            }
        });
        $jacocoInit[225] = true;
        ofFloat.setDuration(300);
        $jacocoInit[226] = true;
        ofFloat.start();
        $jacocoInit[227] = true;
    }

    private void resetFootView(int i) {
        boolean[] $jacocoInit = $jacocoInit();
        this.footerView.stopAnimation();
        $jacocoInit[228] = true;
        this.footerView.setStartEndTrim(0.5f, 1.25f);
        $jacocoInit[229] = true;
        ValueAnimator ofFloat = ValueAnimator.ofFloat(new float[]{(float) i, 0.0f});
        $jacocoInit[230] = true;
        ofFloat.addUpdateListener(new ValueAnimator.AnimatorUpdateListener(this) {
            private static transient /* synthetic */ boolean[] $jacocoData;
            final /* synthetic */ WXSwipeLayout this$0;

            private static /* synthetic */ boolean[] $jacocoInit() {
                boolean[] zArr = $jacocoData;
                if (zArr != null) {
                    return zArr;
                }
                boolean[] a2 = Offline.a(-5191673903868777719L, "com/taobao/weex/ui/view/refresh/core/WXSwipeLayout$7", 5);
                $jacocoData = a2;
                return a2;
            }

            {
                boolean[] $jacocoInit = $jacocoInit();
                this.this$0 = r3;
                $jacocoInit[0] = true;
            }

            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                boolean[] $jacocoInit = $jacocoInit();
                FrameLayout.LayoutParams layoutParams = (FrameLayout.LayoutParams) WXSwipeLayout.access$500(this.this$0).getLayoutParams();
                $jacocoInit[1] = true;
                layoutParams.height = (int) ((Float) valueAnimator.getAnimatedValue()).floatValue();
                $jacocoInit[2] = true;
                WXSwipeLayout.access$500(this.this$0).setLayoutParams(layoutParams);
                $jacocoInit[3] = true;
                WXSwipeLayout.access$200(this.this$0, (float) (-layoutParams.height));
                $jacocoInit[4] = true;
            }
        });
        $jacocoInit[231] = true;
        ofFloat.addListener(new WXRefreshAnimatorListener(this) {
            private static transient /* synthetic */ boolean[] $jacocoData;
            final /* synthetic */ WXSwipeLayout this$0;

            private static /* synthetic */ boolean[] $jacocoInit() {
                boolean[] zArr = $jacocoData;
                if (zArr != null) {
                    return zArr;
                }
                boolean[] a2 = Offline.a(-4902514943673431290L, "com/taobao/weex/ui/view/refresh/core/WXSwipeLayout$8", 2);
                $jacocoData = a2;
                return a2;
            }

            {
                boolean[] $jacocoInit = $jacocoInit();
                this.this$0 = r3;
                $jacocoInit[0] = true;
            }

            public void onAnimationEnd(Animator animator) {
                boolean[] $jacocoInit = $jacocoInit();
                WXSwipeLayout.access$700(this.this$0);
                $jacocoInit[1] = true;
            }
        });
        $jacocoInit[232] = true;
        ofFloat.setDuration(300);
        $jacocoInit[233] = true;
        ofFloat.start();
        $jacocoInit[234] = true;
    }

    private void resetLoadmoreState() {
        boolean[] $jacocoInit = $jacocoInit();
        this.mRefreshing = false;
        this.isConfirm = false;
        this.mCurrentAction = -1;
        $jacocoInit[235] = true;
    }

    public boolean canChildScrollUp() {
        boolean[] $jacocoInit = $jacocoInit();
        boolean z = false;
        if (this.mTargetView == null) {
            $jacocoInit[236] = true;
            return false;
        } else if (Build.VERSION.SDK_INT >= 14) {
            boolean canScrollVertically = ViewCompat.canScrollVertically(this.mTargetView, -1);
            $jacocoInit[252] = true;
            return canScrollVertically;
        } else if (this.mTargetView instanceof AbsListView) {
            AbsListView absListView = (AbsListView) this.mTargetView;
            $jacocoInit[237] = true;
            if (absListView.getChildCount() <= 0) {
                $jacocoInit[238] = true;
            } else {
                $jacocoInit[239] = true;
                if (absListView.getFirstVisiblePosition() > 0) {
                    $jacocoInit[240] = true;
                } else {
                    View childAt = absListView.getChildAt(0);
                    $jacocoInit[241] = true;
                    if (childAt.getTop() >= absListView.getPaddingTop()) {
                        $jacocoInit[242] = true;
                    } else {
                        $jacocoInit[243] = true;
                    }
                }
                $jacocoInit[244] = true;
                z = true;
                $jacocoInit[246] = true;
                return z;
            }
            $jacocoInit[245] = true;
            $jacocoInit[246] = true;
            return z;
        } else {
            if (ViewCompat.canScrollVertically(this.mTargetView, -1)) {
                $jacocoInit[247] = true;
            } else if (this.mTargetView.getScrollY() > 0) {
                $jacocoInit[248] = true;
            } else {
                $jacocoInit[250] = true;
                $jacocoInit[251] = true;
                return z;
            }
            $jacocoInit[249] = true;
            z = true;
            $jacocoInit[251] = true;
            return z;
        }
    }

    public boolean canChildScrollDown() {
        boolean[] $jacocoInit = $jacocoInit();
        boolean z = false;
        if (this.mTargetView == null) {
            $jacocoInit[253] = true;
            return false;
        } else if (Build.VERSION.SDK_INT >= 14) {
            boolean canScrollVertically = ViewCompat.canScrollVertically(this.mTargetView, 1);
            $jacocoInit[270] = true;
            return canScrollVertically;
        } else if (this.mTargetView instanceof AbsListView) {
            AbsListView absListView = (AbsListView) this.mTargetView;
            $jacocoInit[254] = true;
            if (absListView.getChildCount() > 0) {
                $jacocoInit[255] = true;
                View childAt = absListView.getChildAt(absListView.getChildCount() - 1);
                $jacocoInit[256] = true;
                int bottom = childAt.getBottom();
                $jacocoInit[257] = true;
                if (absListView.getLastVisiblePosition() != ((ListAdapter) absListView.getAdapter()).getCount() - 1) {
                    $jacocoInit[258] = true;
                } else {
                    $jacocoInit[259] = true;
                    if (bottom > absListView.getMeasuredHeight()) {
                        $jacocoInit[260] = true;
                    } else {
                        $jacocoInit[261] = true;
                        z = true;
                        $jacocoInit[263] = true;
                        return z;
                    }
                }
                $jacocoInit[262] = true;
                $jacocoInit[263] = true;
                return z;
            }
            $jacocoInit[264] = true;
            return false;
        } else {
            if (ViewCompat.canScrollVertically(this.mTargetView, 1)) {
                $jacocoInit[265] = true;
            } else if (this.mTargetView.getScrollY() > 0) {
                $jacocoInit[266] = true;
            } else {
                $jacocoInit[268] = true;
                $jacocoInit[269] = true;
                return z;
            }
            $jacocoInit[267] = true;
            z = true;
            $jacocoInit[269] = true;
            return z;
        }
    }

    public float dipToPx(Context context, float f) {
        boolean[] $jacocoInit = $jacocoInit();
        DisplayMetrics displayMetrics = context.getResources().getDisplayMetrics();
        $jacocoInit[271] = true;
        float applyDimension = TypedValue.applyDimension(1, f, displayMetrics);
        $jacocoInit[272] = true;
        return applyDimension;
    }

    public void setOnLoadingListener(WXOnLoadingListener wXOnLoadingListener) {
        boolean[] $jacocoInit = $jacocoInit();
        this.onLoadingListener = wXOnLoadingListener;
        $jacocoInit[273] = true;
    }

    public void setOnRefreshListener(WXOnRefreshListener wXOnRefreshListener) {
        boolean[] $jacocoInit = $jacocoInit();
        this.onRefreshListener = wXOnRefreshListener;
        $jacocoInit[274] = true;
    }

    public void addOnRefreshOffsetChangedListener(@Nullable OnRefreshOffsetChangedListener onRefreshOffsetChangedListener) {
        boolean[] $jacocoInit = $jacocoInit();
        if (onRefreshOffsetChangedListener == null) {
            $jacocoInit[275] = true;
        } else if (this.mRefreshOffsetChangedListeners.contains(onRefreshOffsetChangedListener)) {
            $jacocoInit[276] = true;
        } else {
            $jacocoInit[277] = true;
            this.mRefreshOffsetChangedListeners.add(onRefreshOffsetChangedListener);
            $jacocoInit[278] = true;
        }
        $jacocoInit[279] = true;
    }

    public boolean removeOnRefreshOffsetChangedListener(@Nullable OnRefreshOffsetChangedListener onRefreshOffsetChangedListener) {
        boolean[] $jacocoInit = $jacocoInit();
        if (onRefreshOffsetChangedListener != null) {
            $jacocoInit[280] = true;
            boolean remove = this.mRefreshOffsetChangedListeners.remove(onRefreshOffsetChangedListener);
            $jacocoInit[281] = true;
            return remove;
        }
        $jacocoInit[282] = true;
        return false;
    }

    private void notifyOnRefreshOffsetChangedListener(int i) {
        boolean[] $jacocoInit = $jacocoInit();
        int size = this.mRefreshOffsetChangedListeners.size();
        $jacocoInit[283] = true;
        int i2 = 0;
        while (true) {
            if (i2 >= size) {
                $jacocoInit[284] = true;
                break;
            }
            $jacocoInit[285] = true;
            if (i2 >= this.mRefreshOffsetChangedListeners.size()) {
                $jacocoInit[286] = true;
                break;
            }
            OnRefreshOffsetChangedListener onRefreshOffsetChangedListener = this.mRefreshOffsetChangedListeners.get(i2);
            if (onRefreshOffsetChangedListener == null) {
                $jacocoInit[287] = true;
            } else {
                $jacocoInit[288] = true;
                onRefreshOffsetChangedListener.onOffsetChanged(i);
                $jacocoInit[289] = true;
            }
            i2++;
            $jacocoInit[290] = true;
        }
        $jacocoInit[291] = true;
    }

    public void finishPullRefresh() {
        int i;
        boolean[] $jacocoInit = $jacocoInit();
        if (this.mCurrentAction != 0) {
            $jacocoInit[292] = true;
        } else {
            $jacocoInit[293] = true;
            if (this.headerView == null) {
                i = 0;
                $jacocoInit[294] = true;
            } else {
                i = this.headerView.getMeasuredHeight();
                $jacocoInit[295] = true;
            }
            resetHeaderView(i);
            $jacocoInit[296] = true;
        }
        $jacocoInit[297] = true;
    }

    public void finishPullLoad() {
        int i;
        boolean[] $jacocoInit = $jacocoInit();
        if (this.mCurrentAction != 1) {
            $jacocoInit[298] = true;
        } else {
            $jacocoInit[299] = true;
            if (this.footerView == null) {
                i = 0;
                $jacocoInit[300] = true;
            } else {
                i = this.footerView.getMeasuredHeight();
                $jacocoInit[301] = true;
            }
            resetFootView(i);
            $jacocoInit[302] = true;
        }
        $jacocoInit[303] = true;
    }

    public WXRefreshView getHeaderView() {
        boolean[] $jacocoInit = $jacocoInit();
        WXRefreshView wXRefreshView = this.headerView;
        $jacocoInit[304] = true;
        return wXRefreshView;
    }

    public WXRefreshView getFooterView() {
        boolean[] $jacocoInit = $jacocoInit();
        WXRefreshView wXRefreshView = this.footerView;
        $jacocoInit[305] = true;
        return wXRefreshView;
    }

    public boolean isPullLoadEnable() {
        boolean[] $jacocoInit = $jacocoInit();
        boolean z = this.mPullLoadEnable;
        $jacocoInit[306] = true;
        return z;
    }

    public void setPullLoadEnable(boolean z) {
        boolean[] $jacocoInit = $jacocoInit();
        this.mPullLoadEnable = z;
        $jacocoInit[307] = true;
    }

    public boolean isPullRefreshEnable() {
        boolean[] $jacocoInit = $jacocoInit();
        boolean z = this.mPullRefreshEnable;
        $jacocoInit[308] = true;
        return z;
    }

    public void setPullRefreshEnable(boolean z) {
        boolean[] $jacocoInit = $jacocoInit();
        this.mPullRefreshEnable = z;
        $jacocoInit[309] = true;
    }

    public boolean isRefreshing() {
        boolean[] $jacocoInit = $jacocoInit();
        boolean z = this.mRefreshing;
        $jacocoInit[310] = true;
        return z;
    }

    public void setRefreshHeight(int i) {
        boolean[] $jacocoInit = $jacocoInit();
        this.refreshViewHeight = (float) i;
        this.refreshViewFlowHeight = this.refreshViewHeight * 1.0f;
        $jacocoInit[311] = true;
    }

    public void setLoadingHeight(int i) {
        boolean[] $jacocoInit = $jacocoInit();
        this.loadingViewHeight = (float) i;
        this.loadingViewFlowHeight = this.loadingViewHeight * 1.0f;
        $jacocoInit[312] = true;
    }

    public void setRefreshBgColor(int i) {
        boolean[] $jacocoInit = $jacocoInit();
        this.headerView.setBackgroundColor(i);
        $jacocoInit[313] = true;
    }

    public void setLoadingBgColor(int i) {
        boolean[] $jacocoInit = $jacocoInit();
        this.footerView.setBackgroundColor(i);
        $jacocoInit[314] = true;
    }
}
