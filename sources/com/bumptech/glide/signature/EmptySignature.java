package com.bumptech.glide.signature;

import android.support.annotation.NonNull;
import com.bumptech.glide.load.Key;
import java.security.MessageDigest;

public final class EmptySignature implements Key {
    private static final EmptySignature c = new EmptySignature();

    public void a(@NonNull MessageDigest messageDigest) {
    }

    public String toString() {
        return "EmptySignature";
    }

    @NonNull
    public static EmptySignature a() {
        return c;
    }

    private EmptySignature() {
    }
}
