package javax.jmdns.impl.tasks;

import com.taobao.weex.el.parse.Operators;
import java.util.HashSet;
import java.util.Timer;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.jmdns.impl.DNSIncoming;
import javax.jmdns.impl.DNSOutgoing;
import javax.jmdns.impl.DNSQuestion;
import javax.jmdns.impl.DNSRecord;
import javax.jmdns.impl.JmDNSImpl;
import javax.jmdns.impl.constants.DNSConstants;

public class Responder extends DNSTask {

    /* renamed from: a  reason: collision with root package name */
    static Logger f2670a = Logger.getLogger(Responder.class.getName());
    private final DNSIncoming b;
    private final boolean c;

    public Responder(JmDNSImpl jmDNSImpl, DNSIncoming dNSIncoming, int i) {
        super(jmDNSImpl);
        this.b = dNSIncoming;
        this.c = i != DNSConstants.c;
    }

    public String b() {
        StringBuilder sb = new StringBuilder();
        sb.append("Responder(");
        sb.append(a() != null ? a().b() : "");
        sb.append(Operators.BRACKET_END_STR);
        return sb.toString();
    }

    public String toString() {
        return super.toString() + " incomming: " + this.b;
    }

    public void a(Timer timer) {
        boolean z = true;
        for (DNSQuestion dNSQuestion : this.b.g()) {
            if (f2670a.isLoggable(Level.FINEST)) {
                Logger logger = f2670a;
                logger.finest(b() + "start() question=" + dNSQuestion);
            }
            z = dNSQuestion.a(a());
            if (!z) {
                break;
            }
        }
        int i = 0;
        int nextInt = (!z || this.b.p()) ? (JmDNSImpl.y().nextInt(96) + 20) - this.b.b() : 0;
        if (nextInt >= 0) {
            i = nextInt;
        }
        if (f2670a.isLoggable(Level.FINEST)) {
            Logger logger2 = f2670a;
            logger2.finest(b() + "start() Responder chosen delay=" + i);
        }
        if (!a().isCanceling() && !a().isCanceled()) {
            timer.schedule(this, (long) i);
        }
    }

    public void run() {
        a().b(this.b);
        HashSet<DNSQuestion> hashSet = new HashSet<>();
        HashSet<DNSRecord> hashSet2 = new HashSet<>();
        if (a().isAnnounced()) {
            try {
                for (DNSQuestion dNSQuestion : this.b.g()) {
                    if (f2670a.isLoggable(Level.FINER)) {
                        Logger logger = f2670a;
                        logger.finer(b() + "run() JmDNS responding to: " + dNSQuestion);
                    }
                    if (this.c) {
                        hashSet.add(dNSQuestion);
                    }
                    dNSQuestion.a(a(), hashSet2);
                }
                long currentTimeMillis = System.currentTimeMillis();
                for (DNSRecord dNSRecord : this.b.j()) {
                    if (dNSRecord.a(currentTimeMillis)) {
                        hashSet2.remove(dNSRecord);
                        if (f2670a.isLoggable(Level.FINER)) {
                            Logger logger2 = f2670a;
                            logger2.finer(b() + "JmDNS Responder Known Answer Removed");
                        }
                    }
                }
                if (!hashSet2.isEmpty()) {
                    if (f2670a.isLoggable(Level.FINER)) {
                        Logger logger3 = f2670a;
                        logger3.finer(b() + "run() JmDNS responding");
                    }
                    DNSOutgoing dNSOutgoing = new DNSOutgoing(33792, !this.c, this.b.c());
                    dNSOutgoing.a(this.b.d());
                    for (DNSQuestion dNSQuestion2 : hashSet) {
                        if (dNSQuestion2 != null) {
                            dNSOutgoing = a(dNSOutgoing, dNSQuestion2);
                        }
                    }
                    for (DNSRecord dNSRecord2 : hashSet2) {
                        if (dNSRecord2 != null) {
                            dNSOutgoing = a(dNSOutgoing, this.b, dNSRecord2);
                        }
                    }
                    if (!dNSOutgoing.s()) {
                        a().a(dNSOutgoing);
                    }
                }
            } catch (Throwable th) {
                Logger logger4 = f2670a;
                Level level = Level.WARNING;
                logger4.log(level, b() + "run() exception ", th);
                a().close();
            }
        }
    }
}
