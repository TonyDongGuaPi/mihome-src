package com.mi.global.shop.model;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.gson.annotations.SerializedName;
import com.taobao.weex.el.parse.Operators;

public class UserCentralTip implements Parcelable {
    public static final Parcelable.Creator<UserCentralTip> CREATOR = new Parcelable.Creator<UserCentralTip>() {
        public UserCentralTip createFromParcel(Parcel parcel) {
            return new UserCentralTip(parcel);
        }

        public UserCentralTip[] newArray(int i) {
            return new UserCentralTip[i];
        }
    };
    @SerializedName("img")
    public String mBgImageUrl;
    @SerializedName("tips")
    public String mChickenSoup;
    @SerializedName("greeting")
    public String mTimeGreetings;

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.mBgImageUrl);
        parcel.writeString(this.mTimeGreetings);
        parcel.writeString(this.mChickenSoup);
    }

    public UserCentralTip() {
    }

    private UserCentralTip(Parcel parcel) {
        this.mBgImageUrl = parcel.readString();
        this.mTimeGreetings = parcel.readString();
        this.mChickenSoup = parcel.readString();
    }

    public String toString() {
        return "UserCentralTip{mBgImageUrl='" + this.mBgImageUrl + Operators.SINGLE_QUOTE + ", mTimeGreetings='" + this.mTimeGreetings + Operators.SINGLE_QUOTE + ", mChickenSoup='" + this.mChickenSoup + Operators.SINGLE_QUOTE + Operators.BLOCK_END;
    }
}
