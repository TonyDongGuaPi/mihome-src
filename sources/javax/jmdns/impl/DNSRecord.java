package javax.jmdns.impl;

import java.io.DataOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.Inet4Address;
import java.net.Inet6Address;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.jmdns.ServiceEvent;
import javax.jmdns.ServiceInfo;
import javax.jmdns.impl.DNSOutgoing;
import javax.jmdns.impl.constants.DNSRecordClass;
import javax.jmdns.impl.constants.DNSRecordType;

public abstract class DNSRecord extends DNSEntry {
    public static final byte[] c = {0};
    private static Logger d = Logger.getLogger(DNSRecord.class.getName());
    public InetAddress b;
    private int e;
    private long f = System.currentTimeMillis();

    public abstract ServiceInfo a(boolean z);

    /* access modifiers changed from: package-private */
    public abstract DNSOutgoing a(JmDNSImpl jmDNSImpl, DNSIncoming dNSIncoming, InetAddress inetAddress, int i, DNSOutgoing dNSOutgoing) throws IOException;

    /* access modifiers changed from: package-private */
    public abstract void a(DNSOutgoing.MessageOutputStream messageOutputStream);

    /* access modifiers changed from: package-private */
    public abstract boolean a(DNSRecord dNSRecord);

    /* access modifiers changed from: package-private */
    public abstract boolean a(JmDNSImpl jmDNSImpl);

    /* access modifiers changed from: package-private */
    public abstract boolean a(JmDNSImpl jmDNSImpl, long j);

    public abstract ServiceEvent b(JmDNSImpl jmDNSImpl);

    public abstract boolean o();

    DNSRecord(String str, DNSRecordType dNSRecordType, DNSRecordClass dNSRecordClass, boolean z, int i) {
        super(str, dNSRecordType, dNSRecordClass, z);
        this.e = i;
    }

    public boolean equals(Object obj) {
        return (obj instanceof DNSRecord) && super.equals(obj) && a((DNSRecord) obj);
    }

    /* access modifiers changed from: package-private */
    public boolean b(DNSRecord dNSRecord) {
        return e() == dNSRecord.e();
    }

    /* access modifiers changed from: package-private */
    public boolean a(DNSIncoming dNSIncoming) {
        try {
            for (DNSRecord c2 : dNSIncoming.i()) {
                if (c(c2)) {
                    return true;
                }
            }
            return false;
        } catch (ArrayIndexOutOfBoundsException e2) {
            Logger logger = d;
            Level level = Level.WARNING;
            logger.log(level, "suppressedBy() message " + dNSIncoming + " exception ", e2);
            return false;
        }
    }

    /* access modifiers changed from: package-private */
    public boolean c(DNSRecord dNSRecord) {
        return equals(dNSRecord) && dNSRecord.e > this.e / 2;
    }

    /* access modifiers changed from: package-private */
    public long a(int i) {
        return this.f + (((long) (i * this.e)) * 10);
    }

    /* access modifiers changed from: package-private */
    public int c(long j) {
        return (int) Math.max(0, (a(100) - j) / 1000);
    }

    public boolean b(long j) {
        return a(100) <= j;
    }

    public boolean a(long j) {
        return a(50) <= j;
    }

    /* access modifiers changed from: package-private */
    public void d(DNSRecord dNSRecord) {
        this.f = dNSRecord.f;
        this.e = dNSRecord.e;
    }

    /* access modifiers changed from: package-private */
    public void d(long j) {
        this.f = j;
        this.e = 1;
    }

    public static class IPv4Address extends Address {
        IPv4Address(String str, DNSRecordClass dNSRecordClass, boolean z, int i, InetAddress inetAddress) {
            super(str, DNSRecordType.TYPE_A, dNSRecordClass, z, i, inetAddress);
        }

        IPv4Address(String str, DNSRecordClass dNSRecordClass, boolean z, int i, byte[] bArr) {
            super(str, DNSRecordType.TYPE_A, dNSRecordClass, z, i, bArr);
        }

        /* access modifiers changed from: package-private */
        public void a(DNSOutgoing.MessageOutputStream messageOutputStream) {
            if (this.d != null) {
                byte[] address = this.d.getAddress();
                if (!(this.d instanceof Inet4Address)) {
                    byte[] bArr = new byte[4];
                    System.arraycopy(address, 12, bArr, 0, 4);
                    address = bArr;
                }
                messageOutputStream.a(address, 0, address.length);
            }
        }

