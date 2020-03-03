package javax.jmdns.impl;

import java.util.logging.Logger;

class SocketListener extends Thread {

    /* renamed from: a  reason: collision with root package name */
    static Logger f2665a = Logger.getLogger(SocketListener.class.getName());
    private final JmDNSImpl b;

    /* JADX WARNING: Illegal instructions before constructor call */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    SocketListener(javax.jmdns.impl.JmDNSImpl r3) {
        /*
            r2 = this;
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            r0.<init>()
            java.lang.String r1 = "SocketListener("
            r0.append(r1)
            if (r3 == 0) goto L_0x0011
            java.lang.String r1 = r3.b()
            goto L_0x0013
        L_0x0011:
            java.lang.String r1 = ""
        L_0x0013:
            r0.append(r1)
            java.lang.String r1 = ")"
            r0.append(r1)
            java.lang.String r0 = r0.toString()
            r2.<init>(r0)
            r0 = 1
            r2.setDaemon(r0)
            r2.b = r3
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: javax.jmdns.impl.SocketListener.<init>(javax.jmdns.impl.JmDNSImpl):void");
    }

    /* JADX WARNING: Code restructure failed: missing block: B:32:0x00d7, code lost:
        r0 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:33:0x00d8, code lost:
        r0.printStackTrace();
        r1 = f2665a;
        r2 = java.util.logging.Level.WARNING;
        r1.log(r2, getName() + ".run() exception ", r0);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:34:0x00f8, code lost:
        r0 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:43:0x0119, code lost:
        r1 = f2665a;
        r2 = java.util.logging.Level.WARNING;
        r1.log(r2, getName() + ".run() exception ", r0);
        r7.b.s();
     */
    /* JADX WARNING: Failed to process nested try/catch */
    /* JADX WARNING: Removed duplicated region for block: B:32:0x00d7 A[ExcHandler: Exception (r0v6 'e' java.lang.Exception A[CUSTOM_DECLARE]), Splitter:B:1:0x0002] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void run() {
        /*
            r7 = this;
            r0 = 8972(0x230c, float:1.2572E-41)
            byte[] r0 = new byte[r0]     // Catch:{ IOException -> 0x00f8, Exception -> 0x00d7 }
            java.net.DatagramPacket r1 = new java.net.DatagramPacket     // Catch:{ IOException -> 0x00f8, Exception -> 0x00d7 }
            int r2 = r0.length     // Catch:{ IOException -> 0x00f8, Exception -> 0x00d7 }
            r1.<init>(r0, r2)     // Catch:{ IOException -> 0x00f8, Exception -> 0x00d7 }
        L_0x000a:
            javax.jmdns.impl.JmDNSImpl r2 = r7.b     // Catch:{ IOException -> 0x00f8, Exception -> 0x00d7 }
            boolean r2 = r2.isCanceling()     // Catch:{ IOException -> 0x00f8, Exception -> 0x00d7 }
            if (r2 != 0) goto L_0x013a
            javax.jmdns.impl.JmDNSImpl r2 = r7.b     // Catch:{ IOException -> 0x00f8, Exception -> 0x00d7 }
            boolean r2 = r2.isCanceled()     // Catch:{ IOException -> 0x00f8, Exception -> 0x00d7 }
            if (r2 != 0) goto L_0x013a
            int r2 = r0.length     // Catch:{ IOException -> 0x00f8, Exception -> 0x00d7 }
            r1.setLength(r2)     // Catch:{ IOException -> 0x00f8, Exception -> 0x00d7 }
            javax.jmdns.impl.JmDNSImpl r2 = r7.b     // Catch:{ IOException -> 0x00f8, Exception -> 0x00d7 }
            java.net.MulticastSocket r2 = r2.D()     // Catch:{ IOException -> 0x00f8, Exception -> 0x00d7 }
            r2.receive(r1)     // Catch:{ IOException -> 0x00f8, Exception -> 0x00d7 }
            javax.jmdns.impl.JmDNSImpl r2 = r7.b     // Catch:{ IOException -> 0x00f8, Exception -> 0x00d7 }
            boolean r2 = r2.isCanceling()     // Catch:{ IOException -> 0x00f8, Exception -> 0x00d7 }
            if (r2 != 0) goto L_0x013a
            javax.jmdns.impl.JmDNSImpl r2 = r7.b     // Catch:{ IOException -> 0x00f8, Exception -> 0x00d7 }
            boolean r2 = r2.isCanceled()     // Catch:{ IOException -> 0x00f8, Exception -> 0x00d7 }
            if (r2 != 0) goto L_0x013a
            javax.jmdns.impl.JmDNSImpl r2 = r7.b     // Catch:{ IOException -> 0x00f8, Exception -> 0x00d7 }
            boolean r2 = r2.isClosing()     // Catch:{ IOException -> 0x00f8, Exception -> 0x00d7 }
            if (r2 != 0) goto L_0x013a
            javax.jmdns.impl.JmDNSImpl r2 = r7.b     // Catch:{ IOException -> 0x00f8, Exception -> 0x00d7 }
            boolean r2 = r2.isClosed()     // Catch:{ IOException -> 0x00f8, Exception -> 0x00d7 }
            if (r2 == 0) goto L_0x0049
            goto L_0x013a
        L_0x0049:
            javax.jmdns.impl.JmDNSImpl r2 = r7.b     // Catch:{ IOException -> 0x00b8, Exception -> 0x00d7 }
            javax.jmdns.impl.HostInfo r2 = r2.r()     // Catch:{ IOException -> 0x00b8, Exception -> 0x00d7 }
            boolean r2 = r2.a((java.net.DatagramPacket) r1)     // Catch:{ IOException -> 0x00b8, Exception -> 0x00d7 }
            if (r2 == 0) goto L_0x0056
            goto L_0x000a
        L_0x0056:
            javax.jmdns.impl.DNSIncoming r2 = new javax.jmdns.impl.DNSIncoming     // Catch:{ IOException -> 0x00b8, Exception -> 0x00d7 }
            r2.<init>(r1)     // Catch:{ IOException -> 0x00b8, Exception -> 0x00d7 }
            java.util.logging.Logger r3 = f2665a     // Catch:{ IOException -> 0x00b8, Exception -> 0x00d7 }
            java.util.logging.Level r4 = java.util.logging.Level.FINEST     // Catch:{ IOException -> 0x00b8, Exception -> 0x00d7 }
            boolean r3 = r3.isLoggable(r4)     // Catch:{ IOException -> 0x00b8, Exception -> 0x00d7 }
            if (r3 == 0) goto L_0x0087
            java.util.logging.Logger r3 = f2665a     // Catch:{ IOException -> 0x00b8, Exception -> 0x00d7 }
            java.lang.StringBuilder r4 = new java.lang.StringBuilder     // Catch:{ IOException -> 0x00b8, Exception -> 0x00d7 }
            r4.<init>()     // Catch:{ IOException -> 0x00b8, Exception -> 0x00d7 }
            java.lang.String r5 = r7.getName()     // Catch:{ IOException -> 0x00b8, Exception -> 0x00d7 }
            r4.append(r5)     // Catch:{ IOException -> 0x00b8, Exception -> 0x00d7 }
            java.lang.String r5 = ".run() JmDNS in:"
            r4.append(r5)     // Catch:{ IOException -> 0x00b8, Exception -> 0x00d7 }
            r5 = 1
            java.lang.String r5 = r2.a((boolean) r5)     // Catch:{ IOException -> 0x00b8, Exception -> 0x00d7 }
            r4.append(r5)     // Catch:{ IOException -> 0x00b8, Exception -> 0x00d7 }
            java.lang.String r4 = r4.toString()     // Catch:{ IOException -> 0x00b8, Exception -> 0x00d7 }
            r3.finest(r4)     // Catch:{ IOException -> 0x00b8, Exception -> 0x00d7 }
        L_0x0087:
            boolean r3 = r2.q()     // Catch:{ IOException -> 0x00b8, Exception -> 0x00d7 }
            if (r3 == 0) goto L_0x00b1
            int r3 = r1.getPort()     // Catch:{ IOException -> 0x00b8, Exception -> 0x00d7 }
            int r4 = javax.jmdns.impl.constants.DNSConstants.c     // Catch:{ IOException -> 0x00b8, Exception -> 0x00d7 }
            if (r3 == r4) goto L_0x00a2
            javax.jmdns.impl.JmDNSImpl r3 = r7.b     // Catch:{ IOException -> 0x00b8, Exception -> 0x00d7 }
            java.net.InetAddress r4 = r1.getAddress()     // Catch:{ IOException -> 0x00b8, Exception -> 0x00d7 }
            int r5 = r1.getPort()     // Catch:{ IOException -> 0x00b8, Exception -> 0x00d7 }
            r3.a((javax.jmdns.impl.DNSIncoming) r2, (java.net.InetAddress) r4, (int) r5)     // Catch:{ IOException -> 0x00b8, Exception -> 0x00d7 }
        L_0x00a2:
            javax.jmdns.impl.JmDNSImpl r3 = r7.b     // Catch:{ IOException -> 0x00b8, Exception -> 0x00d7 }
            javax.jmdns.impl.JmDNSImpl r4 = r7.b     // Catch:{ IOException -> 0x00b8, Exception -> 0x00d7 }
            java.net.InetAddress r4 = r4.E()     // Catch:{ IOException -> 0x00b8, Exception -> 0x00d7 }
            int r5 = javax.jmdns.impl.constants.DNSConstants.c     // Catch:{ IOException -> 0x00b8, Exception -> 0x00d7 }
            r3.a((javax.jmdns.impl.DNSIncoming) r2, (java.net.InetAddress) r4, (int) r5)     // Catch:{ IOException -> 0x00b8, Exception -> 0x00d7 }
            goto L_0x000a
        L_0x00b1:
            javax.jmdns.impl.JmDNSImpl r3 = r7.b     // Catch:{ IOException -> 0x00b8, Exception -> 0x00d7 }
            r3.a((javax.jmdns.impl.DNSIncoming) r2)     // Catch:{ IOException -> 0x00b8, Exception -> 0x00d7 }
            goto L_0x000a
        L_0x00b8:
            r2 = move-exception
            java.util.logging.Logger r3 = f2665a     // Catch:{ IOException -> 0x00f8, Exception -> 0x00d7 }
            java.util.logging.Level r4 = java.util.logging.Level.WARNING     // Catch:{ IOException -> 0x00f8, Exception -> 0x00d7 }
            java.lang.StringBuilder r5 = new java.lang.StringBuilder     // Catch:{ IOException -> 0x00f8, Exception -> 0x00d7 }
            r5.<init>()     // Catch:{ IOException -> 0x00f8, Exception -> 0x00d7 }
            java.lang.String r6 = r7.getName()     // Catch:{ IOException -> 0x00f8, Exception -> 0x00d7 }
            r5.append(r6)     // Catch:{ IOException -> 0x00f8, Exception -> 0x00d7 }
            java.lang.String r6 = ".run() exception "
            r5.append(r6)     // Catch:{ IOException -> 0x00f8, Exception -> 0x00d7 }
            java.lang.String r5 = r5.toString()     // Catch:{ IOException -> 0x00f8, Exception -> 0x00d7 }
            r3.log(r4, r5, r2)     // Catch:{ IOException -> 0x00f8, Exception -> 0x00d7 }
            goto L_0x000a
        L_0x00d7:
            r0 = move-exception
            r0.printStackTrace()
            java.util.logging.Logger r1 = f2665a
            java.util.logging.Level r2 = java.util.logging.Level.WARNING
            java.lang.StringBuilder r3 = new java.lang.StringBuilder
            r3.<init>()
            java.lang.String r4 = r7.getName()
            r3.append(r4)
            java.lang.String r4 = ".run() exception "
            r3.append(r4)
            java.lang.String r3 = r3.toString()
            r1.log(r2, r3, r0)
            goto L_0x013a
        L_0x00f8:
            r0 = move-exception
            javax.jmdns.impl.JmDNSImpl r1 = r7.b
            boolean r1 = r1.isCanceling()
            if (r1 != 0) goto L_0x013a
            javax.jmdns.impl.JmDNSImpl r1 = r7.b
            boolean r1 = r1.isCanceled()
            if (r1 != 0) goto L_0x013a
            javax.jmdns.impl.JmDNSImpl r1 = r7.b
            boolean r1 = r1.isClosing()
            if (r1 != 0) goto L_0x013a
            javax.jmdns.impl.JmDNSImpl r1 = r7.b
            boolean r1 = r1.isClosed()
            if (r1 != 0) goto L_0x013a
            java.util.logging.Logger r1 = f2665a
            java.util.logging.Level r2 = java.util.logging.Level.WARNING
            java.lang.StringBuilder r3 = new java.lang.StringBuilder
            r3.<init>()
            java.lang.String r4 = r7.getName()
            r3.append(r4)
            java.lang.String r4 = ".run() exception "
            r3.append(r4)
            java.lang.String r3 = r3.toString()
            r1.log(r2, r3, r0)
            javax.jmdns.impl.JmDNSImpl r0 = r7.b
            r0.s()
        L_0x013a:
            java.util.logging.Logger r0 = f2665a
            java.util.logging.Level r1 = java.util.logging.Level.FINEST
            boolean r0 = r0.isLoggable(r1)
            if (r0 == 0) goto L_0x015e
            java.util.logging.Logger r0 = f2665a
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            r1.<init>()
            java.lang.String r2 = r7.getName()
            r1.append(r2)
            java.lang.String r2 = ".run() exiting."
            r1.append(r2)
            java.lang.String r1 = r1.toString()
            r0.finest(r1)
        L_0x015e:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: javax.jmdns.impl.SocketListener.run():void");
    }

    public JmDNSImpl a() {
        return this.b;
    }
}
