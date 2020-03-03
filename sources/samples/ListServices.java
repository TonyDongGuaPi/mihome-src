package samples;

public class ListServices {
    /* JADX WARNING: Removed duplicated region for block: B:27:0x0046 A[SYNTHETIC, Splitter:B:27:0x0046] */
    /* JADX WARNING: Removed duplicated region for block: B:34:? A[RETURN, SYNTHETIC] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static void a(java.lang.String[] r6) {
        /*
            r6 = 0
            javax.jmdns.JmDNS r0 = javax.jmdns.JmDNS.a()     // Catch:{ Exception -> 0x0038, all -> 0x0033 }
        L_0x0005:
            java.lang.String r6 = "_airport._tcp.local."
            javax.jmdns.ServiceInfo[] r6 = r0.c(r6)     // Catch:{ Exception -> 0x0031 }
            java.io.PrintStream r1 = java.lang.System.out     // Catch:{ Exception -> 0x0031 }
            java.lang.String r2 = "List _airport._tcp.local."
            r1.println(r2)     // Catch:{ Exception -> 0x0031 }
            int r1 = r6.length     // Catch:{ Exception -> 0x0031 }
            r2 = 0
        L_0x0014:
            if (r2 >= r1) goto L_0x0020
            r3 = r6[r2]     // Catch:{ Exception -> 0x0031 }
            java.io.PrintStream r4 = java.lang.System.out     // Catch:{ Exception -> 0x0031 }
            r4.println(r3)     // Catch:{ Exception -> 0x0031 }
            int r2 = r2 + 1
            goto L_0x0014
        L_0x0020:
            java.io.PrintStream r6 = java.lang.System.out     // Catch:{ Exception -> 0x0031 }
            r6.println()     // Catch:{ Exception -> 0x0031 }
            r1 = 5000(0x1388, double:2.4703E-320)
            java.lang.Thread.sleep(r1)     // Catch:{ InterruptedException -> 0x002b }
            goto L_0x0005
        L_0x002b:
            if (r0 == 0) goto L_0x0042
        L_0x002d:
            r0.close()     // Catch:{ IOException -> 0x0042 }
            goto L_0x0042
        L_0x0031:
            r6 = move-exception
            goto L_0x003c
        L_0x0033:
            r0 = move-exception
            r5 = r0
            r0 = r6
            r6 = r5
            goto L_0x0044
        L_0x0038:
            r0 = move-exception
            r5 = r0
            r0 = r6
            r6 = r5
        L_0x003c:
            r6.printStackTrace()     // Catch:{ all -> 0x0043 }
            if (r0 == 0) goto L_0x0042
            goto L_0x002d
        L_0x0042:
            return
        L_0x0043:
            r6 = move-exception
        L_0x0044:
            if (r0 == 0) goto L_0x0049
            r0.close()     // Catch:{ IOException -> 0x0049 }
        L_0x0049:
            throw r6
        */
        throw new UnsupportedOperationException("Method not decompiled: samples.ListServices.a(java.lang.String[]):void");
    }
}
