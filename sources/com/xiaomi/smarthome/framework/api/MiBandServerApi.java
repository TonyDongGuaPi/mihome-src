package com.xiaomi.smarthome.framework.api;

import com.facebook.internal.AnalyticsEvents;
import com.xiaomi.smarthome.frame.ErrorCode;
import com.xiaomi.smarthome.library.apache.http.NameValuePair;
import com.xiaomi.smarthome.library.apache.http.message.BasicNameValuePair;
import com.xiaomi.smarthome.library.common.network.NetworkUtils;
import com.xiaomi.smarthome.library.http.ClientUtil;
import com.xiaomi.smarthome.library.http.Error;
import com.xiaomi.smarthome.library.http.HttpApi;
import com.xiaomi.smarthome.library.http.Request2;
import com.xiaomi.smarthome.library.http.async.AsyncHandler;
import com.xiaomi.smarthome.library.http.async.HttpAsyncHandle;
import com.xiaomi.smarthome.library.http.async.TextAsyncHandler;
import com.xiaomi.smarthome.miio.miband.utils.MiBandOAuthSetting;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import okhttp3.OkHttpClient;
import okhttp3.Response;
import org.json.JSONException;
import org.json.JSONObject;

public class MiBandServerApi {

    /* renamed from: a  reason: collision with root package name */
    public static final String f16372a = "https://hmservice.mi-ae.com.cn";
    public static final String b = "POST";
    public static final String c = "GET";
    private static volatile MiBandServerApi d;
    private OkHttpClient e = ClientUtil.a();

    private MiBandServerApi() {
    }

    public static MiBandServerApi a() {
        if (d == null) {
            synchronized (MiBandServerApi.class) {
                if (d == null) {
                    d = new MiBandServerApi();
                }
            }
        }
        return d;
    }

    public HttpAsyncHandle a(String str, String str2, final AsyncResponseCallback<JSONObject> asyncResponseCallback) {
        if (NetworkUtils.a()) {
            ArrayList arrayList = new ArrayList(15);
            arrayList.add(new BasicNameValuePair("appid", MiBandOAuthSetting.b()));
            arrayList.add(new BasicNameValuePair("third_appid", MiBandOAuthSetting.f19476a));
            arrayList.add(new BasicNameValuePair("third_appsecret", MiBandOAuthSetting.b));
            arrayList.add(new BasicNameValuePair("l", "english"));
            arrayList.add(new BasicNameValuePair("v", "1.0"));
            arrayList.add(new BasicNameValuePair(AnalyticsEvents.PARAMETER_CALL_ID, Long.toString(System.currentTimeMillis() / 1000)));
            arrayList.add(new BasicNameValuePair("mac_key", str2));
            arrayList.add(new BasicNameValuePair("access_token", str));
            return HttpApi.a(this.e, new Request2.Builder().a("POST").b("https://hmservice.mi-ae.com.cn/user/info/getData").a((List<NameValuePair>) arrayList).a(), (AsyncHandler) new TextAsyncHandler() {
                /* renamed from: a */
                public void onSuccess(String str, Response response) {
                    try {
                        JSONObject jSONObject = new JSONObject(str);
                        int optInt = jSONObject.optInt("code");
                        ErrorCode valueof = ErrorCode.valueof(optInt);
                        if (optInt != 1) {
                            if (optInt != 0) {
                                if (asyncResponseCallback != null) {
                                    asyncResponseCallback.a(valueof.getCode());
                                    return;
                                }
                                return;
                            }
                        }
                        if (asyncResponseCallback != null) {
                            asyncResponseCallback.a(jSONObject);
                        }
                    } catch (JSONException unused) {
                        if (asyncResponseCallback != null) {
                            asyncResponseCallback.a(ErrorCode.INVALID.getCode());
                        }
                    }
                }

                public void onFailure(Error error, Exception exc, Response response) {
                    if (asyncResponseCallback != null) {
                        asyncResponseCallback.a(ErrorCode.INVALID.getCode());
                    }
                }
            });
        } else if (asyncResponseCallback == null) {
            return null;
        } else {
            asyncResponseCallback.a(ErrorCode.ERROR_NETWORK_ERROR.getCode());
            return null;
        }
    }

    public HttpAsyncHandle a(Date date, Date date2, String str, String str2, final AsyncResponseCallback<JSONObject> asyncResponseCallback) {
        if (NetworkUtils.a()) {
            ArrayList arrayList = new ArrayList(15);
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
            String format = simpleDateFormat.format(date);
            String format2 = simpleDateFormat.format(date2);
            arrayList.add(new BasicNameValuePair("appid", MiBandOAuthSetting.b()));
            arrayList.add(new BasicNameValuePair("third_appid", MiBandOAuthSetting.f19476a));
            arrayList.add(new BasicNameValuePair("third_appsecret", MiBandOAuthSetting.b));
            arrayList.add(new BasicNameValuePair("fromdate", format));
            arrayList.add(new BasicNameValuePair("todate", format2));
            arrayList.add(new BasicNameValuePair("l", "english"));
            arrayList.add(new BasicNameValuePair("v", "1.0"));
            arrayList.add(new BasicNameValuePair(AnalyticsEvents.PARAMETER_CALL_ID, Long.toString(System.currentTimeMillis() / 1000)));
            arrayList.add(new BasicNameValuePair("mac_key", str2));
            arrayList.add(new BasicNameValuePair("access_token", str));
            return HttpApi.a(this.e, new Request2.Builder().a("POST").b("https://hmservice.mi-ae.com.cn/user/summary/getData").a((List<NameValuePair>) arrayList).a(), (AsyncHandler) new TextAsyncHandler() {
                /* renamed from: a */
                public void onSuccess(String str, Response response) {
                    try {
                        JSONObject jSONObject = new JSONObject(str);
                        int optInt = jSONObject.optInt("code");
                        ErrorCode valueof = ErrorCode.valueof(optInt);
                        if (optInt != 1) {
                            if (optInt != 0) {
                                if (asyncResponseCallback != null) {
                                    asyncResponseCallback.a(valueof.getCode());
                                    return;
                                }
                                return;
                            }
                        }
                        if (asyncResponseCallback != null) {
                            asyncResponseCallback.a(jSONObject);
                        }
                    } catch (JSONException unused) {
                        if (asyncResponseCallback != null) {
                            asyncResponseCallback.a(ErrorCode.INVALID.getCode());
                        }
                    }
                }

                public void onFailure(Error error, Exception exc, Response response) {
                    if (asyncResponseCallback != null) {
                        asyncResponseCallback.a(ErrorCode.INVALID.getCode());
                    }
                }
            });
        } else if (asyncResponseCallback == null) {
            return null;
        } else {
            asyncResponseCallback.a(ErrorCode.ERROR_NETWORK_ERROR.getCode());
            return null;
        }
    }
}
