package com.xiaomi.mimc.common;

import com.xiaomi.mimc.MIMCGroupMessage;
import com.xiaomi.mimc.MIMCMessage;
import com.xiaomi.mimc.MIMCServerAck;
import com.xiaomi.mimc.MIMCUser;
import com.xiaomi.mimc.data.ChannelSession;
import com.xiaomi.mimc.data.ChannelUser;
import com.xiaomi.mimc.data.P2PCallSession;
import com.xiaomi.mimc.packet.V6Packet;
import com.xiaomi.mimc.processor.BurrowProcessor;
import com.xiaomi.mimc.processor.OnLaunchedProcessor;
import com.xiaomi.mimc.proto.Mimc;
import com.xiaomi.mimc.proto.RtsSignal;
import com.xiaomi.mimc.protobuf.ByteString;
import com.xiaomi.mimc.protobuf.InvalidProtocolBufferException;
import com.xiaomi.msg.logger.MIMCLog;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.ConcurrentMap;

public class UserMessageHandler {

    /* renamed from: a  reason: collision with root package name */
    private static final String f11180a = "UserMessageHandler";
    private MIMCUser b;

    public UserMessageHandler(MIMCUser mIMCUser) {
        this.b = mIMCUser;
    }

    public void a(V6Packet v6Packet) {
        V6Packet v6Packet2 = v6Packet;
        try {
            Mimc.MIMCPacket a2 = Mimc.MIMCPacket.a(v6Packet2.c);
            if (a2 == null) {
                MIMCLog.b(f11180a, "HandleMessage packet is null.");
                return;
            }
            MIMCLog.b(f11180a, String.format("RECV_PACKET, chid:%d, uuid:%d, packetId:%s, type:%s", new Object[]{Integer.valueOf(v6Packet2.b.b()), Long.valueOf(v6Packet2.b.d()), a2.b(), a2.j()}));
            switch (a2.j()) {
                case UC_PACKET:
                    a(a2);
                    break;
                case RTS_SIGNAL:
                    b(a2);
                    break;
            }
            if (a2.j() == Mimc.MIMC_MSG_TYPE.PACKET_ACK) {
                Mimc.MIMCPacketAck a3 = Mimc.MIMCPacketAck.a(a2.m());
                MIMCServerAck mIMCServerAck = new MIMCServerAck(a3.b(), a3.j(), a3.m(), a3.r());
                this.b.w().remove(mIMCServerAck.a());
                MIMCLog.b(f11180a, String.format("TimeoutMessageLog timeoutPackets remove, packetId:%s", new Object[]{mIMCServerAck.a()}));
                this.b.r().a(mIMCServerAck);
            } else if (a2.j() == Mimc.MIMC_MSG_TYPE.COMPOUND) {
                Mimc.MIMCPacketList a4 = Mimc.MIMCPacketList.a(a2.m());
                if (!this.b.n().equals(a4.d())) {
                    MIMCLog.d(f11180a, String.format("RECV_PACKET, PACKET_LIST, RESOURCE_NOT_MATCH, %s!=%s", new Object[]{this.b.n(), a4.d()}));
                    return;
                }
                String b2 = this.b.b();
                Mimc.MIMCPacket.Builder p = Mimc.MIMCPacket.p();
                p.a(b2);
                p.b(this.b.o());
                p.a(Mimc.MIMC_MSG_TYPE.SEQUENCE_ACK);
                Mimc.MIMCSequenceAck.Builder h = Mimc.MIMCSequenceAck.h();
                h.a(a4.b());
                h.a(a4.d());
                h.b(a4.g());
                p.c(((Mimc.MIMCSequenceAck) h.Z()).J());
                this.b.b(a2.b(), ((Mimc.MIMCPacket) p.Z()).K(), MIMCConstant.Z);
                MIMCLog.b(f11180a, "push packet Mimc.MIMC_MSG_TYPE.SEQUENCE_ACK");
                MIMCLog.b(f11180a, String.format("SEND_PACKET, SEQUENCE_ACK, packetId:%s, maxSequence:%d", new Object[]{a2.b(), Long.valueOf(a4.g())}));
                int j = a4.j();
                LinkedList linkedList = new LinkedList();
                LinkedList linkedList2 = new LinkedList();
                for (int i = 0; i < j; i++) {
                    Mimc.MIMCPacket a5 = a4.a(i);
                    if (this.b.A().contains(Long.valueOf(a5.h()))) {
                        MIMCLog.b(f11180a, String.format("RECV_PACKET, IGNORE, RECEIVED_AGAGIN, sequence:%d", new Object[]{Long.valueOf(a5.h())}));
                    } else {
                        this.b.h(a5.h());
                        if (a5.j() == Mimc.MIMC_MSG_TYPE.P2P_MESSAGE) {
                            Mimc.MIMCP2PMessage a6 = Mimc.MIMCP2PMessage.a(a5.m());
                            if (MIMCUtils.c(a6.d().i()) || this.b.n().equals(a6.d().i())) {
                                MIMCLog.b(f11180a, String.format("RECV_PACKET, PACKET, packet:%s", new Object[]{a5}));
                                linkedList.add(new MIMCMessage(a5.b(), a5.h(), a6.b().d(), a6.b().i(), a6.d().d(), a6.d().i(), a6.f().toByteArray(), a5.o(), a6.j()));
                            } else {
                                MIMCLog.c(f11180a, String.format("RECV_PACKET, PACKET, RESOURCE_NOT_MATCH, %s!=%s", new Object[]{this.b.n(), a6.d().i()}));
                            }
                        } else if (a5.j() == Mimc.MIMC_MSG_TYPE.P2T_MESSAGE) {
                            Mimc.MIMCP2TMessage a7 = Mimc.MIMCP2TMessage.a(a5.m());
                            String b3 = a5.b();
                            long h2 = a5.h();
                            String d = a7.b().d();
                            String i2 = a7.b().i();
                            long d2 = a7.d().d();
                            byte[] byteArray = a7.f().toByteArray();
                            long o = a5.o();
                            String j2 = a7.j();
                            MIMCGroupMessage mIMCGroupMessage = r11;
                            MIMCGroupMessage mIMCGroupMessage2 = new MIMCGroupMessage(b3, h2, d, i2, d2, byteArray, o, j2);
                            linkedList2.add(mIMCGroupMessage);
                        } else {
                            MIMCLog.c(f11180a, String.format("RECV_PACKET, INVALID_TYPE, TYPE:%s", new Object[]{a5.j()}));
                        }
                    }
                }
                if (linkedList.size() > 0) {
                    Collections.sort(linkedList);
                    MIMCLog.b(f11180a, String.format("handleMessage p2pMessages.size:%d", new Object[]{Integer.valueOf(linkedList.size())}));
                    this.b.r().a((List<MIMCMessage>) linkedList);
                }
                if (linkedList2.size() > 0) {
                    Collections.sort(linkedList2);
                    MIMCLog.b(f11180a, String.format("handleGroupMessage p2tMessages.size:%d", new Object[]{Integer.valueOf(linkedList2.size())}));
                    this.b.r().b((List<MIMCGroupMessage>) linkedList2);
                }
            }
        } catch (Exception e) {
            MIMCLog.c(f11180a, "Handle message exception:", e);
        }
    }

