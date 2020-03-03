package com.xiaomi.mimc.xmdtransceiverhandler;

import com.xiaomi.mimc.MIMCUser;
import com.xiaomi.mimc.common.RTSUtils;
import com.xiaomi.mimc.data.ChannelSession;
import com.xiaomi.mimc.data.MIMCContext;
import com.xiaomi.mimc.data.P2PCallSession;
import com.xiaomi.mimc.data.RtsChannelType;
import com.xiaomi.mimc.data.RtsDataType;
import com.xiaomi.mimc.data.TempChannelSession;
import com.xiaomi.mimc.processor.OnLaunchedProcessor;
import com.xiaomi.mimc.proto.RtsData;
import com.xiaomi.msg.handler.StreamHandler;
import com.xiaomi.msg.logger.MIMCLog;
import java.util.Map;

public class RTSStreamHandler implements StreamHandler {

    /* renamed from: a  reason: collision with root package name */
    private static final String f11359a = "RTSStreamHandler";
    private MIMCUser b;

    public RTSStreamHandler(MIMCUser mIMCUser) {
        this.b = mIMCUser;
    }

    public void a(short s) {
        MIMCLog.b(f11359a, String.format("client handle new stream %d", new Object[]{Short.valueOf(s)}));
    }

    public void b(short s) {
        MIMCLog.b(f11359a, String.format("client handle close stream %d", new Object[]{Short.valueOf(s)}));
    }

