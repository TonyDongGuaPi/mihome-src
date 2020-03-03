package com.xiaomi.mimc;

import com.taobao.weex.common.Constants;
import com.xiaomi.mimc.client.Connection;
import com.xiaomi.mimc.client.RecvThread;
import com.xiaomi.mimc.client.SendThread;
import com.xiaomi.mimc.client.TriggerThread;
import com.xiaomi.mimc.common.HttpUtils;
import com.xiaomi.mimc.common.MIMCConstant;
import com.xiaomi.mimc.common.MIMCUtils;
import com.xiaomi.mimc.common.RTSUtils;
import com.xiaomi.mimc.common.UserMessageHandler;
import com.xiaomi.mimc.data.ChannelSession;
import com.xiaomi.mimc.data.ChannelUser;
import com.xiaomi.mimc.data.MIMCContext;
import com.xiaomi.mimc.data.MIMCObject;
import com.xiaomi.mimc.data.MIMCStreamConfig;
import com.xiaomi.mimc.data.P2PCallSession;
import com.xiaomi.mimc.data.RtsChannelType;
import com.xiaomi.mimc.data.RtsDataType;
import com.xiaomi.mimc.data.TempChannelSession;
import com.xiaomi.mimc.data.TimeoutPacket;
import com.xiaomi.mimc.json.JSONException;
import com.xiaomi.mimc.json.JSONObject;
import com.xiaomi.mimc.packet.V6Packet;
import com.xiaomi.mimc.processor.QueryUnlimitedGroupsProcessor;
import com.xiaomi.mimc.proto.ImsPushService;
import com.xiaomi.mimc.proto.Mimc;
import com.xiaomi.mimc.proto.RtsData;
import com.xiaomi.mimc.proto.RtsSignal;
import com.xiaomi.mimc.protobuf.ByteString;
import com.xiaomi.mimc.xmdtransceiverhandler.RTSConnectionHandler;
import com.xiaomi.mimc.xmdtransceiverhandler.RTSDatagramHandler;
import com.xiaomi.mimc.xmdtransceiverhandler.RTSStreamHandler;
import com.xiaomi.msg.XMDTransceiver;
import com.xiaomi.msg.data.XMDPacket;
import com.xiaomi.msg.handler.ConnectionHandler;
import com.xiaomi.msg.handler.DatagramHandler;
import com.xiaomi.msg.handler.StreamHandler;
import com.xiaomi.msg.logger.MIMCLog;
import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.atomic.AtomicLong;

public class MIMCUser {

    /* renamed from: a  reason: collision with root package name */
    private static final String f11156a = "MIMCUser";
    private static ConcurrentMap<String, MIMCUser> am = new ConcurrentHashMap();
    private MIMCOnlineStatusListener A;
    private MIMCUnlimitedGroupHandler B;
    private UserMessageHandler C;
    private MIMCRtsChannelHandler D;
    private int E = 1;
    private MIMCRtsCallHandler F;
    private RTSDatagramHandler G;
    private RTSConnectionHandler H;
    private RTSStreamHandler I;
    private volatile XMDTransceiver J;
    private Object K = new Object();
    private String L;
    private String M;
    private long N;
    private String O;
    private String P;
    private String Q;
    private String R;
    private String S = MIMCUtils.a(15);
    private AtomicLong T = new AtomicLong();
    private Set<Long> U = new TreeSet();
    private Set<Long> V = new HashSet();
    private Set<Long> W = new HashSet();
    private ConcurrentMap<String, TimeoutPacket> X = new ConcurrentHashMap();
    private ConcurrentMap<Long, P2PCallSession> Y = new ConcurrentHashMap();
    private ConcurrentMap<Long, ChannelSession> Z = new ConcurrentHashMap();
    private ConcurrentMap<Long, TempChannelSession> aa = new ConcurrentHashMap();
    private final String ab;
    private final String ac;
    private String ad;
    private QueryUnlimitedGroupsProcessor ae = null;
    /* access modifiers changed from: private */
    public Object af;
    private Object ag;
    private Object ah;
    /* access modifiers changed from: private */
    public Object ai;
    private SendThread aj;
    private RecvThread ak;
    private TriggerThread al;
    private int an = 15000;
    private int ao = 10000;
    private int ap = 30000;
    private int aq;
    private MIMCStreamConfig ar;
    private MIMCStreamConfig as;
    private volatile RelayState b = RelayState.NOT_CREATED;
    private long c;
    private volatile long d = -1;
    private volatile short e = -1;
    private short f = -1;
    private short g = -1;
    private volatile RtsData.BindRelayResponse h;
    private final long i;
    private final String j;
    private String k;
    private volatile MIMCConstant.OnlineStatus l;
    private Map<String, String> m = new TreeMap();
    private Map<String, String> n = new TreeMap();
    private int o;
    private long p;
    private String q;
    private String r;
    private String s;
    private volatile boolean t;
    private Connection u;
    private long v;
    private long w;
    private long x;
    private MIMCTokenFetcher y;
    private MIMCMessageHandler z;

    public enum RelayState {
        NOT_CREATED,
        BEING_CREATED,
        SUCC_CREATED
    }

    public static synchronized MIMCUser a(long j2, String str, String str2) {
        MIMCUser a2;
        synchronized (MIMCUser.class) {
            a2 = a(j2, str, str2, MIMCConstant.ak, MIMCConstant.al);
        }
        return a2;
    }

    public static synchronized MIMCUser a(long j2, String str, String str2, String str3) {
        MIMCUser a2;
        synchronized (MIMCUser.class) {
            a2 = a(j2, str, str2, str3, MIMCConstant.ak, MIMCConstant.al);
        }
        return a2;
    }

    public static synchronized MIMCUser a(long j2, String str, String str2, String str3, String str4) {
        String str5 = str;
        String str6 = str2;
        synchronized (MIMCUser.class) {
            if (!j(str2)) {
                MIMCLog.c(f11156a, String.format("The incoming cachePath is wrongful, cachePath:%s", new Object[]{str6}));
                return null;
            } else if (MIMCUtils.c(str)) {
                MIMCLog.c(f11156a, "The incoming appAccount is empty.");
                return null;
            } else if (am.containsKey(str)) {
                MIMCUser mIMCUser = (MIMCUser) am.get(str);
                return mIMCUser;
            } else {
                String format = String.format("%s_%s", new Object[]{str5, MIMCConstant.ai});
                String format2 = String.format("%s_%s", new Object[]{str5, MIMCConstant.an});
                String a2 = MIMCUtils.a(str6, format2, format);
                if (a2 != null) {
                    if (a2.length() == 0) {
                    }
                    MIMCUser mIMCUser2 = new MIMCUser(j2, str, str2, a2, format2, str, str3, str4);
                    am.put(str, mIMCUser2);
                    return mIMCUser2;
                }
                a2 = String.format("%s", new Object[]{i(8)});
                MIMCUtils.a(str6, format2, format, a2);
                MIMCUser mIMCUser22 = new MIMCUser(j2, str, str2, a2, format2, str, str3, str4);
                am.put(str, mIMCUser22);
                return mIMCUser22;
            }
        }
    }

