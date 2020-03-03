package com.xiaomi.smarthome.library.bluetooth.connect.request;

import android.bluetooth.BluetoothGattCharacteristic;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import com.xiaomi.smarthome.library.bluetooth.connect.BleResponser;
import com.xiaomi.smarthome.library.bluetooth.connect.listener.WriteCharacterListener;
import com.xiaomi.smarthome.library.bluetooth.utils.BluetoothLog;
import java.util.List;
import java.util.UUID;

public class BleBatchWriteRequest extends BleRequest implements WriteCharacterListener {
    static final int h = 1;
    private static final int w = 2;
    /* access modifiers changed from: private */
    public int A;
    Handler i = new Handler(Looper.myLooper()) {
        public void handleMessage(Message message) {
            if (message.what == 1) {
                BleBatchWriteRequest.a(BleBatchWriteRequest.this);
                if (BleBatchWriteRequest.this.y >= BleBatchWriteRequest.this.A * 2) {
                    BleBatchWriteRequest.this.i.removeCallbacksAndMessages((Object) null);
                    BluetoothLog.a("batchHandler ,try write more time ,stop try", new Object[0]);
                    BleBatchWriteRequest.this.r();
                    BleBatchWriteRequest.this.e(0);
                    return;
                }
                boolean unused = BleBatchWriteRequest.this.s();
            }
        }
    };
    private int x = 2;
    /* access modifiers changed from: private */
    public int y = 1;
    private List<byte[]> z;

    static /* synthetic */ int a(BleBatchWriteRequest bleBatchWriteRequest) {
        int i2 = bleBatchWriteRequest.y;
        bleBatchWriteRequest.y = i2 + 1;
        return i2;
    }

    public BleBatchWriteRequest(UUID uuid, UUID uuid2, List<byte[]> list, BleResponser bleResponser) {
        super(bleResponser);
        this.k = uuid;
        this.l = uuid2;
        this.z = list;
        this.A = list.size();
    }

    public void i() {
        int e = e();
        if (e != 2 && e != 19) {
            e(-1);
        } else if (s()) {
            q();
        }
    }

    /* access modifiers changed from: private */
    public boolean s() {
        if (this.z == null || this.z.isEmpty()) {
            e(0);
            r();
            return false;
        }
        if (a(this.k, this.l, this.z.get(0))) {
            this.z.remove(0);
            BluetoothLog.d("OTARequest write success,add delay time to " + this.x);
            return true;
        }
        BluetoothLog.d("OTARequest write fail,add delay time to " + this.x);
        return true;
    }

    public void a(BluetoothGattCharacteristic bluetoothGattCharacteristic, int i2, byte[] bArr) {
        BluetoothLog.d("BleBatchWriteRequest onCharacteristicWrite status=" + i2);
        this.y = this.y + 1;
        if (this.y >= this.A * 2) {
            e(0);
            r();
            return;
        }
        s();
    }
}
