package javax.jmdns.impl;

import com.huawei.hms.support.api.push.HmsPushConst;
import com.taobao.weex.annotation.JSMethod;
import com.taobao.weex.el.parse.Operators;
import java.io.IOException;
import java.io.PrintStream;
import java.io.Serializable;
import java.net.DatagramPacket;
import java.net.Inet4Address;
import java.net.Inet6Address;
import java.net.InetAddress;
import java.net.MulticastSocket;
import java.net.SocketException;
import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Random;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.locks.ReentrantLock;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.jmdns.JmDNS;
import javax.jmdns.ServiceEvent;
import javax.jmdns.ServiceInfo;
import javax.jmdns.ServiceListener;
import javax.jmdns.ServiceTypeListener;
import javax.jmdns.impl.DNSRecord;
import javax.jmdns.impl.DNSTaskStarter;
import javax.jmdns.impl.ListenerStatus;
import javax.jmdns.impl.constants.DNSConstants;
import javax.jmdns.impl.constants.DNSRecordClass;
import javax.jmdns.impl.constants.DNSRecordType;
import javax.jmdns.impl.constants.DNSState;
import javax.jmdns.impl.tasks.DNSTask;

public class JmDNSImpl extends JmDNS implements DNSStatefulObject, DNSTaskStarter {
    private static Logger c = Logger.getLogger(JmDNSImpl.class.getName());
    private static final Random r = new Random();
    protected Thread b;
    private volatile InetAddress d;
    private volatile MulticastSocket e;
    private final List<DNSListener> f;
    private final ConcurrentMap<String, List<ListenerStatus.ServiceListenerStatus>> g;
    private final Set<ListenerStatus.ServiceTypeListenerStatus> h;
    private final DNSCache i;
    private final ConcurrentMap<String, ServiceInfo> j;
    private final ConcurrentMap<String, ServiceTypeEntry> k;
    private volatile JmDNS.Delegate l;
    private HostInfo m;
    private Thread n;
    private int o;
    private long p;
    private final ExecutorService q = Executors.newSingleThreadExecutor();
    private final ReentrantLock s = new ReentrantLock();
    private DNSIncoming t;
    private final ConcurrentMap<String, ServiceCollector> u;
    private final String v;
    private final Object w = new Object();

    public enum Operation {
        Remove,
        Update,
        Add,
        RegisterServiceType,
        Noop
    }

    public JmDNSImpl getDns() {
        return this;
    }

    public static class ServiceTypeEntry extends AbstractMap<String, String> implements Cloneable {

        /* renamed from: a  reason: collision with root package name */
        private final Set<Map.Entry<String, String>> f2651a = new HashSet();
        private final String b;

        private static class SubTypeEntry implements Serializable, Cloneable, Map.Entry<String, String> {
            private static final long serialVersionUID = 9188503522395855322L;
            private final String _key;
            private final String _value;

            public SubTypeEntry clone() {
                return this;
            }

            public SubTypeEntry(String str) {
                this._value = str == null ? "" : str;
                this._key = this._value.toLowerCase();
            }

            public String getKey() {
                return this._key;
            }

            public String getValue() {
                return this._value;
            }

            public String setValue(String str) {
                throw new UnsupportedOperationException();
            }

            public boolean equals(Object obj) {
                if (!(obj instanceof Map.Entry)) {
                    return false;
                }
                Map.Entry entry = (Map.Entry) obj;
                if (!getKey().equals(entry.getKey()) || !getValue().equals(entry.getValue())) {
                    return false;
                }
                return true;
            }

            public int hashCode() {
                int i = 0;
                int hashCode = this._key == null ? 0 : this._key.hashCode();
                if (this._value != null) {
                    i = this._value.hashCode();
                }
                return hashCode ^ i;
            }

            public String toString() {
                return this._key + "=" + this._value;
            }
        }

        public ServiceTypeEntry(String str) {
            this.b = str;
        }

        public String a() {
            return this.b;
        }

        public Set<Map.Entry<String, String>> entrySet() {
            return this.f2651a;
        }

        public boolean a(String str) {
            return str != null && containsKey(str.toLowerCase());
        }

        public boolean b(String str) {
            if (str == null || a(str)) {
                return false;
            }
            this.f2651a.add(new SubTypeEntry(str));
            return true;
        }

        public Iterator<String> b() {
            return keySet().iterator();
        }

        /* renamed from: c */
        public ServiceTypeEntry clone() {
            ServiceTypeEntry serviceTypeEntry = new ServiceTypeEntry(a());
            for (Map.Entry<String, String> value : entrySet()) {
                serviceTypeEntry.b((String) value.getValue());
            }
            return serviceTypeEntry;
        }

        public String toString() {
            StringBuilder sb = new StringBuilder(200);
            if (isEmpty()) {
                sb.append("empty");
            } else {
                for (String append : values()) {
                    sb.append(append);
                    sb.append(", ");
                }
                sb.setLength(sb.length() - 2);
            }
            return sb.toString();
        }
    }

    public static void a(String[] strArr) {
        String str;
        try {
            Properties properties = new Properties();
            properties.load(JmDNSImpl.class.getResourceAsStream("/META-INF/maven/javax.jmdns/jmdns/pom.properties"));
            str = properties.getProperty("version");
        } catch (Exception unused) {
            str = "RUNNING.IN.IDE.FULL";
        }
        PrintStream printStream = System.out;
        printStream.println("JmDNS version \"" + str + "\"");
        System.out.println(" ");
        PrintStream printStream2 = System.out;
        printStream2.println("Running on java version \"" + System.getProperty("java.version") + "\" (build " + System.getProperty("java.runtime.version") + ") from " + System.getProperty("java.vendor"));
        PrintStream printStream3 = System.out;
        printStream3.println("Operating environment \"" + System.getProperty("os.name") + "\" version " + System.getProperty("os.version") + " on " + System.getProperty("os.arch"));
        System.out.println("For more information on JmDNS please visit https://sourceforge.net/projects/jmdns/");
    }

    public JmDNSImpl(InetAddress inetAddress, String str) throws Exception {
        if (c.isLoggable(Level.FINER)) {
            c.finer("JmDNS instance created");
        }
        this.i = new DNSCache(100);
        this.f = Collections.synchronizedList(new ArrayList());
        this.g = new ConcurrentHashMap();
        this.h = Collections.synchronizedSet(new HashSet());
        this.u = new ConcurrentHashMap();
        this.j = new ConcurrentHashMap(20);
        this.k = new ConcurrentHashMap(20);
        this.m = HostInfo.a(inetAddress, this, str);
        this.v = str == null ? this.m.a() : str;
        b(r());
        a((Collection<? extends ServiceInfo>) v().values());
        o();
    }

    private void a(Collection<? extends ServiceInfo> collection) {
        if (this.n == null) {
            this.n = new SocketListener(this);
            this.n.start();
        }
        k();
        for (ServiceInfo serviceInfoImpl : collection) {
            try {
                a((ServiceInfo) new ServiceInfoImpl(serviceInfoImpl));
            } catch (Exception e2) {
                c.log(Level.WARNING, "start() Registration exception ", e2);
            }
        }
    }