    public static synchronized MIMCUser a(long j2, String str, String str2, String str3, String str4, String str5) {
        String str6 = str2;
        String str7 = str3;
        synchronized (MIMCUser.class) {
            if (!j(str2)) {
                MIMCLog.c(f11156a, String.format("The incoming cachePath is wrongful, cachePath:%s", new Object[]{str6}));
                return null;
            } else if (MIMCUtils.c(str)) {
                MIMCLog.c(f11156a, "The incoming appAccount is empty.");
                return null;
            } else if (MIMCUtils.c(str3)) {
                MIMCLog.c(f11156a, "The incoming resource is null.");
                return null;
            } else {
                String format = String.format("%s_%s", new Object[]{str, str7});
                if (am.containsKey(format)) {
                    MIMCUser mIMCUser = (MIMCUser) am.get(format);
                    return mIMCUser;
                }
                String format2 = String.format("%s_%s_%s", new Object[]{str, str7, MIMCConstant.an});
                String format3 = String.format("%s_%s_%s", new Object[]{str, str7, MIMCConstant.ai});
                if (!str7.equals(MIMCUtils.a(str6, format2, format3))) {
                    MIMCUtils.a(str6, format2, format3, str7);
                }
                MIMCUser mIMCUser2 = new MIMCUser(j2, str, str2, str3, format2, format, str4, str5);
                am.put(format, mIMCUser2);
                return mIMCUser2;
            }
        }
    }

    private MIMCUser(long j2, String str, String str2, String str3, String str4, String str5, String str6, String str7) {
        this.i = j2;
        this.L = str6;
        this.M = str7;
        this.j = str;
        this.l = MIMCConstant.OnlineStatus.OFFLINE;
        this.v = 0;
        this.w = 0;
        this.x = 0;
        this.t = false;
        this.ab = str2;
        MIMCLog.a(str2);
        this.q = str3;
        this.ac = str4;
        this.ad = str5;
        this.C = new UserMessageHandler(this);
        this.u = new Connection(this);
        this.aj = new SendThread(this.u);
        this.aj.start();
        this.ak = new RecvThread(this.u);
        this.ak.start();
        this.al = new TriggerThread(this);
        this.al.start();
        this.ar = new MIMCStreamConfig(1, 150, false);
        this.as = new MIMCStreamConfig(0, false);
    }

    private static boolean j(String str) {
        if (str == null || str.length() == 0) {
            MIMCLog.c(f11156a, "The incoming cachePath is null.");
            return false;
        }
        File file = new File(str);
        if (file.exists()) {
            return true;
        }
        return file.mkdir();
    }

    public boolean a() {
        MIMCLog.b(f11156a, String.format("%s login", new Object[]{q()}));
        if (p()) {
            MIMCLog.c(f11156a, String.format("The user is online, ignore login, uuid:%d, status:%s", new Object[]{Long.valueOf(this.p), this.l}));
            return true;
        } else if (this.aj == null || this.ak == null) {
            throw new IllegalThreadStateException("sendThread or recvThread is null.");
        } else if (s() == null || t() == null || r() == null) {
            throw new IllegalStateException("Please register all callback functions.");
        } else {
            this.t = true;
            k(0);
            if (this.ae == null || !this.ae.isAlive()) {
                this.ae = new QueryUnlimitedGroupsProcessor(this);
                this.ae.start();
            }
            return true;
        }
    }

    public String b() {
        return this.S + "-" + this.T.incrementAndGet();
    }

    public String a(String str, byte[] bArr) {
        return a(str, bArr, "", true);
    }

    public String a(String str, byte[] bArr, boolean z2) {
        return a(str, bArr, "", z2);
    }

    public String a(String str, byte[] bArr, String str2) {
        return a(str, bArr, str2, true);
    }

    public String a(String str, byte[] bArr, String str2, boolean z2) {
        if (bArr.length > 10240) {
            MIMCLog.c(f11156a, String.format("sendMessage, invalid dataLen len:%d > MAX_MESSAGE_SIZE", new Object[]{Integer.valueOf(bArr.length)}));
            return null;
        } else if (V() != MIMCConstant.OnlineStatus.ONLINE) {
            MIMCLog.c(f11156a, String.format("sendMessage, FailedNotOnline, uuid:%d", new Object[]{Long.valueOf(j())}));
            return null;
        } else {
            Mimc.MIMCUser.Builder l2 = Mimc.MIMCUser.l();
            l2.a(this.i);
            l2.a(this.j);
            l2.b(j());
            l2.b(n());
            Mimc.MIMCUser.Builder l3 = Mimc.MIMCUser.l();
            l3.a(this.i);
            l3.a(str);
            Mimc.MIMCP2PMessage.Builder m2 = Mimc.MIMCP2PMessage.m();
            m2.a(l2);
            m2.b(l3);
            m2.a(ByteString.copyFrom(bArr));
            m2.a(z2);
            m2.a(str2);
            Mimc.MIMCPacket.Builder p2 = Mimc.MIMCPacket.p();
            String b2 = b();
            p2.a(b2);
            p2.b(this.k);
            p2.a(Mimc.MIMC_MSG_TYPE.P2P_MESSAGE);
            p2.c(((Mimc.MIMCP2PMessage) m2.Z()).J());
            this.X.put(b2, new TimeoutPacket((Mimc.MIMCPacket) p2.Z(), System.currentTimeMillis()));
            MIMCLog.b(f11156a, String.format("TimeoutMessageLog sendMessage timeoutPackets put packetId:%s", new Object[]{b2}));
            b(b2, ((Mimc.MIMCPacket) p2.Z()).K(), MIMCConstant.Y);
            MIMCLog.b(f11156a, String.format("sendMessage push packet. packetLen:%d", new Object[]{Integer.valueOf(((Mimc.MIMCPacket) p2.Z()).K().length)}));
            return b2;
        }
    }

    public String a(long j2, byte[] bArr) {
        return a(j2, bArr, "", true);
    }

    public String a(long j2, byte[] bArr, boolean z2) {
        return a(j2, bArr, "", z2);
    }

    public String a(long j2, byte[] bArr, String str) {
        return a(j2, bArr, str, true);
    }

    public String a(long j2, byte[] bArr, String str, boolean z2) {
        if (bArr.length > 10240) {
            MIMCLog.c(f11156a, String.format("sendGroupMessage, invalid dataLen len:%d > MAX_MESSAGE_SIZE", new Object[]{Integer.valueOf(bArr.length)}));
            return null;
        } else if (V() != MIMCConstant.OnlineStatus.ONLINE) {
            MIMCLog.c(f11156a, String.format("sendGroupMessage, FailedNotOnline, uuid:%d", new Object[]{Long.valueOf(j())}));
            return null;
        } else {
            Mimc.MIMCUser.Builder l2 = Mimc.MIMCUser.l();
            l2.a(this.i);
            l2.a(this.j);
            l2.b(j());
            l2.b(n());
            Mimc.MIMCGroup.Builder e2 = Mimc.MIMCGroup.e();
            e2.a(this.i);
            e2.b(j2);
            Mimc.MIMCP2TMessage.Builder m2 = Mimc.MIMCP2TMessage.m();
            m2.a(l2);
            m2.a(e2);
            m2.a(ByteString.copyFrom(bArr));
            m2.a(z2);
            m2.a(str);
            Mimc.MIMCPacket.Builder p2 = Mimc.MIMCPacket.p();
            String b2 = b();
            p2.a(b2);
            p2.b(o());
            p2.a(Mimc.MIMC_MSG_TYPE.P2T_MESSAGE);
            p2.c(((Mimc.MIMCP2TMessage) m2.Z()).J());
            this.X.put(b2, new TimeoutPacket((Mimc.MIMCPacket) p2.Z(), System.currentTimeMillis()));
            MIMCLog.b(f11156a, String.format("TimeoutMessageLog sendGroupMessage timeoutPackets put packetId:%s", new Object[]{b2}));
            b(b2, ((Mimc.MIMCPacket) p2.Z()).K(), MIMCConstant.Y);
            MIMCLog.b(f11156a, String.format("sendGroupMessage push packet. packetLen:%d", new Object[]{Integer.valueOf(((Mimc.MIMCPacket) p2.Z()).K().length)}));
            return b2;
        }
    }

