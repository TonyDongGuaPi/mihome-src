package com.drew.tools;

import com.drew.lang.annotations.NotNull;
import java.io.File;
import java.io.IOException;

public class FileUtil {

    /* renamed from: a  reason: collision with root package name */
    static final /* synthetic */ boolean f5270a = (!FileUtil.class.desiredAssertionStatus());

    /* JADX WARNING: Removed duplicated region for block: B:11:0x0013  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static void a(@com.drew.lang.annotations.NotNull java.io.File r2, @com.drew.lang.annotations.NotNull byte[] r3) throws java.io.IOException {
        /*
            r0 = 0
            java.io.FileOutputStream r1 = new java.io.FileOutputStream     // Catch:{ all -> 0x0010 }
            r1.<init>(r2)     // Catch:{ all -> 0x0010 }
            r1.write(r3)     // Catch:{ all -> 0x000d }
            r1.close()
            return
        L_0x000d:
            r2 = move-exception
            r0 = r1
            goto L_0x0011
        L_0x0010:
            r2 = move-exception
        L_0x0011:
            if (r0 == 0) goto L_0x0016
            r0.close()
        L_0x0016:
            throw r2
        */
        throw new UnsupportedOperationException("Method not decompiled: com.drew.tools.FileUtil.a(java.io.File, byte[]):void");
    }

    /* JADX WARNING: Removed duplicated region for block: B:21:0x0033  */
    @com.drew.lang.annotations.NotNull
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static byte[] a(@com.drew.lang.annotations.NotNull java.io.File r5) throws java.io.IOException {
        /*
            long r0 = r5.length()
            int r0 = (int) r0
            boolean r1 = f5270a
            if (r1 != 0) goto L_0x0012
            if (r0 == 0) goto L_0x000c
            goto L_0x0012
        L_0x000c:
            java.lang.AssertionError r5 = new java.lang.AssertionError
            r5.<init>()
            throw r5
        L_0x0012:
            byte[] r1 = new byte[r0]
            r2 = 0
            r3 = 0
            java.io.FileInputStream r4 = new java.io.FileInputStream     // Catch:{ all -> 0x0030 }
            r4.<init>(r5)     // Catch:{ all -> 0x0030 }
        L_0x001b:
            if (r2 == r0) goto L_0x002c
            int r5 = r0 - r2
            int r5 = r4.read(r1, r2, r5)     // Catch:{ all -> 0x0029 }
            r3 = -1
            if (r5 != r3) goto L_0x0027
            goto L_0x002c
        L_0x0027:
            int r2 = r2 + r5
            goto L_0x001b
        L_0x0029:
            r5 = move-exception
            r3 = r4
            goto L_0x0031
        L_0x002c:
            r4.close()
            return r1
        L_0x0030:
            r5 = move-exception
        L_0x0031:
            if (r3 == 0) goto L_0x0036
            r3.close()
        L_0x0036:
            throw r5
        */
        throw new UnsupportedOperationException("Method not decompiled: com.drew.tools.FileUtil.a(java.io.File):byte[]");
    }

    @NotNull
    public static byte[] a(@NotNull String str) throws IOException {
        return a(new File(str));
    }
}
