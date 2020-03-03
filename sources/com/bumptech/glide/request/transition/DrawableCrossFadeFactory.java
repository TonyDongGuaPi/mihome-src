package com.bumptech.glide.request.transition;

import android.graphics.drawable.Drawable;
import com.bumptech.glide.load.DataSource;

public class DrawableCrossFadeFactory implements TransitionFactory<Drawable> {

    /* renamed from: a  reason: collision with root package name */
    private final int f5081a;
    private final boolean b;
    private DrawableCrossFadeTransition c;

    protected DrawableCrossFadeFactory(int i, boolean z) {
        this.f5081a = i;
        this.b = z;
    }

    public Transition<Drawable> a(DataSource dataSource, boolean z) {
        return dataSource == DataSource.MEMORY_CACHE ? NoTransition.b() : a();
    }

    private Transition<Drawable> a() {
        if (this.c == null) {
            this.c = new DrawableCrossFadeTransition(this.f5081a, this.b);
        }
        return this.c;
    }

    public static class Builder {

        /* renamed from: a  reason: collision with root package name */
        private static final int f5082a = 300;
        private final int b;
        private boolean c;

        public Builder() {
            this(300);
        }

        public Builder(int i) {
            this.b = i;
        }

        public Builder a(boolean z) {
            this.c = z;
            return this;
        }

        public DrawableCrossFadeFactory a() {
            return new DrawableCrossFadeFactory(this.b, this.c);
        }
    }
}
