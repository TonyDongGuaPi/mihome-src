package com.xiaomi.mimc.client;

import com.xiaomi.mimc.MIMCGroupMessage;
import com.xiaomi.mimc.MIMCMessage;
import com.xiaomi.mimc.MIMCUser;
import com.xiaomi.mimc.common.MIMCConstant;
import com.xiaomi.mimc.common.RTSUtils;
import com.xiaomi.mimc.data.ChannelSession;
import com.xiaomi.mimc.data.ChannelUser;
import com.xiaomi.mimc.data.P2PCallSession;
import com.xiaomi.mimc.data.TempChannelSession;
import com.xiaomi.mimc.data.TimeoutPacket;
import com.xiaomi.mimc.proto.Mimc;
import com.xiaomi.mimc.proto.RtsData;
import com.xiaomi.mimc.proto.RtsSignal;
import com.xiaomi.mimc.protobuf.ByteString;
import com.xiaomi.msg.data.XMDPacket;
import com.xiaomi.msg.logger.MIMCLog;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentMap;

public class TriggerThread extends Thread {

    /* renamed from: a  reason: collision with root package name */
    private static final String f11167a = "TriggerThread";
    private MIMCUser b;
    private boolean c = false;
    private long d;
    private long e;
    private long f;

    public TriggerThread(MIMCUser mIMCUser) {
        this.b = mIMCUser;
    }

    public void a(boolean z) {
        this.c = z;
    }

    public void run() {
        MIMCLog.b(f11167a, "ThreadTrigger started");
        while (!this.c) {
            try {
                long currentTimeMillis = System.currentTimeMillis();
                Connection ab = this.b.ab();
                if (ab.s() > 0 && currentTimeMillis - ab.s() > 0) {
                    MIMCLog.c(f11167a, String.format("ThreadTrigger connection.reset currentTime:%d, connection.NextResetSockTimestamp:%d", new Object[]{Long.valueOf(currentTimeMillis), Long.valueOf(ab.s())}));
                    ab.b();
                }
                Thread.sleep(1000);
                a();
                c();
                b();
                f();
                g();
                d();
                e();
            } catch (Exception e2) {
                MIMCLog.d(f11167a, "Thread is exception in timeout thread, exception:", e2);
            }
        }
    }

    public void a() throws Exception {
        ArrayList<String> arrayList = new ArrayList<>();
        for (Map.Entry entry : this.b.w().entrySet()) {
            Mimc.MIMCPacket b2 = ((TimeoutPacket) entry.getValue()).b();
            if (System.currentTimeMillis() - ((TimeoutPacket) entry.getValue()).a() >= 10000) {
                if (b2.j() == Mimc.MIMC_MSG_TYPE.P2P_MESSAGE) {
                    Mimc.MIMCP2PMessage a2 = Mimc.MIMCP2PMessage.a(b2.m());
                    MIMCMessage mIMCMessage = new MIMCMessage(b2.b(), b2.h(), a2.b().d(), a2.b().i(), a2.d().d(), a2.d().i(), a2.f().toByteArray(), b2.o(), a2.j());
                    if (this.b.r() == null) {
                        MIMCLog.d(f11167a, "MessageHandler is null, please registerMessageHandler first!");
                    } else {
                        this.b.r().a(mIMCMessage);
                        MIMCLog.d(f11167a, String.format("handleSendMessageTimeout packetId:%s, p2pMessage:%s", new Object[]{mIMCMessage.a(), mIMCMessage}));
                    }
                } else if (b2.j() == Mimc.MIMC_MSG_TYPE.P2T_MESSAGE) {
                    Mimc.MIMCP2TMessage a3 = Mimc.MIMCP2TMessage.a(b2.m());
                    MIMCGroupMessage mIMCGroupMessage = new MIMCGroupMessage(b2.b(), b2.h(), a3.b().d(), a3.b().i(), a3.d().d(), a3.f().toByteArray(), b2.o(), a3.j());
                    if (this.b.r() == null) {
                        MIMCLog.d(f11167a, "MessageHandler is null, please registerMessageHandler first!");
                    } else {
                        this.b.r().a(mIMCGroupMessage);
                        MIMCLog.d(f11167a, String.format("TimeoutMessageLog handleSendGroupMessageTimeout packetId:%s, p2tMessage:%s", new Object[]{mIMCGroupMessage.a(), mIMCGroupMessage}));
                    }
                } else if (b2.j() == Mimc.MIMC_MSG_TYPE.UC_PACKET) {
                    Mimc.UCMessage a4 = Mimc.UCMessage.a(Mimc.UCPacket.a(b2.m()).f());
                    MIMCGroupMessage mIMCGroupMessage2 = new MIMCGroupMessage(b2.b(), b2.h(), a4.j().d(), a4.j().i(), a4.b().d(), a4.d().toByteArray(), b2.o(), a4.r());
                    if (this.b.r() == null) {
                        MIMCLog.d(f11167a, "MessageHandler is null, please registerMessageHandler first!");
                    } else {
                        this.b.r().b(mIMCGroupMessage2);
                        MIMCLog.d(f11167a, String.format("TimeoutMessageLog handleSendUnlimitedGroupMessageTimeout packetId:%s, groupMessage:%s", new Object[]{mIMCGroupMessage2.a(), mIMCGroupMessage2}));
                    }
                }
                arrayList.add(b2.b());
            }
        }
        for (String remove : arrayList) {
            this.b.w().remove(remove);
        }
    }