        public ServiceInfo a(boolean z) {
            ServiceInfoImpl serviceInfoImpl = (ServiceInfoImpl) super.a(z);
            serviceInfoImpl.a((Inet4Address) this.d);
            return serviceInfoImpl;
        }
    }

    public static class IPv6Address extends Address {
        IPv6Address(String str, DNSRecordClass dNSRecordClass, boolean z, int i, InetAddress inetAddress) {
            super(str, DNSRecordType.TYPE_AAAA, dNSRecordClass, z, i, inetAddress);
        }

        IPv6Address(String str, DNSRecordClass dNSRecordClass, boolean z, int i, byte[] bArr) {
            super(str, DNSRecordType.TYPE_AAAA, dNSRecordClass, z, i, bArr);
        }

        /* access modifiers changed from: package-private */
        public void a(DNSOutgoing.MessageOutputStream messageOutputStream) {
            if (this.d != null) {
                byte[] address = this.d.getAddress();
                if (this.d instanceof Inet4Address) {
                    byte[] bArr = new byte[16];
                    for (int i = 0; i < 16; i++) {
                        if (i < 11) {
                            bArr[i] = address[i - 12];
                        } else {
                            bArr[i] = 0;
                        }
                    }
                    address = bArr;
                }
                messageOutputStream.a(address, 0, address.length);
            }
        }

        public ServiceInfo a(boolean z) {
            ServiceInfoImpl serviceInfoImpl = (ServiceInfoImpl) super.a(z);
            serviceInfoImpl.a((Inet6Address) this.d);
            return serviceInfoImpl;
        }
    }

    public static abstract class Address extends DNSRecord {
        private static Logger e = Logger.getLogger(Address.class.getName());
        InetAddress d;

        /* access modifiers changed from: package-private */
        public DNSOutgoing a(JmDNSImpl jmDNSImpl, DNSIncoming dNSIncoming, InetAddress inetAddress, int i, DNSOutgoing dNSOutgoing) throws IOException {
            return dNSOutgoing;
        }

        public boolean o() {
            return false;
        }

        protected Address(String str, DNSRecordType dNSRecordType, DNSRecordClass dNSRecordClass, boolean z, int i, InetAddress inetAddress) {
            super(str, dNSRecordType, dNSRecordClass, z, i);
            this.d = inetAddress;
        }

        protected Address(String str, DNSRecordType dNSRecordType, DNSRecordClass dNSRecordClass, boolean z, int i, byte[] bArr) {
            super(str, dNSRecordType, dNSRecordClass, z, i);
            try {
                this.d = InetAddress.getByAddress(bArr);
            } catch (UnknownHostException e2) {
                e.log(Level.WARNING, "Address() exception ", e2);
            }
        }

        /* access modifiers changed from: package-private */
        public boolean e(DNSRecord dNSRecord) {
            if ((dNSRecord instanceof Address) && f(dNSRecord) && a(dNSRecord)) {
                return true;
            }
            return false;
        }

        /* access modifiers changed from: package-private */
        public boolean f(DNSRecord dNSRecord) {
            return b().equalsIgnoreCase(dNSRecord.b());
        }

        /* access modifiers changed from: package-private */
        public boolean a(DNSRecord dNSRecord) {
            if (!(dNSRecord instanceof Address)) {
                return false;
            }
            Address address = (Address) dNSRecord;
            if (s() == null && address.s() != null) {
                return false;
            }
            try {
                return s().equals(address.s());
            } catch (Exception e2) {
                e2.printStackTrace();
                return false;
            }
        }

        /* access modifiers changed from: package-private */
        public InetAddress s() {
            return this.d;
        }

        /* access modifiers changed from: protected */
        public void a(DataOutputStream dataOutputStream) throws IOException {
            DNSRecord.super.a(dataOutputStream);
            for (byte writeByte : s().getAddress()) {
                dataOutputStream.writeByte(writeByte);
            }
        }

