package com.miui.tsmclient.entity;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.gson.annotations.SerializedName;

public class VersionControlInfo implements Parcelable {
    public static final Parcelable.Creator<VersionControlInfo> CREATOR = new Parcelable.Creator<VersionControlInfo>() {
        public VersionControlInfo createFromParcel(Parcel parcel) {
            return new VersionControlInfo();
        }

        public VersionControlInfo[] newArray(int i) {
            return new VersionControlInfo[i];
        }
    };
    @SerializedName("content")
    public String mContent;
    @SerializedName("needConfirm")
    public boolean mNeedConfirm;
    @SerializedName("serviceName")
    public String mServiceName;
    @SerializedName("title")
    public String mTitle;
    @SerializedName("id")
    public long mVersionControlId;

    public int describeContents() {
        return 0;
    }

    public void readFromParcel(Parcel parcel) {
        this.mNeedConfirm = parcel.readByte() != 0;
        this.mVersionControlId = parcel.readLong();
        this.mServiceName = parcel.readString();
        this.mTitle = parcel.readString();
        this.mContent = parcel.readString();
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeByte(this.mNeedConfirm ? (byte) 1 : 0);
        parcel.writeLong(this.mVersionControlId);
        parcel.writeString(this.mServiceName);
        parcel.writeString(this.mTitle);
        parcel.writeString(this.mContent);
    }
}
