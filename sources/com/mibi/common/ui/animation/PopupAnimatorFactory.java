package com.mibi.common.ui.animation;

import android.animation.Animator;
import android.animation.AnimatorInflater;
import android.animation.ObjectAnimator;
import android.app.Activity;
import android.widget.FrameLayout;
import com.mibi.common.R;
import com.mibi.common.base.StepFragment;

public class PopupAnimatorFactory implements StepFragment.IAnimatorFactory {
    public int a() {
        return R.animator.mibi_fragment_popup_up_enter;
    }

    public int b() {
        return R.animator.mibi_fragment_popup_down_exit;
    }

    public int c() {
        return R.animator.mibi_fragment_popup_up_enter;
    }

    public int d() {
        return R.animator.mibi_fragment_popup_down_exit;
    }

    public Animator a(Activity activity, int i, boolean z, int i2) {
        if (i2 == 0) {
            return null;
        }
        ObjectAnimator objectAnimator = (ObjectAnimator) AnimatorInflater.loadAnimator(activity, i2);
        int height = ((FrameLayout) activity.findViewById(16908290)).getHeight();
        if (i2 == a()) {
            objectAnimator.setFloatValues(new float[]{(float) height, 0.0f});
        } else if (i2 == b()) {
            objectAnimator.setFloatValues(new float[]{0.0f, (float) height});
        } else if (i2 == c()) {
            objectAnimator.setFloatValues(new float[]{(float) height, 0.0f});
        } else if (i2 == d()) {
            objectAnimator.setFloatValues(new float[]{0.0f, (float) height});
        }
        return objectAnimator;
    }
}
