package com.xiaomi.youpin.core.api;

import com.xiaomi.youpin.core.net.NetCallback;
import com.xiaomi.youpin.core.net.NetError;
import com.xiaomi.youpin.core.net.NetResult;
import java.io.IOException;
import okhttp3.MediaType;
import okhttp3.RequestBody;
import okio.Buffer;
import okio.BufferedSink;
import okio.ForwardingSink;
import okio.Okio;
import okio.Sink;

public class ProgressRequestBody extends RequestBody {

    /* renamed from: a  reason: collision with root package name */
    private final RequestBody f23328a;
    /* access modifiers changed from: private */
    public final NetCallback<NetResult, NetError> b;
    private BufferedSink c;

    public ProgressRequestBody(RequestBody requestBody, NetCallback<NetResult, NetError> netCallback) {
        this.f23328a = requestBody;
        this.b = netCallback;
    }

    public MediaType contentType() {
        return this.f23328a.contentType();
    }

    public long contentLength() throws IOException {
        return this.f23328a.contentLength();
    }

    public void writeTo(BufferedSink bufferedSink) throws IOException {
        if (this.c == null) {
            this.c = Okio.buffer(a((Sink) bufferedSink));
        }
        this.f23328a.writeTo(this.c);
        this.c.flush();
    }

    private Sink a(Sink sink) {
        return new ForwardingSink(sink) {

            /* renamed from: a  reason: collision with root package name */
            long f23329a = 0;
            long b = 0;

            public void write(Buffer buffer, long j) throws IOException {
                super.write(buffer, j);
                if (this.b == 0) {
                    this.b = ProgressRequestBody.this.contentLength();
                }
                this.f23329a += j;
                ProgressRequestBody.this.b.a(this.f23329a, this.b, this.f23329a == this.b);
            }
        };
    }
}