    public String c() {
        if (V() != MIMCConstant.OnlineStatus.ONLINE) {
            MIMCLog.c(f11156a, String.format("pull, FailedNotOnline, uuid:%d", new Object[]{Long.valueOf(j())}));
            return null;
        }
        Mimc.MIMCPull.Builder f2 = Mimc.MIMCPull.f();
        f2.a(j());
        f2.a(n());
        Mimc.MIMCPacket.Builder p2 = Mimc.MIMCPacket.p();
        String b2 = b();
        p2.a(b2);
        p2.b(this.k);
        p2.a(Mimc.MIMC_MSG_TYPE.PULL);
        p2.c(((Mimc.MIMCPull) f2.Z()).J());
        b(b2, ((Mimc.MIMCPacket) p2.Z()).K(), MIMCConstant.Y);
        return b2;
    }

    public long a(String str) {
        return a(str, (String) null, (byte[]) null);
    }

    public long b(String str, byte[] bArr) {
        return a(str, (String) null, bArr);
    }

    public long a(String str, String str2) {
        return a(str, str2, (byte[]) null);
    }

    public synchronized long a(String str, String str2, byte[] bArr) {
        long au;
        String str3 = str;
        String str4 = str2;
        synchronized (this) {
            MIMCLog.b(f11156a, String.format("dialCall toAppAccount:%s, toResource:%s", new Object[]{str3, str4}));
            if (V() != MIMCConstant.OnlineStatus.ONLINE) {
                MIMCLog.c(f11156a, String.format("dialCall, FailedNotOnline, uuid:%d", new Object[]{Long.valueOf(j())}));
                return -1;
            } else if (u() == null) {
                MIMCLog.c(f11156a, "dialCall, Please register rts call handler by registerRtsCallHandler().");
                return -1;
            } else if (this.Y.size() >= this.E) {
                MIMCLog.c(f11156a, String.format("dialCall, this account:%s is busy.", new Object[]{q()}));
                return -1;
            } else {
                RtsSignal.UserInfo.Builder E2 = RtsSignal.UserInfo.E();
                E2.b(k());
                E2.b(str3);
                if (str4 != null) {
                    E2.a(str4);
                }
                do {
                    au = au();
                } while (x().containsKey(Long.valueOf(au)));
                MIMCLog.b(f11156a, String.format("dialCall callId:%d", new Object[]{Long.valueOf(au)}));
                if (L() == RelayState.NOT_CREATED) {
                    MIMCLog.b(f11156a, "dialCall RelayState.NOT_CREATED");
                    this.Y.put(Long.valueOf(au), new P2PCallSession(au, (RtsSignal.UserInfo) E2.Z(), RtsSignal.CallType.SINGLE_CALL, P2PCallSession.CallState.WAIT_SEND_CREATE_REQUEST, System.currentTimeMillis(), true, bArr, this.N));
                    MIMCLog.b(f11156a, String.format("dialCall rtsCalls.put callId:%d, callState:%s", new Object[]{Long.valueOf(au), P2PCallSession.CallState.WAIT_SEND_CREATE_REQUEST}));
                    RTSUtils.a(this);
                    return au;
                } else if (L() == RelayState.BEING_CREATED) {
                    MIMCLog.b(f11156a, "dialCall RelayState.BEING_CREATED");
                    this.Y.put(Long.valueOf(au), new P2PCallSession(au, (RtsSignal.UserInfo) E2.Z(), RtsSignal.CallType.SINGLE_CALL, P2PCallSession.CallState.WAIT_SEND_CREATE_REQUEST, System.currentTimeMillis(), true, bArr, this.N));
                    MIMCLog.b(f11156a, String.format("dialCall rtsCalls.put callId:%d, callState:%s", new Object[]{Long.valueOf(au), P2PCallSession.CallState.WAIT_SEND_CREATE_REQUEST}));
                    return au;
                } else if (L() != RelayState.SUCC_CREATED) {
                    return -1;
                } else {
                    MIMCLog.b(f11156a, "dialCall RelayState.SUCC_CREATED");
                    this.Y.put(Long.valueOf(au), new P2PCallSession(au, (RtsSignal.UserInfo) E2.Z(), RtsSignal.CallType.SINGLE_CALL, P2PCallSession.CallState.WAIT_RECEIVE_CREATE_RESPONSE, System.currentTimeMillis(), true, bArr, this.N));
                    MIMCLog.b(f11156a, String.format("dialCall rtsCalls.put callId:%d, callState:%s", new Object[]{Long.valueOf(au), P2PCallSession.CallState.WAIT_RECEIVE_CREATE_RESPONSE}));
                    RTSUtils.a(this, au);
                    return au;
                }
            }
        }
    }

    private long au() {
        Random random = new Random();
        long nextInt = (long) random.nextInt(128);
        for (int i2 = 0; i2 < 7; i2++) {
            nextInt = (nextInt << 8) | ((long) random.nextInt(256));
        }
        return nextInt & Long.MAX_VALUE;
    }

    public int a(long j2, byte[] bArr, RtsDataType rtsDataType, XMDPacket.DataPriority dataPriority, boolean z2, Object obj) {
        return a(j2, bArr, rtsDataType, dataPriority, z2, 0, (RtsChannelType) null, obj);
    }

    public int a(long j2, byte[] bArr, RtsDataType rtsDataType, XMDPacket.DataPriority dataPriority, boolean z2, int i2, Object obj) {
        return a(j2, bArr, rtsDataType, dataPriority, z2, i2, (RtsChannelType) null, obj);
    }

