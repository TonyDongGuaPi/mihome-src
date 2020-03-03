package miuipub.net.http;

import com.alipay.sdk.sys.a;
import com.google.common.net.HttpHeaders;
import com.xiaomi.infra.galaxy.fds.Common;
import java.io.Closeable;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FilterInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.RandomAccessFile;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.zip.GZIPInputStream;
import miuipub.net.http.Cache;
import miuipub.util.IOUtils;
import miuipub.util.SoftReferenceSingleton;
import org.apache.http.Header;
import org.apache.http.HeaderElement;
import org.apache.http.HttpEntity;
import org.apache.http.HttpException;
import org.apache.http.HttpRequest;
import org.apache.http.HttpRequestInterceptor;
import org.apache.http.HttpResponse;
import org.apache.http.HttpVersion;
import org.apache.http.auth.AuthScope;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.client.CookieStore;
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.client.protocol.ClientContext;
import org.apache.http.conn.params.ConnManagerParams;
import org.apache.http.conn.scheme.PlainSocketFactory;
import org.apache.http.conn.scheme.Scheme;
import org.apache.http.conn.scheme.SchemeRegistry;
import org.apache.http.conn.ssl.SSLSocketFactory;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.conn.tsccm.ThreadSafeClientConnManager;
import org.apache.http.impl.cookie.DateUtils;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;
import org.apache.http.params.HttpProtocolParams;
import org.apache.http.protocol.BasicHttpContext;
import org.apache.http.protocol.HttpContext;
import org.apache.http.protocol.SyncBasicHttpContext;

public class HttpSession {

    /* renamed from: a  reason: collision with root package name */
    private static final int f2980a = 10000;
    private static final int b = 8192;
    private static final String c = "Accept-Encoding";
    private static final String d = "gzip";
    private static final SoftReferenceSingleton<HttpSession> e = new SoftReferenceSingleton<HttpSession>() {
        /* access modifiers changed from: protected */
        /* renamed from: a */
        public HttpSession createInstance() {
            return new HttpSession();
        }
    };
    private final HttpContext f = new SyncBasicHttpContext(new BasicHttpContext());
    private final DefaultHttpClient g;
    /* access modifiers changed from: private */
    public final Map<String, String> h;
    private Cache i;
    private RetryStrategy j;

    public interface ProgressListener {
        void a(long j, long j2);
    }

    public HttpSession() {
        BasicHttpParams basicHttpParams = new BasicHttpParams();
        ConnManagerParams.setTimeout(basicHttpParams, 10000);
        ConnManagerParams.setMaxTotalConnections(basicHttpParams, 20);
        HttpConnectionParams.setSoTimeout(basicHttpParams, 10000);
        HttpConnectionParams.setConnectionTimeout(basicHttpParams, 10000);
        HttpConnectionParams.setTcpNoDelay(basicHttpParams, true);
        HttpConnectionParams.setSocketBufferSize(basicHttpParams, 8192);
        HttpProtocolParams.setVersion(basicHttpParams, HttpVersion.HTTP_1_1);
        HttpProtocolParams.setUserAgent(basicHttpParams, "miui v5");
        SchemeRegistry schemeRegistry = new SchemeRegistry();
        schemeRegistry.register(new Scheme("http", PlainSocketFactory.getSocketFactory(), 80));
        schemeRegistry.register(new Scheme("https", SSLSocketFactory.getSocketFactory(), 443));
        ThreadSafeClientConnManager threadSafeClientConnManager = new ThreadSafeClientConnManager(basicHttpParams, schemeRegistry);
        this.g = new DefaultHttpClient(threadSafeClientConnManager, basicHttpParams);
        this.f.setAttribute(ClientContext.COOKIE_STORE, PersistentCookieStore.a());
        this.g.addRequestInterceptor(new HttpRequestInterceptor() {
            public void process(HttpRequest httpRequest, HttpContext httpContext) throws HttpException, IOException {
                if (!httpRequest.containsHeader("Accept-Encoding")) {
                    httpRequest.addHeader("Accept-Encoding", "gzip");
                }
                for (Map.Entry entry : HttpSession.this.h.entrySet()) {
                    httpRequest.addHeader((String) entry.getKey(), (String) entry.getValue());
                }
            }
        });
        this.h = new HashMap();
        this.i = DiskBasedCache.c();
        this.j = new BaseRetryStrategy();
    }

