package com.xiaomi.mimc.example;

import com.xiaomi.mimc.MIMCGroupMessage;
import com.xiaomi.mimc.MIMCMessage;
import com.xiaomi.mimc.MIMCMessageHandler;
import com.xiaomi.mimc.MIMCOnlineStatusListener;
import com.xiaomi.mimc.MIMCRtsCallHandler;
import com.xiaomi.mimc.MIMCRtsChannelHandler;
import com.xiaomi.mimc.MIMCServerAck;
import com.xiaomi.mimc.MIMCTokenFetcher;
import com.xiaomi.mimc.MIMCUser;
import com.xiaomi.mimc.common.MIMCConstant;
import com.xiaomi.mimc.data.ChannelUser;
import com.xiaomi.mimc.data.LaunchedResponse;
import com.xiaomi.mimc.data.RtsChannelType;
import com.xiaomi.mimc.data.RtsDataType;
import com.xiaomi.msg.logger.MIMCLog;
import java.util.List;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class ChannelTest {

    /* renamed from: a  reason: collision with root package name */
    private static final String f1469a = "ChannelTest";
    private static final String f = "http://10.38.162.149/api/account/token";
    private static long n = 500;
    private long b = 2882303761517479657L;
    private String c = "5221747911657";
    private String d = "PtfBeZyC+H8SIM/UXhZx1w==";
    private String e = "REGION_CN";
    private final String g = "http://10.38.162.117:6000/gslb/";
    private final String h = "user1";
    private final String i = "user2";
    private final String j = "user3";
    private MIMCUser k;
    private MIMCUser l;
    private MIMCUser m;
    /* access modifiers changed from: private */
    public long o;
    /* access modifiers changed from: private */
    public String p;

    @Before
    public void a() throws Exception {
        LogUtils.a();
        this.k = MIMCUser.a(this.b, "user1", "./files", "user1", "http://10.38.162.117:6000/gslb/", "http://10.38.162.149/");
        this.l = MIMCUser.a(this.b, "user2", "./files", "user2", "http://10.38.162.117:6000/gslb/", "http://10.38.162.149/");
        this.m = MIMCUser.a(this.b, "user3", "./files", "user3", "http://10.38.162.117:6000/gslb/", "http://10.38.162.149/");
        b(this.k);
        b(this.l);
        b(this.m);
    }

    @After
    public void b() throws Exception {
        this.k.f();
        this.l.f();
        this.m.f();
        Thread.sleep(3000);
    }

    private void b(final MIMCUser mIMCUser) {
        mIMCUser.a((MIMCTokenFetcher) new TokenFetcher(this.b, this.c, this.d, f, mIMCUser.q(), this.e));
        mIMCUser.a((MIMCOnlineStatusListener) new MIMCOnlineStatusListener() {
            public void a(MIMCConstant.OnlineStatus onlineStatus, String str, String str2, String str3) {
                MIMCLog.b(ChannelTest.f1469a, String.format("Online status changed, appAccount:%s, isOnline:%s, errType:%s, :%s, errDesc:%s", new Object[]{mIMCUser.q(), onlineStatus, str, str2, str3}));
            }
        });
        mIMCUser.a((MIMCMessageHandler) new MIMCMessageHandler() {
            public void a(MIMCGroupMessage mIMCGroupMessage) {
            }

            public void a(MIMCMessage mIMCMessage) {
            }

            public void b(MIMCGroupMessage mIMCGroupMessage) {
            }

            public void b(List<MIMCGroupMessage> list) {
            }

            public void c(List<MIMCGroupMessage> list) {
            }

            public void a(List<MIMCMessage> list) {
                for (MIMCMessage next : list) {
                    MIMCLog.b(ChannelTest.f1469a, String.format("Received message, P2P, %s--------------------------------->%s, packetId:%s, payload:%s", new Object[]{next.d(), mIMCUser.q(), next.a(), new String(next.h())}));
                }
            }

            public void a(MIMCServerAck mIMCServerAck) {
                MIMCLog.b(ChannelTest.f1469a, String.format("Received message ack, serverAck:%s", new Object[]{mIMCServerAck}));
            }
        });
        mIMCUser.a((MIMCRtsChannelHandler) new MIMCRtsChannelHandler() {
            public void a(long j, int i, Object obj) {
            }

            public void b(long j, int i, Object obj) {
            }

            public void a(long j, long j2, String str, boolean z, String str2, byte[] bArr) {
                MIMCLog.b(ChannelTest.f1469a, String.format("onCreateChannel callId:%d callKey:%s success:%s desc:%s identity:%s extra:%s", new Object[]{Long.valueOf(j2), str, Boolean.valueOf(z), str2, Long.valueOf(j), bArr}));
                long unused = ChannelTest.this.o = j2;
                String unused2 = ChannelTest.this.p = str;
            }

            public void a(long j, String str, String str2, boolean z, String str3, byte[] bArr, List<ChannelUser> list) {
                MIMCLog.b(ChannelTest.f1469a, String.format("onJoinChannel callId:%d callKey:%s account:%s resource:%s success:%s desc:%s extra:%s members.size:%d", new Object[]{Long.valueOf(j), ChannelTest.this.p, str, str2, Boolean.valueOf(z), str3, bArr, Integer.valueOf(list.size())}));
            }

            public void a(long j, String str, String str2, boolean z, String str3) {
                MIMCLog.b(ChannelTest.f1469a, String.format("onLeaveChannel account:%s resource:%s success:%s desc:%s", new Object[]{Long.valueOf(j), ChannelTest.this.p, Boolean.valueOf(z), str3}));
            }

            public void a(long j, String str, String str2) {
                MIMCLog.b(ChannelTest.f1469a, String.format("onUserJoined callId:%d account:%s resource:%s", new Object[]{Long.valueOf(j), str, str2}));
            }

            public void b(long j, String str, String str2) {
                MIMCLog.b(ChannelTest.f1469a, String.format("onUserLeft callId:%d account:%s resource:%s", new Object[]{Long.valueOf(j), str, str2}));
            }

            public void a(long j, String str, String str2, byte[] bArr, RtsDataType rtsDataType) {
                MIMCLog.b(ChannelTest.f1469a, String.format("handleData ------------------------->> callId:%d fromAccount:%s resource:%s data:%s dataType:%s", new Object[]{Long.valueOf(j), str, str2, new String(bArr), rtsDataType}));
            }
        });
        mIMCUser.a((MIMCRtsCallHandler) new MIMCRtsCallHandler() {
            public LaunchedResponse a(String str, String str2, long j, byte[] bArr) {
                return null;
            }

            public void a(long j, int i, Object obj) {
            }

            public void a(long j, String str) {
            }

            public void a(long j, String str, String str2, byte[] bArr, RtsDataType rtsDataType, RtsChannelType rtsChannelType) {
            }

            public void a(long j, boolean z, String str) {
            }

            public void b(long j, int i, Object obj) {
            }
        });
    }

    @Test
    public void c() throws Exception {
        a(this.k);
        this.o = 0;
        this.p = null;
        this.k.a((byte[]) null);
        Thread.sleep(3000);
        Assert.assertNotEquals(this.o, 0);
        MIMCLog.b(f1469a, String.format("callId:%d callKey:%s", new Object[]{Long.valueOf(this.o), this.p}));
        a(this.l);
        this.l.b(this.o, this.p);
        Thread.sleep(1000);
        this.l.b(this.o, this.p);
        Thread.sleep(3000);
        Thread.sleep(3000);
        Thread.sleep(60000);
        logout(this.k);
        logout(this.l);
    }

    public void a(MIMCUser mIMCUser) throws Exception {
        long currentTimeMillis = System.currentTimeMillis();
        mIMCUser.a();
        while (System.currentTimeMillis() - currentTimeMillis < 5000 && !mIMCUser.p()) {
            Thread.sleep(n);
        }
        Assert.assertTrue("LOGIN FAILED", mIMCUser.p());
    }

    public void logout(MIMCUser mIMCUser) throws Exception {
        long currentTimeMillis = System.currentTimeMillis();
        mIMCUser.logout();
        while (System.currentTimeMillis() - currentTimeMillis < 5000 && mIMCUser.p()) {
            Thread.sleep(n);
        }
        Assert.assertFalse("LOGOUT FAILED, USER STILL ONLINE", mIMCUser.p());
    }
}
