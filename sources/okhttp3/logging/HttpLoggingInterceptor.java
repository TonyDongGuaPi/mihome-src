package okhttp3.logging;

import java.io.EOFException;
import java.nio.charset.Charset;
import java.util.Collections;
import java.util.Set;
import java.util.TreeSet;
import okhttp3.Headers;
import okhttp3.Interceptor;
import okhttp3.internal.platform.Platform;
import okio.Buffer;

public final class HttpLoggingInterceptor implements Interceptor {
    private static final Charset UTF8 = Charset.forName("UTF-8");
    private volatile Set<String> headersToRedact;
    private volatile Level level;
    private final Logger logger;

    public enum Level {
        NONE,
        BASIC,
        HEADERS,
        BODY
    }

    public interface Logger {
        public static final Logger DEFAULT = new Logger() {
            public void log(String str) {
                Platform.get().log(4, str, (Throwable) null);
            }
        };

        void log(String str);
    }

    public HttpLoggingInterceptor() {
        this(Logger.DEFAULT);
    }

    public HttpLoggingInterceptor(Logger logger2) {
        this.headersToRedact = Collections.emptySet();
        this.level = Level.NONE;
        this.logger = logger2;
    }

    public void redactHeader(String str) {
        TreeSet treeSet = new TreeSet(String.CASE_INSENSITIVE_ORDER);
        treeSet.addAll(this.headersToRedact);
        treeSet.add(str);
        this.headersToRedact = treeSet;
    }

    public HttpLoggingInterceptor setLevel(Level level2) {
        if (level2 != null) {
            this.level = level2;
            return this;
        }
        throw new NullPointerException("level == null. Use Level.NONE instead.");
    }

    public Level getLevel() {
        return this.level;
    }

