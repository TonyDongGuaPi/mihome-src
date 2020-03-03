package javax.jmdns.impl;

import com.taobao.weex.el.parse.Operators;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.Inet4Address;
import java.net.Inet6Address;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.jmdns.NetworkTopologyDiscovery;
import javax.jmdns.impl.DNSRecord;
import javax.jmdns.impl.DNSStatefulObject;
import javax.jmdns.impl.constants.DNSRecordClass;
import javax.jmdns.impl.constants.DNSRecordType;
import javax.jmdns.impl.constants.DNSState;
import javax.jmdns.impl.tasks.DNSTask;

public class HostInfo implements DNSStatefulObject {
    private static Logger d = Logger.getLogger(HostInfo.class.getName());

    /* renamed from: a  reason: collision with root package name */
    protected String f2641a;
    protected InetAddress b;
    protected NetworkInterface c;
    private final HostInfoState e;
    private int f;

    private static final class HostInfoState extends DNSStatefulObject.DefaultImplementation {
        private static final long serialVersionUID = -8191476803620402088L;

        public HostInfoState(JmDNSImpl jmDNSImpl) {
            setDns(jmDNSImpl);
        }
    }

    public static HostInfo a(InetAddress inetAddress, JmDNSImpl jmDNSImpl, String str) {
        String str2;
        InetAddress inetAddress2;
        if (inetAddress == null) {
            try {
                String property = System.getProperty("net.mdns.interface");
                if (property != null) {
                    inetAddress2 = InetAddress.getByName(property);
                } else {
                    inetAddress2 = InetAddress.getLocalHost();
                    if (inetAddress2.isLoopbackAddress()) {
                        InetAddress[] a2 = NetworkTopologyDiscovery.Factory.c().a();
                        if (a2.length > 0) {
                            inetAddress2 = a2[0];
                        }
                    }
                }
                str2 = inetAddress2.getHostName();
                if (inetAddress2.isLoopbackAddress()) {
                    d.warning("Could not find any address beside the loopback.");
                }
            } catch (IOException e2) {
                Logger logger = d;
                Level level = Level.WARNING;
                logger.log(level, "Could not intialize the host network interface on " + inetAddress + "because of an error: " + e2.getMessage(), e2);
                inetAddress2 = g();
                if (str == null || str.length() <= 0) {
                    str = "computer";
                }
            }
        } else {
            str2 = inetAddress.getHostName();
            inetAddress2 = inetAddress;
        }
        if (str2.contains("in-addr.arpa") || str2.equals(inetAddress2.getHostAddress())) {
            if (str == null || str.length() <= 0) {
                str2 = inetAddress2.getHostAddress();
            }
            str2 = str;
        }
        String replace = str2.replace('.', '-');
        return new HostInfo(inetAddress2, replace + ".local.", jmDNSImpl);
    }

    private static InetAddress g() {
        try {
            return InetAddress.getByName((String) null);
        } catch (UnknownHostException unused) {
            return null;
        }
    }

    private HostInfo(InetAddress inetAddress, String str, JmDNSImpl jmDNSImpl) {
        this.e = new HostInfoState(jmDNSImpl);
        this.b = inetAddress;
        this.f2641a = str;
        if (inetAddress != null) {
            try {
                this.c = NetworkInterface.getByInetAddress(inetAddress);
            } catch (Exception e2) {
                d.log(Level.SEVERE, "LocalHostInfo() exception ", e2);
            }
        }
    }

    public String a() {
        return this.f2641a;
    }

    public InetAddress b() {
        return this.b;
    }

    /* access modifiers changed from: package-private */
    public Inet4Address c() {
        if (b() instanceof Inet4Address) {
            return (Inet4Address) this.b;
        }
        return null;
    }

    /* access modifiers changed from: package-private */
    public Inet6Address d() {
        if (b() instanceof Inet6Address) {
            return (Inet6Address) this.b;
        }
        return null;
    }

    public NetworkInterface e() {
        return this.c;
    }

    public boolean a(DNSRecord.Address address) {
        DNSRecord.Address a2 = a(address.e(), address.g(), 3600);
        if (a2 == null || !a2.b((DNSRecord) address) || !a2.f(address) || a2.a((DNSRecord) address)) {
            return false;
        }
        return true;
    }

    /* access modifiers changed from: package-private */
    public synchronized String f() {
        this.f++;
        int indexOf = this.f2641a.indexOf(".local.");
        int lastIndexOf = this.f2641a.lastIndexOf(45);
        StringBuilder sb = new StringBuilder();
        String str = this.f2641a;
        if (lastIndexOf != -1) {
            indexOf = lastIndexOf;
        }
        sb.append(str.substring(0, indexOf));
        sb.append("-");
        sb.append(this.f);
        sb.append(".local.");
        this.f2641a = sb.toString();
        return this.f2641a;
    }

