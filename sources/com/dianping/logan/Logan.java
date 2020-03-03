package com.dianping.logan;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

public class Logan {

    /* renamed from: a  reason: collision with root package name */
    static boolean f5161a = false;
    private static OnLoganProtocolStatus b;
    private static LoganControlCenter c;

    public static void a(LoganConfig loganConfig) {
        c = LoganControlCenter.a(loganConfig);
    }

    public static void a(String str, int i) {
        if (c != null) {
            c.a(str, i);
            return;
        }
        throw new RuntimeException("Please initialize Logan first");
    }

    public static void a() {
        if (c != null) {
            c.a();
            return;
        }
        throw new RuntimeException("Please initialize Logan first");
    }

    public static void a(String[] strArr, SendLogRunnable sendLogRunnable) {
        if (c != null) {
            c.a(strArr, sendLogRunnable);
            return;
        }
        throw new RuntimeException("Please initialize Logan first");
    }

    public static void a(String str, String str2, String str3, String str4, String str5, String str6, String str7, SendLogCallback sendLogCallback) {
        HashMap hashMap = new HashMap();
        hashMap.put("fileDate", str2);
        hashMap.put("appId", str3);
        hashMap.put("unionId", str4);
        hashMap.put("deviceId", str5);
        hashMap.put("buildVersion", str6);
        hashMap.put("appVersion", str7);
        hashMap.put("platform", "1");
        a(str, str2, hashMap, sendLogCallback);
    }

    public static void a(String str, String str2, Map<String, String> map, SendLogCallback sendLogCallback) {
        if (c != null) {
            SendLogDefaultRunnable sendLogDefaultRunnable = new SendLogDefaultRunnable();
            sendLogDefaultRunnable.a(str);
            sendLogDefaultRunnable.a(sendLogCallback);
            sendLogDefaultRunnable.a(map);
            c.a(new String[]{str2}, (SendLogRunnable) sendLogDefaultRunnable);
            return;
        }
        throw new RuntimeException("Please initialize Logan first");
    }

    public static Map<String, Long> b() {
        File[] listFiles;
        if (c != null) {
            File b2 = c.b();
            if (!b2.exists() || (listFiles = b2.listFiles()) == null) {
                return null;
            }
            HashMap hashMap = new HashMap();
            for (File file : listFiles) {
                try {
                    hashMap.put(Util.a(Long.parseLong(file.getName())), Long.valueOf(file.length()));
                } catch (NumberFormatException unused) {
                }
            }
            return hashMap;
        }
        throw new RuntimeException("Please initialize Logan first");
    }

    public static void a(boolean z) {
        f5161a = z;
    }

    static void b(String str, int i) {
        if (b != null) {
            b.a(str, i);
        }
    }

    public static void a(OnLoganProtocolStatus onLoganProtocolStatus) {
        b = onLoganProtocolStatus;
    }
}