    private void a(Mimc.MIMCPacket mIMCPacket) throws InvalidProtocolBufferException {
        Mimc.UCPacket a2 = Mimc.UCPacket.a(mIMCPacket.m());
        if (a2 == null) {
            MIMCLog.c(f11180a, "HandleUCPacket ucPacket is null.");
            return;
        }
        switch (a2.d()) {
            case PONG:
                a(a2);
                return;
            case JOIN_RESP:
                b(a2);
                return;
            case QUIT_RESP:
                c(a2);
                return;
            case MESSAGE_LIST:
                d(a2);
                return;
            case QUERY_ONLINE_USERS_RESP:
                e(a2);
                return;
            case DISMISS:
                f(a2);
                return;
            default:
                return;
        }
    }

    private void a(Mimc.UCPacket uCPacket) throws InvalidProtocolBufferException {
        if (Mimc.UCPing.a(uCPacket.f()) == null) {
            MIMCLog.c(f11180a, "HandleUnlimitedGroups ucPing is null.");
        } else {
            MIMCLog.a(f11180a, "HandleUnlimitedGroups received uc pong packet.");
        }
    }

    private void b(Mimc.UCPacket uCPacket) throws InvalidProtocolBufferException {
        Mimc.UCJoinResp a2 = Mimc.UCJoinResp.a(uCPacket.f());
        if (a2 == null) {
            MIMCLog.c(f11180a, "HandleJoinUnlimitedGroup ucJoinResp is null.");
            return;
        }
        MIMCLog.a(f11180a, String.format("HandleJoinUnlimitedGroup topicId:%d code:%d message:%s", new Object[]{Long.valueOf(a2.b().d()), Integer.valueOf(a2.d()), a2.f()}));
        if (a2.d() == 0) {
            this.b.D().add(Long.valueOf(a2.b().d()));
        }
        if (this.b.v() != null) {
            this.b.v().a(a2.b().d(), a2.d(), a2.f(), this.b.ai());
        }
    }

    private void c(Mimc.UCPacket uCPacket) throws InvalidProtocolBufferException {
        Mimc.UCQuitResp a2 = Mimc.UCQuitResp.a(uCPacket.f());
        if (a2 == null) {
            MIMCLog.c(f11180a, "HandleQuitUnlimitedGroup ucQuitResp is null.");
            return;
        }
        MIMCLog.a(f11180a, String.format("handleQuitUnlimitedGroup topicId:%d code:%d message:%s", new Object[]{Long.valueOf(a2.b().d()), Integer.valueOf(a2.d()), a2.f()}));
        this.b.D().remove(Long.valueOf(a2.b().d()));
        if (this.b.v() != null) {
            this.b.v().b(a2.b().d(), a2.d(), a2.f(), this.b.aj());
        }
    }

