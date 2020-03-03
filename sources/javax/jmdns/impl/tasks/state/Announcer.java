package javax.jmdns.impl.tasks.state;

import com.taobao.weex.el.parse.Operators;
import java.io.IOException;
import java.util.Timer;
import java.util.logging.Logger;
import javax.jmdns.impl.DNSIncoming;
import javax.jmdns.impl.DNSOutgoing;
import javax.jmdns.impl.DNSRecord;
import javax.jmdns.impl.JmDNSImpl;
import javax.jmdns.impl.ServiceInfoImpl;
import javax.jmdns.impl.constants.DNSState;

public class Announcer extends DNSStateTask {

    /* renamed from: a  reason: collision with root package name */
    static Logger f2672a = Logger.getLogger(Announcer.class.getName());

    public String c() {
        return "announcing";
    }

    public Announcer(JmDNSImpl jmDNSImpl) {
        super(jmDNSImpl, g());
        b(DNSState.ANNOUNCING_1);
        a(DNSState.ANNOUNCING_1);
    }

    public String b() {
        StringBuilder sb = new StringBuilder();
        sb.append("Announcer(");
        sb.append(a() != null ? a().b() : "");
        sb.append(Operators.BRACKET_END_STR);
        return sb.toString();
    }

    public String toString() {
        return super.toString() + " state: " + j();
    }

    public void a(Timer timer) {
        if (!a().isCanceling() && !a().isCanceled()) {
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
        return new DNSOutgoing(33792);
    }

    /* access modifiers changed from: protected */
    public DNSOutgoing a(DNSOutgoing dNSOutgoing) throws IOException {
        for (DNSRecord a2 : a().r().a(true, h())) {
            dNSOutgoing = a(dNSOutgoing, (DNSIncoming) null, a2);
        }
        return dNSOutgoing;
    }

    /* access modifiers changed from: protected */
    public DNSOutgoing a(ServiceInfoImpl serviceInfoImpl, DNSOutgoing dNSOutgoing) throws IOException {
        for (DNSRecord a2 : serviceInfoImpl.a(true, h(), a().r())) {
            dNSOutgoing = a(dNSOutgoing, (DNSIncoming) null, a2);
        }
        return dNSOutgoing;
    }

    /* access modifiers changed from: protected */
    public void a(Throwable th) {
        a().s();
    }

    /* access modifiers changed from: protected */
    public void f() {
        b(j().advance());
        if (!j().isAnnouncing()) {
            cancel();
            a().m();
        }
    }
}
