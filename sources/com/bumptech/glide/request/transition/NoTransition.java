package com.bumptech.glide.request.transition;

import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.request.transition.Transition;

public class NoTransition<R> implements Transition<R> {

    /* renamed from: a  reason: collision with root package name */
    static final NoTransition<?> f5084a = new NoTransition<>();
    private static final TransitionFactory<?> b = new NoAnimationFactory();

    public boolean a(Object obj, Transition.ViewAdapter viewAdapter) {
        return false;
    }

    public static class NoAnimationFactory<R> implements TransitionFactory<R> {
        public Transition<R> a(DataSource dataSource, boolean z) {
            return NoTransition.f5084a;
        }
    }

    public static <R> TransitionFactory<R> a() {
        return b;
    }

    public static <R> Transition<R> b() {
        return f5084a;
    }
}
