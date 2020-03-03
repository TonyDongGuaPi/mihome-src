package com.miui.tsmclient.entity;

import android.content.Context;
import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import android.text.SpannableStringBuilder;
import android.text.TextUtils;
import android.text.style.StrikethroughSpan;
import android.text.style.TextAppearanceSpan;
import com.miui.tsmclient.util.ResUtils;
import com.miui.tsmclientsdk.MiTsmConstants;
import com.taobao.weex.el.parse.Operators;
import com.tsmclient.smartcard.ByteArray;

public class InAppTransData implements Parcelable {
    public static final Parcelable.Creator<InAppTransData> CREATOR = new Parcelable.Creator<InAppTransData>() {
        public InAppTransData createFromParcel(Parcel parcel) {
            return new InAppTransData(parcel);
        }

        public InAppTransData[] newArray(int i) {
            return new InAppTransData[i];
        }
    };
    public static final String PAY_SUPPORT_CARD_ALL = "00";
    public static final String PAY_SUPPORT_CARD_CREDIT = "02";
    public static final String PAY_SUPPORT_CARD_DEBIT = "01";
    public static final int VERIFY_METHOD_FINGER = 1;
    public static final int VERIFY_METHOD_PASSWORD = 2;
    private int mChannel;
    private String mCurrencyCode;
    private long mDiscountAmount;
    private String mEncryptPayAmount;
    private String mIccData;
    private String mMerchantId;
    private String mMerchantName;
    private int mMiTsmSignKeyIndex;
    private String mMiTsmSignedData;
    private long mOrderAmount;
    private String mOrderNumber;
    private long mPayAmount;
    private String mPayCardAppletAid;
    private String mPayCardPan;
    private String mPayCardVcReferenceId;
    private int mSignKeyIndex;
    private String mSignedData;
    private String mSupCardAttr;
    private int mVerifyMethod;

    public int describeContents() {
        return 0;
    }

    public InAppTransData() {
    }

    protected InAppTransData(Parcel parcel) {
        this.mOrderNumber = parcel.readString();
        this.mMerchantName = parcel.readString();
        this.mMerchantId = parcel.readString();
        this.mEncryptPayAmount = parcel.readString();
        this.mSignedData = parcel.readString();
        this.mCurrencyCode = parcel.readString();
        this.mSupCardAttr = parcel.readString();
        this.mOrderAmount = parcel.readLong();
        this.mDiscountAmount = parcel.readLong();
        this.mPayAmount = parcel.readLong();
        this.mSignKeyIndex = parcel.readInt();
        this.mChannel = parcel.readInt();
        this.mPayCardAppletAid = parcel.readString();
        this.mPayCardPan = parcel.readString();
        this.mPayCardVcReferenceId = parcel.readString();
        this.mIccData = parcel.readString();
        this.mMiTsmSignedData = parcel.readString();
        this.mVerifyMethod = parcel.readInt();
        this.mMiTsmSignKeyIndex = parcel.readInt();
    }

    public void setVerifyMethod(int i) {
        this.mVerifyMethod = i;
    }

    public String getMerchantName() {
        return this.mMerchantName == null ? "" : this.mMerchantName;
    }

    public String getOrderNumber() {
        return this.mOrderNumber == null ? "" : this.mOrderNumber;
    }

    public String getMerchantId() {
        return this.mMerchantId == null ? "" : this.mMerchantId;
    }

    public String getEncryptPayAmount() {
        return this.mEncryptPayAmount == null ? "" : this.mEncryptPayAmount;
    }

    public String getSignedData() {
        return this.mSignedData == null ? "" : this.mSignedData;
    }

    public String getCurrencyCode() {
        return this.mCurrencyCode == null ? "" : this.mCurrencyCode;
    }

    public String getPan() {
        return this.mPayCardPan == null ? "" : this.mPayCardPan;
    }

    public String getPayCardAppletAid() {
        return this.mPayCardAppletAid;
    }

    public void setMiTsmSignKeyIndex(int i) {
        this.mMiTsmSignKeyIndex = i;
    }

    public void setMiTsmSignedData(String str) {
        this.mMiTsmSignedData = str;
    }

    public long getOrderAmount() {
        return this.mOrderAmount;
    }

    public long getDiscountAmount() {
        return this.mDiscountAmount;
    }

    public long getPayAmount() {
        return this.mPayAmount;
    }

    public int getSignKeyIndex() {
        return this.mSignKeyIndex;
    }

    public int getChannel() {
        return this.mChannel;
    }

