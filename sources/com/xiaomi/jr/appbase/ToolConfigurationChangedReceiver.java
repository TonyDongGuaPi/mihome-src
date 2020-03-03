package com.xiaomi.jr.appbase;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import com.xiaomi.jr.appbase.app.MiFiAppController;

public class ToolConfigurationChangedReceiver extends BroadcastReceiver {
    public void onReceive(Context context, Intent intent) {
        Log.i("TestTool", "received tool configuration changed");
        MiFiAppController.a().a();
    }
}
