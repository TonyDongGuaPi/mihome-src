package com.youpin.weex.app.util;

import com.alibaba.fastjson.JSON;
import com.xiaomi.youpin.log.LogUtils;
import com.xiaomi.youpin.youpin_network.YouPinHttpsApi;
import java.io.IOException;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.MediaType;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import okhttp3.ResponseBody;
import org.json.JSONObject;

public class HttpManager {
    /* access modifiers changed from: private */

    /* renamed from: a  reason: collision with root package name */
    public String f2545a;
    private MediaType b;

    public interface HttpCallback {
        void a(String str);

        void a(String str, Object obj);
    }

    private HttpManager() {
        this.f2545a = com.alipay.zoloz.android.phone.mrpc.core.HttpManager.TAG;
        this.b = MediaType.parse("application/json");
    }

    public static HttpManager a() {
        return Holder.f2547a;
    }

    public String a(String str, JSONObject jSONObject) throws Exception {
        Response execute = YouPinHttpsApi.a().b().newCall(new Request.Builder().post(RequestBody.create(this.b, jSONObject.toString())).url(str).build()).execute();
        if (execute.isSuccessful()) {
            ResponseBody body = execute.body();
            if (body != null) {
                return body.string();
            }
            throw new Exception("response.body() is null");
        }
        throw new Exception(execute.message());
    }

    public void a(String str, String str2, final Class cls, final HttpCallback httpCallback) {
        String str3 = this.f2545a;
        LogUtils.d(str3, "Weex params is : " + str2);
        YouPinHttpsApi.a().b().newCall(new Request.Builder().addHeader("Content-Type", "application/json").post(RequestBody.create(this.b, str2)).url(str).build()).enqueue(new Callback() {
            public void onFailure(Call call, IOException iOException) {
                String a2 = HttpManager.this.f2545a;
                LogUtils.d(a2, "Weex fail :error is " + iOException.toString());
                httpCallback.a(iOException.toString());
            }

            public void onResponse(Call call, Response response) throws IOException {
                String str;
                ResponseBody body = response.body();
                if (body == null) {
                    LogUtils.d(HttpManager.this.f2545a, "Weex fail: response.body() is null");
                    httpCallback.a("response.body() is null");
                } else if (!response.isSuccessful()) {
                    httpCallback.a(response.message());
                    try {
                        body.close();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    LogUtils.d(HttpManager.this.f2545a, "Weex fail is: " + response.message());
                } else {
                    try {
                        str = body.string();
                        try {
                            body.close();
                        } catch (Exception e2) {
                            e2.printStackTrace();
                        }
                    } catch (Exception e3) {
                        e3.printStackTrace();
                        try {
                            body.close();
                        } catch (Exception e4) {
                            e4.printStackTrace();
                        }
                        str = null;
                    } catch (Throwable th) {
                        try {
                            body.close();
                        } catch (Exception e5) {
                            e5.printStackTrace();
                        }
                        throw th;
                    }
                    LogUtils.d(HttpManager.this.f2545a, "Weex response: " + str);
                    if (cls == null) {
                        httpCallback.a(str, (Object) null);
                        try {
                            body.close();
                        } catch (Exception e6) {
                            e6.printStackTrace();
                        }
                    } else {
                        httpCallback.a(str, JSON.parseObject(str, cls));
                    }
                }
            }
        });
    }

    private static class Holder {
        /* access modifiers changed from: private */

        /* renamed from: a  reason: collision with root package name */
        public static HttpManager f2547a = new HttpManager();

        private Holder() {
        }
    }
}