    public void b() {
        if (this.b.L() != MIMCUser.RelayState.NOT_CREATED && this.b.L() != MIMCUser.RelayState.SUCC_CREATED && System.currentTimeMillis() - this.b.M() >= 5000) {
            this.b.C().b(this.b.G());
            for (Map.Entry entry : this.b.x().entrySet()) {
                if (((P2PCallSession) entry.getValue()).b() == P2PCallSession.CallState.WAIT_CALL_ON_LAUNCHED) {
                    RTSUtils.a(this.b, RtsSignal.RTSResult.INTERNAL_ERROR1, MIMCConstant.O, ((Long) entry.getKey()).longValue(), -1);
                }
                this.b.u().a(((Long) entry.getKey()).longValue(), MIMCConstant.O);
            }
            this.b.x().clear();
            this.b.U();
            MIMCLog.b(f11167a, MIMCConstant.O);
        }
    }

    public void c() {
        long j;
        ConcurrentMap<Long, P2PCallSession> x = this.b.x();
        if (!x.isEmpty()) {
            ArrayList<Long> arrayList = new ArrayList<>();
            for (Map.Entry entry : x.entrySet()) {
                long longValue = ((Long) entry.getKey()).longValue();
                P2PCallSession p2PCallSession = (P2PCallSession) entry.getValue();
                P2PCallSession.CallState b2 = p2PCallSession.b();
                if (b2 == P2PCallSession.CallState.RUNNING) {
                    a(longValue, p2PCallSession.r());
                } else if (System.currentTimeMillis() - p2PCallSession.s() >= ((long) this.b.af())) {
                    MIMCLog.c(f11167a, String.format("TIMEOUT, CALL_ID:%d, CALL_STATE:%s", new Object[]{Long.valueOf(longValue), b2}));
                    if (p2PCallSession.b() == P2PCallSession.CallState.WAIT_CALL_ON_LAUNCHED || p2PCallSession.b() == P2PCallSession.CallState.WAIT_SEND_INVITE_RESPONSE) {
                        j = longValue;
                        RTSUtils.a(this.b, RtsSignal.RTSResult.INTERNAL_ERROR1, "TIMEOUT", longValue, -1);
                    } else {
                        j = longValue;
                    }
                    arrayList.add(Long.valueOf(j));
                }
            }
            for (Long l : arrayList) {
                RTSUtils.a(l.longValue(), this.b);
                P2PCallSession p2PCallSession2 = (P2PCallSession) x.remove(l);
                MIMCLog.c(f11167a, String.format("TIMEOUT currentCalls.remove callId:%d", new Object[]{l}));
                if (p2PCallSession2.b() == P2PCallSession.CallState.WAIT_SEND_CREATE_REQUEST) {
                    this.b.u().a(l.longValue(), MIMCConstant.M);
                    MIMCLog.c(f11167a, MIMCConstant.M);
                } else if (p2PCallSession2.b() == P2PCallSession.CallState.WAIT_RECEIVE_CREATE_RESPONSE) {
                    this.b.u().a(l.longValue(), MIMCConstant.M);
                    MIMCLog.c(f11167a, MIMCConstant.M);
                } else if (p2PCallSession2.b() == P2PCallSession.CallState.WAIT_CALL_ON_LAUNCHED) {
                    this.b.u().a(l.longValue(), MIMCConstant.N);
                    MIMCLog.c(f11167a, MIMCConstant.M);
                } else if (p2PCallSession2.b() == P2PCallSession.CallState.WAIT_SEND_INVITE_RESPONSE) {
                    this.b.u().a(l.longValue(), MIMCConstant.N);
                    MIMCLog.c(f11167a, MIMCConstant.M);
                } else if (p2PCallSession2.b() == P2PCallSession.CallState.WAIT_SEND_UPDATE_REQUEST) {
                    this.b.u().a(l.longValue(), MIMCConstant.Q);
                    MIMCLog.c(f11167a, MIMCConstant.Q);
                    RTSUtils.a(this.b, l.longValue(), MIMCConstant.Q);
                } else if (p2PCallSession2.b() == P2PCallSession.CallState.WAIT_RECEIVE_UPDATE_RESPONSE) {
                    this.b.u().a(l.longValue(), MIMCConstant.Q);
                    MIMCLog.c(f11167a, MIMCConstant.Q);
                    RTSUtils.a(this.b, l.longValue(), MIMCConstant.Q);
                } else {
                    this.b.u().a(l.longValue(), "TIMEOUT");
                    MIMCLog.c(f11167a, "UNKNOWN_TIMEOUT");
                }
                RTSUtils.c(this.b);
            }
        }
    }

