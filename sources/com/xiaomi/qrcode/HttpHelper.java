package com.xiaomi.qrcode;

import android.util.Log;
import com.google.common.net.HttpHeaders;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.net.URLConnection;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;

public final class HttpHelper {

    /* renamed from: a  reason: collision with root package name */
    private static final String f12962a = "HttpHelper";
    private static final Collection<String> b = new HashSet(Arrays.asList(new String[]{"amzn.to", "bit.ly", "bitly.com", "fb.me", "goo.gl", "is.gd", "j.mp", "lnkd.in", "ow.ly", "R.BEETAGG.COM", "r.beetagg.com", "SCN.BY", "su.pr", "t.co", "tinyurl.com", "tr.im"}));

    public enum ContentType {
        HTML,
        JSON,
        XML,
        TEXT
    }

    private HttpHelper() {
    }

    public static CharSequence a(String str, ContentType contentType) throws IOException {
        return a(str, contentType, Integer.MAX_VALUE);
    }

    public static CharSequence a(String str, ContentType contentType, int i) throws IOException {
        String str2;
        switch (contentType) {
            case HTML:
                str2 = "application/xhtml+xml,text/html,text/*,*/*";
                break;
            case JSON:
                str2 = "application/json,text/*,*/*";
                break;
            case XML:
                str2 = "application/xml,text/*,*/*";
                break;
            default:
                str2 = "text/*,*/*";
                break;
        }
        return a(str, str2, i);
    }

    /* JADX INFO: finally extract failed */
    private static CharSequence a(String str, String str2, int i) throws IOException {
        int i2 = 0;
        while (i2 < 5) {
            HttpURLConnection a2 = a(new URL(str));
            a2.setInstanceFollowRedirects(true);
            a2.setRequestProperty(HttpHeaders.ACCEPT, str2);
            a2.setRequestProperty(HttpHeaders.ACCEPT_CHARSET, "utf-8,*");
            a2.setRequestProperty("User-Agent", "ZXing (Android)");
            try {
                int a3 = a(a2);
                if (a3 == 200) {
                    CharSequence a4 = a((URLConnection) a2, i);
                    a2.disconnect();
                    return a4;
                } else if (a3 == 302) {
                    String headerField = a2.getHeaderField("Location");
                    if (headerField != null) {
                        i2++;
                        a2.disconnect();
                        str = headerField;
                    } else {
                        throw new IOException("No Location");
                    }
                } else {
                    throw new IOException("Bad HTTP response: " + a3);
                }
            } catch (Throwable th) {
                a2.disconnect();
                throw th;
            }
        }
        throw new IOException("Too many redirects");
    }

    /* JADX WARNING: Code restructure failed: missing block: B:2:0x0008, code lost:
        r0 = r2.indexOf("charset=");
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static java.lang.String a(java.net.URLConnection r2) {
        /*
            java.lang.String r0 = "Content-Type"
            java.lang.String r2 = r2.getHeaderField(r0)
            if (r2 == 0) goto L_0x001c
            java.lang.String r0 = "charset="
            int r0 = r2.indexOf(r0)
            if (r0 < 0) goto L_0x001c
            java.lang.String r1 = "charset="
            int r1 = r1.length()
            int r0 = r0 + r1
            java.lang.String r2 = r2.substring(r0)
            return r2
        L_0x001c:
            java.lang.String r2 = "UTF-8"
            return r2
        */
        throw new UnsupportedOperationException("Method not decompiled: com.xiaomi.qrcode.HttpHelper.a(java.net.URLConnection):java.lang.String");
    }

    /* JADX WARNING: Removed duplicated region for block: B:19:0x0032 A[SYNTHETIC, Splitter:B:19:0x0032] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static java.lang.CharSequence a(java.net.URLConnection r4, int r5) throws java.io.IOException {
        /*
            java.lang.String r0 = a((java.net.URLConnection) r4)
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            r1.<init>()
            r2 = 0
            java.io.InputStreamReader r3 = new java.io.InputStreamReader     // Catch:{ all -> 0x002e }
            java.io.InputStream r4 = r4.getInputStream()     // Catch:{ all -> 0x002e }
            r3.<init>(r4, r0)     // Catch:{ all -> 0x002e }
            r4 = 1024(0x400, float:1.435E-42)
            char[] r4 = new char[r4]     // Catch:{ all -> 0x002c }
        L_0x0017:
            int r0 = r1.length()     // Catch:{ all -> 0x002c }
            if (r0 >= r5) goto L_0x0028
            int r0 = r3.read(r4)     // Catch:{ all -> 0x002c }
            if (r0 <= 0) goto L_0x0028
            r2 = 0
            r1.append(r4, r2, r0)     // Catch:{ all -> 0x002c }
            goto L_0x0017
        L_0x0028:
            r3.close()     // Catch:{ IOException | NullPointerException -> 0x002b }
        L_0x002b:
            return r1
        L_0x002c:
            r4 = move-exception
            goto L_0x0030
        L_0x002e:
            r4 = move-exception
            r3 = r2
        L_0x0030:
            if (r3 == 0) goto L_0x0035
            r3.close()     // Catch:{ IOException | NullPointerException -> 0x0035 }
        L_0x0035:
            throw r4
        */
        throw new UnsupportedOperationException("Method not decompiled: com.xiaomi.qrcode.HttpHelper.a(java.net.URLConnection, int):java.lang.CharSequence");
    }

    public static URI a(URI uri) throws IOException {
        if (!b.contains(uri.getHost())) {
            return uri;
        }
        HttpURLConnection a2 = a(uri.toURL());
        a2.setInstanceFollowRedirects(false);
        a2.setDoInput(false);
        a2.setRequestMethod("HEAD");
        a2.setRequestProperty("User-Agent", "ZXing (Android)");
        try {
            int a3 = a(a2);
            if (a3 != 307) {
                switch (a3) {
                    case 300:
                    case 301:
                    case 302:
                    case 303:
                        break;
                }
            }
            String headerField = a2.getHeaderField("Location");
            if (headerField != null) {
                try {
                    URI uri2 = new URI(headerField);
                    a2.disconnect();
                    return uri2;
                } catch (URISyntaxException unused) {
                }
            }
            return uri;
        } finally {
            a2.disconnect();
        }
    }

    private static HttpURLConnection a(URL url) throws IOException {
        try {
            URLConnection openConnection = url.openConnection();
            if (openConnection instanceof HttpURLConnection) {
                return (HttpURLConnection) openConnection;
            }
            throw new IOException();
        } catch (NullPointerException e) {
            String str = f12962a;
            Log.w(str, "Bad URI? " + url);
            throw new IOException(e);
        }
    }

    private static int a(HttpURLConnection httpURLConnection) throws IOException {
        try {
            httpURLConnection.connect();
            try {
                return httpURLConnection.getResponseCode();
            } catch (IllegalArgumentException | NullPointerException | StringIndexOutOfBoundsException e) {
                throw new IOException(e);
            }
        } catch (IllegalArgumentException | IndexOutOfBoundsException | NullPointerException | SecurityException e2) {
            throw new IOException(e2);
        }
    }
}
