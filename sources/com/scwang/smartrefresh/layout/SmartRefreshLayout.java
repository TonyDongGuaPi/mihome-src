package com.scwang.smartrefresh.layout;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ValueAnimator;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.os.Build;
import android.os.Handler;
import android.support.annotation.ColorInt;
import android.support.annotation.ColorRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.view.NestedScrollingChildHelper;
import android.support.v4.view.NestedScrollingParent;
import android.support.v4.view.NestedScrollingParentHelper;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.VelocityTracker;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.view.animation.AnimationUtils;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.Interpolator;
import android.widget.Scroller;
import android.widget.TextView;
import com.scwang.smartrefresh.layout.api.DefaultRefreshFooterCreator;
import com.scwang.smartrefresh.layout.api.DefaultRefreshHeaderCreator;
import com.scwang.smartrefresh.layout.api.DefaultRefreshInitializer;
import com.scwang.smartrefresh.layout.api.RefreshContent;
import com.scwang.smartrefresh.layout.api.RefreshFooter;
import com.scwang.smartrefresh.layout.api.RefreshHeader;
import com.scwang.smartrefresh.layout.api.RefreshInternal;
import com.scwang.smartrefresh.layout.api.RefreshKernel;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.api.ScrollBoundaryDecider;
import com.scwang.smartrefresh.layout.constant.DimensionStatus;
import com.scwang.smartrefresh.layout.constant.RefreshState;
import com.scwang.smartrefresh.layout.constant.SpinnerStyle;
import com.scwang.smartrefresh.layout.footer.BallPulseFooter;
import com.scwang.smartrefresh.layout.header.BezierRadarHeader;
import com.scwang.smartrefresh.layout.impl.RefreshContentWrapper;
import com.scwang.smartrefresh.layout.listener.OnLoadMoreListener;
import com.scwang.smartrefresh.layout.listener.OnMultiPurposeListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshLoadMoreListener;
import com.scwang.smartrefresh.layout.util.DelayedRunnable;
import com.scwang.smartrefresh.layout.util.DensityUtil;
import com.scwang.smartrefresh.layout.util.ScrollBoundaryUtil;
import com.scwang.smartrefresh.layout.util.SmartUtil;
import com.scwang.smartrefresh.layout.util.ViscousFluidInterpolator;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;

@SuppressLint({"RestrictedApi"})
public class SmartRefreshLayout extends ViewGroup implements NestedScrollingParent, RefreshLayout {
    protected static DefaultRefreshFooterCreator sFooterCreator = new DefaultRefreshFooterCreator() {
        @NonNull
        public RefreshFooter a(@NonNull Context context, @NonNull RefreshLayout refreshLayout) {
            return new BallPulseFooter(context);
        }
    };
    protected static DefaultRefreshHeaderCreator sHeaderCreator = new DefaultRefreshHeaderCreator() {
        @NonNull
        public RefreshHeader a(@NonNull Context context, @NonNull RefreshLayout refreshLayout) {
            return new BezierRadarHeader(context);
        }
    };
    protected static DefaultRefreshInitializer sRefreshInitializer;
    protected Runnable animationRunnable;
    protected int mCurrentVelocity;
    protected boolean mDisableContentWhenLoading;
    protected boolean mDisableContentWhenRefresh;
    protected char mDragDirection;
    protected float mDragRate;
    protected boolean mEnableAutoLoadMore;
    protected boolean mEnableClipFooterWhenFixedBehind;
    protected boolean mEnableClipHeaderWhenFixedBehind;
    protected boolean mEnableFooterFollowWhenLoadFinished;
    protected boolean mEnableFooterTranslationContent;
    protected boolean mEnableHeaderTranslationContent;
    protected boolean mEnableLoadMore;
    protected boolean mEnableLoadMoreWhenContentNotFull;
    protected boolean mEnableOverScrollBounce;
    protected boolean mEnableOverScrollDrag;
    protected boolean mEnablePreviewInEditMode;
    protected boolean mEnablePureScrollMode;
    protected boolean mEnableRefresh;
    protected boolean mEnableScrollContentWhenLoaded;
    protected boolean mEnableScrollContentWhenRefreshed;
    protected MotionEvent mFalsifyEvent;
    protected int mFixedFooterViewId;
    protected int mFixedHeaderViewId;
    protected int mFloorDuration;
    protected int mFooterBackgroundColor;
    protected int mFooterHeight;
    protected DimensionStatus mFooterHeightStatus;
    protected int mFooterInsetStart;
    protected boolean mFooterLocked;
    protected float mFooterMaxDragRate;
    protected boolean mFooterNeedTouchEventWhenLoading;
    protected boolean mFooterNoMoreData;
    protected int mFooterTranslationViewId;
    protected float mFooterTriggerRate;
    protected Handler mHandler;
    protected int mHeaderBackgroundColor;
    protected int mHeaderHeight;
    protected DimensionStatus mHeaderHeightStatus;
    protected int mHeaderInsetStart;
    protected float mHeaderMaxDragRate;
    protected boolean mHeaderNeedTouchEventWhenRefreshing;
    protected int mHeaderTranslationViewId;
    protected float mHeaderTriggerRate;
    protected boolean mIsBeingDragged;
    protected RefreshKernel mKernel;
    protected long mLastOpenTime;
    protected int mLastSpinner;
    protected float mLastTouchX;
    protected float mLastTouchY;
    protected List<DelayedRunnable> mListDelayedRunnable;
    protected OnLoadMoreListener mLoadMoreListener;
    protected boolean mManualFooterTranslationContent;
    protected boolean mManualHeaderTranslationContent;
    protected boolean mManualLoadMore;
    protected boolean mManualNestedScrolling;
    protected int mMaximumVelocity;
    protected int mMinimumVelocity;
    protected NestedScrollingChildHelper mNestedChild;
    protected boolean mNestedInProgress;
    protected NestedScrollingParentHelper mNestedParent;
    protected OnMultiPurposeListener mOnMultiPurposeListener;
    protected Paint mPaint;
    protected int[] mParentOffsetInWindow;
    protected int[] mPrimaryColors;
    protected int mReboundDuration;
    protected Interpolator mReboundInterpolator;
    protected RefreshContent mRefreshContent;
    protected RefreshInternal mRefreshFooter;
    protected RefreshInternal mRefreshHeader;
    protected OnRefreshListener mRefreshListener;
    protected int mScreenHeightPixels;
    protected ScrollBoundaryDecider mScrollBoundaryDecider;
    protected Scroller mScroller;
    protected int mSpinner;
    protected RefreshState mState;
    protected boolean mSuperDispatchTouchEvent;
    protected int mTotalUnconsumed;
    protected int mTouchSlop;
    protected int mTouchSpinner;
    protected float mTouchX;
    protected float mTouchY;
    protected VelocityTracker mVelocityTracker;
    protected boolean mVerticalPermit;
    protected RefreshState mViceState;
    protected ValueAnimator reboundAnimator;

    public SmartRefreshLayout getLayout() {
        return this;
    }

    public SmartRefreshLayout(Context context) {
        this(context, (AttributeSet) null);
    }

    public SmartRefreshLayout(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public SmartRefreshLayout(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.mFloorDuration = 250;
        this.mReboundDuration = 250;
        this.mDragRate = 0.5f;
        this.mDragDirection = 'n';
        this.mFixedHeaderViewId = -1;
        this.mFixedFooterViewId = -1;
        this.mHeaderTranslationViewId = -1;
        this.mFooterTranslationViewId = -1;
        this.mEnableRefresh = true;
        this.mEnableLoadMore = false;
        this.mEnableClipHeaderWhenFixedBehind = true;
        this.mEnableClipFooterWhenFixedBehind = true;
        this.mEnableHeaderTranslationContent = true;
        this.mEnableFooterTranslationContent = true;
        this.mEnableFooterFollowWhenLoadFinished = false;
        this.mEnablePreviewInEditMode = true;
        this.mEnableOverScrollBounce = true;
        this.mEnableOverScrollDrag = false;
        this.mEnableAutoLoadMore = true;
        this.mEnablePureScrollMode = false;
        this.mEnableScrollContentWhenLoaded = true;
        this.mEnableScrollContentWhenRefreshed = true;
        this.mEnableLoadMoreWhenContentNotFull = true;
        this.mDisableContentWhenRefresh = false;
        this.mDisableContentWhenLoading = false;
        this.mFooterNoMoreData = false;
        this.mManualLoadMore = false;
        this.mManualNestedScrolling = false;
        this.mManualHeaderTranslationContent = false;
        this.mManualFooterTranslationContent = false;
        this.mParentOffsetInWindow = new int[2];
        this.mNestedChild = new NestedScrollingChildHelper(this);
        this.mNestedParent = new NestedScrollingParentHelper(this);
        this.mHeaderHeightStatus = DimensionStatus.DefaultUnNotify;
        this.mFooterHeightStatus = DimensionStatus.DefaultUnNotify;
        this.mHeaderMaxDragRate = 2.5f;
        this.mFooterMaxDragRate = 2.5f;
        this.mHeaderTriggerRate = 1.0f;
        this.mFooterTriggerRate = 1.0f;
        this.mKernel = new RefreshKernelImpl();
        this.mState = RefreshState.None;
        this.mViceState = RefreshState.None;
        this.mLastOpenTime = 0;
        this.mHeaderBackgroundColor = 0;
        this.mFooterBackgroundColor = 0;
        this.mFooterLocked = false;
        this.mVerticalPermit = false;
        this.mFalsifyEvent = null;
        super.setClipToPadding(false);
        DensityUtil densityUtil = new DensityUtil();
        ViewConfiguration viewConfiguration = ViewConfiguration.get(context);
        this.mScroller = new Scroller(context);
        this.mVelocityTracker = VelocityTracker.obtain();
        this.mScreenHeightPixels = context.getResources().getDisplayMetrics().heightPixels;
        this.mReboundInterpolator = new ViscousFluidInterpolator();
        this.mTouchSlop = viewConfiguration.getScaledTouchSlop();
        this.mMinimumVelocity = viewConfiguration.getScaledMinimumFlingVelocity();
        this.mMaximumVelocity = viewConfiguration.getScaledMaximumFlingVelocity();
        this.mFooterHeight = densityUtil.b(60.0f);
        this.mHeaderHeight = densityUtil.b(100.0f);
        if (sRefreshInitializer != null) {
            sRefreshInitializer.a(context, this);
        }
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R.styleable.SmartRefreshLayout);
        this.mNestedChild.setNestedScrollingEnabled(obtainStyledAttributes.getBoolean(R.styleable.SmartRefreshLayout_srlEnableNestedScrolling, this.mNestedChild.isNestedScrollingEnabled()));
        this.mDragRate = obtainStyledAttributes.getFloat(R.styleable.SmartRefreshLayout_srlDragRate, this.mDragRate);
        this.mHeaderMaxDragRate = obtainStyledAttributes.getFloat(R.styleable.SmartRefreshLayout_srlHeaderMaxDragRate, this.mHeaderMaxDragRate);
        this.mFooterMaxDragRate = obtainStyledAttributes.getFloat(R.styleable.SmartRefreshLayout_srlFooterMaxDragRate, this.mFooterMaxDragRate);
        this.mHeaderTriggerRate = obtainStyledAttributes.getFloat(R.styleable.SmartRefreshLayout_srlHeaderTriggerRate, this.mHeaderTriggerRate);
        this.mFooterTriggerRate = obtainStyledAttributes.getFloat(R.styleable.SmartRefreshLayout_srlFooterTriggerRate, this.mFooterTriggerRate);
        this.mEnableRefresh = obtainStyledAttributes.getBoolean(R.styleable.SmartRefreshLayout_srlEnableRefresh, this.mEnableRefresh);
        this.mReboundDuration = obtainStyledAttributes.getInt(R.styleable.SmartRefreshLayout_srlReboundDuration, this.mReboundDuration);
        this.mEnableLoadMore = obtainStyledAttributes.getBoolean(R.styleable.SmartRefreshLayout_srlEnableLoadMore, this.mEnableLoadMore);
        this.mHeaderHeight = obtainStyledAttributes.getDimensionPixelOffset(R.styleable.SmartRefreshLayout_srlHeaderHeight, this.mHeaderHeight);
        this.mFooterHeight = obtainStyledAttributes.getDimensionPixelOffset(R.styleable.SmartRefreshLayout_srlFooterHeight, this.mFooterHeight);
        this.mHeaderInsetStart = obtainStyledAttributes.getDimensionPixelOffset(R.styleable.SmartRefreshLayout_srlHeaderInsetStart, this.mHeaderInsetStart);
        this.mFooterInsetStart = obtainStyledAttributes.getDimensionPixelOffset(R.styleable.SmartRefreshLayout_srlFooterInsetStart, this.mFooterInsetStart);
        this.mDisableContentWhenRefresh = obtainStyledAttributes.getBoolean(R.styleable.SmartRefreshLayout_srlDisableContentWhenRefresh, this.mDisableContentWhenRefresh);
        this.mDisableContentWhenLoading = obtainStyledAttributes.getBoolean(R.styleable.SmartRefreshLayout_srlDisableContentWhenLoading, this.mDisableContentWhenLoading);
        this.mEnableHeaderTranslationContent = obtainStyledAttributes.getBoolean(R.styleable.SmartRefreshLayout_srlEnableHeaderTranslationContent, this.mEnableHeaderTranslationContent);
        this.mEnableFooterTranslationContent = obtainStyledAttributes.getBoolean(R.styleable.SmartRefreshLayout_srlEnableFooterTranslationContent, this.mEnableFooterTranslationContent);
        this.mEnablePreviewInEditMode = obtainStyledAttributes.getBoolean(R.styleable.SmartRefreshLayout_srlEnablePreviewInEditMode, this.mEnablePreviewInEditMode);
        this.mEnableAutoLoadMore = obtainStyledAttributes.getBoolean(R.styleable.SmartRefreshLayout_srlEnableAutoLoadMore, this.mEnableAutoLoadMore);
        this.mEnableOverScrollBounce = obtainStyledAttributes.getBoolean(R.styleable.SmartRefreshLayout_srlEnableOverScrollBounce, this.mEnableOverScrollBounce);
        this.mEnablePureScrollMode = obtainStyledAttributes.getBoolean(R.styleable.SmartRefreshLayout_srlEnablePureScrollMode, this.mEnablePureScrollMode);
        this.mEnableScrollContentWhenLoaded = obtainStyledAttributes.getBoolean(R.styleable.SmartRefreshLayout_srlEnableScrollContentWhenLoaded, this.mEnableScrollContentWhenLoaded);
        this.mEnableScrollContentWhenRefreshed = obtainStyledAttributes.getBoolean(R.styleable.SmartRefreshLayout_srlEnableScrollContentWhenRefreshed, this.mEnableScrollContentWhenRefreshed);
        this.mEnableLoadMoreWhenContentNotFull = obtainStyledAttributes.getBoolean(R.styleable.SmartRefreshLayout_srlEnableLoadMoreWhenContentNotFull, this.mEnableLoadMoreWhenContentNotFull);
        this.mEnableFooterFollowWhenLoadFinished = obtainStyledAttributes.getBoolean(R.styleable.SmartRefreshLayout_srlEnableFooterFollowWhenLoadFinished, this.mEnableFooterFollowWhenLoadFinished);
        this.mEnableClipHeaderWhenFixedBehind = obtainStyledAttributes.getBoolean(R.styleable.SmartRefreshLayout_srlEnableClipHeaderWhenFixedBehind, this.mEnableClipHeaderWhenFixedBehind);
        this.mEnableClipFooterWhenFixedBehind = obtainStyledAttributes.getBoolean(R.styleable.SmartRefreshLayout_srlEnableClipFooterWhenFixedBehind, this.mEnableClipFooterWhenFixedBehind);
        this.mEnableOverScrollDrag = obtainStyledAttributes.getBoolean(R.styleable.SmartRefreshLayout_srlEnableOverScrollDrag, this.mEnableOverScrollDrag);
        this.mFixedHeaderViewId = obtainStyledAttributes.getResourceId(R.styleable.SmartRefreshLayout_srlFixedHeaderViewId, this.mFixedHeaderViewId);
        this.mFixedFooterViewId = obtainStyledAttributes.getResourceId(R.styleable.SmartRefreshLayout_srlFixedFooterViewId, this.mFixedFooterViewId);
        this.mHeaderTranslationViewId = obtainStyledAttributes.getResourceId(R.styleable.SmartRefreshLayout_srlHeaderTranslationViewId, this.mHeaderTranslationViewId);
        this.mFooterTranslationViewId = obtainStyledAttributes.getResourceId(R.styleable.SmartRefreshLayout_srlFooterTranslationViewId, this.mFooterTranslationViewId);
        if (this.mEnablePureScrollMode && !obtainStyledAttributes.hasValue(R.styleable.SmartRefreshLayout_srlEnableOverScrollDrag)) {
            this.mEnableOverScrollDrag = true;
        }
        this.mManualLoadMore = obtainStyledAttributes.hasValue(R.styleable.SmartRefreshLayout_srlEnableLoadMore);
        this.mManualHeaderTranslationContent = obtainStyledAttributes.hasValue(R.styleable.SmartRefreshLayout_srlEnableHeaderTranslationContent);
        this.mManualNestedScrolling = this.mManualNestedScrolling || obtainStyledAttributes.hasValue(R.styleable.SmartRefreshLayout_srlEnableNestedScrolling);
        this.mHeaderHeightStatus = obtainStyledAttributes.hasValue(R.styleable.SmartRefreshLayout_srlHeaderHeight) ? DimensionStatus.XmlLayoutUnNotify : this.mHeaderHeightStatus;
        this.mFooterHeightStatus = obtainStyledAttributes.hasValue(R.styleable.SmartRefreshLayout_srlFooterHeight) ? DimensionStatus.XmlLayoutUnNotify : this.mFooterHeightStatus;
        int color = obtainStyledAttributes.getColor(R.styleable.SmartRefreshLayout_srlAccentColor, 0);
        int color2 = obtainStyledAttributes.getColor(R.styleable.SmartRefreshLayout_srlPrimaryColor, 0);
        if (color2 != 0) {
            if (color != 0) {
                this.mPrimaryColors = new int[]{color2, color};
            } else {
                this.mPrimaryColors = new int[]{color2};
            }
        } else if (color != 0) {
            this.mPrimaryColors = new int[]{0, color};
        }
        obtainStyledAttributes.recycle();
    }

