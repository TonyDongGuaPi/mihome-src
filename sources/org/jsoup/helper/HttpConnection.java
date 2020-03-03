package org.jsoup.helper;

import com.alipay.sdk.util.i;
import com.google.common.net.HttpHeaders;
import java.io.BufferedInputStream;
import java.io.BufferedWriter;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.InetSocketAddress;
import java.net.MalformedURLException;
import java.net.Proxy;
import java.net.URI;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.nio.ByteBuffer;
import java.nio.charset.Charset;
import java.nio.charset.IllegalCharsetNameException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;
import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLSession;
import javax.net.ssl.SSLSocketFactory;
import kotlin.text.Typography;
import org.apache.commons.cli.HelpFormatter;
import org.jsoup.Connection;
import org.jsoup.UncheckedIOException;
import org.jsoup.internal.ConstrainableInputStream;
import org.jsoup.internal.Normalizer;
import org.jsoup.nodes.Document;
import org.jsoup.parser.Parser;
import org.jsoup.parser.TokenQueue;

public class HttpConnection implements Connection {

    /* renamed from: a  reason: collision with root package name */
    public static final String f3649a = "Content-Encoding";
    public static final String b = "Mozilla/5.0 (Macintosh; Intel Mac OS X 10_11_6) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/53.0.2785.143 Safari/537.36";
    private static final String c = "User-Agent";
    private static final String d = "Content-Type";
    private static final String e = "multipart/form-data";
    private static final String f = "application/x-www-form-urlencoded";
    private static final int g = 307;
    private static final String h = "application/octet-stream";
    private Connection.Request i = new Request();
    private Connection.Response j = new Response();

    public static Connection g(String str) {
        HttpConnection httpConnection = new HttpConnection();
        httpConnection.a(str);
        return httpConnection;
    }

    public static Connection b(URL url) {
        HttpConnection httpConnection = new HttpConnection();
        httpConnection.a(url);
        return httpConnection;
    }

    private static String i(String str) {
        try {
            return c(new URL(str)).toExternalForm();
        } catch (Exception unused) {
            return str;
        }
    }

    static URL c(URL url) {
        try {
            return new URL(new URI(url.toExternalForm().replaceAll(" ", "%20")).toASCIIString());
        } catch (Exception unused) {
            return url;
        }
    }

    /* access modifiers changed from: private */
    public static String j(String str) {
        if (str == null) {
            return null;
        }
        return str.replaceAll("\"", "%22");
    }

    private HttpConnection() {
    }

    public Connection a(URL url) {
        this.i.a(url);
        return this;
    }

    public Connection a(String str) {
        Validate.a(str, "Must supply a valid URL");
        try {
            this.i.a(new URL(i(str)));
            return this;
        } catch (MalformedURLException e2) {
            throw new IllegalArgumentException("Malformed URL: " + str, e2);
        }
    }

    public Connection a(Proxy proxy) {
        this.i.a(proxy);
        return this;
    }

    public Connection a(String str, int i2) {
        this.i.a(str, i2);
        return this;
    }

    public Connection b(String str) {
        Validate.a((Object) str, "User agent must not be null");
        this.i.a("User-Agent", str);
        return this;
    }

    public Connection a(int i2) {
        this.i.a(i2);
        return this;
    }

    public Connection b(int i2) {
        this.i.b(i2);
        return this;
    }

    public Connection a(boolean z) {
        this.i.a(z);
        return this;
    }

    public Connection c(String str) {
        Validate.a((Object) str, "Referrer must not be null");
        this.i.a(HttpHeaders.REFERER, str);
        return this;
    }

    public Connection a(Connection.Method method) {
        this.i.a(method);
        return this;
    }

    public Connection b(boolean z) {
        this.i.b(z);
        return this;
    }

    public Connection c(boolean z) {
        this.i.c(z);
        return this;
    }

    public Connection d(boolean z) {
        this.i.d(z);
        return this;
    }

    public Connection a(String str, String str2) {
        this.i.a((Connection.KeyVal) KeyVal.a(str, str2));
        return this;
    }

    public Connection a(String str, String str2, InputStream inputStream) {
        this.i.a((Connection.KeyVal) KeyVal.a(str, str2, inputStream));
        return this;
    }

    public Connection a(String str, String str2, InputStream inputStream, String str3) {
        this.i.a(KeyVal.a(str, str2, inputStream).c(str3));
        return this;
    }

    public Connection a(Map<String, String> map) {
        Validate.a((Object) map, "Data map must not be null");
        for (Map.Entry next : map.entrySet()) {
            this.i.a((Connection.KeyVal) KeyVal.a((String) next.getKey(), (String) next.getValue()));
        }
        return this;
    }

    public Connection a(String... strArr) {
        Validate.a((Object) strArr, "Data key value pairs must not be null");
        Validate.a(strArr.length % 2 == 0, "Must supply an even number of key value pairs");
        for (int i2 = 0; i2 < strArr.length; i2 += 2) {
            String str = strArr[i2];
            String str2 = strArr[i2 + 1];
            Validate.a(str, "Data key must not be empty");
            Validate.a((Object) str2, "Data value must not be null");
            this.i.a((Connection.KeyVal) KeyVal.a(str, str2));
        }
        return this;
    }

    public Connection a(Collection<Connection.KeyVal> collection) {
        Validate.a((Object) collection, "Data collection must not be null");
        for (Connection.KeyVal a2 : collection) {
            this.i.a(a2);
        }
        return this;
    }

    public Connection.KeyVal d(String str) {
        Validate.a(str, "Data key must not be empty");
        for (Connection.KeyVal next : d().m()) {
            if (next.a().equals(str)) {
                return next;
            }
        }
        return null;
    }

    public Connection e(String str) {
        this.i.h(str);
        return this;
    }

    public Connection b(String str, String str2) {
        this.i.a(str, str2);
        return this;
    }

    public Connection b(Map<String, String> map) {
        Validate.a((Object) map, "Header map must not be null");
        for (Map.Entry next : map.entrySet()) {
            this.i.a((String) next.getKey(), (String) next.getValue());
        }
        return this;
    }

