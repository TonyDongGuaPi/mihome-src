package com.mi.global.bbs.utils;

import android.support.v4.view.ViewPager;
import android.view.View;

public class BannerPageTransformer implements ViewPager.PageTransformer {
    private boolean isShowAlpha = true;
    private float mMinAlpha = 0.65f;
    private float mMinScale = 0.92f;

    public BannerPageTransformer() {
    }

    public BannerPageTransformer(boolean z) {
        this.isShowAlpha = z;
    }

    public BannerPageTransformer(float f, float f2) {
        setMinAlpha(f);
        setMinScale(f2);
    }

    public void transformPage(View view, float f) {
        if (f < -1.0f) {
            handleInvisiblePage(view, f);
        } else if (f <= 0.0f) {
            handleLeftPage(view, f);
        } else if (f <= 1.0f) {
            handleRightPage(view, f);
        } else {
            handleInvisiblePage(view, f);
        }
    }

    public void handleInvisiblePage(View view, float f) {
        if (this.isShowAlpha) {
            view.setAlpha(0.0f);
        }
    }

    public void handleLeftPage(View view, float f) {
        handler(view, f);
    }

    public void handleRightPage(View view, float f) {
        handler(view, f);
    }

    private void handler(View view, float f) {
        view.getWidth();
        view.getHeight();
        float max = Math.max(this.mMinScale, 1.0f - Math.abs(f));
        view.setScaleX(max);
        if (this.isShowAlpha) {
            view.setAlpha(this.mMinAlpha + (((max - this.mMinScale) / (1.0f - this.mMinScale)) * (1.0f - this.mMinAlpha)));
        }
    }

    public void setMinAlpha(float f) {
        this.mMinAlpha = f;
    }

    public void setMinScale(float f) {
        this.mMinScale = f;
    }
}
