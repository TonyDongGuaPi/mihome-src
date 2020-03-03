package com.xiaomi.smarthome.httpserver;

import com.alipay.sdk.sys.a;
import com.alipay.sdk.util.i;
import com.google.code.microlog4android.appender.DatagramAppender;
import com.taobao.weex.el.parse.Operators;
import com.xiaomi.youpin.network.annotation.ContentType;
import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.Closeable;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.io.PushbackInputStream;
import java.io.RandomAccessFile;
import java.io.UnsupportedEncodingException;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;
import java.net.SocketTimeoutException;
import java.net.URLDecoder;
import java.nio.ByteBuffer;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Set;
import java.util.StringTokenizer;
import java.util.TimeZone;

public abstract class NanoHTTPD {

    /* renamed from: a  reason: collision with root package name */
    public static final int f18380a = 5000;
    public static final String b = "text/plain";
    public static final String c = "text/html";
    private static final String d = "NanoHttpd.QUERY_STRING";
    private final String e;
    private final int f;
    /* access modifiers changed from: private */
    public ServerSocket g;
    private Set<Socket> h;
    private Thread i;
    /* access modifiers changed from: private */
    public AsyncRunner j;
    /* access modifiers changed from: private */
    public TempFileManagerFactory k;

    public interface AsyncRunner {
        void a(Runnable runnable);
    }

    public interface IHTTPSession {
        void a() throws IOException;

        void a(Map<String, String> map) throws IOException, ResponseException;

        Map<String, String> b();

        String c();

        Map<String, String> d();

        String e();

        Method f();

        InputStream g();

        CookieHandler h();
    }

    public interface TempFile {
        OutputStream a() throws Exception;

        void b() throws Exception;

        String c();
    }

    public interface TempFileManager {
        TempFile a() throws Exception;

        void b();
    }

    public interface TempFileManagerFactory {
        TempFileManager a();
    }

    public NanoHTTPD(int i2) {
        this((String) null, i2);
    }

    public NanoHTTPD(String str, int i2) {
        this.h = new HashSet();
        this.e = str;
        this.f = i2;
        a((TempFileManagerFactory) new DefaultTempFileManagerFactory());
        a((AsyncRunner) new DefaultAsyncRunner());
    }

    /* access modifiers changed from: private */
    public static void b(Closeable closeable) {
        if (closeable != null) {
            try {
                closeable.close();
            } catch (IOException unused) {
            }
        }
    }

    /* access modifiers changed from: private */
    public static void d(Socket socket) {
        if (socket != null) {
            try {
                socket.close();
            } catch (IOException unused) {
            }
        }
    }

    private static void a(ServerSocket serverSocket) {
        if (serverSocket != null) {
            try {
                serverSocket.close();
            } catch (IOException unused) {
            }
        }
    }

