package com.xiaomi.smarthome.camera.activity.setting.record;

import android.content.Context;
import android.os.Environment;
import com.Utils.FileUtil;
import com.mijia.debug.SDKLog;
import java.io.File;

public class LeaveMsgUtil {
    public static final String TAG = "LeaveMsgUtil";

    private static String getAudioFilePath(Context context) {
        String str = (Environment.getExternalStorageDirectory() + File.separator + context.getPackageName()) + File.separator + "audioMsgH" + File.separator;
        File file = new File(str);
        if (!file.exists() || !file.isDirectory()) {
            boolean mkdirs = file.mkdirs();
            SDKLog.b(TAG, "mkdir result = " + mkdirs);
        }
        SDKLog.b(TAG, "getAudioFilePath=" + str);
        return str;
    }

    public static String getAudioFilePath(String str, long j) {
        return FileUtil.e(str) + j + ".alaw";
    }
}
