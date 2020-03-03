package com.xiaomi.accountsdk.request;

import android.annotation.SuppressLint;
import android.net.Uri;
import android.text.TextUtils;
import com.taobao.weex.el.parse.Operators;
import com.xiaomi.accountsdk.account.XMPassportSettings;
import com.xiaomi.accountsdk.utils.DiagnosisLog;
import com.xiaomi.accountsdk.utils.DiagnosisLogInterface;
import com.xiaomi.accountsdk.utils.EasyMap;
import com.xiaomi.accountsdk.utils.IOUtils;
import com.xiaomi.accountsdk.utils.ObjectUtils;
import com.xiaomi.accountsdk.utils.ServerTimeUtil;
import com.xiaomi.accountsdk.utils.VersionUtils;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.UnsupportedEncodingException;
import java.net.CookieHandler;
import java.net.CookieManager;
import java.net.HttpCookie;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URI;
import java.net.URL;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.logging.Logger;
import org.json.JSONException;
import org.json.JSONObject;

@SuppressLint({"NewApi"})
public final class SimpleRequest {
    private static final boolean DEBUG = false;
    public static final String HEADER_KEY_USER_AGENT = "User-Agent";
    public static final String LOCATION = "Location";
    private static final String NAME_VALUE_SEPARATOR = "=";
    private static final String PARAMETER_SEPARATOR = "&";
    private static final int TIMEOUT = 30000;
    public static final String UTF8 = "utf-8";
    private static final Logger log = Logger.getLogger(SimpleRequest.class.getSimpleName());
    /* access modifiers changed from: private */
    public static ConnectivityListener sConnectivityListener;
    private static HttpURLConnectionFactory sHttpURLConnectionFactory = new HttpURLConnectionFactory() {
        public HttpURLConnection makeConn(URL url) throws IOException {
            if (SimpleRequest.sConnectivityListener != null) {
                SimpleRequest.sConnectivityListener.onOpenUrlConnection(url);
            }
            return (HttpURLConnection) url.openConnection();
        }
    };
    private static RequestLoggerForTest sRequestLoggerForTest = null;

    public interface HttpURLConnectionFactory {
        HttpURLConnection makeConn(URL url) throws IOException;
    }

    public interface RequestLoggerForTest {
        void log(String str, Map<String, String> map, Map<String, String> map2, Map<String, String> map3, boolean z, Integer num, Map<String, String> map4);
    }

    public static void setConnectivityListener(ConnectivityListener connectivityListener) {
        sConnectivityListener = connectivityListener;
    }

    static void injectHttpURLConnectionFactoryForTest(HttpURLConnectionFactory httpURLConnectionFactory) {
        sHttpURLConnectionFactory = httpURLConnectionFactory;
    }

    static HttpURLConnectionFactory getHttpURLConnectionFactoryForTest() {
        return sHttpURLConnectionFactory;
    }

    public static void setRequestLoggerForTest(RequestLoggerForTest requestLoggerForTest) {
        sRequestLoggerForTest = requestLoggerForTest;
    }

    public static void resetRequestLoggerForTest() {
        setRequestLoggerForTest((RequestLoggerForTest) null);
    }

    private static String appendUrl(String str, Map<String, String> map) {
        if (str != null) {
            Uri.Builder buildUpon = Uri.parse(str).buildUpon();
            if (map != null && !map.isEmpty()) {
                for (Map.Entry next : map.entrySet()) {
                    buildUpon.appendQueryParameter((String) next.getKey(), (String) next.getValue());
                }
            }
            return buildUpon.build().toString();
        }
        throw new NullPointerException("origin is not allowed null");
    }

    public static StringContent getAsString(String str, Map<String, String> map, Map<String, String> map2, boolean z) throws IOException, AccessDeniedException, AuthenticationFailureException {
        return getAsString(str, map, (Map<String, String>) null, map2, z, (Integer) null);
    }

    public static StringContent getAsString(String str, Map<String, String> map, Map<String, String> map2, Map<String, String> map3, boolean z) throws IOException, AccessDeniedException, AuthenticationFailureException {
        return getAsString(str, map, map2, map3, z, (Integer) null);
    }

