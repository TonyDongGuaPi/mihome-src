package com.xiaomi.mishopsdk.plugin;

import android.content.Intent;
import android.os.Bundle;
import com.xiaomi.mishopsdk.fragment.BasePluginFragment;

public class PluginProductDetailActivity1 extends PluginChildActivity {
    /* access modifiers changed from: protected */
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        BasePluginFragment.lastTimeOfProductDetail1 = System.currentTimeMillis();
    }

    /* access modifiers changed from: protected */
    public void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        BasePluginFragment.lastTimeOfProductDetail1 = System.currentTimeMillis();
    }

    /* access modifiers changed from: protected */
    public void onDestroy() {
        super.onDestroy();
        BasePluginFragment.lastTimeOfProductDetail1 = 0;
    }
}
