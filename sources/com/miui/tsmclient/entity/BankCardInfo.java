package com.miui.tsmclient.entity;

import android.os.Parcel;
import android.os.Parcelable;
import com.miui.tsmclient.util.LogUtils;
import org.json.JSONException;
import org.json.JSONObject;

public class BankCardInfo extends CardInfo {
    public static final String CARD_ART = "card_art";
    public static final String CARD_INFO_FIELD_BANK_CARD_FRONT_COLOR = "bank_card_front_color";
    public static final String CARD_INFO_FIELD_BANK_CARD_ISSUER_CHANNEL = "bank_card_issuer_channel";
    public static final String CARD_INFO_FIELD_BANK_CARD_ISSUER_ID = "bank_card_issuer_id";
    public static final String CARD_INFO_FIELD_BANK_CARD_PAN = "bank_card_pan";
    public static final String CARD_INFO_FIELD_BANK_CARD_PAN_LAST_DIGITS = "bank_card_pan_last_digits";
    public static final String CARD_INFO_FIELD_BANK_CARD_PRODUCT_NAME = "bank_card_product_name";
    public static final String CARD_INFO_FIELD_BANK_CARD_PRODUCT_TYPE_ID = "bank_card_product_id";
    public static final String CARD_INFO_FIELD_BANK_CARD_TYPE = "bank_card_type";
    public static final String CARD_INFO_FIELD_BANK_CARD_VC_NUM = "bank_card_vc_card_num";
    public static final String CARD_INFO_FIELD_BANK_CARD_VC_REFERENCE_ID = "bank_card_vc_reference_id";
    public static final String CARD_INFO_FIELD_BANK_CONTACT_NUM = "bank_contact_num";
    public static final String CARD_INFO_FIELD_BANK_LOGO_URL = "bank_logo_url";
    public static final String CARD_INFO_FIELD_BANK_LOGO_WITH_NAME_URL = "bank_logo_with_name_url";
    public static final String CARD_INFO_FIELD_BANK_NAME = "bank_name";
    public static final String CARD_USER_TERMS = "card_user_terms";
    public static final String CARD_VC_STATUS = "card_vc_status";
    public static final Parcelable.Creator<BankCardInfo> CREATOR = new Parcelable.Creator<BankCardInfo>() {
        public BankCardInfo createFromParcel(Parcel parcel) {
            BankCardInfo bankCardInfo = new BankCardInfo();
            bankCardInfo.readFromParcel(parcel);
            return bankCardInfo;
        }

        public BankCardInfo[] newArray(int i) {
            return new BankCardInfo[i];
        }
    };
    public static final int CREDIT = 2;
    public static final int DEBIT = 1;
    public static final int ISSUER_CHANNEL_CMB = 2;
    public static final int ISSUER_CHANNEL_UP = 1;
    public static final int VC_STATUS_ACTIVATED = 0;
    public static final int VC_STATUS_DELETE = 3;
    public static final int VC_STATUS_LOCK = 2;
    public static final int VC_STATUS_WAIT_ACTIVATE = 1;
    public String mBankCardPan;
    public int mBankCardType;
    public String mBankContactNum;
    public String mBankLogoUrl;
    public String mBankLogoWithNameUrl;
    public String mBankName;
    public String mCardArt;
    public String mCardFrontColor;
    public String mCardProductName;
    public String mCardProductTypeId;
    public int mIssuerChannel;
    public String mIssuerId;
    public String mPanLastDigits;
    public String mPhoneLastDigits;
    public String mUserTerms;
    public String mVCReferenceId;
    public int mVCStatus;
    public String mVCardNumber;

    public boolean isBankCard() {
        return true;
    }

    public BankCardInfo() {
        super("BANKCARD");
        this.mCardName = "Mi Pay";
        this.mIsSecure = true;
        this.mCardGroupId = CardGroupType.BANKCARD.getId();
    }

