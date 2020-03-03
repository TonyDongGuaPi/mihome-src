package com.mics.widget;

import android.view.animation.AccelerateInterpolator;
import android.view.animation.AlphaAnimation;
import android.view.animation.AnimationSet;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.TranslateAnimation;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class NewMessageHintManager {

    /* renamed from: a  reason: collision with root package name */
    private RelativeLayout f7814a;
    private TextView b;

    private NewMessageHintManager(RelativeLayout relativeLayout, TextView textView) {
        this.f7814a = relativeLayout;
        this.b = textView;
    }

    public static NewMessageHintManager a(RelativeLayout relativeLayout, TextView textView) {
        return new NewMessageHintManager(relativeLayout, textView);
    }

    public void a(CharSequence charSequence) {
        this.b.setText(charSequence);
        if (this.f7814a.getVisibility() == 0) {
            this.f7814a.setVisibility(8);
            AnimationSet animationSet = new AnimationSet(false);
            TranslateAnimation translateAnimation = new TranslateAnimation(1, 0.0f, 1, 0.4f, 1, 0.0f, 1, 0.0f);
            translateAnimation.setInterpolator(new AccelerateInterpolator());
            translateAnimation.setDuration(200);
            TranslateAnimation translateAnimation2 = new TranslateAnimation(1, 0.0f, 1, -0.4f, 1, 0.0f, 1, 0.0f);
            translateAnimation2.setInterpolator(new DecelerateInterpolator());
            translateAnimation2.setDuration(200);
            translateAnimation2.setStartOffset(200);
            animationSet.addAnimation(translateAnimation);
            animationSet.addAnimation(translateAnimation2);
            this.f7814a.startAnimation(animationSet);
            this.f7814a.setVisibility(0);
            return;
        }
        this.f7814a.setVisibility(0);
        AnimationSet animationSet2 = new AnimationSet(true);
        TranslateAnimation translateAnimation3 = new TranslateAnimation(1, 1.0f, 1, 0.0f, 1, 0.0f, 1, 0.0f);
        AlphaAnimation alphaAnimation = new AlphaAnimation(0.6f, 1.0f);
        animationSet2.addAnimation(translateAnimation3);
        animationSet2.addAnimation(alphaAnimation);
        animationSet2.setDuration(400);
        this.f7814a.startAnimation(animationSet2);
    }

    public void a() {
        if (this.f7814a.getVisibility() != 8) {
            this.f7814a.setVisibility(8);
            AnimationSet animationSet = new AnimationSet(true);
            TranslateAnimation translateAnimation = new TranslateAnimation(1, 0.0f, 1, 1.0f, 1, 0.0f, 1, 0.0f);
            AlphaAnimation alphaAnimation = new AlphaAnimation(1.0f, 0.0f);
            animationSet.addAnimation(translateAnimation);
            animationSet.addAnimation(alphaAnimation);
            animationSet.setDuration(200);
            this.f7814a.startAnimation(animationSet);
        }
    }
}
