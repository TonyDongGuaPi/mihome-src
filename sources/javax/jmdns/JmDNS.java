package javax.jmdns;

import java.io.Closeable;
import java.io.IOException;
import java.net.InetAddress;
import java.util.Collection;
import java.util.Map;
import javax.jmdns.impl.JmDNSImpl;

public abstract class JmDNS implements Closeable {

    /* renamed from: a  reason: collision with root package name */
    public static final String f2622a = "3.4.1";

    public interface Delegate {
        void a(JmDNS jmDNS, Collection<ServiceInfo> collection);
    }

    public abstract Delegate a(Delegate delegate);

    public abstract ServiceInfo a(String str, String str2);

    public abstract ServiceInfo a(String str, String str2, long j);

    public abstract ServiceInfo a(String str, String str2, boolean z);

    public abstract ServiceInfo a(String str, String str2, boolean z, long j);

    public abstract void a(String str, ServiceListener serviceListener);

    public abstract void a(ServiceInfo serviceInfo) throws IOException;

    public abstract void a(ServiceTypeListener serviceTypeListener) throws IOException;

    public abstract ServiceInfo[] a(String str, long j);

    public abstract String b();

    public abstract Map<String, ServiceInfo[]> b(String str, long j);

    public abstract void b(String str, String str2);

    public abstract void b(String str, String str2, long j);

    public abstract void b(String str, String str2, boolean z);

    public abstract void b(String str, String str2, boolean z, long j);

    public abstract void b(String str, ServiceListener serviceListener);

    public abstract void b(ServiceInfo serviceInfo);

    public abstract void b(ServiceTypeListener serviceTypeListener);

    public abstract boolean b(String str);

    public abstract String c();

    public abstract ServiceInfo[] c(String str);

    public abstract InetAddress d() throws IOException;

    public abstract Map<String, ServiceInfo[]> d(String str);

    public abstract void e();

    @Deprecated
    public abstract void f();

    public abstract Delegate g();

    public static JmDNS a() throws Exception {
        return new JmDNSImpl((InetAddress) null, (String) null);
    }

    public static JmDNS a(InetAddress inetAddress) throws Exception {
        return new JmDNSImpl(inetAddress, (String) null);
    }

    public static JmDNS a(String str) throws Exception {
        return new JmDNSImpl((InetAddress) null, str);
    }

    public static JmDNS a(InetAddress inetAddress, String str) throws Exception {
        return new JmDNSImpl(inetAddress, str);
    }
}