    public int getVerifyMethod() {
        return this.mVerifyMethod;
    }

    public void fillTransResponseData(ByteArray byteArray) {
        if (byteArray != null) {
            this.mIccData = byteArray.toString();
        }
    }

    public boolean isOrderSupPayCard(BankCardInfo bankCardInfo) {
        if (bankCardInfo == null) {
            return false;
        }
        String supCardAttr = getSupCardAttr();
        if (2 == bankCardInfo.mBankCardType) {
            if ("00".equals(supCardAttr) || "02".equals(supCardAttr)) {
                return true;
            }
            return false;
        } else if ("00".equals(supCardAttr) || "01".equals(supCardAttr)) {
            return true;
        } else {
            return false;
        }
    }

    public String getSupCardAttr() {
        return TextUtils.isEmpty(this.mSupCardAttr) ? "00" : this.mSupCardAttr;
    }

    public SpannableStringBuilder buildPayAmountStr(Context context) {
        String string = context.getString(ResUtils.getStringIdentifier(context, MiTsmConstants.KEY_INAPP_PAY_AMOUNT), new Object[]{Float.valueOf(((float) this.mPayAmount) / 100.0f)});
        SpannableStringBuilder spannableStringBuilder = new SpannableStringBuilder(string);
        spannableStringBuilder.setSpan(new TextAppearanceSpan(context, ResUtils.getStyleIdentifier(context, "TextAppearance.InApp.Denom")), 0, string.length() - 1, 17);
        return spannableStringBuilder;
    }

    public SpannableStringBuilder buildOrderAmountStr(Context context) {
        String string = context.getString(ResUtils.getStringIdentifier(context, MiTsmConstants.KEY_INAPP_ORDER_AMOUNT), new Object[]{Float.valueOf(((float) this.mOrderAmount) / 100.0f)});
        SpannableStringBuilder spannableStringBuilder = new SpannableStringBuilder(string);
        spannableStringBuilder.setSpan(new StrikethroughSpan(), 0, string.length(), 17);
        return spannableStringBuilder;
    }

    public Bundle toBundle() {
        Bundle bundle = new Bundle();
        bundle.putString(MiTsmConstants.KEY_INAPP_ORDER_NUMBER, this.mOrderNumber);
        bundle.putString(MiTsmConstants.KEY_INAPP_MERCHANT_NAME, this.mMerchantName);
        bundle.putString(MiTsmConstants.KEY_INAPP_MERCHANT_ID, this.mMerchantId);
        bundle.putString(MiTsmConstants.KEY_INAPP_SIGNED_DATA, this.mMiTsmSignedData);
        bundle.putString(MiTsmConstants.KEY_INAPP_CURRENCY_CODE, this.mCurrencyCode);
        bundle.putString(MiTsmConstants.KEY_INAPP_VC_REFERENCE_ID, this.mPayCardVcReferenceId);
        bundle.putLong(MiTsmConstants.KEY_INAPP_ORDER_AMOUNT, this.mOrderAmount);
        bundle.putLong(MiTsmConstants.KEY_INAPP_DISCOUNT_AMOUNT, this.mDiscountAmount);
        bundle.putLong(MiTsmConstants.KEY_INAPP_PAY_AMOUNT, this.mPayAmount);
        bundle.putString(MiTsmConstants.KEY_INAPP_ENCRYPT_PAY_AMOUNT, this.mEncryptPayAmount);
        bundle.putInt(MiTsmConstants.KEY_INAPP_SIGN_KEY_INDEX, this.mMiTsmSignKeyIndex);
        bundle.putString(MiTsmConstants.KEY_INAPP_PAN, this.mPayCardPan);
        bundle.putString(MiTsmConstants.KEY_INAPP_ICC_DATA, this.mIccData);
        bundle.putInt(MiTsmConstants.KEY_INAPP_VERIFY_METHOD, this.mVerifyMethod);
        bundle.putString(MiTsmConstants.KEY_INAPP_ORDER_SUP_CARD_ATTR, this.mSupCardAttr);
        return bundle;
    }