    public static StringContent getAsString(String str, Map<String, String> map, Map<String, String> map2, Map<String, String> map3, boolean z, Integer num) throws IOException, AccessDeniedException, AuthenticationFailureException {
        BufferedReader bufferedReader;
        String headerField;
        if (sRequestLoggerForTest != null) {
            sRequestLoggerForTest.log(str, map, map2, map3, z, num, (Map<String, String>) null);
        }
        String appendUrl = appendUrl(str, map);
        String logGetRequest = getDiagnosisLogger().logGetRequest(str, map, appendUrl, map2, map3);
        HttpURLConnection makeConn = makeConn(appendUrl, map3, map2, num);
        if (makeConn != null) {
            try {
                makeConn.setDoInput(true);
                makeConn.setRequestMethod("GET");
                makeConn.connect();
                int responseCode = makeConn.getResponseCode();
                getDiagnosisLogger().logResponseCode(logGetRequest, responseCode);
                if (!(ServerTimeUtil.getComputer() == null || (headerField = makeConn.getHeaderField("Date")) == null)) {
                    ServerTimeUtil.getComputer().alignWithServerDateHeader(str, headerField);
                }
                if (responseCode != 200) {
                    if (responseCode != 302) {
                        if (responseCode == 403) {
                            throw new AccessDeniedException(responseCode, "access denied, encrypt error or user is forbidden to access the resource");
                        } else if (responseCode == 401 || responseCode == 400) {
                            AuthenticationFailureException authenticationFailureException = new AuthenticationFailureException(responseCode, "authentication failure for get, code: " + responseCode);
                            authenticationFailureException.setWwwAuthenticateHeader(makeConn.getHeaderField("WWW-Authenticate"));
                            authenticationFailureException.setCaDisableSecondsHeader(makeConn.getHeaderField("CA-DISABLE-SECONDS"));
                            throw authenticationFailureException;
                        } else {
                            Logger logger = log;
                            logger.info("http status error when GET: " + responseCode);
                            if (responseCode == 301) {
                                Logger logger2 = log;
                                logger2.info("unexpected redirect from " + makeConn.getURL().getHost() + " to " + makeConn.getHeaderField("Location"));
                            }
                            throw new IOException("unexpected http res code: " + responseCode);
                        }
                    }
                }
                Map headerFields = makeConn.getHeaderFields();
                CookieManager cookieManager = new CookieManager();
                URI create = URI.create(appendUrl);
                cookieManager.put(create, headerFields);
                Map<String, String> parseCookies = parseCookies(cookieManager.getCookieStore().get(create));
                StringBuilder sb = new StringBuilder();
                if (z) {
                    bufferedReader = new BufferedReader(new InputStreamReader(makeConn.getInputStream()), 1024);
                    while (true) {
                        String readLine = bufferedReader.readLine();
                        if (readLine == null) {
                            break;
                        }
                        sb.append(readLine);
                    }
                    IOUtils.closeQuietly((Reader) bufferedReader);
                }
                String sb2 = sb.toString();
                StringContent stringContent = new StringContent(sb2);
                stringContent.putCookies(parseCookies);
                stringContent.putHeaders(ObjectUtils.listToMap(headerFields));
                stringContent.setHttpCode(responseCode);
                getDiagnosisLogger().logResponse(logGetRequest, sb2, headerFields, parseCookies);
                makeConn.disconnect();
                return stringContent;
            } catch (Exception e) {
                try {
                    getDiagnosisLogger().logRequestException(e);
                    throw e;
                } catch (Throwable th) {
                    makeConn.disconnect();
                    throw th;
                }
            } catch (Throwable th2) {
                IOUtils.closeQuietly((Reader) bufferedReader);
                throw th2;
            }
        } else {
            log.severe("failed to create URLConnection");
            throw new IOException("failed to create connection");
        }
    }

    private static DiagnosisLogInterface getDiagnosisLogger() {
        return DiagnosisLog.get();
    }

    public static StreamContent getAsStream(String str, Map<String, String> map, Map<String, String> map2) throws IOException, AccessDeniedException, AuthenticationFailureException {
        return getAsStream(str, map, map2, (Map<String, String>) null);
    }

