package com.amap.openapi;

import android.support.annotation.NonNull;
import com.amap.openapi.bi;

public class s implements bi.a {

    /* renamed from: a  reason: collision with root package name */
    private int f4742a;
    private byte[] b;

    public s(int i, @NonNull byte[] bArr) {
        this.f4742a = i;
        this.b = bArr;
    }

    public long a() {
        return (long) (this.b.length + 17);
    }

    public int b() {
        return this.f4742a;
    }

    public byte[] c() {
        return this.b;
    }
}
