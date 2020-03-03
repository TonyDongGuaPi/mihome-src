package com.xiaomi.smarthome.core.server.internal.bluetooth.security.securitychipauth;

import com.xiaomi.smarthome.core.server.internal.bluetooth.model.BluetoothCache;
import com.xiaomi.smarthome.frame.log.BluetoothMyLogger;
import com.xiaomi.smarthome.library.bluetooth.connect.response.BleReadResponse;
import com.xiaomi.smarthome.library.bluetooth.utils.BluetoothUtils;
import java.util.HashMap;
import java.util.Map;
import kotlin.jvm.internal.ShortCompanionObject;

public class BleSecurityChipEncrypt {

    /* renamed from: a  reason: collision with root package name */
    private static Map<String, EncryptCounter> f14367a = new HashMap();

    /* JADX WARNING: Code restructure failed: missing block: B:19:0x0071, code lost:
        return;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static synchronized void a(java.lang.String r10, byte[] r11, com.xiaomi.smarthome.library.bluetooth.connect.response.BleReadResponse r12) {
        /*
            java.lang.Class<com.xiaomi.smarthome.core.server.internal.bluetooth.security.securitychipauth.BleSecurityChipEncrypt> r0 = com.xiaomi.smarthome.core.server.internal.bluetooth.security.securitychipauth.BleSecurityChipEncrypt.class
            monitor-enter(r0)
            boolean r1 = b(r10)     // Catch:{ all -> 0x0077 }
            r2 = 0
            r3 = -1
            if (r1 != 0) goto L_0x0010
            a((com.xiaomi.smarthome.library.bluetooth.connect.response.BleReadResponse) r12, (int) r3, (byte[]) r2)     // Catch:{ all -> 0x0077 }
            monitor-exit(r0)
            return
        L_0x0010:
            byte[] r1 = com.xiaomi.smarthome.core.server.internal.bluetooth.model.BluetoothCache.w(r10)     // Catch:{ all -> 0x0077 }
            if (r1 == 0) goto L_0x0072
            int r4 = r1.length     // Catch:{ all -> 0x0077 }
            if (r4 != 0) goto L_0x001a
            goto L_0x0072
        L_0x001a:
            r4 = 16
            r5 = 32
            byte[] r4 = java.util.Arrays.copyOfRange(r1, r4, r5)     // Catch:{ all -> 0x0077 }
            r5 = 36
            r6 = 40
            byte[] r1 = java.util.Arrays.copyOfRange(r1, r5, r6)     // Catch:{ all -> 0x0077 }
            r5 = 12
            byte[] r5 = new byte[r5]     // Catch:{ all -> 0x0077 }
            r6 = 4
            r7 = 0
            java.lang.System.arraycopy(r1, r7, r5, r7, r6)     // Catch:{ all -> 0x0077 }
            com.xiaomi.smarthome.core.server.internal.bluetooth.security.securitychipauth.BleSecurityChipEncrypt$EncryptCounter r10 = c(r10)     // Catch:{ all -> 0x0077 }
            short r1 = r10.b()     // Catch:{ all -> 0x0077 }
            byte[] r8 = r10.d()     // Catch:{ all -> 0x0077 }
            r9 = 8
            java.lang.System.arraycopy(r8, r7, r5, r9, r6)     // Catch:{ all -> 0x0077 }
            byte[] r11 = com.xiaomi.smarthome.core.server.internal.bluetooth.security.securitychipauth.utils.SecurityChipUtil.a((byte[]) r4, (byte[]) r5, (byte[]) r11)     // Catch:{ all -> 0x0077 }
            if (r11 != 0) goto L_0x004e
            a((com.xiaomi.smarthome.library.bluetooth.connect.response.BleReadResponse) r12, (int) r3, (byte[]) r2)     // Catch:{ all -> 0x0077 }
            goto L_0x0070
        L_0x004e:
            r10.c()     // Catch:{ all -> 0x0077 }
            r10 = 65535(0xffff, float:9.1834E-41)
            r10 = r10 & r1
            short r10 = (short) r10     // Catch:{ all -> 0x0077 }
            int r1 = r11.length     // Catch:{ all -> 0x0077 }
            int r1 = r1 + 2
            java.nio.ByteBuffer r1 = java.nio.ByteBuffer.allocate(r1)     // Catch:{ all -> 0x0077 }
            java.nio.ByteOrder r2 = java.nio.ByteOrder.LITTLE_ENDIAN     // Catch:{ all -> 0x0077 }
            java.nio.ByteBuffer r1 = r1.order(r2)     // Catch:{ all -> 0x0077 }
            r1.putShort(r10)     // Catch:{ all -> 0x0077 }
            r1.put(r11)     // Catch:{ all -> 0x0077 }
            byte[] r10 = r1.array()     // Catch:{ all -> 0x0077 }
            a((com.xiaomi.smarthome.library.bluetooth.connect.response.BleReadResponse) r12, (int) r7, (byte[]) r10)     // Catch:{ all -> 0x0077 }
        L_0x0070:
            monitor-exit(r0)
            return
        L_0x0072:
            a((com.xiaomi.smarthome.library.bluetooth.connect.response.BleReadResponse) r12, (int) r3, (byte[]) r2)     // Catch:{ all -> 0x0077 }
            monitor-exit(r0)
            return
        L_0x0077:
            r10 = move-exception
            monitor-exit(r0)
            throw r10
        */
        throw new UnsupportedOperationException("Method not decompiled: com.xiaomi.smarthome.core.server.internal.bluetooth.security.securitychipauth.BleSecurityChipEncrypt.a(java.lang.String, byte[], com.xiaomi.smarthome.library.bluetooth.connect.response.BleReadResponse):void");
    }

    /* JADX WARNING: Unknown top exception splitter block from list: {B:21:0x0064=Splitter:B:21:0x0064, B:25:0x0069=Splitter:B:25:0x0069} */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static synchronized void b(java.lang.String r9, byte[] r10, com.xiaomi.smarthome.library.bluetooth.connect.response.BleReadResponse r11) {
        /*
            java.lang.Class<com.xiaomi.smarthome.core.server.internal.bluetooth.security.securitychipauth.BleSecurityChipEncrypt> r0 = com.xiaomi.smarthome.core.server.internal.bluetooth.security.securitychipauth.BleSecurityChipEncrypt.class
            monitor-enter(r0)
            boolean r1 = b(r9)     // Catch:{ all -> 0x006e }
            r2 = 0
            r3 = -1
            if (r1 != 0) goto L_0x0010
            a((com.xiaomi.smarthome.library.bluetooth.connect.response.BleReadResponse) r11, (int) r3, (byte[]) r2)     // Catch:{ all -> 0x006e }
            monitor-exit(r0)
            return
        L_0x0010:
            if (r10 == 0) goto L_0x0069
            int r1 = r10.length     // Catch:{ all -> 0x006e }
            r4 = 2
            if (r1 > r4) goto L_0x0017
            goto L_0x0069
        L_0x0017:
            byte[] r1 = com.xiaomi.smarthome.core.server.internal.bluetooth.model.BluetoothCache.w(r9)     // Catch:{ all -> 0x006e }
            if (r1 == 0) goto L_0x0064
            int r5 = r1.length     // Catch:{ all -> 0x006e }
            if (r5 != 0) goto L_0x0021
            goto L_0x0064
        L_0x0021:
            r2 = 16
            r3 = 0
            byte[] r2 = java.util.Arrays.copyOfRange(r1, r3, r2)     // Catch:{ all -> 0x006e }
            r5 = 32
            r6 = 36
            byte[] r1 = java.util.Arrays.copyOfRange(r1, r5, r6)     // Catch:{ all -> 0x006e }
            r5 = 1
            byte r5 = r10[r5]     // Catch:{ all -> 0x006e }
            r6 = 8
            int r5 = r5 << r6
            byte r7 = r10[r3]     // Catch:{ all -> 0x006e }
            r7 = r7 & 255(0xff, float:3.57E-43)
            r5 = r5 | r7
            short r5 = (short) r5     // Catch:{ all -> 0x006e }
            int r7 = r10.length     // Catch:{ all -> 0x006e }
            int r7 = r7 - r4
            byte[] r7 = new byte[r7]     // Catch:{ all -> 0x006e }
            int r8 = r10.length     // Catch:{ all -> 0x006e }
            int r8 = r8 - r4
            java.lang.System.arraycopy(r10, r4, r7, r3, r8)     // Catch:{ all -> 0x006e }
            com.xiaomi.smarthome.core.server.internal.bluetooth.security.securitychipauth.BleSecurityChipEncrypt$EncryptCounter r9 = c(r9)     // Catch:{ all -> 0x006e }
            r9.a(r5)     // Catch:{ all -> 0x006e }
            r10 = 12
            byte[] r10 = new byte[r10]     // Catch:{ all -> 0x006e }
            r4 = 4
            java.lang.System.arraycopy(r1, r3, r10, r3, r4)     // Catch:{ all -> 0x006e }
            byte[] r9 = r9.e()     // Catch:{ all -> 0x006e }
            java.lang.System.arraycopy(r9, r3, r10, r6, r4)     // Catch:{ all -> 0x006e }
            byte[] r9 = com.xiaomi.smarthome.core.server.internal.bluetooth.security.securitychipauth.utils.SecurityChipUtil.b(r2, r10, r7)     // Catch:{ all -> 0x006e }
            a((com.xiaomi.smarthome.library.bluetooth.connect.response.BleReadResponse) r11, (int) r3, (byte[]) r9)     // Catch:{ all -> 0x006e }
            monitor-exit(r0)
            return
        L_0x0064:
            a((com.xiaomi.smarthome.library.bluetooth.connect.response.BleReadResponse) r11, (int) r3, (byte[]) r2)     // Catch:{ all -> 0x006e }
            monitor-exit(r0)
            return
        L_0x0069:
            a((com.xiaomi.smarthome.library.bluetooth.connect.response.BleReadResponse) r11, (int) r3, (byte[]) r2)     // Catch:{ all -> 0x006e }
            monitor-exit(r0)
            return
        L_0x006e:
            r9 = move-exception
            monitor-exit(r0)
            throw r9
        */
        throw new UnsupportedOperationException("Method not decompiled: com.xiaomi.smarthome.core.server.internal.bluetooth.security.securitychipauth.BleSecurityChipEncrypt.b(java.lang.String, byte[], com.xiaomi.smarthome.library.bluetooth.connect.response.BleReadResponse):void");
    }

    private static void a(BleReadResponse bleReadResponse, int i, byte[] bArr) {
        if (bleReadResponse != null) {
            bleReadResponse.a(i, bArr);
        }
    }

    private static boolean b(String str) {
        if (!BluetoothUtils.c(str)) {
            BluetoothMyLogger.c(str + " is not connected");
            return false;
        }
        byte[] w = BluetoothCache.w(str);
        if (w != null && w.length == 64) {
            return true;
        }
        BluetoothMyLogger.c(str + " sessionKey is null or invalid");
        return false;
    }

    private static EncryptCounter c(String str) {
        EncryptCounter encryptCounter = f14367a.get(str);
        if (encryptCounter != null) {
            return encryptCounter;
        }
        EncryptCounter encryptCounter2 = new EncryptCounter();
        f14367a.put(str, encryptCounter2);
        return encryptCounter2;
    }

    public static void a(String str) {
        EncryptCounter encryptCounter = f14367a.get(str);
        if (encryptCounter != null) {
            encryptCounter.a();
        }
    }

    private static class EncryptCounter {

        /* renamed from: a  reason: collision with root package name */
        private short f14368a;
        private short b;
        private short c;
        private short d;

        public void a() {
            this.f14368a = 0;
            this.b = 0;
            this.c = 0;
            this.d = 0;
        }

        public short b() {
            return this.b;
        }

        public void c() {
            int i = (this.b & ShortCompanionObject.f2832a) >> 15;
            this.b = (short) (this.b + 1);
            if (i != ((32768 & this.b) >> 15)) {
                this.f14368a = (short) (this.f14368a + 1);
            }
        }

        public byte[] d() {
            return new byte[]{(byte) (this.b & 255), (byte) (this.b >> 8), (byte) (this.f14368a & 255), (byte) (this.f14368a >> 8)};
        }

        public void a(short s) {
            int i = (this.d & ShortCompanionObject.f2832a) >> 15;
            int i2 = (32768 & s) >> 15;
            this.d = s;
            if (i != i2) {
                this.c = (short) (this.c + 1);
            }
        }

        public byte[] e() {
            return new byte[]{(byte) (this.d & 255), (byte) (this.d >> 8), (byte) (this.c & 255), (byte) (this.c >> 8)};
        }
    }
}