    public static StreamContent getAsStream(String str, Map<String, String> map, Map<String, String> map2, Map<String, String> map3) throws IOException, AccessDeniedException, AuthenticationFailureException {
        String appendUrl = appendUrl(str, map);
        String logGetRequest = getDiagnosisLogger().logGetRequest(str, map, appendUrl, map3, map2);
        HttpURLConnection makeConn = makeConn(appendUrl, map2, map3, (Integer) null);
        if (makeConn != null) {
            try {
                makeConn.setDoInput(true);
                makeConn.setRequestMethod("GET");
                makeConn.setInstanceFollowRedirects(true);
                makeConn.connect();
                int responseCode = makeConn.getResponseCode();
                getDiagnosisLogger().logResponseCode(logGetRequest, responseCode);
                if (responseCode == 200) {
                    Map headerFields = makeConn.getHeaderFields();
                    CookieManager cookieManager = new CookieManager();
                    URI create = URI.create(appendUrl);
                    cookieManager.put(create, headerFields);
                    Map<String, String> parseCookies = parseCookies(cookieManager.getCookieStore().get(create));
                    parseCookies.putAll(ObjectUtils.listToMap(headerFields));
                    StreamContent streamContent = new StreamContent(makeConn.getInputStream());
                    streamContent.putHeaders(parseCookies);
                    return streamContent;
                } else if (responseCode == 403) {
                    throw new AccessDeniedException(responseCode, "access denied, encrypt error or user is forbidden to access the resource");
                } else if (responseCode == 401 || responseCode == 400) {
                    AuthenticationFailureException authenticationFailureException = new AuthenticationFailureException(responseCode, "authentication failure for get, code: " + responseCode);
                    authenticationFailureException.setWwwAuthenticateHeader(makeConn.getHeaderField("WWW-Authenticate"));
                    authenticationFailureException.setCaDisableSecondsHeader(makeConn.getHeaderField("CA-DISABLE-SECONDS"));
                    throw authenticationFailureException;
                } else {
                    Logger logger = log;
                    logger.info("http status error when GET: " + responseCode);
                    if (responseCode == 301) {
                        Logger logger2 = log;
                        logger2.info("unexpected redirect from " + makeConn.getURL().getHost() + " to " + makeConn.getHeaderField("Location"));
                    }
                    throw new IOException("unexpected http res code: " + responseCode);
                }
            } catch (ProtocolException unused) {
                throw new IOException("protocol error");
            }
        } else {
            log.severe("failed to create URLConnection");
            throw new IOException("failed to create connection");
        }
    }

    public static MapContent getAsMap(String str, Map<String, String> map, Map<String, String> map2, boolean z) throws IOException, AccessDeniedException, AuthenticationFailureException {
        return getAsMap(str, map, map2, (Map<String, String>) null, z);
    }

    public static MapContent getAsMap(String str, Map<String, String> map, Map<String, String> map2, Map<String, String> map3, boolean z) throws IOException, AccessDeniedException, AuthenticationFailureException {
        return convertStringToMap(getAsString(str, map, map3, map2, z));
    }

    public static StringContent postAsString(String str, Map<String, String> map, Map<String, String> map2, boolean z) throws IOException, AccessDeniedException, AuthenticationFailureException {
        return postAsString(str, map, map2, (Map<String, String>) null, (Map<String, String>) null, z, (Integer) null);
    }

    public static StringContent postAsString(String str, Map<String, String> map, Map<String, String> map2, Map<String, String> map3, boolean z, Integer num) throws IOException, AccessDeniedException, AuthenticationFailureException {
        return postAsString(str, map, map2, map3, (Map<String, String>) null, z, num);
    }

    public static StringContent postAsString(String str, Map<String, String> map, Map<String, String> map2, Map<String, String> map3, Map<String, String> map4, boolean z) throws IOException, AccessDeniedException, AuthenticationFailureException {
        return postAsString(str, map, map2, map3, map4, z, (Integer) null);
    }

