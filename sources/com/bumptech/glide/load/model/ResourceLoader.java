package com.bumptech.glide.load.model;

import android.content.res.AssetFileDescriptor;
import android.content.res.Resources;
import android.net.Uri;
import android.os.ParcelFileDescriptor;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;
import com.bumptech.glide.load.Options;
import com.bumptech.glide.load.model.ModelLoader;
import com.xiaomi.smarthome.fastvideo.IOUtils;
import java.io.InputStream;

public class ResourceLoader<Data> implements ModelLoader<Integer, Data> {

    /* renamed from: a  reason: collision with root package name */
    private static final String f4966a = "ResourceLoader";
    private final ModelLoader<Uri, Data> b;
    private final Resources c;

    public boolean a(@NonNull Integer num) {
        return true;
    }

    public ResourceLoader(Resources resources, ModelLoader<Uri, Data> modelLoader) {
        this.c = resources;
        this.b = modelLoader;
    }

    public ModelLoader.LoadData<Data> a(@NonNull Integer num, int i, int i2, @NonNull Options options) {
        Uri b2 = b(num);
        if (b2 == null) {
            return null;
        }
        return this.b.a(b2, i, i2, options);
    }

    @Nullable
    private Uri b(Integer num) {
        try {
            return Uri.parse("android.resource://" + this.c.getResourcePackageName(num.intValue()) + IOUtils.f15883a + this.c.getResourceTypeName(num.intValue()) + IOUtils.f15883a + this.c.getResourceEntryName(num.intValue()));
        } catch (Resources.NotFoundException e) {
            if (!Log.isLoggable(f4966a, 5)) {
                return null;
            }
            Log.w(f4966a, "Received invalid resource id: " + num, e);
            return null;
        }
    }

    public static class StreamFactory implements ModelLoaderFactory<Integer, InputStream> {

        /* renamed from: a  reason: collision with root package name */
        private final Resources f4969a;

        public void a() {
        }

        public StreamFactory(Resources resources) {
            this.f4969a = resources;
        }

        @NonNull
        public ModelLoader<Integer, InputStream> a(MultiModelLoaderFactory multiModelLoaderFactory) {
            return new ResourceLoader(this.f4969a, multiModelLoaderFactory.b(Uri.class, InputStream.class));
        }
    }

    public static class FileDescriptorFactory implements ModelLoaderFactory<Integer, ParcelFileDescriptor> {

        /* renamed from: a  reason: collision with root package name */
        private final Resources f4968a;

        public void a() {
        }

        public FileDescriptorFactory(Resources resources) {
            this.f4968a = resources;
        }

        @NonNull
        public ModelLoader<Integer, ParcelFileDescriptor> a(MultiModelLoaderFactory multiModelLoaderFactory) {
            return new ResourceLoader(this.f4968a, multiModelLoaderFactory.b(Uri.class, ParcelFileDescriptor.class));
        }
    }

    public static final class AssetFileDescriptorFactory implements ModelLoaderFactory<Integer, AssetFileDescriptor> {

        /* renamed from: a  reason: collision with root package name */
        private final Resources f4967a;

        public void a() {
        }

        public AssetFileDescriptorFactory(Resources resources) {
            this.f4967a = resources;
        }

        public ModelLoader<Integer, AssetFileDescriptor> a(MultiModelLoaderFactory multiModelLoaderFactory) {
            return new ResourceLoader(this.f4967a, multiModelLoaderFactory.b(Uri.class, AssetFileDescriptor.class));
        }
    }

    public static class UriFactory implements ModelLoaderFactory<Integer, Uri> {

        /* renamed from: a  reason: collision with root package name */
        private final Resources f4970a;

        public void a() {
        }

        public UriFactory(Resources resources) {
            this.f4970a = resources;
        }

        @NonNull
        public ModelLoader<Integer, Uri> a(MultiModelLoaderFactory multiModelLoaderFactory) {
            return new ResourceLoader(this.f4970a, UnitModelLoader.a());
        }
    }
}
