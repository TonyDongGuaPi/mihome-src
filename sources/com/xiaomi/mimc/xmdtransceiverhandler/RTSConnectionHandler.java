package com.xiaomi.mimc.xmdtransceiverhandler;

import com.xiaomi.mimc.MIMCUser;
import com.xiaomi.mimc.common.MIMCConstant;
import com.xiaomi.mimc.common.MIMCUtils;
import com.xiaomi.mimc.common.RTSUtils;
import com.xiaomi.mimc.data.ConnectionContent;
import com.xiaomi.mimc.data.P2PCallSession;
import com.xiaomi.mimc.processor.RelayAddressProcessor;
import com.xiaomi.mimc.proto.RtsData;
import com.xiaomi.mimc.protobuf.InvalidProtocolBufferException;
import com.xiaomi.msg.data.XMDPacket;
import com.xiaomi.msg.handler.ConnectionHandler;
import com.xiaomi.msg.logger.MIMCLog;
import java.util.Map;

public class RTSConnectionHandler implements ConnectionHandler {

    /* renamed from: a  reason: collision with root package name */
    private static final String f11357a = "RTSConnectionHandler";
    private MIMCUser b;

    public RTSConnectionHandler(MIMCUser mIMCUser) {
        this.b = mIMCUser;
    }

    public void a(long j, Object obj) {
        ConnectionContent connectionContent = (ConnectionContent) obj;
        if (connectionContent.c() == ConnectionContent.ConnType.RELAY_CONN) {
            MIMCLog.b(f11357a, String.format("MIMC connId:%d HANDLE_CREATE_RELAY_CONN_SUCCESS create relay connection success", new Object[]{Long.valueOf(j)}));
            if (this.b.L() == MIMCUser.RelayState.NOT_CREATED) {
                MIMCLog.c(f11357a, "MIMCUser.RelayState.NOT_CREATED");
                return;
            }
            short a2 = this.b.C().a(j, XMDPacket.StreamType.ACK_STREAM, (short) MIMCConstant.V, false);
            MIMCLog.b(f11357a, String.format("xmdTransceiver createStream success, streamId:%d", new Object[]{Short.valueOf(a2)}));
            this.b.a(a2);
            if (!RTSUtils.b(this.b)) {
                MIMCLog.c(f11357a, String.format("MIMC connId:%d SEND_BIND_RELAY_REQUEST_FAIL", new Object[]{Long.valueOf(j)}));
                for (Map.Entry key : this.b.x().entrySet()) {
                    this.b.u().a(((Long) key.getKey()).longValue(), MIMCConstant.S);
                }
                if (j != -1) {
                    this.b.C().b(j);
                }
                this.b.x().clear();
                this.b.U();
                return;
            }
            MIMCLog.c(f11357a, String.format("MIMC connId:%d SEND_BIND_RELAY_REQUEST_SUCCESS", new Object[]{Long.valueOf(j)}));
        } else if (connectionContent.c() == ConnectionContent.ConnType.P2P_INTRANET_CONN) {
            MIMCLog.b(f11357a, String.format("MIMC connId:%d HANDLE_CREATE_P2P_INTRANET_CONN_SUCCESS create p2p intranet connection success, uuid:%d", new Object[]{Long.valueOf(j), Long.valueOf(this.b.j())}));
            P2PCallSession p2PCallSession = (P2PCallSession) this.b.x().get(Long.valueOf(connectionContent.d()));
            if (p2PCallSession != null) {
                p2PCallSession.a(j);
            }
        } else if (connectionContent.c() == ConnectionContent.ConnType.P2P_INTERNET_CONN) {
            MIMCLog.b(f11357a, String.format("MIMC connId:%d HANDLE_CREATE_P2P_INTERNET_CONN_SUCCESS create p2p internet connection success, uuid:%d", new Object[]{Long.valueOf(j), Long.valueOf(this.b.j())}));
            P2PCallSession p2PCallSession2 = (P2PCallSession) this.b.x().get(Long.valueOf(connectionContent.d()));
            if (p2PCallSession2 != null) {
                p2PCallSession2.b(j);
            }
        } else {
            MIMCLog.c(f11357a, String.format("HANDLE_CREATE_ALL_CONN_FAILED Error ConnType:%s", new Object[]{connectionContent.c()}));
        }
    }