    public Connection c(String str, String str2) {
        this.i.d(str, str2);
        return this;
    }

    public Connection c(Map<String, String> map) {
        Validate.a((Object) map, "Cookie map must not be null");
        for (Map.Entry next : map.entrySet()) {
            this.i.d((String) next.getKey(), (String) next.getValue());
        }
        return this;
    }

    public Connection a(Parser parser) {
        this.i.a(parser);
        return this;
    }

    public Document a() throws IOException {
        this.i.a(Connection.Method.GET);
        c();
        return this.j.j();
    }

    public Document b() throws IOException {
        this.i.a(Connection.Method.POST);
        c();
        return this.j.j();
    }

    public Connection.Response c() throws IOException {
        this.j = Response.a(this.i);
        return this.j;
    }

    public Connection.Request d() {
        return this.i;
    }

    public Connection a(Connection.Request request) {
        this.i = request;
        return this;
    }

    public Connection.Response e() {
        return this.j;
    }

    public Connection a(Connection.Response response) {
        this.j = response;
        return this;
    }

    public Connection f(String str) {
        this.i.i(str);
        return this;
    }

    private static abstract class Base<T extends Connection.Base> implements Connection.Base<T> {

        /* renamed from: a  reason: collision with root package name */
        URL f3650a;
        Connection.Method b;
        Map<String, List<String>> c;
        Map<String, String> d;

        private Base() {
            this.c = new LinkedHashMap();
            this.d = new LinkedHashMap();
        }

        public URL a() {
            return this.f3650a;
        }

        public T a(URL url) {
            Validate.a((Object) url, "URL must not be null");
            this.f3650a = url;
            return this;
        }

        public Connection.Method b() {
            return this.b;
        }

        public T a(Connection.Method method) {
            Validate.a((Object) method, "Method must not be null");
            this.b = method;
            return this;
        }

        public String a(String str) {
            Validate.a((Object) str, "Header name must not be null");
            List<String> i = i(str);
            if (i.size() > 0) {
                return StringUtil.a((Collection) i, ", ");
            }
            return null;
        }

        public T b(String str, String str2) {
            Validate.a(str);
            if (str2 == null) {
                str2 = "";
            }
            List b2 = b(str);
            if (b2.isEmpty()) {
                b2 = new ArrayList();
                this.c.put(str, b2);
            }
            b2.add(h(str2));
            return this;
        }

        public List<String> b(String str) {
            Validate.a(str);
            return i(str);
        }

        private static String h(String str) {
            try {
                byte[] bytes = str.getBytes("ISO-8859-1");
                if (!a(bytes)) {
                    return str;
                }
                return new String(bytes, "UTF-8");
            } catch (UnsupportedEncodingException unused) {
                return str;
            }
        }

        /* JADX WARNING: Code restructure failed: missing block: B:13:0x0026, code lost:
            if ((((r8[1] & 255) == 187) & ((r8[2] & 255) == 191)) != false) goto L_0x002a;
         */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        private static boolean a(byte[] r8) {
            /*
                int r0 = r8.length
                r1 = 3
                r2 = 0
                r3 = 1
                if (r0 < r1) goto L_0x0029
                byte r0 = r8[r2]
                r0 = r0 & 255(0xff, float:3.57E-43)
                r4 = 239(0xef, float:3.35E-43)
                if (r0 != r4) goto L_0x0029
                byte r0 = r8[r3]
                r0 = r0 & 255(0xff, float:3.57E-43)
                r4 = 187(0xbb, float:2.62E-43)
                if (r0 != r4) goto L_0x0018
                r0 = 1
                goto L_0x0019
            L_0x0018:
                r0 = 0
            L_0x0019:
                r4 = 2
                byte r4 = r8[r4]
                r4 = r4 & 255(0xff, float:3.57E-43)
                r5 = 191(0xbf, float:2.68E-43)
                if (r4 != r5) goto L_0x0024
                r4 = 1
                goto L_0x0025
            L_0x0024:
                r4 = 0
            L_0x0025:
                r0 = r0 & r4
                if (r0 == 0) goto L_0x0029
                goto L_0x002a
            L_0x0029:
                r1 = 0
            L_0x002a:
                int r0 = r8.length
            L_0x002b:
                if (r1 >= r0) goto L_0x005d
                byte r4 = r8[r1]
                r5 = r4 & 128(0x80, float:1.794E-43)
                if (r5 != 0) goto L_0x0034
                goto L_0x005a
            L_0x0034:
                r5 = r4 & 224(0xe0, float:3.14E-43)
                r6 = 192(0xc0, float:2.69E-43)
                if (r5 != r6) goto L_0x003d
                int r4 = r1 + 1
                goto L_0x004e
            L_0x003d:
                r5 = r4 & 240(0xf0, float:3.36E-43)
                r7 = 224(0xe0, float:3.14E-43)
                if (r5 != r7) goto L_0x0046
                int r4 = r1 + 2
                goto L_0x004e
            L_0x0046:
                r4 = r4 & 248(0xf8, float:3.48E-43)
                r5 = 240(0xf0, float:3.36E-43)
                if (r4 != r5) goto L_0x005c
                int r4 = r1 + 3
            L_0x004e:
                if (r1 >= r4) goto L_0x005a
                int r1 = r1 + 1
                byte r5 = r8[r1]
                r5 = r5 & r6
                r7 = 128(0x80, float:1.794E-43)
                if (r5 == r7) goto L_0x004e
                return r2
            L_0x005a:
                int r1 = r1 + r3
                goto L_0x002b
            L_0x005c:
                return r2
            L_0x005d:
                return r3
            */
            throw new UnsupportedOperationException("Method not decompiled: org.jsoup.helper.HttpConnection.Base.a(byte[]):boolean");
        }

        public T a(String str, String str2) {
            Validate.a(str, "Header name must not be empty");
            d(str);
            b(str, str2);
            return this;
        }

        public boolean c(String str) {
            Validate.a(str, "Header name must not be empty");
            return i(str).size() != 0;
        }

