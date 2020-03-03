package com.xiaomi.smarthome.library.bluetooth.search;

import android.os.Handler;
import android.os.Message;
import com.xiaomi.smarthome.library.bluetooth.utils.BluetoothLog;
import com.xiaomi.smarthome.library.bluetooth.utils.BluetoothUtils;
import com.xiaomi.smarthome.library.common.util.MessageHandlerThread;

public class BluetoothSearchHelper {

    /* renamed from: a  reason: collision with root package name */
    private static final int f18526a = 16;
    private static final int b = 32;
    private MessageHandlerThread c;
    private Handler d;
    /* access modifiers changed from: private */
    public BluetoothSearchRequest e;

    public void a(BluetoothSearchRequest bluetoothSearchRequest, BluetoothSearchResponse bluetoothSearchResponse) {
        if (bluetoothSearchRequest != null && bluetoothSearchResponse != null) {
            bluetoothSearchRequest.a((BluetoothSearchResponse) new BluetoothSearchResponseWrapper(bluetoothSearchResponse));
            if (BluetoothUtils.b()) {
                this.d.obtainMessage(16, bluetoothSearchRequest).sendToTarget();
            } else {
                a(bluetoothSearchRequest);
            }
        }
    }

    public boolean a() {
        return this.e != null;
    }

    public void a(BluetoothSearchRequest bluetoothSearchRequest) {
        this.d.obtainMessage(32, bluetoothSearchRequest).sendToTarget();
    }

    /* access modifiers changed from: private */
    public void b(BluetoothSearchRequest bluetoothSearchRequest) {
        if (this.e != null) {
            this.e.b();
            this.e = bluetoothSearchRequest;
            this.e.a();
            return;
        }
        this.e = bluetoothSearchRequest;
        this.e.a();
    }

    /* access modifiers changed from: private */
    public void c(BluetoothSearchRequest bluetoothSearchRequest) {
        if (this.e != null) {
            if (this.e == bluetoothSearchRequest || bluetoothSearchRequest == null) {
                this.e.b();
                this.e = null;
            }
        } else if (bluetoothSearchRequest != null) {
            bluetoothSearchRequest.b();
        }
    }

    private class BluetoothSearchResponseWrapper implements BluetoothSearchResponse {
        private BluetoothSearchResponse b;

        private BluetoothSearchResponseWrapper(BluetoothSearchResponse bluetoothSearchResponse) {
            this.b = bluetoothSearchResponse;
        }

        public void a() {
            BluetoothLog.c("Bluetooth search start");
            BluetoothSearchResponser.a().a(this.b);
        }

        public void a(BluetoothSearchResult bluetoothSearchResult) {
            BluetoothDeviceHandler.a().a(bluetoothSearchResult, this.b);
        }

        public void b() {
            BluetoothSearchRequest unused = BluetoothSearchHelper.this.e = null;
            BluetoothLog.c("Bluetooth search stop");
            BluetoothSearchResponser.a().b(this.b);
        }

        public void c() {
            BluetoothSearchRequest unused = BluetoothSearchHelper.this.e = null;
            BluetoothLog.c("Bluetooth search cancel");
            BluetoothSearchResponser.a().c(this.b);
        }
    }

    private BluetoothSearchHelper() {
        this.c = new MessageHandlerThread("BluetoothSearch");
        this.c.start();
        this.d = new Handler(this.c.getLooper()) {
            public void handleMessage(Message message) {
                BluetoothSearchRequest bluetoothSearchRequest = (BluetoothSearchRequest) message.obj;
                int i = message.what;
                if (i == 16) {
                    BluetoothSearchHelper.this.b(bluetoothSearchRequest);
                } else if (i == 32) {
                    BluetoothSearchHelper.this.c(bluetoothSearchRequest);
                }
            }
        };
    }

    public static BluetoothSearchHelper b() {
        return BluetoothSearchManagerHolder.f18528a;
    }

    private static class BluetoothSearchManagerHolder {
        /* access modifiers changed from: private */

        /* renamed from: a  reason: collision with root package name */
        public static BluetoothSearchHelper f18528a = new BluetoothSearchHelper();

        private BluetoothSearchManagerHolder() {
        }
    }
}
