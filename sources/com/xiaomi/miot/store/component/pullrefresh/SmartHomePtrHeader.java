package com.xiaomi.miot.store.component.pullrefresh;

import android.animation.Animator;
import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.widget.FrameLayout;
import com.airbnb.lottie.LottieAnimationView;
import com.airbnb.lottie.LottieComposition;
import com.sina.weibo.sdk.utils.UIUtils;
import com.xiaomi.miot.store.R;
import com.xiaomi.miot.store.component.pullrefresh.youpinptr.PtrFrameLayout;
import com.xiaomi.miot.store.component.pullrefresh.youpinptr.PtrUIHandler;
import com.xiaomi.miot.store.component.pullrefresh.youpinptr.indicator.PtrIndicator;
import com.xiaomi.youpin.log.LogUtils;

public class SmartHomePtrHeader extends FrameLayout implements PtrUIHandler {
    public static final String PTR_IMAGES_DARK = "ptr_images_dark";
    public static final String PTR_IMAGES_LIGHT = "ptr_images_light";
    public static final String TAG = "NativePtr";
    protected View header;
    protected float mAnimationPoint = 0.6744f;
    protected LottieComposition mLottieComposition1;
    protected LottieComposition mLottieComposition2;
    protected int mLottieGrade = 1;
    public boolean mRefreshCompleted = false;
    protected LottieAnimationView mRefreshLottie;

    public SmartHomePtrHeader(Context context) {
        super(context);
    }

    public SmartHomePtrHeader(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public SmartHomePtrHeader(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
    }

    public void initViews(Context context, ViewGroup viewGroup) {
        this.header = LayoutInflater.from(context).inflate(R.layout.yp_pull_header_ptr, viewGroup, false);
        this.mRefreshLottie = (LottieAnimationView) this.header.findViewById(R.id.pull_header_lottie);
        this.mRefreshLottie.loop(true);
        this.mRefreshLottie.setImageAssetsFolder(PTR_IMAGES_DARK);
        addView(this.header);
        this.mLottieComposition2 = LottieComposition.Factory.fromFileSync(getContext(), "ptr_refresh_2.json");
        this.mLottieComposition1 = LottieComposition.Factory.fromFileSync(getContext(), "ptr_refresh_1.json");
        if (this.mLottieComposition1 != null) {
            this.mRefreshLottie.setComposition(this.mLottieComposition1);
        }
        switchHeaderView();
        LogUtils.d("NativePtr", "initViews ------");
        this.mRefreshLottie.addAnimatorListener(new Animator.AnimatorListener() {
            public void onAnimationStart(Animator animator) {
                LogUtils.d("NativePtr", "onAnimationStart ------ ");
            }

            public void onAnimationEnd(Animator animator) {
                LogUtils.d("NativePtr", "onAnimationEnd ------ ");
            }

            public void onAnimationCancel(Animator animator) {
                LogUtils.d("NativePtr", "onAnimationCancel ------ ");
            }

            public void onAnimationRepeat(Animator animator) {
                LogUtils.d("NativePtr", "onAnimationRepeat ------ ");
                SmartHomePtrHeader.this.onAnimationRepeated();
            }
        });
    }

    public void setStyleType(String str) {
        this.mRefreshLottie.setImageAssetsFolder(str);
    }

    public void switchHeaderView() {
        if (this.header != null) {
            this.header.setBackgroundResource(R.color.transparent);
            this.mRefreshLottie.setProgress(0.0f);
        }
    }

    private void changeRefreshLottie() {
        this.mRefreshLottie.pauseAnimation();
        this.mRefreshLottie.setComposition(this.mLottieComposition2);
        this.mLottieGrade = 3;
        this.mRefreshLottie.setMinAndMaxProgress(0.0f, 1.0f);
        this.mRefreshLottie.playAnimation();
    }

    /* access modifiers changed from: protected */
    public void onFinishInflate() {
        super.onFinishInflate();
    }

    /* access modifiers changed from: protected */
    public void onDetachedFromWindow() {
        super.onDetachedFromWindow();
    }

    /* access modifiers changed from: protected */
    public void onAttachedToWindow() {
        super.onAttachedToWindow();
        ViewParent parent = getParent();
        if (parent != null) {
            PtrFrameLayout ptrFrameLayout = (PtrFrameLayout) parent;
            ptrFrameLayout.performRefreshComplete();
            ptrFrameLayout.addPtrUIHandler(this);
        }
    }

    public void onUIReset(PtrFrameLayout ptrFrameLayout) {
        this.mRefreshLottie.cancelAnimation();
        LogUtils.d("NativePtr", "onUIReset ------ ");
    }

    public void onUIRefreshPrepare(PtrFrameLayout ptrFrameLayout) {
        this.mLottieGrade = 1;
        this.mRefreshLottie.setComposition(this.mLottieComposition1);
        this.mRefreshLottie.setProgress(0.0f);
        this.mRefreshCompleted = false;
        LogUtils.d("NativePtr", "onUIRefreshPrepare ------ ");
    }

    public void onUIRefreshBegin(PtrFrameLayout ptrFrameLayout) {
        this.mRefreshLottie.setRepeatMode(1);
        this.mRefreshLottie.setRepeatCount(-1);
    }

    /* access modifiers changed from: private */
    public void onAnimationRepeated() {
        if (this.mRefreshCompleted) {
            this.mRefreshCompleted = false;
            ViewParent parent = getParent();
            if (parent != null) {
                ((PtrFrameLayout) parent).refreshComplete();
            }
        }
        if (this.mLottieGrade == 2) {
            changeRefreshLottie();
        }
    }

    public void onUIRefreshComplete(PtrFrameLayout ptrFrameLayout) {
        this.mRefreshLottie.cancelAnimation();
        LogUtils.d("NativePtr", "onUIRefreshComplete ------ cancel");
    }

    public void onUIPositionChange(PtrFrameLayout ptrFrameLayout, boolean z, byte b, PtrIndicator ptrIndicator) {
        if (ptrIndicator.getCurrentPosY() > 0 && !this.mRefreshLottie.isAnimating() && this.mLottieGrade == 1) {
            LogUtils.d("NativePtr", "onUIPositionChange ------ playAnimation");
            this.mRefreshLottie.playAnimation();
            this.mLottieGrade = 2;
        }
    }

    public void updateHeaderOffsetY(int i) {
        if (this.mRefreshLottie != null) {
            ((FrameLayout.LayoutParams) this.mRefreshLottie.getLayoutParams()).setMargins(0, UIUtils.a(i, getContext()), 0, 0);
        }
    }
}
