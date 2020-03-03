package com.bumptech.glide.load.resource;

import android.content.Context;
import android.support.annotation.NonNull;
import com.bumptech.glide.load.Transformation;
import com.bumptech.glide.load.engine.Resource;
import java.security.MessageDigest;

public final class UnitTransformation<T> implements Transformation<T> {
    private static final Transformation<?> c = new UnitTransformation();

    @NonNull
    public Resource<T> a(@NonNull Context context, @NonNull Resource<T> resource, int i, int i2) {
        return resource;
    }

    public void a(@NonNull MessageDigest messageDigest) {
    }

    @NonNull
    public static <T> UnitTransformation<T> a() {
        return (UnitTransformation) c;
    }

    private UnitTransformation() {
    }
}
