package com.mi.blockcanary.ui;

import com.mi.blockcanary.internal.BlockInfo;
import java.io.File;

final class BlockInfoEx extends BlockInfo {
    private static final String F = "BlockInfoEx";
    public File D;
    public String E;

    BlockInfoEx() {
    }

    /* JADX WARNING: Removed duplicated region for block: B:61:0x0189 A[SYNTHETIC, Splitter:B:61:0x0189] */
    /* JADX WARNING: Removed duplicated region for block: B:69:0x019c A[SYNTHETIC, Splitter:B:69:0x019c] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static com.mi.blockcanary.ui.BlockInfoEx a(java.io.File r6) {
        /*
            com.mi.blockcanary.ui.BlockInfoEx r0 = new com.mi.blockcanary.ui.BlockInfoEx
            r0.<init>()
            r0.D = r6
            r1 = 0
            java.io.InputStreamReader r2 = new java.io.InputStreamReader     // Catch:{ Throwable -> 0x017c, all -> 0x0179 }
            java.io.FileInputStream r3 = new java.io.FileInputStream     // Catch:{ Throwable -> 0x017c, all -> 0x0179 }
            r3.<init>(r6)     // Catch:{ Throwable -> 0x017c, all -> 0x0179 }
            java.lang.String r6 = "UTF-8"
            r2.<init>(r3, r6)     // Catch:{ Throwable -> 0x017c, all -> 0x0179 }
            java.io.BufferedReader r6 = new java.io.BufferedReader     // Catch:{ Throwable -> 0x017c, all -> 0x0179 }
            r6.<init>(r2)     // Catch:{ Throwable -> 0x017c, all -> 0x0179 }
        L_0x0019:
            java.lang.String r1 = r6.readLine()     // Catch:{ Throwable -> 0x0177 }
            if (r1 == 0) goto L_0x0173
            java.lang.String r2 = "cpu_core"
            boolean r2 = r1.startsWith(r2)     // Catch:{ Throwable -> 0x0177 }
            r3 = 1
            if (r2 == 0) goto L_0x003b
            java.lang.String r2 = " = "
            java.lang.String[] r1 = r1.split(r2)     // Catch:{ Throwable -> 0x0177 }
            r1 = r1[r3]     // Catch:{ Throwable -> 0x0177 }
            java.lang.Integer r1 = java.lang.Integer.valueOf(r1)     // Catch:{ Throwable -> 0x0177 }
            int r1 = r1.intValue()     // Catch:{ Throwable -> 0x0177 }
            r0.s = r1     // Catch:{ Throwable -> 0x0177 }
            goto L_0x0019
        L_0x003b:
            java.lang.String r2 = "time_start"
            boolean r2 = r1.startsWith(r2)     // Catch:{ Throwable -> 0x0177 }
            if (r2 == 0) goto L_0x004f
            java.lang.String r2 = " = "
            java.lang.String[] r1 = r1.split(r2)     // Catch:{ Throwable -> 0x0177 }
            r1 = r1[r3]     // Catch:{ Throwable -> 0x0177 }
            r0.y = r1     // Catch:{ Throwable -> 0x0177 }
            goto L_0x0019
        L_0x004f:
            java.lang.String r2 = "time_end"
            boolean r2 = r1.startsWith(r2)     // Catch:{ Throwable -> 0x0177 }
            if (r2 == 0) goto L_0x0063
            java.lang.String r2 = " = "
            java.lang.String[] r1 = r1.split(r2)     // Catch:{ Throwable -> 0x0177 }
            r1 = r1[r3]     // Catch:{ Throwable -> 0x0177 }
            r0.z = r1     // Catch:{ Throwable -> 0x0177 }
            goto L_0x0019
        L_0x0063:
            java.lang.String r2 = "time"
            boolean r2 = r1.startsWith(r2)     // Catch:{ Throwable -> 0x0177 }
            if (r2 == 0) goto L_0x007f
            java.lang.String r2 = " = "
            java.lang.String[] r1 = r1.split(r2)     // Catch:{ Throwable -> 0x0177 }
            r1 = r1[r3]     // Catch:{ Throwable -> 0x0177 }
            java.lang.Long r1 = java.lang.Long.valueOf(r1)     // Catch:{ Throwable -> 0x0177 }
            long r1 = r1.longValue()     // Catch:{ Throwable -> 0x0177 }
            r0.w = r1     // Catch:{ Throwable -> 0x0177 }
            goto L_0x0019
        L_0x007f:
            java.lang.String r2 = "thread_time"
            boolean r2 = r1.startsWith(r2)     // Catch:{ Throwable -> 0x0177 }
            if (r2 == 0) goto L_0x009c
            java.lang.String r2 = " = "
            java.lang.String[] r1 = r1.split(r2)     // Catch:{ Throwable -> 0x0177 }
            r1 = r1[r3]     // Catch:{ Throwable -> 0x0177 }
            java.lang.Long r1 = java.lang.Long.valueOf(r1)     // Catch:{ Throwable -> 0x0177 }
            long r1 = r1.longValue()     // Catch:{ Throwable -> 0x0177 }
            r0.x = r1     // Catch:{ Throwable -> 0x0177 }
            goto L_0x0019
        L_0x009c:
            java.lang.String r2 = "totalMemory"
            boolean r2 = r1.startsWith(r2)     // Catch:{ Throwable -> 0x0177 }
            if (r2 == 0) goto L_0x00b1
            java.lang.String r2 = " = "
            java.lang.String[] r1 = r1.split(r2)     // Catch:{ Throwable -> 0x0177 }
            r1 = r1[r3]     // Catch:{ Throwable -> 0x0177 }
            r0.u = r1     // Catch:{ Throwable -> 0x0177 }
            goto L_0x0019
        L_0x00b1:
            java.lang.String r2 = "freeMemory"
            boolean r2 = r1.startsWith(r2)     // Catch:{ Throwable -> 0x0177 }
            if (r2 == 0) goto L_0x00c5
            java.lang.String r2 = " = "
            java.lang.String[] r1 = r1.split(r2)     // Catch:{ Throwable -> 0x0177 }
            r1 = r1[r3]     // Catch:{ Throwable -> 0x0177 }
            r0.t = r1     // Catch:{ Throwable -> 0x0177 }
            goto L_0x0019
        L_0x00c5:
            java.lang.String r2 = "cpu_busy"
            boolean r2 = r1.startsWith(r2)     // Catch:{ Throwable -> 0x0177 }
            if (r2 == 0) goto L_0x00e1
            java.lang.String r2 = " = "
            java.lang.String[] r1 = r1.split(r2)     // Catch:{ Throwable -> 0x0177 }
            r1 = r1[r3]     // Catch:{ Throwable -> 0x0177 }
            java.lang.Boolean r1 = java.lang.Boolean.valueOf(r1)     // Catch:{ Throwable -> 0x0177 }
            boolean r1 = r1.booleanValue()     // Catch:{ Throwable -> 0x0177 }
            r0.A = r1     // Catch:{ Throwable -> 0x0177 }
            goto L_0x0019
        L_0x00e1:
            java.lang.String r2 = "cpu_rate"
            boolean r2 = r1.startsWith(r2)     // Catch:{ Throwable -> 0x0177 }
            if (r2 == 0) goto L_0x012c
            java.lang.String r2 = " = "
            java.lang.String[] r2 = r1.split(r2)     // Catch:{ Throwable -> 0x0177 }
            int r4 = r2.length     // Catch:{ Throwable -> 0x0177 }
            if (r4 <= r3) goto L_0x0019
            java.lang.StringBuilder r4 = new java.lang.StringBuilder     // Catch:{ Throwable -> 0x0177 }
            r2 = r2[r3]     // Catch:{ Throwable -> 0x0177 }
            r4.<init>(r2)     // Catch:{ Throwable -> 0x0177 }
            java.lang.String r2 = " = "
            java.lang.String[] r1 = r1.split(r2)     // Catch:{ Throwable -> 0x0177 }
            r1 = r1[r3]     // Catch:{ Throwable -> 0x0177 }
            r4.append(r1)     // Catch:{ Throwable -> 0x0177 }
            java.lang.String r1 = "\r\n"
            r4.append(r1)     // Catch:{ Throwable -> 0x0177 }
            java.lang.String r1 = r6.readLine()     // Catch:{ Throwable -> 0x0177 }
        L_0x010d:
            if (r1 == 0) goto L_0x0124
            java.lang.String r2 = ""
            boolean r2 = r1.equals(r2)     // Catch:{ Throwable -> 0x0177 }
            if (r2 != 0) goto L_0x0124
            r4.append(r1)     // Catch:{ Throwable -> 0x0177 }
            java.lang.String r1 = "\r\n"
            r4.append(r1)     // Catch:{ Throwable -> 0x0177 }
            java.lang.String r1 = r6.readLine()     // Catch:{ Throwable -> 0x0177 }
            goto L_0x010d
        L_0x0124:
            java.lang.String r1 = r4.toString()     // Catch:{ Throwable -> 0x0177 }
            r0.B = r1     // Catch:{ Throwable -> 0x0177 }
            goto L_0x0019
        L_0x012c:
            java.lang.String r2 = "stack"
            boolean r2 = r1.startsWith(r2)     // Catch:{ Throwable -> 0x0177 }
            if (r2 == 0) goto L_0x0019
            java.lang.StringBuilder r2 = new java.lang.StringBuilder     // Catch:{ Throwable -> 0x0177 }
            java.lang.String r4 = " = "
            java.lang.String[] r1 = r1.split(r4)     // Catch:{ Throwable -> 0x0177 }
            r1 = r1[r3]     // Catch:{ Throwable -> 0x0177 }
            r2.<init>(r1)     // Catch:{ Throwable -> 0x0177 }
            java.lang.String r1 = r6.readLine()     // Catch:{ Throwable -> 0x0177 }
        L_0x0146:
            if (r1 == 0) goto L_0x0019
            java.lang.String r3 = ""
            boolean r3 = r1.equals(r3)     // Catch:{ Throwable -> 0x0177 }
            if (r3 != 0) goto L_0x0159
            r2.append(r1)     // Catch:{ Throwable -> 0x0177 }
            java.lang.String r1 = "\r\n"
            r2.append(r1)     // Catch:{ Throwable -> 0x0177 }
            goto L_0x016e
        L_0x0159:
            int r1 = r2.length()     // Catch:{ Throwable -> 0x0177 }
            if (r1 <= 0) goto L_0x016e
            java.util.ArrayList r1 = r0.C     // Catch:{ Throwable -> 0x0177 }
            java.lang.String r2 = r2.toString()     // Catch:{ Throwable -> 0x0177 }
            r1.add(r2)     // Catch:{ Throwable -> 0x0177 }
            java.lang.StringBuilder r1 = new java.lang.StringBuilder     // Catch:{ Throwable -> 0x0177 }
            r1.<init>()     // Catch:{ Throwable -> 0x0177 }
            r2 = r1
        L_0x016e:
            java.lang.String r1 = r6.readLine()     // Catch:{ Throwable -> 0x0177 }
            goto L_0x0146
        L_0x0173:
            r6.close()     // Catch:{ Throwable -> 0x0177 }
            goto L_0x0195
        L_0x0177:
            r1 = move-exception
            goto L_0x0180
        L_0x0179:
            r0 = move-exception
            r6 = r1
            goto L_0x019a
        L_0x017c:
            r6 = move-exception
            r5 = r1
            r1 = r6
            r6 = r5
        L_0x0180:
            java.lang.String r2 = "BlockInfoEx"
            java.lang.String r3 = "newInstance: "
            android.util.Log.e(r2, r3, r1)     // Catch:{ all -> 0x0199 }
            if (r6 == 0) goto L_0x0195
            r6.close()     // Catch:{ Exception -> 0x018d }
            goto L_0x0195
        L_0x018d:
            r6 = move-exception
            java.lang.String r1 = "BlockInfoEx"
            java.lang.String r2 = "newInstance: "
            android.util.Log.e(r1, r2, r6)
        L_0x0195:
            r0.b()
            return r0
        L_0x0199:
            r0 = move-exception
        L_0x019a:
            if (r6 == 0) goto L_0x01a8
            r6.close()     // Catch:{ Exception -> 0x01a0 }
            goto L_0x01a8
        L_0x01a0:
            r6 = move-exception
            java.lang.String r1 = "BlockInfoEx"
            java.lang.String r2 = "newInstance: "
            android.util.Log.e(r1, r2, r6)
        L_0x01a8:
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.mi.blockcanary.ui.BlockInfoEx.a(java.io.File):com.mi.blockcanary.ui.BlockInfoEx");
    }
}
