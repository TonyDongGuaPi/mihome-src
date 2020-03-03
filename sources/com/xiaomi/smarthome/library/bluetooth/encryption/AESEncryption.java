package com.xiaomi.smarthome.library.bluetooth.encryption;

import com.xiaomi.smarthome.library.bluetooth.utils.AESCryptUtils;
import com.xiaomi.smarthome.library.bluetooth.utils.BluetoothLog;

public final class AESEncryption implements Encryption {
    public String a(String str, String str2) {
        try {
            return AESCryptUtils.a(str, str2);
        } catch (Throwable th) {
            BluetoothLog.a(th);
            return str2;
        }
    }

    public String b(String str, String str2) {
        try {
            return AESCryptUtils.b(str, str2);
        } catch (Throwable th) {
            BluetoothLog.a(th);
            return str2;
        }
    }
}
