package com.xiaomi.mimc.client;

import com.xiaomi.mimc.MIMCUser;
import com.xiaomi.mimc.cipher.RC4;
import com.xiaomi.mimc.common.MIMCConstant;
import com.xiaomi.mimc.common.MIMCUtils;
import com.xiaomi.mimc.common.ResolverClient;
import com.xiaomi.mimc.data.MIMCObject;
import com.xiaomi.mimc.json.JSONArray;
import com.xiaomi.msg.logger.MIMCLog;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentLinkedQueue;

public class Connection {

    /* renamed from: a  reason: collision with root package name */
    private static final String f11164a = "Connection";
    private static final int i = 5000;
    private static final int s = 1000;
    private String b;
    private int c;
    private volatile ConnState d;
    private volatile byte[] e = null;
    private ConcurrentLinkedQueue<MIMCObject> f;
    private String g;
    private Socket h;
    private long j;
    private String k = "";
    private String l = "";
    private String m = "";
    private String n = "";
    private String o = "";
    private String p = "";
    private int q = 0;
    private long r = 0;
    private MIMCUser t;

    public enum ConnState {
        SOCKET_INIT,
        SOCKET_CONNECTED,
        HANDSHAKE_CONNECTED
    }

    public Connection(MIMCUser mIMCUser) {
        this.t = mIMCUser;
        this.j = -1;
        this.d = ConnState.SOCKET_INIT;
        this.f = new ConcurrentLinkedQueue<>();
        this.h = null;
    }

    public void a() {
        if (this.h != null) {
            try {
                this.h.close();
                this.h = null;
            } catch (IOException e2) {
                MIMCLog.d("Connection", "socket close exception, e:", e2);
            }
        }
    }

    public void b() {
        MIMCLog.b("Connection", "Connection is reset...");
        if (System.currentTimeMillis() - this.r < 1000) {
            MIMCLog.b("Connection", "Repeated call reset().");
            return;
        }
        this.r = System.currentTimeMillis();
        a();
        this.d = ConnState.SOCKET_INIT;
        this.t.k(0);
        this.t.l(0);
        this.t.a(MIMCConstant.OnlineStatus.OFFLINE);
        this.e = null;
        this.j = -1;
        this.t.t().a(MIMCConstant.OnlineStatus.OFFLINE, "", "NETWORK_RESET", "NETWORK_RESET");
    }

