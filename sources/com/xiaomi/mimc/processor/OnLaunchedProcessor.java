package com.xiaomi.mimc.processor;

import com.xiaomi.mimc.MIMCUser;
import com.xiaomi.mimc.common.RTSUtils;
import com.xiaomi.mimc.data.LaunchedResponse;
import com.xiaomi.mimc.data.P2PCallSession;
import com.xiaomi.mimc.proto.RtsSignal;
import com.xiaomi.msg.logger.MIMCLog;

public class OnLaunchedProcessor extends Thread {

    /* renamed from: a  reason: collision with root package name */
    private static final String f11209a = "OnLaunchedProcessor";
    private MIMCUser b;
    private long c;

    public OnLaunchedProcessor(MIMCUser mIMCUser, long j) {
        this.b = mIMCUser;
        this.c = j;
    }

    public void run() {
        MIMCLog.b(f11209a, String.format("OnLaunchedProcessor run callId:%d", new Object[]{Long.valueOf(this.c)}));
        P2PCallSession p2PCallSession = (P2PCallSession) this.b.x().get(Long.valueOf(this.c));
        if (p2PCallSession == null) {
            MIMCLog.d(f11209a, String.format("This cahtSession is null in OnLaunchedProcessor.run", new Object[0]));
            return;
        }
        RtsSignal.UserInfo a2 = p2PCallSession.a();
        LaunchedResponse a3 = this.b.u().a(a2.i(), a2.d(), this.c, p2PCallSession.l());
        if (!a3.a()) {
            RTSUtils.a(this.b, RtsSignal.RTSResult.PEER_REFUSE, a3.b(), this.c, -1);
            MIMCLog.a(f11209a, String.format("SEND_PACKET, SEND_INVITE_RESPONSE %s", new Object[]{RtsSignal.RTSResult.PEER_REFUSE}));
            this.b.x().remove(Long.valueOf(this.c));
            RTSUtils.c(this.b);
            MIMCLog.b(f11209a, String.format("LaunchedResponse peer_refuse mimcUser.getRtsCalls().remove callId:%d", new Object[]{Long.valueOf(this.c)}));
            return;
        }
        if (this.b.x().get(Long.valueOf(this.c)) != null) {
            if (RTSUtils.a(this.b, RtsSignal.RTSResult.SUCC, a3.b(), this.c, this.b.G())) {
                MIMCLog.b(f11209a, String.format("MIMC connId:%d callId:%d SENT_INVITE_RESPONSE_TO_CALL_CENTER_SUCCESS SEND_PACKET, SEND_INVITE_RESPONSE %s", new Object[]{Long.valueOf(this.b.G()), Long.valueOf(this.c), RtsSignal.RTSResult.SUCC}));
            } else {
                MIMCLog.b(f11209a, String.format("MIMC connId:%d callId:%d SENT_INVITE_RESPONSE_TO_CALL_CENTER_FAIL", new Object[]{Long.valueOf(this.b.G()), Long.valueOf(this.c)}));
            }
            p2PCallSession.a(P2PCallSession.CallState.RUNNING).d(System.currentTimeMillis());
            MIMCLog.b(f11209a, String.format("uuid:%d, currentCalls.put callId:%d, callSession.state:%s", new Object[]{Long.valueOf(this.b.j()), Long.valueOf(this.c), P2PCallSession.CallState.RUNNING}));
        }
        BurrowProcessor burrowProcessor = new BurrowProcessor(this.b, this.c);
        burrowProcessor.setDaemon(true);
        burrowProcessor.start();
    }
}
