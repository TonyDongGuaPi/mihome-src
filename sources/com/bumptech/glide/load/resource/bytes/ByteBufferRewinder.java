package com.bumptech.glide.load.resource.bytes;

import android.support.annotation.NonNull;
import com.bumptech.glide.load.data.DataRewinder;
import java.nio.ByteBuffer;

public class ByteBufferRewinder implements DataRewinder<ByteBuffer> {

    /* renamed from: a  reason: collision with root package name */
    private final ByteBuffer f5013a;

    public void b() {
    }

    public ByteBufferRewinder(ByteBuffer byteBuffer) {
        this.f5013a = byteBuffer;
    }

    @NonNull
    /* renamed from: c */
    public ByteBuffer a() {
        this.f5013a.position(0);
        return this.f5013a;
    }

    public static class Factory implements DataRewinder.Factory<ByteBuffer> {
        @NonNull
        public DataRewinder<ByteBuffer> a(ByteBuffer byteBuffer) {
            return new ByteBufferRewinder(byteBuffer);
        }

        @NonNull
        public Class<ByteBuffer> a() {
            return ByteBuffer.class;
        }
    }
}
