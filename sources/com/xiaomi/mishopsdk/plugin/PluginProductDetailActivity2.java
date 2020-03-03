package com.xiaomi.mishopsdk.plugin;

import android.content.Intent;
import android.os.Bundle;
import com.xiaomi.mishopsdk.fragment.BasePluginFragment;

public class PluginProductDetailActivity2 extends PluginChildActivity {
    /* access modifiers changed from: protected */
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        BasePluginFragment.lastTimeOfProductDetail2 = System.currentTimeMillis();
    }

    /* access modifiers changed from: protected */
    public void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        BasePluginFragment.lastTimeOfProductDetail2 = System.currentTimeMillis();
    }

    /* access modifiers changed from: protected */
    public void onDestroy() {
        super.onDestroy();
        BasePluginFragment.lastTimeOfProductDetail2 = 0;
    }
}
