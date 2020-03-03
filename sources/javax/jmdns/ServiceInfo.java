package javax.jmdns;

import java.net.Inet4Address;
import java.net.Inet6Address;
import java.net.InetAddress;
import java.util.Enumeration;
import java.util.Map;
import javax.jmdns.impl.ServiceInfoImpl;

public abstract class ServiceInfo implements Cloneable {

    /* renamed from: a  reason: collision with root package name */
    public static final byte[] f2625a = new byte[0];

    public enum Fields {
        Domain,
        Protocol,
        Application,
        Instance,
        Subtype
    }

    public abstract String A();

    public abstract String B();

    public abstract String C();

    public abstract String D();

    public abstract Map<Fields, String> E();

    @Deprecated
    public abstract String a(String str);

    public abstract void a(Map<String, ?> map) throws IllegalStateException;

    public abstract void a(byte[] bArr) throws IllegalStateException;

    public abstract boolean a();

    public abstract String b();

    public abstract String[] b(String str);

    public abstract String c();

    public abstract byte[] c(String str);

    public abstract String d();

    public abstract String d(String str);

    public abstract String e();

    public abstract String f();

    public abstract String g();

    @Deprecated
    public abstract String h();

    public abstract String[] i();

    @Deprecated
    public abstract InetAddress j();

    @Deprecated
    public abstract InetAddress k();

    @Deprecated
    public abstract Inet4Address l();

    @Deprecated
    public abstract Inet6Address m();

    public abstract InetAddress[] n();

    public abstract Inet4Address[] o();

    public abstract Inet6Address[] p();

    public abstract int q();

    public abstract int r();

    public abstract int s();

    public abstract byte[] t();

    @Deprecated
    public abstract String u();

    @Deprecated
    public abstract String v();

    public abstract String[] w();

    public abstract Enumeration<String> x();

    public abstract String y();

    public abstract boolean z();

    public static ServiceInfo a(String str, String str2, int i, String str3) {
        return new ServiceInfoImpl(str, str2, "", i, 0, 0, false, str3);
    }

    public static ServiceInfo a(String str, String str2, String str3, int i, String str4) {
        return new ServiceInfoImpl(str, str2, str3, i, 0, 0, false, str4);
    }

    public static ServiceInfo a(String str, String str2, int i, int i2, int i3, String str3) {
        return new ServiceInfoImpl(str, str2, "", i, i2, i3, false, str3);
    }

    public static ServiceInfo a(String str, String str2, String str3, int i, int i2, int i3, String str4) {
        return new ServiceInfoImpl(str, str2, str3, i, i2, i3, false, str4);
    }

    public static ServiceInfo a(String str, String str2, int i, int i2, int i3, Map<String, ?> map) {
        return new ServiceInfoImpl(str, str2, "", i, i2, i3, false, map);
    }

    public static ServiceInfo a(String str, String str2, String str3, int i, int i2, int i3, Map<String, ?> map) {
        return new ServiceInfoImpl(str, str2, str3, i, i2, i3, false, map);
    }

    public static ServiceInfo a(String str, String str2, int i, int i2, int i3, byte[] bArr) {
        return new ServiceInfoImpl(str, str2, "", i, i2, i3, false, bArr);
    }

    public static ServiceInfo a(String str, String str2, String str3, int i, int i2, int i3, byte[] bArr) {
        return new ServiceInfoImpl(str, str2, str3, i, i2, i3, false, bArr);
    }

    public static ServiceInfo a(String str, String str2, int i, int i2, int i3, boolean z, String str3) {
        return new ServiceInfoImpl(str, str2, "", i, i2, i3, z, str3);
    }

    public static ServiceInfo a(String str, String str2, String str3, int i, int i2, int i3, boolean z, String str4) {
        return new ServiceInfoImpl(str, str2, str3, i, i2, i3, z, str4);
    }

    public static ServiceInfo a(String str, String str2, int i, int i2, int i3, boolean z, Map<String, ?> map) {
        return new ServiceInfoImpl(str, str2, "", i, i2, i3, z, map);
    }

    public static ServiceInfo a(String str, String str2, String str3, int i, int i2, int i3, boolean z, Map<String, ?> map) {
        return new ServiceInfoImpl(str, str2, str3, i, i2, i3, z, map);
    }

    public static ServiceInfo a(String str, String str2, int i, int i2, int i3, boolean z, byte[] bArr) {
        return new ServiceInfoImpl(str, str2, "", i, i2, i3, z, bArr);
    }

    public static ServiceInfo a(String str, String str2, String str3, int i, int i2, int i3, boolean z, byte[] bArr) {
        return new ServiceInfoImpl(str, str2, str3, i, i2, i3, z, bArr);
    }

    public static ServiceInfo a(Map<Fields, String> map, int i, int i2, int i3, boolean z, Map<String, ?> map2) {
        return new ServiceInfoImpl(map, i, i2, i3, z, map2);
    }

    /* renamed from: F */
    public ServiceInfo clone() {
        try {
            return (ServiceInfo) super.clone();
        } catch (CloneNotSupportedException unused) {
            return null;
        }
    }
}
