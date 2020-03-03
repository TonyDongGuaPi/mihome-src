package com.taobao.weex.yp_compoment.pullrefresh;

import android.animation.ValueAnimator;
import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.widget.FrameLayout;
import com.airbnb.lottie.LottieAnimationView;
import com.taobao.weex.R;
import com.taobao.weex.yp_compoment.pullrefresh.youpinptr.PtrFrameLayout;
import com.taobao.weex.yp_compoment.pullrefresh.youpinptr.PtrUIHandler;
import com.taobao.weex.yp_compoment.pullrefresh.youpinptr.indicator.PtrIndicator;
import org.jacoco.agent.rt.internal_8ff85ea.Offline;

public class SmartHomePtrHeader extends FrameLayout implements PtrUIHandler {
    private static transient /* synthetic */ boolean[] $jacocoData;
    protected View header;
    protected float mAnimationPoint = 0.6f;
    protected LottieAnimationView mRefreshLottie;
    protected ValueAnimator mValueAnimator;

    private static /* synthetic */ boolean[] $jacocoInit() {
        boolean[] zArr = $jacocoData;
        if (zArr != null) {
            return zArr;
        }
        boolean[] a2 = Offline.a(-454658018662158059L, "com/taobao/weex/yp_compoment/pullrefresh/SmartHomePtrHeader", 54);
        $jacocoData = a2;
        return a2;
    }

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public SmartHomePtrHeader(Context context) {
        super(context);
        boolean[] $jacocoInit = $jacocoInit();
        $jacocoInit[0] = true;
    }

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public SmartHomePtrHeader(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        boolean[] $jacocoInit = $jacocoInit();
        $jacocoInit[1] = true;
    }

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public SmartHomePtrHeader(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        boolean[] $jacocoInit = $jacocoInit();
        $jacocoInit[2] = true;
    }

    public void initViews(Context context, ViewGroup viewGroup) {
        boolean[] $jacocoInit = $jacocoInit();
        this.header = LayoutInflater.from(context).inflate(R.layout.weex_yp_pull_header_ptr, viewGroup, false);
        $jacocoInit[3] = true;
        this.mRefreshLottie = (LottieAnimationView) this.header.findViewById(R.id.pull_header_lottie);
        $jacocoInit[4] = true;
        this.mRefreshLottie.loop(true);
        $jacocoInit[5] = true;
        this.mRefreshLottie.setImageAssetsFolder("slogan_images");
        $jacocoInit[6] = true;
        this.mRefreshLottie.setAnimation("pull_refresh.json");
        $jacocoInit[7] = true;
        addView(this.header);
        $jacocoInit[8] = true;
        switchHeaderView();
        $jacocoInit[9] = true;
    }

    public void switchHeaderView() {
        boolean[] $jacocoInit = $jacocoInit();
        if (this.header == null) {
            $jacocoInit[10] = true;
        } else {
            $jacocoInit[11] = true;
            this.header.setBackgroundResource(17170445);
            $jacocoInit[12] = true;
            this.mRefreshLottie.setProgress(0.0f);
            $jacocoInit[13] = true;
        }
        $jacocoInit[14] = true;
    }

    /* access modifiers changed from: protected */
    public void onFinishInflate() {
        boolean[] $jacocoInit = $jacocoInit();
        super.onFinishInflate();
        $jacocoInit[15] = true;
    }

    /* access modifiers changed from: protected */
    public void onDetachedFromWindow() {
        boolean[] $jacocoInit = $jacocoInit();
        super.onDetachedFromWindow();
        $jacocoInit[16] = true;
        if (this.mValueAnimator == null) {
            $jacocoInit[17] = true;
        } else if (!this.mValueAnimator.isRunning()) {
            $jacocoInit[18] = true;
        } else {
            $jacocoInit[19] = true;
            this.mValueAnimator.cancel();
            $jacocoInit[20] = true;
        }
        $jacocoInit[21] = true;
    }

    /* access modifiers changed from: protected */
    public void onAttachedToWindow() {
        boolean[] $jacocoInit = $jacocoInit();
        super.onAttachedToWindow();
        $jacocoInit[22] = true;
        ViewParent parent = getParent();
        if (parent == null) {
            $jacocoInit[23] = true;
        } else {
            $jacocoInit[24] = true;
            ((PtrFrameLayout) parent).addPtrUIHandler(this);
            $jacocoInit[25] = true;
        }
        $jacocoInit[26] = true;
    }

