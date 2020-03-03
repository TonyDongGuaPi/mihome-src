package com.xiaomi.ai.streamplayer;

import android.os.Build;
import com.xiaomi.ai.utils.Log;

public class TTSDecoderConfig {

    /* renamed from: a  reason: collision with root package name */
    static boolean f9933a = false;
    private static final String b = "TTSDecoderConfig";

    public static void a(boolean z) {
        if (Build.VERSION.SDK_INT > 16) {
            f9933a = z;
            return;
        }
        Log.a(b, "setTTSDecoderConfig failed os version = " + Build.VERSION.SDK_INT);
    }
}
