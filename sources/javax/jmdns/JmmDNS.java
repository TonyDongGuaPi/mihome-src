package javax.jmdns;

import java.io.Closeable;
import java.io.IOException;
import java.net.InetAddress;
import java.util.Map;
import java.util.concurrent.atomic.AtomicReference;
import javax.jmdns.impl.JmmDNSImpl;

public interface JmmDNS extends Closeable {
    void a(String str);

    void a(String str, ServiceListener serviceListener);

    void a(NetworkTopologyListener networkTopologyListener);

    void a(ServiceInfo serviceInfo) throws IOException;

    void a(ServiceTypeListener serviceTypeListener) throws IOException;

    String[] a();

    ServiceInfo[] a(String str, long j);

    ServiceInfo[] a(String str, String str2);

    ServiceInfo[] a(String str, String str2, long j);

    ServiceInfo[] a(String str, String str2, boolean z);

    ServiceInfo[] a(String str, String str2, boolean z, long j);

    Map<String, ServiceInfo[]> b(String str, long j);

    void b(String str, String str2);

    void b(String str, String str2, long j);

    void b(String str, String str2, boolean z);

    void b(String str, String str2, boolean z, long j);

    void b(String str, ServiceListener serviceListener);

    void b(NetworkTopologyListener networkTopologyListener);

    void b(ServiceInfo serviceInfo);

    void b(ServiceTypeListener serviceTypeListener);

    String[] b();

    ServiceInfo[] b(String str);

    Map<String, ServiceInfo[]> c(String str);

    InetAddress[] c() throws IOException;

    void d();

    NetworkTopologyListener[] e();

    public static final class Factory {

        /* renamed from: a  reason: collision with root package name */
        private static volatile JmmDNS f2623a;
        private static final AtomicReference<ClassDelegate> b = new AtomicReference<>();

        public interface ClassDelegate {
            JmmDNS a();
        }

        private Factory() {
        }

        public static void a(ClassDelegate classDelegate) {
            b.set(classDelegate);
        }

        public static ClassDelegate a() {
            return b.get();
        }

        protected static JmmDNS b() {
            ClassDelegate classDelegate = b.get();
            JmmDNS a2 = classDelegate != null ? classDelegate.a() : null;
            return a2 != null ? a2 : new JmmDNSImpl();
        }

        public static JmmDNS c() {
            if (f2623a == null) {
                synchronized (Factory.class) {
                    if (f2623a == null) {
                        f2623a = b();
                    }
                }
            }
            return f2623a;
        }
    }
}