    private void b(HostInfo hostInfo) throws Exception {
        if (this.d == null) {
            if (hostInfo.b() instanceof Inet6Address) {
                this.d = InetAddress.getByName(DNSConstants.b);
            } else {
                this.d = InetAddress.getByName(DNSConstants.f2666a);
            }
        }
        if (this.e != null) {
            F();
        }
        this.e = new MulticastSocket(DNSConstants.c);
        if (!(hostInfo == null || hostInfo.e() == null)) {
            try {
                this.e.setNetworkInterface(hostInfo.e());
            } catch (SocketException e2) {
                if (c.isLoggable(Level.FINE)) {
                    Logger logger = c;
                    logger.fine("openMulticastSocket() Set network interface exception: " + e2.getMessage());
                }
            }
        }
        this.e.setTimeToLive(255);
        this.e.joinGroup(this.d);
    }

    /* JADX WARNING: Can't wrap try/catch for region: R(9:5|6|7|9|10|(3:31|28|11)|32|34|37) */
    /* JADX WARNING: Code restructure failed: missing block: B:33:0x005e, code lost:
        c.log(java.util.logging.Level.WARNING, "closeMulticastSocket() Close socket exception ", r1);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:8:0x001e, code lost:
        r1 = move-exception;
     */
    /* JADX WARNING: Exception block dominator not found, dom blocks: [] */
    /* JADX WARNING: Failed to process nested try/catch */
    /* JADX WARNING: Missing exception handler attribute for start block: B:26:0x0057 */
    /* JADX WARNING: Missing exception handler attribute for start block: B:9:0x0020 */
    /* JADX WARNING: Removed duplicated region for block: B:15:0x0031 A[Catch:{ Exception -> 0x001e }] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private void F() {
        /*
            r5 = this;
            java.util.logging.Logger r0 = c
            java.util.logging.Level r1 = java.util.logging.Level.FINER
            boolean r0 = r0.isLoggable(r1)
            if (r0 == 0) goto L_0x0011
            java.util.logging.Logger r0 = c
            java.lang.String r1 = "closeMulticastSocket()"
            r0.finer(r1)
        L_0x0011:
            java.net.MulticastSocket r0 = r5.e
            if (r0 == 0) goto L_0x0069
            r0 = 0
            java.net.MulticastSocket r1 = r5.e     // Catch:{ SocketException -> 0x0020 }
            java.net.InetAddress r2 = r5.d     // Catch:{ SocketException -> 0x0020 }
            r1.leaveGroup(r2)     // Catch:{ SocketException -> 0x0020 }
            goto L_0x0020
        L_0x001e:
            r1 = move-exception
            goto L_0x005e
        L_0x0020:
            java.net.MulticastSocket r1 = r5.e     // Catch:{ Exception -> 0x001e }
            r1.close()     // Catch:{ Exception -> 0x001e }
        L_0x0025:
            java.lang.Thread r1 = r5.n     // Catch:{ Exception -> 0x001e }
            if (r1 == 0) goto L_0x005b
            java.lang.Thread r1 = r5.n     // Catch:{ Exception -> 0x001e }
            boolean r1 = r1.isAlive()     // Catch:{ Exception -> 0x001e }
            if (r1 == 0) goto L_0x005b
            monitor-enter(r5)     // Catch:{ Exception -> 0x001e }
            java.lang.Thread r1 = r5.n     // Catch:{ InterruptedException -> 0x0057 }
            if (r1 == 0) goto L_0x0057
            java.lang.Thread r1 = r5.n     // Catch:{ InterruptedException -> 0x0057 }
            boolean r1 = r1.isAlive()     // Catch:{ InterruptedException -> 0x0057 }
            if (r1 == 0) goto L_0x0057
            java.util.logging.Logger r1 = c     // Catch:{ InterruptedException -> 0x0057 }
            java.util.logging.Level r2 = java.util.logging.Level.FINER     // Catch:{ InterruptedException -> 0x0057 }
            boolean r1 = r1.isLoggable(r2)     // Catch:{ InterruptedException -> 0x0057 }
            if (r1 == 0) goto L_0x004f
            java.util.logging.Logger r1 = c     // Catch:{ InterruptedException -> 0x0057 }
            java.lang.String r2 = "closeMulticastSocket(): waiting for jmDNS monitor"
            r1.finer(r2)     // Catch:{ InterruptedException -> 0x0057 }
        L_0x004f:
            r1 = 1000(0x3e8, double:4.94E-321)
            r5.wait(r1)     // Catch:{ InterruptedException -> 0x0057 }
            goto L_0x0057
        L_0x0055:
            r1 = move-exception
            goto L_0x0059
        L_0x0057:
            monitor-exit(r5)     // Catch:{ all -> 0x0055 }
            goto L_0x0025
        L_0x0059:
            monitor-exit(r5)     // Catch:{ all -> 0x0055 }
            throw r1     // Catch:{ Exception -> 0x001e }
        L_0x005b:
            r5.n = r0     // Catch:{ Exception -> 0x001e }
            goto L_0x0067
        L_0x005e:
            java.util.logging.Logger r2 = c
            java.util.logging.Level r3 = java.util.logging.Level.WARNING
            java.lang.String r4 = "closeMulticastSocket() Close socket exception "
            r2.log(r3, r4, r1)
        L_0x0067:
            r5.e = r0
        L_0x0069:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: javax.jmdns.impl.JmDNSImpl.F():void");
    }

    public boolean advanceState(DNSTask dNSTask) {
        return this.m.advanceState(dNSTask);
    }

    public boolean revertState() {
        return this.m.revertState();
    }

    public boolean cancelState() {
        return this.m.cancelState();
    }

    public boolean closeState() {
        return this.m.closeState();
    }

    public boolean recoverState() {
        return this.m.recoverState();
    }

    public void associateWithTask(DNSTask dNSTask, DNSState dNSState) {
        this.m.associateWithTask(dNSTask, dNSState);
    }

    public void removeAssociationWithTask(DNSTask dNSTask) {
        this.m.removeAssociationWithTask(dNSTask);
    }

    public boolean isAssociatedWithTask(DNSTask dNSTask, DNSState dNSState) {
        return this.m.isAssociatedWithTask(dNSTask, dNSState);
    }

    public boolean isProbing() {
        return this.m.isProbing();
    }

    public boolean isAnnouncing() {
        return this.m.isAnnouncing();
    }

    public boolean isAnnounced() {
        return this.m.isAnnounced();
    }

    public boolean isCanceling() {
        return this.m.isCanceling();
    }

    public boolean isCanceled() {
        return this.m.isCanceled();
    }

    public boolean isClosing() {
        return this.m.isClosing();
    }

    public boolean isClosed() {
        return this.m.isClosed();
    }

    public boolean waitForAnnounced(long j2) {
        return this.m.waitForAnnounced(j2);
    }

    public boolean waitForCanceled(long j2) {
        return this.m.waitForCanceled(j2);
    }

    public DNSCache q() {
        return this.i;
    }

    public String b() {
        return this.v;
    }

    public String c() {
        return this.m.a();
    }

    public HostInfo r() {
        return this.m;
    }

    public InetAddress d() throws IOException {
        return this.e.getInterface();
    }

    public ServiceInfo a(String str, String str2) {
        return a(str, str2, false, 6000);
    }

    public ServiceInfo a(String str, String str2, long j2) {
        return a(str, str2, false, j2);
    }

    public ServiceInfo a(String str, String str2, boolean z) {
        return a(str, str2, z, 6000);
    }

    public ServiceInfo a(String str, String str2, boolean z, long j2) {
        ServiceInfoImpl a2 = a(str, str2, "", z);
        a((ServiceInfo) a2, j2);
        if (a2.a()) {
            return a2;
        }
        return null;
    }

    /* access modifiers changed from: package-private */
    public ServiceInfoImpl a(String str, String str2, String str3, boolean z) {
        u();
        String lowerCase = str.toLowerCase();
        b(str);
        if (this.u.putIfAbsent(lowerCase, new ServiceCollector(str)) == null) {
            a(lowerCase, (ServiceListener) this.u.get(lowerCase), true);
        }
        ServiceInfoImpl b2 = b(str, str2, str3, z);
        a(b2);
        return b2;
    }