    public void a() throws IOException {
        this.g = new ServerSocket();
        this.g.bind(this.e != null ? new InetSocketAddress(this.e, this.f) : new InetSocketAddress(this.f));
        this.i = new Thread(new Runnable() {
            public void run() {
                do {
                    try {
                        final Socket accept = NanoHTTPD.this.g.accept();
                        NanoHTTPD.this.a(accept);
                        accept.setSoTimeout(5000);
                        final InputStream inputStream = accept.getInputStream();
                        NanoHTTPD.this.j.a(new Runnable() {
                            /* JADX WARNING: Removed duplicated region for block: B:16:0x0041 A[Catch:{ all -> 0x0061 }] */
                            /* Code decompiled incorrectly, please refer to instructions dump. */
                            public void run() {
                                /*
                                    r9 = this;
                                    r0 = 0
                                    java.net.Socket r1 = r0     // Catch:{ Exception -> 0x0039, all -> 0x0034 }
                                    java.io.OutputStream r1 = r1.getOutputStream()     // Catch:{ Exception -> 0x0039, all -> 0x0034 }
                                    com.xiaomi.smarthome.httpserver.NanoHTTPD$1 r0 = com.xiaomi.smarthome.httpserver.NanoHTTPD.AnonymousClass1.this     // Catch:{ Exception -> 0x0032 }
                                    com.xiaomi.smarthome.httpserver.NanoHTTPD r0 = com.xiaomi.smarthome.httpserver.NanoHTTPD.this     // Catch:{ Exception -> 0x0032 }
                                    com.xiaomi.smarthome.httpserver.NanoHTTPD$TempFileManagerFactory r0 = r0.k     // Catch:{ Exception -> 0x0032 }
                                    com.xiaomi.smarthome.httpserver.NanoHTTPD$TempFileManager r4 = r0.a()     // Catch:{ Exception -> 0x0032 }
                                    com.xiaomi.smarthome.httpserver.NanoHTTPD$HTTPSession r0 = new com.xiaomi.smarthome.httpserver.NanoHTTPD$HTTPSession     // Catch:{ Exception -> 0x0032 }
                                    com.xiaomi.smarthome.httpserver.NanoHTTPD$1 r2 = com.xiaomi.smarthome.httpserver.NanoHTTPD.AnonymousClass1.this     // Catch:{ Exception -> 0x0032 }
                                    com.xiaomi.smarthome.httpserver.NanoHTTPD r3 = com.xiaomi.smarthome.httpserver.NanoHTTPD.this     // Catch:{ Exception -> 0x0032 }
                                    java.io.InputStream r5 = r1     // Catch:{ Exception -> 0x0032 }
                                    java.net.Socket r2 = r0     // Catch:{ Exception -> 0x0032 }
                                    java.net.InetAddress r7 = r2.getInetAddress()     // Catch:{ Exception -> 0x0032 }
                                    r2 = r0
                                    r6 = r1
                                    r2.<init>(r4, r5, r6, r7)     // Catch:{ Exception -> 0x0032 }
                                L_0x0026:
                                    java.net.Socket r2 = r0     // Catch:{ Exception -> 0x0032 }
                                    boolean r2 = r2.isClosed()     // Catch:{ Exception -> 0x0032 }
                                    if (r2 != 0) goto L_0x004a
                                    r0.a()     // Catch:{ Exception -> 0x0032 }
                                    goto L_0x0026
                                L_0x0032:
                                    r0 = move-exception
                                    goto L_0x003d
                                L_0x0034:
                                    r1 = move-exception
                                    r8 = r1
                                    r1 = r0
                                    r0 = r8
                                    goto L_0x0062
                                L_0x0039:
                                    r1 = move-exception
                                    r8 = r1
                                    r1 = r0
                                    r0 = r8
                                L_0x003d:
                                    boolean r2 = r0 instanceof java.net.SocketException     // Catch:{ all -> 0x0061 }
                                    if (r2 == 0) goto L_0x004a
                                    java.lang.String r2 = "NanoHttpd Shutdown"
                                    java.lang.String r0 = r0.getMessage()     // Catch:{ all -> 0x0061 }
                                    r2.equals(r0)     // Catch:{ all -> 0x0061 }
                                L_0x004a:
                                    com.xiaomi.smarthome.httpserver.NanoHTTPD.b((java.io.Closeable) r1)
                                    java.io.InputStream r0 = r1
                                    com.xiaomi.smarthome.httpserver.NanoHTTPD.b((java.io.Closeable) r0)
                                    java.net.Socket r0 = r0
                                    com.xiaomi.smarthome.httpserver.NanoHTTPD.d(r0)
                                    com.xiaomi.smarthome.httpserver.NanoHTTPD$1 r0 = com.xiaomi.smarthome.httpserver.NanoHTTPD.AnonymousClass1.this
                                    com.xiaomi.smarthome.httpserver.NanoHTTPD r0 = com.xiaomi.smarthome.httpserver.NanoHTTPD.this
                                    java.net.Socket r1 = r0
                                    r0.b((java.net.Socket) r1)
                                    return
                                L_0x0061:
                                    r0 = move-exception
                                L_0x0062:
                                    com.xiaomi.smarthome.httpserver.NanoHTTPD.b((java.io.Closeable) r1)
                                    java.io.InputStream r1 = r1
                                    com.xiaomi.smarthome.httpserver.NanoHTTPD.b((java.io.Closeable) r1)
                                    java.net.Socket r1 = r0
                                    com.xiaomi.smarthome.httpserver.NanoHTTPD.d(r1)
                                    com.xiaomi.smarthome.httpserver.NanoHTTPD$1 r1 = com.xiaomi.smarthome.httpserver.NanoHTTPD.AnonymousClass1.this
                                    com.xiaomi.smarthome.httpserver.NanoHTTPD r1 = com.xiaomi.smarthome.httpserver.NanoHTTPD.this
                                    java.net.Socket r2 = r0
                                    r1.b((java.net.Socket) r2)
                                    throw r0
                                */
                                throw new UnsupportedOperationException("Method not decompiled: com.xiaomi.smarthome.httpserver.NanoHTTPD.AnonymousClass1.AnonymousClass1.run():void");
                            }
                        });
                    } catch (IOException unused) {
                    }
                } while (!NanoHTTPD.this.g.isClosed());
            }
        });
        this.i.setDaemon(true);
        this.i.setName("NanoHttpd Main Listener");
        this.i.start();
    }

    public void b() {
        try {
            a(this.g);
            c();
            if (this.i != null) {
                this.i.join();
            }
        } catch (Exception unused) {
        }
    }

    public synchronized void a(Socket socket) {
        this.h.add(socket);
    }

    public synchronized void b(Socket socket) {
        this.h.remove(socket);
    }

    public synchronized void c() {
        for (Socket d2 : this.h) {
            d(d2);
        }
    }

    public final int d() {
        if (this.g == null) {
            return -1;
        }
        return this.g.getLocalPort();
    }

    public final boolean e() {
        return (this.g == null || this.i == null) ? false : true;
    }

    public final boolean f() {
        return e() && !this.g.isClosed() && this.i.isAlive();
    }

    @Deprecated
    public Response a(String str, Method method, Map<String, String> map, Map<String, String> map2, Map<String, String> map3) {
        return new Response((Response.IStatus) Response.Status.NOT_FOUND, "text/plain", "Not Found");
    }

