package com.xiaomi.smarthome.audioprocess;

import com.xiaomi.smarthome.frame.FrameManager;

public class PcmUtil {
    public static void a(String str, byte[] bArr) {
        try {
            FrameManager.b().c().openFileOutput(str, 32768).write(bArr);
        } catch (Exception unused) {
        }
    }
}
