package com.bumptech.glide.request.transition;

import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.request.transition.ViewPropertyTransition;

public class ViewPropertyAnimationFactory<R> implements TransitionFactory<R> {

    /* renamed from: a  reason: collision with root package name */
    private final ViewPropertyTransition.Animator f5088a;
    private ViewPropertyTransition<R> b;

    public ViewPropertyAnimationFactory(ViewPropertyTransition.Animator animator) {
        this.f5088a = animator;
    }

    public Transition<R> a(DataSource dataSource, boolean z) {
        if (dataSource == DataSource.MEMORY_CACHE || !z) {
            return NoTransition.b();
        }
        if (this.b == null) {
            this.b = new ViewPropertyTransition<>(this.f5088a);
        }
        return this.b;
    }
}
