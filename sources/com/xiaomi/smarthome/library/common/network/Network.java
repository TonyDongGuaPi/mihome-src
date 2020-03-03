package com.xiaomi.smarthome.library.common.network;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.telephony.TelephonyManager;
import android.text.TextUtils;
import android.util.Log;
import com.xiaomi.miot.support.monitor.aop.trace.TraceNetTrafficMonitor;
import java.io.FilterInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.InetSocketAddress;
import java.net.Proxy;
import java.net.URL;
import java.net.URLConnection;
import java.util.Map;
import java.util.regex.Pattern;
import org.aspectj.runtime.internal.AroundClosure;

public class Network {

    /* renamed from: a  reason: collision with root package name */
    public static final String f18634a = "Mozilla/5.0 (Windows; U; Windows NT 6.1; en-US) AppleWebKit/534.3 (KHTML, like Gecko) Chrome/6.0.464.0 Safari/534.3";
    public static final String b = "Mozilla/5.0 (Windows; U; Windows NT 6.1; en-US) AppleWebKit/534.3 (KHTML, like Gecko) Chrome/6.0.464.0 Safari/534.3";
    public static final String c = "10.0.0.172";
    public static final int d = 80;
    public static final String e = "X-Online-Host";
    public static final String f = "User-Agent";
    public static final int g = 10000;
    public static final int h = 15000;
    public static final String i = "3gwap";
    public static final String j = "3gnet";
    public static final String k = "wifi";
    public static final String l = "#777";
    public static final Pattern m = Pattern.compile("([^\\s;]+)(.*)");
    public static final Pattern n = Pattern.compile("(.*?charset\\s*=[^a-zA-Z0-9]*)([-a-zA-Z0-9]+)(.*)", 2);
    public static final Pattern o = Pattern.compile("(\\<\\?xml\\s+.*?encoding\\s*=[^a-zA-Z0-9]*)([-a-zA-Z0-9]+)(.*)", 2);
    public static final String p = "RESPONSE_CODE";
    public static final String q = "RESPONSE_BODY";
    private static final String r = "common.Network";

    public class AjcClosure1 extends AroundClosure {
        public AjcClosure1(Object[] objArr) {
            super(objArr);
        }

        public Object run(Object[] objArr) {
            return Network.b((URL) this.state[0]);
        }
    }

    public class AjcClosure11 extends AroundClosure {
        public AjcClosure11(Object[] objArr) {
            super(objArr);
        }

        public Object run(Object[] objArr) {
            return Network.f((URL) this.state[0]);
        }
    }

    public class AjcClosure13 extends AroundClosure {
        public AjcClosure13(Object[] objArr) {
            super(objArr);
        }

        public Object run(Object[] objArr) {
            return Network.g((URL) this.state[0]);
        }
    }

    public class AjcClosure3 extends AroundClosure {
        public AjcClosure3(Object[] objArr) {
            super(objArr);
        }

        public Object run(Object[] objArr) {
            return Network.c((URL) this.state[0]);
        }
    }

    public class AjcClosure5 extends AroundClosure {
        public AjcClosure5(Object[] objArr) {
            super(objArr);
        }

        public Object run(Object[] objArr) {
            return Network.d((URL) this.state[0]);
        }
    }

    public class AjcClosure7 extends AroundClosure {
        public AjcClosure7(Object[] objArr) {
            super(objArr);
        }

        public Object run(Object[] objArr) {
            return Network.e((URL) this.state[0]);
        }
    }

    public class AjcClosure9 extends AroundClosure {
        public AjcClosure9(Object[] objArr) {
            super(objArr);
        }

        public Object run(Object[] objArr) {
            Object[] objArr2 = this.state;
            return Network.a((URL) objArr2[0], (Proxy) objArr2[1]);
        }
    }

    public static boolean a(String str, OutputStream outputStream) {
        return a(str, outputStream, false, (Context) null);
    }

