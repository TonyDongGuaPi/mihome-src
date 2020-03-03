package com.mijia.camera;

import android.content.Context;
import android.support.v4.app.ActivityCompat;

public class MediaRemuxing {
    private static native int native_remuxing(String str, String str2);

    static {
        System.loadLibrary("media_remuxing");
    }

    public static int remuxing(Context context, String str, String str2) {
        if (ActivityCompat.checkSelfPermission(context, "android.permission.WRITE_EXTERNAL_STORAGE") != 0) {
            return 1;
        }
        return native_remuxing(str, str2);
    }
}