    public void a(long j, short s, int i, byte[] bArr) {
        MIMCLog.a(f11359a, String.format("RECV_PACKET, STREAM_DATA, connId=%d, streamId=%d, dataLen=%d", new Object[]{Long.valueOf(j), Short.valueOf(s), Integer.valueOf(bArr.length)}));
        try {
            RtsData.UserPacket a2 = RtsData.UserPacket.a(bArr);
            if (a2.e() && a2.c()) {
                if (a2.g()) {
                    MIMCLog.a(f11359a, String.format("RECV_PACKET, USER_PACKET, uuid：%d, resource:%s", new Object[]{Long.valueOf(a2.f()), a2.h()}));
                    if (a2.d() == RtsData.PKT_TYPE.BIND_RELAY_RESPONSE) {
                        RtsData.BindRelayResponse a3 = RtsData.BindRelayResponse.a(a2.n());
                        if (a3.a() && a3.c() && a3.h() && a3.f() && a3.l()) {
                            if (a3.b()) {
                                MIMCLog.b(f11359a, String.format("MIMC connId:%d RECEIVE_BIND_RELAY_RESPONSE_SUCCESS, bindRelayResponse:%s", new Object[]{Long.valueOf(j), a3}));
                                this.b.a(a3);
                                this.b.a(MIMCUser.RelayState.SUCC_CREATED);
                                this.b.j(System.currentTimeMillis());
                                for (Map.Entry entry : this.b.x().entrySet()) {
                                    long longValue = ((Long) entry.getKey()).longValue();
                                    P2PCallSession p2PCallSession = (P2PCallSession) entry.getValue();
                                    if (p2PCallSession.b() != P2PCallSession.CallState.WAIT_SEND_CREATE_REQUEST || !p2PCallSession.i()) {
                                        if (p2PCallSession.b() == P2PCallSession.CallState.WAIT_CALL_ON_LAUNCHED && !p2PCallSession.i()) {
                                            MIMCLog.b(f11359a, String.format("MIMC connId:%d callId:%d WAIT_CALL_ON_LAUNCHED", new Object[]{Long.valueOf(j), Long.valueOf(longValue)}));
                                            if (p2PCallSession.m() == null) {
                                                OnLaunchedProcessor onLaunchedProcessor = new OnLaunchedProcessor(this.b, longValue);
                                                p2PCallSession.a(onLaunchedProcessor);
                                                onLaunchedProcessor.setDaemon(true);
                                                onLaunchedProcessor.start();
                                            }
                                        }
                                        if (p2PCallSession.b() == P2PCallSession.CallState.WAIT_SEND_UPDATE_REQUEST) {
                                            MIMCLog.b(f11359a, String.format("WAIT_SEND_UPDATE_REQUEST, callId:%d", new Object[]{Long.valueOf(longValue)}));
                                            RTSUtils.b(this.b, longValue);
                                            p2PCallSession.a(P2PCallSession.CallState.WAIT_RECEIVE_UPDATE_RESPONSE).d(System.currentTimeMillis());
                                        }
                                    } else {
                                        MIMCLog.b(f11359a, String.format("MIMC connId:%d callId:%d WAIT_SEND_CREATE_REQUEST", new Object[]{Long.valueOf(j), Long.valueOf(longValue)}));
                                        RTSUtils.a(this.b, longValue);
                                    }
                                }
                                for (Map.Entry entry2 : this.b.z().entrySet()) {
                                    long longValue2 = ((Long) entry2.getKey()).longValue();
                                    MIMCLog.b(f11359a, String.format("BEGIN_SEND_CREATE_CHANNEL_REQUEST, identity:%d", new Object[]{Long.valueOf(longValue2)}));
                                    RTSUtils.a(this.b, longValue2, ((TempChannelSession) entry2.getValue()).a());
                                }
                                for (Map.Entry entry3 : this.b.y().entrySet()) {
                                    long longValue3 = ((Long) entry3.getKey()).longValue();
                                    MIMCLog.b(f11359a, String.format("BEGIN_SEND_JOIN_CHANNEL_REQUEST, callId:%d", new Object[]{Long.valueOf(longValue3)}));
                                    RTSUtils.b(this.b, longValue3, ((ChannelSession) entry3.getValue()).b());
                                }
                                return;
                            }
                        }
                        MIMCLog.c(f11359a, "RECV_PACKET, BIND_RELAY_RESPONSE, PARAMS_NOT_ILLEGAL.");
                        return;
                    } else if (a2.d() == RtsData.PKT_TYPE.PING_RELAY_RESPONSE) {
                        MIMCLog.a(f11359a, String.format("RECV_PING_RELAY_RESPONSE uuid:%d", new Object[]{Long.valueOf(this.b.j())}));
                        RtsData.PingRelayResponse a4 = RtsData.PingRelayResponse.a(a2.n());
                        if (a4.a() && a4.c()) {
                            if (a4.f()) {
                                if (!this.b.K().d().equals(a4.d()) || this.b.K().g() != a4.g()) {
                                    MIMCLog.c(f11359a, String.format("THE EXTERNAL IP OR PORT OF THE DEVICE HAS CHANGED, oldIp:%s, newIp:%s, oldPort:%d, newPort:%d", new Object[]{this.b.K().d(), a4.d(), Integer.valueOf(this.b.K().g()), Integer.valueOf(a4.g())}));
                                    this.b.C().b(this.b.G());
                                    this.b.U();
                                    RTSUtils.d(this.b);
                                    return;
                                }
                                return;
                            }
                        }
                        MIMCLog.c(f11359a, "RECV_PACKET, PING_RELAY_RESPONSE, PARAM_NOT_ILLEGAL.");
                        return;
                    } else if (a2.d() == RtsData.PKT_TYPE.USER_DATA_AUDIO) {
                        MIMCLog.a(f11359a, String.format("RECV_USER_PACKET USER_DATA_AUDIO, uuid:%d", new Object[]{Long.valueOf(this.b.j())}));
                        long l = a2.l();
                        byte[] byteArray = a2.n().toByteArray();
                        if (this.b.G() == j) {
                            if (this.b.x().containsKey(Long.valueOf(l))) {
                                this.b.u().a(l, a2.r(), a2.h(), byteArray, RtsDataType.AUDIO, RtsChannelType.RELAY);
                                return;
                            } else if (this.b.y().containsKey(Long.valueOf(l))) {
                                this.b.g().a(l, a2.r(), a2.h(), byteArray, RtsDataType.AUDIO);
                                return;
                            } else {
                                return;
                            }
                        } else if (this.b.c(l) == j) {
                            this.b.u().a(l, a2.r(), a2.h(), byteArray, RtsDataType.AUDIO, RtsChannelType.P2P_INTRANET);
                            return;
                        } else if (this.b.d(l) == j) {
                            this.b.u().a(l, a2.r(), a2.h(), byteArray, RtsDataType.AUDIO, RtsChannelType.P2P_INTERNET);
                            return;
                        } else {
                            return;
                        }
                    } else if (a2.d() == RtsData.PKT_TYPE.USER_DATA_VIDEO) {
                        MIMCLog.a(f11359a, String.format("RECV_USER_PACKET USER_DATA_VIDEO, uuid:%d", new Object[]{Long.valueOf(this.b.j())}));
                        long l2 = a2.l();
                        byte[] byteArray2 = a2.n().toByteArray();
                        if (this.b.G() == j) {
                            if (this.b.x().containsKey(Long.valueOf(l2))) {
                                this.b.u().a(l2, a2.r(), a2.h(), byteArray2, RtsDataType.VIDEO, RtsChannelType.RELAY);
                                return;
                            } else if (this.b.y().containsKey(Long.valueOf(l2))) {
                                this.b.g().a(l2, a2.r(), a2.h(), byteArray2, RtsDataType.VIDEO);
                                return;
                            } else {
                                return;
                            }
                        } else if (this.b.c(l2) == j) {
                            this.b.u().a(l2, a2.r(), a2.h(), byteArray2, RtsDataType.VIDEO, RtsChannelType.P2P_INTRANET);
                            return;
                        } else if (this.b.d(l2) == j) {
                            this.b.u().a(l2, a2.r(), a2.h(), byteArray2, RtsDataType.VIDEO, RtsChannelType.P2P_INTERNET);
                            return;
                        } else {
                            return;
                        }
                    } else {
                        MIMCLog.c(f11359a, String.format("RECV_PACKET, INVALID_TYPE, TYPE:%s", new Object[]{a2.d()}));
                        return;
                    }
                }
            }
            MIMCLog.c(f11359a, "RECV_PACKET, STREAM_HANDLER, DO NOT CONTAIN REQUEST FIELD");
        } catch (Exception e) {
            MIMCLog.d(f11359a, "handleRecvStreamData e:", e);
        }
    }

