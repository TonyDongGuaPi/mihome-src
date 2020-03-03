package com.xiaomi.mimc.common;

import com.xiaomi.mimc.MIMCUser;
import com.xiaomi.mimc.common.MIMCConstant;
import com.xiaomi.mimc.data.MIMCContext;
import com.xiaomi.mimc.data.P2PCallSession;
import com.xiaomi.mimc.proto.Mimc;
import com.xiaomi.mimc.proto.RtsData;
import com.xiaomi.mimc.proto.RtsSignal;
import com.xiaomi.mimc.protobuf.ByteString;
import com.xiaomi.msg.XMDTransceiver;
import com.xiaomi.msg.data.XMDPacket;
import com.xiaomi.msg.logger.MIMCLog;
import java.net.InetSocketAddress;
import java.util.Map;

public class RTSUtils {

    /* renamed from: a  reason: collision with root package name */
    private static final String f11177a = "RTSUtils";
    /* access modifiers changed from: private */
    public static String b;
    /* access modifiers changed from: private */
    public static final Object c = new Object();

    /*  JADX ERROR: IndexOutOfBoundsException in pass: RegionMakerVisitor
        java.lang.IndexOutOfBoundsException: Index 0 out of bounds for length 0
        	at java.base/jdk.internal.util.Preconditions.outOfBounds(Preconditions.java:64)
        	at java.base/jdk.internal.util.Preconditions.outOfBoundsCheckIndex(Preconditions.java:70)
        	at java.base/jdk.internal.util.Preconditions.checkIndex(Preconditions.java:248)
        	at java.base/java.util.Objects.checkIndex(Objects.java:372)
        	at java.base/java.util.ArrayList.get(ArrayList.java:458)
        	at jadx.core.dex.nodes.InsnNode.getArg(InsnNode.java:101)
        	at jadx.core.dex.visitors.regions.RegionMaker.traverseMonitorExits(RegionMaker.java:611)
        	at jadx.core.dex.visitors.regions.RegionMaker.traverseMonitorExits(RegionMaker.java:619)
        	at jadx.core.dex.visitors.regions.RegionMaker.traverseMonitorExits(RegionMaker.java:619)
        	at jadx.core.dex.visitors.regions.RegionMaker.traverseMonitorExits(RegionMaker.java:619)
        	at jadx.core.dex.visitors.regions.RegionMaker.traverseMonitorExits(RegionMaker.java:619)
        	at jadx.core.dex.visitors.regions.RegionMaker.traverseMonitorExits(RegionMaker.java:619)
        	at jadx.core.dex.visitors.regions.RegionMaker.traverseMonitorExits(RegionMaker.java:619)
        	at jadx.core.dex.visitors.regions.RegionMaker.traverseMonitorExits(RegionMaker.java:619)
        	at jadx.core.dex.visitors.regions.RegionMaker.traverseMonitorExits(RegionMaker.java:619)
        	at jadx.core.dex.visitors.regions.RegionMaker.traverseMonitorExits(RegionMaker.java:619)
        	at jadx.core.dex.visitors.regions.RegionMaker.traverseMonitorExits(RegionMaker.java:619)
        	at jadx.core.dex.visitors.regions.RegionMaker.processMonitorEnter(RegionMaker.java:561)
        	at jadx.core.dex.visitors.regions.RegionMaker.traverse(RegionMaker.java:133)
        	at jadx.core.dex.visitors.regions.RegionMaker.makeRegion(RegionMaker.java:86)
        	at jadx.core.dex.visitors.regions.RegionMaker.processExcHandler(RegionMaker.java:1043)
        	at jadx.core.dex.visitors.regions.RegionMaker.processTryCatchBlocks(RegionMaker.java:975)
        	at jadx.core.dex.visitors.regions.RegionMakerVisitor.visit(RegionMakerVisitor.java:52)
        */
    /* JADX WARNING: Removed duplicated region for block: B:32:0x00af  */
    /* JADX WARNING: Removed duplicated region for block: B:56:0x013b  */
    /* JADX WARNING: Removed duplicated region for block: B:57:0x0161  */
    public static synchronized long a(com.xiaomi.mimc.MIMCUser r17) {
        /*
            r1 = r17
            java.lang.Class<com.xiaomi.mimc.common.RTSUtils> r2 = com.xiaomi.mimc.common.RTSUtils.class
            monitor-enter(r2)
            java.lang.String r3 = ""
            java.lang.String r0 = r17.S()     // Catch:{ all -> 0x0188 }
            boolean r4 = com.xiaomi.mimc.common.MIMCUtils.d(r0)     // Catch:{ all -> 0x0188 }
            if (r4 == 0) goto L_0x003b
            java.lang.String r0 = "RTSUtils"
            java.lang.String r4 = "Relay address is empty from memory."
            com.xiaomi.msg.logger.MIMCLog.c(r0, r4)     // Catch:{ all -> 0x0188 }
            java.lang.String r0 = r17.ac()     // Catch:{ all -> 0x0188 }
            java.lang.String r4 = r17.T()     // Catch:{ all -> 0x0188 }
            java.lang.String r5 = "mimcRelayAddress"
            java.lang.String r0 = com.xiaomi.mimc.common.MIMCUtils.a((java.lang.String) r0, (java.lang.String) r4, (java.lang.String) r5)     // Catch:{ all -> 0x0188 }
            boolean r4 = com.xiaomi.mimc.common.MIMCUtils.d(r0)     // Catch:{ all -> 0x0188 }
            if (r4 == 0) goto L_0x003b
            java.lang.String r4 = "RTSUtils"
            java.lang.String r5 = "Relay address is empty from local file."
            com.xiaomi.msg.logger.MIMCLog.c(r4, r5)     // Catch:{ all -> 0x0188 }
            com.xiaomi.mimc.processor.RelayAddressProcessor r4 = new com.xiaomi.mimc.processor.RelayAddressProcessor     // Catch:{ all -> 0x0188 }
            r4.<init>(r1)     // Catch:{ all -> 0x0188 }
            r4.start()     // Catch:{ all -> 0x0188 }
        L_0x003b:
            boolean r4 = com.xiaomi.mimc.common.MIMCUtils.d(r0)     // Catch:{ all -> 0x0188 }
            r5 = 2
            r6 = 1
            r7 = 0
            if (r4 != 0) goto L_0x00a6
            com.xiaomi.mimc.json.JSONArray r4 = new com.xiaomi.mimc.json.JSONArray     // Catch:{ JSONException -> 0x009c }
            r4.<init>((java.lang.String) r0)     // Catch:{ JSONException -> 0x009c }
            java.lang.String r0 = "RTSUtils"
            java.lang.String r8 = "Get relay address from memory or local file."
            com.xiaomi.msg.logger.MIMCLog.b(r0, r8)     // Catch:{ JSONException -> 0x009c }
            java.util.Random r0 = new java.util.Random     // Catch:{ JSONException -> 0x009c }
            long r8 = java.lang.System.currentTimeMillis()     // Catch:{ JSONException -> 0x009c }
            r0.<init>(r8)     // Catch:{ JSONException -> 0x009c }
            int r8 = r4.a()     // Catch:{ JSONException -> 0x009c }
            int r0 = r0.nextInt(r8)     // Catch:{ JSONException -> 0x009c }
            java.lang.String r0 = r4.l(r0)     // Catch:{ JSONException -> 0x009c }
            java.lang.String r4 = ":"
            java.lang.String[] r0 = r0.split(r4)     // Catch:{ JSONException -> 0x009c }
            r4 = r0[r7]     // Catch:{ JSONException -> 0x009c }
            r0 = r0[r6]     // Catch:{ JSONException -> 0x0099 }
            java.lang.Integer r0 = java.lang.Integer.valueOf(r0)     // Catch:{ JSONException -> 0x0099 }
            int r3 = r0.intValue()     // Catch:{ JSONException -> 0x0099 }
            java.lang.String r0 = "RTSUtils"
            java.lang.String r8 = "Random get relayIp:%s, relayPort:%d from local file."
            java.lang.Object[] r9 = new java.lang.Object[r5]     // Catch:{ JSONException -> 0x0092 }
            r9[r7] = r4     // Catch:{ JSONException -> 0x0092 }
            java.lang.Integer r10 = java.lang.Integer.valueOf(r3)     // Catch:{ JSONException -> 0x0092 }
            r9[r6] = r10     // Catch:{ JSONException -> 0x0092 }
            java.lang.String r8 = java.lang.String.format(r8, r9)     // Catch:{ JSONException -> 0x0092 }
            com.xiaomi.msg.logger.MIMCLog.b(r0, r8)     // Catch:{ JSONException -> 0x0092 }
            r16 = r4
            r4 = r3
            r3 = r16
            goto L_0x00a7
        L_0x0092:
            r0 = move-exception
            r16 = r4
            r4 = r3
            r3 = r16
            goto L_0x009e
        L_0x0099:
            r0 = move-exception
            r3 = r4
            goto L_0x009d
        L_0x009c:
            r0 = move-exception
        L_0x009d:
            r4 = 0
        L_0x009e:
            java.lang.String r8 = "RTSUtils"
            java.lang.String r9 = "CreateRelayConn exception:"
            com.xiaomi.msg.logger.MIMCLog.d(r8, r9, r0)     // Catch:{ all -> 0x0188 }
            goto L_0x00a7
        L_0x00a6:
            r4 = 0
        L_0x00a7:
            boolean r0 = com.xiaomi.mimc.common.MIMCUtils.c((java.lang.String) r3)     // Catch:{ all -> 0x0188 }
            r8 = -1
            if (r0 != 0) goto L_0x00b5
            if (r4 != 0) goto L_0x00b2
            goto L_0x00b5
        L_0x00b2:
            r11 = r3
            r12 = r4
            goto L_0x00e9
        L_0x00b5:
            java.lang.Object r3 = c     // Catch:{ all -> 0x0188 }
            monitor-enter(r3)     // Catch:{ all -> 0x0188 }
            java.lang.Thread r0 = new java.lang.Thread     // Catch:{ all -> 0x0185 }
            com.xiaomi.mimc.common.RTSUtils$1 r4 = new com.xiaomi.mimc.common.RTSUtils$1     // Catch:{ all -> 0x0185 }
            r4.<init>(r1)     // Catch:{ all -> 0x0185 }
            r0.<init>(r4)     // Catch:{ all -> 0x0185 }
            r0.start()     // Catch:{ all -> 0x0185 }
            java.lang.String r0 = ""
            b = r0     // Catch:{ IllegalMonitorStateException | InterruptedException -> 0x00cf }
            java.lang.Object r0 = c     // Catch:{ IllegalMonitorStateException | InterruptedException -> 0x00cf }
            r0.wait()     // Catch:{ IllegalMonitorStateException | InterruptedException -> 0x00cf }
            goto L_0x00d7
        L_0x00cf:
            r0 = move-exception
            java.lang.String r4 = "RTSUtils"
            java.lang.String r10 = "createRelayConn wait exception:"
            com.xiaomi.msg.logger.MIMCLog.d(r4, r10, r0)     // Catch:{ all -> 0x0185 }
        L_0x00d7:
            monitor-exit(r3)     // Catch:{ all -> 0x0185 }
            java.lang.String r0 = b     // Catch:{ all -> 0x0188 }
            boolean r0 = com.xiaomi.mimc.common.MIMCUtils.c((java.lang.String) r0)     // Catch:{ all -> 0x0188 }
            if (r0 == 0) goto L_0x00e2
            monitor-exit(r2)
            return r8
        L_0x00e2:
            java.lang.String r3 = b     // Catch:{ all -> 0x0188 }
            r4 = 80
            r11 = r3
            r12 = 80
        L_0x00e9:
            com.xiaomi.mimc.proto.RtsData$UserPacket$Builder r0 = com.xiaomi.mimc.proto.RtsData.UserPacket.t()     // Catch:{ all -> 0x0188 }
            long r3 = r17.j()     // Catch:{ all -> 0x0188 }
            r0.a((long) r3)     // Catch:{ all -> 0x0188 }
            java.lang.String r3 = r17.n()     // Catch:{ all -> 0x0188 }
            r0.a((java.lang.String) r3)     // Catch:{ all -> 0x0188 }
            com.xiaomi.mimc.proto.RtsData$PKT_TYPE r3 = com.xiaomi.mimc.proto.RtsData.PKT_TYPE.RELAY_CONN_REQUEST     // Catch:{ all -> 0x0188 }
            r0.a((com.xiaomi.mimc.proto.RtsData.PKT_TYPE) r3)     // Catch:{ all -> 0x0188 }
            java.lang.String r3 = "RTSUtils"
            java.lang.String r4 = "createConnection relayId:%s relayPort:%d"
            java.lang.Object[] r10 = new java.lang.Object[r5]     // Catch:{ all -> 0x0188 }
            r10[r7] = r11     // Catch:{ all -> 0x0188 }
            java.lang.Integer r13 = java.lang.Integer.valueOf(r12)     // Catch:{ all -> 0x0188 }
            r10[r6] = r13     // Catch:{ all -> 0x0188 }
            java.lang.String r4 = java.lang.String.format(r4, r10)     // Catch:{ all -> 0x0188 }
            com.xiaomi.msg.logger.MIMCLog.a((java.lang.String) r3, (java.lang.String) r4)     // Catch:{ all -> 0x0188 }
            com.xiaomi.msg.XMDTransceiver r10 = r17.C()     // Catch:{ all -> 0x0188 }
            com.xiaomi.mimc.protobuf.GeneratedMessageLite r0 = r0.Z()     // Catch:{ all -> 0x0188 }
            com.xiaomi.mimc.proto.RtsData$UserPacket r0 = (com.xiaomi.mimc.proto.RtsData.UserPacket) r0     // Catch:{ all -> 0x0188 }
            byte[] r13 = r0.K()     // Catch:{ all -> 0x0188 }
            r14 = 5
            com.xiaomi.mimc.data.ConnectionContent r15 = new com.xiaomi.mimc.data.ConnectionContent     // Catch:{ all -> 0x0188 }
            long r3 = r17.j()     // Catch:{ all -> 0x0188 }
            java.lang.String r0 = r17.n()     // Catch:{ all -> 0x0188 }
            com.xiaomi.mimc.data.ConnectionContent$ConnType r5 = com.xiaomi.mimc.data.ConnectionContent.ConnType.RELAY_CONN     // Catch:{ all -> 0x0188 }
            r15.<init>(r3, r0, r5)     // Catch:{ all -> 0x0188 }
            long r3 = r10.a(r11, r12, r13, r14, r15)     // Catch:{ all -> 0x0188 }
            int r0 = (r3 > r8 ? 1 : (r3 == r8 ? 0 : -1))
            if (r0 != 0) goto L_0x0161
            java.lang.String r0 = "RTSUtils"
            java.lang.String r5 = "createConnection failed, uuid:%d, resource:%s, connId:%d"
            r8 = 3
            java.lang.Object[] r8 = new java.lang.Object[r8]     // Catch:{ all -> 0x0188 }
            long r9 = r17.j()     // Catch:{ all -> 0x0188 }
            java.lang.Long r9 = java.lang.Long.valueOf(r9)     // Catch:{ all -> 0x0188 }
            r8[r7] = r9     // Catch:{ all -> 0x0188 }
            java.lang.String r1 = r17.n()     // Catch:{ all -> 0x0188 }
            r8[r6] = r1     // Catch:{ all -> 0x0188 }
            java.lang.Long r1 = java.lang.Long.valueOf(r3)     // Catch:{ all -> 0x0188 }
            r6 = 2
            r8[r6] = r1     // Catch:{ all -> 0x0188 }
            java.lang.String r1 = java.lang.String.format(r5, r8)     // Catch:{ all -> 0x0188 }
            com.xiaomi.msg.logger.MIMCLog.c(r0, r1)     // Catch:{ all -> 0x0188 }
            goto L_0x0183
        L_0x0161:
            r1.i((long) r3)     // Catch:{ all -> 0x0188 }
            com.xiaomi.mimc.MIMCUser$RelayState r0 = com.xiaomi.mimc.MIMCUser.RelayState.BEING_CREATED     // Catch:{ all -> 0x0188 }
            r1.a((com.xiaomi.mimc.MIMCUser.RelayState) r0)     // Catch:{ all -> 0x0188 }
            long r8 = java.lang.System.currentTimeMillis()     // Catch:{ all -> 0x0188 }
            r1.j((long) r8)     // Catch:{ all -> 0x0188 }
            java.lang.String r0 = "RTSUtils"
            java.lang.String r1 = "MIMC connId:%d SEND_CREATE_RELAY_CONN_REQUEST_SUCCESS send create connection request with relay success."
            java.lang.Object[] r5 = new java.lang.Object[r6]     // Catch:{ all -> 0x0188 }
            java.lang.Long r6 = java.lang.Long.valueOf(r3)     // Catch:{ all -> 0x0188 }
            r5[r7] = r6     // Catch:{ all -> 0x0188 }
            java.lang.String r1 = java.lang.String.format(r1, r5)     // Catch:{ all -> 0x0188 }
            com.xiaomi.msg.logger.MIMCLog.b(r0, r1)     // Catch:{ all -> 0x0188 }
        L_0x0183:
            monitor-exit(r2)
            return r3
        L_0x0185:
            r0 = move-exception
            monitor-exit(r3)     // Catch:{ all -> 0x0185 }
            throw r0     // Catch:{ all -> 0x0188 }
        L_0x0188:
            r0 = move-exception
            monitor-exit(r2)
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.xiaomi.mimc.common.RTSUtils.a(com.xiaomi.mimc.MIMCUser):long");
    }

    public static boolean b(MIMCUser mIMCUser) {
        if (mIMCUser.K() != null) {
            MIMCLog.d(f11177a, "SEND_STREAM BIND_RELAY_REQUEST SUCCESS mimcUser.getBindRelayResponse() != null.");
            return true;
        }
        RtsData.BindRelayRequest.Builder s = RtsData.BindRelayRequest.s();
        s.a(mIMCUser.j());
        s.a(mIMCUser.n());
        InetSocketAddress a2 = mIMCUser.C().a();
        if (a2 != null) {
            s.b(a2.getAddress().getHostAddress());
            s.a(a2.getPort());
        }
        s.c(mIMCUser.m());
        RtsData.StreamConfig.Builder i = RtsData.StreamConfig.i();
        if (mIMCUser.am().a() == 0) {
            i.a(RtsData.STREAM_STRATEGY.FEC_STRATEGY);
        } else if (mIMCUser.am().a() == 1) {
            i.a(RtsData.STREAM_STRATEGY.ACK_STRATEGY);
        }
        i.a(mIMCUser.am().b());
        i.a(mIMCUser.am().c());
        s.a((RtsData.StreamConfig) i.Z());
        RtsData.StreamConfig.Builder i2 = RtsData.StreamConfig.i();
        if (mIMCUser.an().a() == 0) {
            i2.a(RtsData.STREAM_STRATEGY.FEC_STRATEGY);
        } else if (mIMCUser.an().a() == 1) {
            i2.a(RtsData.STREAM_STRATEGY.ACK_STRATEGY);
        }
        i2.a(mIMCUser.an().b());
        i2.a(mIMCUser.an().c());
        s.c((RtsData.StreamConfig) i2.Z());
        RtsData.UserPacket.Builder t = RtsData.UserPacket.t();
        t.a(RtsData.PKT_TYPE.BIND_RELAY_REQUEST);
        t.a(mIMCUser.j());
        t.a(mIMCUser.n());
        t.b(((RtsData.BindRelayRequest) s.Z()).J());
        try {
            int a3 = mIMCUser.C().a(mIMCUser.G(), mIMCUser.H(), ((RtsData.UserPacket) t.Z()).K(), false, XMDPacket.DataPriority.P0, 3, (Object) null);
            if (a3 == -1) {
                MIMCLog.c(f11177a, String.format("SEND_STREAM BIND_RELAY_REQUEST FAIL, PACKET:%s groupId:%d", new Object[]{t.Z(), Integer.valueOf(a3)}));
                return false;
            }
            MIMCLog.b(f11177a, String.format("SEND_BIND_RELAY_REQUEST_SUCCESS uuid:%d, connId:%d, streamId:%d, groupId:%d, PACKET:%s", new Object[]{Long.valueOf(mIMCUser.j()), Long.valueOf(mIMCUser.G()), Short.valueOf(mIMCUser.H()), Integer.valueOf(a3), t.Z()}));
            return true;
        } catch (Exception e) {
            MIMCLog.d(f11177a, "SEND_STREAM BIND_RELAY_REQUEST EXCEPTION:", e);
            return false;
        }
    }

    public static void a(long j, MIMCUser mIMCUser) {
        b(j, mIMCUser);
        c(j, mIMCUser);
    }

    public static void b(long j, MIMCUser mIMCUser) {
        P2PCallSession p2PCallSession = (P2PCallSession) mIMCUser.x().get(Long.valueOf(j));
        if (p2PCallSession == null) {
            MIMCLog.c(f11177a, String.format("callSession is null in closeP2PIntranetConn, callId:%d", new Object[]{Long.valueOf(j)}));
            return;
        }
        if (p2PCallSession.c() != -1) {
            mIMCUser.C().b(p2PCallSession.c());
        }
        p2PCallSession.p();
    }

    public static void c(long j, MIMCUser mIMCUser) {
        P2PCallSession p2PCallSession = (P2PCallSession) mIMCUser.x().get(Long.valueOf(j));
        if (p2PCallSession == null) {
            MIMCLog.c(f11177a, String.format("callSession is null in clearLocalP2PConn, callId:%d", new Object[]{Long.valueOf(j)}));
            return;
        }
        if (p2PCallSession.f() != -1) {
            mIMCUser.C().b(p2PCallSession.f());
        }
        p2PCallSession.q();
    }

    public static void c(MIMCUser mIMCUser) {
        if (mIMCUser.x().size() > 0 || mIMCUser.z().size() > 0 || mIMCUser.y().size() > 0) {
            MIMCLog.b(f11177a, "This connection contains call ids!");
            return;
        }
        MIMCLog.b(f11177a, "This connection does not exist call!");
        if (mIMCUser.G() != -1) {
            mIMCUser.C().b(mIMCUser.G());
        }
        mIMCUser.U();
    }

    public static RtsData.BurrowPacket.Builder a(long j, String str, long j2, RtsData.BURROW_TYPE burrow_type, long j3) {
        RtsData.BurrowPacket.Builder m = RtsData.BurrowPacket.m();
        m.a(j);
        m.a(str);
        m.b(j2);
        m.c(j3);
        m.a(burrow_type);
        return m;
    }

    public static boolean a(MIMCUser mIMCUser, long j) {
        RtsSignal.UserInfo.Builder E = RtsSignal.UserInfo.E();
        E.a(mIMCUser.j());
        E.a(mIMCUser.n());
        E.b(mIMCUser.k());
        E.b(mIMCUser.q());
        InetSocketAddress a2 = mIMCUser.C().a();
        if (a2 != null) {
            E.c(a2.getAddress().getHostAddress());
            E.a(a2.getPort());
        }
        MIMCLog.b(f11177a, String.format("SendCreateRequest intranetIp:%s, intranetPort:%d", new Object[]{E.m(), Integer.valueOf(E.p())}));
        RtsData.BindRelayResponse K = mIMCUser.K();
        if (K == null) {
            MIMCLog.c(f11177a, String.format("BindRelayResponse is null in sendCreateRequest, callId:%d", new Object[]{Long.valueOf(j)}));
            return false;
        }
        E.d(K.d());
        E.b(K.g());
        MIMCLog.b(f11177a, String.format("SendCreateRequest internetIp:%s, internetPort:%d", new Object[]{K.d(), Integer.valueOf(K.g())}));
        E.e(K.i());
        E.c(K.m());
        E.c(mIMCUser.G());
        MIMCLog.b(f11177a, String.format("SendCreateRequest relayconnId:%d", new Object[]{Long.valueOf(mIMCUser.G())}));
        P2PCallSession p2PCallSession = (P2PCallSession) mIMCUser.x().get(Long.valueOf(j));
        RtsSignal.CreateRequest.Builder l = RtsSignal.CreateRequest.l();
        l.a(p2PCallSession.a()).a(E);
        if (p2PCallSession.l() != null) {
            l.b(ByteString.copyFrom(p2PCallSession.l()));
        }
        RtsSignal.RTSMessage.Builder q = RtsSignal.RTSMessage.q();
        q.a(RtsSignal.RTSMessageType.CREATE_REQUEST);
        q.a(j);
        q.a(RtsSignal.CallType.SINGLE_CALL);
        q.b(mIMCUser.j());
        q.a(mIMCUser.n());
        q.b(((RtsSignal.CreateRequest) l.Z()).J());
        q.c(mIMCUser.n(j));
        Mimc.MIMCPacket.Builder p = Mimc.MIMCPacket.p();
        String b2 = mIMCUser.b();
        p.a(b2);
        p.b(mIMCUser.o());
        p.a(Mimc.MIMC_MSG_TYPE.RTS_SIGNAL);
        p.c(((RtsSignal.RTSMessage) q.Z()).J());
        mIMCUser.b(b2, ((Mimc.MIMCPacket) p.Z()).K(), MIMCConstant.Z);
        MIMCLog.b(f11177a, String.format("MIMC connId:%d callId:%d SEND_CREATE_REQUEST_TO_CALL_CENTER_SUCCESS sendCreateRequest push packet, packetId:%s uuid:%d", new Object[]{Long.valueOf(mIMCUser.G()), Long.valueOf(j), b2, Long.valueOf(mIMCUser.j())}));
        ((P2PCallSession) mIMCUser.x().get(Long.valueOf(j))).a(P2PCallSession.CallState.WAIT_RECEIVE_CREATE_RESPONSE).d(System.currentTimeMillis());
        return true;
    }

    public static boolean a(MIMCUser mIMCUser, RtsSignal.RTSResult rTSResult, String str, long j, long j2) {
        if (mIMCUser.V() != MIMCConstant.OnlineStatus.ONLINE) {
            MIMCLog.c(f11177a, String.format("sendInviteResponse, Fail4NotOnline, callId:%d, connId:%d, uuid:%d", new Object[]{Long.valueOf(j), Long.valueOf(j2), Long.valueOf(mIMCUser.j())}));
            return false;
        }
        RtsSignal.UserInfo.Builder E = RtsSignal.UserInfo.E();
        E.a(mIMCUser.j());
        E.a(mIMCUser.n());
        E.b(mIMCUser.k());
        E.b(mIMCUser.q());
        InetSocketAddress a2 = mIMCUser.C().a();
        if (a2 != null) {
            E.c(a2.getAddress().getHostAddress());
            E.a(a2.getPort());
        }
        MIMCLog.b(f11177a, String.format("sendInviteResponse, IntranetIp:%s, IntranetPort:%d, uuid:%d", new Object[]{E.m(), Integer.valueOf(E.p()), Long.valueOf(mIMCUser.j())}));
        if (j2 != -1) {
            E.c(j2);
        }
        if (mIMCUser.K() != null) {
            E.d(mIMCUser.K().d());
            E.b(mIMCUser.K().g());
            E.e(mIMCUser.K().i());
            E.c(mIMCUser.K().m());
        }
        RtsSignal.InviteResponse.Builder l = RtsSignal.InviteResponse.l();
        l.a(rTSResult);
        l.a(str);
        l.a(E);
        RtsSignal.RTSMessage.Builder q = RtsSignal.RTSMessage.q();
        q.a(RtsSignal.RTSMessageType.INVITE_RESPONSE);
        q.a(j);
        q.a(RtsSignal.CallType.SINGLE_CALL);
        q.b(mIMCUser.j());
        q.a(mIMCUser.n());
        q.b(((RtsSignal.InviteResponse) l.Z()).J());
        q.c(mIMCUser.n(j));
        Mimc.MIMCPacket.Builder p = Mimc.MIMCPacket.p();
        String b2 = mIMCUser.b();
        p.a(b2);
        p.b(mIMCUser.o());
        p.a(Mimc.MIMC_MSG_TYPE.RTS_SIGNAL);
        p.c(((RtsSignal.RTSMessage) q.Z()).J());
        mIMCUser.b(b2, ((Mimc.MIMCPacket) p.Z()).K(), MIMCConstant.Z);
        MIMCLog.b(f11177a, "pushPacket sendInviteResponse");
        return true;
    }

    public static void a(MIMCUser mIMCUser, long j, String str) {
        RtsSignal.ByeRequest.Builder d = RtsSignal.ByeRequest.d();
        if (str != null) {
            d.a(str);
        }
        RtsSignal.RTSMessage.Builder q = RtsSignal.RTSMessage.q();
        q.a(RtsSignal.RTSMessageType.BYE_REQUEST);
        q.a(j);
        q.a(RtsSignal.CallType.SINGLE_CALL);
        q.b(mIMCUser.j());
        q.a(mIMCUser.n());
        q.b(((RtsSignal.ByeRequest) d.Z()).J());
        q.c(mIMCUser.n(j));
        Mimc.MIMCPacket.Builder p = Mimc.MIMCPacket.p();
        String b2 = mIMCUser.b();
        p.a(b2);
        p.b(mIMCUser.o());
        p.a(Mimc.MIMC_MSG_TYPE.RTS_SIGNAL);
        p.c(((RtsSignal.RTSMessage) q.Z()).J());
        mIMCUser.b(b2, ((Mimc.MIMCPacket) p.Z()).K(), MIMCConstant.Y);
        MIMCLog.a(f11177a, String.format("pushPacket, BYE_REQUEST PACKET:%s", new Object[]{p.Z()}));
    }

    public static void d(MIMCUser mIMCUser) {
        a(mIMCUser);
        for (Map.Entry value : mIMCUser.x().entrySet()) {
            ((P2PCallSession) value.getValue()).a(P2PCallSession.CallState.WAIT_SEND_UPDATE_REQUEST).d(System.currentTimeMillis());
        }
    }

    public static boolean b(MIMCUser mIMCUser, long j) {
        RtsSignal.UserInfo.Builder E = RtsSignal.UserInfo.E();
        E.a(mIMCUser.j());
        E.a(mIMCUser.n());
        E.b(mIMCUser.k());
        E.b(mIMCUser.q());
        InetSocketAddress a2 = mIMCUser.C().a();
        if (a2 != null) {
            E.c(a2.getAddress().getHostAddress());
            E.a(a2.getPort());
        }
        E.d(mIMCUser.K().d());
        E.b(mIMCUser.K().g());
        E.e(mIMCUser.K().i());
        E.c(mIMCUser.K().m());
        E.c(mIMCUser.G());
        RtsSignal.UpdateRequest.Builder c2 = RtsSignal.UpdateRequest.c();
        c2.a(E);
        RtsSignal.RTSMessage.Builder q = RtsSignal.RTSMessage.q();
        q.a(RtsSignal.RTSMessageType.UPDATE_REQUEST);
        q.a(j);
        q.a(RtsSignal.CallType.SINGLE_CALL);
        q.b(mIMCUser.j());
        q.a(mIMCUser.n());
        q.b(((RtsSignal.UpdateRequest) c2.Z()).J());
        q.c(mIMCUser.n(j));
        Mimc.MIMCPacket.Builder p = Mimc.MIMCPacket.p();
        String b2 = mIMCUser.b();
        p.a(b2);
        p.b(mIMCUser.o());
        p.a(Mimc.MIMC_MSG_TYPE.RTS_SIGNAL);
        p.c(((RtsSignal.RTSMessage) q.Z()).J());
        mIMCUser.b(b2, ((Mimc.MIMCPacket) p.Z()).K(), MIMCConstant.Y);
        ((P2PCallSession) mIMCUser.x().get(Long.valueOf(j))).a(P2PCallSession.CallState.WAIT_RECEIVE_UPDATE_RESPONSE).d(System.currentTimeMillis());
        return true;
    }

    public static int a(MIMCUser mIMCUser, long j, byte[] bArr, XMDPacket.DataPriority dataPriority, boolean z, int i, RtsData.PKT_TYPE pkt_type, MIMCContext mIMCContext) {
        MIMCUser mIMCUser2 = mIMCUser;
        RtsData.PKT_TYPE pkt_type2 = pkt_type;
        RtsData.UserPacket.Builder t = RtsData.UserPacket.t();
        t.a(pkt_type2);
        t.a(mIMCUser.j());
        t.b(mIMCUser.q());
        t.a(mIMCUser.n());
        t.b(j);
        t.b(ByteString.copyFrom(bArr));
        t.c(mIMCUser.n(j));
        long G = mIMCUser.G();
        if (G == -1) {
            MIMCLog.c(f11177a, "SEND_RTS_DATA BY RELAY RELAY_COON_ID NULL");
            return -1;
        }
        XMDTransceiver C = mIMCUser.C();
        if (pkt_type2 == RtsData.PKT_TYPE.USER_DATA_VIDEO) {
            if (mIMCUser.I() == -1) {
                XMDPacket.StreamType streamType = XMDPacket.StreamType.FEC_STREAM;
                if (mIMCUser.an().a() == 1) {
                    streamType = XMDPacket.StreamType.ACK_STREAM;
                }
                mIMCUser2.b(C.a(G, streamType, (short) mIMCUser.an().b(), mIMCUser.an().c()));
            }
            int a2 = C.a(G, mIMCUser.I(), ((RtsData.UserPacket) t.Z()).K(), z, dataPriority, i, mIMCContext);
            MIMCLog.b(f11177a, String.format("SEND_STREAM_DATA BY RELAY, USER_DATA_VIDEO, RELAY_CHANNEL,relayconnId:%d, relayVedioStreamId:%d, callId:%d, uuid:%d, resource:%s, payloadLen:%d, groupId:%d", new Object[]{Long.valueOf(G), Short.valueOf(mIMCUser.I()), Long.valueOf(t.l()), Long.valueOf(t.f()), t.h(), Integer.valueOf(t.n().size()), Integer.valueOf(a2)}));
            return a2;
        } else if (pkt_type2 != RtsData.PKT_TYPE.USER_DATA_AUDIO) {
            return -1;
        } else {
            if (mIMCUser.J() == -1) {
                XMDPacket.StreamType streamType2 = XMDPacket.StreamType.ACK_STREAM;
                if (mIMCUser.am().a() == 0) {
                    streamType2 = XMDPacket.StreamType.FEC_STREAM;
                }
                mIMCUser2.c(C.a(G, streamType2, (short) mIMCUser.am().b(), mIMCUser.am().c()));
            }
            int a3 = C.a(G, mIMCUser.J(), ((RtsData.UserPacket) t.Z()).K(), z, dataPriority, i, mIMCContext);
            if (a3 == -1) {
                MIMCLog.b(f11177a, String.format("FAILED SEND_STREAM_DATA BY RELAY, USER_DATA_AUDIO, RELAY_CHANNEL, relayconnId:%d, relayAudioStreamId:%d, callId:%d, uuid:%d, resource:%s, payloadLen:%d, groupId:%d", new Object[]{Long.valueOf(G), Short.valueOf(mIMCUser.J()), Long.valueOf(t.l()), Long.valueOf(t.f()), t.h(), Integer.valueOf(t.n().size()), Integer.valueOf(a3)}));
            }
            return a3;
        }
    }

    public static int b(MIMCUser mIMCUser, long j, byte[] bArr, XMDPacket.DataPriority dataPriority, boolean z, int i, RtsData.PKT_TYPE pkt_type, MIMCContext mIMCContext) {
        short s;
        short s2;
        RtsData.PKT_TYPE pkt_type2 = pkt_type;
        RtsData.UserPacket.Builder t = RtsData.UserPacket.t();
        t.a(pkt_type2);
        t.a(mIMCUser.j());
        t.b(mIMCUser.q());
        t.a(mIMCUser.n());
        t.b(j);
        t.b(ByteString.copyFrom(bArr));
        t.c(mIMCUser.n(j));
        XMDTransceiver C = mIMCUser.C();
        P2PCallSession p2PCallSession = (P2PCallSession) mIMCUser.x().get(Long.valueOf(j));
        long c2 = p2PCallSession.c();
        short e = p2PCallSession.e();
        short d = p2PCallSession.d();
        if (p2PCallSession.j() && c2 != -1) {
            if (pkt_type2 == RtsData.PKT_TYPE.USER_DATA_VIDEO) {
                if (d == -1) {
                    XMDPacket.StreamType streamType = XMDPacket.StreamType.FEC_STREAM;
                    if (mIMCUser.an().a() == 1) {
                        streamType = XMDPacket.StreamType.ACK_STREAM;
                    }
                    s2 = C.a(c2, streamType, (short) mIMCUser.an().b(), mIMCUser.an().c());
                    p2PCallSession.a(s2);
                } else {
                    s2 = d;
                }
                int a2 = C.a(c2, s2, ((RtsData.UserPacket) t.Z()).K(), z, dataPriority, i, mIMCContext);
                if (a2 != -1) {
                    return a2;
                }
                MIMCLog.b(f11177a, String.format("FAILED SEND_STREAM_DATA BY P2P, INTRANET USER_DATA_VIDEO, P2P_CHANNEL, intranetP2PconnId:%d, intranetP2PVideoStreamId:%d, callId:%d, uuid:%d, resource:%s, payloadLen:%d, groupId:%d", new Object[]{Long.valueOf(c2), Short.valueOf(s2), Long.valueOf(t.l()), Long.valueOf(t.f()), t.h(), Integer.valueOf(t.n().size()), Integer.valueOf(a2)}));
                return a2;
            } else if (pkt_type2 == RtsData.PKT_TYPE.USER_DATA_AUDIO) {
                if (e == -1) {
                    XMDPacket.StreamType streamType2 = XMDPacket.StreamType.ACK_STREAM;
                    if (mIMCUser.am().a() == 0) {
                        streamType2 = XMDPacket.StreamType.FEC_STREAM;
                    }
                    s = C.a(c2, streamType2, (short) mIMCUser.am().b(), mIMCUser.am().c());
                    p2PCallSession.b(s);
                } else {
                    s = e;
                }
                int a3 = C.a(c2, s, ((RtsData.UserPacket) t.Z()).K(), z, dataPriority, i, mIMCContext);
                if (a3 != -1) {
                    return a3;
                }
                MIMCLog.b(f11177a, String.format("FAILED SEND_STREAM_DATA BY P2P, INTRANET USER_DATA_AUDIO, P2P_CHANNEL, intranetP2PconnId:%d, intranetP2PAudioStreamId:%d, callId:%d, uuid:%d, resource:%s, payloadLen:%d, groupId:%d", new Object[]{Long.valueOf(c2), Short.valueOf(s), Long.valueOf(t.l()), Long.valueOf(t.f()), t.h(), Integer.valueOf(t.n().size()), Integer.valueOf(a3)}));
                return a3;
            }
        }
        return -1;
    }

    public static int c(MIMCUser mIMCUser, long j, byte[] bArr, XMDPacket.DataPriority dataPriority, boolean z, int i, RtsData.PKT_TYPE pkt_type, MIMCContext mIMCContext) {
        short s;
        short s2;
        RtsData.PKT_TYPE pkt_type2 = pkt_type;
        RtsData.UserPacket.Builder t = RtsData.UserPacket.t();
        t.a(pkt_type2);
        t.a(mIMCUser.j());
        t.b(mIMCUser.q());
        t.a(mIMCUser.n());
        t.b(j);
        t.b(ByteString.copyFrom(bArr));
        t.c(mIMCUser.n(j));
        XMDTransceiver C = mIMCUser.C();
        P2PCallSession p2PCallSession = (P2PCallSession) mIMCUser.x().get(Long.valueOf(j));
        long f = p2PCallSession.f();
        short h = p2PCallSession.h();
        short g = p2PCallSession.g();
        if (p2PCallSession.k() && f != -1) {
            if (pkt_type2 == RtsData.PKT_TYPE.USER_DATA_VIDEO) {
                if (g == -1) {
                    XMDPacket.StreamType streamType = XMDPacket.StreamType.FEC_STREAM;
                    if (mIMCUser.an().a() == 1) {
                        streamType = XMDPacket.StreamType.ACK_STREAM;
                    }
                    s2 = C.a(f, streamType, (short) mIMCUser.an().b(), mIMCUser.an().c());
                    p2PCallSession.c(s2);
                } else {
                    s2 = g;
                }
                int a2 = C.a(f, s2, ((RtsData.UserPacket) t.Z()).K(), z, dataPriority, i, mIMCContext);
                if (a2 != -1) {
                    return a2;
                }
                MIMCLog.b(f11177a, String.format("FAILED SEND_STREAM_DATA BY P2P, INTERNET USER_DATA_VIDEO, P2P_CHANNEL, internetP2PconnId:%d, internetP2PVideoStreamId:%d, PACKET:%s, groupId:%d", new Object[]{Long.valueOf(f), Short.valueOf(s2), t.Z(), Integer.valueOf(a2)}));
                return a2;
            } else if (pkt_type2 == RtsData.PKT_TYPE.USER_DATA_AUDIO) {
                if (h == -1) {
                    XMDPacket.StreamType streamType2 = XMDPacket.StreamType.ACK_STREAM;
                    if (mIMCUser.am().a() == 0) {
                        streamType2 = XMDPacket.StreamType.FEC_STREAM;
                    }
                    s = C.a(f, streamType2, (short) mIMCUser.am().b(), mIMCUser.am().c());
                    p2PCallSession.d(s);
                } else {
                    s = h;
                }
                int a3 = C.a(f, s, ((RtsData.UserPacket) t.Z()).K(), z, dataPriority, i, mIMCContext);
                if (a3 != -1) {
                    return a3;
                }
                MIMCLog.b(f11177a, String.format("FAILED SEND_STREAM_DATA BY P2P, INTERNET USER_DATA_AUDIO, P2P_CHANNEL, internetP2PconnId:%d, internetP2PAudioStreamId:%d, PACKET:%s, groupId:%d", new Object[]{Long.valueOf(f), Short.valueOf(s), t.Z(), Integer.valueOf(a3)}));
                return a3;
            }
        }
        return -1;
    }

    public static RtsSignal.UserInfo e(MIMCUser mIMCUser) {
        RtsSignal.UserInfo.Builder E = RtsSignal.UserInfo.E();
        E.a(mIMCUser.j());
        E.a(mIMCUser.n());
        E.b(mIMCUser.k());
        E.b(mIMCUser.q());
        InetSocketAddress a2 = mIMCUser.C().a();
        if (a2 != null) {
            E.c(a2.getAddress().getHostAddress());
            E.a(a2.getPort());
        }
        RtsData.BindRelayResponse K = mIMCUser.K();
        if (K == null) {
            MIMCLog.c(f11177a, String.format("bindRelayResponse is null when buildUserInfo.", new Object[0]));
            return null;
        }
        E.d(K.d());
        E.b(K.g());
        E.e(K.i());
        E.c(K.m());
        MIMCLog.b(f11177a, String.format("buildUserInfo intranetIp:%s intranetPort:%d internetIp:%s internetPort:%d relayIp:%s relayPort:%d", new Object[]{E.m(), Integer.valueOf(E.p()), K.d(), Integer.valueOf(K.g()), K.i(), Integer.valueOf(K.m())}));
        E.c(mIMCUser.G());
        return (RtsSignal.UserInfo) E.Z();
    }

    public static boolean a(MIMCUser mIMCUser, long j, byte[] bArr) {
        RtsSignal.UserInfo e = e(mIMCUser);
        if (e == null) {
            MIMCLog.c(f11177a, String.format("fromUser is null when sendCreateChannelRequest.", new Object[0]));
            return false;
        }
        RtsSignal.CreateChannelRequest.Builder g = RtsSignal.CreateChannelRequest.g();
        g.a(e);
        g.a(j);
        if (bArr != null) {
            g.a(ByteString.copyFrom(bArr));
        }
        RtsSignal.RTSMessage.Builder q = RtsSignal.RTSMessage.q();
        q.a(RtsSignal.RTSMessageType.CREATE_CHANNEL_REQUEST);
        q.a(RtsSignal.CallType.CHANNEL_CALL);
        q.b(mIMCUser.j());
        q.a(mIMCUser.n());
        q.b(((RtsSignal.CreateChannelRequest) g.Z()).J());
        q.c(mIMCUser.al());
        Mimc.MIMCPacket.Builder p = Mimc.MIMCPacket.p();
        String b2 = mIMCUser.b();
        p.a(b2);
        p.b(mIMCUser.o());
        p.a(Mimc.MIMC_MSG_TYPE.RTS_SIGNAL);
        p.c(((RtsSignal.RTSMessage) q.Z()).J());
        mIMCUser.b(b2, ((Mimc.MIMCPacket) p.Z()).K(), MIMCConstant.Z);
        MIMCLog.b(f11177a, String.format("MIMC connId:%d SEND_CREATE_CHANNEL_REQUEST_TO_CALL_CENTER_SUCCESS sendCreateChannelRequest push packet, packetId:%s uuid:%d", new Object[]{Long.valueOf(mIMCUser.G()), b2, Long.valueOf(mIMCUser.j())}));
        return true;
    }

    public static boolean b(MIMCUser mIMCUser, long j, String str) {
        RtsSignal.UserInfo e = e(mIMCUser);
        if (e == null) {
            MIMCLog.c(f11177a, String.format("fromUser is null when sendJoinChannelRequest.", new Object[0]));
            return false;
        }
        RtsSignal.JoinChannelRequest.Builder h = RtsSignal.JoinChannelRequest.h();
        h.a(e);
        h.a(j);
        h.a(str);
        RtsSignal.RTSMessage.Builder q = RtsSignal.RTSMessage.q();
        q.a(RtsSignal.RTSMessageType.JOIN_CHANNEL_REQUEST);
        q.a(RtsSignal.CallType.CHANNEL_CALL);
        q.a(j);
        q.b(mIMCUser.j());
        q.a(mIMCUser.n());
        q.b(((RtsSignal.JoinChannelRequest) h.Z()).J());
        q.c(mIMCUser.al());
        Mimc.MIMCPacket.Builder p = Mimc.MIMCPacket.p();
        String b2 = mIMCUser.b();
        p.a(b2);
        p.b(mIMCUser.o());
        p.a(Mimc.MIMC_MSG_TYPE.RTS_SIGNAL);
        p.c(((RtsSignal.RTSMessage) q.Z()).J());
        mIMCUser.b(b2, ((Mimc.MIMCPacket) p.Z()).K(), MIMCConstant.Z);
        MIMCLog.b(f11177a, String.format("MIMC connId:%d SEND_JOIN_CHANNEL_REQUEST_TO_CALL_CENTER_SUCCESS sendJoinChannelRequest push packet, packetId:%s uuid:%d", new Object[]{Long.valueOf(mIMCUser.G()), b2, Long.valueOf(mIMCUser.j())}));
        return true;
    }

    public static boolean c(MIMCUser mIMCUser, long j, String str) {
        RtsSignal.UserInfo e = e(mIMCUser);
        if (e == null) {
            MIMCLog.c(f11177a, String.format("fromUser is null when sendLeaveChannelRequest.", new Object[0]));
            return false;
        }
        RtsSignal.LeaveChannelRequest.Builder h = RtsSignal.LeaveChannelRequest.h();
        h.a(e);
        h.a(j);
        h.a(str);
        RtsSignal.RTSMessage.Builder q = RtsSignal.RTSMessage.q();
        q.a(RtsSignal.RTSMessageType.LEAVE_CHANNEL_REQUEST);
        q.a(RtsSignal.CallType.CHANNEL_CALL);
        q.a(j);
        q.b(mIMCUser.j());
        q.a(mIMCUser.n());
        q.b(((RtsSignal.LeaveChannelRequest) h.Z()).J());
        q.c(mIMCUser.al());
        Mimc.MIMCPacket.Builder p = Mimc.MIMCPacket.p();
        String b2 = mIMCUser.b();
        p.a(b2);
        p.b(mIMCUser.o());
        p.a(Mimc.MIMC_MSG_TYPE.RTS_SIGNAL);
        p.c(((RtsSignal.RTSMessage) q.Z()).J());
        mIMCUser.b(b2, ((Mimc.MIMCPacket) p.Z()).K(), MIMCConstant.Z);
        MIMCLog.b(f11177a, String.format("MIMC connId:%d SEND_LEAVE_CHANNEL_REQUEST_TO_CALL_CENTER_SUCCESS sendLeaveChannelRequest push packet, packetId:%s uuid:%d", new Object[]{Long.valueOf(mIMCUser.G()), b2, Long.valueOf(mIMCUser.j())}));
        return true;
    }
}