    /* JADX WARNING: Missing exception handler attribute for start block: B:73:0x01d1 */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static com.xiaomi.accountsdk.request.SimpleRequest.StringContent postAsString(java.lang.String r9, java.util.Map<java.lang.String, java.lang.String> r10, java.util.Map<java.lang.String, java.lang.String> r11, java.util.Map<java.lang.String, java.lang.String> r12, java.util.Map<java.lang.String, java.lang.String> r13, boolean r14, java.lang.Integer r15) throws java.io.IOException, com.xiaomi.accountsdk.request.AccessDeniedException, com.xiaomi.accountsdk.request.AuthenticationFailureException {
        /*
            com.xiaomi.accountsdk.request.SimpleRequest$RequestLoggerForTest r0 = sRequestLoggerForTest
            if (r0 == 0) goto L_0x0010
            com.xiaomi.accountsdk.request.SimpleRequest$RequestLoggerForTest r1 = sRequestLoggerForTest
            r2 = r9
            r3 = r10
            r4 = r12
            r5 = r11
            r6 = r14
            r7 = r15
            r8 = r13
            r1.log(r2, r3, r4, r5, r6, r7, r8)
        L_0x0010:
            if (r13 == 0) goto L_0x0017
            java.lang.String r0 = appendUrl(r9, r13)
            goto L_0x0018
        L_0x0017:
            r0 = r9
        L_0x0018:
            com.xiaomi.accountsdk.utils.DiagnosisLogInterface r1 = getDiagnosisLogger()
            r2 = r9
            r3 = r13
            r4 = r0
            r5 = r10
            r6 = r11
            r7 = r12
            java.lang.String r9 = r1.logPostRequest(r2, r3, r4, r5, r6, r7)
            java.net.HttpURLConnection r11 = makeConn(r0, r11, r12, r15)
            if (r11 == 0) goto L_0x01dd
            r13 = 1
            r11.setDoInput(r13)     // Catch:{ ProtocolException -> 0x01d1 }
            r11.setDoOutput(r13)     // Catch:{ ProtocolException -> 0x01d1 }
            java.lang.String r13 = "POST"
            r11.setRequestMethod(r13)     // Catch:{ ProtocolException -> 0x01d1 }
            r11.connect()     // Catch:{ ProtocolException -> 0x01d1 }
            if (r10 == 0) goto L_0x0064
            boolean r13 = r10.isEmpty()     // Catch:{ ProtocolException -> 0x01d1 }
            if (r13 != 0) goto L_0x0064
            java.lang.String r13 = "&"
            java.lang.String r10 = encodeFormatAndJoinMap(r10, r13)     // Catch:{ ProtocolException -> 0x01d1 }
            java.io.OutputStream r13 = r11.getOutputStream()     // Catch:{ ProtocolException -> 0x01d1 }
            java.io.BufferedOutputStream r15 = new java.io.BufferedOutputStream     // Catch:{ ProtocolException -> 0x01d1 }
            r15.<init>(r13)     // Catch:{ ProtocolException -> 0x01d1 }
            java.lang.String r13 = "utf-8"
            byte[] r10 = r10.getBytes(r13)     // Catch:{ all -> 0x005f }
            r15.write(r10)     // Catch:{ all -> 0x005f }
            com.xiaomi.accountsdk.utils.IOUtils.closeQuietly((java.io.OutputStream) r15)     // Catch:{ ProtocolException -> 0x01d1 }
            goto L_0x0064
        L_0x005f:
            r9 = move-exception
            com.xiaomi.accountsdk.utils.IOUtils.closeQuietly((java.io.OutputStream) r15)     // Catch:{ ProtocolException -> 0x01d1 }
            throw r9     // Catch:{ ProtocolException -> 0x01d1 }
        L_0x0064:
            int r10 = r11.getResponseCode()     // Catch:{ ProtocolException -> 0x01d1 }
            com.xiaomi.accountsdk.utils.DiagnosisLogInterface r13 = getDiagnosisLogger()     // Catch:{ ProtocolException -> 0x01d1 }
            r13.logResponseCode(r9, r10)     // Catch:{ ProtocolException -> 0x01d1 }
            r13 = 200(0xc8, float:2.8E-43)
            if (r10 == r13) goto L_0x0113
            r13 = 302(0x12e, float:4.23E-43)
            if (r10 != r13) goto L_0x0079
            goto L_0x0113
        L_0x0079:
            r9 = 403(0x193, float:5.65E-43)
            if (r10 == r9) goto L_0x010b
            r9 = 401(0x191, float:5.62E-43)
            if (r10 == r9) goto L_0x00e2
            r9 = 400(0x190, float:5.6E-43)
            if (r10 == r9) goto L_0x00e2
            java.util.logging.Logger r9 = log     // Catch:{ ProtocolException -> 0x01d1 }
            java.lang.StringBuilder r12 = new java.lang.StringBuilder     // Catch:{ ProtocolException -> 0x01d1 }
            r12.<init>()     // Catch:{ ProtocolException -> 0x01d1 }
            java.lang.String r13 = "http status error when POST: "
            r12.append(r13)     // Catch:{ ProtocolException -> 0x01d1 }
            r12.append(r10)     // Catch:{ ProtocolException -> 0x01d1 }
            java.lang.String r12 = r12.toString()     // Catch:{ ProtocolException -> 0x01d1 }
            r9.info(r12)     // Catch:{ ProtocolException -> 0x01d1 }
            r9 = 301(0x12d, float:4.22E-43)
            if (r10 != r9) goto L_0x00cb
            java.util.logging.Logger r9 = log     // Catch:{ ProtocolException -> 0x01d1 }
            java.lang.StringBuilder r12 = new java.lang.StringBuilder     // Catch:{ ProtocolException -> 0x01d1 }
            r12.<init>()     // Catch:{ ProtocolException -> 0x01d1 }
            java.lang.String r13 = "unexpected redirect from "
            r12.append(r13)     // Catch:{ ProtocolException -> 0x01d1 }
            java.net.URL r13 = r11.getURL()     // Catch:{ ProtocolException -> 0x01d1 }
            java.lang.String r13 = r13.getHost()     // Catch:{ ProtocolException -> 0x01d1 }
            r12.append(r13)     // Catch:{ ProtocolException -> 0x01d1 }
            java.lang.String r13 = " to "
            r12.append(r13)     // Catch:{ ProtocolException -> 0x01d1 }
            java.lang.String r13 = "Location"
            java.lang.String r13 = r11.getHeaderField(r13)     // Catch:{ ProtocolException -> 0x01d1 }
            r12.append(r13)     // Catch:{ ProtocolException -> 0x01d1 }
            java.lang.String r12 = r12.toString()     // Catch:{ ProtocolException -> 0x01d1 }
            r9.info(r12)     // Catch:{ ProtocolException -> 0x01d1 }
        L_0x00cb:
            java.io.IOException r9 = new java.io.IOException     // Catch:{ ProtocolException -> 0x01d1 }
            java.lang.StringBuilder r12 = new java.lang.StringBuilder     // Catch:{ ProtocolException -> 0x01d1 }
            r12.<init>()     // Catch:{ ProtocolException -> 0x01d1 }
            java.lang.String r13 = "unexpected http res code: "
            r12.append(r13)     // Catch:{ ProtocolException -> 0x01d1 }
            r12.append(r10)     // Catch:{ ProtocolException -> 0x01d1 }
            java.lang.String r10 = r12.toString()     // Catch:{ ProtocolException -> 0x01d1 }
            r9.<init>(r10)     // Catch:{ ProtocolException -> 0x01d1 }
            throw r9     // Catch:{ ProtocolException -> 0x01d1 }
        L_0x00e2:
            com.xiaomi.accountsdk.request.AuthenticationFailureException r9 = new com.xiaomi.accountsdk.request.AuthenticationFailureException     // Catch:{ ProtocolException -> 0x01d1 }
            java.lang.StringBuilder r12 = new java.lang.StringBuilder     // Catch:{ ProtocolException -> 0x01d1 }
            r12.<init>()     // Catch:{ ProtocolException -> 0x01d1 }
            java.lang.String r13 = "authentication failure for post, code: "
            r12.append(r13)     // Catch:{ ProtocolException -> 0x01d1 }
            r12.append(r10)     // Catch:{ ProtocolException -> 0x01d1 }
            java.lang.String r12 = r12.toString()     // Catch:{ ProtocolException -> 0x01d1 }
            r9.<init>(r10, r12)     // Catch:{ ProtocolException -> 0x01d1 }
            java.lang.String r10 = "WWW-Authenticate"
            java.lang.String r10 = r11.getHeaderField(r10)     // Catch:{ ProtocolException -> 0x01d1 }
            r9.setWwwAuthenticateHeader(r10)     // Catch:{ ProtocolException -> 0x01d1 }
            java.lang.String r10 = "CA-DISABLE-SECONDS"
            java.lang.String r10 = r11.getHeaderField(r10)     // Catch:{ ProtocolException -> 0x01d1 }
            r9.setCaDisableSecondsHeader(r10)     // Catch:{ ProtocolException -> 0x01d1 }
            throw r9     // Catch:{ ProtocolException -> 0x01d1 }
        L_0x010b:
            com.xiaomi.accountsdk.request.AccessDeniedException r9 = new com.xiaomi.accountsdk.request.AccessDeniedException     // Catch:{ ProtocolException -> 0x01d1 }
            java.lang.String r12 = "access denied, encrypt error or user is forbidden to access the resource"
            r9.<init>(r10, r12)     // Catch:{ ProtocolException -> 0x01d1 }
            throw r9     // Catch:{ ProtocolException -> 0x01d1 }
        L_0x0113:
            java.util.Map r13 = r11.getHeaderFields()     // Catch:{ ProtocolException -> 0x01d1 }
            java.net.URI r15 = java.net.URI.create(r0)     // Catch:{ ProtocolException -> 0x01d1 }
            java.lang.String r1 = r15.getHost()     // Catch:{ ProtocolException -> 0x01d1 }
            java.util.HashSet r2 = new java.util.HashSet     // Catch:{ ProtocolException -> 0x01d1 }
            r2.<init>()     // Catch:{ ProtocolException -> 0x01d1 }
            r2.add(r1)     // Catch:{ ProtocolException -> 0x01d1 }
            if (r12 == 0) goto L_0x013a
            java.lang.String r3 = "host"
            boolean r3 = r12.containsKey(r3)     // Catch:{ ProtocolException -> 0x01d1 }
            if (r3 == 0) goto L_0x013a
            java.lang.String r3 = "host"
            java.lang.Object r12 = r12.get(r3)     // Catch:{ ProtocolException -> 0x01d1 }
            r2.add(r12)     // Catch:{ ProtocolException -> 0x01d1 }
        L_0x013a:
            java.lang.String r12 = "c.id.mi.com"
            boolean r12 = r2.contains(r12)     // Catch:{ ProtocolException -> 0x01d1 }
            if (r12 == 0) goto L_0x0147
            java.lang.String r12 = "account.xiaomi.com"
            r2.add(r12)     // Catch:{ ProtocolException -> 0x01d1 }
        L_0x0147:
            com.xiaomi.accountsdk.request.SimpleRequest$2 r12 = new com.xiaomi.accountsdk.request.SimpleRequest$2     // Catch:{ ProtocolException -> 0x01d1 }
            r12.<init>(r2)     // Catch:{ ProtocolException -> 0x01d1 }
            java.net.CookieManager r3 = new java.net.CookieManager     // Catch:{ ProtocolException -> 0x01d1 }
            r4 = 0
            r3.<init>(r4, r12)     // Catch:{ ProtocolException -> 0x01d1 }
            r3.put(r15, r13)     // Catch:{ ProtocolException -> 0x01d1 }
            java.util.HashMap r12 = new java.util.HashMap     // Catch:{ ProtocolException -> 0x01d1 }
            r12.<init>()     // Catch:{ ProtocolException -> 0x01d1 }
            java.net.CookieStore r15 = r3.getCookieStore()     // Catch:{ ProtocolException -> 0x01d1 }
            java.util.Iterator r2 = r2.iterator()     // Catch:{ ProtocolException -> 0x01d1 }
        L_0x0162:
            boolean r3 = r2.hasNext()     // Catch:{ ProtocolException -> 0x01d1 }
            if (r3 == 0) goto L_0x0184
            java.lang.Object r3 = r2.next()     // Catch:{ ProtocolException -> 0x01d1 }
            java.lang.String r3 = (java.lang.String) r3     // Catch:{ ProtocolException -> 0x01d1 }
            java.lang.String r3 = r0.replaceFirst(r1, r3)     // Catch:{ ProtocolException -> 0x01d1 }
            java.net.URI r3 = java.net.URI.create(r3)     // Catch:{ ProtocolException -> 0x01d1 }
            java.util.List r3 = r15.get(r3)     // Catch:{ ProtocolException -> 0x01d1 }
            java.util.Map r3 = parseCookies(r3)     // Catch:{ ProtocolException -> 0x01d1 }
            if (r3 == 0) goto L_0x0162
            r12.putAll(r3)     // Catch:{ ProtocolException -> 0x01d1 }
            goto L_0x0162
        L_0x0184:
            java.lang.StringBuilder r15 = new java.lang.StringBuilder     // Catch:{ ProtocolException -> 0x01d1 }
            r15.<init>()     // Catch:{ ProtocolException -> 0x01d1 }
            if (r14 == 0) goto L_0x01ae
            java.io.BufferedReader r14 = new java.io.BufferedReader     // Catch:{ ProtocolException -> 0x01d1 }
            java.io.InputStreamReader r0 = new java.io.InputStreamReader     // Catch:{ ProtocolException -> 0x01d1 }
            java.io.InputStream r1 = r11.getInputStream()     // Catch:{ ProtocolException -> 0x01d1 }
            r0.<init>(r1)     // Catch:{ ProtocolException -> 0x01d1 }
            r1 = 1024(0x400, float:1.435E-42)
            r14.<init>(r0, r1)     // Catch:{ ProtocolException -> 0x01d1 }
        L_0x019b:
            java.lang.String r0 = r14.readLine()     // Catch:{ all -> 0x01a9 }
            if (r0 == 0) goto L_0x01a5
            r15.append(r0)     // Catch:{ all -> 0x01a9 }
            goto L_0x019b
        L_0x01a5:
            com.xiaomi.accountsdk.utils.IOUtils.closeQuietly((java.io.Reader) r14)     // Catch:{ ProtocolException -> 0x01d1 }
            goto L_0x01ae
        L_0x01a9:
            r9 = move-exception
            com.xiaomi.accountsdk.utils.IOUtils.closeQuietly((java.io.Reader) r14)     // Catch:{ ProtocolException -> 0x01d1 }
            throw r9     // Catch:{ ProtocolException -> 0x01d1 }
        L_0x01ae:
            java.lang.String r14 = r15.toString()     // Catch:{ ProtocolException -> 0x01d1 }
            com.xiaomi.accountsdk.request.SimpleRequest$StringContent r15 = new com.xiaomi.accountsdk.request.SimpleRequest$StringContent     // Catch:{ ProtocolException -> 0x01d1 }
            r15.<init>(r14)     // Catch:{ ProtocolException -> 0x01d1 }
            r15.putCookies(r12)     // Catch:{ ProtocolException -> 0x01d1 }
            r15.setHttpCode(r10)     // Catch:{ ProtocolException -> 0x01d1 }
            java.util.Map r10 = com.xiaomi.accountsdk.utils.ObjectUtils.listToMap(r13)     // Catch:{ ProtocolException -> 0x01d1 }
            r15.putHeaders(r10)     // Catch:{ ProtocolException -> 0x01d1 }
            com.xiaomi.accountsdk.utils.DiagnosisLogInterface r10 = getDiagnosisLogger()     // Catch:{ ProtocolException -> 0x01d1 }
            r10.logResponse(r9, r14, r13, r12)     // Catch:{ ProtocolException -> 0x01d1 }
            r11.disconnect()
            return r15
        L_0x01cf:
            r9 = move-exception
            goto L_0x01d9
        L_0x01d1:
            java.io.IOException r9 = new java.io.IOException     // Catch:{ all -> 0x01cf }
            java.lang.String r10 = "protocol error"
            r9.<init>(r10)     // Catch:{ all -> 0x01cf }
            throw r9     // Catch:{ all -> 0x01cf }
        L_0x01d9:
            r11.disconnect()
            throw r9
        L_0x01dd:
            java.util.logging.Logger r9 = log
            java.lang.String r10 = "failed to create URLConnection"
            r9.severe(r10)
            java.io.IOException r9 = new java.io.IOException
            java.lang.String r10 = "failed to create connection"
            r9.<init>(r10)
            throw r9
        */
        throw new UnsupportedOperationException("Method not decompiled: com.xiaomi.accountsdk.request.SimpleRequest.postAsString(java.lang.String, java.util.Map, java.util.Map, java.util.Map, java.util.Map, boolean, java.lang.Integer):com.xiaomi.accountsdk.request.SimpleRequest$StringContent");
    }

