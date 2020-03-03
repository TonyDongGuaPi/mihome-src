package com.xiaomi.smarthome.framework.bluetooth;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Handler;
import android.os.Looper;
import android.text.TextUtils;
import com.xiaomi.smarthome.app.startup.CTAHelper;
import com.xiaomi.smarthome.application.CommonApplication;
import com.xiaomi.smarthome.device.bluetooth.receiver.BluetoothStateReceiver;
import com.xiaomi.smarthome.device.bluetooth.receiver.BondStateReceiver;
import com.xiaomi.smarthome.device.bluetooth.receiver.ConnectStatusReceiver;
import com.xiaomi.smarthome.device.bluetooth.receiver.IBluetoothReceiver;
import com.xiaomi.smarthome.library.bluetooth.utils.BluetoothUtils;

public class BluetoothReceiver extends BroadcastReceiver {

    /* renamed from: a  reason: collision with root package name */
    private static final IBluetoothReceiver[] f16473a = {BluetoothStateReceiver.b(), BondStateReceiver.b(), ConnectStatusReceiver.b()};
    /* access modifiers changed from: private */
    public static BluetoothReceiver b;
    private static Handler c = new Handler(Looper.getMainLooper());

    /* access modifiers changed from: private */
    public static IntentFilter a() {
        IntentFilter intentFilter = new IntentFilter();
        for (IBluetoothReceiver a2 : f16473a) {
            for (String addAction : a2.a()) {
                intentFilter.addAction(addAction);
            }
        }
        return intentFilter;
    }

    public static void registerBluetoothReceiver(long j) {
        c.postDelayed(new Runnable() {
            public void run() {
                if (BluetoothReceiver.b == null) {
                    BluetoothReceiver unused = BluetoothReceiver.b = new BluetoothReceiver();
                    BluetoothUtils.a((BroadcastReceiver) BluetoothReceiver.b, BluetoothReceiver.a());
                }
            }
        }, j);
    }

    public static void unregisterBluetoothReceiver() {
        c.removeCallbacksAndMessages((Object) null);
        if (b != null) {
            BluetoothUtils.a((BroadcastReceiver) b);
            b = null;
        }
    }

    public void onReceive(Context context, Intent intent) {
        if (CommonApplication.isApplicationStart()) {
            a(context, intent);
        } else if (!CTAHelper.a(CommonApplication.getAppContext()) && CTAHelper.b(CommonApplication.getAppContext())) {
            a(context, intent);
        }
    }

    private void a(Context context, Intent intent) {
        if (intent != null) {
            String action = intent.getAction();
            if (!TextUtils.isEmpty(action)) {
                IBluetoothReceiver[] iBluetoothReceiverArr = f16473a;
                int length = iBluetoothReceiverArr.length;
                int i = 0;
                while (i < length) {
                    IBluetoothReceiver iBluetoothReceiver = iBluetoothReceiverArr[i];
                    if (!iBluetoothReceiver.a().contains(action) || !iBluetoothReceiver.a(context, intent)) {
                        i++;
                    } else {
                        return;
                    }
                }
            }
        }
    }
}