        /* access modifiers changed from: package-private */
        public boolean a(JmDNSImpl jmDNSImpl, long j) {
            if (!jmDNSImpl.r().a(this)) {
                return false;
            }
            int e2 = e(jmDNSImpl.r().a(e(), g(), 3600));
            if (e2 == 0) {
                e.finer("handleQuery() Ignoring an identical address query");
                return false;
            }
            e.finer("handleQuery() Conflicting query detected.");
            if (jmDNSImpl.isProbing() && e2 > 0) {
                jmDNSImpl.r().f();
                jmDNSImpl.q().clear();
                Iterator<ServiceInfo> it = jmDNSImpl.v().values().iterator();
                while (it.hasNext()) {
                    ((ServiceInfoImpl) it.next()).revertState();
                }
            }
            jmDNSImpl.revertState();
            return true;
        }

        /* access modifiers changed from: package-private */
        public boolean a(JmDNSImpl jmDNSImpl) {
            if (!jmDNSImpl.r().a(this)) {
                return false;
            }
            e.finer("handleResponse() Denial detected");
            if (jmDNSImpl.isProbing()) {
                jmDNSImpl.r().f();
                jmDNSImpl.q().clear();
                Iterator<ServiceInfo> it = jmDNSImpl.v().values().iterator();
                while (it.hasNext()) {
                    ((ServiceInfoImpl) it.next()).revertState();
                }
            }
            jmDNSImpl.revertState();
            return true;
        }

        public ServiceInfo a(boolean z) {
            return new ServiceInfoImpl(h(), 0, 0, 0, z, (byte[]) null);
        }

        public ServiceEvent b(JmDNSImpl jmDNSImpl) {
            ServiceInfo a2 = a(false);
            ((ServiceInfoImpl) a2).a(jmDNSImpl);
            return new ServiceEventImpl(jmDNSImpl, a2.b(), a2.d(), a2);
        }

        /* access modifiers changed from: protected */
        public void a(StringBuilder sb) {
            DNSRecord.super.a(sb);
            sb.append(" address: '");
            sb.append(s() != null ? s().getHostAddress() : "null");
            sb.append("'");
        }
    }

    public static class Pointer extends DNSRecord {
        private final String d;

        /* access modifiers changed from: package-private */
        public DNSOutgoing a(JmDNSImpl jmDNSImpl, DNSIncoming dNSIncoming, InetAddress inetAddress, int i, DNSOutgoing dNSOutgoing) throws IOException {
            return dNSOutgoing;
        }

        /* access modifiers changed from: package-private */
        public boolean a(JmDNSImpl jmDNSImpl) {
            return false;
        }

        /* access modifiers changed from: package-private */
        public boolean a(JmDNSImpl jmDNSImpl, long j) {
            return false;
        }

        public boolean o() {
            return false;
        }

        public Pointer(String str, DNSRecordClass dNSRecordClass, boolean z, int i, String str2) {
            super(str, DNSRecordType.TYPE_PTR, dNSRecordClass, z, i);
            this.d = str2;
        }

        public boolean a(DNSEntry dNSEntry) {
            return DNSRecord.super.a(dNSEntry) && (dNSEntry instanceof Pointer) && a((DNSRecord) (Pointer) dNSEntry);
        }

        /* access modifiers changed from: package-private */
        public void a(DNSOutgoing.MessageOutputStream messageOutputStream) {
            messageOutputStream.a(this.d);
        }

        /* access modifiers changed from: package-private */
        public boolean a(DNSRecord dNSRecord) {
            if (!(dNSRecord instanceof Pointer)) {
                return false;
            }
            Pointer pointer = (Pointer) dNSRecord;
            if (this.d != null || pointer.d == null) {
                return this.d.equals(pointer.d);
            }
            return false;
        }

        /* access modifiers changed from: package-private */
        public String s() {
            return this.d;
        }

        public ServiceInfo a(boolean z) {
            if (i()) {
                return new ServiceInfoImpl(ServiceInfoImpl.e(s()), 0, 0, 0, z, (byte[]) null);
            } else if (k()) {
                return new ServiceInfoImpl(h(), 0, 0, 0, z, (byte[]) null);
            } else if (j()) {
                return new ServiceInfoImpl(h(), 0, 0, 0, z, (byte[]) null);
            } else {
                Map<ServiceInfo.Fields, String> e = ServiceInfoImpl.e(s());
                e.put(ServiceInfo.Fields.Subtype, h().get(ServiceInfo.Fields.Subtype));
                return new ServiceInfoImpl(e, 0, 0, 0, z, s());
            }
        }

