package com.xiaomi.payment.recharge;

import com.xiaomi.payment.data.MibiConstants;
import com.xiaomi.payment.recharge.MsgPayRechargeMethod;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public abstract class MsgPayRechargeMethodParser extends BaseRechargeMethodParser {
    public RechargeMethod a(String str, JSONObject jSONObject) throws JSONException {
        MsgPayRechargeMethod msgPayRechargeMethod = (MsgPayRechargeMethod) super.a(str, jSONObject);
        JSONObject jSONObject2 = jSONObject.getJSONObject("data");
        msgPayRechargeMethod.mMibi = jSONObject2.getInt(MibiConstants.gl);
        msgPayRechargeMethod.mMoney = jSONObject2.getInt("money");
        JSONArray jSONArray = jSONObject2.getJSONArray(MibiConstants.gf);
        for (int i = 0; i < jSONArray.length(); i++) {
            JSONObject jSONObject3 = jSONArray.getJSONObject(i);
            MsgPayRechargeMethod.MessagePayDominationInfo messagePayDominationInfo = new MsgPayRechargeMethod.MessagePayDominationInfo();
            messagePayDominationInfo.mPayId = jSONObject3.getString(MibiConstants.eZ);
            messagePayDominationInfo.mMibiAmount = jSONObject3.getLong(MibiConstants.fa);
            messagePayDominationInfo.mChargeFee = jSONObject3.getLong(MibiConstants.dq);
            msgPayRechargeMethod.mPayIdMap.put(Long.valueOf(messagePayDominationInfo.mMibiAmount), messagePayDominationInfo.mPayId);
            msgPayRechargeMethod.mDenominationMibiList.add(Long.valueOf(messagePayDominationInfo.mMibiAmount));
            msgPayRechargeMethod.mInfos.add(messagePayDominationInfo);
        }
        return msgPayRechargeMethod;
    }
}
