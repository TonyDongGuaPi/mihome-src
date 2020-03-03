package com.bumptech.glide.load.engine;

import android.support.annotation.NonNull;
import com.bumptech.glide.load.Key;
import com.bumptech.glide.load.Options;
import com.bumptech.glide.load.Transformation;
import com.bumptech.glide.load.engine.bitmap_recycle.ArrayPool;
import com.bumptech.glide.util.LruCache;
import com.bumptech.glide.util.Util;
import com.taobao.weex.el.parse.Operators;
import java.nio.ByteBuffer;
import java.security.MessageDigest;

final class ResourceCacheKey implements Key {
    private static final LruCache<Class<?>, byte[]> c = new LruCache<>(50);
    private final ArrayPool d;
    private final Key e;
    private final Key f;
    private final int g;
    private final int h;
    private final Class<?> i;
    private final Options j;
    private final Transformation<?> k;

    ResourceCacheKey(ArrayPool arrayPool, Key key, Key key2, int i2, int i3, Transformation<?> transformation, Class<?> cls, Options options) {
        this.d = arrayPool;
        this.e = key;
        this.f = key2;
        this.g = i2;
        this.h = i3;
        this.k = transformation;
        this.i = cls;
        this.j = options;
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof ResourceCacheKey)) {
            return false;
        }
        ResourceCacheKey resourceCacheKey = (ResourceCacheKey) obj;
        if (this.h != resourceCacheKey.h || this.g != resourceCacheKey.g || !Util.a((Object) this.k, (Object) resourceCacheKey.k) || !this.i.equals(resourceCacheKey.i) || !this.e.equals(resourceCacheKey.e) || !this.f.equals(resourceCacheKey.f) || !this.j.equals(resourceCacheKey.j)) {
            return false;
        }
        return true;
    }

    public int hashCode() {
        int hashCode = (((((this.e.hashCode() * 31) + this.f.hashCode()) * 31) + this.g) * 31) + this.h;
        if (this.k != null) {
            hashCode = (hashCode * 31) + this.k.hashCode();
        }
        return (((hashCode * 31) + this.i.hashCode()) * 31) + this.j.hashCode();
    }

    public void a(@NonNull MessageDigest messageDigest) {
        byte[] bArr = (byte[]) this.d.b(8, byte[].class);
        ByteBuffer.wrap(bArr).putInt(this.g).putInt(this.h).array();
        this.f.a(messageDigest);
        this.e.a(messageDigest);
        messageDigest.update(bArr);
        if (this.k != null) {
            this.k.a(messageDigest);
        }
        this.j.a(messageDigest);
        messageDigest.update(a());
        this.d.a(bArr);
    }

    private byte[] a() {
        byte[] c2 = c.c(this.i);
        if (c2 != null) {
            return c2;
        }
        byte[] bytes = this.i.getName().getBytes(b);
        c.b(this.i, bytes);
        return bytes;
    }

    public String toString() {
        return "ResourceCacheKey{sourceKey=" + this.e + ", signature=" + this.f + ", width=" + this.g + ", height=" + this.h + ", decodedResourceClass=" + this.i + ", transformation='" + this.k + Operators.SINGLE_QUOTE + ", options=" + this.j + Operators.BLOCK_END;
    }
}