        public boolean c(String str, String str2) {
            Validate.a(str);
            Validate.a(str2);
            for (String equalsIgnoreCase : b(str)) {
                if (str2.equalsIgnoreCase(equalsIgnoreCase)) {
                    return true;
                }
            }
            return false;
        }

        public T d(String str) {
            Validate.a(str, "Header name must not be empty");
            Map.Entry<String, List<String>> j = j(str);
            if (j != null) {
                this.c.remove(j.getKey());
            }
            return this;
        }

        public Map<String, String> c() {
            LinkedHashMap linkedHashMap = new LinkedHashMap(this.c.size());
            for (Map.Entry next : this.c.entrySet()) {
                String str = (String) next.getKey();
                List list = (List) next.getValue();
                if (list.size() > 0) {
                    linkedHashMap.put(str, list.get(0));
                }
            }
            return linkedHashMap;
        }

        public Map<String, List<String>> d() {
            return this.c;
        }

        private List<String> i(String str) {
            Validate.a((Object) str);
            for (Map.Entry next : this.c.entrySet()) {
                if (str.equalsIgnoreCase((String) next.getKey())) {
                    return (List) next.getValue();
                }
            }
            return Collections.emptyList();
        }

        private Map.Entry<String, List<String>> j(String str) {
            String a2 = Normalizer.a(str);
            for (Map.Entry<String, List<String>> next : this.c.entrySet()) {
                if (Normalizer.a(next.getKey()).equals(a2)) {
                    return next;
                }
            }
            return null;
        }

        public String e(String str) {
            Validate.a(str, "Cookie name must not be empty");
            return this.d.get(str);
        }

        public T d(String str, String str2) {
            Validate.a(str, "Cookie name must not be empty");
            Validate.a((Object) str2, "Cookie value must not be null");
            this.d.put(str, str2);
            return this;
        }

        public boolean f(String str) {
            Validate.a(str, "Cookie name must not be empty");
            return this.d.containsKey(str);
        }

        public T g(String str) {
            Validate.a(str, "Cookie name must not be empty");
            this.d.remove(str);
            return this;
        }

        public Map<String, String> e() {
            return this.d;
        }
    }

    public static class Request extends Base<Connection.Request> implements Connection.Request {
        private Proxy e;
        private int f = 30000;
        private int g = 1048576;
        private boolean h = true;
        private Collection<Connection.KeyVal> i = new ArrayList();
        private String j = null;
        private boolean k = false;
        private boolean l = false;
        private Parser m;
        /* access modifiers changed from: private */
        public boolean n = false;
        private boolean o = true;
        private String p = "UTF-8";

        public /* bridge */ /* synthetic */ String a(String str) {
            return super.a(str);
        }

        public /* bridge */ /* synthetic */ URL a() {
            return super.a();
        }

        public /* bridge */ /* synthetic */ List b(String str) {
            return super.b(str);
        }

        public /* bridge */ /* synthetic */ Connection.Method b() {
            return super.b();
        }

        public /* bridge */ /* synthetic */ Map c() {
            return super.c();
        }

        public /* bridge */ /* synthetic */ boolean c(String str) {
            return super.c(str);
        }

        public /* bridge */ /* synthetic */ boolean c(String str, String str2) {
            return super.c(str, str2);
        }

        public /* bridge */ /* synthetic */ Map d() {
            return super.d();
        }

        public /* bridge */ /* synthetic */ String e(String str) {
            return super.e(str);
        }

        public /* bridge */ /* synthetic */ Map e() {
            return super.e();
        }

        public /* bridge */ /* synthetic */ boolean f(String str) {
            return super.f(str);
        }

        Request() {
            super();
            this.b = Connection.Method.GET;
            b("Accept-Encoding", "gzip");
            b("User-Agent", HttpConnection.b);
            this.m = Parser.e();
        }

        public Proxy f() {
            return this.e;
        }

        /* renamed from: b */
        public Request a(Proxy proxy) {
            this.e = proxy;
            return this;
        }

        /* renamed from: b */
        public Request a(String str, int i2) {
            this.e = new Proxy(Proxy.Type.HTTP, InetSocketAddress.createUnresolved(str, i2));
            return this;
        }

        public int g() {
            return this.f;
        }

        /* renamed from: c */
        public Request a(int i2) {
            Validate.a(i2 >= 0, "Timeout milliseconds must be 0 (infinite) or greater");
            this.f = i2;
            return this;
        }

        public int h() {
            return this.g;
        }

        public Connection.Request b(int i2) {
            Validate.a(i2 >= 0, "maxSize must be 0 (unlimited) or larger");
            this.g = i2;
            return this;
        }

        public boolean i() {
            return this.h;
        }

        public Connection.Request a(boolean z) {
            this.h = z;
            return this;
        }

        public boolean j() {
            return this.k;
        }

        public boolean l() {
            return this.o;
        }

        public void d(boolean z) {
            this.o = z;
        }

        public Connection.Request b(boolean z) {
            this.k = z;
            return this;
        }

        public boolean k() {
            return this.l;
        }

        public Connection.Request c(boolean z) {
            this.l = z;
            return this;
        }

        /* renamed from: b */
        public Request a(Connection.KeyVal keyVal) {
            Validate.a((Object) keyVal, "Key val must not be null");
            this.i.add(keyVal);
            return this;
        }

        public Collection<Connection.KeyVal> m() {
            return this.i;
        }

        public Connection.Request h(String str) {
            this.j = str;
            return this;
        }

        public String n() {
            return this.j;
        }

        /* renamed from: b */
        public Request a(Parser parser) {
            this.m = parser;
            this.n = true;
            return this;
        }

        public Parser o() {
            return this.m;
        }

        public Connection.Request i(String str) {
            Validate.a((Object) str, "Charset must not be null");
            if (Charset.isSupported(str)) {
                this.p = str;
                return this;
            }
            throw new IllegalCharsetNameException(str);
        }

        public String p() {
            return this.p;
        }
    }

