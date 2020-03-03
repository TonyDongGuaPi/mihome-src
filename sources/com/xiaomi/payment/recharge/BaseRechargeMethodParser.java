package com.xiaomi.payment.recharge;

import android.util.Log;
import com.mibi.common.exception.PaymentException;
import com.xiaomi.payment.data.EntryData;
import com.xiaomi.payment.data.MibiConstants;
import org.json.JSONException;
import org.json.JSONObject;

public abstract class BaseRechargeMethodParser implements RechargeMethodParser {

    /* renamed from: a  reason: collision with root package name */
    private static final String f12405a = "BaseRechargeMhdParser";

    public abstract RechargeMethod a();

    public RechargeMethod a(String str, JSONObject jSONObject) throws JSONException {
        RechargeMethod a2 = a();
        a2.mChannel = jSONObject.getString("channel");
        a2.mTitle = jSONObject.getString("title");
        a2.mContentHint = jSONObject.optString(MibiConstants.fY);
        a2.mDiscountDesc = jSONObject.optString(MibiConstants.fZ);
        a2.mDiscountRate = jSONObject.optDouble("discount", 1.0d);
        JSONObject optJSONObject = jSONObject.optJSONObject("entry");
        if (optJSONObject != null) {
            a2.mContentHintEntryData = new EntryData();
            try {
                a2.mContentHintEntryData.parseEntryData(optJSONObject);
            } catch (PaymentException e) {
                Log.e(f12405a, "BaseRechargeMethodParser PaymentException ", e);
            }
        }
        return a2;
    }
}
