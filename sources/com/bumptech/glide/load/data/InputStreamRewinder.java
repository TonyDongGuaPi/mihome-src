package com.bumptech.glide.load.data;

import android.support.annotation.NonNull;
import com.bumptech.glide.load.data.DataRewinder;
import com.bumptech.glide.load.engine.bitmap_recycle.ArrayPool;
import com.bumptech.glide.load.resource.bitmap.RecyclableBufferedInputStream;
import java.io.IOException;
import java.io.InputStream;

public final class InputStreamRewinder implements DataRewinder<InputStream> {

    /* renamed from: a  reason: collision with root package name */
    private static final int f4843a = 5242880;
    private final RecyclableBufferedInputStream b;

    InputStreamRewinder(InputStream inputStream, ArrayPool arrayPool) {
        this.b = new RecyclableBufferedInputStream(inputStream, arrayPool);
        this.b.mark(f4843a);
    }

    @NonNull
    /* renamed from: c */
    public InputStream a() throws IOException {
        this.b.reset();
        return this.b;
    }

    public void b() {
        this.b.b();
    }

    public static final class Factory implements DataRewinder.Factory<InputStream> {

        /* renamed from: a  reason: collision with root package name */
        private final ArrayPool f4844a;

        public Factory(ArrayPool arrayPool) {
            this.f4844a = arrayPool;
        }

        @NonNull
        public DataRewinder<InputStream> a(InputStream inputStream) {
            return new InputStreamRewinder(inputStream, this.f4844a);
        }

        @NonNull
        public Class<InputStream> a() {
            return InputStream.class;
        }
    }
}
