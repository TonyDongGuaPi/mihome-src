package com.bumptech.glide.load.model.stream;

import android.support.annotation.NonNull;
import com.bumptech.glide.load.Options;
import com.bumptech.glide.load.model.GlideUrl;
import com.bumptech.glide.load.model.ModelLoader;
import com.bumptech.glide.load.model.ModelLoaderFactory;
import com.bumptech.glide.load.model.MultiModelLoaderFactory;
import java.io.InputStream;
import java.net.URL;

public class UrlLoader implements ModelLoader<URL, InputStream> {

    /* renamed from: a  reason: collision with root package name */
    private final ModelLoader<GlideUrl, InputStream> f4989a;

    public boolean a(@NonNull URL url) {
        return true;
    }

    public UrlLoader(ModelLoader<GlideUrl, InputStream> modelLoader) {
        this.f4989a = modelLoader;
    }

    public ModelLoader.LoadData<InputStream> a(@NonNull URL url, int i, int i2, @NonNull Options options) {
        return this.f4989a.a(new GlideUrl(url), i, i2, options);
    }

    public static class StreamFactory implements ModelLoaderFactory<URL, InputStream> {
        public void a() {
        }

        @NonNull
        public ModelLoader<URL, InputStream> a(MultiModelLoaderFactory multiModelLoaderFactory) {
            return new UrlLoader(multiModelLoaderFactory.b(GlideUrl.class, InputStream.class));
        }
    }
}