    public void a(long j, short s, int i, Object obj) {
        MIMCContext mIMCContext = (MIMCContext) obj;
        if (mIMCContext == null) {
            MIMCLog.a(f11359a, String.format("handleSendStreamDataSucc this context is null, connId:%d streamId:%d groupId:%d", new Object[]{Long.valueOf(j), Short.valueOf(s), Integer.valueOf(i)}));
            return;
        }
        long a2 = mIMCContext.a();
        if (this.b.x().containsKey(Long.valueOf(a2))) {
            this.b.u().a(a2, i, mIMCContext.b());
        } else if (this.b.y().containsKey(Long.valueOf(a2))) {
            this.b.g().a(a2, i, mIMCContext.b());
        }
    }

    public void b(long j, short s, int i, Object obj) {
        MIMCContext mIMCContext = (MIMCContext) obj;
        if (mIMCContext == null) {
            MIMCLog.a(f11359a, String.format("handleSendStreamDataFail this context is null, connId:%d streamId:%d groupId:%d", new Object[]{Long.valueOf(j), Short.valueOf(s), Integer.valueOf(i)}));
            return;
        }
        long a2 = mIMCContext.a();
        if (this.b.x().containsKey(Long.valueOf(a2))) {
            this.b.u().b(a2, i, mIMCContext.b());
        } else if (this.b.y().containsKey(Long.valueOf(a2))) {
            this.b.g().b(a2, i, mIMCContext.b());
        }
    }

    public boolean a(long j) {
        if (!this.b.x().containsKey(Long.valueOf(j))) {
            MIMCLog.c(f11359a, "CALL_ID NOT IN CURRENT_CALLS");
            return false;
        } else if (((P2PCallSession) this.b.x().get(Long.valueOf(j))).b() == P2PCallSession.CallState.RUNNING) {
            return true;
        } else {
            MIMCLog.c(f11359a, "CALL_ID NOT RUNNING STATE");
            return false;
        }
    }
}
