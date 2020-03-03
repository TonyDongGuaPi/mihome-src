package com.bumptech.glide.load.model;

import android.content.res.AssetFileDescriptor;
import android.net.Uri;
import android.os.ParcelFileDescriptor;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import com.bumptech.glide.load.Options;
import com.bumptech.glide.load.model.ModelLoader;
import java.io.File;
import java.io.InputStream;

public class StringLoader<Data> implements ModelLoader<String, Data> {

    /* renamed from: a  reason: collision with root package name */
    private final ModelLoader<Uri, Data> f4972a;

    public boolean a(@NonNull String str) {
        return true;
    }

    public StringLoader(ModelLoader<Uri, Data> modelLoader) {
        this.f4972a = modelLoader;
    }

    public ModelLoader.LoadData<Data> a(@NonNull String str, int i, int i2, @NonNull Options options) {
        Uri b = b(str);
        if (b == null || !this.f4972a.a(b)) {
            return null;
        }
        return this.f4972a.a(b, i, i2, options);
    }

    @Nullable
    private static Uri b(String str) {
        if (TextUtils.isEmpty(str)) {
            return null;
        }
        if (str.charAt(0) == '/') {
            return c(str);
        }
        Uri parse = Uri.parse(str);
        return parse.getScheme() == null ? c(str) : parse;
    }

    private static Uri c(String str) {
        return Uri.fromFile(new File(str));
    }

    public static class StreamFactory implements ModelLoaderFactory<String, InputStream> {
        public void a() {
        }

        @NonNull
        public ModelLoader<String, InputStream> a(@NonNull MultiModelLoaderFactory multiModelLoaderFactory) {
            return new StringLoader(multiModelLoaderFactory.b(Uri.class, InputStream.class));
        }
    }

    public static class FileDescriptorFactory implements ModelLoaderFactory<String, ParcelFileDescriptor> {
        public void a() {
        }

        @NonNull
        public ModelLoader<String, ParcelFileDescriptor> a(@NonNull MultiModelLoaderFactory multiModelLoaderFactory) {
            return new StringLoader(multiModelLoaderFactory.b(Uri.class, ParcelFileDescriptor.class));
        }
    }

    public static final class AssetFileDescriptorFactory implements ModelLoaderFactory<String, AssetFileDescriptor> {
        public void a() {
        }

        public ModelLoader<String, AssetFileDescriptor> a(@NonNull MultiModelLoaderFactory multiModelLoaderFactory) {
            return new StringLoader(multiModelLoaderFactory.b(Uri.class, AssetFileDescriptor.class));
        }
    }
}