    public static class Response extends Base<Connection.Response> implements Connection.Response {
        private static final int e = 20;
        private static SSLSocketFactory f = null;
        private static final String g = "Location";
        private static final Pattern r = Pattern.compile("(application|text)/\\w*\\+?xml.*");
        private int h;
        private String i;
        private ByteBuffer j;
        private InputStream k;
        private String l;
        private String m;
        private boolean n = false;
        private boolean o = false;
        private int p = 0;
        private Connection.Request q;

        public /* bridge */ /* synthetic */ String a(String str) {
            return super.a(str);
        }

        public /* bridge */ /* synthetic */ URL a() {
            return super.a();
        }

        public /* bridge */ /* synthetic */ List b(String str) {
            return super.b(str);
        }

        public /* bridge */ /* synthetic */ Connection.Method b() {
            return super.b();
        }

        public /* bridge */ /* synthetic */ Map c() {
            return super.c();
        }

        public /* bridge */ /* synthetic */ boolean c(String str) {
            return super.c(str);
        }

        public /* bridge */ /* synthetic */ boolean c(String str, String str2) {
            return super.c(str, str2);
        }

        public /* bridge */ /* synthetic */ Map d() {
            return super.d();
        }

        public /* bridge */ /* synthetic */ String e(String str) {
            return super.e(str);
        }

        public /* bridge */ /* synthetic */ Map e() {
            return super.e();
        }

        public /* bridge */ /* synthetic */ boolean f(String str) {
            return super.f(str);
        }

        Response() {
            super();
        }

        private Response(Response response) throws IOException {
            super();
            if (response != null) {
                this.p = response.p + 1;
                if (this.p >= 20) {
                    throw new IOException(String.format("Too many redirects occurred trying to load URL %s", new Object[]{response.a()}));
                }
            }
        }

        static Response a(Connection.Request request) throws IOException {
            return a(request, (Response) null);
        }

