package com.xiaomi.payment.recharge;

import com.xiaomi.payment.data.MibiConstants;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class AlipayRechargeMethodParser extends BaseRechargeMethodParser {
    public RechargeMethod a(String str, JSONObject jSONObject) throws JSONException {
        AlipayRechargeMethod alipayRechargeMethod = (AlipayRechargeMethod) super.a(str, jSONObject);
        JSONObject jSONObject2 = jSONObject.getJSONObject("data");
        alipayRechargeMethod.mMinMoneyValue = jSONObject2.getLong(MibiConstants.gd);
        alipayRechargeMethod.mMaxMoneyValue = jSONObject2.getLong(MibiConstants.gc);
        alipayRechargeMethod.mWarnValue = jSONObject2.optLong(MibiConstants.ge);
        JSONArray jSONArray = jSONObject2.getJSONArray(MibiConstants.gf);
        for (int i = 0; i < jSONArray.length(); i++) {
            alipayRechargeMethod.mMoneyValues.add(Long.valueOf(jSONArray.getLong(i)));
        }
        alipayRechargeMethod.mMinMibiValue = alipayRechargeMethod.mMinMoneyValue;
        alipayRechargeMethod.mMaxMibiValue = alipayRechargeMethod.mMaxMoneyValue;
        alipayRechargeMethod.mWarnMibiValue = alipayRechargeMethod.mWarnValue;
        alipayRechargeMethod.mMibiValues = alipayRechargeMethod.mMoneyValues;
        return alipayRechargeMethod;
    }

    public RechargeMethod a() {
        return new AlipayRechargeMethod();
    }
}
