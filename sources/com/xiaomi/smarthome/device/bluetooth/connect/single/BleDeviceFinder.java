package com.xiaomi.smarthome.device.bluetooth.connect.single;

import android.text.TextUtils;
import com.xiaomi.smarthome.application.SHApplication;
import com.xiaomi.smarthome.core.entity.bluetooth.SearchRequest;
import com.xiaomi.smarthome.device.BleDevice;
import com.xiaomi.smarthome.device.bluetooth.BLEDeviceManager;
import com.xiaomi.smarthome.device.bluetooth.MiioBtSearchResponse;
import com.xiaomi.smarthome.device.bluetooth.utils.BluetoothHelper;
import com.xiaomi.smarthome.download.Constants;
import com.xiaomi.smarthome.frame.log.BluetoothMyLogger;

public class BleDeviceFinder {

    /* renamed from: a  reason: collision with root package name */
    private static final String f15209a = "BleDeviceFinder";
    private static final int b = 8000;
    private static final int c = 2;
    /* access modifiers changed from: private */
    public boolean d = false;
    /* access modifiers changed from: private */
    public String e;
    /* access modifiers changed from: private */
    public BleDeviceFinderCallback f;
    /* access modifiers changed from: private */
    public Runnable g = new Runnable() {
        public void run() {
            if (BleDeviceFinder.this.d) {
                if (BLEDeviceManager.e()) {
                    BLEDeviceManager.a(new SearchRequest.Builder().a(8000, 2).a(), BleDeviceFinder.this.h);
                } else {
                    SHApplication.getGlobalHandler().postDelayed(BleDeviceFinder.this.g, Constants.x);
                }
            }
        }
    };
    /* access modifiers changed from: private */
    public final MiioBtSearchResponse h = new MiioBtSearchResponse() {
        public void a() {
        }

        public void a(BleDevice bleDevice) {
            if (BleDeviceFinder.this.d && bleDevice != null && TextUtils.equals(bleDevice.mac, BleDeviceFinder.this.e)) {
                boolean unused = BleDeviceFinder.this.d = false;
                BluetoothHelper.b();
                BleDeviceFinder.this.f.a();
            }
        }

        public void b() {
            if (BleDeviceFinder.this.d) {
                boolean unused = BleDeviceFinder.this.d = false;
                BleDeviceFinder.this.f.b();
            }
        }

        public void c() {
            if (BleDeviceFinder.this.d) {
                boolean unused = BleDeviceFinder.this.d = false;
                BleDeviceFinder.this.f.b();
            }
        }
    };

    public interface BleDeviceFinderCallback {
        void a();

        void b();
    }

    public void a(String str, BleDeviceFinderCallback bleDeviceFinderCallback) {
        BluetoothMyLogger.f("BleDeviceFinder startFindDevice");
        if (TextUtils.isEmpty(str)) {
            BluetoothMyLogger.f("BleDeviceFinder mac is empty");
        } else if (bleDeviceFinderCallback == null) {
            BluetoothMyLogger.f("BleDeviceFinder callback is empty");
        } else {
            this.d = true;
            this.e = str;
            this.f = bleDeviceFinderCallback;
            if (BLEDeviceManager.e()) {
                BLEDeviceManager.a(new SearchRequest.Builder().a(8000, 2).a(), this.h);
                return;
            }
            BLEDeviceManager.f();
            SHApplication.getGlobalHandler().removeCallbacks(this.g);
            SHApplication.getGlobalHandler().postDelayed(this.g, Constants.x);
        }
    }

    public void a() {
        BluetoothMyLogger.f("BleDeviceFinder stopFindDevice, mIsFinding = " + this.d);
        if (this.d) {
            this.d = false;
            SHApplication.getGlobalHandler().removeCallbacks(this.g);
            BluetoothHelper.b();
        }
    }
}