    public Response a(IHTTPSession iHTTPSession) {
        HashMap hashMap = new HashMap();
        Method f2 = iHTTPSession.f();
        if (Method.PUT.equals(f2) || Method.POST.equals(f2)) {
            try {
                iHTTPSession.a(hashMap);
            } catch (IOException e2) {
                Response.Status status = Response.Status.INTERNAL_ERROR;
                return new Response((Response.IStatus) status, "text/plain", "SERVER INTERNAL ERROR: IOException: " + e2.getMessage());
            } catch (ResponseException e3) {
                return new Response((Response.IStatus) e3.getStatus(), "text/plain", e3.getMessage());
            }
        }
        Map<String, String> b2 = iHTTPSession.b();
        b2.put(d, iHTTPSession.c());
        return a(iHTTPSession.e(), f2, iHTTPSession.d(), b2, hashMap);
    }

    /* access modifiers changed from: protected */
    public String a(String str) {
        try {
            return URLDecoder.decode(str, "UTF8");
        } catch (UnsupportedEncodingException unused) {
            return null;
        }
    }

    /* access modifiers changed from: protected */
    public Map<String, List<String>> a(Map<String, String> map) {
        return b(map.get(d));
    }

    /* access modifiers changed from: protected */
    public Map<String, List<String>> b(String str) {
        HashMap hashMap = new HashMap();
        if (str != null) {
            StringTokenizer stringTokenizer = new StringTokenizer(str, a.b);
            while (stringTokenizer.hasMoreTokens()) {
                String nextToken = stringTokenizer.nextToken();
                int indexOf = nextToken.indexOf(61);
                String trim = (indexOf >= 0 ? a(nextToken.substring(0, indexOf)) : a(nextToken)).trim();
                if (!hashMap.containsKey(trim)) {
                    hashMap.put(trim, new ArrayList());
                }
                String a2 = indexOf >= 0 ? a(nextToken.substring(indexOf + 1)) : null;
                if (a2 != null) {
                    ((List) hashMap.get(trim)).add(a2);
                }
            }
        }
        return hashMap;
    }

    public void a(AsyncRunner asyncRunner) {
        this.j = asyncRunner;
    }

    public void a(TempFileManagerFactory tempFileManagerFactory) {
        this.k = tempFileManagerFactory;
    }

    public enum Method {
        GET,
        PUT,
        POST,
        DELETE,
        HEAD,
        OPTIONS;

        static Method lookup(String str) {
            for (Method method : values()) {
                if (method.toString().equalsIgnoreCase(str)) {
                    return method;
                }
            }
            return null;
        }
    }

    public static class DefaultAsyncRunner implements AsyncRunner {

        /* renamed from: a  reason: collision with root package name */
        private long f18385a;

        public void a(Runnable runnable) {
            this.f18385a++;
            Thread thread = new Thread(runnable);
            thread.setDaemon(true);
            thread.setName("NanoHttpd Request Processor (#" + this.f18385a + Operators.BRACKET_END_STR);
            thread.start();
        }
    }

    public static class DefaultTempFileManager implements TempFileManager {

        /* renamed from: a  reason: collision with root package name */
        private final String f18387a = System.getProperty("java.io.tmpdir");
        private final List<TempFile> b = new ArrayList();

        public TempFile a() throws Exception {
            DefaultTempFile defaultTempFile = new DefaultTempFile(this.f18387a);
            this.b.add(defaultTempFile);
            return defaultTempFile;
        }

        public void b() {
            for (TempFile b2 : this.b) {
                try {
                    b2.b();
                } catch (Exception unused) {
                }
            }
            this.b.clear();
        }
    }

    public static class DefaultTempFile implements TempFile {

        /* renamed from: a  reason: collision with root package name */
        private File f18386a;
        private OutputStream b = new FileOutputStream(this.f18386a);

        public DefaultTempFile(String str) throws IOException {
            this.f18386a = File.createTempFile("NanoHTTPD-", "", new File(str));
        }

        public OutputStream a() throws Exception {
            return this.b;
        }

        public void b() throws Exception {
            NanoHTTPD.b((Closeable) this.b);
            this.f18386a.delete();
        }

        public String c() {
            return this.f18386a.getAbsolutePath();
        }
    }

    public static class Response {

        /* renamed from: a  reason: collision with root package name */
        private IStatus f18390a;
        private String b;
        private InputStream c;
        private Map<String, String> d;
        private Method e;
        private boolean f;

        public interface IStatus {
            String getDescription();

            int getRequestStatus();
        }

        public Response(String str) {
            this((IStatus) Status.OK, NanoHTTPD.c, str);
        }

        public Response(IStatus iStatus, String str, InputStream inputStream) {
            this.d = new HashMap();
            this.f18390a = iStatus;
            this.b = str;
            this.c = inputStream;
        }

        public Response(IStatus iStatus, String str, String str2) {
            ByteArrayInputStream byteArrayInputStream;
            this.d = new HashMap();
            this.f18390a = iStatus;
            this.b = str;
            if (str2 != null) {
                try {
                    byteArrayInputStream = new ByteArrayInputStream(str2.getBytes("UTF-8"));
                } catch (UnsupportedEncodingException unused) {
                    return;
                }
            } else {
                byteArrayInputStream = null;
            }
            this.c = byteArrayInputStream;
        }

