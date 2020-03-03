package com.xiaomi.youpin.youpin_common.statistic;

import com.xiaomi.youpin.cookie.YouPinCookieHandler;
import com.xiaomi.youpin.log.LogUtils;
import com.xiaomi.youpin.log.NetMonitor;
import com.xiaomi.youpin.youpin_common.UserAgent;
import java.io.IOException;
import java.util.concurrent.TimeUnit;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.Interceptor;
import okhttp3.JavaNetCookieJar;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.json.JSONArray;

public class StatApi {

    /* renamed from: a  reason: collision with root package name */
    static final String f23808a = "StatApi";
    static final String b = "https://shopapi.io.mi.com/app/stat/visit";
    static final String c = "https://shopapi.io.mi.com/app/stat/visitv2";
    private static final Object d = new Object();
    private static StatApi e;
    private OkHttpClient f;

    public interface CallBack {
        void a(int i, String str);

        void a(String str);
    }

    private StatApi() {
        OkHttpClient.Builder addNetworkInterceptor = new OkHttpClient.Builder().connectTimeout(20, TimeUnit.SECONDS).readTimeout(30, TimeUnit.SECONDS).writeTimeout(30, TimeUnit.SECONDS).cookieJar(new JavaNetCookieJar(new YouPinCookieHandler())).addNetworkInterceptor(new Interceptor() {
            public Response intercept(Interceptor.Chain chain) throws IOException {
                return chain.proceed(chain.request().newBuilder().removeHeader("User-Agent").addHeader("User-Agent", UserAgent.d()).build());
            }
        });
        NetMonitor.addNetworkInterceptor(addNetworkInterceptor);
        this.f = addNetworkInterceptor.build();
    }

    public static StatApi a() {
        if (e == null) {
            synchronized (d) {
                if (e == null) {
                    e = new StatApi();
                }
            }
        }
        return e;
    }

    public void a(JSONArray jSONArray, final CallBack callBack) {
        FormBody.Builder builder = new FormBody.Builder();
        builder.add("data", jSONArray.toString());
        LogUtils.d(f23808a, "https://shopapi.io.mi.com/app/stat/visit :" + jSONArray.toString());
        this.f.newCall(new Request.Builder().url(b).post(builder.build()).build()).enqueue(new Callback() {
            public void onFailure(Call call, IOException iOException) {
                String str;
                if (iOException != null) {
                    LogUtils.d(StatApi.f23808a, iOException.toString());
                }
                if (callBack != null) {
                    CallBack callBack = callBack;
                    if (iOException == null) {
                        str = "net request failure";
                    } else {
                        str = iOException.getMessage();
                    }
                    callBack.a(-1, str);
                }
            }

            public void onResponse(Call call, Response response) throws IOException {
                LogUtils.d(StatApi.f23808a, response.toString());
                if (response.isSuccessful()) {
                    try {
                        String string = response.body().string();
                        if (callBack != null) {
                            callBack.a(string);
                        }
                    } catch (Exception e) {
                        if (callBack != null) {
                            callBack.a(-1, e.getMessage());
                        }
                    }
                } else if (callBack != null) {
                    callBack.a(response.code(), response.message());
                }
            }
        });
    }

    public void b(JSONArray jSONArray, final CallBack callBack) {
        FormBody.Builder builder = new FormBody.Builder();
        builder.add("data", jSONArray.toString());
        LogUtils.d(f23808a, "https://shopapi.io.mi.com/app/stat/visitv2 :" + jSONArray.toString());
        this.f.newCall(new Request.Builder().url(c).post(builder.build()).build()).enqueue(new Callback() {
            public void onFailure(Call call, IOException iOException) {
                if (iOException != null) {
                    LogUtils.d(StatApi.f23808a, iOException.toString());
                }
                if (callBack != null) {
                    callBack.a(-1, iOException == null ? "net request failure" : iOException.getMessage());
                }
            }

            public void onResponse(Call call, Response response) throws IOException {
                LogUtils.d(StatApi.f23808a, response.toString());
                if (response.isSuccessful()) {
                    try {
                        String string = response.body().string();
                        if (callBack != null) {
                            callBack.a(string);
                        }
                    } catch (Exception e) {
                        if (callBack != null) {
                            callBack.a(-1, e.getMessage());
                        }
                    }
                } else if (callBack != null) {
                    callBack.a(response.code(), response.message());
                }
            }
        });
    }
}
