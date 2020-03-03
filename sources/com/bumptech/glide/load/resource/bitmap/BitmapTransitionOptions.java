package com.bumptech.glide.load.resource.bitmap;

import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import com.bumptech.glide.TransitionOptions;
import com.bumptech.glide.request.transition.BitmapTransitionFactory;
import com.bumptech.glide.request.transition.DrawableCrossFadeFactory;
import com.bumptech.glide.request.transition.TransitionFactory;

public final class BitmapTransitionOptions extends TransitionOptions<BitmapTransitionOptions, Bitmap> {
    @NonNull
    public static BitmapTransitionOptions a() {
        return new BitmapTransitionOptions().e();
    }

    @NonNull
    public static BitmapTransitionOptions a(int i) {
        return new BitmapTransitionOptions().c(i);
    }

    @NonNull
    public static BitmapTransitionOptions a(@NonNull DrawableCrossFadeFactory drawableCrossFadeFactory) {
        return new BitmapTransitionOptions().b(drawableCrossFadeFactory);
    }

    @NonNull
    public static BitmapTransitionOptions a(@NonNull DrawableCrossFadeFactory.Builder builder) {
        return new BitmapTransitionOptions().b(builder);
    }

    @NonNull
    public static BitmapTransitionOptions a(@NonNull TransitionFactory<Drawable> transitionFactory) {
        return new BitmapTransitionOptions().d(transitionFactory);
    }

    @NonNull
    public static BitmapTransitionOptions c(@NonNull TransitionFactory<Bitmap> transitionFactory) {
        return (BitmapTransitionOptions) new BitmapTransitionOptions().b(transitionFactory);
    }

    @NonNull
    public BitmapTransitionOptions e() {
        return b(new DrawableCrossFadeFactory.Builder());
    }

    @NonNull
    public BitmapTransitionOptions c(int i) {
        return b(new DrawableCrossFadeFactory.Builder(i));
    }

    @NonNull
    public BitmapTransitionOptions b(@NonNull DrawableCrossFadeFactory drawableCrossFadeFactory) {
        return d(drawableCrossFadeFactory);
    }

    @NonNull
    public BitmapTransitionOptions d(@NonNull TransitionFactory<Drawable> transitionFactory) {
        return (BitmapTransitionOptions) b(new BitmapTransitionFactory(transitionFactory));
    }

    @NonNull
    public BitmapTransitionOptions b(@NonNull DrawableCrossFadeFactory.Builder builder) {
        return d(builder.a());
    }
}
