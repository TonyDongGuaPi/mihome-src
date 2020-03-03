package com.xiaomi.smarthome.framework.openapi;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;
import com.xiaomi.smarthome.HomeKeyManager;
import com.xiaomi.smarthome.app.startup.StartupCheckList;

public class OpenApiReceiver extends BroadcastReceiver {
    public void onReceive(final Context context, final Intent intent) {
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
                OpenApiReceiver.this.doWork(context, intent);
            }
        });
        HomeKeyManager.a().a(false);
    }

    /* access modifiers changed from: package-private */
    public void doWork(Context context, Intent intent) {
        String action = intent.getAction();
        if (!TextUtils.isEmpty(action)) {
            if (action.equals(OpenApi.f16685a)) {
                String stringExtra = intent.getStringExtra("type");
                String stringExtra2 = intent.getStringExtra("sub_type");
                if (!TextUtils.isEmpty(stringExtra)) {
                    if (!TextUtils.isEmpty(stringExtra2)) {
                        stringExtra.equalsIgnoreCase("plugin_debug");
                        return;
                    }
                    return;
                }
            }
            OpenApi.a(intent);
        }
    }
}