    public JSONObject serialize() {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("title", this.mCardName);
            jSONObject.put(CardInfo.KEY_CARD_NO, this.mCardNo);
            jSONObject.put("aid", this.mAid);
            jSONObject.put(CARD_INFO_FIELD_BANK_CARD_PAN, this.mBankCardPan);
            jSONObject.put("bank_card_type", this.mBankCardType);
            jSONObject.put(CARD_INFO_FIELD_BANK_CARD_VC_REFERENCE_ID, this.mVCReferenceId);
            jSONObject.put(CARD_INFO_FIELD_BANK_CARD_VC_NUM, this.mVCardNumber);
            jSONObject.put(CARD_INFO_FIELD_BANK_CARD_PRODUCT_TYPE_ID, this.mCardProductTypeId);
            jSONObject.put(CARD_INFO_FIELD_BANK_CARD_ISSUER_ID, this.mIssuerId);
            jSONObject.put(CARD_INFO_FIELD_BANK_CARD_PRODUCT_NAME, this.mCardProductName);
            jSONObject.put("card_user_terms", this.mUserTerms);
            jSONObject.put("card_art", this.mCardArt);
            jSONObject.put("card_vc_status", this.mVCStatus);
            jSONObject.put(CARD_INFO_FIELD_BANK_CARD_ISSUER_CHANNEL, this.mIssuerChannel);
            jSONObject.put(CARD_INFO_FIELD_BANK_CARD_PAN_LAST_DIGITS, this.mPanLastDigits);
            jSONObject.put("bank_name", this.mBankName);
            jSONObject.put(CARD_INFO_FIELD_BANK_LOGO_URL, this.mBankLogoUrl);
            jSONObject.put(CARD_INFO_FIELD_BANK_LOGO_WITH_NAME_URL, this.mBankLogoWithNameUrl);
            jSONObject.put(CARD_INFO_FIELD_BANK_CARD_FRONT_COLOR, this.mCardFrontColor);
            jSONObject.put(CARD_INFO_FIELD_BANK_CONTACT_NUM, this.mBankContactNum);
        } catch (JSONException e) {
            LogUtils.e("serialize bankcard info to json object failed!", e);
        }
        return jSONObject;
    }

    public CardInfo parse(JSONObject jSONObject) {
        if (jSONObject != null) {
            this.mCardName = jSONObject.optString("title");
            if (jSONObject.has(CardInfo.KEY_CARD_NO)) {
                this.mCardNo = jSONObject.optString(CardInfo.KEY_CARD_NO);
            }
            this.mAid = jSONObject.optString("aid");
            this.mBankCardPan = jSONObject.optString(CARD_INFO_FIELD_BANK_CARD_PAN);
            this.mBankCardType = jSONObject.optInt("bank_card_type");
            this.mVCReferenceId = jSONObject.optString(CARD_INFO_FIELD_BANK_CARD_VC_REFERENCE_ID);
            this.mVCardNumber = jSONObject.optString(CARD_INFO_FIELD_BANK_CARD_VC_NUM);
            this.mCardProductTypeId = jSONObject.optString(CARD_INFO_FIELD_BANK_CARD_PRODUCT_TYPE_ID);
            this.mIssuerId = jSONObject.optString(CARD_INFO_FIELD_BANK_CARD_ISSUER_ID);
            this.mCardProductName = jSONObject.optString(CARD_INFO_FIELD_BANK_CARD_PRODUCT_NAME);
            this.mUserTerms = jSONObject.optString("card_user_terms");
            this.mCardArt = jSONObject.optString("card_art");
            this.mVCStatus = jSONObject.optInt("card_vc_status");
            this.mIssuerChannel = jSONObject.optInt(CARD_INFO_FIELD_BANK_CARD_ISSUER_CHANNEL);
            this.mPanLastDigits = jSONObject.optString(CARD_INFO_FIELD_BANK_CARD_PAN_LAST_DIGITS);
            this.mBankName = jSONObject.optString("bank_name");
            this.mBankLogoUrl = jSONObject.optString(CARD_INFO_FIELD_BANK_LOGO_URL);
            this.mBankLogoWithNameUrl = jSONObject.optString(CARD_INFO_FIELD_BANK_LOGO_WITH_NAME_URL);
            this.mCardFrontColor = jSONObject.optString(CARD_INFO_FIELD_BANK_CARD_FRONT_COLOR);
            this.mBankContactNum = jSONObject.optString(CARD_INFO_FIELD_BANK_CONTACT_NUM);
        }
        return this;
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.mCardName);
        parcel.writeString(this.mCardNo);
        parcel.writeString(this.mAid);
        parcel.writeString(this.mBankCardPan);
        parcel.writeInt(this.mBankCardType);
        parcel.writeString(this.mVCReferenceId);
        parcel.writeString(this.mVCardNumber);
        parcel.writeString(this.mCardProductTypeId);
        parcel.writeString(this.mIssuerId);
        parcel.writeString(this.mCardProductName);
        parcel.writeString(this.mUserTerms);
        parcel.writeString(this.mCardArt);
        parcel.writeInt(this.mVCStatus);
        parcel.writeString(this.mPhoneLastDigits);
        parcel.writeInt(this.mIssuerChannel);
        parcel.writeString(this.mPanLastDigits);
        parcel.writeString(this.mBankName);
        parcel.writeString(this.mBankLogoUrl);
        parcel.writeString(this.mBankLogoWithNameUrl);
        parcel.writeString(this.mCardFrontColor);
        parcel.writeString(this.mBankContactNum);
    }

    public void readFromParcel(Parcel parcel) {
        this.mCardName = parcel.readString();
        this.mCardNo = parcel.readString();
        this.mAid = parcel.readString();
        this.mBankCardPan = parcel.readString();
        this.mBankCardType = parcel.readInt();
        this.mVCReferenceId = parcel.readString();
        this.mVCardNumber = parcel.readString();
        this.mCardProductTypeId = parcel.readString();
        this.mIssuerId = parcel.readString();
        this.mCardProductName = parcel.readString();
        this.mUserTerms = parcel.readString();
        this.mCardArt = parcel.readString();
        this.mVCStatus = parcel.readInt();
        this.mPhoneLastDigits = parcel.readString();
        this.mIssuerChannel = parcel.readInt();
        this.mPanLastDigits = parcel.readString();
        this.mBankName = parcel.readString();
        this.mBankLogoUrl = parcel.readString();
        this.mBankLogoWithNameUrl = parcel.readString();
        this.mCardFrontColor = parcel.readString();
        this.mBankContactNum = parcel.readString();
    }
}
