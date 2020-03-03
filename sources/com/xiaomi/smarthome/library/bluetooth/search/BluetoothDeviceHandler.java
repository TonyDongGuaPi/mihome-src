package com.xiaomi.smarthome.library.bluetooth.search;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import com.xiaomi.smarthome.library.common.util.MessageHandlerThread;

public class BluetoothDeviceHandler {

    /* renamed from: a  reason: collision with root package name */
    private static final int f18523a = 17;
    private MessageHandlerThread b;
    private Handler c;

    private BluetoothDeviceHandler() {
        this.b = new MessageHandlerThread("BluetoothDeviceHandler");
        this.b.start();
        this.c = new Handler(this.b.getLooper()) {
            public void handleMessage(Message message) {
                BluetoothDeviceHandler.this.a(message);
            }
        };
    }

    public static BluetoothDeviceHandler a() {
        return BluetoothDeviceHandlerHolder.f18525a;
    }

    private static class BluetoothDeviceHandlerHolder {
        /* access modifiers changed from: private */

        /* renamed from: a  reason: collision with root package name */
        public static BluetoothDeviceHandler f18525a = new BluetoothDeviceHandler();

        private BluetoothDeviceHandlerHolder() {
        }
    }

    /* access modifiers changed from: private */
    public void a(Message message) {
        if (message.what == 17) {
            b((BluetoothSearchResult) message.getData().getParcelable("device"), (BluetoothSearchResponse) message.obj);
        }
    }

    public void a(BluetoothSearchResult bluetoothSearchResult, BluetoothSearchResponse bluetoothSearchResponse) {
        Message obtainMessage = this.c.obtainMessage(17, bluetoothSearchResponse);
        Bundle bundle = new Bundle();
        bundle.putParcelable("device", bluetoothSearchResult);
        obtainMessage.setData(bundle);
        obtainMessage.sendToTarget();
    }

    private void b(BluetoothSearchResult bluetoothSearchResult, BluetoothSearchResponse bluetoothSearchResponse) {
        bluetoothSearchResult.e();
        BluetoothSearchResponser.a().a(bluetoothSearchResult, bluetoothSearchResponse);
    }
}
