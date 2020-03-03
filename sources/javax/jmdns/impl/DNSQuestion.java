package javax.jmdns.impl;

import java.net.InetAddress;
import java.util.Iterator;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.jmdns.ServiceInfo;
import javax.jmdns.impl.DNSRecord;
import javax.jmdns.impl.constants.DNSRecordClass;
import javax.jmdns.impl.constants.DNSRecordType;

public class DNSQuestion extends DNSEntry {
    private static Logger b = Logger.getLogger(DNSQuestion.class.getName());

    public void a(StringBuilder sb) {
    }

    public void a(JmDNSImpl jmDNSImpl, Set<DNSRecord> set) {
    }

    public boolean a(long j) {
        return false;
    }

    public boolean a(JmDNSImpl jmDNSImpl) {
        return false;
    }

    public boolean b(long j) {
        return false;
    }

    private static class DNS4Address extends DNSQuestion {
        DNS4Address(String str, DNSRecordType dNSRecordType, DNSRecordClass dNSRecordClass, boolean z) {
            super(str, dNSRecordType, dNSRecordClass, z);
        }

        public void a(JmDNSImpl jmDNSImpl, Set<DNSRecord> set) {
            DNSRecord.Address a2 = jmDNSImpl.r().a(e(), true, 3600);
            if (a2 != null) {
                set.add(a2);
            }
        }

        public boolean a(JmDNSImpl jmDNSImpl) {
            String lowerCase = b().toLowerCase();
            return jmDNSImpl.r().a().equals(lowerCase) || jmDNSImpl.v().keySet().contains(lowerCase);
        }
    }

    private static class DNS6Address extends DNSQuestion {
        DNS6Address(String str, DNSRecordType dNSRecordType, DNSRecordClass dNSRecordClass, boolean z) {
            super(str, dNSRecordType, dNSRecordClass, z);
        }

        public void a(JmDNSImpl jmDNSImpl, Set<DNSRecord> set) {
            DNSRecord.Address a2 = jmDNSImpl.r().a(e(), true, 3600);
            if (a2 != null) {
                set.add(a2);
            }
        }

        public boolean a(JmDNSImpl jmDNSImpl) {
            String lowerCase = b().toLowerCase();
            return jmDNSImpl.r().a().equals(lowerCase) || jmDNSImpl.v().keySet().contains(lowerCase);
        }
    }

    private static class HostInformation extends DNSQuestion {
        HostInformation(String str, DNSRecordType dNSRecordType, DNSRecordClass dNSRecordClass, boolean z) {
            super(str, dNSRecordType, dNSRecordClass, z);
        }
    }

    private static class Pointer extends DNSQuestion {
        Pointer(String str, DNSRecordType dNSRecordType, DNSRecordClass dNSRecordClass, boolean z) {
            super(str, dNSRecordType, dNSRecordClass, z);
        }

        public void a(JmDNSImpl jmDNSImpl, Set<DNSRecord> set) {
            Iterator<ServiceInfo> it = jmDNSImpl.v().values().iterator();
            while (it.hasNext()) {
                a(jmDNSImpl, set, (ServiceInfoImpl) it.next());
            }
            if (i()) {
                for (String str : jmDNSImpl.C().keySet()) {
                    set.add(new DNSRecord.Pointer("_services._dns-sd._udp.local.", DNSRecordClass.CLASS_IN, false, 3600, jmDNSImpl.C().get(str).a()));
                }
            } else if (k()) {
                String str2 = h().get(ServiceInfo.Fields.Instance);
                if (str2 != null && str2.length() > 0) {
                    InetAddress b = jmDNSImpl.r().b();
                    if (str2.equalsIgnoreCase(b != null ? b.getHostAddress() : "")) {
                        if (l()) {
                            set.add(jmDNSImpl.r().b(DNSRecordType.TYPE_A, false, 3600));
                        }
                        if (m()) {
                            set.add(jmDNSImpl.r().b(DNSRecordType.TYPE_AAAA, false, 3600));
                        }
                    }
                }
            } else {
                j();
            }
        }
    }

    private static class Service extends DNSQuestion {
        Service(String str, DNSRecordType dNSRecordType, DNSRecordClass dNSRecordClass, boolean z) {
            super(str, dNSRecordType, dNSRecordClass, z);
        }

        public void a(JmDNSImpl jmDNSImpl, Set<DNSRecord> set) {
            String lowerCase = b().toLowerCase();
            if (jmDNSImpl.r().a().equalsIgnoreCase(lowerCase)) {
                set.addAll(jmDNSImpl.r().a(g(), 3600));
            } else if (jmDNSImpl.C().containsKey(lowerCase)) {
                new Pointer(b(), DNSRecordType.TYPE_PTR, f(), g()).a(jmDNSImpl, set);
            } else {
                a(jmDNSImpl, set, (ServiceInfoImpl) jmDNSImpl.v().get(lowerCase));
            }
        }