    public static InAppTransData newInstance(Bundle bundle) {
        InAppTransData inAppTransData = new InAppTransData();
        if (bundle != null) {
            inAppTransData.mOrderNumber = bundle.getString(MiTsmConstants.KEY_INAPP_ORDER_NUMBER, "");
            inAppTransData.mMerchantName = bundle.getString(MiTsmConstants.KEY_INAPP_MERCHANT_NAME, "");
            inAppTransData.mMerchantId = bundle.getString(MiTsmConstants.KEY_INAPP_MERCHANT_ID, "");
            inAppTransData.mOrderAmount = bundle.getLong(MiTsmConstants.KEY_INAPP_ORDER_AMOUNT, 0);
            inAppTransData.mDiscountAmount = bundle.getLong(MiTsmConstants.KEY_INAPP_DISCOUNT_AMOUNT, 0);
            inAppTransData.mPayAmount = bundle.getLong(MiTsmConstants.KEY_INAPP_PAY_AMOUNT, 0);
            inAppTransData.mSignedData = bundle.getString(MiTsmConstants.KEY_INAPP_SIGNED_DATA, "");
            inAppTransData.mSignKeyIndex = bundle.getInt(MiTsmConstants.KEY_INAPP_SIGN_KEY_INDEX, 0);
            inAppTransData.mCurrencyCode = bundle.getString(MiTsmConstants.KEY_INAPP_CURRENCY_CODE, "");
            inAppTransData.mEncryptPayAmount = bundle.getString(MiTsmConstants.KEY_INAPP_ENCRYPT_PAY_AMOUNT, "");
            inAppTransData.mChannel = bundle.getInt(MiTsmConstants.KEY_INAPP_CHANNEL, -1);
            inAppTransData.mSupCardAttr = bundle.getString(MiTsmConstants.KEY_INAPP_ORDER_SUP_CARD_ATTR, "00");
        }
        return inAppTransData;
    }

    public void fillPayCardInfo(BankCardInfo bankCardInfo) {
        if (bankCardInfo != null) {
            this.mPayCardPan = bankCardInfo.mVCardNumber;
            this.mPayCardVcReferenceId = bankCardInfo.mVCReferenceId;
            this.mPayCardAppletAid = bankCardInfo.mAid;
        }
    }

    public String toString() {
        return "InAppTransData{mOrderNumber='" + this.mOrderNumber + Operators.SINGLE_QUOTE + ", mMerchantName='" + this.mMerchantName + Operators.SINGLE_QUOTE + ", mMerchantId='" + this.mMerchantId + Operators.SINGLE_QUOTE + ", mEncryptPayAmount='" + this.mEncryptPayAmount + Operators.SINGLE_QUOTE + ", mSignedData='" + this.mSignedData + Operators.SINGLE_QUOTE + ", mCurrencyCode='" + this.mCurrencyCode + Operators.SINGLE_QUOTE + ", mSupCardAttr='" + this.mSupCardAttr + Operators.SINGLE_QUOTE + ", mOrderAmount=" + this.mOrderAmount + ", mDiscountAmount=" + this.mDiscountAmount + ", mPayAmount=" + this.mPayAmount + ", mSignKeyIndex=" + this.mSignKeyIndex + ", mChannel=" + this.mChannel + ", mPayCardAppletAid='" + this.mPayCardAppletAid + Operators.SINGLE_QUOTE + ", mPayCardPan='" + this.mPayCardPan + Operators.SINGLE_QUOTE + ", mPayCardVcReferenceId='" + this.mPayCardVcReferenceId + Operators.SINGLE_QUOTE + ", mIccData='" + this.mIccData + Operators.SINGLE_QUOTE + ", mMiTsmSignedData='" + this.mMiTsmSignedData + Operators.SINGLE_QUOTE + ", mVerifyMethod=" + this.mVerifyMethod + ", mMiTsmSignKeyIndex=" + this.mMiTsmSignKeyIndex + Operators.BLOCK_END;
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.mOrderNumber);
        parcel.writeString(this.mMerchantName);
        parcel.writeString(this.mMerchantId);
        parcel.writeString(this.mEncryptPayAmount);
        parcel.writeString(this.mSignedData);
        parcel.writeString(this.mCurrencyCode);
        parcel.writeString(this.mSupCardAttr);
        parcel.writeLong(this.mOrderAmount);
        parcel.writeLong(this.mDiscountAmount);
        parcel.writeLong(this.mPayAmount);
        parcel.writeInt(this.mSignKeyIndex);
        parcel.writeInt(this.mChannel);
        parcel.writeString(this.mPayCardAppletAid);
        parcel.writeString(this.mPayCardPan);
        parcel.writeString(this.mPayCardVcReferenceId);
        parcel.writeString(this.mIccData);
        parcel.writeString(this.mMiTsmSignedData);
        parcel.writeInt(this.mVerifyMethod);
        parcel.writeInt(this.mMiTsmSignKeyIndex);
    }
}
