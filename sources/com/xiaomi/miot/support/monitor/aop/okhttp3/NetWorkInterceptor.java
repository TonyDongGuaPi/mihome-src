package com.xiaomi.miot.support.monitor.aop.okhttp3;

import android.text.TextUtils;
import com.xiaomi.miot.support.monitor.MiotMonitorManager;
import com.xiaomi.miot.support.monitor.utils.AndroidUtils;
import java.io.IOException;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import okhttp3.ResponseBody;
import okio.BufferedSource;

public class NetWorkInterceptor implements Interceptor {

    /* renamed from: a  reason: collision with root package name */
    private static final String f11447a = "miot_monitor_debug";
    private OkHttpData b;

    public Response intercept(Interceptor.Chain chain) throws IOException {
        long currentTimeMillis = System.currentTimeMillis();
        this.b = new OkHttpData();
        this.b.d = currentTimeMillis;
        this.b.f = AndroidUtils.a(MiotMonitorManager.a().h()) ? 1 : 2;
        Request request = chain.request();
        a(request);
        try {
            Response proceed = chain.proceed(request);
            this.b.e = System.currentTimeMillis() - currentTimeMillis;
            a(proceed);
            DataRecordUtils.a(this.b);
            return proceed;
        } catch (IOException e) {
            throw e;
        }
    }

    private void a(Request request) {
        long j;
        if (request != null && request.url() != null && !TextUtils.isEmpty(request.url().toString())) {
            this.b.f11449a = request.url().toString();
            RequestBody body = request.body();
            if (body == null) {
                this.b.b = (long) request.url().toString().getBytes().length;
                return;
            }
            try {
                j = body.contentLength();
            } catch (IOException e) {
                e.printStackTrace();
                j = 0;
            }
            if (j > 0) {
                this.b.b = j;
                return;
            }
            this.b.b = (long) request.url().toString().getBytes().length;
        }
    }

    private void a(Response response) {
        ResponseBody body;
        BufferedSource source;
        if (response != null) {
            this.b.g = response.code();
            if (response.isSuccessful() && (body = response.body()) != null) {
                long contentLength = body.contentLength();
                if (contentLength <= 0 && (source = body.source()) != null) {
                    try {
                        source.request(Long.MAX_VALUE);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    contentLength = source.buffer().size();
                }
                this.b.c = contentLength;
            }
        }
    }
}
