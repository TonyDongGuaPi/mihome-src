package javax.jmdns.impl;

import com.facebook.appevents.UserDataStore;
import com.taobao.weex.annotation.JSMethod;
import com.taobao.weex.el.parse.Operators;
import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.util.Collections;
import java.util.Map;
import javax.jmdns.ServiceInfo;
import javax.jmdns.impl.constants.DNSRecordClass;
import javax.jmdns.impl.constants.DNSRecordType;

public abstract class DNSEntry {

    /* renamed from: a  reason: collision with root package name */
    final Map<ServiceInfo.Fields, String> f2628a = ServiceInfoImpl.e(b());
    private final String b;
    private final String c;
    private final String d;
    private final DNSRecordType e;
    private final DNSRecordClass f;
    private final boolean g;

    /* access modifiers changed from: protected */
    public void a(StringBuilder sb) {
    }

    public abstract boolean a(long j);

    public abstract boolean b(long j);

    DNSEntry(String str, DNSRecordType dNSRecordType, DNSRecordClass dNSRecordClass, boolean z) {
        String str2;
        String str3;
        String str4;
        this.c = str;
        this.e = dNSRecordType;
        this.f = dNSRecordClass;
        this.g = z;
        String str5 = this.f2628a.get(ServiceInfo.Fields.Domain);
        String str6 = this.f2628a.get(ServiceInfo.Fields.Protocol);
        String str7 = this.f2628a.get(ServiceInfo.Fields.Application);
        String lowerCase = this.f2628a.get(ServiceInfo.Fields.Instance).toLowerCase();
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
        this.d = sb.toString();
        StringBuilder sb2 = new StringBuilder();
        if (lowerCase.length() > 0) {
            str4 = lowerCase + ".";
        } else {
            str4 = "";
        }
        sb2.append(str4);
        sb2.append(this.d);
        this.b = sb2.toString().toLowerCase();
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof DNSEntry)) {
            return false;
        }
        DNSEntry dNSEntry = (DNSEntry) obj;
        if (!d().equals(dNSEntry.d()) || !e().equals(dNSEntry.e()) || f() != dNSEntry.f()) {
            return false;
        }
        return true;
    }

    public boolean a(DNSEntry dNSEntry) {
        return d().equals(dNSEntry.d()) && e().equals(dNSEntry.e()) && (DNSRecordClass.CLASS_ANY == dNSEntry.f() || f().equals(dNSEntry.f()));
    }

    public boolean b(DNSEntry dNSEntry) {
        return a().equals(dNSEntry.a());
    }

    public String a() {
        String str = h().get(ServiceInfo.Fields.Subtype);
        return str != null ? str : "";
    }

    public String b() {
        return this.c != null ? this.c : "";
    }

    public String c() {
        return this.d != null ? this.d : "";
    }

    public String d() {
        return this.b != null ? this.b : "";
    }

    public DNSRecordType e() {
        return this.e != null ? this.e : DNSRecordType.TYPE_IGNORE;
    }

    public DNSRecordClass f() {
        return this.f != null ? this.f : DNSRecordClass.CLASS_UNKNOWN;
    }

    public boolean g() {
        return this.g;
    }

    public Map<ServiceInfo.Fields, String> h() {
        return Collections.unmodifiableMap(this.f2628a);
    }

    public boolean i() {
        return this.f2628a.get(ServiceInfo.Fields.Application).equals("dns-sd") && this.f2628a.get(ServiceInfo.Fields.Instance).equals("_services");
    }

    public boolean j() {
        if (!this.f2628a.get(ServiceInfo.Fields.Application).equals("dns-sd")) {
            return false;
        }
        String str = this.f2628a.get(ServiceInfo.Fields.Instance);
        if ("b".equals(str) || UserDataStore.DATE_OF_BIRTH.equals(str) || "r".equals(str) || "dr".equals(str) || "lb".equals(str)) {
            return true;
        }
        return false;
    }

    public boolean k() {
        return l() || m();
    }

    public boolean l() {
        return this.f2628a.get(ServiceInfo.Fields.Domain).endsWith("in-addr.arpa");
    }

    public boolean m() {
        return this.f2628a.get(ServiceInfo.Fields.Domain).endsWith("ip6.arpa");
    }

    public boolean c(DNSEntry dNSEntry) {
        return dNSEntry != null && dNSEntry.f() == f();
    }

    public boolean d(DNSEntry dNSEntry) {
        return dNSEntry != null && dNSEntry.e() == e();
    }

    /* access modifiers changed from: protected */
    public void a(DataOutputStream dataOutputStream) throws IOException {
        dataOutputStream.write(b().getBytes("UTF8"));
        dataOutputStream.writeShort(e().indexValue());
        dataOutputStream.writeShort(f().indexValue());
    }

    /* access modifiers changed from: protected */
    public byte[] n() {
        try {
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            DataOutputStream dataOutputStream = new DataOutputStream(byteArrayOutputStream);
            a(dataOutputStream);
            dataOutputStream.close();
            return byteArrayOutputStream.toByteArray();
        } catch (IOException unused) {
            throw new InternalError();
        }
    }

    public int e(DNSEntry dNSEntry) {
        byte[] n = n();
        byte[] n2 = dNSEntry.n();
        int min = Math.min(n.length, n2.length);
        for (int i = 0; i < min; i++) {
            if (n[i] > n2[i]) {
                return 1;
            }
            if (n[i] < n2[i]) {
                return -1;
            }
        }
        return n.length - n2.length;
    }

    public int hashCode() {
        return d().hashCode() + e().indexValue() + f().indexValue();
    }

    public String toString() {
        StringBuilder sb = new StringBuilder(200);
        sb.append(Operators.ARRAY_START_STR);
        sb.append(getClass().getSimpleName());
        sb.append("@");
        sb.append(System.identityHashCode(this));
        sb.append(" type: ");
        sb.append(e());
        sb.append(", class: ");
        sb.append(f());
        sb.append(this.g ? "-unique," : ",");
        sb.append(" name: ");
        sb.append(this.c);
        a(sb);
        sb.append(Operators.ARRAY_END_STR);
        return sb.toString();
    }
}