    public int a(long j2, byte[] bArr, RtsDataType rtsDataType, XMDPacket.DataPriority dataPriority, boolean z2, int i2, RtsChannelType rtsChannelType, Object obj) {
        byte[] bArr2 = bArr;
        RtsDataType rtsDataType2 = rtsDataType;
        RtsChannelType rtsChannelType2 = rtsChannelType;
        MIMCLog.a(f11156a, String.format("sendRtsData callId:%d", new Object[]{Long.valueOf(j2)}));
        if (bArr2.length > 524288) {
            MIMCLog.c(f11156a, String.format("sendRtsData invalid dataLen len:%d > MAX_SEND_DATA_LEN, callId:%d", new Object[]{Integer.valueOf(bArr2.length), Long.valueOf(j2)}));
            return -1;
        } else if (rtsDataType2 != RtsDataType.VIDEO && rtsDataType2 != RtsDataType.AUDIO) {
            MIMCLog.c(f11156a, String.format("sendRtsData dataType error, dataType:%s", new Object[]{rtsDataType2}));
            return -1;
        } else if (!this.Y.containsKey(Long.valueOf(j2)) && !this.Z.containsKey(Long.valueOf(j2))) {
            MIMCLog.c(f11156a, String.format("sendRtsData callId:%d is invalid.", new Object[]{Long.valueOf(j2)}));
            return -1;
        } else if (!this.Y.containsKey(Long.valueOf(j2)) || ((P2PCallSession) this.Y.get(Long.valueOf(j2))).b() == P2PCallSession.CallState.RUNNING) {
            RtsData.PKT_TYPE pkt_type = rtsDataType2 == RtsDataType.VIDEO ? RtsData.PKT_TYPE.USER_DATA_VIDEO : RtsData.PKT_TYPE.USER_DATA_AUDIO;
            long j3 = j2;
            MIMCContext mIMCContext = new MIMCContext(j2, obj);
            if (rtsChannelType2 == RtsChannelType.RELAY || (rtsChannelType2 == null && this.Z.containsKey(Long.valueOf(j2)))) {
                MIMCLog.a(f11156a, "SEND RTS_DATA BY RELAY");
                return RTSUtils.a(this, j2, bArr, dataPriority, z2, i2, pkt_type, mIMCContext);
            } else if (rtsChannelType2 == RtsChannelType.P2P_INTRANET) {
                MIMCLog.a(f11156a, "SEND RTS_DATA BY P2P INTRANET");
                return RTSUtils.b(this, j2, bArr, dataPriority, z2, i2, pkt_type, mIMCContext);
            } else if (rtsChannelType2 == RtsChannelType.P2P_INTERNET) {
                MIMCLog.a(f11156a, "SEND RTS_DATA BY P2P INTERNET");
                return RTSUtils.c(this, j2, bArr, dataPriority, z2, i2, pkt_type, mIMCContext);
            } else if (rtsChannelType2 != null) {
                MIMCLog.c(f11156a, "SEND_RTS_DATA CHANNEL_TYPE ERROR");
                return -1;
            } else if (a(j2)) {
                MIMCLog.a(f11156a, "SEND RTS_DATA BY P2P INTRANET");
                return RTSUtils.b(this, j2, bArr, dataPriority, z2, i2, pkt_type, mIMCContext);
            } else if (b(j2)) {
                MIMCLog.a(f11156a, "SEND RTS_DATA BY P2P INTERNET");
                return RTSUtils.c(this, j2, bArr, dataPriority, z2, i2, pkt_type, mIMCContext);
            } else if (L() == RelayState.SUCC_CREATED) {
                MIMCLog.a(f11156a, "SEND RTS_DATA BY RELAY");
                return RTSUtils.a(this, j2, bArr, dataPriority, z2, i2, pkt_type, mIMCContext);
            } else {
                MIMCLog.c(f11156a, "NO AVAILABLE CHANNEL TO SEND RTS_DATA");
                return -1;
            }
        } else {
            MIMCLog.c(f11156a, String.format("sendRtsData the callState is not running, callId:%d", new Object[]{Long.valueOf(j2)}));
            return -1;
        }
    }

    public boolean a(long j2) {
        P2PCallSession p2PCallSession = (P2PCallSession) x().get(Long.valueOf(j2));
        if (p2PCallSession == null || !p2PCallSession.j() || p2PCallSession.c() == -1) {
            return false;
        }
        return true;
    }

    public boolean b(long j2) {
        P2PCallSession p2PCallSession = (P2PCallSession) x().get(Long.valueOf(j2));
        if (p2PCallSession == null || !p2PCallSession.k() || p2PCallSession.f() == -1) {
            return false;
        }
        return true;
    }

    public long c(long j2) {
        P2PCallSession p2PCallSession = (P2PCallSession) x().get(Long.valueOf(j2));
        if (p2PCallSession == null) {
            return -1;
        }
        return p2PCallSession.c();
    }

    public long d(long j2) {
        P2PCallSession p2PCallSession = (P2PCallSession) x().get(Long.valueOf(j2));
        if (p2PCallSession == null) {
            return -1;
        }
        return p2PCallSession.f();
    }

    public void e(long j2) {
        a(j2, (String) null);
    }

    public void a(long j2, String str) {
        if (!this.Y.containsKey(Long.valueOf(j2))) {
            MIMCLog.c(f11156a, String.format("callId:%d is not exist", new Object[]{Long.valueOf(j2)}));
            return;
        }
        RTSUtils.a(this, j2, str);
        if (u() != null) {
            u().a(j2, MIMCConstant.L);
        }
        MIMCLog.b(f11156a, String.format("CLOSED_INITIATIVELY, callId:%d", new Object[]{Long.valueOf(j2)}));
        RTSUtils.a(j2, this);
        x().remove(Long.valueOf(j2));
        MIMCLog.b(f11156a, String.format("in recv_inviteRequest, currentCalls.remove callId:%d", new Object[]{Long.valueOf(j2)}));
        RTSUtils.c(this);
    }

    public long a(byte[] bArr) {
        long au;
        if (V() != MIMCConstant.OnlineStatus.ONLINE) {
            MIMCLog.c(f11156a, String.format("createChannel, this account:%s uuid:%d is not online.", new Object[]{q(), Long.valueOf(j())}));
            g().a(-1, -1, "", false, String.format("This %s is not online.", new Object[]{q()}), bArr);
            return -1;
        } else if (g() == null) {
            MIMCLog.c(f11156a, "createChannel, Please register channel handler by registerChannelHandler().");
            g().a(-1, -1, "", false, "Please register channel handler by registerChannelHandler().", bArr);
            return -1;
        } else {
            do {
                au = au();
            } while (z().containsKey(Long.valueOf(au)));
            MIMCLog.b(f11156a, String.format("createChannel build identity:%d", new Object[]{Long.valueOf(au)}));
            if (L() == RelayState.NOT_CREATED) {
                MIMCLog.b(f11156a, "createChannel RelayState.NOT_CREATED AND THEN WAIT_SEND_CREATE_CHANNEL_REQUEST");
                RTSUtils.a(this);
                z().put(Long.valueOf(au), new TempChannelSession(System.currentTimeMillis(), bArr));
            } else if (L() == RelayState.BEING_CREATED) {
                MIMCLog.b(f11156a, "createChannel RelayState.BEING_CREATED");
                z().put(Long.valueOf(au), new TempChannelSession(System.currentTimeMillis(), bArr));
            } else if (L() == RelayState.SUCC_CREATED) {
                MIMCLog.b(f11156a, "createChannel RelayState.SUCC_CREATED AND THEN BEGIN_SEND_CREATE_CHANNEL_REQUEST");
                z().put(Long.valueOf(au), new TempChannelSession(System.currentTimeMillis(), bArr));
                if (!RTSUtils.a(this, au, bArr)) {
                    return -1;
                }
            }
            return au;
        }
    }

