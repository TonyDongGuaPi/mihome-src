package com.bumptech.glide.load.model.stream;

import android.net.Uri;
import android.support.annotation.NonNull;
import com.bumptech.glide.load.Options;
import com.bumptech.glide.load.model.GlideUrl;
import com.bumptech.glide.load.model.ModelLoader;
import com.bumptech.glide.load.model.ModelLoaderFactory;
import com.bumptech.glide.load.model.MultiModelLoaderFactory;
import java.io.InputStream;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

public class HttpUriLoader implements ModelLoader<Uri, InputStream> {

    /* renamed from: a  reason: collision with root package name */
    private static final Set<String> f4984a = Collections.unmodifiableSet(new HashSet(Arrays.asList(new String[]{"http", "https"})));
    private final ModelLoader<GlideUrl, InputStream> b;

    public HttpUriLoader(ModelLoader<GlideUrl, InputStream> modelLoader) {
        this.b = modelLoader;
    }

    public ModelLoader.LoadData<InputStream> a(@NonNull Uri uri, int i, int i2, @NonNull Options options) {
        return this.b.a(new GlideUrl(uri.toString()), i, i2, options);
    }

    public boolean a(@NonNull Uri uri) {
        return f4984a.contains(uri.getScheme());
    }

    public static class Factory implements ModelLoaderFactory<Uri, InputStream> {
        public void a() {
        }

        @NonNull
        public ModelLoader<Uri, InputStream> a(MultiModelLoaderFactory multiModelLoaderFactory) {
            return new HttpUriLoader(multiModelLoaderFactory.b(GlideUrl.class, InputStream.class));
        }
    }
}
