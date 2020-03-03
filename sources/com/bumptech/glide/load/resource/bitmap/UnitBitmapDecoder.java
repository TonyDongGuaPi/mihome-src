package com.bumptech.glide.load.resource.bitmap;

import android.graphics.Bitmap;
import android.support.annotation.NonNull;
import com.bumptech.glide.load.Options;
import com.bumptech.glide.load.ResourceDecoder;
import com.bumptech.glide.load.engine.Resource;
import com.bumptech.glide.util.Util;

public final class UnitBitmapDecoder implements ResourceDecoder<Bitmap, Bitmap> {
    public boolean a(@NonNull Bitmap bitmap, @NonNull Options options) {
        return true;
    }

    public Resource<Bitmap> a(@NonNull Bitmap bitmap, int i, int i2, @NonNull Options options) {
        return new NonOwnedBitmapResource(bitmap);
    }

    private static final class NonOwnedBitmapResource implements Resource<Bitmap> {

        /* renamed from: a  reason: collision with root package name */
        private final Bitmap f5009a;

        public void f() {
        }

        NonOwnedBitmapResource(@NonNull Bitmap bitmap) {
            this.f5009a = bitmap;
        }

        @NonNull
        public Class<Bitmap> c() {
            return Bitmap.class;
        }

        @NonNull
        /* renamed from: a */
        public Bitmap d() {
            return this.f5009a;
        }

        public int e() {
            return Util.b(this.f5009a);
        }
    }
}
