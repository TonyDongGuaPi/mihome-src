package javax.jmdns.impl;

import cn.com.fmsh.tsm.business.constants.Constants;
import com.taobao.weex.annotation.JSMethod;
import com.taobao.weex.el.parse.Operators;
import com.xiaomi.miot.support.monitor.core.MiotDataStorage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.Inet4Address;
import java.net.Inet6Address;
import java.net.InetAddress;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Set;
import java.util.Vector;
import java.util.logging.Logger;
import javax.jmdns.ServiceInfo;
import javax.jmdns.impl.DNSRecord;
import javax.jmdns.impl.DNSStatefulObject;
import javax.jmdns.impl.constants.DNSRecordClass;
import javax.jmdns.impl.constants.DNSState;
import javax.jmdns.impl.tasks.DNSTask;
import org.cybergarage.http.HTTP;

public class ServiceInfoImpl extends ServiceInfo implements DNSListener, DNSStatefulObject {
    private static Logger b = Logger.getLogger(ServiceInfoImpl.class.getName());
    private String c;
    private String d;
    private String e;
    private String f;
    private String g;
    private String h;
    private int i;
    private int j;
    private int k;
    private byte[] l;
    private Map<String, byte[]> m;
    private final Set<Inet4Address> n;
    private final Set<Inet6Address> o;
    private transient String p;
    private boolean q;
    private boolean r;
    private final ServiceInfoState s;
    private Delegate t;

    public interface Delegate {
        void a(ServiceInfo serviceInfo, byte[] bArr);
    }

    private static final class ServiceInfoState extends DNSStatefulObject.DefaultImplementation {
        private static final long serialVersionUID = 1104131034952196820L;
        private final ServiceInfoImpl _info;

        public ServiceInfoState(ServiceInfoImpl serviceInfoImpl) {
            this._info = serviceInfoImpl;
        }

        /* access modifiers changed from: protected */
        public void setTask(DNSTask dNSTask) {
            super.setTask(dNSTask);
            if (this._task == null && this._info.I()) {
                lock();
                try {
                    if (this._task == null && this._info.I()) {
                        if (this._state.isAnnounced()) {
                            setState(DNSState.ANNOUNCING_1);
                            if (getDns() != null) {
                                getDns().l();
                            }
                        }
                        this._info.a(false);
                    }
                } finally {
                    unlock();
                }
            }
        }

        public void setDns(JmDNSImpl jmDNSImpl) {
            super.setDns(jmDNSImpl);
        }
    }

    public ServiceInfoImpl(String str, String str2, String str3, int i2, int i3, int i4, boolean z, String str4) {
        this(a(str, str2, str3), i2, i3, i4, z, (byte[]) null);
        this.h = str4;
        try {
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream(str4.length());
            a(byteArrayOutputStream, str4);
            this.l = byteArrayOutputStream.toByteArray();
        } catch (IOException e2) {
            throw new RuntimeException("unexpected exception: " + e2);
        }
    }

    public ServiceInfoImpl(String str, String str2, String str3, int i2, int i3, int i4, boolean z, Map<String, ?> map) {
        this(a(str, str2, str3), i2, i3, i4, z, c(map));
    }

    public ServiceInfoImpl(String str, String str2, String str3, int i2, int i3, int i4, boolean z, byte[] bArr) {
        this(a(str, str2, str3), i2, i3, i4, z, bArr);
    }

    public ServiceInfoImpl(Map<ServiceInfo.Fields, String> map, int i2, int i3, int i4, boolean z, Map<String, ?> map2) {
        this(map, i2, i3, i4, z, c(map2));
    }

    ServiceInfoImpl(Map<ServiceInfo.Fields, String> map, int i2, int i3, int i4, boolean z, String str) {
        this(map, i2, i3, i4, z, (byte[]) null);
        this.h = str;
        try {
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream(str.length());
            a(byteArrayOutputStream, str);
            this.l = byteArrayOutputStream.toByteArray();
        } catch (IOException e2) {
            throw new RuntimeException("unexpected exception: " + e2);
        }
    }

    ServiceInfoImpl(Map<ServiceInfo.Fields, String> map, int i2, int i3, int i4, boolean z, byte[] bArr) {
        Map<ServiceInfo.Fields, String> b2 = b(map);
        this.c = b2.get(ServiceInfo.Fields.Domain);
        this.d = b2.get(ServiceInfo.Fields.Protocol);
        this.e = b2.get(ServiceInfo.Fields.Application);
        this.f = b2.get(ServiceInfo.Fields.Instance);
        this.g = b2.get(ServiceInfo.Fields.Subtype);
        this.i = i2;
        this.j = i3;
        this.k = i4;
        this.l = bArr;
        a(false);
        this.s = new ServiceInfoState(this);
        this.q = z;
        this.n = Collections.synchronizedSet(new LinkedHashSet());
        this.o = Collections.synchronizedSet(new LinkedHashSet());
    }

    ServiceInfoImpl(ServiceInfo serviceInfo) {
        this.n = Collections.synchronizedSet(new LinkedHashSet());
        this.o = Collections.synchronizedSet(new LinkedHashSet());
        if (serviceInfo != null) {
            this.c = serviceInfo.A();
            this.d = serviceInfo.B();
            this.e = serviceInfo.C();
            this.f = serviceInfo.d();
            this.g = serviceInfo.D();
            this.i = serviceInfo.q();
            this.j = serviceInfo.s();
            this.k = serviceInfo.r();
            this.l = serviceInfo.t();
            this.q = serviceInfo.z();
            Collections.addAll(this.o, serviceInfo.p());
            Collections.addAll(this.n, serviceInfo.o());
        }
        this.s = new ServiceInfoState(this);
    }

