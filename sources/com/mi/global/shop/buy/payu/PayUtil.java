package com.mi.global.shop.buy.payu;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.text.TextUtils;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.mi.global.shop.R;
import com.mi.global.shop.ShopApp;
import com.mi.global.shop.buy.BFLVerifyOTPFragment;
import com.mi.global.shop.buy.ConfirmActivity;
import com.mi.global.shop.buy.GoogleTezPayWrapper;
import com.mi.global.shop.buy.payu.PayU;
import com.mi.global.shop.locale.LocaleHelper;
import com.mi.global.shop.newmodel.pay.payparam.NewPayGoData;
import com.mi.global.shop.newmodel.pay.payparam.NewPayGoResult;
import com.mi.global.shop.request.SimpleCallback;
import com.mi.global.shop.request.SimpleJsonRequest;
import com.mi.global.shop.request.SimpleProtobufRequest;
import com.mi.global.shop.util.ConnectionHelper;
import com.mi.global.shop.util.RequestUtil;
import com.mi.global.shop.util.Utils;
import com.mi.global.shop.xmsf.account.LoginManager;
import com.mi.log.LogUtil;
import com.mi.multimonitor.CrashReport;
import com.mi.util.MiToast;
import com.mi.util.RequestQueueUtil;
import com.payu.sdk.Constants;
import com.payu.sdk.Params;
import com.payu.sdk.Payment;
import com.payu.sdk.ProcessPaymentActivity;
import com.payu.sdk.exceptions.HashException;
import com.payu.sdk.exceptions.MissingParameterException;
import com.xiaomi.stat.d;
import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import org.json.JSONException;
import org.json.JSONObject;

public class PayUtil {

    /* renamed from: a  reason: collision with root package name */
    private static final String f6893a = "PayUtil";

    public static void a(String str, String str2, String str3, ConfirmActivity confirmActivity, PayU.PaymentMode paymentMode, Params params, String str4, String str5, String str6, String str7, String str8) {
        Request request;
        Uri.Builder buildUpon = Uri.parse(ConnectionHelper.aY()).buildUpon();
        String str9 = str;
        buildUpon.appendQueryParameter("id", str);
        buildUpon.appendQueryParameter("bank", str2);
        buildUpon.appendQueryParameter("type", str3);
        if (!TextUtils.isEmpty(str5)) {
            buildUpon.appendQueryParameter("emibank", str5);
        } else {
            String str10 = str5;
        }
        String str11 = params.get(PayU.j);
        if (!TextUtils.isEmpty(str11)) {
            buildUpon.appendQueryParameter(PayU.j, str11);
        }
        if (!TextUtils.isEmpty(str6)) {
            buildUpon.appendQueryParameter(BFLVerifyOTPFragment.c, str6);
        } else {
            String str12 = str6;
        }
        if (!TextUtils.isEmpty(str7)) {
            buildUpon.appendQueryParameter(d.aa, str7);
        } else {
            String str13 = str7;
        }
        if (!TextUtils.isEmpty(str8)) {
            buildUpon.appendQueryParameter("vpa", str8);
        } else {
            String str14 = str8;
        }
        HashMap hashMap = new HashMap();
        hashMap.put("checkcode", b(str4));
        final ConfirmActivity confirmActivity2 = confirmActivity;
        final String str15 = str3;
        final PayU.PaymentMode paymentMode2 = paymentMode;
        final Params params2 = params;
        final String str16 = str;
        final String str17 = str2;
        final String str18 = str4;
        final String str19 = str5;
        final String str20 = str6;
        final String str21 = str7;
        final String str22 = str8;
        AnonymousClass1 r2 = new SimpleCallback<NewPayGoResult>() {
            public void a(NewPayGoResult newPayGoResult) {
                NewPayGoResult newPayGoResult2 = newPayGoResult;
                confirmActivity2.hideLoading();
                if (newPayGoResult2.data != null) {
                    PayUtil.a(confirmActivity2, newPayGoResult2.data.params);
                    if (!Constants.PAYTYPE_TEZ.equalsIgnoreCase(str15)) {
                        PayUtil.a(confirmActivity2, paymentMode2, params2);
                    } else if (TextUtils.isEmpty(newPayGoResult2.data.ext)) {
                        PayUtil.a(str16, str17, Constants.PAYTYPE_UPI, confirmActivity2, paymentMode2, params2, str18, str19, str20, str21, str22);
                    } else {
                        PayUtil.b(confirmActivity2, newPayGoResult2.data, str15, str16, paymentMode2, params2);
                    }
                }
            }

            public void a(String str) {
                super.a(str);
                LogUtil.b(PayUtil.f6893a, "getOrderViewInfo Exception:" + str);
                confirmActivity2.hideLoading();
                PayUtil.d(str);
            }
        };
        if (ShopApp.n()) {
            request = new SimpleProtobufRequest(buildUpon.toString(), NewPayGoResult.class, RequestUtil.a((Map<String, String>) hashMap, true), r2);
        } else {
            request = new SimpleJsonRequest(buildUpon.toString(), NewPayGoResult.class, RequestUtil.a((Map<String, String>) hashMap, true), r2);
        }
        request.setTag(f6893a);
        RequestQueueUtil.a().add(request);
        request.setRetryPolicy(new DefaultRetryPolicy(15000, 0, 1.0f));
        confirmActivity.showLoading();
    }

