package com.mi.global.bbs.model;

import android.os.Parcel;
import android.os.Parcelable;

public class ForYouRecoomend implements Parcelable {
    public static final Parcelable.Creator<ForYouRecoomend> CREATOR = new Parcelable.Creator<ForYouRecoomend>() {
        public ForYouRecoomend createFromParcel(Parcel parcel) {
            return new ForYouRecoomend(parcel);
        }

        public ForYouRecoomend[] newArray(int i) {
            return new ForYouRecoomend[i];
        }
    };
    public boolean adminRecommend;
    public String area;
    public String fid;
    public String fup;
    public String icon;
    public String name;
    public String threads;
    public int thumbupstatus;
    public String type;

    public int describeContents() {
        return 0;
    }

    protected ForYouRecoomend(Parcel parcel) {
        this.fid = parcel.readString();
        this.area = parcel.readString();
        this.type = parcel.readString();
        this.name = parcel.readString();
        this.fup = parcel.readString();
        this.threads = parcel.readString();
        this.icon = parcel.readString();
        this.adminRecommend = parcel.readByte() != 0;
        this.thumbupstatus = parcel.readInt();
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.fid);
        parcel.writeString(this.area);
        parcel.writeString(this.type);
        parcel.writeString(this.name);
        parcel.writeString(this.fup);
        parcel.writeString(this.threads);
        parcel.writeString(this.icon);
        parcel.writeByte(this.adminRecommend ? (byte) 1 : 0);
        parcel.writeInt(this.thumbupstatus);
    }
}
