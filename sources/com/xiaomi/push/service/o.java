package com.xiaomi.push.service;

import android.content.Context;
import android.content.Intent;
import android.util.Pair;
import com.xiaomi.channel.commonutils.logger.b;
import com.xiaomi.push.gf;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class o {

    /* renamed from: a  reason: collision with root package name */
    private static ArrayList<Pair<String, byte[]>> f12934a = new ArrayList<>();

    /* renamed from: a  reason: collision with other field name */
    private static final Map<String, byte[]> f352a = new HashMap();

    public static void a(Context context, int i, String str) {
        synchronized (f352a) {
            for (String next : f352a.keySet()) {
                a(context, next, f352a.get(next), i, str);
            }
            f352a.clear();
        }
    }

    public static void a(Context context, String str, byte[] bArr, int i, String str2) {
        Intent intent = new Intent("com.xiaomi.mipush.ERROR");
        intent.setPackage(str);
        intent.putExtra("mipush_payload", bArr);
        intent.putExtra("mipush_error_code", i);
        intent.putExtra("mipush_error_msg", str2);
        context.sendBroadcast(intent, w.a(str));
    }

    public static void a(XMPushService xMPushService) {
        try {
            synchronized (f352a) {
                for (String next : f352a.keySet()) {
                    w.a(xMPushService, next, f352a.get(next));
                }
                f352a.clear();
            }
        } catch (gf e) {
            b.a((Throwable) e);
            xMPushService.a(10, (Exception) e);
        }
    }

    public static void a(String str, byte[] bArr) {
        synchronized (f352a) {
            f352a.put(str, bArr);
        }
    }

    public static void b(XMPushService xMPushService) {
        ArrayList<Pair<String, byte[]>> arrayList;
        try {
            synchronized (f12934a) {
                arrayList = f12934a;
                f12934a = new ArrayList<>();
            }
            Iterator<Pair<String, byte[]>> it = arrayList.iterator();
            while (it.hasNext()) {
                Pair next = it.next();
                w.a(xMPushService, (String) next.first, (byte[]) next.second);
            }
        } catch (gf e) {
            b.a((Throwable) e);
            xMPushService.a(10, (Exception) e);
        }
    }

    public static void b(String str, byte[] bArr) {
        synchronized (f12934a) {
            f12934a.add(new Pair(str, bArr));
            if (f12934a.size() > 50) {
                f12934a.remove(0);
            }
        }
    }
}
