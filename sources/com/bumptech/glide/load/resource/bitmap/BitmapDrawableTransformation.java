package com.bumptech.glide.load.resource.bitmap;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import com.bumptech.glide.load.Transformation;
import com.bumptech.glide.load.engine.Resource;
import com.bumptech.glide.util.Preconditions;
import java.security.MessageDigest;

@Deprecated
public class BitmapDrawableTransformation implements Transformation<BitmapDrawable> {
    private final Transformation<Drawable> c;

    private static Resource<Drawable> b(Resource<BitmapDrawable> resource) {
        return resource;
    }

    public BitmapDrawableTransformation(Transformation<Bitmap> transformation) {
        this.c = (Transformation) Preconditions.a(new DrawableTransformation(transformation, false));
    }

    @NonNull
    public Resource<BitmapDrawable> a(@NonNull Context context, @NonNull Resource<BitmapDrawable> resource, int i, int i2) {
        return a(this.c.a(context, b(resource), i, i2));
    }

    private static Resource<BitmapDrawable> a(Resource<Drawable> resource) {
        if (resource.d() instanceof BitmapDrawable) {
            return resource;
        }
        throw new IllegalArgumentException("Wrapped transformation unexpectedly returned a non BitmapDrawable resource: " + resource.d());
    }

    public boolean equals(Object obj) {
        if (obj instanceof BitmapDrawableTransformation) {
            return this.c.equals(((BitmapDrawableTransformation) obj).c);
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
