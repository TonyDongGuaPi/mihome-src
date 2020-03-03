package com.bumptech.glide.load.model;

import android.support.annotation.NonNull;
import android.util.Log;
import com.bumptech.glide.Priority;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.Options;
import com.bumptech.glide.load.data.DataFetcher;
import com.bumptech.glide.load.model.ModelLoader;
import com.bumptech.glide.signature.ObjectKey;
import com.bumptech.glide.util.ByteBufferUtil;
import java.io.File;
import java.io.IOException;
import java.nio.ByteBuffer;

public class ByteBufferFileLoader implements ModelLoader<File, ByteBuffer> {

    /* renamed from: a  reason: collision with root package name */
    private static final String f4940a = "ByteBufferFileLoader";

    public boolean a(@NonNull File file) {
        return true;
    }

    public ModelLoader.LoadData<ByteBuffer> a(@NonNull File file, int i, int i2, @NonNull Options options) {
        return new ModelLoader.LoadData<>(new ObjectKey(file), new ByteBufferFetcher(file));
    }

    public static class Factory implements ModelLoaderFactory<File, ByteBuffer> {
        public void a() {
        }

        @NonNull
        public ModelLoader<File, ByteBuffer> a(@NonNull MultiModelLoaderFactory multiModelLoaderFactory) {
            return new ByteBufferFileLoader();
        }
    }

    private static final class ByteBufferFetcher implements DataFetcher<ByteBuffer> {

        /* renamed from: a  reason: collision with root package name */
        private final File f4941a;

        public void b() {
        }

        public void c() {
        }

        ByteBufferFetcher(File file) {
            this.f4941a = file;
        }

        public void a(@NonNull Priority priority, @NonNull DataFetcher.DataCallback<? super ByteBuffer> dataCallback) {
            try {
                dataCallback.a(ByteBufferUtil.a(this.f4941a));
            } catch (IOException e) {
                if (Log.isLoggable(ByteBufferFileLoader.f4940a, 3)) {
                    Log.d(ByteBufferFileLoader.f4940a, "Failed to obtain ByteBuffer for file", e);
                }
                dataCallback.a((Exception) e);
            }
        }

        @NonNull
        public Class<ByteBuffer> a() {
            return ByteBuffer.class;
        }

        @NonNull
        public DataSource d() {
            return DataSource.LOCAL;
        }
    }
}
