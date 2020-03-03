package com.xiaomi.smarthome.library.common.network;

import com.xiaomi.smarthome.device.api.Parser;
import com.xiaomi.smarthome.frame.AsyncCallback;
import com.xiaomi.smarthome.frame.Error;
import com.xiaomi.smarthome.frame.ErrorCode;
import com.xiaomi.smarthome.library.apache.http.NameValuePair;
import com.xiaomi.smarthome.library.http.ClientUtil;
import com.xiaomi.smarthome.library.http.HttpApi;
import com.xiaomi.smarthome.library.http.Request2;
import com.xiaomi.smarthome.library.http.async.AsyncHandler;
import com.xiaomi.smarthome.library.http.async.TextAsyncHandler;
import com.xiaomi.smarthome.library.http.util.RequestParamUtil;
import java.io.IOException;
import java.util.List;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.json.JSONException;

public class LocalNetworkUtils {

    /* renamed from: a  reason: collision with root package name */
    private static OkHttpClient f18630a = ClientUtil.a();

    public interface JSONParser<T> {
        T a(String str) throws JSONException;
    }

    public static void a(String str, TextAsyncHandler textAsyncHandler) {
        HttpApi.a(f18630a, new Request2.Builder().a("GET").b(str).a(), (AsyncHandler) textAsyncHandler);
    }

    public static <T> void a(String str, String str2, List<NameValuePair> list, final JSONParser<T> jSONParser, final AsyncCallback<T, Error> asyncCallback) {
        Request request;
        if (str2.equals("POST")) {
            request = new Request.Builder().url(str).post(RequestParamUtil.a(list)).build();
        } else {
            request = new Request.Builder().url(RequestParamUtil.a(str, list)).build();
        }
        f18630a.newCall(request).enqueue(new Callback() {
            public void onFailure(Call call, IOException iOException) {
                String str;
                if (asyncCallback != null) {
                    AsyncCallback asyncCallback = asyncCallback;
                    int code = ErrorCode.INVALID.getCode();
                    if (iOException == null) {
                        str = "request failure";
                    } else {
                        str = iOException.getMessage();
                    }
                    asyncCallback.sendFailureMessage(new Error(code, str));
                }
            }

            public void onResponse(Call call, Response response) throws IOException {
                if (response.isSuccessful()) {
                    try {
                        String string = response.body().string();
                        Object obj = null;
                        if (jSONParser != null) {
                            obj = jSONParser.a(string);
                        }
                        if (asyncCallback != null) {
                            asyncCallback.sendSuccessMessage(obj);
                        }
                    } catch (Exception e) {
                        if (asyncCallback != null) {
                            asyncCallback.sendFailureMessage(new Error(ErrorCode.INVALID.getCode(), e.getMessage()));
                        }
                    }
                } else if (asyncCallback != null) {
                    asyncCallback.sendFailureMessage(new Error(response.code(), "request failure"));
                }
            }
        });
    }

    public static <T> void a(String str, String str2, List<NameValuePair> list, final com.xiaomi.smarthome.device.api.Callback<T> callback, final Parser<T> parser) {
        a(str, str2, list, new JSONParser<T>() {
            public T a(String str) throws JSONException {
                if (parser != null) {
                    return parser.parse(str);
                }
                return null;
            }
        }, new AsyncCallback<T, Error>() {
            public void onSuccess(T t) {
                if (callback != null) {
                    callback.onSuccess(t);
                }
            }

            public void onFailure(Error error) {
                if (callback != null) {
                    callback.onFailure(error.a(), "");
                }
            }
        });
    }
}
