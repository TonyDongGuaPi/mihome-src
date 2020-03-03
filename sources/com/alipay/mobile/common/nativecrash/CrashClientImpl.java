package com.alipay.mobile.common.nativecrash;

import android.util.Log;
import com.alipay.mobile.common.logging.impl.StatisticalExceptionHandler;
import java.io.File;

public class CrashClientImpl {
    public boolean onAddCrashStats(int i, int i2) {
        return false;
    }

    public void onCrashRestarting(boolean z) {
    }

    public String onGetCallbackInfo(String str) {
        return null;
    }

    public void onLogGenerated(File file, String str) {
        if (file != null) {
            Log.w("CrashClientImpl", "onLogGenerated = " + file.getAbsolutePath());
            StatisticalExceptionHandler.getInstance().handleNativeException(file.getAbsolutePath(), str);
        }
    }
}
