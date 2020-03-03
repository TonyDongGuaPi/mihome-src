package com.bumptech.glide.load.resource.bytes;

import android.support.annotation.NonNull;
import com.bumptech.glide.load.engine.Resource;
import com.bumptech.glide.util.Preconditions;

public class BytesResource implements Resource<byte[]> {

    /* renamed from: a  reason: collision with root package name */
    private final byte[] f5014a;

    public void f() {
    }

    public BytesResource(byte[] bArr) {
        this.f5014a = (byte[]) Preconditions.a(bArr);
    }

    @NonNull
    public Class<byte[]> c() {
        return byte[].class;
    }

    @NonNull
    /* renamed from: a */
    public byte[] d() {
        return this.f5014a;
    }

    public int e() {
        return this.f5014a.length;
    }
}
