package com.xiaomi.msg.data;

import com.xiaomi.msg.common.Constants;
import com.xiaomi.msg.common.RSACoder;
import java.net.InetSocketAddress;
import java.security.KeyPair;
import java.security.interfaces.RSAPublicKey;
import java.util.Iterator;
import java.util.Vector;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.atomic.AtomicLong;

public class ConnInfo {

    /* renamed from: a  reason: collision with root package name */
    private short f12082a;
    private InetSocketAddress b;
    private int c;
    private KeyPair d;
    private long e;
    private long f;
    private byte[] g;
    private ConcurrentMap<Short, StreamInfo> h;
    private boolean i;
    private Vector<RTTInfo> j;
    private AtomicLong k;
    private double l;
    private double m;
    private Object n;
    private boolean o;
    private ConnState p;
    private RTTInfo q;

    public ConnInfo(InetSocketAddress inetSocketAddress, int i2) {
        this(inetSocketAddress, i2, false, (Object) null);
    }

    public ConnInfo(InetSocketAddress inetSocketAddress, int i2, boolean z, Object obj) {
        this.b = inetSocketAddress;
        this.c = i2;
        this.e = System.currentTimeMillis();
        this.f = System.currentTimeMillis();
        this.i = false;
        this.h = new ConcurrentHashMap();
        this.g = null;
        this.d = RSACoder.a();
        this.j = new Vector<>(Constants.u);
        this.k = new AtomicLong(0);
        this.l = 0.0d;
        this.n = obj;
        this.o = z;
    }

    public synchronized void a(InetSocketAddress inetSocketAddress) {
        this.b = inetSocketAddress;
    }

    public void a(boolean z) {
        this.i = z;
    }

    public synchronized void a(long j2) {
        this.f = j2;
    }

    public void a(byte[] bArr) {
        this.g = bArr;
    }

    public void a(double d2) {
        this.m = d2;
    }

    public synchronized void b(double d2) {
        this.l = d2;
    }

    public void a(short s, StreamInfo streamInfo) {
        this.h.put(Short.valueOf(s), streamInfo);
    }

    public void a(short s) {
        this.h.remove(Short.valueOf(s));
    }

    public boolean b(short s) {
        return this.h.containsKey(Short.valueOf(s));
    }

    public StreamInfo c(short s) {
        return (StreamInfo) this.h.get(Short.valueOf(s));
    }

    public synchronized InetSocketAddress a() {
        return this.b;
    }

    public int b() {
        return this.c;
    }

    public byte[] c() {
        return ((RSAPublicKey) this.d.getPublic()).getModulus().toByteArray();
    }

    public byte[] d() {
        return ((RSAPublicKey) this.d.getPublic()).getPublicExponent().toByteArray();
    }

    public byte[] e() {
        return this.d.getPrivate().getEncoded();
    }

    public byte[] f() {
        return this.g;
    }

    public long g() {
        return this.e;
    }

    public boolean h() {
        return this.i;
    }

    public synchronized long i() {
        return this.f;
    }

    public ConcurrentMap<Short, StreamInfo> j() {
        return this.h;
    }

    public long k() {
        return this.k.getAndAdd(1);
    }

    public long l() {
        synchronized (this.j) {
            int size = this.j.size() - 1;
            while (size >= 0) {
                if (this.j.get(size).b(System.currentTimeMillis())) {
                    long a2 = this.j.get(size).a();
                    return a2;
                } else if (this.j.get(size).a() == Long.MAX_VALUE) {
                    size--;
                } else {
                    long a3 = this.j.get(size).a();
                    return a3;
                }
            }
            return Long.MAX_VALUE;
        }
    }

    public RTTInfo b(long j2) {
        synchronized (this.j) {
            Iterator<RTTInfo> it = this.j.iterator();
            while (it.hasNext()) {
                RTTInfo next = it.next();
                if (j2 == next.c()) {
                    return next;
                }
            }
            return null;
        }
    }

    public void c(long j2) {
        synchronized (this.j) {
            if (this.j.size() == Constants.u) {
                this.j.remove(0);
            }
            this.j.add(new RTTInfo(j2, System.currentTimeMillis()));
        }
    }

    private void r() {
        double d2;
        long currentTimeMillis = System.currentTimeMillis();
        Iterator<RTTInfo> it = this.j.iterator();
        int i2 = 0;
        double d3 = 0.0d;
        double d4 = 0.0d;
        int i3 = 0;
        while (it.hasNext()) {
            RTTInfo next = it.next();
            if (i2 == 100) {
                break;
            } else if (next.b(currentTimeMillis)) {
                i2++;
                double a2 = (double) next.a();
                if (a2 != 9.223372036854776E18d) {
                    i3++;
                    Double.isNaN(a2);
                    d4 += a2;
                }
            }
        }
        if (i3 == 0) {
            d2 = 0.0d;
        } else {
            double d5 = (double) i3;
            Double.isNaN(d5);
            d2 = d4 / d5;
        }
        a(d2);
        if (i2 != 0) {
            d3 = (double) ((i2 - i3) / i2);
        }
        b(d3);
    }

    public Vector<RTTInfo> m() {
        return this.j;
    }

    public synchronized double n() {
        return this.l;
    }

    private double s() {
        return this.m;
    }

    public Object o() {
        return this.n;
    }

    public short p() {
        int i2;
        if (this.o) {
            short s = this.f12082a;
            this.f12082a = (short) (s + 1);
            i2 = s * 2;
        } else {
            short s2 = this.f12082a;
            this.f12082a = (short) (s2 + 1);
            i2 = (s2 * 2) + 1;
        }
        return (short) i2;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj != null && getClass() != obj.getClass()) {
            return false;
        }
        ConnInfo connInfo = (ConnInfo) obj;
        if ((this.b != null || connInfo.b != null) && !this.b.equals(connInfo.b)) {
            return false;
        }
        return true;
    }

    public int hashCode() {
        return this.b.hashCode();
    }

    public void a(RTTInfo rTTInfo) {
        this.q = rTTInfo;
    }

    public enum ConnState {
        CONNECTING(0),
        CONNECTED(1),
        CLOSING(2),
        CLOSED(3);
        
        private int value;

        private ConnState(int i) {
            this.value = i;
        }
    }

    public ConnState q() {
        return this.p;
    }

    public void a(ConnState connState) {
        this.p = connState;
    }
}
