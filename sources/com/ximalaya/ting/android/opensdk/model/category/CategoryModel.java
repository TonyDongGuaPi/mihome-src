package com.ximalaya.ting.android.opensdk.model.category;

import android.os.Parcel;
import android.os.Parcelable;

public class CategoryModel implements Parcelable {
    public static final Parcelable.Creator<CategoryModel> CREATOR = new Parcelable.Creator<CategoryModel>() {
        /* renamed from: a */
        public CategoryModel createFromParcel(Parcel parcel) {
            CategoryModel categoryModel = new CategoryModel();
            categoryModel.a(parcel.readInt());
            categoryModel.a(parcel.readString());
            return categoryModel;
        }

        /* renamed from: a */
        public CategoryModel[] newArray(int i) {
            return new CategoryModel[i];
        }
    };

    /* renamed from: a  reason: collision with root package name */
    private int f2050a;
    private String b;

    public int describeContents() {
        return 0;
    }

    public int a() {
        return this.f2050a;
    }

    public void a(int i) {
        this.f2050a = i;
    }

    public String b() {
        return this.b;
    }

    public void a(String str) {
        this.b = str;
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(this.f2050a);
        parcel.writeString(this.b);
    }
}
