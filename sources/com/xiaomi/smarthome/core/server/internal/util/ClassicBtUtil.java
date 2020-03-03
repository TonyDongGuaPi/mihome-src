package com.xiaomi.smarthome.core.server.internal.util;

import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothProfile;
import java.lang.reflect.Method;

public class ClassicBtUtil {
    public static boolean a(BluetoothDevice bluetoothDevice, BluetoothProfile bluetoothProfile) {
        if (bluetoothDevice == null || bluetoothProfile == null) {
            return false;
        }
        if (bluetoothDevice.getBondState() != 12) {
            Logger.c("ClassicBtUtil connectBluetoothProfile ,device is un bonded ");
            return false;
        }
        a(bluetoothDevice, bluetoothProfile, 100);
        try {
            Method declaredMethod = bluetoothProfile.getClass().getDeclaredMethod("connect", new Class[]{BluetoothDevice.class});
            declaredMethod.setAccessible(true);
            declaredMethod.invoke(bluetoothProfile, new Object[]{bluetoothDevice});
            return true;
        } catch (Exception e) {
            Logger.b("ClassBtUtil connect exception:" + e.getMessage());
            e.printStackTrace();
            return false;
        }
    }

    public static boolean b(BluetoothDevice bluetoothDevice, BluetoothProfile bluetoothProfile) {
        if (bluetoothDevice == null || bluetoothProfile == null) {
            Logger.c("ClassicBtUtil disconnectBluetoothProfile ,device or profile is null! ");
            return false;
        } else if (bluetoothDevice.getBondState() != 12) {
            Logger.c("ClassicBtUtil disconnectBluetoothProfile ,device is un bonded ");
            return false;
        } else {
            a(bluetoothDevice, bluetoothProfile, 0);
            try {
                Method declaredMethod = bluetoothProfile.getClass().getDeclaredMethod("disconnect", new Class[]{BluetoothDevice.class});
                declaredMethod.setAccessible(true);
                declaredMethod.invoke(bluetoothProfile, new Object[]{bluetoothDevice});
                return true;
            } catch (Exception e) {
                Logger.b("ClassBtUtil disconnect exception:" + e.getMessage());
                e.printStackTrace();
                return false;
            }
        }
    }

    public static void a(BluetoothDevice bluetoothDevice, BluetoothProfile bluetoothProfile, int i) {
        if (bluetoothProfile != null) {
            try {
                bluetoothProfile.getClass().getMethod("setPriority", new Class[]{BluetoothDevice.class, Integer.TYPE}).invoke(bluetoothProfile, new Object[]{bluetoothDevice, Integer.valueOf(i)});
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
