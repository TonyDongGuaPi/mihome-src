package com.xiaomi.smarthome.library.bluetooth.search;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import com.xiaomi.smarthome.library.bluetooth.utils.BluetoothLog;
import com.xiaomi.smarthome.library.bluetooth.utils.BluetoothUtils;

public class BluetoothSearchResponser {

    /* renamed from: a  reason: collision with root package name */
    private static final int f18534a = 64;
    private static final int b = 80;
    private static final int c = 96;
    private static final int d = 112;
    private final Handler e;

    private BluetoothSearchResponser() {
        this.e = new Handler(Looper.getMainLooper()) {
            public void handleMessage(Message message) {
                BluetoothSearchResponse bluetoothSearchResponse = (BluetoothSearchResponse) message.obj;
                int i = message.what;
                if (i == 64) {
                    bluetoothSearchResponse.a();
                } else if (i == 80) {
                    bluetoothSearchResponse.c();
                } else if (i == 96) {
                    bluetoothSearchResponse.b();
                } else if (i == 112) {
                    BluetoothSearchResult bluetoothSearchResult = (BluetoothSearchResult) message.getData().getParcelable("device");
                    if (BluetoothLog.a()) {
                        BluetoothLog.c(String.format("%s device founded: %s", new Object[]{BluetoothUtils.a(bluetoothSearchResult.j()), bluetoothSearchResult.toString()}));
                    }
                    bluetoothSearchResponse.a(bluetoothSearchResult);
                }
            }
        };
    }

    public static BluetoothSearchResponser a() {
        return BluetoothSearchResponserHolder.f18536a;
    }

    private static class BluetoothSearchResponserHolder {
        /* access modifiers changed from: private */

        /* renamed from: a  reason: collision with root package name */
        public static BluetoothSearchResponser f18536a = new BluetoothSearchResponser();

        private BluetoothSearchResponserHolder() {
        }
    }

    public void a(BluetoothSearchResponse bluetoothSearchResponse) {
        this.e.obtainMessage(64, bluetoothSearchResponse).sendToTarget();
    }

    public void b(BluetoothSearchResponse bluetoothSearchResponse) {
        this.e.obtainMessage(96, bluetoothSearchResponse).sendToTarget();
    }

    public void c(BluetoothSearchResponse bluetoothSearchResponse) {
        this.e.obtainMessage(80, bluetoothSearchResponse).sendToTarget();
    }

    public void a(BluetoothSearchResult bluetoothSearchResult, BluetoothSearchResponse bluetoothSearchResponse) {
        Message obtainMessage = this.e.obtainMessage(112, bluetoothSearchResponse);
        obtainMessage.getData().putParcelable("device", bluetoothSearchResult);
        obtainMessage.sendToTarget();
    }
}