        public void a(String str, String str2) {
            this.d.put(str, str2);
        }

        public String a(String str) {
            return this.d.get(str);
        }

        /* access modifiers changed from: protected */
        public void a(OutputStream outputStream) {
            String str = this.b;
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("E, d MMM yyyy HH:mm:ss 'GMT'", Locale.US);
            simpleDateFormat.setTimeZone(TimeZone.getTimeZone("GMT"));
            try {
                if (this.f18390a != null) {
                    PrintWriter printWriter = new PrintWriter(outputStream);
                    printWriter.print("HTTP/1.1 " + this.f18390a.getDescription() + " \r\n");
                    if (str != null) {
                        printWriter.print("Content-Type: " + str + "\r\n");
                    }
                    if (this.d == null || this.d.get("Date") == null) {
                        printWriter.print("Date: " + simpleDateFormat.format(new Date()) + "\r\n");
                    }
                    if (this.d != null) {
                        for (String next : this.d.keySet()) {
                            printWriter.print(next + ": " + this.d.get(next) + "\r\n");
                        }
                    }
                    a(printWriter, this.d);
                    if (this.e == Method.HEAD || !this.f) {
                        int available = this.c != null ? this.c.available() : 0;
                        a(printWriter, this.d, available);
                        printWriter.print("\r\n");
                        printWriter.flush();
                        a(outputStream, available);
                    } else {
                        a(outputStream, printWriter);
                    }
                    outputStream.flush();
                    NanoHTTPD.b((Closeable) this.c);
                    return;
                }
                throw new Error("sendResponse(): Status can't be null.");
            } catch (IOException unused) {
            }
        }

        /* access modifiers changed from: protected */
        public void a(PrintWriter printWriter, Map<String, String> map, int i) {
            if (!a(map, "content-length")) {
                printWriter.print("Content-Length: " + i + "\r\n");
            }
        }

        /* access modifiers changed from: protected */
        public void a(PrintWriter printWriter, Map<String, String> map) {
            if (!a(map, "connection")) {
                printWriter.print("Connection: keep-alive\r\n");
            }
        }

        private boolean a(Map<String, String> map, String str) {
            boolean z = false;
            for (String equalsIgnoreCase : map.keySet()) {
                z |= equalsIgnoreCase.equalsIgnoreCase(str);
            }
            return z;
        }

        private void a(OutputStream outputStream, PrintWriter printWriter) throws IOException {
            printWriter.print("Transfer-Encoding: chunked\r\n");
            printWriter.print("\r\n");
            printWriter.flush();
            byte[] bytes = "\r\n".getBytes();
            byte[] bArr = new byte[16384];
            while (true) {
                int read = this.c.read(bArr);
                if (read > 0) {
                    outputStream.write(String.format("%x\r\n", new Object[]{Integer.valueOf(read)}).getBytes());
                    outputStream.write(bArr, 0, read);
                    outputStream.write(bytes);
                } else {
                    outputStream.write(String.format("0\r\n\r\n", new Object[0]).getBytes());
                    return;
                }
            }
        }

        private void a(OutputStream outputStream, int i) throws IOException {
            if (this.e != Method.HEAD && this.c != null) {
                byte[] bArr = new byte[16384];
                while (i > 0) {
                    int read = this.c.read(bArr, 0, i > 16384 ? 16384 : i);
                    if (read > 0) {
                        outputStream.write(bArr, 0, read);
                        i -= read;
                    } else {
                        return;
                    }
                }
            }
        }

        public IStatus c() {
            return this.f18390a;
        }

        public void a(Status status) {
            this.f18390a = status;
        }

        public String d() {
            return this.b;
        }

        public void b(String str) {
            this.b = str;
        }

        public InputStream e() {
            return this.c;
        }

        public void a(InputStream inputStream) {
            this.c = inputStream;
        }

        public Method f() {
            return this.e;
        }

        public void a(Method method) {
            this.e = method;
        }

        public void a(boolean z) {
            this.f = z;
        }

        public enum Status implements IStatus {
            SWITCH_PROTOCOL(101, "Switching Protocols"),
            OK(200, "OK"),
            CREATED(201, "Created"),
            ACCEPTED(202, "Accepted"),
            NO_CONTENT(204, "No Content"),
            PARTIAL_CONTENT(206, "Partial Content"),
            REDIRECT(301, "Moved Permanently"),
            NOT_MODIFIED(304, "Not Modified"),
            BAD_REQUEST(400, "Bad Request"),
            UNAUTHORIZED(401, "Unauthorized"),
            FORBIDDEN(403, "Forbidden"),
            NOT_FOUND(404, "Not Found"),
            METHOD_NOT_ALLOWED(405, "Method Not Allowed"),
            RANGE_NOT_SATISFIABLE(416, "Requested Range Not Satisfiable"),
            INTERNAL_ERROR(500, "Internal Server Error");
            
            private final String description;
            private final int requestStatus;

            private Status(int i, String str) {
                this.requestStatus = i;
                this.description = str;
            }

            public int getRequestStatus() {
                return this.requestStatus;
            }

