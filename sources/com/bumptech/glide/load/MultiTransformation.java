package com.bumptech.glide.load;

import android.content.Context;
import android.support.annotation.NonNull;
import com.bumptech.glide.load.engine.Resource;
import java.security.MessageDigest;
import java.util.Arrays;
import java.util.Collection;

public class MultiTransformation<T> implements Transformation<T> {
    private final Collection<? extends Transformation<T>> c;

    @SafeVarargs
    public MultiTransformation(@NonNull Transformation<T>... transformationArr) {
        if (transformationArr.length != 0) {
            this.c = Arrays.asList(transformationArr);
            return;
        }
        throw new IllegalArgumentException("MultiTransformation must contain at least one Transformation");
    }

    public MultiTransformation(@NonNull Collection<? extends Transformation<T>> collection) {
        if (!collection.isEmpty()) {
            this.c = collection;
            return;
        }
        throw new IllegalArgumentException("MultiTransformation must contain at least one Transformation");
    }

    @NonNull
    public Resource<T> a(@NonNull Context context, @NonNull Resource<T> resource, int i, int i2) {
        Resource<T> resource2 = resource;
        for (Transformation a2 : this.c) {
            Resource<T> a3 = a2.a(context, resource2, i, i2);
            if (resource2 != null && !resource2.equals(resource) && !resource2.equals(a3)) {
                resource2.f();
            }
            resource2 = a3;
        }
        return resource2;
    }

    public boolean equals(Object obj) {
        if (obj instanceof MultiTransformation) {
            return this.c.equals(((MultiTransformation) obj).c);
        }
        return false;
    }

    public int hashCode() {
        return this.c.hashCode();
    }

    public void a(@NonNull MessageDigest messageDigest) {
        for (Transformation a2 : this.c) {
            a2.a(messageDigest);
        }
    }
}