    private void d(Mimc.UCPacket uCPacket) throws InvalidProtocolBufferException {
        Mimc.UCMessageList a2 = Mimc.UCMessageList.a(uCPacket.f());
        if (a2 == null) {
            MIMCLog.c(f11180a, "HandleUnlimitedGroupMessage ucMessageList is null.");
            return;
        }
        MIMCLog.b(f11180a, "Enter handleUnlimitedGroupMessage.");
        a(uCPacket, a2);
        ArrayList arrayList = new ArrayList();
        for (int i = 0; i < a2.e(); i++) {
            Mimc.UCMessage a3 = a2.a(i);
            if (a3 != null) {
                if (!this.b.B().add(Long.valueOf(a3.f()))) {
                    MIMCLog.b(f11180a, String.format("RECV_UCMessage, IGNORE, RECEIVED_AGAIN, sequence:%d", new Object[]{Long.valueOf(a3.f())}));
                } else {
                    MIMCLog.a(f11180a, String.format("RECV_UCMessage, RECEIVED, sequence:%d, packetId:%s, user:%s/%s|%d", new Object[]{Long.valueOf(a3.f()), a3.o(), this.b.q(), this.b.n(), Long.valueOf(this.b.j())}));
                    arrayList.add(new MIMCGroupMessage(a3.o(), a3.f(), a3.j().d(), a3.j().i(), a3.b().d(), a3.d().toByteArray(), a3.m(), a3.r()));
                }
            }
        }
        if (!arrayList.isEmpty()) {
            this.b.r().c(arrayList);
        }
    }

    private void e(Mimc.UCPacket uCPacket) throws InvalidProtocolBufferException {
        if (Mimc.UCQueryOnlineUsersResp.a(uCPacket.f()) == null) {
            MIMCLog.c(f11180a, "HandleQueryUnlimitedGroupOnlineUsers ucQueryOnlineUsersResp is null.");
        }
    }

    private void f(Mimc.UCPacket uCPacket) throws InvalidProtocolBufferException {
        Mimc.UCDismiss a2 = Mimc.UCDismiss.a(uCPacket.f());
        if (a2 == null) {
            MIMCLog.c(f11180a, "HandleDismissUnlimitedGroup ucDismiss is null.");
            return;
        }
        MIMCLog.a(f11180a, String.format("handleDismissUnlimitedGroup topicId:%d", new Object[]{Long.valueOf(a2.b().d())}));
        long d = a2.b().d();
        this.b.D().remove(Long.valueOf(d));
        if (this.b.v() != null) {
            this.b.v().a(d);
        }
    }

    private void a(Mimc.UCPacket uCPacket, Mimc.UCMessageList uCMessageList) {
        Mimc.UCSequenceAck.Builder e = Mimc.UCSequenceAck.e();
        Mimc.UCPacket.Builder a2 = Mimc.UCPacket.j().a(this.b.b()).a(Mimc.UC_MSG_TYPE.SEQ_ACK);
        String b2 = this.b.b();
        Mimc.MIMCPacket mIMCPacket = (Mimc.MIMCPacket) Mimc.MIMCPacket.p().a(b2).b(this.b.o()).a(Mimc.MIMC_MSG_TYPE.UC_PACKET).c(ByteString.copyFrom(((Mimc.UCPacket) a2.a((Mimc.MIMCUser) Mimc.MIMCUser.l().a(this.b.k()).a(uCPacket.b().d()).b(uCPacket.b().g()).b(uCPacket.b().i()).Z()).a(ByteString.copyFrom(((Mimc.UCSequenceAck) e.a((Mimc.UCGroup) Mimc.UCGroup.e().a(uCPacket.b().b()).b(uCMessageList.b().d()).Z()).a(uCMessageList.g()).Z()).K())).Z()).K())).Z();
        this.b.b(b2, mIMCPacket.K(), MIMCConstant.Z);
        MIMCLog.b(f11180a, String.format("SendUnlimitedGroupSequenceAck push packet. packetLen:%d", new Object[]{Integer.valueOf(mIMCPacket.K().length)}));
    }

    private void a(RtsSignal.RTSResult rTSResult, long j) {
        RtsSignal.UpdateResponse.Builder c = RtsSignal.UpdateResponse.c();
        c.a(rTSResult);
        RtsSignal.RTSMessage.Builder q = RtsSignal.RTSMessage.q();
        q.a(RtsSignal.RTSMessageType.UPDATE_RESPONSE);
        q.a(j);
        q.a(RtsSignal.CallType.SINGLE_CALL);
        q.b(this.b.j());
        q.a(this.b.n());
        q.b(((RtsSignal.UpdateResponse) c.Z()).J());
        q.c(this.b.n(j));
        Mimc.MIMCPacket.Builder p = Mimc.MIMCPacket.p();
        p.a(this.b.b());
        p.b(this.b.o());
        p.a(Mimc.MIMC_MSG_TYPE.RTS_SIGNAL);
        p.c(((RtsSignal.RTSMessage) q.Z()).J());
        this.b.b(p.b(), ((Mimc.MIMCPacket) p.Z()).K(), MIMCConstant.Z);
    }

