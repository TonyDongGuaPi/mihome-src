package com.xiaomi.miot.store.wxpay;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.support.v4.content.LocalBroadcastManager;
import android.text.TextUtils;
import com.tencent.mm.opensdk.modelpay.PayReq;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;
import com.xiaomi.miot.store.api.ICallback;
import com.xiaomi.miot.store.api.IPayProvider;
import com.xiaomi.youpin.log.LogUtils;
import java.util.HashMap;
import org.json.JSONException;
import org.json.JSONObject;

public class WxpayProvider implements IPayProvider {

    /* renamed from: a  reason: collision with root package name */
    public static final String f11439a = "com.xiaomi.smarthome.action.WX_PAY_RESULT";
    public static final String b = "extra_error_code";
    public static final String c = "extra_error_message";
    private static final String d = "WxpayProvider";
    private static final String e = "wxpay";
    private static final String f = "pay_result_code";
    private static final String g = "pay_result_msg";
    private static final String h = "appid";
    private static final String i = "partnerid";
    private static final String j = "prepayid";
    private static final String k = "noncestr";
    private static final String l = "package";
    private static final String m = "timestamp";
    private static final String n = "wx_sign";
    private static final int o = 0;
    private static final int p = 1;
    private static final int q = 2;
    private static final int r = 3;
    private IWXAPI s;
    /* access modifiers changed from: private */
    public ICallback t;
    /* access modifiers changed from: private */
    public PayReceiver u;
    private LocalBroadcastManager v = null;

    public String name() {
        return e;
    }

    public boolean onActivityResult(int i2, int i3, Intent intent) {
        return false;
    }

    public void clear() {
        if (!(this.v == null || this.u == null)) {
            this.v.unregisterReceiver(this.u);
        }
        this.u = null;
        this.v = null;
        this.u = null;
        this.t = null;
    }

    public void pay(Activity activity, String str, ICallback iCallback) {
        int i2;
        JSONObject jSONObject;
        this.t = iCallback;
        HashMap hashMap = new HashMap();
        int i3 = 1;
        if (TextUtils.isEmpty(str) || activity == null || activity.isFinishing()) {
            LogUtils.w(d, "pay param is invalid.payStr is:" + str + ",activity is:" + activity);
        } else {
            if (this.u == null) {
                this.u = new PayReceiver();
                IntentFilter intentFilter = new IntentFilter(f11439a);
                this.v = LocalBroadcastManager.getInstance(activity.getApplicationContext());
                this.v.registerReceiver(this.u, intentFilter);
            }
            try {
                jSONObject = new JSONObject(str);
                i2 = 0;
            } catch (JSONException e2) {
                LogUtils.e(d, "parse payStr failed.", e2);
                jSONObject = null;
                i2 = 1;
            }
            if (jSONObject != null) {
                String optString = jSONObject.optString("appid");
                a(activity, optString);
                int a2 = a();
                if (a2 == 0) {
                    PayReq payReq = new PayReq();
                    payReq.appId = optString;
                    payReq.partnerId = jSONObject.optString("partnerid");
                    payReq.prepayId = jSONObject.optString("prepayid");
                    payReq.nonceStr = jSONObject.optString("noncestr");
                    payReq.packageValue = jSONObject.optString("package");
                    payReq.timeStamp = jSONObject.optString("timestamp");
                    payReq.sign = jSONObject.optString(n);
                    if (this.s.sendReq(payReq)) {
                        LogUtils.d(d, "send req to wx api success!");
                        return;
                    }
                } else {
                    i3 = a2;
                }
            } else {
                i3 = i2;
            }
        }
        hashMap.put(f, String.valueOf(i3));
        this.t.callback(hashMap);
    }

    private void a(Activity activity, String str) {
        if (this.s == null) {
            this.s = WXAPIFactory.createWXAPI(activity.getApplicationContext(), str);
        }
        this.s.registerApp(str);
    }

    private int a() {
        if (!this.s.isWXAppInstalled()) {
            LogUtils.d(d, "wx app has not been installed.");
            return 2;
        } else if (this.s.isWXAppSupportAPI()) {
            return 0;
        } else {
            LogUtils.d(d, "Current wx app not support pay.");
            return 3;
        }
    }

    private class PayReceiver extends BroadcastReceiver {
        private PayReceiver() {
        }

        public void onReceive(Context context, Intent intent) {
            LogUtils.v(WxpayProvider.d, "PayReceiver onReceive.");
            LocalBroadcastManager.getInstance(context.getApplicationContext()).unregisterReceiver(this);
            PayReceiver unused = WxpayProvider.this.u = null;
            if (intent != null && TextUtils.equals(intent.getAction(), WxpayProvider.f11439a)) {
                int intExtra = intent.getIntExtra("extra_error_code", 0);
                String stringExtra = intent.getStringExtra(WxpayProvider.c);
                LogUtils.d(WxpayProvider.d, "received pay result,errorCode:" + intExtra + ",errMsg:" + stringExtra);
                if (WxpayProvider.this.t != null) {
                    HashMap hashMap = new HashMap();
                    hashMap.put(WxpayProvider.f, String.valueOf(intExtra));
                    hashMap.put(WxpayProvider.g, stringExtra);
                    WxpayProvider.this.t.callback(hashMap);
                }
            }
        }
    }
}
