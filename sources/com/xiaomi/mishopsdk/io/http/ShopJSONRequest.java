package com.xiaomi.mishopsdk.io.http;

import android.text.TextUtils;
import android.util.Log;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.alibaba.fastjson.parser.Feature;
import com.mishopsdk.volley.NetworkError;
import com.mishopsdk.volley.NetworkResponse;
import com.mishopsdk.volley.NoConnectionError;
import com.mishopsdk.volley.ParseError;
import com.mishopsdk.volley.Response;
import com.mishopsdk.volley.ServerError;
import com.mishopsdk.volley.TimeoutError;
import com.mishopsdk.volley.VolleyError;
import com.mishopsdk.volley.toolbox.BaseRequest;
import com.mishopsdk.volley.toolbox.HttpHeaderParser;
import com.mishopsdk.volley.toolbox.ObjRequest;
import com.xiaomi.mishopsdk.ShopApp;
import com.xiaomi.mishopsdk.account.lib.AccountConstants;
import com.xiaomi.mishopsdk.account.lib.ExtendedAuthToken;
import com.xiaomi.mishopsdk.account.lib.LoginManager;
import com.xiaomi.mishopsdk.event.CloseSiteEvent;
import com.xiaomi.mishopsdk.event.RelogoutEvent;
import com.xiaomi.mishopsdk.io.entity.ShopApiBaseResult;
import com.xiaomi.mishopsdk.io.http.ShopApiError;
import com.xiaomi.mishopsdk.util.PreferenceUtil;
import de.greenrobot.event.EventBus;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class ShopJSONRequest<T> extends ObjRequest<T> {
    public static boolean DEBUG = Log.isLoggable(TAG, 2);
    private static final String TAG = "ShopJsonRequest";
    protected Class<T> mClass;
    protected TypeReference<T> mTypeToken;

    public ShopJSONRequest(Builder<?> builder) {
        super(builder);
        if (builder.mClass != null) {
            this.mClass = builder.mClass;
        }
        if (builder.mTypeToken != null) {
            this.mTypeToken = builder.mTypeToken;
        }
        if (this.mClass == null && this.mTypeToken == null) {
            throw new RuntimeException("ShopJSONRequest must have one Parse class or TypeToken");
        }
    }

    /* access modifiers changed from: protected */
    public Response parseApiResponse(String str, NetworkResponse networkResponse) throws VolleyError {
        T t;
        try {
            handleServiceToken(networkResponse);
            if (DEBUG) {
                Log.v(TAG, "ShopJSONRequest >> " + str);
            }
            ShopApiBaseResult parseShopApi = parseShopApi(str);
            if (TextUtils.isEmpty(parseShopApi.mCloseLink)) {
                if (parseShopApi.isApiOk(this.mClass != null && this.mClass.isAssignableFrom(Void.class))) {
                    if (this.mTypeToken != null) {
                        t = JSON.parseObject(parseShopApi.mJsonData.toString(), this.mTypeToken, new Feature[0]);
                    } else if (this.mClass.isAssignableFrom(JSONObject.class)) {
                        t = new JSONObject(parseShopApi.mJsonData.toString());
                    } else if (this.mClass.isAssignableFrom(JSONArray.class)) {
                        t = new JSONArray(parseShopApi.mJsonData.toString());
                    } else if (this.mClass.isAssignableFrom(String.class)) {
                        t = parseShopApi.mJsonData.toString();
                    } else if (this.mClass.isAssignableFrom(Void.class)) {
                        return Response.success(null, HttpHeaderParser.parseCacheHeaders(networkResponse));
                    } else {
                        t = JSON.parseObject(parseShopApi.mJsonData.toString(), this.mClass);
                    }
                    if (t != null) {
                        return Response.success(t, HttpHeaderParser.parseCacheHeaders(networkResponse));
                    }
                    ShopApiError shopApiError = new ShopApiError(networkResponse);
                    shopApiError.setErrorType(ShopApiError.ErrorType.PARSE);
                    throw shopApiError;
                }
                ShopApiError shopApiError2 = new ShopApiError(networkResponse);
                shopApiError2.setApiBaseResult(parseShopApi);
                shopApiError2.setErrorType(ShopApiError.ErrorType.CUSTOM);
                throw shopApiError2;
            }
            EventBus.getDefault().post(new CloseSiteEvent(parseShopApi.mCloseLink));
            throw new ParseError(networkResponse);
        } catch (JSONException unused) {
            ShopApiError shopApiError3 = new ShopApiError(networkResponse);
            shopApiError3.setErrorType(ShopApiError.ErrorType.PARSE);
            throw shopApiError3;
        }
    }

    private void handleServiceToken(NetworkResponse networkResponse) {
        if (networkResponse != null && networkResponse.headers != null) {
            String str = networkResponse.headers.get("Token-Refresh");
            if (!TextUtils.isEmpty(str) && Boolean.parseBoolean(str) && LoginManager.getInstance().hasLogin() && LoginManager.iShopAccountManager != null && System.currentTimeMillis() - ShopApp.invalidate_event_time > 10000) {
                ShopApp.invalidate_event_time = System.currentTimeMillis();
                LoginManager.iShopAccountManager.invalidateAuthToken("com.xiaomi", LoginManager.getInstance().getExtendedAuthToken("eshopmobile").toPlain());
                PreferenceUtil.setStringPref(ShopApp.instance, AccountConstants.PREF_EXTENDED_TOKEN, ExtendedAuthToken.parse(LoginManager.getInstance().getAccountAuthToken("eshopmobile")).toPlain());
            }
        }
    }

    /* access modifiers changed from: protected */
    public ShopApiBaseResult parseShopApi(String str) throws JSONException {
        LoginManager instance;
        LoginManager instance2;
        JSONObject jSONObject = new JSONObject(str);
        String optString = jSONObject.optString("info");
        long optLong = jSONObject.optLong("code", -1);
        String optString2 = jSONObject.optString("desc");
        ShopApiBaseResult.Builder jsonData = new ShopApiBaseResult.Builder().setCode(optLong).setInfo(optString).setDescription(optString2).setCloseLink(jSONObject.optString("close_link")).setJsonData(new StringBuilder(jSONObject.optString("data")));
        if ((optLong == 10000200010001000L || optLong == 21457512) && (instance2 = LoginManager.getInstance()) != null && instance2.hasLogin()) {
            LoginManager.getInstance().logout();
        }
        if ((optLong == 10000200010001004L || optLong == 10000200010001007L) && (instance = LoginManager.getInstance()) != null && instance.hasLogin()) {
            EventBus.getDefault().post(new RelogoutEvent(System.currentTimeMillis()));
        }
        return jsonData.build();
    }

    /* access modifiers changed from: protected */
    public VolleyError parseNetworkError(VolleyError volleyError) {
        if (volleyError instanceof ShopApiError) {
            return volleyError;
        }
        ShopApiError shopApiError = new ShopApiError(volleyError.networkResponse);
        shopApiError.initCause(volleyError.getCause());
        shopApiError.setNetworkTimeMs(volleyError.getNetworkTimeMs());
        if (volleyError instanceof NoConnectionError) {
            shopApiError.setErrorType(ShopApiError.ErrorType.NOCONNECTION);
        } else if (volleyError instanceof ServerError) {
            shopApiError.setErrorType(ShopApiError.ErrorType.SERVER);
        } else if (volleyError instanceof TimeoutError) {
            shopApiError.setErrorType(ShopApiError.ErrorType.TIMEOUT);
        } else if (volleyError instanceof ParseError) {
            shopApiError.setErrorType(ShopApiError.ErrorType.PARSE);
        } else if (volleyError instanceof NetworkError) {
            shopApiError.setErrorType(ShopApiError.ErrorType.NETWORK);
        }
        return shopApiError;
    }

    public static Builder<?> builder() {
        return new Builder<>();
    }

    public static class Builder<B extends Builder<B>> extends BaseRequest.Builder<B> {
        /* access modifiers changed from: private */
        public Class mClass;
        /* access modifiers changed from: private */
        public TypeReference mTypeToken;

        public B setClass(Class cls) {
            this.mClass = cls;
            return (Builder) self();
        }

        public B setTypeToken(TypeReference typeReference) {
            this.mTypeToken = typeReference;
            return (Builder) self();
        }

        public ShopJSONRequest build() {
            return new ShopJSONRequest(this);
        }
    }
}
