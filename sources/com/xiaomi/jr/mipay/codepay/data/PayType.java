package com.xiaomi.jr.mipay.codepay.data;

import com.google.gson.annotations.SerializedName;
import java.io.Serializable;
import java.util.ArrayList;

public class PayType implements Serializable {
    public static final int CARD_TYPE_CREDIT = 2;
    public static final int CARD_TYPE_DEBIT = 1;
    public static final String PAY_TYPE_BANK_CARD = "BANKCARD";
    public static final String PAY_TYPE_BANLANCE = "BANLANCE";
    public static final String PAY_TYPE_BIND_CARD = "BINDCARD";
    public static final String PAY_TYPE_INSTALLMENT = "MIJINTERM";
    private static final long serialVersionUID = 1;
    @SerializedName("agreement")
    public ArrayList<Agreement> mAgreements;
    @SerializedName("authCode")
    public String mAuthCode;
    @SerializedName("isAvailable")
    public boolean mAvailable = true;
    @SerializedName("balance")
    public long mBalance;
    @SerializedName("bankName")
    public String mBankName;
    @SerializedName("briefSummary")
    public String mBriefSummary;
    @SerializedName("cardType")
    public int mCardType;
    @SerializedName("imageUrl")
    public String mIconUrl;
    @SerializedName("payType")
    public String mPayType;
    @SerializedName("payTypeId")
    public int mPayTypeId;
    @SerializedName("subSummary")
    public String mSubSummary;
    @SerializedName("summary")
    public String mSummary;
    @SerializedName("tailNo")
    public String mTailNum;
    @SerializedName("tips")
    public String mTips;
}