        /* JADX WARNING: Removed duplicated region for block: B:24:0x007e A[Catch:{ IOException -> 0x01df }] */
        /* JADX WARNING: Removed duplicated region for block: B:29:0x00a1 A[Catch:{ IOException -> 0x01df }] */
        /* JADX WARNING: Removed duplicated region for block: B:45:0x0110 A[Catch:{ IOException -> 0x01df }] */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        static org.jsoup.helper.HttpConnection.Response a(org.jsoup.Connection.Request r9, org.jsoup.helper.HttpConnection.Response r10) throws java.io.IOException {
            /*
                java.lang.String r0 = "Request must not be null"
                org.jsoup.helper.Validate.a((java.lang.Object) r9, (java.lang.String) r0)
                java.net.URL r0 = r9.a()
                java.lang.String r0 = r0.getProtocol()
                java.lang.String r1 = "http"
                boolean r1 = r0.equals(r1)
                if (r1 != 0) goto L_0x0026
                java.lang.String r1 = "https"
                boolean r0 = r0.equals(r1)
                if (r0 == 0) goto L_0x001e
                goto L_0x0026
            L_0x001e:
                java.net.MalformedURLException r9 = new java.net.MalformedURLException
                java.lang.String r10 = "Only http & https protocols supported"
                r9.<init>(r10)
                throw r9
            L_0x0026:
                org.jsoup.Connection$Method r0 = r9.b()
                boolean r0 = r0.hasBody()
                java.lang.String r1 = r9.n()
                r2 = 1
                if (r1 == 0) goto L_0x0037
                r1 = 1
                goto L_0x0038
            L_0x0037:
                r1 = 0
            L_0x0038:
                if (r0 != 0) goto L_0x0052
                java.lang.StringBuilder r3 = new java.lang.StringBuilder
                r3.<init>()
                java.lang.String r4 = "Cannot set a request body for HTTP method "
                r3.append(r4)
                org.jsoup.Connection$Method r4 = r9.b()
                r3.append(r4)
                java.lang.String r3 = r3.toString()
                org.jsoup.helper.Validate.b(r1, r3)
            L_0x0052:
                java.util.Collection r3 = r9.m()
                int r3 = r3.size()
                r4 = 0
                if (r3 <= 0) goto L_0x0065
                if (r0 == 0) goto L_0x0061
                if (r1 == 0) goto L_0x0065
            L_0x0061:
                e((org.jsoup.Connection.Request) r9)
                goto L_0x006c
            L_0x0065:
                if (r0 == 0) goto L_0x006c
                java.lang.String r0 = c((org.jsoup.Connection.Request) r9)
                goto L_0x006d
            L_0x006c:
                r0 = r4
            L_0x006d:
                long r5 = java.lang.System.nanoTime()
                java.net.HttpURLConnection r1 = b((org.jsoup.Connection.Request) r9)
                r1.connect()     // Catch:{ IOException -> 0x01df }
                boolean r3 = r1.getDoOutput()     // Catch:{ IOException -> 0x01df }
                if (r3 == 0) goto L_0x0085
                java.io.OutputStream r3 = r1.getOutputStream()     // Catch:{ IOException -> 0x01df }
                a(r9, r3, r0)     // Catch:{ IOException -> 0x01df }
            L_0x0085:
                int r0 = r1.getResponseCode()     // Catch:{ IOException -> 0x01df }
                org.jsoup.helper.HttpConnection$Response r3 = new org.jsoup.helper.HttpConnection$Response     // Catch:{ IOException -> 0x01df }
                r3.<init>(r10)     // Catch:{ IOException -> 0x01df }
                r3.a((java.net.HttpURLConnection) r1, (org.jsoup.Connection.Response) r10)     // Catch:{ IOException -> 0x01df }
                r3.q = r9     // Catch:{ IOException -> 0x01df }
                java.lang.String r10 = "Location"
                boolean r10 = r3.c((java.lang.String) r10)     // Catch:{ IOException -> 0x01df }
                if (r10 == 0) goto L_0x0110
                boolean r10 = r9.i()     // Catch:{ IOException -> 0x01df }
                if (r10 == 0) goto L_0x0110
                r10 = 307(0x133, float:4.3E-43)
                if (r0 == r10) goto L_0x00b9
                org.jsoup.Connection$Method r10 = org.jsoup.Connection.Method.GET     // Catch:{ IOException -> 0x01df }
                r9.a((org.jsoup.Connection.Method) r10)     // Catch:{ IOException -> 0x01df }
                java.util.Collection r10 = r9.m()     // Catch:{ IOException -> 0x01df }
                r10.clear()     // Catch:{ IOException -> 0x01df }
                r9.h(r4)     // Catch:{ IOException -> 0x01df }
                java.lang.String r10 = "Content-Type"
                r9.d(r10)     // Catch:{ IOException -> 0x01df }
            L_0x00b9:
                java.lang.String r10 = "Location"
                java.lang.String r10 = r3.a((java.lang.String) r10)     // Catch:{ IOException -> 0x01df }
                if (r10 == 0) goto L_0x00d6
                java.lang.String r0 = "http:/"
                boolean r0 = r10.startsWith(r0)     // Catch:{ IOException -> 0x01df }
                if (r0 == 0) goto L_0x00d6
                r0 = 6
                char r2 = r10.charAt(r0)     // Catch:{ IOException -> 0x01df }
                r4 = 47
                if (r2 == r4) goto L_0x00d6
                java.lang.String r10 = r10.substring(r0)     // Catch:{ IOException -> 0x01df }
            L_0x00d6:
                java.net.URL r0 = r9.a()     // Catch:{ IOException -> 0x01df }
                java.net.URL r10 = org.jsoup.helper.StringUtil.a((java.net.URL) r0, (java.lang.String) r10)     // Catch:{ IOException -> 0x01df }
                java.net.URL r10 = org.jsoup.helper.HttpConnection.c((java.net.URL) r10)     // Catch:{ IOException -> 0x01df }
                r9.a((java.net.URL) r10)     // Catch:{ IOException -> 0x01df }
                java.util.Map r10 = r3.d     // Catch:{ IOException -> 0x01df }
                java.util.Set r10 = r10.entrySet()     // Catch:{ IOException -> 0x01df }
                java.util.Iterator r10 = r10.iterator()     // Catch:{ IOException -> 0x01df }
            L_0x00ef:
                boolean r0 = r10.hasNext()     // Catch:{ IOException -> 0x01df }
                if (r0 == 0) goto L_0x010b
                java.lang.Object r0 = r10.next()     // Catch:{ IOException -> 0x01df }
                java.util.Map$Entry r0 = (java.util.Map.Entry) r0     // Catch:{ IOException -> 0x01df }
                java.lang.Object r2 = r0.getKey()     // Catch:{ IOException -> 0x01df }
                java.lang.String r2 = (java.lang.String) r2     // Catch:{ IOException -> 0x01df }
                java.lang.Object r0 = r0.getValue()     // Catch:{ IOException -> 0x01df }
                java.lang.String r0 = (java.lang.String) r0     // Catch:{ IOException -> 0x01df }
                r9.d(r2, r0)     // Catch:{ IOException -> 0x01df }
                goto L_0x00ef
            L_0x010b:
                org.jsoup.helper.HttpConnection$Response r9 = a((org.jsoup.Connection.Request) r9, (org.jsoup.helper.HttpConnection.Response) r3)     // Catch:{ IOException -> 0x01df }
                return r9
            L_0x0110:
                r10 = 200(0xc8, float:2.8E-43)
                if (r0 < r10) goto L_0x0118
                r10 = 400(0x190, float:5.6E-43)
                if (r0 < r10) goto L_0x011e
            L_0x0118:
                boolean r10 = r9.j()     // Catch:{ IOException -> 0x01df }
                if (r10 == 0) goto L_0x01cf
            L_0x011e:
                java.lang.String r10 = r3.i()     // Catch:{ IOException -> 0x01df }
                if (r10 == 0) goto L_0x014f
                boolean r0 = r9.k()     // Catch:{ IOException -> 0x01df }
                if (r0 != 0) goto L_0x014f
                java.lang.String r0 = "text/"
                boolean r0 = r10.startsWith(r0)     // Catch:{ IOException -> 0x01df }
                if (r0 != 0) goto L_0x014f
                java.util.regex.Pattern r0 = r     // Catch:{ IOException -> 0x01df }
                java.util.regex.Matcher r0 = r0.matcher(r10)     // Catch:{ IOException -> 0x01df }
                boolean r0 = r0.matches()     // Catch:{ IOException -> 0x01df }
                if (r0 == 0) goto L_0x013f
                goto L_0x014f
            L_0x013f:
                org.jsoup.UnsupportedMimeTypeException r0 = new org.jsoup.UnsupportedMimeTypeException     // Catch:{ IOException -> 0x01df }
                java.lang.String r2 = "Unhandled content type. Must be text/*, application/xml, or application/xhtml+xml"
                java.net.URL r9 = r9.a()     // Catch:{ IOException -> 0x01df }
                java.lang.String r9 = r9.toString()     // Catch:{ IOException -> 0x01df }
                r0.<init>(r2, r10, r9)     // Catch:{ IOException -> 0x01df }
                throw r0     // Catch:{ IOException -> 0x01df }
            L_0x014f:
                if (r10 == 0) goto L_0x0171
                java.util.regex.Pattern r0 = r     // Catch:{ IOException -> 0x01df }
                java.util.regex.Matcher r10 = r0.matcher(r10)     // Catch:{ IOException -> 0x01df }
                boolean r10 = r10.matches()     // Catch:{ IOException -> 0x01df }
                if (r10 == 0) goto L_0x0171
                boolean r10 = r9 instanceof org.jsoup.helper.HttpConnection.Request     // Catch:{ IOException -> 0x01df }
                if (r10 == 0) goto L_0x0171
                r10 = r9
                org.jsoup.helper.HttpConnection$Request r10 = (org.jsoup.helper.HttpConnection.Request) r10     // Catch:{ IOException -> 0x01df }
                boolean r10 = r10.n     // Catch:{ IOException -> 0x01df }
                if (r10 != 0) goto L_0x0171
                org.jsoup.parser.Parser r10 = org.jsoup.parser.Parser.f()     // Catch:{ IOException -> 0x01df }
                r9.a((org.jsoup.parser.Parser) r10)     // Catch:{ IOException -> 0x01df }
            L_0x0171:
                java.lang.String r10 = r3.m     // Catch:{ IOException -> 0x01df }
                java.lang.String r10 = org.jsoup.helper.DataUtil.a((java.lang.String) r10)     // Catch:{ IOException -> 0x01df }
                r3.l = r10     // Catch:{ IOException -> 0x01df }
                int r10 = r1.getContentLength()     // Catch:{ IOException -> 0x01df }
                if (r10 == 0) goto L_0x01c6
                org.jsoup.Connection$Method r10 = r9.b()     // Catch:{ IOException -> 0x01df }
                org.jsoup.Connection$Method r0 = org.jsoup.Connection.Method.HEAD     // Catch:{ IOException -> 0x01df }
                if (r10 == r0) goto L_0x01c6
                r3.k = r4     // Catch:{ IOException -> 0x01df }
                java.io.InputStream r10 = r1.getErrorStream()     // Catch:{ IOException -> 0x01df }
                if (r10 == 0) goto L_0x0194
                java.io.InputStream r10 = r1.getErrorStream()     // Catch:{ IOException -> 0x01df }
                goto L_0x0198
            L_0x0194:
                java.io.InputStream r10 = r1.getInputStream()     // Catch:{ IOException -> 0x01df }
            L_0x0198:
                r3.k = r10     // Catch:{ IOException -> 0x01df }
                java.lang.String r10 = "Content-Encoding"
                java.lang.String r0 = "gzip"
                boolean r10 = r3.c(r10, r0)     // Catch:{ IOException -> 0x01df }
                if (r10 == 0) goto L_0x01ad
                java.util.zip.GZIPInputStream r10 = new java.util.zip.GZIPInputStream     // Catch:{ IOException -> 0x01df }
                java.io.InputStream r0 = r3.k     // Catch:{ IOException -> 0x01df }
                r10.<init>(r0)     // Catch:{ IOException -> 0x01df }
                r3.k = r10     // Catch:{ IOException -> 0x01df }
            L_0x01ad:
                java.io.InputStream r10 = r3.k     // Catch:{ IOException -> 0x01df }
                r0 = 32768(0x8000, float:4.5918E-41)
                int r4 = r9.h()     // Catch:{ IOException -> 0x01df }
                org.jsoup.internal.ConstrainableInputStream r10 = org.jsoup.internal.ConstrainableInputStream.a(r10, r0, r4)     // Catch:{ IOException -> 0x01df }
                int r9 = r9.g()     // Catch:{ IOException -> 0x01df }
                long r7 = (long) r9     // Catch:{ IOException -> 0x01df }
                org.jsoup.internal.ConstrainableInputStream r9 = r10.a(r5, r7)     // Catch:{ IOException -> 0x01df }
                r3.k = r9     // Catch:{ IOException -> 0x01df }
                goto L_0x01cc
            L_0x01c6:
                java.nio.ByteBuffer r9 = org.jsoup.helper.DataUtil.a()     // Catch:{ IOException -> 0x01df }
                r3.j = r9     // Catch:{ IOException -> 0x01df }
            L_0x01cc:
                r3.n = r2
                return r3
            L_0x01cf:
                org.jsoup.HttpStatusException r10 = new org.jsoup.HttpStatusException     // Catch:{ IOException -> 0x01df }
                java.lang.String r2 = "HTTP error fetching URL"
                java.net.URL r9 = r9.a()     // Catch:{ IOException -> 0x01df }
                java.lang.String r9 = r9.toString()     // Catch:{ IOException -> 0x01df }
                r10.<init>(r2, r0, r9)     // Catch:{ IOException -> 0x01df }
                throw r10     // Catch:{ IOException -> 0x01df }
            L_0x01df:
                r9 = move-exception
                r1.disconnect()
                throw r9
            */
            throw new UnsupportedOperationException("Method not decompiled: org.jsoup.helper.HttpConnection.Response.a(org.jsoup.Connection$Request, org.jsoup.helper.HttpConnection$Response):org.jsoup.helper.HttpConnection$Response");
        }

