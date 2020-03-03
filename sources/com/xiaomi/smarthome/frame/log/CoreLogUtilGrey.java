package com.xiaomi.smarthome.frame.log;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.util.Log;
import com.xiaomi.smarthome.core.server.CoreService;
import com.xiaomi.smarthome.globalsetting.GlobalSetting;

public class CoreLogUtilGrey {

    /* renamed from: a  reason: collision with root package name */
    public static final String f16120a = "mijia_log_greydeveloper";
    public static final String b = "showlog";
    /* access modifiers changed from: private */
    public static boolean c = false;

    static {
        Context appContext = CoreService.getAppContext();
        if (appContext != null) {
            appContext.registerReceiver(new BroadcastReceiver() {
                public void onReceive(Context context, Intent intent) {
                    boolean unused = CoreLogUtilGrey.c = intent.getBooleanExtra(CoreLogUtilGrey.b, false);
                }
            }, new IntentFilter(f16120a));
        }
    }

    public static void a(String str, String str2) {
        try {
            if (GlobalSetting.q) {
                Log.d(str, str2);
            } else if (GlobalSetting.u) {
                Log.d(str, str2);
            }
            if (GlobalSetting.u || c) {
                MyLogger a2 = MyLogger.a();
                a2.a(str + ":" + str2);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
