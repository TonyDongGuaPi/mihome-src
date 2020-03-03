package com.bumptech.glide.request.transition;

import android.content.Context;
import android.view.View;
import android.view.animation.Animation;
import com.bumptech.glide.request.transition.Transition;

public class ViewTransition<R> implements Transition<R> {

    /* renamed from: a  reason: collision with root package name */
    private final ViewTransitionAnimationFactory f5090a;

    interface ViewTransitionAnimationFactory {
        Animation a(Context context);
    }

    ViewTransition(ViewTransitionAnimationFactory viewTransitionAnimationFactory) {
        this.f5090a = viewTransitionAnimationFactory;
    }

    public boolean a(R r, Transition.ViewAdapter viewAdapter) {
        View j = viewAdapter.j();
        if (j == null) {
            return false;
        }
        j.clearAnimation();
        j.startAnimation(this.f5090a.a(j.getContext()));
        return false;
    }
}