        public int f() {
            return this.h;
        }

        public String g() {
            return this.i;
        }

        public String h() {
            return this.l;
        }

        /* renamed from: i */
        public Response h(String str) {
            this.l = str;
            return this;
        }

        public String i() {
            return this.m;
        }

        public Document j() throws IOException {
            Validate.a(this.n, "Request must be executed (with .execute(), .get(), or .post() before parsing response");
            if (this.j != null) {
                this.k = new ByteArrayInputStream(this.j.array());
                this.o = false;
            }
            Validate.b(this.o, "Input stream already read and parsed, cannot re-read.");
            Document b = DataUtil.b(this.k, this.l, this.f3650a.toExternalForm(), this.q.o());
            this.l = b.m().b().name();
            this.o = true;
            p();
            return b;
        }

        private void o() {
            Validate.a(this.n, "Request must be executed (with .execute(), .get(), or .post() before getting response body");
            if (this.j == null) {
                Validate.b(this.o, "Request has already been read (with .parse())");
                try {
                    this.j = DataUtil.a(this.k, this.q.h());
                    this.o = true;
                    p();
                } catch (IOException e2) {
                    throw new UncheckedIOException(e2);
                } catch (Throwable th) {
                    this.o = true;
                    p();
                    throw th;
                }
            }
        }

        public String k() {
            String str;
            o();
            if (this.l == null) {
                str = Charset.forName("UTF-8").decode(this.j).toString();
            } else {
                str = Charset.forName(this.l).decode(this.j).toString();
            }
            this.j.rewind();
            return str;
        }

        public byte[] l() {
            o();
            return this.j.array();
        }

        public Connection.Response m() {
            o();
            return this;
        }

