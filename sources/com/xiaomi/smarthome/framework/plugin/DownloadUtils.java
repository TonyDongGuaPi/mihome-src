package com.xiaomi.smarthome.framework.plugin;

import android.content.Context;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import org.aspectj.runtime.internal.AroundClosure;

public class DownloadUtils {

    /* renamed from: a  reason: collision with root package name */
    public static final int f17131a = 1;
    public static final int b = 2;
    public static final int c = 3;

    public class AjcClosure1 extends AroundClosure {
        public AjcClosure1(Object[] objArr) {
            super(objArr);
        }

        public Object run(Object[] objArr) {
            return DownloadUtils.a((URL) this.state[0]);
        }
    }

    public class AjcClosure3 extends AroundClosure {
        public AjcClosure3(Object[] objArr) {
            super(objArr);
        }

        public Object run(Object[] objArr) {
            return DownloadUtils.b((URL) this.state[0]);
        }
    }

    public interface OnDownloadProgress {
        void a();

        void a(long j, long j2);

        void a(String str);

        void b();
    }

    public static synchronized String a(Context context) {
        synchronized (DownloadUtils.class) {
        }
        return "Mozilla/5.0 (Macintosh; Intel Mac OS X 10.7; rv:20.0) Gecko/20100101 Firefox/20.0";
    }

