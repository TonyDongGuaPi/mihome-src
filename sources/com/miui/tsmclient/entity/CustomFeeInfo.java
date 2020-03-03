package com.miui.tsmclient.entity;

import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import android.support.annotation.NonNull;
import com.miui.tsmclient.net.TSMAuthContants;
import com.miui.tsmclient.util.LogUtils;
import org.json.JSONException;
import org.json.JSONObject;

public class CustomFeeInfo extends FeeInfo {
    public static final Parcelable.Creator<CustomFeeInfo> CREATOR = new Parcelable.Creator<CustomFeeInfo>() {
        public CustomFeeInfo createFromParcel(Parcel parcel) {
            return new CustomFeeInfo(parcel);
        }

        public CustomFeeInfo[] newArray(int i) {
            return new CustomFeeInfo[i];
        }
    };
    private static final int INVALID_CUSTOM_FEE = Integer.MIN_VALUE;
    private int mCustomFee;
    private CustomInfo mCustomInfo;

    public boolean isCustomFee() {
        return true;
    }

    public CustomFeeInfo(@NonNull CustomInfo customInfo) {
        this.mCustomFee = Integer.MIN_VALUE;
        this.mCustomInfo = customInfo;
    }

    public int getPayFee() {
        int issueFee = getIssueFee();
        return isValidRechargeFee() ? issueFee + getRechargeFee() : issueFee;
    }

    public int getIssueFee() {
        return this.mCustomInfo.getCustomIssueFee();
    }

    public int getRechargeFee() {
        return this.mCustomFee;
    }

    public boolean isValidRechargeFee() {
        return isValidSection(this.mCustomFee);
    }

    public void setRechargeFee(int i) {
        if (isValidSection(i)) {
            this.mCustomFee = i;
        }
    }

    public boolean isValidSection(int i) {
        return i >= this.mCustomInfo.getMinFee() && i <= this.mCustomInfo.getMaxFee();
    }

    public void restoreRechargeFee() {
        this.mCustomFee = Integer.MIN_VALUE;
    }

    public void restoreIssueFee() {
        this.mCustomInfo.restoreCustomIssueFee();
    }

    public Bundle getCustomFeeExtra(PayableCardInfo payableCardInfo) {
        Bundle bundle = new Bundle();
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put(TSMAuthContants.CONFIG_ID, payableCardInfo.getCustomConfigId());
            jSONObject.put(TSMAuthContants.USE_CUSTOM_FEE, true);
            jSONObject.put(TSMAuthContants.CUSTOM_ISSUE_FEE, getIssueFee());
            jSONObject.put(TSMAuthContants.CUSTOM_RECHARGE_FEE, getRechargeFee());
            jSONObject.put(TSMAuthContants.PARAM_ACTION_TYPE, (payableCardInfo.mHasIssue ? CardActionType.RECHARGE : CardActionType.ISSUE).name());
            bundle.putString(TSMAuthContants.EXTRA_CUSTOM_FEE, jSONObject.toString());
        } catch (JSONException e) {
            LogUtils.e("getCustomFeeExtra called, but occur a error.", e);
        }
        return bundle;
    }

    public int getMinFee() {
        return this.mCustomInfo.getMinFee();
    }

    public int getMaxFee() {
        return this.mCustomInfo.getMaxFee();
    }

    public boolean isValid() {
        return this.mCustomInfo != null;
    }

    private CustomFeeInfo(Parcel parcel) {
        this.mCustomFee = Integer.MIN_VALUE;
        readFromParcel(parcel);
    }

    public int describeContents() {
        return super.describeContents();
    }

    public void writeToParcel(Parcel parcel, int i) {
        super.writeToParcel(parcel, i);
        parcel.writeParcelable(this.mCustomInfo, i);
        parcel.writeInt(this.mCustomFee);
    }

    public void readFromParcel(Parcel parcel) {
        super.readFromParcel(parcel);
        this.mCustomInfo = (CustomInfo) parcel.readParcelable(CustomInfo.class.getClassLoader());
        this.mCustomFee = parcel.readInt();
    }

    public JSONObject serialize() {
        return new JSONObject();
    }
}