    public static MapContent postAsMap(String str, Map<String, String> map, Map<String, String> map2, boolean z) throws IOException, AccessDeniedException, AuthenticationFailureException {
        return postAsMap(str, map, map2, (Map<String, String>) null, z);
    }

    public static MapContent postAsMap(String str, Map<String, String> map, Map<String, String> map2, Map<String, String> map3, boolean z) throws IOException, AccessDeniedException, AuthenticationFailureException {
        return convertStringToMap(postAsString(str, map, map2, map3, (Map<String, String>) null, z));
    }

    protected static MapContent convertStringToMap(StringContent stringContent) {
        JSONObject jSONObject;
        if (stringContent == null) {
            return null;
        }
        try {
            jSONObject = new JSONObject(stringContent.getBody());
        } catch (JSONException e) {
            e.printStackTrace();
            jSONObject = null;
        }
        if (jSONObject == null) {
            return null;
        }
        MapContent mapContent = new MapContent(ObjectUtils.jsonToMap(jSONObject));
        mapContent.putHeaders(stringContent.getHeaders());
        return mapContent;
    }

    protected static HttpURLConnection makeConn(String str, Map<String, String> map, Map<String, String> map2, Integer num) {
        URL url;
        try {
            url = new URL(str);
        } catch (MalformedURLException e) {
            e.printStackTrace();
            url = null;
        }
        if (url == null) {
            log.severe("failed to init url");
            return null;
        }
        if (num == null) {
            num = 30000;
        }
        try {
            CookieHandler.setDefault((CookieHandler) null);
            HttpURLConnection makeConn = sHttpURLConnectionFactory.makeConn(url);
            boolean z = false;
            makeConn.setInstanceFollowRedirects(false);
            makeConn.setConnectTimeout(num.intValue());
            makeConn.setReadTimeout(num.intValue());
            makeConn.setUseCaches(false);
            makeConn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
            if (map2 == null || TextUtils.isEmpty(map2.get("User-Agent"))) {
                z = true;
            }
            if (z && !TextUtils.isEmpty(XMPassportSettings.getUserAgent())) {
                makeConn.setRequestProperty("User-Agent", XMPassportSettings.getUserAgent());
            }
            if (map == null) {
                map = new EasyMap<>();
            }
            map.put("sdkVersion", VersionUtils.getVersion());
            makeConn.setRequestProperty("Cookie", joinMap(map, "; "));
            if (map2 != null) {
                for (String next : map2.keySet()) {
                    makeConn.setRequestProperty(next, map2.get(next));
                }
            }
            return makeConn;
        } catch (Exception e2) {
            e2.printStackTrace();
            return null;
        }
    }

