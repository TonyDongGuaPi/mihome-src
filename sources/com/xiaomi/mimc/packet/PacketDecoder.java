package com.xiaomi.mimc.packet;

public class PacketDecoder {

    /* renamed from: a  reason: collision with root package name */
    private static final String f11205a = "PacketDecoder";

    /* JADX WARNING: type inference failed for: r2v2, types: [int, short] */
    /* JADX WARNING: Unknown variable types count: 1 */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static com.xiaomi.mimc.packet.V6Packet a(byte[] r10, byte[] r11, byte[] r12, byte[] r13, java.lang.String r14) throws java.lang.Exception {
        /*
            com.xiaomi.mimc.packet.V6Packet r0 = new com.xiaomi.mimc.packet.V6Packet
            r0.<init>()
            r1 = 0
            short r2 = a(r10, r1)
            char r2 = (char) r2
            r3 = 2
            short r10 = a(r10, r3)
            char r10 = (char) r10
            r4 = 0
            r5 = 1
            r6 = 49918(0xc2fe, float:6.995E-41)
            if (r2 != r6) goto L_0x00ce
            r6 = 5
            if (r10 == r6) goto L_0x001d
            goto L_0x00ce
        L_0x001d:
            if (r13 == 0) goto L_0x0023
            byte[] r11 = com.xiaomi.mimc.cipher.RC4.a((byte[]) r13, (byte[]) r11)
        L_0x0023:
            short r10 = a(r11, r1)
            short r2 = a(r11, r3)
            r6 = 4
            int r6 = b(r11, r6)
            if (r10 != r3) goto L_0x00ad
            if (r2 < 0) goto L_0x00ad
            if (r6 >= 0) goto L_0x0037
            goto L_0x00ad
        L_0x0037:
            byte[] r10 = new byte[r2]
            r7 = 8
            java.lang.System.arraycopy(r11, r7, r10, r1, r2)
            byte[] r7 = new byte[r6]
            int r8 = r2 + 8
            java.lang.System.arraycopy(r11, r8, r7, r1, r6)
            r0.a((byte[]) r10)
            java.lang.String r10 = "SECMSG"
            com.xiaomi.mimc.proto.ImsPushService$ClientHeader r11 = r0.b
            java.lang.String r11 = r11.m()
            boolean r10 = r10.equalsIgnoreCase(r11)
            if (r10 == 0) goto L_0x007d
            com.xiaomi.mimc.proto.ImsPushService$ClientHeader r10 = r0.b
            java.lang.String r10 = r10.s()
            byte[] r10 = com.xiaomi.mimc.cipher.RC4.a((java.lang.String) r14, (java.lang.String) r10)
            byte[] r7 = com.xiaomi.mimc.cipher.RC4.a((byte[]) r10, (byte[]) r7)
            java.lang.String r11 = "PacketDecoder"
            java.lang.String r14 = "MessageDecode, Actual, v6Header:%s, v6Payload:%s"
            java.lang.Object[] r8 = new java.lang.Object[r3]
            com.xiaomi.mimc.proto.ImsPushService$ClientHeader r9 = r0.b
            r8[r1] = r9
            java.lang.String r9 = new java.lang.String
            r9.<init>(r7)
            r8[r5] = r9
            java.lang.String r14 = java.lang.String.format(r14, r8)
            com.xiaomi.msg.logger.MIMCLog.b(r11, r14)
            goto L_0x007e
        L_0x007d:
            r10 = r4
        L_0x007e:
            r0.b(r7)
            byte[] r10 = r0.a(r13, r10)
            int r2 = r2 + 16
            int r2 = r2 + r6
            int r10 = com.xiaomi.mimc.common.MIMCUtils.a((byte[]) r10, (int) r1, (int) r2)
            int r11 = b(r12, r1)
            if (r10 == r11) goto L_0x00ac
            java.lang.String r12 = "PacketDecoder"
            java.lang.String r13 = "RecvData, InvalidPacket, CloseChannel!!!, %d != %d"
            java.lang.Object[] r14 = new java.lang.Object[r3]
            java.lang.Integer r10 = java.lang.Integer.valueOf(r10)
            r14[r1] = r10
            java.lang.Integer r10 = java.lang.Integer.valueOf(r11)
            r14[r5] = r10
            java.lang.String r10 = java.lang.String.format(r13, r14)
            com.xiaomi.msg.logger.MIMCLog.c(r12, r10)
            return r4
        L_0x00ac:
            return r0
        L_0x00ad:
            java.lang.String r11 = "PacketDecoder"
            java.lang.String r12 = "InvalidPacketReceived, CloseChannel!!!, paloadType:%s, v6headerLen:%d, v6PayloadLen:%d"
            r13 = 3
            java.lang.Object[] r13 = new java.lang.Object[r13]
            java.lang.Short r10 = java.lang.Short.valueOf(r10)
            r13[r1] = r10
            java.lang.Short r10 = java.lang.Short.valueOf(r2)
            r13[r5] = r10
            java.lang.Integer r10 = java.lang.Integer.valueOf(r6)
            r13[r3] = r10
            java.lang.String r10 = java.lang.String.format(r12, r13)
            com.xiaomi.msg.logger.MIMCLog.c(r11, r10)
            return r4
        L_0x00ce:
            java.lang.String r11 = "PacketDecoder"
            java.lang.String r12 = "Head RecvData, InvalidPacket, CloseChannel!!!,  magic:%d, version:%d"
            java.lang.Object[] r13 = new java.lang.Object[r3]
            short r14 = (short) r2
            java.lang.Short r14 = java.lang.Short.valueOf(r14)
            r13[r1] = r14
            short r10 = (short) r10
            java.lang.Short r10 = java.lang.Short.valueOf(r10)
            r13[r5] = r10
            java.lang.String r10 = java.lang.String.format(r12, r13)
            com.xiaomi.msg.logger.MIMCLog.c(r11, r10)
            return r4
        */
        throw new UnsupportedOperationException("Method not decompiled: com.xiaomi.mimc.packet.PacketDecoder.a(byte[], byte[], byte[], byte[], java.lang.String):com.xiaomi.mimc.packet.V6Packet");
    }

    public static short a(byte[] bArr, int i) {
        return (short) (((bArr[i + 1] & 255) | ((bArr[i] & 255) << 8)) & -1);
    }

    public static int b(byte[] bArr, int i) {
        return ((bArr[i + 3] & 255) | ((bArr[i] & 255) << 24) | ((bArr[i + 1] & 255) << 16) | ((bArr[i + 2] & 255) << 8)) & -1;
    }
}
