package com.xiaomi.miot.support.monitor.core.net.impl;

import com.xiaomi.miot.support.monitor.core.net.NetInfo;
import com.xiaomi.miot.support.monitor.core.net.i.IStreamCompleteListener;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import org.apache.http.Header;
import org.apache.http.HttpEntity;

public class AopHttpEntity implements IStreamCompleteListener, HttpEntity {
    private static final String c = "AopHttpEntity";

    /* renamed from: a  reason: collision with root package name */
    protected final HttpEntity f11468a;
    protected NetInfo b;

    public void a(long j) {
    }

    public void b(long j) {
    }

    public void c(long j) {
    }

    public void d(long j) {
    }

    public void writeTo(OutputStream outputStream) throws IOException {
    }

    public AopHttpEntity(HttpEntity httpEntity, NetInfo netInfo) {
        this.f11468a = httpEntity;
        this.b = netInfo;
    }

    public boolean isRepeatable() {
        return this.f11468a.isRepeatable();
    }

    public boolean isChunked() {
        return this.f11468a.isChunked();
    }

    public long getContentLength() {
        return this.f11468a.getContentLength();
    }

    public Header getContentType() {
        return this.f11468a.getContentType();
    }

    public Header getContentEncoding() {
        return this.f11468a.getContentEncoding();
    }

    public InputStream getContent() throws IOException, IllegalStateException {
        try {
            AopInputStream aopInputStream = new AopInputStream(this.f11468a.getContent());
            aopInputStream.a(this);
            return aopInputStream;
        } catch (IOException e) {
            throw e;
        } catch (IllegalStateException e2) {
            throw e2;
        }
    }

    public boolean isStreaming() {
        return this.f11468a.isStreaming();
    }

    public void consumeContent() throws IOException {
        try {
            this.f11468a.consumeContent();
        } catch (IOException e) {
            throw e;
        }
    }
}
