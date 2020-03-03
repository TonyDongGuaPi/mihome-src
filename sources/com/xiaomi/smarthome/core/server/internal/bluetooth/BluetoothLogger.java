package com.xiaomi.smarthome.core.server.internal.bluetooth;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.text.TextUtils;
import com.xiaomi.smarthome.library.bluetooth.BluetoothConstants;
import com.xiaomi.smarthome.library.bluetooth.utils.BluetoothLog;
import com.xiaomi.smarthome.library.bluetooth.utils.BluetoothUtils;
import com.xiaomi.smarthome.library.common.util.SerialExecutor;
import com.xiaomi.smarthome.library.common.util.Task;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.Writer;
import java.util.Calendar;
import java.util.concurrent.Executor;

public class BluetoothLogger {

    /* renamed from: a  reason: collision with root package name */
    private static final String f14134a = "miio-bluetooth";
    private static final int b = 1;
    private static final int c = 2000;
    private static Context d;
    private static Calendar e;
    /* access modifiers changed from: private */
    public static Writer f;
    private static Executor g = new SerialExecutor();
    private static Handler h = new Handler(Looper.getMainLooper()) {
        public void handleMessage(Message message) {
            if (message.what == 1) {
                boolean unused = BluetoothLogger.h();
            }
        }
    };

    public static void a(Context context) {
        d = context;
        e = Calendar.getInstance();
        g = new SerialExecutor();
        BluetoothUtils.a((BroadcastReceiver) new BluetoothLogReceiver(), new IntentFilter(BluetoothConstants.F));
        try {
            f();
        } catch (Throwable th) {
            BluetoothLog.a(th);
        }
        e();
    }

    /* access modifiers changed from: private */
    public static void e() {
        if (h.hasMessages(1)) {
            h.removeMessages(1);
        }
        h.sendEmptyMessageDelayed(1, 2000);
    }

    private static void f() throws Exception {
        String format = String.format("%s_%s.log", new Object[]{f14134a, j()});
        File g2 = g();
        if (g2 != null) {
            f = new BufferedWriter(new FileWriter(new File(g2, format), true));
        }
    }

    private static File g() {
        File externalFilesDir = d.getExternalFilesDir("log");
        if (externalFilesDir == null) {
            return null;
        }
        if (externalFilesDir.exists() && externalFilesDir.isFile()) {
            externalFilesDir.delete();
        }
        if (externalFilesDir.exists() || externalFilesDir.mkdirs()) {
            return externalFilesDir;
        }
        return null;
    }

    /* access modifiers changed from: private */
    public static boolean h() {
        if (f == null) {
            return false;
        }
        Task.a((Task) new Task() {
            public void a() {
                try {
                    if (BluetoothLogger.f != null) {
                        BluetoothLogger.f.flush();
                    }
                } catch (Exception e) {
                    Writer unused = BluetoothLogger.f = null;
                    e.printStackTrace();
                }
            }
        }, g);
        return true;
    }

    /* access modifiers changed from: private */
    public static void b(final String str) {
        if (f != null && !TextUtils.isEmpty(str)) {
            Task.a((Task) new Task() {
                public void a() {
                    try {
                        if (BluetoothLogger.f != null) {
                            BluetoothLogger.f.write(String.format("%s: %s\n", new Object[]{BluetoothLogger.i(), str}));
                            BluetoothLogger.e();
                        }
                    } catch (Exception e) {
                        Writer unused = BluetoothLogger.f = null;
                        e.printStackTrace();
                    }
                }
            }, g);
        }
    }

    /* access modifiers changed from: private */
    public static String i() {
        e.setTimeInMillis(System.currentTimeMillis());
        return String.format("%02d:%02d:%02d.%03d", new Object[]{Integer.valueOf(e.get(11)), Integer.valueOf(e.get(12)), Integer.valueOf(e.get(13)), Integer.valueOf(e.get(14))});
    }

    private static String j() {
        e.setTimeInMillis(System.currentTimeMillis());
        return String.format("%02d-%02d", new Object[]{Integer.valueOf(e.get(2) + 1), Integer.valueOf(e.get(5))});
    }

    private static class BluetoothLogReceiver extends BroadcastReceiver {
        private BluetoothLogReceiver() {
        }

        public void onReceive(Context context, Intent intent) {
            if (intent != null && BluetoothConstants.F.equals(intent.getAction())) {
                String stringExtra = intent.getStringExtra("message");
                if (!TextUtils.isEmpty(stringExtra)) {
                    BluetoothLogger.b(stringExtra);
                }
            }
        }
    }
}
