package javax.jmdns.impl;

import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.atomic.AtomicReference;
import javax.jmdns.impl.tasks.RecordReaper;
import javax.jmdns.impl.tasks.Responder;
import javax.jmdns.impl.tasks.resolver.ServiceInfoResolver;
import javax.jmdns.impl.tasks.resolver.ServiceResolver;
import javax.jmdns.impl.tasks.resolver.TypeResolver;
import javax.jmdns.impl.tasks.state.Announcer;
import javax.jmdns.impl.tasks.state.Canceler;
import javax.jmdns.impl.tasks.state.Prober;
import javax.jmdns.impl.tasks.state.Renewer;

public interface DNSTaskStarter {
    void a(DNSIncoming dNSIncoming, int i);

    void a(ServiceInfoImpl serviceInfoImpl);

    void ad_();

    void e_(String str);

    void h();

    void i();

    void j();

    void k();

    void l();

    void m();

    void n();

    void o();

    void p();

    public static final class Factory {

        /* renamed from: a  reason: collision with root package name */
        private static volatile Factory f2640a;
        private static final AtomicReference<ClassDelegate> c = new AtomicReference<>();
        private final ConcurrentMap<JmDNSImpl, DNSTaskStarter> b = new ConcurrentHashMap(20);

        public interface ClassDelegate {
            DNSTaskStarter a(JmDNSImpl jmDNSImpl);
        }

        private Factory() {
        }

        public static void a(ClassDelegate classDelegate) {
            c.set(classDelegate);
        }

        public static ClassDelegate a() {
            return c.get();
        }

        protected static DNSTaskStarter a(JmDNSImpl jmDNSImpl) {
            ClassDelegate classDelegate = c.get();
            DNSTaskStarter a2 = classDelegate != null ? classDelegate.a(jmDNSImpl) : null;
            return a2 != null ? a2 : new DNSTaskStarterImpl(jmDNSImpl);
        }

        public static Factory b() {
            if (f2640a == null) {
                synchronized (Factory.class) {
                    if (f2640a == null) {
                        f2640a = new Factory();
                    }
                }
            }
            return f2640a;
        }

        public DNSTaskStarter b(JmDNSImpl jmDNSImpl) {
            DNSTaskStarter dNSTaskStarter = (DNSTaskStarter) this.b.get(jmDNSImpl);
            if (dNSTaskStarter != null) {
                return dNSTaskStarter;
            }
            this.b.putIfAbsent(jmDNSImpl, a(jmDNSImpl));
            return (DNSTaskStarter) this.b.get(jmDNSImpl);
        }
    }

    public static final class DNSTaskStarterImpl implements DNSTaskStarter {

        /* renamed from: a  reason: collision with root package name */
        private final JmDNSImpl f2638a;
        private final Timer b = new StarterTimer("JmDNS(" + this.f2638a.b() + ").Timer", true);
        private final Timer c = new StarterTimer("JmDNS(" + this.f2638a.b() + ").State.Timer", false);

        public static class StarterTimer extends Timer {

            /* renamed from: a  reason: collision with root package name */
            private volatile boolean f2639a = false;

            public StarterTimer() {
            }

            public StarterTimer(boolean z) {
                super(z);
            }

            public StarterTimer(String str, boolean z) {
                super(str, z);
            }

            public StarterTimer(String str) {
                super(str);
            }

            public synchronized void cancel() {
                if (!this.f2639a) {
                    this.f2639a = true;
                    super.cancel();
                }
            }

            public synchronized void schedule(TimerTask timerTask, long j) {
                if (!this.f2639a) {
                    super.schedule(timerTask, j);
                }
            }

            public synchronized void schedule(TimerTask timerTask, Date date) {
                if (!this.f2639a) {
                    super.schedule(timerTask, date);
                }
            }

            public synchronized void schedule(TimerTask timerTask, long j, long j2) {
                if (!this.f2639a) {
                    super.schedule(timerTask, j, j2);
                }
            }

            public synchronized void schedule(TimerTask timerTask, Date date, long j) {
                if (!this.f2639a) {
                    super.schedule(timerTask, date, j);
                }
            }

            public synchronized void scheduleAtFixedRate(TimerTask timerTask, long j, long j2) {
                if (!this.f2639a) {
                    super.scheduleAtFixedRate(timerTask, j, j2);
                }
            }

            public synchronized void scheduleAtFixedRate(TimerTask timerTask, Date date, long j) {
                if (!this.f2639a) {
                    super.scheduleAtFixedRate(timerTask, date, j);
                }
            }
        }

        public DNSTaskStarterImpl(JmDNSImpl jmDNSImpl) {
            this.f2638a = jmDNSImpl;
        }

        public void ad_() {
            this.b.purge();
        }

        public void h() {
            this.c.purge();
        }

        public void i() {
            this.b.cancel();
        }

        public void j() {
            this.c.cancel();
        }

        public void k() {
            new Prober(this.f2638a).a(this.c);
        }

        public void l() {
            new Announcer(this.f2638a).a(this.c);
        }

        public void m() {
            new Renewer(this.f2638a).a(this.c);
        }

        public void n() {
            new Canceler(this.f2638a).a(this.c);
        }

        public void o() {
            new RecordReaper(this.f2638a).a(this.b);
        }

        public void a(ServiceInfoImpl serviceInfoImpl) {
            new ServiceInfoResolver(this.f2638a, serviceInfoImpl).a(this.b);
        }

        public void p() {
            new TypeResolver(this.f2638a).a(this.b);
        }

        public void e_(String str) {
            new ServiceResolver(this.f2638a, str).a(this.b);
        }

        public void a(DNSIncoming dNSIncoming, int i) {
            new Responder(this.f2638a, dNSIncoming, i).a(this.b);
        }
    }
}
