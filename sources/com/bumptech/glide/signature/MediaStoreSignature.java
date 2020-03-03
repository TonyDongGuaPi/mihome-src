package com.bumptech.glide.signature;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import com.bumptech.glide.load.Key;
import java.nio.ByteBuffer;
import java.security.MessageDigest;

public class MediaStoreSignature implements Key {
    @NonNull
    private final String c;
    private final long d;
    private final int e;

    public MediaStoreSignature(@Nullable String str, long j, int i) {
        this.c = str == null ? "" : str;
        this.d = j;
        this.e = i;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        MediaStoreSignature mediaStoreSignature = (MediaStoreSignature) obj;
        return this.d == mediaStoreSignature.d && this.e == mediaStoreSignature.e && this.c.equals(mediaStoreSignature.c);
    }

    public int hashCode() {
        return (((this.c.hashCode() * 31) + ((int) (this.d ^ (this.d >>> 32)))) * 31) + this.e;
    }

    public void a(@NonNull MessageDigest messageDigest) {
        messageDigest.update(ByteBuffer.allocate(12).putLong(this.d).putInt(this.e).array());
        messageDigest.update(this.c.getBytes(b));
    }
}
