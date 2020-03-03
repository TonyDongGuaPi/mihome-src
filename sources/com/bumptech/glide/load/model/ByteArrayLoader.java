package com.bumptech.glide.load.model;

import android.support.annotation.NonNull;
import com.bumptech.glide.Priority;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.Options;
import com.bumptech.glide.load.data.DataFetcher;
import com.bumptech.glide.load.model.ModelLoader;
import com.bumptech.glide.signature.ObjectKey;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.nio.ByteBuffer;

public class ByteArrayLoader<Data> implements ModelLoader<byte[], Data> {

    /* renamed from: a  reason: collision with root package name */
    private final Converter<Data> f4935a;

    public interface Converter<Data> {
        Class<Data> a();

        Data b(byte[] bArr);
    }

    public boolean a(@NonNull byte[] bArr) {
        return true;
    }

    public ByteArrayLoader(Converter<Data> converter) {
        this.f4935a = converter;
    }

    public ModelLoader.LoadData<Data> a(@NonNull byte[] bArr, int i, int i2, @NonNull Options options) {
        return new ModelLoader.LoadData<>(new ObjectKey(bArr), new Fetcher(bArr, this.f4935a));
    }

    private static class Fetcher<Data> implements DataFetcher<Data> {

        /* renamed from: a  reason: collision with root package name */
        private final byte[] f4937a;
        private final Converter<Data> b;

        public void b() {
        }

        public void c() {
        }

        Fetcher(byte[] bArr, Converter<Data> converter) {
            this.f4937a = bArr;
            this.b = converter;
        }

        public void a(@NonNull Priority priority, @NonNull DataFetcher.DataCallback<? super Data> dataCallback) {
            dataCallback.a(this.b.b(this.f4937a));
        }

        @NonNull
        public Class<Data> a() {
            return this.b.a();
        }

        @NonNull
        public DataSource d() {
            return DataSource.LOCAL;
        }
    }

    public static class ByteBufferFactory implements ModelLoaderFactory<byte[], ByteBuffer> {
        public void a() {
        }

        @NonNull
        public ModelLoader<byte[], ByteBuffer> a(@NonNull MultiModelLoaderFactory multiModelLoaderFactory) {
            return new ByteArrayLoader(new Converter<ByteBuffer>() {
                /* renamed from: a */
                public ByteBuffer b(byte[] bArr) {
                    return ByteBuffer.wrap(bArr);
                }

                public Class<ByteBuffer> a() {
                    return ByteBuffer.class;
                }
            });
        }
    }

    public static class StreamFactory implements ModelLoaderFactory<byte[], InputStream> {
        public void a() {
        }

        @NonNull
        public ModelLoader<byte[], InputStream> a(@NonNull MultiModelLoaderFactory multiModelLoaderFactory) {
            return new ByteArrayLoader(new Converter<InputStream>() {
                /* renamed from: a */
                public InputStream b(byte[] bArr) {
                    return new ByteArrayInputStream(bArr);
                }

                public Class<InputStream> a() {
                    return InputStream.class;
                }
            });
        }
    }
}
