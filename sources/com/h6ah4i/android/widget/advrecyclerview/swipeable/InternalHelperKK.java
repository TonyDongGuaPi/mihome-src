package com.h6ah4i.android.widget.advrecyclerview.swipeable;

import android.animation.ValueAnimator;
import android.annotation.TargetApi;
import android.view.View;

class InternalHelperKK {
    InternalHelperKK() {
    }

    @TargetApi(19)
    public static void a(View view) {
        view.animate().setUpdateListener((ValueAnimator.AnimatorUpdateListener) null);
    }
}
