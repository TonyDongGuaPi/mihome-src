package com.xiaomi.mimc.xmdtransceiverhandler;

import com.xiaomi.mimc.MIMCUser;
import com.xiaomi.mimc.common.RTSUtils;
import com.xiaomi.mimc.data.ConnectionContent;
import com.xiaomi.mimc.data.P2PCallSession;
import com.xiaomi.mimc.proto.RtsData;
import com.xiaomi.mimc.proto.RtsSignal;
import com.xiaomi.mimc.protobuf.InvalidProtocolBufferException;
import com.xiaomi.msg.handler.DatagramHandler;
import com.xiaomi.msg.logger.MIMCLog;
import java.net.InetSocketAddress;

public class RTSDatagramHandler implements DatagramHandler {

    /* renamed from: a  reason: collision with root package name */
    private static final String f11358a = "RTSDatagramHandler";
    private MIMCUser b;

    public RTSDatagramHandler(MIMCUser mIMCUser) {
        this.b = mIMCUser;
    }

    public void a(InetSocketAddress inetSocketAddress, byte[] bArr) {
        String hostAddress = inetSocketAddress.getAddress().getHostAddress();
        int port = inetSocketAddress.getPort();
        MIMCLog.a(f11358a, String.format("RECV_DATAGRAM. from ip:%s, port:%d ", new Object[]{hostAddress, Integer.valueOf(port)}));
        try {
            RtsData.BurrowPacket a2 = RtsData.BurrowPacket.a(bArr);
            if (a2.h() && a2.j() && a2.f() && a2.a()) {
                if (a2.c()) {
                    long g = a2.g();
                    long i = a2.i();
                    long b2 = a2.b();
                    String d = a2.d();
                    P2PCallSession p2PCallSession = (P2PCallSession) this.b.x().get(Long.valueOf(g));
                    if (p2PCallSession == null) {
                        MIMCLog.c(f11358a, String.format("CurrentCalls is null, callId:%d", new Object[]{Long.valueOf(g)}));
                        return;
                    }
                    P2PCallSession.CallState b3 = p2PCallSession.b();
                    if (b3 == P2PCallSession.CallState.RUNNING) {
                        if (p2PCallSession.r() == RtsSignal.CallType.SINGLE_CALL) {
                            if (!a(p2PCallSession, b2, d)) {
                                MIMCLog.c(f11358a, "RECV_DATAGRAM. PARAMS IS ERROR");
                                return;
                            } else if (a2.l() == RtsData.BURROW_TYPE.INTRANET_BURROW_REQUEST) {
                                MIMCLog.b(f11358a, String.format("RECV_DATAGRAM INTRANET_BURROW_REQUEST. fromIp:%s, fromPort:%d, uuid:%d, resource:%s", new Object[]{hostAddress, Integer.valueOf(port), Long.valueOf(b2), d}));
                                a(hostAddress, port, g);
                                this.b.C().a(hostAddress, port, ((RtsData.BurrowPacket) RTSUtils.a(this.b.j(), this.b.n(), g, RtsData.BURROW_TYPE.INTRANET_BURROW_RESPONSE, i).Z()).K(), 0);
                                MIMCLog.b(f11358a, String.format("SEND_INTRANET_BURROW_RESPONSE SUCCESS, currentuuid:%d", new Object[]{Long.valueOf(this.b.j())}));
                                return;
                            } else if (a2.l() == RtsData.BURROW_TYPE.INTERNET_BURROW_REQUEST) {
                                MIMCLog.b(f11358a, String.format("RECV_DATAGRAM INTERNET_BURROW_REQUEST. fromIp:%s, fromPort:%d, uuid:%d, resource:%s", new Object[]{hostAddress, Integer.valueOf(port), Long.valueOf(b2), d}));
                                a(hostAddress, port, g);
                                this.b.C().a(hostAddress, port, ((RtsData.BurrowPacket) RTSUtils.a(this.b.j(), this.b.n(), g, RtsData.BURROW_TYPE.INTERNET_BURROW_RESPONSE, i).Z()).K(), 0);
                                MIMCLog.b(f11358a, String.format("SEND_DATAGRAM INTERNET_BURROW_RESPONSE SUCCESS, currentuuid:%d", new Object[]{Long.valueOf(this.b.j())}));
                                return;
                            } else if (a2.l() == RtsData.BURROW_TYPE.INTRANET_BURROW_RESPONSE) {
                                MIMCLog.b(f11358a, String.format("RECV_DATAGRAM INTRANET_BURROW_RESPONSE. currentuuid:%d, fromIp:%s, fromPort:%d, uuid:%d, resource:%s", new Object[]{Long.valueOf(this.b.j()), hostAddress, Integer.valueOf(port), Long.valueOf(b2), d}));
                                if (p2PCallSession.j()) {
                                    MIMCLog.b(f11358a, String.format("INTRANET BURROW SUCCESS currentuuid:%d", new Object[]{Long.valueOf(this.b.j())}));
                                    return;
                                }
                                p2PCallSession.b(true);
                                MIMCLog.b(f11358a, String.format("setIntranetBurrowState true, currentuuid:%d", new Object[]{Long.valueOf(this.b.j())}));
                                if (p2PCallSession.i()) {
                                    MIMCLog.b(f11358a, String.format("creator create intranet p2p connection, connId:%d", new Object[]{Long.valueOf(a(hostAddress, port, RtsData.PKT_TYPE.INTRANET_CONN_REQUEST, g, ConnectionContent.ConnType.P2P_INTRANET_CONN))}));
                                    return;
                                }
                                return;
                            } else if (a2.l() == RtsData.BURROW_TYPE.INTERNET_BURROW_RESPONSE) {
                                MIMCLog.b(f11358a, String.format("RECV_DATAGRAM INTERNET_BURROW_RESPONSE.currentuuid:%d, fromIp:%s, fromPort:%d, uuid:%d, resource:%s", new Object[]{Long.valueOf(this.b.j()), hostAddress, Integer.valueOf(port), Long.valueOf(b2), d}));
                                if (p2PCallSession.k()) {
                                    MIMCLog.b(f11358a, String.format("INTERNET BURROW SUCCESS, currentuuid:%d", new Object[]{Long.valueOf(this.b.j())}));
                                    return;
                                }
                                p2PCallSession.c(true);
                                MIMCLog.b(f11358a, String.format("setInternetBurrowState true currentuuid:%d", new Object[]{Long.valueOf(this.b.j())}));
                                if (p2PCallSession.i()) {
                                    MIMCLog.b(f11358a, String.format("creator create internet p2p connection, connId:%d", new Object[]{Long.valueOf(a(hostAddress, port, RtsData.PKT_TYPE.INTERNET_CONN_REQUEST, g, ConnectionContent.ConnType.P2P_INTERNET_CONN))}));
                                    return;
                                }
                                return;
                            } else {
                                MIMCLog.c(f11358a, String.format("RECV_DATAGRAM INVALID_TYPE. TYPE:%s", new Object[]{a2.l()}));
                                return;
                            }
                        }
                    }
                    MIMCLog.c(f11358a, String.format("The callSession is not match, uuid:%d, callState:%s, callType:%s", new Object[]{Long.valueOf(this.b.j()), b3, p2PCallSession.r()}));
                    return;
                }
            }
            MIMCLog.c(f11358a, "RECV_DATAGRAM. DO NOT CONTAIN REQUEST FIELD");
        } catch (InvalidProtocolBufferException e) {
            e.printStackTrace();
        }
    }

