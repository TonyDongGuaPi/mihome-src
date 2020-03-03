package com.mi.global.bbs.model;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.gson.annotations.SerializedName;

public class Prize implements Parcelable {
    public static final Parcelable.Creator<Prize> CREATOR = new Parcelable.Creator<Prize>() {
        public Prize createFromParcel(Parcel parcel) {
            return new Prize(parcel);
        }

        public Prize[] newArray(int i) {
            return new Prize[i];
        }
    };
    @SerializedName("id")
    private String id;
    @SerializedName("name")
    private String name;

    public int describeContents() {
        return 0;
    }

    public String getId() {
        return this.id;
    }

    public void setId(String str) {
        this.id = str;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String str) {
        this.name = str;
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.id);
        parcel.writeString(this.name);
    }

    public Prize() {
    }

    public Prize(String str, String str2) {
        this.id = str;
        this.name = str2;
    }

    protected Prize(Parcel parcel) {
        this.id = parcel.readString();
        this.name = parcel.readString();
    }
}
