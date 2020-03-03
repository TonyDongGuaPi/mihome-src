package com.xiaomi.mimc.processor;

import com.xiaomi.mimc.MIMCUser;
import com.xiaomi.mimc.common.RTSUtils;
import com.xiaomi.mimc.data.P2PCallSession;
import com.xiaomi.mimc.proto.RtsData;
import com.xiaomi.mimc.proto.RtsSignal;
import com.xiaomi.msg.logger.MIMCLog;

public class BurrowProcessor extends Thread {

    /* renamed from: a  reason: collision with root package name */
    private static final String f11208a = "BurrowProcessor";
    private MIMCUser b;
    private long c;

    public BurrowProcessor(MIMCUser mIMCUser, long j) {
        this.b = mIMCUser;
        this.c = j;
    }

    public void run() {
        try {
            MIMCLog.b(f11208a, String.format("BurrowProcessor.run start uuid:%d", new Object[]{Long.valueOf(this.b.j())}));
            int i = 0;
            while (true) {
                if (i >= 10) {
                    break;
                }
                Thread.sleep(500);
                if (a(i) == -1) {
                    break;
                }
                i++;
            }
            MIMCLog.b(f11208a, String.format("BurrowProcessor.run over sendBurrowRequest, uuid:%d", new Object[]{Long.valueOf(this.b.j())}));
        } catch (Exception e) {
            MIMCLog.d(f11208a, "BurrowProcessor.run got exception:", e);
        }
    }

    public int a(int i) {
        P2PCallSession p2PCallSession = (P2PCallSession) this.b.x().get(Long.valueOf(this.c));
        if (p2PCallSession == null) {
            MIMCLog.c(f11208a, String.format("The callId is not in current calls, callId:%d", new Object[]{Long.valueOf(this.c)}));
            return -1;
        } else if (p2PCallSession.r() != RtsSignal.CallType.SINGLE_CALL) {
            MIMCLog.c(f11208a, "The current call is not Signal.");
            return -1;
        } else {
            long j = (long) i;
            RtsData.BurrowPacket.Builder a2 = RTSUtils.a(this.b.j(), this.b.n(), this.c, RtsData.BURROW_TYPE.INTRANET_BURROW_REQUEST, j);
            long j2 = j;
            RtsData.BurrowPacket.Builder a3 = RTSUtils.a(this.b.j(), this.b.n(), this.c, RtsData.BURROW_TYPE.INTERNET_BURROW_REQUEST, j2);
            RtsSignal.UserInfo a4 = p2PCallSession.a();
            if (!this.b.C().a(a4.m(), a4.p(), ((RtsData.BurrowPacket) a2.Z()).K(), 0)) {
                MIMCLog.c(f11208a, String.format("SEND INTRANET BURROW REQUEST FAIL uuid:%d, intranetBurrowPacket:%s, IntranetIp:%s", new Object[]{Long.valueOf(this.b.j()), a2.Z(), a4.m()}));
            } else {
                MIMCLog.b(f11208a, String.format("SEND INTRANET BURROW REQUEST SUCCESS, uuid:%d", new Object[]{Long.valueOf(this.b.j())}));
            }
            if (!this.b.C().a(a4.r(), a4.u(), ((RtsData.BurrowPacket) a3.Z()).K(), 0)) {
                MIMCLog.c(f11208a, String.format("SEND INTERNET BURROW REQUEST FAIL uuid:%d, internetBurrowPacket:%s, InternetIp:%s", new Object[]{Long.valueOf(this.b.j()), a3.Z(), a4.r()}));
            } else {
                MIMCLog.b(f11208a, String.format("SEND INTERNET BURROW REQUEST SUCCESS, uuid:%d", new Object[]{Long.valueOf(this.b.j())}));
            }
            return 0;
        }
    }
}
