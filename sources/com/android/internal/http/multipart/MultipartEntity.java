package com.android.internal.http.multipart;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import org.apache.http.Header;
import org.apache.http.entity.AbstractHttpEntity;
import org.apache.http.params.HttpParams;

public class MultipartEntity extends AbstractHttpEntity {

    /* renamed from: a  reason: collision with root package name */
    public static final String f4753a = "http.method.multipart.boundary";
    protected Part[] b = null;

    public MultipartEntity(Part[] partArr, HttpParams httpParams) {
        throw new RuntimeException("Stub!");
    }

    public MultipartEntity(Part[] partArr) {
        throw new RuntimeException("Stub!");
    }

    /* access modifiers changed from: protected */
    public byte[] a() {
        throw new RuntimeException("Stub!");
    }

    public boolean isRepeatable() {
        throw new RuntimeException("Stub!");
    }

    public void writeTo(OutputStream outputStream) throws IOException {
        throw new RuntimeException("Stub!");
    }

    public Header getContentType() {
        throw new RuntimeException("Stub!");
    }

    public long getContentLength() {
        throw new RuntimeException("Stub!");
    }

    public InputStream getContent() throws IOException, IllegalStateException {
        throw new RuntimeException("Stub!");
    }

    public boolean isStreaming() {
        throw new RuntimeException("Stub!");
    }
}
