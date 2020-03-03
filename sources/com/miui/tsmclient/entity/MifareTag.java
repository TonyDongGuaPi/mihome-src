package com.miui.tsmclient.entity;

import android.os.Parcel;
import android.os.Parcelable;

public class MifareTag implements Parcelable {
    public static final Parcelable.Creator<MifareTag> CREATOR = new Parcelable.Creator<MifareTag>() {
        public MifareTag createFromParcel(Parcel parcel) {
            return new MifareTag(parcel);
        }

        public MifareTag[] newArray(int i) {
            return new MifareTag[i];
        }
    };
    private String mAtqa;
    private String mBlockContent;
    private String mSak;
    private int mSize;
    private String mUid;

    public int describeContents() {
        return 0;
    }

    public MifareTag() {
        this.mAtqa = "0400";
        this.mSak = "08";
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.mUid);
        parcel.writeString(this.mAtqa);
        parcel.writeString(this.mSak);
        parcel.writeInt(this.mSize);
        parcel.writeString(this.mBlockContent);
    }

    public void setUid(String str) {
        this.mUid = str;
    }

    public void setAtqa(String str) {
        this.mAtqa = str;
    }

    public void setSak(String str) {
        this.mSak = str;
    }

    public void setSize(int i) {
        this.mSize = i;
    }

    public void setBlockContent(String str) {
        this.mBlockContent = str;
    }

    public String getUid() {
        return this.mUid;
    }

    public String getAtqa() {
        return this.mAtqa;
    }

    public String getSak() {
        return this.mSak;
    }

    public int getSize() {
        return this.mSize;
    }

    public String getBlockContent() {
        return this.mBlockContent;
    }

    private void readFromParcel(Parcel parcel) {
        this.mUid = parcel.readString();
        this.mAtqa = parcel.readString();
        this.mSak = parcel.readString();
        this.mSize = parcel.readInt();
        this.mBlockContent = parcel.readString();
    }

    private MifareTag(Parcel parcel) {
        readFromParcel(parcel);
    }
}