    public static Map<ServiceInfo.Fields, String> a(String str, String str2, String str3) {
        Map<ServiceInfo.Fields, String> e2 = e(str);
        e2.put(ServiceInfo.Fields.Instance, str2);
        e2.put(ServiceInfo.Fields.Subtype, str3);
        return b(e2);
    }

    /* JADX WARNING: Removed duplicated region for block: B:21:0x007d  */
    /* JADX WARNING: Removed duplicated region for block: B:24:0x008d  */
    /* JADX WARNING: Removed duplicated region for block: B:34:0x00d7  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static java.util.Map<javax.jmdns.ServiceInfo.Fields, java.lang.String> e(java.lang.String r11) {
        /*
            java.lang.String r0 = r11.toLowerCase()
            java.lang.String r1 = ""
            java.lang.String r2 = ""
            java.lang.String r3 = ""
            java.lang.String r4 = ""
            java.lang.String r5 = "in-addr.arpa"
            boolean r5 = r0.contains(r5)
            r6 = 0
            if (r5 != 0) goto L_0x00e7
            java.lang.String r5 = "ip6.arpa"
            boolean r5 = r0.contains(r5)
            if (r5 == 0) goto L_0x001f
            goto L_0x00e7
        L_0x001f:
            java.lang.String r5 = "_"
            boolean r5 = r0.contains(r5)
            r7 = 46
            if (r5 != 0) goto L_0x0049
            java.lang.String r5 = "."
            boolean r5 = r0.contains(r5)
            if (r5 == 0) goto L_0x0049
            int r0 = r0.indexOf(r7)
            java.lang.String r3 = r11.substring(r6, r0)
            java.lang.String r3 = h(r3)
            java.lang.String r11 = r11.substring(r0)
            java.lang.String r11 = h(r11)
            java.lang.String r0 = ""
            goto L_0x0107
        L_0x0049:
            java.lang.String r5 = "_"
            boolean r5 = r0.startsWith(r5)
            if (r5 == 0) goto L_0x0059
            java.lang.String r5 = "_services"
            boolean r5 = r0.startsWith(r5)
            if (r5 == 0) goto L_0x0074
        L_0x0059:
            int r5 = r0.indexOf(r7)
            if (r5 <= 0) goto L_0x0074
            java.lang.String r3 = r11.substring(r6, r5)
            int r5 = r5 + 1
            int r8 = r0.length()
            if (r5 >= r8) goto L_0x0074
            java.lang.String r8 = r0.substring(r5)
            java.lang.String r11 = r11.substring(r5)
            goto L_0x0075
        L_0x0074:
            r8 = r0
        L_0x0075:
            java.lang.String r5 = "._"
            int r5 = r8.lastIndexOf(r5)
            if (r5 <= 0) goto L_0x0087
            int r5 = r5 + 2
            int r1 = r8.indexOf(r7, r5)
            java.lang.String r1 = r11.substring(r5, r1)
        L_0x0087:
            int r5 = r1.length()
            if (r5 <= 0) goto L_0x00ca
            java.lang.StringBuilder r5 = new java.lang.StringBuilder
            r5.<init>()
            java.lang.String r7 = "_"
            r5.append(r7)
            java.lang.String r7 = r1.toLowerCase()
            r5.append(r7)
            java.lang.String r7 = "."
            r5.append(r7)
            java.lang.String r5 = r5.toString()
            int r5 = r8.indexOf(r5)
            int r7 = r1.length()
            int r7 = r7 + r5
            int r7 = r7 + 2
            int r9 = r8.length()
            java.lang.String r10 = "."
            boolean r8 = r8.endsWith(r10)
            int r9 = r9 - r8
            java.lang.String r7 = r11.substring(r7, r9)     // Catch:{ StringIndexOutOfBoundsException -> 0x00c2 }
            r4 = r7
        L_0x00c2:
            int r5 = r5 + -1
            if (r5 <= 0) goto L_0x00ca
            java.lang.String r0 = r11.substring(r6, r5)
        L_0x00ca:
            r11 = r4
            java.lang.String r4 = r0.toLowerCase()
            java.lang.String r5 = "._sub"
            int r4 = r4.indexOf(r5)
            if (r4 <= 0) goto L_0x0107
            int r2 = r4 + 5
            java.lang.String r4 = r0.substring(r6, r4)
            java.lang.String r4 = h(r4)
            java.lang.String r0 = r0.substring(r2)
            r2 = r4
            goto L_0x0107
        L_0x00e7:
            java.lang.String r3 = "in-addr.arpa"
            boolean r3 = r0.contains(r3)
            if (r3 == 0) goto L_0x00f6
            java.lang.String r3 = "in-addr.arpa"
        L_0x00f1:
            int r0 = r0.indexOf(r3)
            goto L_0x00f9
        L_0x00f6:
            java.lang.String r3 = "ip6.arpa"
            goto L_0x00f1
        L_0x00f9:
            java.lang.String r3 = r11.substring(r6, r0)
            java.lang.String r3 = h(r3)
            java.lang.String r11 = r11.substring(r0)
            java.lang.String r0 = ""
        L_0x0107:
            java.util.HashMap r4 = new java.util.HashMap
            r5 = 5
            r4.<init>(r5)
            javax.jmdns.ServiceInfo$Fields r5 = javax.jmdns.ServiceInfo.Fields.Domain
            java.lang.String r11 = h(r11)
            r4.put(r5, r11)
            javax.jmdns.ServiceInfo$Fields r11 = javax.jmdns.ServiceInfo.Fields.Protocol
            r4.put(r11, r1)
            javax.jmdns.ServiceInfo$Fields r11 = javax.jmdns.ServiceInfo.Fields.Application
            java.lang.String r0 = h(r0)
            r4.put(r11, r0)
            javax.jmdns.ServiceInfo$Fields r11 = javax.jmdns.ServiceInfo.Fields.Instance
            r4.put(r11, r3)
            javax.jmdns.ServiceInfo$Fields r11 = javax.jmdns.ServiceInfo.Fields.Subtype
            r4.put(r11, r2)
            return r4
        */
        throw new UnsupportedOperationException("Method not decompiled: javax.jmdns.impl.ServiceInfoImpl.e(java.lang.String):java.util.Map");
    }

    protected static Map<ServiceInfo.Fields, String> b(Map<ServiceInfo.Fields, String> map) {
        HashMap hashMap = new HashMap(5);
        String str = map.containsKey(ServiceInfo.Fields.Domain) ? map.get(ServiceInfo.Fields.Domain) : "local";
        if (str == null || str.length() == 0) {
            str = "local";
        }
        hashMap.put(ServiceInfo.Fields.Domain, h(str));
        String str2 = map.containsKey(ServiceInfo.Fields.Protocol) ? map.get(ServiceInfo.Fields.Protocol) : MiotDataStorage.b;
        if (str2 == null || str2.length() == 0) {
            str2 = MiotDataStorage.b;
        }
        hashMap.put(ServiceInfo.Fields.Protocol, h(str2));
        String str3 = map.containsKey(ServiceInfo.Fields.Application) ? map.get(ServiceInfo.Fields.Application) : "";
        if (str3 == null || str3.length() == 0) {
            str3 = "";
        }
        hashMap.put(ServiceInfo.Fields.Application, h(str3));
        String str4 = map.containsKey(ServiceInfo.Fields.Instance) ? map.get(ServiceInfo.Fields.Instance) : "";
        if (str4 == null || str4.length() == 0) {
            str4 = "";
        }
        hashMap.put(ServiceInfo.Fields.Instance, h(str4));
        String str5 = map.containsKey(ServiceInfo.Fields.Subtype) ? map.get(ServiceInfo.Fields.Subtype) : "";
        if (str5 == null || str5.length() == 0) {
            str5 = "";
        }
        hashMap.put(ServiceInfo.Fields.Subtype, h(str5));
        return hashMap;
    }

    private static String h(String str) {
        if (str == null) {
            return "";
        }
        String trim = str.trim();
        if (trim.startsWith(".")) {
            trim = trim.substring(1);
        }
        if (trim.startsWith(JSMethod.NOT_SET)) {
            trim = trim.substring(1);
        }
        return trim.endsWith(".") ? trim.substring(0, trim.length() - 1) : trim;
    }

    public String b() {
        String str;
        String str2;
        String A = A();
        String B = B();
        String C = C();
        StringBuilder sb = new StringBuilder();
        if (C.length() > 0) {
            str = JSMethod.NOT_SET + C + ".";
        } else {
            str = "";
        }
        sb.append(str);
        if (B.length() > 0) {
            str2 = JSMethod.NOT_SET + B + ".";
        } else {
            str2 = "";
        }
        sb.append(str2);
        sb.append(A);
        sb.append(".");
        return sb.toString();
    }

    public String c() {
        String str;
        String D = D();
        StringBuilder sb = new StringBuilder();
        if (D.length() > 0) {
            str = JSMethod.NOT_SET + D.toLowerCase() + "._sub.";
        } else {
            str = "";
        }
        sb.append(str);
        sb.append(b());
        return sb.toString();
    }

    public String d() {
        return this.f != null ? this.f : "";
    }

    public String e() {
        if (this.p == null) {
            this.p = f().toLowerCase();
        }
        return this.p;
    }

    /* access modifiers changed from: package-private */
    public void f(String str) {
        this.f = str;
        this.p = null;
    }

    public String f() {
        String str;
        String str2;
        String str3;
        String A = A();
        String B = B();
        String C = C();
        String d2 = d();
        StringBuilder sb = new StringBuilder();
        if (d2.length() > 0) {
            str = d2 + ".";
        } else {
            str = "";
        }
        sb.append(str);
        if (C.length() > 0) {
            str2 = JSMethod.NOT_SET + C + ".";
        } else {
            str2 = "";
        }
        sb.append(str2);
        if (B.length() > 0) {
            str3 = JSMethod.NOT_SET + B + ".";
        } else {
            str3 = "";
        }
        sb.append(str3);
        sb.append(A);
        sb.append(".");
        return sb.toString();
    }

    public String g() {
        return this.h != null ? this.h : "";
    }

    /* access modifiers changed from: package-private */
    public void g(String str) {
        this.h = str;
    }

    @Deprecated
    public String h() {
        String[] i2 = i();
        return i2.length > 0 ? i2[0] : "";
    }

    public String[] i() {
        Inet4Address[] o2 = o();
        Inet6Address[] p2 = p();
        String[] strArr = new String[(o2.length + p2.length)];
        for (int i2 = 0; i2 < o2.length; i2++) {
            strArr[i2] = o2[i2].getHostAddress();
        }
        for (int i3 = 0; i3 < p2.length; i3++) {
            strArr[o2.length + i3] = Operators.ARRAY_START_STR + p2[i3].getHostAddress() + Operators.ARRAY_END_STR;
        }
        return strArr;
    }

    /* access modifiers changed from: package-private */
    public void a(Inet4Address inet4Address) {
        this.n.add(inet4Address);
    }

    /* access modifiers changed from: package-private */
    public void a(Inet6Address inet6Address) {
        this.o.add(inet6Address);
    }

    @Deprecated
    public InetAddress j() {
        return k();
    }

    @Deprecated
    public InetAddress k() {
        InetAddress[] n2 = n();
        if (n2.length > 0) {
            return n2[0];
        }
        return null;
    }

    @Deprecated
    public Inet4Address l() {
        Inet4Address[] o2 = o();
        if (o2.length > 0) {
            return o2[0];
        }
        return null;
    }

    @Deprecated
    public Inet6Address m() {
        Inet6Address[] p2 = p();
        if (p2.length > 0) {
            return p2[0];
        }
        return null;
    }

    public InetAddress[] n() {
        ArrayList arrayList = new ArrayList(this.n.size() + this.o.size());
        arrayList.addAll(this.n);
        arrayList.addAll(this.o);
        return (InetAddress[]) arrayList.toArray(new InetAddress[arrayList.size()]);
    }

    public Inet4Address[] o() {
        return (Inet4Address[]) this.n.toArray(new Inet4Address[this.n.size()]);
    }

    public Inet6Address[] p() {
        return (Inet6Address[]) this.o.toArray(new Inet6Address[this.o.size()]);
    }

    public int q() {
        return this.i;
    }

    public int r() {
        return this.k;
    }

    public int s() {
        return this.j;
    }

    public byte[] t() {
        return (this.l == null || this.l.length <= 0) ? DNSRecord.c : this.l;
    }

    @Deprecated
    public String u() {
        Map<String, byte[]> G = G();
        Iterator<String> it = G.keySet().iterator();
        if (!it.hasNext()) {
            return "";
        }
        String next = it.next();
        byte[] bArr = G.get(next);
        if (bArr == null || bArr.length <= 0) {
            return next;
        }
        return next + "=" + new String(bArr);
    }

    @Deprecated
    public String v() {
        return a("http");
    }

    public String[] w() {
        return b("http");
    }

    @Deprecated
    public String a(String str) {
        String[] b2 = b(str);
        if (b2.length > 0) {
            return b2[0];
        }
        return str + "://null:" + q();
    }

    public String[] b(String str) {
        InetAddress[] n2 = n();
        String[] strArr = new String[n2.length];
        for (int i2 = 0; i2 < n2.length; i2++) {
            String str2 = str + "://" + n2[i2].getHostAddress() + ":" + q();
            String d2 = d("path");
            if (d2 != null) {
                if (d2.contains("://")) {
                    str2 = d2;
                } else {
                    StringBuilder sb = new StringBuilder();
                    sb.append(str2);
                    if (!d2.startsWith("/")) {
                        d2 = "/" + d2;
                    }
                    sb.append(d2);
                    str2 = sb.toString();
                }
            }
            strArr[i2] = str2;
        }
        return strArr;
    }

    public synchronized byte[] c(String str) {
        return G().get(str);
    }

    public synchronized String d(String str) {
        byte[] bArr = G().get(str);
        if (bArr == null) {
            return null;
        }
        if (bArr == f2625a) {
            return "true";
        }
        return a(bArr, 0, bArr.length);
    }

    public Enumeration<String> x() {
        Map<String, byte[]> G = G();
        return new Vector(G != null ? G.keySet() : Collections.emptySet()).elements();
    }

    public String C() {
        return this.e != null ? this.e : "";
    }

    public String A() {
        return this.c != null ? this.c : "local";
    }

    public String B() {
        return this.d != null ? this.d : MiotDataStorage.b;
    }

    public String D() {
        return this.g != null ? this.g : "";
    }

    public Map<ServiceInfo.Fields, String> E() {
        HashMap hashMap = new HashMap(5);
        hashMap.put(ServiceInfo.Fields.Domain, A());
        hashMap.put(ServiceInfo.Fields.Protocol, B());
        hashMap.put(ServiceInfo.Fields.Application, C());
        hashMap.put(ServiceInfo.Fields.Instance, d());
        hashMap.put(ServiceInfo.Fields.Subtype, D());
        return hashMap;
    }

    static void a(OutputStream outputStream, String str) throws IOException {
        int length = str.length();
        for (int i2 = 0; i2 < length; i2++) {
            char charAt = str.charAt(i2);
            if (charAt >= 1 && charAt <= 127) {
                outputStream.write(charAt);
            } else if (charAt > 2047) {
                outputStream.write(((charAt >> 12) & 15) | 224);
                outputStream.write(((charAt >> 6) & 63) | 128);
                outputStream.write(((charAt >> 0) & 63) | 128);
            } else {
                outputStream.write(((charAt >> 6) & 31) | 192);
                outputStream.write(((charAt >> 0) & 63) | 128);
            }
        }
    }

    /* access modifiers changed from: package-private */
    public String a(byte[] bArr, int i2, int i3) {
        int i4;
        StringBuilder sb = new StringBuilder();
        int i5 = i2 + i3;
        while (i2 < i5) {
            int i6 = i2 + 1;
            byte b2 = bArr[i2] & 255;
            int i7 = b2 >> 4;
            switch (i7) {
                case 0:
                case 1:
                case 2:
                case 3:
                case 4:
                case 5:
                case 6:
                case 7:
                    break;
                default:
                    switch (i7) {
                        case 12:
                        case 13:
                            if (i6 < i3) {
                                i4 = i6 + 1;
                                b2 = ((b2 & 31) << 6) | (bArr[i6] & Constants.TagName.CARD_APP_ACTIVATION_STATUS);
                                break;
                            } else {
                                return null;
                            }
                        case 14:
                            if (i6 + 2 >= i3) {
                                return null;
                            }
                            int i8 = i6 + 1;
                            byte b3 = ((b2 & 15) << 12) | ((bArr[i6] & Constants.TagName.CARD_APP_ACTIVATION_STATUS) << 6);
                            i6 = i8 + 1;
                            b2 = b3 | (bArr[i8] & Constants.TagName.CARD_APP_ACTIVATION_STATUS);
                            continue;
                        default:
                            i4 = i6 + 1;
                            if (i4 < i3) {
                                b2 = ((b2 & Constants.TagName.CARD_APP_ACTIVATION_STATUS) << 4) | (bArr[i6] & 15);
                                break;
                            } else {
                                return null;
                            }
                    }
                    i6 = i4;
                    break;
            }
            sb.append((char) b2);
            i2 = i6;
        }
        return sb.toString();
    }

    /* access modifiers changed from: package-private */
    /* JADX WARNING: Code restructure failed: missing block: B:27:0x006a, code lost:
        r0.clear();
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public synchronized java.util.Map<java.lang.String, byte[]> G() {
        /*
            r9 = this;
            monitor-enter(r9)
            java.util.Map<java.lang.String, byte[]> r0 = r9.m     // Catch:{ all -> 0x0087 }
            if (r0 != 0) goto L_0x007a
            byte[] r0 = r9.t()     // Catch:{ all -> 0x0087 }
            if (r0 == 0) goto L_0x007a
            java.util.Hashtable r0 = new java.util.Hashtable     // Catch:{ all -> 0x0087 }
            r0.<init>()     // Catch:{ all -> 0x0087 }
            r1 = 0
            r2 = 0
        L_0x0012:
            byte[] r3 = r9.t()     // Catch:{ Exception -> 0x006e }
            int r3 = r3.length     // Catch:{ Exception -> 0x006e }
            if (r2 >= r3) goto L_0x0078
            byte[] r3 = r9.t()     // Catch:{ Exception -> 0x006e }
            int r4 = r2 + 1
            byte r2 = r3[r2]     // Catch:{ Exception -> 0x006e }
            r2 = r2 & 255(0xff, float:3.57E-43)
            if (r2 == 0) goto L_0x006a
            int r3 = r4 + r2
            byte[] r5 = r9.t()     // Catch:{ Exception -> 0x006e }
            int r5 = r5.length     // Catch:{ Exception -> 0x006e }
            if (r3 <= r5) goto L_0x002f
            goto L_0x006a
        L_0x002f:
            r5 = 0
        L_0x0030:
            if (r5 >= r2) goto L_0x0041
            byte[] r6 = r9.t()     // Catch:{ Exception -> 0x006e }
            int r7 = r4 + r5
            byte r6 = r6[r7]     // Catch:{ Exception -> 0x006e }
            r7 = 61
            if (r6 == r7) goto L_0x0041
            int r5 = r5 + 1
            goto L_0x0030
        L_0x0041:
            byte[] r6 = r9.t()     // Catch:{ Exception -> 0x006e }
            java.lang.String r6 = r9.a((byte[]) r6, (int) r4, (int) r5)     // Catch:{ Exception -> 0x006e }
            if (r6 != 0) goto L_0x004f
            r0.clear()     // Catch:{ Exception -> 0x006e }
            goto L_0x0078
        L_0x004f:
            if (r5 != r2) goto L_0x0058
            byte[] r2 = f2625a     // Catch:{ Exception -> 0x006e }
            r0.put(r6, r2)     // Catch:{ Exception -> 0x006e }
            r2 = r4
            goto L_0x0012
        L_0x0058:
            int r5 = r5 + 1
            int r2 = r2 - r5
            byte[] r7 = new byte[r2]     // Catch:{ Exception -> 0x006e }
            byte[] r8 = r9.t()     // Catch:{ Exception -> 0x006e }
            int r4 = r4 + r5
            java.lang.System.arraycopy(r8, r4, r7, r1, r2)     // Catch:{ Exception -> 0x006e }
            r0.put(r6, r7)     // Catch:{ Exception -> 0x006e }
            r2 = r3
            goto L_0x0012
        L_0x006a:
            r0.clear()     // Catch:{ Exception -> 0x006e }
            goto L_0x0078
        L_0x006e:
            r1 = move-exception
            java.util.logging.Logger r2 = b     // Catch:{ all -> 0x0087 }
            java.util.logging.Level r3 = java.util.logging.Level.WARNING     // Catch:{ all -> 0x0087 }
            java.lang.String r4 = "Malformed TXT Field "
            r2.log(r3, r4, r1)     // Catch:{ all -> 0x0087 }
        L_0x0078:
            r9.m = r0     // Catch:{ all -> 0x0087 }
        L_0x007a:
            java.util.Map<java.lang.String, byte[]> r0 = r9.m     // Catch:{ all -> 0x0087 }
            if (r0 == 0) goto L_0x0081
            java.util.Map<java.lang.String, byte[]> r0 = r9.m     // Catch:{ all -> 0x0087 }
            goto L_0x0085
        L_0x0081:
            java.util.Map r0 = java.util.Collections.emptyMap()     // Catch:{ all -> 0x0087 }
        L_0x0085:
            monitor-exit(r9)
            return r0
        L_0x0087:
            r0 = move-exception
            monitor-exit(r9)
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: javax.jmdns.impl.ServiceInfoImpl.G():java.util.Map");
    }

    /* JADX WARNING: Can't fix incorrect switch cases order */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void a(javax.jmdns.impl.DNSCache r6, long r7, javax.jmdns.impl.DNSEntry r9) {
        /*
            r5 = this;
            boolean r0 = r9 instanceof javax.jmdns.impl.DNSRecord
            if (r0 == 0) goto L_0x0142
            boolean r0 = r9.b((long) r7)
            if (r0 != 0) goto L_0x0142
            int[] r0 = javax.jmdns.impl.ServiceInfoImpl.AnonymousClass1.f2664a
            javax.jmdns.impl.constants.DNSRecordType r1 = r9.e()
            int r1 = r1.ordinal()
            r0 = r0[r1]
            r1 = 0
            r2 = 1
            switch(r0) {
                case 1: goto L_0x00f7;
                case 2: goto L_0x00da;
                case 3: goto L_0x0052;
                case 4: goto L_0x0039;
                case 5: goto L_0x001d;
                default: goto L_0x001b;
            }
        L_0x001b:
            goto L_0x0114
        L_0x001d:
            java.lang.String r6 = r5.D()
            int r6 = r6.length()
            if (r6 != 0) goto L_0x0114
            java.lang.String r6 = r9.a()
            int r6 = r6.length()
            if (r6 == 0) goto L_0x0114
            java.lang.String r6 = r9.a()
            r5.g = r6
            goto L_0x0115
        L_0x0039:
            java.lang.String r6 = r9.b()
            java.lang.String r7 = r5.f()
            boolean r6 = r6.equalsIgnoreCase(r7)
            if (r6 == 0) goto L_0x0114
            r6 = r9
            javax.jmdns.impl.DNSRecord$Text r6 = (javax.jmdns.impl.DNSRecord.Text) r6
            byte[] r6 = r6.s()
            r5.l = r6
            goto L_0x0115
        L_0x0052:
            java.lang.String r0 = r9.b()
            java.lang.String r3 = r5.f()
            boolean r0 = r0.equalsIgnoreCase(r3)
            if (r0 == 0) goto L_0x0114
            r0 = r9
            javax.jmdns.impl.DNSRecord$Service r0 = (javax.jmdns.impl.DNSRecord.Service) r0
            java.lang.String r3 = r5.h
            if (r3 == 0) goto L_0x0076
            java.lang.String r3 = r5.h
            java.lang.String r4 = r0.s()
            boolean r3 = r3.equalsIgnoreCase(r4)
            if (r3 != 0) goto L_0x0074
            goto L_0x0076
        L_0x0074:
            r3 = 0
            goto L_0x0077
        L_0x0076:
            r3 = 1
        L_0x0077:
            java.lang.String r4 = r0.s()
            r5.h = r4
            int r4 = r0.v()
            r5.i = r4
            int r4 = r0.u()
            r5.j = r4
            int r0 = r0.t()
            r5.k = r0
            if (r3 == 0) goto L_0x00d7
            java.util.Set<java.net.Inet4Address> r0 = r5.n
            r0.clear()
            java.util.Set<java.net.Inet6Address> r0 = r5.o
            r0.clear()
            java.lang.String r0 = r5.h
            javax.jmdns.impl.constants.DNSRecordType r2 = javax.jmdns.impl.constants.DNSRecordType.TYPE_A
            javax.jmdns.impl.constants.DNSRecordClass r3 = javax.jmdns.impl.constants.DNSRecordClass.CLASS_IN
            java.util.Collection r0 = r6.b(r0, r2, r3)
            java.util.Iterator r0 = r0.iterator()
        L_0x00a9:
            boolean r2 = r0.hasNext()
            if (r2 == 0) goto L_0x00b9
            java.lang.Object r2 = r0.next()
            javax.jmdns.impl.DNSEntry r2 = (javax.jmdns.impl.DNSEntry) r2
            r5.a((javax.jmdns.impl.DNSCache) r6, (long) r7, (javax.jmdns.impl.DNSEntry) r2)
            goto L_0x00a9
        L_0x00b9:
            java.lang.String r0 = r5.h
            javax.jmdns.impl.constants.DNSRecordType r2 = javax.jmdns.impl.constants.DNSRecordType.TYPE_AAAA
            javax.jmdns.impl.constants.DNSRecordClass r3 = javax.jmdns.impl.constants.DNSRecordClass.CLASS_IN
            java.util.Collection r0 = r6.b(r0, r2, r3)
            java.util.Iterator r0 = r0.iterator()
        L_0x00c7:
            boolean r2 = r0.hasNext()
            if (r2 == 0) goto L_0x00d8
            java.lang.Object r2 = r0.next()
            javax.jmdns.impl.DNSEntry r2 = (javax.jmdns.impl.DNSEntry) r2
            r5.a((javax.jmdns.impl.DNSCache) r6, (long) r7, (javax.jmdns.impl.DNSEntry) r2)
            goto L_0x00c7
        L_0x00d7:
            r1 = 1
        L_0x00d8:
            r2 = r1
            goto L_0x0115
        L_0x00da:
            java.lang.String r6 = r9.b()
            java.lang.String r7 = r5.g()
            boolean r6 = r6.equalsIgnoreCase(r7)
            if (r6 == 0) goto L_0x0114
            java.util.Set<java.net.Inet6Address> r6 = r5.o
            r7 = r9
            javax.jmdns.impl.DNSRecord$Address r7 = (javax.jmdns.impl.DNSRecord.Address) r7
            java.net.InetAddress r7 = r7.s()
            java.net.Inet6Address r7 = (java.net.Inet6Address) r7
            r6.add(r7)
            goto L_0x0115
        L_0x00f7:
            java.lang.String r6 = r9.b()
            java.lang.String r7 = r5.g()
            boolean r6 = r6.equalsIgnoreCase(r7)
            if (r6 == 0) goto L_0x0114
            java.util.Set<java.net.Inet4Address> r6 = r5.n
            r7 = r9
            javax.jmdns.impl.DNSRecord$Address r7 = (javax.jmdns.impl.DNSRecord.Address) r7
            java.net.InetAddress r7 = r7.s()
            java.net.Inet4Address r7 = (java.net.Inet4Address) r7
            r6.add(r7)
            goto L_0x0115
        L_0x0114:
            r2 = 0
        L_0x0115:
            if (r2 == 0) goto L_0x0139
            boolean r6 = r5.a()
            if (r6 == 0) goto L_0x0139
            javax.jmdns.impl.JmDNSImpl r6 = r5.getDns()
            if (r6 == 0) goto L_0x0139
            javax.jmdns.impl.DNSRecord r9 = (javax.jmdns.impl.DNSRecord) r9
            javax.jmdns.ServiceEvent r7 = r9.b((javax.jmdns.impl.JmDNSImpl) r6)
            javax.jmdns.impl.ServiceEventImpl r8 = new javax.jmdns.impl.ServiceEventImpl
            java.lang.String r9 = r7.getType()
            java.lang.String r7 = r7.getName()
            r8.<init>(r6, r9, r7, r5)
            r6.a((javax.jmdns.ServiceEvent) r8)
        L_0x0139:
            monitor-enter(r5)
            r5.notifyAll()     // Catch:{ all -> 0x013f }
            monitor-exit(r5)     // Catch:{ all -> 0x013f }
            goto L_0x0142
        L_0x013f:
            r6 = move-exception
            monitor-exit(r5)     // Catch:{ all -> 0x013f }
            throw r6
        L_0x0142:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: javax.jmdns.impl.ServiceInfoImpl.a(javax.jmdns.impl.DNSCache, long, javax.jmdns.impl.DNSEntry):void");
    }

    public synchronized boolean a() {
        return g() != null && K() && t() != null && t().length > 0;
    }

    private boolean K() {
        return this.n.size() > 0 || this.o.size() > 0;
    }

    public boolean advanceState(DNSTask dNSTask) {
        return this.s.advanceState(dNSTask);
    }

    public boolean revertState() {
        return this.s.revertState();
    }

    public boolean cancelState() {
        return this.s.cancelState();
    }

    public boolean closeState() {
        return this.s.closeState();
    }

    public boolean recoverState() {
        return this.s.recoverState();
    }

    public void removeAssociationWithTask(DNSTask dNSTask) {
        this.s.removeAssociationWithTask(dNSTask);
    }

    public void associateWithTask(DNSTask dNSTask, DNSState dNSState) {
        this.s.associateWithTask(dNSTask, dNSState);
    }

    public boolean isAssociatedWithTask(DNSTask dNSTask, DNSState dNSState) {
        return this.s.isAssociatedWithTask(dNSTask, dNSState);
    }

    public boolean isProbing() {
        return this.s.isProbing();
    }

    public boolean isAnnouncing() {
        return this.s.isAnnouncing();
    }

    public boolean isAnnounced() {
        return this.s.isAnnounced();
    }

    public boolean isCanceling() {
        return this.s.isCanceling();
    }

    public boolean isCanceled() {
        return this.s.isCanceled();
    }

    public boolean isClosing() {
        return this.s.isClosing();
    }

    public boolean isClosed() {
        return this.s.isClosed();
    }

    public boolean waitForAnnounced(long j2) {
        return this.s.waitForAnnounced(j2);
    }

    public boolean waitForCanceled(long j2) {
        return this.s.waitForCanceled(j2);
    }

    public int hashCode() {
        return f().hashCode();
    }

    public boolean equals(Object obj) {
        return (obj instanceof ServiceInfoImpl) && f().equals(((ServiceInfoImpl) obj).f());
    }

    public String y() {
        StringBuilder sb = new StringBuilder();
        int length = t().length;
        int i2 = 0;
        while (true) {
            if (i2 >= length) {
                break;
            } else if (i2 >= 200) {
                sb.append("...");
                break;
            } else {
                byte b2 = t()[i2] & 255;
                if (b2 < 32 || b2 > Byte.MAX_VALUE) {
                    sb.append("\\0");
                    sb.append(Integer.toString(b2, 8));
                } else {
                    sb.append((char) b2);
                }
                i2++;
            }
        }
        return sb.toString();
    }

    /* renamed from: H */
    public ServiceInfoImpl clone() {
        ServiceInfoImpl serviceInfoImpl = new ServiceInfoImpl(E(), this.i, this.j, this.k, this.q, this.l);
        Collections.addAll(serviceInfoImpl.o, p());
        Collections.addAll(serviceInfoImpl.n, o());
        return serviceInfoImpl;
    }

    public String toString() {
        String str;
        StringBuilder sb = new StringBuilder();
        sb.append(Operators.ARRAY_START_STR);
        sb.append(getClass().getSimpleName());
        sb.append("@");
        sb.append(System.identityHashCode(this));
        sb.append(" ");
        sb.append("name: '");
        if (d().length() > 0) {
            str = d() + ".";
        } else {
            str = "";
        }
        sb.append(str);
        sb.append(c());
        sb.append("' address: '");
        InetAddress[] n2 = n();
        if (n2.length > 0) {
            for (InetAddress append : n2) {
                sb.append(append);
                sb.append(Operators.CONDITION_IF_MIDDLE);
                sb.append(q());
                sb.append(' ');
            }
        } else {
            sb.append("(null):");
            sb.append(q());
        }
        sb.append("' status: '");
        sb.append(this.s.toString());
        sb.append(z() ? "' is persistent," : "',");
        sb.append(" has ");
        sb.append(a() ? "" : "NO ");
        sb.append("data");
        if (t().length > 0) {
            Map<String, byte[]> G = G();
            if (!G.isEmpty()) {
                sb.append("\n");
                for (String next : G.keySet()) {
                    sb.append(HTTP.TAB);
                    sb.append(next);
                    sb.append(": ");
                    sb.append(new String(G.get(next)));
                    sb.append("\n");
                }
            } else {
                sb.append(" empty");
            }
        }
        sb.append(Operators.ARRAY_END);
        return sb.toString();
    }

    public Collection<DNSRecord> a(boolean z, int i2, HostInfo hostInfo) {
        ArrayList arrayList = new ArrayList();
        if (D().length() > 0) {
            arrayList.add(new DNSRecord.Pointer(c(), DNSRecordClass.CLASS_IN, false, i2, f()));
        }
        arrayList.add(new DNSRecord.Pointer(b(), DNSRecordClass.CLASS_IN, false, i2, f()));
        String f2 = f();
        DNSRecordClass dNSRecordClass = DNSRecordClass.CLASS_IN;
        int i3 = this.k;
        int i4 = this.j;
        arrayList.add(new DNSRecord.Service(f2, dNSRecordClass, z, i2, i3, i4, this.i, hostInfo.a()));
        arrayList.add(new DNSRecord.Text(f(), DNSRecordClass.CLASS_IN, z, i2, t()));
        return arrayList;
    }

    public void a(byte[] bArr) throws IllegalStateException {
        synchronized (this) {
            this.l = bArr;
            this.m = null;
            a(true);
        }
    }

    public void a(Map<String, ?> map) throws IllegalStateException {
        a(c(map));
    }

    /* access modifiers changed from: package-private */
    public void b(byte[] bArr) {
        this.l = bArr;
        this.m = null;
    }

    private static byte[] c(Map<String, ?> map) {
        String str;
        byte[] bArr = null;
        if (map != null) {
            try {
                ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream(256);
                for (String next : map.keySet()) {
                    Object obj = map.get(next);
                    ByteArrayOutputStream byteArrayOutputStream2 = new ByteArrayOutputStream(100);
                    a(byteArrayOutputStream2, next);
                    if (obj != null) {
                        if (obj instanceof String) {
                            byteArrayOutputStream2.write(61);
                            a(byteArrayOutputStream2, (String) obj);
                        } else if (obj instanceof byte[]) {
                            byte[] bArr2 = (byte[]) obj;
                            if (bArr2.length > 0) {
                                byteArrayOutputStream2.write(61);
                                byteArrayOutputStream2.write(bArr2, 0, bArr2.length);
                            } else {
                                obj = null;
                            }
                        } else {
                            throw new IllegalArgumentException("invalid property value: " + obj);
                        }
                    }
                    byte[] byteArray = byteArrayOutputStream2.toByteArray();
                    if (byteArray.length > 255) {
                        StringBuilder sb = new StringBuilder();
                        sb.append("Cannot have individual values larger that 255 chars. Offending value: ");
                        sb.append(next);
                        if (obj != null) {
                            str = "";
                        } else {
                            str = "=" + obj;
                        }
                        sb.append(str);
                        throw new IOException(sb.toString());
                    }
                    byteArrayOutputStream.write((byte) byteArray.length);
                    byteArrayOutputStream.write(byteArray, 0, byteArray.length);
                }
                bArr = byteArrayOutputStream.toByteArray();
            } catch (IOException e2) {
                throw new RuntimeException("unexpected exception: " + e2);
            }
        }
        return (bArr == null || bArr.length <= 0) ? DNSRecord.c : bArr;
    }

    public void a(JmDNSImpl jmDNSImpl) {
        this.s.setDns(jmDNSImpl);
    }

    public JmDNSImpl getDns() {
        return this.s.getDns();
    }

    public boolean z() {
        return this.q;
    }

    public void a(boolean z) {
        this.r = z;
        if (this.r) {
            this.s.setTask((DNSTask) null);
        }
    }

    public boolean I() {
        return this.r;
    }

    /* access modifiers changed from: package-private */
    public Delegate J() {
        return this.t;
    }

    /* access modifiers changed from: package-private */
    public void a(Delegate delegate) {
        this.t = delegate;
    }
}
