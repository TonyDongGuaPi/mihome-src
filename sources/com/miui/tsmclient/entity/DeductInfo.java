package com.miui.tsmclient.entity;

import android.os.Parcel;
import android.os.Parcelable;
import android.text.TextUtils;
import com.google.gson.annotations.SerializedName;

public class DeductInfo implements Parcelable {
    public static final Parcelable.Creator<DeductInfo> CREATOR = new Parcelable.Creator<DeductInfo>() {
        public DeductInfo createFromParcel(Parcel parcel) {
            return new DeductInfo(parcel);
        }

        public DeductInfo[] newArray(int i) {
            return new DeductInfo[0];
        }
    };
    private static final int FLAG_RESP_CODE = 200;
    @SerializedName("valid")
    private boolean mAutoRechargeValid;
    @SerializedName("threshold")
    private int mBalanceThreshold;
    @SerializedName("bankShortName")
    private String mBankShortName;
    @SerializedName("cardTailNum")
    private String mCardTailNum;
    @SerializedName("deductId")
    private String mDeductId;
    @SerializedName("feeId")
    private int mFeeId;
    @SerializedName("isServiceAvailable")
    private boolean mServiceAvailable;
    @SerializedName("contractStatus")
    private int mStatusCode;

    public int describeContents() {
        return 0;
    }

    public boolean equals(Object obj) {
        if (obj == null || !(obj instanceof DeductInfo)) {
            return false;
        }
        DeductInfo deductInfo = (DeductInfo) obj;
        if (this.mAutoRechargeValid == deductInfo.isAutoRechargeValid() && this.mBalanceThreshold == deductInfo.getBalanceThreshold() && this.mFeeId == deductInfo.getFeeId() && TextUtils.equals(this.mCardTailNum, deductInfo.getCardTailNum()) && TextUtils.equals(this.mBankShortName, deductInfo.getBankShortName()) && this.mStatusCode == deductInfo.getStatusCode() && TextUtils.equals(this.mDeductId, deductInfo.getDeductId()) && this.mServiceAvailable == deductInfo.isServiceAvailable()) {
            return true;
        }
        return false;
    }

    private DeductInfo(Parcel parcel) {
        readFromParcel(parcel);
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeByte(this.mAutoRechargeValid ? (byte) 1 : 0);
        parcel.writeInt(this.mBalanceThreshold);
        parcel.writeInt(this.mFeeId);
        parcel.writeString(this.mCardTailNum);
        parcel.writeString(this.mBankShortName);
        parcel.writeInt(this.mStatusCode);
        parcel.writeString(this.mDeductId);
        parcel.writeByte(this.mServiceAvailable ? (byte) 1 : 0);
    }

    public void readFromParcel(Parcel parcel) {
        boolean z = false;
        this.mAutoRechargeValid = parcel.readByte() == 1;
        this.mBalanceThreshold = parcel.readInt();
        this.mFeeId = parcel.readInt();
        this.mCardTailNum = parcel.readString();
        this.mBankShortName = parcel.readString();
        this.mStatusCode = parcel.readInt();
        this.mDeductId = parcel.readString();
        if (parcel.readByte() == 1) {
            z = true;
        }
        this.mServiceAvailable = z;
    }

    public boolean isDeductible() {
        return this.mStatusCode == 200;
    }

    public boolean isBalanceValid() {
        return this.mBalanceThreshold > 0;
    }

    public boolean isAutoRechargeServiceOpen() {
        return isDeductible() && isAutoRechargeValid() && this.mFeeId > 0 && isServiceAvailable();
    }

    public boolean isBankInfoValid() {
        return isDeductible() && !TextUtils.isEmpty(this.mBankShortName) && !TextUtils.isEmpty(this.mCardTailNum);
    }

    public int getBalanceThreshold() {
        return this.mBalanceThreshold;
    }

    public boolean isAutoRechargeValid() {
        return this.mAutoRechargeValid;
    }

    public int getFeeId() {
        return this.mFeeId;
    }

    public String getCardTailNum() {
        return this.mCardTailNum;
    }

    public String getBankShortName() {
        return this.mBankShortName;
    }

    public int getStatusCode() {
        return this.mStatusCode;
    }

    public String getDeductId() {
        return this.mDeductId;
    }

    public boolean isServiceAvailable() {
        return this.mServiceAvailable;
    }
}
