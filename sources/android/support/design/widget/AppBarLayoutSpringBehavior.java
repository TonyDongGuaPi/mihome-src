package android.support.design.widget;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ValueAnimator;
import android.content.Context;
import android.os.Build;
import android.support.annotation.VisibleForTesting;
import android.support.design.animation.AnimationUtils;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CoordinatorLayout;
import android.support.v4.view.ViewCompat;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.Interpolator;
import com.xiaomi.smarthome.R;
import com.xiaomi.smarthome.application.SHApplication;
import com.xiaomi.smarthome.framework.log.LogUtil;
import com.xiaomi.smarthome.library.common.util.DisplayUtils;
import com.xiaomi.smarthome.miio.TitleBarUtil;
import com.xiaomi.smarthome.newui.widget.MainPageAppBarLayout;
import com.xiaomi.smarthome.newui.widget.topnavi.widgets.MyViewPager;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

public class AppBarLayoutSpringBehavior extends AppBarLayout.Behavior {
    private static final int MAX_OFFSET_ANIMATION_DURATION = 600;
    public static final float MIN_PULL_TO_EXPAND = 300.0f;
    public static final float MIN_PULL_TO_REFRESH_LENGTH = 150.0f;
    private static final int STATE_DRAGGING = 1;
    private static final int STATE_EXPAND = 4;
    private static final int STATE_IDLE = 0;
    private static final int STATE_REFRESHING = 2;
    private static final int STATE_SPRING_BACK = 3;
    private static final String TAG = "AppBarLayoutSpringBehavior";
    int collapseHeight = 0;
    private ValueAnimator mAnimateBackAnimator;
    private MainPageAppBarLayout mAppBarLayout;
    private boolean mChildPosAlignedInCollapseMode = false;
    private int mContentHeight = 0;
    private float mIsFling = 0.0f;
    /* access modifiers changed from: private */
    public boolean mIsNormalScrollEnabled = true;
    private boolean mIsPullToRefreshEnabled = true;
    /* access modifiers changed from: private */
    public int mOffset = 0;
    private ValueAnimator mOffsetAnimator;
    private float mOffsetSpring;
    private int mPreHeadHeight;
    private PullToRefreshCallback mPullToRefreshCallback;
    private Animator mRunningAnimator = null;
    private List<SpringOffsetCallback> mSpringOffsetCallbacks;
    private ValueAnimator mSpringRecoverAnimator;
    /* access modifiers changed from: private */
    public int mState = 0;
    private MyViewPager mViewPager;

    public interface PullToRefreshCallback {
        void onComplete();

        void onDrag(int i, boolean z);

        void onRefresh();
    }

    public interface SpringOffsetCallback {
        void springCallback(int i);
    }

    private static boolean checkFlag(int i, int i2) {
        return (i & i2) == i2;
    }

    static float constrain(float f, float f2, float f3) {
        return f < f2 ? f2 : f > f3 ? f3 : f;
    }

    static int constrain(int i, int i2, int i3) {
        return i < i2 ? i2 : i > i3 ? i3 : i;
    }

    public AppBarLayoutSpringBehavior() {
    }

