package com.lidroid.xutils.http.client.multipart;

import com.lidroid.xutils.http.callback.RequestCallBackHandler;
import com.lidroid.xutils.http.client.entity.UploadEntity;
import com.lidroid.xutils.http.client.multipart.content.ContentBody;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.charset.Charset;
import java.util.Random;
import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.message.BasicHeader;

public class MultipartEntity implements UploadEntity, HttpEntity {
    private static final char[] b = "-_1234567890abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ".toCharArray();

    /* renamed from: a  reason: collision with root package name */
    private CallBackInfo f6351a;
    private final HttpMultipart c;
    private Header d;
    private long e;
    private volatile boolean f;
    private final String g;
    private final Charset h;
    private String i;

    public Header getContentEncoding() {
        return null;
    }

    public void a(RequestCallBackHandler requestCallBackHandler) {
        this.f6351a.b = requestCallBackHandler;
    }

    public static class CallBackInfo {

        /* renamed from: a  reason: collision with root package name */
        public static final CallBackInfo f6352a = new CallBackInfo();
        public RequestCallBackHandler b = null;
        public long c = 0;
        public long d = 0;

        public boolean a(boolean z) {
            if (this.b != null) {
                return this.b.a(this.c, this.d, z);
            }
            return true;
        }
    }

    public MultipartEntity(HttpMultipartMode httpMultipartMode, String str, Charset charset) {
        this.f6351a = new CallBackInfo();
        this.i = "form-data";
        this.g = str == null ? a() : str;
        httpMultipartMode = httpMultipartMode == null ? HttpMultipartMode.STRICT : httpMultipartMode;
        this.h = charset == null ? MIME.f : charset;
        this.c = new HttpMultipart(this.i, this.h, this.g, httpMultipartMode);
        this.d = new BasicHeader("Content-Type", a(this.g, this.h));
        this.f = true;
    }

    public MultipartEntity(HttpMultipartMode httpMultipartMode) {
        this(httpMultipartMode, (String) null, (Charset) null);
    }

    public MultipartEntity() {
        this(HttpMultipartMode.STRICT, (String) null, (Charset) null);
    }

    public void a(String str) {
        this.i = str;
        this.c.a(str);
        this.d = new BasicHeader("Content-Type", a(this.g, this.h));
    }

    /* access modifiers changed from: protected */
    public String a(String str, Charset charset) {
        StringBuilder sb = new StringBuilder();
        sb.append("multipart/" + this.i + "; boundary=");
        sb.append(str);
        return sb.toString();
    }

    /* access modifiers changed from: protected */
    public String a() {
        StringBuilder sb = new StringBuilder();
        Random random = new Random();
        int nextInt = random.nextInt(11) + 30;
        for (int i2 = 0; i2 < nextInt; i2++) {
            sb.append(b[random.nextInt(b.length)]);
        }
        return sb.toString();
    }

    public void a(FormBodyPart formBodyPart) {
        this.c.a(formBodyPart);
        this.f = true;
    }

    public void a(String str, ContentBody contentBody) {
        a(new FormBodyPart(str, contentBody));
    }

    public void a(String str, ContentBody contentBody, String str2) {
        a(new FormBodyPart(str, contentBody, str2));
    }

    public boolean isRepeatable() {
        for (FormBodyPart b2 : this.c.d()) {
            if (b2.b().g() < 0) {
                return false;
            }
        }
        return true;
    }

    public boolean isChunked() {
        return !isRepeatable();
    }

    public boolean isStreaming() {
        return !isRepeatable();
    }

    public long getContentLength() {
        if (this.f) {
            this.e = this.c.f();
            this.f = false;
        }
        return this.e;
    }

    public Header getContentType() {
        return this.d;
    }

    public void consumeContent() throws IOException, UnsupportedOperationException {
        if (isStreaming()) {
            throw new UnsupportedOperationException("Streaming entity does not implement #consumeContent()");
        }
    }

    public InputStream getContent() throws IOException, UnsupportedOperationException {
        throw new UnsupportedOperationException("Multipart form entity does not implement #getContent()");
    }

    public void writeTo(OutputStream outputStream) throws IOException {
        this.f6351a.c = getContentLength();
        this.c.a(outputStream, this.f6351a);
    }
}
