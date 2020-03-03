package javax.jmdns.impl.tasks.state;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.jmdns.ServiceInfo;
import javax.jmdns.impl.DNSOutgoing;
import javax.jmdns.impl.DNSStatefulObject;
import javax.jmdns.impl.JmDNSImpl;
import javax.jmdns.impl.ServiceInfoImpl;
import javax.jmdns.impl.constants.DNSState;
import javax.jmdns.impl.tasks.DNSTask;

public abstract class DNSStateTask extends DNSTask {
    static Logger b = Logger.getLogger(DNSStateTask.class.getName());
    private static int c = 3600;

    /* renamed from: a  reason: collision with root package name */
    private final int f2674a;
    private DNSState d = null;

    /* access modifiers changed from: protected */
    public abstract DNSOutgoing a(DNSOutgoing dNSOutgoing) throws IOException;

    /* access modifiers changed from: protected */
    public abstract DNSOutgoing a(ServiceInfoImpl serviceInfoImpl, DNSOutgoing dNSOutgoing) throws IOException;

    /* access modifiers changed from: protected */
    public abstract void a(Throwable th);

    public abstract String c();

    /* access modifiers changed from: protected */
    public abstract boolean d();

    /* access modifiers changed from: protected */
    public abstract DNSOutgoing e();

    /* access modifiers changed from: protected */
    public abstract void f();

    public static int g() {
        return c;
    }

    public static void a(int i) {
        c = i;
    }

    public DNSStateTask(JmDNSImpl jmDNSImpl, int i) {
        super(jmDNSImpl);
        this.f2674a = i;
    }

    public int h() {
        return this.f2674a;
    }

    /* access modifiers changed from: protected */
    public void a(DNSState dNSState) {
        synchronized (a()) {
            a().associateWithTask(this, dNSState);
        }
        Iterator<ServiceInfo> it = a().v().values().iterator();
        while (it.hasNext()) {
            ((ServiceInfoImpl) it.next()).associateWithTask(this, dNSState);
        }
    }

    /* access modifiers changed from: protected */
    public void i() {
        synchronized (a()) {
            a().removeAssociationWithTask(this);
        }
        Iterator<ServiceInfo> it = a().v().values().iterator();
        while (it.hasNext()) {
            ((ServiceInfoImpl) it.next()).removeAssociationWithTask(this);
        }
    }

    public void run() {
        DNSOutgoing e = e();
        try {
            if (!d()) {
                cancel();
                return;
            }
            ArrayList arrayList = new ArrayList();
            synchronized (a()) {
                if (a().isAssociatedWithTask(this, j())) {
                    Logger logger = b;
                    logger.finer(b() + ".run() JmDNS " + c() + " " + a().b());
                    arrayList.add(a());
                    e = a(e);
                }
            }
            Iterator<ServiceInfo> it = a().v().values().iterator();
            while (it.hasNext()) {
                ServiceInfoImpl serviceInfoImpl = (ServiceInfoImpl) it.next();
                synchronized (serviceInfoImpl) {
                    if (serviceInfoImpl.isAssociatedWithTask(this, j())) {
                        Logger logger2 = b;
                        logger2.fine(b() + ".run() JmDNS " + c() + " " + serviceInfoImpl.f());
                        arrayList.add(serviceInfoImpl);
                        e = a(serviceInfoImpl, e);
                    }
                }
            }
            if (!e.s()) {
                Logger logger3 = b;
                logger3.finer(b() + ".run() JmDNS " + c() + " #" + j());
                a().a(e);
                a((List<DNSStatefulObject>) arrayList);
                f();
                return;
            }
            a((List<DNSStatefulObject>) arrayList);
            cancel();
        } catch (Throwable th) {
            Logger logger4 = b;
            Level level = Level.WARNING;
            logger4.log(level, b() + ".run() exception ", th);
            a(th);
        }
    }

    /* access modifiers changed from: protected */
    public void a(List<DNSStatefulObject> list) {
        if (list != null) {
            for (DNSStatefulObject next : list) {
                synchronized (next) {
                    next.advanceState(this);
                }
            }
        }
    }

    /* access modifiers changed from: protected */
    public DNSState j() {
        return this.d;
    }

    /* access modifiers changed from: protected */
    public void b(DNSState dNSState) {
        this.d = dNSState;
    }
}
