package com.bumptech.glide.load.resource.bitmap;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.Transformation;
import com.bumptech.glide.load.engine.Resource;
import com.bumptech.glide.load.engine.bitmap_recycle.BitmapPool;
import java.security.MessageDigest;

public class DrawableTransformation implements Transformation<Drawable> {
    private final Transformation<Bitmap> c;
    private final boolean d;

    public Transformation<BitmapDrawable> a() {
        return this;
    }

    public DrawableTransformation(Transformation<Bitmap> transformation, boolean z) {
        this.c = transformation;
        this.d = z;
    }

    @NonNull
    public Resource<Drawable> a(@NonNull Context context, @NonNull Resource<Drawable> resource, int i, int i2) {
        BitmapPool b = Glide.b(context).b();
        Drawable d2 = resource.d();
        Resource<Bitmap> a2 = DrawableToBitmapConverter.a(b, d2, i, i2);
        if (a2 != null) {
            Resource<Bitmap> a3 = this.c.a(context, a2, i, i2);
            if (!a3.equals(a2)) {
                return a(context, a3);
            }
            a3.f();
            return resource;
        } else if (!this.d) {
            return resource;
        } else {
            throw new IllegalArgumentException("Unable to convert " + d2 + " to a Bitmap");
        }
    }

    private Resource<Drawable> a(Context context, Resource<Bitmap> resource) {
        return LazyBitmapDrawableResource.a(context.getResources(), resource);
    }

    public boolean equals(Object obj) {
        if (obj instanceof DrawableTransformation) {
            return this.c.equals(((DrawableTransformation) obj).c);
        }
        return false;
    }

    public int hashCode() {
        return this.c.hashCode();
    }

    public void a(@NonNull MessageDigest messageDigest) {
        this.c.a(messageDigest);
    }
}