    private static String encodeFormatAndJoinMap(Map<String, String> map, String str) {
        if (map == null || map.isEmpty()) {
            return null;
        }
        StringBuilder sb = new StringBuilder();
        for (Map.Entry next : map.entrySet()) {
            if (sb.length() > 0) {
                sb.append(str);
            }
            String encode = encode((String) next.getKey());
            String encode2 = !TextUtils.isEmpty((CharSequence) next.getValue()) ? encode((String) next.getValue()) : "";
            sb.append(encode);
            sb.append("=");
            sb.append(encode2);
        }
        return sb.toString();
    }

    private static String encode(String str) {
        try {
            return URLEncoder.encode(str, "utf-8");
        } catch (UnsupportedEncodingException e) {
            throw new IllegalArgumentException(e);
        }
    }

    protected static String joinMap(Map<String, String> map, String str) {
        if (map == null) {
            return null;
        }
        StringBuilder sb = new StringBuilder();
        for (Map.Entry next : map.entrySet()) {
            if (sb.length() > 0) {
                sb.append(str);
            }
            sb.append((String) next.getKey());
            sb.append("=");
            sb.append((String) next.getValue());
        }
        return sb.toString();
    }

    protected static Map<String, String> parseCookies(List<HttpCookie> list) {
        HashMap hashMap = new HashMap();
        for (HttpCookie next : list) {
            if (!next.hasExpired()) {
                String name = next.getName();
                String value = next.getValue();
                if (name != null) {
                    hashMap.put(name, value);
                }
            }
        }
        return hashMap;
    }

