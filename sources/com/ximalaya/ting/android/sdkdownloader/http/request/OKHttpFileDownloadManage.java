package com.ximalaya.ting.android.sdkdownloader.http.request;

import android.text.TextUtils;
import com.ximalaya.ting.android.opensdk.httputil.HttpDNSUtilForOpenSDK;
import com.ximalaya.ting.android.sdkdownloader.http.BaseParams;
import com.ximalaya.ting.android.sdkdownloader.http.ProgressHandler;
import com.ximalaya.ting.android.sdkdownloader.http.RequestParams;
import java.io.IOException;
import java.util.List;
import java.util.concurrent.TimeUnit;
import okhttp3.Call;
import okhttp3.OkHttpClient;
import okhttp3.Request;

class OKHttpFileDownloadManage {
    OKHttpFileDownloadManage() {
    }

    public static Call a(RequestParams requestParams, ProgressHandler progressHandler) throws IOException {
        OkHttpClient b = b(requestParams, progressHandler);
        if (b == null) {
            return null;
        }
        Request.Builder url = new Request.Builder().url(requestParams.e());
        List<BaseParams.Header> b2 = requestParams.b();
        if (b2 != null) {
            for (BaseParams.Header header : b2) {
                String str = header.b;
                String a2 = header.a();
                if (!TextUtils.isEmpty(str) && !TextUtils.isEmpty(a2)) {
                    if (header.f2353a) {
                        url.header(str, a2);
                    } else {
                        url.addHeader(str, a2);
                    }
                }
            }
        }
        return b.newCall(url.build());
    }

    private static OkHttpClient b(RequestParams requestParams, ProgressHandler progressHandler) {
        try {
            OkHttpClient.Builder proxy = new OkHttpClient().newBuilder().connectTimeout((long) requestParams.h(), TimeUnit.MILLISECONDS).readTimeout((long) requestParams.h(), TimeUnit.MILLISECONDS).proxy(requestParams.f());
            proxy.addInterceptor(HttpDNSUtilForOpenSDK.a());
            return proxy.build();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
