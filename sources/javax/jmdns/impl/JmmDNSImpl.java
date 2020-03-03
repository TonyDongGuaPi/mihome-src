package javax.jmdns.impl;

import java.io.IOException;
import java.net.InetAddress;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.jmdns.JmDNS;
import javax.jmdns.JmmDNS;
import javax.jmdns.NetworkTopologyDiscovery;
import javax.jmdns.NetworkTopologyEvent;
import javax.jmdns.NetworkTopologyListener;
import javax.jmdns.ServiceInfo;
import javax.jmdns.ServiceListener;
import javax.jmdns.ServiceTypeListener;
import javax.jmdns.impl.ServiceInfoImpl;

public class JmmDNSImpl implements JmmDNS, NetworkTopologyListener, ServiceInfoImpl.Delegate {

    /* renamed from: a  reason: collision with root package name */
    private static Logger f2653a = Logger.getLogger(JmmDNSImpl.class.getName());
    private final Set<NetworkTopologyListener> b = Collections.synchronizedSet(new HashSet());
    private final ConcurrentMap<InetAddress, JmDNS> c = new ConcurrentHashMap();
    private final ConcurrentMap<String, ServiceInfo> d = new ConcurrentHashMap(20);
    private final ExecutorService e = Executors.newSingleThreadExecutor();
    private final ExecutorService f = Executors.newCachedThreadPool();
    private final Timer g = new Timer("Multihommed mDNS.Timer", true);

    public JmmDNSImpl() {
        new NetworkChecker(this, NetworkTopologyDiscovery.Factory.c()).a(this.g);
    }

    public void close() throws IOException {
        if (f2653a.isLoggable(Level.FINER)) {
            Logger logger = f2653a;
            logger.finer("Cancelling JmmDNS: " + this);
        }
        this.g.cancel();
        this.e.shutdown();
        ExecutorService newCachedThreadPool = Executors.newCachedThreadPool();
        for (final JmDNS jmDNS : this.c.values()) {
            newCachedThreadPool.submit(new Runnable() {
                public void run() {
                    try {
                        jmDNS.close();
                    } catch (IOException unused) {
                    }
                }
            });
        }
        newCachedThreadPool.shutdown();
        try {
            newCachedThreadPool.awaitTermination(5000, TimeUnit.MILLISECONDS);
        } catch (InterruptedException e2) {
            f2653a.log(Level.WARNING, "Exception ", e2);
        }
        this.c.clear();
    }

    public String[] a() {
        HashSet hashSet = new HashSet();
        for (JmDNS b2 : this.c.values()) {
            hashSet.add(b2.b());
        }
        return (String[]) hashSet.toArray(new String[hashSet.size()]);
    }

    public String[] b() {
        HashSet hashSet = new HashSet();
        for (JmDNS c2 : this.c.values()) {
            hashSet.add(c2.c());
        }
        return (String[]) hashSet.toArray(new String[hashSet.size()]);
    }

    public InetAddress[] c() throws IOException {
        HashSet hashSet = new HashSet();
        for (JmDNS d2 : this.c.values()) {
            hashSet.add(d2.d());
        }
        return (InetAddress[]) hashSet.toArray(new InetAddress[hashSet.size()]);
    }

    public ServiceInfo[] a(String str, String str2) {
        return a(str, str2, false, 6000);
    }

    public ServiceInfo[] a(String str, String str2, long j) {
        return a(str, str2, false, j);
    }

    public ServiceInfo[] a(String str, String str2, boolean z) {
        return a(str, str2, z, 6000);
    }

    public ServiceInfo[] a(String str, String str2, boolean z, long j) {
        Set synchronizedSet = Collections.synchronizedSet(new HashSet(this.c.size()));
        ExecutorService newCachedThreadPool = Executors.newCachedThreadPool();
        for (final JmDNS jmDNS : this.c.values()) {
            final Set set = synchronizedSet;
            final String str3 = str;
            final String str4 = str2;
            final boolean z2 = z;
            final long j2 = j;
            newCachedThreadPool.submit(new Runnable() {
                public void run() {
                    set.add(jmDNS.a(str3, str4, z2, j2));
                }
            });
        }
        newCachedThreadPool.shutdown();
        try {
            newCachedThreadPool.awaitTermination(j, TimeUnit.MILLISECONDS);
        } catch (InterruptedException e2) {
            f2653a.log(Level.WARNING, "Exception ", e2);
        }
        return (ServiceInfo[]) synchronizedSet.toArray(new ServiceInfo[synchronizedSet.size()]);
    }

