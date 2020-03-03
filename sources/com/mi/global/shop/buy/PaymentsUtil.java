package com.mi.global.shop.buy;

import android.support.annotation.Nullable;
import android.text.TextUtils;
import com.alipay.sdk.cons.c;
import com.unionpay.tsmservice.data.Constant;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

final class PaymentsUtil {
    PaymentsUtil() {
    }

    static JSONObject a(JSONObject jSONObject, List<JSONObject> list) throws JSONException {
        JSONObject jSONObject2 = new JSONObject();
        jSONObject2.put(c.m, 2);
        jSONObject2.put("apiVersionMinor", 0);
        jSONObject2.put("transactionInfo", jSONObject);
        jSONObject2.put("allowedPaymentMethods", new JSONArray(list));
        return jSONObject2;
    }

    static JSONObject a(String str, @Nullable String str2) throws JSONException {
        JSONObject jSONObject = new JSONObject();
        jSONObject.put("currencyCode", Constant.KEY_CURRENCYTYPE_INR);
        jSONObject.put("totalPriceStatus", "FINAL");
        jSONObject.put("totalPrice", str);
        if (!TextUtils.isEmpty(str2)) {
            jSONObject.put("transactionNote", str2);
        }
        return jSONObject;
    }

    static JSONObject a(String str, String str2, String str3, String str4, @Nullable String str5, @Nullable String str6) throws JSONException {
        JSONObject jSONObject = new JSONObject();
        jSONObject.put("payeeVpa", str);
        jSONObject.put("payeeName", str2);
        jSONObject.put("transactionReferenceId", str3);
        jSONObject.put("mcc", str4);
        if (!TextUtils.isEmpty(str5)) {
            jSONObject.put("referenceUrl", str5);
        }
        if (!TextUtils.isEmpty(str6)) {
            jSONObject.put("transactionId", str6);
        }
        return a("UPI", jSONObject, a());
    }

    static JSONObject a(String str, JSONObject jSONObject, JSONObject jSONObject2) throws JSONException {
        JSONObject jSONObject3 = new JSONObject();
        jSONObject3.put("type", str);
        jSONObject3.put("parameters", jSONObject);
        jSONObject3.put("tokenizationSpecification", jSONObject2);
        return jSONObject3;
    }

    private static JSONObject a() throws JSONException {
        JSONObject jSONObject = new JSONObject();
        jSONObject.put("type", "DIRECT");
        return jSONObject;
    }
}
