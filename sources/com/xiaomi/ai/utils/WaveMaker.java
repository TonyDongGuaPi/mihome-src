package com.xiaomi.ai.utils;

import cn.com.fmsh.tsm.business.constants.Constants;
import java.io.InputStream;
import java.io.OutputStream;

public class WaveMaker {
    public static void a(long j, int i, long j2, InputStream inputStream, OutputStream outputStream) {
        int i2 = i;
        a(outputStream, j2, j, i2, ((16 * j) * ((long) i2)) / 8);
        byte[] bArr = new byte[4096];
        InputStream inputStream2 = inputStream;
        while (true) {
            int read = inputStream.read(bArr);
            if (read > 0) {
                outputStream.write(bArr, 0, read);
            } else {
                return;
            }
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:12:0x0018  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static void a(long r9, int r11, long r12, java.io.InputStream r14, java.lang.String r15) {
        /*
            r0 = 0
            java.io.FileOutputStream r8 = new java.io.FileOutputStream     // Catch:{ all -> 0x0015 }
            r8.<init>(r15)     // Catch:{ all -> 0x0015 }
            r1 = r9
            r3 = r11
            r4 = r12
            r6 = r14
            r7 = r8
            a((long) r1, (int) r3, (long) r4, (java.io.InputStream) r6, (java.io.OutputStream) r7)     // Catch:{ all -> 0x0012 }
            r8.close()
            return
        L_0x0012:
            r9 = move-exception
            r0 = r8
            goto L_0x0016
        L_0x0015:
            r9 = move-exception
        L_0x0016:
            if (r0 == 0) goto L_0x001b
            r0.close()
        L_0x001b:
            throw r9
        */
        throw new UnsupportedOperationException("Method not decompiled: com.xiaomi.ai.utils.WaveMaker.a(long, int, long, java.io.InputStream, java.lang.String):void");
    }

    /* JADX WARNING: Removed duplicated region for block: B:11:0x001f  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static void a(long r9, int r11, java.lang.String r12, java.lang.String r13) {
        /*
            r0 = 0
            java.io.FileInputStream r8 = new java.io.FileInputStream     // Catch:{ all -> 0x001b }
            r8.<init>(r12)     // Catch:{ all -> 0x001b }
            java.nio.channels.FileChannel r12 = r8.getChannel()     // Catch:{ all -> 0x0019 }
            long r4 = r12.size()     // Catch:{ all -> 0x0019 }
            r1 = r9
            r3 = r11
            r6 = r8
            r7 = r13
            a((long) r1, (int) r3, (long) r4, (java.io.InputStream) r6, (java.lang.String) r7)     // Catch:{ all -> 0x0019 }
            r8.close()
            return
        L_0x0019:
            r9 = move-exception
            goto L_0x001d
        L_0x001b:
            r9 = move-exception
            r8 = r0
        L_0x001d:
            if (r8 == 0) goto L_0x0022
            r8.close()
        L_0x0022:
            throw r9
        */
        throw new UnsupportedOperationException("Method not decompiled: com.xiaomi.ai.utils.WaveMaker.a(long, int, java.lang.String, java.lang.String):void");
    }

    /* JADX WARNING: Removed duplicated region for block: B:12:0x006f  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static void a(java.io.File r15) {
        /*
            long r0 = r15.length()
            r2 = 44
            long r0 = r0 - r2
            r2 = 36
            long r2 = r2 + r0
            r4 = 0
            java.io.RandomAccessFile r5 = new java.io.RandomAccessFile     // Catch:{ all -> 0x006b }
            java.lang.String r6 = "rw"
            r5.<init>(r15, r6)     // Catch:{ all -> 0x006b }
            r6 = 4
            r5.seek(r6)     // Catch:{ all -> 0x0069 }
            r15 = 4
            byte[] r15 = new byte[r15]     // Catch:{ all -> 0x0069 }
            r6 = 255(0xff, double:1.26E-321)
            long r8 = r2 & r6
            int r4 = (int) r8     // Catch:{ all -> 0x0069 }
            byte r4 = (byte) r4     // Catch:{ all -> 0x0069 }
            r8 = 0
            r15[r8] = r4     // Catch:{ all -> 0x0069 }
            r4 = 8
            long r9 = r2 >> r4
            long r9 = r9 & r6
            int r9 = (int) r9     // Catch:{ all -> 0x0069 }
            byte r9 = (byte) r9     // Catch:{ all -> 0x0069 }
            r10 = 1
            r15[r10] = r9     // Catch:{ all -> 0x0069 }
            r9 = 16
            long r11 = r2 >> r9
            long r11 = r11 & r6
            int r11 = (int) r11     // Catch:{ all -> 0x0069 }
            byte r11 = (byte) r11     // Catch:{ all -> 0x0069 }
            r12 = 2
            r15[r12] = r11     // Catch:{ all -> 0x0069 }
            r11 = 24
            long r2 = r2 >> r11
            long r2 = r2 & r6
            int r2 = (int) r2     // Catch:{ all -> 0x0069 }
            byte r2 = (byte) r2     // Catch:{ all -> 0x0069 }
            r3 = 3
            r15[r3] = r2     // Catch:{ all -> 0x0069 }
            r5.write(r15)     // Catch:{ all -> 0x0069 }
            long r13 = r0 & r6
            int r2 = (int) r13     // Catch:{ all -> 0x0069 }
            byte r2 = (byte) r2     // Catch:{ all -> 0x0069 }
            r15[r8] = r2     // Catch:{ all -> 0x0069 }
            long r13 = r0 >> r4
            long r13 = r13 & r6
            int r2 = (int) r13     // Catch:{ all -> 0x0069 }
            byte r2 = (byte) r2     // Catch:{ all -> 0x0069 }
            r15[r10] = r2     // Catch:{ all -> 0x0069 }
            long r8 = r0 >> r9
            long r8 = r8 & r6
            int r2 = (int) r8     // Catch:{ all -> 0x0069 }
            byte r2 = (byte) r2     // Catch:{ all -> 0x0069 }
            r15[r12] = r2     // Catch:{ all -> 0x0069 }
            long r0 = r0 >> r11
            long r0 = r0 & r6
            int r0 = (int) r0     // Catch:{ all -> 0x0069 }
            byte r0 = (byte) r0     // Catch:{ all -> 0x0069 }
            r15[r3] = r0     // Catch:{ all -> 0x0069 }
            r0 = 40
            r5.seek(r0)     // Catch:{ all -> 0x0069 }
            r5.write(r15)     // Catch:{ all -> 0x0069 }
            r5.close()
            return
        L_0x0069:
            r15 = move-exception
            goto L_0x006d
        L_0x006b:
            r15 = move-exception
            r5 = r4
        L_0x006d:
            if (r5 == 0) goto L_0x0072
            r5.close()
        L_0x0072:
            throw r15
        */
        throw new UnsupportedOperationException("Method not decompiled: com.xiaomi.ai.utils.WaveMaker.a(java.io.File):void");
    }

