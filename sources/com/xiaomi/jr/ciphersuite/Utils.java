package com.xiaomi.jr.ciphersuite;

import android.text.TextUtils;
import android.util.Base64;
import android.util.Log;
import java.io.Closeable;
import java.io.IOException;

public class Utils {
    public static void a(Closeable closeable) {
        if (closeable != null) {
            try {
                closeable.close();
            } catch (IOException unused) {
            }
        }
    }

    public static void a(String str, String str2, Throwable th) {
        Log.i(str, str2, th);
    }

    public static boolean a(String str) {
        return TextUtils.isEmpty(str);
    }

    public static String a(byte[] bArr) {
        return Base64.encodeToString(bArr, 2);
    }

    public static byte[] b(String str) {
        return Base64.decode(str, 0);
    }
}