    private void a(long j, RtsSignal.RTSResult rTSResult) {
        RtsSignal.ByeResponse.Builder f = RtsSignal.ByeResponse.f();
        f.a(rTSResult);
        RtsSignal.RTSMessage.Builder q = RtsSignal.RTSMessage.q();
        q.a(RtsSignal.RTSMessageType.BYE_RESPONSE);
        q.a(j);
        q.a(RtsSignal.CallType.SINGLE_CALL);
        q.b(this.b.j());
        q.a(this.b.n());
        q.b(((RtsSignal.ByeResponse) f.Z()).J());
        q.c(this.b.n(j));
        Mimc.MIMCPacket.Builder p = Mimc.MIMCPacket.p();
        p.a(this.b.b());
        p.b(this.b.o());
        p.a(Mimc.MIMC_MSG_TYPE.RTS_SIGNAL);
        p.c(((RtsSignal.RTSMessage) q.Z()).J());
        this.b.b(p.b(), ((Mimc.MIMCPacket) p.Z()).K(), MIMCConstant.Z);
        MIMCLog.b(f11180a, String.format("PUSH_PACKET BYE_RESPONSE, dataLen:%d", new Object[]{Integer.valueOf(((Mimc.MIMCPacket) p.Z()).K().length)}));
    }

    private RtsSignal.UserInfo a(long j, String str, List<RtsSignal.UserInfo> list) {
        for (RtsSignal.UserInfo next : list) {
            if (next.b() == j && str.equals(next.d())) {
                MIMCLog.a(f11180a, "Get fromUser from members");
                return next;
            }
        }
        return null;
    }

