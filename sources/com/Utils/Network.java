package com.Utils;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.telephony.TelephonyManager;
import android.text.TextUtils;
import android.util.Log;
import java.io.FilterInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.InetSocketAddress;
import java.net.Proxy;
import java.net.URL;
import java.util.Map;
import java.util.regex.Pattern;

public class Network {

    /* renamed from: a  reason: collision with root package name */
    public static final String f665a = "Mozilla/5.0 (Windows; U; Windows NT 6.1; en-US) AppleWebKit/534.3 (KHTML, like Gecko) Chrome/6.0.464.0 Safari/534.3";
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

    public static boolean a(String str, OutputStream outputStream) {
        return a(str, outputStream, false, (Context) null);
    }

    /* JADX WARNING: Removed duplicated region for block: B:34:0x0069 A[SYNTHETIC, Splitter:B:34:0x0069] */
    /* JADX WARNING: Removed duplicated region for block: B:38:0x006e A[SYNTHETIC, Splitter:B:38:0x006e] */
    /* JADX WARNING: Removed duplicated region for block: B:43:0x0074 A[SYNTHETIC, Splitter:B:43:0x0074] */
    /* JADX WARNING: Removed duplicated region for block: B:47:0x0079 A[SYNTHETIC, Splitter:B:47:0x0079] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static boolean a(java.lang.String r5, java.io.OutputStream r6, boolean r7, android.content.Context r8) {
        /*
            r0 = 0
            r1 = 0
            java.net.URL r2 = new java.net.URL     // Catch:{ IOException -> 0x0050 }
            r2.<init>(r5)     // Catch:{ IOException -> 0x0050 }
            java.net.URLConnection r5 = r2.openConnection()     // Catch:{ IOException -> 0x0050 }
            java.net.HttpURLConnection r5 = (java.net.HttpURLConnection) r5     // Catch:{ IOException -> 0x0050 }
            r2 = 10000(0x2710, float:1.4013E-41)
            r5.setConnectTimeout(r2)     // Catch:{ IOException -> 0x0050 }
            r2 = 15000(0x3a98, float:2.102E-41)
            r5.setReadTimeout(r2)     // Catch:{ IOException -> 0x0050 }
            r2 = 1
            java.net.HttpURLConnection.setFollowRedirects(r2)     // Catch:{ IOException -> 0x0050 }
            r5.connect()     // Catch:{ IOException -> 0x0050 }
            java.io.InputStream r5 = r5.getInputStream()     // Catch:{ IOException -> 0x0050 }
            r1 = 1024(0x400, float:1.435E-42)
            byte[] r1 = new byte[r1]     // Catch:{ IOException -> 0x004b, all -> 0x0048 }
        L_0x0026:
            int r3 = r5.read(r1)     // Catch:{ IOException -> 0x004b, all -> 0x0048 }
            r4 = -1
            if (r3 == r4) goto L_0x003b
            r6.write(r1, r0, r3)     // Catch:{ IOException -> 0x004b, all -> 0x0048 }
            if (r7 == 0) goto L_0x0026
            if (r8 == 0) goto L_0x0026
            boolean r3 = b(r8)     // Catch:{ IOException -> 0x004b, all -> 0x0048 }
            if (r3 != 0) goto L_0x0026
            r0 = 1
        L_0x003b:
            r7 = r0 ^ 1
            if (r5 == 0) goto L_0x0042
            r5.close()     // Catch:{ IOException -> 0x0042 }
        L_0x0042:
            if (r6 == 0) goto L_0x0047
            r6.close()     // Catch:{ IOException -> 0x0047 }
        L_0x0047:
            return r7
        L_0x0048:
            r7 = move-exception
            r1 = r5
            goto L_0x0072
        L_0x004b:
            r7 = move-exception
            r1 = r5
            goto L_0x0051
        L_0x004e:
            r7 = move-exception
            goto L_0x0072
        L_0x0050:
            r7 = move-exception
        L_0x0051:
            java.lang.String r5 = "common.Network"
            java.lang.StringBuilder r8 = new java.lang.StringBuilder     // Catch:{ all -> 0x004e }
            r8.<init>()     // Catch:{ all -> 0x004e }
            java.lang.String r2 = "error while download file"
            r8.append(r2)     // Catch:{ all -> 0x004e }
            r8.append(r7)     // Catch:{ all -> 0x004e }
            java.lang.String r7 = r8.toString()     // Catch:{ all -> 0x004e }
            android.util.Log.e(r5, r7)     // Catch:{ all -> 0x004e }
            if (r1 == 0) goto L_0x006c
            r1.close()     // Catch:{ IOException -> 0x006c }
        L_0x006c:
            if (r6 == 0) goto L_0x0071
            r6.close()     // Catch:{ IOException -> 0x0071 }
        L_0x0071:
            return r0
        L_0x0072:
            if (r1 == 0) goto L_0x0077
            r1.close()     // Catch:{ IOException -> 0x0077 }
        L_0x0077:
            if (r6 == 0) goto L_0x007c
            r6.close()     // Catch:{ IOException -> 0x007c }
        L_0x007c:
            throw r7
        */
        throw new UnsupportedOperationException("Method not decompiled: com.Utils.Network.a(java.lang.String, java.io.OutputStream, boolean, android.content.Context):boolean");
    }

    public static boolean a(String str, OutputStream outputStream, Context context) {
        HttpURLConnection httpURLConnection;
        try {
            URL url = new URL(str);
            if (c(context)) {
                HttpURLConnection.setFollowRedirects(false);
                String a2 = a(url);
                String host = url.getHost();
                httpURLConnection = (HttpURLConnection) new URL(a2).openConnection();
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
                    HttpURLConnection httpURLConnection2 = (HttpURLConnection) new URL(a3).openConnection();
                    httpURLConnection2.setRequestProperty("X-Online-Host", host2);
                    HttpURLConnection httpURLConnection3 = httpURLConnection2;
                    responseCode = httpURLConnection2.getResponseCode();
                    httpURLConnection = httpURLConnection3;
                }
            } else {
                httpURLConnection = (HttpURLConnection) url.openConnection();
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

    public static int a(Context context) {
        NetworkInfo activeNetworkInfo;
        ConnectivityManager connectivityManager = (ConnectivityManager) context.getApplicationContext().getSystemService("connectivity");
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
        if (!"CN".equalsIgnoreCase(((TelephonyManager) context.getSystemService("phone")).getSimCountryIso()) || (connectivityManager = (ConnectivityManager) context.getApplicationContext().getSystemService("connectivity")) == null || (activeNetworkInfo = connectivityManager.getActiveNetworkInfo()) == null) {
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
        if (!"CN".equalsIgnoreCase(((TelephonyManager) context.getApplicationContext().getSystemService("phone")).getSimCountryIso()) || (connectivityManager = (ConnectivityManager) context.getApplicationContext().getSystemService("connectivity")) == null || (activeNetworkInfo = connectivityManager.getActiveNetworkInfo()) == null) {
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
            return (HttpURLConnection) url.openConnection(new Proxy(Proxy.Type.HTTP, new InetSocketAddress("10.0.0.200", 80)));
        }
        if (!c(context)) {
            return (HttpURLConnection) url.openConnection();
        }
        String host = url.getHost();
        HttpURLConnection httpURLConnection = (HttpURLConnection) new URL(a(url)).openConnection();
        httpURLConnection.addRequestProperty("X-Online-Host", host);
        return httpURLConnection;
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
        public int f667a;
        public String b;
        public String c;
        public String d;
        public Map<String, String> e;

        public String toString() {
            return String.format("resCode = %1$d, headers = %2$s", new Object[]{Integer.valueOf(this.f667a), this.e.toString()});
        }
    }

    public static final class DoneHandlerInputStream extends FilterInputStream {

        /* renamed from: a  reason: collision with root package name */
        private boolean f666a;

        public DoneHandlerInputStream(InputStream inputStream) {
            super(inputStream);
        }

        public int read(byte[] bArr, int i, int i2) throws IOException {
            int read;
            if (!this.f666a && (read = super.read(bArr, i, i2)) != -1) {
                return read;
            }
            this.f666a = true;
            return -1;
        }
    }
}
