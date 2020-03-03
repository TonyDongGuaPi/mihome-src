package com.youpin.weex.app.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.RelativeLayout;
import com.airbnb.lottie.LottieAnimationView;
import com.airbnb.lottie.LottieComposition;
import com.airbnb.lottie.OnCompositionLoadedListener;
import com.youpin.weex.app.R;

public class CommonLoadingView extends RelativeLayout {
    /* access modifiers changed from: private */

    /* renamed from: a  reason: collision with root package name */
    public LottieAnimationView f2551a;
    private boolean b;

    public CommonLoadingView(Context context) {
        this(context, (AttributeSet) null);
    }

    public CommonLoadingView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public CommonLoadingView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.b = false;
        a(attributeSet);
    }

    private void a(AttributeSet attributeSet) {
        LayoutInflater.from(getContext()).inflate(R.layout.common_page_loading, this, true);
        setBackgroundColor(-1);
    }

    /* access modifiers changed from: protected */
    public void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        stopAnimation();
    }

    public void stopAnimation() {
        if (this.f2551a != null) {
            this.f2551a.pauseAnimation();
        }
        this.b = false;
    }

    public void startAnimation() {
        if (this.f2551a == null) {
            this.f2551a = (LottieAnimationView) findViewById(R.id.loading_lottie);
            try {
                LottieComposition.Factory.fromAssetFileName(getContext(), "empty_refresh.json", new OnCompositionLoadedListener() {
                    public void onCompositionLoaded(LottieComposition lottieComposition) {
                        CommonLoadingView.this.f2551a.loop(true);
                        CommonLoadingView.this.f2551a.setComposition(lottieComposition);
                        CommonLoadingView.this.f2551a.playAnimation();
                    }
                });
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            this.f2551a.playAnimation();
        }
        this.b = true;
    }

    public boolean isAnimationLoading() {
        return this.b;
    }
}