    private void b(Mimc.MIMCPacket mIMCPacket) throws InvalidProtocolBufferException {
        boolean z;
        RtsSignal.RTSMessage a2 = RtsSignal.RTSMessage.a(mIMCPacket.m());
        if (!a2.c() || !a2.a() || !a2.e() || !a2.m()) {
            MIMCLog.c(f11180a, "RECV_PACKET, RTS_MESSAGE PARAMS IS ILLEGAL");
            return;
        }
        long d = a2.d();
        MIMCLog.b(f11180a, String.format("RECV_PACKET, RTS_SIGNAL, CALL_ID:%d, uuid:%d", new Object[]{Long.valueOf(d), Long.valueOf(this.b.j())}));
        switch (a2.b()) {
            case CREATE_CHANNEL_RESPONSE:
                a(a2);
                break;
            case JOIN_CHANNEL_RESPONSE:
                b(a2);
                break;
            case LEAVE_CHANNEL_RESPONSE:
                c(a2);
                break;
            case USER_JOIN_NOTIFICATION:
                d(a2);
                break;
            case USER_LEAVE_NOTIFICATION:
                e(a2);
                break;
        }
        if (a2.b() != RtsSignal.RTSMessageType.INVITE_REQUEST) {
            long j = d;
            if (a2.b() == RtsSignal.RTSMessageType.CREATE_RESPONSE) {
                if (!this.b.x().containsKey(Long.valueOf(j))) {
                    MIMCLog.c(f11180a, String.format("RECV_CREATE_RESPONSE NOT IN CURRENT_CALL, callId:%d", new Object[]{Long.valueOf(j)}));
                    return;
                }
                RtsSignal.CreateResponse a3 = RtsSignal.CreateResponse.a(a2.n());
                if (!a3.a() || !a3.c() || a3.i() == null || a3.i().size() == 0) {
                    MIMCLog.c(f11180a, "RECV_CREATE_RESPONSE, PARAMS IS ERROR");
                    this.b.u().a(j, false, "PARAM IS NOT MATCH");
                    this.b.x().remove(Long.valueOf(j));
                } else if (a2.f() != RtsSignal.CallType.SINGLE_CALL || a3.i().size() == 2) {
                    MIMCLog.b(f11180a, String.format("RECV_CREATE_RESPONSE, RTS_RESULT:%s", new Object[]{a3.b()}));
                    if (a3.b() == RtsSignal.RTSResult.SUCC) {
                        MIMCLog.b(f11180a, String.format("MAKE ACCEPTED:%b", new Object[]{true}));
                        z = true;
                    } else {
                        z = false;
                    }
                    if (z) {
                        P2PCallSession p2PCallSession = (P2PCallSession) this.b.x().get(Long.valueOf(j));
                        if (p2PCallSession == null) {
                            MIMCLog.c(f11180a, String.format("callId=%d is not exist", new Object[]{Long.valueOf(j)}));
                            return;
                        }
                        RtsSignal.UserInfo a4 = a(a2.h(), a2.j(), a3.i());
                        p2PCallSession.a(P2PCallSession.CallState.RUNNING);
                        p2PCallSession.d(System.currentTimeMillis());
                        p2PCallSession.a(a4);
                        MIMCLog.a(f11180a, String.format("uuid:%d, update currentCalls callId:%d, callSession.state:%s", new Object[]{Long.valueOf(this.b.j()), Long.valueOf(j), P2PCallSession.CallState.RUNNING}));
                        BurrowProcessor burrowProcessor = new BurrowProcessor(this.b, j);
                        burrowProcessor.setDaemon(true);
                        burrowProcessor.start();
                    } else {
                        this.b.x().remove(Long.valueOf(j));
                        RTSUtils.c(this.b);
                    }
                    this.b.u().a(j, z, a3.d());
                } else {
                    MIMCLog.c(f11180a, "RECV_CREATE_RESPONSE, SINGLE_CALL, THE SIZE OF MEMBERS IS ERROR");
                    this.b.u().a(j, false, "PARAM IS NOT MATCH");
                    this.b.x().remove(Long.valueOf(j));
                }
            } else if (a2.b() == RtsSignal.RTSMessageType.BYE_RESPONSE) {
                if (!this.b.x().containsKey(Long.valueOf(j))) {
                    MIMCLog.c(f11180a, String.format("RECV_BYE_RESPONSE NOT IN CURRENT_CALLS, callId:%d", new Object[]{Long.valueOf(j)}));
                    return;
                }
                RtsSignal.ByeResponse a5 = RtsSignal.ByeResponse.a(a2.n());
                if (!a5.a() || a5.b() != RtsSignal.RTSResult.SUCC) {
                    MIMCLog.c(f11180a, "RECV_BYE_RESPONSE, PARAMS IS ERROR");
                }
                MIMCLog.b(f11180a, String.format("RECV_BYE_RESPONSE, BYE_RESPONSE:%s, uuid:%d, resource:%s", new Object[]{a5, Long.valueOf(this.b.j()), this.b.n()}));
                this.b.u().a(j, a5.b().name());
                RTSUtils.a(j, this.b);
                this.b.x().remove(Long.valueOf(j));
                MIMCLog.a(f11180a, String.format("in recv_byeResponse, currentCalls.remove callId:%d", new Object[]{Long.valueOf(j)}));
                RTSUtils.c(this.b);
            } else if (a2.b() == RtsSignal.RTSMessageType.BYE_REQUEST) {
                if (!this.b.x().containsKey(Long.valueOf(j))) {
                    MIMCLog.c(f11180a, String.format("RECV_BYE_REQUEST NOT IN CURRENT_CALLS, callId:%d， currentUser:%d", new Object[]{Long.valueOf(j), Long.valueOf(this.b.j())}));
                    return;
                }
                RtsSignal.ByeRequest a6 = RtsSignal.ByeRequest.a(a2.n());
                MIMCLog.b(f11180a, String.format("RECV_BYE_REQUEST, BYE_REQUEST:%s, uuid:%d, resource:%s", new Object[]{a6, Long.valueOf(this.b.j()), this.b.n()}));
                a(j, RtsSignal.RTSResult.SUCC);
                OnLaunchedProcessor m = ((P2PCallSession) this.b.x().get(Long.valueOf(j))).m();
                if (m != null && m.isAlive()) {
                    m.interrupt();
                }
                this.b.u().a(j, a6.b());
                RTSUtils.a(j, this.b);
                this.b.x().remove(Long.valueOf(j));
                MIMCLog.a(f11180a, String.format("in recv_byeRequest, currentCalls.remove callId:%d", new Object[]{Long.valueOf(j)}));
                RTSUtils.c(this.b);
            } else if (a2.b() == RtsSignal.RTSMessageType.UPDATE_REQUEST) {
                if (!this.b.x().containsKey(Long.valueOf(j))) {
                    MIMCLog.c(f11180a, String.format("RECV_UPDATE_REQUEST NOT IN CURRENT_CALLS, callId:%d", new Object[]{Long.valueOf(j)}));
                    return;
                }
                RtsSignal.UpdateRequest a7 = RtsSignal.UpdateRequest.a(a2.n());
                if (!a7.a()) {
                    MIMCLog.c(f11180a, "RECV_PACKET, UPDATE_REQUEST, PARAM IS ERROR");
                    a(RtsSignal.RTSResult.PARAMETER_ERROR, j);
                    return;
                }
                RtsSignal.UserInfo b2 = a7.b();
                if (!b2.q() || !b2.t() || !b2.l() || !b2.o()) {
                    MIMCLog.c(f11180a, "RECV_PACKET, UPDATE_REQUEST, PARAM IS ERROR");
                    a(RtsSignal.RTSResult.PARAMETER_ERROR, j);
                    return;
                }
                MIMCLog.b(f11180a, String.format("RECV_PACKET, UPDATE_REQUEST, updateRequest:%s", new Object[]{a7}));
                P2PCallSession p2PCallSession2 = (P2PCallSession) this.b.x().get(Long.valueOf(j));
                if (p2PCallSession2 == null) {
                    MIMCLog.c(f11180a, String.format("callId=%d is not exist", new Object[]{Long.valueOf(j)}));
                    return;
                }
                p2PCallSession2.a(b2);
                RTSUtils.a(j, this.b);
                a(RtsSignal.RTSResult.SUCC, j);
                MIMCLog.b(f11180a, "SEND_PACKET UPDATE_RESPONSE, RtsSignal.RTSResult.OK");
                BurrowProcessor burrowProcessor2 = new BurrowProcessor(this.b, j);
                burrowProcessor2.setDaemon(true);
                burrowProcessor2.start();
            } else if (a2.b() != RtsSignal.RTSMessageType.UPDATE_RESPONSE) {
            } else {
                if (!this.b.x().containsKey(Long.valueOf(j))) {
                    MIMCLog.c(f11180a, String.format("RECV_UPDATE_RESPONSE NOT IN CURRENT_CALLS, callId:%d", new Object[]{Long.valueOf(j)}));
                    return;
                }
                RtsSignal.UpdateResponse a8 = RtsSignal.UpdateResponse.a(a2.n());
                if (!a8.a() || a8.b() != RtsSignal.RTSResult.SUCC) {
                    MIMCLog.c(f11180a, "RECV_PACKET UPDATE_RESPONSE, PARAM IS ERROR");
                }
                MIMCLog.b(f11180a, String.format("RECV_PACKET UPDATE_RESPONSE, updateResponse:%s", new Object[]{a8}));
                ((P2PCallSession) this.b.x().get(Long.valueOf(j))).a(P2PCallSession.CallState.RUNNING).d(System.currentTimeMillis());
            }
        } else if (this.b.x().size() >= this.b.ag()) {
            MIMCLog.c(f11180a, String.format("RECV_INVITE_REQUEST, Busying, uuid:%d, account:%s, resource:%s, callsCnt:%d", new Object[]{Long.valueOf(this.b.j()), this.b.q(), this.b.n(), Integer.valueOf(this.b.x().size())}));
            RTSUtils.a(this.b, RtsSignal.RTSResult.PEER_REFUSE, "USER_BUSY", d, -1);
        } else {
            RtsSignal.InviteRequest a9 = RtsSignal.InviteRequest.a(a2.n());
            List<RtsSignal.UserInfo> c = a9.c();
            if (!a9.a() || c == null || c.size() == 0) {
                MIMCLog.c(f11180a, "RECV_INVITE_REQUEST, PARAMS IS ERROR");
                RTSUtils.a(this.b, RtsSignal.RTSResult.PARAMETER_ERROR, "INVALID_PARAM", d, -1);
                return;
            }
            MIMCLog.b(f11180a, String.format("RECV_INVITE_REQUEST, STAREAM_TYPE:%s, uuid:%d, resource:%s", new Object[]{a9.b(), Long.valueOf(this.b.j()), this.b.n()}));
            RtsSignal.UserInfo a10 = a(a2.h(), a2.j(), c);
            if (a10 == null) {
                MIMCLog.c(f11180a, "RECV_INVITE_REQUEST, CAN NOT FIND FROM");
                RTSUtils.a(this.b, RtsSignal.RTSResult.PARAMETER_ERROR, "MEMBERS_NOT_CONTAIN_SENDER", d, -1);
                return;
            }
            MIMCLog.b(f11180a, String.format("RECV_INVITE_REQUEST, FROM, from:%s", new Object[]{a10}));
            if (this.b.L() == MIMCUser.RelayState.NOT_CREATED) {
                MIMCLog.b(f11180a, "IN INVITE_REQUEST RelayState.NOT_CREATED");
                RTSUtils.a(this.b);
                if (this.b.x().size() >= this.b.ag()) {
                    MIMCLog.c(f11180a, "RECV_INVITE_REQUEST, Busying");
                    RTSUtils.a(this.b, RtsSignal.RTSResult.PEER_REFUSE, "USER_BUSY", d, -1);
                    return;
                }
                ConcurrentMap<Long, P2PCallSession> x = this.b.x();
                Long valueOf = Long.valueOf(d);
                RtsSignal.CallType f = a2.f();
                P2PCallSession.CallState callState = P2PCallSession.CallState.WAIT_CALL_ON_LAUNCHED;
                long currentTimeMillis = System.currentTimeMillis();
                byte[] byteArray = a9.j().toByteArray();
                long p = a2.p();
                P2PCallSession p2PCallSession3 = r3;
                P2PCallSession p2PCallSession4 = new P2PCallSession(d, a10, f, callState, currentTimeMillis, false, byteArray, p);
                x.put(valueOf, p2PCallSession3);
                MIMCLog.b(f11180a, String.format("in recv_inviteRequest mimcUser.getRtsCalls().put callId:%d, callState:%s", new Object[]{Long.valueOf(d), P2PCallSession.CallState.WAIT_CALL_ON_LAUNCHED}));
            } else {
                long j2 = d;
                if (this.b.L() == MIMCUser.RelayState.BEING_CREATED) {
                    MIMCLog.b(f11180a, "IN INVITE_REQUEST RelayState.BEING_CREATED");
                    if (this.b.x().size() >= this.b.ag()) {
                        MIMCLog.c(f11180a, "RECV_INVITE_REQUEST, Busying");
                        RTSUtils.a(this.b, RtsSignal.RTSResult.PEER_REFUSE, "USER_BUSY", j2, -1);
                        return;
                    }
                    P2PCallSession p2PCallSession5 = r3;
                    P2PCallSession p2PCallSession6 = new P2PCallSession(j2, a10, a2.f(), P2PCallSession.CallState.WAIT_CALL_ON_LAUNCHED, System.currentTimeMillis(), false, a9.j().toByteArray(), a2.p());
                    this.b.x().put(Long.valueOf(j2), p2PCallSession5);
                } else if (this.b.L() == MIMCUser.RelayState.SUCC_CREATED) {
                    MIMCLog.b(f11180a, "IN INVITE_REQUEST RelayState.SUCC_CREATED");
                    if (this.b.x().size() >= this.b.ag()) {
                        MIMCLog.c(f11180a, "RECV_INVITE_REQUEST, Busying");
                        RTSUtils.a(this.b, RtsSignal.RTSResult.PEER_REFUSE, "USER_BUSY", j2, -1);
                        return;
                    }
                    ConcurrentMap<Long, P2PCallSession> x2 = this.b.x();
                    Long valueOf2 = Long.valueOf(j2);
                    RtsSignal.CallType f2 = a2.f();
                    P2PCallSession.CallState callState2 = P2PCallSession.CallState.WAIT_SEND_INVITE_RESPONSE;
                    long currentTimeMillis2 = System.currentTimeMillis();
                    byte[] byteArray2 = a9.j().toByteArray();
                    long p2 = a2.p();
                    P2PCallSession p2PCallSession7 = r3;
                    P2PCallSession p2PCallSession8 = new P2PCallSession(j2, a10, f2, callState2, currentTimeMillis2, false, byteArray2, p2);
                    x2.put(valueOf2, p2PCallSession7);
                    P2PCallSession p2PCallSession9 = (P2PCallSession) this.b.x().get(Long.valueOf(j2));
                    if (p2PCallSession9.m() == null) {
                        OnLaunchedProcessor onLaunchedProcessor = new OnLaunchedProcessor(this.b, j2);
                        p2PCallSession9.a(onLaunchedProcessor);
                        onLaunchedProcessor.setDaemon(true);
                        onLaunchedProcessor.start();
                        return;
                    }
                    return;
                } else {
                    return;
                }
            }
        }
    }