            public String getDescription() {
                return "" + this.requestStatus + " " + this.description;
            }
        }
    }

    public static final class ResponseException extends Exception {
        private final Response.Status status;

        public ResponseException(Response.Status status2, String str) {
            super(str);
            this.status = status2;
        }

        public ResponseException(Response.Status status2, String str, Exception exc) {
            super(str, exc);
            this.status = status2;
        }

        public Response.Status getStatus() {
            return this.status;
        }
    }

    private class DefaultTempFileManagerFactory implements TempFileManagerFactory {
        private DefaultTempFileManagerFactory() {
        }

        public TempFileManager a() {
            return new DefaultTempFileManager();
        }
    }

    protected class HTTPSession implements IHTTPSession {

        /* renamed from: a  reason: collision with root package name */
        public static final int f18389a = 8192;
        private final TempFileManager c;
        private final OutputStream d;
        private PushbackInputStream e;
        private int f;
        private int g;
        private String h;
        private Method i;
        private Map<String, String> j;
        private Map<String, String> k;
        private CookieHandler l;
        private String m;

        public HTTPSession(TempFileManager tempFileManager, InputStream inputStream, OutputStream outputStream) {
            this.c = tempFileManager;
            this.e = new PushbackInputStream(inputStream, 8192);
            this.d = outputStream;
        }

        public HTTPSession(TempFileManager tempFileManager, InputStream inputStream, OutputStream outputStream, InetAddress inetAddress) {
            this.c = tempFileManager;
            this.e = new PushbackInputStream(inputStream, 8192);
            this.d = outputStream;
            String str = (inetAddress.isLoopbackAddress() || inetAddress.isAnyLocalAddress()) ? DatagramAppender.DEFAULT_HOST : inetAddress.getHostAddress().toString();
            this.k = new HashMap();
            this.k.put("remote-addr", str);
            this.k.put("http-client-ip", str);
        }

        public void a() throws IOException {
            try {
                byte[] bArr = new byte[8192];
                this.f = 0;
                this.g = 0;
                int read = this.e.read(bArr, 0, 8192);
                if (read != -1) {
                    while (true) {
                        if (read <= 0) {
                            break;
                        }
                        this.g += read;
                        this.f = a(bArr, this.g);
                        if (this.f > 0) {
                            break;
                        }
                        read = this.e.read(bArr, this.g, 8192 - this.g);
                    }
                    if (this.f < this.g) {
                        this.e.unread(bArr, this.f, this.g - this.f);
                    }
                    this.j = new HashMap();
                    if (this.k == null) {
                        this.k = new HashMap();
                    }
                    BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(new ByteArrayInputStream(bArr, 0, this.g)));
                    HashMap hashMap = new HashMap();
                    a(bufferedReader, hashMap, this.j, this.k);
                    this.i = Method.lookup((String) hashMap.get("method"));
                    if (this.i != null) {
                        this.h = (String) hashMap.get("uri");
                        this.l = new CookieHandler(this.k);
                        Response a2 = NanoHTTPD.this.a((IHTTPSession) this);
                        if (a2 != null) {
                            this.l.a(a2);
                            a2.a(this.i);
                            a2.a(this.d);
                            this.c.b();
                            return;
                        }
                        throw new ResponseException(Response.Status.INTERNAL_ERROR, "SERVER INTERNAL ERROR: Serve() returned a null response.");
                    }
                    throw new ResponseException(Response.Status.BAD_REQUEST, "BAD REQUEST: Syntax error.");
                }
                NanoHTTPD.b((Closeable) this.e);
                NanoHTTPD.b((Closeable) this.d);
                throw new SocketException("NanoHttpd Shutdown");
            } catch (Exception unused) {
                NanoHTTPD.b((Closeable) this.e);
                NanoHTTPD.b((Closeable) this.d);
                throw new SocketException("NanoHttpd Shutdown");
            } catch (SocketException e2) {
                throw e2;
            } catch (SocketTimeoutException e3) {
                throw e3;
            } catch (IOException e4) {
                new Response((Response.IStatus) Response.Status.INTERNAL_ERROR, "text/plain", "SERVER INTERNAL ERROR: IOException: " + e4.getMessage()).a(this.d);
                NanoHTTPD.b((Closeable) this.d);
            } catch (ResponseException e5) {
                try {
                    new Response((Response.IStatus) e5.getStatus(), "text/plain", e5.getMessage()).a(this.d);
                    NanoHTTPD.b((Closeable) this.d);
                } catch (Throwable th) {
                    this.c.b();
                    throw th;
                }
            }
        }

