package com.xiaomi.smarthome.library.bluetooth.utils;

import android.content.Intent;
import android.os.Handler;
import android.os.HandlerThread;
import android.text.TextUtils;
import android.util.Log;
import com.xiaomi.smarthome.library.bluetooth.BluetoothConstants;
import com.xiaomi.smarthome.library.bluetooth.BluetoothContextManager;
import com.xiaomi.smarthome.library.common.util.XMStringUtils;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.logging.Level;

public class BluetoothLog {

    /* renamed from: a  reason: collision with root package name */
    private static final String f18546a = "miio-bluetooth";
    private static Handler b;

    public static boolean a() {
        return false;
    }

    private static boolean b() {
        return !BluetoothContextManager.o();
    }

    public static void a(final Level level, final String str) {
        if (b == null) {
            HandlerThread handlerThread = new HandlerThread("BluetoothLog");
            handlerThread.start();
            b = new Handler(handlerThread.getLooper());
        }
        b.post(new Runnable() {
            public void run() {
                if (!TextUtils.isEmpty(str)) {
                    Intent intent = new Intent(BluetoothConstants.F);
                    intent.putExtra("message", str);
                    intent.putExtra("level", level);
                    BluetoothUtils.a(intent);
                }
            }
        });
    }

    public static void a(String str) {
        if (b()) {
            Log.i(f18546a, str);
            a(Level.INFO, str);
        }
    }

    public static void b(String str) {
        if (b()) {
            Log.e(f18546a, str);
            a(Level.SEVERE, str);
        }
    }

    public static void c(String str) {
        if (b()) {
            Log.v(f18546a, str);
            a(Level.FINE, str);
        }
    }

    public static void d(String str) {
        if (b()) {
            Log.d(f18546a, str);
            a(Level.FINE, str);
        }
    }

    public static void a(String str, Object... objArr) {
        if (b()) {
            String format = String.format(str, objArr);
            Log.d(f18546a, format);
            a(Level.FINE, format);
        }
    }

    public static void e(String str) {
        if (b()) {
            Log.w(f18546a, str);
            a(Level.WARNING, str);
        }
    }

    public static void a(Throwable th) {
        b(c(th));
    }

    public static void b(Throwable th) {
        e(c(th));
    }

    private static String c(Throwable th) {
        StringWriter stringWriter = new StringWriter();
        PrintWriter printWriter = new PrintWriter(stringWriter);
        while (th != null) {
            th.printStackTrace(printWriter);
            th = th.getCause();
        }
        String obj = stringWriter.toString();
        printWriter.close();
        return XMStringUtils.i(obj);
    }

    public static void f(String str) {
        e(Log.getStackTraceString(new Throwable(str)));
    }
}
