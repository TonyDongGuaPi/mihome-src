package com.mipay.ucashier.data;

import java.io.Serializable;
import org.json.JSONException;
import org.json.JSONObject;

public class PayType implements Serializable {
    public static final String PAY_NAME_ALIPAY = "ALIPAY";
    public static final String PAY_NAME_MIPAY = "MIPAY";
    public static final String PAY_NAME_TENPAY = "TENPAY";
    public String mPayIconUrl;
    public String mPayName;
    public String mPayTip;
    public String mPayTitle;
    public int mPayType;

    public static PayType fromJson(JSONObject jSONObject) throws JSONException {
        PayType payType = new PayType();
        payType.mPayType = jSONObject.getInt("payType");
        payType.mPayName = jSONObject.getString(UCashierConstants.KEY_PAY_NAME);
        payType.mPayTitle = jSONObject.getString(UCashierConstants.KEY_PAY_TITLE);
        payType.mPayIconUrl = jSONObject.getString(UCashierConstants.KEY_PAY_ICON_URL);
        payType.mPayTip = jSONObject.optString(UCashierConstants.KEY_PAY_TIP);
        return payType;
    }
}