    public static class HeaderContent {
        private final Set<String> cookieKeys = new HashSet();
        private final Map<String, String> headers = new HashMap();
        private int httpCode = -1;

        public void putHeader(String str, String str2) {
            this.headers.put(str, str2);
        }

        public String getHeader(String str) {
            return this.headers.get(str);
        }

        public Map<String, String> getHeaders() {
            return this.headers;
        }

        public void putHeaders(Map<String, String> map) {
            this.headers.putAll(map);
        }

        public void putCookies(Map<String, String> map) {
            putHeaders(map);
            if (map != null) {
                this.cookieKeys.addAll(map.keySet());
            }
        }

        public Set<String> getCookieKeys() {
            return this.cookieKeys;
        }

        public int getHttpCode() {
            return this.httpCode;
        }

        public void setHttpCode(int i) {
            this.httpCode = i;
        }

        public String toString() {
            return "HeaderContent{headers=" + this.headers + Operators.BLOCK_END;
        }
    }

    public static class StringContent extends HeaderContent {
        private String body;

        public StringContent(String str) {
            this.body = str;
        }

        public String getBody() {
            return this.body;
        }

        public String toString() {
            return "StringContent{body='" + this.body + Operators.SINGLE_QUOTE + Operators.BLOCK_END;
        }
    }

    public static class MapContent extends HeaderContent {
        private Map<String, Object> bodies;

        public MapContent(Map<String, Object> map) {
            this.bodies = map;
        }

        public Object getFromBody(String str) {
            return this.bodies.get(str);
        }

        public String toString() {
            return "MapContent{bodies=" + this.bodies + Operators.BLOCK_END;
        }
    }

    public static class StreamContent extends HeaderContent {
        private InputStream stream;

        public StreamContent(InputStream inputStream) {
            this.stream = inputStream;
        }

        public InputStream getStream() {
            return this.stream;
        }

        public void closeStream() {
            IOUtils.closeQuietly(this.stream);
        }
    }
}
