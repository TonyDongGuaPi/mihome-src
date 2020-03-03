package com.miui.tsmclient.entity;

import android.os.Parcel;
import android.os.Parcelable;
import android.text.TextUtils;
import com.miui.tsmclient.database.JSONSerializable;
import com.miui.tsmclient.util.LogUtils;
import org.json.JSONException;
import org.json.JSONObject;

public class CustomInfo implements Parcelable, JSONSerializable, ObjectParser<CustomInfo> {
    public static final Parcelable.Creator<CustomInfo> CREATOR = new Parcelable.Creator<CustomInfo>() {
        public CustomInfo createFromParcel(Parcel parcel) {
            return new CustomInfo(parcel);
        }

        public CustomInfo[] newArray(int i) {
            return new CustomInfo[i];
        }
    };
    private static final String KEY_CUSTOM_ISSUE_FEE = "customIssueFee";
    private static final int KEY_CUSTOM_ISSUE_FEE_DEFAULT = 0;
    private static final String KEY_EXTRA = "extra";
    private static final String KEY_MAX_FEE = "maxFee";
    private static final String KEY_MIN_FEE = "minFee";
    private int mCustomIssueFee;
    private String mExtra;
    private int mMaxFee;
    private int mMinFee;

    public int describeContents() {
        return 0;
    }

    public CustomInfo() {
    }

    private CustomInfo(Parcel parcel) {
        readFromParcel(parcel);
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(this.mMinFee);
        parcel.writeInt(this.mMaxFee);
        parcel.writeInt(this.mCustomIssueFee);
        parcel.writeString(this.mExtra);
    }

    public JSONObject serialize() {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put(KEY_MIN_FEE, this.mMinFee);
            jSONObject.put(KEY_MAX_FEE, this.mMaxFee);
            jSONObject.put("customIssueFee", this.mCustomIssueFee);
            if (!TextUtils.isEmpty(this.mExtra)) {
                jSONObject.put("extra", this.mExtra);
            }
        } catch (JSONException e) {
            LogUtils.e("serialize customInfo info failed.", e);
        }
        return jSONObject;
    }

    public CustomInfo parse(JSONObject jSONObject) {
        if (jSONObject != null) {
            this.mMinFee = jSONObject.optInt(KEY_MIN_FEE);
            this.mMaxFee = jSONObject.optInt(KEY_MAX_FEE);
            this.mCustomIssueFee = jSONObject.optInt("customIssueFee");
            if (jSONObject.has("extra")) {
                this.mExtra = jSONObject.optString("extra");
            }
        }
        return this;
    }

    private void readFromParcel(Parcel parcel) {
        this.mMinFee = parcel.readInt();
        this.mMaxFee = parcel.readInt();
        this.mCustomIssueFee = parcel.readInt();
        this.mExtra = parcel.readString();
    }

    public int getMinFee() {
        return this.mMinFee;
    }

    public int getMaxFee() {
        return this.mMaxFee;
    }

    public int getCustomIssueFee() {
        return this.mCustomIssueFee;
    }

    public void restoreCustomIssueFee() {
        this.mCustomIssueFee = 0;
    }

    public String getExtra() {
        return this.mExtra;
    }
}
