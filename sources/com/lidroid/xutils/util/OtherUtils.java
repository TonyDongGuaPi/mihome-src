package com.lidroid.xutils.util;

import android.os.StatFs;
import android.text.TextUtils;
import com.google.common.net.HttpHeaders;
import java.io.File;
import java.io.UnsupportedEncodingException;
import java.security.SecureRandom;
import java.security.cert.X509Certificate;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.KeyManager;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;
import org.apache.http.Header;
import org.apache.http.HeaderElement;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.cybergarage.http.HTTP;

public class OtherUtils {

    /* renamed from: a  reason: collision with root package name */
    private static final int f6369a = 100;
    private static SSLSocketFactory b;

    private OtherUtils() {
    }

    /* JADX WARNING: Removed duplicated region for block: B:12:0x0039  */
    /* JADX WARNING: Removed duplicated region for block: B:13:0x003d  */
    /* JADX WARNING: Removed duplicated region for block: B:16:0x004d  */
    /* JADX WARNING: Removed duplicated region for block: B:19:0x0067  */
    /* JADX WARNING: Removed duplicated region for block: B:22:0x0076  */
    /* JADX WARNING: Removed duplicated region for block: B:27:0x008e  */
    /* JADX WARNING: Removed duplicated region for block: B:9:0x0026  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static java.lang.String a(android.content.Context r4) {
        /*
            r0 = 0
            if (r4 == 0) goto L_0x001f
            java.lang.String r1 = "com.android.internal.R$string"
            java.lang.Class r1 = java.lang.Class.forName(r1)     // Catch:{ Throwable -> 0x001f }
            java.lang.String r2 = "web_user_agent"
            java.lang.reflect.Field r1 = r1.getDeclaredField(r2)     // Catch:{ Throwable -> 0x001f }
            java.lang.Object r1 = r1.get(r0)     // Catch:{ Throwable -> 0x001f }
            java.lang.Integer r1 = (java.lang.Integer) r1     // Catch:{ Throwable -> 0x001f }
            int r1 = r1.intValue()     // Catch:{ Throwable -> 0x001f }
            java.lang.String r4 = r4.getString(r1)     // Catch:{ Throwable -> 0x001f }
            goto L_0x0020
        L_0x001f:
            r4 = r0
        L_0x0020:
            boolean r0 = android.text.TextUtils.isEmpty(r4)
            if (r0 == 0) goto L_0x0028
            java.lang.String r4 = "Mozilla/5.0 (Linux; U; Android %s) AppleWebKit/533.1 (KHTML, like Gecko) Version/4.0 %sSafari/533.1"
        L_0x0028:
            java.util.Locale r0 = java.util.Locale.getDefault()
            java.lang.StringBuffer r1 = new java.lang.StringBuffer
            r1.<init>()
            java.lang.String r2 = android.os.Build.VERSION.RELEASE
            int r3 = r2.length()
            if (r3 <= 0) goto L_0x003d
            r1.append(r2)
            goto L_0x0042
        L_0x003d:
            java.lang.String r2 = "1.0"
            r1.append(r2)
        L_0x0042:
            java.lang.String r2 = "; "
            r1.append(r2)
            java.lang.String r2 = r0.getLanguage()
            if (r2 == 0) goto L_0x0067
            java.lang.String r2 = r2.toLowerCase()
            r1.append(r2)
            java.lang.String r0 = r0.getCountry()
            if (r0 == 0) goto L_0x006c
            java.lang.String r2 = "-"
            r1.append(r2)
            java.lang.String r0 = r0.toLowerCase()
            r1.append(r0)
            goto L_0x006c
        L_0x0067:
            java.lang.String r0 = "en"
            r1.append(r0)
        L_0x006c:
            java.lang.String r0 = "REL"
            java.lang.String r2 = android.os.Build.VERSION.CODENAME
            boolean r0 = r0.equals(r2)
            if (r0 == 0) goto L_0x0086
            java.lang.String r0 = android.os.Build.MODEL
            int r2 = r0.length()
            if (r2 <= 0) goto L_0x0086
            java.lang.String r2 = "; "
            r1.append(r2)
            r1.append(r0)
        L_0x0086:
            java.lang.String r0 = android.os.Build.ID
            int r2 = r0.length()
            if (r2 <= 0) goto L_0x0096
            java.lang.String r2 = " Build/"
            r1.append(r2)
            r1.append(r0)
        L_0x0096:
            r0 = 2
            java.lang.Object[] r0 = new java.lang.Object[r0]
            r2 = 0
            r0[r2] = r1
            r1 = 1
            java.lang.String r2 = "Mobile "
            r0[r1] = r2
            java.lang.String r4 = java.lang.String.format(r4, r0)
            return r4
        */
        throw new UnsupportedOperationException("Method not decompiled: com.lidroid.xutils.util.OtherUtils.a(android.content.Context):java.lang.String");
    }

    /* JADX WARNING: Code restructure failed: missing block: B:2:0x000c, code lost:
        r0 = r2.getExternalCacheDir();
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static java.lang.String a(android.content.Context r2, java.lang.String r3) {
        /*
            java.lang.String r0 = "mounted"
            java.lang.String r1 = android.os.Environment.getExternalStorageState()
            boolean r0 = r0.equals(r1)
            if (r0 == 0) goto L_0x0017
            java.io.File r0 = r2.getExternalCacheDir()
            if (r0 == 0) goto L_0x0017
            java.lang.String r0 = r0.getPath()
            goto L_0x0018
        L_0x0017:
            r0 = 0
        L_0x0018:
            if (r0 != 0) goto L_0x002a
            java.io.File r2 = r2.getCacheDir()
            if (r2 == 0) goto L_0x002a
            boolean r1 = r2.exists()
            if (r1 == 0) goto L_0x002a
            java.lang.String r0 = r2.getPath()
        L_0x002a:
            java.lang.StringBuilder r2 = new java.lang.StringBuilder
            java.lang.String r0 = java.lang.String.valueOf(r0)
            r2.<init>(r0)
            java.lang.String r0 = java.io.File.separator
            r2.append(r0)
            r2.append(r3)
            java.lang.String r2 = r2.toString()
            return r2
        */
        throw new UnsupportedOperationException("Method not decompiled: com.lidroid.xutils.util.OtherUtils.a(android.content.Context, java.lang.String):java.lang.String");
    }

    public static long a(File file) {
        try {
            StatFs statFs = new StatFs(file.getPath());
            return ((long) statFs.getBlockSize()) * ((long) statFs.getAvailableBlocks());
        } catch (Throwable th) {
            LogUtils.b(th.getMessage(), th);
            return -1;
        }
    }

    public static boolean a(HttpResponse httpResponse) {
        String value;
        if (httpResponse == null) {
            return false;
        }
        Header firstHeader = httpResponse.getFirstHeader(HttpHeaders.ACCEPT_RANGES);
        if (firstHeader != null) {
            return HTTP.CONTENT_RANGE_BYTES.equals(firstHeader.getValue());
        }
        Header firstHeader2 = httpResponse.getFirstHeader("Content-Range");
        if (firstHeader2 == null || (value = firstHeader2.getValue()) == null || !value.startsWith(HTTP.CONTENT_RANGE_BYTES)) {
            return false;
        }
        return true;
    }

    public static String b(HttpResponse httpResponse) {
        Header firstHeader;
        if (httpResponse == null || (firstHeader = httpResponse.getFirstHeader("Content-Disposition")) == null) {
            return null;
        }
        for (HeaderElement parameterByName : firstHeader.getElements()) {
            NameValuePair parameterByName2 = parameterByName.getParameterByName("filename");
            if (parameterByName2 != null) {
                String value = parameterByName2.getValue();
                return CharsetUtils.a(value, "UTF-8", value.length());
            }
        }
        return null;
    }

    /* JADX WARNING: Removed duplicated region for block: B:14:0x002f A[SYNTHETIC, Splitter:B:14:0x002f] */
    /* JADX WARNING: Removed duplicated region for block: B:19:0x0036  */
    /* JADX WARNING: Removed duplicated region for block: B:23:? A[RETURN, SYNTHETIC] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static java.nio.charset.Charset a(org.apache.http.client.methods.HttpRequestBase r6) {
        /*
            r0 = 0
            if (r6 != 0) goto L_0x0004
            return r0
        L_0x0004:
            java.lang.String r1 = "Content-Type"
            org.apache.http.Header r6 = r6.getFirstHeader(r1)
            r1 = 0
            if (r6 == 0) goto L_0x0028
            org.apache.http.HeaderElement[] r6 = r6.getElements()
            int r2 = r6.length
            r3 = 0
        L_0x0013:
            if (r3 < r2) goto L_0x0016
            goto L_0x0028
        L_0x0016:
            r4 = r6[r3]
            java.lang.String r5 = "charset"
            org.apache.http.NameValuePair r4 = r4.getParameterByName(r5)
            if (r4 == 0) goto L_0x0025
            java.lang.String r6 = r4.getValue()
            goto L_0x0029
        L_0x0025:
            int r3 = r3 + 1
            goto L_0x0013
        L_0x0028:
            r6 = r0
        L_0x0029:
            boolean r2 = android.text.TextUtils.isEmpty(r6)
            if (r2 != 0) goto L_0x0034
            boolean r2 = java.nio.charset.Charset.isSupported(r6)     // Catch:{ Throwable -> 0x0034 }
            r1 = r2
        L_0x0034:
            if (r1 == 0) goto L_0x003a
            java.nio.charset.Charset r0 = java.nio.charset.Charset.forName(r6)
        L_0x003a:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.lidroid.xutils.util.OtherUtils.a(org.apache.http.client.methods.HttpRequestBase):java.nio.charset.Charset");
    }

    public static long a(String str, String str2) throws UnsupportedEncodingException {
        long j = 0;
        if (TextUtils.isEmpty(str)) {
            return 0;
        }
        int length = str.length();
        if (length < 100) {
            return (long) str.getBytes(str2).length;
        }
        int i = 0;
        while (i < length) {
            int i2 = i + 100;
            j += (long) a(str, i, i2 < length ? i2 : length).getBytes(str2).length;
            i = i2;
        }
        return j;
    }

    public static String a(String str, int i, int i2) {
        return new String(str.substring(i, i2));
    }

    public static StackTraceElement a() {
        return Thread.currentThread().getStackTrace()[3];
    }

    public static StackTraceElement b() {
        return Thread.currentThread().getStackTrace()[4];
    }

    public static void c() {
        if (b == null) {
            TrustManager[] trustManagerArr = {new X509TrustManager() {
                public void checkClientTrusted(X509Certificate[] x509CertificateArr, String str) {
                }

                public void checkServerTrusted(X509Certificate[] x509CertificateArr, String str) {
                }

                public X509Certificate[] getAcceptedIssuers() {
                    return null;
                }
            }};
            try {
                SSLContext instance = SSLContext.getInstance("TLS");
                instance.init((KeyManager[]) null, trustManagerArr, (SecureRandom) null);
                b = instance.getSocketFactory();
            } catch (Throwable th) {
                LogUtils.b(th.getMessage(), th);
            }
        }
        if (b != null) {
            HttpsURLConnection.setDefaultSSLSocketFactory(b);
            HttpsURLConnection.setDefaultHostnameVerifier(org.apache.http.conn.ssl.SSLSocketFactory.ALLOW_ALL_HOSTNAME_VERIFIER);
        }
    }
}