        public BufferedInputStream n() {
            Validate.a(this.n, "Request must be executed (with .execute(), .get(), or .post() before getting response body");
            Validate.b(this.o, "Request has already been read");
            this.o = true;
            return ConstrainableInputStream.a(this.k, 32768, this.q.h());
        }

        private static HttpURLConnection b(Connection.Request request) throws IOException {
            URLConnection uRLConnection;
            if (request.f() == null) {
                uRLConnection = request.a().openConnection();
            } else {
                uRLConnection = request.a().openConnection(request.f());
            }
            HttpURLConnection httpURLConnection = (HttpURLConnection) uRLConnection;
            httpURLConnection.setRequestMethod(request.b().name());
            httpURLConnection.setInstanceFollowRedirects(false);
            httpURLConnection.setConnectTimeout(request.g());
            httpURLConnection.setReadTimeout(request.g() / 2);
            if ((httpURLConnection instanceof HttpsURLConnection) && !request.l()) {
                r();
                HttpsURLConnection httpsURLConnection = (HttpsURLConnection) httpURLConnection;
                httpsURLConnection.setSSLSocketFactory(f);
                httpsURLConnection.setHostnameVerifier(q());
            }
            if (request.b().hasBody()) {
                httpURLConnection.setDoOutput(true);
            }
            if (request.e().size() > 0) {
                httpURLConnection.addRequestProperty("Cookie", d(request));
            }
            for (Map.Entry next : request.d().entrySet()) {
                for (String addRequestProperty : (List) next.getValue()) {
                    httpURLConnection.addRequestProperty((String) next.getKey(), addRequestProperty);
                }
            }
            return httpURLConnection;
        }

        private void p() {
            if (this.k != null) {
                try {
                    this.k.close();
                } catch (IOException unused) {
                } catch (Throwable th) {
                    this.k = null;
                    throw th;
                }
                this.k = null;
            }
        }

        private static HostnameVerifier q() {
            return new HostnameVerifier() {
                public boolean verify(String str, SSLSession sSLSession) {
                    return true;
                }
            };
        }

        /* JADX WARNING: Can't wrap try/catch for region: R(7:5|6|7|8|9|10|11) */
        /* JADX WARNING: Missing exception handler attribute for start block: B:9:0x0028 */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        private static synchronized void r() throws java.io.IOException {
            /*
                java.lang.Class<org.jsoup.helper.HttpConnection$Response> r0 = org.jsoup.helper.HttpConnection.Response.class
                monitor-enter(r0)
                javax.net.ssl.SSLSocketFactory r1 = f     // Catch:{ all -> 0x0032 }
                if (r1 != 0) goto L_0x0030
                r1 = 1
                javax.net.ssl.TrustManager[] r1 = new javax.net.ssl.TrustManager[r1]     // Catch:{ all -> 0x0032 }
                r2 = 0
                org.jsoup.helper.HttpConnection$Response$2 r3 = new org.jsoup.helper.HttpConnection$Response$2     // Catch:{ all -> 0x0032 }
                r3.<init>()     // Catch:{ all -> 0x0032 }
                r1[r2] = r3     // Catch:{ all -> 0x0032 }
                java.lang.String r2 = "SSL"
                javax.net.ssl.SSLContext r2 = javax.net.ssl.SSLContext.getInstance(r2)     // Catch:{ KeyManagementException | NoSuchAlgorithmException -> 0x0028 }
                r3 = 0
                java.security.SecureRandom r4 = new java.security.SecureRandom     // Catch:{ KeyManagementException | NoSuchAlgorithmException -> 0x0028 }
                r4.<init>()     // Catch:{ KeyManagementException | NoSuchAlgorithmException -> 0x0028 }
                r2.init(r3, r1, r4)     // Catch:{ KeyManagementException | NoSuchAlgorithmException -> 0x0028 }
                javax.net.ssl.SSLSocketFactory r1 = r2.getSocketFactory()     // Catch:{ KeyManagementException | NoSuchAlgorithmException -> 0x0028 }
                f = r1     // Catch:{ KeyManagementException | NoSuchAlgorithmException -> 0x0028 }
                goto L_0x0030
            L_0x0028:
                java.io.IOException r1 = new java.io.IOException     // Catch:{ all -> 0x0032 }
                java.lang.String r2 = "Can't create unsecure trust manager"
                r1.<init>(r2)     // Catch:{ all -> 0x0032 }
                throw r1     // Catch:{ all -> 0x0032 }
            L_0x0030:
                monitor-exit(r0)
                return
            L_0x0032:
                r1 = move-exception
                monitor-exit(r0)
                throw r1
            */
            throw new UnsupportedOperationException("Method not decompiled: org.jsoup.helper.HttpConnection.Response.r():void");
        }

        private void a(HttpURLConnection httpURLConnection, Connection.Response response) throws IOException {
            this.b = Connection.Method.valueOf(httpURLConnection.getRequestMethod());
            this.f3650a = httpURLConnection.getURL();
            this.h = httpURLConnection.getResponseCode();
            this.i = httpURLConnection.getResponseMessage();
            this.m = httpURLConnection.getContentType();
            a((Map<String, List<String>>) a(httpURLConnection));
            if (response != null) {
                for (Map.Entry next : response.e().entrySet()) {
                    if (!f((String) next.getKey())) {
                        d((String) next.getKey(), (String) next.getValue());
                    }
                }
            }
        }

        private static LinkedHashMap<String, List<String>> a(HttpURLConnection httpURLConnection) {
            LinkedHashMap<String, List<String>> linkedHashMap = new LinkedHashMap<>();
            int i2 = 0;
            while (true) {
                String headerFieldKey = httpURLConnection.getHeaderFieldKey(i2);
                String headerField = httpURLConnection.getHeaderField(i2);
                if (headerFieldKey == null && headerField == null) {
                    return linkedHashMap;
                }
                i2++;
                if (!(headerFieldKey == null || headerField == null)) {
                    if (linkedHashMap.containsKey(headerFieldKey)) {
                        linkedHashMap.get(headerFieldKey).add(headerField);
                    } else {
                        ArrayList arrayList = new ArrayList();
                        arrayList.add(headerField);
                        linkedHashMap.put(headerFieldKey, arrayList);
                    }
                }
            }
        }

