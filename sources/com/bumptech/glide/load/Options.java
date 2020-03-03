package com.bumptech.glide.load;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.util.ArrayMap;
import com.bumptech.glide.util.CachedHashCodeArrayMap;
import com.taobao.weex.el.parse.Operators;
import java.security.MessageDigest;

public final class Options implements Key {
    private final ArrayMap<Option<?>, Object> c = new CachedHashCodeArrayMap();

    public void a(@NonNull Options options) {
        this.c.putAll(options.c);
    }

    @NonNull
    public <T> Options a(@NonNull Option<T> option, @NonNull T t) {
        this.c.put(option, t);
        return this;
    }

    @Nullable
    public <T> T a(@NonNull Option<T> option) {
        return this.c.containsKey(option) ? this.c.get(option) : option.a();
    }

    public boolean equals(Object obj) {
        if (obj instanceof Options) {
            return this.c.equals(((Options) obj).c);
        }
        return false;
    }

    public int hashCode() {
        return this.c.hashCode();
    }

    public void a(@NonNull MessageDigest messageDigest) {
        for (int i = 0; i < this.c.size(); i++) {
            a(this.c.keyAt(i), this.c.valueAt(i), messageDigest);
        }
    }

    public String toString() {
        return "Options{values=" + this.c + Operators.BLOCK_END;
    }

    private static <T> void a(@NonNull Option<T> option, @NonNull Object obj, @NonNull MessageDigest messageDigest) {
        option.a(obj, messageDigest);
    }
}
