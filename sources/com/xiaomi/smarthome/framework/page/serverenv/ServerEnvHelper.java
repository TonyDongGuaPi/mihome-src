package com.xiaomi.smarthome.framework.page.serverenv;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.support.v4.content.LocalBroadcastManager;
import com.google.android.exoplayer2.C;
import com.xiaomi.smarthome.framework.page.SelectServerEnvActivity;

public class ServerEnvHelper {

    /* renamed from: a  reason: collision with root package name */
    public static final String f17023a = "action_select_server_env_local_broadcast_complete";
    public static final String b = "param_key";
    public static final int c = 1;
    public static final int d = 2;

    public interface SelectedServerEnvCallback {
        void a();

        void b();
    }

    public static void a(Context context, final SelectedServerEnvCallback selectedServerEnvCallback) {
        final LocalBroadcastManager instance = LocalBroadcastManager.getInstance(context);
        instance.registerReceiver(new BroadcastReceiver() {
            public void onReceive(Context context, Intent intent) {
                instance.unregisterReceiver(this);
                int intExtra = intent.getIntExtra("param_key", 0);
                if (intExtra == 1) {
                    if (selectedServerEnvCallback != null) {
                        selectedServerEnvCallback.a();
                    }
                } else if (intExtra == 2 && selectedServerEnvCallback != null) {
                    selectedServerEnvCallback.b();
                }
            }
        }, new IntentFilter(f17023a));
        Intent intent = new Intent(context, SelectServerEnvActivity.class);
        intent.addFlags(C.ENCODING_PCM_MU_LAW);
        context.startActivity(intent);
    }
}
