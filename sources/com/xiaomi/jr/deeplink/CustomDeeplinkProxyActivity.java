package com.xiaomi.jr.deeplink;

import android.app.Activity;
import android.os.Bundle;

public class CustomDeeplinkProxyActivity extends Activity {
    /* access modifiers changed from: protected */
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        CustomDeeplinkHandler.handleIntent(this, getIntent());
        finish();
    }
}
