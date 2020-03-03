package com.xiaomi.smarthome.framework.log;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Handler;
import android.util.Log;
import com.xiaomi.smarthome.application.CommonApplication;
import com.xiaomi.smarthome.frame.HostSetting;
import com.xiaomi.smarthome.frame.log.CoreLogUtilGrey;
import com.xiaomi.smarthome.globalsetting.GlobalSetting;

public class LogUtilGrey {

    /* renamed from: a  reason: collision with root package name */
    private static Handler f1538a;
    /* access modifiers changed from: private */
    public static boolean b = false;

    static {
        try {
            CommonApplication.getAppContext().registerReceiver(new BroadcastReceiver() {
                public void onReceive(Context context, Intent intent) {
                    boolean unused = LogUtilGrey.b = intent.getBooleanExtra(CoreLogUtilGrey.b, false);
                }
            }, new IntentFilter(CoreLogUtilGrey.f16120a));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void a(String str, String str2) {
        try {
            if (GlobalSetting.q) {
                Log.d(str, str2);
            } else if (GlobalSetting.u) {
                Log.d(str, str2);
            }
            if (!HostSetting.h || GlobalSetting.u || b) {
                a().post(new Runnable(str, str2) {
                    private final /* synthetic */ String f$0;
                    private final /* synthetic */ String f$1;

                    {
                        this.f$0 = r1;
                        this.f$1 = r2;
                    }

                    public final void run() {
                        MyLog.f(this.f$0 + ":" + this.f$1);
                    }
                });
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void a(String str, String str2, boolean z) {
        try {
            if (GlobalSetting.q) {
                Log.d(str, str2);
            } else if (GlobalSetting.u) {
                Log.d(str, str2);
            }
            if (z || GlobalSetting.u) {
                a().post(new Runnable(str, str2) {
                    private final /* synthetic */ String f$0;
                    private final /* synthetic */ String f$1;

                    {
                        this.f$0 = r1;
                        this.f$1 = r2;
                    }

                    public final void run() {
                        MyLog.f(this.f$0 + ":" + this.f$1);
                    }
                });
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static Handler a() {
        return CommonApplication.getGlobalWorkerHandler();
    }
}
