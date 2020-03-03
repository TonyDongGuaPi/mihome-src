package com.xiaomi.smarthome.library.http;

import com.xiaomi.smarthome.library.http.async.AsyncHandler;
import com.xiaomi.smarthome.library.http.async.HttpAsyncHandle;
import com.xiaomi.smarthome.library.http.sync.SyncHandler;
import com.xiaomi.smarthome.library.http.util.HeaderUtil;
import com.xiaomi.smarthome.library.http.util.KeyValuePairUtil;
import com.xiaomi.smarthome.library.http.util.RequestParamUtil;
import java.io.IOException;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Headers;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class HttpApi {

    /* renamed from: a  reason: collision with root package name */
    private static OkHttpClient f19101a;

    public static <R> R a(OkHttpClient okHttpClient, Request request, SyncHandler<R> syncHandler) throws Exception {
        if (okHttpClient == null) {
            throw new RuntimeException("client is null");
        } else if (syncHandler != null) {
            Request.Builder builder = new Request.Builder();
            Headers a2 = HeaderUtil.a(request.b());
            if (a2 != null) {
                builder.headers(a2);
            }
            if (request.c().equals("POST")) {
                builder.url(request.a()).post(KeyValuePairUtil.a(request));
            } else if (request.c().equals("GET")) {
                builder.url(KeyValuePairUtil.a(request.a(), request.d()));
            } else {
                throw new RuntimeException("method unsupported");
            }
            try {
                Response execute = okHttpClient.newCall(builder.build()).execute();
                R b = syncHandler.b(execute);
                if (!(execute == null || execute.body() == null)) {
                    execute.close();
                }
                return b;
            } catch (IOException e) {
                throw new RuntimeException("failure:" + e.getMessage());
            }
        } else {
            throw new RuntimeException("handler is null");
        }
    }

    public static <R> R a(Request request, SyncHandler<R> syncHandler) throws Exception {
        return a(a(), request, syncHandler);
    }

    public static HttpAsyncHandle a(OkHttpClient okHttpClient, Request request, final AsyncHandler asyncHandler) {
        if (okHttpClient != null) {
            Request.Builder builder = new Request.Builder();
            Headers a2 = HeaderUtil.a(request.b());
            if (a2 != null) {
                builder.headers(a2);
            }
            if (request.c().equals("POST")) {
                builder.url(request.a()).post(KeyValuePairUtil.a(request));
            } else if (request.c().equals("GET")) {
                builder.url(KeyValuePairUtil.a(request.a(), request.d()));
            } else {
                throw new RuntimeException("method unsupported");
            }
            Call newCall = okHttpClient.newCall(builder.build());
            newCall.enqueue(new Callback() {
                public void onFailure(Call call, IOException iOException) {
                    if (asyncHandler != null) {
                        asyncHandler.processFailure(call, iOException);
                    }
                }

                public void onResponse(Call call, Response response) throws IOException {
                    if (asyncHandler != null) {
                        asyncHandler.processResponse(response);
                    }
                    if (response.body() != null) {
                        response.close();
                    }
                }
            });
            return new HttpAsyncHandle(newCall);
        }
        throw new RuntimeException("client is null");
    }

    public static HttpAsyncHandle a(Request request, final AsyncHandler asyncHandler) {
        Request.Builder builder = new Request.Builder();
        Headers a2 = HeaderUtil.a(request.b());
        if (a2 != null) {
            builder.headers(a2);
        }
        if (request.c().equals("POST")) {
            builder.url(request.a()).post(KeyValuePairUtil.a(request));
        } else if (request.c().equals("GET")) {
            builder.url(KeyValuePairUtil.a(request.a(), request.d()));
        } else {
            throw new RuntimeException("method unsupported");
        }
        Call newCall = a().newCall(builder.build());
        newCall.enqueue(new Callback() {
            public void onFailure(Call call, IOException iOException) {
                if (asyncHandler != null) {
                    asyncHandler.processFailure(call, iOException);
                }
            }

            public void onResponse(Call call, Response response) throws IOException {
                if (asyncHandler != null) {
                    asyncHandler.processResponse(response);
                }
                if (response.body() != null) {
                    response.close();
                }
            }
        });
        return new HttpAsyncHandle(newCall);
    }

    @Deprecated
    public static <R> R a(OkHttpClient okHttpClient, Request2 request2, SyncHandler<R> syncHandler) throws Exception {
        if (okHttpClient == null) {
            throw new RuntimeException("client is null");
        } else if (syncHandler != null) {
            Request.Builder builder = new Request.Builder();
            Headers a2 = HeaderUtil.a(request2.b());
            if (a2 != null) {
                builder.headers(a2);
            }
            if (request2.c().equals("POST")) {
                builder.url(request2.a()).post(RequestParamUtil.a(request2.d()));
            } else if (request2.c().equals("GET")) {
                builder.url(RequestParamUtil.a(request2.a(), request2.d()));
            } else {
                throw new RuntimeException("method unsupported");
            }
            try {
                Response execute = okHttpClient.newCall(builder.build()).execute();
                R b = syncHandler.b(execute);
                if (!(execute == null || execute.body() == null)) {
                    execute.close();
                }
                return b;
            } catch (IOException e) {
                throw new RuntimeException("failure:" + e.getMessage());
            }
        } else {
            throw new RuntimeException("handler is null");
        }
    }

    @Deprecated
    public static HttpAsyncHandle a(OkHttpClient okHttpClient, Request2 request2, final AsyncHandler asyncHandler) {
        if (okHttpClient != null) {
            Request.Builder builder = new Request.Builder();
            Headers a2 = HeaderUtil.a(request2.b());
            if (a2 != null) {
                builder.headers(a2);
            }
            if (request2.c().equals("POST")) {
                builder.url(request2.a()).post(RequestParamUtil.a(request2.d()));
            } else if (request2.c().equals("GET")) {
                builder.url(RequestParamUtil.a(request2.a(), request2.d()));
            } else {
                throw new RuntimeException("method unsupported");
            }
            Call newCall = okHttpClient.newCall(builder.build());
            newCall.enqueue(new Callback() {
                public void onFailure(Call call, IOException iOException) {
                    if (asyncHandler != null) {
                        asyncHandler.processFailure(call, iOException);
                    }
                }

                public void onResponse(Call call, Response response) throws IOException {
                    if (asyncHandler != null) {
                        asyncHandler.processResponse(response);
                    }
                    if (response.body() != null) {
                        response.close();
                    }
                }
            });
            return new HttpAsyncHandle(newCall);
        }
        throw new RuntimeException("client is null");
    }

    private static OkHttpClient a() {
        if (f19101a == null) {
            synchronized (HttpApi.class) {
                if (f19101a == null) {
                    f19101a = ClientUtil.a();
                }
            }
        }
        return f19101a;
    }
}
