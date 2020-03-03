package com.mi.global.bbs.model;

import android.os.Parcel;
import android.os.Parcelable;

public class HomeFormTitle implements Parcelable {
    public static final Parcelable.Creator<HomeFormTitle> CREATOR = new Parcelable.Creator<HomeFormTitle>() {
        public HomeFormTitle createFromParcel(Parcel parcel) {
            return new HomeFormTitle(parcel);
        }

        public HomeFormTitle[] newArray(int i) {
            return new HomeFormTitle[i];
        }
    };
    private String link;
    private String title;

    public int describeContents() {
        return 0;
    }

    public HomeFormTitle() {
    }

    public HomeFormTitle(String str, String str2) {
        this.title = str;
        this.link = str2;
    }

    protected HomeFormTitle(Parcel parcel) {
        this.title = parcel.readString();
        this.link = parcel.readString();
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.title);
        parcel.writeString(this.link);
    }

    public String getTitle() {
        return this.title;
    }

    public void setTitle(String str) {
        this.title = str;
    }

    public String getLink() {
        return this.link;
    }

    public void setLink(String str) {
        this.link = str;
    }
}
