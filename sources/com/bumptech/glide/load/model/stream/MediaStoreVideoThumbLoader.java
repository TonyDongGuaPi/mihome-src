package com.bumptech.glide.load.model.stream;

import android.content.Context;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import com.bumptech.glide.load.Options;
import com.bumptech.glide.load.data.mediastore.MediaStoreUtil;
import com.bumptech.glide.load.data.mediastore.ThumbFetcher;
import com.bumptech.glide.load.model.ModelLoader;
import com.bumptech.glide.load.model.ModelLoaderFactory;
import com.bumptech.glide.load.model.MultiModelLoaderFactory;
import com.bumptech.glide.load.resource.bitmap.VideoDecoder;
import com.bumptech.glide.signature.ObjectKey;
import java.io.InputStream;

public class MediaStoreVideoThumbLoader implements ModelLoader<Uri, InputStream> {

    /* renamed from: a  reason: collision with root package name */
    private final Context f4987a;

    public MediaStoreVideoThumbLoader(Context context) {
        this.f4987a = context.getApplicationContext();
    }

    @Nullable
    public ModelLoader.LoadData<InputStream> a(@NonNull Uri uri, int i, int i2, @NonNull Options options) {
        if (!MediaStoreUtil.a(i, i2) || !a(options)) {
            return null;
        }
        return new ModelLoader.LoadData<>(new ObjectKey(uri), ThumbFetcher.b(this.f4987a, uri));
    }

    private boolean a(Options options) {
        Long l = (Long) options.a(VideoDecoder.c);
        return l != null && l.longValue() == -1;
    }

    public boolean a(@NonNull Uri uri) {
        return MediaStoreUtil.b(uri);
    }

    public static class Factory implements ModelLoaderFactory<Uri, InputStream> {

        /* renamed from: a  reason: collision with root package name */
        private final Context f4988a;

        public void a() {
        }

        public Factory(Context context) {
            this.f4988a = context;
        }

        @NonNull
        public ModelLoader<Uri, InputStream> a(MultiModelLoaderFactory multiModelLoaderFactory) {
            return new MediaStoreVideoThumbLoader(this.f4988a);
        }
    }
}