        public void a(Map<String, String> map) throws IOException, ResponseException {
            BufferedReader bufferedReader;
            RandomAccessFile randomAccessFile;
            long j2;
            Map<String, String> map2 = map;
            StringTokenizer stringTokenizer = null;
            try {
                randomAccessFile = i();
                try {
                    if (this.k.containsKey("content-length")) {
                        j2 = (long) Integer.parseInt(this.k.get("content-length"));
                    } else {
                        j2 = this.f < this.g ? (long) (this.g - this.f) : 0;
                    }
                    byte[] bArr = new byte[512];
                    while (this.g >= 0 && j2 > 0) {
                        this.g = this.e.read(bArr, 0, (int) Math.min(j2, 512));
                        j2 -= (long) this.g;
                        if (this.g > 0) {
                            randomAccessFile.write(bArr, 0, this.g);
                        }
                    }
                    MappedByteBuffer map3 = randomAccessFile.getChannel().map(FileChannel.MapMode.READ_ONLY, 0, randomAccessFile.length());
                    randomAccessFile.seek(0);
                    bufferedReader = new BufferedReader(new InputStreamReader(new FileInputStream(randomAccessFile.getFD())));
                    try {
                        if (Method.POST.equals(this.i)) {
                            String str = "";
                            String str2 = this.k.get("content-type");
                            if (str2 != null) {
                                stringTokenizer = new StringTokenizer(str2, ",; ");
                                if (stringTokenizer.hasMoreTokens()) {
                                    str = stringTokenizer.nextToken();
                                }
                            }
                            if (!ContentType.FORM_DATA.equalsIgnoreCase(str)) {
                                String str3 = "";
                                StringBuilder sb = new StringBuilder();
                                char[] cArr = new char[512];
                                for (int read = bufferedReader.read(cArr); read >= 0 && !str3.endsWith("\r\n"); read = bufferedReader.read(cArr)) {
                                    str3 = String.valueOf(cArr, 0, read);
                                    sb.append(str3);
                                }
                                String trim = sb.toString().trim();
                                if ("application/x-www-form-urlencoded".equalsIgnoreCase(str)) {
                                    a(trim, this.j);
                                } else if (trim.length() != 0) {
                                    map2.put("postData", trim);
                                }
                            } else if (stringTokenizer.hasMoreTokens()) {
                                String substring = str2.substring(str2.indexOf("boundary=") + "boundary=".length(), str2.length());
                                if (substring.startsWith("\"") && substring.endsWith("\"")) {
                                    substring = substring.substring(1, substring.length() - 1);
                                }
                                a(substring, map3, bufferedReader, this.j, map);
                            } else {
                                throw new ResponseException(Response.Status.BAD_REQUEST, "BAD REQUEST: Content type is multipart/form-data but boundary missing. Usage: GET /example/file.html");
                            }
                        } else if (Method.PUT.equals(this.i)) {
                            map2.put("content", a(map3, 0, map3.limit()));
                        }
                        NanoHTTPD.b((Closeable) randomAccessFile);
                        NanoHTTPD.b((Closeable) bufferedReader);
                    } catch (Throwable th) {
                        th = th;
                        NanoHTTPD.b((Closeable) randomAccessFile);
                        NanoHTTPD.b((Closeable) bufferedReader);
                        throw th;
                    }
                } catch (Throwable th2) {
                    th = th2;
                    bufferedReader = null;
                    NanoHTTPD.b((Closeable) randomAccessFile);
                    NanoHTTPD.b((Closeable) bufferedReader);
                    throw th;
                }
            } catch (Throwable th3) {
                th = th3;
                randomAccessFile = null;
                bufferedReader = null;
                NanoHTTPD.b((Closeable) randomAccessFile);
                NanoHTTPD.b((Closeable) bufferedReader);
                throw th;
            }
        }

        private void a(BufferedReader bufferedReader, Map<String, String> map, Map<String, String> map2, Map<String, String> map3) throws ResponseException {
            String str;
            try {
                String readLine = bufferedReader.readLine();
                if (readLine != null) {
                    StringTokenizer stringTokenizer = new StringTokenizer(readLine);
                    if (stringTokenizer.hasMoreTokens()) {
                        map.put("method", stringTokenizer.nextToken());
                        if (stringTokenizer.hasMoreTokens()) {
                            String nextToken = stringTokenizer.nextToken();
                            int indexOf = nextToken.indexOf(63);
                            if (indexOf >= 0) {
                                a(nextToken.substring(indexOf + 1), map2);
                                str = NanoHTTPD.this.a(nextToken.substring(0, indexOf));
                            } else {
                                str = NanoHTTPD.this.a(nextToken);
                            }
                            if (stringTokenizer.hasMoreTokens()) {
                                String readLine2 = bufferedReader.readLine();
                                while (readLine2 != null && readLine2.trim().length() > 0) {
                                    int indexOf2 = readLine2.indexOf(58);
                                    if (indexOf2 >= 0) {
                                        map3.put(readLine2.substring(0, indexOf2).trim().toLowerCase(Locale.US), readLine2.substring(indexOf2 + 1).trim());
                                    }
                                    readLine2 = bufferedReader.readLine();
                                }
                            }
                            map.put("uri", str);
                            return;
                        }
                        throw new ResponseException(Response.Status.BAD_REQUEST, "BAD REQUEST: Missing URI. Usage: GET /example/file.html");
                    }
                    throw new ResponseException(Response.Status.BAD_REQUEST, "BAD REQUEST: Syntax error. Usage: GET /example/file.html");
                }
            } catch (IOException e2) {
                Response.Status status = Response.Status.INTERNAL_ERROR;
                throw new ResponseException(status, "SERVER INTERNAL ERROR: IOException: " + e2.getMessage(), e2);
            }
        }

