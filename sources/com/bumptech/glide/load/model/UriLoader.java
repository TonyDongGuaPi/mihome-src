package com.bumptech.glide.load.model;

import android.content.ContentResolver;
import android.content.res.AssetFileDescriptor;
import android.net.Uri;
import android.os.ParcelFileDescriptor;
import android.support.annotation.NonNull;
import com.bumptech.glide.load.Options;
import com.bumptech.glide.load.data.AssetFileDescriptorLocalUriFetcher;
import com.bumptech.glide.load.data.DataFetcher;
import com.bumptech.glide.load.data.FileDescriptorLocalUriFetcher;
import com.bumptech.glide.load.data.StreamLocalUriFetcher;
import com.bumptech.glide.load.model.ModelLoader;
import com.bumptech.glide.signature.ObjectKey;
import com.facebook.common.util.UriUtil;
import java.io.InputStream;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

public class UriLoader<Data> implements ModelLoader<Uri, Data> {

    /* renamed from: a  reason: collision with root package name */
    private static final Set<String> f4976a = Collections.unmodifiableSet(new HashSet(Arrays.asList(new String[]{"file", UriUtil.QUALIFIED_RESOURCE_SCHEME, "content"})));
    private final LocalUriFetcherFactory<Data> b;

    public interface LocalUriFetcherFactory<Data> {
        DataFetcher<Data> a(Uri uri);
    }

    public UriLoader(LocalUriFetcherFactory<Data> localUriFetcherFactory) {
        this.b = localUriFetcherFactory;
    }

    public ModelLoader.LoadData<Data> a(@NonNull Uri uri, int i, int i2, @NonNull Options options) {
        return new ModelLoader.LoadData<>(new ObjectKey(uri), this.b.a(uri));
    }

    public boolean a(@NonNull Uri uri) {
        return f4976a.contains(uri.getScheme());
    }

    public static class StreamFactory implements ModelLoaderFactory<Uri, InputStream>, LocalUriFetcherFactory<InputStream> {

        /* renamed from: a  reason: collision with root package name */
        private final ContentResolver f4979a;

        public void a() {
        }

        public StreamFactory(ContentResolver contentResolver) {
            this.f4979a = contentResolver;
        }

        public DataFetcher<InputStream> a(Uri uri) {
            return new StreamLocalUriFetcher(this.f4979a, uri);
        }

        @NonNull
        public ModelLoader<Uri, InputStream> a(MultiModelLoaderFactory multiModelLoaderFactory) {
            return new UriLoader(this);
        }
    }

    public static class FileDescriptorFactory implements ModelLoaderFactory<Uri, ParcelFileDescriptor>, LocalUriFetcherFactory<ParcelFileDescriptor> {

        /* renamed from: a  reason: collision with root package name */
        private final ContentResolver f4978a;

        public void a() {
        }

        public FileDescriptorFactory(ContentResolver contentResolver) {
            this.f4978a = contentResolver;
        }

        public DataFetcher<ParcelFileDescriptor> a(Uri uri) {
            return new FileDescriptorLocalUriFetcher(this.f4978a, uri);
        }

        @NonNull
        public ModelLoader<Uri, ParcelFileDescriptor> a(MultiModelLoaderFactory multiModelLoaderFactory) {
            return new UriLoader(this);
        }
    }

    public static final class AssetFileDescriptorFactory implements ModelLoaderFactory<Uri, AssetFileDescriptor>, LocalUriFetcherFactory<AssetFileDescriptor> {

        /* renamed from: a  reason: collision with root package name */
        private final ContentResolver f4977a;

        public void a() {
        }

        public AssetFileDescriptorFactory(ContentResolver contentResolver) {
            this.f4977a = contentResolver;
        }

        public ModelLoader<Uri, AssetFileDescriptor> a(MultiModelLoaderFactory multiModelLoaderFactory) {
            return new UriLoader(this);
        }

        public DataFetcher<AssetFileDescriptor> a(Uri uri) {
            return new AssetFileDescriptorLocalUriFetcher(this.f4977a, uri);
        }
    }
}
