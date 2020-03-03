package com.xiaomi.ai;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import com.xiaomi.ai.utils.Log;

class a extends BroadcastReceiver {

    /* renamed from: a  reason: collision with root package name */
    final /* synthetic */ BluetoothManager f9913a;

    a(BluetoothManager bluetoothManager) {
        this.f9913a = bluetoothManager;
    }

    public void onReceive(Context context, Intent intent) {
        StringBuilder sb;
        String str;
        String str2;
        String action = intent.getAction();
        Log.f("MiSpeechSDK:BluetoothManager", ">>> BT SCO state changed !!! action = " + action);
        if ("android.media.ACTION_SCO_AUDIO_STATE_UPDATED".equals(action)) {
            int intExtra = intent.getIntExtra("android.media.extra.SCO_AUDIO_STATE", -1);
            Log.f("MiSpeechSDK:BluetoothManager", "status = " + intExtra);
        } else if ("android.bluetooth.headset.profile.action.AUDIO_STATE_CHANGED".equals(action)) {
            int intExtra2 = intent.getIntExtra("android.bluetooth.profile.extra.STATE", -1);
            Log.f("MiSpeechSDK:BluetoothManager", "status = " + intExtra2);
            if (intExtra2 == 10) {
                str = "MiSpeechSDK:BluetoothManager";
                sb = new StringBuilder();
                str2 = "STATE_AUDIO_DISCONNECTED ";
            } else if (intExtra2 == 12) {
                str = "MiSpeechSDK:BluetoothManager";
                sb = new StringBuilder();
                str2 = "STATE_AUDIO_CONNECTED ";
            } else {
                return;
            }
            sb.append(str2);
            sb.append(this.f9913a.b());
            Log.f(str, sb.toString());
        }
    }
}