    private void a(RtsSignal.RTSMessage rTSMessage) throws InvalidProtocolBufferException {
        RtsSignal.CreateChannelResponse a2 = RtsSignal.CreateChannelResponse.a(rTSMessage.n());
        long m = a2.m();
        long g = a2.g();
        String i = a2.i();
        if (a2.b() != RtsSignal.RTSResult.SUCC) {
            MIMCLog.c(f11180a, String.format("RECV_PACKET RECEIVE_CREATE_CHANNEL_RESPONSE, PARAMS IS ERROR. account:%s resource:%s callId:%d callKey:%s", new Object[]{this.b.q(), this.b.n(), Long.valueOf(g), i}));
            this.b.z().remove(Long.valueOf(m));
            this.b.g().a(m, g, i, false, a2.d(), a2.o().toByteArray());
            return;
        }
        RtsSignal.UserInfo q = a2.q();
        byte[] byteArray = a2.o().toByteArray();
        ConcurrentMap<Long, ChannelSession> y = this.b.y();
        Long valueOf = Long.valueOf(g);
        RtsSignal.CreateChannelResponse createChannelResponse = a2;
        ChannelSession channelSession = r5;
        long j = g;
        ChannelSession channelSession2 = new ChannelSession(RtsSignal.CallType.CHANNEL_CALL, g, i, q, -1, byteArray);
        y.put(valueOf, channelSession);
        MIMCLog.b(f11180a, String.format("RECEIVE_PACKET RECEIVE_CREATE_CHANNEL_RESPONSE, account:%s resource:%s identity:%d callId:%d callKey:%s result:%s desc:%s userInfo:%s", new Object[]{this.b.q(), this.b.n(), Long.valueOf(m), Long.valueOf(j), i, createChannelResponse.b(), createChannelResponse.d(), q}));
        this.b.z().remove(Long.valueOf(m));
        this.b.g().a(m, j, i, true, createChannelResponse.d(), createChannelResponse.o().toByteArray());
    }

