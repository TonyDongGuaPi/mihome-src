package samples;

import cn.com.fmsh.tsm.business.constants.Constants;
import java.io.IOException;
import java.io.PrintStream;
import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import javax.jmdns.ServiceEvent;
import javax.jmdns.ServiceListener;

public class ITunesRemotePairing implements Runnable, ServiceListener {

    /* renamed from: a  reason: collision with root package name */
    public static final String f4252a = "_touch-able._tcp.local.";
    public static final String b = "_dacp._tcp.local.";
    public static final String c = "_touch-remote._tcp.local.";
    public static volatile boolean d = true;
    public static byte[] f = {Constants.TagName.PAY_ORDER, Constants.TagName.PUBLISH_END_TIME, Constants.TagName.ELECTRONIC_ID, 97, 0, 0, 0, Constants.TagName.BUSINESS_ORDER_OP_TYPE, Constants.TagName.PAY_ORDER, Constants.TagName.PUBLISH_END_TIME, Constants.TagName.ELECTRONIC_ID, 103, 0, 0, 0, 8, 0, 0, 0, 0, 0, 0, 0, 1, Constants.TagName.PAY_ORDER, Constants.TagName.PUBLISH_END_TIME, Constants.TagName.ELECTRONIC_STARTTIME, Constants.TagName.PUBLISH_END_TIME, 0, 0, 0, 22, Constants.TagName.TERMINAL_BACK_CONTENT, Constants.TagName.PAY_ORDER_LIST, Constants.TagName.PUBLISH_END_TIME, Constants.TagName.MAIN_ORDER_ID, Constants.TagName.ELECTRONIC_STARTTIME, Constants.TagName.MAIN_ORDER_ID, Constants.TagName.ELECTRONIC_TYPE, Constants.TagName.ELECTRONIC_USE_TYPE, Constants.TagName.ELECTRONIC_NUMBER, 97, Constants.TagName.ELECTRONIC_USE_TYPE, Constants.TagName.ELECTRONIC_END_TIME, Constants.TagName.ELECTRONIC_NUMBER, -30, Byte.MIN_VALUE, Constants.TagName.PRODUCT_CODE, Constants.TagName.ELECTRONIC_TYPE, 32, Constants.TagName.MAIN_ORDER_ID, Constants.TagName.ORDER_BRIEF_INFO_LIST, Constants.TagName.ELECTRONIC_END_TIME, Constants.TagName.PAY_ORDER_LIST, Constants.TagName.PAY_ORDER, Constants.TagName.PUBLISH_END_TIME, Constants.TagName.ELECTRONIC_USE_TYPE, Constants.TagName.ELECTRONIC_STATE, 0, 0, 0, 4, Constants.TagName.MAIN_ORDER_ID, Constants.TagName.ORDER_BRIEF_INFO_LIST, Constants.TagName.ELECTRONIC_END_TIME, Constants.TagName.PAY_ORDER_LIST};
    private static final char[] g = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F'};
    protected final Random e = new Random();

    public static void a(String[] strArr) throws IOException {
        ExecutorService newSingleThreadExecutor = Executors.newSingleThreadExecutor();
        newSingleThreadExecutor.submit(new ITunesRemotePairing());
        newSingleThreadExecutor.shutdown();
    }