    /* access modifiers changed from: package-private */
    public ServiceInfoImpl b(String str, String str2, String str3, boolean z) {
        ServiceInfoImpl serviceInfoImpl;
        ServiceInfo a2;
        ServiceInfo a3;
        ServiceInfo a4;
        ServiceInfo a5;
        boolean z2 = z;
        byte[] bArr = null;
        byte[] bArr2 = null;
        ServiceInfoImpl serviceInfoImpl2 = new ServiceInfoImpl(str, str2, str3, 0, 0, 0, z, bArr2);
        DNSEntry a6 = q().a((DNSEntry) new DNSRecord.Pointer(str, DNSRecordClass.CLASS_ANY, false, 0, serviceInfoImpl2.f()));
        if ((a6 instanceof DNSRecord) && (serviceInfoImpl = (ServiceInfoImpl) ((DNSRecord) a6).a(z2)) != null) {
            Map<ServiceInfo.Fields, String> E = serviceInfoImpl.E();
            String str4 = "";
            DNSEntry a7 = q().a(serviceInfoImpl2.f(), DNSRecordType.TYPE_SRV, DNSRecordClass.CLASS_ANY);
            if ((a7 instanceof DNSRecord) && (a5 = ((DNSRecord) a7).a(z2)) != null) {
                serviceInfoImpl = new ServiceInfoImpl(E, a5.q(), a5.s(), a5.r(), z, bArr2);
                bArr = a5.t();
                str4 = a5.g();
            }
            DNSEntry a8 = q().a(str4, DNSRecordType.TYPE_A, DNSRecordClass.CLASS_ANY);
            if ((a8 instanceof DNSRecord) && (a4 = ((DNSRecord) a8).a(z2)) != null) {
                for (Inet4Address a9 : a4.o()) {
                    serviceInfoImpl.a(a9);
                }
                serviceInfoImpl.b(a4.t());
            }
            DNSEntry a10 = q().a(str4, DNSRecordType.TYPE_AAAA, DNSRecordClass.CLASS_ANY);
            if ((a10 instanceof DNSRecord) && (a3 = ((DNSRecord) a10).a(z2)) != null) {
                for (Inet6Address a11 : a3.p()) {
                    serviceInfoImpl.a(a11);
                }
                serviceInfoImpl.b(a3.t());
            }
            DNSEntry a12 = q().a(serviceInfoImpl.f(), DNSRecordType.TYPE_TXT, DNSRecordClass.CLASS_ANY);
            if ((a12 instanceof DNSRecord) && (a2 = ((DNSRecord) a12).a(z2)) != null) {
                serviceInfoImpl.b(a2.t());
            }
            if (serviceInfoImpl.t().length == 0) {
                serviceInfoImpl.b(bArr);
            }
            if (serviceInfoImpl.a()) {
                return serviceInfoImpl;
            }
        }
        return serviceInfoImpl2;
    }

    private void a(ServiceInfo serviceInfo, long j2) {
        synchronized (serviceInfo) {
            long j3 = j2 / 200;
            if (j3 < 1) {
                j3 = 1;
            }
            for (int i2 = 0; ((long) i2) < j3 && !serviceInfo.a(); i2++) {
                try {
                    serviceInfo.wait(200);
                } catch (InterruptedException unused) {
                }
            }
        }
    }

    public void b(String str, String str2) {
        b(str, str2, false, 6000);
    }

    public void b(String str, String str2, boolean z) {
        b(str, str2, z, 6000);
    }

    public void b(String str, String str2, long j2) {
        b(str, str2, false, 6000);
    }

    public void b(String str, String str2, boolean z, long j2) {
        a((ServiceInfo) a(str, str2, "", z), j2);
    }

    /* access modifiers changed from: package-private */
    public void a(final ServiceEvent serviceEvent) {
        ArrayList<ListenerStatus.ServiceListenerStatus> arrayList;
        List list = (List) this.g.get(serviceEvent.getType().toLowerCase());
        if (list != null && !list.isEmpty() && serviceEvent.getInfo() != null && serviceEvent.getInfo().a()) {
            synchronized (list) {
                arrayList = new ArrayList<>(list);
            }
            for (final ListenerStatus.ServiceListenerStatus serviceListenerStatus : arrayList) {
                this.q.submit(new Runnable() {
                    public void run() {
                        serviceListenerStatus.c(serviceEvent);
                    }
                });
            }
        }
    }

    public void a(ServiceTypeListener serviceTypeListener) throws IOException {
        ListenerStatus.ServiceTypeListenerStatus serviceTypeListenerStatus = new ListenerStatus.ServiceTypeListenerStatus(serviceTypeListener, false);
        this.h.add(serviceTypeListenerStatus);
        for (String serviceEventImpl : this.k.keySet()) {
            serviceTypeListenerStatus.a(new ServiceEventImpl(this, serviceEventImpl, "", (ServiceInfo) null));
        }
        p();
    }

    public void b(ServiceTypeListener serviceTypeListener) {
        this.h.remove(new ListenerStatus.ServiceTypeListenerStatus(serviceTypeListener, false));
    }

    public void a(String str, ServiceListener serviceListener) {
        a(str, serviceListener, false);
    }

    private void a(String str, ServiceListener serviceListener, boolean z) {
        ListenerStatus.ServiceListenerStatus serviceListenerStatus = new ListenerStatus.ServiceListenerStatus(serviceListener, z);
        String lowerCase = str.toLowerCase();
        List list = (List) this.g.get(lowerCase);
        if (list == null) {
            if (this.g.putIfAbsent(lowerCase, new LinkedList()) == null && this.u.putIfAbsent(lowerCase, new ServiceCollector(str)) == null) {
                a(lowerCase, (ServiceListener) this.u.get(lowerCase), true);
            }
            list = (List) this.g.get(lowerCase);
        }
        if (list != null) {
            synchronized (list) {
                if (!list.contains(serviceListener)) {
                    list.add(serviceListenerStatus);
                }
            }
        }
        ArrayList<ServiceEvent> arrayList = new ArrayList<>();
        Iterator<DNSEntry> it = q().a().iterator();
        while (it.hasNext()) {
            DNSRecord dNSRecord = (DNSRecord) it.next();
            if (dNSRecord.e() == DNSRecordType.TYPE_SRV && dNSRecord.d().endsWith(lowerCase)) {
                arrayList.add(new ServiceEventImpl(this, dNSRecord.c(), c(dNSRecord.c(), dNSRecord.b()), dNSRecord.p()));
            }
        }
        for (ServiceEvent a2 : arrayList) {
            serviceListenerStatus.a(a2);
        }
        e_(str);
    }

