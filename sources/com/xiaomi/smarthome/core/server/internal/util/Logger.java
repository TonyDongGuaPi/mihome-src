package com.xiaomi.smarthome.core.server.internal.util;

import com.xiaomi.smarthome.library.bluetooth.utils.BluetoothLog;

public class Logger {

    /* renamed from: a  reason: collision with root package name */
    private static final String f14731a = "classic-bluetooth:";

    public static void a(String str) {
        BluetoothLog.d("classic-bluetooth::" + str);
    }

    public static void a(String str, String str2) {
        BluetoothLog.d("classic-bluetooth:child tag:" + str + "=>" + str2);
    }

    public static void b(String str) {
        BluetoothLog.b("classic-bluetooth::" + str);
    }

    public static void b(String str, String str2) {
        BluetoothLog.b("classic-bluetooth:child tag:" + str + "=>" + str2);
    }

    public static void c(String str) {
        BluetoothLog.e("classic-bluetooth::" + str);
    }

    public static void c(String str, String str2) {
        BluetoothLog.e("classic-bluetooth:child tag :" + str + "=>" + str2);
    }
}