    public boolean a(P2PCallSession p2PCallSession, long j, String str) {
        if (p2PCallSession.r() != RtsSignal.CallType.SINGLE_CALL) {
            MIMCLog.c(f11358a, "The current call is not Signal");
            return false;
        } else if (p2PCallSession.a().b() == j && p2PCallSession.a().d().equals(str)) {
            return true;
        } else {
            MIMCLog.c(f11358a, String.format("The uuid or resource is not equals uuid:%d, resource:%s, otheruuid:%d, otherResource:%s", new Object[]{Long.valueOf(j), str, Long.valueOf(p2PCallSession.a().b()), p2PCallSession.a().d()}));
            return false;
        }
    }

    private long a(String str, int i, RtsData.PKT_TYPE pkt_type, long j, ConnectionContent.ConnType connType) {
        long j2 = j;
        RtsData.UserPacket.Builder t = RtsData.UserPacket.t();
        t.a(this.b.j());
        t.a(this.b.n());
        RtsData.PKT_TYPE pkt_type2 = pkt_type;
        t.a(pkt_type);
        t.b(j2);
        t.c(this.b.n(j2));
        long a2 = this.b.C().a(str, i, ((RtsData.UserPacket) t.Z()).K(), 5, new ConnectionContent(this.b.j(), this.b.n(), connType, j2));
        if (a2 == -1) {
            MIMCLog.c(f11358a, String.format("Create connection with p2p failed, uuid:%d, resource:%s", new Object[]{Long.valueOf(this.b.j()), this.b.n()}));
        }
        return a2;
    }

    private void a(String str, int i, long j) {
        P2PCallSession p2PCallSession = (P2PCallSession) this.b.x().get(Long.valueOf(j));
        if (p2PCallSession == null) {
            MIMCLog.c(f11358a, "callSession is null in updateBurrowIpOrPort");
            return;
        }
        RtsSignal.UserInfo a2 = p2PCallSession.a();
        RtsSignal.UserInfo.Builder builder = (RtsSignal.UserInfo.Builder) a2.Y();
        if (!str.equals(a2.r()) && !str.equals(a2.m())) {
            builder.d(str);
            MIMCLog.b(f11358a, String.format("RECV_BURROW_REQUEST otherIp:%s, intranetIp:%s, internetIp:%s", new Object[]{str, a2.m(), a2.r()}));
        }
        if (!(i == a2.u() || i == a2.p())) {
            builder.b(i);
            MIMCLog.b(f11358a, String.format("RECV_BURROW_REQUEST otherPort:%d, intranetPort:%d, internetPort:%d", new Object[]{Integer.valueOf(i), Integer.valueOf(a2.p()), Integer.valueOf(a2.u())}));
        }
        p2PCallSession.a((RtsSignal.UserInfo) builder.Z());
    }
}
