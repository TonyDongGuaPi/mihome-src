package com.xiaomi.account.auth;

import android.content.Context;
import com.xiaomi.account.openauth.XMAuthericationException;
import com.xiaomi.account.openauth.XiaomiOAuthConstants;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.List;
import org.apache.http.NameValuePair;
import org.apache.http.client.utils.URLEncodedUtils;

public class AuthorizeApi {
    private static final int CONNECTION_TIMEOUT = 10000;
    private static final String HOST = XiaomiOAuthConstants.OAUTH2_API_HOST;
    private static final String METHOD_GET = "GET";
    private static final String OAUTH_URL_BASE = XiaomiOAuthConstants.OAUTH2_API_URL_BASE;
    private static final int READ_TIMEOUT = 15000;
    private static final String UTF8 = "UTF-8";

    @Deprecated
    public static String doHttpGet(Context context, String str, long j, String str2) throws XMAuthericationException {
        return doHttpGet(str, j, str2, (String) null, (String) null);
    }

    public static String doHttpGet(String str, long j, String str2) throws XMAuthericationException {
        return doHttpGet(str, j, str2, (String) null, (String) null);
    }

    @Deprecated
    public static String doHttpGet(Context context, String str, long j, String str2, String str3, String str4) throws XMAuthericationException {
        return doHttpGet(str, j, str2, str3, str4);
    }

