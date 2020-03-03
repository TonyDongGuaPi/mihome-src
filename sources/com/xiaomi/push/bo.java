package com.xiaomi.push;

import android.content.Context;
import android.content.SharedPreferences;

public class bo {

    /* renamed from: a  reason: collision with root package name */
    private static volatile bo f12652a;
    private Context b;

    private bo(Context context) {
        this.b = context;
    }

    public static bo a(Context context) {
        if (f12652a == null) {
            synchronized (bo.class) {
                if (f12652a == null) {
                    f12652a = new bo(context);
                }
            }
        }
        return f12652a;
    }

    public synchronized void a(String str, String str2, long j) {
        SharedPreferences.Editor edit = this.b.getSharedPreferences(str, 4).edit();
        edit.putLong(str2, j);
        edit.commit();
    }

    public synchronized void a(String str, String str2, String str3) {
        SharedPreferences.Editor edit = this.b.getSharedPreferences(str, 4).edit();
        edit.putString(str2, str3);
        edit.commit();
    }

    /* JADX WARNING: Code restructure failed: missing block: B:10:0x0012, code lost:
        return r5;
     */
    /* JADX WARNING: Exception block dominator not found, dom blocks: [] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public synchronized long b(java.lang.String r3, java.lang.String r4, long r5) {
        /*
            r2 = this;
            monitor-enter(r2)
            android.content.Context r0 = r2.b     // Catch:{ Throwable -> 0x0011, all -> 0x000e }
            r1 = 4
            android.content.SharedPreferences r3 = r0.getSharedPreferences(r3, r1)     // Catch:{ Throwable -> 0x0011, all -> 0x000e }
            long r3 = r3.getLong(r4, r5)     // Catch:{ Throwable -> 0x0011, all -> 0x000e }
            monitor-exit(r2)
            return r3
        L_0x000e:
            r3 = move-exception
            monitor-exit(r2)
            throw r3
        L_0x0011:
            monitor-exit(r2)
            return r5
        */
        throw new UnsupportedOperationException("Method not decompiled: com.xiaomi.push.bo.b(java.lang.String, java.lang.String, long):long");
    }

    /* JADX WARNING: Code restructure failed: missing block: B:10:0x0012, code lost:
        return r5;
     */
    /* JADX WARNING: Exception block dominator not found, dom blocks: [] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public synchronized java.lang.String b(java.lang.String r3, java.lang.String r4, java.lang.String r5) {
        /*
            r2 = this;
            monitor-enter(r2)
            android.content.Context r0 = r2.b     // Catch:{ Throwable -> 0x0011, all -> 0x000e }
            r1 = 4
            android.content.SharedPreferences r3 = r0.getSharedPreferences(r3, r1)     // Catch:{ Throwable -> 0x0011, all -> 0x000e }
            java.lang.String r3 = r3.getString(r4, r5)     // Catch:{ Throwable -> 0x0011, all -> 0x000e }
            monitor-exit(r2)
            return r3
        L_0x000e:
            r3 = move-exception
            monitor-exit(r2)
            throw r3
        L_0x0011:
            monitor-exit(r2)
            return r5
        */
        throw new UnsupportedOperationException("Method not decompiled: com.xiaomi.push.bo.b(java.lang.String, java.lang.String, java.lang.String):java.lang.String");
    }
}
