package com.xiaomi.mimc.client;

import com.xiaomi.mimc.MIMCUser;
import com.xiaomi.mimc.client.Connection;
import com.xiaomi.mimc.common.MIMCConstant;
import com.xiaomi.mimc.common.MIMCUtils;
import com.xiaomi.mimc.packet.PacketDecoder;
import com.xiaomi.mimc.packet.V6Packet;
import com.xiaomi.mimc.proto.ImsPushService;
import com.xiaomi.msg.logger.MIMCLog;

public class RecvThread extends Thread {

    /* renamed from: a  reason: collision with root package name */
    private static final String f11165a = "RecvThread";
    private Connection b;
    private boolean c = false;

    public RecvThread(Connection connection) {
        this.b = connection;
    }

    public void a(boolean z) {
        this.c = z;
    }

    public void run() {
        int b2;
        if (this.b == null) {
            MIMCLog.c(f11165a, "Connection is null, thread is not started");
            return;
        }
        MIMCLog.b(f11165a, "RecvThread start");
        MIMCUser r = this.b.r();
        while (!this.c) {
            if (this.b.f() == Connection.ConnState.SOCKET_INIT) {
                a(5);
            } else {
                try {
                    byte[] bArr = new byte[8];
                    int b3 = this.b.b(bArr, bArr.length);
                    if (b3 != 8) {
                        MIMCLog.d(f11165a, String.format("Connection is reset, v6-head is not equal. readLen:%d", new Object[]{Integer.valueOf(b3)}));
                        this.b.b();
                    } else {
                        short a2 = PacketDecoder.a(bArr, 0);
                        if (a2 != -15618) {
                            MIMCLog.d(f11165a, String.format("Connection is reset, v6-magic is not equal, MIMCConstant.MAGIC:%s, magic:%s", new Object[]{Integer.toHexString(49918), Integer.toHexString(a2)}));
                            this.b.b();
                        } else {
                            short a3 = PacketDecoder.a(bArr, 2);
                            if (a3 != 5) {
                                MIMCLog.d(f11165a, "Connection is reset, v6-version is not equal");
                                this.b.b();
                            } else {
                                int b4 = PacketDecoder.b(bArr, 4);
                                if (b4 < 0) {
                                    MIMCLog.d(f11165a, String.format("Connection is reset, packetLen:%d < 0", new Object[]{Integer.valueOf(b4)}));
                                    this.b.b();
                                } else {
                                    MIMCLog.b(f11165a, String.format("V6 header is right, magic:%s, version:%s, packetLen:%d", new Object[]{Integer.toHexString(a2 & 65535), Integer.toHexString(a3), Integer.valueOf(b4)}));
                                    byte[] bArr2 = new byte[b4];
                                    MIMCLog.b(f11165a, String.format("packetBins:%d, packetLen:%d", new Object[]{Integer.valueOf(bArr2.length), Integer.valueOf(b4)}));
                                    if (b4 <= 0 || (b2 = this.b.b(bArr2, b4)) == b4) {
                                        byte[] bArr3 = new byte[4];
                                        int b5 = this.b.b(bArr3, bArr3.length);
                                        if (b5 != 4) {
                                            MIMCLog.c(f11165a, String.format("Connection is reset, crcBytesRead:%d != CRC_LEN:%d", new Object[]{Integer.valueOf(b5), 4}));
                                            this.b.b();
                                        } else if (this.b.r().e() != 100) {
                                            MIMCLog.b(f11165a, "connection.setNextResetSockTimestamp(-1)");
                                            this.b.a(-1);
                                            if (b4 == 0) {
                                                MIMCLog.a(f11165a, "This v6 packet is ping or pong");
                                            } else {
                                                V6Packet a4 = PacketDecoder.a(bArr, bArr2, bArr3, this.b.e(), this.b.r().W());
                                                if (a4 == null) {
                                                    MIMCLog.d(f11165a, "Connection is reset, v6-packet decode fail");
                                                    this.b.b();
                                                } else if (MIMCConstant.l.equalsIgnoreCase(a4.b.m())) {
                                                    ImsPushService.XMMsgConnResp a5 = ImsPushService.XMMsgConnResp.a(a4.c);
                                                    this.b.a(a5.b());
                                                    this.b.a(Connection.ConnState.HANDSHAKE_CONNECTED);
                                                    MIMCLog.b(f11165a, String.format("Received CMD_CONN id:%s, chid:%d, uuid:%d, cmd: %s, header:%s, rawData:%s", new Object[]{a4.b.s(), Integer.valueOf(a4.b.b()), Long.valueOf(a4.b.d()), a4.b.m(), a4.b, a5}));
                                                } else if (MIMCConstant.m.equalsIgnoreCase(a4.b.m())) {
                                                    ImsPushService.XMMsgBindResp a6 = ImsPushService.XMMsgBindResp.a(a4.c);
                                                    MIMCLog.b(f11165a, String.format("Received CMD_BIND id:%s, chid:%d, uuid:%d, resource:%s, cmd:%s, header:%s, rawData:%s", new Object[]{a4.b.s(), Integer.valueOf(a4.b.b()), Long.valueOf(a4.b.d()), a4.b.i(), a4.b.m(), a4.b, a6}));
                                                    MIMCConstant.OnlineStatus onlineStatus = a6.b() ? MIMCConstant.OnlineStatus.ONLINE : MIMCConstant.OnlineStatus.OFFLINE;
                                                    r.a(onlineStatus);
                                                    r.t().a(onlineStatus, a6.d(), a6.g(), a6.j());
                                                    a(r, a6);
                                                } else if (MIMCConstant.p.equalsIgnoreCase(a4.b.m())) {
                                                    MIMCLog.b(f11165a, String.format("Received CMD_SECMSG uuid:%d", new Object[]{Long.valueOf(r.j())}));
                                                    r.aa().a(a4);
                                                } else if (MIMCConstant.q.equalsIgnoreCase(a4.b.m())) {
                                                    ImsPushService.XMMsgBindResp a7 = ImsPushService.XMMsgBindResp.a(a4.c);
                                                    MIMCLog.b(f11165a, String.format("Received CMD_KICK id:%s, chid:%d, uuid:%d, cmd:%s, header:%s, rawData:%s", new Object[]{a4.b.s(), Integer.valueOf(a4.b.b()), Long.valueOf(a4.b.d()), a4.b.m(), a4.b, a7}));
                                                    if (!a7.b()) {
                                                        MIMCLog.c(f11165a, String.format("This uuid:%d is kicked result:%b", new Object[]{Long.valueOf(r.j()), Boolean.valueOf(a7.b())}));
                                                        r.a(MIMCConstant.OnlineStatus.OFFLINE);
                                                        r.t().a(MIMCConstant.OnlineStatus.OFFLINE, a7.d(), a7.g(), a7.j());
                                                        a(r, a7);
                                                    }
                                                }
                                            }
                                        }
                                    } else {
                                        MIMCLog.d(f11165a, String.format("Connection is reset, readPacketLen:%d != packetLen:%d", new Object[]{Integer.valueOf(b2), Integer.valueOf(b4)}));
                                        this.b.b();
                                    }
                                }
                            }
                        }
                    }
                } catch (Exception e) {
                    MIMCLog.d(f11165a, "RecvThread exception e", e);
                }
            }
        }
    }

    private void a(long j) {
        try {
            Thread.sleep(j);
        } catch (InterruptedException e) {
            MIMCLog.d(f11165a, "SendThread Thread sleep exception, e:", e);
        }
    }

    private void a(MIMCUser mIMCUser, ImsPushService.XMMsgBindResp xMMsgBindResp) {
        if ("invalid-token".equalsIgnoreCase(xMMsgBindResp.g()) || "token-expired".equalsIgnoreCase(xMMsgBindResp.d())) {
            MIMCUtils.c(mIMCUser);
        }
    }
}