    public void b(String str, ServiceListener serviceListener) {
        String lowerCase = str.toLowerCase();
        List list = (List) this.g.get(lowerCase);
        if (list != null) {
            synchronized (list) {
                list.remove(new ListenerStatus.ServiceListenerStatus(serviceListener, false));
                if (list.isEmpty()) {
                    this.g.remove(lowerCase, list);
                }
            }
        }
    }

    public void a(ServiceInfo serviceInfo) throws IOException {
        if (isClosing() || isClosed()) {
            throw new IllegalStateException("This DNS is closed.");
        }
        ServiceInfoImpl serviceInfoImpl = (ServiceInfoImpl) serviceInfo;
        if (serviceInfoImpl.getDns() != null) {
            if (serviceInfoImpl.getDns() != this) {
                throw new IllegalStateException("A service information can only be registered with a single instamce of JmDNS.");
            } else if (this.j.get(serviceInfoImpl.e()) != null) {
                throw new IllegalStateException("A service information can only be registered once.");
            }
        }
        serviceInfoImpl.a(this);
        b(serviceInfoImpl.c());
        serviceInfoImpl.recoverState();
        serviceInfoImpl.g(this.m.a());
        serviceInfoImpl.a(this.m.c());
        serviceInfoImpl.a(this.m.d());
        waitForAnnounced(6000);
        b(serviceInfoImpl);
        while (this.j.putIfAbsent(serviceInfoImpl.e(), serviceInfoImpl) != null) {
            b(serviceInfoImpl);
        }
        k();
        serviceInfoImpl.waitForAnnounced(6000);
        if (c.isLoggable(Level.FINE)) {
            Logger logger = c;
            logger.fine("registerService() JmDNS registered service as " + serviceInfoImpl);
        }
    }

    public void b(ServiceInfo serviceInfo) {
        ServiceInfoImpl serviceInfoImpl = (ServiceInfoImpl) this.j.get(serviceInfo.e());
        if (serviceInfoImpl != null) {
            serviceInfoImpl.cancelState();
            n();
            serviceInfoImpl.waitForCanceled(5000);
            this.j.remove(serviceInfoImpl.e(), serviceInfoImpl);
            if (c.isLoggable(Level.FINE)) {
                Logger logger = c;
                logger.fine("unregisterService() JmDNS unregistered service as " + serviceInfoImpl);
                return;
            }
            return;
        }
        Logger logger2 = c;
        logger2.warning("Removing unregistered service info: " + serviceInfo.e());
    }

    public void e() {
        if (c.isLoggable(Level.FINER)) {
            c.finer("unregisterAllServices()");
        }
        for (String str : this.j.keySet()) {
            ServiceInfoImpl serviceInfoImpl = (ServiceInfoImpl) this.j.get(str);
            if (serviceInfoImpl != null) {
                if (c.isLoggable(Level.FINER)) {
                    Logger logger = c;
                    logger.finer("Cancelling service info: " + serviceInfoImpl);
                }
                serviceInfoImpl.cancelState();
            }
        }
        n();
        for (String str2 : this.j.keySet()) {
            ServiceInfoImpl serviceInfoImpl2 = (ServiceInfoImpl) this.j.get(str2);
            if (serviceInfoImpl2 != null) {
                if (c.isLoggable(Level.FINER)) {
                    Logger logger2 = c;
                    logger2.finer("Wait for service info cancel: " + serviceInfoImpl2);
                }
                serviceInfoImpl2.waitForCanceled(5000);
                this.j.remove(str2, serviceInfoImpl2);
            }
        }
    }

