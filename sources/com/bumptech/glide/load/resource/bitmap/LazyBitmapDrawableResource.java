package com.bumptech.glide.load.resource.bitmap;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.Initializable;
import com.bumptech.glide.load.engine.Resource;
import com.bumptech.glide.load.engine.bitmap_recycle.BitmapPool;
import com.bumptech.glide.util.Preconditions;

public final class LazyBitmapDrawableResource implements Initializable, Resource<BitmapDrawable> {

    /* renamed from: a  reason: collision with root package name */
    private final Resources f5003a;
    private final Resource<Bitmap> b;

    @Deprecated
    public static LazyBitmapDrawableResource a(Context context, Bitmap bitmap) {
        return (LazyBitmapDrawableResource) a(context.getResources(), (Resource<Bitmap>) BitmapResource.a(bitmap, Glide.b(context).b()));
    }

    @Deprecated
    public static LazyBitmapDrawableResource a(Resources resources, BitmapPool bitmapPool, Bitmap bitmap) {
        return (LazyBitmapDrawableResource) a(resources, (Resource<Bitmap>) BitmapResource.a(bitmap, bitmapPool));
    }

    @Nullable
    public static Resource<BitmapDrawable> a(@NonNull Resources resources, @Nullable Resource<Bitmap> resource) {
        if (resource == null) {
            return null;
        }
        return new LazyBitmapDrawableResource(resources, resource);
    }

    private LazyBitmapDrawableResource(@NonNull Resources resources, @NonNull Resource<Bitmap> resource) {
        this.f5003a = (Resources) Preconditions.a(resources);
        this.b = (Resource) Preconditions.a(resource);
    }

    @NonNull
    public Class<BitmapDrawable> c() {
        return BitmapDrawable.class;
    }

    @NonNull
    /* renamed from: b */
    public BitmapDrawable d() {
        return new BitmapDrawable(this.f5003a, this.b.d());
    }

    public int e() {
        return this.b.e();
    }

    public void f() {
        this.b.f();
    }

    public void a() {
        if (this.b instanceof Initializable) {
            ((Initializable) this.b).a();
        }
    }
}
