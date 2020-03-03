package com.xiaomi.smarthome.framework.navigate;

import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import com.xiaomi.smarthome.library.common.util.DisplayUtils;
import com.xiaomi.youpin.app_sdk.url_dispatch.UrlDispatchManger;

public class ShopLauncherMiddle extends FragmentActivity {
    /* access modifiers changed from: protected */
    public void onCreate(Bundle bundle) {
        DisplayUtils.e(this);
        super.onCreate(bundle);
        if (Build.VERSION.SDK_INT != 26 || !DisplayUtils.d(this)) {
            setRequestedOrientation(1);
        }
        Uri data = getIntent().getData();
        if (data != null) {
            UrlDispatchManger.a().c(data.toString());
        }
        finish();
    }
}
