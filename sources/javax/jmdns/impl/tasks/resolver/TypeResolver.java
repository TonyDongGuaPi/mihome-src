package javax.jmdns.impl.tasks.resolver;

import com.taobao.weex.el.parse.Operators;
import java.io.IOException;
import javax.jmdns.impl.DNSOutgoing;
import javax.jmdns.impl.DNSQuestion;
import javax.jmdns.impl.DNSRecord;
import javax.jmdns.impl.JmDNSImpl;
import javax.jmdns.impl.constants.DNSRecordClass;
import javax.jmdns.impl.constants.DNSRecordType;

public class TypeResolver extends DNSResolverTask {
    /* access modifiers changed from: protected */
    public String c() {
        return "querying type";
    }

    public TypeResolver(JmDNSImpl jmDNSImpl) {
        super(jmDNSImpl);
    }

    public String b() {
        StringBuilder sb = new StringBuilder();
        sb.append("TypeResolver(");
        sb.append(a() != null ? a().b() : "");
        sb.append(Operators.BRACKET_END_STR);
        return sb.toString();
    }

    /* access modifiers changed from: protected */
    public DNSOutgoing b(DNSOutgoing dNSOutgoing) throws IOException {
        long currentTimeMillis = System.currentTimeMillis();
        for (String str : a().C().keySet()) {
            dNSOutgoing = a(dNSOutgoing, (DNSRecord) new DNSRecord.Pointer("_services._dns-sd._udp.local.", DNSRecordClass.CLASS_IN, false, 3600, a().C().get(str).a()), currentTimeMillis);
        }
        return dNSOutgoing;
    }

    /* access modifiers changed from: protected */
    public DNSOutgoing a(DNSOutgoing dNSOutgoing) throws IOException {
        return a(dNSOutgoing, DNSQuestion.a("_services._dns-sd._udp.local.", DNSRecordType.TYPE_PTR, DNSRecordClass.CLASS_IN, false));
    }
}