        public ServiceEvent b(JmDNSImpl jmDNSImpl) {
            ServiceInfo a2 = a(false);
            ((ServiceInfoImpl) a2).a(jmDNSImpl);
            String b = a2.b();
            return new ServiceEventImpl(jmDNSImpl, b, JmDNSImpl.c(b, s()), a2);
        }

        /* access modifiers changed from: protected */
        public void a(StringBuilder sb) {
            DNSRecord.super.a(sb);
            sb.append(" alias: '");
            sb.append(this.d != null ? this.d.toString() : "null");
            sb.append("'");
        }
    }

    public static class Text extends DNSRecord {
        private final byte[] d;

        /* access modifiers changed from: package-private */
        public DNSOutgoing a(JmDNSImpl jmDNSImpl, DNSIncoming dNSIncoming, InetAddress inetAddress, int i, DNSOutgoing dNSOutgoing) throws IOException {
            return dNSOutgoing;
        }

        /* access modifiers changed from: package-private */
        public boolean a(JmDNSImpl jmDNSImpl) {
            return false;
        }

        /* access modifiers changed from: package-private */
        public boolean a(JmDNSImpl jmDNSImpl, long j) {
            return false;
        }

        public boolean o() {
            return true;
        }

        public Text(String str, DNSRecordClass dNSRecordClass, boolean z, int i, byte[] bArr) {
            super(str, DNSRecordType.TYPE_TXT, dNSRecordClass, z, i);
            this.d = (bArr == null || bArr.length <= 0) ? c : bArr;
        }

        /* access modifiers changed from: package-private */
        public byte[] s() {
            return this.d;
        }

        /* access modifiers changed from: package-private */
        public void a(DNSOutgoing.MessageOutputStream messageOutputStream) {
            messageOutputStream.a(this.d, 0, this.d.length);
        }

        /* access modifiers changed from: package-private */
        public boolean a(DNSRecord dNSRecord) {
            if (!(dNSRecord instanceof Text)) {
                return false;
            }
            Text text = (Text) dNSRecord;
            if ((this.d == null && text.d != null) || text.d.length != this.d.length) {
                return false;
            }
            int length = this.d.length;
            while (true) {
                int i = length - 1;
                if (length <= 0) {
                    return true;
                }
                if (text.d[i] != this.d[i]) {
                    return false;
                }
                length = i;
            }
        }

        public ServiceInfo a(boolean z) {
            return new ServiceInfoImpl(h(), 0, 0, 0, z, this.d);
        }

        public ServiceEvent b(JmDNSImpl jmDNSImpl) {
            ServiceInfo a2 = a(false);
            ((ServiceInfoImpl) a2).a(jmDNSImpl);
            return new ServiceEventImpl(jmDNSImpl, a2.b(), a2.d(), a2);
        }

        /* access modifiers changed from: protected */
        public void a(StringBuilder sb) {
            String str;
            DNSRecord.super.a(sb);
            sb.append(" text: '");
            if (this.d.length > 20) {
                str = new String(this.d, 0, 17) + "...";
            } else {
                str = new String(this.d);
            }
            sb.append(str);
            sb.append("'");
        }
    }

    public static class Service extends DNSRecord {
        private static Logger d = Logger.getLogger(Service.class.getName());
        private final int e;
        private final int f;
        private final int g;
        private final String h;

        public boolean o() {
            return true;
        }

        public Service(String str, DNSRecordClass dNSRecordClass, boolean z, int i, int i2, int i3, int i4, String str2) {
            super(str, DNSRecordType.TYPE_SRV, dNSRecordClass, z, i);
            this.e = i2;
            this.f = i3;
            this.g = i4;
            this.h = str2;
        }

        /* access modifiers changed from: package-private */
        public void a(DNSOutgoing.MessageOutputStream messageOutputStream) {
            messageOutputStream.b(this.e);
            messageOutputStream.b(this.f);
            messageOutputStream.b(this.g);
            if (DNSIncoming.f2629a) {
                messageOutputStream.a(this.h);
                return;
            }
            messageOutputStream.b(this.h, 0, this.h.length());
            messageOutputStream.a(0);
        }