    public AppBarLayoutSpringBehavior(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public boolean onStartNestedScroll(CoordinatorLayout coordinatorLayout, AppBarLayout appBarLayout, View view, View view2, int i) {
        LogUtil.a(TAG, "onStartNestedScroll");
        boolean z = (i & 2) != 0 && appBarLayout.hasScrollableChildren();
        if (z && this.mOffsetAnimator != null) {
            this.mOffsetAnimator.cancel();
        }
        if (z && this.mSpringRecoverAnimator != null && this.mSpringRecoverAnimator.isRunning()) {
            this.mSpringRecoverAnimator.cancel();
        }
        if (this.mAnimateBackAnimator != null && this.mAnimateBackAnimator.isRunning()) {
            this.mAnimateBackAnimator.cancel();
        }
        setLastNestedScrollingChildRef();
        String str = TAG;
        LogUtil.a(str, "onStartNestedScroll started=" + z);
        return z;
    }

    public boolean onStartNestedScroll(CoordinatorLayout coordinatorLayout, AppBarLayout appBarLayout, View view, View view2, int i, int i2) {
        LogUtil.a(TAG, "onStartNestedScroll");
        boolean z = (i & 2) != 0 && appBarLayout.hasScrollableChildren();
        if (z && this.mOffsetAnimator != null) {
            this.mOffsetAnimator.cancel();
        }
        if (z && this.mSpringRecoverAnimator != null && this.mSpringRecoverAnimator.isRunning()) {
            this.mSpringRecoverAnimator.cancel();
        }
        if (this.mAnimateBackAnimator != null && this.mAnimateBackAnimator.isRunning()) {
            this.mAnimateBackAnimator.cancel();
        }
        setLastNestedScrollingChildRef();
        String str = TAG;
        LogUtil.a(str, "onStartNestedScroll started=" + z);
        return z;
    }

    private void setLastNestedScrollingChildRef() {
        try {
            Field declaredField = AppBarLayout.Behavior.class.getDeclaredField("mLastNestedScrollingChildRef");
            declaredField.setAccessible(true);
            declaredField.set(this, (Object) null);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void onStopNestedScroll(final CoordinatorLayout coordinatorLayout, final AppBarLayout appBarLayout, View view, int i) {
        String str = TAG;
        LogUtil.a(str, "onStopNestedScroll mIsFling=" + this.mIsFling + ",mOffsetSpring=" + this.mOffsetSpring + ",mOffset=" + this.mOffset + ",abl.getHeight=" + appBarLayout.getHeight() + ",mContentHeight=" + this.mContentHeight);
        if (this.mOffset >= 0 || this.mIsNormalScrollEnabled || appBarLayout.getHeight() != this.mContentHeight) {
            super.onStopNestedScroll(coordinatorLayout, appBarLayout, view, i);
            if (this.mIsFling == 0.0f || this.mOffsetSpring > 0.0f || this.mOffset < 0) {
                checkShouldSpringRecover(coordinatorLayout, appBarLayout);
            } else if ((appBarLayout instanceof MainPageAppBarLayout) && ((MainPageAppBarLayout) appBarLayout).isCollapsingMode()) {
                this.mIsFling = 0.0f;
                return;
            } else if (((float) this.mOffset) <= 300.0f) {
                refreshComplete(coordinatorLayout, appBarLayout);
            } else if (((float) appBarLayout.getHeight()) < ((float) this.mContentHeight) + 300.0f || !this.mIsPullToRefreshEnabled || this.mPullToRefreshCallback == null) {
                refreshComplete(coordinatorLayout, appBarLayout);
            } else {
                this.mPullToRefreshCallback.onRefresh();
            }
            this.mIsFling = 0.0f;
        } else if (this.mRunningAnimator == null || !this.mRunningAnimator.isRunning()) {
            if (this.mAnimateBackAnimator != null && this.mAnimateBackAnimator.isRunning()) {
                this.mAnimateBackAnimator.cancel();
            }
            this.mAnimateBackAnimator = ValueAnimator.ofInt(new int[0]);
            this.mRunningAnimator = this.mAnimateBackAnimator;
            this.mAnimateBackAnimator.setInterpolator(new DecelerateInterpolator());
            this.mAnimateBackAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                public void onAnimationUpdate(ValueAnimator valueAnimator) {
                    AppBarLayoutSpringBehavior.this.setHeaderTopBottomOffset(coordinatorLayout, appBarLayout, ((Integer) valueAnimator.getAnimatedValue()).intValue());
                }
            });
            int dimensionPixelSize = (this.mContentHeight - SHApplication.getAppContext().getResources().getDimensionPixelSize(R.dimen.titlebar_height)) - DisplayUtils.a(20.0f);
            String str2 = TAG;
            LogUtil.a(str2, "checkShouldSpringRecover position=" + dimensionPixelSize);
            this.mAnimateBackAnimator.setIntValues(new int[]{this.mOffset, 0});
            this.mAnimateBackAnimator.addListener(new Animator.AnimatorListener() {
                public void onAnimationCancel(Animator animator) {
                }

                public void onAnimationRepeat(Animator animator) {
                }

                public void onAnimationStart(Animator animator) {
                }

                public void onAnimationEnd(Animator animator) {
                    int unused = AppBarLayoutSpringBehavior.this.mOffset = 0;
                }
            });
            this.mAnimateBackAnimator.setDuration(200);
            LogUtil.a(TAG, "onStopNestedScroll animation start 128");
            this.mAnimateBackAnimator.start();
        }
    }

    public void onStopNestedScroll(final CoordinatorLayout coordinatorLayout, final AppBarLayout appBarLayout, View view) {
        String str = TAG;
        LogUtil.a(str, "onStopNestedScroll mIsFling=" + this.mIsFling + ",mOffsetSpring=" + this.mOffsetSpring + ",mOffset=" + this.mOffset + ",abl.getHeight=" + appBarLayout.getHeight() + ",mContentHeight=" + this.mContentHeight);
        if (this.mOffset >= 0 || this.mIsNormalScrollEnabled || appBarLayout.getHeight() != this.mContentHeight) {
            super.onStopNestedScroll(coordinatorLayout, appBarLayout, view);
            if (this.mIsFling == 0.0f || this.mOffsetSpring > 0.0f || this.mOffset < 0) {
                checkShouldSpringRecover(coordinatorLayout, appBarLayout);
            } else if ((appBarLayout instanceof MainPageAppBarLayout) && ((MainPageAppBarLayout) appBarLayout).isCollapsingMode()) {
                this.mIsFling = 0.0f;
                return;
            } else if (((float) this.mOffset) <= 300.0f) {
                refreshComplete(coordinatorLayout, appBarLayout);
            } else if (((float) appBarLayout.getHeight()) < ((float) this.mContentHeight) + 300.0f || !this.mIsPullToRefreshEnabled || this.mPullToRefreshCallback == null) {
                refreshComplete(coordinatorLayout, appBarLayout);
            } else {
                this.mPullToRefreshCallback.onRefresh();
            }
            this.mIsFling = 0.0f;
        } else if (this.mRunningAnimator == null || !this.mRunningAnimator.isRunning()) {
            if (this.mAnimateBackAnimator != null && this.mAnimateBackAnimator.isRunning()) {
                this.mAnimateBackAnimator.cancel();
            }
            this.mAnimateBackAnimator = ValueAnimator.ofInt(new int[0]);
            this.mRunningAnimator = this.mAnimateBackAnimator;
            this.mAnimateBackAnimator.setInterpolator(new DecelerateInterpolator());
            this.mAnimateBackAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                public void onAnimationUpdate(ValueAnimator valueAnimator) {
                    AppBarLayoutSpringBehavior.this.setHeaderTopBottomOffset(coordinatorLayout, appBarLayout, ((Integer) valueAnimator.getAnimatedValue()).intValue());
                }
            });
            int dimensionPixelSize = (this.mContentHeight - SHApplication.getAppContext().getResources().getDimensionPixelSize(R.dimen.titlebar_height)) - DisplayUtils.a(20.0f);
            String str2 = TAG;
            LogUtil.a(str2, "checkShouldSpringRecover position=" + dimensionPixelSize);
            this.mAnimateBackAnimator.setIntValues(new int[]{this.mOffset, 0});
            this.mAnimateBackAnimator.addListener(new Animator.AnimatorListener() {
                public void onAnimationCancel(Animator animator) {
                }

                public void onAnimationRepeat(Animator animator) {
                }

                public void onAnimationStart(Animator animator) {
                }

                public void onAnimationEnd(Animator animator) {
                    int unused = AppBarLayoutSpringBehavior.this.mOffset = 0;
                }
            });
            this.mAnimateBackAnimator.setDuration(200);
            LogUtil.a(TAG, "onStopNestedScroll animation start 128");
            this.mAnimateBackAnimator.start();
        }
    }

    public boolean onNestedPreFling(CoordinatorLayout coordinatorLayout, AppBarLayout appBarLayout, View view, float f, float f2) {
        LogUtil.a(TAG, "onNestedPreFling");
        this.mIsFling = f2;
        if (((MainPageAppBarLayout) appBarLayout).isCollapsingMode()) {
            return super.onNestedPreFling(coordinatorLayout, appBarLayout, view, f, f2);
        }
        return true;
    }

    public boolean onNestedFling(CoordinatorLayout coordinatorLayout, AppBarLayout appBarLayout, View view, float f, float f2, boolean z) {
        String str = TAG;
        LogUtil.a(str, "onNestedFling " + f2);
        boolean z2 = true;
        if (!z) {
            z2 = fling(coordinatorLayout, appBarLayout, -appBarLayout.getTotalScrollRange(), 0, -f2);
        } else if (f2 < 0.0f) {
            z2 = false;
        } else if (this.mOffsetSpring <= 0.0f) {
            int dimensionPixelSize = (this.mContentHeight - SHApplication.getAppContext().getResources().getDimensionPixelSize(R.dimen.titlebar_height)) - DisplayUtils.a(20.0f);
            if (Math.abs(-appBarLayout.getUpNestedPreScrollRange()) > Math.abs(dimensionPixelSize * 3)) {
                animateOffsetTo(coordinatorLayout, appBarLayout, getTopBottomOffsetForScrollingSibling() - dimensionPixelSize, f2);
            } else {
                getTopBottomOffsetForScrollingSibling();
                checkShouldSpringRecover(coordinatorLayout, appBarLayout);
            }
        } else {
            int dimensionPixelSize2 = (this.mContentHeight - SHApplication.getAppContext().getResources().getDimensionPixelSize(R.dimen.titlebar_height)) - DisplayUtils.a(20.0f);
            if (Math.abs(-appBarLayout.getUpNestedPreScrollRange()) > Math.abs(dimensionPixelSize2 * 3)) {
                animateOffsetTo(coordinatorLayout, appBarLayout, getTopBottomOffsetForScrollingSibling() - dimensionPixelSize2, f2);
            } else {
                getTopBottomOffsetForScrollingSibling();
                refreshComplete(coordinatorLayout, appBarLayout);
            }
        }
        setWasNestedFlung(z2);
        return z2;
    }

    private void setWasNestedFlung(boolean z) {
        try {
            Field declaredField = AppBarLayout.Behavior.class.getDeclaredField("mWasNestedFlung");
            declaredField.setAccessible(true);
            declaredField.set(this, Boolean.valueOf(z));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void setContentHeight(int i) {
        String str = TAG;
        LogUtil.a(str, "setContentHeight=" + i);
        this.mContentHeight = i;
        this.mPreHeadHeight = i;
    }

    private void checkShouldSpringRecover(final CoordinatorLayout coordinatorLayout, final AppBarLayout appBarLayout) {
        LogUtil.a(TAG, "checkShouldSpringRecover");
        if (this.mOffset > 0 && this.mOffsetSpring == 0.0f) {
            setHeaderTopBottomOffset(coordinatorLayout, appBarLayout, 0);
        } else if (this.mOffsetSpring > 0.0f) {
            animateRecoverBySpring(coordinatorLayout, appBarLayout);
        } else if ((appBarLayout instanceof MainPageAppBarLayout) && ((MainPageAppBarLayout) appBarLayout).isCollapsingMode()) {
        } else {
            if (this.mRunningAnimator == null || !this.mRunningAnimator.isRunning()) {
                if (this.mAnimateBackAnimator != null && this.mAnimateBackAnimator.isRunning()) {
                    this.mAnimateBackAnimator.cancel();
                }
                this.mAnimateBackAnimator = ValueAnimator.ofInt(new int[0]);
                this.mRunningAnimator = this.mAnimateBackAnimator;
                this.mAnimateBackAnimator.setInterpolator(new DecelerateInterpolator());
                this.mAnimateBackAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                    public void onAnimationUpdate(ValueAnimator valueAnimator) {
                        AppBarLayoutSpringBehavior.this.setHeaderTopBottomOffset(coordinatorLayout, appBarLayout, ((Integer) valueAnimator.getAnimatedValue()).intValue());
                    }
                });
                int topAndBottomOffset = getTopAndBottomOffset();
                int totalScrollRange = appBarLayout.getTotalScrollRange();
                final boolean z = Math.abs(topAndBottomOffset) < Math.abs(totalScrollRange / 2);
                if (z) {
                    this.mAnimateBackAnimator.setIntValues(new int[]{topAndBottomOffset, 0});
                } else {
                    this.mAnimateBackAnimator.setIntValues(new int[]{topAndBottomOffset, -totalScrollRange});
                }
                this.mAnimateBackAnimator.addListener(new AnimatorListenerAdapter() {
                    public void onAnimationEnd(Animator animator) {
                        super.onAnimationEnd(animator);
                        if (z) {
                            int unused = AppBarLayoutSpringBehavior.this.mOffset = 0;
                        }
                    }
                });
                this.mAnimateBackAnimator.setDuration(200);
                LogUtil.a(TAG, "animation start 260");
                this.mAnimateBackAnimator.start();
            }
        }
    }

    public void setPullToRefreshCallback(PullToRefreshCallback pullToRefreshCallback) {
        this.mPullToRefreshCallback = pullToRefreshCallback;
    }

    public void collapse(CoordinatorLayout coordinatorLayout, AppBarLayout appBarLayout) {
        if (this.mIsNormalScrollEnabled) {
            setHeaderTopBottomOffset(coordinatorLayout, appBarLayout, -((this.mContentHeight - appBarLayout.getResources().getDimensionPixelSize(R.dimen.titlebar_height)) - TitleBarUtil.a()));
        }
    }

    public void expand(CoordinatorLayout coordinatorLayout, AppBarLayout appBarLayout) {
        setHeaderTopBottomOffset(coordinatorLayout, appBarLayout, 0);
    }

    public void refreshComplete(final CoordinatorLayout coordinatorLayout, final AppBarLayout appBarLayout) {
        LogUtil.a(TAG, "refreshComplete");
        this.mState = 3;
        if (this.mSpringRecoverAnimator == null) {
            this.mSpringRecoverAnimator = new ValueAnimator();
            this.mSpringRecoverAnimator.setDuration(200);
            this.mSpringRecoverAnimator.setInterpolator(new DecelerateInterpolator());
            this.mSpringRecoverAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                public void onAnimationUpdate(ValueAnimator valueAnimator) {
                    AppBarLayoutSpringBehavior.this.updateSpringHeaderHeight(coordinatorLayout, appBarLayout, (float) ((Integer) valueAnimator.getAnimatedValue()).intValue());
                }
            });
            this.mSpringRecoverAnimator.addListener(new Animator.AnimatorListener() {
                public void onAnimationCancel(Animator animator) {
                }

                public void onAnimationRepeat(Animator animator) {
                }

                public void onAnimationStart(Animator animator) {
                }

                public void onAnimationEnd(Animator animator) {
                    int unused = AppBarLayoutSpringBehavior.this.mState = 0;
                }
            });
        } else if (this.mSpringRecoverAnimator.isRunning()) {
            this.mSpringRecoverAnimator.cancel();
        }
        if (this.mRunningAnimator == null || !this.mRunningAnimator.isRunning()) {
            this.mRunningAnimator = this.mSpringRecoverAnimator;
            if (Integer.compare((int) this.mOffsetSpring, 0) != 0) {
                LogUtil.a(TAG, "animation start 330");
                this.mSpringRecoverAnimator.setIntValues(new int[]{(int) this.mOffsetSpring, 0});
                this.mSpringRecoverAnimator.start();
            }
            if (this.mIsPullToRefreshEnabled && this.mPullToRefreshCallback != null) {
                this.mPullToRefreshCallback.onComplete();
            }
        }
    }

    private void processPullRefresh(CoordinatorLayout coordinatorLayout, AppBarLayout appBarLayout) {
        String str = TAG;
        LogUtil.a(str, "processPullRefresh mOffsetSpring=" + this.mOffsetSpring + ",mOffset=" + this.mOffset);
        if (this.mOffsetSpring < 150.0f) {
            refreshComplete(coordinatorLayout, appBarLayout);
        } else if (this.mOffsetSpring < 300.0f) {
            this.mState = 2;
            if (this.mIsPullToRefreshEnabled && this.mPullToRefreshCallback != null) {
                this.mPullToRefreshCallback.onRefresh();
            }
        } else {
            this.mState = 2;
            if (this.mIsPullToRefreshEnabled && this.mPullToRefreshCallback != null) {
                this.mPullToRefreshCallback.onRefresh();
            }
        }
    }

    private void animateToNormalExpand(final CoordinatorLayout coordinatorLayout, final AppBarLayout appBarLayout, Animator.AnimatorListener animatorListener) {
        int topBottomOffsetForScrollingSibling = getTopBottomOffsetForScrollingSibling();
        if (topBottomOffsetForScrollingSibling != 0) {
            ValueAnimator ofInt = ValueAnimator.ofInt(new int[]{topBottomOffsetForScrollingSibling, 0});
            if (this.mRunningAnimator == null || !this.mRunningAnimator.isRunning()) {
                this.mRunningAnimator = ofInt;
                ofInt.setInterpolator(AnimationUtils.DECELERATE_INTERPOLATOR);
                ofInt.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                    public void onAnimationUpdate(ValueAnimator valueAnimator) {
                        AppBarLayoutSpringBehavior.this.setHeaderTopBottomOffset(coordinatorLayout, appBarLayout, ((Integer) valueAnimator.getAnimatedValue()).intValue());
                    }
                });
                ofInt.addListener(animatorListener);
                ofInt.setDuration((long) Math.min(200, 600));
                LogUtil.a(TAG, "animation start 376");
                ofInt.start();
            }
        } else if (animatorListener != null) {
            animatorListener.onAnimationEnd((Animator) null);
        }
    }

    private void animateRecoverBySpring(final CoordinatorLayout coordinatorLayout, final AppBarLayout appBarLayout) {
        String str = TAG;
        LogUtil.a(str, "animateRecoverBySpring mOffsetSpring=" + this.mOffsetSpring + ",mOffset=" + this.mOffset);
        if (this.mIsPullToRefreshEnabled) {
            processPullRefresh(coordinatorLayout, appBarLayout);
            return;
        }
        if (this.mSpringRecoverAnimator == null) {
            this.mSpringRecoverAnimator = new ValueAnimator();
            this.mSpringRecoverAnimator.setDuration(200);
            this.mSpringRecoverAnimator.setInterpolator(new DecelerateInterpolator());
            this.mSpringRecoverAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                public void onAnimationUpdate(ValueAnimator valueAnimator) {
                    AppBarLayoutSpringBehavior.this.updateSpringHeaderHeight(coordinatorLayout, appBarLayout, (float) ((Integer) valueAnimator.getAnimatedValue()).intValue());
                }
            });
        } else if (this.mSpringRecoverAnimator.isRunning()) {
            this.mSpringRecoverAnimator.cancel();
        }
        if (this.mRunningAnimator == null || !this.mRunningAnimator.isRunning()) {
            this.mRunningAnimator = this.mSpringRecoverAnimator;
            this.mSpringRecoverAnimator.setIntValues(new int[]{(int) this.mOffsetSpring, 0});
            LogUtil.a(TAG, "animation start 401");
            this.mSpringRecoverAnimator.start();
        }
    }

    public boolean onLayoutChild(CoordinatorLayout coordinatorLayout, AppBarLayout appBarLayout, int i) {
        boolean onLayoutChild = super.onLayoutChild(coordinatorLayout, appBarLayout, i);
        if (this.collapseHeight == 0 && appBarLayout.getChildAt(0) != null) {
            this.collapseHeight = DisplayUtils.a(65.0f);
        }
        return onLayoutChild;
    }

    /* access modifiers changed from: package-private */
    public int getHeaderExpandedHeight(AppBarLayout appBarLayout) {
        int childCount = appBarLayout.getChildCount();
        int i = 0;
        for (int i2 = 0; i2 < childCount; i2++) {
            View childAt = appBarLayout.getChildAt(i2);
            AppBarLayout.LayoutParams layoutParams = (AppBarLayout.LayoutParams) childAt.getLayoutParams();
            childAt.getMeasuredHeight();
            int i3 = layoutParams.topMargin;
            int i4 = layoutParams.bottomMargin;
            i = childAt.getBottom();
        }
        return Math.max(0, i);
    }

    /* access modifiers changed from: package-private */
    public void onFlingFinished(CoordinatorLayout coordinatorLayout, AppBarLayout appBarLayout) {
        LogUtil.a(TAG, "onFlingFinished");
        animateRecoverBySpring(coordinatorLayout, appBarLayout);
    }

    private void snapToChildIfNeeded(CoordinatorLayout coordinatorLayout, AppBarLayout appBarLayout) {
        LogUtil.a(TAG, "snapToChildIfNeeded");
        int topBottomOffsetForScrollingSibling = getTopBottomOffsetForScrollingSibling();
        int childIndexOnOffset = getChildIndexOnOffset(appBarLayout, topBottomOffsetForScrollingSibling);
        if (childIndexOnOffset >= 0) {
            View childAt = appBarLayout.getChildAt(childIndexOnOffset);
            int scrollFlags = ((AppBarLayout.LayoutParams) childAt.getLayoutParams()).getScrollFlags();
            if ((scrollFlags & 17) == 17) {
                int i = -childAt.getTop();
                int i2 = -childAt.getBottom();
                if (childIndexOnOffset == appBarLayout.getChildCount() - 1) {
                    i2 += appBarLayout.getTopInset();
                }
                if (checkFlag(scrollFlags, 2)) {
                    i2 += ViewCompat.getMinimumHeight(childAt);
                } else if (checkFlag(scrollFlags, 5)) {
                    int minimumHeight = ViewCompat.getMinimumHeight(childAt) + i2;
                    if (topBottomOffsetForScrollingSibling < minimumHeight) {
                        i = minimumHeight;
                    } else {
                        i2 = minimumHeight;
                    }
                }
                if (topBottomOffsetForScrollingSibling < (i2 + i) / 2) {
                    i = i2;
                }
                animateOffsetTo(coordinatorLayout, appBarLayout, constrain(i, -appBarLayout.getTotalScrollRange(), 0), 0.0f);
            }
        }
    }

    private void animateOffsetTo(CoordinatorLayout coordinatorLayout, AppBarLayout appBarLayout, int i, float f) {
        int i2;
        LogUtil.a(TAG, "animateOffsetTo");
        int abs = Math.abs(getTopBottomOffsetForScrollingSibling() - i);
        float abs2 = Math.abs(f);
        if (abs2 > 0.0f) {
            i2 = Math.round((((float) abs) / abs2) * 1000.0f) * 3;
        } else {
            i2 = (int) (((((float) abs) / ((float) appBarLayout.getHeight())) + 1.0f) * 150.0f);
        }
        animateOffsetWithDuration(coordinatorLayout, appBarLayout, i, i2);
    }

    private void animateOffsetWithDuration(final CoordinatorLayout coordinatorLayout, final AppBarLayout appBarLayout, int i, int i2) {
        LogUtil.a(TAG, "animateOffsetWithDuration");
        int topBottomOffsetForScrollingSibling = getTopBottomOffsetForScrollingSibling();
        if (topBottomOffsetForScrollingSibling != i) {
            if (this.mOffsetAnimator == null) {
                this.mOffsetAnimator = new ValueAnimator();
                this.mOffsetAnimator.setInterpolator(AnimationUtils.DECELERATE_INTERPOLATOR);
                this.mOffsetAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                    public void onAnimationUpdate(ValueAnimator valueAnimator) {
                        AppBarLayoutSpringBehavior.this.setHeaderTopBottomOffset(coordinatorLayout, appBarLayout, ((Integer) valueAnimator.getAnimatedValue()).intValue());
                    }
                });
            } else {
                this.mOffsetAnimator.cancel();
            }
            if (this.mRunningAnimator == null || !this.mRunningAnimator.isRunning()) {
                this.mRunningAnimator = this.mOffsetAnimator;
                this.mOffsetAnimator.setDuration((long) Math.min(i2, 600));
                this.mOffsetAnimator.setIntValues(new int[]{topBottomOffsetForScrollingSibling, i});
                String str = TAG;
                LogUtil.a(str, "animation start 526 currentOffset=" + topBottomOffsetForScrollingSibling + ",offset=" + i);
                this.mOffsetAnimator.start();
            }
        } else if (this.mOffsetAnimator != null && this.mOffsetAnimator.isRunning()) {
            this.mOffsetAnimator.cancel();
        }
    }

    private int getChildIndexOnOffset(AppBarLayout appBarLayout, int i) {
        int childCount = appBarLayout.getChildCount();
        for (int i2 = 0; i2 < childCount; i2++) {
            View childAt = appBarLayout.getChildAt(i2);
            int i3 = -i;
            if (childAt.getTop() <= i3 && childAt.getBottom() >= i3) {
                return i2;
            }
        }
        return -1;
    }

    public void setPullToRefreshEnabled(boolean z) {
        this.mIsPullToRefreshEnabled = z;
    }

    public void setNormalScrollEnable(boolean z, CoordinatorLayout coordinatorLayout, AppBarLayout appBarLayout) {
        if (!z) {
            animateToNormalExpand(coordinatorLayout, appBarLayout, new Animator.AnimatorListener() {
                public void onAnimationCancel(Animator animator) {
                }

                public void onAnimationRepeat(Animator animator) {
                }

                public void onAnimationStart(Animator animator) {
                }

                public void onAnimationEnd(Animator animator) {
                    boolean unused = AppBarLayoutSpringBehavior.this.mIsNormalScrollEnabled = false;
                }
            });
        } else {
            this.mIsNormalScrollEnabled = true;
        }
    }

    private void setViewPagerPadding(ViewGroup viewGroup) {
        if (this.mViewPager == null) {
            this.mViewPager = (MyViewPager) viewGroup.findViewById(R.id.viewpager);
            if (this.mViewPager == null) {
                return;
            }
        }
        if (this.mAppBarLayout == null) {
            this.mAppBarLayout = (MainPageAppBarLayout) viewGroup.findViewById(R.id.main_appbar);
            if (this.mAppBarLayout == null) {
                return;
            }
        }
        if (getOffsetSpring() <= 0) {
            int totalScrollRange = this.mAppBarLayout.getTotalScrollRange();
            int topAndBottomOffset = getTopAndBottomOffset();
            if (topAndBottomOffset <= 0) {
                topAndBottomOffset += totalScrollRange;
            }
            this.mViewPager.setPadding(this.mViewPager.getPaddingLeft(), this.mViewPager.getPaddingTop(), this.mViewPager.getPaddingRight(), topAndBottomOffset);
        }
    }

    public boolean setTopAndBottomOffset(int i) {
        return super.setTopAndBottomOffset(i);
    }

    /* access modifiers changed from: package-private */
    public int setHeaderTopBottomOffset(CoordinatorLayout coordinatorLayout, AppBarLayout appBarLayout, int i, int i2, int i3) {
        int i4;
        int i5;
        this.mOffset = i;
        String str = TAG;
        LogUtil.a(str, "setHeaderTopBottomOffset mOffset=" + this.mOffset);
        boolean z = false;
        if (this.mIsPullToRefreshEnabled && this.mState == 2 && i > 0) {
            return 0;
        }
        if (this.mOffsetSpring <= 0.0f) {
            this.mState = 0;
        } else {
            this.mState = 1;
        }
        int topBottomOffsetForScrollingSibling = getTopBottomOffsetForScrollingSibling();
        if (this.mOffsetSpring == 0.0f || i >= 0) {
            i5 = i;
            i4 = 0;
        } else {
            String str2 = TAG;
            LogUtil.a(str2, "setHeaderTopBottomOffset1 mOffsetSpring=" + this.mOffsetSpring + ",originNew=" + i);
            int i6 = (int) (this.mOffsetSpring + ((float) i));
            if (i6 < 0) {
                i5 = i6;
                i6 = 0;
            } else {
                i5 = i;
            }
            updateSpringOffsetByscroll(coordinatorLayout, appBarLayout, (float) i6);
            i4 = getTopBottomOffsetForScrollingSibling() - i;
            if (i6 >= 0) {
                if (this.mIsPullToRefreshEnabled && this.mPullToRefreshCallback != null) {
                    PullToRefreshCallback pullToRefreshCallback = this.mPullToRefreshCallback;
                    int i7 = (int) this.mOffsetSpring;
                    if (this.mOffsetSpring >= 150.0f) {
                        z = true;
                    }
                    pullToRefreshCallback.onDrag(i7, z);
                }
                coordinatorLayout.dispatchDependentViewsChanged(appBarLayout);
                appBarLayout.dispatchOffsetUpdates(getTopAndBottomOffset());
                return i4;
            }
        }
        if (this.mOffsetSpring <= 0.0f || appBarLayout.getHeight() < this.mPreHeadHeight || i5 <= 0) {
            if (i2 != 0 && topBottomOffsetForScrollingSibling >= i2 && topBottomOffsetForScrollingSibling <= i3) {
                int constrain = constrain(i5, i2, i3);
                if (topBottomOffsetForScrollingSibling != constrain) {
                    setTopAndBottomOffset(appBarLayout.hasChildWithInterpolator() ? interpolateOffset(appBarLayout, constrain) : constrain);
                    i4 = topBottomOffsetForScrollingSibling - constrain;
                    appBarLayout.dispatchOffsetUpdates(getTopAndBottomOffset());
                    updateAppBarLayoutDrawableState(coordinatorLayout, appBarLayout, constrain, constrain < topBottomOffsetForScrollingSibling ? -1 : 1);
                } else if (topBottomOffsetForScrollingSibling != i2) {
                    String str3 = TAG;
                    LogUtil.a(str3, "setHeaderTopBottomOffset3 mOffsetSpring=" + this.mOffsetSpring + ",originNew=" + i);
                    updateSpringOffsetByscroll(coordinatorLayout, appBarLayout, this.mOffsetSpring + ((float) (i / 3)));
                    i4 = getTopBottomOffsetForScrollingSibling() - i;
                }
            }
            if (!(appBarLayout instanceof MainPageAppBarLayout)) {
                coordinatorLayout.dispatchDependentViewsChanged(appBarLayout);
                appBarLayout.dispatchOffsetUpdates(getTopAndBottomOffset());
                this.mChildPosAlignedInCollapseMode = false;
            } else if (!((MainPageAppBarLayout) appBarLayout).isCollapsingMode()) {
                coordinatorLayout.dispatchDependentViewsChanged(appBarLayout);
                appBarLayout.dispatchOffsetUpdates(getTopAndBottomOffset());
                this.mChildPosAlignedInCollapseMode = false;
            } else if (!this.mChildPosAlignedInCollapseMode) {
                coordinatorLayout.dispatchDependentViewsChanged(appBarLayout);
                appBarLayout.dispatchOffsetUpdates(getTopAndBottomOffset());
                this.mChildPosAlignedInCollapseMode = true;
            }
            setViewPagerPadding(coordinatorLayout);
            return i4;
        }
        String str4 = TAG;
        LogUtil.a(str4, "setHeaderTopBottomOffset2 mOffsetSpring=" + this.mOffsetSpring + ",originNew=" + i);
        updateSpringOffsetByscroll(coordinatorLayout, appBarLayout, this.mOffsetSpring + (((float) i) / 3.0f));
        int topBottomOffsetForScrollingSibling2 = getTopBottomOffsetForScrollingSibling() - i;
        if (this.mIsPullToRefreshEnabled && this.mPullToRefreshCallback != null) {
            PullToRefreshCallback pullToRefreshCallback2 = this.mPullToRefreshCallback;
            int i8 = (int) this.mOffsetSpring;
            if (this.mOffsetSpring >= 150.0f) {
                z = true;
            }
            pullToRefreshCallback2.onDrag(i8, z);
        }
        coordinatorLayout.dispatchDependentViewsChanged(appBarLayout);
        appBarLayout.dispatchOffsetUpdates(getTopAndBottomOffset());
        return topBottomOffsetForScrollingSibling2;
    }

    private int interpolateOffset(AppBarLayout appBarLayout, int i) {
        LogUtil.a(TAG, "interpolateOffset");
        int abs = Math.abs(i);
        int childCount = appBarLayout.getChildCount();
        int i2 = 0;
        int i3 = 0;
        while (true) {
            if (i3 >= childCount) {
                break;
            }
            View childAt = appBarLayout.getChildAt(i3);
            AppBarLayout.LayoutParams layoutParams = (AppBarLayout.LayoutParams) childAt.getLayoutParams();
            Interpolator scrollInterpolator = layoutParams.getScrollInterpolator();
            if (abs < childAt.getTop() || abs > childAt.getBottom()) {
                i3++;
            } else if (scrollInterpolator != null) {
                int scrollFlags = layoutParams.getScrollFlags();
                if ((scrollFlags & 1) != 0) {
                    i2 = 0 + childAt.getHeight() + layoutParams.topMargin + layoutParams.bottomMargin;
                    if ((scrollFlags & 2) != 0) {
                        i2 -= ViewCompat.getMinimumHeight(childAt);
                    }
                }
                if (ViewCompat.getFitsSystemWindows(childAt)) {
                    i2 -= appBarLayout.getTopInset();
                }
                if (i2 > 0) {
                    float f = (float) i2;
                    return Integer.signum(i) * (childAt.getTop() + Math.round(f * scrollInterpolator.getInterpolation(((float) (abs - childAt.getTop())) / f)));
                }
            }
        }
        return i;
    }

    private void updateAppBarLayoutDrawableState(CoordinatorLayout coordinatorLayout, AppBarLayout appBarLayout, int i, int i2) {
        LogUtil.a(TAG, "updateAppBarLayoutDrawableState");
        View appBarChildOnOffset = getAppBarChildOnOffset(appBarLayout, i);
        if (appBarChildOnOffset != null) {
            int scrollFlags = ((AppBarLayout.LayoutParams) appBarChildOnOffset.getLayoutParams()).getScrollFlags();
            boolean z = false;
            if ((scrollFlags & 1) != 0) {
                int minimumHeight = ViewCompat.getMinimumHeight(appBarChildOnOffset);
                if (i2 <= 0 || (scrollFlags & 12) == 0 ? !((scrollFlags & 2) == 0 || (-i) < (appBarChildOnOffset.getBottom() - minimumHeight) - appBarLayout.getTopInset()) : (-i) >= (appBarChildOnOffset.getBottom() - minimumHeight) - appBarLayout.getTopInset()) {
                    z = true;
                }
            }
            if (appBarLayout.setLiftedState(z) && Build.VERSION.SDK_INT >= 11 && shouldJumpElevationState(coordinatorLayout, appBarLayout)) {
                appBarLayout.jumpDrawablesToCurrentState();
            }
        }
    }

    private boolean shouldJumpElevationState(CoordinatorLayout coordinatorLayout, AppBarLayout appBarLayout) {
        LogUtil.a(TAG, "shouldJumpElevationState");
        List<View> dependents = coordinatorLayout.getDependents(appBarLayout);
        int size = dependents.size();
        int i = 0;
        while (i < size) {
            CoordinatorLayout.Behavior behavior = ((CoordinatorLayout.LayoutParams) dependents.get(i).getLayoutParams()).getBehavior();
            if (!(behavior instanceof AppBarLayout.ScrollingViewBehavior)) {
                i++;
            } else if (((AppBarLayout.ScrollingViewBehavior) behavior).getOverlayTop() != 0) {
                return true;
            } else {
                return false;
            }
        }
        return false;
    }

    private static View getAppBarChildOnOffset(AppBarLayout appBarLayout, int i) {
        int abs = Math.abs(i);
        int childCount = appBarLayout.getChildCount();
        for (int i2 = 0; i2 < childCount; i2++) {
            View childAt = appBarLayout.getChildAt(i2);
            if (abs >= childAt.getTop() && abs <= childAt.getBottom()) {
                return childAt;
            }
        }
        return null;
    }

    private void updateSpringOffsetByscroll(CoordinatorLayout coordinatorLayout, AppBarLayout appBarLayout, float f) {
        LogUtil.a(TAG, "updateSpringOffsetByscroll");
        if (this.mSpringRecoverAnimator != null && this.mSpringRecoverAnimator.isRunning()) {
            this.mSpringRecoverAnimator.cancel();
        }
        updateSpringHeaderHeight(coordinatorLayout, appBarLayout, f);
    }

    /* access modifiers changed from: private */
    public void updateSpringHeaderHeight(CoordinatorLayout coordinatorLayout, AppBarLayout appBarLayout, float f) {
        String str = TAG;
        LogUtil.a(str, "updateSpringHeaderHeight " + f + ",mOffset=" + this.mOffset);
        if (appBarLayout.getHeight() >= this.mPreHeadHeight && f >= 0.0f) {
            if (this.mSpringOffsetCallbacks != null) {
                for (SpringOffsetCallback springCallback : this.mSpringOffsetCallbacks) {
                    springCallback.springCallback((int) f);
                }
            }
            String str2 = TAG;
            LogUtil.a(str2, "updateSpringHeaderHeight mPreHeadHeight=" + this.mPreHeadHeight + ",appBarLayout.getHeight()=" + appBarLayout.getHeight());
            this.mOffsetSpring = f;
            CoordinatorLayout.LayoutParams layoutParams = (CoordinatorLayout.LayoutParams) appBarLayout.getLayoutParams();
            layoutParams.height = (int) (((float) this.mPreHeadHeight) + f);
            appBarLayout.setLayoutParams(layoutParams);
            coordinatorLayout.dispatchDependentViewsChanged(appBarLayout);
        }
    }

    public int getOffsetSpring() {
        return (int) this.mOffsetSpring;
    }

    public void addSpringOffsetCallback(SpringOffsetCallback springOffsetCallback) {
        if (this.mSpringOffsetCallbacks == null) {
            this.mSpringOffsetCallbacks = new ArrayList();
        }
        if (springOffsetCallback != null && !this.mSpringOffsetCallbacks.contains(springOffsetCallback)) {
            this.mSpringOffsetCallbacks.add(springOffsetCallback);
        }
    }

    public void removeSpringOffsetCallback(SpringOffsetCallback springOffsetCallback) {
        if (springOffsetCallback != null && this.mSpringOffsetCallbacks != null) {
            this.mSpringOffsetCallbacks.remove(springOffsetCallback);
        }
    }

    /* access modifiers changed from: package-private */
    @VisibleForTesting
    public boolean isOffsetAnimatorRunning() {
        return this.mOffsetAnimator != null && this.mOffsetAnimator.isRunning();
    }

    public int getTopBottomOffsetForScrollingSibling() {
        return super.getTopBottomOffsetForScrollingSibling();
    }
}
