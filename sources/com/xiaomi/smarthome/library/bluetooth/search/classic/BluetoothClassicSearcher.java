package com.xiaomi.smarthome.library.bluetooth.search.classic;

import android.bluetooth.BluetoothDevice;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.util.Log;
import com.xiaomi.smarthome.library.bluetooth.search.BluetoothSearchResponse;
import com.xiaomi.smarthome.library.bluetooth.search.BluetoothSearchResult;
import com.xiaomi.smarthome.library.bluetooth.search.BluetoothSearcher;
import com.xiaomi.smarthome.library.bluetooth.utils.BluetoothUtils;
import java.util.UUID;
import kotlin.jvm.internal.ShortCompanionObject;

public class BluetoothClassicSearcher extends BluetoothSearcher {
    private BluetoothSearchReceiver d;

    private BluetoothClassicSearcher() {
        this.f18540a = BluetoothUtils.e();
    }

    public static BluetoothClassicSearcher c() {
        return BluetoothClassicSearcherHolder.f18541a;
    }

    private static class BluetoothClassicSearcherHolder {
        /* access modifiers changed from: private */

        /* renamed from: a  reason: collision with root package name */
        public static BluetoothClassicSearcher f18541a = new BluetoothClassicSearcher();

        private BluetoothClassicSearcherHolder() {
        }
    }

    public void a(UUID[] uuidArr, BluetoothSearchResponse bluetoothSearchResponse) {
        super.a(uuidArr, bluetoothSearchResponse);
        d();
        if (this.f18540a.isDiscovering()) {
            Log.i("stopScan", "BCS cancelDiscovery");
            this.f18540a.cancelDiscovery();
        }
        Log.i("startScan", "BCS startDiscovery");
        this.f18540a.startDiscovery();
    }

    public void a() {
        e();
        if (this.f18540a.isDiscovering()) {
            Log.i("stopScan", "BCS cancelDiscovery");
            this.f18540a.cancelDiscovery();
        }
        super.a();
    }

    /* access modifiers changed from: protected */
    public void b() {
        e();
        if (this.f18540a.isDiscovering()) {
            Log.i("stopScan", "BCS cancelDiscovery");
            this.f18540a.cancelDiscovery();
        }
        super.b();
    }

    private void d() {
        if (this.d == null) {
            this.d = new BluetoothSearchReceiver();
            BluetoothUtils.n().registerReceiver(this.d, new IntentFilter("android.bluetooth.device.action.FOUND"));
        }
    }

    private void e() {
        if (this.d != null) {
            BluetoothUtils.n().unregisterReceiver(this.d);
            this.d = null;
        }
    }

    private class BluetoothSearchReceiver extends BroadcastReceiver {
        private BluetoothSearchReceiver() {
        }

        public void onReceive(Context context, Intent intent) {
            if (intent.getAction().equals("android.bluetooth.device.action.FOUND")) {
                BluetoothSearchResult bluetoothSearchResult = new BluetoothSearchResult((BluetoothDevice) intent.getParcelableExtra("android.bluetooth.device.extra.DEVICE"), intent.getShortExtra("android.bluetooth.device.extra.RSSI", ShortCompanionObject.f2832a), (byte[]) null);
                bluetoothSearchResult.c();
                BluetoothClassicSearcher.this.a(bluetoothSearchResult);
            }
        }
    }
}