    /* access modifiers changed from: protected */
    /* JADX WARNING: Removed duplicated region for block: B:29:0x0052  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void onFinishInflate() {
        /*
            r11 = this;
            super.onFinishInflate()
            int r0 = super.getChildCount()
            r1 = 3
            if (r0 > r1) goto L_0x009e
            r2 = -1
            r3 = 0
            r4 = 0
            r5 = -1
            r6 = 0
        L_0x000f:
            r7 = 2
            r8 = 1
            if (r4 >= r0) goto L_0x0033
            android.view.View r9 = super.getChildAt(r4)
            boolean r10 = com.scwang.smartrefresh.layout.util.SmartUtil.c(r9)
            if (r10 == 0) goto L_0x0024
            if (r6 < r7) goto L_0x0021
            if (r4 != r8) goto L_0x0024
        L_0x0021:
            r5 = r4
            r6 = 2
            goto L_0x0030
        L_0x0024:
            boolean r7 = r9 instanceof com.scwang.smartrefresh.layout.api.RefreshInternal
            if (r7 != 0) goto L_0x0030
            if (r6 >= r8) goto L_0x0030
            if (r4 <= 0) goto L_0x002d
            goto L_0x002e
        L_0x002d:
            r8 = 0
        L_0x002e:
            r5 = r4
            r6 = r8
        L_0x0030:
            int r4 = r4 + 1
            goto L_0x000f
        L_0x0033:
            if (r5 < 0) goto L_0x004d
            com.scwang.smartrefresh.layout.impl.RefreshContentWrapper r4 = new com.scwang.smartrefresh.layout.impl.RefreshContentWrapper
            android.view.View r6 = super.getChildAt(r5)
            r4.<init>(r6)
            r11.mRefreshContent = r4
            if (r5 != r8) goto L_0x0048
            if (r0 != r1) goto L_0x0046
            r1 = 0
            goto L_0x004f
        L_0x0046:
            r1 = 0
            goto L_0x004e
        L_0x0048:
            if (r0 != r7) goto L_0x004d
            r1 = -1
            r7 = 1
            goto L_0x004f
        L_0x004d:
            r1 = -1
        L_0x004e:
            r7 = -1
        L_0x004f:
            r4 = 0
        L_0x0050:
            if (r4 >= r0) goto L_0x009d
            android.view.View r5 = super.getChildAt(r4)
            if (r4 == r1) goto L_0x008b
            if (r4 == r7) goto L_0x0065
            if (r1 != r2) goto L_0x0065
            com.scwang.smartrefresh.layout.api.RefreshInternal r6 = r11.mRefreshHeader
            if (r6 != 0) goto L_0x0065
            boolean r6 = r5 instanceof com.scwang.smartrefresh.layout.api.RefreshHeader
            if (r6 == 0) goto L_0x0065
            goto L_0x008b
        L_0x0065:
            if (r4 == r7) goto L_0x006d
            if (r7 != r2) goto L_0x009a
            boolean r6 = r5 instanceof com.scwang.smartrefresh.layout.api.RefreshFooter
            if (r6 == 0) goto L_0x009a
        L_0x006d:
            boolean r6 = r11.mEnableLoadMore
            if (r6 != 0) goto L_0x0078
            boolean r6 = r11.mManualLoadMore
            if (r6 != 0) goto L_0x0076
            goto L_0x0078
        L_0x0076:
            r6 = 0
            goto L_0x0079
        L_0x0078:
            r6 = 1
        L_0x0079:
            r11.mEnableLoadMore = r6
            boolean r6 = r5 instanceof com.scwang.smartrefresh.layout.api.RefreshFooter
            if (r6 == 0) goto L_0x0082
            com.scwang.smartrefresh.layout.api.RefreshFooter r5 = (com.scwang.smartrefresh.layout.api.RefreshFooter) r5
            goto L_0x0088
        L_0x0082:
            com.scwang.smartrefresh.layout.impl.RefreshFooterWrapper r6 = new com.scwang.smartrefresh.layout.impl.RefreshFooterWrapper
            r6.<init>(r5)
            r5 = r6
        L_0x0088:
            r11.mRefreshFooter = r5
            goto L_0x009a
        L_0x008b:
            boolean r6 = r5 instanceof com.scwang.smartrefresh.layout.api.RefreshHeader
            if (r6 == 0) goto L_0x0092
            com.scwang.smartrefresh.layout.api.RefreshHeader r5 = (com.scwang.smartrefresh.layout.api.RefreshHeader) r5
            goto L_0x0098
        L_0x0092:
            com.scwang.smartrefresh.layout.impl.RefreshHeaderWrapper r6 = new com.scwang.smartrefresh.layout.impl.RefreshHeaderWrapper
            r6.<init>(r5)
            r5 = r6
        L_0x0098:
            r11.mRefreshHeader = r5
        L_0x009a:
            int r4 = r4 + 1
            goto L_0x0050
        L_0x009d:
            return
        L_0x009e:
            java.lang.RuntimeException r0 = new java.lang.RuntimeException
            java.lang.String r1 = "最多只支持3个子View，Most only support three sub view"
            r0.<init>(r1)
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.scwang.smartrefresh.layout.SmartRefreshLayout.onFinishInflate():void");
    }

    /* access modifiers changed from: protected */
    public void onAttachedToWindow() {
        super.onAttachedToWindow();
        if (!isInEditMode()) {
            if (this.mHandler == null) {
                this.mHandler = new Handler();
            }
            View view = null;
            if (this.mListDelayedRunnable != null) {
                for (DelayedRunnable next : this.mListDelayedRunnable) {
                    this.mHandler.postDelayed(next, next.f8800a);
                }
                this.mListDelayedRunnable.clear();
                this.mListDelayedRunnable = null;
            }
            if (this.mRefreshHeader == null) {
                setRefreshHeader(sHeaderCreator.a(getContext(), this));
            }
            if (this.mRefreshFooter == null) {
                setRefreshFooter(sFooterCreator.a(getContext(), this));
            } else {
                this.mEnableLoadMore = this.mEnableLoadMore || !this.mManualLoadMore;
            }
            if (this.mRefreshContent == null) {
                int childCount = getChildCount();
                for (int i = 0; i < childCount; i++) {
                    View childAt = getChildAt(i);
                    if ((this.mRefreshHeader == null || childAt != this.mRefreshHeader.getView()) && (this.mRefreshFooter == null || childAt != this.mRefreshFooter.getView())) {
                        this.mRefreshContent = new RefreshContentWrapper(childAt);
                    }
                }
            }
            if (this.mRefreshContent == null) {
                int a2 = DensityUtil.a(20.0f);
                TextView textView = new TextView(getContext());
                textView.setTextColor(-39424);
                textView.setGravity(17);
                textView.setTextSize(20.0f);
                textView.setText(R.string.srl_content_empty);
                super.addView(textView, -1, -1);
                this.mRefreshContent = new RefreshContentWrapper(textView);
                this.mRefreshContent.a().setPadding(a2, a2, a2, a2);
            }
            View findViewById = this.mFixedHeaderViewId > 0 ? findViewById(this.mFixedHeaderViewId) : null;
            if (this.mFixedFooterViewId > 0) {
                view = findViewById(this.mFixedFooterViewId);
            }
            this.mRefreshContent.a(this.mScrollBoundaryDecider);
            this.mRefreshContent.a(this.mEnableLoadMoreWhenContentNotFull);
            this.mRefreshContent.a(this.mKernel, findViewById, view);
            if (this.mSpinner != 0) {
                notifyStateChanged(RefreshState.None);
                RefreshContent refreshContent = this.mRefreshContent;
                this.mSpinner = 0;
                refreshContent.a(0, this.mHeaderTranslationViewId, this.mFooterTranslationViewId);
            }
            if (!this.mManualNestedScrolling && !isNestedScrollingEnabled()) {
                post(new Runnable() {
                    public void run() {
                        ViewParent parent = SmartRefreshLayout.this.getParent();
                        while (parent != null) {
                            if (parent instanceof NestedScrollingParent) {
                                SmartRefreshLayout smartRefreshLayout = SmartRefreshLayout.this;
                                if (((NestedScrollingParent) parent).onStartNestedScroll(smartRefreshLayout, smartRefreshLayout, 2)) {
                                    SmartRefreshLayout.this.setNestedScrollingEnabled(true);
                                    SmartRefreshLayout.this.mManualNestedScrolling = false;
                                    return;
                                }
                            }
                            if (parent instanceof View) {
                                parent = ((View) parent).getParent();
                            } else {
                                return;
                            }
                        }
                    }
                });
            }
        }
        if (this.mPrimaryColors != null) {
            if (this.mRefreshHeader != null) {
                this.mRefreshHeader.setPrimaryColors(this.mPrimaryColors);
            }
            if (this.mRefreshFooter != null) {
                this.mRefreshFooter.setPrimaryColors(this.mPrimaryColors);
            }
        }
        if (this.mRefreshContent != null) {
            super.bringChildToFront(this.mRefreshContent.a());
        }
        if (!(this.mRefreshHeader == null || this.mRefreshHeader.getSpinnerStyle() == SpinnerStyle.FixedBehind)) {
            super.bringChildToFront(this.mRefreshHeader.getView());
        }
        if (this.mRefreshFooter != null && this.mRefreshFooter.getSpinnerStyle() != SpinnerStyle.FixedBehind) {
            super.bringChildToFront(this.mRefreshFooter.getView());
        }
    }

    /* access modifiers changed from: protected */
    public void onMeasure(int i, int i2) {
        int i3;
        int i4;
        int i5 = i;
        int i6 = i2;
        boolean z = isInEditMode() && this.mEnablePreviewInEditMode;
        int childCount = super.getChildCount();
        int i7 = 0;
        for (int i8 = 0; i8 < childCount; i8++) {
            View childAt = super.getChildAt(i8);
            if (this.mRefreshHeader != null && this.mRefreshHeader.getView() == childAt) {
                View view = this.mRefreshHeader.getView();
                LayoutParams layoutParams = (LayoutParams) view.getLayoutParams();
                int childMeasureSpec = ViewGroup.getChildMeasureSpec(i5, layoutParams.leftMargin + layoutParams.rightMargin, layoutParams.width);
                if (this.mHeaderHeightStatus.gteReplaceWith(DimensionStatus.XmlLayoutUnNotify)) {
                    view.measure(childMeasureSpec, View.MeasureSpec.makeMeasureSpec(Math.max((this.mHeaderHeight - layoutParams.bottomMargin) - layoutParams.topMargin, 0), 1073741824));
                } else if (this.mRefreshHeader.getSpinnerStyle() == SpinnerStyle.MatchLayout) {
                    if (!this.mHeaderHeightStatus.notified) {
                        super.measureChild(view, childMeasureSpec, View.MeasureSpec.makeMeasureSpec(Math.max((View.MeasureSpec.getSize(i2) - layoutParams.bottomMargin) - layoutParams.topMargin, 0), Integer.MIN_VALUE));
                        i4 = view.getMeasuredHeight();
                    } else {
                        i4 = 0;
                    }
                    view.measure(childMeasureSpec, View.MeasureSpec.makeMeasureSpec(Math.max((View.MeasureSpec.getSize(i2) - layoutParams.bottomMargin) - layoutParams.topMargin, 0), 1073741824));
                    if (i4 > 0 && i4 != view.getMeasuredHeight()) {
                        this.mHeaderHeight = i4 + layoutParams.bottomMargin + layoutParams.topMargin;
                    }
                } else if (layoutParams.height > 0) {
                    if (this.mHeaderHeightStatus.canReplaceWith(DimensionStatus.XmlExactUnNotify)) {
                        this.mHeaderHeight = layoutParams.height + layoutParams.bottomMargin + layoutParams.topMargin;
                        this.mHeaderHeightStatus = DimensionStatus.XmlExactUnNotify;
                    }
                    view.measure(childMeasureSpec, View.MeasureSpec.makeMeasureSpec(layoutParams.height, 1073741824));
                } else if (layoutParams.height == -2) {
                    view.measure(childMeasureSpec, View.MeasureSpec.makeMeasureSpec(Math.max((View.MeasureSpec.getSize(i2) - layoutParams.bottomMargin) - layoutParams.topMargin, 0), Integer.MIN_VALUE));
                    int measuredHeight = view.getMeasuredHeight();
                    if (measuredHeight > 0 && this.mHeaderHeightStatus.canReplaceWith(DimensionStatus.XmlWrapUnNotify)) {
                        this.mHeaderHeightStatus = DimensionStatus.XmlWrapUnNotify;
                        this.mHeaderHeight = view.getMeasuredHeight() + layoutParams.bottomMargin + layoutParams.topMargin;
                    } else if (measuredHeight <= 0) {
                        view.measure(childMeasureSpec, View.MeasureSpec.makeMeasureSpec(Math.max((this.mHeaderHeight - layoutParams.bottomMargin) - layoutParams.topMargin, 0), 1073741824));
                    }
                } else if (layoutParams.height == -1) {
                    view.measure(childMeasureSpec, View.MeasureSpec.makeMeasureSpec(Math.max((this.mHeaderHeight - layoutParams.bottomMargin) - layoutParams.topMargin, 0), 1073741824));
                } else {
                    view.measure(childMeasureSpec, i6);
                }
                if (this.mRefreshHeader.getSpinnerStyle() == SpinnerStyle.Scale && !z) {
                    view.measure(childMeasureSpec, View.MeasureSpec.makeMeasureSpec(Math.max((Math.max(0, isEnableRefresh() ? this.mSpinner : 0) - layoutParams.bottomMargin) - layoutParams.topMargin, 0), 1073741824));
                }
                if (!this.mHeaderHeightStatus.notified) {
                    this.mHeaderHeightStatus = this.mHeaderHeightStatus.notified();
                    this.mRefreshHeader.onInitialized(this.mKernel, this.mHeaderHeight, (int) (this.mHeaderMaxDragRate * ((float) this.mHeaderHeight)));
                }
                if (z && isEnableRefresh()) {
                    i7 += view.getMeasuredHeight();
                }
            }
            if (this.mRefreshFooter != null && this.mRefreshFooter.getView() == childAt) {
                View view2 = this.mRefreshFooter.getView();
                LayoutParams layoutParams2 = (LayoutParams) view2.getLayoutParams();
                int childMeasureSpec2 = ViewGroup.getChildMeasureSpec(i5, layoutParams2.leftMargin + layoutParams2.rightMargin, layoutParams2.width);
                if (this.mFooterHeightStatus.gteReplaceWith(DimensionStatus.XmlLayoutUnNotify)) {
                    view2.measure(childMeasureSpec2, View.MeasureSpec.makeMeasureSpec(Math.max((this.mFooterHeight - layoutParams2.topMargin) - layoutParams2.bottomMargin, 0), 1073741824));
                } else if (this.mRefreshFooter.getSpinnerStyle() == SpinnerStyle.MatchLayout) {
                    if (!this.mFooterHeightStatus.notified) {
                        super.measureChild(view2, childMeasureSpec2, View.MeasureSpec.makeMeasureSpec((View.MeasureSpec.getSize(i2) - layoutParams2.topMargin) - layoutParams2.bottomMargin, Integer.MIN_VALUE));
                        i3 = view2.getMeasuredHeight();
                    } else {
                        i3 = 0;
                    }
                    view2.measure(childMeasureSpec2, View.MeasureSpec.makeMeasureSpec((View.MeasureSpec.getSize(i2) - layoutParams2.topMargin) - layoutParams2.bottomMargin, 1073741824));
                    if (i3 > 0 && i3 != view2.getMeasuredHeight()) {
                        this.mHeaderHeight = i3 + layoutParams2.topMargin + layoutParams2.bottomMargin;
                    }
                } else if (layoutParams2.height > 0) {
                    if (this.mFooterHeightStatus.canReplaceWith(DimensionStatus.XmlExactUnNotify)) {
                        this.mFooterHeight = layoutParams2.height + layoutParams2.topMargin + layoutParams2.bottomMargin;
                        this.mFooterHeightStatus = DimensionStatus.XmlExactUnNotify;
                    }
                    view2.measure(childMeasureSpec2, View.MeasureSpec.makeMeasureSpec(layoutParams2.height, 1073741824));
                } else if (layoutParams2.height == -2) {
                    view2.measure(childMeasureSpec2, View.MeasureSpec.makeMeasureSpec(Math.max((View.MeasureSpec.getSize(i2) - layoutParams2.topMargin) - layoutParams2.bottomMargin, 0), Integer.MIN_VALUE));
                    int measuredHeight2 = view2.getMeasuredHeight();
                    if (measuredHeight2 > 0 && this.mFooterHeightStatus.canReplaceWith(DimensionStatus.XmlWrapUnNotify)) {
                        this.mFooterHeightStatus = DimensionStatus.XmlWrapUnNotify;
                        this.mFooterHeight = view2.getMeasuredHeight() + layoutParams2.topMargin + layoutParams2.bottomMargin;
                    } else if (measuredHeight2 <= 0) {
                        view2.measure(childMeasureSpec2, View.MeasureSpec.makeMeasureSpec(Math.max((this.mFooterHeight - layoutParams2.topMargin) - layoutParams2.bottomMargin, 0), 1073741824));
                    }
                } else if (layoutParams2.height == -1) {
                    view2.measure(childMeasureSpec2, View.MeasureSpec.makeMeasureSpec(Math.max((this.mFooterHeight - layoutParams2.topMargin) - layoutParams2.bottomMargin, 0), 1073741824));
                } else {
                    view2.measure(childMeasureSpec2, i6);
                }
                if (this.mRefreshFooter.getSpinnerStyle() == SpinnerStyle.Scale && !z) {
                    view2.measure(childMeasureSpec2, View.MeasureSpec.makeMeasureSpec(Math.max((Math.max(0, this.mEnableLoadMore ? -this.mSpinner : 0) - layoutParams2.topMargin) - layoutParams2.bottomMargin, 0), 1073741824));
                }
                if (!this.mFooterHeightStatus.notified) {
                    this.mFooterHeightStatus = this.mFooterHeightStatus.notified();
                    this.mRefreshFooter.onInitialized(this.mKernel, this.mFooterHeight, (int) (this.mFooterMaxDragRate * ((float) this.mFooterHeight)));
                }
                if (z && isEnableLoadMore()) {
                    i7 += view2.getMeasuredHeight();
                }
            }
            if (this.mRefreshContent != null && this.mRefreshContent.a() == childAt) {
                View a2 = this.mRefreshContent.a();
                LayoutParams layoutParams3 = (LayoutParams) a2.getLayoutParams();
                a2.measure(ViewGroup.getChildMeasureSpec(i5, getPaddingLeft() + getPaddingRight() + layoutParams3.leftMargin + layoutParams3.rightMargin, layoutParams3.width), ViewGroup.getChildMeasureSpec(i6, getPaddingTop() + getPaddingBottom() + layoutParams3.topMargin + layoutParams3.bottomMargin + ((!z || !isEnableRefresh() || this.mRefreshHeader == null || (!this.mEnableHeaderTranslationContent && this.mRefreshHeader.getSpinnerStyle() != SpinnerStyle.FixedBehind)) ? 0 : this.mHeaderHeight) + ((!z || !isEnableLoadMore() || this.mRefreshFooter == null || (!this.mEnableFooterTranslationContent && this.mRefreshFooter.getSpinnerStyle() != SpinnerStyle.FixedBehind)) ? 0 : this.mFooterHeight), layoutParams3.height));
                i7 += a2.getMeasuredHeight();
            }
        }
        super.setMeasuredDimension(View.resolveSize(super.getSuggestedMinimumWidth(), i5), View.resolveSize(i7, i6));
        this.mLastTouchX = (float) (getMeasuredWidth() / 2);
    }

    /* access modifiers changed from: protected */
    public void onLayout(boolean z, int i, int i2, int i3, int i4) {
        int paddingLeft = getPaddingLeft();
        int paddingTop = getPaddingTop();
        getPaddingBottom();
        int childCount = super.getChildCount();
        for (int i5 = 0; i5 < childCount; i5++) {
            View childAt = super.getChildAt(i5);
            boolean z2 = true;
            if (this.mRefreshContent != null && this.mRefreshContent.a() == childAt) {
                boolean z3 = isInEditMode() && this.mEnablePreviewInEditMode && isEnableRefresh() && this.mRefreshHeader != null;
                View a2 = this.mRefreshContent.a();
                LayoutParams layoutParams = (LayoutParams) a2.getLayoutParams();
                int i6 = layoutParams.leftMargin + paddingLeft;
                int i7 = layoutParams.topMargin + paddingTop;
                int measuredWidth = a2.getMeasuredWidth() + i6;
                int measuredHeight = a2.getMeasuredHeight() + i7;
                if (z3 && (this.mEnableHeaderTranslationContent || this.mRefreshHeader.getSpinnerStyle() == SpinnerStyle.FixedBehind)) {
                    i7 += this.mHeaderHeight;
                    measuredHeight += this.mHeaderHeight;
                }
                a2.layout(i6, i7, measuredWidth, measuredHeight);
            }
            if (this.mRefreshHeader != null && this.mRefreshHeader.getView() == childAt) {
                boolean z4 = isInEditMode() && this.mEnablePreviewInEditMode && isEnableRefresh();
                View view = this.mRefreshHeader.getView();
                LayoutParams layoutParams2 = (LayoutParams) view.getLayoutParams();
                int i8 = layoutParams2.leftMargin;
                int i9 = layoutParams2.topMargin + this.mHeaderInsetStart;
                int measuredWidth2 = view.getMeasuredWidth() + i8;
                int measuredHeight2 = view.getMeasuredHeight() + i9;
                if (!z4 && this.mRefreshHeader.getSpinnerStyle() == SpinnerStyle.Translate) {
                    i9 -= this.mHeaderHeight;
                    measuredHeight2 -= this.mHeaderHeight;
                }
                view.layout(i8, i9, measuredWidth2, measuredHeight2);
            }
            if (this.mRefreshFooter != null && this.mRefreshFooter.getView() == childAt) {
                if (!isInEditMode() || !this.mEnablePreviewInEditMode || !isEnableLoadMore()) {
                    z2 = false;
                }
                View view2 = this.mRefreshFooter.getView();
                LayoutParams layoutParams3 = (LayoutParams) view2.getLayoutParams();
                SpinnerStyle spinnerStyle = this.mRefreshFooter.getSpinnerStyle();
                int i10 = layoutParams3.leftMargin;
                int measuredHeight3 = (layoutParams3.topMargin + getMeasuredHeight()) - this.mFooterInsetStart;
                if (z2 || spinnerStyle == SpinnerStyle.FixedFront || spinnerStyle == SpinnerStyle.FixedBehind) {
                    measuredHeight3 -= this.mFooterHeight;
                } else if (spinnerStyle == SpinnerStyle.Scale && this.mSpinner < 0) {
                    measuredHeight3 -= Math.max(isEnableLoadMore() ? -this.mSpinner : 0, 0);
                }
                view2.layout(i10, measuredHeight3, view2.getMeasuredWidth() + i10, view2.getMeasuredHeight() + measuredHeight3);
            }
        }
    }