    public static HttpSession a() {
        return e.get();
    }

    public void a(CookieStore cookieStore) {
        this.f.setAttribute(ClientContext.COOKIE_STORE, cookieStore);
    }

    public void a(String str) {
        HttpProtocolParams.setUserAgent(this.g.getParams(), str);
    }

    public void a(int i2) {
        HttpParams params = this.g.getParams();
        ConnManagerParams.setTimeout(params, (long) i2);
        HttpConnectionParams.setSoTimeout(params, i2);
        HttpConnectionParams.setConnectionTimeout(params, i2);
    }

    public void a(SSLSocketFactory sSLSocketFactory) {
        this.g.getConnectionManager().getSchemeRegistry().register(new Scheme("https", sSLSocketFactory, 443));
    }

    public void a(String str, String str2) {
        this.h.put(str, str2);
    }

    public void b(String str, String str2) {
        a(str, str2, AuthScope.ANY);
    }

    public void a(String str, String str2, AuthScope authScope) {
        this.g.getCredentialsProvider().setCredentials(authScope, new UsernamePasswordCredentials(str, str2));
    }

    public void a(RetryStrategy retryStrategy) {
        this.j = retryStrategy;
    }

    public void a(Cache cache) {
        if (this.i != cache) {
            this.i = cache;
        }
    }

    public void b(String str) {
        if (this.i != null) {
            this.i.b(str);
        }
    }

    public void b() {
        if (this.i != null) {
            this.i.b();
        }
    }

    public void a(File file, String str, Map<String, String> map, HttpRequestParams httpRequestParams, ProgressListener progressListener) throws IOException {
        FileOutputStream fileOutputStream;
        HttpGet httpGet = new HttpGet(a(str, httpRequestParams));
        a((HttpUriRequest) httpGet, map);
        long length = file.exists() ? file.length() : 0;
        httpGet.addHeader("RANGE", "bytes=" + length + "-");
        DefaultHttpResponse a2 = a((HttpUriRequest) httpGet, progressListener);
        RandomAccessFile randomAccessFile = null;
        try {
            String str2 = a2.f().get(Common.w);
            if (str2 != null) {
                if (str2.startsWith("bytes " + length)) {
                    RandomAccessFile randomAccessFile2 = new RandomAccessFile(file, "rw");
                    try {
                        randomAccessFile2.seek(length);
                        byte[] bArr = new byte[4096];
                        while (true) {
                            int read = a2.b().read(bArr);
                            if (read == -1) {
                                break;
                            }
                            randomAccessFile2.write(bArr, 0, read);
                        }
                        randomAccessFile2.close();
                        fileOutputStream = null;
                        randomAccessFile = randomAccessFile2;
                        IOUtils.a((Closeable) randomAccessFile);
                        IOUtils.a((OutputStream) fileOutputStream);
                        a2.g();
                    } catch (Throwable th) {
                        th = th;
                        fileOutputStream = null;
                        randomAccessFile = randomAccessFile2;
                        IOUtils.a((Closeable) randomAccessFile);
                        IOUtils.a((OutputStream) fileOutputStream);
                        a2.g();
                        throw th;
                    }
                }
            }
            fileOutputStream = new FileOutputStream(file);
            try {
                IOUtils.a(a2.b(), (OutputStream) fileOutputStream);
                fileOutputStream.close();
                IOUtils.a((Closeable) randomAccessFile);
                IOUtils.a((OutputStream) fileOutputStream);
                a2.g();
            } catch (Throwable th2) {
                th = th2;
                IOUtils.a((Closeable) randomAccessFile);
                IOUtils.a((OutputStream) fileOutputStream);
                a2.g();
                throw th;
            }
        } catch (Throwable th3) {
            th = th3;
            fileOutputStream = null;
            IOUtils.a((Closeable) randomAccessFile);
            IOUtils.a((OutputStream) fileOutputStream);
            a2.g();
            throw th;
        }
    }