    /* access modifiers changed from: private */
    public static void b(ConfirmActivity confirmActivity, NewPayGoData newPayGoData, String str, String str2, PayU.PaymentMode paymentMode, Params params) {
        try {
            JSONObject jSONObject = new JSONObject(newPayGoData.ext);
            String optString = jSONObject.optString("type");
            String optString2 = jSONObject.optString("mcc");
            String optString3 = jSONObject.optString("payeeVpa");
            String optString4 = jSONObject.optString("payeeName");
            String optString5 = jSONObject.optString("tokenType");
            String optString6 = jSONObject.optString("totalPriceStatus");
            String optString7 = jSONObject.optString("currencyCode");
            if (confirmActivity != null && confirmActivity.getpayParam() != null) {
                String str3 = confirmActivity.getpayParam().get("referenceId");
                GoogleTezPayWrapper googleTezPayWrapper = new GoogleTezPayWrapper();
                googleTezPayWrapper.a(confirmActivity, GoogleTezPayWrapper.a(str3, confirmActivity.getAmount() + "", optString, optString2, optString3, optString4, optString5, optString6, optString7, confirmActivity.getpayParam().get("txnid")));
            }
        } catch (JSONException e) {
            e.printStackTrace();
            if (!LocaleHelper.q()) {
                CrashReport.postCrash(Thread.currentThread(), (Throwable) e);
            }
            d("");
        }
    }

    /* access modifiers changed from: private */
    public static void d(String str) {
        if (TextUtils.isEmpty(str)) {
            MiToast.a((Context) ShopApp.g(), R.string.buy_confirm_order_payfailed, 1);
        } else {
            MiToast.a((Context) ShopApp.g(), (CharSequence) str, 1);
        }
    }

    protected static void a(ConfirmActivity confirmActivity, String str) {
        JSONObject jSONObject;
        if (!TextUtils.isEmpty(str)) {
            try {
                jSONObject = new JSONObject(str);
            } catch (JSONException e) {
                e.printStackTrace();
                if (!LocaleHelper.q()) {
                    CrashReport.postCrash(Thread.currentThread(), (Throwable) e);
                }
                d("");
                jSONObject = null;
            }
            if (jSONObject != null) {
                Iterator<String> keys = jSONObject.keys();
                HashMap hashMap = new HashMap();
                PayU.ak = null;
                while (keys.hasNext()) {
                    String obj = keys.next().toString();
                    try {
                        if (obj.equals("hash")) {
                            PayU.ak = jSONObject.getString("hash");
                        } else if (obj.equals("supportStoreCards")) {
                            PayU.av = Boolean.parseBoolean(jSONObject.getString("supportStoreCards"));
                        } else if (obj.equals("key")) {
                            PayU.f6891a = jSONObject.getString("key");
                        }
                        hashMap.put(obj, jSONObject.getString(obj));
                    } catch (JSONException e2) {
                        e2.printStackTrace();
                    }
                }
                confirmActivity.setpayParam(hashMap);
            }
        }
    }

    public static void a(ConfirmActivity confirmActivity, PayU.PaymentMode paymentMode, Params params) {
        try {
            Intent paymentIntent = confirmActivity.getPaymentIntent();
            Payment payment = new Payment();
            payment.getClass();
            Payment.Builder builder = new Payment.Builder();
            builder.set("mode", String.valueOf(paymentMode));
            for (String str : paymentIntent.getExtras().keySet()) {
                builder.set(str, String.valueOf(paymentIntent.getExtras().get(str)));
                params.put(str, builder.get(str));
            }
            String a2 = PayU.a(confirmActivity).a(builder.create(), params);
            Intent intent = new Intent(confirmActivity, ProcessPaymentActivity.class);
            intent.putExtra("postData", a2);
            intent.addFlags(com.xiaomi.mishopsdk.util.Constants.CALLIGRAPHY_TAG_PRICE);
            confirmActivity.startActivityForResult(intent, 100);
        } catch (MissingParameterException e) {
            e.printStackTrace();
        } catch (HashException e2) {
            e2.printStackTrace();
        }
    }

    public static String a(String str) {
        DecimalFormat decimalFormat;
        if (LocaleHelper.g()) {
            decimalFormat = new DecimalFormat(",###");
        } else if (LocaleHelper.j()) {
            decimalFormat = new DecimalFormat(".###");
        } else {
            decimalFormat = new DecimalFormat(",###");
        }
        try {
            return decimalFormat.format(Double.parseDouble(str));
        } catch (Exception e) {
            LogUtil.b(f6893a, "format " + str + " failed:" + e.toString());
            return "";
        }
    }

    public static String b(String str) {
        try {
            return Utils.c(LoginManager.u().c() + "_card_" + str);
        } catch (Exception unused) {
            return "";
        }
    }
}