    public void b(long j2, String str) {
        if (V() != MIMCConstant.OnlineStatus.ONLINE) {
            MIMCLog.c(f11156a, String.format("joinChannel, this uuid:%d is not online. callId:%d callKey:%s", new Object[]{Long.valueOf(j()), Long.valueOf(j2), str}));
            long j3 = j2;
            g().a(j3, q(), n(), false, String.format("This %s is not online.", new Object[]{q()}), (byte[]) null, (List<ChannelUser>) null);
        } else if (g() == null) {
            MIMCLog.c(f11156a, String.format("joinChannel, please register channel handler by registerChannelHandler(). callId:%d callKey:%s", new Object[]{Long.valueOf(j2), str}));
            g().a(j2, q(), n(), false, "Please register channel handler by registerChannelHandler().", (byte[]) null, (List<ChannelUser>) null);
        } else if (L() == RelayState.NOT_CREATED) {
            MIMCLog.b(f11156a, String.format("joinChannel RelayState.NOT_CREATED AND THEN WAIT_SEND_JOIN_CHANNEL_REQUEST. callId:%d callKey:%s", new Object[]{Long.valueOf(j2), str}));
            RTSUtils.a(this);
            if (!y().containsKey(Long.valueOf(j2))) {
                y().put(Long.valueOf(j2), new ChannelSession(RtsSignal.CallType.CHANNEL_CALL, j2, str, RTSUtils.e(this), System.currentTimeMillis()));
                return;
            }
            g().a(j2, q(), n(), true, "ALREADY_IN_SESSION", ((ChannelSession) y().get(Long.valueOf(j2))).c(), f(j2));
        } else if (L() == RelayState.BEING_CREATED) {
            MIMCLog.b(f11156a, String.format("joinChannel RelayState.BEING_CREATED. callId:%d callKey:%s", new Object[]{Long.valueOf(j2), str}));
            if (!y().containsKey(Long.valueOf(j2))) {
                y().put(Long.valueOf(j2), new ChannelSession(RtsSignal.CallType.CHANNEL_CALL, j2, str, RTSUtils.e(this), System.currentTimeMillis()));
                return;
            }
            g().a(j2, q(), n(), true, "ALREADY_IN_SESSION", ((ChannelSession) y().get(Long.valueOf(j2))).c(), f(j2));
        } else if (L() == RelayState.SUCC_CREATED) {
            MIMCLog.b(f11156a, String.format("joinChannel RelayState.SUCC_CREATED AND THEN BEGIN_SEND_JOIN_CHANNEL_REQUEST. callId:%d callKey:%s", new Object[]{Long.valueOf(j2), str}));
            if (!y().containsKey(Long.valueOf(j2))) {
                y().put(Long.valueOf(j2), new ChannelSession(RtsSignal.CallType.CHANNEL_CALL, j2, str, RTSUtils.e(this), System.currentTimeMillis()));
                RTSUtils.b(this, j2, str);
                return;
            }
            g().a(j2, q(), n(), true, "ALREADY_IN_SESSION", ((ChannelSession) y().get(Long.valueOf(j2))).c(), f(j2));
        }
    }

    public void c(long j2, String str) {
        if (V() != MIMCConstant.OnlineStatus.ONLINE) {
            MIMCLog.c(f11156a, String.format("leaveChannel, this uuid:%d is not online. callId:%d callKey:%s", new Object[]{Long.valueOf(j()), Long.valueOf(j2), str}));
            long j3 = j2;
            g().a(j3, q(), n(), false, String.format("This %s is not online.", new Object[]{q()}));
        } else if (!y().containsKey(Long.valueOf(j2))) {
            MIMCLog.c(f11156a, String.format("leaveChannel this callId:%d is not in channels.", new Object[]{Long.valueOf(j2)}));
            long j4 = j2;
            g().a(j4, q(), n(), false, String.format("This %d is not in channels.", new Object[]{Long.valueOf(j2)}));
        } else {
            y().remove(Long.valueOf(j2));
            g().a(j2, q(), n(), true, "LEAVE_CHANNEL");
            MIMCLog.b(f11156a, String.format("leaveChannel BEGIN_SEND_LEAVE_CHANNEL_REQUEST. callId:%d callKey:%s", new Object[]{Long.valueOf(j2), str}));
            RTSUtils.c(this, j2, str);
        }
    }

    public List<ChannelUser> f(long j2) {
        ChannelSession channelSession = (ChannelSession) y().get(Long.valueOf(j2));
        if (channelSession == null) {
            MIMCLog.d(f11156a, String.format("getChannelUsers this callId:%d is not in channels.", new Object[]{Long.valueOf(j2)}));
            return null;
        }
        ArrayList arrayList = new ArrayList();
        for (RtsSignal.UserInfo next : channelSession.d()) {
            arrayList.add(new ChannelUser(next.i(), next.d()));
        }
        return arrayList;
    }

    public void a(String str, Object obj) {
        if (V() != MIMCConstant.OnlineStatus.ONLINE) {
            MIMCLog.c(f11156a, String.format("createUnlimitedGroup, FailNotOnline, uuid:%d", new Object[]{Long.valueOf(j())}));
            return;
        }
        this.af = obj;
        HashMap hashMap = new HashMap();
        hashMap.put("token", m());
        hashMap.put("Content-Type", "application/json");
        HttpUtils.a(this.M + "api/uctopic", hashMap, "{ \"topicName\":\"" + str + "\"}", new HttpUtils.CallBack() {
            public void a(String str) {
                MIMCLog.b(MIMCUser.f11156a, String.format("createUnlimitedGroup:%s uuid:%d", new Object[]{str, Long.valueOf(MIMCUser.this.j())}));
                try {
                    JSONObject jSONObject = new JSONObject(str);
                    String l = jSONObject.l("message");
                    if (jSONObject.h("code") == 200) {
                        JSONObject j = jSONObject.j("data");
                        long parseLong = Long.parseLong(j.l("topicId"));
                        String l2 = j.l("topicName");
                        MIMCUser.this.b(parseLong, (Object) null);
                        if (MIMCUser.this.v() != null) {
                            MIMCUser.this.v().a(parseLong, l2, 0, l, MIMCUser.this.af);
                        }
                    } else if (MIMCUser.this.v() != null) {
                        MIMCUser.this.v().a(-1, "", -1, l, MIMCUser.this.af);
                    }
                } catch (JSONException e) {
                    MIMCLog.d(MIMCUser.f11156a, "Create unlimited group exception:", e);
                }
            }

            public void a(Exception exc) {
                if (MIMCUser.this.v() != null) {
                    MIMCUser.this.v().a(-1, "", -1, exc.getMessage(), MIMCUser.this.af);
                }
            }
        });
    }

    public void a(final long j2, Object obj) {
        if (V() != MIMCConstant.OnlineStatus.ONLINE) {
            MIMCLog.c(f11156a, String.format("dismissUnlimitedGroup, FailNotOnline, uuid:%d", new Object[]{Long.valueOf(j())}));
            return;
        }
        this.ai = obj;
        HashMap hashMap = new HashMap();
        hashMap.put("token", m());
        hashMap.put("topicId", String.valueOf(j2));
        hashMap.put("Content-Type", "application/json");
        HttpUtils.b(this.M + "api/uctopic", (Map<String, String>) hashMap, (HttpUtils.CallBack) new HttpUtils.CallBack() {
            public void a(String str) {
                MIMCLog.b(MIMCUser.f11156a, String.format("dismissUnlimitedGroup:%s", new Object[]{str}));
                try {
                    JSONObject jSONObject = new JSONObject(str);
                    String l = jSONObject.l("message");
                    if (jSONObject.h("code") == 200) {
                        if (MIMCUser.this.v() != null) {
                            MIMCUser.this.v().c(j2, 0, l, MIMCUser.this.ai);
                        }
                    } else if (MIMCUser.this.v() != null) {
                        MIMCUser.this.v().c(j2, -1, l, MIMCUser.this.ai);
                    }
                } catch (JSONException e) {
                    MIMCLog.d(MIMCUser.f11156a, "Dismiss unlimited group exception:", e);
                }
            }

            public void a(Exception exc) {
                MIMCLog.b(MIMCUser.f11156a, String.format("dismissUnlimitedGroup:%s", new Object[]{exc.getMessage()}));
                if (MIMCUser.this.v() != null) {
                    MIMCUser.this.v().c(j2, -1, exc.getMessage(), MIMCUser.this.ai);
                }
            }
        });
    }

