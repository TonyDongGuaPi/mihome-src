package com.mi.global.bbs.model;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.gson.annotations.SerializedName;
import java.util.List;

public class CategoryGroup extends CategoryItem {
    public static final Parcelable.Creator<CategoryGroup> CREATOR = new Parcelable.Creator<CategoryGroup>() {
        public CategoryGroup createFromParcel(Parcel parcel) {
            return new CategoryGroup(parcel);
        }

        public CategoryGroup[] newArray(int i) {
            return new CategoryGroup[i];
        }
    };
    @SerializedName("fid")
    protected String fid;
    private List<CategoryItem> types;

    public int describeContents() {
        return 0;
    }

    public String getFid() {
        return this.fid;
    }

    public void setFid(String str) {
        this.fid = str;
    }

    public List<CategoryItem> getTypes() {
        return this.types;
    }

    public void setTypes(List<CategoryItem> list) {
        this.types = list;
    }

    public void writeToParcel(Parcel parcel, int i) {
        super.writeToParcel(parcel, i);
        parcel.writeString(this.fid);
        parcel.writeTypedList(this.types);
    }

    public CategoryGroup() {
    }

    protected CategoryGroup(Parcel parcel) {
        super(parcel);
        this.fid = parcel.readString();
        this.types = parcel.createTypedArrayList(CategoryItem.CREATOR);
    }
}
