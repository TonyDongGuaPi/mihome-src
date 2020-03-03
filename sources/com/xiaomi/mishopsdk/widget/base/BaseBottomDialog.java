package com.xiaomi.mishopsdk.widget.base;

import android.content.Context;
import android.view.WindowManager;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.LinearInterpolator;
import android.view.animation.TranslateAnimation;

@Deprecated
public abstract class BaseBottomDialog extends BaseDialog {
    private static final long ANIM_DURATION = 300;

    public BaseBottomDialog(Context context) {
        super(context);
    }

    /* access modifiers changed from: protected */
    public Animation getShowAnimation(Context context) {
        TranslateAnimation translateAnimation = new TranslateAnimation(1, 0.0f, 1, 0.0f, 1, 1.0f, 1, 0.0f);
        translateAnimation.setDuration(ANIM_DURATION);
        translateAnimation.setInterpolator(new AccelerateDecelerateInterpolator());
        return translateAnimation;
    }

    /* access modifiers changed from: protected */
    public Animation getDismissAnimation(Context context) {
        AnimationSet animationSet = new AnimationSet(true);
        TranslateAnimation translateAnimation = new TranslateAnimation(1, 0.0f, 1, 0.0f, 1, 0.0f, 1, 1.0f);
        AlphaAnimation alphaAnimation = new AlphaAnimation(1.0f, 0.0f);
        animationSet.addAnimation(translateAnimation);
        animationSet.addAnimation(alphaAnimation);
        animationSet.setDuration(ANIM_DURATION);
        animationSet.setInterpolator(new LinearInterpolator());
        return animationSet;
    }

    /* access modifiers changed from: protected */
    public void customLayoutParams(WindowManager.LayoutParams layoutParams) {
        layoutParams.width = -1;
        layoutParams.height = -2;
        layoutParams.gravity = 80;
    }
}