    public void d() {
        ConcurrentMap<Long, TempChannelSession> z = this.b.z();
        if (!z.isEmpty()) {
            ArrayList<Long> arrayList = new ArrayList<>();
            for (Map.Entry entry : z.entrySet()) {
                long longValue = ((Long) entry.getKey()).longValue();
                if (System.currentTimeMillis() - ((TempChannelSession) entry.getValue()).b() >= ((long) this.b.af())) {
                    arrayList.add(Long.valueOf(longValue));
                }
            }
            for (Long l : arrayList) {
                this.b.g().a(l.longValue(), 0, "", false, "CREATE_CHANNEL_TIMEOUT", ((TempChannelSession) this.b.z().remove(l)).a());
            }
            RTSUtils.c(this.b);
        }
    }

    public void e() {
        ConcurrentMap<Long, ChannelSession> y = this.b.y();
        if (!y.isEmpty()) {
            ArrayList<Long> arrayList = new ArrayList<>();
            for (Map.Entry entry : y.entrySet()) {
                long longValue = ((Long) entry.getKey()).longValue();
                ChannelSession channelSession = (ChannelSession) entry.getValue();
                if (channelSession.s() <= 0) {
                    a(longValue, channelSession.r());
                } else if (System.currentTimeMillis() - channelSession.s() >= ((long) this.b.af())) {
                    arrayList.add(Long.valueOf(longValue));
                }
            }
            for (Long l : arrayList) {
                this.b.g().a(l.longValue(), this.b.q(), this.b.n(), false, "JOIN_CHANNEL_TIMEOUT", ((ChannelSession) this.b.y().remove(l)).c(), (List<ChannelUser>) null);
            }
            RTSUtils.c(this.b);
        }
    }

