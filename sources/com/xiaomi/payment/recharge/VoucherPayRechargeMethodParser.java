package com.xiaomi.payment.recharge;

import com.xiaomi.payment.data.MibiConstants;
import org.json.JSONException;
import org.json.JSONObject;

public class VoucherPayRechargeMethodParser extends BaseRechargeMethodParser {
    public RechargeMethod a(String str, JSONObject jSONObject) throws JSONException {
        VoucherPayRechargeMethod voucherPayRechargeMethod = (VoucherPayRechargeMethod) super.a(str, jSONObject);
        JSONObject jSONObject2 = jSONObject.getJSONObject("data");
        voucherPayRechargeMethod.mMinLen = jSONObject2.getLong(MibiConstants.gh);
        voucherPayRechargeMethod.mMaxLen = jSONObject2.getLong(MibiConstants.gg);
        return voucherPayRechargeMethod;
    }

    public RechargeMethod a() {
        return new VoucherPayRechargeMethod();
    }
}
