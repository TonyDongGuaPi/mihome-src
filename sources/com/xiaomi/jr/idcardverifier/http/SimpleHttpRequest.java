package com.xiaomi.jr.idcardverifier.http;

import android.support.annotation.NonNull;
import com.xiaomi.jr.common.utils.MifiLog;
import com.xiaomi.jr.common.utils.Utils;
import java.io.Closeable;
import java.io.IOException;
import java.util.Map;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;

public class SimpleHttpRequest {

    /* renamed from: a  reason: collision with root package name */
    private static final String f10869a = "SimpleHttpRequest";
    private static OkHttpClient b;

    public interface Listener {
        void a(String str);

        void b(String str);
    }

    public static class Response {

        /* renamed from: a  reason: collision with root package name */
        public boolean f10871a;
        public String b;
    }

    public static void a() {
        b = new OkHttpClient.Builder().build();
    }

    public static void a(String str, Map<String, String> map, final Listener listener) {
        b.newCall(c(str, map)).enqueue(new Callback() {
            public void onFailure(@NonNull Call call, @NonNull IOException iOException) {
                Response response = new Response();
                response.b = iOException.getMessage();
                a(response);
            }

            public void onResponse(@NonNull Call call, @NonNull okhttp3.Response response) throws IOException {
                a(SimpleHttpRequest.b(response));
            }

            private void a(Response response) {
                if (listener == null) {
                    return;
                }
                if (response.f10871a) {
                    listener.a(response.b);
                } else {
                    listener.b(response.b);
                }
            }
        });
    }

    public static Response a(String str, Map<String, String> map) {
        return a(c(str, map));
    }

    public static Response b(String str, Map<String, String> map) {
        return a(d(str, map));
    }

    private static Response a(Request request) {
        Response response = new Response();
        okhttp3.Response response2 = null;
        try {
            okhttp3.Response execute = b.newCall(request).execute();
            try {
                Response b2 = b(execute);
                Utils.a((Closeable) execute);
                return b2;
            } catch (IOException e) {
                IOException iOException = e;
                response2 = execute;
                e = iOException;
                try {
                    response.b = e.getMessage();
                    Utils.a((Closeable) response2);
                    return response;
                } catch (Throwable th) {
                    th = th;
                    Utils.a((Closeable) response2);
                    throw th;
                }
            } catch (Throwable th2) {
                th = th2;
                response2 = execute;
                Utils.a((Closeable) response2);
                throw th;
            }
        } catch (IOException e2) {
            e = e2;
            response.b = e.getMessage();
            Utils.a((Closeable) response2);
            return response;
        }
    }

    private static Request c(String str, Map<String, String> map) {
        Request.Builder builder = new Request.Builder();
        return BasicParamsInterceptor.a(builder.url(str).get().build().url().newBuilder(), builder, map);
    }

    private static Request d(String str, Map<String, String> map) {
        return BasicParamsInterceptor.a((RequestBody) null, new Request.Builder().url(str), map);
    }

    /* access modifiers changed from: private */
    public static Response b(okhttp3.Response response) {
        Response response2 = new Response();
        if (response == null || !response.isSuccessful() || response.body() == null) {
            StringBuilder sb = new StringBuilder();
            sb.append("okhttp response fail: ");
            sb.append(response != null ? response.message() : null);
            response2.b = sb.toString();
            return response2;
        }
        response2.f10871a = true;
        try {
            response2.b = response.body().string();
        } catch (IOException e) {
            MifiLog.d(f10869a, "get okhttp response body fail, " + e.getMessage());
        }
        return response2;
    }
}