    private void b(RtsSignal.RTSMessage rTSMessage) throws InvalidProtocolBufferException {
        RtsSignal.JoinChannelResponse a2 = RtsSignal.JoinChannelResponse.a(rTSMessage.n());
        long g = a2.g();
        if (a2.b() == RtsSignal.RTSResult.PARAMETER_ERROR) {
            MIMCLog.c(f11180a, String.format("RECV_PACKET RECEIVE_JOIN_CHANNEL_RESPONSE, PARAMS IS ERROR. account:%s resource:%s callId:%d, desc:%s", new Object[]{this.b.q(), this.b.n(), Long.valueOf(g), a2.d()}));
            this.b.y().remove(Long.valueOf(g));
            this.b.g().a(g, this.b.q(), this.b.n(), false, a2.d(), (byte[]) null, (List<ChannelUser>) null);
        } else if (a2.b() == RtsSignal.RTSResult.SUCC || a2.b() == RtsSignal.RTSResult.ALREADY_IN_SESSION) {
            ChannelSession channelSession = (ChannelSession) this.b.y().get(Long.valueOf(g));
            if (channelSession != null) {
                MIMCLog.b(f11180a, String.format("RECEIVE_PACKET RECEIVE_JOIN_CHANNEL_RESPONSE, account:%s resource:%s callId:%d callKey:%s result:%s desc:%s userInfoList.size:%d extra:%s", new Object[]{this.b.q(), this.b.n(), Long.valueOf(g), channelSession.b(), a2.b(), a2.d(), Integer.valueOf(a2.j().size()), a2.i()}));
                channelSession.a();
                channelSession.a(a2.j());
                channelSession.a(a2.i().toByteArray());
                channelSession.d(-1);
                ArrayList arrayList = new ArrayList();
                for (RtsSignal.UserInfo next : channelSession.d()) {
                    arrayList.add(new ChannelUser(next.i(), next.d()));
                }
                this.b.g().a(g, this.b.q(), this.b.n(), true, a2.d(), channelSession.c(), arrayList);
                return;
            }
            MIMCLog.c(f11180a, String.format("RECV_PACKET RECEIVE_JOIN_CHANNEL_RESPONSE, account:%s resource:%s this callId:%d is not exist from response.", new Object[]{this.b.q(), this.b.n(), Long.valueOf(g)}));
        }
    }

