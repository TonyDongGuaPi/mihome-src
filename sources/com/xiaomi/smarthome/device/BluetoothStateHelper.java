package com.xiaomi.smarthome.device;

import android.os.Handler;
import android.os.Looper;
import com.xiaomi.smarthome.application.SHApplication;
import com.xiaomi.smarthome.device.bluetooth.receiver.BluetoothStateReceiver;
import com.xiaomi.smarthome.library.bluetooth.connect.response.BleResponse;
import com.xiaomi.smarthome.library.bluetooth.utils.BluetoothLog;
import com.xiaomi.smarthome.library.bluetooth.utils.BluetoothUtils;

public class BluetoothStateHelper {

    /* renamed from: a  reason: collision with root package name */
    private static BluetoothStateReceiver.BluetoothStateChangeListener f14758a;

    private static void b() {
        BluetoothStateReceiver.b().a(f14758a);
    }

    /* access modifiers changed from: private */
    public static void c() {
        BluetoothStateReceiver.b().b(f14758a);
        f14758a = null;
    }

    private static String a(int i) {
        switch (i) {
            case 10:
                return "state_off";
            case 11:
                return "state_turning_on";
            case 12:
                return "state_on";
            case 13:
                return "state_turning_off";
            default:
                return "unknown " + i;
        }
    }

    /* access modifiers changed from: private */
    public static void b(final BleResponse bleResponse, final int i) {
        BluetoothUtils.c(new Runnable() {
            public void run() {
                BluetoothLog.c(String.format("BluetoothState change response: %d", new Object[]{Integer.valueOf(i)}));
                bleResponse.a(i, null);
            }
        });
    }

    public static void a(BleResponse bleResponse) {
        if (bleResponse != null && f14758a == null) {
            SHApplication.getGlobalWorkerHandler().post(new Runnable(new Handler(Looper.getMainLooper())) {
                private final /* synthetic */ Handler f$1;

                {
                    this.f$1 = r2;
                }

                public final void run() {
                    BluetoothStateHelper.b(BleResponse.this, this.f$1);
                }
            });
        }
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ void b(final BleResponse bleResponse, final Handler handler) {
        int c = BluetoothUtils.c();
        BluetoothLog.c(String.format("openBluetooth: current status = %s", new Object[]{a(c)}));
        switch (c) {
            case 10:
            case 13:
                if (!BluetoothUtils.k()) {
                    b(bleResponse, -1);
                    return;
                }
                break;
            case 11:
                break;
            case 12:
                b(bleResponse, 0);
                return;
            default:
                b(bleResponse, -1);
                return;
        }
        f14758a = new BluetoothStateReceiver.BluetoothStateChangeListener() {
            public void a(int i, int i2) {
                if (i != 12 && i2 == 12) {
                    handler.removeCallbacksAndMessages((Object) null);
                    BluetoothStateHelper.c();
                    BluetoothStateHelper.b(bleResponse, 0);
                }
            }
        };
        handler.postDelayed(new Runnable() {
            public void run() {
                handler.removeCallbacksAndMessages((Object) null);
                BluetoothStateHelper.c();
                BluetoothStateHelper.b(bleResponse, -7);
            }
        }, 10000);
        b();
    }

    public static void b(BleResponse bleResponse) {
        if (bleResponse != null && f14758a == null) {
            SHApplication.getGlobalWorkerHandler().post(new Runnable(new Handler(Looper.getMainLooper())) {
                private final /* synthetic */ Handler f$1;

                {
                    this.f$1 = r2;
                }

                public final void run() {
                    BluetoothStateHelper.a(BleResponse.this, this.f$1);
                }
            });
        }
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ void a(final BleResponse bleResponse, final Handler handler) {
        int c = BluetoothUtils.c();
        BluetoothLog.c(String.format("closeBluetooth: current status = %s", new Object[]{a(c)}));
        switch (c) {
            case 10:
                b(bleResponse, 0);
                return;
            case 11:
            case 12:
                if (!BluetoothUtils.f()) {
                    b(bleResponse, -1);
                    return;
                }
                break;
            case 13:
                break;
            default:
                b(bleResponse, -1);
                return;
        }
        f14758a = new BluetoothStateReceiver.BluetoothStateChangeListener() {
            public void a(int i, int i2) {
                if (i != 10 && i2 == 10) {
                    handler.removeCallbacksAndMessages((Object) null);
                    BluetoothStateHelper.c();
                    BluetoothStateHelper.b(bleResponse, 0);
                }
            }
        };
        handler.postDelayed(new Runnable() {
            public void run() {
                handler.removeCallbacksAndMessages((Object) null);
                BluetoothStateHelper.c();
                BluetoothStateHelper.b(bleResponse, -7);
            }
        }, 10000);
        b();
    }
}