    public HttpResponse a(String str, Map<String, String> map, HttpRequestParams httpRequestParams, ProgressListener progressListener) throws IOException {
        HttpGet httpGet = new HttpGet(a(str, httpRequestParams));
        a((HttpUriRequest) httpGet, map);
        return a((HttpUriRequest) httpGet, progressListener);
    }

    public HttpResponse b(String str, Map<String, String> map, HttpRequestParams httpRequestParams, ProgressListener progressListener) throws IOException {
        HttpPost httpPost = new HttpPost(str);
        if (httpRequestParams != null) {
            httpPost.setEntity(httpRequestParams.b());
        }
        a((HttpUriRequest) httpPost, map);
        return a((HttpUriRequest) httpPost, progressListener);
    }

    public HttpResponse c(String str, Map<String, String> map, HttpRequestParams httpRequestParams, ProgressListener progressListener) throws IOException {
        HttpPut httpPut = new HttpPut(str);
        if (httpRequestParams != null) {
            httpPut.setEntity(httpRequestParams.b());
        }
        a((HttpUriRequest) httpPut, map);
        return a((HttpUriRequest) httpPut, progressListener);
    }

    public HttpResponse d(String str, Map<String, String> map, HttpRequestParams httpRequestParams, ProgressListener progressListener) throws IOException {
        HttpDelete httpDelete = new HttpDelete(a(str, httpRequestParams));
        a((HttpUriRequest) httpDelete, map);
        return a((HttpUriRequest) httpDelete, progressListener);
    }

