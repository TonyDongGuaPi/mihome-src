package com.bumptech.glide.load.model;

import android.support.annotation.NonNull;
import android.util.Base64;
import com.bumptech.glide.Priority;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.Options;
import com.bumptech.glide.load.data.DataFetcher;
import com.bumptech.glide.load.model.ModelLoader;
import com.bumptech.glide.signature.ObjectKey;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;

public final class DataUrlLoader<Model, Data> implements ModelLoader<Model, Data> {

    /* renamed from: a  reason: collision with root package name */
    private static final String f4942a = "data:image";
    private static final String b = ";base64";
    private final DataDecoder<Data> c;

    public interface DataDecoder<Data> {
        Class<Data> a();

        Data a(String str) throws IllegalArgumentException;

        void a(Data data) throws IOException;
    }

    public DataUrlLoader(DataDecoder<Data> dataDecoder) {
        this.c = dataDecoder;
    }

    public ModelLoader.LoadData<Data> a(@NonNull Model model, int i, int i2, @NonNull Options options) {
        return new ModelLoader.LoadData<>(new ObjectKey(model), new DataUriFetcher(model.toString(), this.c));
    }

    public boolean a(@NonNull Model model) {
        return model.toString().startsWith(f4942a);
    }

    private static final class DataUriFetcher<Data> implements DataFetcher<Data> {

        /* renamed from: a  reason: collision with root package name */
        private final String f4943a;
        private final DataDecoder<Data> b;
        private Data c;

        public void c() {
        }

        DataUriFetcher(String str, DataDecoder<Data> dataDecoder) {
            this.f4943a = str;
            this.b = dataDecoder;
        }

        public void a(@NonNull Priority priority, @NonNull DataFetcher.DataCallback<? super Data> dataCallback) {
            try {
                this.c = this.b.a(this.f4943a);
                dataCallback.a(this.c);
            } catch (IllegalArgumentException e) {
                dataCallback.a((Exception) e);
            }
        }

        public void b() {
            try {
                this.b.a(this.c);
            } catch (IOException unused) {
            }
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

    public static final class StreamFactory<Model> implements ModelLoaderFactory<Model, InputStream> {

        /* renamed from: a  reason: collision with root package name */
        private final DataDecoder<InputStream> f4944a = new DataDecoder<InputStream>() {
            /* renamed from: b */
            public InputStream a(String str) {
                if (str.startsWith(DataUrlLoader.f4942a)) {
                    int indexOf = str.indexOf(44);
                    if (indexOf == -1) {
                        throw new IllegalArgumentException("Missing comma in data URL.");
                    } else if (str.substring(0, indexOf).endsWith(DataUrlLoader.b)) {
                        return new ByteArrayInputStream(Base64.decode(str.substring(indexOf + 1), 0));
                    } else {
                        throw new IllegalArgumentException("Not a base64 image data URL.");
                    }
                } else {
                    throw new IllegalArgumentException("Not a valid image data URL.");
                }
            }

            public void a(InputStream inputStream) throws IOException {
                inputStream.close();
            }

            public Class<InputStream> a() {
                return InputStream.class;
            }
        };

        public void a() {
        }

        @NonNull
        public ModelLoader<Model, InputStream> a(@NonNull MultiModelLoaderFactory multiModelLoaderFactory) {
            return new DataUrlLoader(this.f4944a);
        }
    }
}
