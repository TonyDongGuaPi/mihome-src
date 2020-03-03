package com.bumptech.glide.request.transition;

import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.request.transition.Transition;

public abstract class BitmapContainerTransitionFactory<R> implements TransitionFactory<R> {

    /* renamed from: a  reason: collision with root package name */
    private final TransitionFactory<Drawable> f5079a;

    /* access modifiers changed from: protected */
    public abstract Bitmap a(R r);

    public BitmapContainerTransitionFactory(TransitionFactory<Drawable> transitionFactory) {
        this.f5079a = transitionFactory;
    }

    public Transition<R> a(DataSource dataSource, boolean z) {
        return new BitmapGlideAnimation(this.f5079a.a(dataSource, z));
    }

    private final class BitmapGlideAnimation implements Transition<R> {
        private final Transition<Drawable> b;

        BitmapGlideAnimation(Transition<Drawable> transition) {
            this.b = transition;
        }

        public boolean a(R r, Transition.ViewAdapter viewAdapter) {
            return this.b.a(new BitmapDrawable(viewAdapter.j().getResources(), BitmapContainerTransitionFactory.this.a(r)), viewAdapter);
        }
    }
}
