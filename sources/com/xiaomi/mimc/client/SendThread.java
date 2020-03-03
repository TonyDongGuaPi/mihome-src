package com.xiaomi.mimc.client;

import com.xiaomi.mimc.MIMCUser;
import com.xiaomi.mimc.client.Connection;
import com.xiaomi.mimc.common.Backoff;
import com.xiaomi.mimc.common.MIMCConstant;
import com.xiaomi.mimc.common.MIMCUtils;
import com.xiaomi.mimc.data.MIMCObject;
import com.xiaomi.mimc.packet.V6Packet;
import com.xiaomi.mimc.packet.V6PacketEncoder;
import com.xiaomi.msg.logger.MIMCLog;
import java.util.concurrent.TimeUnit;

public class SendThread extends Thread {

    /* renamed from: a  reason: collision with root package name */
    private static final String f11166a = "SendThread";
    private Connection b;
    private boolean c;
    private Backoff d = Backoff.a().a(TimeUnit.SECONDS, 1).b(TimeUnit.MINUTES, 1).a(2).a();

    public SendThread(Connection connection) {
        this.b = connection;
        this.c = false;
    }

    public void a(boolean z) {
        this.c = z;
    }

    public void run() {
        if (this.b == null) {
            MIMCLog.c(f11166a, "Connection is null, Send thread is not started");
            return;
        }
        MIMCLog.b(f11166a, "SendThread start");
        MIMCUser r = this.b.r();
        while (!this.c) {
            String str = MIMCConstant.Y;
            V6Packet v6Packet = null;
            try {
                if (this.b.f() == Connection.ConnState.SOCKET_INIT) {
                    if (MIMCUtils.c(r.m())) {
                        MIMCLog.b(f11166a, "Fetch token...");
                        MIMCUtils.a(r);
                        if (MIMCUtils.c(r.m())) {
                            this.d.c();
                        } else {
                            this.d.b();
                        }
                    }
                    if (System.currentTimeMillis() - r.Y() <= 5000) {
                        a(5);
                    } else {
                        r.l(System.currentTimeMillis());
                        if (!this.b.c()) {
                            MIMCLog.d(f11166a, String.format("Connection fail, uuid:%d, host:%s, port:%d", new Object[]{Long.valueOf(r.j()), this.b.o(), Integer.valueOf(this.b.p())}));
                        } else {
                            this.b.a(Connection.ConnState.SOCKET_CONNECTED);
                            r.l(0);
                            v6Packet = MIMCUtils.b(this.b);
                        }
                    }
                } else if (this.b.f() == Connection.ConnState.SOCKET_CONNECTED) {
                    a(5);
                } else if (this.b.f() == Connection.ConnState.HANDSHAKE_CONNECTED) {
                    if (r.V() == MIMCConstant.OnlineStatus.ONLINE) {
                        MIMCObject poll = this.b.q().poll();
                        if (poll != null) {
                            str = poll.a();
                            v6Packet = (V6Packet) poll.b();
                            MIMCLog.a(f11166a, String.format("Send v6-packet msgType:%s", new Object[]{str}));
                        } else if (System.currentTimeMillis() - r.Z() > ((long) r.ad())) {
                            v6Packet = MIMCUtils.c(this.b);
                        } else {
                            a(100);
                        }
                    } else {
                        if (MIMCUtils.c(r.m())) {
                            MIMCLog.b(f11166a, "Fetch token...");
                            MIMCUtils.a(r);
                            if (MIMCUtils.c(r.m())) {
                                this.d.c();
                            } else {
                                this.d.b();
                            }
                        }
                        if (!r.d()) {
                            a(500);
                        } else if (System.currentTimeMillis() - r.X() <= 5000) {
                            a(100);
                        } else {
                            v6Packet = MIMCUtils.a(this.b);
                            r.k(System.currentTimeMillis());
                        }
                    }
                }
                if (v6Packet == null) {
                    MIMCLog.d(f11166a, "v6Packet is null");
                    a(100);
                } else {
                    if (str == MIMCConstant.Y) {
                        this.b.t();
                        r.m(System.currentTimeMillis());
                        MIMCLog.a(f11166a, "TrySetNextResetSockTs and setLastPingTimestamp, for sending date type: MIMC_C2S_DOUBLE_DIRECTION");
                    }
                    byte[] a2 = V6PacketEncoder.a(v6Packet, this.b);
                    if (a2 == null) {
                        MIMCLog.d(f11166a, "data is null");
                    } else if (r.e() >= 100 || this.b.a(a2, a2.length) == a2.length) {
                        MIMCLog.b(f11166a, String.format("Send data success, dataLen:%d", new Object[]{Integer.valueOf(a2.length)}));
                    } else {
                        this.b.b();
                        MIMCLog.d(f11166a, "Connection is reset, write fail");
                    }
                }
            } catch (Exception e) {
                MIMCLog.d(f11166a, "Exception:", e);
            }
        }
    }

    private void a(long j) {
        try {
            Thread.sleep(j);
        } catch (InterruptedException e) {
            MIMCLog.d(f11166a, "sleep exception:", e);
        }
    }
}
