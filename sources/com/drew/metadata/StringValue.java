package com.drew.metadata;

import com.drew.lang.annotations.NotNull;
import com.drew.lang.annotations.Nullable;
import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;

public final class StringValue {
    @NotNull

    /* renamed from: a  reason: collision with root package name */
    private final byte[] f5209a;
    @Nullable
    private final Charset b;

    public StringValue(@NotNull byte[] bArr, @Nullable Charset charset) {
        this.f5209a = bArr;
        this.b = charset;
    }

    @NotNull
    public byte[] a() {
        return this.f5209a;
    }

    @Nullable
    public Charset b() {
        return this.b;
    }

    public String toString() {
        return a(this.b);
    }

    public String a(@Nullable Charset charset) {
        if (charset != null) {
            try {
                return new String(this.f5209a, charset.name());
            } catch (UnsupportedEncodingException unused) {
            }
        }
        return new String(this.f5209a);
    }
}
