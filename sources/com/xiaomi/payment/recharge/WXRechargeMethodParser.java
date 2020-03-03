package com.xiaomi.payment.recharge;

import com.xiaomi.payment.data.MibiConstants;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class WXRechargeMethodParser extends BaseRechargeMethodParser {
    public RechargeMethod a(String str, JSONObject jSONObject) throws JSONException {
        WXRechargeMethod wXRechargeMethod = (WXRechargeMethod) super.a(str, jSONObject);
        JSONObject jSONObject2 = jSONObject.getJSONObject("data");
        wXRechargeMethod.mMinMoneyValue = jSONObject2.getLong(MibiConstants.gd);
        wXRechargeMethod.mMaxMoneyValue = jSONObject2.getLong(MibiConstants.gc);
        wXRechargeMethod.mWarnValue = jSONObject2.optLong(MibiConstants.ge);
        JSONArray jSONArray = jSONObject2.getJSONArray(MibiConstants.gf);
        for (int i = 0; i < jSONArray.length(); i++) {
            wXRechargeMethod.mMoneyValues.add(Long.valueOf(jSONArray.getLong(i)));
        }
        wXRechargeMethod.mMinMibiValue = wXRechargeMethod.mMinMoneyValue;
        wXRechargeMethod.mMaxMibiValue = wXRechargeMethod.mMaxMoneyValue;
        wXRechargeMethod.mWarnMibiValue = wXRechargeMethod.mWarnValue;
        wXRechargeMethod.mMibiValues = wXRechargeMethod.mMoneyValues;
        return wXRechargeMethod;
    }

    public RechargeMethod a() {
        return new WXRechargeMethod();
    }
}
