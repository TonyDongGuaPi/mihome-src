package javax.jmdns.impl.tasks.resolver;

import com.taobao.weex.el.parse.Operators;
import java.io.IOException;
import javax.jmdns.ServiceInfo;
import javax.jmdns.impl.DNSOutgoing;
import javax.jmdns.impl.DNSQuestion;
import javax.jmdns.impl.DNSRecord;
import javax.jmdns.impl.JmDNSImpl;
import javax.jmdns.impl.constants.DNSRecordClass;
import javax.jmdns.impl.constants.DNSRecordType;

public class ServiceResolver extends DNSResolverTask {
    private final String b;

    /* access modifiers changed from: protected */
    public String c() {
        return "querying service";
    }

    public ServiceResolver(JmDNSImpl jmDNSImpl, String str) {
        super(jmDNSImpl);
        this.b = str;
    }

    public String b() {
        StringBuilder sb = new StringBuilder();
        sb.append("ServiceResolver(");
        sb.append(a() != null ? a().b() : "");
        sb.append(Operators.BRACKET_END_STR);
        return sb.toString();
    }

    /* access modifiers changed from: protected */
    public DNSOutgoing b(DNSOutgoing dNSOutgoing) throws IOException {
        long currentTimeMillis = System.currentTimeMillis();
        for (ServiceInfo next : a().v().values()) {
            dNSOutgoing = a(dNSOutgoing, (DNSRecord) new DNSRecord.Pointer(next.b(), DNSRecordClass.CLASS_IN, false, 3600, next.f()), currentTimeMillis);
        }
        return dNSOutgoing;
    }

    /* access modifiers changed from: protected */
    public DNSOutgoing a(DNSOutgoing dNSOutgoing) throws IOException {
        return a(dNSOutgoing, DNSQuestion.a(this.b, DNSRecordType.TYPE_PTR, DNSRecordClass.CLASS_IN, false));
    }
}
