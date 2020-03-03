package com.xiaomi.yp_ui.widget;

import android.content.Context;
import android.support.v4.content.ContextCompat;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.RelativeLayout;
import com.airbnb.lottie.LottieAnimationView;
import com.airbnb.lottie.LottieComposition;
import com.airbnb.lottie.OnCompositionLoadedListener;
import com.xiaomi.yp_ui.R;

public class CommonLoadingView extends RelativeLayout {
    /* access modifiers changed from: private */

    /* renamed from: a  reason: collision with root package name */
    public LottieAnimationView f1602a;
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
        LayoutInflater.from(getContext()).inflate(R.layout.yp_common_page_loading, this, true);
        setBackgroundColor(ContextCompat.getColor(getContext(), R.color.white));
    }

    /* access modifiers changed from: protected */
    public void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        stopAnimation();
    }

    public void stopAnimation() {
        if (this.f1602a != null) {
            this.f1602a.pauseAnimation();
        }
        this.b = false;
    }

    public void startAnimation() {
        if (this.f1602a == null) {
            this.f1602a = (LottieAnimationView) findViewById(R.id.loading_lottie);
            try {
                LottieComposition.Factory.fromAssetFileName(getContext(), "empty_refresh.json", new OnCompositionLoadedListener() {
                    public void onCompositionLoaded(LottieComposition lottieComposition) {
                        CommonLoadingView.this.f1602a.loop(true);
                        CommonLoadingView.this.f1602a.setComposition(lottieComposition);
                        CommonLoadingView.this.f1602a.playAnimation();
                    }
                });
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            this.f1602a.playAnimation();
        }
        this.b = true;
    }

    public boolean isAnimationLoading() {
        return this.b;
    }
}