    private static void a(OutputStream outputStream, long j, long j2, int i, long j3) {
        long j4 = j + 36;
        outputStream.write(new byte[]{Constants.TagName.TERMINAL_BACK_QUESTION_FLAG, Constants.TagName.ORDER_BRIEF_INFO, Constants.TagName.TERMINAL_BASEBAND_VERSION, Constants.TagName.TERMINAL_BASEBAND_VERSION, (byte) ((int) (j4 & 255)), (byte) ((int) ((j4 >> 8) & 255)), (byte) ((int) ((j4 >> 16) & 255)), (byte) ((int) ((j4 >> 24) & 255)), 87, Constants.TagName.TERMINAL_BACK_CONTENT, Constants.TagName.QUERY_DATA_SORT_TYPE, Constants.TagName.TERMINAL_MODEL_NUMBER, 102, Constants.TagName.PUBLISH_END_TIME, Constants.TagName.ELECTRONIC_USE_TYPE, 32, 16, 0, 0, 0, 1, 0, (byte) i, 0, (byte) ((int) (j2 & 255)), (byte) ((int) ((j2 >> 8) & 255)), (byte) ((int) ((j2 >> 16) & 255)), (byte) ((int) ((j2 >> 24) & 255)), (byte) ((int) (j3 & 255)), (byte) ((int) ((j3 >> 8) & 255)), (byte) ((int) ((j3 >> 16) & 255)), (byte) ((int) ((j3 >> 24) & 255)), 2, 0, 16, 0, Constants.TagName.PAY_ORDER_LIST, 97, Constants.TagName.ELECTRONIC_USE_TYPE, 97, (byte) ((int) (j & 255)), (byte) ((int) ((j >> 8) & 255)), (byte) ((int) ((j >> 16) & 255)), (byte) ((int) ((j >> 24) & 255))}, 0, 44);
    }
}
