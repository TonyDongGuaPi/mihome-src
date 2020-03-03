package com.bumptech.glide.load.resource.bitmap;

import android.graphics.Bitmap;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import com.bumptech.glide.load.engine.Initializable;
import com.bumptech.glide.load.engine.Resource;
import com.bumptech.glide.load.engine.bitmap_recycle.BitmapPool;
import com.bumptech.glide.util.Preconditions;
import com.bumptech.glide.util.Util;

public class BitmapResource implements Initializable, Resource<Bitmap> {

    /* renamed from: a  reason: collision with root package name */
    private final Bitmap f4994a;
    private final BitmapPool b;

    @Nullable
    public static BitmapResource a(@Nullable Bitmap bitmap, @NonNull BitmapPool bitmapPool) {
        if (bitmap == null) {
            return null;
        }
        return new BitmapResource(bitmap, bitmapPool);
    }

    public BitmapResource(@NonNull Bitmap bitmap, @NonNull BitmapPool bitmapPool) {
        this.f4994a = (Bitmap) Preconditions.a(bitmap, "Bitmap must not be null");
        this.b = (BitmapPool) Preconditions.a(bitmapPool, "BitmapPool must not be null");
    }

    @NonNull
    public Class<Bitmap> c() {
        return Bitmap.class;
    }

    @NonNull
    /* renamed from: b */
    public Bitmap d() {
        return this.f4994a;
    }

    public int e() {
        return Util.b(this.f4994a);
    }

    public void f() {
        this.b.a(this.f4994a);
    }

    public void a() {
        this.f4994a.prepareToDraw();
    }
}
