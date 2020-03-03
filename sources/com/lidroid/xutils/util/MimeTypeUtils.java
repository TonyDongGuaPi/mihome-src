package com.lidroid.xutils.util;

import android.webkit.MimeTypeMap;

public class MimeTypeUtils {
    private MimeTypeUtils() {
    }

    public static String a(String str) {
        int lastIndexOf = str.lastIndexOf(".");
        if (lastIndexOf == -1) {
            return "application/octet-stream";
        }
        return MimeTypeMap.getSingleton().getMimeTypeFromExtension(str.substring(lastIndexOf + 1));
    }
}
