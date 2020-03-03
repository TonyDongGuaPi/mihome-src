package com.xiaomi.ai.utils;

import com.xiaomi.ai.mibrain.Mibrainsdk;

final class d implements Mibrainsdk.MibrainsdkLogHook {
    d() {
    }

    public void onLog(int i, String str) {
        if (i == Mibrainsdk.MIBRAIN_DEBUG_LEVEL_DEBUG) {
            Log.d("mibrainsdk-jni", str);
        } else if (i == Mibrainsdk.MIBRAIN_DEBUG_LEVEL_WARNING) {
            Log.b("mibrainsdk-jni", str);
        } else {
            Log.a("mibrainsdk-jni", str);
        }
    }
}
