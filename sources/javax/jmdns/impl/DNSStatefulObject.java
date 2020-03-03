package javax.jmdns.impl;

import com.taobao.weex.ui.module.WXModalUIModule;
import java.util.Collection;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.jmdns.impl.constants.DNSState;
import javax.jmdns.impl.tasks.DNSTask;

public interface DNSStatefulObject {
    boolean advanceState(DNSTask dNSTask);

    void associateWithTask(DNSTask dNSTask, DNSState dNSState);

    boolean cancelState();

    boolean closeState();

    JmDNSImpl getDns();

    boolean isAnnounced();

    boolean isAnnouncing();

    boolean isAssociatedWithTask(DNSTask dNSTask, DNSState dNSState);

    boolean isCanceled();

    boolean isCanceling();

    boolean isClosed();

    boolean isClosing();

    boolean isProbing();

    boolean recoverState();

    void removeAssociationWithTask(DNSTask dNSTask);

    boolean revertState();

    boolean waitForAnnounced(long j);

    boolean waitForCanceled(long j);

    public static final class DNSStatefulObjectSemaphore {

        /* renamed from: a  reason: collision with root package name */
        private static Logger f2636a = Logger.getLogger(DNSStatefulObjectSemaphore.class.getName());
        private final String b;
        private final ConcurrentMap<Thread, Semaphore> c = new ConcurrentHashMap(50);

        public DNSStatefulObjectSemaphore(String str) {
            this.b = str;
        }

        public void a(long j) {
            Thread currentThread = Thread.currentThread();
            if (((Semaphore) this.c.get(currentThread)) == null) {
                Semaphore semaphore = new Semaphore(1, true);
                semaphore.drainPermits();
                this.c.putIfAbsent(currentThread, semaphore);
            }
            try {
                ((Semaphore) this.c.get(currentThread)).tryAcquire(j, TimeUnit.MILLISECONDS);
            } catch (InterruptedException e) {
                f2636a.log(Level.FINER, "Exception ", e);
            }
        }

        public void a() {
            Collection<Semaphore> values = this.c.values();
            for (Semaphore semaphore : values) {
                semaphore.release();
                values.remove(semaphore);
            }
        }

        public String toString() {
            StringBuilder sb = new StringBuilder(1000);
            sb.append("Semaphore: ");
            sb.append(this.b);
            if (this.c.size() == 0) {
                sb.append(" no semaphores.");
            } else {
                sb.append(" semaphores:\n");
                for (Thread thread : this.c.keySet()) {
                    sb.append("\tThread: ");
                    sb.append(thread.getName());
                    sb.append(' ');
                    sb.append(this.c.get(thread));
                    sb.append(10);
                }
            }
            return sb.toString();
        }
    }

    public static class DefaultImplementation extends ReentrantLock implements DNSStatefulObject {

        /* renamed from: a  reason: collision with root package name */
        private static Logger f2637a = Logger.getLogger(DefaultImplementation.class.getName());
        private static final long serialVersionUID = -3264781576883412227L;
        private final DNSStatefulObjectSemaphore _announcing = new DNSStatefulObjectSemaphore("Announce");
        private final DNSStatefulObjectSemaphore _canceling = new DNSStatefulObjectSemaphore(WXModalUIModule.CANCEL);
        private volatile JmDNSImpl _dns = null;
        protected volatile DNSState _state = DNSState.PROBING_1;
        protected volatile DNSTask _task = null;

        public JmDNSImpl getDns() {
            return this._dns;
        }

        /* access modifiers changed from: protected */
        public void setDns(JmDNSImpl jmDNSImpl) {
            this._dns = jmDNSImpl;
        }

        public void associateWithTask(DNSTask dNSTask, DNSState dNSState) {
            if (this._task == null && this._state == dNSState) {
                lock();
                try {
                    if (this._task == null && this._state == dNSState) {
                        setTask(dNSTask);
                    }
                } finally {
                    unlock();
                }
            }
        }

        public void removeAssociationWithTask(DNSTask dNSTask) {
            if (this._task == dNSTask) {
                lock();
                try {
                    if (this._task == dNSTask) {
                        setTask((DNSTask) null);
                    }
                } finally {
                    unlock();
                }
            }
        }

