package com.xiaomi.smarthome.library.bluetooth.search.le;

import android.annotation.TargetApi;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.util.Log;
import com.xiaomi.smarthome.library.bluetooth.search.BluetoothSearchResponse;
import com.xiaomi.smarthome.library.bluetooth.search.BluetoothSearchResult;
import com.xiaomi.smarthome.library.bluetooth.search.BluetoothSearcher;
import com.xiaomi.smarthome.library.bluetooth.utils.BluetoothLog;
import com.xiaomi.smarthome.library.bluetooth.utils.BluetoothUtils;
import java.util.UUID;

public class BluetoothLESearcher extends BluetoothSearcher {
    private final BluetoothAdapter.LeScanCallback d;

    private BluetoothLESearcher() {
        this.d = new BluetoothAdapter.LeScanCallback() {
            public void onLeScan(BluetoothDevice bluetoothDevice, int i, byte[] bArr) {
                BluetoothSearchResult bluetoothSearchResult = new BluetoothSearchResult(bluetoothDevice, i, bArr);
                bluetoothSearchResult.a();
                BluetoothLESearcher.this.a(bluetoothSearchResult);
            }
        };
        this.f18540a = BluetoothUtils.e();
    }

    public static BluetoothLESearcher c() {
        return BluetoothLESearcherHolder.f18544a;
    }

    private static class BluetoothLESearcherHolder {
        /* access modifiers changed from: private */

        /* renamed from: a  reason: collision with root package name */
        public static BluetoothLESearcher f18544a = new BluetoothLESearcher();

        private BluetoothLESearcherHolder() {
        }
    }

    @TargetApi(18)
    public void a(UUID[] uuidArr, BluetoothSearchResponse bluetoothSearchResponse) {
        super.a(uuidArr, bluetoothSearchResponse);
        try {
            Log.i("startScan", "BLS startLeScan");
            this.f18540a.startLeScan(uuidArr, this.d);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void a() {
        d();
        super.a();
    }

    /* access modifiers changed from: protected */
    public void b() {
        d();
        super.b();
    }

    @TargetApi(18)
    private void d() {
        try {
            if (this.f18540a != null) {
                Log.i("stopScan", "BLS stopLeScan");
                this.f18540a.stopLeScan(this.d);
            }
        } catch (Throwable th) {
            BluetoothLog.a(th);
        }
    }
}
