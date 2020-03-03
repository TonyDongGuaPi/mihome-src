package com.xiaomi.mipush.sdk;

import android.content.Context;
import com.xiaomi.channel.commonutils.logger.LoggerInterface;
import com.xiaomi.channel.commonutils.logger.b;
import com.xiaomi.push.Cdo;
import com.xiaomi.push.ai;
import com.xiaomi.push.dn;
import java.io.File;

public class Logger {

    /* renamed from: a  reason: collision with root package name */
    private static boolean f11508a = false;
    private static LoggerInterface b;

    protected static LoggerInterface a() {
        return b;
    }

    public static File a(String str) {
        try {
            File file = new File(str);
            if (file.exists()) {
                if (file.isDirectory()) {
                    File[] listFiles = file.listFiles();
                    for (int i = 0; i < listFiles.length; i++) {
                        if (listFiles[i].isFile() && !listFiles[i].getName().contains("lock") && listFiles[i].getName().contains("log")) {
                            return listFiles[i];
                        }
                    }
                    return null;
                }
            }
            return null;
        } catch (NullPointerException unused) {
            b.d("null pointer exception while retrieve file.");
        }
    }

    public static void a(Context context) {
        f11508a = true;
        c(context);
    }

    public static void a(Context context, LoggerInterface loggerInterface) {
        b = loggerInterface;
        c(context);
    }

    public static void a(Context context, boolean z) {
        ai.a(context).a((Runnable) new u(context, z));
    }

    public static void b(Context context) {
        f11508a = false;
        c(context);
    }

    public static void c(Context context) {
        boolean z = false;
        boolean z2 = b != null;
        if (f11508a) {
            z2 = false;
        } else if (d(context)) {
            z = true;
        }
        Cdo doVar = null;
        LoggerInterface loggerInterface = z2 ? b : null;
        if (z) {
            doVar = new Cdo(context);
        }
        b.a((LoggerInterface) new dn(loggerInterface, doVar));
    }

    private static boolean d(Context context) {
        try {
            String[] strArr = context.getPackageManager().getPackageInfo(context.getPackageName(), 4096).requestedPermissions;
            if (strArr != null) {
                for (String equals : strArr) {
                    if ("android.permission.WRITE_EXTERNAL_STORAGE".equals(equals)) {
                        return true;
                    }
                }
            }
        } catch (Exception unused) {
        }
        return false;
    }
}
