package com.xiaomi.smarthome.frame.log;

import android.content.Context;
import android.text.TextUtils;
import com.xiaomi.smarthome.frame.core.CoreApi;
import com.xiaomi.smarthome.frame.process.ProcessUtil;
import com.xiaomi.smarthome.library.bluetooth.BluetoothContextManager;
import com.xiaomi.smarthome.library.bluetooth.utils.BluetoothLog;
import com.xiaomi.smarthome.library.crypto.Base64Coder;
import java.io.UnsupportedEncodingException;

public class BluetoothMyLogger {
    public static String a(String str) {
        if (TextUtils.isEmpty(str)) {
            return "";
        }
        if (!BluetoothContextManager.o()) {
            return str;
        }
        try {
            return Base64Coder.a(str.getBytes("UTF-8"), 24);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            return "";
        }
    }

    public static void b(String str) {
        g(str);
        BluetoothLog.a(str);
    }

    public static void c(String str) {
        g(str);
        BluetoothLog.b(str);
    }

    public static void d(String str) {
        g(str);
        BluetoothLog.c(str);
    }

    public static void e(String str) {
        g(str);
        BluetoothLog.d(str);
    }

    public static void f(String str) {
        g(str);
        BluetoothLog.e(str);
    }

    private static void g(String str) {
        String str2 = "core-bluetooth: " + str;
        Context n = BluetoothContextManager.n();
        if (n == null) {
            return;
        }
        if (ProcessUtil.i(n)) {
            MyLogger.a().a(str2);
        } else {
            CoreApi.a().a(0, (String) null, str2);
        }
    }
}
