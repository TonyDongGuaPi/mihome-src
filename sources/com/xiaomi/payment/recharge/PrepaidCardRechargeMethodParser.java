package com.xiaomi.payment.recharge;

import com.xiaomi.payment.data.MibiConstants;
import com.xiaomi.payment.recharge.PrepaidCardRechargeMethod;
import java.util.Collections;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class PrepaidCardRechargeMethodParser extends BaseRechargeMethodParser {
    public RechargeMethod a(String str, JSONObject jSONObject) throws JSONException {
        PrepaidCardRechargeMethod prepaidCardRechargeMethod = (PrepaidCardRechargeMethod) super.a(str, jSONObject);
        JSONObject jSONObject2 = jSONObject.getJSONObject("data");
        JSONArray names = jSONObject2.names();
        for (int i = 0; i < names.length(); i++) {
            String string = names.getString(i);
            PrepaidCardRechargeMethod.CarrierInfo carrierInfo = new PrepaidCardRechargeMethod.CarrierInfo();
            JSONObject jSONObject3 = jSONObject2.getJSONObject(names.getString(i));
            carrierInfo.mName = string;
            carrierInfo.mTitle = jSONObject3.getString("title");
            carrierInfo.mOrder = jSONObject3.getInt("order");
            carrierInfo.mIsDefault = jSONObject3.optBoolean(MibiConstants.go, false);
            JSONArray jSONArray = jSONObject3.getJSONArray(MibiConstants.gf);
            for (int i2 = 0; i2 < jSONArray.length(); i2++) {
                carrierInfo.mMoneyValues.add(Long.valueOf(jSONArray.getLong(i2)));
            }
            JSONArray jSONArray2 = jSONObject3.getJSONArray("length");
            for (int i3 = 0; i3 < jSONArray2.length(); i3++) {
                JSONObject jSONObject4 = jSONArray2.getJSONObject(i3);
                PrepaidCardRechargeMethod.CardPwdLen cardPwdLen = new PrepaidCardRechargeMethod.CardPwdLen();
                cardPwdLen.mCardLen = jSONObject4.getInt(MibiConstants.gj);
                cardPwdLen.mPwdLen = jSONObject4.getInt(MibiConstants.gk);
                carrierInfo.mCardPwdLens.add(cardPwdLen);
            }
            prepaidCardRechargeMethod.mCarrierInfos.add(carrierInfo);
        }
        Collections.sort(prepaidCardRechargeMethod.mCarrierInfos);
        return prepaidCardRechargeMethod;
    }

    public RechargeMethod a() {
        return new PrepaidCardRechargeMethod();
    }
}
