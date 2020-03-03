package com.bumptech.glide.load.model.stream;

import android.content.Context;
import android.net.Uri;
import android.support.annotation.NonNull;
import com.bumptech.glide.load.Options;
import com.bumptech.glide.load.data.mediastore.MediaStoreUtil;
import com.bumptech.glide.load.data.mediastore.ThumbFetcher;
import com.bumptech.glide.load.model.ModelLoader;
import com.bumptech.glide.load.model.ModelLoaderFactory;
import com.bumptech.glide.load.model.MultiModelLoaderFactory;
import com.bumptech.glide.signature.ObjectKey;
import java.io.InputStream;

public class MediaStoreImageThumbLoader implements ModelLoader<Uri, InputStream> {

    /* renamed from: a  reason: collision with root package name */
    private final Context f4985a;

    public MediaStoreImageThumbLoader(Context context) {
        this.f4985a = context.getApplicationContext();
    }

    public ModelLoader.LoadData<InputStream> a(@NonNull Uri uri, int i, int i2, @NonNull Options options) {
        if (MediaStoreUtil.a(i, i2)) {
            return new ModelLoader.LoadData<>(new ObjectKey(uri), ThumbFetcher.a(this.f4985a, uri));
        }
        return null;
    }

    public boolean a(@NonNull Uri uri) {
        return MediaStoreUtil.c(uri);
    }

    public static class Factory implements ModelLoaderFactory<Uri, InputStream> {

        /* renamed from: a  reason: collision with root package name */
        private final Context f4986a;

        public void a() {
        }

        public Factory(Context context) {
            this.f4986a = context;
        }

        @NonNull
        public ModelLoader<Uri, InputStream> a(MultiModelLoaderFactory multiModelLoaderFactory) {
            return new MediaStoreImageThumbLoader(this.f4986a);
        }
    }
}
