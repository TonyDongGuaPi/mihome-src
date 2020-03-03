package javax.jmdns.impl;

import java.lang.reflect.Method;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.util.Enumeration;
import java.util.HashSet;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.jmdns.NetworkTopologyDiscovery;

public class NetworkTopologyDiscoveryImpl implements NetworkTopologyDiscovery {

    /* renamed from: a  reason: collision with root package name */
    private static final Logger f2663a = Logger.getLogger(NetworkTopologyDiscoveryImpl.class.getName());
    private final Method b;
    private final Method c;

    public void a(InetAddress inetAddress) {
    }

    public void b(InetAddress inetAddress) {
    }

    public NetworkTopologyDiscoveryImpl() {
        Method method;
        Method method2 = null;
        try {
            method = NetworkInterface.class.getMethod("isUp", (Class[]) null);
        } catch (Exception unused) {
            method = null;
        }
        this.b = method;
        try {
            method2 = NetworkInterface.class.getMethod("supportsMulticast", (Class[]) null);
        } catch (Exception unused2) {
        }
        this.c = method2;
    }

    public InetAddress[] a() {
        HashSet hashSet = new HashSet();
        try {
            Enumeration<NetworkInterface> networkInterfaces = NetworkInterface.getNetworkInterfaces();
            while (networkInterfaces.hasMoreElements()) {
                NetworkInterface nextElement = networkInterfaces.nextElement();
                Enumeration<InetAddress> inetAddresses = nextElement.getInetAddresses();
                while (inetAddresses.hasMoreElements()) {
                    InetAddress nextElement2 = inetAddresses.nextElement();
                    if (f2663a.isLoggable(Level.FINEST)) {
                        Logger logger = f2663a;
                        logger.finest("Found NetworkInterface/InetAddress: " + nextElement + " -- " + nextElement2);
                    }
                    if (a(nextElement, nextElement2)) {
                        hashSet.add(nextElement2);
                    }
                }
            }
        } catch (SocketException e) {
            Logger logger2 = f2663a;
            logger2.warning("Error while fetching network interfaces addresses: " + e);
        }
        return (InetAddress[]) hashSet.toArray(new InetAddress[hashSet.size()]);
    }

    /* JADX WARNING: Exception block dominator not found, dom blocks: [] */
    /* JADX WARNING: Failed to process nested try/catch */
    /* JADX WARNING: Missing exception handler attribute for start block: B:16:0x002d */
    /* JADX WARNING: Missing exception handler attribute for start block: B:9:0x0018 */
    /* JADX WARNING: Removed duplicated region for block: B:12:0x001c A[SYNTHETIC, Splitter:B:12:0x001c] */
    /* JADX WARNING: Removed duplicated region for block: B:19:0x0033 A[RETURN] */
    /* JADX WARNING: Removed duplicated region for block: B:20:0x0034 A[RETURN] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public boolean a(java.net.NetworkInterface r5, java.net.InetAddress r6) {
        /*
            r4 = this;
            r0 = 0
            java.lang.reflect.Method r1 = r4.b     // Catch:{ Exception -> 0x0036 }
            r2 = 0
            if (r1 == 0) goto L_0x0018
            java.lang.reflect.Method r1 = r4.b     // Catch:{ Exception -> 0x0018 }
            r3 = r2
            java.lang.Object[] r3 = (java.lang.Object[]) r3     // Catch:{ Exception -> 0x0018 }
            java.lang.Object r1 = r1.invoke(r5, r3)     // Catch:{ Exception -> 0x0018 }
            java.lang.Boolean r1 = (java.lang.Boolean) r1     // Catch:{ Exception -> 0x0018 }
            boolean r1 = r1.booleanValue()     // Catch:{ Exception -> 0x0018 }
            if (r1 != 0) goto L_0x0018
            return r0
        L_0x0018:
            java.lang.reflect.Method r1 = r4.c     // Catch:{ Exception -> 0x0036 }
            if (r1 == 0) goto L_0x002d
            java.lang.reflect.Method r1 = r4.c     // Catch:{ Exception -> 0x002d }
            java.lang.Object[] r2 = (java.lang.Object[]) r2     // Catch:{ Exception -> 0x002d }
            java.lang.Object r5 = r1.invoke(r5, r2)     // Catch:{ Exception -> 0x002d }
            java.lang.Boolean r5 = (java.lang.Boolean) r5     // Catch:{ Exception -> 0x002d }
            boolean r5 = r5.booleanValue()     // Catch:{ Exception -> 0x002d }
            if (r5 != 0) goto L_0x002d
            return r0
        L_0x002d:
            boolean r5 = r6.isLoopbackAddress()     // Catch:{ Exception -> 0x0036 }
            if (r5 == 0) goto L_0x0034
            return r0
        L_0x0034:
            r5 = 1
            return r5
        L_0x0036:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: javax.jmdns.impl.NetworkTopologyDiscoveryImpl.a(java.net.NetworkInterface, java.net.InetAddress):boolean");
    }
}
