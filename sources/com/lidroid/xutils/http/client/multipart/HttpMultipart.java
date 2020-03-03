package com.lidroid.xutils.http.client.multipart;

import com.lidroid.xutils.http.client.multipart.MultipartEntity;
import com.lidroid.xutils.http.client.multipart.content.ContentBody;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InterruptedIOException;
import java.io.OutputStream;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.apache.commons.cli.HelpFormatter;
import org.apache.http.util.ByteArrayBuffer;

class HttpMultipart {

    /* renamed from: a  reason: collision with root package name */
    private static final ByteArrayBuffer f6347a = a(MIME.f, ": ");
    private static final ByteArrayBuffer b = a(MIME.f, "\r\n");
    private static final ByteArrayBuffer c = a(MIME.f, HelpFormatter.f);
    private static /* synthetic */ int[] i;
    private String d;
    private final Charset e;
    private final String f;
    private final List<FormBodyPart> g;
    private final HttpMultipartMode h;

    /* JADX WARNING: Can't wrap try/catch for region: R(7:3|4|5|6|7|8|10) */
    /* JADX WARNING: Failed to process nested try/catch */
    /* JADX WARNING: Missing exception handler attribute for start block: B:6:0x0015 */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    static /* synthetic */ int[] g() {
        /*
            int[] r0 = i
            if (r0 == 0) goto L_0x0005
            return r0
        L_0x0005:
            com.lidroid.xutils.http.client.multipart.HttpMultipartMode[] r0 = com.lidroid.xutils.http.client.multipart.HttpMultipartMode.values()
            int r0 = r0.length
            int[] r0 = new int[r0]
            com.lidroid.xutils.http.client.multipart.HttpMultipartMode r1 = com.lidroid.xutils.http.client.multipart.HttpMultipartMode.BROWSER_COMPATIBLE     // Catch:{ NoSuchFieldError -> 0x0015 }
            int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0015 }
            r2 = 2
            r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0015 }
        L_0x0015:
            com.lidroid.xutils.http.client.multipart.HttpMultipartMode r1 = com.lidroid.xutils.http.client.multipart.HttpMultipartMode.STRICT     // Catch:{ NoSuchFieldError -> 0x001e }
            int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x001e }
            r2 = 1
            r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x001e }
        L_0x001e:
            i = r0
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.lidroid.xutils.http.client.multipart.HttpMultipart.g():int[]");
    }

    private static ByteArrayBuffer a(Charset charset, String str) {
        ByteBuffer encode = charset.encode(CharBuffer.wrap(str));
        ByteArrayBuffer byteArrayBuffer = new ByteArrayBuffer(encode.remaining());
        byteArrayBuffer.append(encode.array(), encode.position(), encode.remaining());
        return byteArrayBuffer;
    }

    private static void a(ByteArrayBuffer byteArrayBuffer, OutputStream outputStream) throws IOException {
        outputStream.write(byteArrayBuffer.buffer(), 0, byteArrayBuffer.length());
        outputStream.flush();
    }

    private static void a(String str, Charset charset, OutputStream outputStream) throws IOException {
        a(a(charset, str), outputStream);
    }

    private static void a(String str, OutputStream outputStream) throws IOException {
        a(a(MIME.f, str), outputStream);
    }

    private static void a(MinimalField minimalField, OutputStream outputStream) throws IOException {
        a(minimalField.a(), outputStream);
        a(f6347a, outputStream);
        a(minimalField.b(), outputStream);
        a(b, outputStream);
    }

    private static void a(MinimalField minimalField, Charset charset, OutputStream outputStream) throws IOException {
        a(minimalField.a(), charset, outputStream);
        a(f6347a, outputStream);
        a(minimalField.b(), charset, outputStream);
        a(b, outputStream);
    }

    public HttpMultipart(String str, Charset charset, String str2, HttpMultipartMode httpMultipartMode) {
        if (str == null) {
            throw new IllegalArgumentException("Multipart subtype may not be null");
        } else if (str2 != null) {
            this.d = str;
            this.e = charset == null ? MIME.f : charset;
            this.f = str2;
            this.g = new ArrayList();
            this.h = httpMultipartMode;
        } else {
            throw new IllegalArgumentException("Multipart boundary may not be null");
        }
    }

    public HttpMultipart(String str, Charset charset, String str2) {
        this(str, charset, str2, HttpMultipartMode.STRICT);
    }

    public HttpMultipart(String str, String str2) {
        this(str, (Charset) null, str2);
    }

    public void a(String str) {
        this.d = str;
    }

    public String a() {
        return this.d;
    }

    public Charset b() {
        return this.e;
    }

    public HttpMultipartMode c() {
        return this.h;
    }

    public List<FormBodyPart> d() {
        return this.g;
    }

    public void a(FormBodyPart formBodyPart) {
        if (formBodyPart != null) {
            this.g.add(formBodyPart);
        }
    }

    public String e() {
        return this.f;
    }

    private void a(HttpMultipartMode httpMultipartMode, OutputStream outputStream, boolean z) throws IOException {
        a(httpMultipartMode, outputStream, MultipartEntity.CallBackInfo.f6352a, z);
    }

    private void a(HttpMultipartMode httpMultipartMode, OutputStream outputStream, MultipartEntity.CallBackInfo callBackInfo, boolean z) throws IOException {
        callBackInfo.d = 0;
        ByteArrayBuffer a2 = a(this.e, e());
        for (FormBodyPart next : this.g) {
            if (callBackInfo.a(true)) {
                a(c, outputStream);
                callBackInfo.d += (long) c.length();
                a(a2, outputStream);
                callBackInfo.d += (long) a2.length();
                a(b, outputStream);
                callBackInfo.d += (long) b.length();
                MinimalFieldHeader c2 = next.c();
                switch (g()[httpMultipartMode.ordinal()]) {
                    case 1:
                        Iterator<MinimalField> it = c2.iterator();
                        while (it.hasNext()) {
                            MinimalField next2 = it.next();
                            a(next2, outputStream);
                            callBackInfo.d = callBackInfo.d + ((long) (a(MIME.f, String.valueOf(next2.a()) + next2.b()).length() + f6347a.length() + b.length()));
                        }
                        break;
                    case 2:
                        MinimalField a3 = c2.a("Content-Disposition");
                        a(a3, this.e, outputStream);
                        callBackInfo.d = callBackInfo.d + ((long) (a(this.e, String.valueOf(a3.a()) + a3.b()).length() + f6347a.length() + b.length()));
                        if (next.b().d() != null) {
                            MinimalField a4 = c2.a("Content-Type");
                            a(a4, this.e, outputStream);
                            callBackInfo.d = callBackInfo.d + ((long) (a(this.e, String.valueOf(a4.a()) + a4.b()).length() + f6347a.length() + b.length()));
                            break;
                        }
                        break;
                }
                a(b, outputStream);
                callBackInfo.d += (long) b.length();
                if (z) {
                    ContentBody b2 = next.b();
                    b2.a(callBackInfo);
                    b2.a(outputStream);
                }
                a(b, outputStream);
                callBackInfo.d += (long) b.length();
            } else {
                throw new InterruptedIOException("cancel");
            }
        }
        a(c, outputStream);
        callBackInfo.d += (long) c.length();
        a(a2, outputStream);
        callBackInfo.d += (long) a2.length();
        a(c, outputStream);
        callBackInfo.d += (long) c.length();
        a(b, outputStream);
        callBackInfo.d += (long) b.length();
        callBackInfo.a(true);
    }

    public void a(OutputStream outputStream, MultipartEntity.CallBackInfo callBackInfo) throws IOException {
        a(this.h, outputStream, callBackInfo, true);
    }

    public long f() {
        long j = 0;
        for (FormBodyPart b2 : this.g) {
            long g2 = b2.b().g();
            if (g2 < 0) {
                return -1;
            }
            j += g2;
        }
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        try {
            a(this.h, (OutputStream) byteArrayOutputStream, false);
            return j + ((long) byteArrayOutputStream.toByteArray().length);
        } catch (Throwable unused) {
            return -1;
        }
    }
}
