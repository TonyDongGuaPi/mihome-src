package com.mi.global.shop.request;

import android.content.Context;
import android.text.TextUtils;
import android.webkit.CookieSyncManager;
import com.alipay.sdk.sys.a;
import com.android.volley.AuthFailureError;
import com.android.volley.Response;
import com.mi.global.shop.ShopApp;
import com.mi.global.shop.util.MiShopStatInterface;
import com.mi.global.shop.util.NetworkUtil;
import com.mi.global.shop.util.Utils;
import com.mi.global.shop.xmsf.account.LoginManager;
import com.mi.log.LogUtil;
import com.mi.multimonitor.Request;
import com.mi.util.Device;
import com.mi.util.RequestQueueUtil;
import com.mi.volley.MiAbstractJsonObjectRequest;
import java.util.Map;
import org.json.JSONException;
import org.json.JSONObject;

public class MiJsonObjectRequest extends MiAbstractJsonObjectRequest {

    /* renamed from: a  reason: collision with root package name */
    public static final int f6941a = 20005;
    /* access modifiers changed from: private */
    public static final String b = "MiJsonObjectRequest";
    /* access modifiers changed from: private */
    public Response.Listener<JSONObject> c;

    public MiJsonObjectRequest(int i, String str, Response.Listener<JSONObject> listener, Response.ErrorListener errorListener) {
        this(i, str, (String) null, listener, errorListener);
    }

    public MiJsonObjectRequest(int i, String str, String str2, Response.Listener<JSONObject> listener, Response.ErrorListener errorListener) {
        super(i, str, str2, listener, errorListener);
        this.c = this.mListener;
        if (this.mListener != null) {
            this.mListener = new Response.Listener<JSONObject>() {
                /* renamed from: a */
                public void onResponse(JSONObject jSONObject) {
                    try {
                        if (jSONObject.getInt(Request.RESULT_CODE_KEY) == 20005) {
                            LoginManager u = LoginManager.u();
                            u.q();
                            String r = u.r();
                            if (!TextUtils.isEmpty(r)) {
                                String b = MiJsonObjectRequest.b;
                                LogUtil.b(b, "new extended token plain:" + r);
                                Utils.Preference.setStringPref("pref_extended_token", r);
                                Utils.Preference.setLongPref("pref_last_refresh_serviceToken_time", Long.valueOf(System.currentTimeMillis()));
                                RequestQueueUtil.a().add((MiJsonObjectRequest) MiJsonObjectRequest.this.clone());
                                return;
                            }
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    } catch (CloneNotSupportedException e2) {
                        e2.printStackTrace();
                    }
                    MiJsonObjectRequest.this.c.onResponse(jSONObject);
                }
            };
        }
    }

    public Map<String, String> getHeaders() throws AuthFailureError {
        Map<String, String> headers = super.getHeaders();
        headers.put("Mi-Info", a());
        return headers;
    }

    public String getCookies() {
        try {
            CookieSyncManager.createInstance(ShopApp.g());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return CookieUtil.a();
    }

    public static String a() {
        StringBuilder sb = new StringBuilder();
        sb.append("version");
        sb.append("=");
        sb.append(Device.r);
        sb.append(a.b);
        sb.append("phone_model");
        sb.append("=");
        sb.append(Device.e);
        sb.append(a.b);
        sb.append("networkType");
        sb.append("=");
        sb.append(NetworkUtil.a());
        sb.append(a.b);
        sb.append("appname");
        sb.append("=");
        sb.append("shop");
        sb.append(a.b);
        sb.append("android_sdk_version");
        sb.append("=");
        sb.append(Device.m);
        sb.append(a.b);
        sb.append("android_version");
        sb.append("=");
        sb.append(Device.o);
        sb.append(a.b);
        if (TextUtils.isEmpty(MiShopStatInterface.a((Context) ShopApp.g()))) {
            sb.append("DEVICEID");
            sb.append("=");
            sb.append(Device.C);
            sb.append(a.b);
        } else {
            sb.append("DEVICEID");
            sb.append("=");
            sb.append(MiShopStatInterface.a((Context) ShopApp.g()));
            sb.append(a.b);
        }
        if (ShopApp.h != null) {
            if (ShopApp.h.f.equals("community_sdk")) {
                sb.append("request_from");
                sb.append("=");
                sb.append("community_sdk");
                sb.append(a.b);
            } else if (ShopApp.h.f.equals("mihome_sdk")) {
                sb.append("request_from");
                sb.append("=");
                sb.append("mihome_sdk");
                sb.append(a.b);
            } else {
                sb.append("request_from");
                sb.append("=");
                sb.append("community_sdk");
                sb.append(a.b);
            }
        }
        String str = b;
        LogUtil.b(str, "device:" + sb.toString());
        return sb.toString();
    }
}
