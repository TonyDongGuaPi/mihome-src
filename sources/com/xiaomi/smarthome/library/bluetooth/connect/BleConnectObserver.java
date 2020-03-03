package com.xiaomi.smarthome.library.bluetooth.connect;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Handler;
import android.os.Looper;
import com.xiaomi.smarthome.library.bluetooth.utils.BluetoothLog;
import com.xiaomi.smarthome.library.bluetooth.utils.BluetoothUtils;
import java.util.ArrayList;
import java.util.List;

public class BleConnectObserver implements IBleConnectObserver {

    /* renamed from: a  reason: collision with root package name */
    private static final int f18507a = 5;
    private static BleConnectObserver b;
    private BleConnectObserverReceiver c;
    private List<BleConnectObservable> d = new ArrayList();
    private Handler e = new Handler(Looper.getMainLooper());

    private BleConnectObserver() {
        b();
    }

    private void b(String str) {
        for (int i = 0; i < this.d.size(); i++) {
            if (this.d.get(i).f18509a.equals(str)) {
                this.d.remove(i);
                return;
            }
        }
    }

    /* access modifiers changed from: private */
    public void c(String str) {
        BleConnectObservable bleConnectObservable;
        int i = 0;
        while (true) {
            if (i >= this.d.size()) {
                bleConnectObservable = null;
                break;
            } else if (this.d.get(i).f18509a.equals(str)) {
                bleConnectObservable = this.d.remove(i);
                break;
            } else {
                i++;
            }
        }
        if (bleConnectObservable == null) {
            bleConnectObservable = new BleConnectObservable(str);
        }
        this.d.add(0, bleConnectObservable);
        if (this.d.size() > 5) {
            BluetoothLog.e(String.format("BleConnectObserver reach limit", new Object[0]));
            for (BleConnectObservable bleConnectObservable2 : this.d) {
                BluetoothLog.e(String.format(">>> mac = %s", new Object[]{bleConnectObservable2.f18509a}));
            }
        }
        while (this.d.size() > 5) {
            BleConnectManager.a().a(this.d.remove(this.d.size() - 1).f18509a);
        }
    }

    public static BleConnectObserver a() {
        if (b == null) {
            synchronized (BleConnectObserver.class) {
                if (b == null) {
                    b = new BleConnectObserver();
                }
            }
        }
        return b;
    }

    private void b() {
        if (this.c == null) {
            this.c = new BleConnectObserverReceiver();
            IntentFilter intentFilter = new IntentFilter();
            intentFilter.addAction("com.xiaomi.smarthome.bluetooth.connect_status_changed");
            intentFilter.addAction("com.xiaomi.smarthome.bluetooth.character_changed");
            BluetoothUtils.a((BroadcastReceiver) this.c, intentFilter);
        }
    }

    public void a(final String str) {
        this.e.post(new Runnable() {
            public void run() {
                if (BluetoothUtils.c(str)) {
                    BleConnectObserver.this.c(str);
                }
            }
        });
    }

    private class BleConnectObservable {

        /* renamed from: a  reason: collision with root package name */
        String f18509a;

        BleConnectObservable(String str) {
            this.f18509a = str;
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (obj == null || getClass() != obj.getClass()) {
                return false;
            }
            return this.f18509a.equals(((BleConnectObservable) obj).f18509a);
        }

        public int hashCode() {
            return this.f18509a.hashCode();
        }
    }

    private class BleConnectObserverReceiver extends BroadcastReceiver {
        private BleConnectObserverReceiver() {
        }

        public void onReceive(Context context, Intent intent) {
            if (intent != null && intent.getAction() != null && intent.hasExtra("key_device_address")) {
                String action = intent.getAction();
                if ("com.xiaomi.smarthome.bluetooth.connect_status_changed".equals(action) && intent.hasExtra("key_connect_status")) {
                    BleConnectObserver.this.a(intent);
                } else if ("com.xiaomi.smarthome.bluetooth.character_changed".equals(action)) {
                    BleConnectObserver.this.b(intent);
                }
            }
        }
    }

    /* access modifiers changed from: private */
    public void a(Intent intent) {
        String stringExtra = intent.getStringExtra("key_device_address");
        int intExtra = intent.getIntExtra("key_connect_status", 0);
        if (intExtra == 16) {
            c(stringExtra);
        } else if (intExtra == 32) {
            b(stringExtra);
        }
    }

    /* access modifiers changed from: private */
    public void b(Intent intent) {
        c(intent.getStringExtra("key_device_address"));
    }
}
