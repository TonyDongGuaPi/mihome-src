package com.xiaomi.youpin.login.other.log;

import android.util.Log;
import com.xiaomi.smarthome.globalsetting.GlobalSetting;

public class LoginLogUtilsGrey {
    public static void a(String str, String str2) {
        try {
            if (GlobalSetting.q) {
                Log.d(str, str2);
            } else if (GlobalSetting.u) {
                Log.d(str, str2);
            }
            if (GlobalSetting.u) {
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
