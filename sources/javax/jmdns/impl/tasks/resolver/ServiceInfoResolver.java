package javax.jmdns.impl.tasks.resolver;

import com.taobao.weex.el.parse.Operators;
import java.io.IOException;
import javax.jmdns.impl.DNSOutgoing;
import javax.jmdns.impl.DNSQuestion;
import javax.jmdns.impl.DNSRecord;
import javax.jmdns.impl.JmDNSImpl;
import javax.jmdns.impl.ServiceInfoImpl;
import javax.jmdns.impl.constants.DNSRecordClass;
import javax.jmdns.impl.constants.DNSRecordType;

public class ServiceInfoResolver extends DNSResolverTask {
    private final ServiceInfoImpl b;

    public ServiceInfoResolver(JmDNSImpl jmDNSImpl, ServiceInfoImpl serviceInfoImpl) {
        super(jmDNSImpl);
        this.b = serviceInfoImpl;
        serviceInfoImpl.a(a());
        a().a((DNSListener) serviceInfoImpl, DNSQuestion.a(serviceInfoImpl.f(), DNSRecordType.TYPE_ANY, DNSRecordClass.CLASS_IN, false));
    }

    public String b() {
        StringBuilder sb = new StringBuilder();
        sb.append("ServiceInfoResolver(");
        sb.append(a() != null ? a().b() : "");
        sb.append(Operators.BRACKET_END_STR);
        return sb.toString();
    }

    public boolean cancel() {
        boolean cancel = super.cancel();
        if (!this.b.z()) {
            a().a((DNSListener) this.b);
        }
        return cancel;
    }

    /* access modifiers changed from: protected */
    public DNSOutgoing b(DNSOutgoing dNSOutgoing) throws IOException {
        if (this.b.a()) {
            return dNSOutgoing;
        }
        long currentTimeMillis = System.currentTimeMillis();
        DNSOutgoing a2 = a(a(dNSOutgoing, (DNSRecord) a().q().a(this.b.f(), DNSRecordType.TYPE_SRV, DNSRecordClass.CLASS_IN), currentTimeMillis), (DNSRecord) a().q().a(this.b.f(), DNSRecordType.TYPE_TXT, DNSRecordClass.CLASS_IN), currentTimeMillis);
        return this.b.g().length() > 0 ? a(a(a2, (DNSRecord) a().q().a(this.b.g(), DNSRecordType.TYPE_A, DNSRecordClass.CLASS_IN), currentTimeMillis), (DNSRecord) a().q().a(this.b.g(), DNSRecordType.TYPE_AAAA, DNSRecordClass.CLASS_IN), currentTimeMillis) : a2;
    }

    /* access modifiers changed from: protected */
    public DNSOutgoing a(DNSOutgoing dNSOutgoing) throws IOException {
        if (this.b.a()) {
            return dNSOutgoing;
        }
        DNSOutgoing a2 = a(a(dNSOutgoing, DNSQuestion.a(this.b.f(), DNSRecordType.TYPE_SRV, DNSRecordClass.CLASS_IN, false)), DNSQuestion.a(this.b.f(), DNSRecordType.TYPE_TXT, DNSRecordClass.CLASS_IN, false));
        return this.b.g().length() > 0 ? a(a(a2, DNSQuestion.a(this.b.g(), DNSRecordType.TYPE_A, DNSRecordClass.CLASS_IN, false)), DNSQuestion.a(this.b.g(), DNSRecordType.TYPE_AAAA, DNSRecordClass.CLASS_IN, false)) : a2;
    }

    /* access modifiers changed from: protected */
    public String c() {
        StringBuilder sb = new StringBuilder();
        sb.append("querying service info: ");
        sb.append(this.b != null ? this.b.f() : "null");
        return sb.toString();
    }
}