    /* JADX WARNING: Code restructure failed: missing block: B:24:0x0060, code lost:
        return a(r11, r1, r12);
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private miuipub.net.http.DefaultHttpResponse a(org.apache.http.client.methods.HttpUriRequest r11, miuipub.net.http.HttpSession.ProgressListener r12) throws java.io.IOException {
        /*
            r10 = this;
            miuipub.net.http.Cache$Entry r1 = r10.a((org.apache.http.client.methods.HttpUriRequest) r11)
            if (r1 == 0) goto L_0x002c
            long r2 = r1.h
            long r4 = java.lang.System.currentTimeMillis()
            int r0 = (r2 > r4 ? 1 : (r2 == r4 ? 0 : -1))
            if (r0 <= 0) goto L_0x002c
            miuipub.net.http.DefaultHttpResponse r0 = new miuipub.net.http.DefaultHttpResponse
            r3 = 200(0xc8, float:2.8E-43)
            java.util.Map<java.lang.String, java.lang.String> r4 = r1.i
            java.io.InputStream r5 = r1.f2973a
            long r6 = r1.b
            java.lang.String r8 = r1.d
            java.lang.String r9 = r1.e
            r2 = r0
            r2.<init>(r3, r4, r5, r6, r8, r9)
            if (r12 == 0) goto L_0x002b
            long r2 = r1.b
            long r4 = r1.b
            r12.a(r2, r4)
        L_0x002b:
            return r0
        L_0x002c:
            if (r12 == 0) goto L_0x0033
            r2 = -1
            r12.a(r2, r2)
        L_0x0033:
            if (r1 == 0) goto L_0x0038
            a((org.apache.http.client.methods.HttpUriRequest) r11, (miuipub.net.http.Cache.Entry) r1)
        L_0x0038:
            java.util.Map<java.lang.String, java.lang.String> r0 = r10.h
            a((org.apache.http.client.methods.HttpUriRequest) r11, (java.util.Map<java.lang.String, java.lang.String>) r0)
            java.lang.String r0 = "Accept-Encoding"
            boolean r0 = r11.containsHeader(r0)
            if (r0 != 0) goto L_0x004c
            java.lang.String r0 = "Accept-Encoding"
            java.lang.String r2 = "gzip"
            r11.addHeader(r0, r2)
        L_0x004c:
            miuipub.net.http.RetryStrategy r2 = r10.j
        L_0x004e:
            if (r2 == 0) goto L_0x005c
            int r0 = r2.a()     // Catch:{ IOException -> 0x005a, NullPointerException -> 0x0058 }
            r10.a((int) r0)     // Catch:{ IOException -> 0x005a, NullPointerException -> 0x0058 }
            goto L_0x005c
        L_0x0058:
            r0 = move-exception
            goto L_0x0061
        L_0x005a:
            r0 = move-exception
            goto L_0x006b
        L_0x005c:
            miuipub.net.http.DefaultHttpResponse r0 = r10.a((org.apache.http.client.methods.HttpUriRequest) r11, (miuipub.net.http.Cache.Entry) r1, (miuipub.net.http.HttpSession.ProgressListener) r12)     // Catch:{ IOException -> 0x005a, NullPointerException -> 0x0058 }
            return r0
        L_0x0061:
            if (r2 == 0) goto L_0x006a
            boolean r3 = r2.a(r0)
            if (r3 == 0) goto L_0x006a
            goto L_0x004e
        L_0x006a:
            throw r0
        L_0x006b:
            if (r2 == 0) goto L_0x0074
            boolean r3 = r2.a(r0)
            if (r3 == 0) goto L_0x0074
            goto L_0x004e
        L_0x0074:
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: miuipub.net.http.HttpSession.a(org.apache.http.client.methods.HttpUriRequest, miuipub.net.http.HttpSession$ProgressListener):miuipub.net.http.DefaultHttpResponse");
    }

    private DefaultHttpResponse a(HttpUriRequest httpUriRequest, Cache.Entry entry, ProgressListener progressListener) throws IOException {
        DefaultHttpResponse defaultHttpResponse;
        InputStream inputStream;
        String str;
        String str2;
        long j2;
        HttpUriRequest httpUriRequest2 = httpUriRequest;
        Cache.Entry entry2 = entry;
        GZIPInputStream gZIPInputStream = null;
        try {
            HttpResponse execute = this.g.execute(httpUriRequest2, this.f);
            int statusCode = execute.getStatusLine().getStatusCode();
            HttpEntity entity = execute.getEntity();
            if (statusCode == 304 && entry2 != null) {
                defaultHttpResponse = new DefaultHttpResponse(200, entry2.i, entry2.f2973a, entry2.b, entry2.d, entry2.e);
            } else if (statusCode < 200 || statusCode > 299) {
                throw new IOException(execute.getStatusLine().getReasonPhrase());
            } else {
                Map<String, String> a2 = a(execute.getAllHeaders());
                if (entity != null) {
                    inputStream = new CountingInputStream(entity, a2.get(Common.w), progressListener);
                    try {
                        long contentLength = entity.getContentLength();
                        String lowerCase = entity.getContentType() != null ? entity.getContentType().getValue().toLowerCase() : null;
                        String lowerCase2 = entity.getContentEncoding() != null ? entity.getContentEncoding().getValue().toLowerCase() : null;
                        if (lowerCase2 == null || !lowerCase2.contains("gzip")) {
                            str = lowerCase2;
                            j2 = contentLength;
                            str2 = lowerCase;
                            DefaultHttpResponse defaultHttpResponse2 = new DefaultHttpResponse(statusCode, a2, inputStream, j2, str2, str);
                            a(httpUriRequest2, defaultHttpResponse2);
                            defaultHttpResponse = defaultHttpResponse2;
                        } else {
                            GZIPInputStream gZIPInputStream2 = new GZIPInputStream(inputStream);
                            try {
                                StringBuilder sb = new StringBuilder();
                                for (HeaderElement headerElement : entity.getContentEncoding().getElements()) {
                                    if (!"gzip".equalsIgnoreCase(headerElement.getName())) {
                                        if (sb.length() != 0) {
                                            sb.append(", ");
                                        }
                                        sb.append(headerElement.getName());
                                    }
                                }
                                String sb2 = sb.toString();
                                a2.put("content-encoding", sb2);
                                str = sb2;
                                str2 = lowerCase;
                                inputStream = gZIPInputStream2;
                            } catch (Throwable th) {
                                th = th;
                                gZIPInputStream = gZIPInputStream2;
                                IOUtils.a((InputStream) gZIPInputStream);
                                throw th;
                            }
                        }
                    } catch (Throwable th2) {
                        th = th2;
                        gZIPInputStream = inputStream;
                        IOUtils.a((InputStream) gZIPInputStream);
                        throw th;
                    }
                } else {
                    inputStream = null;
                    str2 = null;
                    str = null;
                }
                j2 = -1;
                DefaultHttpResponse defaultHttpResponse22 = new DefaultHttpResponse(statusCode, a2, inputStream, j2, str2, str);
                a(httpUriRequest2, defaultHttpResponse22);
                defaultHttpResponse = defaultHttpResponse22;
            }
            IOUtils.a((InputStream) null);
            return defaultHttpResponse;
        } catch (Throwable th3) {
            th = th3;
            IOUtils.a((InputStream) gZIPInputStream);
            throw th;
        }
    }

    private void a(HttpUriRequest httpUriRequest, DefaultHttpResponse defaultHttpResponse) throws IOException {
        Cache cache = this.i;
        if (cache != null && "GET".equals(httpUriRequest.getMethod()) && !httpUriRequest.containsHeader("RANGE")) {
            String uri = httpUriRequest.getURI().toString();
            Cache.Entry a2 = HttpHeaderParser.a((HttpResponse) defaultHttpResponse);
            if (a2 != null && cache.a(uri, a2)) {
                defaultHttpResponse.a(a2.f2973a, a2.b);
            }
        }
    }

    private Cache.Entry a(HttpUriRequest httpUriRequest) {
        Cache cache = this.i;
        if (cache != null && "GET".equals(httpUriRequest.getMethod())) {
            return cache.a(httpUriRequest.getURI().toString());
        }
        return null;
    }

    private static void a(HttpUriRequest httpUriRequest, Map<String, String> map) {
        if (map != null && map.size() > 0) {
            for (Map.Entry next : map.entrySet()) {
                httpUriRequest.addHeader((String) next.getKey(), (String) next.getValue());
            }
        }
    }

    private static void a(HttpUriRequest httpUriRequest, Cache.Entry entry) {
        if (entry.c != null) {
            httpUriRequest.addHeader(HttpHeaders.IF_NONE_MATCH, entry.c);
        }
        if (entry.f > 0) {
            httpUriRequest.addHeader(HttpHeaders.IF_MODIFIED_SINCE, DateUtils.formatDate(new Date(entry.f)));
        }
    }

    private static Map<String, String> a(Header[] headerArr) {
        HashMap hashMap = new HashMap();
        if (headerArr != null) {
            for (Header header : headerArr) {
                hashMap.put(header.getName().toLowerCase(), header.getValue());
            }
        }
        return hashMap;
    }

    private static String a(String str, HttpRequestParams httpRequestParams) {
        String a2;
        if (httpRequestParams == null || (a2 = httpRequestParams.a()) == null || a2.length() <= 0) {
            return str;
        }
        if (str.indexOf(63) >= 0) {
            return str + "?" + a2;
        }
        return str + a.b + a2;
    }

    private static class CountingInputStream extends FilterInputStream {

        /* renamed from: a  reason: collision with root package name */
        private ProgressListener f2982a;
        private long b;
        private long c = 0;
        private long d = 0;
        private HttpEntity e;

