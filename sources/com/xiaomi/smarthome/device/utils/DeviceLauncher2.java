package com.xiaomi.smarthome.device.utils;

import android.app.Activity;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import com.xiaomi.smarthome.HomeKeyManager;
import com.xiaomi.smarthome.R;
import com.xiaomi.smarthome.app.startup.StartupCheckList;
import com.xiaomi.smarthome.framework.navigate.SmartHomeJumper;
import com.xiaomi.smarthome.library.common.util.DisplayUtils;

public class DeviceLauncher2 extends FragmentActivity {
    /* access modifiers changed from: protected */
    public void onCreate(Bundle bundle) {
        DisplayUtils.e(this);
        super.onCreate(bundle);
        if (Build.VERSION.SDK_INT != 26 || !DisplayUtils.d(this)) {
            setRequestedOrientation(1);
        }
        setContentView(R.layout.scene_launcher_layout);
        StartupCheckList.a((StartupCheckList.CheckListCallback) new StartupCheckList.CheckListCallback() {
            public void a() {
            }

            public void c() {
            }

            public void b() {
                DeviceLauncher2.this.finish();
            }

            public void d() {
                DeviceLauncher2.this.finish();
            }

            public void e() {
                DeviceLauncher2.this.a();
            }
        });
        HomeKeyManager.a().a(false);
    }

    /* access modifiers changed from: private */
    public void a() {
        SmartHomeJumper.a((Activity) this, getIntent()).a(getIntent());
    }

    /* access modifiers changed from: protected */
    public void onResume() {
        super.onResume();
    }
}
