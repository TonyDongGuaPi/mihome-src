package com.bumptech.glide.load.resource.gif;

import android.graphics.Bitmap;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import com.bumptech.glide.gifdecoder.GifDecoder;
import com.bumptech.glide.load.engine.bitmap_recycle.ArrayPool;
import com.bumptech.glide.load.engine.bitmap_recycle.BitmapPool;

public final class GifBitmapProvider implements GifDecoder.BitmapProvider {

    /* renamed from: a  reason: collision with root package name */
    private final BitmapPool f5020a;
    @Nullable
    private final ArrayPool b;

    public GifBitmapProvider(BitmapPool bitmapPool) {
        this(bitmapPool, (ArrayPool) null);
    }

    public GifBitmapProvider(BitmapPool bitmapPool, @Nullable ArrayPool arrayPool) {
        this.f5020a = bitmapPool;
        this.b = arrayPool;
    }

    @NonNull
    public Bitmap a(int i, int i2, @NonNull Bitmap.Config config) {
        return this.f5020a.b(i, i2, config);
    }

    public void a(@NonNull Bitmap bitmap) {
        this.f5020a.a(bitmap);
    }

    @NonNull
    public byte[] a(int i) {
        if (this.b == null) {
            return new byte[i];
        }
        return (byte[]) this.b.a(i, byte[].class);
    }

    public void a(@NonNull byte[] bArr) {
        if (this.b != null) {
            this.b.a(bArr);
        }
    }

    @NonNull
    public int[] b(int i) {
        if (this.b == null) {
            return new int[i];
        }
        return (int[]) this.b.a(i, int[].class);
    }

    public void a(@NonNull int[] iArr) {
        if (this.b != null) {
            this.b.a(iArr);
        }
    }
}