        public CountingInputStream(HttpEntity httpEntity, String str, ProgressListener progressListener) throws IOException {
            super(httpEntity.getContent());
            this.e = httpEntity;
            this.b = httpEntity.getContentLength();
            this.f2982a = progressListener;
            if (str != null && str.length() > 0) {
                Matcher matcher = Pattern.compile("bytes\\s+(\\d+)-(\\d+)/(\\d+)").matcher(str);
                if (matcher.matches() && matcher.groupCount() == 3) {
                    this.c = Long.parseLong(matcher.group(1));
                    this.b = Long.parseLong(matcher.group(3));
                }
            }
        }

        public int read(byte[] bArr) throws IOException {
            return read(bArr, 0, bArr.length);
        }

        public int read(byte[] bArr, int i, int i2) throws IOException {
            int read = super.read(bArr, i, i2);
            if (read > 0) {
                this.c += (long) read;
                a(read);
            }
            return read;
        }

        public int read() throws IOException {
            int read = super.read();
            if (read > 0) {
                this.c++;
                a(1);
            }
            return read;
        }

        public void close() throws IOException {
            this.e.consumeContent();
            super.close();
        }

        private void a(int i) {
            if (this.b > 0 && this.f2982a != null) {
                long j = (this.c * 10) / this.b;
                if (this.d != j || i > 1024) {
                    this.d = j;
                    this.f2982a.a(this.b, this.c);
                }
            }
        }
    }
}