    private void c(RtsSignal.RTSMessage rTSMessage) throws InvalidProtocolBufferException {
        RtsSignal.LeaveChannelResponse a2 = RtsSignal.LeaveChannelResponse.a(rTSMessage.n());
        long g = a2.g();
        if (a2.b() == RtsSignal.RTSResult.PARAMETER_ERROR) {
            MIMCLog.c(f11180a, String.format("RECV_PACKET RECEIVE_LEAVE_CHANNEL_RESPONSE, PARAMS IS ERROR. account:%s resource:%s callId:%d desc:%s", new Object[]{this.b.q(), this.b.n(), Long.valueOf(g), a2.d()}));
            this.b.g().a(g, this.b.q(), this.b.n(), false, a2.d());
        } else if (a2.b() == RtsSignal.RTSResult.SUCC) {
            MIMCLog.c(f11180a, String.format("RECV_PACKET RECEIVE_LEAVE_CHANNEL_RESPONSE SUCC account:%s, resource:%s, callId:%d desc:%s", new Object[]{this.b.q(), this.b.n(), Long.valueOf(g), a2.d()}));
        }
    }

    private void d(RtsSignal.RTSMessage rTSMessage) throws InvalidProtocolBufferException {
        RtsSignal.UserJoinNotification a2 = RtsSignal.UserJoinNotification.a(rTSMessage.n());
        long b2 = a2.b();
        String d = a2.d();
        RtsSignal.UserInfo g = a2.g();
        MIMCLog.b(f11180a, String.format("RECV_PACKET RECEIVE_USER_JOIN_NOTIFICATION, new user coming account:%s resource:%s callId:%d callKey:%s", new Object[]{g.i(), g.d(), Long.valueOf(b2), d}));
        if (this.b.y().containsKey(Long.valueOf(b2))) {
            ((ChannelSession) this.b.y().get(Long.valueOf(b2))).a(g);
            this.b.g().a(b2, g.i(), g.d());
        }
    }

    private void e(RtsSignal.RTSMessage rTSMessage) throws InvalidProtocolBufferException {
        RtsSignal.UserLeaveNotification a2 = RtsSignal.UserLeaveNotification.a(rTSMessage.n());
        long b2 = a2.b();
        String d = a2.d();
        RtsSignal.UserInfo g = a2.g();
        MIMCLog.b(f11180a, String.format("RECV_PACKET RECEIVE_USER_LEAVE_NOTIFICATION, this usr left account:%s resource:%s callId:%d callKey:%s", new Object[]{g.i(), g.d(), Long.valueOf(b2), d}));
        if (this.b.y().containsKey(Long.valueOf(b2))) {
            ((ChannelSession) this.b.y().get(Long.valueOf(b2))).b(g);
            this.b.g().b(b2, g.i(), g.d());
        }
    }
}