    public void b(String str, String str2) {
        b(str, str2, false, 6000);
    }

    public void b(String str, String str2, boolean z) {
        b(str, str2, z, 6000);
    }

    public void b(String str, String str2, long j) {
        b(str, str2, false, j);
    }

    public void b(String str, String str2, boolean z, long j) {
        for (final JmDNS jmDNS : this.c.values()) {
            final String str3 = str;
            final String str4 = str2;
            final boolean z2 = z;
            final long j2 = j;
            this.f.submit(new Runnable() {
                public void run() {
                    jmDNS.b(str3, str4, z2, j2);
                }
            });
        }
    }

    public void a(ServiceTypeListener serviceTypeListener) throws IOException {
        for (JmDNS a2 : this.c.values()) {
            a2.a(serviceTypeListener);
        }
    }

    public void b(ServiceTypeListener serviceTypeListener) {
        for (JmDNS b2 : this.c.values()) {
            b2.b(serviceTypeListener);
        }
    }

    public void a(String str, ServiceListener serviceListener) {
        for (JmDNS a2 : this.c.values()) {
            a2.a(str, serviceListener);
        }
    }

    public void b(String str, ServiceListener serviceListener) {
        for (JmDNS b2 : this.c.values()) {
            b2.b(str, serviceListener);
        }
    }

    public void a(ServiceInfo serviceInfo, byte[] bArr) {
        synchronized (this.d) {
            for (JmDNS jmDNS : this.c.values()) {
                ServiceInfo serviceInfo2 = ((JmDNSImpl) jmDNS).v().get(serviceInfo.f());
                if (serviceInfo2 != null) {
                    serviceInfo2.a(bArr);
                } else {
                    f2653a.warning("We have a mDNS that does not know about the service info being updated.");
                }
            }
        }
    }

    public void a(ServiceInfo serviceInfo) throws IOException {
        synchronized (this.d) {
            for (JmDNS a2 : this.c.values()) {
                a2.a(serviceInfo.clone());
            }
            ((ServiceInfoImpl) serviceInfo).a((ServiceInfoImpl.Delegate) this);
            this.d.put(serviceInfo.f(), serviceInfo);
        }
    }

    public void b(ServiceInfo serviceInfo) {
        synchronized (this.d) {
            for (JmDNS b2 : this.c.values()) {
                b2.b(serviceInfo);
            }
            ((ServiceInfoImpl) serviceInfo).a((ServiceInfoImpl.Delegate) null);
            this.d.remove(serviceInfo.f());
        }
    }

    public void d() {
        synchronized (this.d) {
            for (JmDNS e2 : this.c.values()) {
                e2.e();
            }
            this.d.clear();
        }
    }

    public void a(String str) {
        for (JmDNS b2 : this.c.values()) {
            b2.b(str);
        }
    }

    public ServiceInfo[] b(String str) {
        return a(str, 6000);
    }

    public ServiceInfo[] a(String str, long j) {
        Set synchronizedSet = Collections.synchronizedSet(new HashSet(this.c.size() * 5));
        ExecutorService newCachedThreadPool = Executors.newCachedThreadPool();
        for (final JmDNS jmDNS : this.c.values()) {
            final Set set = synchronizedSet;
            final String str2 = str;
            final long j2 = j;
            newCachedThreadPool.submit(new Runnable() {
                public void run() {
                    set.addAll(Arrays.asList(jmDNS.a(str2, j2)));
                }
            });
        }
        newCachedThreadPool.shutdown();
        try {
            newCachedThreadPool.awaitTermination(j, TimeUnit.MILLISECONDS);
        } catch (InterruptedException e2) {
            f2653a.log(Level.WARNING, "Exception ", e2);
        }
        return (ServiceInfo[]) synchronizedSet.toArray(new ServiceInfo[synchronizedSet.size()]);
    }