        public boolean a(JmDNSImpl jmDNSImpl) {
            String lowerCase = b().toLowerCase();
            return jmDNSImpl.r().a().equals(lowerCase) || jmDNSImpl.v().keySet().contains(lowerCase);
        }
    }

    private static class Text extends DNSQuestion {
        Text(String str, DNSRecordType dNSRecordType, DNSRecordClass dNSRecordClass, boolean z) {
            super(str, dNSRecordType, dNSRecordClass, z);
        }

        public void a(JmDNSImpl jmDNSImpl, Set<DNSRecord> set) {
            a(jmDNSImpl, set, (ServiceInfoImpl) jmDNSImpl.v().get(b().toLowerCase()));
        }

        public boolean a(JmDNSImpl jmDNSImpl) {
            String lowerCase = b().toLowerCase();
            return jmDNSImpl.r().a().equals(lowerCase) || jmDNSImpl.v().keySet().contains(lowerCase);
        }
    }

    private static class AllRecords extends DNSQuestion {
        public boolean d(DNSEntry dNSEntry) {
            return dNSEntry != null;
        }

        AllRecords(String str, DNSRecordType dNSRecordType, DNSRecordClass dNSRecordClass, boolean z) {
            super(str, dNSRecordType, dNSRecordClass, z);
        }

        public void a(JmDNSImpl jmDNSImpl, Set<DNSRecord> set) {
            String lowerCase = b().toLowerCase();
            if (jmDNSImpl.r().a().equalsIgnoreCase(lowerCase)) {
                set.addAll(jmDNSImpl.r().a(g(), 3600));
            } else if (jmDNSImpl.C().containsKey(lowerCase)) {
                new Pointer(b(), DNSRecordType.TYPE_PTR, f(), g()).a(jmDNSImpl, set);
            } else {
                a(jmDNSImpl, set, (ServiceInfoImpl) jmDNSImpl.v().get(lowerCase));
            }
        }

        public boolean a(JmDNSImpl jmDNSImpl) {
            String lowerCase = b().toLowerCase();
            return jmDNSImpl.r().a().equals(lowerCase) || jmDNSImpl.v().keySet().contains(lowerCase);
        }
    }

    DNSQuestion(String str, DNSRecordType dNSRecordType, DNSRecordClass dNSRecordClass, boolean z) {
        super(str, dNSRecordType, dNSRecordClass, z);
    }

    public static DNSQuestion a(String str, DNSRecordType dNSRecordType, DNSRecordClass dNSRecordClass, boolean z) {
        switch (dNSRecordType) {
            case TYPE_A:
                return new DNS4Address(str, dNSRecordType, dNSRecordClass, z);
            case TYPE_A6:
                return new DNS6Address(str, dNSRecordType, dNSRecordClass, z);
            case TYPE_AAAA:
                return new DNS6Address(str, dNSRecordType, dNSRecordClass, z);
            case TYPE_ANY:
                return new AllRecords(str, dNSRecordType, dNSRecordClass, z);
            case TYPE_HINFO:
                return new HostInformation(str, dNSRecordType, dNSRecordClass, z);
            case TYPE_PTR:
                return new Pointer(str, dNSRecordType, dNSRecordClass, z);
            case TYPE_SRV:
                return new Service(str, dNSRecordType, dNSRecordClass, z);
            case TYPE_TXT:
                return new Text(str, dNSRecordType, dNSRecordClass, z);
            default:
                return new DNSQuestion(str, dNSRecordType, dNSRecordClass, z);
        }
    }

    /* access modifiers changed from: package-private */
    public boolean f(DNSEntry dNSEntry) {
        return c(dNSEntry) && d(dNSEntry) && b().equals(dNSEntry.b());
    }

    /* access modifiers changed from: protected */
    public void a(JmDNSImpl jmDNSImpl, Set<DNSRecord> set, ServiceInfoImpl serviceInfoImpl) {
        if (serviceInfoImpl != null && serviceInfoImpl.isAnnounced()) {
            if (b().equalsIgnoreCase(serviceInfoImpl.f()) || b().equalsIgnoreCase(serviceInfoImpl.b())) {
                set.addAll(jmDNSImpl.r().a(true, 3600));
                set.addAll(serviceInfoImpl.a(true, 3600, jmDNSImpl.r()));
            }
            if (b.isLoggable(Level.FINER)) {
                Logger logger = b;
                logger.finer(jmDNSImpl.b() + " DNSQuestion(" + b() + ").addAnswersForServiceInfo(): info: " + serviceInfoImpl + "\n" + set);
            }
        }
    }
}
