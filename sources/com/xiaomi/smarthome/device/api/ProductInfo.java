package com.xiaomi.smarthome.device.api;

import android.os.Parcel;
import android.os.Parcelable;
import com.taobao.weex.el.parse.Operators;

public class ProductInfo implements Parcelable {
    public static final Parcelable.Creator<ProductInfo> CREATOR = new Parcelable.Creator<ProductInfo>() {
        public ProductInfo createFromParcel(Parcel parcel) {
            return new ProductInfo(parcel);
        }

        public ProductInfo[] newArray(int i) {
            return new ProductInfo[i];
        }
    };
    public String desc = "";
    public String iconReal = "";
    public String model;
    public String name = "";
    public int productId;

    public int describeContents() {
        return 0;
    }

    public ProductInfo() {
    }

    public ProductInfo(Parcel parcel) {
        this.productId = parcel.readInt();
        this.model = parcel.readString();
        this.name = parcel.readString();
        this.iconReal = parcel.readString();
        this.desc = parcel.readString();
    }

    private void writeString(String str, Parcel parcel) {
        if (str == null) {
            parcel.writeString("");
        } else {
            parcel.writeString(str);
        }
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(this.productId);
        writeString(this.model, parcel);
        writeString(this.name, parcel);
        writeString(this.iconReal, parcel);
        writeString(this.desc, parcel);
    }

    public String toString() {
        return "ProductInfo{productId=" + this.productId + ", model='" + this.model + Operators.SINGLE_QUOTE + ", name='" + this.name + Operators.SINGLE_QUOTE + ", iconReal='" + this.iconReal + Operators.SINGLE_QUOTE + ", desc='" + this.desc + Operators.SINGLE_QUOTE + Operators.BLOCK_END;
    }
}