    public boolean b(String str) {
        String str2;
        String str3;
        boolean z;
        ServiceTypeEntry serviceTypeEntry;
        String str4;
        Map<ServiceInfo.Fields, String> e2 = ServiceInfoImpl.e(str);
        String str5 = e2.get(ServiceInfo.Fields.Domain);
        String str6 = e2.get(ServiceInfo.Fields.Protocol);
        String str7 = e2.get(ServiceInfo.Fields.Application);
        String str8 = e2.get(ServiceInfo.Fields.Subtype);
        StringBuilder sb = new StringBuilder();
        if (str7.length() > 0) {
            str2 = JSMethod.NOT_SET + str7 + ".";
        } else {
            str2 = "";
        }
        sb.append(str2);
        if (str6.length() > 0) {
            str3 = JSMethod.NOT_SET + str6 + ".";
        } else {
            str3 = "";
        }
        sb.append(str3);
        sb.append(str5);
        sb.append(".");
        String sb2 = sb.toString();
        String lowerCase = sb2.toLowerCase();
        if (c.isLoggable(Level.FINE)) {
            Logger logger = c;
            StringBuilder sb3 = new StringBuilder();
            sb3.append(b());
            sb3.append(".registering service type: ");
            sb3.append(str);
            sb3.append(" as: ");
            sb3.append(sb2);
            if (str8.length() > 0) {
                str4 = " subtype: " + str8;
            } else {
                str4 = "";
            }
            sb3.append(str4);
            logger.fine(sb3.toString());
        }
        if (this.k.containsKey(lowerCase) || str7.toLowerCase().equals("dns-sd") || str5.toLowerCase().endsWith("in-addr.arpa") || str5.toLowerCase().endsWith("ip6.arpa")) {
            z = false;
        } else {
            z = this.k.putIfAbsent(lowerCase, new ServiceTypeEntry(sb2)) == null;
            if (z) {
                ListenerStatus.ServiceTypeListenerStatus[] serviceTypeListenerStatusArr = (ListenerStatus.ServiceTypeListenerStatus[]) this.h.toArray(new ListenerStatus.ServiceTypeListenerStatus[this.h.size()]);
                final ServiceEventImpl serviceEventImpl = new ServiceEventImpl(this, sb2, "", (ServiceInfo) null);
                for (final ListenerStatus.ServiceTypeListenerStatus serviceTypeListenerStatus : serviceTypeListenerStatusArr) {
                    this.q.submit(new Runnable() {
                        public void run() {
                            serviceTypeListenerStatus.a(serviceEventImpl);
                        }
                    });
                }
            }
        }
        if (str8.length() > 0 && (serviceTypeEntry = (ServiceTypeEntry) this.k.get(lowerCase)) != null && !serviceTypeEntry.a(str8)) {
            synchronized (serviceTypeEntry) {
                if (!serviceTypeEntry.a(str8)) {
                    serviceTypeEntry.b(str8);
                    ListenerStatus.ServiceTypeListenerStatus[] serviceTypeListenerStatusArr2 = (ListenerStatus.ServiceTypeListenerStatus[]) this.h.toArray(new ListenerStatus.ServiceTypeListenerStatus[this.h.size()]);
                    final ServiceEventImpl serviceEventImpl2 = new ServiceEventImpl(this, JSMethod.NOT_SET + str8 + "._sub." + sb2, "", (ServiceInfo) null);
                    for (final ListenerStatus.ServiceTypeListenerStatus serviceTypeListenerStatus2 : serviceTypeListenerStatusArr2) {
                        this.q.submit(new Runnable() {
                            public void run() {
                                serviceTypeListenerStatus2.b(serviceEventImpl2);
                            }
                        });
                    }
                    z = true;
                }
            }
        }
        return z;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:13:0x005d, code lost:
        if (c.isLoggable(java.util.logging.Level.FINER) == false) goto L_0x00a5;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:14:0x005f, code lost:
        r3 = c;
        r3.finer("makeServiceNameUnique() JmDNS.makeServiceNameUnique srv collision:" + r5 + " s.server=" + r7.s() + " " + r10.m.a() + " equals:" + r7.s().equals(r10.m.a()));
     */
    /* JADX WARNING: Code restructure failed: missing block: B:15:0x00a5, code lost:
        r11.f(e(r11.d()));
        r3 = true;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private boolean b(javax.jmdns.impl.ServiceInfoImpl r11) {
        /*
            r10 = this;
            java.lang.String r0 = r11.e()
            long r1 = java.lang.System.currentTimeMillis()
        L_0x0008:
            r3 = 0
            javax.jmdns.impl.DNSCache r4 = r10.q()
            java.lang.String r5 = r11.e()
            java.util.Collection r4 = r4.b((java.lang.String) r5)
            java.util.Iterator r4 = r4.iterator()
        L_0x0019:
            boolean r5 = r4.hasNext()
            r6 = 1
            if (r5 == 0) goto L_0x00b1
            java.lang.Object r5 = r4.next()
            javax.jmdns.impl.DNSEntry r5 = (javax.jmdns.impl.DNSEntry) r5
            javax.jmdns.impl.constants.DNSRecordType r7 = javax.jmdns.impl.constants.DNSRecordType.TYPE_SRV
            javax.jmdns.impl.constants.DNSRecordType r8 = r5.e()
            boolean r7 = r7.equals(r8)
            if (r7 == 0) goto L_0x0019
            boolean r7 = r5.b((long) r1)
            if (r7 != 0) goto L_0x0019
            r7 = r5
            javax.jmdns.impl.DNSRecord$Service r7 = (javax.jmdns.impl.DNSRecord.Service) r7
            int r8 = r7.v()
            int r9 = r11.q()
            if (r8 != r9) goto L_0x0055
            java.lang.String r8 = r7.s()
            javax.jmdns.impl.HostInfo r9 = r10.m
            java.lang.String r9 = r9.a()
            boolean r8 = r8.equals(r9)
            if (r8 != 0) goto L_0x0019
        L_0x0055:
            java.util.logging.Logger r3 = c
            java.util.logging.Level r4 = java.util.logging.Level.FINER
            boolean r3 = r3.isLoggable(r4)
            if (r3 == 0) goto L_0x00a5
            java.util.logging.Logger r3 = c
            java.lang.StringBuilder r4 = new java.lang.StringBuilder
            r4.<init>()
            java.lang.String r8 = "makeServiceNameUnique() JmDNS.makeServiceNameUnique srv collision:"
            r4.append(r8)
            r4.append(r5)
            java.lang.String r5 = " s.server="
            r4.append(r5)
            java.lang.String r5 = r7.s()
            r4.append(r5)
            java.lang.String r5 = " "
            r4.append(r5)
            javax.jmdns.impl.HostInfo r5 = r10.m
            java.lang.String r5 = r5.a()
            r4.append(r5)
            java.lang.String r5 = " equals:"
            r4.append(r5)
            java.lang.String r5 = r7.s()
            javax.jmdns.impl.HostInfo r7 = r10.m
            java.lang.String r7 = r7.a()
            boolean r5 = r5.equals(r7)
            r4.append(r5)
            java.lang.String r4 = r4.toString()
            r3.finer(r4)
        L_0x00a5:
            java.lang.String r3 = r11.d()
            java.lang.String r3 = r10.e(r3)
            r11.f(r3)
            r3 = 1
        L_0x00b1:
            java.util.concurrent.ConcurrentMap<java.lang.String, javax.jmdns.ServiceInfo> r4 = r10.j
            java.lang.String r5 = r11.e()
            java.lang.Object r4 = r4.get(r5)
            javax.jmdns.ServiceInfo r4 = (javax.jmdns.ServiceInfo) r4
            if (r4 == 0) goto L_0x00cd
            if (r4 == r11) goto L_0x00cd
            java.lang.String r3 = r11.d()
            java.lang.String r3 = r10.e(r3)
            r11.f(r3)
            r3 = 1
        L_0x00cd:
            if (r3 != 0) goto L_0x0008
            java.lang.String r11 = r11.e()
            boolean r11 = r0.equals(r11)
            r11 = r11 ^ r6
            return r11
        */
        throw new UnsupportedOperationException("Method not decompiled: javax.jmdns.impl.JmDNSImpl.b(javax.jmdns.impl.ServiceInfoImpl):boolean");
    }

    /* access modifiers changed from: package-private */
    public String e(String str) {
        try {
            int lastIndexOf = str.lastIndexOf(40);
            int lastIndexOf2 = str.lastIndexOf(41);
            if (lastIndexOf < 0 || lastIndexOf >= lastIndexOf2) {
                return str + " (2)";
            }
            return str.substring(0, lastIndexOf) + Operators.BRACKET_START_STR + (Integer.parseInt(str.substring(lastIndexOf + 1, lastIndexOf2)) + 1) + Operators.BRACKET_END_STR;
        } catch (NumberFormatException unused) {
            return str + " (2)";
        }
    }

    public void a(DNSListener dNSListener, DNSQuestion dNSQuestion) {
        long currentTimeMillis = System.currentTimeMillis();
        this.f.add(dNSListener);
        if (dNSQuestion != null) {
            for (DNSEntry dNSEntry : q().b(dNSQuestion.b().toLowerCase())) {
                if (dNSQuestion.f(dNSEntry) && !dNSEntry.b(currentTimeMillis)) {
                    dNSListener.a(q(), currentTimeMillis, dNSEntry);
                }
            }
        }
    }

    public void a(DNSListener dNSListener) {
        this.f.remove(dNSListener);
    }

    public void a(DNSRecord dNSRecord) {
        ServiceInfo p2 = dNSRecord.p();
        if (this.u.containsKey(p2.b().toLowerCase())) {
            e_(p2.b());
        }
    }

