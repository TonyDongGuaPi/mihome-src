package javax.jmdns.impl.tasks.state;

import com.taobao.weex.el.parse.Operators;
import java.io.IOException;
import java.util.Timer;
import java.util.logging.Logger;
import javax.jmdns.impl.DNSOutgoing;
import javax.jmdns.impl.DNSQuestion;
import javax.jmdns.impl.DNSRecord;
import javax.jmdns.impl.JmDNSImpl;
import javax.jmdns.impl.ServiceInfoImpl;
import javax.jmdns.impl.constants.DNSRecordClass;
import javax.jmdns.impl.constants.DNSRecordType;
import javax.jmdns.impl.constants.DNSState;

public class Prober extends DNSStateTask {

    /* renamed from: a  reason: collision with root package name */
    static Logger f2675a = Logger.getLogger(Prober.class.getName());

    public String c() {
        return "probing";
    }

    public Prober(JmDNSImpl jmDNSImpl) {
        super(jmDNSImpl, g());
        b(DNSState.PROBING_1);
        a(DNSState.PROBING_1);
    }

    public String b() {
        StringBuilder sb = new StringBuilder();
        sb.append("Prober(");
        sb.append(a() != null ? a().b() : "");
        sb.append(Operators.BRACKET_END_STR);
        return sb.toString();
    }

    public String toString() {
        return super.toString() + " state: " + j();
    }

    public void a(Timer timer) {
        long currentTimeMillis = System.currentTimeMillis();
        if (currentTimeMillis - a().w() < 5000) {
            a().a(a().x() + 1);
        } else {
            a().a(1);
        }
        a().a(currentTimeMillis);
        if (a().isAnnounced() && a().x() < 10) {
            timer.schedule(this, (long) JmDNSImpl.y().nextInt(251), 250);
        } else if (!a().isCanceling() && !a().isCanceled()) {
            timer.schedule(this, 1000, 1000);
        }
    }

    public boolean cancel() {
        i();
        return super.cancel();
    }

    /* access modifiers changed from: protected */
    public boolean d() {
        return !a().isCanceling() && !a().isCanceled();
    }

    /* access modifiers changed from: protected */
    public DNSOutgoing e() {
        return new DNSOutgoing(0);
    }

    /* access modifiers changed from: protected */
    public DNSOutgoing a(DNSOutgoing dNSOutgoing) throws IOException {
        dNSOutgoing.a(DNSQuestion.a(a().r().a(), DNSRecordType.TYPE_ANY, DNSRecordClass.CLASS_IN, false));
        for (DNSRecord a2 : a().r().a(false, h())) {
            dNSOutgoing = a(dNSOutgoing, a2);
        }
        return dNSOutgoing;
    }

    /* access modifiers changed from: protected */
    public DNSOutgoing a(ServiceInfoImpl serviceInfoImpl, DNSOutgoing dNSOutgoing) throws IOException {
        return a(a(dNSOutgoing, DNSQuestion.a(serviceInfoImpl.f(), DNSRecordType.TYPE_ANY, DNSRecordClass.CLASS_IN, false)), (DNSRecord) new DNSRecord.Service(serviceInfoImpl.f(), DNSRecordClass.CLASS_IN, false, h(), serviceInfoImpl.r(), serviceInfoImpl.s(), serviceInfoImpl.q(), a().r().a()));
    }

    /* access modifiers changed from: protected */
    public void a(Throwable th) {
        a().s();
    }

    /* access modifiers changed from: protected */
    public void f() {
        b(j().advance());
        if (!j().isProbing()) {
            cancel();
            a().l();
        }
    }
}
