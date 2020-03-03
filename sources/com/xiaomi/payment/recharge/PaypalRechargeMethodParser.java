package com.xiaomi.payment.recharge;

import com.xiaomi.payment.data.MibiConstants;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class PaypalRechargeMethodParser extends BaseRechargeMethodParser {
    public RechargeMethod a(String str, JSONObject jSONObject) throws JSONException {
        PaypalRechargeMethod paypalRechargeMethod = (PaypalRechargeMethod) super.a(str, jSONObject);
        JSONObject jSONObject2 = jSONObject.getJSONObject("data");
        paypalRechargeMethod.mMinMoneyValue = jSONObject2.getLong(MibiConstants.gd);
        paypalRechargeMethod.mMaxMoneyValue = jSONObject2.getLong(MibiConstants.gc);
        paypalRechargeMethod.mWarnValue = jSONObject2.optLong(MibiConstants.ge);
        JSONArray jSONArray = jSONObject2.getJSONArray(MibiConstants.gf);
        for (int i = 0; i < jSONArray.length(); i++) {
            paypalRechargeMethod.mMoneyValues.add(Long.valueOf(jSONArray.getLong(i)));
        }
        paypalRechargeMethod.mMinMibiValue = paypalRechargeMethod.mMinMoneyValue;
        paypalRechargeMethod.mMaxMibiValue = paypalRechargeMethod.mMaxMoneyValue;
        paypalRechargeMethod.mWarnMibiValue = paypalRechargeMethod.mWarnValue;
        paypalRechargeMethod.mMibiValues = paypalRechargeMethod.mMoneyValues;
        return paypalRechargeMethod;
    }

    public RechargeMethod a() {
        return new PaypalRechargeMethod();
    }
}
