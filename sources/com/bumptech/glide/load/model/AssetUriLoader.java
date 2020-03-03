package com.bumptech.glide.load.model;

import android.content.res.AssetManager;
import android.net.Uri;
import android.os.ParcelFileDescriptor;
import android.support.annotation.NonNull;
import com.bumptech.glide.load.Options;
import com.bumptech.glide.load.data.DataFetcher;
import com.bumptech.glide.load.data.FileDescriptorAssetPathFetcher;
import com.bumptech.glide.load.data.StreamAssetPathFetcher;
import com.bumptech.glide.load.model.ModelLoader;
import com.bumptech.glide.signature.ObjectKey;
import java.io.InputStream;

public class AssetUriLoader<Data> implements ModelLoader<Uri, Data> {

    /* renamed from: a  reason: collision with root package name */
    private static final String f4932a = "android_asset";
    private static final String b = "file:///android_asset/";
    private static final int c = b.length();
    private final AssetManager d;
    private final AssetFetcherFactory<Data> e;

    public interface AssetFetcherFactory<Data> {
        DataFetcher<Data> a(AssetManager assetManager, String str);
    }

    public AssetUriLoader(AssetManager assetManager, AssetFetcherFactory<Data> assetFetcherFactory) {
        this.d = assetManager;
        this.e = assetFetcherFactory;
    }

    public ModelLoader.LoadData<Data> a(@NonNull Uri uri, int i, int i2, @NonNull Options options) {
        return new ModelLoader.LoadData<>(new ObjectKey(uri), this.e.a(this.d, uri.toString().substring(c)));
    }

    public boolean a(@NonNull Uri uri) {
        if (!"file".equals(uri.getScheme()) || uri.getPathSegments().isEmpty() || !f4932a.equals(uri.getPathSegments().get(0))) {
            return false;
        }
        return true;
    }

    public static class StreamFactory implements AssetFetcherFactory<InputStream>, ModelLoaderFactory<Uri, InputStream> {

        /* renamed from: a  reason: collision with root package name */
        private final AssetManager f4934a;

        public void a() {
        }

        public StreamFactory(AssetManager assetManager) {
            this.f4934a = assetManager;
        }

        @NonNull
        public ModelLoader<Uri, InputStream> a(MultiModelLoaderFactory multiModelLoaderFactory) {
            return new AssetUriLoader(this.f4934a, this);
        }

        public DataFetcher<InputStream> a(AssetManager assetManager, String str) {
            return new StreamAssetPathFetcher(assetManager, str);
        }
    }

    public static class FileDescriptorFactory implements AssetFetcherFactory<ParcelFileDescriptor>, ModelLoaderFactory<Uri, ParcelFileDescriptor> {

        /* renamed from: a  reason: collision with root package name */
        private final AssetManager f4933a;

        public void a() {
        }

        public FileDescriptorFactory(AssetManager assetManager) {
            this.f4933a = assetManager;
        }

        @NonNull
        public ModelLoader<Uri, ParcelFileDescriptor> a(MultiModelLoaderFactory multiModelLoaderFactory) {
            return new AssetUriLoader(this.f4933a, this);
        }

        public DataFetcher<ParcelFileDescriptor> a(AssetManager assetManager, String str) {
            return new FileDescriptorAssetPathFetcher(assetManager, str);
        }
    }
}
