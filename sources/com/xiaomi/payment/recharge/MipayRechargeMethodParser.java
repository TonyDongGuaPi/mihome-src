package com.xiaomi.payment.recharge;

import com.xiaomi.payment.data.MibiConstants;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class MipayRechargeMethodParser extends BaseRechargeMethodParser {
    public RechargeMethod a(String str, JSONObject jSONObject) throws JSONException {
        MipayRechargeMethod mipayRechargeMethod = (MipayRechargeMethod) super.a(str, jSONObject);
        JSONObject jSONObject2 = jSONObject.getJSONObject("data");
        mipayRechargeMethod.mMinMoneyValue = jSONObject2.getLong(MibiConstants.gd);
        mipayRechargeMethod.mMaxMoneyValue = jSONObject2.getLong(MibiConstants.gc);
        mipayRechargeMethod.mWarnValue = jSONObject2.optLong(MibiConstants.ge);
        JSONArray jSONArray = jSONObject2.getJSONArray(MibiConstants.gf);
        for (int i = 0; i < jSONArray.length(); i++) {
            mipayRechargeMethod.mMoneyValues.add(Long.valueOf(jSONArray.getLong(i)));
        }
        mipayRechargeMethod.mMinMibiValue = mipayRechargeMethod.mMinMoneyValue;
        mipayRechargeMethod.mMaxMibiValue = mipayRechargeMethod.mMaxMoneyValue;
        mipayRechargeMethod.mWarnMibiValue = mipayRechargeMethod.mWarnValue;
        mipayRechargeMethod.mMibiValues = mipayRechargeMethod.mMoneyValues;
        return mipayRechargeMethod;
    }

    public RechargeMethod a() {
        return new MipayRechargeMethod();
    }
}
