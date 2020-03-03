package javax.jmdns;

import java.net.InetAddress;
import java.net.NetworkInterface;
import java.util.concurrent.atomic.AtomicReference;
import javax.jmdns.impl.NetworkTopologyDiscoveryImpl;

public interface NetworkTopologyDiscovery {
    void a(InetAddress inetAddress);

    boolean a(NetworkInterface networkInterface, InetAddress inetAddress);

    InetAddress[] a();

    void b(InetAddress inetAddress);

    public static final class Factory {

        /* renamed from: a  reason: collision with root package name */
        private static volatile NetworkTopologyDiscovery f2624a;
        private static final AtomicReference<ClassDelegate> b = new AtomicReference<>();

        public interface ClassDelegate {
            NetworkTopologyDiscovery a();
        }

        private Factory() {
        }

        public static void a(ClassDelegate classDelegate) {
            b.set(classDelegate);
        }

        public static ClassDelegate a() {
            return b.get();
        }

        protected static NetworkTopologyDiscovery b() {
            ClassDelegate classDelegate = b.get();
            NetworkTopologyDiscovery a2 = classDelegate != null ? classDelegate.a() : null;
            return a2 != null ? a2 : new NetworkTopologyDiscoveryImpl();
        }

        public static NetworkTopologyDiscovery c() {
            if (f2624a == null) {
                synchronized (Factory.class) {
                    if (f2624a == null) {
                        f2624a = b();
                    }
                }
            }
            return f2624a;
        }
    }
}