    public String b(long j2, Object obj) {
        if (V() != MIMCConstant.OnlineStatus.ONLINE) {
            MIMCLog.c(f11156a, String.format("joinUnlimitedGroup, FailNotOnline, uuid:%d", new Object[]{Long.valueOf(j())}));
            return null;
        }
        this.ag = obj;
        Mimc.UCJoin.Builder c2 = Mimc.UCJoin.c();
        String b2 = b();
        Mimc.MIMCPacket mIMCPacket = (Mimc.MIMCPacket) Mimc.MIMCPacket.p().a(b2).b(this.k).a(Mimc.MIMC_MSG_TYPE.UC_PACKET).c(ByteString.copyFrom(((Mimc.UCPacket) Mimc.UCPacket.j().a(b2).a(Mimc.UC_MSG_TYPE.JOIN).a((Mimc.MIMCUser) Mimc.MIMCUser.l().a(this.i).a(this.j).b(j()).b(this.q).Z()).a(ByteString.copyFrom(((Mimc.UCJoin) c2.a((Mimc.UCGroup) Mimc.UCGroup.e().a(this.i).b(j2).Z()).Z()).K())).Z()).K())).Z();
        b(b2, mIMCPacket.K(), MIMCConstant.Y);
        MIMCLog.b(f11156a, String.format("joinUnlimitedGroup push packet. packetLen:%d", new Object[]{Integer.valueOf(mIMCPacket.K().length)}));
        return b2;
    }

    public String c(long j2, Object obj) {
        if (V() != MIMCConstant.OnlineStatus.ONLINE) {
            MIMCLog.c(f11156a, String.format("quitUnlimitedGroup, FailNotOnline, uuid:%d", new Object[]{Long.valueOf(j())}));
            return null;
        }
        this.ah = obj;
        Mimc.UCQuit.Builder c2 = Mimc.UCQuit.c();
        String b2 = b();
        Mimc.MIMCPacket mIMCPacket = (Mimc.MIMCPacket) Mimc.MIMCPacket.p().a(b2).b(this.k).a(Mimc.MIMC_MSG_TYPE.UC_PACKET).c(ByteString.copyFrom(((Mimc.UCPacket) Mimc.UCPacket.j().a(b2).a(Mimc.UC_MSG_TYPE.QUIT).a((Mimc.MIMCUser) Mimc.MIMCUser.l().a(this.i).a(this.j).b(j()).b(this.q).Z()).a(ByteString.copyFrom(((Mimc.UCQuit) c2.a((Mimc.UCGroup) Mimc.UCGroup.e().a(this.i).b(j2).Z()).Z()).K())).Z()).K())).Z();
        b(b2, mIMCPacket.K(), MIMCConstant.Y);
        MIMCLog.b(f11156a, String.format("quitUnlimitedGroup push packet. packetLen:%d", new Object[]{Integer.valueOf(mIMCPacket.K().length)}));
        return b2;
    }

    public String b(long j2, byte[] bArr) {
        return b(j2, bArr, "", true);
    }

    public String b(long j2, byte[] bArr, boolean z2) {
        return b(j2, bArr, "", z2);
    }

    public String b(long j2, byte[] bArr, String str) {
        return b(j2, bArr, str, true);
    }

    public String b(long j2, byte[] bArr, String str, boolean z2) {
        if (bArr.length > 10240) {
            MIMCLog.c(f11156a, String.format("sendUnlimitedGroupMessage, invalid dataLen len:%d > MAX_MESSAGE_SIZE", new Object[]{Integer.valueOf(bArr.length)}));
            return null;
        } else if (V() != MIMCConstant.OnlineStatus.ONLINE) {
            MIMCLog.c(f11156a, String.format("sendUnlimitedGroupMessage, FailNotOnline, uuid:%d", new Object[]{Long.valueOf(j())}));
            return null;
        } else {
            Mimc.MIMCUser mIMCUser = (Mimc.MIMCUser) Mimc.MIMCUser.l().a(this.i).a(this.j).b(j()).b(this.q).Z();
            String b2 = b();
            Mimc.MIMCPacket mIMCPacket = (Mimc.MIMCPacket) Mimc.MIMCPacket.p().a(b2).b(this.k).a(Mimc.MIMC_MSG_TYPE.UC_PACKET).c(ByteString.copyFrom(((Mimc.UCPacket) Mimc.UCPacket.j().a(b2).a(Mimc.UC_MSG_TYPE.MESSAGE).a(mIMCUser).a(ByteString.copyFrom(((Mimc.UCMessage) Mimc.UCMessage.t().a((Mimc.UCGroup) Mimc.UCGroup.e().a(this.i).b(j2).Z()).a(z2).a(ByteString.copyFrom(bArr)).a(b2).a(mIMCUser).b(str).Z()).K())).Z()).K())).Z();
            this.X.put(b2, new TimeoutPacket(mIMCPacket, System.currentTimeMillis()));
            MIMCLog.b(f11156a, String.format("TimeoutMessageLog sendUnlimitedGroupMessage timeoutPackets put packetId:%s uuid:%d", new Object[]{b2, Long.valueOf(j())}));
            b(b2, mIMCPacket.K(), MIMCConstant.Y);
            MIMCLog.b(f11156a, String.format("sendUnlimitedGroupMessage push packet. packetLen:%d, packetId:%s, uuid:%d", new Object[]{Integer.valueOf(mIMCPacket.K().length), b2, Long.valueOf(j())}));
            return b2;
        }
    }

    public boolean logout() {
        MIMCLog.b(f11156a, String.format("%s logout", new Object[]{q()}));
        this.t = false;
        try {
            if (this.d != -1) {
                if (!(this.e == -1 || this.J == null)) {
                    this.J.a(this.d, this.e);
                }
                if (!(this.g == -1 || this.J == null)) {
                    this.J.a(this.d, this.g);
                }
                if (!(this.f == -1 || this.J == null)) {
                    this.J.a(this.d, this.f);
                }
                if (this.J != null) {
                    this.J.b(this.d);
                }
            }
            for (Map.Entry key : this.Y.entrySet()) {
                RTSUtils.a(this, ((Long) key.getKey()).longValue(), "LOGOUT");
            }
            for (Map.Entry entry : this.Z.entrySet()) {
                RTSUtils.c(this, ((Long) entry.getKey()).longValue(), ((ChannelSession) entry.getValue()).b());
            }
        } catch (Exception e2) {
            MIMCLog.d(f11156a, "Exception in logout, e:", e2);
        } catch (Throwable unused) {
        }
        this.Y.clear();
        this.Z.clear();
        this.X.clear();
        U();
        return av();
    }

