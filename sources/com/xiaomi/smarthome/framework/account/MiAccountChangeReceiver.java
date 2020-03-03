package com.xiaomi.smarthome.framework.account;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;
import com.xiaomi.smarthome.app.startup.StartupCheckList;
import com.xiaomi.smarthome.frame.AsyncCallback;
import com.xiaomi.smarthome.frame.Error;
import com.xiaomi.smarthome.frame.core.CoreApi;
import com.xiaomi.smarthome.framework.log.LogUtilGrey;
import com.xiaomi.smarthome.framework.login.logic.LoginManager;
import com.xiaomi.smarthome.framework.login.util.LoginUtil;

public class MiAccountChangeReceiver extends BroadcastReceiver {
    public void onReceive(final Context context, final Intent intent) {
        if (StartupCheckList.b()) {
            StartupCheckList.a((StartupCheckList.CheckListCallback) new StartupCheckList.CheckListCallback() {
                public void a() {
                }

                public void b() {
                }

                public void c() {
                }

                public void d() {
                }

                public void e() {
                    MiAccountChangeReceiver.this.doWork(context, intent);
                }
            });
        }
    }

    /* access modifiers changed from: package-private */
    public void doWork(Context context, Intent intent) {
        CoreApi.a().a(context, (CoreApi.IsCoreReadyCallback) new CoreApi.IsCoreReadyCallback() {
            public void onCoreReady() {
                if (CoreApi.a().v()) {
                    String s = CoreApi.a().s();
                    String b = LoginUtil.b();
                    if (TextUtils.isEmpty(b) || (!TextUtils.isEmpty(b) && !b.equalsIgnoreCase(s))) {
                        try {
                            LogUtilGrey.a("MiAccountChangeReceiver", "系统帐号不一致 登出");
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                        LoginManager.a().logout((AsyncCallback<Void, Error>) null);
                    }
                }
            }
        });
    }
}
