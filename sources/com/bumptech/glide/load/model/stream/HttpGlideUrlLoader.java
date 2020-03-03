package com.bumptech.glide.load.model.stream;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import com.bumptech.glide.load.Option;
import com.bumptech.glide.load.Options;
import com.bumptech.glide.load.data.HttpUrlFetcher;
import com.bumptech.glide.load.model.GlideUrl;
import com.bumptech.glide.load.model.ModelCache;
import com.bumptech.glide.load.model.ModelLoader;
import com.bumptech.glide.load.model.ModelLoaderFactory;
import com.bumptech.glide.load.model.MultiModelLoaderFactory;
import java.io.InputStream;

public class HttpGlideUrlLoader implements ModelLoader<GlideUrl, InputStream> {

    /* renamed from: a  reason: collision with root package name */
    public static final Option<Integer> f4982a = Option.a("com.bumptech.glide.load.model.stream.HttpGlideUrlLoader.Timeout", 2500);
    @Nullable
    private final ModelCache<GlideUrl, GlideUrl> b;

    public boolean a(@NonNull GlideUrl glideUrl) {
        return true;
    }

    public HttpGlideUrlLoader() {
        this((ModelCache<GlideUrl, GlideUrl>) null);
    }

    public HttpGlideUrlLoader(@Nullable ModelCache<GlideUrl, GlideUrl> modelCache) {
        this.b = modelCache;
    }

    public ModelLoader.LoadData<InputStream> a(@NonNull GlideUrl glideUrl, int i, int i2, @NonNull Options options) {
        if (this.b != null) {
            GlideUrl a2 = this.b.a(glideUrl, 0, 0);
            if (a2 == null) {
                this.b.a(glideUrl, 0, 0, glideUrl);
            } else {
                glideUrl = a2;
            }
        }
        return new ModelLoader.LoadData<>(glideUrl, new HttpUrlFetcher(glideUrl, ((Integer) options.a(f4982a)).intValue()));
    }

    public static class Factory implements ModelLoaderFactory<GlideUrl, InputStream> {

        /* renamed from: a  reason: collision with root package name */
        private final ModelCache<GlideUrl, GlideUrl> f4983a = new ModelCache<>(500);

        public void a() {
        }

        @NonNull
        public ModelLoader<GlideUrl, InputStream> a(MultiModelLoaderFactory multiModelLoaderFactory) {
            return new HttpGlideUrlLoader(this.f4983a);
        }
    }
}
