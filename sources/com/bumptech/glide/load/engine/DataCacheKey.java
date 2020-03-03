package com.bumptech.glide.load.engine;

import android.support.annotation.NonNull;
import com.bumptech.glide.load.Key;
import com.taobao.weex.el.parse.Operators;
import java.security.MessageDigest;

final class DataCacheKey implements Key {
    private final Key c;
    private final Key d;

    DataCacheKey(Key key, Key key2) {
        this.c = key;
        this.d = key2;
    }

    /* access modifiers changed from: package-private */
    public Key a() {
        return this.c;
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof DataCacheKey)) {
            return false;
        }
        DataCacheKey dataCacheKey = (DataCacheKey) obj;
        if (!this.c.equals(dataCacheKey.c) || !this.d.equals(dataCacheKey.d)) {
            return false;
        }
        return true;
    }

    public int hashCode() {
        return (this.c.hashCode() * 31) + this.d.hashCode();
    }

    public String toString() {
        return "DataCacheKey{sourceKey=" + this.c + ", signature=" + this.d + Operators.BLOCK_END;
    }

    public void a(@NonNull MessageDigest messageDigest) {
        this.c.a(messageDigest);
        this.d.a(messageDigest);
    }
}
