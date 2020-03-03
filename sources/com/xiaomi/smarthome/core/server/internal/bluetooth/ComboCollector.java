package com.xiaomi.smarthome.core.server.internal.bluetooth;

import android.text.TextUtils;
import com.xiaomi.smarthome.library.bluetooth.BluetoothContextManager;
import com.xiaomi.smarthome.library.bluetooth.utils.BluetoothLog;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

public class ComboCollector extends BluetoothContextManager {

    /* renamed from: a  reason: collision with root package name */
    private static ConcurrentMap<String, String> f14143a = new ConcurrentHashMap();

    public static void a(String str, String str2) {
        if (!TextUtils.isEmpty(str2) && !TextUtils.isEmpty(str)) {
            BluetoothLog.c(String.format("addCombo: key = %s, mac = %s", new Object[]{str, str2}));
            if (!f14143a.containsKey(str)) {
                f14143a.put(str, str2);
            }
        }
    }

    public static String a(String str) {
        if (!TextUtils.isEmpty(str)) {
            return (String) f14143a.get(str.toUpperCase());
        }
        return null;
    }
}