        private void a(String str, ByteBuffer byteBuffer, BufferedReader bufferedReader, Map<String, String> map, Map<String, String> map2) throws ResponseException {
            Map<String, String> map3;
            String str2;
            String str3 = str;
            ByteBuffer byteBuffer2 = byteBuffer;
            try {
                int[] a2 = a(byteBuffer2, str.getBytes());
                String readLine = bufferedReader.readLine();
                int i2 = 1;
                while (readLine != null) {
                    if (readLine.contains(str3)) {
                        i2++;
                        HashMap hashMap = new HashMap();
                        String readLine2 = bufferedReader.readLine();
                        while (readLine2 != null && readLine2.trim().length() > 0) {
                            int indexOf = readLine2.indexOf(58);
                            if (indexOf != -1) {
                                hashMap.put(readLine2.substring(0, indexOf).trim().toLowerCase(Locale.US), readLine2.substring(indexOf + 1).trim());
                            }
                            readLine2 = bufferedReader.readLine();
                        }
                        if (readLine2 != null) {
                            String str4 = (String) hashMap.get("content-disposition");
                            if (str4 != null) {
                                StringTokenizer stringTokenizer = new StringTokenizer(str4, i.b);
                                HashMap hashMap2 = new HashMap();
                                while (stringTokenizer.hasMoreTokens()) {
                                    String trim = stringTokenizer.nextToken().trim();
                                    int indexOf2 = trim.indexOf(61);
                                    if (indexOf2 != -1) {
                                        hashMap2.put(trim.substring(0, indexOf2).trim().toLowerCase(Locale.US), trim.substring(indexOf2 + 1).trim());
                                    }
                                }
                                String str5 = (String) hashMap2.get("name");
                                String substring = str5.substring(1, str5.length() - 1);
                                String str6 = "";
                                if (hashMap.get("content-type") == null) {
                                    while (readLine2 != null && !readLine2.contains(str3)) {
                                        readLine2 = bufferedReader.readLine();
                                        if (readLine2 != null) {
                                            int indexOf3 = readLine2.indexOf(str3);
                                            if (indexOf3 == -1) {
                                                str2 = str6 + readLine2;
                                            } else {
                                                str2 = str6 + readLine2.substring(0, indexOf3 - 2);
                                            }
                                            str6 = str2;
                                        }
                                    }
                                    map3 = map;
                                    Map<String, String> map4 = map2;
                                } else if (i2 <= a2.length) {
                                    int a3 = a(byteBuffer2, a2[i2 - 2]);
                                    map2.put(substring, a(byteBuffer2, a3, (a2[i2 - 1] - a3) - 4));
                                    String str7 = (String) hashMap2.get("filename");
                                    str6 = str7.substring(1, str7.length() - 1);
                                    do {
                                        readLine2 = bufferedReader.readLine();
                                        if (readLine2 == null || readLine2.contains(str3)) {
                                            map3 = map;
                                        }
                                        readLine2 = bufferedReader.readLine();
                                        break;
                                    } while (readLine2.contains(str3));
                                    map3 = map;
                                } else {
                                    throw new ResponseException(Response.Status.INTERNAL_ERROR, "Error processing request");
                                }
                                map3.put(substring, str6);
                            } else {
                                throw new ResponseException(Response.Status.BAD_REQUEST, "BAD REQUEST: Content type is multipart/form-data but no content-disposition info found. Usage: GET /example/file.html");
                            }
                        } else {
                            Map<String, String> map5 = map;
                            Map<String, String> map6 = map2;
                        }
                        readLine = readLine2;
                    } else {
                        throw new ResponseException(Response.Status.BAD_REQUEST, "BAD REQUEST: Content type is multipart/form-data but next chunk does not start with boundary. Usage: GET /example/file.html");
                    }
                }
            } catch (IOException e2) {
                throw new ResponseException(Response.Status.INTERNAL_ERROR, "SERVER INTERNAL ERROR: IOException: " + e2.getMessage(), e2);
            }
        }

        private int a(byte[] bArr, int i2) {
            int i3 = 0;
            while (true) {
                int i4 = i3 + 3;
                if (i4 >= i2) {
                    return 0;
                }
                if (bArr[i3] == 13 && bArr[i3 + 1] == 10 && bArr[i3 + 2] == 13 && bArr[i4] == 10) {
                    return i3 + 4;
                }
                i3++;
            }
        }

        private int[] a(ByteBuffer byteBuffer, byte[] bArr) {
            ArrayList arrayList = new ArrayList();
            int i2 = 0;
            int i3 = 0;
            int i4 = -1;
            while (i2 < byteBuffer.limit()) {
                if (byteBuffer.get(i2) == bArr[i3]) {
                    if (i3 == 0) {
                        i4 = i2;
                    }
                    i3++;
                    if (i3 == bArr.length) {
                        arrayList.add(Integer.valueOf(i4));
                    } else {
                        i2++;
                    }
                } else {
                    i2 -= i3;
                }
                i3 = 0;
                i4 = -1;
                i2++;
            }
            int[] iArr = new int[arrayList.size()];
            for (int i5 = 0; i5 < iArr.length; i5++) {
                iArr[i5] = ((Integer) arrayList.get(i5)).intValue();
            }
            return iArr;
        }

