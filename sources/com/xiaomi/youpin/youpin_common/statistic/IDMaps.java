package com.xiaomi.youpin.youpin_common.statistic;

import java.util.HashMap;
import java.util.Map;

public class IDMaps {

    /* renamed from: a  reason: collision with root package name */
    private static Map<String, String> f23802a = new HashMap();

    /* JADX WARNING: Code restructure failed: missing block: B:21:0x002a, code lost:
        return r1;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static synchronized java.lang.String a(java.lang.String r3) {
        /*
            java.lang.Class<com.xiaomi.youpin.youpin_common.statistic.IDMaps> r0 = com.xiaomi.youpin.youpin_common.statistic.IDMaps.class
            monitor-enter(r0)
            boolean r1 = android.text.TextUtils.isEmpty(r3)     // Catch:{ all -> 0x002b }
            if (r1 == 0) goto L_0x000d
            java.lang.String r3 = ""
            monitor-exit(r0)
            return r3
        L_0x000d:
            java.util.Map<java.lang.String, java.lang.String> r1 = f23802a     // Catch:{ all -> 0x002b }
            java.lang.Object r1 = r1.get(r3)     // Catch:{ all -> 0x002b }
            java.lang.String r1 = (java.lang.String) r1     // Catch:{ all -> 0x002b }
            if (r1 != 0) goto L_0x0029
            java.lang.String r1 = "FAjHg6j3MB8tiNJW"
            java.lang.String r2 = "FAjHg6j3MB8tiNJW"
            java.lang.String r1 = com.xiaomi.youpin.common.util.crypto.AesUtil.a((java.lang.String) r3, (java.lang.String) r1, (java.lang.String) r2)     // Catch:{ Exception -> 0x0025 }
            java.util.Map<java.lang.String, java.lang.String> r2 = f23802a     // Catch:{ all -> 0x002b }
            r2.put(r3, r1)     // Catch:{ all -> 0x002b }
            goto L_0x0029
        L_0x0025:
            java.lang.String r3 = ""
            monitor-exit(r0)
            return r3
        L_0x0029:
            monitor-exit(r0)
            return r1
        L_0x002b:
            r3 = move-exception
            monitor-exit(r0)
            throw r3
        */
        throw new UnsupportedOperationException("Method not decompiled: com.xiaomi.youpin.youpin_common.statistic.IDMaps.a(java.lang.String):java.lang.String");
    }
}