    /* JADX WARNING: Code restructure failed: missing block: B:37:0x00c5, code lost:
        r3 = e;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:41:0x00cc, code lost:
        r3 = e;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:44:0x00d3, code lost:
        r3 = e;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:47:0x00da, code lost:
        r3 = e;
     */
    /* JADX WARNING: Failed to process nested try/catch */
    /* JADX WARNING: Missing exception handler attribute for start block: B:11:0x0080 */
    /* JADX WARNING: Removed duplicated region for block: B:37:0x00c5 A[ExcHandler: InvalidKeyException (e java.security.InvalidKeyException), Splitter:B:1:0x001e] */
    /* JADX WARNING: Removed duplicated region for block: B:41:0x00cc A[Catch:{ IOException -> 0x0080, UnsupportedEncodingException -> 0x00da, NoSuchAlgorithmException -> 0x00cc, InvalidKeyException -> 0x00c5, all -> 0x00c3 }, ExcHandler: NoSuchAlgorithmException (e java.security.NoSuchAlgorithmException), Splitter:B:1:0x001e] */
    /* JADX WARNING: Removed duplicated region for block: B:47:0x00da A[Catch:{ IOException -> 0x0080, UnsupportedEncodingException -> 0x00da, NoSuchAlgorithmException -> 0x00cc, InvalidKeyException -> 0x00c5, all -> 0x00c3 }, ExcHandler: UnsupportedEncodingException (e java.io.UnsupportedEncodingException), Splitter:B:1:0x001e] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static java.lang.String doHttpGet(java.lang.String r3, long r4, java.lang.String r6, java.lang.String r7, java.lang.String r8) throws com.xiaomi.account.openauth.XMAuthericationException {
        /*
            java.util.ArrayList r0 = new java.util.ArrayList
            r0.<init>()
            org.apache.http.message.BasicNameValuePair r1 = new org.apache.http.message.BasicNameValuePair
            java.lang.String r2 = "clientId"
            java.lang.String r4 = java.lang.String.valueOf(r4)
            r1.<init>(r2, r4)
            r0.add(r1)
            org.apache.http.message.BasicNameValuePair r4 = new org.apache.http.message.BasicNameValuePair
            java.lang.String r5 = "token"
            r4.<init>(r5, r6)
            r0.add(r4)
            r4 = 0
            java.net.URL r5 = new java.net.URL     // Catch:{ UnsupportedEncodingException -> 0x00da, IOException -> 0x00d3, NoSuchAlgorithmException -> 0x00cc, InvalidKeyException -> 0x00c5 }
            java.lang.StringBuilder r1 = new java.lang.StringBuilder     // Catch:{ UnsupportedEncodingException -> 0x00da, IOException -> 0x00d3, NoSuchAlgorithmException -> 0x00cc, InvalidKeyException -> 0x00c5 }
            r1.<init>()     // Catch:{ UnsupportedEncodingException -> 0x00da, IOException -> 0x00d3, NoSuchAlgorithmException -> 0x00cc, InvalidKeyException -> 0x00c5 }
            java.lang.String r2 = OAUTH_URL_BASE     // Catch:{ UnsupportedEncodingException -> 0x00da, IOException -> 0x00d3, NoSuchAlgorithmException -> 0x00cc, InvalidKeyException -> 0x00c5 }
            r1.append(r2)     // Catch:{ UnsupportedEncodingException -> 0x00da, IOException -> 0x00d3, NoSuchAlgorithmException -> 0x00cc, InvalidKeyException -> 0x00c5 }
            r1.append(r3)     // Catch:{ UnsupportedEncodingException -> 0x00da, IOException -> 0x00d3, NoSuchAlgorithmException -> 0x00cc, InvalidKeyException -> 0x00c5 }
            java.lang.String r1 = r1.toString()     // Catch:{ UnsupportedEncodingException -> 0x00da, IOException -> 0x00d3, NoSuchAlgorithmException -> 0x00cc, InvalidKeyException -> 0x00c5 }
            java.lang.String r1 = com.xiaomi.account.auth.AuthorizeHelper.generateUrl(r1, r0)     // Catch:{ UnsupportedEncodingException -> 0x00da, IOException -> 0x00d3, NoSuchAlgorithmException -> 0x00cc, InvalidKeyException -> 0x00c5 }
            r5.<init>(r1)     // Catch:{ UnsupportedEncodingException -> 0x00da, IOException -> 0x00d3, NoSuchAlgorithmException -> 0x00cc, InvalidKeyException -> 0x00c5 }
            r1 = 1
            java.net.HttpURLConnection.setFollowRedirects(r1)     // Catch:{ UnsupportedEncodingException -> 0x00da, IOException -> 0x00d3, NoSuchAlgorithmException -> 0x00cc, InvalidKeyException -> 0x00c5 }
            java.net.URLConnection r5 = r5.openConnection()     // Catch:{ UnsupportedEncodingException -> 0x00da, IOException -> 0x00d3, NoSuchAlgorithmException -> 0x00cc, InvalidKeyException -> 0x00c5 }
            java.net.HttpURLConnection r5 = (java.net.HttpURLConnection) r5     // Catch:{ UnsupportedEncodingException -> 0x00da, IOException -> 0x00d3, NoSuchAlgorithmException -> 0x00cc, InvalidKeyException -> 0x00c5 }
            r5.setDoInput(r1)     // Catch:{ UnsupportedEncodingException -> 0x00da, IOException -> 0x00d3, NoSuchAlgorithmException -> 0x00cc, InvalidKeyException -> 0x00c5 }
            java.lang.String r1 = "GET"
            r5.setRequestMethod(r1)     // Catch:{ UnsupportedEncodingException -> 0x00da, IOException -> 0x00d3, NoSuchAlgorithmException -> 0x00cc, InvalidKeyException -> 0x00c5 }
            r1 = 10000(0x2710, float:1.4013E-41)
            r5.setConnectTimeout(r1)     // Catch:{ UnsupportedEncodingException -> 0x00da, IOException -> 0x00d3, NoSuchAlgorithmException -> 0x00cc, InvalidKeyException -> 0x00c5 }
            r1 = 15000(0x3a98, float:2.102E-41)
            r5.setReadTimeout(r1)     // Catch:{ UnsupportedEncodingException -> 0x00da, IOException -> 0x00d3, NoSuchAlgorithmException -> 0x00cc, InvalidKeyException -> 0x00c5 }
            java.util.HashMap r3 = makeHeaders(r3, r6, r7, r8, r0)     // Catch:{ UnsupportedEncodingException -> 0x00da, IOException -> 0x00d3, NoSuchAlgorithmException -> 0x00cc, InvalidKeyException -> 0x00c5 }
            if (r3 == 0) goto L_0x0078
            java.util.Set r6 = r3.keySet()     // Catch:{ UnsupportedEncodingException -> 0x00da, IOException -> 0x00d3, NoSuchAlgorithmException -> 0x00cc, InvalidKeyException -> 0x00c5 }
            java.util.Iterator r6 = r6.iterator()     // Catch:{ UnsupportedEncodingException -> 0x00da, IOException -> 0x00d3, NoSuchAlgorithmException -> 0x00cc, InvalidKeyException -> 0x00c5 }
        L_0x0062:
            boolean r7 = r6.hasNext()     // Catch:{ UnsupportedEncodingException -> 0x00da, IOException -> 0x00d3, NoSuchAlgorithmException -> 0x00cc, InvalidKeyException -> 0x00c5 }
            if (r7 == 0) goto L_0x0078
            java.lang.Object r7 = r6.next()     // Catch:{ UnsupportedEncodingException -> 0x00da, IOException -> 0x00d3, NoSuchAlgorithmException -> 0x00cc, InvalidKeyException -> 0x00c5 }
            java.lang.String r7 = (java.lang.String) r7     // Catch:{ UnsupportedEncodingException -> 0x00da, IOException -> 0x00d3, NoSuchAlgorithmException -> 0x00cc, InvalidKeyException -> 0x00c5 }
            java.lang.Object r8 = r3.get(r7)     // Catch:{ UnsupportedEncodingException -> 0x00da, IOException -> 0x00d3, NoSuchAlgorithmException -> 0x00cc, InvalidKeyException -> 0x00c5 }
            java.lang.String r8 = (java.lang.String) r8     // Catch:{ UnsupportedEncodingException -> 0x00da, IOException -> 0x00d3, NoSuchAlgorithmException -> 0x00cc, InvalidKeyException -> 0x00c5 }
            r5.setRequestProperty(r7, r8)     // Catch:{ UnsupportedEncodingException -> 0x00da, IOException -> 0x00d3, NoSuchAlgorithmException -> 0x00cc, InvalidKeyException -> 0x00c5 }
            goto L_0x0062
        L_0x0078:
            r5.connect()     // Catch:{ UnsupportedEncodingException -> 0x00da, IOException -> 0x00d3, NoSuchAlgorithmException -> 0x00cc, InvalidKeyException -> 0x00c5 }
            java.io.InputStream r3 = r5.getInputStream()     // Catch:{ IOException -> 0x0080, UnsupportedEncodingException -> 0x00da, NoSuchAlgorithmException -> 0x00cc, InvalidKeyException -> 0x00c5 }
            goto L_0x0084
        L_0x0080:
            java.io.InputStream r3 = r5.getErrorStream()     // Catch:{ UnsupportedEncodingException -> 0x00da, IOException -> 0x00d3, NoSuchAlgorithmException -> 0x00cc, InvalidKeyException -> 0x00c5 }
        L_0x0084:
            if (r3 == 0) goto L_0x00b8
            java.io.BufferedReader r5 = new java.io.BufferedReader     // Catch:{ UnsupportedEncodingException -> 0x00da, IOException -> 0x00d3, NoSuchAlgorithmException -> 0x00cc, InvalidKeyException -> 0x00c5 }
            java.io.InputStreamReader r6 = new java.io.InputStreamReader     // Catch:{ UnsupportedEncodingException -> 0x00da, IOException -> 0x00d3, NoSuchAlgorithmException -> 0x00cc, InvalidKeyException -> 0x00c5 }
            r6.<init>(r3)     // Catch:{ UnsupportedEncodingException -> 0x00da, IOException -> 0x00d3, NoSuchAlgorithmException -> 0x00cc, InvalidKeyException -> 0x00c5 }
            r3 = 1024(0x400, float:1.435E-42)
            r5.<init>(r6, r3)     // Catch:{ UnsupportedEncodingException -> 0x00da, IOException -> 0x00d3, NoSuchAlgorithmException -> 0x00cc, InvalidKeyException -> 0x00c5 }
            java.lang.StringBuilder r3 = new java.lang.StringBuilder     // Catch:{ UnsupportedEncodingException -> 0x00b5, IOException -> 0x00b2, NoSuchAlgorithmException -> 0x00af, InvalidKeyException -> 0x00ac, all -> 0x00a9 }
            r3.<init>()     // Catch:{ UnsupportedEncodingException -> 0x00b5, IOException -> 0x00b2, NoSuchAlgorithmException -> 0x00af, InvalidKeyException -> 0x00ac, all -> 0x00a9 }
        L_0x0097:
            java.lang.String r4 = r5.readLine()     // Catch:{ UnsupportedEncodingException -> 0x00b5, IOException -> 0x00b2, NoSuchAlgorithmException -> 0x00af, InvalidKeyException -> 0x00ac, all -> 0x00a9 }
            if (r4 == 0) goto L_0x00a1
            r3.append(r4)     // Catch:{ UnsupportedEncodingException -> 0x00b5, IOException -> 0x00b2, NoSuchAlgorithmException -> 0x00af, InvalidKeyException -> 0x00ac, all -> 0x00a9 }
            goto L_0x0097
        L_0x00a1:
            java.lang.String r3 = r3.toString()     // Catch:{ UnsupportedEncodingException -> 0x00b5, IOException -> 0x00b2, NoSuchAlgorithmException -> 0x00af, InvalidKeyException -> 0x00ac, all -> 0x00a9 }
            closeQuietly(r5)
            return r3
        L_0x00a9:
            r3 = move-exception
            r4 = r5
            goto L_0x00e1
        L_0x00ac:
            r3 = move-exception
            r4 = r5
            goto L_0x00c6
        L_0x00af:
            r3 = move-exception
            r4 = r5
            goto L_0x00cd
        L_0x00b2:
            r3 = move-exception
            r4 = r5
            goto L_0x00d4
        L_0x00b5:
            r3 = move-exception
            r4 = r5
            goto L_0x00db
        L_0x00b8:
            com.xiaomi.account.openauth.XMAuthericationException r3 = new com.xiaomi.account.openauth.XMAuthericationException     // Catch:{ UnsupportedEncodingException -> 0x00da, IOException -> 0x00d3, NoSuchAlgorithmException -> 0x00cc, InvalidKeyException -> 0x00c5 }
            java.io.IOException r5 = new java.io.IOException     // Catch:{ UnsupportedEncodingException -> 0x00da, IOException -> 0x00d3, NoSuchAlgorithmException -> 0x00cc, InvalidKeyException -> 0x00c5 }
            r5.<init>()     // Catch:{ UnsupportedEncodingException -> 0x00da, IOException -> 0x00d3, NoSuchAlgorithmException -> 0x00cc, InvalidKeyException -> 0x00c5 }
            r3.<init>((java.lang.Throwable) r5)     // Catch:{ UnsupportedEncodingException -> 0x00da, IOException -> 0x00d3, NoSuchAlgorithmException -> 0x00cc, InvalidKeyException -> 0x00c5 }
            throw r3     // Catch:{ UnsupportedEncodingException -> 0x00da, IOException -> 0x00d3, NoSuchAlgorithmException -> 0x00cc, InvalidKeyException -> 0x00c5 }
        L_0x00c3:
            r3 = move-exception
            goto L_0x00e1
        L_0x00c5:
            r3 = move-exception
        L_0x00c6:
            com.xiaomi.account.openauth.XMAuthericationException r5 = new com.xiaomi.account.openauth.XMAuthericationException     // Catch:{ all -> 0x00c3 }
            r5.<init>((java.lang.Throwable) r3)     // Catch:{ all -> 0x00c3 }
            throw r5     // Catch:{ all -> 0x00c3 }
        L_0x00cc:
            r3 = move-exception
        L_0x00cd:
            com.xiaomi.account.openauth.XMAuthericationException r5 = new com.xiaomi.account.openauth.XMAuthericationException     // Catch:{ all -> 0x00c3 }
            r5.<init>((java.lang.Throwable) r3)     // Catch:{ all -> 0x00c3 }
            throw r5     // Catch:{ all -> 0x00c3 }
        L_0x00d3:
            r3 = move-exception
        L_0x00d4:
            com.xiaomi.account.openauth.XMAuthericationException r5 = new com.xiaomi.account.openauth.XMAuthericationException     // Catch:{ all -> 0x00c3 }
            r5.<init>((java.lang.Throwable) r3)     // Catch:{ all -> 0x00c3 }
            throw r5     // Catch:{ all -> 0x00c3 }
        L_0x00da:
            r3 = move-exception
        L_0x00db:
            com.xiaomi.account.openauth.XMAuthericationException r5 = new com.xiaomi.account.openauth.XMAuthericationException     // Catch:{ all -> 0x00c3 }
            r5.<init>((java.lang.Throwable) r3)     // Catch:{ all -> 0x00c3 }
            throw r5     // Catch:{ all -> 0x00c3 }
        L_0x00e1:
            closeQuietly(r4)
            throw r3
        */
        throw new UnsupportedOperationException("Method not decompiled: com.xiaomi.account.auth.AuthorizeApi.doHttpGet(java.lang.String, long, java.lang.String, java.lang.String, java.lang.String):java.lang.String");
    }

    private static void closeQuietly(BufferedReader bufferedReader) {
        if (bufferedReader != null) {
            try {
                bufferedReader.close();
            } catch (IOException unused) {
            }
        }
    }

    private static HashMap<String, String> makeHeaders(String str, String str2, String str3, String str4, List<NameValuePair> list) throws InvalidKeyException, NoSuchAlgorithmException, UnsupportedEncodingException {
        if (str3 == null && str4 == null) {
            return null;
        }
        String generateNonce = AuthorizeHelper.generateNonce();
        return AuthorizeHelper.buildMacRequestHead(str2, generateNonce, AuthorizeHelper.getMacAccessTokenSignatureString(generateNonce, "GET", HOST, str, URLEncodedUtils.format(list, "UTF-8"), str3, str4));
    }
}
