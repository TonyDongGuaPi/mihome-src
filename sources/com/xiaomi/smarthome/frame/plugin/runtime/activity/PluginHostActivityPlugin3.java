package com.xiaomi.smarthome.frame.plugin.runtime.activity;

public class PluginHostActivityPlugin3 extends PluginHostActivity {
    /* access modifiers changed from: protected */
    public void onDestroy() {
        super.onDestroy();
        getHandler().removeMessages(1);
    }
}
