package com.xiaomi.smarthome.framework.plugin.rn;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.annotation.Nullable;
import com.xiaomi.smarthome.core.server.internal.util.LogUtil;
import com.xiaomi.smarthome.frame.plugin.PluginApi;
import com.xiaomi.smarthome.frame.plugin.runtime.bridge.PluginBridgeService;
import com.xiaomi.smarthome.framework.plugin.rn.utils.RnPluginLog;

public class LoadingRNActivity extends LoadingBaseActivity {
    public static final String ACTION_LAUNCH_PLUGIN_FAIL = "action_launch_plugin_fail";
    public static final String ACTION_LAUNCH_PLUGIN_FINISH = "action_launch_plugin_finish";

    /* renamed from: a  reason: collision with root package name */
    private BroadcastReceiver f17213a = new BroadcastReceiver() {
        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();
            LogUtil.c("PluginStartTime", "action_launch_plugin  " + System.currentTimeMillis());
            if (action != null) {
                char c = 65535;
                int hashCode = action.hashCode();
                if (hashCode != -1056971268) {
                    if (hashCode == 646936263 && action.equals(LoadingRNActivity.ACTION_LAUNCH_PLUGIN_FAIL)) {
                        c = 0;
                    }
                } else if (action.equals(LoadingRNActivity.ACTION_LAUNCH_PLUGIN_FINISH)) {
                    c = 1;
                }
                switch (c) {
                    case 0:
                        if (LoadingRNActivity.this.mDevice == null || LoadingRNActivity.this.mDevice.equals(intent.getParcelableExtra(PluginBridgeService.EXTRA_DEVICESTAT))) {
                            LoadingRNActivity.this.onBackPressed();
                            return;
                        }
                        return;
                    case 1:
                        RnPluginLog.a("Broadcast: receiver ACTION_LAUNCH_PLUGIN_FINISH broadcast...");
                        LoadingRNActivity.this.onBackPressed();
                        return;
                    default:
                        return;
                }
            }
        }
    };

    /* access modifiers changed from: protected */
    public void onCreate(@Nullable Bundle bundle) {
        super.onCreate(bundle);
        getWindow().addFlags(128);
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction(ACTION_LAUNCH_PLUGIN_FAIL);
        intentFilter.addAction(ACTION_LAUNCH_PLUGIN_FINISH);
        registerReceiver(this.f17213a, intentFilter);
        LogUtil.c("PluginStartTime", "LoadingRNActivity  " + System.currentTimeMillis());
    }

    public void onBackPressed() {
        super.onBackPressed();
        sendBroadcast(new Intent(PluginApi.ACTION_LOADING_FINISH).putExtra(PluginBridgeService.EXTRA_DEVICESTAT, this.mDevice));
    }

    /* access modifiers changed from: protected */
    public void onDestroy() {
        super.onDestroy();
        unregisterReceiver(this.f17213a);
    }
}