    public void a(long j2, DNSRecord dNSRecord, Operation operation) {
        ArrayList<DNSListener> arrayList;
        List<ListenerStatus.ServiceListenerStatus> list;
        synchronized (this.f) {
            arrayList = new ArrayList<>(this.f);
        }
        for (DNSListener a2 : arrayList) {
            a2.a(q(), j2, dNSRecord);
        }
        if (DNSRecordType.TYPE_PTR.equals(dNSRecord.e())) {
            final ServiceEvent b2 = dNSRecord.b(this);
            b2._source = dNSRecord.b;
            if (b2.getInfo() == null || !b2.getInfo().a()) {
                ServiceInfoImpl b3 = b(b2.getType(), b2.getName(), "", false);
                if (b3.a()) {
                    b2 = new ServiceEventImpl(this, b2.getType(), b2.getName(), b3);
                }
            }
            List list2 = (List) this.g.get(b2.getType().toLowerCase());
            if (list2 != null) {
                synchronized (list2) {
                    list = new ArrayList<>(list2);
                }
            } else {
                list = Collections.emptyList();
            }
            if (c.isLoggable(Level.FINEST)) {
                Logger logger = c;
                logger.finest(b() + ".updating record for event: " + b2 + " list " + list + " operation: " + operation);
            }
            if (!list.isEmpty()) {
                switch (operation) {
                    case Add:
                        for (final ListenerStatus.ServiceListenerStatus serviceListenerStatus : list) {
                            if (serviceListenerStatus.b()) {
                                serviceListenerStatus.a(b2);
                            } else {
                                this.q.submit(new Runnable() {
                                    public void run() {
                                        serviceListenerStatus.a(b2);
                                    }
                                });
                            }
                        }
                        return;
                    case Remove:
                        for (final ListenerStatus.ServiceListenerStatus serviceListenerStatus2 : list) {
                            if (serviceListenerStatus2.b()) {
                                serviceListenerStatus2.b(b2);
                            } else {
                                this.q.submit(new Runnable() {
                                    public void run() {
                                        serviceListenerStatus2.b(b2);
                                    }
                                });
                            }
                        }
                        return;
                    default:
                        return;
                }
            }
        }
    }

    /* access modifiers changed from: package-private */
    public void a(DNSRecord dNSRecord, long j2) {
        Operation operation = Operation.Noop;
        boolean b2 = dNSRecord.b(j2);
        if (c.isLoggable(Level.FINE)) {
            Logger logger = c;
            logger.fine(b() + " handle response: " + dNSRecord);
        }
        if (!dNSRecord.i() && !dNSRecord.j()) {
            boolean g2 = dNSRecord.g();
            DNSRecord dNSRecord2 = (DNSRecord) q().a((DNSEntry) dNSRecord);
            if (c.isLoggable(Level.FINE)) {
                Logger logger2 = c;
                logger2.fine(b() + " handle response cached record: " + dNSRecord2);
            }
            if (g2) {
                for (DNSEntry dNSEntry : q().b(dNSRecord.d())) {
                    if (!dNSRecord.e().equals(DNSRecordType.TYPE_PTR) && dNSRecord.e().equals(dNSEntry.e()) && dNSRecord.f().equals(dNSEntry.f()) && dNSEntry != dNSRecord2) {
                        ((DNSRecord) dNSEntry).d(j2);
                    }
                }
            }
            if (dNSRecord2 != null) {
                if (b2) {
                    if (dNSRecord.r() == 0) {
                        operation = Operation.Noop;
                        dNSRecord2.d(j2);
                    } else {
                        operation = Operation.Remove;
                        q().c((DNSEntry) dNSRecord2);
                    }
                } else if (dNSRecord.a(dNSRecord2) && (dNSRecord.b((DNSEntry) dNSRecord2) || dNSRecord.a().length() <= 0)) {
                    dNSRecord2.d(dNSRecord);
                    dNSRecord = dNSRecord2;
                } else if (dNSRecord.o()) {
                    operation = Operation.Update;
                    q().a((DNSEntry) dNSRecord, (DNSEntry) dNSRecord2);
                } else {
                    operation = Operation.Add;
                    q().b((DNSEntry) dNSRecord);
                }
            } else if (!b2) {
                operation = Operation.Add;
                q().b((DNSEntry) dNSRecord);
            }
        }
        if (dNSRecord.e() == DNSRecordType.TYPE_PTR) {
            if (dNSRecord.i()) {
                if (!b2) {
                    b(((DNSRecord.Pointer) dNSRecord).s());
                    return;
                }
                return;
            } else if ((b(dNSRecord.b()) || false) && operation == Operation.Noop) {
                operation = Operation.RegisterServiceType;
            }
        }
        if (operation != Operation.Noop) {
            a(j2, dNSRecord, operation);
        }
    }

    /* access modifiers changed from: package-private */
    public void a(DNSIncoming dNSIncoming) throws IOException {
        long currentTimeMillis = System.currentTimeMillis();
        boolean z = false;
        boolean z2 = false;
        for (DNSRecord dNSRecord : dNSIncoming.i()) {
            a(dNSRecord, currentTimeMillis);
            if (DNSRecordType.TYPE_A.equals(dNSRecord.e()) || DNSRecordType.TYPE_AAAA.equals(dNSRecord.e())) {
                z |= dNSRecord.a(this);
            } else {
                z2 |= dNSRecord.a(this);
            }
        }
        if (z || z2) {
            k();
        }
    }

    /* JADX INFO: finally extract failed */
    /* access modifiers changed from: package-private */
    public void a(DNSIncoming dNSIncoming, InetAddress inetAddress, int i2) throws IOException {
        if (c.isLoggable(Level.FINE)) {
            Logger logger = c;
            logger.fine(b() + ".handle query: " + dNSIncoming);
        }
        boolean z = false;
        long currentTimeMillis = System.currentTimeMillis() + 120;
        for (DNSRecord a2 : dNSIncoming.i()) {
            z |= a2.a(this, currentTimeMillis);
        }
        z();
        try {
            if (this.t != null) {
                this.t.a(dNSIncoming);
            } else {
                DNSIncoming a3 = dNSIncoming.clone();
                if (dNSIncoming.p()) {
                    this.t = a3;
                }
                a(a3, i2);
            }
            A();
            long currentTimeMillis2 = System.currentTimeMillis();
            for (DNSRecord a4 : dNSIncoming.j()) {
                a(a4, currentTimeMillis2);
            }
            if (z) {
                k();
            }
        } catch (Throwable th) {
            A();
            throw th;
        }
    }

    public void b(DNSIncoming dNSIncoming) {
        z();
        try {
            if (this.t == dNSIncoming) {
                this.t = null;
            }
        } finally {
            A();
        }
    }

    public DNSOutgoing a(DNSIncoming dNSIncoming, InetAddress inetAddress, int i2, DNSOutgoing dNSOutgoing, DNSRecord dNSRecord) throws IOException {
        if (dNSOutgoing == null) {
            dNSOutgoing = new DNSOutgoing(33792, false, dNSIncoming.c());
        }
        try {
            dNSOutgoing.a(dNSIncoming, dNSRecord);
            return dNSOutgoing;
        } catch (IOException unused) {
            dNSOutgoing.b(dNSOutgoing.e() | 512);
            dNSOutgoing.a(dNSIncoming.d());
            a(dNSOutgoing);
            DNSOutgoing dNSOutgoing2 = new DNSOutgoing(33792, false, dNSIncoming.c());
            dNSOutgoing2.a(dNSIncoming, dNSRecord);
            return dNSOutgoing2;
        }
    }

