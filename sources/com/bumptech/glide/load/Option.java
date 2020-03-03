package com.bumptech.glide.load;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import com.bumptech.glide.util.Preconditions;
import com.taobao.weex.el.parse.Operators;
import java.security.MessageDigest;

public final class Option<T> {

    /* renamed from: a  reason: collision with root package name */
    private static final CacheKeyUpdater<Object> f4836a = new CacheKeyUpdater<Object>() {
        public void a(@NonNull byte[] bArr, @NonNull Object obj, @NonNull MessageDigest messageDigest) {
        }
    };
    private final T b;
    private final CacheKeyUpdater<T> c;
    private final String d;
    private volatile byte[] e;

    public interface CacheKeyUpdater<T> {
        void a(@NonNull byte[] bArr, @NonNull T t, @NonNull MessageDigest messageDigest);
    }

    @NonNull
    public static <T> Option<T> a(@NonNull String str) {
        return new Option<>(str, (Object) null, c());
    }

    @NonNull
    public static <T> Option<T> a(@NonNull String str, @NonNull T t) {
        return new Option<>(str, t, c());
    }

    @NonNull
    public static <T> Option<T> a(@NonNull String str, @NonNull CacheKeyUpdater<T> cacheKeyUpdater) {
        return new Option<>(str, (Object) null, cacheKeyUpdater);
    }

    @NonNull
    public static <T> Option<T> a(@NonNull String str, @Nullable T t, @NonNull CacheKeyUpdater<T> cacheKeyUpdater) {
        return new Option<>(str, t, cacheKeyUpdater);
    }

    private Option(@NonNull String str, @Nullable T t, @NonNull CacheKeyUpdater<T> cacheKeyUpdater) {
        this.d = Preconditions.a(str);
        this.b = t;
        this.c = (CacheKeyUpdater) Preconditions.a(cacheKeyUpdater);
    }

    @Nullable
    public T a() {
        return this.b;
    }

    public void a(@NonNull T t, @NonNull MessageDigest messageDigest) {
        this.c.a(b(), t, messageDigest);
    }

    @NonNull
    private byte[] b() {
        if (this.e == null) {
            this.e = this.d.getBytes(Key.b);
        }
        return this.e;
    }

    public boolean equals(Object obj) {
        if (obj instanceof Option) {
            return this.d.equals(((Option) obj).d);
        }
        return false;
    }

    public int hashCode() {
        return this.d.hashCode();
    }

    @NonNull
    private static <T> CacheKeyUpdater<T> c() {
        return f4836a;
    }

    public String toString() {
        return "Option{key='" + this.d + Operators.SINGLE_QUOTE + Operators.BLOCK_END;
    }
}
