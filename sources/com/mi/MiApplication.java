package com.mi;

import android.app.Application;
import android.content.Context;
import com.mi.log.LogUtil;
import com.mi.util.Constants;
import com.mi.util.Device;
import com.mi.util.LeakCanaryUtil;
import com.mi.util.NotificationChannelUtil;
import com.mi.util.RequestQueueUtil;
import com.mi.util.ScreenInfo;
import com.mi.util.permission.PermissionUtil;

public class MiApplication extends Application {
    public void onCreate() {
        super.onCreate();
        MiApplicationContext.f1260a = this;
        Device.a(this, PermissionUtil.a((Context) this, "android.permission.READ_PHONE_STATE"));
        LogUtil.a(Constants.f1348a);
        ScreenInfo.a().a(this);
        initNetwork();
        LeakCanaryUtil.a(this);
        NotificationChannelUtil.a();
    }

    /* access modifiers changed from: protected */
    public void initNetwork() {
        RequestQueueUtil.a(this);
    }
}
