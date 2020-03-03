package com.mi.global.shop.util;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.PrintWriter;

public class FileLog {

    /* renamed from: a  reason: collision with root package name */
    public static final boolean f7093a = true;
    static PrintWriter b;

    private static synchronized void b() {
        synchronized (FileLog.class) {
            if (b == null) {
                try {
                    b = new PrintWriter(new BufferedWriter(new FileWriter("/sdcard/milog.txt", true)));
                } catch (Exception unused) {
                    b = null;
                }
            }
        }
        return;
    }

    public static synchronized void a(String str) {
        synchronized (FileLog.class) {
            if (b == null) {
                b();
            }
            if (b != null) {
                b.println(str);
                b.flush();
                a();
            }
        }
    }

    public static synchronized void a(byte[] bArr) {
        synchronized (FileLog.class) {
            a(b(bArr));
        }
    }

    private static String b(byte[] bArr) {
        StringBuilder sb = new StringBuilder("");
        if (bArr == null || bArr.length <= 0) {
            return null;
        }
        for (byte b2 : bArr) {
            String hexString = Integer.toHexString(b2 & 255);
            if (hexString.length() < 2) {
                sb.append(0);
            }
            sb.append(hexString);
        }
        return sb.toString();
    }

    public static synchronized void a(Throwable th) {
        synchronized (FileLog.class) {
            if (b == null) {
                b();
            }
            if (b != null) {
                th.printStackTrace(b);
                b.flush();
                a();
            }
        }
    }

    public static synchronized void a(String str, Throwable th) {
        synchronized (FileLog.class) {
            if (b == null) {
                b();
            }
            if (b != null) {
                b.println(str);
                th.printStackTrace(b);
                b.flush();
                a();
            }
        }
    }

    public static synchronized void a() {
        synchronized (FileLog.class) {
            if (b != null) {
                b.close();
                b = null;
            }
        }
    }
}
