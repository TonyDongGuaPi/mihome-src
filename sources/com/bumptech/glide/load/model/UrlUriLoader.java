package com.bumptech.glide.load.model;

import android.net.Uri;
import android.support.annotation.NonNull;
import com.bumptech.glide.load.Options;
import com.bumptech.glide.load.model.ModelLoader;
import java.io.InputStream;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

public class UrlUriLoader<Data> implements ModelLoader<Uri, Data> {

    /* renamed from: a  reason: collision with root package name */
    private static final Set<String> f4980a = Collections.unmodifiableSet(new HashSet(Arrays.asList(new String[]{"http", "https"})));
    private final ModelLoader<GlideUrl, Data> b;

    public UrlUriLoader(ModelLoader<GlideUrl, Data> modelLoader) {
        this.b = modelLoader;
    }

    public ModelLoader.LoadData<Data> a(@NonNull Uri uri, int i, int i2, @NonNull Options options) {
        return this.b.a(new GlideUrl(uri.toString()), i, i2, options);
    }

    public boolean a(@NonNull Uri uri) {
        return f4980a.contains(uri.getScheme());
    }

    public static class StreamFactory implements ModelLoaderFactory<Uri, InputStream> {
        public void a() {
        }

        @NonNull
        public ModelLoader<Uri, InputStream> a(MultiModelLoaderFactory multiModelLoaderFactory) {
            return new UrlUriLoader(multiModelLoaderFactory.b(GlideUrl.class, InputStream.class));
        }
    }
}