        /* access modifiers changed from: protected */
        public void a(DataOutputStream dataOutputStream) throws IOException {
            DNSRecord.super.a(dataOutputStream);
            dataOutputStream.writeShort(this.e);
            dataOutputStream.writeShort(this.f);
            dataOutputStream.writeShort(this.g);
            try {
                dataOutputStream.write(this.h.getBytes("UTF-8"));
            } catch (UnsupportedEncodingException unused) {
            }
        }

        /* access modifiers changed from: package-private */
        public String s() {
            return this.h;
        }

        public int t() {
            return this.e;
        }

        public int u() {
            return this.f;
        }

        public int v() {
            return this.g;
        }

        /* access modifiers changed from: package-private */
        public boolean a(DNSRecord dNSRecord) {
            if (!(dNSRecord instanceof Service)) {
                return false;
            }
            Service service = (Service) dNSRecord;
            if (this.e == service.e && this.f == service.f && this.g == service.g && this.h.equals(service.h)) {
                return true;
            }
            return false;
        }

        /* access modifiers changed from: package-private */
        public boolean a(JmDNSImpl jmDNSImpl, long j) {
            ServiceInfoImpl serviceInfoImpl = (ServiceInfoImpl) jmDNSImpl.v().get(d());
            if (serviceInfoImpl == null || ((!serviceInfoImpl.isAnnouncing() && !serviceInfoImpl.isAnnounced()) || (this.g == serviceInfoImpl.q() && this.h.equalsIgnoreCase(jmDNSImpl.r().a())))) {
                return false;
            }
            Logger logger = d;
            logger.finer("handleQuery() Conflicting probe detected from: " + q());
            Service service = new Service(serviceInfoImpl.f(), DNSRecordClass.CLASS_IN, true, 3600, serviceInfoImpl.r(), serviceInfoImpl.s(), serviceInfoImpl.q(), jmDNSImpl.r().a());
            try {
                if (jmDNSImpl.d().equals(q())) {
                    Logger logger2 = d;
                    logger2.warning("Got conflicting probe from ourselves\nincoming: " + toString() + "\nlocal   : " + service.toString());
                }
            } catch (IOException e2) {
                d.log(Level.WARNING, "IOException", e2);
            }
            int e3 = e(service);
            if (e3 == 0) {
                d.finer("handleQuery() Ignoring a identical service query");
                return false;
            } else if (!serviceInfoImpl.isProbing() || e3 <= 0) {
                return false;
            } else {
                String lowerCase = serviceInfoImpl.f().toLowerCase();
                serviceInfoImpl.f(jmDNSImpl.e(serviceInfoImpl.d()));
                jmDNSImpl.v().remove(lowerCase);
                jmDNSImpl.v().put(serviceInfoImpl.f().toLowerCase(), serviceInfoImpl);
                Logger logger3 = d;
                logger3.finer("handleQuery() Lost tie break: new unique name chosen:" + serviceInfoImpl.d());
                serviceInfoImpl.revertState();
                return true;
            }
        }

        /* access modifiers changed from: package-private */
        public boolean a(JmDNSImpl jmDNSImpl) {
            ServiceInfoImpl serviceInfoImpl = (ServiceInfoImpl) jmDNSImpl.v().get(d());
            if (serviceInfoImpl == null) {
                return false;
            }
            if (this.g == serviceInfoImpl.q() && this.h.equalsIgnoreCase(jmDNSImpl.r().a())) {
                return false;
            }
            d.finer("handleResponse() Denial detected");
            if (serviceInfoImpl.isProbing()) {
                String lowerCase = serviceInfoImpl.f().toLowerCase();
                serviceInfoImpl.f(jmDNSImpl.e(serviceInfoImpl.d()));
                jmDNSImpl.v().remove(lowerCase);
                jmDNSImpl.v().put(serviceInfoImpl.f().toLowerCase(), serviceInfoImpl);
                Logger logger = d;
                logger.finer("handleResponse() New unique name chose:" + serviceInfoImpl.d());
            }
            serviceInfoImpl.revertState();
            return true;
        }

