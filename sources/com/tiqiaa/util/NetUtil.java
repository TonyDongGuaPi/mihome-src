package com.tiqiaa.util;

import android.content.Context;

public final class NetUtil {
    public static final int Base64Flag_Default = 0;
    public static final int Base64Flag_NoPadding = 1;
    public static final int Base64Flag_UrlSafe = 8;

    public static native byte[] b64decode(String str);

    public static native String b64encode(byte[] bArr, int i);

    public static native String decode(Context context, String str);

    public static native String encode(Context context, String str, int i);
}
