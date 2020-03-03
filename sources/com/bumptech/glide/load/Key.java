package com.bumptech.glide.load;

import android.support.annotation.NonNull;
import java.nio.charset.Charset;
import java.security.MessageDigest;

public interface Key {

    /* renamed from: a  reason: collision with root package name */
    public static final String f4835a = "UTF-8";
    public static final Charset b = Charset.forName("UTF-8");

    void a(@NonNull MessageDigest messageDigest);

    boolean equals(Object obj);

    int hashCode();
}
