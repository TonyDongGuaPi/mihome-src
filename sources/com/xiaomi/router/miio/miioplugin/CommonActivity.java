package com.xiaomi.router.miio.miioplugin;

import android.app.Activity;
import android.os.Build;
import android.os.Bundle;
import com.xiaomi.smarthome.library.common.util.DisplayUtils;

@Deprecated
public class CommonActivity extends Activity {
    /* access modifiers changed from: protected */
    public void onCreate(Bundle bundle) {
        DisplayUtils.e(this);
        super.onCreate(bundle);
        if (Build.VERSION.SDK_INT != 26 || !DisplayUtils.d(this)) {
            setRequestedOrientation(1);
        }
        String stringExtra = getIntent().getStringExtra("yeelink_mac");
        if (stringExtra != null) {
            ((CommonApplication) getApplication()).setMacAddr(stringExtra);
        }
    }
}
