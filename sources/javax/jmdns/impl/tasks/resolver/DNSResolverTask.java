package javax.jmdns.impl.tasks.resolver;

import java.io.IOException;
import java.util.Timer;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.jmdns.impl.DNSOutgoing;
import javax.jmdns.impl.JmDNSImpl;
import javax.jmdns.impl.tasks.DNSTask;

public abstract class DNSResolverTask extends DNSTask {
    private static Logger b = Logger.getLogger(DNSResolverTask.class.getName());

    /* renamed from: a  reason: collision with root package name */
    protected int f2671a = 0;

    /* access modifiers changed from: protected */
    public abstract DNSOutgoing a(DNSOutgoing dNSOutgoing) throws IOException;

    /* access modifiers changed from: protected */
    public abstract DNSOutgoing b(DNSOutgoing dNSOutgoing) throws IOException;

    /* access modifiers changed from: protected */
    public abstract String c();

    public DNSResolverTask(JmDNSImpl jmDNSImpl) {
        super(jmDNSImpl);
    }

    public String toString() {
        return super.toString() + " count: " + this.f2671a;
    }

    public void a(Timer timer) {
        if (!a().isCanceling() && !a().isCanceled()) {
            timer.schedule(this, 225, 225);
        }
    }

    public void run() {
        try {
            if (!a().isCanceling()) {
                if (!a().isCanceled()) {
                    int i = this.f2671a;
                    this.f2671a = i + 1;
                    if (i < 3) {
                        if (b.isLoggable(Level.FINER)) {
                            Logger logger = b;
                            logger.finer(b() + ".run() JmDNS " + c());
                        }
                        DNSOutgoing a2 = a(new DNSOutgoing(0));
                        if (a().isAnnounced()) {
                            a2 = b(a2);
                        }
                        if (!a2.s()) {
                            a().a(a2);
                            return;
                        }
                        return;
                    }
                    cancel();
                    return;
                }
            }
            cancel();
        } catch (Throwable th) {
            Logger logger2 = b;
            Level level = Level.WARNING;
            logger2.log(level, b() + ".run() exception ", th);
            a().s();
        }
    }
}