    public void a(DNSOutgoing dNSOutgoing) throws IOException {
        if (!dNSOutgoing.s()) {
            byte[] b2 = dNSOutgoing.b();
            DatagramPacket datagramPacket = new DatagramPacket(b2, b2.length, this.d, DNSConstants.c);
            if (c.isLoggable(Level.FINEST)) {
                try {
                    DNSIncoming dNSIncoming = new DNSIncoming(datagramPacket);
                    if (c.isLoggable(Level.FINEST)) {
                        Logger logger = c;
                        logger.finest("send(" + b() + ") JmDNS out:" + dNSIncoming.a(true));
                    }
                } catch (IOException e2) {
                    Logger logger2 = c;
                    String cls = getClass().toString();
                    logger2.throwing(cls, "send(" + b() + ") - JmDNS can not parse what it sends!!!", e2);
                }
            }
            MulticastSocket multicastSocket = this.e;
            if (multicastSocket != null && !multicastSocket.isClosed()) {
                multicastSocket.send(datagramPacket);
            }
        }
    }

    public void ad_() {
        DNSTaskStarter.Factory.b().b(getDns()).ad_();
    }

    public void h() {
        DNSTaskStarter.Factory.b().b(getDns()).h();
    }

    public void i() {
        DNSTaskStarter.Factory.b().b(getDns()).i();
    }

    public void j() {
        DNSTaskStarter.Factory.b().b(getDns()).j();
    }

    public void k() {
        DNSTaskStarter.Factory.b().b(getDns()).k();
    }

    public void l() {
        DNSTaskStarter.Factory.b().b(getDns()).l();
    }

    public void m() {
        DNSTaskStarter.Factory.b().b(getDns()).m();
    }

    public void n() {
        DNSTaskStarter.Factory.b().b(getDns()).n();
    }

    public void o() {
        DNSTaskStarter.Factory.b().b(getDns()).o();
    }

    public void a(ServiceInfoImpl serviceInfoImpl) {
        DNSTaskStarter.Factory.b().b(getDns()).a(serviceInfoImpl);
    }

    public void p() {
        DNSTaskStarter.Factory.b().b(getDns()).p();
    }

    public void e_(String str) {
        DNSTaskStarter.Factory.b().b(getDns()).e_(str);
    }

    public void a(DNSIncoming dNSIncoming, int i2) {
        DNSTaskStarter.Factory.b().b(getDns()).a(dNSIncoming, i2);
    }

    protected class Shutdown implements Runnable {
        protected Shutdown() {
        }

        public void run() {
            try {
                JmDNSImpl.this.b = null;
                JmDNSImpl.this.close();
            } catch (Throwable th) {
                PrintStream printStream = System.err;
                printStream.println("Error while shuting down. " + th);
            }
        }
    }

    public void s() {
        Logger logger = c;
        logger.finer(b() + "recover()");
        if (!isClosing() && !isClosed() && !isCanceling() && !isCanceled()) {
            synchronized (this.w) {
                if (cancelState()) {
                    Logger logger2 = c;
                    logger2.finer(b() + "recover() thread " + Thread.currentThread().getName());
                    StringBuilder sb = new StringBuilder();
                    sb.append(b());
                    sb.append(".recover()");
                    new Thread(sb.toString()) {
                        public void run() {
                            JmDNSImpl.this.t();
                        }
                    }.start();
                }
            }
        }
    }

    /* access modifiers changed from: package-private */
    public void t() {
        if (c.isLoggable(Level.FINER)) {
            Logger logger = c;
            logger.finer(b() + "recover() Cleanning up");
        }
        c.warning("RECOVERING");
        ad_();
        ArrayList<ServiceInfo> arrayList = new ArrayList<>(v().values());
        e();
        G();
        waitForCanceled(5000);
        h();
        F();
        q().clear();
        if (c.isLoggable(Level.FINER)) {
            Logger logger2 = c;
            logger2.finer(b() + "recover() All is clean");
        }
        if (isCanceled()) {
            for (ServiceInfo serviceInfo : arrayList) {
                ((ServiceInfoImpl) serviceInfo).recoverState();
            }
            recoverState();
            try {
                b(r());
                a((Collection<? extends ServiceInfo>) arrayList);
            } catch (Exception e2) {
                Logger logger3 = c;
                Level level = Level.WARNING;
                logger3.log(level, b() + "recover() Start services exception ", e2);
            }
            Logger logger4 = c;
            Level level2 = Level.WARNING;
            logger4.log(level2, b() + "recover() We are back!");
            return;
        }
        Logger logger5 = c;
        Level level3 = Level.WARNING;
        logger5.log(level3, b() + "recover() Could not recover we are Down!");
        if (g() != null) {
            g().a(getDns(), arrayList);
        }
    }

    public void u() {
        long currentTimeMillis = System.currentTimeMillis();
        for (DNSEntry next : q().a()) {
            try {
                DNSRecord dNSRecord = (DNSRecord) next;
                if (dNSRecord.b(currentTimeMillis)) {
                    a(currentTimeMillis, dNSRecord, Operation.Remove);
                    q().c((DNSEntry) dNSRecord);
                } else if (dNSRecord.a(currentTimeMillis)) {
                    a(dNSRecord);
                }
            } catch (Exception e2) {
                Logger logger = c;
                Level level = Level.SEVERE;
                logger.log(level, b() + ".Error while reaping records: " + next, e2);
                c.severe(toString());
            }
        }
    }

    public void close() {
        if (!isClosing()) {
            if (c.isLoggable(Level.FINER)) {
                Logger logger = c;
                logger.finer("Cancelling JmDNS: " + this);
            }
            if (closeState()) {
                c.finer("Canceling the timer");
                i();
                e();
                G();
                if (c.isLoggable(Level.FINER)) {
                    Logger logger2 = c;
                    logger2.finer("Wait for JmDNS cancel: " + this);
                }
                waitForCanceled(5000);
                c.finer("Canceling the state timer");
                j();
                this.q.shutdown();
                F();
                if (this.b != null) {
                    Runtime.getRuntime().removeShutdownHook(this.b);
                }
                if (c.isLoggable(Level.FINER)) {
                    c.finer("JmDNS closed.");
                }
            }
            advanceState((DNSTask) null);
        }
    }

    @Deprecated
    public void f() {
        System.err.println(toString());
    }

    public String toString() {
        StringBuilder sb = new StringBuilder(2048);
        sb.append("\t---- Local Host -----");
        sb.append(HmsPushConst.NEW_LINE);
        sb.append(this.m);
        sb.append("\n\t---- Services -----");
        for (String str : this.j.keySet()) {
            sb.append("\n\t\tService: ");
            sb.append(str);
            sb.append(": ");
            sb.append(this.j.get(str));
        }
        sb.append("\n");
        sb.append("\t---- Types ----");
        for (String str2 : this.k.keySet()) {
            ServiceTypeEntry serviceTypeEntry = (ServiceTypeEntry) this.k.get(str2);
            sb.append("\n\t\tType: ");
            sb.append(serviceTypeEntry.a());
            sb.append(": ");
            sb.append(serviceTypeEntry.isEmpty() ? "no subtypes" : serviceTypeEntry);
        }
        sb.append("\n");
        sb.append(this.i.toString());
        sb.append("\n");
        sb.append("\t---- Service Collectors ----");
        for (String str3 : this.u.keySet()) {
            sb.append("\n\t\tService Collector: ");
            sb.append(str3);
            sb.append(": ");
            sb.append(this.u.get(str3));
        }
        sb.append("\n");
        sb.append("\t---- Service Listeners ----");
        for (String str4 : this.g.keySet()) {
            sb.append("\n\t\tService Listener: ");
            sb.append(str4);
            sb.append(": ");
            sb.append(this.g.get(str4));
        }
        return sb.toString();
    }