        /* access modifiers changed from: package-private */
        public DNSOutgoing a(JmDNSImpl jmDNSImpl, DNSIncoming dNSIncoming, InetAddress inetAddress, int i, DNSOutgoing dNSOutgoing) throws IOException {
            ServiceInfoImpl serviceInfoImpl = (ServiceInfoImpl) jmDNSImpl.v().get(d());
            if (serviceInfoImpl != null) {
                if ((this.g == serviceInfoImpl.q()) != this.h.equals(jmDNSImpl.r().a())) {
                    return jmDNSImpl.a(dNSIncoming, inetAddress, i, dNSOutgoing, new Service(serviceInfoImpl.f(), DNSRecordClass.CLASS_IN, true, 3600, serviceInfoImpl.r(), serviceInfoImpl.s(), serviceInfoImpl.q(), jmDNSImpl.r().a()));
                }
            }
            return dNSOutgoing;
        }

        public ServiceInfo a(boolean z) {
            return new ServiceInfoImpl(h(), this.g, this.f, this.e, z, this.h);
        }

        public ServiceEvent b(JmDNSImpl jmDNSImpl) {
            ServiceInfo a2 = a(false);
            ((ServiceInfoImpl) a2).a(jmDNSImpl);
            return new ServiceEventImpl(jmDNSImpl, a2.b(), a2.d(), a2);
        }

        /* access modifiers changed from: protected */
        public void a(StringBuilder sb) {
            DNSRecord.super.a(sb);
            sb.append(" server: '");
            sb.append(this.h);
            sb.append(":");
            sb.append(this.g);
            sb.append("'");
        }
    }

    public static class HostInformation extends DNSRecord {
        String d;
        String e;

        /* access modifiers changed from: package-private */
        public DNSOutgoing a(JmDNSImpl jmDNSImpl, DNSIncoming dNSIncoming, InetAddress inetAddress, int i, DNSOutgoing dNSOutgoing) throws IOException {
            return dNSOutgoing;
        }

        /* access modifiers changed from: package-private */
        public boolean a(JmDNSImpl jmDNSImpl) {
            return false;
        }

        /* access modifiers changed from: package-private */
        public boolean a(JmDNSImpl jmDNSImpl, long j) {
            return false;
        }

        public boolean o() {
            return true;
        }

        public HostInformation(String str, DNSRecordClass dNSRecordClass, boolean z, int i, String str2, String str3) {
            super(str, DNSRecordType.TYPE_HINFO, dNSRecordClass, z, i);
            this.e = str2;
            this.d = str3;
        }

        /* access modifiers changed from: package-private */
        public boolean a(DNSRecord dNSRecord) {
            if (!(dNSRecord instanceof HostInformation)) {
                return false;
            }
            HostInformation hostInformation = (HostInformation) dNSRecord;
            if (this.e == null && hostInformation.e != null) {
                return false;
            }
            if ((this.d != null || hostInformation.d == null) && this.e.equals(hostInformation.e) && this.d.equals(hostInformation.d)) {
                return true;
            }
            return false;
        }

        /* access modifiers changed from: package-private */
        public void a(DNSOutgoing.MessageOutputStream messageOutputStream) {
            String str = this.e + " " + this.d;
            messageOutputStream.b(str, 0, str.length());
        }

        public ServiceInfo a(boolean z) {
            HashMap hashMap = new HashMap(2);
            hashMap.put("cpu", this.e);
            hashMap.put("os", this.d);
            return new ServiceInfoImpl(h(), 0, 0, 0, z, (Map<String, ?>) hashMap);
        }

        public ServiceEvent b(JmDNSImpl jmDNSImpl) {
            ServiceInfo a2 = a(false);
            ((ServiceInfoImpl) a2).a(jmDNSImpl);
            return new ServiceEventImpl(jmDNSImpl, a2.b(), a2.d(), a2);
        }

        /* access modifiers changed from: protected */
        public void a(StringBuilder sb) {
            DNSRecord.super.a(sb);
            sb.append(" cpu: '");
            sb.append(this.e);
            sb.append("' os: '");
            sb.append(this.d);
            sb.append("'");
        }
    }

    public ServiceInfo p() {
        return a(false);
    }

    public void a(InetAddress inetAddress) {
        this.b = inetAddress;
    }

    public InetAddress q() {
        return this.b;
    }

    /* access modifiers changed from: protected */
    public void a(StringBuilder sb) {
        super.a(sb);
        sb.append(" ttl: '");
        sb.append(c(System.currentTimeMillis()));
        sb.append("/");
        sb.append(this.e);
        sb.append("'");
    }

    public void b(int i) {
        this.e = i;
    }

    public int r() {
        return this.e;
    }
}
