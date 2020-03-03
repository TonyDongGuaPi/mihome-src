package com.bumptech.glide.load.data.mediastore;

import android.net.Uri;

public final class MediaStoreUtil {

    /* renamed from: a  reason: collision with root package name */
    private static final int f4847a = 512;
    private static final int b = 384;

    public static boolean a(int i, int i2) {
        return i != Integer.MIN_VALUE && i2 != Integer.MIN_VALUE && i <= 512 && i2 <= 384;
    }

    private MediaStoreUtil() {
    }

    public static boolean a(Uri uri) {
        return uri != null && "content".equals(uri.getScheme()) && "media".equals(uri.getAuthority());
    }

    private static boolean d(Uri uri) {
        return uri.getPathSegments().contains("video");
    }

    public static boolean b(Uri uri) {
        return a(uri) && d(uri);
    }

    public static boolean c(Uri uri) {
        return a(uri) && !d(uri);
    }
}