    public void a(long j, RtsSignal.CallType callType) {
        if (System.currentTimeMillis() - this.e >= 10000) {
            this.e = System.currentTimeMillis();
            RtsSignal.PingRequest.Builder a2 = RtsSignal.PingRequest.a();
            RtsSignal.RTSMessage.Builder q = RtsSignal.RTSMessage.q();
            q.a(RtsSignal.RTSMessageType.PING_REQUEST);
            q.a(j);
            q.a(callType);
            q.b(this.b.j());
            q.a(this.b.n());
            q.b(((RtsSignal.PingRequest) a2.Z()).J());
            q.c(this.b.n(j));
            Mimc.MIMCPacket.Builder p = Mimc.MIMCPacket.p();
            p.a(this.b.b());
            p.b(this.b.o());
            p.a(Mimc.MIMC_MSG_TYPE.RTS_SIGNAL);
            p.c(((RtsSignal.RTSMessage) q.Z()).J());
            this.b.b(p.b(), ((Mimc.MIMCPacket) p.Z()).K(), MIMCConstant.Z);
            MIMCLog.b(f11167a, "pushPacket pingCallCenter");
        }
    }

    public void f() {
        if (!this.b.x().isEmpty() && this.b.K() != null && this.b.L() == MIMCUser.RelayState.SUCC_CREATED && System.currentTimeMillis() - this.f >= 10000) {
            this.f = System.currentTimeMillis();
            RtsData.PingRelayRequest.Builder f2 = RtsData.PingRelayRequest.f();
            f2.a(this.b.j());
            f2.a(this.b.n());
            RtsData.UserPacket.Builder t = RtsData.UserPacket.t();
            t.a(RtsData.PKT_TYPE.PING_RELAY_REQUEST);
            t.a(this.b.j());
            t.a(this.b.n());
            t.b(((RtsData.PingRelayRequest) f2.Z()).J());
            if (this.b.G() == -1 || this.b.H() == -1 || this.b.L() != MIMCUser.RelayState.SUCC_CREATED) {
                MIMCLog.c(f11167a, "PING_RELAY CONN_ID OR STREAM_ID IS NULL");
            } else if (-1 == this.b.C().a(this.b.G(), this.b.H(), ((RtsData.UserPacket) t.Z()).K(), true, XMDPacket.DataPriority.P1, 3, (Object) null)) {
                MIMCLog.c(f11167a, String.format("SEND_STREAM_DATA FAIL, PACKET:%s", new Object[]{t.Z()}));
            } else {
                MIMCLog.b(f11167a, String.format("SEND PING RELAY REQUEST SUCCESS, relayconnId:%d, relayControlStreamId:%d", new Object[]{Long.valueOf(this.b.G()), Short.valueOf(this.b.H())}));
            }
        }
    }

    private void g() {
        if (System.currentTimeMillis() - this.d >= ((long) this.b.ae())) {
            this.d = System.currentTimeMillis();
            if (!this.b.D().isEmpty()) {
                if (this.b.V() != MIMCConstant.OnlineStatus.ONLINE) {
                    MIMCLog.a(f11167a, String.format("This %s is offline.", new Object[]{this.b.q()}));
                    return;
                }
                String str = "";
                Mimc.UCPing.Builder d2 = Mimc.UCPing.d();
                for (Long next : this.b.D()) {
                    d2.a((Mimc.UCGroup) Mimc.UCGroup.e().a(this.b.k()).b(next.longValue()).Z());
                    str = str + "|" + next;
                }
                String b2 = this.b.b();
                Mimc.MIMCPacket mIMCPacket = (Mimc.MIMCPacket) Mimc.MIMCPacket.p().a(b2).b(this.b.o()).a(Mimc.MIMC_MSG_TYPE.UC_PACKET).c(ByteString.copyFrom(((Mimc.UCPacket) Mimc.UCPacket.j().a(b2).a(Mimc.UC_MSG_TYPE.PING).a((Mimc.MIMCUser) Mimc.MIMCUser.l().a(this.b.k()).a(this.b.q()).b(this.b.j()).b(this.b.n()).Z()).a(ByteString.copyFrom(((Mimc.UCPing) d2.Z()).K())).Z()).K())).Z();
                this.b.b(b2, mIMCPacket.K(), MIMCConstant.Y);
                MIMCLog.a(f11167a, String.format("PingUnlimitedGroup push packet. %s, packetLen:%d uuid:%d, resource:%s, groups:%s", new Object[]{this.b.V(), Integer.valueOf(mIMCPacket.K().length), Long.valueOf(this.b.j()), this.b.n(), str}));
            }
        }
    }
}
