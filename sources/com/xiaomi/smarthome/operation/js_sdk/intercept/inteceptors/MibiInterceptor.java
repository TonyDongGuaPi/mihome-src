package com.xiaomi.smarthome.operation.js_sdk.intercept.inteceptors;

import android.app.Activity;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Base64;
import android.util.Log;
import android.webkit.WebView;
import com.taobao.weex.ui.view.gesture.WXGestureType;
import com.xiaomi.passport.accountmanager.MiAccountManager;
import com.xiaomi.payment.data.MibiConstants;
import com.xiaomi.smarthome.frame.server_compact.ServerCompact;
import com.xiaomi.smarthome.framework.log.LogUtil;
import com.xiaomi.smarthome.miio.camera.cloudstorage.model.ICloudVideoCallback;
import com.xiaomi.smarthome.miio.camera.cloudstorage.utils.CloudVideoNetUtils;
import com.xiaomi.smarthome.operation.js_sdk.intercept.IUrlInterceptorAdapter;
import java.util.Locale;
import java.util.UUID;
import miuipub.payment.IXmsfPaymentListener;
import miuipub.payment.XmsfPaymentManager;
import org.json.JSONException;
import org.json.JSONObject;

public class MibiInterceptor extends IUrlInterceptorAdapter {

    /* renamed from: a  reason: collision with root package name */
    private static final String f21076a = "MibiInterceptor";
    /* access modifiers changed from: private */
    public final Context b;
    private String c = null;

    public MibiInterceptor(Context context) {
        this.b = context;
    }

    public String c() {
        return this.c;
    }

    public void a(String str) {
        this.c = str;
    }

    public boolean a(WebView webView, String str) {
        try {
            Uri parse = Uri.parse(str);
            Log.d(f21076a, "shouldOverrideUrlLoading:" + str);
            if (parse != null && parse.getHost() != null && parse.getHost().contains("mibi.mi.com") && !TextUtils.isEmpty(parse.getPath()) && parse.getPath().contains("pay")) {
                String queryParameter = parse.getQueryParameter(WXGestureType.GestureInfo.POINTER_ID);
                if (!TextUtils.isEmpty(queryParameter) && queryParameter.equals("mipay_sr62m5p7ds")) {
                    webView.stopLoading();
                    try {
                        String queryParameter2 = parse.getQueryParameter("pid");
                        String queryParameter3 = parse.getQueryParameter("xiaomiId");
                        boolean booleanQueryParameter = parse.getBooleanQueryParameter("autoRenew", false);
                        String queryParameter4 = parse.getQueryParameter("payExtraInfo");
                        String queryParameter5 = parse.getQueryParameter("did");
                        if (TextUtils.isEmpty(queryParameter5)) {
                            queryParameter5 = this.c;
                        }
                        if (TextUtils.isEmpty(queryParameter5)) {
                            LogUtil.b(f21076a, "processPayMibi: empty did: pay failed");
                            return false;
                        }
                        JSONObject jSONObject = new JSONObject();
                        jSONObject.put("userId", queryParameter3);
                        jSONObject.put("did", queryParameter5);
                        jSONObject.put("pid", queryParameter2);
                        jSONObject.put("autoRenew", booleanQueryParameter);
                        if (!TextUtils.isEmpty(queryParameter4)) {
                            jSONObject.put("payExtraInfo", queryParameter4);
                        }
                        Locale d = ServerCompact.d(this.b);
                        if (d == null || TextUtils.isEmpty(d.getCountry())) {
                            Locale locale = Locale.getDefault();
                            if (locale != null && !TextUtils.isEmpty(locale.getCountry())) {
                                jSONObject.put("region", "" + locale.getCountry().toLowerCase());
                            }
                        } else {
                            jSONObject.put("region", "" + d.getCountry().toLowerCase());
                        }
                        a(jSONObject.toString(), booleanQueryParameter);
                        return true;
                    } catch (Exception e) {
                        Log.e(f21076a, "processPayMibi: " + e.getLocalizedMessage());
                        return true;
                    }
                }
            }
            return false;
        } catch (Exception e2) {
            Log.e(f21076a, "processPayMibi: ", e2);
            return false;
        }
    }

    private void a(String str, final boolean z) {
        if (!TextUtils.isEmpty(str)) {
            CloudVideoNetUtils.getInstance().getMipaySignV2(str, new ICloudVideoCallback<JSONObject>() {
                /* renamed from: a */
                public void onCloudVideoSuccess(JSONObject jSONObject, Object obj) {
                    JSONObject optJSONObject;
                    if (jSONObject != null) {
                        int optInt = jSONObject.optInt("code", -1);
                        String optString = jSONObject.optString("result");
                        if (!TextUtils.isEmpty(optString) && optString.equals("ok") && optInt == 0 && (optJSONObject = jSONObject.optJSONObject("data")) != null) {
                            String optString2 = optJSONObject.optString("orderToken");
                            if (!TextUtils.isEmpty(optString2)) {
                                try {
                                    String str = new String(Base64.decode(optString2.getBytes("UTF-8"), 8));
                                    LogUtil.a(MibiInterceptor.f21076a, "decodedOrderTokenString " + str);
                                    String str2 = null;
                                    try {
                                        str2 = new JSONObject(str).optString(MibiConstants.gA);
                                    } catch (JSONException e) {
                                        e.printStackTrace();
                                    }
                                    MiAccountManager miAccountManager = MiAccountManager.get(MibiInterceptor.this.b);
                                    XmsfPaymentManager a2 = XmsfPaymentManager.a(MibiInterceptor.this.b);
                                    if (miAccountManager.isUseSystem()) {
                                        miAccountManager.setUseSystem();
                                    }
                                    a2.d();
                                    if (z) {
                                        a2.b((Activity) MibiInterceptor.this.b, str, str2, new Bundle());
                                        return;
                                    }
                                    a2.a((Activity) MibiInterceptor.this.b, UUID.randomUUID().toString(), str, (Bundle) null, (IXmsfPaymentListener) new IXmsfPaymentListener() {
                                        public void a(String str, Bundle bundle) {
                                            LogUtil.a(MibiInterceptor.f21076a, "pay success:" + str);
                                        }

                                        public void a(String str, int i, String str2, Bundle bundle) {
                                            LogUtil.a(MibiInterceptor.f21076a, "pay failed:" + str);
                                        }
                                    });
                                } catch (Exception e2) {
                                    LogUtil.b(MibiInterceptor.f21076a, "getMipaySign exception:" + e2.getLocalizedMessage());
                                }
                            }
                        }
                    }
                }

                public void onCloudVideoFailed(int i, String str) {
                    LogUtil.b(MibiInterceptor.f21076a, "onCloudVideoFailed code:" + i + " info:" + str);
                }
            });
        }
    }
}