        public boolean isAssociatedWithTask(DNSTask dNSTask, DNSState dNSState) {
            lock();
            try {
                return this._task == dNSTask && this._state == dNSState;
            } finally {
                unlock();
            }
        }

        /* access modifiers changed from: protected */
        public void setTask(DNSTask dNSTask) {
            this._task = dNSTask;
        }

        /* access modifiers changed from: protected */
        public void setState(DNSState dNSState) {
            lock();
            try {
                this._state = dNSState;
                if (isAnnounced()) {
                    this._announcing.a();
                }
                if (isCanceled()) {
                    this._canceling.a();
                    this._announcing.a();
                }
            } finally {
                unlock();
            }
        }

        public boolean advanceState(DNSTask dNSTask) {
            if (this._task != dNSTask) {
                return true;
            }
            lock();
            try {
                if (this._task == dNSTask) {
                    setState(this._state.advance());
                } else {
                    Logger logger = f2637a;
                    logger.warning("Trying to advance state whhen not the owner. owner: " + this._task + " perpetrator: " + dNSTask);
                }
                return true;
            } finally {
                unlock();
            }
        }

        public boolean revertState() {
            if (a()) {
                return true;
            }
            lock();
            try {
                if (!a()) {
                    setState(this._state.revert());
                    setTask((DNSTask) null);
                }
                return true;
            } finally {
                unlock();
            }
        }

        public boolean cancelState() {
            boolean z = false;
            if (!a()) {
                lock();
                try {
                    if (!a()) {
                        setState(DNSState.CANCELING_1);
                        setTask((DNSTask) null);
                        z = true;
                    }
                } finally {
                    unlock();
                }
            }
            return z;
        }

        public boolean closeState() {
            boolean z = false;
            if (!b()) {
                lock();
                try {
                    if (!b()) {
                        setState(DNSState.CLOSING);
                        setTask((DNSTask) null);
                        z = true;
                    }
                } finally {
                    unlock();
                }
            }
            return z;
        }

        /* JADX INFO: finally extract failed */
        public boolean recoverState() {
            lock();
            try {
                setState(DNSState.PROBING_1);
                setTask((DNSTask) null);
                unlock();
                return false;
            } catch (Throwable th) {
                unlock();
                throw th;
            }
        }

        public boolean isProbing() {
            return this._state.isProbing();
        }

        public boolean isAnnouncing() {
            return this._state.isAnnouncing();
        }

        public boolean isAnnounced() {
            return this._state.isAnnounced();
        }

        public boolean isCanceling() {
            return this._state.isCanceling();
        }

        public boolean isCanceled() {
            return this._state.isCanceled();
        }

        public boolean isClosing() {
            return this._state.isClosing();
        }

        public boolean isClosed() {
            return this._state.isClosed();
        }

        private boolean a() {
            return this._state.isCanceled() || this._state.isCanceling();
        }

        private boolean b() {
            return this._state.isClosed() || this._state.isClosing();
        }

        public boolean waitForAnnounced(long j) {
            if (!isAnnounced() && !a()) {
                this._announcing.a(j);
            }
            if (!isAnnounced()) {
                if (a() || b()) {
                    Logger logger = f2637a;
                    logger.fine("Wait for announced cancelled: " + this);
                } else {
                    Logger logger2 = f2637a;
                    logger2.warning("Wait for announced timed out: " + this);
                }
            }
            return isAnnounced();
        }

        public boolean waitForCanceled(long j) {
            if (!isCanceled()) {
                this._canceling.a(j);
            }
            if (!isCanceled() && !b()) {
                Logger logger = f2637a;
                logger.warning("Wait for canceled timed out: " + this);
            }
            return isCanceled();
        }

        public String toString() {
            String str;
            StringBuilder sb = new StringBuilder();
            if (this._dns != null) {
                str = "DNS: " + this._dns.b();
            } else {
                str = "NO DNS";
            }
            sb.append(str);
            sb.append(" state: ");
            sb.append(this._state);
            sb.append(" task: ");
            sb.append(this._task);
            return sb.toString();
        }
    }
}