        /* access modifiers changed from: package-private */
        public void a(Map<String, List<String>> map) {
            for (Map.Entry next : map.entrySet()) {
                String str = (String) next.getKey();
                if (str != null) {
                    List<String> list = (List) next.getValue();
                    if (str.equalsIgnoreCase("Set-Cookie")) {
                        for (String str2 : list) {
                            if (str2 != null) {
                                TokenQueue tokenQueue = new TokenQueue(str2);
                                String trim = tokenQueue.h("=").trim();
                                String trim2 = tokenQueue.f(i.b).trim();
                                if (trim.length() > 0) {
                                    d(trim, trim2);
                                }
                            }
                        }
                    }
                    for (String b : list) {
                        b(str, b);
                    }
                }
            }
        }

        private static String c(Connection.Request request) {
            if (!request.c("Content-Type")) {
                if (HttpConnection.c(request)) {
                    String b = DataUtil.b();
                    request.a("Content-Type", "multipart/form-data; boundary=" + b);
                    return b;
                }
                request.a("Content-Type", "application/x-www-form-urlencoded; charset=" + request.p());
            }
            return null;
        }

        private static void a(Connection.Request request, OutputStream outputStream, String str) throws IOException {
            Collection<Connection.KeyVal> m2 = request.m();
            BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream, request.p()));
            if (str != null) {
                for (Connection.KeyVal next : m2) {
                    bufferedWriter.write(HelpFormatter.f);
                    bufferedWriter.write(str);
                    bufferedWriter.write("\r\n");
                    bufferedWriter.write("Content-Disposition: form-data; name=\"");
                    bufferedWriter.write(HttpConnection.j(next.a()));
                    bufferedWriter.write("\"");
                    if (next.d()) {
                        bufferedWriter.write("; filename=\"");
                        bufferedWriter.write(HttpConnection.j(next.b()));
                        bufferedWriter.write("\"\r\nContent-Type: ");
                        bufferedWriter.write(next.e() != null ? next.e() : "application/octet-stream");
                        bufferedWriter.write("\r\n\r\n");
                        bufferedWriter.flush();
                        DataUtil.a(next.c(), outputStream);
                        outputStream.flush();
                    } else {
                        bufferedWriter.write("\r\n\r\n");
                        bufferedWriter.write(next.b());
                    }
                    bufferedWriter.write("\r\n");
                }
                bufferedWriter.write(HelpFormatter.f);
                bufferedWriter.write(str);
                bufferedWriter.write(HelpFormatter.f);
            } else if (request.n() != null) {
                bufferedWriter.write(request.n());
            } else {
                boolean z = true;
                for (Connection.KeyVal next2 : m2) {
                    if (!z) {
                        bufferedWriter.append(Typography.c);
                    } else {
                        z = false;
                    }
                    bufferedWriter.write(URLEncoder.encode(next2.a(), request.p()));
                    bufferedWriter.write(61);
                    bufferedWriter.write(URLEncoder.encode(next2.b(), request.p()));
                }
            }
            bufferedWriter.close();
        }

        private static String d(Connection.Request request) {
            StringBuilder a2 = StringUtil.a();
            boolean z = true;
            for (Map.Entry next : request.e().entrySet()) {
                if (!z) {
                    a2.append("; ");
                } else {
                    z = false;
                }
                a2.append((String) next.getKey());
                a2.append('=');
                a2.append((String) next.getValue());
            }
            return a2.toString();
        }

        private static void e(Connection.Request request) throws IOException {
            boolean z;
            URL a2 = request.a();
            StringBuilder a3 = StringUtil.a();
            a3.append(a2.getProtocol());
            a3.append("://");
            a3.append(a2.getAuthority());
            a3.append(a2.getPath());
            a3.append("?");
            if (a2.getQuery() != null) {
                a3.append(a2.getQuery());
                z = false;
            } else {
                z = true;
            }
            for (Connection.KeyVal next : request.m()) {
                Validate.b(next.d(), "InputStream data not supported in URL query string.");
                if (!z) {
                    a3.append(Typography.c);
                } else {
                    z = false;
                }
                a3.append(URLEncoder.encode(next.a(), "UTF-8"));
                a3.append('=');
                a3.append(URLEncoder.encode(next.b(), "UTF-8"));
            }
            request.a(new URL(a3.toString()));
            request.m().clear();
        }
    }

    /* access modifiers changed from: private */
    public static boolean c(Connection.Request request) {
        for (Connection.KeyVal d2 : request.m()) {
            if (d2.d()) {
                return true;
            }
        }
        return false;
    }

    public static class KeyVal implements Connection.KeyVal {

        /* renamed from: a  reason: collision with root package name */
        private String f3651a;
        private String b;
        private InputStream c;
        private String d;

        public static KeyVal a(String str, String str2) {
            return new KeyVal().a(str).b(str2);
        }

        public static KeyVal a(String str, String str2, InputStream inputStream) {
            return new KeyVal().a(str).b(str2).a(inputStream);
        }

        private KeyVal() {
        }

        /* renamed from: d */
        public KeyVal a(String str) {
            Validate.a(str, "Data key must not be empty");
            this.f3651a = str;
            return this;
        }

        public String a() {
            return this.f3651a;
        }

        /* renamed from: e */
        public KeyVal b(String str) {
            Validate.a((Object) str, "Data value must not be null");
            this.b = str;
            return this;
        }

        public String b() {
            return this.b;
        }

        /* renamed from: b */
        public KeyVal a(InputStream inputStream) {
            Validate.a((Object) this.b, "Data input stream must not be null");
            this.c = inputStream;
            return this;
        }

        public InputStream c() {
            return this.c;
        }

        public boolean d() {
            return this.c != null;
        }

        public Connection.KeyVal c(String str) {
            Validate.a(str);
            this.d = str;
            return this;
        }

        public String e() {
            return this.d;
        }

        public String toString() {
            return this.f3651a + "=" + this.b;
        }
    }
}