    /* access modifiers changed from: protected */
    public void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        this.mKernel.a(0, true);
        notifyStateChanged(RefreshState.None);
        if (this.mHandler != null) {
            this.mHandler.removeCallbacksAndMessages((Object) null);
            this.mHandler = null;
        }
        if (this.mListDelayedRunnable != null) {
            this.mListDelayedRunnable.clear();
            this.mListDelayedRunnable = null;
        }
        this.mManualLoadMore = true;
        this.mManualNestedScrolling = true;
        this.animationRunnable = null;
        if (this.reboundAnimator != null) {
            this.reboundAnimator.removeAllListeners();
            this.reboundAnimator.removeAllUpdateListeners();
            this.reboundAnimator.cancel();
            this.reboundAnimator = null;
        }
    }

    /* access modifiers changed from: protected */
    public boolean drawChild(Canvas canvas, View view, long j) {
        View a2 = this.mRefreshContent != null ? this.mRefreshContent.a() : null;
        if (this.mRefreshHeader != null && this.mRefreshHeader.getView() == view) {
            if (!isEnableRefresh() || (!this.mEnablePreviewInEditMode && isInEditMode())) {
                return true;
            }
            if (a2 != null) {
                int max = Math.max(a2.getTop() + a2.getPaddingTop() + this.mSpinner, view.getTop());
                if (!(this.mHeaderBackgroundColor == 0 || this.mPaint == null)) {
                    this.mPaint.setColor(this.mHeaderBackgroundColor);
                    if (this.mRefreshHeader.getSpinnerStyle() == SpinnerStyle.Scale) {
                        max = view.getBottom();
                    } else if (this.mRefreshHeader.getSpinnerStyle() == SpinnerStyle.Translate) {
                        max = view.getBottom() + this.mSpinner;
                    }
                    canvas.drawRect((float) view.getLeft(), (float) view.getTop(), (float) view.getRight(), (float) max, this.mPaint);
                }
                if (this.mEnableClipHeaderWhenFixedBehind && this.mRefreshHeader.getSpinnerStyle() == SpinnerStyle.FixedBehind) {
                    canvas.save();
                    canvas.clipRect(view.getLeft(), view.getTop(), view.getRight(), max);
                    boolean drawChild = super.drawChild(canvas, view, j);
                    canvas.restore();
                    return drawChild;
                }
            }
        }
        if (this.mRefreshFooter != null && this.mRefreshFooter.getView() == view) {
            if (!isEnableLoadMore() || (!this.mEnablePreviewInEditMode && isInEditMode())) {
                return true;
            }
            if (a2 != null) {
                int min = Math.min((a2.getBottom() - a2.getPaddingBottom()) + this.mSpinner, view.getBottom());
                if (!(this.mFooterBackgroundColor == 0 || this.mPaint == null)) {
                    this.mPaint.setColor(this.mFooterBackgroundColor);
                    if (this.mRefreshFooter.getSpinnerStyle() == SpinnerStyle.Scale) {
                        min = view.getTop();
                    } else if (this.mRefreshFooter.getSpinnerStyle() == SpinnerStyle.Translate) {
                        min = view.getTop() + this.mSpinner;
                    }
                    canvas.drawRect((float) view.getLeft(), (float) min, (float) view.getRight(), (float) view.getBottom(), this.mPaint);
                }
                if (this.mEnableClipFooterWhenFixedBehind && this.mRefreshFooter.getSpinnerStyle() == SpinnerStyle.FixedBehind) {
                    canvas.save();
                    canvas.clipRect(view.getLeft(), min, view.getRight(), view.getBottom());
                    boolean drawChild2 = super.drawChild(canvas, view, j);
                    canvas.restore();
                    return drawChild2;
                }
            }
        }
        return super.drawChild(canvas, view, j);
    }

    public void computeScroll() {
        float f;
        this.mScroller.getCurrY();
        if (this.mScroller.computeScrollOffset()) {
            int finalY = this.mScroller.getFinalY();
            if ((finalY >= 0 || ((!this.mEnableOverScrollDrag && !isEnableRefresh()) || !this.mRefreshContent.c())) && (finalY <= 0 || ((!this.mEnableOverScrollDrag && !isEnableLoadMore()) || !this.mRefreshContent.d()))) {
                this.mVerticalPermit = true;
                invalidate();
                return;
            }
            if (this.mVerticalPermit) {
                if (Build.VERSION.SDK_INT >= 14) {
                    f = finalY > 0 ? -this.mScroller.getCurrVelocity() : this.mScroller.getCurrVelocity();
                } else {
                    f = (((float) (this.mScroller.getCurrY() - finalY)) * 1.0f) / ((float) Math.max(this.mScroller.getDuration() - this.mScroller.timePassed(), 1));
                }
                animSpinnerBounce(f);
            }
            this.mScroller.forceFinished(true);
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:196:0x02c2  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public boolean dispatchTouchEvent(android.view.MotionEvent r23) {
        /*
            r22 = this;
            r0 = r22
            r1 = r23
            int r6 = r23.getActionMasked()
            r2 = 6
            r10 = 0
            r11 = 1
            if (r6 != r2) goto L_0x000f
            r3 = 1
            goto L_0x0010
        L_0x000f:
            r3 = 0
        L_0x0010:
            if (r3 == 0) goto L_0x0017
            int r4 = r23.getActionIndex()
            goto L_0x0018
        L_0x0017:
            r4 = -1
        L_0x0018:
            int r5 = r23.getPointerCount()
            r7 = 0
            r8 = 0
            r9 = 0
            r12 = 0
        L_0x0020:
            if (r8 >= r5) goto L_0x0032
            if (r4 != r8) goto L_0x0025
            goto L_0x002f
        L_0x0025:
            float r13 = r1.getX(r8)
            float r9 = r9 + r13
            float r13 = r1.getY(r8)
            float r12 = r12 + r13
        L_0x002f:
            int r8 = r8 + 1
            goto L_0x0020
        L_0x0032:
            if (r3 == 0) goto L_0x0036
            int r5 = r5 + -1
        L_0x0036:
            float r3 = (float) r5
            float r9 = r9 / r3
            float r8 = r12 / r3
            r3 = 5
            if (r6 == r2) goto L_0x003f
            if (r6 != r3) goto L_0x004c
        L_0x003f:
            boolean r4 = r0.mIsBeingDragged
            if (r4 == 0) goto L_0x004c
            float r4 = r0.mTouchY
            float r5 = r0.mLastTouchY
            float r5 = r8 - r5
            float r4 = r4 + r5
            r0.mTouchY = r4
        L_0x004c:
            r0.mLastTouchX = r9
            r0.mLastTouchY = r8
            boolean r4 = r0.mNestedInProgress
            if (r4 == 0) goto L_0x00a8
            int r2 = r0.mTotalUnconsumed
            boolean r1 = super.dispatchTouchEvent(r23)
            r3 = 2
            if (r6 != r3) goto L_0x00a7
            int r3 = r0.mTotalUnconsumed
            if (r2 != r3) goto L_0x00a7
            float r2 = r0.mLastTouchX
            int r2 = (int) r2
            int r3 = r22.getWidth()
            float r4 = r0.mLastTouchX
            if (r3 != 0) goto L_0x006d
            goto L_0x006e
        L_0x006d:
            r11 = r3
        L_0x006e:
            float r5 = (float) r11
            float r4 = r4 / r5
            boolean r5 = r22.isEnableRefresh()
            if (r5 == 0) goto L_0x008c
            int r5 = r0.mSpinner
            if (r5 <= 0) goto L_0x008c
            com.scwang.smartrefresh.layout.api.RefreshInternal r5 = r0.mRefreshHeader
            if (r5 == 0) goto L_0x008c
            com.scwang.smartrefresh.layout.api.RefreshInternal r5 = r0.mRefreshHeader
            boolean r5 = r5.isSupportHorizontalDrag()
            if (r5 == 0) goto L_0x008c
            com.scwang.smartrefresh.layout.api.RefreshInternal r5 = r0.mRefreshHeader
            r5.onHorizontalDrag(r4, r2, r3)
            goto L_0x00a7
        L_0x008c:
            boolean r5 = r22.isEnableLoadMore()
            if (r5 == 0) goto L_0x00a7
            int r5 = r0.mSpinner
            if (r5 >= 0) goto L_0x00a7
            com.scwang.smartrefresh.layout.api.RefreshInternal r5 = r0.mRefreshFooter
            if (r5 == 0) goto L_0x00a7
            com.scwang.smartrefresh.layout.api.RefreshInternal r5 = r0.mRefreshFooter
            boolean r5 = r5.isSupportHorizontalDrag()
            if (r5 == 0) goto L_0x00a7
            com.scwang.smartrefresh.layout.api.RefreshInternal r5 = r0.mRefreshFooter
            r5.onHorizontalDrag(r4, r2, r3)
        L_0x00a7:
            return r1
        L_0x00a8:
            boolean r4 = r22.isEnabled()
            if (r4 == 0) goto L_0x0377
            boolean r4 = r22.isEnableRefresh()
            if (r4 != 0) goto L_0x00be
            boolean r4 = r22.isEnableLoadMore()
            if (r4 != 0) goto L_0x00be
            boolean r4 = r0.mEnableOverScrollDrag
            if (r4 == 0) goto L_0x0377
        L_0x00be:
            boolean r4 = r0.mHeaderNeedTouchEventWhenRefreshing
            if (r4 == 0) goto L_0x00d4
            com.scwang.smartrefresh.layout.constant.RefreshState r4 = r0.mState
            boolean r4 = r4.isOpening
            if (r4 != 0) goto L_0x00ce
            com.scwang.smartrefresh.layout.constant.RefreshState r4 = r0.mState
            boolean r4 = r4.isFinishing
            if (r4 == 0) goto L_0x00d4
        L_0x00ce:
            com.scwang.smartrefresh.layout.constant.RefreshState r4 = r0.mState
            boolean r4 = r4.isHeader
            if (r4 != 0) goto L_0x0377
        L_0x00d4:
            boolean r4 = r0.mFooterNeedTouchEventWhenLoading
            if (r4 == 0) goto L_0x00ec
            com.scwang.smartrefresh.layout.constant.RefreshState r4 = r0.mState
            boolean r4 = r4.isOpening
            if (r4 != 0) goto L_0x00e4
            com.scwang.smartrefresh.layout.constant.RefreshState r4 = r0.mState
            boolean r4 = r4.isFinishing
            if (r4 == 0) goto L_0x00ec
        L_0x00e4:
            com.scwang.smartrefresh.layout.constant.RefreshState r4 = r0.mState
            boolean r4 = r4.isFooter
            if (r4 == 0) goto L_0x00ec
            goto L_0x0377
        L_0x00ec:
            boolean r4 = r0.interceptAnimatorByAction(r6)
            if (r4 != 0) goto L_0x0376
            com.scwang.smartrefresh.layout.constant.RefreshState r4 = r0.mState
            boolean r4 = r4.isFinishing
            if (r4 != 0) goto L_0x0376
            com.scwang.smartrefresh.layout.constant.RefreshState r4 = r0.mState
            com.scwang.smartrefresh.layout.constant.RefreshState r5 = com.scwang.smartrefresh.layout.constant.RefreshState.Loading
            if (r4 != r5) goto L_0x0102
            boolean r4 = r0.mDisableContentWhenLoading
            if (r4 != 0) goto L_0x0376
        L_0x0102:
            com.scwang.smartrefresh.layout.constant.RefreshState r4 = r0.mState
            com.scwang.smartrefresh.layout.constant.RefreshState r5 = com.scwang.smartrefresh.layout.constant.RefreshState.Refreshing
            if (r4 != r5) goto L_0x010e
            boolean r4 = r0.mDisableContentWhenRefresh
            if (r4 == 0) goto L_0x010e
            goto L_0x0376
        L_0x010e:
            r4 = 104(0x68, float:1.46E-43)
            r5 = 0
            switch(r6) {
                case 0: goto L_0x0330;
                case 1: goto L_0x02e5;
                case 2: goto L_0x0116;
                case 3: goto L_0x0300;
                default: goto L_0x0114;
            }
        L_0x0114:
            goto L_0x0371
        L_0x0116:
            float r2 = r0.mTouchX
            float r9 = r9 - r2
            float r2 = r0.mTouchY
            float r2 = r8 - r2
            android.view.VelocityTracker r3 = r0.mVelocityTracker
            r3.addMovement(r1)
            boolean r3 = r0.mIsBeingDragged
            r6 = 3
            if (r3 != 0) goto L_0x01f3
            char r3 = r0.mDragDirection
            if (r3 == r4) goto L_0x01f3
            com.scwang.smartrefresh.layout.api.RefreshContent r3 = r0.mRefreshContent
            if (r3 == 0) goto L_0x01f3
            char r3 = r0.mDragDirection
            r12 = 118(0x76, float:1.65E-43)
            if (r3 == r12) goto L_0x016c
            float r3 = java.lang.Math.abs(r2)
            int r13 = r0.mTouchSlop
            float r13 = (float) r13
            int r3 = (r3 > r13 ? 1 : (r3 == r13 ? 0 : -1))
            if (r3 < 0) goto L_0x014d
            float r3 = java.lang.Math.abs(r9)
            float r13 = java.lang.Math.abs(r2)
            int r3 = (r3 > r13 ? 1 : (r3 == r13 ? 0 : -1))
            if (r3 >= 0) goto L_0x014d
            goto L_0x016c
        L_0x014d:
            float r3 = java.lang.Math.abs(r9)
            int r13 = r0.mTouchSlop
            float r13 = (float) r13
            int r3 = (r3 > r13 ? 1 : (r3 == r13 ? 0 : -1))
            if (r3 < 0) goto L_0x01f3
            float r3 = java.lang.Math.abs(r9)
            float r13 = java.lang.Math.abs(r2)
            int r3 = (r3 > r13 ? 1 : (r3 == r13 ? 0 : -1))
            if (r3 <= 0) goto L_0x01f3
            char r3 = r0.mDragDirection
            if (r3 == r12) goto L_0x01f3
            r0.mDragDirection = r4
            goto L_0x01f3
        L_0x016c:
            r0.mDragDirection = r12
            int r3 = (r2 > r7 ? 1 : (r2 == r7 ? 0 : -1))
            if (r3 <= 0) goto L_0x0192
            int r3 = r0.mSpinner
            if (r3 < 0) goto L_0x0188
            boolean r3 = r0.mEnableOverScrollDrag
            if (r3 != 0) goto L_0x0180
            boolean r3 = r22.isEnableRefresh()
            if (r3 == 0) goto L_0x0192
        L_0x0180:
            com.scwang.smartrefresh.layout.api.RefreshContent r3 = r0.mRefreshContent
            boolean r3 = r3.c()
            if (r3 == 0) goto L_0x0192
        L_0x0188:
            r0.mIsBeingDragged = r11
            int r3 = r0.mTouchSlop
            float r3 = (float) r3
            float r3 = r8 - r3
            r0.mTouchY = r3
            goto L_0x01be
        L_0x0192:
            int r3 = (r2 > r7 ? 1 : (r2 == r7 ? 0 : -1))
            if (r3 >= 0) goto L_0x01be
            int r3 = r0.mSpinner
            if (r3 > 0) goto L_0x01b6
            boolean r3 = r0.mEnableOverScrollDrag
            if (r3 != 0) goto L_0x01a4
            boolean r3 = r22.isEnableLoadMore()
            if (r3 == 0) goto L_0x01be
        L_0x01a4:
            com.scwang.smartrefresh.layout.constant.RefreshState r3 = r0.mState
            com.scwang.smartrefresh.layout.constant.RefreshState r4 = com.scwang.smartrefresh.layout.constant.RefreshState.Loading
            if (r3 != r4) goto L_0x01ae
            boolean r3 = r0.mFooterLocked
            if (r3 != 0) goto L_0x01b6
        L_0x01ae:
            com.scwang.smartrefresh.layout.api.RefreshContent r3 = r0.mRefreshContent
            boolean r3 = r3.d()
            if (r3 == 0) goto L_0x01be
        L_0x01b6:
            r0.mIsBeingDragged = r11
            int r3 = r0.mTouchSlop
            float r3 = (float) r3
            float r3 = r3 + r8
            r0.mTouchY = r3
        L_0x01be:
            boolean r3 = r0.mIsBeingDragged
            if (r3 == 0) goto L_0x01f3
            float r2 = r0.mTouchY
            float r2 = r8 - r2
            boolean r3 = r0.mSuperDispatchTouchEvent
            if (r3 == 0) goto L_0x01d0
            r1.setAction(r6)
            super.dispatchTouchEvent(r23)
        L_0x01d0:
            int r3 = r0.mSpinner
            if (r3 > 0) goto L_0x01e5
            int r3 = r0.mSpinner
            if (r3 != 0) goto L_0x01dd
            int r3 = (r2 > r7 ? 1 : (r2 == r7 ? 0 : -1))
            if (r3 <= 0) goto L_0x01dd
            goto L_0x01e5
        L_0x01dd:
            com.scwang.smartrefresh.layout.api.RefreshKernel r3 = r0.mKernel
            com.scwang.smartrefresh.layout.constant.RefreshState r4 = com.scwang.smartrefresh.layout.constant.RefreshState.PullUpToLoad
            r3.a((com.scwang.smartrefresh.layout.constant.RefreshState) r4)
            goto L_0x01ec
        L_0x01e5:
            com.scwang.smartrefresh.layout.api.RefreshKernel r3 = r0.mKernel
            com.scwang.smartrefresh.layout.constant.RefreshState r4 = com.scwang.smartrefresh.layout.constant.RefreshState.PullDownToRefresh
            r3.a((com.scwang.smartrefresh.layout.constant.RefreshState) r4)
        L_0x01ec:
            android.view.ViewParent r3 = r22.getParent()
            r3.requestDisallowInterceptTouchEvent(r11)
        L_0x01f3:
            boolean r3 = r0.mIsBeingDragged
            if (r3 == 0) goto L_0x02d2
            int r3 = (int) r2
            int r4 = r0.mTouchSpinner
            int r3 = r3 + r4
            com.scwang.smartrefresh.layout.constant.RefreshState r4 = r0.mViceState
            boolean r4 = r4.isHeader
            if (r4 == 0) goto L_0x0207
            if (r3 < 0) goto L_0x0213
            int r4 = r0.mLastSpinner
            if (r4 < 0) goto L_0x0213
        L_0x0207:
            com.scwang.smartrefresh.layout.constant.RefreshState r4 = r0.mViceState
            boolean r4 = r4.isFooter
            if (r4 == 0) goto L_0x02cd
            if (r3 > 0) goto L_0x0213
            int r4 = r0.mLastSpinner
            if (r4 <= 0) goto L_0x02cd
        L_0x0213:
            r0.mLastSpinner = r3
            long r20 = r23.getEventTime()
            android.view.MotionEvent r1 = r0.mFalsifyEvent
            if (r1 != 0) goto L_0x0238
            r16 = 0
            float r1 = r0.mTouchX
            float r17 = r1 + r9
            float r1 = r0.mTouchY
            r19 = 0
            r12 = r20
            r14 = r20
            r18 = r1
            android.view.MotionEvent r1 = android.view.MotionEvent.obtain(r12, r14, r16, r17, r18, r19)
            r0.mFalsifyEvent = r1
            android.view.MotionEvent r1 = r0.mFalsifyEvent
            super.dispatchTouchEvent(r1)
        L_0x0238:
            r16 = 2
            float r1 = r0.mTouchX
            float r17 = r1 + r9
            float r1 = r0.mTouchY
            float r4 = (float) r3
            float r18 = r1 + r4
            r19 = 0
            r12 = r20
            r14 = r20
            android.view.MotionEvent r1 = android.view.MotionEvent.obtain(r12, r14, r16, r17, r18, r19)
            super.dispatchTouchEvent(r1)
            boolean r4 = r0.mFooterLocked
            if (r4 == 0) goto L_0x0261
            int r4 = r0.mTouchSlop
            float r4 = (float) r4
            int r2 = (r2 > r4 ? 1 : (r2 == r4 ? 0 : -1))
            if (r2 <= 0) goto L_0x0261
            int r2 = r0.mSpinner
            if (r2 >= 0) goto L_0x0261
            r0.mFooterLocked = r10
        L_0x0261:
            if (r3 <= 0) goto L_0x0284
            boolean r2 = r0.mEnableOverScrollDrag
            if (r2 != 0) goto L_0x026d
            boolean r2 = r22.isEnableRefresh()
            if (r2 == 0) goto L_0x0284
        L_0x026d:
            com.scwang.smartrefresh.layout.api.RefreshContent r2 = r0.mRefreshContent
            boolean r2 = r2.c()
            if (r2 == 0) goto L_0x0284
            r0.mLastTouchY = r8
            r0.mTouchY = r8
            r0.mTouchSpinner = r10
            com.scwang.smartrefresh.layout.api.RefreshKernel r2 = r0.mKernel
            com.scwang.smartrefresh.layout.constant.RefreshState r3 = com.scwang.smartrefresh.layout.constant.RefreshState.PullDownToRefresh
            r2.a((com.scwang.smartrefresh.layout.constant.RefreshState) r3)
        L_0x0282:
            r3 = 0
            goto L_0x02a6
        L_0x0284:
            if (r3 >= 0) goto L_0x02a6
            boolean r2 = r0.mEnableOverScrollDrag
            if (r2 != 0) goto L_0x0290
            boolean r2 = r22.isEnableLoadMore()
            if (r2 == 0) goto L_0x02a6
        L_0x0290:
            com.scwang.smartrefresh.layout.api.RefreshContent r2 = r0.mRefreshContent
            boolean r2 = r2.d()
            if (r2 == 0) goto L_0x02a6
            r0.mLastTouchY = r8
            r0.mTouchY = r8
            r0.mTouchSpinner = r10
            com.scwang.smartrefresh.layout.api.RefreshKernel r2 = r0.mKernel
            com.scwang.smartrefresh.layout.constant.RefreshState r3 = com.scwang.smartrefresh.layout.constant.RefreshState.PullUpToLoad
            r2.a((com.scwang.smartrefresh.layout.constant.RefreshState) r3)
            goto L_0x0282
        L_0x02a6:
            com.scwang.smartrefresh.layout.constant.RefreshState r2 = r0.mViceState
            boolean r2 = r2.isHeader
            if (r2 == 0) goto L_0x02ae
            if (r3 < 0) goto L_0x02b6
        L_0x02ae:
            com.scwang.smartrefresh.layout.constant.RefreshState r2 = r0.mViceState
            boolean r2 = r2.isFooter
            if (r2 == 0) goto L_0x02be
            if (r3 <= 0) goto L_0x02be
        L_0x02b6:
            int r1 = r0.mSpinner
            if (r1 == 0) goto L_0x02bd
            r0.moveSpinnerInfinitely(r7)
        L_0x02bd:
            return r11
        L_0x02be:
            android.view.MotionEvent r2 = r0.mFalsifyEvent
            if (r2 == 0) goto L_0x02ca
            r0.mFalsifyEvent = r5
            r1.setAction(r6)
            super.dispatchTouchEvent(r1)
        L_0x02ca:
            r1.recycle()
        L_0x02cd:
            float r1 = (float) r3
            r0.moveSpinnerInfinitely(r1)
            return r11
        L_0x02d2:
            boolean r3 = r0.mFooterLocked
            if (r3 == 0) goto L_0x0371
            int r3 = r0.mTouchSlop
            float r3 = (float) r3
            int r2 = (r2 > r3 ? 1 : (r2 == r3 ? 0 : -1))
            if (r2 <= 0) goto L_0x0371
            int r2 = r0.mSpinner
            if (r2 >= 0) goto L_0x0371
            r0.mFooterLocked = r10
            goto L_0x0371
        L_0x02e5:
            android.view.VelocityTracker r2 = r0.mVelocityTracker
            r2.addMovement(r1)
            android.view.VelocityTracker r2 = r0.mVelocityTracker
            r3 = 1000(0x3e8, float:1.401E-42)
            int r4 = r0.mMaximumVelocity
            float r4 = (float) r4
            r2.computeCurrentVelocity(r3, r4)
            android.view.VelocityTracker r2 = r0.mVelocityTracker
            float r2 = r2.getYVelocity()
            int r2 = (int) r2
            r0.mCurrentVelocity = r2
            r0.startFlingIfNeed(r5)
        L_0x0300:
            android.view.VelocityTracker r2 = r0.mVelocityTracker
            r2.clear()
            r2 = 110(0x6e, float:1.54E-43)
            r0.mDragDirection = r2
            android.view.MotionEvent r2 = r0.mFalsifyEvent
            if (r2 == 0) goto L_0x0326
            android.view.MotionEvent r2 = r0.mFalsifyEvent
            r2.recycle()
            r0.mFalsifyEvent = r5
            long r4 = r23.getEventTime()
            float r7 = r0.mTouchX
            r9 = 0
            r2 = r4
            android.view.MotionEvent r2 = android.view.MotionEvent.obtain(r2, r4, r6, r7, r8, r9)
            super.dispatchTouchEvent(r2)
            r2.recycle()
        L_0x0326:
            r22.overSpinner()
            boolean r2 = r0.mIsBeingDragged
            if (r2 == 0) goto L_0x0371
            r0.mIsBeingDragged = r10
            return r11
        L_0x0330:
            r0.mCurrentVelocity = r10
            android.view.VelocityTracker r5 = r0.mVelocityTracker
            r5.addMovement(r1)
            android.widget.Scroller r5 = r0.mScroller
            r5.forceFinished(r11)
            r0.mTouchX = r9
            r0.mTouchY = r8
            r0.mLastSpinner = r10
            int r5 = r0.mSpinner
            r0.mTouchSpinner = r5
            r0.mIsBeingDragged = r10
            boolean r5 = super.dispatchTouchEvent(r23)
            r0.mSuperDispatchTouchEvent = r5
            com.scwang.smartrefresh.layout.constant.RefreshState r5 = r0.mState
            com.scwang.smartrefresh.layout.constant.RefreshState r6 = com.scwang.smartrefresh.layout.constant.RefreshState.TwoLevel
            if (r5 != r6) goto L_0x0367
            float r5 = r0.mTouchY
            int r6 = r22.getMeasuredHeight()
            int r6 = r6 * 5
            int r6 = r6 / r2
            float r2 = (float) r6
            int r2 = (r5 > r2 ? 1 : (r5 == r2 ? 0 : -1))
            if (r2 >= 0) goto L_0x0367
            r0.mDragDirection = r4
            boolean r1 = r0.mSuperDispatchTouchEvent
            return r1
        L_0x0367:
            com.scwang.smartrefresh.layout.api.RefreshContent r2 = r0.mRefreshContent
            if (r2 == 0) goto L_0x0370
            com.scwang.smartrefresh.layout.api.RefreshContent r2 = r0.mRefreshContent
            r2.a((android.view.MotionEvent) r1)
        L_0x0370:
            return r11
        L_0x0371:
            boolean r1 = super.dispatchTouchEvent(r23)
            return r1
        L_0x0376:
            return r10
        L_0x0377:
            boolean r1 = super.dispatchTouchEvent(r23)
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.scwang.smartrefresh.layout.SmartRefreshLayout.dispatchTouchEvent(android.view.MotionEvent):boolean");
    }

    /* access modifiers changed from: protected */
    public boolean startFlingIfNeed(Float f) {
        float floatValue = f == null ? (float) this.mCurrentVelocity : f.floatValue();
        if (Math.abs(floatValue) > ((float) this.mMinimumVelocity)) {
            if (((float) this.mSpinner) * floatValue < 0.0f) {
                if (this.mState.isOpening) {
                    if (!(this.mState == RefreshState.TwoLevel || this.mState == this.mViceState)) {
                        this.animationRunnable = new FlingRunnable(floatValue).a();
                        return true;
                    }
                } else if (((float) this.mSpinner) > ((float) this.mHeaderHeight) * this.mHeaderTriggerRate || ((float) (-this.mSpinner)) > ((float) this.mFooterHeight) * this.mFooterTriggerRate) {
                    return true;
                }
            }
            if ((floatValue < 0.0f && ((this.mEnableOverScrollBounce && (this.mEnableOverScrollDrag || isEnableLoadMore())) || ((this.mState == RefreshState.Loading && this.mSpinner >= 0) || (this.mEnableAutoLoadMore && isEnableLoadMore())))) || (floatValue > 0.0f && ((this.mEnableOverScrollBounce && (this.mEnableOverScrollDrag || isEnableRefresh())) || (this.mState == RefreshState.Refreshing && this.mSpinner <= 0)))) {
                this.mVerticalPermit = false;
                this.mScroller.fling(0, 0, 0, (int) (-floatValue), 0, 0, -2147483647, Integer.MAX_VALUE);
                this.mScroller.computeScrollOffset();
                invalidate();
            }
        }
        return false;
    }

    /* access modifiers changed from: protected */
    public boolean interceptAnimatorByAction(int i) {
        if (i == 0) {
            if (this.reboundAnimator != null) {
                if (this.mState.isFinishing || this.mState == RefreshState.TwoLevelReleased) {
                    return true;
                }
                if (this.mState == RefreshState.PullDownCanceled) {
                    this.mKernel.a(RefreshState.PullDownToRefresh);
                } else if (this.mState == RefreshState.PullUpCanceled) {
                    this.mKernel.a(RefreshState.PullUpToLoad);
                }
                this.reboundAnimator.cancel();
                this.reboundAnimator = null;
            }
            this.animationRunnable = null;
        }
        if (this.reboundAnimator != null) {
            return true;
        }
        return false;
    }

    /* access modifiers changed from: protected */
    public void notifyStateChanged(RefreshState refreshState) {
        RefreshState refreshState2 = this.mState;
        if (refreshState2 != refreshState) {
            this.mState = refreshState;
            this.mViceState = refreshState;
            RefreshInternal refreshInternal = this.mRefreshHeader;
            RefreshInternal refreshInternal2 = this.mRefreshFooter;
            OnMultiPurposeListener onMultiPurposeListener = this.mOnMultiPurposeListener;
            if (refreshInternal != null) {
                refreshInternal.onStateChanged(this, refreshState2, refreshState);
            }
            if (refreshInternal2 != null) {
                refreshInternal2.onStateChanged(this, refreshState2, refreshState);
            }
            if (onMultiPurposeListener != null) {
                onMultiPurposeListener.onStateChanged(this, refreshState2, refreshState);
            }
        }
    }

    /* access modifiers changed from: protected */
    public void setStateDirectLoading() {
        if (this.mState != RefreshState.Loading) {
            this.mLastOpenTime = System.currentTimeMillis();
            this.mFooterLocked = true;
            notifyStateChanged(RefreshState.Loading);
            if (this.mLoadMoreListener != null) {
                this.mLoadMoreListener.a(this);
            } else if (this.mOnMultiPurposeListener == null) {
                finishLoadMore(2000);
            }
            if (this.mRefreshFooter != null) {
                this.mRefreshFooter.onStartAnimator(this, this.mFooterHeight, (int) (this.mFooterMaxDragRate * ((float) this.mFooterHeight)));
            }
            if (this.mOnMultiPurposeListener != null && (this.mRefreshFooter instanceof RefreshFooter)) {
                this.mOnMultiPurposeListener.a(this);
                this.mOnMultiPurposeListener.b((RefreshFooter) this.mRefreshFooter, this.mFooterHeight, (int) (this.mFooterMaxDragRate * ((float) this.mFooterHeight)));
            }
        }
    }

    /* access modifiers changed from: protected */
    public void setStateLoading() {
        AnonymousClass4 r0 = new AnimatorListenerAdapter() {
            public void onAnimationEnd(Animator animator) {
                SmartRefreshLayout.this.setStateDirectLoading();
            }
        };
        notifyStateChanged(RefreshState.LoadReleased);
        ValueAnimator a2 = this.mKernel.a(-this.mFooterHeight);
        if (a2 != null) {
            a2.addListener(r0);
        }
        if (this.mRefreshFooter != null) {
            this.mRefreshFooter.onReleased(this, this.mFooterHeight, (int) (this.mFooterMaxDragRate * ((float) this.mFooterHeight)));
        }
        if (this.mOnMultiPurposeListener != null && (this.mRefreshFooter instanceof RefreshFooter)) {
            this.mOnMultiPurposeListener.a((RefreshFooter) this.mRefreshFooter, this.mFooterHeight, (int) (this.mFooterMaxDragRate * ((float) this.mFooterHeight)));
        }
        if (a2 == null) {
            r0.onAnimationEnd((Animator) null);
        }
    }

    /* access modifiers changed from: protected */
    public void setStateRefreshing() {
        AnonymousClass5 r0 = new AnimatorListenerAdapter() {
            public void onAnimationEnd(Animator animator) {
                SmartRefreshLayout.this.mLastOpenTime = System.currentTimeMillis();
                SmartRefreshLayout.this.notifyStateChanged(RefreshState.Refreshing);
                if (SmartRefreshLayout.this.mRefreshListener != null) {
                    SmartRefreshLayout.this.mRefreshListener.b(SmartRefreshLayout.this);
                } else if (SmartRefreshLayout.this.mOnMultiPurposeListener == null) {
                    SmartRefreshLayout.this.finishRefresh(3000);
                }
                if (SmartRefreshLayout.this.mRefreshHeader != null) {
                    SmartRefreshLayout.this.mRefreshHeader.onStartAnimator(SmartRefreshLayout.this, SmartRefreshLayout.this.mHeaderHeight, (int) (SmartRefreshLayout.this.mHeaderMaxDragRate * ((float) SmartRefreshLayout.this.mHeaderHeight)));
                }
                if (SmartRefreshLayout.this.mOnMultiPurposeListener != null && (SmartRefreshLayout.this.mRefreshHeader instanceof RefreshHeader)) {
                    SmartRefreshLayout.this.mOnMultiPurposeListener.b(SmartRefreshLayout.this);
                    SmartRefreshLayout.this.mOnMultiPurposeListener.b((RefreshHeader) SmartRefreshLayout.this.mRefreshHeader, SmartRefreshLayout.this.mHeaderHeight, (int) (SmartRefreshLayout.this.mHeaderMaxDragRate * ((float) SmartRefreshLayout.this.mHeaderHeight)));
                }
            }
        };
        notifyStateChanged(RefreshState.RefreshReleased);
        ValueAnimator a2 = this.mKernel.a(this.mHeaderHeight);
        if (a2 != null) {
            a2.addListener(r0);
        }
        if (this.mRefreshHeader != null) {
            this.mRefreshHeader.onReleased(this, this.mHeaderHeight, (int) (this.mHeaderMaxDragRate * ((float) this.mHeaderHeight)));
        }
        if (this.mOnMultiPurposeListener != null && (this.mRefreshHeader instanceof RefreshHeader)) {
            this.mOnMultiPurposeListener.a((RefreshHeader) this.mRefreshHeader, this.mHeaderHeight, (int) (this.mHeaderMaxDragRate * ((float) this.mHeaderHeight)));
        }
        if (a2 == null) {
            r0.onAnimationEnd((Animator) null);
        }
    }

    /* access modifiers changed from: protected */
    public void resetStatus() {
        if (this.mState != RefreshState.None && this.mSpinner == 0) {
            notifyStateChanged(RefreshState.None);
        }
        if (this.mSpinner != 0) {
            this.mKernel.a(0);
        }
    }

    /* access modifiers changed from: protected */
    public void setViceState(RefreshState refreshState) {
        if (this.mState.isDragging && this.mState.isHeader != refreshState.isHeader) {
            notifyStateChanged(RefreshState.None);
        }
        if (this.mViceState != refreshState) {
            this.mViceState = refreshState;
        }
    }

    protected class FlingRunnable implements Runnable {

        /* renamed from: a  reason: collision with root package name */
        int f8785a;
        int b = 0;
        int c = 10;
        float d;
        float e = 0.98f;
        long f = 0;
        long g = AnimationUtils.currentAnimationTimeMillis();

        FlingRunnable(float f2) {
            this.d = f2;
            this.f8785a = SmartRefreshLayout.this.mSpinner;
        }

        public Runnable a() {
            if (SmartRefreshLayout.this.mState.isFinishing) {
                return null;
            }
            if (SmartRefreshLayout.this.mSpinner != 0 && ((!SmartRefreshLayout.this.mState.isOpening && (!SmartRefreshLayout.this.mFooterNoMoreData || !SmartRefreshLayout.this.mEnableFooterFollowWhenLoadFinished || !SmartRefreshLayout.this.isEnableLoadMore())) || (((SmartRefreshLayout.this.mState == RefreshState.Loading || (SmartRefreshLayout.this.mFooterNoMoreData && SmartRefreshLayout.this.mEnableFooterFollowWhenLoadFinished && SmartRefreshLayout.this.isEnableLoadMore())) && SmartRefreshLayout.this.mSpinner < (-SmartRefreshLayout.this.mFooterHeight)) || (SmartRefreshLayout.this.mState == RefreshState.Refreshing && SmartRefreshLayout.this.mSpinner > SmartRefreshLayout.this.mHeaderHeight)))) {
                int i = 0;
                int i2 = SmartRefreshLayout.this.mSpinner;
                int i3 = SmartRefreshLayout.this.mSpinner;
                float f2 = this.d;
                while (true) {
                    if (i3 * i2 <= 0) {
                        break;
                    }
                    double d2 = (double) f2;
                    i++;
                    double pow = Math.pow((double) this.e, (double) ((this.c * i) / 10));
                    Double.isNaN(d2);
                    f2 = (float) (d2 * pow);
                    float f3 = ((((float) this.c) * 1.0f) / 1000.0f) * f2;
                    if (Math.abs(f3) >= 1.0f) {
                        i2 = (int) (((float) i2) + f3);
                    } else if (!SmartRefreshLayout.this.mState.isOpening || ((SmartRefreshLayout.this.mState == RefreshState.Refreshing && i2 > SmartRefreshLayout.this.mHeaderHeight) || (SmartRefreshLayout.this.mState != RefreshState.Refreshing && i2 < (-SmartRefreshLayout.this.mFooterHeight)))) {
                        return null;
                    }
                }
            }
            this.f = AnimationUtils.currentAnimationTimeMillis();
            SmartRefreshLayout.this.postDelayed(this, (long) this.c);
            return this;
        }

        public void run() {
            if (SmartRefreshLayout.this.animationRunnable == this && !SmartRefreshLayout.this.mState.isFinishing) {
                long currentAnimationTimeMillis = AnimationUtils.currentAnimationTimeMillis();
                double d2 = (double) this.d;
                double pow = Math.pow((double) this.e, (double) ((currentAnimationTimeMillis - this.f) / ((long) (1000 / this.c))));
                Double.isNaN(d2);
                this.d = (float) (d2 * pow);
                float f2 = this.d * ((((float) (currentAnimationTimeMillis - this.g)) * 1.0f) / 1000.0f);
                if (Math.abs(f2) > 1.0f) {
                    this.g = currentAnimationTimeMillis;
                    this.f8785a = (int) (((float) this.f8785a) + f2);
                    if (SmartRefreshLayout.this.mSpinner * this.f8785a > 0) {
                        SmartRefreshLayout.this.mKernel.a(this.f8785a, true);
                        SmartRefreshLayout.this.postDelayed(this, (long) this.c);
                        return;
                    }
                    SmartRefreshLayout.this.animationRunnable = null;
                    SmartRefreshLayout.this.mKernel.a(0, true);
                    SmartUtil.a(SmartRefreshLayout.this.mRefreshContent.b(), (int) (-this.d));
                    if (SmartRefreshLayout.this.mFooterLocked && f2 > 0.0f) {
                        SmartRefreshLayout.this.mFooterLocked = false;
                        return;
                    }
                    return;
                }
                SmartRefreshLayout.this.animationRunnable = null;
            }
        }
    }

    protected class BounceRunnable implements Runnable {

        /* renamed from: a  reason: collision with root package name */
        int f8784a = 0;
        int b = 10;
        int c;
        long d;
        float e = 0.0f;
        float f;

        BounceRunnable(float f2, int i) {
            this.f = f2;
            this.c = i;
            this.d = AnimationUtils.currentAnimationTimeMillis();
            SmartRefreshLayout.this.postDelayed(this, (long) this.b);
        }

        public void run() {
            if (SmartRefreshLayout.this.animationRunnable == this && !SmartRefreshLayout.this.mState.isFinishing) {
                if (Math.abs(SmartRefreshLayout.this.mSpinner) < Math.abs(this.c)) {
                    double d2 = (double) this.f;
                    int i = this.f8784a + 1;
                    this.f8784a = i;
                    double pow = Math.pow(0.949999988079071d, (double) i);
                    Double.isNaN(d2);
                    this.f = (float) (d2 * pow);
                } else if (this.c != 0) {
                    double d3 = (double) this.f;
                    int i2 = this.f8784a + 1;
                    this.f8784a = i2;
                    double pow2 = Math.pow(0.44999998807907104d, (double) i2);
                    Double.isNaN(d3);
                    this.f = (float) (d3 * pow2);
                } else {
                    double d4 = (double) this.f;
                    int i3 = this.f8784a + 1;
                    this.f8784a = i3;
                    double pow3 = Math.pow(0.8500000238418579d, (double) i3);
                    Double.isNaN(d4);
                    this.f = (float) (d4 * pow3);
                }
                long currentAnimationTimeMillis = AnimationUtils.currentAnimationTimeMillis();
                float f2 = this.f * ((((float) (currentAnimationTimeMillis - this.d)) * 1.0f) / 1000.0f);
                if (Math.abs(f2) >= 1.0f) {
                    this.d = currentAnimationTimeMillis;
                    this.e += f2;
                    SmartRefreshLayout.this.moveSpinnerInfinitely(this.e);
                    SmartRefreshLayout.this.postDelayed(this, (long) this.b);
                    return;
                }
                SmartRefreshLayout.this.animationRunnable = null;
                if (Math.abs(SmartRefreshLayout.this.mSpinner) >= Math.abs(this.c)) {
                    SmartRefreshLayout.this.animSpinner(this.c, 0, SmartRefreshLayout.this.mReboundInterpolator, Math.min(Math.max((int) DensityUtil.a(Math.abs(SmartRefreshLayout.this.mSpinner - this.c)), 30), 100) * 10);
                }
            }
        }
    }

    /* access modifiers changed from: protected */
    public ValueAnimator animSpinner(int i, int i2, Interpolator interpolator, int i3) {
        if (this.mSpinner == i) {
            return null;
        }
        if (this.reboundAnimator != null) {
            this.reboundAnimator.cancel();
        }
        this.animationRunnable = null;
        this.reboundAnimator = ValueAnimator.ofInt(new int[]{this.mSpinner, i});
        this.reboundAnimator.setDuration((long) i3);
        this.reboundAnimator.setInterpolator(interpolator);
        this.reboundAnimator.addListener(new AnimatorListenerAdapter() {
            public void onAnimationCancel(Animator animator) {
                super.onAnimationEnd(animator);
            }

            public void onAnimationEnd(Animator animator) {
                SmartRefreshLayout.this.reboundAnimator = null;
                if (SmartRefreshLayout.this.mSpinner == 0) {
                    if (SmartRefreshLayout.this.mState != RefreshState.None && !SmartRefreshLayout.this.mState.isOpening) {
                        SmartRefreshLayout.this.notifyStateChanged(RefreshState.None);
                    }
                } else if (SmartRefreshLayout.this.mState != SmartRefreshLayout.this.mViceState) {
                    SmartRefreshLayout.this.setViceState(SmartRefreshLayout.this.mState);
                }
            }
        });
        this.reboundAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                SmartRefreshLayout.this.mKernel.a(((Integer) valueAnimator.getAnimatedValue()).intValue(), false);
            }
        });
        this.reboundAnimator.setStartDelay((long) i2);
        this.reboundAnimator.start();
        return this.reboundAnimator;
    }

    /* access modifiers changed from: protected */
    public void animSpinnerBounce(float f) {
        if (this.reboundAnimator != null) {
            return;
        }
        if (f > 0.0f && (this.mState == RefreshState.Refreshing || this.mState == RefreshState.TwoLevel)) {
            this.animationRunnable = new BounceRunnable(f, this.mHeaderHeight);
        } else if (f < 0.0f && (this.mState == RefreshState.Loading || ((this.mEnableFooterFollowWhenLoadFinished && this.mFooterNoMoreData && isEnableLoadMore()) || (this.mEnableAutoLoadMore && !this.mFooterNoMoreData && isEnableLoadMore() && this.mState != RefreshState.Refreshing)))) {
            this.animationRunnable = new BounceRunnable(f, -this.mFooterHeight);
        } else if (this.mSpinner == 0 && this.mEnableOverScrollBounce) {
            this.animationRunnable = new BounceRunnable(f, 0);
        }
    }

    /* access modifiers changed from: protected */
    public void overSpinner() {
        if (this.mState == RefreshState.TwoLevel) {
            if (this.mCurrentVelocity > -1000 && this.mSpinner > getMeasuredHeight() / 2) {
                ValueAnimator a2 = this.mKernel.a(getMeasuredHeight());
                if (a2 != null) {
                    a2.setDuration((long) this.mFloorDuration);
                }
            } else if (this.mIsBeingDragged) {
                this.mKernel.c();
            }
        } else if (this.mState == RefreshState.Loading || (this.mEnableFooterFollowWhenLoadFinished && this.mFooterNoMoreData && this.mSpinner < 0 && isEnableLoadMore())) {
            if (this.mSpinner < (-this.mFooterHeight)) {
                this.mKernel.a(-this.mFooterHeight);
            } else if (this.mSpinner > 0) {
                this.mKernel.a(0);
            }
        } else if (this.mState == RefreshState.Refreshing) {
            if (this.mSpinner > this.mHeaderHeight) {
                this.mKernel.a(this.mHeaderHeight);
            } else if (this.mSpinner < 0) {
                this.mKernel.a(0);
            }
        } else if (this.mState == RefreshState.PullDownToRefresh) {
            this.mKernel.a(RefreshState.PullDownCanceled);
        } else if (this.mState == RefreshState.PullUpToLoad) {
            this.mKernel.a(RefreshState.PullUpCanceled);
        } else if (this.mState == RefreshState.ReleaseToRefresh) {
            setStateRefreshing();
        } else if (this.mState == RefreshState.ReleaseToLoad) {
            setStateLoading();
        } else if (this.mState == RefreshState.ReleaseToTwoLevel) {
            this.mKernel.a(RefreshState.TwoLevelReleased);
        } else if (this.mSpinner != 0) {
            this.mKernel.a(0);
        }
    }

    /* access modifiers changed from: protected */
    public void moveSpinnerInfinitely(float f) {
        float f2 = f;
        if (this.mState == RefreshState.TwoLevel && f2 > 0.0f) {
            this.mKernel.a(Math.min((int) f2, getMeasuredHeight()), true);
        } else if (this.mState != RefreshState.Refreshing || f2 < 0.0f) {
            if (f2 >= 0.0f || (this.mState != RefreshState.Loading && ((!this.mEnableFooterFollowWhenLoadFinished || !this.mFooterNoMoreData || !isEnableLoadMore()) && (!this.mEnableAutoLoadMore || this.mFooterNoMoreData || !isEnableLoadMore())))) {
                if (f2 >= 0.0f) {
                    double d = (double) (this.mHeaderMaxDragRate * ((float) this.mHeaderHeight));
                    double max = (double) Math.max(this.mScreenHeightPixels / 2, getHeight());
                    double max2 = (double) Math.max(0.0f, this.mDragRate * f2);
                    Double.isNaN(max2);
                    double d2 = -max2;
                    if (max == 0.0d) {
                        max = 1.0d;
                    }
                    Double.isNaN(d);
                    this.mKernel.a((int) Math.min(d * (1.0d - Math.pow(100.0d, d2 / max)), max2), true);
                } else {
                    double d3 = (double) (this.mFooterMaxDragRate * ((float) this.mFooterHeight));
                    double max3 = (double) Math.max(this.mScreenHeightPixels / 2, getHeight());
                    double d4 = (double) (-Math.min(0.0f, this.mDragRate * f2));
                    Double.isNaN(d4);
                    double d5 = -d4;
                    if (max3 == 0.0d) {
                        max3 = 1.0d;
                    }
                    Double.isNaN(d3);
                    this.mKernel.a((int) (-Math.min(d3 * (1.0d - Math.pow(100.0d, d5 / max3)), d4)), true);
                }
            } else if (f2 > ((float) (-this.mFooterHeight))) {
                this.mKernel.a((int) f2, true);
            } else {
                double d6 = (double) ((this.mFooterMaxDragRate - 1.0f) * ((float) this.mFooterHeight));
                double max4 = (double) (Math.max((this.mScreenHeightPixels * 4) / 3, getHeight()) - this.mFooterHeight);
                double d7 = (double) (-Math.min(0.0f, (((float) this.mFooterHeight) + f2) * this.mDragRate));
                Double.isNaN(d7);
                double d8 = -d7;
                if (max4 == 0.0d) {
                    max4 = 1.0d;
                }
                Double.isNaN(d6);
                this.mKernel.a(((int) (-Math.min(d6 * (1.0d - Math.pow(100.0d, d8 / max4)), d7))) - this.mFooterHeight, true);
            }
        } else if (f2 < ((float) this.mHeaderHeight)) {
            this.mKernel.a((int) f2, true);
        } else {
            double d9 = (double) ((this.mHeaderMaxDragRate - 1.0f) * ((float) this.mHeaderHeight));
            double max5 = (double) (Math.max((this.mScreenHeightPixels * 4) / 3, getHeight()) - this.mHeaderHeight);
            double max6 = (double) Math.max(0.0f, (f2 - ((float) this.mHeaderHeight)) * this.mDragRate);
            Double.isNaN(max6);
            double d10 = -max6;
            if (max5 == 0.0d) {
                max5 = 1.0d;
            }
            Double.isNaN(d9);
            this.mKernel.a(((int) Math.min(d9 * (1.0d - Math.pow(100.0d, d10 / max5)), max6)) + this.mHeaderHeight, true);
        }
        if (this.mEnableAutoLoadMore && !this.mFooterNoMoreData && isEnableLoadMore() && f2 < 0.0f && this.mState != RefreshState.Refreshing && this.mState != RefreshState.Loading && this.mState != RefreshState.LoadFinish) {
            setStateDirectLoading();
            if (this.mDisableContentWhenLoading) {
                this.animationRunnable = null;
                this.mKernel.a(-this.mFooterHeight);
            }
        }
    }

    /* access modifiers changed from: protected */
    public boolean checkLayoutParams(ViewGroup.LayoutParams layoutParams) {
        return layoutParams instanceof LayoutParams;
    }

    /* access modifiers changed from: protected */
    public LayoutParams generateDefaultLayoutParams() {
        return new LayoutParams(-1, -1);
    }

    /* access modifiers changed from: protected */
    public LayoutParams generateLayoutParams(ViewGroup.LayoutParams layoutParams) {
        return new LayoutParams(layoutParams);
    }

    public LayoutParams generateLayoutParams(AttributeSet attributeSet) {
        return new LayoutParams(getContext(), attributeSet);
    }

    public static class LayoutParams extends ViewGroup.MarginLayoutParams {

        /* renamed from: a  reason: collision with root package name */
        public int f8786a = 0;
        public SpinnerStyle b = null;

        public LayoutParams(Context context, AttributeSet attributeSet) {
            super(context, attributeSet);
            TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R.styleable.SmartRefreshLayout_Layout);
            this.f8786a = obtainStyledAttributes.getColor(R.styleable.SmartRefreshLayout_Layout_layout_srlBackgroundColor, this.f8786a);
            if (obtainStyledAttributes.hasValue(R.styleable.SmartRefreshLayout_Layout_layout_srlSpinnerStyle)) {
                this.b = SpinnerStyle.values()[obtainStyledAttributes.getInt(R.styleable.SmartRefreshLayout_Layout_layout_srlSpinnerStyle, SpinnerStyle.Translate.ordinal())];
            }
            obtainStyledAttributes.recycle();
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

    public int getNestedScrollAxes() {
        return this.mNestedParent.getNestedScrollAxes();
    }

    public boolean onStartNestedScroll(@NonNull View view, @NonNull View view2, int i) {
        if (!(isEnabled() && isNestedScrollingEnabled() && (i & 2) != 0) || (!this.mEnableOverScrollDrag && !isEnableRefresh() && !isEnableLoadMore())) {
            return false;
        }
        return true;
    }

    public void onNestedScrollAccepted(@NonNull View view, @NonNull View view2, int i) {
        this.mNestedParent.onNestedScrollAccepted(view, view2, i);
        this.mNestedChild.startNestedScroll(i & 2);
        this.mTotalUnconsumed = this.mSpinner;
        this.mNestedInProgress = true;
    }

    public void onNestedPreScroll(@NonNull View view, int i, int i2, @NonNull int[] iArr) {
        int i3;
        if (this.mTotalUnconsumed * i2 > 0) {
            if (Math.abs(i2) > Math.abs(this.mTotalUnconsumed)) {
                i3 = this.mTotalUnconsumed;
                this.mTotalUnconsumed = 0;
            } else {
                this.mTotalUnconsumed -= i2;
                i3 = i2;
            }
            moveSpinnerInfinitely((float) this.mTotalUnconsumed);
            if (this.mViceState.isOpening || this.mViceState == RefreshState.None) {
                if (this.mSpinner > 0) {
                    this.mKernel.a(RefreshState.PullDownToRefresh);
                } else {
                    this.mKernel.a(RefreshState.PullUpToLoad);
                }
            }
        } else if (i2 <= 0 || !this.mFooterLocked) {
            i3 = 0;
        } else {
            this.mTotalUnconsumed -= i2;
            moveSpinnerInfinitely((float) this.mTotalUnconsumed);
            i3 = i2;
        }
        this.mNestedChild.dispatchNestedPreScroll(i, i2 - i3, iArr, (int[]) null);
        iArr[1] = iArr[1] + i3;
    }

    public void onNestedScroll(@NonNull View view, int i, int i2, int i3, int i4) {
        this.mNestedChild.dispatchNestedScroll(i, i2, i3, i4, this.mParentOffsetInWindow);
        int i5 = i4 + this.mParentOffsetInWindow[1];
        if (i5 == 0) {
            return;
        }
        if (this.mEnableOverScrollDrag || ((i5 < 0 && isEnableRefresh()) || (i5 > 0 && isEnableLoadMore()))) {
            if (this.mViceState == RefreshState.None) {
                this.mKernel.a(i5 > 0 ? RefreshState.PullUpToLoad : RefreshState.PullDownToRefresh);
            }
            int i6 = this.mTotalUnconsumed - i5;
            this.mTotalUnconsumed = i6;
            moveSpinnerInfinitely((float) i6);
        }
    }

    public boolean onNestedPreFling(@NonNull View view, float f, float f2) {
        return (this.mFooterLocked && f2 > 0.0f) || startFlingIfNeed(Float.valueOf(-f2)) || this.mNestedChild.dispatchNestedPreFling(f, f2);
    }

    public boolean onNestedFling(@NonNull View view, float f, float f2, boolean z) {
        return this.mNestedChild.dispatchNestedFling(f, f2, z);
    }

    public void onStopNestedScroll(@NonNull View view) {
        this.mNestedParent.onStopNestedScroll(view);
        this.mNestedInProgress = false;
        this.mTotalUnconsumed = 0;
        overSpinner();
        this.mNestedChild.stopNestedScroll();
    }

    public void setNestedScrollingEnabled(boolean z) {
        this.mManualNestedScrolling = true;
        this.mNestedChild.setNestedScrollingEnabled(z);
    }

    public boolean isNestedScrollingEnabled() {
        return this.mNestedChild.isNestedScrollingEnabled();
    }

    public boolean canScrollVertically(int i) {
        View b = this.mRefreshContent.b();
        if (i < 0) {
            if (this.mEnableOverScrollDrag || isEnableRefresh() || ScrollBoundaryUtil.a(b)) {
                return true;
            }
            return false;
        } else if (i <= 0 || this.mEnableOverScrollDrag || isEnableLoadMore() || ScrollBoundaryUtil.b(b)) {
            return true;
        } else {
            return false;
        }
    }

    public SmartRefreshLayout setFooterHeight(float f) {
        if (this.mFooterHeightStatus.canReplaceWith(DimensionStatus.CodeExact)) {
            this.mFooterHeight = DensityUtil.a(f);
            this.mFooterHeightStatus = DimensionStatus.CodeExactUnNotify;
            if (this.mRefreshFooter != null) {
                this.mRefreshFooter.getView().requestLayout();
            }
        }
        return this;
    }

    public SmartRefreshLayout setHeaderHeight(float f) {
        if (this.mHeaderHeightStatus.canReplaceWith(DimensionStatus.CodeExact)) {
            this.mHeaderHeight = DensityUtil.a(f);
            this.mHeaderHeightStatus = DimensionStatus.CodeExactUnNotify;
            if (this.mRefreshHeader != null) {
                this.mRefreshHeader.getView().requestLayout();
            }
        }
        return this;
    }

    public SmartRefreshLayout setHeaderInsetStart(float f) {
        this.mHeaderInsetStart = DensityUtil.a(f);
        return this;
    }

    public SmartRefreshLayout setFooterInsetStart(float f) {
        this.mFooterInsetStart = DensityUtil.a(f);
        return this;
    }

    public SmartRefreshLayout setDragRate(float f) {
        this.mDragRate = f;
        return this;
    }

    public SmartRefreshLayout setHeaderMaxDragRate(float f) {
        this.mHeaderMaxDragRate = f;
        if (this.mRefreshHeader == null || this.mHandler == null) {
            this.mHeaderHeightStatus = this.mHeaderHeightStatus.unNotify();
        } else {
            this.mRefreshHeader.onInitialized(this.mKernel, this.mHeaderHeight, (int) (this.mHeaderMaxDragRate * ((float) this.mHeaderHeight)));
        }
        return this;
    }

    public SmartRefreshLayout setFooterMaxDragRate(float f) {
        this.mFooterMaxDragRate = f;
        if (this.mRefreshFooter == null || this.mHandler == null) {
            this.mFooterHeightStatus = this.mFooterHeightStatus.unNotify();
        } else {
            this.mRefreshFooter.onInitialized(this.mKernel, this.mFooterHeight, (int) (((float) this.mFooterHeight) * this.mFooterMaxDragRate));
        }
        return this;
    }

    public SmartRefreshLayout setHeaderTriggerRate(float f) {
        this.mHeaderTriggerRate = f;
        return this;
    }

    public SmartRefreshLayout setFooterTriggerRate(float f) {
        this.mFooterTriggerRate = f;
        return this;
    }

    public SmartRefreshLayout setReboundInterpolator(@NonNull Interpolator interpolator) {
        this.mReboundInterpolator = interpolator;
        return this;
    }

    public SmartRefreshLayout setReboundDuration(int i) {
        this.mReboundDuration = i;
        return this;
    }

    public SmartRefreshLayout setEnableLoadMore(boolean z) {
        this.mManualLoadMore = true;
        this.mEnableLoadMore = z;
        return this;
    }

    public SmartRefreshLayout setEnableRefresh(boolean z) {
        this.mEnableRefresh = z;
        return this;
    }

    public SmartRefreshLayout setEnableHeaderTranslationContent(boolean z) {
        this.mEnableHeaderTranslationContent = z;
        this.mManualHeaderTranslationContent = true;
        return this;
    }

    public SmartRefreshLayout setEnableFooterTranslationContent(boolean z) {
        this.mEnableFooterTranslationContent = z;
        return this;
    }

    public SmartRefreshLayout setEnableAutoLoadMore(boolean z) {
        this.mEnableAutoLoadMore = z;
        return this;
    }

    public SmartRefreshLayout setEnableOverScrollBounce(boolean z) {
        this.mEnableOverScrollBounce = z;
        return this;
    }

    public SmartRefreshLayout setEnablePureScrollMode(boolean z) {
        this.mEnablePureScrollMode = z;
        return this;
    }

    public SmartRefreshLayout setEnableScrollContentWhenLoaded(boolean z) {
        this.mEnableScrollContentWhenLoaded = z;
        return this;
    }

    public SmartRefreshLayout setEnableScrollContentWhenRefreshed(boolean z) {
        this.mEnableScrollContentWhenRefreshed = z;
        return this;
    }

    public SmartRefreshLayout setEnableLoadMoreWhenContentNotFull(boolean z) {
        this.mEnableLoadMoreWhenContentNotFull = z;
        if (this.mRefreshContent != null) {
            this.mRefreshContent.a(z);
        }
        return this;
    }

    public SmartRefreshLayout setEnableOverScrollDrag(boolean z) {
        this.mEnableOverScrollDrag = z;
        return this;
    }

    public SmartRefreshLayout setEnableFooterFollowWhenLoadFinished(boolean z) {
        this.mEnableFooterFollowWhenLoadFinished = z;
        return this;
    }

    public SmartRefreshLayout setEnableClipHeaderWhenFixedBehind(boolean z) {
        this.mEnableClipHeaderWhenFixedBehind = z;
        return this;
    }

    public SmartRefreshLayout setEnableClipFooterWhenFixedBehind(boolean z) {
        this.mEnableClipFooterWhenFixedBehind = z;
        return this;
    }

    public RefreshLayout setEnableNestedScroll(boolean z) {
        setNestedScrollingEnabled(z);
        return this;
    }

    public SmartRefreshLayout setDisableContentWhenRefresh(boolean z) {
        this.mDisableContentWhenRefresh = z;
        return this;
    }

    public SmartRefreshLayout setDisableContentWhenLoading(boolean z) {
        this.mDisableContentWhenLoading = z;
        return this;
    }

    public SmartRefreshLayout setRefreshHeader(@NonNull RefreshHeader refreshHeader) {
        return setRefreshHeader(refreshHeader, -1, -2);
    }

    public SmartRefreshLayout setRefreshHeader(@NonNull RefreshHeader refreshHeader, int i, int i2) {
        if (this.mRefreshHeader != null) {
            super.removeView(this.mRefreshHeader.getView());
        }
        this.mRefreshHeader = refreshHeader;
        this.mHeaderBackgroundColor = 0;
        this.mHeaderNeedTouchEventWhenRefreshing = false;
        this.mHeaderHeightStatus = this.mHeaderHeightStatus.unNotify();
        if (refreshHeader.getSpinnerStyle() == SpinnerStyle.FixedBehind) {
            super.addView(this.mRefreshHeader.getView(), 0, new LayoutParams(i, i2));
        } else {
            super.addView(this.mRefreshHeader.getView(), i, i2);
        }
        return this;
    }

    public SmartRefreshLayout setRefreshFooter(@NonNull RefreshFooter refreshFooter) {
        return setRefreshFooter(refreshFooter, -1, -2);
    }

    public SmartRefreshLayout setRefreshFooter(@NonNull RefreshFooter refreshFooter, int i, int i2) {
        if (this.mRefreshFooter != null) {
            super.removeView(this.mRefreshFooter.getView());
        }
        this.mRefreshFooter = refreshFooter;
        this.mFooterBackgroundColor = 0;
        this.mFooterNeedTouchEventWhenLoading = false;
        this.mFooterHeightStatus = this.mFooterHeightStatus.unNotify();
        this.mEnableLoadMore = !this.mManualLoadMore || this.mEnableLoadMore;
        if (this.mRefreshFooter.getSpinnerStyle() == SpinnerStyle.FixedBehind) {
            super.addView(this.mRefreshFooter.getView(), 0, new LayoutParams(i, i2));
        } else {
            super.addView(this.mRefreshFooter.getView(), i, i2);
        }
        return this;
    }

    public RefreshLayout setRefreshContent(@NonNull View view) {
        return setRefreshContent(view, -1, -1);
    }

    public RefreshLayout setRefreshContent(@NonNull View view, int i, int i2) {
        if (this.mRefreshContent != null) {
            super.removeView(this.mRefreshContent.a());
        }
        super.addView(view, 0, new LayoutParams(i, i2));
        if (this.mRefreshHeader != null && this.mRefreshHeader.getSpinnerStyle() == SpinnerStyle.FixedBehind) {
            super.bringChildToFront(view);
            if (!(this.mRefreshFooter == null || this.mRefreshFooter.getSpinnerStyle() == SpinnerStyle.FixedBehind)) {
                super.bringChildToFront(this.mRefreshFooter.getView());
            }
        } else if (this.mRefreshFooter != null && this.mRefreshFooter.getSpinnerStyle() == SpinnerStyle.FixedBehind) {
            super.bringChildToFront(view);
            if (this.mRefreshHeader != null && this.mRefreshHeader.getSpinnerStyle() == SpinnerStyle.FixedBehind) {
                super.bringChildToFront(this.mRefreshHeader.getView());
            }
        }
        this.mRefreshContent = new RefreshContentWrapper(view);
        if (this.mHandler != null) {
            View view2 = null;
            View findViewById = this.mFixedHeaderViewId > 0 ? findViewById(this.mFixedHeaderViewId) : null;
            if (this.mFixedFooterViewId > 0) {
                view2 = findViewById(this.mFixedFooterViewId);
            }
            this.mRefreshContent.a(this.mScrollBoundaryDecider);
            this.mRefreshContent.a(this.mEnableLoadMoreWhenContentNotFull);
            this.mRefreshContent.a(this.mKernel, findViewById, view2);
        }
        return this;
    }

    @Nullable
    public RefreshFooter getRefreshFooter() {
        if (this.mRefreshFooter instanceof RefreshFooter) {
            return (RefreshFooter) this.mRefreshFooter;
        }
        return null;
    }

    @Nullable
    public RefreshHeader getRefreshHeader() {
        if (this.mRefreshHeader instanceof RefreshHeader) {
            return (RefreshHeader) this.mRefreshHeader;
        }
        return null;
    }

    public RefreshState getState() {
        return this.mState;
    }

    public SmartRefreshLayout setOnRefreshListener(OnRefreshListener onRefreshListener) {
        this.mRefreshListener = onRefreshListener;
        return this;
    }

    public SmartRefreshLayout setOnLoadMoreListener(OnLoadMoreListener onLoadMoreListener) {
        this.mLoadMoreListener = onLoadMoreListener;
        this.mEnableLoadMore = this.mEnableLoadMore || (!this.mManualLoadMore && onLoadMoreListener != null);
        return this;
    }

    public SmartRefreshLayout setOnRefreshLoadMoreListener(OnRefreshLoadMoreListener onRefreshLoadMoreListener) {
        this.mRefreshListener = onRefreshLoadMoreListener;
        this.mLoadMoreListener = onRefreshLoadMoreListener;
        this.mEnableLoadMore = this.mEnableLoadMore || (!this.mManualLoadMore && onRefreshLoadMoreListener != null);
        return this;
    }

    public SmartRefreshLayout setOnMultiPurposeListener(OnMultiPurposeListener onMultiPurposeListener) {
        this.mOnMultiPurposeListener = onMultiPurposeListener;
        return this;
    }

    public SmartRefreshLayout setPrimaryColors(@ColorInt int... iArr) {
        if (this.mRefreshHeader != null) {
            this.mRefreshHeader.setPrimaryColors(iArr);
        }
        if (this.mRefreshFooter != null) {
            this.mRefreshFooter.setPrimaryColors(iArr);
        }
        this.mPrimaryColors = iArr;
        return this;
    }

    public SmartRefreshLayout setPrimaryColorsId(@ColorRes int... iArr) {
        int[] iArr2 = new int[iArr.length];
        for (int i = 0; i < iArr.length; i++) {
            iArr2[i] = SmartUtil.a(getContext(), iArr[i]);
        }
        setPrimaryColors(iArr2);
        return this;
    }

    public SmartRefreshLayout setScrollBoundaryDecider(ScrollBoundaryDecider scrollBoundaryDecider) {
        this.mScrollBoundaryDecider = scrollBoundaryDecider;
        if (this.mRefreshContent != null) {
            this.mRefreshContent.a(scrollBoundaryDecider);
        }
        return this;
    }

    public SmartRefreshLayout setNoMoreData(boolean z) {
        this.mFooterNoMoreData = z;
        if ((this.mRefreshFooter instanceof RefreshFooter) && !((RefreshFooter) this.mRefreshFooter).setNoMoreData(z)) {
            PrintStream printStream = System.out;
            printStream.println("Footer:" + this.mRefreshFooter + " Prompt completion is not supported.(不支持提示完成)");
        }
        return this;
    }

    public SmartRefreshLayout finishRefresh() {
        return finishRefresh(Math.max(0, 300 - ((int) (System.currentTimeMillis() - this.mLastOpenTime))));
    }

    public SmartRefreshLayout finishLoadMore() {
        return finishLoadMore(Math.max(0, 300 - ((int) (System.currentTimeMillis() - this.mLastOpenTime))));
    }

    public SmartRefreshLayout finishRefresh(int i) {
        return finishRefresh(i, true);
    }

    public SmartRefreshLayout finishRefresh(boolean z) {
        long currentTimeMillis = System.currentTimeMillis() - this.mLastOpenTime;
        int i = 0;
        if (z) {
            i = Math.max(0, 300 - ((int) currentTimeMillis));
        }
        return finishRefresh(i, z);
    }

    public SmartRefreshLayout finishRefresh(int i, final boolean z) {
        postDelayed(new Runnable() {
            public void run() {
                if (SmartRefreshLayout.this.mState == RefreshState.Refreshing && SmartRefreshLayout.this.mRefreshHeader != null && SmartRefreshLayout.this.mRefreshContent != null) {
                    SmartRefreshLayout.this.notifyStateChanged(RefreshState.RefreshFinish);
                    int onFinish = SmartRefreshLayout.this.mRefreshHeader.onFinish(SmartRefreshLayout.this, z);
                    if (SmartRefreshLayout.this.mOnMultiPurposeListener != null && (SmartRefreshLayout.this.mRefreshHeader instanceof RefreshHeader)) {
                        SmartRefreshLayout.this.mOnMultiPurposeListener.a((RefreshHeader) SmartRefreshLayout.this.mRefreshHeader, z);
                    }
                    if (onFinish < Integer.MAX_VALUE) {
                        if (SmartRefreshLayout.this.mIsBeingDragged || SmartRefreshLayout.this.mNestedInProgress) {
                            if (SmartRefreshLayout.this.mIsBeingDragged) {
                                SmartRefreshLayout.this.mTouchY = SmartRefreshLayout.this.mLastTouchY;
                                SmartRefreshLayout.this.mTouchSpinner = 0;
                                SmartRefreshLayout.this.mIsBeingDragged = false;
                            }
                            long currentTimeMillis = System.currentTimeMillis();
                            long j = currentTimeMillis;
                            boolean unused = SmartRefreshLayout.super.dispatchTouchEvent(MotionEvent.obtain(currentTimeMillis, j, 0, SmartRefreshLayout.this.mLastTouchX, (SmartRefreshLayout.this.mLastTouchY + ((float) SmartRefreshLayout.this.mSpinner)) - ((float) (SmartRefreshLayout.this.mTouchSlop * 2)), 0));
                            boolean unused2 = SmartRefreshLayout.super.dispatchTouchEvent(MotionEvent.obtain(currentTimeMillis, j, 2, SmartRefreshLayout.this.mLastTouchX, SmartRefreshLayout.this.mLastTouchY + ((float) SmartRefreshLayout.this.mSpinner), 0));
                            if (SmartRefreshLayout.this.mNestedInProgress) {
                                SmartRefreshLayout.this.mTotalUnconsumed = 0;
                            }
                        }
                        if (SmartRefreshLayout.this.mSpinner > 0) {
                            ValueAnimator.AnimatorUpdateListener animatorUpdateListener = null;
                            ValueAnimator animSpinner = SmartRefreshLayout.this.animSpinner(0, onFinish, SmartRefreshLayout.this.mReboundInterpolator, SmartRefreshLayout.this.mReboundDuration);
                            if (SmartRefreshLayout.this.mEnableScrollContentWhenRefreshed) {
                                animatorUpdateListener = SmartRefreshLayout.this.mRefreshContent.a(SmartRefreshLayout.this.mSpinner);
                            }
                            if (animSpinner != null && animatorUpdateListener != null) {
                                animSpinner.addUpdateListener(animatorUpdateListener);
                            }
                        } else if (SmartRefreshLayout.this.mSpinner < 0) {
                            SmartRefreshLayout.this.animSpinner(0, onFinish, SmartRefreshLayout.this.mReboundInterpolator, SmartRefreshLayout.this.mReboundDuration);
                        } else {
                            SmartRefreshLayout.this.mKernel.a(0, false);
                            SmartRefreshLayout.this.resetStatus();
                        }
                    }
                }
            }
        }, i <= 0 ? 1 : (long) i);
        return this;
    }

    public SmartRefreshLayout finishLoadMore(int i) {
        return finishLoadMore(i, true, false);
    }

    public SmartRefreshLayout finishLoadMore(boolean z) {
        return finishLoadMore(z ? Math.max(0, 300 - ((int) (System.currentTimeMillis() - this.mLastOpenTime))) : 0, z, false);
    }

    public SmartRefreshLayout finishLoadMore(int i, final boolean z, final boolean z2) {
        postDelayed(new Runnable() {
            public void run() {
                boolean z = true;
                if (SmartRefreshLayout.this.mState == RefreshState.Loading && SmartRefreshLayout.this.mRefreshFooter != null && SmartRefreshLayout.this.mRefreshContent != null) {
                    SmartRefreshLayout.this.notifyStateChanged(RefreshState.LoadFinish);
                    int onFinish = SmartRefreshLayout.this.mRefreshFooter.onFinish(SmartRefreshLayout.this, z);
                    if (SmartRefreshLayout.this.mOnMultiPurposeListener != null && (SmartRefreshLayout.this.mRefreshFooter instanceof RefreshFooter)) {
                        SmartRefreshLayout.this.mOnMultiPurposeListener.a((RefreshFooter) SmartRefreshLayout.this.mRefreshFooter, z);
                    }
                    if (onFinish < Integer.MAX_VALUE) {
                        if (!z2 || !SmartRefreshLayout.this.mEnableFooterFollowWhenLoadFinished || SmartRefreshLayout.this.mSpinner >= 0 || !SmartRefreshLayout.this.mRefreshContent.d()) {
                            z = false;
                        }
                        final int max = SmartRefreshLayout.this.mSpinner - (z ? Math.max(SmartRefreshLayout.this.mSpinner, -SmartRefreshLayout.this.mFooterHeight) : 0);
                        if (SmartRefreshLayout.this.mIsBeingDragged || SmartRefreshLayout.this.mNestedInProgress) {
                            if (SmartRefreshLayout.this.mIsBeingDragged) {
                                SmartRefreshLayout.this.mTouchY = SmartRefreshLayout.this.mLastTouchY;
                                SmartRefreshLayout.this.mIsBeingDragged = false;
                                SmartRefreshLayout.this.mTouchSpinner = SmartRefreshLayout.this.mSpinner - max;
                            }
                            long currentTimeMillis = System.currentTimeMillis();
                            float f = (float) max;
                            long j = currentTimeMillis;
                            long j2 = currentTimeMillis;
                            boolean unused = SmartRefreshLayout.super.dispatchTouchEvent(MotionEvent.obtain(j, j2, 0, SmartRefreshLayout.this.mLastTouchX, SmartRefreshLayout.this.mLastTouchY + f + ((float) (SmartRefreshLayout.this.mTouchSlop * 2)), 0));
                            boolean unused2 = SmartRefreshLayout.super.dispatchTouchEvent(MotionEvent.obtain(j, j2, 2, SmartRefreshLayout.this.mLastTouchX, SmartRefreshLayout.this.mLastTouchY + f, 0));
                            if (SmartRefreshLayout.this.mNestedInProgress) {
                                SmartRefreshLayout.this.mTotalUnconsumed = 0;
                            }
                        }
                        SmartRefreshLayout.this.postDelayed(new Runnable() {
                            public void run() {
                                ValueAnimator valueAnimator;
                                ValueAnimator.AnimatorUpdateListener a2 = (!SmartRefreshLayout.this.mEnableScrollContentWhenLoaded || max >= 0) ? null : SmartRefreshLayout.this.mRefreshContent.a(SmartRefreshLayout.this.mSpinner);
                                if (a2 != null) {
                                    a2.onAnimationUpdate(ValueAnimator.ofInt(new int[]{0, 0}));
                                }
                                AnonymousClass1 r2 = new AnimatorListenerAdapter() {
                                    public void onAnimationCancel(Animator animator) {
                                        super.onAnimationEnd(animator);
                                    }

                                    public void onAnimationEnd(Animator animator) {
                                        SmartRefreshLayout.this.mFooterLocked = false;
                                        if (z2) {
                                            SmartRefreshLayout.this.setNoMoreData(true);
                                        }
                                        if (SmartRefreshLayout.this.mState == RefreshState.LoadFinish) {
                                            SmartRefreshLayout.this.notifyStateChanged(RefreshState.None);
                                        }
                                    }
                                };
                                if (SmartRefreshLayout.this.mSpinner > 0) {
                                    valueAnimator = SmartRefreshLayout.this.mKernel.a(0);
                                } else {
                                    if (a2 != null || SmartRefreshLayout.this.mSpinner == 0) {
                                        if (SmartRefreshLayout.this.reboundAnimator != null) {
                                            SmartRefreshLayout.this.reboundAnimator.cancel();
                                            SmartRefreshLayout.this.reboundAnimator = null;
                                        }
                                        SmartRefreshLayout.this.mKernel.a(0, false);
                                        SmartRefreshLayout.this.resetStatus();
                                    } else if (!z2 || !SmartRefreshLayout.this.mEnableFooterFollowWhenLoadFinished) {
                                        valueAnimator = SmartRefreshLayout.this.mKernel.a(0);
                                    } else if (SmartRefreshLayout.this.mSpinner >= (-SmartRefreshLayout.this.mFooterHeight)) {
                                        SmartRefreshLayout.this.notifyStateChanged(RefreshState.None);
                                    } else {
                                        valueAnimator = SmartRefreshLayout.this.mKernel.a(-SmartRefreshLayout.this.mFooterHeight);
                                    }
                                    valueAnimator = null;
                                }
                                if (valueAnimator != null) {
                                    valueAnimator.addListener(r2);
                                } else {
                                    r2.onAnimationEnd((Animator) null);
                                }
                            }
                        }, SmartRefreshLayout.this.mSpinner < 0 ? (long) onFinish : 0);
                    }
                } else if (z2) {
                    SmartRefreshLayout.this.setNoMoreData(true);
                }
            }
        }, i <= 0 ? 1 : (long) i);
        return this;
    }

    public SmartRefreshLayout finishLoadMoreWithNoMoreData() {
        return finishLoadMore(Math.max(0, 300 - ((int) (System.currentTimeMillis() - this.mLastOpenTime))), true, true);
    }

    public boolean autoRefresh() {
        return autoRefresh(this.mHandler == null ? 400 : 0, this.mReboundDuration, ((((this.mHeaderMaxDragRate / 2.0f) + 0.5f) * ((float) this.mHeaderHeight)) * 1.0f) / ((float) (this.mHeaderHeight == 0 ? 1 : this.mHeaderHeight)));
    }

    public boolean autoRefresh(int i, final int i2, final float f) {
        if (this.mState != RefreshState.None || !isEnableRefresh()) {
            return false;
        }
        if (this.reboundAnimator != null) {
            this.reboundAnimator.cancel();
        }
        AnonymousClass10 r0 = new Runnable() {
            public void run() {
                SmartRefreshLayout.this.reboundAnimator = ValueAnimator.ofInt(new int[]{SmartRefreshLayout.this.mSpinner, (int) (((float) SmartRefreshLayout.this.mHeaderHeight) * f)});
                SmartRefreshLayout.this.reboundAnimator.setDuration((long) i2);
                SmartRefreshLayout.this.reboundAnimator.setInterpolator(new DecelerateInterpolator());
                SmartRefreshLayout.this.reboundAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                    public void onAnimationUpdate(ValueAnimator valueAnimator) {
                        SmartRefreshLayout.this.mKernel.a(((Integer) valueAnimator.getAnimatedValue()).intValue(), true);
                    }
                });
                SmartRefreshLayout.this.reboundAnimator.addListener(new AnimatorListenerAdapter() {
                    public void onAnimationStart(Animator animator) {
                        SmartRefreshLayout smartRefreshLayout = SmartRefreshLayout.this;
                        SmartRefreshLayout.this.mLastTouchX = (float) (smartRefreshLayout.getMeasuredWidth() / 2);
                        SmartRefreshLayout.this.mKernel.a(RefreshState.PullDownToRefresh);
                    }

                    public void onAnimationEnd(Animator animator) {
                        SmartRefreshLayout.this.reboundAnimator = null;
                        if (SmartRefreshLayout.this.mState != RefreshState.ReleaseToRefresh) {
                            SmartRefreshLayout.this.mKernel.a(RefreshState.ReleaseToRefresh);
                        }
                        SmartRefreshLayout.this.overSpinner();
                    }
                });
                SmartRefreshLayout.this.reboundAnimator.start();
            }
        };
        if (i > 0) {
            this.reboundAnimator = new ValueAnimator();
            postDelayed(r0, (long) i);
            return true;
        }
        r0.run();
        return true;
    }

    public boolean autoLoadMore() {
        return autoLoadMore(0, this.mReboundDuration, ((((float) this.mFooterHeight) * ((this.mFooterMaxDragRate / 2.0f) + 0.5f)) * 1.0f) / ((float) (this.mFooterHeight == 0 ? 1 : this.mFooterHeight)));
    }

    public boolean autoLoadMore(int i, final int i2, final float f) {
        if (this.mState != RefreshState.None || !isEnableLoadMore() || this.mFooterNoMoreData) {
            return false;
        }
        if (this.reboundAnimator != null) {
            this.reboundAnimator.cancel();
        }
        AnonymousClass11 r0 = new Runnable() {
            public void run() {
                SmartRefreshLayout.this.reboundAnimator = ValueAnimator.ofInt(new int[]{SmartRefreshLayout.this.mSpinner, -((int) (((float) SmartRefreshLayout.this.mFooterHeight) * f))});
                SmartRefreshLayout.this.reboundAnimator.setDuration((long) i2);
                SmartRefreshLayout.this.reboundAnimator.setInterpolator(new DecelerateInterpolator());
                SmartRefreshLayout.this.reboundAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                    public void onAnimationUpdate(ValueAnimator valueAnimator) {
                        SmartRefreshLayout.this.mKernel.a(((Integer) valueAnimator.getAnimatedValue()).intValue(), true);
                    }
                });
                SmartRefreshLayout.this.reboundAnimator.addListener(new AnimatorListenerAdapter() {
                    public void onAnimationStart(Animator animator) {
                        SmartRefreshLayout smartRefreshLayout = SmartRefreshLayout.this;
                        SmartRefreshLayout.this.mLastTouchX = (float) (smartRefreshLayout.getMeasuredWidth() / 2);
                        SmartRefreshLayout.this.mKernel.a(RefreshState.PullUpToLoad);
                    }

                    public void onAnimationEnd(Animator animator) {
                        SmartRefreshLayout.this.reboundAnimator = null;
                        if (SmartRefreshLayout.this.mState != RefreshState.ReleaseToLoad) {
                            SmartRefreshLayout.this.mKernel.a(RefreshState.ReleaseToLoad);
                        }
                        if (SmartRefreshLayout.this.mEnableAutoLoadMore) {
                            SmartRefreshLayout.this.mEnableAutoLoadMore = false;
                            SmartRefreshLayout.this.overSpinner();
                            SmartRefreshLayout.this.mEnableAutoLoadMore = true;
                            return;
                        }
                        SmartRefreshLayout.this.overSpinner();
                    }
                });
                SmartRefreshLayout.this.reboundAnimator.start();
            }
        };
        if (i > 0) {
            this.reboundAnimator = new ValueAnimator();
            postDelayed(r0, (long) i);
            return true;
        }
        r0.run();
        return true;
    }

    public boolean isEnableRefresh() {
        return this.mEnableRefresh && !this.mEnablePureScrollMode;
    }

    public boolean isEnableLoadMore() {
        return this.mEnableLoadMore && !this.mEnablePureScrollMode;
    }

    public static void setDefaultRefreshHeaderCreator(@NonNull DefaultRefreshHeaderCreator defaultRefreshHeaderCreator) {
        sHeaderCreator = defaultRefreshHeaderCreator;
    }

    public static void setDefaultRefreshFooterCreator(@NonNull DefaultRefreshFooterCreator defaultRefreshFooterCreator) {
        sFooterCreator = defaultRefreshFooterCreator;
    }

    public static void setDefaultRefreshInitializer(@NonNull DefaultRefreshInitializer defaultRefreshInitializer) {
        sRefreshInitializer = defaultRefreshInitializer;
    }

    public class RefreshKernelImpl implements RefreshKernel {
        public RefreshKernelImpl() {
        }

        @NonNull
        public RefreshLayout a() {
            return SmartRefreshLayout.this;
        }

        @NonNull
        public RefreshContent b() {
            return SmartRefreshLayout.this.mRefreshContent;
        }

        public RefreshKernel a(@NonNull RefreshState refreshState) {
            switch (refreshState) {
                case None:
                    SmartRefreshLayout.this.resetStatus();
                    return null;
                case PullDownToRefresh:
                    if (SmartRefreshLayout.this.mState.isOpening || !SmartRefreshLayout.this.isEnableRefresh()) {
                        SmartRefreshLayout.this.setViceState(RefreshState.PullDownToRefresh);
                        return null;
                    }
                    SmartRefreshLayout.this.notifyStateChanged(RefreshState.PullDownToRefresh);
                    return null;
                case PullUpToLoad:
                    if (!SmartRefreshLayout.this.isEnableLoadMore() || SmartRefreshLayout.this.mState.isOpening || SmartRefreshLayout.this.mState.isFinishing || (SmartRefreshLayout.this.mFooterNoMoreData && SmartRefreshLayout.this.mEnableFooterFollowWhenLoadFinished)) {
                        SmartRefreshLayout.this.setViceState(RefreshState.PullUpToLoad);
                        return null;
                    }
                    SmartRefreshLayout.this.notifyStateChanged(RefreshState.PullUpToLoad);
                    return null;
                case PullDownCanceled:
                    if (SmartRefreshLayout.this.mState.isOpening || !SmartRefreshLayout.this.isEnableRefresh()) {
                        SmartRefreshLayout.this.setViceState(RefreshState.PullDownCanceled);
                        return null;
                    }
                    SmartRefreshLayout.this.notifyStateChanged(RefreshState.PullDownCanceled);
                    SmartRefreshLayout.this.resetStatus();
                    return null;
                case PullUpCanceled:
                    if (!SmartRefreshLayout.this.isEnableLoadMore() || SmartRefreshLayout.this.mState.isOpening || (SmartRefreshLayout.this.mFooterNoMoreData && SmartRefreshLayout.this.mEnableFooterFollowWhenLoadFinished)) {
                        SmartRefreshLayout.this.setViceState(RefreshState.PullUpCanceled);
                        return null;
                    }
                    SmartRefreshLayout.this.notifyStateChanged(RefreshState.PullUpCanceled);
                    SmartRefreshLayout.this.resetStatus();
                    return null;
                case ReleaseToRefresh:
                    if (SmartRefreshLayout.this.mState.isOpening || !SmartRefreshLayout.this.isEnableRefresh()) {
                        SmartRefreshLayout.this.setViceState(RefreshState.ReleaseToRefresh);
                        return null;
                    }
                    SmartRefreshLayout.this.notifyStateChanged(RefreshState.ReleaseToRefresh);
                    return null;
                case ReleaseToLoad:
                    if (!SmartRefreshLayout.this.isEnableLoadMore() || SmartRefreshLayout.this.mState.isOpening || SmartRefreshLayout.this.mState.isFinishing || (SmartRefreshLayout.this.mFooterNoMoreData && SmartRefreshLayout.this.mEnableFooterFollowWhenLoadFinished)) {
                        SmartRefreshLayout.this.setViceState(RefreshState.ReleaseToLoad);
                        return null;
                    }
                    SmartRefreshLayout.this.notifyStateChanged(RefreshState.ReleaseToLoad);
                    return null;
                case ReleaseToTwoLevel:
                    if (SmartRefreshLayout.this.mState.isOpening || !SmartRefreshLayout.this.isEnableRefresh()) {
                        SmartRefreshLayout.this.setViceState(RefreshState.ReleaseToTwoLevel);
                        return null;
                    }
                    SmartRefreshLayout.this.notifyStateChanged(RefreshState.ReleaseToTwoLevel);
                    return null;
                case RefreshReleased:
                    if (SmartRefreshLayout.this.mState.isOpening || !SmartRefreshLayout.this.isEnableRefresh()) {
                        SmartRefreshLayout.this.setViceState(RefreshState.RefreshReleased);
                        return null;
                    }
                    SmartRefreshLayout.this.notifyStateChanged(RefreshState.RefreshReleased);
                    return null;
                case LoadReleased:
                    if (SmartRefreshLayout.this.mState.isOpening || !SmartRefreshLayout.this.isEnableLoadMore()) {
                        SmartRefreshLayout.this.setViceState(RefreshState.LoadReleased);
                        return null;
                    }
                    SmartRefreshLayout.this.notifyStateChanged(RefreshState.LoadReleased);
                    return null;
                case Refreshing:
                    SmartRefreshLayout.this.setStateRefreshing();
                    return null;
                case Loading:
                    SmartRefreshLayout.this.setStateLoading();
                    return null;
                case RefreshFinish:
                    if (SmartRefreshLayout.this.mState != RefreshState.Refreshing) {
                        return null;
                    }
                    SmartRefreshLayout.this.notifyStateChanged(RefreshState.RefreshFinish);
                    return null;
                case LoadFinish:
                    if (SmartRefreshLayout.this.mState != RefreshState.Loading) {
                        return null;
                    }
                    SmartRefreshLayout.this.notifyStateChanged(RefreshState.LoadFinish);
                    return null;
                case TwoLevelReleased:
                    SmartRefreshLayout.this.notifyStateChanged(RefreshState.TwoLevelReleased);
                    return null;
                case TwoLevelFinish:
                    SmartRefreshLayout.this.notifyStateChanged(RefreshState.TwoLevelFinish);
                    return null;
                case TwoLevel:
                    SmartRefreshLayout.this.notifyStateChanged(RefreshState.TwoLevel);
                    return null;
                default:
                    return null;
            }
        }

        public RefreshKernel a(boolean z) {
            if (z) {
                AnonymousClass1 r4 = new AnimatorListenerAdapter() {
                    public void onAnimationEnd(Animator animator) {
                        SmartRefreshLayout.this.mKernel.a(RefreshState.TwoLevel);
                    }
                };
                ValueAnimator a2 = a(SmartRefreshLayout.this.getMeasuredHeight());
                if (a2 == null || a2 != SmartRefreshLayout.this.reboundAnimator) {
                    r4.onAnimationEnd((Animator) null);
                } else {
                    a2.setDuration((long) SmartRefreshLayout.this.mFloorDuration);
                    a2.addListener(r4);
                }
            } else if (a(0) == null) {
                SmartRefreshLayout.this.notifyStateChanged(RefreshState.None);
            }
            return this;
        }

        public RefreshKernel c() {
            if (SmartRefreshLayout.this.mState == RefreshState.TwoLevel) {
                SmartRefreshLayout.this.mKernel.a(RefreshState.TwoLevelFinish);
                if (SmartRefreshLayout.this.mSpinner == 0) {
                    a(0, false);
                    SmartRefreshLayout.this.notifyStateChanged(RefreshState.None);
                } else {
                    a(0).setDuration((long) SmartRefreshLayout.this.mFloorDuration);
                }
            }
            return this;
        }

        /* JADX WARNING: Removed duplicated region for block: B:121:0x021e  */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public com.scwang.smartrefresh.layout.api.RefreshKernel a(int r18, boolean r19) {
            /*
                r17 = this;
                r0 = r17
                r1 = r18
                com.scwang.smartrefresh.layout.SmartRefreshLayout r2 = com.scwang.smartrefresh.layout.SmartRefreshLayout.this
                int r2 = r2.mSpinner
                if (r2 != r1) goto L_0x002b
                com.scwang.smartrefresh.layout.SmartRefreshLayout r2 = com.scwang.smartrefresh.layout.SmartRefreshLayout.this
                com.scwang.smartrefresh.layout.api.RefreshInternal r2 = r2.mRefreshHeader
                if (r2 == 0) goto L_0x001a
                com.scwang.smartrefresh.layout.SmartRefreshLayout r2 = com.scwang.smartrefresh.layout.SmartRefreshLayout.this
                com.scwang.smartrefresh.layout.api.RefreshInternal r2 = r2.mRefreshHeader
                boolean r2 = r2.isSupportHorizontalDrag()
                if (r2 != 0) goto L_0x002b
            L_0x001a:
                com.scwang.smartrefresh.layout.SmartRefreshLayout r2 = com.scwang.smartrefresh.layout.SmartRefreshLayout.this
                com.scwang.smartrefresh.layout.api.RefreshInternal r2 = r2.mRefreshFooter
                if (r2 == 0) goto L_0x002a
                com.scwang.smartrefresh.layout.SmartRefreshLayout r2 = com.scwang.smartrefresh.layout.SmartRefreshLayout.this
                com.scwang.smartrefresh.layout.api.RefreshInternal r2 = r2.mRefreshFooter
                boolean r2 = r2.isSupportHorizontalDrag()
                if (r2 != 0) goto L_0x002b
            L_0x002a:
                return r0
            L_0x002b:
                com.scwang.smartrefresh.layout.SmartRefreshLayout r9 = com.scwang.smartrefresh.layout.SmartRefreshLayout.this
                com.scwang.smartrefresh.layout.SmartRefreshLayout r2 = com.scwang.smartrefresh.layout.SmartRefreshLayout.this
                int r10 = r2.mSpinner
                com.scwang.smartrefresh.layout.SmartRefreshLayout r2 = com.scwang.smartrefresh.layout.SmartRefreshLayout.this
                r2.mSpinner = r1
                if (r19 == 0) goto L_0x00af
                com.scwang.smartrefresh.layout.SmartRefreshLayout r2 = com.scwang.smartrefresh.layout.SmartRefreshLayout.this
                com.scwang.smartrefresh.layout.constant.RefreshState r2 = r2.mViceState
                boolean r2 = r2.isDragging
                if (r2 == 0) goto L_0x00af
                com.scwang.smartrefresh.layout.SmartRefreshLayout r2 = com.scwang.smartrefresh.layout.SmartRefreshLayout.this
                int r2 = r2.mSpinner
                float r2 = (float) r2
                com.scwang.smartrefresh.layout.SmartRefreshLayout r3 = com.scwang.smartrefresh.layout.SmartRefreshLayout.this
                int r3 = r3.mHeaderHeight
                float r3 = (float) r3
                com.scwang.smartrefresh.layout.SmartRefreshLayout r4 = com.scwang.smartrefresh.layout.SmartRefreshLayout.this
                float r4 = r4.mHeaderTriggerRate
                float r3 = r3 * r4
                int r2 = (r2 > r3 ? 1 : (r2 == r3 ? 0 : -1))
                if (r2 <= 0) goto L_0x0065
                com.scwang.smartrefresh.layout.SmartRefreshLayout r2 = com.scwang.smartrefresh.layout.SmartRefreshLayout.this
                com.scwang.smartrefresh.layout.constant.RefreshState r2 = r2.mState
                com.scwang.smartrefresh.layout.constant.RefreshState r3 = com.scwang.smartrefresh.layout.constant.RefreshState.ReleaseToTwoLevel
                if (r2 == r3) goto L_0x00af
                com.scwang.smartrefresh.layout.SmartRefreshLayout r2 = com.scwang.smartrefresh.layout.SmartRefreshLayout.this
                com.scwang.smartrefresh.layout.api.RefreshKernel r2 = r2.mKernel
                com.scwang.smartrefresh.layout.constant.RefreshState r3 = com.scwang.smartrefresh.layout.constant.RefreshState.ReleaseToRefresh
                r2.a((com.scwang.smartrefresh.layout.constant.RefreshState) r3)
                goto L_0x00af
            L_0x0065:
                com.scwang.smartrefresh.layout.SmartRefreshLayout r2 = com.scwang.smartrefresh.layout.SmartRefreshLayout.this
                int r2 = r2.mSpinner
                int r2 = -r2
                float r2 = (float) r2
                com.scwang.smartrefresh.layout.SmartRefreshLayout r3 = com.scwang.smartrefresh.layout.SmartRefreshLayout.this
                int r3 = r3.mFooterHeight
                float r3 = (float) r3
                com.scwang.smartrefresh.layout.SmartRefreshLayout r4 = com.scwang.smartrefresh.layout.SmartRefreshLayout.this
                float r4 = r4.mFooterTriggerRate
                float r3 = r3 * r4
                int r2 = (r2 > r3 ? 1 : (r2 == r3 ? 0 : -1))
                if (r2 <= 0) goto L_0x008a
                com.scwang.smartrefresh.layout.SmartRefreshLayout r2 = com.scwang.smartrefresh.layout.SmartRefreshLayout.this
                boolean r2 = r2.mFooterNoMoreData
                if (r2 != 0) goto L_0x008a
                com.scwang.smartrefresh.layout.SmartRefreshLayout r2 = com.scwang.smartrefresh.layout.SmartRefreshLayout.this
                com.scwang.smartrefresh.layout.api.RefreshKernel r2 = r2.mKernel
                com.scwang.smartrefresh.layout.constant.RefreshState r3 = com.scwang.smartrefresh.layout.constant.RefreshState.ReleaseToLoad
                r2.a((com.scwang.smartrefresh.layout.constant.RefreshState) r3)
                goto L_0x00af
            L_0x008a:
                com.scwang.smartrefresh.layout.SmartRefreshLayout r2 = com.scwang.smartrefresh.layout.SmartRefreshLayout.this
                int r2 = r2.mSpinner
                if (r2 >= 0) goto L_0x00a0
                com.scwang.smartrefresh.layout.SmartRefreshLayout r2 = com.scwang.smartrefresh.layout.SmartRefreshLayout.this
                boolean r2 = r2.mFooterNoMoreData
                if (r2 != 0) goto L_0x00a0
                com.scwang.smartrefresh.layout.SmartRefreshLayout r2 = com.scwang.smartrefresh.layout.SmartRefreshLayout.this
                com.scwang.smartrefresh.layout.api.RefreshKernel r2 = r2.mKernel
                com.scwang.smartrefresh.layout.constant.RefreshState r3 = com.scwang.smartrefresh.layout.constant.RefreshState.PullUpToLoad
                r2.a((com.scwang.smartrefresh.layout.constant.RefreshState) r3)
                goto L_0x00af
            L_0x00a0:
                com.scwang.smartrefresh.layout.SmartRefreshLayout r2 = com.scwang.smartrefresh.layout.SmartRefreshLayout.this
                int r2 = r2.mSpinner
                if (r2 <= 0) goto L_0x00af
                com.scwang.smartrefresh.layout.SmartRefreshLayout r2 = com.scwang.smartrefresh.layout.SmartRefreshLayout.this
                com.scwang.smartrefresh.layout.api.RefreshKernel r2 = r2.mKernel
                com.scwang.smartrefresh.layout.constant.RefreshState r3 = com.scwang.smartrefresh.layout.constant.RefreshState.PullDownToRefresh
                r2.a((com.scwang.smartrefresh.layout.constant.RefreshState) r3)
            L_0x00af:
                com.scwang.smartrefresh.layout.SmartRefreshLayout r2 = com.scwang.smartrefresh.layout.SmartRefreshLayout.this
                com.scwang.smartrefresh.layout.api.RefreshContent r2 = r2.mRefreshContent
                r11 = 1
                r12 = 0
                if (r2 == 0) goto L_0x0172
                r2 = 0
                if (r1 < 0) goto L_0x00de
                com.scwang.smartrefresh.layout.SmartRefreshLayout r3 = com.scwang.smartrefresh.layout.SmartRefreshLayout.this
                com.scwang.smartrefresh.layout.api.RefreshInternal r3 = r3.mRefreshHeader
                if (r3 == 0) goto L_0x00de
                com.scwang.smartrefresh.layout.SmartRefreshLayout r3 = com.scwang.smartrefresh.layout.SmartRefreshLayout.this
                boolean r3 = r3.mEnableHeaderTranslationContent
                if (r3 != 0) goto L_0x00da
                com.scwang.smartrefresh.layout.SmartRefreshLayout r3 = com.scwang.smartrefresh.layout.SmartRefreshLayout.this
                com.scwang.smartrefresh.layout.api.RefreshInternal r3 = r3.mRefreshHeader
                com.scwang.smartrefresh.layout.constant.SpinnerStyle r3 = r3.getSpinnerStyle()
                com.scwang.smartrefresh.layout.constant.SpinnerStyle r4 = com.scwang.smartrefresh.layout.constant.SpinnerStyle.FixedBehind
                if (r3 != r4) goto L_0x00d3
                goto L_0x00da
            L_0x00d3:
                if (r10 >= 0) goto L_0x00de
                java.lang.Integer r2 = java.lang.Integer.valueOf(r12)
                goto L_0x00de
            L_0x00da:
                java.lang.Integer r2 = java.lang.Integer.valueOf(r18)
            L_0x00de:
                if (r1 > 0) goto L_0x0104
                com.scwang.smartrefresh.layout.SmartRefreshLayout r3 = com.scwang.smartrefresh.layout.SmartRefreshLayout.this
                com.scwang.smartrefresh.layout.api.RefreshInternal r3 = r3.mRefreshFooter
                if (r3 == 0) goto L_0x0104
                com.scwang.smartrefresh.layout.SmartRefreshLayout r3 = com.scwang.smartrefresh.layout.SmartRefreshLayout.this
                boolean r3 = r3.mEnableFooterTranslationContent
                if (r3 != 0) goto L_0x0100
                com.scwang.smartrefresh.layout.SmartRefreshLayout r3 = com.scwang.smartrefresh.layout.SmartRefreshLayout.this
                com.scwang.smartrefresh.layout.api.RefreshInternal r3 = r3.mRefreshFooter
                com.scwang.smartrefresh.layout.constant.SpinnerStyle r3 = r3.getSpinnerStyle()
                com.scwang.smartrefresh.layout.constant.SpinnerStyle r4 = com.scwang.smartrefresh.layout.constant.SpinnerStyle.FixedBehind
                if (r3 != r4) goto L_0x00f9
                goto L_0x0100
            L_0x00f9:
                if (r10 <= 0) goto L_0x0104
                java.lang.Integer r2 = java.lang.Integer.valueOf(r12)
                goto L_0x0104
            L_0x0100:
                java.lang.Integer r2 = java.lang.Integer.valueOf(r18)
            L_0x0104:
                if (r2 == 0) goto L_0x0172
                com.scwang.smartrefresh.layout.SmartRefreshLayout r3 = com.scwang.smartrefresh.layout.SmartRefreshLayout.this
                com.scwang.smartrefresh.layout.api.RefreshContent r3 = r3.mRefreshContent
                int r4 = r2.intValue()
                com.scwang.smartrefresh.layout.SmartRefreshLayout r5 = com.scwang.smartrefresh.layout.SmartRefreshLayout.this
                int r5 = r5.mHeaderTranslationViewId
                com.scwang.smartrefresh.layout.SmartRefreshLayout r6 = com.scwang.smartrefresh.layout.SmartRefreshLayout.this
                int r6 = r6.mFooterTranslationViewId
                r3.a((int) r4, (int) r5, (int) r6)
                com.scwang.smartrefresh.layout.SmartRefreshLayout r3 = com.scwang.smartrefresh.layout.SmartRefreshLayout.this
                boolean r3 = r3.mEnableClipHeaderWhenFixedBehind
                if (r3 == 0) goto L_0x012d
                com.scwang.smartrefresh.layout.SmartRefreshLayout r3 = com.scwang.smartrefresh.layout.SmartRefreshLayout.this
                com.scwang.smartrefresh.layout.api.RefreshInternal r3 = r3.mRefreshHeader
                com.scwang.smartrefresh.layout.constant.SpinnerStyle r3 = r3.getSpinnerStyle()
                com.scwang.smartrefresh.layout.constant.SpinnerStyle r4 = com.scwang.smartrefresh.layout.constant.SpinnerStyle.FixedBehind
                if (r3 != r4) goto L_0x012d
                r3 = 1
                goto L_0x012e
            L_0x012d:
                r3 = 0
            L_0x012e:
                if (r3 != 0) goto L_0x0139
                com.scwang.smartrefresh.layout.SmartRefreshLayout r3 = com.scwang.smartrefresh.layout.SmartRefreshLayout.this
                int r3 = r3.mHeaderBackgroundColor
                if (r3 == 0) goto L_0x0137
                goto L_0x0139
            L_0x0137:
                r3 = 0
                goto L_0x013a
            L_0x0139:
                r3 = 1
            L_0x013a:
                com.scwang.smartrefresh.layout.SmartRefreshLayout r4 = com.scwang.smartrefresh.layout.SmartRefreshLayout.this
                boolean r4 = r4.mEnableClipFooterWhenFixedBehind
                if (r4 == 0) goto L_0x014e
                com.scwang.smartrefresh.layout.SmartRefreshLayout r4 = com.scwang.smartrefresh.layout.SmartRefreshLayout.this
                com.scwang.smartrefresh.layout.api.RefreshInternal r4 = r4.mRefreshFooter
                com.scwang.smartrefresh.layout.constant.SpinnerStyle r4 = r4.getSpinnerStyle()
                com.scwang.smartrefresh.layout.constant.SpinnerStyle r5 = com.scwang.smartrefresh.layout.constant.SpinnerStyle.FixedBehind
                if (r4 != r5) goto L_0x014e
                r4 = 1
                goto L_0x014f
            L_0x014e:
                r4 = 0
            L_0x014f:
                if (r4 != 0) goto L_0x015a
                com.scwang.smartrefresh.layout.SmartRefreshLayout r4 = com.scwang.smartrefresh.layout.SmartRefreshLayout.this
                int r4 = r4.mFooterBackgroundColor
                if (r4 == 0) goto L_0x0158
                goto L_0x015a
            L_0x0158:
                r4 = 0
                goto L_0x015b
            L_0x015a:
                r4 = 1
            L_0x015b:
                if (r3 == 0) goto L_0x0165
                int r3 = r2.intValue()
                if (r3 >= 0) goto L_0x016f
                if (r10 > 0) goto L_0x016f
            L_0x0165:
                if (r4 == 0) goto L_0x0172
                int r2 = r2.intValue()
                if (r2 <= 0) goto L_0x016f
                if (r10 >= 0) goto L_0x0172
            L_0x016f:
                r9.invalidate()
            L_0x0172:
                r13 = 1065353216(0x3f800000, float:1.0)
                if (r1 >= 0) goto L_0x0178
                if (r10 <= 0) goto L_0x028e
            L_0x0178:
                com.scwang.smartrefresh.layout.SmartRefreshLayout r2 = com.scwang.smartrefresh.layout.SmartRefreshLayout.this
                com.scwang.smartrefresh.layout.api.RefreshInternal r2 = r2.mRefreshHeader
                if (r2 == 0) goto L_0x028e
                int r14 = java.lang.Math.max(r1, r12)
                com.scwang.smartrefresh.layout.SmartRefreshLayout r2 = com.scwang.smartrefresh.layout.SmartRefreshLayout.this
                int r2 = r2.mHeaderHeight
                com.scwang.smartrefresh.layout.SmartRefreshLayout r3 = com.scwang.smartrefresh.layout.SmartRefreshLayout.this
                int r3 = r3.mHeaderHeight
                float r3 = (float) r3
                com.scwang.smartrefresh.layout.SmartRefreshLayout r4 = com.scwang.smartrefresh.layout.SmartRefreshLayout.this
                float r4 = r4.mHeaderMaxDragRate
                float r3 = r3 * r4
                int r8 = (int) r3
                float r3 = (float) r14
                float r3 = r3 * r13
                com.scwang.smartrefresh.layout.SmartRefreshLayout r4 = com.scwang.smartrefresh.layout.SmartRefreshLayout.this
                int r4 = r4.mHeaderHeight
                if (r4 != 0) goto L_0x019d
                r4 = 1
                goto L_0x01a1
            L_0x019d:
                com.scwang.smartrefresh.layout.SmartRefreshLayout r4 = com.scwang.smartrefresh.layout.SmartRefreshLayout.this
                int r4 = r4.mHeaderHeight
            L_0x01a1:
                float r4 = (float) r4
                float r15 = r3 / r4
                com.scwang.smartrefresh.layout.SmartRefreshLayout r3 = com.scwang.smartrefresh.layout.SmartRefreshLayout.this
                boolean r3 = r3.isEnableRefresh()
                if (r3 != 0) goto L_0x01bb
                com.scwang.smartrefresh.layout.SmartRefreshLayout r3 = com.scwang.smartrefresh.layout.SmartRefreshLayout.this
                com.scwang.smartrefresh.layout.constant.RefreshState r3 = r3.mState
                com.scwang.smartrefresh.layout.constant.RefreshState r4 = com.scwang.smartrefresh.layout.constant.RefreshState.RefreshFinish
                if (r3 != r4) goto L_0x01b7
                if (r19 != 0) goto L_0x01b7
                goto L_0x01bb
            L_0x01b7:
                r16 = r8
                goto L_0x0264
            L_0x01bb:
                com.scwang.smartrefresh.layout.SmartRefreshLayout r3 = com.scwang.smartrefresh.layout.SmartRefreshLayout.this
                int r3 = r3.mSpinner
                if (r10 == r3) goto L_0x021a
                com.scwang.smartrefresh.layout.SmartRefreshLayout r3 = com.scwang.smartrefresh.layout.SmartRefreshLayout.this
                com.scwang.smartrefresh.layout.api.RefreshInternal r3 = r3.mRefreshHeader
                com.scwang.smartrefresh.layout.constant.SpinnerStyle r3 = r3.getSpinnerStyle()
                com.scwang.smartrefresh.layout.constant.SpinnerStyle r4 = com.scwang.smartrefresh.layout.constant.SpinnerStyle.Translate
                if (r3 != r4) goto L_0x01f3
                com.scwang.smartrefresh.layout.SmartRefreshLayout r3 = com.scwang.smartrefresh.layout.SmartRefreshLayout.this
                com.scwang.smartrefresh.layout.api.RefreshInternal r3 = r3.mRefreshHeader
                android.view.View r3 = r3.getView()
                com.scwang.smartrefresh.layout.SmartRefreshLayout r4 = com.scwang.smartrefresh.layout.SmartRefreshLayout.this
                int r4 = r4.mSpinner
                float r4 = (float) r4
                r3.setTranslationY(r4)
                com.scwang.smartrefresh.layout.SmartRefreshLayout r3 = com.scwang.smartrefresh.layout.SmartRefreshLayout.this
                int r3 = r3.mHeaderBackgroundColor
                if (r3 == 0) goto L_0x020a
                com.scwang.smartrefresh.layout.SmartRefreshLayout r3 = com.scwang.smartrefresh.layout.SmartRefreshLayout.this
                android.graphics.Paint r3 = r3.mPaint
                if (r3 == 0) goto L_0x020a
                com.scwang.smartrefresh.layout.SmartRefreshLayout r3 = com.scwang.smartrefresh.layout.SmartRefreshLayout.this
                boolean r3 = r3.mEnableHeaderTranslationContent
                if (r3 != 0) goto L_0x020a
                r9.invalidate()
                goto L_0x020a
            L_0x01f3:
                com.scwang.smartrefresh.layout.SmartRefreshLayout r3 = com.scwang.smartrefresh.layout.SmartRefreshLayout.this
                com.scwang.smartrefresh.layout.api.RefreshInternal r3 = r3.mRefreshHeader
                com.scwang.smartrefresh.layout.constant.SpinnerStyle r3 = r3.getSpinnerStyle()
                com.scwang.smartrefresh.layout.constant.SpinnerStyle r4 = com.scwang.smartrefresh.layout.constant.SpinnerStyle.Scale
                if (r3 != r4) goto L_0x020a
                com.scwang.smartrefresh.layout.SmartRefreshLayout r3 = com.scwang.smartrefresh.layout.SmartRefreshLayout.this
                com.scwang.smartrefresh.layout.api.RefreshInternal r3 = r3.mRefreshHeader
                android.view.View r3 = r3.getView()
                r3.requestLayout()
            L_0x020a:
                if (r19 != 0) goto L_0x021a
                com.scwang.smartrefresh.layout.SmartRefreshLayout r3 = com.scwang.smartrefresh.layout.SmartRefreshLayout.this
                com.scwang.smartrefresh.layout.api.RefreshInternal r3 = r3.mRefreshHeader
                r4 = 0
                r5 = r15
                r6 = r14
                r7 = r2
                r16 = r8
                r3.onMoving(r4, r5, r6, r7, r8)
                goto L_0x021c
            L_0x021a:
                r16 = r8
            L_0x021c:
                if (r19 == 0) goto L_0x0264
                com.scwang.smartrefresh.layout.SmartRefreshLayout r3 = com.scwang.smartrefresh.layout.SmartRefreshLayout.this
                com.scwang.smartrefresh.layout.api.RefreshInternal r3 = r3.mRefreshHeader
                boolean r3 = r3.isSupportHorizontalDrag()
                if (r3 == 0) goto L_0x0251
                com.scwang.smartrefresh.layout.SmartRefreshLayout r3 = com.scwang.smartrefresh.layout.SmartRefreshLayout.this
                float r3 = r3.mLastTouchX
                int r3 = (int) r3
                int r4 = r9.getWidth()
                com.scwang.smartrefresh.layout.SmartRefreshLayout r5 = com.scwang.smartrefresh.layout.SmartRefreshLayout.this
                float r5 = r5.mLastTouchX
                if (r4 != 0) goto L_0x0239
                r6 = 1
                goto L_0x023a
            L_0x0239:
                r6 = r4
            L_0x023a:
                float r6 = (float) r6
                float r5 = r5 / r6
                com.scwang.smartrefresh.layout.SmartRefreshLayout r6 = com.scwang.smartrefresh.layout.SmartRefreshLayout.this
                com.scwang.smartrefresh.layout.api.RefreshInternal r6 = r6.mRefreshHeader
                r6.onHorizontalDrag(r5, r3, r4)
                com.scwang.smartrefresh.layout.SmartRefreshLayout r3 = com.scwang.smartrefresh.layout.SmartRefreshLayout.this
                com.scwang.smartrefresh.layout.api.RefreshInternal r3 = r3.mRefreshHeader
                r4 = 1
                r5 = r15
                r6 = r14
                r7 = r2
                r8 = r16
                r3.onMoving(r4, r5, r6, r7, r8)
                goto L_0x0264
            L_0x0251:
                com.scwang.smartrefresh.layout.SmartRefreshLayout r3 = com.scwang.smartrefresh.layout.SmartRefreshLayout.this
                int r3 = r3.mSpinner
                if (r10 == r3) goto L_0x0264
                com.scwang.smartrefresh.layout.SmartRefreshLayout r3 = com.scwang.smartrefresh.layout.SmartRefreshLayout.this
                com.scwang.smartrefresh.layout.api.RefreshInternal r3 = r3.mRefreshHeader
                r4 = 1
                r5 = r15
                r6 = r14
                r7 = r2
                r8 = r16
                r3.onMoving(r4, r5, r6, r7, r8)
            L_0x0264:
                com.scwang.smartrefresh.layout.SmartRefreshLayout r3 = com.scwang.smartrefresh.layout.SmartRefreshLayout.this
                int r3 = r3.mSpinner
                if (r10 == r3) goto L_0x028e
                com.scwang.smartrefresh.layout.SmartRefreshLayout r3 = com.scwang.smartrefresh.layout.SmartRefreshLayout.this
                com.scwang.smartrefresh.layout.listener.OnMultiPurposeListener r3 = r3.mOnMultiPurposeListener
                if (r3 == 0) goto L_0x028e
                com.scwang.smartrefresh.layout.SmartRefreshLayout r3 = com.scwang.smartrefresh.layout.SmartRefreshLayout.this
                com.scwang.smartrefresh.layout.api.RefreshInternal r3 = r3.mRefreshHeader
                boolean r3 = r3 instanceof com.scwang.smartrefresh.layout.api.RefreshHeader
                if (r3 == 0) goto L_0x028e
                com.scwang.smartrefresh.layout.SmartRefreshLayout r3 = com.scwang.smartrefresh.layout.SmartRefreshLayout.this
                com.scwang.smartrefresh.layout.listener.OnMultiPurposeListener r3 = r3.mOnMultiPurposeListener
                com.scwang.smartrefresh.layout.SmartRefreshLayout r4 = com.scwang.smartrefresh.layout.SmartRefreshLayout.this
                com.scwang.smartrefresh.layout.api.RefreshInternal r4 = r4.mRefreshHeader
                com.scwang.smartrefresh.layout.api.RefreshHeader r4 = (com.scwang.smartrefresh.layout.api.RefreshHeader) r4
                r7 = r2
                r2 = r3
                r3 = r4
                r4 = r19
                r5 = r15
                r6 = r14
                r8 = r16
                r2.a((com.scwang.smartrefresh.layout.api.RefreshHeader) r3, (boolean) r4, (float) r5, (int) r6, (int) r7, (int) r8)
            L_0x028e:
                if (r1 <= 0) goto L_0x0292
                if (r10 >= 0) goto L_0x039a
            L_0x0292:
                com.scwang.smartrefresh.layout.SmartRefreshLayout r2 = com.scwang.smartrefresh.layout.SmartRefreshLayout.this
                com.scwang.smartrefresh.layout.api.RefreshInternal r2 = r2.mRefreshFooter
                if (r2 == 0) goto L_0x039a
                int r1 = java.lang.Math.min(r1, r12)
                int r8 = -r1
                com.scwang.smartrefresh.layout.SmartRefreshLayout r1 = com.scwang.smartrefresh.layout.SmartRefreshLayout.this
                int r12 = r1.mFooterHeight
                com.scwang.smartrefresh.layout.SmartRefreshLayout r1 = com.scwang.smartrefresh.layout.SmartRefreshLayout.this
                int r1 = r1.mFooterHeight
                float r1 = (float) r1
                com.scwang.smartrefresh.layout.SmartRefreshLayout r2 = com.scwang.smartrefresh.layout.SmartRefreshLayout.this
                float r2 = r2.mFooterMaxDragRate
                float r1 = r1 * r2
                int r14 = (int) r1
                float r1 = (float) r8
                float r1 = r1 * r13
                com.scwang.smartrefresh.layout.SmartRefreshLayout r2 = com.scwang.smartrefresh.layout.SmartRefreshLayout.this
                int r2 = r2.mFooterHeight
                if (r2 != 0) goto L_0x02b8
                r2 = 1
                goto L_0x02bc
            L_0x02b8:
                com.scwang.smartrefresh.layout.SmartRefreshLayout r2 = com.scwang.smartrefresh.layout.SmartRefreshLayout.this
                int r2 = r2.mFooterHeight
            L_0x02bc:
                float r2 = (float) r2
                float r13 = r1 / r2
                com.scwang.smartrefresh.layout.SmartRefreshLayout r1 = com.scwang.smartrefresh.layout.SmartRefreshLayout.this
                boolean r1 = r1.isEnableLoadMore()
                if (r1 != 0) goto L_0x02d1
                com.scwang.smartrefresh.layout.SmartRefreshLayout r1 = com.scwang.smartrefresh.layout.SmartRefreshLayout.this
                com.scwang.smartrefresh.layout.constant.RefreshState r1 = r1.mState
                com.scwang.smartrefresh.layout.constant.RefreshState r2 = com.scwang.smartrefresh.layout.constant.RefreshState.LoadFinish
                if (r1 != r2) goto L_0x0373
                if (r19 != 0) goto L_0x0373
            L_0x02d1:
                com.scwang.smartrefresh.layout.SmartRefreshLayout r1 = com.scwang.smartrefresh.layout.SmartRefreshLayout.this
                int r1 = r1.mSpinner
                if (r10 == r1) goto L_0x032e
                com.scwang.smartrefresh.layout.SmartRefreshLayout r1 = com.scwang.smartrefresh.layout.SmartRefreshLayout.this
                com.scwang.smartrefresh.layout.api.RefreshInternal r1 = r1.mRefreshFooter
                com.scwang.smartrefresh.layout.constant.SpinnerStyle r1 = r1.getSpinnerStyle()
                com.scwang.smartrefresh.layout.constant.SpinnerStyle r2 = com.scwang.smartrefresh.layout.constant.SpinnerStyle.Translate
                if (r1 != r2) goto L_0x0309
                com.scwang.smartrefresh.layout.SmartRefreshLayout r1 = com.scwang.smartrefresh.layout.SmartRefreshLayout.this
                com.scwang.smartrefresh.layout.api.RefreshInternal r1 = r1.mRefreshFooter
                android.view.View r1 = r1.getView()
                com.scwang.smartrefresh.layout.SmartRefreshLayout r2 = com.scwang.smartrefresh.layout.SmartRefreshLayout.this
                int r2 = r2.mSpinner
                float r2 = (float) r2
                r1.setTranslationY(r2)
                com.scwang.smartrefresh.layout.SmartRefreshLayout r1 = com.scwang.smartrefresh.layout.SmartRefreshLayout.this
                int r1 = r1.mFooterBackgroundColor
                if (r1 == 0) goto L_0x0320
                com.scwang.smartrefresh.layout.SmartRefreshLayout r1 = com.scwang.smartrefresh.layout.SmartRefreshLayout.this
                android.graphics.Paint r1 = r1.mPaint
                if (r1 == 0) goto L_0x0320
                com.scwang.smartrefresh.layout.SmartRefreshLayout r1 = com.scwang.smartrefresh.layout.SmartRefreshLayout.this
                boolean r1 = r1.mEnableFooterTranslationContent
                if (r1 != 0) goto L_0x0320
                r9.invalidate()
                goto L_0x0320
            L_0x0309:
                com.scwang.smartrefresh.layout.SmartRefreshLayout r1 = com.scwang.smartrefresh.layout.SmartRefreshLayout.this
                com.scwang.smartrefresh.layout.api.RefreshInternal r1 = r1.mRefreshFooter
                com.scwang.smartrefresh.layout.constant.SpinnerStyle r1 = r1.getSpinnerStyle()
                com.scwang.smartrefresh.layout.constant.SpinnerStyle r2 = com.scwang.smartrefresh.layout.constant.SpinnerStyle.Scale
                if (r1 != r2) goto L_0x0320
                com.scwang.smartrefresh.layout.SmartRefreshLayout r1 = com.scwang.smartrefresh.layout.SmartRefreshLayout.this
                com.scwang.smartrefresh.layout.api.RefreshInternal r1 = r1.mRefreshFooter
                android.view.View r1 = r1.getView()
                r1.requestLayout()
            L_0x0320:
                if (r19 != 0) goto L_0x032e
                com.scwang.smartrefresh.layout.SmartRefreshLayout r1 = com.scwang.smartrefresh.layout.SmartRefreshLayout.this
                com.scwang.smartrefresh.layout.api.RefreshInternal r2 = r1.mRefreshFooter
                r3 = 0
                r4 = r13
                r5 = r8
                r6 = r12
                r7 = r14
                r2.onMoving(r3, r4, r5, r6, r7)
            L_0x032e:
                if (r19 == 0) goto L_0x0373
                com.scwang.smartrefresh.layout.SmartRefreshLayout r1 = com.scwang.smartrefresh.layout.SmartRefreshLayout.this
                com.scwang.smartrefresh.layout.api.RefreshInternal r1 = r1.mRefreshFooter
                boolean r1 = r1.isSupportHorizontalDrag()
                if (r1 == 0) goto L_0x0361
                com.scwang.smartrefresh.layout.SmartRefreshLayout r1 = com.scwang.smartrefresh.layout.SmartRefreshLayout.this
                float r1 = r1.mLastTouchX
                int r1 = (int) r1
                int r2 = r9.getWidth()
                com.scwang.smartrefresh.layout.SmartRefreshLayout r3 = com.scwang.smartrefresh.layout.SmartRefreshLayout.this
                float r3 = r3.mLastTouchX
                if (r2 != 0) goto L_0x034a
                goto L_0x034b
            L_0x034a:
                r11 = r2
            L_0x034b:
                float r4 = (float) r11
                float r3 = r3 / r4
                com.scwang.smartrefresh.layout.SmartRefreshLayout r4 = com.scwang.smartrefresh.layout.SmartRefreshLayout.this
                com.scwang.smartrefresh.layout.api.RefreshInternal r4 = r4.mRefreshFooter
                r4.onHorizontalDrag(r3, r1, r2)
                com.scwang.smartrefresh.layout.SmartRefreshLayout r1 = com.scwang.smartrefresh.layout.SmartRefreshLayout.this
                com.scwang.smartrefresh.layout.api.RefreshInternal r2 = r1.mRefreshFooter
                r3 = 1
                r4 = r13
                r5 = r8
                r6 = r12
                r7 = r14
                r2.onMoving(r3, r4, r5, r6, r7)
                goto L_0x0373
            L_0x0361:
                com.scwang.smartrefresh.layout.SmartRefreshLayout r1 = com.scwang.smartrefresh.layout.SmartRefreshLayout.this
                int r1 = r1.mSpinner
                if (r10 == r1) goto L_0x0373
                com.scwang.smartrefresh.layout.SmartRefreshLayout r1 = com.scwang.smartrefresh.layout.SmartRefreshLayout.this
                com.scwang.smartrefresh.layout.api.RefreshInternal r2 = r1.mRefreshFooter
                r3 = 1
                r4 = r13
                r5 = r8
                r6 = r12
                r7 = r14
                r2.onMoving(r3, r4, r5, r6, r7)
            L_0x0373:
                com.scwang.smartrefresh.layout.SmartRefreshLayout r1 = com.scwang.smartrefresh.layout.SmartRefreshLayout.this
                int r1 = r1.mSpinner
                if (r10 == r1) goto L_0x039a
                com.scwang.smartrefresh.layout.SmartRefreshLayout r1 = com.scwang.smartrefresh.layout.SmartRefreshLayout.this
                com.scwang.smartrefresh.layout.listener.OnMultiPurposeListener r1 = r1.mOnMultiPurposeListener
                if (r1 == 0) goto L_0x039a
                com.scwang.smartrefresh.layout.SmartRefreshLayout r1 = com.scwang.smartrefresh.layout.SmartRefreshLayout.this
                com.scwang.smartrefresh.layout.api.RefreshInternal r1 = r1.mRefreshFooter
                boolean r1 = r1 instanceof com.scwang.smartrefresh.layout.api.RefreshFooter
                if (r1 == 0) goto L_0x039a
                com.scwang.smartrefresh.layout.SmartRefreshLayout r1 = com.scwang.smartrefresh.layout.SmartRefreshLayout.this
                com.scwang.smartrefresh.layout.listener.OnMultiPurposeListener r1 = r1.mOnMultiPurposeListener
                com.scwang.smartrefresh.layout.SmartRefreshLayout r2 = com.scwang.smartrefresh.layout.SmartRefreshLayout.this
                com.scwang.smartrefresh.layout.api.RefreshInternal r2 = r2.mRefreshFooter
                com.scwang.smartrefresh.layout.api.RefreshFooter r2 = (com.scwang.smartrefresh.layout.api.RefreshFooter) r2
                r3 = r19
                r4 = r13
                r5 = r8
                r6 = r12
                r7 = r14
                r1.a((com.scwang.smartrefresh.layout.api.RefreshFooter) r2, (boolean) r3, (float) r4, (int) r5, (int) r6, (int) r7)
            L_0x039a:
                return r0
            */
            throw new UnsupportedOperationException("Method not decompiled: com.scwang.smartrefresh.layout.SmartRefreshLayout.RefreshKernelImpl.a(int, boolean):com.scwang.smartrefresh.layout.api.RefreshKernel");
        }

        public ValueAnimator a(int i) {
            return SmartRefreshLayout.this.animSpinner(i, 0, SmartRefreshLayout.this.mReboundInterpolator, SmartRefreshLayout.this.mReboundDuration);
        }

        public RefreshKernel a(RefreshInternal refreshInternal, int i) {
            if (SmartRefreshLayout.this.mPaint == null && i != 0) {
                SmartRefreshLayout.this.mPaint = new Paint();
            }
            if (SmartRefreshLayout.this.mRefreshHeader != null && SmartRefreshLayout.this.mRefreshHeader.getView() == refreshInternal.getView()) {
                SmartRefreshLayout.this.mHeaderBackgroundColor = i;
            } else if (SmartRefreshLayout.this.mRefreshFooter != null && SmartRefreshLayout.this.mRefreshFooter.getView() == refreshInternal.getView()) {
                SmartRefreshLayout.this.mFooterBackgroundColor = i;
            }
            return this;
        }

        public RefreshKernel a(@NonNull RefreshInternal refreshInternal, boolean z) {
            if (SmartRefreshLayout.this.mRefreshHeader != null && SmartRefreshLayout.this.mRefreshHeader.getView() == refreshInternal.getView()) {
                SmartRefreshLayout.this.mHeaderNeedTouchEventWhenRefreshing = z;
            } else if (SmartRefreshLayout.this.mRefreshFooter != null && SmartRefreshLayout.this.mRefreshFooter.getView() == refreshInternal.getView()) {
                SmartRefreshLayout.this.mFooterNeedTouchEventWhenLoading = z;
            }
            return this;
        }

        public RefreshKernel b(@NonNull RefreshInternal refreshInternal, boolean z) {
            if (SmartRefreshLayout.this.mRefreshHeader == null || SmartRefreshLayout.this.mRefreshHeader.getView() != refreshInternal.getView()) {
                if (SmartRefreshLayout.this.mRefreshFooter != null && SmartRefreshLayout.this.mRefreshFooter.getView() == refreshInternal.getView() && !SmartRefreshLayout.this.mManualFooterTranslationContent) {
                    SmartRefreshLayout.this.mManualFooterTranslationContent = true;
                    SmartRefreshLayout.this.mEnableFooterTranslationContent = z;
                }
            } else if (!SmartRefreshLayout.this.mManualHeaderTranslationContent) {
                SmartRefreshLayout.this.mManualHeaderTranslationContent = true;
                SmartRefreshLayout.this.mEnableHeaderTranslationContent = z;
            }
            return this;
        }

        public RefreshKernel a(@NonNull RefreshInternal refreshInternal) {
            if (SmartRefreshLayout.this.mRefreshHeader == null || SmartRefreshLayout.this.mRefreshHeader.getView() != refreshInternal.getView()) {
                if (SmartRefreshLayout.this.mRefreshFooter != null && SmartRefreshLayout.this.mRefreshFooter.getView() == refreshInternal.getView() && SmartRefreshLayout.this.mFooterHeightStatus.notified) {
                    SmartRefreshLayout.this.mFooterHeightStatus = SmartRefreshLayout.this.mFooterHeightStatus.unNotify();
                }
            } else if (SmartRefreshLayout.this.mHeaderHeightStatus.notified) {
                SmartRefreshLayout.this.mHeaderHeightStatus = SmartRefreshLayout.this.mHeaderHeightStatus.unNotify();
            }
            return this;
        }

        public RefreshKernel b(int i) {
            SmartRefreshLayout.this.mFloorDuration = i;
            return this;
        }
    }

    public boolean post(@NonNull Runnable runnable) {
        if (this.mHandler != null) {
            return this.mHandler.post(new DelayedRunnable(runnable, 0));
        }
        this.mListDelayedRunnable = this.mListDelayedRunnable == null ? new ArrayList<>() : this.mListDelayedRunnable;
        this.mListDelayedRunnable.add(new DelayedRunnable(runnable, 0));
        return false;
    }

    public boolean postDelayed(@NonNull Runnable runnable, long j) {
        if (j == 0) {
            new DelayedRunnable(runnable, 0).run();
            return true;
        } else if (this.mHandler != null) {
            return this.mHandler.postDelayed(new DelayedRunnable(runnable, 0), j);
        } else {
            this.mListDelayedRunnable = this.mListDelayedRunnable == null ? new ArrayList<>() : this.mListDelayedRunnable;
            this.mListDelayedRunnable.add(new DelayedRunnable(runnable, j));
            return false;
        }
    }
}
