package com.bumptech.glide.load.resource.drawable;

import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import com.bumptech.glide.TransitionOptions;
import com.bumptech.glide.request.transition.DrawableCrossFadeFactory;
import com.bumptech.glide.request.transition.TransitionFactory;

public final class DrawableTransitionOptions extends TransitionOptions<DrawableTransitionOptions, Drawable> {
    @NonNull
    public static DrawableTransitionOptions a() {
        return new DrawableTransitionOptions().e();
    }

    @NonNull
    public static DrawableTransitionOptions a(int i) {
        return new DrawableTransitionOptions().c(i);
    }

    @NonNull
    public static DrawableTransitionOptions a(@NonNull DrawableCrossFadeFactory drawableCrossFadeFactory) {
        return new DrawableTransitionOptions().b(drawableCrossFadeFactory);
    }

    @NonNull
    public static DrawableTransitionOptions a(@NonNull DrawableCrossFadeFactory.Builder builder) {
        return new DrawableTransitionOptions().b(builder);
    }

    @NonNull
    public static DrawableTransitionOptions a(@NonNull TransitionFactory<Drawable> transitionFactory) {
        return (DrawableTransitionOptions) new DrawableTransitionOptions().b(transitionFactory);
    }

    @NonNull
    public DrawableTransitionOptions e() {
        return b(new DrawableCrossFadeFactory.Builder());
    }

    @NonNull
    public DrawableTransitionOptions c(int i) {
        return b(new DrawableCrossFadeFactory.Builder(i));
    }

    @NonNull
    public DrawableTransitionOptions b(@NonNull DrawableCrossFadeFactory drawableCrossFadeFactory) {
        return (DrawableTransitionOptions) b(drawableCrossFadeFactory);
    }

    @NonNull
    public DrawableTransitionOptions b(@NonNull DrawableCrossFadeFactory.Builder builder) {
        return b(builder.a());
    }
}