    /* access modifiers changed from: package-private */
    public boolean a(DatagramPacket datagramPacket) {
        InetAddress address;
        boolean z = false;
        if (b() == null || (address = datagramPacket.getAddress()) == null) {
            return false;
        }
        if (address.isLinkLocalAddress() && !b().isLinkLocalAddress()) {
            z = true;
        }
        if (!address.isLoopbackAddress() || b().isLoopbackAddress()) {
            return z;
        }
        return true;
    }

    /* access modifiers changed from: package-private */
    public DNSRecord.Address a(DNSRecordType dNSRecordType, boolean z, int i) {
        switch (dNSRecordType) {
            case TYPE_A:
                return b(z, i);
            case TYPE_A6:
            case TYPE_AAAA:
                return c(z, i);
            default:
                return null;
        }
    }

    private DNSRecord.Address b(boolean z, int i) {
        if (!(b() instanceof Inet4Address) && (!(b() instanceof Inet6Address) || !((Inet6Address) b()).isIPv4CompatibleAddress())) {
            return null;
        }
        return new DNSRecord.IPv4Address(a(), DNSRecordClass.CLASS_IN, z, i, b());
    }

    private DNSRecord.Address c(boolean z, int i) {
        if (!(b() instanceof Inet6Address)) {
            return null;
        }
        return new DNSRecord.IPv6Address(a(), DNSRecordClass.CLASS_IN, z, i, b());
    }

    /* access modifiers changed from: package-private */
    public DNSRecord.Pointer b(DNSRecordType dNSRecordType, boolean z, int i) {
        switch (dNSRecordType) {
            case TYPE_A:
                return d(z, i);
            case TYPE_A6:
            case TYPE_AAAA:
                return e(z, i);
            default:
                return null;
        }
    }

    private DNSRecord.Pointer d(boolean z, int i) {
        if (b() instanceof Inet4Address) {
            return new DNSRecord.Pointer(b().getHostAddress() + ".in-addr.arpa.", DNSRecordClass.CLASS_IN, z, i, a());
        } else if (!(b() instanceof Inet6Address) || !((Inet6Address) b()).isIPv4CompatibleAddress()) {
            return null;
        } else {
            byte[] address = b().getAddress();
            return new DNSRecord.Pointer(((address[12] & 255) + "." + (address[13] & 255) + "." + (address[14] & 255) + "." + (address[15] & 255)) + ".in-addr.arpa.", DNSRecordClass.CLASS_IN, z, i, a());
        }
    }

    private DNSRecord.Pointer e(boolean z, int i) {
        if (!(b() instanceof Inet6Address)) {
            return null;
        }
        return new DNSRecord.Pointer(b().getHostAddress() + ".ip6.arpa.", DNSRecordClass.CLASS_IN, z, i, a());
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("local host info[");
        sb.append(a() != null ? a() : "no name");
        sb.append(", ");
        sb.append(e() != null ? e().getDisplayName() : "???");
        sb.append(":");
        sb.append(b() != null ? b().getHostAddress() : "no address");
        sb.append(", ");
        sb.append(this.e);
        sb.append(Operators.ARRAY_END_STR);
        return sb.toString();
    }

    public Collection<DNSRecord> a(boolean z, int i) {
        ArrayList arrayList = new ArrayList();
        DNSRecord.Address b2 = b(z, i);
        if (b2 != null) {
            arrayList.add(b2);
        }
        DNSRecord.Address c2 = c(z, i);
        if (c2 != null) {
            arrayList.add(c2);
        }
        return arrayList;
    }

    public JmDNSImpl getDns() {
        return this.e.getDns();
    }

    public boolean advanceState(DNSTask dNSTask) {
        return this.e.advanceState(dNSTask);
    }

    public void removeAssociationWithTask(DNSTask dNSTask) {
        this.e.removeAssociationWithTask(dNSTask);
    }

    public boolean revertState() {
        return this.e.revertState();
    }

    public void associateWithTask(DNSTask dNSTask, DNSState dNSState) {
        this.e.associateWithTask(dNSTask, dNSState);
    }

    public boolean isAssociatedWithTask(DNSTask dNSTask, DNSState dNSState) {
        return this.e.isAssociatedWithTask(dNSTask, dNSState);
    }

    public boolean cancelState() {
        return this.e.cancelState();
    }

    public boolean closeState() {
        return this.e.closeState();
    }

    public boolean recoverState() {
        return this.e.recoverState();
    }

    public boolean isProbing() {
        return this.e.isProbing();
    }

    public boolean isAnnouncing() {
        return this.e.isAnnouncing();
    }

    public boolean isAnnounced() {
        return this.e.isAnnounced();
    }

    public boolean isCanceling() {
        return this.e.isCanceling();
    }

    public boolean isCanceled() {
        return this.e.isCanceled();
    }

    public boolean isClosing() {
        return this.e.isClosing();
    }

    public boolean isClosed() {
        return this.e.isClosed();
    }

    public boolean waitForAnnounced(long j) {
        return this.e.waitForAnnounced(j);
    }

    public boolean waitForCanceled(long j) {
        if (this.b == null) {
            return true;
        }
        return this.e.waitForCanceled(j);
    }
}
