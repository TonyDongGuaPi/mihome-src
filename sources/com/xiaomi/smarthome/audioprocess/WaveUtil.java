package com.xiaomi.smarthome.audioprocess;

import cn.com.fmsh.tsm.business.constants.Constants;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;

public class WaveUtil {
    /* JADX WARNING: Removed duplicated region for block: B:20:0x0097  */
    /* JADX WARNING: Removed duplicated region for block: B:26:0x00a1  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static void a(java.io.File r4, int r5, java.io.File r6) throws java.io.IOException {
        /*
            long r0 = r4.length()
            int r0 = (int) r0
            byte[] r0 = new byte[r0]
            r1 = 0
            java.io.DataInputStream r2 = new java.io.DataInputStream     // Catch:{ all -> 0x009e }
            java.io.FileInputStream r3 = new java.io.FileInputStream     // Catch:{ all -> 0x009e }
            r3.<init>(r4)     // Catch:{ all -> 0x009e }
            r2.<init>(r3)     // Catch:{ all -> 0x009e }
            r2.read(r0)     // Catch:{ all -> 0x009b }
            r2.close()
            java.io.DataOutputStream r2 = new java.io.DataOutputStream     // Catch:{ all -> 0x0093 }
            java.io.FileOutputStream r3 = new java.io.FileOutputStream     // Catch:{ all -> 0x0093 }
            r3.<init>(r6)     // Catch:{ all -> 0x0093 }
            r2.<init>(r3)     // Catch:{ all -> 0x0093 }
            java.lang.String r6 = "RIFF"
            a((java.io.DataOutputStream) r2, (java.lang.String) r6)     // Catch:{ all -> 0x0091 }
            int r6 = r0.length     // Catch:{ all -> 0x0091 }
            int r6 = r6 + 36
            a((java.io.DataOutputStream) r2, (int) r6)     // Catch:{ all -> 0x0091 }
            java.lang.String r6 = "WAVE"
            a((java.io.DataOutputStream) r2, (java.lang.String) r6)     // Catch:{ all -> 0x0091 }
            java.lang.String r6 = "fmt "
            a((java.io.DataOutputStream) r2, (java.lang.String) r6)     // Catch:{ all -> 0x0091 }
            r6 = 16
            a((java.io.DataOutputStream) r2, (int) r6)     // Catch:{ all -> 0x0091 }
            r1 = 1
            a((java.io.DataOutputStream) r2, (short) r1)     // Catch:{ all -> 0x0091 }
            a((java.io.DataOutputStream) r2, (short) r1)     // Catch:{ all -> 0x0091 }
            r1 = 44100(0xac44, float:6.1797E-41)
            a((java.io.DataOutputStream) r2, (int) r1)     // Catch:{ all -> 0x0091 }
            r1 = 2
            int r5 = r5 * 2
            a((java.io.DataOutputStream) r2, (int) r5)     // Catch:{ all -> 0x0091 }
            a((java.io.DataOutputStream) r2, (short) r1)     // Catch:{ all -> 0x0091 }
            a((java.io.DataOutputStream) r2, (short) r6)     // Catch:{ all -> 0x0091 }
            java.lang.String r5 = "data"
            a((java.io.DataOutputStream) r2, (java.lang.String) r5)     // Catch:{ all -> 0x0091 }
            int r5 = r0.length     // Catch:{ all -> 0x0091 }
            a((java.io.DataOutputStream) r2, (int) r5)     // Catch:{ all -> 0x0091 }
            int r5 = r0.length     // Catch:{ all -> 0x0091 }
            int r5 = r5 / r1
            short[] r5 = new short[r5]     // Catch:{ all -> 0x0091 }
            java.nio.ByteBuffer r6 = java.nio.ByteBuffer.wrap(r0)     // Catch:{ all -> 0x0091 }
            java.nio.ByteOrder r0 = java.nio.ByteOrder.LITTLE_ENDIAN     // Catch:{ all -> 0x0091 }
            java.nio.ByteBuffer r6 = r6.order(r0)     // Catch:{ all -> 0x0091 }
            java.nio.ShortBuffer r6 = r6.asShortBuffer()     // Catch:{ all -> 0x0091 }
            r6.get(r5)     // Catch:{ all -> 0x0091 }
            int r6 = r5.length     // Catch:{ all -> 0x0091 }
            int r6 = r6 * 2
            java.nio.ByteBuffer r6 = java.nio.ByteBuffer.allocate(r6)     // Catch:{ all -> 0x0091 }
            int r0 = r5.length     // Catch:{ all -> 0x0091 }
            r1 = 0
        L_0x007c:
            if (r1 >= r0) goto L_0x0086
            short r3 = r5[r1]     // Catch:{ all -> 0x0091 }
            r6.putShort(r3)     // Catch:{ all -> 0x0091 }
            int r1 = r1 + 1
            goto L_0x007c
        L_0x0086:
            byte[] r4 = a((java.io.File) r4)     // Catch:{ all -> 0x0091 }
            r2.write(r4)     // Catch:{ all -> 0x0091 }
            r2.close()
            return
        L_0x0091:
            r4 = move-exception
            goto L_0x0095
        L_0x0093:
            r4 = move-exception
            r2 = r1
        L_0x0095:
            if (r2 == 0) goto L_0x009a
            r2.close()
        L_0x009a:
            throw r4
        L_0x009b:
            r4 = move-exception
            r1 = r2
            goto L_0x009f
        L_0x009e:
            r4 = move-exception
        L_0x009f:
            if (r1 == 0) goto L_0x00a4
            r1.close()
        L_0x00a4:
            throw r4
        */
        throw new UnsupportedOperationException("Method not decompiled: com.xiaomi.smarthome.audioprocess.WaveUtil.a(java.io.File, int, java.io.File):void");
    }

    public static void a(DataOutputStream dataOutputStream, int i) throws IOException {
        dataOutputStream.write(i >> 0);
        dataOutputStream.write(i >> 8);
        dataOutputStream.write(i >> 16);
        dataOutputStream.write(i >> 24);
    }

    public static void a(DataOutputStream dataOutputStream, short s) throws IOException {
        dataOutputStream.write(s >> 0);
        dataOutputStream.write(s >> 8);
    }

    public static void a(DataOutputStream dataOutputStream, String str) throws IOException {
        for (int i = 0; i < str.length(); i++) {
            dataOutputStream.write(str.charAt(i));
        }
    }

    public static byte[] a(File file) throws IOException {
        int length = (int) file.length();
        byte[] bArr = new byte[length];
        byte[] bArr2 = new byte[length];
        FileInputStream fileInputStream = new FileInputStream(file);
        try {
            int read = fileInputStream.read(bArr, 0, length);
            if (read < length) {
                int i = length - read;
                while (i > 0) {
                    int read2 = fileInputStream.read(bArr2, 0, i);
                    System.arraycopy(bArr2, 0, bArr, length - i, read2);
                    i -= read2;
                }
            }
            fileInputStream.close();
            return bArr;
        } catch (IOException e) {
            throw e;
        } catch (Throwable th) {
            fileInputStream.close();
            throw th;
        }
    }

    public static void a(OutputStream outputStream, short[] sArr, int i, int i2, int i3) throws IOException {
        OutputStream outputStream2 = outputStream;
        int i4 = i;
        int i5 = i2;
        int i6 = i3;
        byte[] a2 = a(sArr);
        long length = (long) (a2.length + 36);
        long j = ((long) ((i4 * i5) * i6)) / 8;
        outputStream2.write(new byte[]{Constants.TagName.TERMINAL_BACK_QUESTION_FLAG, Constants.TagName.ORDER_BRIEF_INFO, Constants.TagName.TERMINAL_BASEBAND_VERSION, Constants.TagName.TERMINAL_BASEBAND_VERSION, (byte) ((int) (length & 255)), (byte) ((int) ((length >> 8) & 255)), (byte) ((int) ((length >> 16) & 255)), (byte) ((int) ((length >> 24) & 255)), 87, Constants.TagName.TERMINAL_BACK_CONTENT, Constants.TagName.QUERY_DATA_SORT_TYPE, Constants.TagName.TERMINAL_MODEL_NUMBER, 102, Constants.TagName.PUBLISH_END_TIME, Constants.TagName.ELECTRONIC_USE_TYPE, 32, (byte) i6, 0, 0, 0, 1, 0, (byte) i5, 0, (byte) (i4 & 255), (byte) ((i4 >> 8) & 255), (byte) ((i4 >> 16) & 255), (byte) ((i4 >> 24) & 255), (byte) ((int) (j & 255)), (byte) ((int) ((j >> 8) & 255)), (byte) ((int) ((j >> 16) & 255)), (byte) ((int) ((j >> 24) & 255)), (byte) ((i5 * i6) / 8), 0, 16, 0, Constants.TagName.PAY_ORDER_LIST, 97, Constants.TagName.ELECTRONIC_USE_TYPE, 97, (byte) (a2.length & 255), (byte) ((a2.length >> 8) & 255), (byte) ((a2.length >> 16) & 255), (byte) ((a2.length >> 24) & 255)}, 0, 44);
        outputStream2.write(a2);
        outputStream.close();
    }

    public static byte[] a(short[] sArr) {
        byte[] bArr = new byte[(sArr.length * 2)];
        int i = 0;
        for (short s : sArr) {
            double d = (double) s;
            Double.isNaN(d);
            short s2 = (short) ((int) (d * 32767.0d));
            int i2 = i + 1;
            bArr[i] = (byte) (s2 & 255);
            i = i2 + 1;
            bArr[i2] = (byte) ((s2 & 65280) >>> 8);
        }
        return bArr;
    }
}
