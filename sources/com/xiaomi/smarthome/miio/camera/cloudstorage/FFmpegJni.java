package com.xiaomi.smarthome.miio.camera.cloudstorage;

import android.text.TextUtils;
import com.xiaomi.smarthome.framework.log.LogUtil;

public class FFmpegJni {
    private static final int INFO_CONVERT_CANCELLED = 101;
    private static final int INFO_CONVERT_COMPLETE = 102;
    public static final int INFO_CONVERT_ERROR_DATA = 103;
    private static final int INFO_CONVERT_PROGRESS = 201;
    private static final int INFO_CONVERT_SIZE = 202;
    private static final int INFO_CONVERT_START = 100;
    private static final String TAG = "FFmpegJni";
    private long mNativeContext = 0;

    private static final native int run(String[] strArr);

    static {
        System.loadLibrary("ijkffmpeg");
        System.loadLibrary("ffmpegjni");
    }

    private void notifyFromNative(int i, int i2) {
        if (i != 202) {
            LogUtil.a(TAG, "callback from native:" + i + "-" + i2);
        }
        switch (i) {
            case 100:
            case 101:
            case 102:
                return;
            default:
                switch (i) {
                }
                return;
        }
    }

    public int runCmd(String str) {
        if (!TextUtils.isEmpty(str)) {
            return run(str.split(" "));
        }
        return -1;
    }

    public int runCmd(String[] strArr) {
        if (strArr == null || strArr.length <= 0) {
            return -1;
        }
        return run(strArr);
    }
}