    /* JADX WARNING: Removed duplicated region for block: B:26:0x0173 A[Catch:{ Exception -> 0x0199 }] */
    /* JADX WARNING: Removed duplicated region for block: B:29:0x0183 A[Catch:{ Exception -> 0x0199 }] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void run() {
        /*
            r11 = this;
            javax.jmdns.JmDNS r0 = javax.jmdns.JmDNS.a()     // Catch:{ Exception -> 0x0199 }
            java.lang.String r1 = "_touch-able._tcp.local."
            r0.a((java.lang.String) r1, (javax.jmdns.ServiceListener) r11)     // Catch:{ Exception -> 0x0199 }
            java.lang.String r1 = "_dacp._tcp.local."
            r0.a((java.lang.String) r1, (javax.jmdns.ServiceListener) r11)     // Catch:{ Exception -> 0x0199 }
            java.util.HashMap r1 = new java.util.HashMap     // Catch:{ Exception -> 0x0199 }
            r1.<init>()     // Catch:{ Exception -> 0x0199 }
            r2 = 4
            byte[] r2 = new byte[r2]     // Catch:{ Exception -> 0x0199 }
            java.util.Random r3 = r11.e     // Catch:{ Exception -> 0x0199 }
            r3.nextBytes(r2)     // Catch:{ Exception -> 0x0199 }
            java.lang.String r3 = "DvNm"
            java.lang.StringBuilder r4 = new java.lang.StringBuilder     // Catch:{ Exception -> 0x0199 }
            r4.<init>()     // Catch:{ Exception -> 0x0199 }
            java.lang.String r5 = "Android-"
            r4.append(r5)     // Catch:{ Exception -> 0x0199 }
            java.lang.String r2 = a((byte[]) r2)     // Catch:{ Exception -> 0x0199 }
            r4.append(r2)     // Catch:{ Exception -> 0x0199 }
            java.lang.String r2 = r4.toString()     // Catch:{ Exception -> 0x0199 }
            r1.put(r3, r2)     // Catch:{ Exception -> 0x0199 }
            java.lang.String r2 = "RemV"
            java.lang.String r3 = "10000"
            r1.put(r2, r3)     // Catch:{ Exception -> 0x0199 }
            java.lang.String r2 = "DvTy"
            java.lang.String r3 = "iPod"
            r1.put(r2, r3)     // Catch:{ Exception -> 0x0199 }
            java.lang.String r2 = "RemN"
            java.lang.String r3 = "Remote"
            r1.put(r2, r3)     // Catch:{ Exception -> 0x0199 }
            java.lang.String r2 = "txtvers"
            java.lang.String r3 = "1"
            r1.put(r2, r3)     // Catch:{ Exception -> 0x0199 }
            r8 = 8
            byte[] r2 = new byte[r8]     // Catch:{ Exception -> 0x0199 }
            java.util.Random r3 = r11.e     // Catch:{ Exception -> 0x0199 }
            r3.nextBytes(r2)     // Catch:{ Exception -> 0x0199 }
            java.lang.String r3 = "Pair"
            java.lang.String r2 = a((byte[]) r2)     // Catch:{ Exception -> 0x0199 }
            r1.put(r3, r2)     // Catch:{ Exception -> 0x0199 }
        L_0x0063:
            boolean r2 = d     // Catch:{ Exception -> 0x0199 }
            if (r2 == 0) goto L_0x0189
            java.net.ServerSocket r9 = new java.net.ServerSocket     // Catch:{ Exception -> 0x0199 }
            r10 = 0
            r9.<init>(r10)     // Catch:{ Exception -> 0x0199 }
            r2 = 20
            byte[] r2 = new byte[r2]     // Catch:{ Exception -> 0x0199 }
            java.util.Random r3 = r11.e     // Catch:{ Exception -> 0x0199 }
            r3.nextBytes(r2)     // Catch:{ Exception -> 0x0199 }
            java.io.PrintStream r3 = java.lang.System.out     // Catch:{ Exception -> 0x0199 }
            java.lang.StringBuilder r4 = new java.lang.StringBuilder     // Catch:{ Exception -> 0x0199 }
            r4.<init>()     // Catch:{ Exception -> 0x0199 }
            java.lang.String r5 = "Requesting pairing for "
            r4.append(r5)     // Catch:{ Exception -> 0x0199 }
            java.lang.String r5 = a((byte[]) r2)     // Catch:{ Exception -> 0x0199 }
            r4.append(r5)     // Catch:{ Exception -> 0x0199 }
            java.lang.String r4 = r4.toString()     // Catch:{ Exception -> 0x0199 }
            r3.println(r4)     // Catch:{ Exception -> 0x0199 }
            java.lang.String r3 = "_touch-remote._tcp.local."
            java.lang.String r4 = a((byte[]) r2)     // Catch:{ Exception -> 0x0199 }
            int r5 = r9.getLocalPort()     // Catch:{ Exception -> 0x0199 }
            r6 = 0
            r7 = 0
            r2 = r3
            r3 = r4
            r4 = r5
            r5 = r6
            r6 = r7
            r7 = r1
            javax.jmdns.ServiceInfo r2 = javax.jmdns.ServiceInfo.a((java.lang.String) r2, (java.lang.String) r3, (int) r4, (int) r5, (int) r6, (java.util.Map<java.lang.String, ?>) r7)     // Catch:{ Exception -> 0x0199 }
            r0.a((javax.jmdns.ServiceInfo) r2)     // Catch:{ Exception -> 0x0199 }
            java.io.PrintStream r3 = java.lang.System.out     // Catch:{ Exception -> 0x0199 }
            java.lang.String r4 = "Waiting for pass code"
            r3.println(r4)     // Catch:{ Exception -> 0x0199 }
            java.net.Socket r3 = r9.accept()     // Catch:{ Exception -> 0x0199 }
            r4 = 0
            java.io.OutputStream r5 = r3.getOutputStream()     // Catch:{ all -> 0x016f }
            java.io.BufferedReader r4 = new java.io.BufferedReader     // Catch:{ all -> 0x016d }
            java.io.InputStreamReader r6 = new java.io.InputStreamReader     // Catch:{ all -> 0x016d }
            java.io.InputStream r3 = r3.getInputStream()     // Catch:{ all -> 0x016d }
            r6.<init>(r3)     // Catch:{ all -> 0x016d }
            r4.<init>(r6)     // Catch:{ all -> 0x016d }
        L_0x00c7:
            boolean r3 = r4.ready()     // Catch:{ all -> 0x016d }
            if (r3 == 0) goto L_0x00d7
            java.lang.String r3 = r4.readLine()     // Catch:{ all -> 0x016d }
            java.io.PrintStream r6 = java.lang.System.out     // Catch:{ all -> 0x016d }
            r6.println(r3)     // Catch:{ all -> 0x016d }
            goto L_0x00c7
        L_0x00d7:
            byte[] r3 = new byte[r8]     // Catch:{ all -> 0x016d }
            java.util.Random r4 = r11.e     // Catch:{ all -> 0x016d }
            r4.nextBytes(r3)     // Catch:{ all -> 0x016d }
            java.io.PrintStream r4 = java.lang.System.out     // Catch:{ all -> 0x016d }
            java.lang.StringBuilder r6 = new java.lang.StringBuilder     // Catch:{ all -> 0x016d }
            r6.<init>()     // Catch:{ all -> 0x016d }
            java.lang.String r7 = "Device guid: "
            r6.append(r7)     // Catch:{ all -> 0x016d }
            java.lang.String r7 = a((byte[]) r3)     // Catch:{ all -> 0x016d }
            r6.append(r7)     // Catch:{ all -> 0x016d }
            java.lang.String r6 = r6.toString()     // Catch:{ all -> 0x016d }
            r4.println(r6)     // Catch:{ all -> 0x016d }
            byte[] r4 = f     // Catch:{ all -> 0x016d }
            r6 = 16
            java.lang.System.arraycopy(r3, r10, r4, r6, r8)     // Catch:{ all -> 0x016d }
            java.lang.String r3 = "HTTP/1.1 200 OK\r\nContent-Length: %d\r\n\r\n"
            r4 = 1
            java.lang.Object[] r4 = new java.lang.Object[r4]     // Catch:{ all -> 0x016d }
            byte[] r6 = f     // Catch:{ all -> 0x016d }
            int r6 = r6.length     // Catch:{ all -> 0x016d }
            java.lang.Integer r6 = java.lang.Integer.valueOf(r6)     // Catch:{ all -> 0x016d }
            r4[r10] = r6     // Catch:{ all -> 0x016d }
            java.lang.String r3 = java.lang.String.format(r3, r4)     // Catch:{ all -> 0x016d }
            byte[] r3 = r3.getBytes()     // Catch:{ all -> 0x016d }
            int r4 = r3.length     // Catch:{ all -> 0x016d }
            byte[] r6 = f     // Catch:{ all -> 0x016d }
            int r6 = r6.length     // Catch:{ all -> 0x016d }
            int r4 = r4 + r6
            byte[] r4 = new byte[r4]     // Catch:{ all -> 0x016d }
            int r6 = r3.length     // Catch:{ all -> 0x016d }
            java.lang.System.arraycopy(r3, r10, r4, r10, r6)     // Catch:{ all -> 0x016d }
            byte[] r6 = f     // Catch:{ all -> 0x016d }
            int r3 = r3.length     // Catch:{ all -> 0x016d }
            byte[] r7 = f     // Catch:{ all -> 0x016d }
            int r7 = r7.length     // Catch:{ all -> 0x016d }
            java.lang.System.arraycopy(r6, r10, r4, r3, r7)     // Catch:{ all -> 0x016d }
            java.io.PrintStream r3 = java.lang.System.out     // Catch:{ all -> 0x016d }
            java.lang.StringBuilder r6 = new java.lang.StringBuilder     // Catch:{ all -> 0x016d }
            r6.<init>()     // Catch:{ all -> 0x016d }
            java.lang.String r7 = "Response: "
            r6.append(r7)     // Catch:{ all -> 0x016d }
            java.lang.String r7 = new java.lang.String     // Catch:{ all -> 0x016d }
            r7.<init>(r4)     // Catch:{ all -> 0x016d }
            r6.append(r7)     // Catch:{ all -> 0x016d }
            java.lang.String r6 = r6.toString()     // Catch:{ all -> 0x016d }
            r3.println(r6)     // Catch:{ all -> 0x016d }
            r5.write(r4)     // Catch:{ all -> 0x016d }
            r5.flush()     // Catch:{ all -> 0x016d }
            java.io.PrintStream r3 = java.lang.System.out     // Catch:{ all -> 0x016d }
            java.lang.String r4 = "someone paired with me!"
            r3.println(r4)     // Catch:{ all -> 0x016d }
            r0.b((javax.jmdns.ServiceInfo) r2)     // Catch:{ all -> 0x016d }
            if (r5 == 0) goto L_0x0159
            r5.close()     // Catch:{ Exception -> 0x0199 }
        L_0x0159:
            java.io.PrintStream r2 = java.lang.System.out     // Catch:{ Exception -> 0x0199 }
            java.lang.String r3 = "Closing Socket"
            r2.println(r3)     // Catch:{ Exception -> 0x0199 }
            boolean r2 = r9.isClosed()     // Catch:{ Exception -> 0x0199 }
            if (r2 != 0) goto L_0x0169
            r9.close()     // Catch:{ Exception -> 0x0199 }
        L_0x0169:
            d = r10     // Catch:{ Exception -> 0x0199 }
            goto L_0x0063
        L_0x016d:
            r0 = move-exception
            goto L_0x0171
        L_0x016f:
            r0 = move-exception
            r5 = r4
        L_0x0171:
            if (r5 == 0) goto L_0x0176
            r5.close()     // Catch:{ Exception -> 0x0199 }
        L_0x0176:
            java.io.PrintStream r1 = java.lang.System.out     // Catch:{ Exception -> 0x0199 }
            java.lang.String r2 = "Closing Socket"
            r1.println(r2)     // Catch:{ Exception -> 0x0199 }
            boolean r1 = r9.isClosed()     // Catch:{ Exception -> 0x0199 }
            if (r1 != 0) goto L_0x0186
            r9.close()     // Catch:{ Exception -> 0x0199 }
        L_0x0186:
            d = r10     // Catch:{ Exception -> 0x0199 }
            throw r0     // Catch:{ Exception -> 0x0199 }
        L_0x0189:
            r1 = 6000(0x1770, double:2.9644E-320)
            java.lang.Thread.sleep(r1)     // Catch:{ Exception -> 0x0199 }
            java.io.PrintStream r1 = java.lang.System.out     // Catch:{ Exception -> 0x0199 }
            java.lang.String r2 = "Closing JmDNS"
            r1.println(r2)     // Catch:{ Exception -> 0x0199 }
            r0.close()     // Catch:{ Exception -> 0x0199 }
            goto L_0x019d
        L_0x0199:
            r0 = move-exception
            r0.printStackTrace()
        L_0x019d:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: samples.ITunesRemotePairing.run():void");
    }

    public void a(ServiceEvent serviceEvent) {
        PrintStream printStream = System.out;
        printStream.println("Service added   : " + serviceEvent.getName() + "." + serviceEvent.getType());
    }

    public void b(ServiceEvent serviceEvent) {
        PrintStream printStream = System.out;
        printStream.println("Service removed : " + serviceEvent.getName() + "." + serviceEvent.getType());
    }

    public void c(ServiceEvent serviceEvent) {
        PrintStream printStream = System.out;
        printStream.println("Service resolved: " + serviceEvent.getName() + "." + serviceEvent.getType() + "\n" + serviceEvent.getInfo());
    }

    private static String a(byte[] bArr) {
        StringBuilder sb = new StringBuilder(bArr.length * 2);
        for (byte b2 : bArr) {
            byte b3 = b2 & 255;
            sb.append(g[b3 / 16]);
            sb.append(g[b3 % 16]);
        }
        return sb.toString();
    }
}
