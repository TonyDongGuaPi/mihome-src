package org.xutils.http.request;

import android.annotation.TargetApi;
import android.net.Uri;
import android.os.Build;
import android.text.TextUtils;
import com.alipay.sdk.sys.a;
import com.alipay.sdk.util.i;
import com.google.common.net.HttpHeaders;
import java.io.Closeable;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Field;
import java.lang.reflect.Type;
import java.net.CookieManager;
import java.net.CookiePolicy;
import java.net.HttpURLConnection;
import java.net.ProtocolException;
import java.net.Proxy;
import java.net.URL;
import java.net.URLDecoder;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.StringTokenizer;
import java.util.TimeZone;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLSocketFactory;
import org.xutils.cache.DiskCacheEntity;
import org.xutils.cache.LruDiskCache;
import org.xutils.common.util.IOUtil;
import org.xutils.common.util.KeyValue;
import org.xutils.common.util.LogUtil;
import org.xutils.ex.HttpException;
import org.xutils.http.BaseParams;
import org.xutils.http.HttpMethod;
import org.xutils.http.RequestParams;
import org.xutils.http.body.ProgressBody;
import org.xutils.http.body.RequestBody;
import org.xutils.http.cookie.DbCookieStore;

public class HttpRequest extends UriRequest {
    private static final CookieManager l = new CookieManager(DbCookieStore.INSTANCE, CookiePolicy.ACCEPT_ALL);
    private String g = null;
    private boolean h = false;
    private InputStream i = null;
    private HttpURLConnection j = null;
    private int k = 0;

    HttpRequest(RequestParams requestParams, Type type) throws Throwable {
        super(requestParams, type);
    }

    /* access modifiers changed from: protected */
    public String a(RequestParams requestParams) {
        String o = requestParams.o();
        StringBuilder sb = new StringBuilder(o);
        if (!o.contains("?")) {
            sb.append("?");
        } else if (!o.endsWith("?")) {
            sb.append(a.b);
        }
        List<KeyValue> g2 = requestParams.g();
        if (g2 != null) {
            for (KeyValue keyValue : g2) {
                String str = keyValue.f4233a;
                String a2 = keyValue.a();
                if (!TextUtils.isEmpty(str) && a2 != null) {
                    sb.append(Uri.encode(str, requestParams.a()));
                    sb.append("=");
                    sb.append(Uri.encode(a2, requestParams.a()));
                    sb.append(a.b);
                }
            }
        }
        if (sb.charAt(sb.length() - 1) == '&') {
            sb.deleteCharAt(sb.length() - 1);
        }
        if (sb.charAt(sb.length() - 1) == '?') {
            sb.deleteCharAt(sb.length() - 1);
        }
        return sb.toString();
    }

