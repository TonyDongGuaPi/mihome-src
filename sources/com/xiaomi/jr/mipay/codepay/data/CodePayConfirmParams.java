package com.xiaomi.jr.mipay.codepay.data;

import com.google.gson.annotations.SerializedName;
import java.io.Serializable;
import java.util.ArrayList;

public class CodePayConfirmParams implements Serializable {
    @SerializedName("authCode")
    public String mAuthCode;
    @SerializedName("payTypeIndex")
    public int mCurPayTypeIndex;
    @SerializedName("payTypeList")
    public ArrayList<PayType> mSupportPayTypeList;
    @SerializedName("amount")
    public long mTradeAmount;
    @SerializedName("tradeId")
    public String mTradeId;
    @SerializedName("tradeTitleInfo")
    public String mTradeSummary;
    @SerializedName("validateType")
    public int mValidateType;

    public PayType getCurPayType() {
        if (this.mSupportPayTypeList == null || this.mCurPayTypeIndex < 0 || this.mCurPayTypeIndex >= this.mSupportPayTypeList.size()) {
            return null;
        }
        return this.mSupportPayTypeList.get(this.mCurPayTypeIndex);
    }
}