    /* JADX WARNING: Removed duplicated region for block: B:34:0x006d A[SYNTHETIC, Splitter:B:34:0x006d] */
    /* JADX WARNING: Removed duplicated region for block: B:38:0x0072 A[SYNTHETIC, Splitter:B:38:0x0072] */
    /* JADX WARNING: Removed duplicated region for block: B:43:0x0078 A[SYNTHETIC, Splitter:B:43:0x0078] */
    /* JADX WARNING: Removed duplicated region for block: B:47:0x007d A[SYNTHETIC, Splitter:B:47:0x007d] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static boolean a(java.lang.String r5, java.io.OutputStream r6, boolean r7, android.content.Context r8) {
        /*
            r0 = 0
            r1 = 0
            java.net.URL r2 = new java.net.URL     // Catch:{ IOException -> 0x0054 }
            r2.<init>(r5)     // Catch:{ IOException -> 0x0054 }
            com.xiaomi.miot.support.monitor.aop.trace.TraceNetTrafficMonitor r5 = com.xiaomi.miot.support.monitor.aop.trace.TraceNetTrafficMonitor.b()     // Catch:{ IOException -> 0x0054 }
            java.net.URLConnection r5 = r5.b(r2)     // Catch:{ IOException -> 0x0054 }
            java.net.HttpURLConnection r5 = (java.net.HttpURLConnection) r5     // Catch:{ IOException -> 0x0054 }
            r2 = 10000(0x2710, float:1.4013E-41)
            r5.setConnectTimeout(r2)     // Catch:{ IOException -> 0x0054 }
            r2 = 15000(0x3a98, float:2.102E-41)
            r5.setReadTimeout(r2)     // Catch:{ IOException -> 0x0054 }
            r2 = 1
            java.net.HttpURLConnection.setFollowRedirects(r2)     // Catch:{ IOException -> 0x0054 }
            r5.connect()     // Catch:{ IOException -> 0x0054 }
            java.io.InputStream r5 = r5.getInputStream()     // Catch:{ IOException -> 0x0054 }
            r1 = 1024(0x400, float:1.435E-42)
            byte[] r1 = new byte[r1]     // Catch:{ IOException -> 0x004f, all -> 0x004c }
        L_0x002a:
            int r3 = r5.read(r1)     // Catch:{ IOException -> 0x004f, all -> 0x004c }
            r4 = -1
            if (r3 == r4) goto L_0x003f
            r6.write(r1, r0, r3)     // Catch:{ IOException -> 0x004f, all -> 0x004c }
            if (r7 == 0) goto L_0x002a
            if (r8 == 0) goto L_0x002a
            boolean r3 = b((android.content.Context) r8)     // Catch:{ IOException -> 0x004f, all -> 0x004c }
            if (r3 != 0) goto L_0x002a
            r0 = 1
        L_0x003f:
            r7 = r0 ^ 1
            if (r5 == 0) goto L_0x0046
            r5.close()     // Catch:{ IOException -> 0x0046 }
        L_0x0046:
            if (r6 == 0) goto L_0x004b
            r6.close()     // Catch:{ IOException -> 0x004b }
        L_0x004b:
            return r7
        L_0x004c:
            r7 = move-exception
            r1 = r5
            goto L_0x0076
        L_0x004f:
            r7 = move-exception
            r1 = r5
            goto L_0x0055
        L_0x0052:
            r7 = move-exception
            goto L_0x0076
        L_0x0054:
            r7 = move-exception
        L_0x0055:
            java.lang.String r5 = "common.Network"
            java.lang.StringBuilder r8 = new java.lang.StringBuilder     // Catch:{ all -> 0x0052 }
            r8.<init>()     // Catch:{ all -> 0x0052 }
            java.lang.String r2 = "error while download file"
            r8.append(r2)     // Catch:{ all -> 0x0052 }
            r8.append(r7)     // Catch:{ all -> 0x0052 }
            java.lang.String r7 = r8.toString()     // Catch:{ all -> 0x0052 }
            android.util.Log.e(r5, r7)     // Catch:{ all -> 0x0052 }
            if (r1 == 0) goto L_0x0070
            r1.close()     // Catch:{ IOException -> 0x0070 }
        L_0x0070:
            if (r6 == 0) goto L_0x0075
            r6.close()     // Catch:{ IOException -> 0x0075 }
        L_0x0075:
            return r0
        L_0x0076:
            if (r1 == 0) goto L_0x007b
            r1.close()     // Catch:{ IOException -> 0x007b }
        L_0x007b:
            if (r6 == 0) goto L_0x0080
            r6.close()     // Catch:{ IOException -> 0x0080 }
        L_0x0080:
            throw r7
        */
        throw new UnsupportedOperationException("Method not decompiled: com.xiaomi.smarthome.library.common.network.Network.a(java.lang.String, java.io.OutputStream, boolean, android.content.Context):boolean");
    }

    static final URLConnection b(URL url) {
        return url.openConnection();
    }

    public static boolean a(String str, OutputStream outputStream, Context context) {
        HttpURLConnection httpURLConnection;
        try {
            URL url = new URL(str);
            if (c(context)) {
                HttpURLConnection.setFollowRedirects(false);
                String a2 = a(url);
                String host = url.getHost();
                httpURLConnection = (HttpURLConnection) TraceNetTrafficMonitor.b().b(new URL(a2));
                httpURLConnection.setRequestProperty("X-Online-Host", host);
                int responseCode = httpURLConnection.getResponseCode();
                while (true) {
                    if (responseCode < 300 || responseCode >= 400) {
                        break;
                    }
                    String headerField = httpURLConnection.getHeaderField("location");
                    if (TextUtils.isEmpty(headerField)) {
                        break;
                    }
                    URL url2 = new URL(headerField);
                    String a3 = a(url2);
                    String host2 = url2.getHost();
                    HttpURLConnection httpURLConnection2 = (HttpURLConnection) TraceNetTrafficMonitor.b().b(new URL(a3));
                    httpURLConnection2.setRequestProperty("X-Online-Host", host2);
                    HttpURLConnection httpURLConnection3 = httpURLConnection2;
                    responseCode = httpURLConnection2.getResponseCode();
                    httpURLConnection = httpURLConnection3;
                }
            } else {
                httpURLConnection = (HttpURLConnection) TraceNetTrafficMonitor.b().b(url);
                HttpURLConnection.setFollowRedirects(true);
            }
            httpURLConnection.setConnectTimeout(10000);
            httpURLConnection.setReadTimeout(15000);
            httpURLConnection.connect();
            InputStream inputStream = httpURLConnection.getInputStream();
            byte[] bArr = new byte[1024];
            while (true) {
                int read = inputStream.read(bArr);
                if (read > 0) {
                    outputStream.write(bArr, 0, read);
                } else {
                    inputStream.close();
                    outputStream.close();
                    return true;
                }
            }
        } catch (IOException e2) {
            Log.e(r, "error while download file" + e2);
            return false;
        }
    }

    static final URLConnection c(URL url) {
        return url.openConnection();
    }

    static final URLConnection d(URL url) {
        return url.openConnection();
    }

    static final URLConnection e(URL url) {
        return url.openConnection();
    }

    public static int a(Context context) {
        NetworkInfo activeNetworkInfo;
        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService("connectivity");
        if (connectivityManager == null || (activeNetworkInfo = connectivityManager.getActiveNetworkInfo()) == null) {
            return -1;
        }
        return activeNetworkInfo.getType();
    }

    public static boolean b(Context context) {
        return a(context) == 1;
    }

    public static boolean c(Context context) {
        ConnectivityManager connectivityManager;
        NetworkInfo activeNetworkInfo;
        if (!"CN".equalsIgnoreCase(((TelephonyManager) context.getSystemService("phone")).getSimCountryIso()) || (connectivityManager = (ConnectivityManager) context.getSystemService("connectivity")) == null || (activeNetworkInfo = connectivityManager.getActiveNetworkInfo()) == null) {
            return false;
        }
        String extraInfo = activeNetworkInfo.getExtraInfo();
        if (TextUtils.isEmpty(extraInfo) || extraInfo.length() < 3 || extraInfo.contains("ctwap") || !extraInfo.regionMatches(true, extraInfo.length() - 3, "wap", 0, 3)) {
            return false;
        }
        return true;
    }

    public static boolean d(Context context) {
        ConnectivityManager connectivityManager;
        NetworkInfo activeNetworkInfo;
        if (!"CN".equalsIgnoreCase(((TelephonyManager) context.getSystemService("phone")).getSimCountryIso()) || (connectivityManager = (ConnectivityManager) context.getSystemService("connectivity")) == null || (activeNetworkInfo = connectivityManager.getActiveNetworkInfo()) == null) {
            return false;
        }
        String extraInfo = activeNetworkInfo.getExtraInfo();
        if (TextUtils.isEmpty(extraInfo) || extraInfo.length() < 3 || !extraInfo.contains("ctwap")) {
            return false;
        }
        return true;
    }

    public static HttpURLConnection a(Context context, URL url) throws IOException {
        if (d(context)) {
            return (HttpURLConnection) TraceNetTrafficMonitor.b().b(url, new Proxy(Proxy.Type.HTTP, new InetSocketAddress("10.0.0.200", 80)));
        } else if (!c(context)) {
            return (HttpURLConnection) TraceNetTrafficMonitor.b().b(url);
        } else {
            String host = url.getHost();
            HttpURLConnection httpURLConnection = (HttpURLConnection) TraceNetTrafficMonitor.b().b(new URL(a(url)));
            httpURLConnection.addRequestProperty("X-Online-Host", host);
            return httpURLConnection;
        }
    }

    static final URLConnection a(URL url, Proxy proxy) {
        return url.openConnection(proxy);
    }

    static final URLConnection f(URL url) {
        return url.openConnection();
    }

    static final URLConnection g(URL url) {
        return url.openConnection();
    }

    public static String a(URL url) {
        StringBuilder sb = new StringBuilder();
        sb.append(url.getProtocol());
        sb.append("://");
        sb.append("10.0.0.172");
        sb.append(url.getPath());
        if (!TextUtils.isEmpty(url.getQuery())) {
            sb.append("?");
            sb.append(url.getQuery());
        }
        return sb.toString();
    }

    public static boolean e(Context context) {
        return a(context) >= 0;
    }

    public static class HttpHeaderInfo {

        /* renamed from: a  reason: collision with root package name */
        public int f18636a;
        public String b;
        public String c;
        public String d;
        public Map<String, String> e;

        public String toString() {
            return String.format("resCode = %1$d, headers = %2$s", new Object[]{Integer.valueOf(this.f18636a), this.e.toString()});
        }
    }

    public static final class DoneHandlerInputStream extends FilterInputStream {

        /* renamed from: a  reason: collision with root package name */
        private boolean f18635a;

        public DoneHandlerInputStream(InputStream inputStream) {
            super(inputStream);
        }

        public int read(byte[] bArr, int i, int i2) throws IOException {
            int read;
            if (!this.f18635a && (read = super.read(bArr, i, i2)) != -1) {
                return read;
            }
            this.f18635a = true;
            return -1;
        }
    }
}