    /* JADX WARNING: Code restructure failed: missing block: B:2:0x0006, code lost:
        r1 = r2.j.getURL();
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public java.lang.String aj_() {
        /*
            r2 = this;
            java.lang.String r0 = r2.f10786a
            java.net.HttpURLConnection r1 = r2.j
            if (r1 == 0) goto L_0x0012
            java.net.HttpURLConnection r1 = r2.j
            java.net.URL r1 = r1.getURL()
            if (r1 == 0) goto L_0x0012
            java.lang.String r0 = r1.toString()
        L_0x0012:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: org.xutils.http.request.HttpRequest.aj_():java.lang.String");
    }

    @TargetApi(19)
    public void a() throws Throwable {
        RequestBody l2;
        SSLSocketFactory q;
        this.h = false;
        this.k = 0;
        URL url = new URL(this.f10786a);
        Proxy s = this.b.s();
        if (s != null) {
            this.j = (HttpURLConnection) url.openConnection(s);
        } else {
            this.j = (HttpURLConnection) url.openConnection();
        }
        if (Build.VERSION.SDK_INT < 19) {
            this.j.setRequestProperty("Connection", "close");
        }
        this.j.setReadTimeout(this.b.u());
        this.j.setConnectTimeout(this.b.u());
        this.j.setInstanceFollowRedirects(this.b.G() == null);
        if ((this.j instanceof HttpsURLConnection) && (q = this.b.q()) != null) {
            ((HttpsURLConnection) this.j).setSSLSocketFactory(q);
        }
        if (this.b.r()) {
            try {
                List list = l.get(url.toURI(), new HashMap(0)).get("Cookie");
                if (list != null) {
                    this.j.setRequestProperty("Cookie", TextUtils.join(i.b, list));
                }
            } catch (Throwable th) {
                LogUtil.b(th.getMessage(), th);
            }
        }
        List<BaseParams.Header> f = this.b.f();
        if (f != null) {
            for (BaseParams.Header header : f) {
                String str = header.f4233a;
                String a2 = header.a();
                if (!TextUtils.isEmpty(str) && !TextUtils.isEmpty(a2)) {
                    if (header.c) {
                        this.j.setRequestProperty(str, a2);
                    } else {
                        this.j.addRequestProperty(str, a2);
                    }
                }
            }
        }
        if (this.f != null) {
            this.f.a(this);
        }
        HttpMethod b = this.b.b();
        try {
            this.j.setRequestMethod(b.toString());
        } catch (ProtocolException e) {
            Field declaredField = HttpURLConnection.class.getDeclaredField("method");
            declaredField.setAccessible(true);
            declaredField.set(this.j, b.toString());
        }
        if (HttpMethod.permitsRequestBody(b) && (l2 = this.b.l()) != null) {
            if (l2 instanceof ProgressBody) {
                ((ProgressBody) l2).a(this.e);
            }
            String a3 = l2.a();
            if (!TextUtils.isEmpty(a3)) {
                this.j.setRequestProperty("Content-Type", a3);
            }
            long b2 = l2.b();
            if (b2 < 0) {
                this.j.setChunkedStreamingMode(262144);
            } else if (b2 < 2147483647L) {
                this.j.setFixedLengthStreamingMode((int) b2);
            } else if (Build.VERSION.SDK_INT >= 19) {
                this.j.setFixedLengthStreamingMode(b2);
            } else {
                this.j.setChunkedStreamingMode(262144);
            }
            this.j.setRequestProperty("Content-Length", String.valueOf(b2));
            this.j.setDoOutput(true);
            l2.a(this.j.getOutputStream());
        }
        if (this.b.r()) {
            try {
                Map headerFields = this.j.getHeaderFields();
                if (headerFields != null) {
                    l.put(url.toURI(), headerFields);
                }
            } catch (Throwable th2) {
                LogUtil.b(th2.getMessage(), th2);
            }
        }
        this.k = this.j.getResponseCode();
        if (this.f != null) {
            this.f.b(this);
        }
        if (this.k == 204 || this.k == 205) {
            throw new HttpException(this.k, j());
        } else if (this.k < 300) {
            this.h = true;
            return;
        } else {
            HttpException httpException = new HttpException(this.k, j());
            try {
                httpException.setResult(IOUtil.a(g(), this.b.a()));
            } catch (Throwable unused) {
            }
            LogUtil.b(httpException.toString() + ", url: " + this.f10786a);
            throw httpException;
        }
    }

    public boolean b() {
        return this.h;
    }

    public String c() {
        if (this.g == null) {
            this.g = this.b.p();
            if (TextUtils.isEmpty(this.g)) {
                this.g = this.b.toString();
            }
        }
        return this.g;
    }

    public Object d() throws Throwable {
        this.h = true;
        return super.d();
    }

    public Object e() throws Throwable {
        this.h = true;
        DiskCacheEntity b = LruDiskCache.a(this.b.v()).a(this.b.w()).b(c());
        if (b == null) {
            return null;
        }
        if (HttpMethod.permitsCache(this.b.b())) {
            Date h2 = b.h();
            if (h2.getTime() > 0) {
                this.b.a(HttpHeaders.IF_MODIFIED_SINCE, a(h2));
            }
            String f = b.f();
            if (!TextUtils.isEmpty(f)) {
                this.b.a(HttpHeaders.IF_NONE_MATCH, f);
            }
        }
        return this.c.b(b);
    }

    public void f() {
        this.b.a(HttpHeaders.IF_MODIFIED_SINCE, (String) null);
        this.b.a(HttpHeaders.IF_NONE_MATCH, (String) null);
    }

    public InputStream g() throws IOException {
        if (this.j != null && this.i == null) {
            this.i = this.j.getResponseCode() >= 400 ? this.j.getErrorStream() : this.j.getInputStream();
        }
        return this.i;
    }

    public void close() throws IOException {
        if (this.i != null) {
            IOUtil.a((Closeable) this.i);
            this.i = null;
        }
        if (this.j != null) {
            this.j.disconnect();
        }
    }

    public long h() {
        long j2 = 0;
        if (this.j == null) {
            return (long) g().available();
        }
        try {
            j2 = (long) this.j.getContentLength();
        } catch (Throwable th) {
            LogUtil.b(th.getMessage(), th);
        }
        if (j2 >= 1) {
            return j2;
        }
        try {
            return (long) g().available();
        } catch (Throwable unused) {
            return j2;
        }
    }

    public int i() throws IOException {
        if (this.j != null) {
            return this.k;
        }
        return g() != null ? 200 : 404;
    }

    public String j() throws IOException {
        if (this.j != null) {
            return URLDecoder.decode(this.j.getResponseMessage(), this.b.a());
        }
        return null;
    }

    public long k() {
        long j2 = -1;
        if (this.j == null) {
            return -1;
        }
        String headerField = this.j.getHeaderField("Cache-Control");
        if (!TextUtils.isEmpty(headerField)) {
            StringTokenizer stringTokenizer = new StringTokenizer(headerField, ",");
            while (true) {
                if (!stringTokenizer.hasMoreTokens()) {
                    break;
                }
                String lowerCase = stringTokenizer.nextToken().trim().toLowerCase();
                if (lowerCase.startsWith("max-age")) {
                    int indexOf = lowerCase.indexOf(61);
                    if (indexOf > 0) {
                        try {
                            long parseLong = Long.parseLong(lowerCase.substring(indexOf + 1).trim());
                            if (parseLong > 0) {
                                j2 = System.currentTimeMillis() + (parseLong * 1000);
                            }
                        } catch (Throwable th) {
                            LogUtil.b(th.getMessage(), th);
                        }
                    }
                }
            }
        }
        if (j2 <= 0) {
            j2 = this.j.getExpiration();
        }
        long currentTimeMillis = (j2 > 0 || this.b.x() <= 0) ? j2 : System.currentTimeMillis() + this.b.x();
        if (currentTimeMillis <= 0) {
            return Long.MAX_VALUE;
        }
        return currentTimeMillis;
    }

    public long l() {
        return a("Last-Modified", System.currentTimeMillis());
    }

    public String m() {
        if (this.j == null) {
            return null;
        }
        return this.j.getHeaderField(HttpHeaders.ETAG);
    }

    public String a(String str) {
        if (this.j == null) {
            return null;
        }
        return this.j.getHeaderField(str);
    }

    public Map<String, List<String>> n() {
        if (this.j == null) {
            return null;
        }
        return this.j.getHeaderFields();
    }

    public long a(String str, long j2) {
        if (this.j == null) {
            return j2;
        }
        return this.j.getHeaderFieldDate(str, j2);
    }

    private static String a(Date date) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("EEE, dd MMM y HH:mm:ss 'GMT'", Locale.US);
        TimeZone timeZone = TimeZone.getTimeZone("GMT");
        simpleDateFormat.setTimeZone(timeZone);
        new GregorianCalendar(timeZone).setTimeInMillis(date.getTime());
        return simpleDateFormat.format(date);
    }
}