    public boolean d() {
        return this.t;
    }

    public void a(int i2) {
        if (i2 < 0 || i2 > 100) {
            MIMCLog.c(f11156a, "packetLossRate is between 0 and 100");
            return;
        }
        this.aq = i2;
        if (this.J != null) {
            this.J.a(i2);
        }
    }

    public int e() {
        return this.aq;
    }

    public void f() {
        MIMCLog.b(f11156a, String.format("%s destroy", new Object[]{q()}));
        if (this.J != null) {
            this.J.c();
            this.J = null;
        }
        if (this.al != null) {
            this.al.a(true);
            this.al.interrupt();
            this.al = null;
        }
        if (this.ae != null) {
            this.ae.a(true);
            this.ae.interrupt();
            this.ae = null;
        }
        if (this.ak != null) {
            this.ak.a(true);
            this.ak.interrupt();
            this.ak = null;
        }
        if (this.aj != null) {
            this.aj.a(true);
            this.aj.interrupt();
            this.aj = null;
        }
        if (this.u != null) {
            this.u.a();
        }
        if (t() != null) {
            t().a(MIMCConstant.OnlineStatus.OFFLINE, "", Constants.Event.SLOT_LIFECYCLE.DESTORY, "");
        }
        if (!this.m.isEmpty()) {
            this.m.clear();
        }
        if (!this.n.isEmpty()) {
            this.n.clear();
        }
        if (!this.X.isEmpty()) {
            this.X.clear();
        }
        if (!this.Y.isEmpty()) {
            this.Y.clear();
        }
        if (!this.Z.isEmpty()) {
            this.Z.clear();
        }
        if (!this.aa.isEmpty()) {
            this.aa.clear();
        }
        if (!am.isEmpty()) {
            am.clear();
        }
        this.y = null;
        this.z = null;
        this.A = null;
    }

    public void a(MIMCOnlineStatusListener mIMCOnlineStatusListener) {
        if (mIMCOnlineStatusListener == null) {
            MIMCLog.d(f11156a, "OnlineStatusHandler, HandlerIsNull");
        } else {
            this.A = mIMCOnlineStatusListener;
        }
    }

    public void a(MIMCTokenFetcher mIMCTokenFetcher) {
        if (mIMCTokenFetcher == null) {
            MIMCLog.d(f11156a, "MIMCTokenFetcher, HandlerIsNull");
        } else {
            this.y = mIMCTokenFetcher;
        }
    }

    public void a(MIMCRtsCallHandler mIMCRtsCallHandler) {
        if (mIMCRtsCallHandler == null) {
            MIMCLog.d(f11156a, "RTSCallEventHandler, HandlerIsNull");
        } else {
            this.F = mIMCRtsCallHandler;
        }
    }

    public void a(MIMCMessageHandler mIMCMessageHandler) {
        if (mIMCMessageHandler == null) {
            MIMCLog.d(f11156a, "MIMCMessageHandler, HandlerIsNull");
        } else {
            this.z = mIMCMessageHandler;
        }
    }

    public void a(MIMCUnlimitedGroupHandler mIMCUnlimitedGroupHandler) {
        if (mIMCUnlimitedGroupHandler == null) {
            MIMCLog.d(f11156a, "MIMCUnlimitedGroupHandler, HandlerIsNull");
        } else {
            this.B = mIMCUnlimitedGroupHandler;
        }
    }

    public void a(MIMCRtsChannelHandler mIMCRtsChannelHandler) {
        if (mIMCRtsChannelHandler == null) {
            MIMCLog.d(f11156a, "MIMCRtsChannelHandler, HandlerIsNull");
        } else {
            this.D = mIMCRtsChannelHandler;
        }
    }

    public MIMCRtsChannelHandler g() {
        return this.D;
    }

    public void b(String str, String str2) {
        this.m.put(str, str2);
    }

    public void c(String str, String str2) {
        this.n.put(str, str2);
    }

    public static String a(Map<String, String> map) {
        if (map == null) {
            return "";
        }
        StringBuilder sb = new StringBuilder();
        for (Map.Entry next : map.entrySet()) {
            sb.append(String.format("%s:%s,", new Object[]{next.getKey(), next.getValue()}));
        }
        if (sb.length() > 1) {
            sb.setLength(sb.length() - 1);
        }
        return sb.toString();
    }

    public String h() {
        return a(this.m);
    }

    public String i() {
        return a(this.n);
    }

    public synchronized long j() {
        return this.p;
    }

    public long k() {
        return this.i;
    }

    public synchronized int l() {
        return this.o;
    }

    public synchronized void b(int i2) {
        this.o = i2;
    }

    public synchronized void g(long j2) {
        this.p = j2;
    }

    public synchronized void b(String str) {
        this.r = str;
    }

    public synchronized String m() {
        return this.s;
    }

    public synchronized void c(String str) {
        this.s = str;
    }

    public synchronized String n() {
        return this.q;
    }

    public synchronized String o() {
        return this.k;
    }

    public synchronized void d(String str) {
        this.k = str;
    }

    public boolean p() {
        return V() == MIMCConstant.OnlineStatus.ONLINE;
    }

    public String q() {
        return this.j;
    }

    public MIMCMessageHandler r() {
        return this.z;
    }

    public MIMCTokenFetcher s() {
        return this.y;
    }

    public MIMCOnlineStatusListener t() {
        return this.A;
    }

    public MIMCRtsCallHandler u() {
        return this.F;
    }

    public MIMCUnlimitedGroupHandler v() {
        return this.B;
    }

    public ConcurrentMap<String, TimeoutPacket> w() {
        return this.X;
    }

    public ConcurrentMap<Long, P2PCallSession> x() {
        return this.Y;
    }

    public ConcurrentMap<Long, ChannelSession> y() {
        return this.Z;
    }

    public ConcurrentMap<Long, TempChannelSession> z() {
        return this.aa;
    }

    public Set<Long> A() {
        return this.U;
    }

    public void h(long j2) {
        this.U.add(Long.valueOf(j2));
        Iterator<Long> it = this.U.iterator();
        while (it.hasNext() && this.U.size() > 500) {
            it.next();
            it.remove();
        }
    }

    public Set<Long> B() {
        return this.V;
    }

    public XMDTransceiver C() {
        if (this.J == null) {
            synchronized (this.K) {
                if (this.J == null) {
                    this.J = new XMDTransceiver();
                    this.G = new RTSDatagramHandler(this);
                    this.H = new RTSConnectionHandler(this);
                    this.I = new RTSStreamHandler(this);
                    this.J.a((DatagramHandler) this.G);
                    this.J.a((ConnectionHandler) this.H);
                    this.J.a((StreamHandler) this.I);
                    this.J.b();
                }
            }
        }
        return this.J;
    }

    public Set<Long> D() {
        return this.W;
    }

    public RTSStreamHandler E() {
        return this.I;
    }

    public RTSConnectionHandler F() {
        return this.H;
    }

    public long G() {
        return this.d;
    }

    public short H() {
        return this.e;
    }

    public void i(long j2) {
        this.d = j2;
    }

    public void a(short s2) {
        this.e = s2;
    }

    public short I() {
        return this.f;
    }

    public void b(short s2) {
        this.f = s2;
    }

    public short J() {
        return this.g;
    }

    public void c(short s2) {
        this.g = s2;
    }

