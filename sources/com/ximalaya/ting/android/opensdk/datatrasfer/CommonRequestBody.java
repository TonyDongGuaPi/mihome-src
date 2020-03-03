package com.ximalaya.ting.android.opensdk.datatrasfer;

import java.io.IOException;
import okhttp3.MediaType;
import okhttp3.RequestBody;
import okio.Buffer;
import okio.BufferedSink;
import okio.ForwardingSink;
import okio.Okio;
import okio.Sink;

public class CommonRequestBody extends RequestBody {

    /* renamed from: a  reason: collision with root package name */
    private final RequestBody f1980a;
    private BufferedSink b;
    /* access modifiers changed from: private */
    public IUploadCallBack c;

    private CommonRequestBody(RequestBody requestBody) {
        this.f1980a = requestBody;
    }

    public CommonRequestBody(RequestBody requestBody, IUploadCallBack iUploadCallBack) {
        this(requestBody);
        this.c = iUploadCallBack;
    }

    public MediaType contentType() {
        return this.f1980a.contentType();
    }

    public long contentLength() throws IOException {
        return this.f1980a.contentLength();
    }

    public void writeTo(BufferedSink bufferedSink) throws IOException {
        if (this.b == null) {
            this.b = Okio.buffer(a(bufferedSink));
        }
        this.f1980a.writeTo(this.b);
        this.b.flush();
        if (this.c != null) {
            this.c.a();
        }
    }

    private Sink a(BufferedSink bufferedSink) {
        return new ForwardingSink(bufferedSink) {

            /* renamed from: a  reason: collision with root package name */
            long f1981a = 0;
            long b = 0;

            public void write(Buffer buffer, long j) throws IOException {
                super.write(buffer, j);
                if (this.b == 0) {
                    this.b = CommonRequestBody.this.contentLength();
                }
                this.f1981a += j;
                if (CommonRequestBody.this.c != null) {
                    CommonRequestBody.this.c.a(this.f1981a, this.b);
                }
            }
        };
    }
}