    public boolean c() {
        try {
            String R = this.t.R();
            int i2 = 5000;
            int i3 = 3;
            if (MIMCUtils.d(R)) {
                MIMCLog.b("Connection", "Memory do not contain the fe address.");
                R = MIMCUtils.a(this.t.ac(), this.t.T(), MIMCConstant.ah);
                if (MIMCUtils.d(R)) {
                    MIMCLog.b("Connection", String.format("Local category do not contain the fe address. Key:%s", new Object[]{MIMCConstant.ah}));
                    R = u();
                    if (MIMCUtils.d(R)) {
                        MIMCLog.c("Connection", String.format("Get fe address failed from resolver. Use resolver domain:%s and port:%d", new Object[]{this.t.P(), 80}));
                        long currentTimeMillis = System.currentTimeMillis();
                        try {
                            this.h = new Socket();
                            this.h.connect(new InetSocketAddress(this.t.P(), 80), 5000);
                        } catch (Exception e2) {
                            MIMCLog.d("Connection", String.format("Socket connect exception, host:%s, port:%d, cost:%d ms e:", new Object[]{this.t.P(), 80, Long.valueOf(System.currentTimeMillis() - currentTimeMillis)}), e2);
                        }
                        return this.h.isConnected();
                    }
                }
            }
            JSONArray jSONArray = new JSONArray(R);
            int i4 = 0;
            while (i4 < jSONArray.a()) {
                String[] split = jSONArray.l(i4).split(":");
                if (split.length != 2) {
                    MIMCLog.c("Connection", String.format("Get range failed, range.length:%d", new Object[]{Integer.valueOf(split.length)}));
                } else {
                    this.b = split[0];
                    this.c = Integer.valueOf(split[1]).intValue();
                    long currentTimeMillis2 = System.currentTimeMillis();
                    try {
                        this.h = new Socket();
                        this.h.connect(new InetSocketAddress(this.b, this.c), i2);
                        if (this.h.isConnected()) {
                            long currentTimeMillis3 = System.currentTimeMillis();
                            Object[] objArr = new Object[4];
                            objArr[0] = Boolean.valueOf(this.h.isConnected());
                            objArr[1] = this.b;
                            objArr[2] = Integer.valueOf(this.c);
                            objArr[i3] = Long.valueOf(currentTimeMillis3 - currentTimeMillis2);
                            MIMCLog.c("Connection", String.format("Socket connect success, isConnected:%b, host:%s, port:%d, cost:%d ms", objArr));
                            return true;
                        }
                        long currentTimeMillis4 = System.currentTimeMillis();
                        Object[] objArr2 = new Object[4];
                        objArr2[0] = Boolean.valueOf(this.h.isConnected());
                        objArr2[1] = this.b;
                        objArr2[2] = Integer.valueOf(this.c);
                        objArr2[i3] = Long.valueOf(currentTimeMillis4 - currentTimeMillis2);
                        MIMCLog.c("Connection", String.format("Socket connect fail, isConnected:%b, host:%s, port:%d, cost:%d ms", objArr2));
                    } catch (Exception e3) {
                        long currentTimeMillis5 = System.currentTimeMillis();
                        Object[] objArr3 = new Object[i3];
                        objArr3[0] = this.b;
                        objArr3[1] = Integer.valueOf(this.c);
                        objArr3[2] = Long.valueOf(currentTimeMillis5 - currentTimeMillis2);
                        MIMCLog.d("Connection", String.format("Socket connect exception, host:%s, port:%d, cost:%d ms e:", objArr3), e3);
                        v();
                    }
                }
                i4++;
                i2 = 5000;
                i3 = 3;
            }
            MIMCLog.c("Connection", String.format("All the fe %d address from memory or local file is not connected.", new Object[]{Integer.valueOf(jSONArray.a())}));
            v();
            long currentTimeMillis6 = System.currentTimeMillis();
            try {
                this.h = new Socket();
                this.h.connect(new InetSocketAddress(this.t.P(), 80), 5000);
            } catch (Exception e4) {
                MIMCLog.d("Connection", String.format("Socket connect exception. host:%s, port:%d, cost:%d ms e:", new Object[]{this.t.P(), 80, Long.valueOf(System.currentTimeMillis() - currentTimeMillis6)}), e4);
            }
            return this.h.isConnected();
        } catch (Exception e5) {
            MIMCLog.c("Connection", "Exception:", e5);
            v();
            return false;
        }
    }

    public void a(MIMCObject mIMCObject) {
        this.f.offer(mIMCObject);
    }

    public int a(byte[] bArr, int i2) {
        if (bArr == null || i2 <= 0) {
            Object[] objArr = new Object[2];
            objArr[0] = Boolean.valueOf(bArr == null);
            objArr[1] = Integer.valueOf(i2);
            MIMCLog.c("Connection", String.format("writen fail, param error, buffer==null:%b, length:%d", objArr));
            return -1;
        } else if (this.h == null || this.h.isClosed()) {
            MIMCLog.c("Connection", "writen fail, socket invalid");
            return -1;
        } else {
            try {
                OutputStream outputStream = this.h.getOutputStream();
                if (outputStream == null) {
                    MIMCLog.c("Connection", String.format("readn fail OutputStream is null, length:%d", new Object[]{Integer.valueOf(i2)}));
                    return -1;
                }
                outputStream.write(bArr, 0, i2);
                return i2;
            } catch (Exception e2) {
                MIMCLog.d("Connection", "writen exception, e:", e2);
                return -1;
            }
        }
    }