    public RtsData.BindRelayResponse K() {
        return this.h;
    }

    public void a(RtsData.BindRelayResponse bindRelayResponse) {
        this.h = bindRelayResponse;
    }

    public synchronized RelayState L() {
        return this.b;
    }

    public synchronized void a(RelayState relayState) {
        this.b = relayState;
    }

    public long M() {
        return this.c;
    }

    public void j(long j2) {
        this.c = j2;
    }

    public String N() {
        return this.L;
    }

    public void e(String str) {
        this.L = str;
    }

    public String O() {
        return this.M;
    }

    public void f(String str) {
        this.O = str;
    }

    public String P() {
        return this.O;
    }

    public void g(String str) {
        this.P = str;
    }

    public String Q() {
        return this.P;
    }

    public void h(String str) {
        this.Q = str;
    }

    public String R() {
        return this.Q;
    }

    public void i(String str) {
        this.R = str;
    }

    public String S() {
        return this.R;
    }

    public String T() {
        return this.ac;
    }

    public void U() {
        this.h = null;
        this.d = -1;
        this.e = -1;
        this.g = -1;
        this.f = -1;
        this.b = RelayState.NOT_CREATED;
        this.c = System.currentTimeMillis();
        MIMCLog.b(f11156a, "logout clearLocalRelayStateAndTs()");
    }

    public synchronized MIMCConstant.OnlineStatus V() {
        return this.l;
    }

    public synchronized void a(MIMCConstant.OnlineStatus onlineStatus) {
        MIMCLog.b(f11156a, String.format("uuid:%d set setOnlineStatus %s", new Object[]{Long.valueOf(this.p), onlineStatus}));
        this.l = onlineStatus;
    }

    public String W() {
        return this.r;
    }

    public long X() {
        return this.v;
    }

    public void k(long j2) {
        this.v = j2;
    }

    public long Y() {
        return this.w;
    }

    public void l(long j2) {
        this.w = j2;
    }

    public long Z() {
        return this.x;
    }

    public void m(long j2) {
        this.x = j2;
    }

    public UserMessageHandler aa() {
        return this.C;
    }

    public Connection ab() {
        return this.u;
    }

    public String ac() {
        return this.ab;
    }

    public void c(int i2) {
        this.an = i2;
    }

    public int ad() {
        return this.an;
    }

    public void d(int i2) {
        this.ao = i2;
    }

    public int ae() {
        return this.ao;
    }

    public void e(int i2) {
        this.ap = i2;
    }

    public int af() {
        return this.ap;
    }

    public int ag() {
        return this.E;
    }

    public void f(int i2) {
        this.E = i2;
    }

    public Object ah() {
        return this.af;
    }

    public Object ai() {
        return this.ag;
    }

    public Object aj() {
        return this.ah;
    }

    public Object ak() {
        return this.ai;
    }

    public long n(long j2) {
        P2PCallSession p2PCallSession = (P2PCallSession) x().get(Long.valueOf(j2));
        if (p2PCallSession == null) {
            return 0;
        }
        return p2PCallSession.n();
    }

    public long al() {
        return this.N;
    }

    public void o(long j2) {
        this.N = j2;
    }

    private boolean av() {
        if (!p()) {
            MIMCLog.b(f11156a, String.format("logout, %s status is OFFLINE, status:%s", new Object[]{q(), this.l}));
            return true;
        }
        ImsPushService.ClientHeader a2 = MIMCUtils.a(this, MIMCConstant.o);
        V6Packet v6Packet = new V6Packet();
        v6Packet.a(a2);
        this.u.a(new MIMCObject(MIMCConstant.Y, v6Packet));
        MIMCLog.b(f11156a, String.format("logout push Packet:%s, header:%s, packet:%s", new Object[]{a2.m(), a2, v6Packet}));
        return true;
    }

    public void b(String str, byte[] bArr, String str2) {
        V6Packet v6Packet = new V6Packet();
        v6Packet.a(MIMCUtils.a(this, MIMCConstant.p, 1, str));
        v6Packet.b(bArr);
        this.u.a(new MIMCObject(str2, v6Packet));
    }

    public void d(long j2, String str) {
        MIMCLog.c(f11156a, String.format("handleUDPConnClosed() connId:%d desc:%s", new Object[]{Long.valueOf(j2), str}));
        if (this.d == -1 || j2 != this.d) {
            for (Map.Entry entry : x().entrySet()) {
                long longValue = ((Long) entry.getKey()).longValue();
                P2PCallSession p2PCallSession = (P2PCallSession) entry.getValue();
                if (p2PCallSession.c() != -1 && j2 == p2PCallSession.c()) {
                    RTSUtils.b(longValue, this);
                } else if (p2PCallSession.f() != -1 && j2 == p2PCallSession.f()) {
                    RTSUtils.c(longValue, this);
                }
            }
            aw();
            return;
        }
        MIMCLog.c(f11156a, String.format("Connection is closed abnormally, connId:%d, desc:%s", new Object[]{Long.valueOf(j2), str}));
        U();
        aw();
    }

    private void aw() {
        if (this.d == -1) {
            ax();
            ay();
        }
    }

    private void ax() {
        ArrayList<Long> arrayList = new ArrayList<>();
        for (Map.Entry entry : x().entrySet()) {
            long longValue = ((Long) entry.getKey()).longValue();
            P2PCallSession p2PCallSession = (P2PCallSession) entry.getValue();
            if (p2PCallSession.c() == -1 && p2PCallSession.f() == -1) {
                RTSUtils.a(this, longValue, MIMCConstant.K);
                if (u() != null) {
                    u().a(longValue, MIMCConstant.K);
                }
                arrayList.add(Long.valueOf(longValue));
                MIMCLog.c(f11156a, String.format("checkAndCloseCalls() remove callId:%d", new Object[]{Long.valueOf(longValue)}));
            }
        }
        for (Long remove : arrayList) {
            x().remove(remove);
        }
    }

    private void ay() {
        for (Map.Entry key : y().entrySet()) {
            long longValue = ((Long) key.getKey()).longValue();
            if (g() != null) {
                y().remove(Long.valueOf(longValue));
                g().a(longValue, q(), n(), true, MIMCConstant.K);
            }
        }
    }

    private static String i(int i2) {
        StringBuilder sb = new StringBuilder();
        Random random = new Random();
        for (int i3 = 0; i3 < i2; i3++) {
            sb.append("abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ".charAt(random.nextInt("abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ".length())));
        }
        return sb.toString();
    }

    public void a(MIMCStreamConfig mIMCStreamConfig) {
        if (mIMCStreamConfig != null) {
            this.ar = mIMCStreamConfig;
        }
    }

    public MIMCStreamConfig am() {
        return this.ar;
    }

    public void b(MIMCStreamConfig mIMCStreamConfig) {
        if (mIMCStreamConfig != null) {
            this.as = mIMCStreamConfig;
        }
    }

    public MIMCStreamConfig an() {
        return this.as;
    }

    public void g(int i2) {
        C().b(i2);
    }

    public int ao() {
        return C().r();
    }

    public void ap() {
        C().t();
    }

    public float aq() {
        return C().p();
    }

    public void h(int i2) {
        C().c(i2);
    }

    public int ar() {
        return C().s();
    }

    public void as() {
        C().u();
    }

    public float at() {
        return C().q();
    }
}