    public ServiceInfo[] c(String str) {
        return a(str, 6000);
    }

    public ServiceInfo[] a(String str, long j2) {
        ServiceCollector serviceCollector;
        u();
        String lowerCase = str.toLowerCase();
        if (isCanceling() || isCanceled()) {
            return new ServiceInfo[0];
        }
        ServiceCollector serviceCollector2 = (ServiceCollector) this.u.get(lowerCase);
        if (serviceCollector2 == null) {
            boolean z = this.u.putIfAbsent(lowerCase, new ServiceCollector(str)) == null;
            serviceCollector = (ServiceCollector) this.u.get(lowerCase);
            if (z) {
                a(str, (ServiceListener) serviceCollector, true);
            }
        } else {
            serviceCollector = serviceCollector2;
        }
        if (c.isLoggable(Level.FINER)) {
            Logger logger = c;
            logger.finer(b() + ".collector: " + serviceCollector);
        }
        if (serviceCollector != null) {
            return serviceCollector.a(j2);
        }
        return new ServiceInfo[0];
    }

    public Map<String, ServiceInfo[]> d(String str) {
        return b(str, 6000);
    }

    public Map<String, ServiceInfo[]> b(String str, long j2) {
        HashMap hashMap = new HashMap(5);
        for (ServiceInfo serviceInfo : a(str, j2)) {
            String lowerCase = serviceInfo.D().toLowerCase();
            if (!hashMap.containsKey(lowerCase)) {
                hashMap.put(lowerCase, new ArrayList(10));
            }
            ((List) hashMap.get(lowerCase)).add(serviceInfo);
        }
        HashMap hashMap2 = new HashMap(hashMap.size());
        for (String str2 : hashMap.keySet()) {
            List list = (List) hashMap.get(str2);
            hashMap2.put(str2, list.toArray(new ServiceInfo[list.size()]));
        }
        return hashMap2;
    }

    private void G() {
        if (c.isLoggable(Level.FINER)) {
            c.finer("disposeServiceCollectors()");
        }
        for (String str : this.u.keySet()) {
            ServiceCollector serviceCollector = (ServiceCollector) this.u.get(str);
            if (serviceCollector != null) {
                b(str, (ServiceListener) serviceCollector);
                this.u.remove(str, serviceCollector);
            }
        }
    }

    private static class ServiceCollector implements ServiceListener {

        /* renamed from: a  reason: collision with root package name */
        private final ConcurrentMap<String, ServiceInfo> f2650a = new ConcurrentHashMap();
        private final ConcurrentMap<String, ServiceEvent> b = new ConcurrentHashMap();
        private final String c;
        private volatile boolean d;

        public ServiceCollector(String str) {
            this.c = str;
            this.d = true;
        }

        public void a(ServiceEvent serviceEvent) {
            synchronized (this) {
                ServiceInfo info = serviceEvent.getInfo();
                if (info == null || !info.a()) {
                    ServiceInfoImpl a2 = ((JmDNSImpl) serviceEvent.getDNS()).a(serviceEvent.getType(), serviceEvent.getName(), info != null ? info.D() : "", true);
                    if (a2 != null) {
                        this.f2650a.put(serviceEvent.getName(), a2);
                    } else {
                        this.b.put(serviceEvent.getName(), serviceEvent);
                    }
                } else {
                    this.f2650a.put(serviceEvent.getName(), info);
                }
            }
        }

        public void b(ServiceEvent serviceEvent) {
            synchronized (this) {
                this.f2650a.remove(serviceEvent.getName());
                this.b.remove(serviceEvent.getName());
            }
        }

        public void c(ServiceEvent serviceEvent) {
            synchronized (this) {
                this.f2650a.put(serviceEvent.getName(), serviceEvent.getInfo());
                this.b.remove(serviceEvent.getName());
            }
        }

        public ServiceInfo[] a(long j) {
            if (this.f2650a.isEmpty() || !this.b.isEmpty() || this.d) {
                long j2 = j / 200;
                if (j2 < 1) {
                    j2 = 1;
                }
                for (int i = 0; ((long) i) < j2; i++) {
                    try {
                        Thread.sleep(200);
                    } catch (InterruptedException unused) {
                    }
                    if (this.b.isEmpty() && !this.f2650a.isEmpty() && !this.d) {
                        break;
                    }
                }
            }
            this.d = false;
            return (ServiceInfo[]) this.f2650a.values().toArray(new ServiceInfo[this.f2650a.size()]);
        }

        public String toString() {
            StringBuilder sb = new StringBuilder();
            sb.append("\n\tType: ");
            sb.append(this.c);
            if (this.f2650a.isEmpty()) {
                sb.append("\n\tNo services collected.");
            } else {
                sb.append("\n\tServices");
                for (String str : this.f2650a.keySet()) {
                    sb.append("\n\t\tService: ");
                    sb.append(str);
                    sb.append(": ");
                    sb.append(this.f2650a.get(str));
                }
            }
            if (this.b.isEmpty()) {
                sb.append("\n\tNo event queued.");
            } else {
                sb.append("\n\tEvents");
                for (String str2 : this.b.keySet()) {
                    sb.append("\n\t\tEvent: ");
                    sb.append(str2);
                    sb.append(": ");
                    sb.append(this.b.get(str2));
                }
            }
            return sb.toString();
        }
    }

    static String c(String str, String str2) {
        String lowerCase = str.toLowerCase();
        String lowerCase2 = str2.toLowerCase();
        return (!lowerCase2.endsWith(lowerCase) || lowerCase2.equals(lowerCase)) ? str2 : str2.substring(0, (str2.length() - str.length()) - 1);
    }

    public Map<String, ServiceInfo> v() {
        return this.j;
    }

    public void a(long j2) {
        this.p = j2;
    }

    public long w() {
        return this.p;
    }

    public void a(int i2) {
        this.o = i2;
    }

    public int x() {
        return this.o;
    }

    public static Random y() {
        return r;
    }

    public void z() {
        this.s.lock();
    }

    public void A() {
        this.s.unlock();
    }

    public void c(DNSIncoming dNSIncoming) {
        this.t = dNSIncoming;
    }

    public DNSIncoming B() {
        return this.t;
    }

    /* access modifiers changed from: package-private */
    public void a(HostInfo hostInfo) {
        this.m = hostInfo;
    }

    public Map<String, ServiceTypeEntry> C() {
        return this.k;
    }

    public MulticastSocket D() {
        return this.e;
    }

    public InetAddress E() {
        return this.d;
    }

    public JmDNS.Delegate g() {
        return this.l;
    }

    public JmDNS.Delegate a(JmDNS.Delegate delegate) {
        JmDNS.Delegate delegate2 = this.l;
        this.l = delegate;
        return delegate2;
    }
}