    public int b(byte[] bArr, int i2) {
        if (i2 <= 0 || bArr == null) {
            MIMCLog.b("Connection", String.format("readn fail length:%d", new Object[]{Integer.valueOf(i2)}));
            return -1;
        }
        try {
            if (this.h != null) {
                if (!this.h.isClosed()) {
                    InputStream inputStream = this.h.getInputStream();
                    if (inputStream == null) {
                        MIMCLog.c("Connection", "readn fail InputStream is null");
                        return -1;
                    }
                    int i3 = 0;
                    while (i3 < i2) {
                        int read = inputStream.read(bArr, i3, i2 - i3);
                        if (read < 0) {
                            MIMCLog.c("Connection", String.format("byteReadLen:%d < 0", new Object[]{Integer.valueOf(read)}));
                            return -1;
                        }
                        i3 += read;
                    }
                    return i2;
                }
            }
            Object[] objArr = new Object[2];
            objArr[0] = Integer.valueOf(i2);
            objArr[1] = this.h == null ? "socket is null." : "socket is closed.";
            MIMCLog.b("Connection", String.format("readn fail socket:%s", objArr));
            return -1;
        } catch (Exception e2) {
            MIMCLog.d("Connection", "readn fail,exception, e", e2);
            return -1;
        }
    }

    public Socket d() {
        return this.h;
    }

    public byte[] e() {
        return this.e;
    }

    public synchronized ConnState f() {
        return this.d;
    }

    public synchronized void a(ConnState connState) {
        this.d = connState;
    }

    public String g() {
        return this.g;
    }

    public void a(String str) {
        this.g = str;
        this.e = RC4.a(str.getBytes(), (str.substring(str.length() / 2, str.length()) + this.n.substring(this.n.length() / 2, this.n.length())).getBytes());
    }

    public void b(String str) {
        this.g = str;
    }

    public String h() {
        return this.k;
    }

    public void c(String str) {
        this.k = str;
    }

    public String i() {
        return this.l;
    }

    public void d(String str) {
        this.l = str;
    }

    public String j() {
        return this.m;
    }

    public void e(String str) {
        this.m = str;
    }

    public String k() {
        return this.n;
    }

    public void f(String str) {
        this.n = str;
    }

    public String l() {
        return this.o;
    }

    public void g(String str) {
        this.o = str;
    }

    public String m() {
        return this.p;
    }

    public void h(String str) {
        this.p = str;
    }

    public int n() {
        return this.q;
    }

    public void a(int i2) {
        this.q = i2;
    }

    public String o() {
        return this.b;
    }

    public int p() {
        return this.c;
    }

    public ConcurrentLinkedQueue<MIMCObject> q() {
        return this.f;
    }

    public MIMCUser r() {
        return this.t;
    }

    public void a(MIMCUser mIMCUser) {
        this.t = mIMCUser;
    }

    public long s() {
        return this.j;
    }

    public void a(long j2) {
        this.j = j2;
    }

    public void t() {
        if (this.j <= 0) {
            a(System.currentTimeMillis() + 5000);
        }
    }

    private String u() {
        HashMap<String, JSONArray> hashMap;
        MIMCLog.b("Connection", String.format("Resolver url:%s", new Object[]{this.t.N()}));
        if (MIMCUtils.c(this.t.P())) {
            hashMap = new ResolverClient().a(this.t.N(), "");
        } else {
            ResolverClient resolverClient = new ResolverClient();
            String N = this.t.N();
            hashMap = resolverClient.a(N, this.t.P() + "," + this.t.Q());
        }
        String str = null;
        if (hashMap == null) {
            MIMCLog.c("Connection", "getIpByResolver rangeAddresses is null");
            return null;
        }
        for (Map.Entry next : hashMap.entrySet()) {
            if (((String) next.getKey()).equals(this.t.P())) {
                str = ((JSONArray) next.getValue()).toString();
                this.t.h(str);
                MIMCUtils.a(this.t.ac(), this.t.T(), MIMCConstant.ah, str);
                MIMCLog.b("Connection", String.format("Get fe address from resolver, address:%s", new Object[]{str}));
            } else if (((String) next.getKey()).equals(this.t.Q())) {
                String jSONArray = ((JSONArray) next.getValue()).toString();
                this.t.i(jSONArray);
                MIMCUtils.a(this.t.ac(), this.t.T(), MIMCConstant.ag, jSONArray);
                MIMCLog.b("Connection", String.format("Get relay address from resolver, address:%s", new Object[]{jSONArray}));
            }
        }
        return str;
    }

    private void v() {
        MIMCUtils.b(this.t);
    }
}
