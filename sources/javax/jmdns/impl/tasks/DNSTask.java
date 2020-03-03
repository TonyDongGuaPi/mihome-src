package javax.jmdns.impl.tasks;

import java.io.IOException;
import java.util.Timer;
import java.util.TimerTask;
import javax.jmdns.impl.DNSIncoming;
import javax.jmdns.impl.DNSOutgoing;
import javax.jmdns.impl.DNSQuestion;
import javax.jmdns.impl.DNSRecord;
import javax.jmdns.impl.JmDNSImpl;

public abstract class DNSTask extends TimerTask {

    /* renamed from: a  reason: collision with root package name */
    private final JmDNSImpl f2668a;

    public abstract void a(Timer timer);

    public abstract String b();

    protected DNSTask(JmDNSImpl jmDNSImpl) {
        this.f2668a = jmDNSImpl;
    }

    public JmDNSImpl a() {
        return this.f2668a;
    }

    public String toString() {
        return b();
    }

    public DNSOutgoing a(DNSOutgoing dNSOutgoing, DNSQuestion dNSQuestion) throws IOException {
        try {
            dNSOutgoing.a(dNSQuestion);
            return dNSOutgoing;
        } catch (IOException unused) {
            int e = dNSOutgoing.e();
            boolean f = dNSOutgoing.f();
            int c = dNSOutgoing.c();
            int d = dNSOutgoing.d();
            dNSOutgoing.b(e | 512);
            dNSOutgoing.a(d);
            this.f2668a.a(dNSOutgoing);
            DNSOutgoing dNSOutgoing2 = new DNSOutgoing(e, f, c);
            dNSOutgoing2.a(dNSQuestion);
            return dNSOutgoing2;
        }
    }

    public DNSOutgoing a(DNSOutgoing dNSOutgoing, DNSIncoming dNSIncoming, DNSRecord dNSRecord) throws IOException {
        try {
            dNSOutgoing.a(dNSIncoming, dNSRecord);
            return dNSOutgoing;
        } catch (IOException unused) {
            int e = dNSOutgoing.e();
            boolean f = dNSOutgoing.f();
            int c = dNSOutgoing.c();
            int d = dNSOutgoing.d();
            dNSOutgoing.b(e | 512);
            dNSOutgoing.a(d);
            this.f2668a.a(dNSOutgoing);
            DNSOutgoing dNSOutgoing2 = new DNSOutgoing(e, f, c);
            dNSOutgoing2.a(dNSIncoming, dNSRecord);
            return dNSOutgoing2;
        }
    }

    public DNSOutgoing a(DNSOutgoing dNSOutgoing, DNSRecord dNSRecord, long j) throws IOException {
        try {
            dNSOutgoing.a(dNSRecord, j);
            return dNSOutgoing;
        } catch (IOException unused) {
            int e = dNSOutgoing.e();
            boolean f = dNSOutgoing.f();
            int c = dNSOutgoing.c();
            int d = dNSOutgoing.d();
            dNSOutgoing.b(e | 512);
            dNSOutgoing.a(d);
            this.f2668a.a(dNSOutgoing);
            DNSOutgoing dNSOutgoing2 = new DNSOutgoing(e, f, c);
            dNSOutgoing2.a(dNSRecord, j);
            return dNSOutgoing2;
        }
    }

    public DNSOutgoing a(DNSOutgoing dNSOutgoing, DNSRecord dNSRecord) throws IOException {
        try {
            dNSOutgoing.a(dNSRecord);
            return dNSOutgoing;
        } catch (IOException unused) {
            int e = dNSOutgoing.e();
            boolean f = dNSOutgoing.f();
            int c = dNSOutgoing.c();
            int d = dNSOutgoing.d();
            dNSOutgoing.b(e | 512);
            dNSOutgoing.a(d);
            this.f2668a.a(dNSOutgoing);
            DNSOutgoing dNSOutgoing2 = new DNSOutgoing(e, f, c);
            dNSOutgoing2.a(dNSRecord);
            return dNSOutgoing2;
        }
    }

    public DNSOutgoing b(DNSOutgoing dNSOutgoing, DNSIncoming dNSIncoming, DNSRecord dNSRecord) throws IOException {
        try {
            dNSOutgoing.b(dNSIncoming, dNSRecord);
            return dNSOutgoing;
        } catch (IOException unused) {
            int e = dNSOutgoing.e();
            boolean f = dNSOutgoing.f();
            int c = dNSOutgoing.c();
            int d = dNSOutgoing.d();
            dNSOutgoing.b(e | 512);
            dNSOutgoing.a(d);
            this.f2668a.a(dNSOutgoing);
            DNSOutgoing dNSOutgoing2 = new DNSOutgoing(e, f, c);
            dNSOutgoing2.b(dNSIncoming, dNSRecord);
            return dNSOutgoing2;
        }
    }
}
