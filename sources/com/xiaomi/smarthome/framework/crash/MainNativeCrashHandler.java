package com.xiaomi.smarthome.framework.crash;

import android.util.Log;
import com.xiaomi.smarthome.application.CommonApplication;
import com.xiaomi.smarthome.crashpin.CrashPin;
import com.xiaomi.smarthome.framework.api.AsyncResponseCallback;
import com.xiaomi.smarthome.framework.commonapi.CommonApiV2;
import com.xiaomi.smarthome.framework.log.MyLog;

public class MainNativeCrashHandler implements CrashPin.CrashPinCallback {

    /* renamed from: a  reason: collision with root package name */
    public static final String f16496a = "NativeCrashHandler";

    public void onNativeCrash(String str) {
        Throwable th = new Throwable();
        String str2 = str + "\n\n" + Log.getStackTraceString(th);
        CommonApiV2.a().a(CommonApplication.getAppContext(), 0, 0, "", "", str2, (AsyncResponseCallback<Void>) null);
        MyLog.f("" + "\n" + "" + "\n" + str2);
    }
}
