package com.mi.global.bbs.model;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.gson.annotations.SerializedName;

public class CategoryItem implements Parcelable {
    public static final Parcelable.Creator<CategoryItem> CREATOR = new Parcelable.Creator<CategoryItem>() {
        public CategoryItem createFromParcel(Parcel parcel) {
            return new CategoryItem(parcel);
        }

        public CategoryItem[] newArray(int i) {
            return new CategoryItem[i];
        }
    };
    @SerializedName("id")
    protected String id;
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

    public CategoryItem() {
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.id);
        parcel.writeString(this.name);
    }

    protected CategoryItem(Parcel parcel) {
        this.id = parcel.readString();
        this.name = parcel.readString();
    }
}