        private String a(ByteBuffer byteBuffer, int i2, int i3) {
            String str = "";
            if (i3 > 0) {
                FileOutputStream fileOutputStream = null;
                try {
                    TempFile a2 = this.c.a();
                    ByteBuffer duplicate = byteBuffer.duplicate();
                    FileOutputStream fileOutputStream2 = new FileOutputStream(a2.c());
                    try {
                        FileChannel channel = fileOutputStream2.getChannel();
                        duplicate.position(i2).limit(i2 + i3);
                        channel.write(duplicate.slice());
                        str = a2.c();
                        NanoHTTPD.b((Closeable) fileOutputStream2);
                    } catch (Exception e2) {
                        e = e2;
                        fileOutputStream = fileOutputStream2;
                        try {
                            throw new Error(e);
                        } catch (Throwable th) {
                            th = th;
                            NanoHTTPD.b((Closeable) fileOutputStream);
                            throw th;
                        }
                    } catch (Throwable th2) {
                        th = th2;
                        fileOutputStream = fileOutputStream2;
                        NanoHTTPD.b((Closeable) fileOutputStream);
                        throw th;
                    }
                } catch (Exception e3) {
                    e = e3;
                    throw new Error(e);
                }
            }
            return str;
        }

        private RandomAccessFile i() {
            try {
                return new RandomAccessFile(this.c.a().c(), "rw");
            } catch (Exception e2) {
                throw new Error(e2);
            }
        }

        private int a(ByteBuffer byteBuffer, int i2) {
            while (i2 < byteBuffer.limit()) {
                if (byteBuffer.get(i2) == 13) {
                    i2++;
                    if (byteBuffer.get(i2) == 10) {
                        i2++;
                        if (byteBuffer.get(i2) == 13) {
                            i2++;
                            if (byteBuffer.get(i2) == 10) {
                                break;
                            }
                        } else {
                            continue;
                        }
                    } else {
                        continue;
                    }
                }
                i2++;
            }
            return i2 + 1;
        }

        private void a(String str, Map<String, String> map) {
            if (str == null) {
                this.m = "";
                return;
            }
            this.m = str;
            StringTokenizer stringTokenizer = new StringTokenizer(str, a.b);
            while (stringTokenizer.hasMoreTokens()) {
                String nextToken = stringTokenizer.nextToken();
                int indexOf = nextToken.indexOf(61);
                if (indexOf >= 0) {
                    map.put(NanoHTTPD.this.a(nextToken.substring(0, indexOf)).trim(), NanoHTTPD.this.a(nextToken.substring(indexOf + 1)));
                } else {
                    map.put(NanoHTTPD.this.a(nextToken).trim(), "");
                }
            }
        }

        public final Map<String, String> b() {
            return this.j;
        }

        public String c() {
            return this.m;
        }

        public final Map<String, String> d() {
            return this.k;
        }

        public final String e() {
            return this.h;
        }

        public final Method f() {
            return this.i;
        }

        public final InputStream g() {
            return this.e;
        }

        public CookieHandler h() {
            return this.l;
        }
    }

    public static class Cookie {

        /* renamed from: a  reason: collision with root package name */
        private String f18383a;
        private String b;
        private String c;

        public Cookie(String str, String str2, String str3) {
            this.f18383a = str;
            this.b = str2;
            this.c = str3;
        }

        public Cookie(String str, String str2) {
            this(str, str2, 30);
        }

        public Cookie(String str, String str2, int i) {
            this.f18383a = str;
            this.b = str2;
            this.c = a(i);
        }

        public String a() {
            return String.format("%s=%s; expires=%s", new Object[]{this.f18383a, this.b, this.c});
        }

        public static String a(int i) {
            Calendar instance = Calendar.getInstance();
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("EEE, dd MMM yyyy HH:mm:ss z", Locale.US);
            simpleDateFormat.setTimeZone(TimeZone.getTimeZone("GMT"));
            instance.add(5, i);
            return simpleDateFormat.format(instance.getTime());
        }
    }

    public class CookieHandler implements Iterable<String> {
        private HashMap<String, String> b = new HashMap<>();
        private ArrayList<Cookie> c = new ArrayList<>();

        public CookieHandler(Map<String, String> map) {
            String str = map.get("cookie");
            if (str != null) {
                for (String trim : str.split(i.b)) {
                    String[] split = trim.trim().split("=");
                    if (split.length == 2) {
                        this.b.put(split[0], split[1]);
                    }
                }
            }
        }

        public Iterator<String> iterator() {
            return this.b.keySet().iterator();
        }

        public String a(String str) {
            return this.b.get(str);
        }

        public void a(String str, String str2, int i) {
            this.c.add(new Cookie(str, str2, Cookie.a(i)));
        }

        public void a(Cookie cookie) {
            this.c.add(cookie);
        }

        public void b(String str) {
            a(str, "-delete-", -30);
        }

        public void a(Response response) {
            Iterator<Cookie> it = this.c.iterator();
            while (it.hasNext()) {
                response.a("Set-Cookie", it.next().a());
            }
        }
    }
}
