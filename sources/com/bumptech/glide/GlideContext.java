package com.bumptech.glide;

import android.content.Context;
import android.content.ContextWrapper;
import android.support.annotation.NonNull;
import android.support.annotation.VisibleForTesting;
import android.widget.ImageView;
import com.bumptech.glide.load.engine.Engine;
import com.bumptech.glide.load.engine.bitmap_recycle.ArrayPool;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.RequestOptions;
import com.bumptech.glide.request.target.ImageViewTargetFactory;
import com.bumptech.glide.request.target.ViewTarget;
import java.util.List;
import java.util.Map;

public class GlideContext extends ContextWrapper {
    @VisibleForTesting

    /* renamed from: a  reason: collision with root package name */
    static final TransitionOptions<?, ?> f4809a = new GenericTransitionOptions();
    private final ArrayPool b;
    private final Registry c;
    private final ImageViewTargetFactory d;
    private final RequestOptions e;
    private final List<RequestListener<Object>> f;
    private final Map<Class<?>, TransitionOptions<?, ?>> g;
    private final Engine h;
    private final boolean i;
    private final int j;

    public GlideContext(@NonNull Context context, @NonNull ArrayPool arrayPool, @NonNull Registry registry, @NonNull ImageViewTargetFactory imageViewTargetFactory, @NonNull RequestOptions requestOptions, @NonNull Map<Class<?>, TransitionOptions<?, ?>> map, @NonNull List<RequestListener<Object>> list, @NonNull Engine engine, boolean z, int i2) {
        super(context.getApplicationContext());
        this.b = arrayPool;
        this.c = registry;
        this.d = imageViewTargetFactory;
        this.e = requestOptions;
        this.f = list;
        this.g = map;
        this.h = engine;
        this.i = z;
        this.j = i2;
    }

    public List<RequestListener<Object>> getDefaultRequestListeners() {
        return this.f;
    }

    public RequestOptions getDefaultRequestOptions() {
        return this.e;
    }

    @NonNull
    public <T> TransitionOptions<?, T> getDefaultTransitionOptions(@NonNull Class<T> cls) {
        TransitionOptions<?, T> transitionOptions = this.g.get(cls);
        if (transitionOptions == null) {
            for (Map.Entry next : this.g.entrySet()) {
                if (((Class) next.getKey()).isAssignableFrom(cls)) {
                    transitionOptions = (TransitionOptions) next.getValue();
                }
            }
        }
        return transitionOptions == null ? f4809a : transitionOptions;
    }

    @NonNull
    public <X> ViewTarget<ImageView, X> buildImageViewTarget(@NonNull ImageView imageView, @NonNull Class<X> cls) {
        return this.d.a(imageView, cls);
    }

    @NonNull
    public Engine getEngine() {
        return this.h;
    }

    @NonNull
    public Registry getRegistry() {
        return this.c;
    }

    public int getLogLevel() {
        return this.j;
    }

    @NonNull
    public ArrayPool getArrayPool() {
        return this.b;
    }

    public boolean isLoggingRequestOriginsEnabled() {
        return this.i;
    }
}
