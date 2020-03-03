package com.bumptech.glide.request.transition;

import android.content.Context;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.request.transition.ViewTransition;

public class ViewAnimationFactory<R> implements TransitionFactory<R> {

    /* renamed from: a  reason: collision with root package name */
    private final ViewTransition.ViewTransitionAnimationFactory f5085a;
    private Transition<R> b;

    public ViewAnimationFactory(Animation animation) {
        this((ViewTransition.ViewTransitionAnimationFactory) new ConcreteViewTransitionAnimationFactory(animation));
    }

    public ViewAnimationFactory(int i) {
        this((ViewTransition.ViewTransitionAnimationFactory) new ResourceViewTransitionAnimationFactory(i));
    }

    ViewAnimationFactory(ViewTransition.ViewTransitionAnimationFactory viewTransitionAnimationFactory) {
        this.f5085a = viewTransitionAnimationFactory;
    }

    public Transition<R> a(DataSource dataSource, boolean z) {
        if (dataSource == DataSource.MEMORY_CACHE || !z) {
            return NoTransition.b();
        }
        if (this.b == null) {
            this.b = new ViewTransition(this.f5085a);
        }
        return this.b;
    }

    private static class ConcreteViewTransitionAnimationFactory implements ViewTransition.ViewTransitionAnimationFactory {

        /* renamed from: a  reason: collision with root package name */
        private final Animation f5086a;

        ConcreteViewTransitionAnimationFactory(Animation animation) {
            this.f5086a = animation;
        }

        public Animation a(Context context) {
            return this.f5086a;
        }
    }

    private static class ResourceViewTransitionAnimationFactory implements ViewTransition.ViewTransitionAnimationFactory {

        /* renamed from: a  reason: collision with root package name */
        private final int f5087a;

        ResourceViewTransitionAnimationFactory(int i) {
            this.f5087a = i;
        }

        public Animation a(Context context) {
            return AnimationUtils.loadAnimation(context, this.f5087a);
        }
    }
}
