package com.xiaomi.smarthome.device.utils;

import android.annotation.TargetApi;
import android.app.KeyguardManager;
import android.bluetooth.BluetoothAdapter;
import android.provider.Settings;
import android.text.TextUtils;
import android.util.Log;
import com.xiaomi.smarthome.application.SHApplication;
import com.xiaomi.smarthome.device.BleDevice;
import com.xiaomi.smarthome.device.Device;
import com.xiaomi.smarthome.device.SmartHomeDeviceHelper;
import com.xiaomi.smarthome.device.bluetooth.BleCacheUtils;
import java.util.List;
import miui.bluetooth.ble.MiBleProfile;
import miui.bluetooth.ble.MiBleUnlockProfile;

public class BleManager {

    /* renamed from: a  reason: collision with root package name */
    public static final String f15415a = "BleManager";
    static BleDevice b = null;
    static MiBleUnlockProfile c = null;
    static int d = -1;
    private static final String e = "bluetooth_unlock_status";

    public interface OnUnlockListener {
        void a();

        void b();
    }

    public static void a() {
        Log.d(f15415a, "initBleState");
        d = -1;
    }

    @TargetApi(16)
    public static boolean b() {
        boolean isKeyguardSecure = ((KeyguardManager) SHApplication.getAppContext().getSystemService("keyguard")).isKeyguardSecure();
        String str = f15415a;
        Log.d(str, "isKeyguradSecure=" + isKeyguardSecure);
        return isKeyguardSecure;
    }

    public static boolean c() {
        BluetoothAdapter defaultAdapter = BluetoothAdapter.getDefaultAdapter();
        if (defaultAdapter != null && defaultAdapter.isEnabled()) {
            return true;
        }
        return false;
    }

    public static Device d() {
        List<Device> m = SmartHomeDeviceHelper.a().m();
        for (int i = 0; i < m.size(); i++) {
            Device device = m.get(i);
            if ((device instanceof BleDevice) && a(((BleDevice) device).mac)) {
                return device;
            }
        }
        return null;
    }

    public static boolean a(String str) {
        if (TextUtils.isEmpty(str)) {
            return false;
        }
        return TextUtils.equals(BleCacheUtils.j(str), "xiaomi.ble.v1");
    }

    public static boolean e() {
        boolean z = false;
        if (!c()) {
            return false;
        }
        try {
            if (Settings.Secure.getInt(SHApplication.getAppContext().getContentResolver(), e, 0) != 0) {
                z = true;
            }
        } catch (Throwable unused) {
        }
        String str = f15415a;
        Log.d(str, "getBluetoothUnlockEnabled=" + z);
        return z;
    }

    public static boolean f() {
        String str = f15415a;
        Log.d(str, "isBleUnlocked,state=" + d);
        if (d == 2) {
            return true;
        }
        return d == 1 ? false : false;
    }

    public static void g() {
        Log.d(f15415a, "disconnectBleDevice");
        try {
            if (c != null) {
                c.disconnect();
            }
        } catch (Throwable unused) {
        }
        b = null;
    }

    public static void a(BleDevice bleDevice, final OnUnlockListener onUnlockListener) {
        Log.d(f15415a, "connectBleDevice");
        try {
            a();
            if (bleDevice == null) {
                g();
            } else if (b == null) {
                b = bleDevice;
                c = new MiBleUnlockProfile(SHApplication.getAppContext(), b.mac, new MiBleProfile.IProfileStateChangeCallback() {
                    public void onState(int i) {
                        String str = BleManager.f15415a;
                        Log.d(str, "onState=" + i);
                        if (i == 4) {
                            BleManager.c.registerUnlockListener(new MiBleUnlockProfile.OnUnlockStateChangeListener() {
                                public void onUnlocked(byte b) {
                                    String str = BleManager.f15415a;
                                    Log.d(str, "onUnlocked,state=" + b);
                                    if (BleManager.d != b) {
                                        BleManager.d = b;
                                        if (onUnlockListener == null) {
                                            return;
                                        }
                                        if (BleManager.d == 2) {
                                            onUnlockListener.a();
                                        } else {
                                            onUnlockListener.b();
                                        }
                                    }
                                }
                            });
                        }
                    }
                });
                c.connect();
            }
        } catch (Throwable unused) {
        }
    }
}
