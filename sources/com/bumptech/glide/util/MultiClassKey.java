package com.bumptech.glide.util;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import com.taobao.weex.el.parse.Operators;

public class MultiClassKey {

    /* renamed from: a  reason: collision with root package name */
    private Class<?> f5104a;
    private Class<?> b;
    private Class<?> c;

    public MultiClassKey() {
    }

    public MultiClassKey(@NonNull Class<?> cls, @NonNull Class<?> cls2) {
        a(cls, cls2);
    }

    public MultiClassKey(@NonNull Class<?> cls, @NonNull Class<?> cls2, @Nullable Class<?> cls3) {
        a(cls, cls2, cls3);
    }

    public void a(@NonNull Class<?> cls, @NonNull Class<?> cls2) {
        a(cls, cls2, (Class<?>) null);
    }

    public void a(@NonNull Class<?> cls, @NonNull Class<?> cls2, @Nullable Class<?> cls3) {
        this.f5104a = cls;
        this.b = cls2;
        this.c = cls3;
    }

    public String toString() {
        return "MultiClassKey{first=" + this.f5104a + ", second=" + this.b + Operators.BLOCK_END;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        MultiClassKey multiClassKey = (MultiClassKey) obj;
        return this.f5104a.equals(multiClassKey.f5104a) && this.b.equals(multiClassKey.b) && Util.a((Object) this.c, (Object) multiClassKey.c);
    }

    public int hashCode() {
        return (((this.f5104a.hashCode() * 31) + this.b.hashCode()) * 31) + (this.c != null ? this.c.hashCode() : 0);
    }
}