    public void b(long j, Object obj) {
        if (((ConnectionContent) obj).c() == ConnectionContent.ConnType.RELAY_CONN) {
            MIMCLog.c(f11357a, String.format("MIMC connId:%d HANDLE_CREATE_RELAY_CONN_FAIL create relay connection failed", new Object[]{Long.valueOf(j)}));
            this.b.i((String) null);
            MIMCUtils.a(this.b.ac(), this.b.T(), MIMCConstant.ag, "");
            new RelayAddressProcessor(this.b).start();
            for (Map.Entry key : this.b.x().entrySet()) {
                this.b.u().a(((Long) key.getKey()).longValue(), MIMCConstant.T);
            }
            this.b.x().clear();
            this.b.U();
            return;
        }
        for (Map.Entry entry : this.b.x().entrySet()) {
            long longValue = ((Long) entry.getKey()).longValue();
            P2PCallSession p2PCallSession = (P2PCallSession) entry.getValue();
            if (j == p2PCallSession.c()) {
                RTSUtils.b(longValue, this.b);
                MIMCLog.c(f11357a, "Create P2PIntranetConnId failed.");
            } else if (j == p2PCallSession.f()) {
                RTSUtils.c(longValue, this.b);
                MIMCLog.c(f11357a, "Create P2PInternetConnId failed.");
            }
        }
    }

    public void a(long j, byte[] bArr) {
        MIMCLog.b(f11357a, String.format("MIMC connId:%d HANDLE_NEW_CONN client new connection.", new Object[]{Long.valueOf(j)}));
        try {
            RtsData.UserPacket a2 = RtsData.UserPacket.a(bArr);
            if (a2.e() && a2.c() && a2.g()) {
                if (a2.j()) {
                    long l = a2.l();
                    RtsData.PKT_TYPE d = a2.d();
                    MIMCLog.b(f11357a, String.format("RECV_PACKET, USER_PACKET, handleNewConn callId:%d, pktType:%s", new Object[]{Long.valueOf(l), d}));
                    P2PCallSession p2PCallSession = (P2PCallSession) this.b.x().get(Long.valueOf(l));
                    if (p2PCallSession == null) {
                        MIMCLog.c(f11357a, "callSession is null in handleNewConn");
                        return;
                    }
                    if (d == RtsData.PKT_TYPE.INTRANET_CONN_REQUEST) {
                        p2PCallSession.a(j);
                        MIMCLog.b(f11357a, String.format("RECV_NEW_CONN INTRANET_CONN_REQUEST uuid:%d", new Object[]{Long.valueOf(a2.f())}));
                    }
                    if (d == RtsData.PKT_TYPE.INTERNET_CONN_REQUEST) {
                        p2PCallSession.b(j);
                        MIMCLog.b(f11357a, String.format("RECV_NEW_CONN INTERNET_CONN_REQUEST uuid:%d", new Object[]{Long.valueOf(a2.f())}));
                        return;
                    }
                    return;
                }
            }
            MIMCLog.c(f11357a, "RECV_PACKET, STREAM_HANDLER, DO NOT CONTAIN REQUEST FIELD");
        } catch (InvalidProtocolBufferException e) {
            MIMCLog.d(f11357a, "handleNewConn e:", e);
        }
    }

    public void a(long j, String str, Object obj) {
        MIMCLog.b(f11357a, String.format("Client connection closed. connId = %d", new Object[]{Long.valueOf(j)}));
        if (str.equals("NORMAL")) {
            MIMCLog.b(f11357a, String.format("Connection is closed normally, connId:%d, errMsg:%s", new Object[]{Long.valueOf(j), str}));
            return;
        }
        this.b.d(j, str);
    }

    public void a(long j, double d, double d2) {
        MIMCLog.a(f11357a, String.format("handleNetStateChange connId:%d, rtt:%f, packetLoss:%f", new Object[]{Long.valueOf(j), Double.valueOf(d), Double.valueOf(d2)}));
    }
}