    /* JADX WARNING: Removed duplicated region for block: B:92:0x02ed  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public okhttp3.Response intercept(okhttp3.Interceptor.Chain r15) throws java.io.IOException {
        /*
            r14 = this;
            okhttp3.logging.HttpLoggingInterceptor$Level r0 = r14.level
            okhttp3.Request r1 = r15.request()
            okhttp3.logging.HttpLoggingInterceptor$Level r2 = okhttp3.logging.HttpLoggingInterceptor.Level.NONE
            if (r0 != r2) goto L_0x000f
            okhttp3.Response r15 = r15.proceed(r1)
            return r15
        L_0x000f:
            okhttp3.logging.HttpLoggingInterceptor$Level r2 = okhttp3.logging.HttpLoggingInterceptor.Level.BODY
            r3 = 0
            r4 = 1
            if (r0 != r2) goto L_0x0017
            r2 = 1
            goto L_0x0018
        L_0x0017:
            r2 = 0
        L_0x0018:
            if (r2 != 0) goto L_0x0021
            okhttp3.logging.HttpLoggingInterceptor$Level r5 = okhttp3.logging.HttpLoggingInterceptor.Level.HEADERS
            if (r0 != r5) goto L_0x001f
            goto L_0x0021
        L_0x001f:
            r0 = 0
            goto L_0x0022
        L_0x0021:
            r0 = 1
        L_0x0022:
            okhttp3.RequestBody r5 = r1.body()
            if (r5 == 0) goto L_0x0029
            goto L_0x002a
        L_0x0029:
            r4 = 0
        L_0x002a:
            okhttp3.Connection r6 = r15.connection()
            java.lang.StringBuilder r7 = new java.lang.StringBuilder
            r7.<init>()
            java.lang.String r8 = "--> "
            r7.append(r8)
            java.lang.String r8 = r1.method()
            r7.append(r8)
            r8 = 32
            r7.append(r8)
            okhttp3.HttpUrl r9 = r1.url()
            r7.append(r9)
            if (r6 == 0) goto L_0x0063
            java.lang.StringBuilder r9 = new java.lang.StringBuilder
            r9.<init>()
            java.lang.String r10 = " "
            r9.append(r10)
            okhttp3.Protocol r6 = r6.protocol()
            r9.append(r6)
            java.lang.String r6 = r9.toString()
            goto L_0x0065
        L_0x0063:
            java.lang.String r6 = ""
        L_0x0065:
            r7.append(r6)
            java.lang.String r6 = r7.toString()
            if (r0 != 0) goto L_0x008d
            if (r4 == 0) goto L_0x008d
            java.lang.StringBuilder r7 = new java.lang.StringBuilder
            r7.<init>()
            r7.append(r6)
            java.lang.String r6 = " ("
            r7.append(r6)
            long r9 = r5.contentLength()
            r7.append(r9)
            java.lang.String r6 = "-byte body)"
            r7.append(r6)
            java.lang.String r6 = r7.toString()
        L_0x008d:
            okhttp3.logging.HttpLoggingInterceptor$Logger r7 = r14.logger
            r7.log(r6)
            r6 = -1
            if (r0 == 0) goto L_0x01ce
            if (r4 == 0) goto L_0x00da
            okhttp3.MediaType r9 = r5.contentType()
            if (r9 == 0) goto L_0x00b8
            okhttp3.logging.HttpLoggingInterceptor$Logger r9 = r14.logger
            java.lang.StringBuilder r10 = new java.lang.StringBuilder
            r10.<init>()
            java.lang.String r11 = "Content-Type: "
            r10.append(r11)
            okhttp3.MediaType r11 = r5.contentType()
            r10.append(r11)
            java.lang.String r10 = r10.toString()
            r9.log(r10)
        L_0x00b8:
            long r9 = r5.contentLength()
            int r11 = (r9 > r6 ? 1 : (r9 == r6 ? 0 : -1))
            if (r11 == 0) goto L_0x00da
            okhttp3.logging.HttpLoggingInterceptor$Logger r9 = r14.logger
            java.lang.StringBuilder r10 = new java.lang.StringBuilder
            r10.<init>()
            java.lang.String r11 = "Content-Length: "
            r10.append(r11)
            long r11 = r5.contentLength()
            r10.append(r11)
            java.lang.String r10 = r10.toString()
            r9.log(r10)
        L_0x00da:
            okhttp3.Headers r9 = r1.headers()
            int r10 = r9.size()
            r11 = 0
        L_0x00e3:
            if (r11 >= r10) goto L_0x00ff
            java.lang.String r12 = r9.name(r11)
            java.lang.String r13 = "Content-Type"
            boolean r13 = r13.equalsIgnoreCase(r12)
            if (r13 != 0) goto L_0x00fc
            java.lang.String r13 = "Content-Length"
            boolean r12 = r13.equalsIgnoreCase(r12)
            if (r12 != 0) goto L_0x00fc
            r14.logHeader(r9, r11)
        L_0x00fc:
            int r11 = r11 + 1
            goto L_0x00e3
        L_0x00ff:
            if (r2 == 0) goto L_0x01b4
            if (r4 != 0) goto L_0x0105
            goto L_0x01b4
        L_0x0105:
            okhttp3.Headers r4 = r1.headers()
            boolean r4 = bodyHasUnknownEncoding(r4)
            if (r4 == 0) goto L_0x0130
            okhttp3.logging.HttpLoggingInterceptor$Logger r4 = r14.logger
            java.lang.StringBuilder r5 = new java.lang.StringBuilder
            r5.<init>()
            java.lang.String r9 = "--> END "
            r5.append(r9)
            java.lang.String r9 = r1.method()
            r5.append(r9)
            java.lang.String r9 = " (encoded body omitted)"
            r5.append(r9)
            java.lang.String r5 = r5.toString()
            r4.log(r5)
            goto L_0x01ce
        L_0x0130:
            okio.Buffer r4 = new okio.Buffer
            r4.<init>()
            r5.writeTo(r4)
            java.nio.charset.Charset r9 = UTF8
            okhttp3.MediaType r10 = r5.contentType()
            if (r10 == 0) goto L_0x0146
            java.nio.charset.Charset r9 = UTF8
            java.nio.charset.Charset r9 = r10.charset(r9)
        L_0x0146:
            okhttp3.logging.HttpLoggingInterceptor$Logger r10 = r14.logger
            java.lang.String r11 = ""
            r10.log(r11)
            boolean r10 = isPlaintext(r4)
            if (r10 == 0) goto L_0x0188
            okhttp3.logging.HttpLoggingInterceptor$Logger r10 = r14.logger
            java.lang.String r4 = r4.readString(r9)
            r10.log(r4)
            okhttp3.logging.HttpLoggingInterceptor$Logger r4 = r14.logger
            java.lang.StringBuilder r9 = new java.lang.StringBuilder
            r9.<init>()
            java.lang.String r10 = "--> END "
            r9.append(r10)
            java.lang.String r10 = r1.method()
            r9.append(r10)
            java.lang.String r10 = " ("
            r9.append(r10)
            long r10 = r5.contentLength()
            r9.append(r10)
            java.lang.String r5 = "-byte body)"
            r9.append(r5)
            java.lang.String r5 = r9.toString()
            r4.log(r5)
            goto L_0x01ce
        L_0x0188:
            okhttp3.logging.HttpLoggingInterceptor$Logger r4 = r14.logger
            java.lang.StringBuilder r9 = new java.lang.StringBuilder
            r9.<init>()
            java.lang.String r10 = "--> END "
            r9.append(r10)
            java.lang.String r10 = r1.method()
            r9.append(r10)
            java.lang.String r10 = " (binary "
            r9.append(r10)
            long r10 = r5.contentLength()
            r9.append(r10)
            java.lang.String r5 = "-byte body omitted)"
            r9.append(r5)
            java.lang.String r5 = r9.toString()
            r4.log(r5)
            goto L_0x01ce
        L_0x01b4:
            okhttp3.logging.HttpLoggingInterceptor$Logger r4 = r14.logger
            java.lang.StringBuilder r5 = new java.lang.StringBuilder
            r5.<init>()
            java.lang.String r9 = "--> END "
            r5.append(r9)
            java.lang.String r9 = r1.method()
            r5.append(r9)
            java.lang.String r5 = r5.toString()
            r4.log(r5)
        L_0x01ce:
            long r4 = java.lang.System.nanoTime()
            okhttp3.Response r15 = r15.proceed(r1)     // Catch:{ Exception -> 0x0399 }
            java.util.concurrent.TimeUnit r1 = java.util.concurrent.TimeUnit.NANOSECONDS
            long r9 = java.lang.System.nanoTime()
            long r9 = r9 - r4
            long r4 = r1.toMillis(r9)
            okhttp3.ResponseBody r1 = r15.body()
            long r9 = r1.contentLength()
            int r11 = (r9 > r6 ? 1 : (r9 == r6 ? 0 : -1))
            if (r11 == 0) goto L_0x01ff
            java.lang.StringBuilder r6 = new java.lang.StringBuilder
            r6.<init>()
            r6.append(r9)
            java.lang.String r7 = "-byte"
            r6.append(r7)
            java.lang.String r6 = r6.toString()
            goto L_0x0201
        L_0x01ff:
            java.lang.String r6 = "unknown-length"
        L_0x0201:
            okhttp3.logging.HttpLoggingInterceptor$Logger r7 = r14.logger
            java.lang.StringBuilder r11 = new java.lang.StringBuilder
            r11.<init>()
            java.lang.String r12 = "<-- "
            r11.append(r12)
            int r12 = r15.code()
            r11.append(r12)
            java.lang.String r12 = r15.message()
            boolean r12 = r12.isEmpty()
            if (r12 == 0) goto L_0x0221
            java.lang.String r12 = ""
            goto L_0x0234
        L_0x0221:
            java.lang.StringBuilder r12 = new java.lang.StringBuilder
            r12.<init>()
            r12.append(r8)
            java.lang.String r13 = r15.message()
            r12.append(r13)
            java.lang.String r12 = r12.toString()
        L_0x0234:
            r11.append(r12)
            r11.append(r8)
            okhttp3.Request r8 = r15.request()
            okhttp3.HttpUrl r8 = r8.url()
            r11.append(r8)
            java.lang.String r8 = " ("
            r11.append(r8)
            r11.append(r4)
            java.lang.String r4 = "ms"
            r11.append(r4)
            if (r0 != 0) goto L_0x026b
            java.lang.StringBuilder r4 = new java.lang.StringBuilder
            r4.<init>()
            java.lang.String r5 = ", "
            r4.append(r5)
            r4.append(r6)
            java.lang.String r5 = " body"
            r4.append(r5)
            java.lang.String r4 = r4.toString()
            goto L_0x026d
        L_0x026b:
            java.lang.String r4 = ""
        L_0x026d:
            r11.append(r4)
            r4 = 41
            r11.append(r4)
            java.lang.String r4 = r11.toString()
            r7.log(r4)
            if (r0 == 0) goto L_0x0398
            okhttp3.Headers r0 = r15.headers()
            int r4 = r0.size()
        L_0x0286:
            if (r3 >= r4) goto L_0x028e
            r14.logHeader(r0, r3)
            int r3 = r3 + 1
            goto L_0x0286
        L_0x028e:
            if (r2 == 0) goto L_0x0391
            boolean r2 = okhttp3.internal.http.HttpHeaders.hasBody(r15)
            if (r2 != 0) goto L_0x0298
            goto L_0x0391
        L_0x0298:
            okhttp3.Headers r2 = r15.headers()
            boolean r2 = bodyHasUnknownEncoding(r2)
            if (r2 == 0) goto L_0x02ab
            okhttp3.logging.HttpLoggingInterceptor$Logger r0 = r14.logger
            java.lang.String r1 = "<-- END HTTP (encoded body omitted)"
            r0.log(r1)
            goto L_0x0398
        L_0x02ab:
            okio.BufferedSource r2 = r1.source()
            r3 = 9223372036854775807(0x7fffffffffffffff, double:NaN)
            r2.request(r3)
            okio.Buffer r2 = r2.buffer()
            java.lang.String r3 = "gzip"
            java.lang.String r4 = "Content-Encoding"
            java.lang.String r0 = r0.get(r4)
            boolean r0 = r3.equalsIgnoreCase(r0)
            r3 = 0
            if (r0 == 0) goto L_0x02f1
            long r4 = r2.size()
            java.lang.Long r0 = java.lang.Long.valueOf(r4)
            okio.GzipSource r4 = new okio.GzipSource     // Catch:{ all -> 0x02ea }
            okio.Buffer r2 = r2.clone()     // Catch:{ all -> 0x02ea }
            r4.<init>(r2)     // Catch:{ all -> 0x02ea }
            okio.Buffer r2 = new okio.Buffer     // Catch:{ all -> 0x02e7 }
            r2.<init>()     // Catch:{ all -> 0x02e7 }
            r2.writeAll(r4)     // Catch:{ all -> 0x02e7 }
            r4.close()
            goto L_0x02f2
        L_0x02e7:
            r15 = move-exception
            r3 = r4
            goto L_0x02eb
        L_0x02ea:
            r15 = move-exception
        L_0x02eb:
            if (r3 == 0) goto L_0x02f0
            r3.close()
        L_0x02f0:
            throw r15
        L_0x02f1:
            r0 = r3
        L_0x02f2:
            java.nio.charset.Charset r3 = UTF8
            okhttp3.MediaType r1 = r1.contentType()
            if (r1 == 0) goto L_0x0300
            java.nio.charset.Charset r3 = UTF8
            java.nio.charset.Charset r3 = r1.charset(r3)
        L_0x0300:
            boolean r1 = isPlaintext(r2)
            if (r1 != 0) goto L_0x032d
            okhttp3.logging.HttpLoggingInterceptor$Logger r0 = r14.logger
            java.lang.String r1 = ""
            r0.log(r1)
            okhttp3.logging.HttpLoggingInterceptor$Logger r0 = r14.logger
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            r1.<init>()
            java.lang.String r3 = "<-- END HTTP (binary "
            r1.append(r3)
            long r2 = r2.size()
            r1.append(r2)
            java.lang.String r2 = "-byte body omitted)"
            r1.append(r2)
            java.lang.String r1 = r1.toString()
            r0.log(r1)
            return r15
        L_0x032d:
            r4 = 0
            int r1 = (r9 > r4 ? 1 : (r9 == r4 ? 0 : -1))
            if (r1 == 0) goto L_0x0347
            okhttp3.logging.HttpLoggingInterceptor$Logger r1 = r14.logger
            java.lang.String r4 = ""
            r1.log(r4)
            okhttp3.logging.HttpLoggingInterceptor$Logger r1 = r14.logger
            okio.Buffer r4 = r2.clone()
            java.lang.String r3 = r4.readString(r3)
            r1.log(r3)
        L_0x0347:
            if (r0 == 0) goto L_0x0371
            okhttp3.logging.HttpLoggingInterceptor$Logger r1 = r14.logger
            java.lang.StringBuilder r3 = new java.lang.StringBuilder
            r3.<init>()
            java.lang.String r4 = "<-- END HTTP ("
            r3.append(r4)
            long r4 = r2.size()
            r3.append(r4)
            java.lang.String r2 = "-byte, "
            r3.append(r2)
            r3.append(r0)
            java.lang.String r0 = "-gzipped-byte body)"
            r3.append(r0)
            java.lang.String r0 = r3.toString()
            r1.log(r0)
            goto L_0x0398
        L_0x0371:
            okhttp3.logging.HttpLoggingInterceptor$Logger r0 = r14.logger
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            r1.<init>()
            java.lang.String r3 = "<-- END HTTP ("
            r1.append(r3)
            long r2 = r2.size()
            r1.append(r2)
            java.lang.String r2 = "-byte body)"
            r1.append(r2)
            java.lang.String r1 = r1.toString()
            r0.log(r1)
            goto L_0x0398
        L_0x0391:
            okhttp3.logging.HttpLoggingInterceptor$Logger r0 = r14.logger
            java.lang.String r1 = "<-- END HTTP"
            r0.log(r1)
        L_0x0398:
            return r15
        L_0x0399:
            r15 = move-exception
            okhttp3.logging.HttpLoggingInterceptor$Logger r0 = r14.logger
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            r1.<init>()
            java.lang.String r2 = "<-- HTTP FAILED: "
            r1.append(r2)
            r1.append(r15)
            java.lang.String r1 = r1.toString()
            r0.log(r1)
            throw r15
        */
        throw new UnsupportedOperationException("Method not decompiled: okhttp3.logging.HttpLoggingInterceptor.intercept(okhttp3.Interceptor$Chain):okhttp3.Response");
    }

    private void logHeader(Headers headers, int i) {
        String value = this.headersToRedact.contains(headers.name(i)) ? "██" : headers.value(i);
        Logger logger2 = this.logger;
        logger2.log(headers.name(i) + ": " + value);
    }

    static boolean isPlaintext(Buffer buffer) {
        try {
            Buffer buffer2 = new Buffer();
            buffer.copyTo(buffer2, 0, buffer.size() < 64 ? buffer.size() : 64);
            for (int i = 0; i < 16; i++) {
                if (buffer2.exhausted()) {
                    return true;
                }
                int readUtf8CodePoint = buffer2.readUtf8CodePoint();
                if (Character.isISOControl(readUtf8CodePoint) && !Character.isWhitespace(readUtf8CodePoint)) {
                    return false;
                }
            }
            return true;
        } catch (EOFException unused) {
            return false;
        }
    }

    private static boolean bodyHasUnknownEncoding(Headers headers) {
        String str = headers.get("Content-Encoding");
        return str != null && !str.equalsIgnoreCase("identity") && !str.equalsIgnoreCase("gzip");
    }
}
