package com.bumptech.glide.load.resource.bitmap;

import android.graphics.Bitmap;
import android.support.annotation.NonNull;
import com.bumptech.glide.load.Options;
import com.bumptech.glide.load.ResourceDecoder;
import com.bumptech.glide.load.engine.Resource;
import com.bumptech.glide.load.engine.bitmap_recycle.ArrayPool;
import com.bumptech.glide.load.engine.bitmap_recycle.BitmapPool;
import com.bumptech.glide.load.resource.bitmap.Downsampler;
import com.bumptech.glide.util.ExceptionCatchingInputStream;
import com.bumptech.glide.util.MarkEnforcingInputStream;
import java.io.IOException;
import java.io.InputStream;

public class StreamBitmapDecoder implements ResourceDecoder<InputStream, Bitmap> {

    /* renamed from: a  reason: collision with root package name */
    private final Downsampler f5006a;
    private final ArrayPool b;

    public StreamBitmapDecoder(Downsampler downsampler, ArrayPool arrayPool) {
        this.f5006a = downsampler;
        this.b = arrayPool;
    }

    public boolean a(@NonNull InputStream inputStream, @NonNull Options options) {
        return this.f5006a.a(inputStream);
    }

    public Resource<Bitmap> a(@NonNull InputStream inputStream, int i, int i2, @NonNull Options options) throws IOException {
        RecyclableBufferedInputStream recyclableBufferedInputStream;
        boolean z;
        if (inputStream instanceof RecyclableBufferedInputStream) {
            recyclableBufferedInputStream = (RecyclableBufferedInputStream) inputStream;
            z = false;
        } else {
            recyclableBufferedInputStream = new RecyclableBufferedInputStream(inputStream, this.b);
            z = true;
        }
        ExceptionCatchingInputStream a2 = ExceptionCatchingInputStream.a(recyclableBufferedInputStream);
        try {
            return this.f5006a.a((InputStream) new MarkEnforcingInputStream(a2), i, i2, options, (Downsampler.DecodeCallbacks) new UntrustedCallbacks(recyclableBufferedInputStream, a2));
        } finally {
            a2.c();
            if (z) {
                recyclableBufferedInputStream.b();
            }
        }
    }

    static class UntrustedCallbacks implements Downsampler.DecodeCallbacks {

        /* renamed from: a  reason: collision with root package name */
        private final RecyclableBufferedInputStream f5007a;
        private final ExceptionCatchingInputStream b;

        UntrustedCallbacks(RecyclableBufferedInputStream recyclableBufferedInputStream, ExceptionCatchingInputStream exceptionCatchingInputStream) {
            this.f5007a = recyclableBufferedInputStream;
            this.b = exceptionCatchingInputStream;
        }

        public void a() {
            this.f5007a.a();
        }

        public void a(BitmapPool bitmapPool, Bitmap bitmap) throws IOException {
            IOException b2 = this.b.b();
            if (b2 != null) {
                if (bitmap != null) {
                    bitmapPool.a(bitmap);
                }
                throw b2;
            }
        }
    }
}