    public static void a(HttpURLConnection httpURLConnection) {
        httpURLConnection.setConnectTimeout(10000);
        httpURLConnection.setReadTimeout(15000);
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r20v0, resolved type: com.xiaomi.smarthome.library.common.network.Network$DoneHandlerInputStream} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r20v1, resolved type: com.xiaomi.smarthome.library.common.network.Network$DoneHandlerInputStream} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r20v2, resolved type: com.xiaomi.smarthome.library.common.network.Network$DoneHandlerInputStream} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r20v3, resolved type: com.xiaomi.smarthome.library.common.network.Network$DoneHandlerInputStream} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r20v4, resolved type: com.xiaomi.smarthome.library.common.network.Network$DoneHandlerInputStream} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r20v5, resolved type: com.xiaomi.smarthome.library.common.network.Network$DoneHandlerInputStream} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r20v6, resolved type: com.xiaomi.smarthome.library.common.network.Network$DoneHandlerInputStream} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r20v7, resolved type: com.xiaomi.smarthome.library.common.network.Network$DoneHandlerInputStream} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r20v8, resolved type: com.xiaomi.smarthome.library.common.network.Network$DoneHandlerInputStream} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r20v9, resolved type: com.xiaomi.smarthome.library.common.network.Network$DoneHandlerInputStream} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r5v26, resolved type: java.io.FileOutputStream} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r20v10, resolved type: com.xiaomi.smarthome.library.common.network.Network$DoneHandlerInputStream} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r5v29, resolved type: java.io.FileOutputStream} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r20v15, resolved type: com.xiaomi.smarthome.library.common.network.Network$DoneHandlerInputStream} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r5v33, resolved type: java.io.FileOutputStream} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r20v16, resolved type: com.xiaomi.smarthome.library.common.network.Network$DoneHandlerInputStream} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r5v37, resolved type: com.xiaomi.smarthome.library.common.network.Network$DoneHandlerInputStream} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r5v38, resolved type: java.io.FileOutputStream} */
    /* JADX WARNING: type inference failed for: r20v13 */
    /* JADX WARNING: type inference failed for: r20v14 */
    /* JADX WARNING: type inference failed for: r20v17 */
    /* JADX WARNING: Code restructure failed: missing block: B:119:0x0240, code lost:
        r0 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:120:0x0241, code lost:
        r2 = r0;
        r20 = r6;
        r7 = r7;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:121:0x0246, code lost:
        r0 = e;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:122:0x0247, code lost:
        r14 = false;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:12:0x004a, code lost:
        r0 = th;
        r5 = r5;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:137:0x0270, code lost:
        r0 = e;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:138:0x0271, code lost:
        r21 = r7;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:139:0x0274, code lost:
        r0 = th;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:140:0x0275, code lost:
        r21 = r7;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:141:0x0277, code lost:
        r8 = r25;
        r2 = r0;
        r5 = null;
        r20 = null;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:142:0x027f, code lost:
        r0 = e;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:143:0x0280, code lost:
        r21 = r7;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:144:0x0283, code lost:
        r14 = false;
        r8 = r25;
        r5 = r0;
        r7 = null;
        r11 = 0;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:15:0x0054, code lost:
        r0 = e;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:16:0x0055, code lost:
        r20 = null;
        r21 = r7;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:175:?, code lost:
        r20.close();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:176:0x0341, code lost:
        r0 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:177:0x0342, code lost:
        com.xiaomi.smarthome.framework.log.MyLog.a((java.lang.Throwable) r0);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:180:?, code lost:
        r5.close();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:181:0x034c, code lost:
        r0 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:182:0x034d, code lost:
        com.xiaomi.smarthome.framework.log.MyLog.a((java.lang.Throwable) r0);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:41:0x00fd, code lost:
        r0 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:42:0x00fe, code lost:
        r2 = r0;
        r21 = r7;
        r5 = null;
        r20 = null;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:53:0x0147, code lost:
        r0 = e;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:74:0x018f, code lost:
        r0 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:75:0x0190, code lost:
        r5 = r0;
        r21 = r7;
        r9 = 0;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:87:0x01ae, code lost:
        r0 = th;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:88:0x01b1, code lost:
        r0 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:89:0x01b2, code lost:
        r8 = r25;
        r5 = r0;
        r7 = null;
        r14 = false;
     */
    /* JADX WARNING: Exception block dominator not found, dom blocks: [B:39:0x00f9, B:67:0x017c] */
    /* JADX WARNING: Failed to process nested try/catch */
    /* JADX WARNING: Multi-variable type inference failed */
    /* JADX WARNING: Removed duplicated region for block: B:119:0x0240 A[ExcHandler: all (r0v30 'th' java.lang.Throwable A[CUSTOM_DECLARE]), Splitter:B:98:0x01e3] */
    /* JADX WARNING: Removed duplicated region for block: B:12:0x004a A[ExcHandler: all (th java.lang.Throwable), PHI: r5 
      PHI: (r5v35 com.xiaomi.smarthome.library.common.network.Network$DoneHandlerInputStream) = (r5v30 com.xiaomi.smarthome.library.common.network.Network$DoneHandlerInputStream), (r5v30 com.xiaomi.smarthome.library.common.network.Network$DoneHandlerInputStream), (r5v0 com.xiaomi.smarthome.library.common.network.Network$DoneHandlerInputStream), (r5v0 com.xiaomi.smarthome.library.common.network.Network$DoneHandlerInputStream), (r5v0 com.xiaomi.smarthome.library.common.network.Network$DoneHandlerInputStream), (r5v0 com.xiaomi.smarthome.library.common.network.Network$DoneHandlerInputStream) binds: [B:49:0x0116, B:50:?, B:26:0x008a, B:27:?, B:10:0x0031, B:11:?] A[DONT_GENERATE, DONT_INLINE], Splitter:B:10:0x0031] */
    /* JADX WARNING: Removed duplicated region for block: B:139:0x0274 A[ExcHandler: all (th java.lang.Throwable), Splitter:B:7:0x001e] */
    /* JADX WARNING: Removed duplicated region for block: B:150:0x02db A[SYNTHETIC, Splitter:B:150:0x02db] */
    /* JADX WARNING: Removed duplicated region for block: B:155:0x02e6 A[SYNTHETIC, Splitter:B:155:0x02e6] */
    /* JADX WARNING: Removed duplicated region for block: B:160:0x02f1  */
    /* JADX WARNING: Removed duplicated region for block: B:165:0x0302  */
    /* JADX WARNING: Removed duplicated region for block: B:166:0x0304  */
    /* JADX WARNING: Removed duplicated region for block: B:174:0x033d A[SYNTHETIC, Splitter:B:174:0x033d] */
    /* JADX WARNING: Removed duplicated region for block: B:179:0x0348 A[SYNTHETIC, Splitter:B:179:0x0348] */
    /* JADX WARNING: Removed duplicated region for block: B:41:0x00fd A[ExcHandler: all (r0v47 'th' java.lang.Throwable A[CUSTOM_DECLARE]), Splitter:B:39:0x00f9] */
    /* JADX WARNING: Removed duplicated region for block: B:87:0x01ae A[ExcHandler: all (th java.lang.Throwable), PHI: r21 
      PHI: (r21v13 long) = (r21v11 long), (r21v11 long), (r21v14 long), (r21v14 long) binds: [B:91:0x01bb, B:92:?, B:85:0x01aa, B:86:?] A[DONT_GENERATE, DONT_INLINE], Splitter:B:85:0x01aa] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static com.xiaomi.smarthome.library.common.network.NetworkUtils.DownloadResponse a(android.content.Context r23, java.lang.String r24, java.io.File r25, com.xiaomi.smarthome.framework.plugin.DownloadUtils.OnDownloadProgress r26, boolean r27, boolean r28) {
        /*
            r1 = r24
            r2 = r26
            boolean r3 = com.xiaomi.smarthome.library.common.network.Network.e((android.content.Context) r23)
            r4 = 2
            r5 = 0
            r6 = 0
            if (r3 != 0) goto L_0x0018
            if (r2 == 0) goto L_0x0012
            r26.b()
        L_0x0012:
            com.xiaomi.smarthome.library.common.network.NetworkUtils$DownloadResponse r0 = new com.xiaomi.smarthome.library.common.network.NetworkUtils$DownloadResponse
            r0.<init>(r6, r4, r6, r5)
            return r0
        L_0x0018:
            long r7 = java.lang.System.currentTimeMillis()
            r9 = 0
            java.net.URL r3 = new java.net.URL     // Catch:{ IOException -> 0x027f, all -> 0x0274 }
            r3.<init>(r1)     // Catch:{ IOException -> 0x027f, all -> 0x0274 }
            java.lang.String r11 = r3.getHost()     // Catch:{ IOException -> 0x027f, all -> 0x0274 }
            r12 = 1
            java.net.HttpURLConnection.setFollowRedirects(r12)     // Catch:{ IOException -> 0x027f, all -> 0x0274 }
            boolean r13 = com.xiaomi.smarthome.library.common.network.Network.c((android.content.Context) r23)     // Catch:{ IOException -> 0x027f, all -> 0x0274 }
            if (r13 == 0) goto L_0x0062
            java.lang.String r3 = com.xiaomi.smarthome.library.common.network.Network.a((java.net.URL) r3)     // Catch:{ IOException -> 0x0054, all -> 0x004a }
            java.net.URL r13 = new java.net.URL     // Catch:{ IOException -> 0x0054, all -> 0x004a }
            r13.<init>(r3)     // Catch:{ IOException -> 0x0054, all -> 0x004a }
            com.xiaomi.miot.support.monitor.aop.trace.TraceNetTrafficMonitor r3 = com.xiaomi.miot.support.monitor.aop.trace.TraceNetTrafficMonitor.b()     // Catch:{ IOException -> 0x0054, all -> 0x004a }
            java.net.URLConnection r3 = r3.b(r13)     // Catch:{ IOException -> 0x0054, all -> 0x004a }
            java.net.HttpURLConnection r3 = (java.net.HttpURLConnection) r3     // Catch:{ IOException -> 0x0054, all -> 0x004a }
            java.lang.String r13 = "X-Online-Host"
            r3.setRequestProperty(r13, r11)     // Catch:{ IOException -> 0x0054, all -> 0x004a }
            goto L_0x006c
        L_0x004a:
            r0 = move-exception
        L_0x004b:
            r2 = r0
            r20 = r5
            r21 = r7
        L_0x0050:
            r8 = r25
            goto L_0x030d
        L_0x0054:
            r0 = move-exception
            r20 = r5
            r21 = r7
        L_0x0059:
            r11 = 0
        L_0x005a:
            r14 = 0
            r8 = r25
            r5 = r0
            r7 = r20
            goto L_0x028b
        L_0x0062:
            com.xiaomi.miot.support.monitor.aop.trace.TraceNetTrafficMonitor r11 = com.xiaomi.miot.support.monitor.aop.trace.TraceNetTrafficMonitor.b()     // Catch:{ IOException -> 0x027f, all -> 0x0274 }
            java.net.URLConnection r3 = r11.b(r3)     // Catch:{ IOException -> 0x027f, all -> 0x0274 }
            java.net.HttpURLConnection r3 = (java.net.HttpURLConnection) r3     // Catch:{ IOException -> 0x027f, all -> 0x0274 }
        L_0x006c:
            java.lang.String r11 = "User-Agent"
            java.lang.String r13 = a((android.content.Context) r23)     // Catch:{ IOException -> 0x027f, all -> 0x0274 }
            r3.setRequestProperty(r11, r13)     // Catch:{ IOException -> 0x027f, all -> 0x0274 }
            java.lang.String r11 = "Connection"
            java.lang.String r13 = "Keep-Alive"
            r3.setRequestProperty(r11, r13)     // Catch:{ IOException -> 0x027f, all -> 0x0274 }
            long r13 = r25.length()     // Catch:{ IOException -> 0x027f, all -> 0x0274 }
            if (r28 == 0) goto L_0x00c5
            int r11 = (r13 > r9 ? 1 : (r13 == r9 ? 0 : -1))
            if (r11 == 0) goto L_0x00c5
            java.lang.String r11 = "Range"
            java.lang.String r15 = "bytes=%d-"
            java.lang.Object[] r9 = new java.lang.Object[r12]     // Catch:{ IOException -> 0x00bd, all -> 0x004a }
            java.lang.Long r10 = java.lang.Long.valueOf(r13)     // Catch:{ IOException -> 0x00bd, all -> 0x004a }
            r9[r6] = r10     // Catch:{ IOException -> 0x00bd, all -> 0x004a }
            java.lang.String r9 = java.lang.String.format(r15, r9)     // Catch:{ IOException -> 0x00bd, all -> 0x004a }
            r3.setRequestProperty(r11, r9)     // Catch:{ IOException -> 0x00bd, all -> 0x004a }
            java.lang.StringBuilder r9 = new java.lang.StringBuilder     // Catch:{ IOException -> 0x00bd, all -> 0x004a }
            r9.<init>()     // Catch:{ IOException -> 0x00bd, all -> 0x004a }
            java.lang.String r10 = "Range:"
            r9.append(r10)     // Catch:{ IOException -> 0x00bd, all -> 0x004a }
            java.lang.String r10 = "bytes=%d-"
            java.lang.Object[] r11 = new java.lang.Object[r12]     // Catch:{ IOException -> 0x00bd, all -> 0x004a }
            java.lang.Long r15 = java.lang.Long.valueOf(r13)     // Catch:{ IOException -> 0x00bd, all -> 0x004a }
            r11[r6] = r15     // Catch:{ IOException -> 0x00bd, all -> 0x004a }
            java.lang.String r10 = java.lang.String.format(r10, r11)     // Catch:{ IOException -> 0x00bd, all -> 0x004a }
            r9.append(r10)     // Catch:{ IOException -> 0x00bd, all -> 0x004a }
            java.lang.String r9 = r9.toString()     // Catch:{ IOException -> 0x00bd, all -> 0x004a }
            com.xiaomi.smarthome.framework.log.MyLog.c(r9)     // Catch:{ IOException -> 0x00bd, all -> 0x004a }
            r9 = r13
            goto L_0x00c7
        L_0x00bd:
            r0 = move-exception
            r20 = r5
            r21 = r7
            r9 = 0
            goto L_0x0059
        L_0x00c5:
            r9 = 0
        L_0x00c7:
            a((java.net.HttpURLConnection) r3)     // Catch:{ IOException -> 0x0270, all -> 0x0274 }
            r3.connect()     // Catch:{ IOException -> 0x0270, all -> 0x0274 }
            int r11 = r3.getResponseCode()     // Catch:{ IOException -> 0x0270, all -> 0x0274 }
            java.lang.StringBuilder r15 = new java.lang.StringBuilder     // Catch:{ IOException -> 0x0267, all -> 0x0274 }
            r15.<init>()     // Catch:{ IOException -> 0x0267, all -> 0x0274 }
            java.lang.String r12 = "the response code is "
            r15.append(r12)     // Catch:{ IOException -> 0x0267, all -> 0x0274 }
            r15.append(r11)     // Catch:{ IOException -> 0x0267, all -> 0x0274 }
            java.lang.String r12 = ", connected in "
            r15.append(r12)     // Catch:{ IOException -> 0x0267, all -> 0x0274 }
            long r18 = java.lang.System.currentTimeMillis()     // Catch:{ IOException -> 0x0267, all -> 0x0274 }
            r12 = 0
            long r4 = r18 - r7
            r15.append(r4)     // Catch:{ IOException -> 0x0267, all -> 0x0274 }
            java.lang.String r4 = r15.toString()     // Catch:{ IOException -> 0x0267, all -> 0x0274 }
            com.xiaomi.smarthome.framework.log.MyLog.c(r4)     // Catch:{ IOException -> 0x0267, all -> 0x0274 }
            r4 = -1
            if (r11 != r4) goto L_0x0155
            if (r2 == 0) goto L_0x0112
            r26.b()     // Catch:{ IOException -> 0x0106, all -> 0x00fd }
            goto L_0x0112
        L_0x00fd:
            r0 = move-exception
            r2 = r0
            r21 = r7
            r5 = 0
            r20 = 0
            goto L_0x0050
        L_0x0106:
            r0 = move-exception
            r5 = r0
            r21 = r7
        L_0x010a:
            r7 = 0
            r14 = 0
            r20 = 0
            r8 = r25
            goto L_0x028b
        L_0x0112:
            com.xiaomi.smarthome.library.common.network.NetworkUtils$DownloadResponse r0 = new com.xiaomi.smarthome.library.common.network.NetworkUtils$DownloadResponse     // Catch:{ IOException -> 0x014d, all -> 0x0149 }
            r5 = 0
            r12 = 2
            r0.<init>(r4, r12, r6, r5)     // Catch:{ IOException -> 0x0147, all -> 0x004a }
            long r2 = java.lang.System.currentTimeMillis()
            long r2 = r2 - r7
            java.lang.StringBuilder r4 = new java.lang.StringBuilder
            r4.<init>()
            java.lang.String r5 = "http downloadFile to "
            r4.append(r5)
            r4.append(r1)
            java.lang.String r1 = " cost "
            r4.append(r1)
            r4.append(r2)
            java.lang.String r1 = "ms, total size = "
            r4.append(r1)
            long r1 = r25.length()
            r4.append(r1)
            java.lang.String r1 = r4.toString()
            com.xiaomi.smarthome.framework.log.MyLog.c(r1)
            return r0
        L_0x0147:
            r0 = move-exception
            goto L_0x014f
        L_0x0149:
            r0 = move-exception
            r5 = 0
            goto L_0x004b
        L_0x014d:
            r0 = move-exception
            r5 = 0
        L_0x014f:
            r20 = r5
            r21 = r7
            goto L_0x005a
        L_0x0155:
            r5 = 0
            r12 = 2
            if (r28 == 0) goto L_0x0196
            r15 = 0
            int r17 = (r13 > r15 ? 1 : (r13 == r15 ? 0 : -1))
            if (r17 <= 0) goto L_0x0196
            r5 = 206(0xce, float:2.89E-43)
            if (r11 == r5) goto L_0x0196
            java.lang.StringBuilder r0 = new java.lang.StringBuilder     // Catch:{ IOException -> 0x0106, all -> 0x00fd }
            r0.<init>()     // Catch:{ IOException -> 0x0106, all -> 0x00fd }
            java.lang.String r5 = "expected response code is 206 while actual code is "
            r0.append(r5)     // Catch:{ IOException -> 0x0106, all -> 0x00fd }
            r0.append(r11)     // Catch:{ IOException -> 0x0106, all -> 0x00fd }
            java.lang.String r5 = " give up append"
            r0.append(r5)     // Catch:{ IOException -> 0x0106, all -> 0x00fd }
            java.lang.String r0 = r0.toString()     // Catch:{ IOException -> 0x0106, all -> 0x00fd }
            com.xiaomi.smarthome.framework.log.MyLog.a((java.lang.String) r0)     // Catch:{ IOException -> 0x0106, all -> 0x00fd }
            boolean r0 = r25.exists()     // Catch:{ IOException -> 0x018f, all -> 0x00fd }
            if (r0 == 0) goto L_0x018b
            boolean r0 = r25.isFile()     // Catch:{ IOException -> 0x018f, all -> 0x00fd }
            if (r0 == 0) goto L_0x018b
            r25.delete()     // Catch:{ IOException -> 0x018f, all -> 0x00fd }
        L_0x018b:
            r9 = r15
            r13 = r9
            r0 = 0
            goto L_0x0198
        L_0x018f:
            r0 = move-exception
            r5 = r0
            r21 = r7
            r9 = r15
            goto L_0x010a
        L_0x0196:
            r0 = r28
        L_0x0198:
            if (r0 == 0) goto L_0x01a1
            int r5 = r3.getContentLength()     // Catch:{ IOException -> 0x0106, all -> 0x00fd }
            int r15 = (int) r13
            int r5 = r5 + r15
            goto L_0x01a5
        L_0x01a1:
            int r5 = r3.getContentLength()     // Catch:{ IOException -> 0x0267, all -> 0x0274 }
        L_0x01a5:
            if (r2 == 0) goto L_0x01b9
            r21 = r7
            long r6 = (long) r5
            r2.a(r13, r6)     // Catch:{ IOException -> 0x01b1, all -> 0x01ae }
            goto L_0x01bb
        L_0x01ae:
            r0 = move-exception
            goto L_0x0277
        L_0x01b1:
            r0 = move-exception
            r8 = r25
            r5 = r0
            r7 = 0
            r14 = 0
            goto L_0x0289
        L_0x01b9:
            r21 = r7
        L_0x01bb:
            com.xiaomi.smarthome.library.common.network.Network$DoneHandlerInputStream r6 = new com.xiaomi.smarthome.library.common.network.Network$DoneHandlerInputStream     // Catch:{ IOException -> 0x0262, all -> 0x01ae }
            java.io.InputStream r3 = r3.getInputStream()     // Catch:{ IOException -> 0x0262, all -> 0x01ae }
            r6.<init>(r3)     // Catch:{ IOException -> 0x0262, all -> 0x01ae }
            java.lang.StringBuilder r3 = new java.lang.StringBuilder     // Catch:{ IOException -> 0x0259, all -> 0x0250 }
            r3.<init>()     // Catch:{ IOException -> 0x0259, all -> 0x0250 }
            java.lang.String r7 = "content bytes "
            r3.append(r7)     // Catch:{ IOException -> 0x0259, all -> 0x0250 }
            r3.append(r5)     // Catch:{ IOException -> 0x0259, all -> 0x0250 }
            java.lang.String r3 = r3.toString()     // Catch:{ IOException -> 0x0259, all -> 0x0250 }
            com.xiaomi.smarthome.framework.log.MyLog.c(r3)     // Catch:{ IOException -> 0x0259, all -> 0x0250 }
            r3 = 1024(0x400, float:1.435E-42)
            byte[] r3 = new byte[r3]     // Catch:{ IOException -> 0x0259, all -> 0x0250 }
            java.io.FileOutputStream r7 = new java.io.FileOutputStream     // Catch:{ IOException -> 0x0259, all -> 0x0250 }
            r8 = r25
            r7.<init>(r8, r0)     // Catch:{ IOException -> 0x024e, all -> 0x024c }
        L_0x01e3:
            int r0 = r6.read(r3)     // Catch:{ IOException -> 0x0246, all -> 0x0240 }
            if (r0 == r4) goto L_0x01f9
            r14 = 0
            r7.write(r3, r14, r0)     // Catch:{ IOException -> 0x01f7, all -> 0x0240 }
            long r12 = (long) r0     // Catch:{ IOException -> 0x01f7, all -> 0x0240 }
            long r9 = r9 + r12
            if (r2 == 0) goto L_0x01f5
            long r12 = (long) r5     // Catch:{ IOException -> 0x01f7, all -> 0x0240 }
            r2.a(r9, r12)     // Catch:{ IOException -> 0x01f7, all -> 0x0240 }
        L_0x01f5:
            r12 = 2
            goto L_0x01e3
        L_0x01f7:
            r0 = move-exception
            goto L_0x0248
        L_0x01f9:
            r14 = 0
            long r3 = java.lang.System.currentTimeMillis()
            long r3 = r3 - r21
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            r0.<init>()
            java.lang.String r5 = "http downloadFile to "
            r0.append(r5)
            r0.append(r1)
            java.lang.String r1 = " cost "
            r0.append(r1)
            r0.append(r3)
            java.lang.String r1 = "ms, total size = "
            r0.append(r1)
            long r3 = r25.length()
            r0.append(r3)
            java.lang.String r0 = r0.toString()
            com.xiaomi.smarthome.framework.log.MyLog.c(r0)
            r6.close()     // Catch:{ IOException -> 0x022c }
            goto L_0x0231
        L_0x022c:
            r0 = move-exception
            r1 = r0
            com.xiaomi.smarthome.framework.log.MyLog.a((java.lang.Throwable) r1)
        L_0x0231:
            r7.close()     // Catch:{ IOException -> 0x0236 }
            r6 = 1
            goto L_0x023c
        L_0x0236:
            r0 = move-exception
            r1 = r0
            com.xiaomi.smarthome.framework.log.MyLog.a((java.lang.Throwable) r1)
            r6 = 0
        L_0x023c:
            r14 = r6
            r5 = 0
            goto L_0x02ef
        L_0x0240:
            r0 = move-exception
            r2 = r0
            r20 = r6
            goto L_0x030c
        L_0x0246:
            r0 = move-exception
            r14 = 0
        L_0x0248:
            r5 = r0
            r20 = r6
            goto L_0x028b
        L_0x024c:
            r0 = move-exception
            goto L_0x0253
        L_0x024e:
            r0 = move-exception
            goto L_0x025c
        L_0x0250:
            r0 = move-exception
            r8 = r25
        L_0x0253:
            r2 = r0
            r20 = r6
            r5 = 0
            goto L_0x030d
        L_0x0259:
            r0 = move-exception
            r8 = r25
        L_0x025c:
            r14 = 0
            r5 = r0
            r20 = r6
            r7 = 0
            goto L_0x028b
        L_0x0262:
            r0 = move-exception
            r8 = r25
            r14 = 0
            goto L_0x026d
        L_0x0267:
            r0 = move-exception
            r21 = r7
            r14 = 0
            r8 = r25
        L_0x026d:
            r5 = r0
            r7 = 0
            goto L_0x0289
        L_0x0270:
            r0 = move-exception
            r21 = r7
            goto L_0x0283
        L_0x0274:
            r0 = move-exception
            r21 = r7
        L_0x0277:
            r8 = r25
            r2 = r0
            r5 = 0
            r20 = 0
            goto L_0x030d
        L_0x027f:
            r0 = move-exception
            r21 = r7
            r15 = r9
        L_0x0283:
            r14 = 0
            r8 = r25
            r5 = r0
            r7 = 0
            r11 = 0
        L_0x0289:
            r20 = 0
        L_0x028b:
            java.lang.StringBuilder r0 = new java.lang.StringBuilder     // Catch:{ all -> 0x030a }
            r0.<init>()     // Catch:{ all -> 0x030a }
            java.lang.String r3 = "error to call url:"
            r0.append(r3)     // Catch:{ all -> 0x030a }
            r0.append(r1)     // Catch:{ all -> 0x030a }
            java.lang.String r3 = " error:"
            r0.append(r3)     // Catch:{ all -> 0x030a }
            java.lang.String r3 = r5.getMessage()     // Catch:{ all -> 0x030a }
            r0.append(r3)     // Catch:{ all -> 0x030a }
            java.lang.String r0 = r0.toString()     // Catch:{ all -> 0x030a }
            com.xiaomi.smarthome.framework.log.MyLog.a((java.lang.String) r0, (java.lang.Throwable) r5)     // Catch:{ all -> 0x030a }
            long r3 = java.lang.System.currentTimeMillis()
            long r3 = r3 - r21
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            r0.<init>()
            java.lang.String r6 = "http downloadFile to "
            r0.append(r6)
            r0.append(r1)
            java.lang.String r1 = " cost "
            r0.append(r1)
            r0.append(r3)
            java.lang.String r1 = "ms, total size = "
            r0.append(r1)
            long r3 = r25.length()
            r0.append(r3)
            java.lang.String r0 = r0.toString()
            com.xiaomi.smarthome.framework.log.MyLog.c(r0)
            if (r20 == 0) goto L_0x02e4
            r20.close()     // Catch:{ IOException -> 0x02df }
            goto L_0x02e4
        L_0x02df:
            r0 = move-exception
            r1 = r0
            com.xiaomi.smarthome.framework.log.MyLog.a((java.lang.Throwable) r1)
        L_0x02e4:
            if (r7 == 0) goto L_0x02ef
            r7.close()     // Catch:{ IOException -> 0x02ea }
            goto L_0x02ef
        L_0x02ea:
            r0 = move-exception
            r1 = r0
            com.xiaomi.smarthome.framework.log.MyLog.a((java.lang.Throwable) r1)
        L_0x02ef:
            if (r2 == 0) goto L_0x02fe
            if (r14 != 0) goto L_0x02f7
            r26.b()
            goto L_0x02fe
        L_0x02f7:
            java.lang.String r0 = r25.getAbsolutePath()
            r2.a(r0)
        L_0x02fe:
            com.xiaomi.smarthome.library.common.network.NetworkUtils$DownloadResponse r0 = new com.xiaomi.smarthome.library.common.network.NetworkUtils$DownloadResponse
            if (r14 == 0) goto L_0x0304
            r4 = 3
            goto L_0x0305
        L_0x0304:
            r4 = 2
        L_0x0305:
            int r1 = (int) r9
            r0.<init>(r11, r4, r1, r5)
            return r0
        L_0x030a:
            r0 = move-exception
            r2 = r0
        L_0x030c:
            r5 = r7
        L_0x030d:
            long r3 = java.lang.System.currentTimeMillis()
            long r3 = r3 - r21
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            r0.<init>()
            java.lang.String r6 = "http downloadFile to "
            r0.append(r6)
            r0.append(r1)
            java.lang.String r1 = " cost "
            r0.append(r1)
            r0.append(r3)
            java.lang.String r1 = "ms, total size = "
            r0.append(r1)
            long r3 = r25.length()
            r0.append(r3)
            java.lang.String r0 = r0.toString()
            com.xiaomi.smarthome.framework.log.MyLog.c(r0)
            if (r20 == 0) goto L_0x0346
            r20.close()     // Catch:{ IOException -> 0x0341 }
            goto L_0x0346
        L_0x0341:
            r0 = move-exception
            r1 = r0
            com.xiaomi.smarthome.framework.log.MyLog.a((java.lang.Throwable) r1)
        L_0x0346:
            if (r5 == 0) goto L_0x0351
            r5.close()     // Catch:{ IOException -> 0x034c }
            goto L_0x0351
        L_0x034c:
            r0 = move-exception
            r1 = r0
            com.xiaomi.smarthome.framework.log.MyLog.a((java.lang.Throwable) r1)
        L_0x0351:
            throw r2
        */
        throw new UnsupportedOperationException("Method not decompiled: com.xiaomi.smarthome.framework.plugin.DownloadUtils.a(android.content.Context, java.lang.String, java.io.File, com.xiaomi.smarthome.framework.plugin.DownloadUtils$OnDownloadProgress, boolean, boolean):com.xiaomi.smarthome.library.common.network.NetworkUtils$DownloadResponse");
    }

    static final URLConnection a(URL url) {
        return url.openConnection();
    }

    static final URLConnection b(URL url) {
        return url.openConnection();
    }
}