    public void onUIReset(PtrFrameLayout ptrFrameLayout) {
        $jacocoInit()[27] = true;
    }

    public void onUIRefreshPrepare(PtrFrameLayout ptrFrameLayout) {
        boolean[] $jacocoInit = $jacocoInit();
        this.mRefreshLottie.setProgress(0.0f);
        $jacocoInit[28] = true;
    }

    public void onUIRefreshBegin(PtrFrameLayout ptrFrameLayout) {
        boolean[] $jacocoInit = $jacocoInit();
        if (this.mValueAnimator != null) {
            $jacocoInit[29] = true;
        } else {
            $jacocoInit[30] = true;
            this.mValueAnimator = ValueAnimator.ofFloat(new float[]{this.mAnimationPoint, 1.0f});
            $jacocoInit[31] = true;
            this.mValueAnimator.setDuration(800);
            $jacocoInit[32] = true;
            this.mValueAnimator.setRepeatMode(1);
            $jacocoInit[33] = true;
            this.mValueAnimator.setRepeatCount(-1);
            $jacocoInit[34] = true;
            this.mValueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener(this) {
                private static transient /* synthetic */ boolean[] $jacocoData;
                final /* synthetic */ SmartHomePtrHeader this$0;

                private static /* synthetic */ boolean[] $jacocoInit() {
                    boolean[] zArr = $jacocoData;
                    if (zArr != null) {
                        return zArr;
                    }
                    boolean[] a2 = Offline.a(2288754515819845438L, "com/taobao/weex/yp_compoment/pullrefresh/SmartHomePtrHeader$1", 3);
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
                    $jacocoInit[1] = true;
                    this.this$0.mRefreshLottie.setProgress(((Float) valueAnimator.getAnimatedValue()).floatValue());
                    $jacocoInit[2] = true;
                }
            });
            $jacocoInit[35] = true;
        }
        if (this.mValueAnimator.isRunning()) {
            $jacocoInit[36] = true;
        } else {
            $jacocoInit[37] = true;
            this.mValueAnimator.start();
            $jacocoInit[38] = true;
        }
        $jacocoInit[39] = true;
    }

    public void onUIRefreshComplete(PtrFrameLayout ptrFrameLayout) {
        boolean[] $jacocoInit = $jacocoInit();
        this.mRefreshLottie.pauseAnimation();
        if (this.mValueAnimator == null) {
            $jacocoInit[40] = true;
        } else {
            $jacocoInit[41] = true;
            this.mValueAnimator.cancel();
            $jacocoInit[42] = true;
        }
        this.mRefreshLottie.setProgress(this.mAnimationPoint);
        $jacocoInit[43] = true;
    }

    public void onUIPositionChange(PtrFrameLayout ptrFrameLayout, boolean z, byte b, PtrIndicator ptrIndicator) {
        boolean[] $jacocoInit = $jacocoInit();
        int currentPosY = ptrIndicator.getCurrentPosY();
        $jacocoInit[44] = true;
        int dimensionPixelSize = getContext().getResources().getDimensionPixelSize(R.dimen.weex_pull_down_header_height);
        $jacocoInit[45] = true;
        int dimensionPixelSize2 = getContext().getResources().getDimensionPixelSize(R.dimen.weex_pull_down_image_bottom);
        if (currentPosY > dimensionPixelSize2) {
            float f = ((((float) currentPosY) - ((float) dimensionPixelSize2)) / ((float) (dimensionPixelSize - dimensionPixelSize2))) / 2.0f;
            if (f <= this.mAnimationPoint) {
                $jacocoInit[46] = true;
            } else {
                f = this.mAnimationPoint;
                $jacocoInit[47] = true;
            }
            if (this.mRefreshLottie.isAnimating()) {
                $jacocoInit[48] = true;
            } else {
                $jacocoInit[49] = true;
                this.mRefreshLottie.setProgress(f);
                $jacocoInit[50] = true;
            }
            $jacocoInit[51] = true;
        } else {
            this.mRefreshLottie.setProgress(0.0f);
            $jacocoInit[52] = true;
        }
        $jacocoInit[53] = true;
    }
}
