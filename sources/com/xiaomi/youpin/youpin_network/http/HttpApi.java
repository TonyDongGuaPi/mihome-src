package com.xiaomi.youpin.youpin_network.http;

import android.support.annotation.NonNull;
import com.xiaomi.youpin.common.util.IOUtils;
import com.xiaomi.youpin.log.LogUtils;
import com.xiaomi.youpin.network.bean.KeyValuePair;
import com.xiaomi.youpin.network.bean.NetRequest;
import com.xiaomi.youpin.network.util.KeyValuePairUtil;
import com.xiaomi.youpin.youpin_network.YouPinHttpsApi;
import java.io.BufferedInputStream;
import java.io.Closeable;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.List;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Headers;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import okhttp3.ResponseBody;

public class HttpApi {
    public static <R> R a(OkHttpClient okHttpClient, NetRequest netRequest, SyncHandler<R> syncHandler) throws Exception {
        if (okHttpClient == null) {
            throw new RuntimeException("client is null");
        } else if (syncHandler != null) {
            try {
                return syncHandler.a(okHttpClient.newCall(a(netRequest)).execute());
            } catch (IOException e) {
                throw new RuntimeException("failure:" + e.getMessage());
            }
        } else {
            throw new RuntimeException("handler is null");
        }
    }

    public static HttpAsyncHandle a(OkHttpClient okHttpClient, NetRequest netRequest, final AsyncHandler asyncHandler) {
        if (okHttpClient != null) {
            Call newCall = okHttpClient.newCall(a(netRequest));
            newCall.enqueue(new Callback() {
                public void onFailure(Call call, IOException iOException) {
                    if (asyncHandler != null) {
                        asyncHandler.a(call, iOException);
                    }
                }

                public void onResponse(Call call, Response response) throws IOException {
                    if (asyncHandler != null) {
                        asyncHandler.a(response);
                    }
                }
            });
            return new HttpAsyncHandle(newCall);
        }
        throw new RuntimeException("client is null");
    }

    public static HttpAsyncHandle a(NetRequest netRequest, final AsyncHandler asyncHandler) {
        Call newCall = YouPinHttpsApi.a().b().newCall(a(netRequest));
        newCall.enqueue(new Callback() {
            public void onFailure(Call call, IOException iOException) {
                if (asyncHandler != null) {
                    asyncHandler.a(call, iOException);
                }
            }

            public void onResponse(Call call, Response response) throws IOException {
                if (asyncHandler != null) {
                    asyncHandler.a(response);
                }
            }
        });
        return new HttpAsyncHandle(newCall);
    }

    public static boolean a(@NonNull String str, @NonNull File file) {
        FileOutputStream fileOutputStream;
        BufferedInputStream bufferedInputStream = null;
        try {
            Response execute = YouPinHttpsApi.a().b().newCall(new Request.Builder().url(str).build()).execute();
            if (execute == null) {
                IOUtils.a((Closeable) null);
                IOUtils.a((Closeable) null);
                return false;
            }
            ResponseBody body = execute.body();
            if (body == null) {
                IOUtils.a((Closeable) null);
                IOUtils.a((Closeable) null);
                return false;
            }
            fileOutputStream = new FileOutputStream(file);
            try {
                BufferedInputStream bufferedInputStream2 = new BufferedInputStream(body.byteStream());
                try {
                    IOUtils.a((InputStream) bufferedInputStream2, (OutputStream) fileOutputStream);
                    fileOutputStream.flush();
                    IOUtils.a((Closeable) bufferedInputStream2);
                    IOUtils.a((Closeable) fileOutputStream);
                    return true;
                } catch (IOException e) {
                    IOException iOException = e;
                    bufferedInputStream = bufferedInputStream2;
                    e = iOException;
                    try {
                        LogUtils.e("okhttp", "download file failed: " + str);
                        e.printStackTrace();
                        IOUtils.a((Closeable) bufferedInputStream);
                        IOUtils.a((Closeable) fileOutputStream);
                        return false;
                    } catch (Throwable th) {
                        th = th;
                        IOUtils.a((Closeable) bufferedInputStream);
                        IOUtils.a((Closeable) fileOutputStream);
                        throw th;
                    }
                } catch (Throwable th2) {
                    th = th2;
                    bufferedInputStream = bufferedInputStream2;
                    IOUtils.a((Closeable) bufferedInputStream);
                    IOUtils.a((Closeable) fileOutputStream);
                    throw th;
                }
            } catch (IOException e2) {
                e = e2;
                LogUtils.e("okhttp", "download file failed: " + str);
                e.printStackTrace();
                IOUtils.a((Closeable) bufferedInputStream);
                IOUtils.a((Closeable) fileOutputStream);
                return false;
            }
        } catch (IOException e3) {
            e = e3;
            fileOutputStream = null;
            LogUtils.e("okhttp", "download file failed: " + str);
            e.printStackTrace();
            IOUtils.a((Closeable) bufferedInputStream);
            IOUtils.a((Closeable) fileOutputStream);
            return false;
        } catch (Throwable th3) {
            th = th3;
            fileOutputStream = null;
            IOUtils.a((Closeable) bufferedInputStream);
            IOUtils.a((Closeable) fileOutputStream);
            throw th;
        }
    }

    private static Headers a(List<KeyValuePair> list) {
        if (list == null || list.isEmpty()) {
            return null;
        }
        return Headers.of(KeyValuePairUtil.b(list));
    }

    private static Request a(NetRequest netRequest) {
        Request.Builder builder = new Request.Builder();
        Headers a2 = a(netRequest.c());
        if (a2 != null) {
            builder.headers(a2);
        }
        if (netRequest.a() == 1) {
            builder.url(netRequest.b()).post(RequestBody.create(MediaType.parse("application/json"), netRequest.g()));
        } else if (netRequest.a() == 2) {
            builder.url(KeyValuePairUtil.a(netRequest.b(), netRequest.d()));
        } else {
            throw new RuntimeException("method unsupported");
        }
        return builder.build();
    }
}
