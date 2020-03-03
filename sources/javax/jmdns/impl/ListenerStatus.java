package javax.jmdns.impl;

import com.taobao.weex.el.parse.Operators;
import java.util.EventListener;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.logging.Logger;
import javax.jmdns.ServiceEvent;
import javax.jmdns.ServiceInfo;
import javax.jmdns.ServiceListener;
import javax.jmdns.ServiceTypeListener;

public class ListenerStatus<T extends EventListener> {

    /* renamed from: a  reason: collision with root package name */
    public static final boolean f2661a = true;
    public static final boolean b = false;
    private final T c;
    private final boolean d;

    public static class ServiceListenerStatus extends ListenerStatus<ServiceListener> {
        private static Logger c = Logger.getLogger(ServiceListenerStatus.class.getName());
        private final ConcurrentMap<String, ServiceInfo> d = new ConcurrentHashMap(32);

        public ServiceListenerStatus(ServiceListener serviceListener, boolean z) {
            super(serviceListener, z);
        }

        /* access modifiers changed from: package-private */
        public void a(ServiceEvent serviceEvent) {
            if (this.d.putIfAbsent(serviceEvent.getName() + "." + serviceEvent.getType(), serviceEvent.getInfo().clone()) == null) {
                ((ServiceListener) a()).a(serviceEvent);
                ServiceInfo info = serviceEvent.getInfo();
                if (info != null && info.a()) {
                    ((ServiceListener) a()).c(serviceEvent);
                    return;
                }
                return;
            }
            Logger logger = c;
            logger.finer("Service Added called for a service already added: " + serviceEvent);
        }

        /* access modifiers changed from: package-private */
        public void b(ServiceEvent serviceEvent) {
            String str = serviceEvent.getName() + "." + serviceEvent.getType();
            if (this.d.remove(str, this.d.get(str))) {
                ((ServiceListener) a()).b(serviceEvent);
                return;
            }
            c.finer("Service Removed called for a service already removed: " + serviceEvent);
        }

        /* access modifiers changed from: package-private */
        public synchronized void c(ServiceEvent serviceEvent) {
            ServiceInfo info = serviceEvent.getInfo();
            if (info == null || !info.a()) {
                c.warning("Service Resolved called for an unresolved event: " + serviceEvent);
            } else {
                String str = serviceEvent.getName() + "." + serviceEvent.getType();
                ServiceInfo serviceInfo = (ServiceInfo) this.d.get(str);
                if (a(info, serviceInfo)) {
                    c.finer("Service Resolved called for a service already resolved: " + serviceEvent);
                } else if (serviceInfo == null) {
                    if (this.d.putIfAbsent(str, info.clone()) == null) {
                        ((ServiceListener) a()).c(serviceEvent);
                    }
                } else if (this.d.replace(str, serviceInfo, info.clone())) {
                    ((ServiceListener) a()).c(serviceEvent);
                }
            }
        }

        private static boolean a(ServiceInfo serviceInfo, ServiceInfo serviceInfo2) {
            if (serviceInfo == null || serviceInfo2 == null || !serviceInfo.equals(serviceInfo2)) {
                return false;
            }
            byte[] t = serviceInfo.t();
            byte[] t2 = serviceInfo2.t();
            if (t.length != t2.length) {
                return false;
            }
            for (int i = 0; i < t.length; i++) {
                if (t[i] != t2[i]) {
                    return false;
                }
            }
            return true;
        }

        public String toString() {
            StringBuilder sb = new StringBuilder(2048);
            sb.append("[Status for ");
            sb.append(((ServiceListener) a()).toString());
            if (this.d.isEmpty()) {
                sb.append(" no type event ");
            } else {
                sb.append(" (");
                for (String append : this.d.keySet()) {
                    sb.append(append);
                    sb.append(", ");
                }
                sb.append(") ");
            }
            sb.append(Operators.ARRAY_END_STR);
            return sb.toString();
        }
    }

    public static class ServiceTypeListenerStatus extends ListenerStatus<ServiceTypeListener> {
        private static Logger c = Logger.getLogger(ServiceTypeListenerStatus.class.getName());
        private final ConcurrentMap<String, String> d = new ConcurrentHashMap(32);

        public ServiceTypeListenerStatus(ServiceTypeListener serviceTypeListener, boolean z) {
            super(serviceTypeListener, z);
        }

        /* access modifiers changed from: package-private */
        public void a(ServiceEvent serviceEvent) {
            if (this.d.putIfAbsent(serviceEvent.getType(), serviceEvent.getType()) == null) {
                ((ServiceTypeListener) a()).a(serviceEvent);
                return;
            }
            Logger logger = c;
            logger.finest("Service Type Added called for a service type already added: " + serviceEvent);
        }

        /* access modifiers changed from: package-private */
        public void b(ServiceEvent serviceEvent) {
            if (this.d.putIfAbsent(serviceEvent.getType(), serviceEvent.getType()) == null) {
                ((ServiceTypeListener) a()).b(serviceEvent);
                return;
            }
            Logger logger = c;
            logger.finest("Service Sub Type Added called for a service sub type already added: " + serviceEvent);
        }

        public String toString() {
            StringBuilder sb = new StringBuilder(2048);
            sb.append("[Status for ");
            sb.append(((ServiceTypeListener) a()).toString());
            if (this.d.isEmpty()) {
                sb.append(" no type event ");
            } else {
                sb.append(" (");
                for (String append : this.d.keySet()) {
                    sb.append(append);
                    sb.append(", ");
                }
                sb.append(") ");
            }
            sb.append(Operators.ARRAY_END_STR);
            return sb.toString();
        }
    }

    public ListenerStatus(T t, boolean z) {
        this.c = t;
        this.d = z;
    }

    public T a() {
        return this.c;
    }

    public boolean b() {
        return this.d;
    }

    public int hashCode() {
        return a().hashCode();
    }

    public boolean equals(Object obj) {
        return (obj instanceof ListenerStatus) && a().equals(((ListenerStatus) obj).a());
    }

    public String toString() {
        return "[Status for " + a().toString() + Operators.ARRAY_END_STR;
    }
}
