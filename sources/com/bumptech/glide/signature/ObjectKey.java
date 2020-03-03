package com.bumptech.glide.signature;

import android.support.annotation.NonNull;
import com.bumptech.glide.load.Key;
import com.bumptech.glide.util.Preconditions;
import com.taobao.weex.el.parse.Operators;
import java.security.MessageDigest;

public final class ObjectKey implements Key {
    private final Object c;

    public ObjectKey(@NonNull Object obj) {
        this.c = Preconditions.a(obj);
    }

    public String toString() {
        return "ObjectKey{object=" + this.c + Operators.BLOCK_END;
    }

    public boolean equals(Object obj) {
        if (obj instanceof ObjectKey) {
            return this.c.equals(((ObjectKey) obj).c);
        }
        return false;
    }

    public int hashCode() {
        return this.c.hashCode();
    }

    public void a(@NonNull MessageDigest messageDigest) {
        messageDigest.update(this.c.toString().getBytes(b));
    }
}
