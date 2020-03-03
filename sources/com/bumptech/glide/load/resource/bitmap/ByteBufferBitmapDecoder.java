package com.bumptech.glide.load.resource.bitmap;

import android.graphics.Bitmap;
import android.support.annotation.NonNull;
import com.bumptech.glide.load.Options;
import com.bumptech.glide.load.ResourceDecoder;
import com.bumptech.glide.load.engine.Resource;
import com.bumptech.glide.util.ByteBufferUtil;
import java.io.IOException;
import java.nio.ByteBuffer;

public class ByteBufferBitmapDecoder implements ResourceDecoder<ByteBuffer, Bitmap> {

    /* renamed from: a  reason: collision with root package name */
    private final Downsampler f4995a;

    public ByteBufferBitmapDecoder(Downsampler downsampler) {
        this.f4995a = downsampler;
    }

    public boolean a(@NonNull ByteBuffer byteBuffer, @NonNull Options options) {
        return this.f4995a.a(byteBuffer);
    }

    public Resource<Bitmap> a(@NonNull ByteBuffer byteBuffer, int i, int i2, @NonNull Options options) throws IOException {
        return this.f4995a.a(ByteBufferUtil.b(byteBuffer), i, i2, options);
    }
}
