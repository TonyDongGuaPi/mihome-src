package com.bumptech.glide.load.engine;

import android.support.annotation.NonNull;
import com.bumptech.glide.load.Key;
import com.bumptech.glide.load.Options;
import com.bumptech.glide.load.Transformation;
import com.bumptech.glide.util.Preconditions;
import com.taobao.weex.el.parse.Operators;
import java.security.MessageDigest;
import java.util.Map;

class EngineKey implements Key {
    private final Object c;
    private final int d;
    private final int e;
    private final Class<?> f;
    private final Class<?> g;
    private final Key h;
    private final Map<Class<?>, Transformation<?>> i;
    private final Options j;
    private int k;

    EngineKey(Object obj, Key key, int i2, int i3, Map<Class<?>, Transformation<?>> map, Class<?> cls, Class<?> cls2, Options options) {
        this.c = Preconditions.a(obj);
        this.h = (Key) Preconditions.a(key, "Signature must not be null");
        this.d = i2;
        this.e = i3;
        this.i = (Map) Preconditions.a(map);
        this.f = (Class) Preconditions.a(cls, "Resource class must not be null");
        this.g = (Class) Preconditions.a(cls2, "Transcode class must not be null");
        this.j = (Options) Preconditions.a(options);
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof EngineKey)) {
            return false;
        }
        EngineKey engineKey = (EngineKey) obj;
        if (!this.c.equals(engineKey.c) || !this.h.equals(engineKey.h) || this.e != engineKey.e || this.d != engineKey.d || !this.i.equals(engineKey.i) || !this.f.equals(engineKey.f) || !this.g.equals(engineKey.g) || !this.j.equals(engineKey.j)) {
            return false;
        }
        return true;
    }

    public int hashCode() {
        if (this.k == 0) {
            this.k = this.c.hashCode();
            this.k = (this.k * 31) + this.h.hashCode();
            this.k = (this.k * 31) + this.d;
            this.k = (this.k * 31) + this.e;
            this.k = (this.k * 31) + this.i.hashCode();
            this.k = (this.k * 31) + this.f.hashCode();
            this.k = (this.k * 31) + this.g.hashCode();
            this.k = (this.k * 31) + this.j.hashCode();
        }
        return this.k;
    }

    public String toString() {
        return "EngineKey{model=" + this.c + ", width=" + this.d + ", height=" + this.e + ", resourceClass=" + this.f + ", transcodeClass=" + this.g + ", signature=" + this.h + ", hashCode=" + this.k + ", transformations=" + this.i + ", options=" + this.j + Operators.BLOCK_END;
    }

    public void a(@NonNull MessageDigest messageDigest) {
        throw new UnsupportedOperationException();
    }
}