    public Map<String, ServiceInfo[]> c(String str) {
        return b(str, 6000);
    }

    public Map<String, ServiceInfo[]> b(String str, long j) {
        HashMap hashMap = new HashMap(5);
        for (ServiceInfo serviceInfo : a(str, j)) {
            String D = serviceInfo.D();
            if (!hashMap.containsKey(D)) {
                hashMap.put(D, new ArrayList(10));
            }
            ((List) hashMap.get(D)).add(serviceInfo);
        }
        HashMap hashMap2 = new HashMap(hashMap.size());
        for (String str2 : hashMap.keySet()) {
            List list = (List) hashMap.get(str2);
            hashMap2.put(str2, list.toArray(new ServiceInfo[list.size()]));
        }
        return hashMap2;
    }

    public void a(NetworkTopologyListener networkTopologyListener) {
        this.b.add(networkTopologyListener);
    }

    public void b(NetworkTopologyListener networkTopologyListener) {
        this.b.remove(networkTopologyListener);
    }

    public NetworkTopologyListener[] e() {
        return (NetworkTopologyListener[]) this.b.toArray(new NetworkTopologyListener[this.b.size()]);
    }

    public void a(NetworkTopologyEvent networkTopologyEvent) {
        InetAddress inetAddress = networkTopologyEvent.getInetAddress();
        try {
            synchronized (this) {
                if (!this.c.containsKey(inetAddress)) {
                    this.c.put(inetAddress, JmDNS.a(inetAddress));
                    final NetworkTopologyEventImpl networkTopologyEventImpl = new NetworkTopologyEventImpl((JmDNS) this.c.get(inetAddress), inetAddress);
                    for (final NetworkTopologyListener networkTopologyListener : e()) {
                        this.e.submit(new Runnable() {
                            public void run() {
                                networkTopologyListener.a(networkTopologyEventImpl);
                            }
                        });
                    }
                }
            }
        } catch (Exception e2) {
            f2653a.warning("Unexpected unhandled exception: " + e2);
        }
    }

    public void b(NetworkTopologyEvent networkTopologyEvent) {
        InetAddress inetAddress = networkTopologyEvent.getInetAddress();
        try {
            synchronized (this) {
                if (this.c.containsKey(inetAddress)) {
                    JmDNS jmDNS = (JmDNS) this.c.remove(inetAddress);
                    jmDNS.close();
                    final NetworkTopologyEventImpl networkTopologyEventImpl = new NetworkTopologyEventImpl(jmDNS, inetAddress);
                    for (final NetworkTopologyListener networkTopologyListener : e()) {
                        this.e.submit(new Runnable() {
                            public void run() {
                                networkTopologyListener.b(networkTopologyEventImpl);
                            }
                        });
                    }
                }
            }
        } catch (Exception e2) {
            f2653a.warning("Unexpected unhandled exception: " + e2);
        }
    }

    static class NetworkChecker extends TimerTask {

        /* renamed from: a  reason: collision with root package name */
        private static Logger f2660a = Logger.getLogger(NetworkChecker.class.getName());
        private final NetworkTopologyListener b;
        private final NetworkTopologyDiscovery c;
        private Set<InetAddress> d = Collections.synchronizedSet(new HashSet());

        public NetworkChecker(NetworkTopologyListener networkTopologyListener, NetworkTopologyDiscovery networkTopologyDiscovery) {
            this.b = networkTopologyListener;
            this.c = networkTopologyDiscovery;
        }

        public void a(Timer timer) {
            timer.schedule(this, 0, 10000);
        }

        public void run() {
            try {
                InetAddress[] a2 = this.c.a();
                HashSet hashSet = new HashSet(a2.length);
                for (InetAddress inetAddress : a2) {
                    hashSet.add(inetAddress);
                    if (!this.d.contains(inetAddress)) {
                        this.b.a(new NetworkTopologyEventImpl(this.b, inetAddress));
                    }
                }
                for (InetAddress next : this.d) {
                    if (!hashSet.contains(next)) {
                        this.b.b(new NetworkTopologyEventImpl(this.b, next));
                    }
                }
                this.d = hashSet;
            } catch (Exception e) {
                f2660a.warning("Unexpected unhandled exception: " + e);
            }
        }
    }
}
