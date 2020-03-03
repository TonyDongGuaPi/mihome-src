package com.xiaomi.youpin.network.bean;

import android.support.annotation.NonNull;
import com.xiaomi.youpin.log.LogUtils;
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
    private final RequestBody f23659a;
    /* access modifiers changed from: private */
    public final NetCallback<NetResult, NetError> b;
    private BufferedSink c;

    public ProgressRequestBody(RequestBody requestBody, NetCallback<NetResult, NetError> netCallback) {
        this.f23659a = requestBody;
        this.b = netCallback;
    }

    public MediaType contentType() {
        return this.f23659a.contentType();
    }

    public long contentLength() throws IOException {
        return this.f23659a.contentLength();
    }

    public void writeTo(BufferedSink bufferedSink) throws IOException {
        if (this.c == null) {
            this.c = Okio.buffer(a((Sink) bufferedSink));
        }
        this.f23659a.writeTo(this.c);
        this.c.flush();
    }

    private Sink a(Sink sink) {
        return new ForwardingSink(sink) {

            /* renamed from: a  reason: collision with root package name */
            long f23660a = 0;
            long b = 0;

            public void write(@NonNull Buffer buffer, long j) throws IOException {
                try {
                    super.write(buffer, j);
                    if (this.b == 0) {
                        this.b = ProgressRequestBody.this.contentLength();
                    }
                    this.f23660a += j;
                    if (ProgressRequestBody.this.b != null) {
                        ProgressRequestBody.this.b.a(this.f23660a, this.b, this.f23660a == this.b);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                    LogUtils.postCatchedException(e);
                }
            }
        };
    }
}
