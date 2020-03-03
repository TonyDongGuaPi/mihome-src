package com.mi.global.shop.base.request;

import android.text.TextUtils;
import android.webkit.CookieSyncManager;
import com.alibaba.android.arouter.facade.annotation.Autowired;
import com.alibaba.android.arouter.launcher.ARouter;
import com.android.volley.AuthFailureError;
import com.android.volley.Response;
import com.mi.global.shop.base.ApplicationAgent;
import com.mi.global.shop.base.service.CookieUtilService;
import com.mi.global.shop.base.service.LoginManagerService;
import com.mi.global.shop.base.utils.Utils;
import com.mi.log.LogUtil;
import com.mi.multimonitor.Request;
import com.mi.util.RequestQueueUtil;
import com.mi.volley.MiAbstractJsonObjectRequest;
import java.util.Map;
import org.json.JSONException;
import org.json.JSONObject;

public class MiJsonObjectRequest extends MiAbstractJsonObjectRequest {
    public static final int CODE_EXPIRED = 20005;
    /* access modifiers changed from: private */
    public static final String TAG = "MiJsonObjectRequest";
    @Autowired
    CookieUtilService cookieUtilService;
    /* access modifiers changed from: private */
    public Response.Listener<JSONObject> internalListener;
    @Autowired
    LoginManagerService loginManagerService;

    public MiJsonObjectRequest(int i, String str, Response.Listener<JSONObject> listener, Response.ErrorListener errorListener) {
        this(i, str, (String) null, listener, errorListener);
        ARouter.a().a((Object) this);
    }

    public MiJsonObjectRequest(int i, String str, String str2, Response.Listener<JSONObject> listener, Response.ErrorListener errorListener) {
        super(i, str, str2, listener, errorListener);
        ARouter.a().a((Object) this);
        if (this.cookieUtilService == null || this.loginManagerService == null) {
            throw new RuntimeException("调用方需要实现 globalShopBase 组件的 Service 包的所有接口。");
        }
        this.internalListener = this.mListener;
        if (this.mListener != null) {
            this.mListener = new Response.Listener<JSONObject>() {
                public void onResponse(JSONObject jSONObject) {
                    try {
                        if (jSONObject.getInt(Request.RESULT_CODE_KEY) == 20005) {
                            MiJsonObjectRequest.this.loginManagerService.a();
                            String b = MiJsonObjectRequest.this.loginManagerService.b();
                            if (!TextUtils.isEmpty(b)) {
                                String access$000 = MiJsonObjectRequest.TAG;
                                LogUtil.b(access$000, "new extended token plain:" + b);
                                Utils.Preference.setStringPref("pref_extended_token", b);
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
                    MiJsonObjectRequest.this.internalListener.onResponse(jSONObject);
                }
            };
        }
    }

    public Map<String, String> getHeaders() throws AuthFailureError {
        Map<String, String> headers = super.getHeaders();
        headers.put("Mi-Info", SimpleProtobufRequest.getDeviceInfo());
        return headers;
    }

    public String getCookies() {
        try {
            CookieSyncManager.createInstance(ApplicationAgent.f5586a);
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (this.cookieUtilService == null) {
            this.cookieUtilService = (CookieUtilService) ARouter.a().a(CookieUtilService.class);
        }
        return this.cookieUtilService.a();
    }
}
