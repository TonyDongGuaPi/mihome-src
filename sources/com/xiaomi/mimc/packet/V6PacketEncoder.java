package com.xiaomi.mimc.packet;

import com.xiaomi.mimc.cipher.RC4;
import com.xiaomi.mimc.client.Connection;
import com.xiaomi.mimc.common.MIMCConstant;
import com.xiaomi.msg.logger.MIMCLog;

public class V6PacketEncoder {

    /* renamed from: a  reason: collision with root package name */
    private static final String f11207a = "V6PacketEncoder";

    public static byte[] a(V6Packet v6Packet, Connection connection) {
        MIMCLog.a(f11207a, String.format("V6PacketEncoder, packet:%s, header:%s", new Object[]{v6Packet, v6Packet.b}));
        if (connection == null || v6Packet == null) {
            MIMCLog.c(f11207a, "V6PacketEncoder encode fail");
            return null;
        } else if (v6Packet.b == null || !MIMCConstant.p.equalsIgnoreCase(v6Packet.b.m())) {
            return v6Packet.a(connection.e(), (byte[]) null);
        } else {
            return v6Packet.a(connection.e(), RC4.a(connection.r().W(), v6Packet.b.s()));
        }
    }
}
