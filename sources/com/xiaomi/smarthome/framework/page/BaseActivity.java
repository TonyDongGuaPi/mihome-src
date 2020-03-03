package com.xiaomi.smarthome.framework.page;

import android.app.Activity;
import android.arch.lifecycle.LifecycleObserver;
import android.os.Bundle;
import com.xiaomi.smarthome.library.DarkModeCompat;
import com.xiaomi.smarthome.library.common.util.ViewUtils;
import com.xiaomi.smarthome.miio.TitleBarUtil;

public class BaseActivity extends CommonActivity {
    /* access modifiers changed from: protected */
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        TitleBarUtil.a((Activity) this);
        DarkModeCompat.b((Activity) this);
    }

    public void addLifecycleObserver(LifecycleObserver lifecycleObserver) {
        if (lifecycleObserver != null) {
            getLifecycle().a(lifecycleObserver);
        }
    }

    /* access modifiers changed from: protected */
    public void onDestroy() {
        super.onDestroy();
        ViewUtils.b((Activity) this);
        ViewUtils.a((Activity) this);
    }
}
